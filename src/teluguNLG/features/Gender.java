

package teluguNLG.features;

/**
 * 
 */

public enum Gender {

	/**
	 * A word or phrase pertaining to a male topic. For example, <em>awanu</em>,
	 * .
	 */
	MASCULINE("masculine"){},

	/**
	 * A word or phrase pertaining to a female or neuter topic. For example, <em>Ame</em>,
	 * <em>axi</em>.
	 */
	NONMASCULINE("nonmasculine"){};
       String gender;

	private Gender(String name){
		this.gender = name;
	}

	public static Gender getGender(String name){
		Gender gen = null;
		for(Gender n: Gender.values()){
			if(n.gender.equals(name))
			gen = n;
		}
		return gen;
	}

	
}
