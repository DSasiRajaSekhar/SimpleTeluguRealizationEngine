package teluguNLG.phrase;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.features.Num;
import teluguNLG.features.Person;
import teluguNLG.features.Gender;
import teluguNLG.features.Pos;

import teluguNLG.word.VerbClass;

import teluguNLG.elements.VAElement;
import teluguNLG.elements.SOCElement;
import teluguNLG.elements.AdverbElement;
public class VAElementBuilder {
	
        private VAElement va=null;
        private AdverbElement ae=null;
	public VAElementBuilder()
        {
          va=new VAElement();



}
            
            public void setVAElement(Node n, Node n1) {
		
                int count=0;
                
                NamedNodeMap attributes = n.getAttributes();
		String type = attributes.getNamedItem("type").getNodeValue();
                va.setVType(type);                 
		SOCElementBuilder sob=new SOCElementBuilder();
                  sob.setSubject(n1,count);
                 SOCElement soc=sob.getSubject();
  
                 NodeList nl=n.getChildNodes();
                          
		for (int k =0; k < nl.getLength(); k++) {
                   if(nl.item(k).getNodeName().equals("#text"))
                      {}
                    else
                     {

			Node n3 = nl.item(k);
			if (n3.getNodeName() == "modifier") {
                                  
                                AdverbElementBuilder aeb=new AdverbElementBuilder();
				aeb.setAdverbElement(n3);
                                  ae=aeb.getAdverbElement();
                                
			} else {
				
				NamedNodeMap nm2 = n3.getAttributes();
				
                                va.setPos(Pos.getPos(nm2.getNamedItem("pos").getNodeValue()));
                                va.setVerb(n3.getTextContent());
				va.setTenseMode(nm2.getNamedItem("tensemode").getNodeValue());			
                                va.setGender(soc.getGender());
				va.setNumber(soc.getNumber());
                                va.setPerson(soc.getPerson());
                                va.setSType(soc.getType());
                                va.setAdverbElement(ae);
                                
			}
			
		}
		
		}

	}

            public VAElement getVAElement()
            {
                
              return this.va;


}

}
