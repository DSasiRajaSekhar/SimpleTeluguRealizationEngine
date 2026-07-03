package teluguNLG.word;

import java.util.regex.*;

import teluguNLG.elements.AdverbElement;

public class AdvClass {
String str=" ",type=" ";
	public String classAdverb(AdverbElement ad) {
                 
		  str=ad.getAdverb();
                 
                type=ad.getSuffix();
                 
		if (Pattern.matches(".*[u|M]", str) && type.equals("ArA"))

			str = str.replace("u", "a");

		else if (Pattern.matches(".*udu", str))

			str = str.replace("udu", "a");

		else if (Pattern.matches(".*du", str))

			str = str.replace("du", "");

		else

		if (Pattern.matches(".*pu", str))

			;

		str = str + type + "";

		return str;
	}

}
