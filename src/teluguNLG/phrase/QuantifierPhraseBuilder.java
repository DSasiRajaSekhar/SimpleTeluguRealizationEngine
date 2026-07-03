package teluguNLG.phrase;

import teluguNLG.elements.QuantifierElement;
import teluguNLG.word.QuantifierClass;

public class QuantifierPhraseBuilder {
	private QuantifierClass qc;
	private String str="";

	public QuantifierPhraseBuilder()
	{
		qc = new QuantifierClass();
	}

	public void setQuantifierPhrase(QuantifierElement quantifier) {
		str = qc.classQuantifier(quantifier);
	}

	public String getQuantifierPhrase()
	{
		return str;
	}
}
