package teluguNLG.phrase;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import teluguNLG.word.VerbClass;

import teluguNLG.elements.VAElement;
import teluguNLG.elements.AdverbElement;

public class VerbPhraseBuilder {
	private VerbClass vc;
        
	private AdverbPhraseBuilder apb;
        private String adverbPhrase = " ",verbPhrase=" ",subVerbPhrase = " ";

	public VerbPhraseBuilder()
        {
          apb = new AdverbPhraseBuilder();
          vc = new VerbClass();
          


}
             public void setVerbPhrase(VAElement ve) {

                                
                                 AdverbElement ae=ve.getAdverbElement();
                                
                                 if(ae!=null)
                                  {
                                  apb.setAdverbPhrase(ae);
                                    
		                adverbPhrase=apb.getAdverbPhrase();
                                
                                  }
				subVerbPhrase = vc.verbs(ve);
                             
			}
			
		
		
		

	

            public String getVerbPhrase()
            {
              verbPhrase = adverbPhrase + " " + subVerbPhrase;    
              return verbPhrase;


}

}
