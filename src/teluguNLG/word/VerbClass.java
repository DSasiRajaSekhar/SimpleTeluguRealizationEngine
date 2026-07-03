

package teluguNLG.word;

import java.util.regex.Pattern;

import teluguNLG.utils.XmlReader;
import teluguNLG.features.Num;
import teluguNLG.features.Gender;
import teluguNLG.features.Person;

import teluguNLG.elements.VAElement;

public class VerbClass {
	
	public String verbs(VAElement ve) {

		String pAlteration = "", tensemodeSuffix = "", vclass = "",tam=" ";
		XmlReader xr;
                        String tensemode=ve.getTenseMode();
                        String verb=ve.getVerb(); 
                         verb.trim();  
                         xr= new XmlReader("xml/tensemodeidentification.xml");
                          tam=xr.getTextContent(tensemode,tam);
                         String originalVerb=verb;
                         xr = new XmlReader("xml/palterations.xml");
                         String specialPAlteration = xr.getTextContentExact(tam, originalVerb);
                       
		if (!specialPAlteration.equals("")) {
			verb = "";
			vclass = originalVerb;
		}
		else if (Pattern.matches("[^aAiIuUeEoOM]?[aAiIuUeEoOM][V]?[^aAiIuUeEoOM][u][^aAiIuUeEoOMcsl][u]",verb)) {
			if ((tam.contentEquals("tama123")) || (tam.contentEquals("tama4"))
					|| (tam.contentEquals("tama5"))) {
				;//s = s;
			} else if (tam.contentEquals("tamb")) {
				verb = verb.replaceFirst("u", "i");
				verb = verb.replace("u", "");

			} else if (tam.contentEquals("tamc")) {
				verb = verb.replaceFirst("u", "a");
				verb = verb.replace("u", "");

			}
			vclass = "class1a";
		}
                      
		else if (Pattern.matches(".*[^aAiIuUeEoOMvk]?[aAiIuUeEoOM][^aAiIuUeEoOMscynv][^aAiIuUeEoOMtp]?[u]",verb)) {
			verb = verb.substring(0, verb.length() - 1);
			vclass = "class1b";

		}

		else if (Pattern.matches("[^aAiIuUeEoOMvwk]?[AiIUeEoOM][n|l][aAiIuUeEoOM]", verb)) {

			verb = verb.substring(0, verb.length() - 1);

			vclass = "class1c";

		}

		else if (Pattern.matches("[^aAiIIuUeEoOM][aAiIIuUeEoOM][^aAiIIuUeEoOM][u][l][aAiIIuUeEoOM]",verb)) {

			verb = verb.substring(0, verb.length() - 3);

			vclass = "class1d";

		}

		else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM][^aAiIIuUeEoOM][aAiIIuUeEoOM][c][aAiIIuUeEoOM]",verb)) {

			verb = verb.substring(0,verb.length() - 3);

			vclass = "class2a1";

		} else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM][^aAiIIuUeEoOM][aAiIIuUeEoOM][s][aAiIIuUeEoOM]",verb)) {

			verb = verb.substring(0, verb.length() - 3);

			vclass = "class2a2";

		}

		else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM][s|y][aAiIIuUeEoOM]", verb)) {

			verb = verb.substring(0, verb.length() - 2);

			vclass = "class2b1";

		} else if (Pattern.matches("[^aAiIIuUeEoOMc]?[a][c|y][aAiIIuUeEoOM]", verb)) {

			verb = verb.substring(0, verb.length() - 2);

			vclass = "class2b2";

		}

		else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM][c][aAiIIuUeEoOM]",verb)) {

			verb = verb.substring(0, verb.length() - 1);

			vclass = "class3a";

		}

		else if (Pattern.matches(".*[u][c][u]", verb)) {

			verb = verb.substring(0, verb.length() - 2);

			vclass = "class3b";

		}

		else if (Pattern.matches(".*[i][n][c][aAiIIuUeEoOM]", verb)) {

			verb = verb.substring(0, verb.length() - 4);

			vclass = "class3c";

		}

		else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM]V?[t][t][aAiIIuUeEoOM]", verb)) {

			verb = verb.substring(0, verb.length() - 3);

			vclass = "class4a1";

		} else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM][V]?[p][p][aAiIIuUeEoOM]", verb)) {

			verb = verb.substring(0, verb.length() - 3);

			vclass = "class4a2";

		}

		else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM][n][n][aAiIIuUeEoOM]", verb)) {

			verb = verb.substring(0, verb.length() - 3);

			vclass = "class4b1";

		}

		else if (Pattern.matches("[^aAiIIuUeEoOM]?[aAiIIuUeEoOM][n][aAiIIuUeEoOM]?", verb)) {

			if(verb.equalsIgnoreCase("un"))
                          verb=verb.substring(0,verb.length()-1);
                         else
                       verb = verb.substring(0, verb.length() - 2);

			vclass = "class5";

		}

		else {

			vclass = verb;
			verb = "";

		}
               
		xr = new XmlReader("xml/palterations.xml");
		if (!specialPAlteration.equals(""))
			pAlteration = specialPAlteration;
		else
			pAlteration = xr.getTextContent(tam, vclass);
		xr = new XmlReader("xml/tensemodesuffixes.xml");
		tensemodeSuffix = xr.getTextContent(tam, tensemode);
		
		if (vclass.equalsIgnoreCase("class5")) {
			tensemodeSuffix = tensemodeSuffix.replace("w", "t");
                        String gender=ve.getGender().toString();
			if (tensemode.equalsIgnoreCase("pasttense"))  {
                           if(gender.equalsIgnoreCase("MASCULINE"))
				pAlteration = pAlteration + "n";
                                                           
			}
                       
		}
		xr = new XmlReader("xml/genderinf.xml");
                String person=ve.getPerson().toString();
                String gender=ve.getGender().toString();
                 if((ve.getSType().equalsIgnoreCase("other")) && (vclass.equalsIgnoreCase("class5")))
                  {gender="NONMASCULINE";person="THIRD";}
                  else
                   { ;}
                  
                  
		String genderInflection="";
                
		if ((ve.getSType().equalsIgnoreCase("nominative")) && (ve.getPerson() !=Person.THIRD)) {
                        gender="MASCULINE";
                      genderInflection=genderInflection = xr.getTextContent(gender, tensemode);

                             genderInflection=genderInflection.substring(0,genderInflection.length()-2);
				if (ve.getPerson() == Person.FIRST) {
					if (ve.getNumber() == Num.PLURAL)
                                        genderInflection = genderInflection + "mu";
                                       else
					genderInflection = genderInflection + "nu";
				} else if (ve.getPerson() == Person.SECOND)					
					genderInflection = genderInflection + "vu";
			          
				
			}
                       else
                        if(ve.getNumber()==Num.PLURAL){
                          gender="MASCULINE";
                      genderInflection=genderInflection = xr.getTextContent(gender, tensemode);
                         }
                        else if((ve.getSType().equalsIgnoreCase("other")) || (ve.getPerson()==Person.THIRD))
                        genderInflection=genderInflection = xr.getTextContent(gender, tensemode);
                        if((ve.getNumber() == Num.PLURAL)&& (ve.getPerson() != Person.FIRST)) {	
                                genderInflection=genderInflection.substring(0,genderInflection.length()-2);				
					genderInflection = genderInflection + "ru";
                            }
                        
		if((gender.equalsIgnoreCase("MASCULINE"))&& (tensemode.equalsIgnoreCase("pasttense")))
                   {
                    tensemodeSuffix=tensemodeSuffix.substring(0,tensemodeSuffix.length()-1);
                    tensemodeSuffix=tensemodeSuffix+"A";
}
                   
                  
                  if((verb.equalsIgnoreCase("u")) && (tensemode.equalsIgnoreCase("pasttense")) && (gender.equalsIgnoreCase("NONMASCULINE")) && (person.equalsIgnoreCase("THIRD")))
                    verb = verb + "Mxi";
                  else if(vclass.equalsIgnoreCase("le")) 
                        {
                         if((ve.getSType().equalsIgnoreCase("other")) || (gender.equalsIgnoreCase("NONMASCULINE")))
                         verb=vclass+"xu";
                         else if(ve.getNumber()==Num.PLURAL)
                         verb=vclass+"ru";
                         else
                         verb=vclass+"du";
}
                  else                          
		verb = verb + pAlteration + tensemodeSuffix + genderInflection;

		return verb;

	}

}
