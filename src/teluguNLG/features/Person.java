package teluguNLG.features;

public enum Person {

	/**
	 * 
	 */
	FIRST("first"){},

	/**
	 *
	 */
	SECOND("second"){},

      /**
	 * 
	 */
        THIRD("third"){};
      String person;

     private Person(String name){
           this.person=name;
          }

      public static Person getPerson(String name){
             Person per=null;
                for(Person n: Person.values()){
                 if(n.person.equals(name))
                     per=n;
               }
        return per;          
         
 }
	
}
