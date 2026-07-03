package teluguNLG.phrase;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.elements.AdjectiveElement;

import teluguNLG.features.Pos;
import teluguNLG.features.AdjectiveType;
public class AdjectiveElementBuilder {
  private AdjectiveElement adj=null;

     public AdjectiveElementBuilder()
     {

       adj = new AdjectiveElement();


}
	 
	public void setAdjectiveElement(Node n) {

		             NodeList nl1 = n.getChildNodes();
                         Node n1=null;
                     if(nl1.item(0).getNodeName().equals("#text"))
                        
				n1 = nl1.item(1);
                        else 
                        n1=nl1.item(0); 
		                NamedNodeMap nm = n1.getAttributes();
		                
				                                
                                adj.setPos(Pos.getPos(nm.getNamedItem("pos").getNodeValue()));
                                adj.setAdjectiveType(AdjectiveType.getAdjectiveType(nm.getNamedItem("type").getNodeValue()));
                                adj.setSuffix(nm.getNamedItem("suffix").getNodeValue().trim());
                                adj.setAdjective(n.getTextContent().trim());
                                

		

	}
     public AdjectiveElement getAdjectiveElement()
     {
        
      return adj;


}

}
