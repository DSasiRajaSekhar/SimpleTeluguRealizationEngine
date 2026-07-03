package teluguNLG.features;

public enum AdjectiveType {

	/**
	 * 
	 */
	DETERMINER("determiner"){},

	/**
	 *
	 */
	QUANTIFIER("quantifier"){},

      /**
	 * 
	 */
        DESCRIPTIVE("descriptive"){};
      String adjectiveType;

     private AdjectiveType(String name){
           this.adjectiveType=name;
          }

      public static AdjectiveType getAdjectiveType(String name){
             AdjectiveType adj=null;
                for(AdjectiveType n: AdjectiveType.values()){
                 if(n.adjectiveType.equals(name))
                     adj=n;
               }
        return adj;          
         
 }
	
}
