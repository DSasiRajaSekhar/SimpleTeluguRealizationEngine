package teluguNLG.phrase;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import teluguNLG.word.AdvClass;

import teluguNLG.elements.AdverbElement;
public class AdverbPhraseBuilder {
  private AdvClass adv;
private String str="";
   public AdverbPhraseBuilder()
    {
     adv = new AdvClass();


}
	

	public void setAdverbPhrase(AdverbElement ae) {
                  
		 str=adv.classAdverb(ae);

		
	}
        public String getAdverbPhrase()
        {
           return str;

}

}
