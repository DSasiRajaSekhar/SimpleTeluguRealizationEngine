package teluguNLG.phrase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.word.RuleReader;
import teluguNLG.features.GrammaticalFunction;

import teluguNLG.elements.SOCElement;
import teluguNLG.elements.VAElement;

public class SentenceBuilder {

private	String sentenceType;
	
private	Node su, vb, ob, co;
private	SOCElementBuilder soc;
private	VAElementBuilder va;
private SOCElement se;
private VAElement ve;
private NounPhraseBuilder npb;
private VerbPhraseBuilder vpb;
	
        GrammaticalFunction sentenceObject;
	HashMap<GrammaticalFunction, List<String>> hm = new HashMap<GrammaticalFunction, List<String>>();
        boolean complement=false;
	boolean verb=false;
        boolean object=false;
	String str;
        int subjectCount=0;
        int complementCount=0;
        int objectCount=0;
        int verbCount=0;

        		List<SOCElement> col=new ArrayList<SOCElement>();
                        List<SOCElement> obl=new ArrayList<SOCElement>();
                        List<VAElement> val=new ArrayList<VAElement>();
                        List<Node> complementNodes=new ArrayList<Node>();
                        List<Node> objectNodes=new ArrayList<Node>();
                        List<String> sl = new ArrayList<String>();
			List<String> cl = new ArrayList<String>();
			List<String> ol = new ArrayList<String>();
			List<String> vl = new ArrayList<String>(); 
        

	public SentenceBuilder() {
		soc = new SOCElementBuilder();
		va = new VAElementBuilder();
                npb=new NounPhraseBuilder();
                vpb=new VerbPhraseBuilder();
	}

	

	public void setSentence(String xmlFile) {
		try {
                       
                        
                  Node snode=null;
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			

			Node root = doc.getDocumentElement();
			root.normalize();
			
			NodeList children = root.getChildNodes();
                  if(children.item(0).getNodeName().equals("#text"))
			snode = children.item(1);
                  else
                  snode=children.item(0);
                  
			NamedNodeMap nm = snode.getAttributes();
			Node n = nm.getNamedItem("type");
			sentenceType = n.getNodeValue();
                        
			if (sentenceType.equals(""))
				sentenceType = "00";
                   Node n1=null;      
			NodeList snlist = snode.getChildNodes();
			for (int i = 0; i < snlist.getLength(); i++) {
                       if(snlist.item(i).getNodeName().equals("#text"))
                       {}
                       else
                        {
				n1= snlist.item(i);
				
                                 sentenceObject= GrammaticalFunction.getGrammaticalFunction(n1.getNodeName());
                                  
                                  if(sentenceObject == GrammaticalFunction.SUBJECT){
				       
					             su=n1;
                                        subjectCount++;
                                        
					          soc.setSubject(su,subjectCount);
                                         se=soc.getSubject();
                                         
                                         npb.setNounPhrase(se,subjectCount,0);
                                         String str=npb.getNounPhrase();
                                        sl.add(str);
                                        hm.put(sentenceObject, sl);
                                       	
                                     
				}

				

				else if (sentenceObject == GrammaticalFunction.COMPLEMENT) {
					 complement=true;
                                         co=n1;  
                                        complementCount++;

					soc.setComplement(co,complementCount);
                                         se=soc.getComplement();
                                          col.add(se);
                                          complementNodes.add(co);
                                         
                                         
                                         
                                        
                         
           
				}
				
				else if (sentenceObject == GrammaticalFunction.OBJECT) {
                                        object=true;
					ob=n1;
                                        objectCount++;

					soc.setObject(ob,objectCount);
                                         se=soc.getObject();
                                         obl.add(se);
                                         objectNodes.add(ob);
                                         
                                        
				}

				else if (sentenceObject == GrammaticalFunction.VERB) {
                                       verb=true;
					vb=n1;
                                       verbCount++;

				}
                      }
                          
                          
			}
                         if(complement){
                         for(int i=0;i<complementCount;i++)
                         {

                           npb.setNounPhrase(col.get(i),complementCount,verbCount);
                           String str=npb.getNounPhrase();
                                         
					cl.add(str);
                                        hm.put(GrammaticalFunction.COMPLEMENT, cl);
                           
}
}

                         if(object){
                         for(int i=0;i<objectCount;i++)
                         {

                           npb.setNounPhrase(obl.get(i),objectCount,verbCount);
                           String str=npb.getNounPhrase();
                                         
					ol.add(str);
                                        hm.put(GrammaticalFunction.OBJECT, ol);
                           
}
}

                         if(verb)
                         {
                         AgreementControllerSelector selector = new AgreementControllerSelector();
                         Node agreementController = selector.select(su, complementNodes, objectNodes);
			 va.setVAElement(vb, agreementController);
                         
                         ve =va.getVAElement();
                      
   
                         vpb.setVerbPhrase(ve);
                         
                        String str=vpb.getVerbPhrase();
                       
                       vl.add(str);
			hm.put(GrammaticalFunction.VERB, vl);
                        }
                         
			

			

		} catch (Exception e) {

			System.err.println(e);
			System.exit(0);

		}
                
          
		
	}

	

	public String getSentence() {
             
           int i;
            String wxSentence="";
            
              List<String> sent=new ArrayList<String>();      
             List<String> subject=hm.get(GrammaticalFunction.SUBJECT);
                 for(i=0;i<subject.size();i++)
                 sent.add(subject.get(i));
                 if(complement)
                 {
                 List<String> complement=hm.get(GrammaticalFunction.COMPLEMENT);
                 for(i=0;i<complement.size();i++)
                 sent.add(complement.get(i));
                 }
                 if(verb)
                 {
                 List<String> verb=hm.get(GrammaticalFunction.VERB);
                for(i=0;i<verb.size();i++)
               sent.add(verb.get(i));
                }
                 for(i=0;i<sent.size();i++)
                
                 wxSentence=wxSentence+sent.get(i).trim()+" "; 
            
             
             
return wxSentence;
                  
                 
	}

}
