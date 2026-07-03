package teluguNLG.phrase;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.word.AdvClass;

import teluguNLG.elements.AdverbElement;

import teluguNLG.features.Pos;

public class AdverbElementBuilder {
  private AdverbElement adv;
   public AdverbElementBuilder()
    {
     adv = new AdverbElement();


}
	

	public void setAdverbElement(Node n) {
                               Node n4=null;
		                NodeList nl2 = n.getChildNodes();
                             if(nl2.item(0).getNodeName().equals("#text"))
                                n4=nl2.item(1);
                             else
                                n4=nl2.item(0);

                                NamedNodeMap nm = n4.getAttributes();
				
				 
                                 
                                   
                                adv.setPos(Pos.getPos(nm.getNamedItem("pos").getNodeValue()));
                                adv.setSuffix(nm.getNamedItem("suffix").getNodeValue().trim());
                                adv.setAdverb(n.getTextContent().trim());
		
	}
        public AdverbElement getAdverbElement()
        {
           return this.adv;

}

}
