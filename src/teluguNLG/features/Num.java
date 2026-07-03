package teluguNLG.features;

public enum Num {

	/**
	 *
	 */
	SINGULAR("singular"){},

	/**
	 *
	 */
	PLURAL("plural"){};
        String number;

      private Num(String name){
          this.number=name;
          }

      public static Num getNumber(String name)
        {
           Num num=null;
              for(Num n: Num.values()){
                             if(n.number.equals(name))
                          num=n;
               }
            return num;    
             
          }
	
}
