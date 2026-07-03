package teluguNLG.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import teluguNLG.features.Gender;

public class XmlReader {

	private Element root;

	public XmlReader(String path) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		try {
			File xmlFile = new File(path);
			if (!xmlFile.exists()) {
				xmlFile = new File("src", path);
			}
			doc = builder.parse(xmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		root = doc.getDocumentElement();
		root.normalize();
	}

	/**
	 * @return the root
	 */
	public Element getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Element root) {
		this.root = root;
	}

	public String getFirstLevelAttributeValue(String syntacticFunction,
			String attribute) {
		String str = null;
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {

			Node n = children.item(i);

			if ((n.getNodeName()).equalsIgnoreCase(syntacticFunction)) {
				NamedNodeMap nm = n.getAttributes();
				Node n1 = nm.getNamedItem(attribute);

				str = n.getTextContent();
				str = n1.getNodeValue();

				if (str.equals(""))
					str = "00";
			}
		}

		return str;
	}

	public NodeList getSecondLevelChildrenNodes(String syntacticFunction) {

		NodeList children = root.getChildNodes();

		Node snode = children.item(1);

		NodeList snlist = snode.getChildNodes();
		for (int i = 1; i < snlist.getLength();) {
			Node n1 = snlist.item(i);
			if (n1.getNodeName() == syntacticFunction) {
				return n1.getChildNodes();
			}
		}
		return null;
	}

	public String getSecondLevelTextContent(String syntacticFunction) {
		String str = null;
		NodeList children = root.getChildNodes();
		Node snode = children.item(1);

		NodeList snlist = snode.getChildNodes();
		for (int i = 1; i < snlist.getLength();) {
			Node n1 = snlist.item(i);

			if (n1.getNodeName() == syntacticFunction) {
				str = n1.getTextContent();
			}

		}
		return str;
	}

	public String getTextContent(Gender case1, String tam) {

		String str = "";

		try {

			/*
			 * DocumentBuilderFactory factory = DocumentBuilderFactory
			 * .newInstance(); DocumentBuilder builder =
			 * factory.newDocumentBuilder(); Document doc = builder.parse(path);
			 * 
			 * doc.getDocumentElement().normalize();
			 * 
			 * Node root = doc.getDocumentElement(); root.normalize();
			 */

			NodeList children = root.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {

				Node n = children.item(i);

				if (case1 == Gender.getGender(n.getNodeName())) {

					if (tam == " ") {

						str = n.getTextContent();

					} else {
						NodeList nl = n.getChildNodes();
						for (int j = 0; j < nl.getLength(); j++) {

							Node n1 = nl.item(j);

							if ((n1.getNodeName()).equalsIgnoreCase(tam))

								str = n1.getTextContent();

						}
					}

				}

			}
		} catch (Exception e) {
			System.err.println(e);
			System.exit(0);

		}

		return str;

	}
                 public String getTextContent(String case1, String tam) {

		    String str = "";

		   try {

			/*
			 * DocumentBuilderFactory factory = DocumentBuilderFactory
			 * .newInstance(); DocumentBuilder builder =
			 * factory.newDocumentBuilder(); Document doc = builder.parse(path);
			 * 
			 * doc.getDocumentElement().normalize();
			 * 
			 * Node root = doc.getDocumentElement(); root.normalize();
			 */

			NodeList children = root.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {

				Node n = children.item(i);

				if ((n.getNodeName()).equalsIgnoreCase(case1)) {

					if (tam == " ") {

						str = n.getTextContent();

					} else {
						NodeList nl = n.getChildNodes();
						for (int j = 0; j < nl.getLength(); j++) {

							Node n1 = nl.item(j);

							if ((n1.getNodeName()).equalsIgnoreCase(tam))

								str = n1.getTextContent();

						}
					}

				}

			}
		} catch (Exception e) {
			System.err.println(e);
			System.exit(0);

		}

		return str;

	}

                 public String getTextContentExact(String case1, String tam) {

		    String str = "";

		   try {

			NodeList children = root.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {

				Node n = children.item(i);

				if ((n.getNodeName()).equals(case1)) {

					if (tam == " ") {

						str = n.getTextContent();

					} else {
						NodeList nl = n.getChildNodes();
						for (int j = 0; j < nl.getLength(); j++) {

							Node n1 = nl.item(j);

							if ((n1.getNodeName()).equals(tam))

								str = n1.getTextContent();

						}
					}

				}

			}
		} catch (Exception e) {
			System.err.println(e);
			System.exit(0);

		}

		return str;

	}
}
