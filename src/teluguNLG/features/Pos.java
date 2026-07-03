package teluguNLG.features;

public enum Pos {

	/**
	 *
	 */
	NOUN("noun"){},

	/**
	 *
	 */
	PRONOUN("pronoun"){},
        /**
	 *
	 */
	ADJECTIVE("adjective"){},
        /**
	 *
	 */
	VERB("verb"){},
        /**
	 *
	 */
	ADVERB("adverb"){};

        String pos;

      private Pos(String name){
          this.pos=name;
          }

      public static Pos getPos(String name)
        {
           Pos p=null;
              for(Pos n: Pos.values()){
                             if(n.pos.equals(name))
                          p=n;
               }
            return p;    
             
          }
	
}
