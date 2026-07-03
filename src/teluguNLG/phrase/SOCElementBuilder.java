package teluguNLG.phrase;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import teluguNLG.features.Pos;
import teluguNLG.features.Num;
import teluguNLG.features.Gender;
import teluguNLG.features.Person;
import teluguNLG.features.AdjectiveType;
import teluguNLG.features.GrammaticalFunction;

import teluguNLG.elements.SOCElement;
import teluguNLG.elements.AdjectiveElement;
import teluguNLG.elements.QuantifierElement;
import teluguNLG.elements.ProperNounCompoundElement;
import teluguNLG.elements.PossessiveElement;

public class SOCElementBuilder {

private SOCElement soc;
private AdjectiveElement ae;
private QuantifierElement qe;
private ProperNounCompoundElement pne;
private PossessiveElement pe;

        public SOCElementBuilder()
        {
         soc=new SOCElement();
}	
	

	private void setSOCElement(Node n,int count) {
                         
                               soc=new SOCElement();
			String case1 ;
                          
                        NamedNodeMap attributes = n.getAttributes();
				String type = attributes.getNamedItem("type").getNodeValue();
                               soc.setType(type);
                               soc.setAdjectiveElement(null);
                               soc.setQuantifierElement(null);
                               soc.setProperNounCompoundElement(null);
                               soc.setPossessiveElement(null);
                               NodeList nl=n.getChildNodes();
		for (int j =0; j < nl.getLength(); j++) {
                   if(nl.item(j).getNodeName().equals("#text"))
                  {}
                   else
                   {

			Node n2 = nl.item(j);
                                 
                               
	                      if (n2.getNodeName().equals("modifier")){
                                 AdjectiveElementBuilder aeb=new AdjectiveElementBuilder();
                                    aeb.setAdjectiveElement(n2);
				    ae=aeb.getAdjectiveElement();
                                    soc.setAdjectiveElement(ae);
                            }
	                      else if (n2.getNodeName().equals("quantifier")){
                                 QuantifierElementBuilder qeb=new QuantifierElementBuilder();
                                    qeb.setQuantifierElement(n2);
				    qe=qeb.getQuantifierElement();
                                    soc.setQuantifierElement(qe);
                            }
	                      else if (n2.getNodeName().equals("propernouncompound")){
                                 ProperNounCompoundElementBuilder pneb=new ProperNounCompoundElementBuilder();
                                    pneb.setProperNounCompoundElement(n2);
				    pne=pneb.getProperNounCompoundElement();
                                    if(pne.getFirstElement() != null)
                                     pne.getFirstElement().setType(type);
                                    if(pne.getFinalElement() != null)
                                     pne.getFinalElement().setType(type);
                                    soc.setProperNounCompoundElement(pne);
                            }
	                      else if (n2.getNodeName().equals("possessive")){
                                 PossessiveElementBuilder peb=new PossessiveElementBuilder();
                                    peb.setPossessiveElement(n2);
				    pe=peb.getPossessiveElement();
                                    soc.setPossessiveElement(pe);
                            }
                        else{	
                                GrammaticalFunction gf=GrammaticalFunction.getGrammaticalFunction(n2.getNodeName());
                               // soc.setGrammaticalFunction(gf);		
				NamedNodeMap nm1 = n2.getAttributes();
				Pos pos = Pos.getPos(nm1.getNamedItem("pos").getNodeValue());
                                 soc.setPos(pos);    
				 case1 = nm1.getNamedItem("casemarker").getNodeValue();
                                soc.setCaseMarker(case1);
				Gender gender = Gender.getGender(nm1.getNamedItem("gender").getNodeValue());
                                 soc.setGender(gender);
				Num number = Num.getNumber(nm1.getNamedItem("number").getNodeValue());
                                soc.setNumber(number);
                                Person person=Person.getPerson(nm1.getNamedItem("person").getNodeValue());
                                soc.setPerson(person);
				String str = n2.getTextContent();
                                soc.setSOC(str);
                               // soc.setCount(count);
				}
			}
			
		}

		
			
                     
                        
		
		

	}
        public void setSubject(Node n,int count)
        {
         setSOCElement(n,count);

}
        public SOCElement getSubject()
        {
          
                      
			return this.soc;

}
 public void setObject(Node n,int count)
{
 setSOCElement(n,count);

}
public SOCElement getObject()
{
 return this.soc;
}
public void setComplement(Node n,int count)
{
 setSOCElement(n,count);

}
public SOCElement getComplement()
{
 return this.soc;
}

}
