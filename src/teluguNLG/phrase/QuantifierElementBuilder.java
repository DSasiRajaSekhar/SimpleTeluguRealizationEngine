package teluguNLG.phrase;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.elements.QuantifierElement;

public class QuantifierElementBuilder {
  private QuantifierElement quantifier=null;

     public QuantifierElementBuilder()
     {
       quantifier = new QuantifierElement();
}

	public void setQuantifierElement(Node n) {

		             NodeList nl1 = n.getChildNodes();
                         Node n1=null;
                     if(nl1.item(0).getNodeName().equals("#text"))
				n1 = nl1.item(1);
                        else
                        n1=nl1.item(0);
		                NamedNodeMap qm = n.getAttributes();

                                quantifier.setType(qm.getNamedItem("type").getNodeValue());
                                quantifier.setCountType(qm.getNamedItem("counttype").getNodeValue());
                                quantifier.setQuantifier(n1.getTextContent().trim());

	}
     public QuantifierElement getQuantifierElement()
     {
      return quantifier;
}

}
