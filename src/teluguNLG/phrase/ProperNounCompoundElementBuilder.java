package teluguNLG.phrase;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.elements.ProperNounCompoundElement;
import teluguNLG.elements.SOCElement;
import teluguNLG.features.Gender;
import teluguNLG.features.Num;
import teluguNLG.features.Person;
import teluguNLG.features.Pos;

public class ProperNounCompoundElementBuilder {
	private ProperNounCompoundElement properNounCompound=null;

	public ProperNounCompoundElementBuilder()
	{
		properNounCompound = new ProperNounCompoundElement();
	}

	public void setProperNounCompoundElement(Node n) {
		NamedNodeMap attributes = n.getAttributes();
		properNounCompound.setRelation(attributes.getNamedItem("relation").getNodeValue());

		NodeList children = n.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child.getNodeName().equals("#text"))
				continue;
			if (!child.getNodeName().equals("word"))
				continue;

			SOCElement wordElement = buildSOCElement(child);
			NamedNodeMap wordAttributes = child.getAttributes();
			String role = wordAttributes.getNamedItem("role").getNodeValue();
			if (role.equals("first"))
				properNounCompound.setFirstElement(wordElement);
			else if (role.equals("final"))
				properNounCompound.setFinalElement(wordElement);
		}
	}

	private SOCElement buildSOCElement(Node n) {
		SOCElement soc = new SOCElement();
		NamedNodeMap attributes = n.getAttributes();

		Pos pos = Pos.getPos(attributes.getNamedItem("pos").getNodeValue());
		soc.setPos(pos);
		String caseMarker = attributes.getNamedItem("casemarker").getNodeValue();
		soc.setCaseMarker(caseMarker);
		Gender gender = Gender.getGender(attributes.getNamedItem("gender").getNodeValue());
		soc.setGender(gender);
		Num number = Num.getNumber(attributes.getNamedItem("number").getNodeValue());
		soc.setNumber(number);
		Person person = Person.getPerson(attributes.getNamedItem("person").getNodeValue());
		soc.setPerson(person);
		soc.setSOC(n.getTextContent().trim());

		return soc;
	}

	public ProperNounCompoundElement getProperNounCompoundElement()
	{
		return properNounCompound;
	}
}
