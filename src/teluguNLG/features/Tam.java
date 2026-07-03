package teluguNLG.features;

public enum Tam {

	/**
	 * 
	 */
	TAMA123("tama123"){},

	/**
	 * 
	 */
	TAMA4("tama4"){},

	
	/**
	 * 
	 */
	TAMA5("tam5"){},
	
	/**
	 *
	 */
	TAMB("tamb"){},
	
	/**
	 * 
	 */
	TAMC("tamc"){};

       String tam;

       private Tam(String name){
           this.tam=name;
          }

      public static Tam getTam(String name){
               Tam t=null;
               for(Tam n: Tam.values()){
               if(n.tam.equals(name))
                 t=n;
              }
          return t;
       }
	
}
