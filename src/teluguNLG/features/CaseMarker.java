package teluguNLG.features;

public enum CaseMarker {

	/**
	 * 
	 * 
	 */
	NI("ni"){},

	/**
	 * 
	 */
	NU("nu"){},

       /**
	 * 
	 */
	lA("la"){},
/**
	 * 
	 */
	GURIMCI("guriMci"){},


/**
	 * 
	 */
	CEWA("cewa"){},

/**
	 * 
	 */
	WO("wo"){},
/**
	 * 
	 */
	KOVRAKU("koVraku"){},
/**
	 * 
	 */
	VALANA("valana"){},
/**
	 * 
	 */
	KI("ki"){},
/**
	 * 
	 */
	KU("ku"){},
/**
	 * 
	 */
	LO("lo"){},
/**
	 * 
	 */
	
	LOPALA("lopala"){},
/**
	 * 
	 */
         AMXU("aMxu"){},
/**
	 * 
	 */
	NA("na"){};

     String cas;

	private CaseMarker(String name){
		this.cas = name;
	}

	public static CaseMarker getCaseMarker(String name){
		CaseMarker cs = null;
		for(CaseMarker n: CaseMarker.values()){
			if(n.cas.equals(name))
			cs = n;
		}
		return cs;
	}

	
}
