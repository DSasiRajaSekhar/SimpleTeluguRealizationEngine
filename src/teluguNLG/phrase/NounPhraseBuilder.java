package teluguNLG.phrase;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.word.NounClass;
import teluguNLG.word.PronounClass;

import teluguNLG.features.Pos;

import teluguNLG.elements.SOCElement;
import teluguNLG.elements.AdjectiveElement;

public class NounPhraseBuilder {
private NounClass nc;
	private PronounClass pc;
	private AdjectivePhraseBuilder apb;
	private QuantifierPhraseBuilder qpb;
	private ProperNounCompoundPhraseBuilder pncpb;
	private PossessivePhraseBuilder ppb;
        private String possessivePhrase = "", quantifierPhrase = "", adjectivePhrase = "", subNounPhrase = "", nounPhrase = "";
       public NounPhraseBuilder()
        {
          apb = new AdjectivePhraseBuilder();
          qpb = new QuantifierPhraseBuilder();
          pncpb = new ProperNounCompoundPhraseBuilder();
          ppb = new PossessivePhraseBuilder();
          nc = new NounClass();
          pc = new PronounClass();

        }
	

	public void setNounPhrase(SOCElement soc,int socCount,int verbCount) {
                         
                        if(soc.getProperNounCompoundElement() != null)
                        {
                         pncpb.setProperNounCompoundPhrase(soc.getProperNounCompoundElement());
                         subNounPhrase=pncpb.getProperNounCompoundPhrase();
                         return;
                        }
                        if(soc.getPossessiveElement() != null)
                        {
                         ppb.setPossessivePhrase(soc.getPossessiveElement());
                         possessivePhrase=ppb.getPossessivePhrase();
                        }
                        if(soc.getQuantifierElement() != null)
                        {
                         qpb.setQuantifierPhrase(soc.getQuantifierElement());
                         quantifierPhrase=qpb.getQuantifierPhrase();
                        }
                        if(soc.getAdjectiveElement() != null)
                        {
			AdjectiveElement adj=soc.getAdjectiveElement();
                         
                         apb.setAdjectivePhrase(adj);
                         
                         adjectivePhrase=apb.getAdjectivePhrase();
                          
                            } 
                                Pos pos=soc.getPos();
				if (pos == null)
					return;
				if (pos == Pos.NOUN) {
					
					subNounPhrase = nc.caseInput(soc);
                                         
				} else {
					

					subNounPhrase = pc.caseInput(soc);

				}
			}
			
		

		
			
                     
                        
		
		

	
        public String getNounPhrase()
        {
          nounPhrase = joinPhrases(possessivePhrase, quantifierPhrase, adjectivePhrase, subNounPhrase);
			possessivePhrase = "";
			quantifierPhrase = "";
			adjectivePhrase = "";
			subNounPhrase = "";
                    
			return nounPhrase;



}

	private String joinPhrases(String first, String second, String third, String fourth) {
		String phrase = "";
		if (first != null)
			first = first.trim();
		if (second != null)
			second = second.trim();
		if (third != null)
			third = third.trim();
		if (fourth != null)
			fourth = fourth.trim();
		if (first != null && !first.equals(""))
			phrase = first;
		if (second != null && !second.equals(""))
			phrase = phrase.equals("") ? second : phrase + " " + second;
		if (third != null && !third.equals(""))
			phrase = phrase.equals("") ? third : phrase + " " + third;
		if (fourth != null && !fourth.equals(""))
			phrase = phrase.equals("") ? fourth : phrase + " " + fourth;
		return phrase;
	}

}
