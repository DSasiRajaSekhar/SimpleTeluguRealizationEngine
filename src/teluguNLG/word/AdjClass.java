package teluguNLG.word;

import java.util.regex.*;

import teluguNLG.elements.AdjectiveElement;


public class AdjClass {
 String s="",case1="";

	public String classAdjective(AdjectiveElement adj) {

		 
                s=adj.getAdjective();
                 case1=adj.getSuffix();
		
		if ((case1.equals("gA")) || (case1.equals("ti"))) {
                          
			if (Pattern.matches(".*lupu", s)) {
                            
				s = s.substring(0, s.length()-4);
				s = s + "la";
			}
                        if (Pattern.matches(".*rupu", s)) {
                              
				s = s.substring(0, s.length()-4);
				s = s + "ra";
			}
                             
		}  
                           else if (case1.equals("aEna")){
                                                   
                                 
			                         
                             s = s.substring(0,s.length()-1);
                             case1="Ena";
                         
                          
		}         else if((case1.equals("")) && (Pattern.matches(".*u",s)))
                         {

                           s=s.substring(0, s.length()-2);
                           s=s+"e";


}

		 

		s = s+case1;
                 
		return s;
	}

}
