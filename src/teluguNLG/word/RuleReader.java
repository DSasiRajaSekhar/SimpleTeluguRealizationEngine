package teluguNLG.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RuleReader {
	String str, str1, str2;
	List<String> sent = new ArrayList<String>();
	List<String> ser = new ArrayList<String>();

	public List<String> ruleReader(String ruleid, HashMap<String, List<String>> hm) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("xml/ruleset.xml");
			Node root = doc.getDocumentElement();
			NodeList nl = root.getChildNodes();
			for (int i = 1; i < nl.getLength(); i += 2) {

				NamedNodeMap nm = nl.item(i).getAttributes();
				Node n = nm.getNamedItem("id");

				if (n.getNodeValue().equalsIgnoreCase(ruleid)) {
					NodeList nl1 = nl.item(i).getChildNodes();

					for (int j = 1; j < nl1.getLength(); j +=2) {
						Node n1 = nl1.item(j);
						NamedNodeMap attributes = n1.getAttributes();
						String type = attributes.getNamedItem("type").getNodeValue();
						str1 = n1.getTextContent();
						
						if (str1.equals("verb")) {
							ser = (ArrayList<String>) hm.get(str1);
							str = ser.get(0);
							sent.add(str);
						} else {
							str1 = str1+"_"+type;
							ser = (ArrayList<String>) hm.get(str1);
							str = ser.get(0);
							str2 = ser.get(1);
							NamedNodeMap nm1 = n1.getAttributes();
							Node n2 = nm1.getNamedItem("case");
							String case1 = n2.getTextContent();

							if (case1.equals(str2))
								sent.add(str);
						}
					}

				}

			}
		} catch (Exception e) {
			System.err.println(e);
			System.exit(0);
		}
		return sent;
	}

}