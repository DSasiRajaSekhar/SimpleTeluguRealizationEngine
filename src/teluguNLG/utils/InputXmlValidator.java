package teluguNLG.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InputXmlValidator {
	public static void validate(String xmlFile) {
		Document doc = parse(xmlFile);
		Element root = doc.getDocumentElement();
		if (root == null || !"document".equals(root.getNodeName())) {
			throw new InvalidInputXmlException("Invalid XML: expected root <document>.");
		}

		Element sentence = firstElementChild(root, "sentence");
		if (sentence == null) {
			throw new InvalidInputXmlException("Invalid XML: expected <sentence> inside <document>.");
		}

		validateSentence(sentence);
	}

	private static Document parse(String xmlFile) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File(xmlFile));
			doc.getDocumentElement().normalize();
			return doc;
		} catch (Exception e) {
			throw new InvalidInputXmlException("Invalid XML: unable to read or parse " + xmlFile + ".", e);
		}
	}

	private static void validateSentence(Element sentence) {
		int subjectIndex = 0;
		int complementIndex = 0;
		int objectIndex = 0;
		int verbIndex = 0;

		NodeList children = sentence.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			Element element = (Element) child;
			String name = element.getNodeName();
			if ("subject".equals(name)) {
				subjectIndex++;
				validateNounLikePhrase(element, "subject", subjectIndex);
			} else if ("complement".equals(name)) {
				complementIndex++;
				validateNounLikePhrase(element, "complement", complementIndex);
			} else if ("object".equals(name)) {
				objectIndex++;
				validateNounLikePhrase(element, "object", objectIndex);
			} else if ("verb".equals(name)) {
				verbIndex++;
				validateVerbPhrase(element, verbIndex);
			}
		}
	}

	private static void validateNounLikePhrase(Element phrase, String phraseName, int index) {
		Element head = directWordChild(phrase);
		Element quantifier = directElementChild(phrase, "quantifier");
		Element possessive = directElementChild(phrase, "possessive");
		Element properNounCompound = directElementChild(phrase, "propernouncompound");
		if (directElementChildCount(phrase, "possessive") > 1) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: only one <possessive> is supported.");
		}
		if (quantifier != null && properNounCompound != null) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <quantifier> and <propernouncompound> cannot be used in the same phrase.");
		}
		if (possessive != null && properNounCompound != null) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <possessive> and <propernouncompound> cannot be used in the same phrase.");
		}
		if (quantifier != null) {
			validateQuantifier(quantifier, phraseName, index);
		}
		if (possessive != null) {
			validatePossessive(possessive, phraseName, index);
		}
		if (properNounCompound != null) {
			validateProperNounCompound(properNounCompound, phraseName, index);
		}
		if (head == null) {
			if (properNounCompound != null) {
				return;
			}
			if (quantifier != null) {
				return;
			}
			Element misplacedHead = wordInsideModifierWithPos(phrase, "noun", "pronoun");
			String message = "Invalid XML in " + phraseName + "[" + index + "]: expected a direct head <word> under <" + phraseName + ">.";
			if (misplacedHead != null) {
				message += " Found a noun/pronoun inside <modifier>; move that head word outside <modifier>.";
			}
			throw new InvalidInputXmlException(message);
		}

		requireAttribute(head, "pos", phraseName, index);
		String pos = head.getAttribute("pos");
		if (!"noun".equals(pos) && !"pronoun".equals(pos)) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: head <word> must have pos=\"noun\" or pos=\"pronoun\".");
		}

		requireAttribute(head, "gender", phraseName, index);
		requireAttribute(head, "number", phraseName, index);
		requireAttribute(head, "person", phraseName, index);
		requireAttribute(head, "casemarker", phraseName, index);
	}

	private static void validatePossessive(Element possessive, String phraseName, int index) {
		if (!possessive.hasAttribute("type")) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <possessive> is missing required attribute \"type\".");
		}
		if (!"genitive".equals(possessive.getAttribute("type"))) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <possessive> currently supports only type=\"genitive\".");
		}

		Element word = directWordChild(possessive);
		if (word == null) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <possessive> must contain a direct <word>.");
		}
		if (directElementChildCount(possessive, "word") != 1) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <possessive> must contain exactly one direct <word>.");
		}

		requireAttribute(word, "pos", phraseName, index);
		String pos = word.getAttribute("pos");
		if (!"noun".equals(pos) && !"pronoun".equals(pos)) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: possessive <word> must have pos=\"noun\" or pos=\"pronoun\".");
		}
		requireAttribute(word, "gender", phraseName, index);
		requireAttribute(word, "number", phraseName, index);
		requireAttribute(word, "person", phraseName, index);
		requireAttribute(word, "casemarker", phraseName, index);
		if (!"yoVkka".equals(word.getAttribute("casemarker"))) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: possessive <word> must have casemarker=\"yoVkka\".");
		}
	}

	private static void validateProperNounCompound(Element properNounCompound, String phraseName, int index) {
		if (!properNounCompound.hasAttribute("relation")) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <propernouncompound> is missing required attribute \"relation\".");
		}
		if (!"mariyu".equals(properNounCompound.getAttribute("relation"))) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <propernouncompound> currently supports only relation=\"mariyu\".");
		}

		int wordCount = 0;
		boolean hasFirst = false;
		boolean hasFinal = false;
		NodeList children = properNounCompound.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			if (!"word".equals(child.getNodeName())) {
				continue;
			}

			wordCount++;
			Element word = (Element) child;
			requireAttribute(word, "pos", phraseName, index);
			requireAttribute(word, "type", phraseName, index);
			requireAttribute(word, "role", phraseName, index);
			requireAttribute(word, "gender", phraseName, index);
			requireAttribute(word, "number", phraseName, index);
			requireAttribute(word, "person", phraseName, index);
			requireAttribute(word, "casemarker", phraseName, index);

			if (!"noun".equals(word.getAttribute("pos"))) {
				throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: proper noun compound words must have pos=\"noun\".");
			}
			if (!"proper".equals(word.getAttribute("type"))) {
				throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: proper noun compound words must have type=\"proper\".");
			}

			String role = word.getAttribute("role");
			if ("first".equals(role)) {
				hasFirst = true;
			} else if ("final".equals(role)) {
				hasFinal = true;
				if (!"plural".equals(word.getAttribute("number"))) {
					throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: role=\"final\" must have number=\"plural\".");
				}
			} else {
				throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: proper noun compound role must be \"first\" or \"final\".");
			}
		}

		if (wordCount != 2 || !hasFirst || !hasFinal) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <propernouncompound> must contain exactly one role=\"first\" word and one role=\"final\" word.");
		}
	}

	private static void validateQuantifier(Element quantifier, String phraseName, int index) {
		if (!quantifier.hasAttribute("type")) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <quantifier> is missing required attribute \"type\".");
		}
		if (!quantifier.hasAttribute("counttype")) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <quantifier> is missing required attribute \"counttype\".");
		}

		Element word = directWordChild(quantifier);
		if (word == null) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: <quantifier> must contain a direct <word>.");
		}
		requireAttribute(word, "pos", phraseName, index);
		if (!"numeral".equals(word.getAttribute("pos"))) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: quantifier <word> must have pos=\"numeral\".");
		}
	}

	private static void validateVerbPhrase(Element verb, int index) {
		Element head = directWordChild(verb);
		if (head == null) {
			throw new InvalidInputXmlException("Invalid XML in verb[" + index + "]: expected a direct verb head <word> under <verb>.");
		}

		requireAttribute(head, "pos", "verb", index);
		if (!"verb".equals(head.getAttribute("pos"))) {
			throw new InvalidInputXmlException("Invalid XML in verb[" + index + "]: head <word> must have pos=\"verb\".");
		}
		requireAttribute(head, "tensemode", "verb", index);
	}

	private static Element firstElementChild(Element parent, String childName) {
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE && childName.equals(child.getNodeName())) {
				return (Element) child;
			}
		}
		return null;
	}

	private static Element directWordChild(Element parent) {
		return directElementChild(parent, "word");
	}

	private static Element directElementChild(Element parent, String childName) {
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE && childName.equals(child.getNodeName())) {
				return (Element) child;
			}
		}
		return null;
	}

	private static int directElementChildCount(Element parent, String childName) {
		int count = 0;
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE && childName.equals(child.getNodeName())) {
				count++;
			}
		}
		return count;
	}

	private static Element wordInsideModifierWithPos(Element phrase, String firstPos, String secondPos) {
		NodeList children = phrase.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeType() != Node.ELEMENT_NODE || !"modifier".equals(child.getNodeName())) {
				continue;
			}

			NodeList modifierChildren = child.getChildNodes();
			for (int j = 0; j < modifierChildren.getLength(); j++) {
				Node modifierChild = modifierChildren.item(j);
				if (modifierChild.getNodeType() != Node.ELEMENT_NODE || !"word".equals(modifierChild.getNodeName())) {
					continue;
				}

				Element word = (Element) modifierChild;
				String pos = word.getAttribute("pos");
				if (firstPos.equals(pos) || secondPos.equals(pos)) {
					return word;
				}
			}
		}
		return null;
	}

	private static void requireAttribute(Element element, String attribute, String phraseName, int index) {
		if (!element.hasAttribute(attribute)) {
			throw new InvalidInputXmlException("Invalid XML in " + phraseName + "[" + index + "]: head <word> is missing required attribute \"" + attribute + "\".");
		}
	}
}
