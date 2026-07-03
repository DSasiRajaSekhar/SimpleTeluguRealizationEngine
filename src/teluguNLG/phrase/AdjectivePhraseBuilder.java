package teluguNLG.phrase;

import teluguNLG.elements.AdjectiveElement;

import teluguNLG.word.AdjClass;

public class AdjectivePhraseBuilder {
  private AdjClass ac;
  private String str="";
     public AdjectivePhraseBuilder()
     {

       ac = new AdjClass();


}
	
	public void setAdjectivePhrase(AdjectiveElement adj) {

		
                
		str = ac.classAdjective(adj);
                

	}
     public String getAdjectivePhrase()
     {
        
      return str;


}

}
