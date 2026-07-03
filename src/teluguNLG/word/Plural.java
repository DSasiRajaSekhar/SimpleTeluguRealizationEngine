package teluguNLG.word;

import java.util.regex.*;

import teluguNLG.utils.XmlReader;

public class Plural {

	public String plural(String str) {
		String str2 = " ", str1 = " ";
		XmlReader xr = new XmlReader("xml/plural.xml");
		str2 = xr.getTextContent(str, str2);

		if (str2.contentEquals("")) {

//Stem final ending in tti,ttu,ddi or ddu. Words like cettu,reddi etc
			 if (Pattern.matches(".*[d|t][d|t][u|i]", str)) {
				str = str.substring(0, str.length() - 2);
				str2 = "plural1";}
// Stem final ending in tu or ti with an optional M. Words like koti,cotu,poVrabAtu,woMti,baMtu 			
                         else if (Pattern.matches(".*M?[t][u|i]", str)) {
				str = str.substring(0, str.length() - 1);
				str2 = "plural1";}
// Stem final ending in Mdi or Mdu.Words like baMdi,paMdu,guMdu.
			 else if (Pattern.matches(".*Md[u|i]", str)) {
				str = str.substring(0, str.length() - 3);
				str2 = "plural2";}
//Stem final ending in llu,nnu followed by a short vowel.
			 else if (Pattern.matches(".*[a|i|u][n|l][n|l][u]", str)) {
				str = str.substring(0, str.length() - 3);
				str2 = "plural2";}
//Stem final ending in di,du,lu,ru and stems with more than two syllables ending in li and ri. Words like gudi,tammudu,vAkili
			 else if (Pattern.matches(".*[d|l|r][u|i]", str)) {
				str = str.substring(0, str.length() - 2);
				str2 = "plural2";}


// Stem final ending in aM and AM.Words like puswakaM,peMdlAM etc.
			 else if (Pattern.matches(".*[a|A]M", str))

			{
				str = str.substring(0, str.length() - 2);
				str2 = "plural3";}
// Stem final ending in Ayi.Words like abbAyi etc.
			 else if (Pattern.matches(".*Ayi", str))
				str2 = "plural1";
// Stem final ending in yyi.Words like ceVyyi,goVyyi etc.
			else if (Pattern.matches(".*yyi", str)){

				str = str.replace("Vyyi", "wu");
                                str2="plural1";
                         
                        }
			else if (Pattern.matches(".*[a|A|e]*i", str)) {
				str = str.substring(0, str.length() - 1);
				str2 = "plural4";
			} else
				str2 = "plural1";
			xr = new XmlReader("xml/pluralinflections.xml");
			str1 = xr.getTextContent(str2, str1);
			str2 = str + str1;

		}

		return str2;

	}

}
