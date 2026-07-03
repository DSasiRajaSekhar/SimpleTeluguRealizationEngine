package teluguNLG.word;

import teluguNLG.elements.QuantifierElement;
import teluguNLG.utils.XmlReader;

public class QuantifierClass {

	public String classQuantifier(QuantifierElement quantifier) {
		String str = quantifier.getQuantifier();
		String type = quantifier.getType();
		String countType = quantifier.getCountType();

		if (str == null)
			return "";

		if (!"numeral".equalsIgnoreCase(type))
			return str;

		if ("object".equalsIgnoreCase(countType))
			return str;

		if ("person".equalsIgnoreCase(countType)) {
			XmlReader xr = new XmlReader("xml/personcount.xml");
			String mapped = xr.getTextContentExact(str, " ");
			if (!mapped.equals(""))
				return mapped;
			return str + " maMxi";
		}

		return str;
	}
}
