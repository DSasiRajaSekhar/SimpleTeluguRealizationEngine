package teluguNLG.phrase;

import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AgreementControllerSelector {

    public Node select(Node subject, List<Node> complements, List<Node> objects) {
        if (isNominative(subject)) {
            return subject;
        }

        if (!"".equals(getType(subject))) {
            return subject;
        }

        Node complement = firstNominative(complements);
        if (complement != null) {
            return complement;
        }

        Node object = firstNominative(objects);
        if (object != null) {
            return object;
        }

        return subject;
    }

    private Node firstNominative(List<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            if (isNominative(node)) {
                return node;
            }
        }
        return null;
    }

    private boolean isNominative(Node node) {
        if (node == null) {
            return false;
        }

        String type = getType(node);
        String caseMarker = getHeadWordCaseMarker(node);

        return ("nominative".equals(type) || "".equals(type)) && "".equals(caseMarker);
    }

    private String getType(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        if (attributes == null || attributes.getNamedItem("type") == null) {
            return "";
        }
        return attributes.getNamedItem("type").getNodeValue().trim();
    }

    private String getHeadWordCaseMarker(Node node) {
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if ("word".equals(child.getNodeName())) {
                NamedNodeMap attributes = child.getAttributes();
                if (attributes != null && attributes.getNamedItem("casemarker") != null) {
                    return attributes.getNamedItem("casemarker").getNodeValue().trim();
                }
            }
        }
        return "";
    }
}
