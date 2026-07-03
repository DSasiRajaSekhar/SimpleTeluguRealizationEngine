package teluguNLG.features;

public enum GrammaticalFunction {

	/**
	 * 
	 * 
	 */
	SUBJECT("subject"){},

	/**
	 * 
	 */
	OBJECT("object"){},

       /**
	 * 
	 */
	COMPLEMENT("complement"){},
/**
	 * 
	 */
	VERB("verb"){};

     String sentence;
   private GrammaticalFunction(String name)
  {
   this.sentence=name;
}
	

	public static GrammaticalFunction getGrammaticalFunction(String name){
		GrammaticalFunction sen = null;
		for(GrammaticalFunction n: GrammaticalFunction.values()){
			if(n.sentence.equals(name))
			sen = n;
		}
		return sen;
	}

	
}
