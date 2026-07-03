package teluguNLG.phrase;

import teluguNLG.elements.ProperNounCompoundElement;
import teluguNLG.elements.SOCElement;
import teluguNLG.features.Num;
import teluguNLG.word.NounClass;

public class ProperNounCompoundPhraseBuilder {
	private String properNounCompoundPhrase = "";
	private NounClass nc;

	public ProperNounCompoundPhraseBuilder()
	{
		nc = new NounClass();
	}

	public void setProperNounCompoundPhrase(ProperNounCompoundElement properNounCompound)
	{
		if (properNounCompound == null)
			return;
		if (!"mariyu".equals(properNounCompound.getRelation()))
			return;

		SOCElement firstElement = properNounCompound.getFirstElement();
		SOCElement finalElement = properNounCompound.getFinalElement();
		if (firstElement == null || finalElement == null)
			return;

		String first = getCombiningForm(firstElement.getSOC());
		String second = getFinalPluralForm(finalElement);
		properNounCompoundPhrase = first + " " + second;
	}

	private String getCombiningForm(String word)
	{
		if (word == null)
			return "";
		word = word.trim();
		if (word.endsWith("a"))
			return word.substring(0, word.length() - 1) + "A";
		return word;
	}

	private String getFinalPluralForm(SOCElement finalElement)
	{
		String word = finalElement.getSOC();
		if (word != null && finalElement.getNumber() == Num.PLURAL && word.trim().endsWith("udu"))
			return word.trim().substring(0, word.trim().length() - 2) + "lu";
		return nc.caseInput(finalElement);
	}

	public String getProperNounCompoundPhrase()
	{
		String phrase = properNounCompoundPhrase;
		properNounCompoundPhrase = "";
		return phrase;
	}
}
