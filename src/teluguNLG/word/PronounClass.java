package teluguNLG.word;

import java.util.regex.*;

import teluguNLG.utils.XmlReader;
import teluguNLG.features.Num;
import teluguNLG.features.Gender;

import teluguNLG.elements.SOCElement;
public class PronounClass

{
	XmlReader xr;// = new XmlReader();

	public String caseInput(SOCElement soc) {
		String str, str4 = " ";

		str = soc.getSOC();
		String case1=soc.getCaseMarker();
		
		Num number = soc.getNumber();
              
		if (number == Num.PLURAL) {
                   
			xr = new XmlReader("xml/pronounplural.xml");
			str = xr.getTextContent(str, str4);
                   
			if (soc.getType().equalsIgnoreCase("nominative"))
				;
			else {
				if (str.equals("memu"))
					str = "mA";
				else if (str.equals("mIru"))
                                      {
                                        if(case1.equals("ni"))
                                        str= "mimmal";
                                        else
					str = "mI";
                                      }
				else {
					str = str.substring(0, str.length() - 1);
					str = str + "a";
				}
			}

		}

		if (soc.getType().equalsIgnoreCase("nominative"))
			;
		else

		{
			if (str.equals("nenu"))
				str = "nA";
			if (str.equals("nuvvu"))
                           {
                            if(case1.equals("nu"))
                             str= "nin";
                            else
				str = "nI";
                            }
			else {
				if (Pattern.matches(".*u", str))
					str = str.replace("u", "i");
			}
                if (str.equals("axi"))
			str = "xAni";

		}
		
		if(case1.equalsIgnoreCase("yoVkka"))
                        case1=" ";


		str = str +case1 + "";
            
		return str;

	}

}
