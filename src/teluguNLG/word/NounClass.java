package teluguNLG.word;

import java.util.regex.*;
import teluguNLG.utils.XmlReader;

import teluguNLG.features.Gender;
import teluguNLG.features.Num;

import teluguNLG.elements.SOCElement;
public class NounClass

{

	public String caseInput(SOCElement soc) {
		String str=soc.getSOC();
                str.trim();
		String case1=soc.getCaseMarker();
		
		Num number = soc.getNumber();
                Gender gender=soc.getGender();
		if (number == Num.PLURAL) {

			Plural p = new Plural();
			str = p.plural(str);
			if (soc.getType().equalsIgnoreCase("nominative"))
				{}
			else {
				str = str.substring(0, str.length() - 1);
				str = str + "a";
			}

		}
                  
		if (soc.getType().equalsIgnoreCase("nominative"))
			{}
                    
		else {
                        
			if (Pattern.matches(".*Vyyi", str)) {
				str = str.substring(0, str.length() - 4);
				str = str + "wi";
			} 
			  if (Pattern.matches(".*[n|l][n|l]u", str)) {
				str = str.substring(0, str.length() - 3);
				str = str + "Mti";
                           
			}
                         if (Pattern.matches(".*[^U][d|l|r][u|i]", str)) {

				if (gender ==Gender.NONMASCULINE) {
					str = str.substring(0, str.length() - 2);
					str = str + "ti";
				}
                            }

			  if (Pattern.matches(".*[d|r|l|n]u", str)) {
				str = str.substring(0, str.length() - 1);
				str = str + "i";
                             
			}   if (Pattern.matches(".*M", str)) {
				if (case1.equalsIgnoreCase("ki")) {
					str = str.substring(0, str.length() - 3);
					str = str + "Aniki";

				}
                            

			}
                           
		}
                 if(case1.equalsIgnoreCase("yoVkka"))
                        case1=" ";

		

		str = str + case1 + "";
                
		return str;

	}

}
