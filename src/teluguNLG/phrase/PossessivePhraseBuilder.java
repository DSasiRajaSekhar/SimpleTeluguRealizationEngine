package teluguNLG.phrase;

import teluguNLG.elements.PossessiveElement;
import teluguNLG.elements.SOCElement;
import teluguNLG.features.Pos;
import teluguNLG.word.NounClass;
import teluguNLG.word.PronounClass;

public class PossessivePhraseBuilder {
	private NounClass nc;
	private PronounClass pc;
	private String possessivePhrase = "";

	public PossessivePhraseBuilder()
	{
		nc = new NounClass();
		pc = new PronounClass();
	}

	public void setPossessivePhrase(PossessiveElement possessive)
	{
		if (possessive == null || possessive.getPossessor() == null)
			return;

		SOCElement possessor = possessive.getPossessor();
		if (possessor.getPos() == Pos.NOUN)
			possessivePhrase = nc.caseInput(possessor);
		else
			possessivePhrase = pc.caseInput(possessor);
	}

	public String getPossessivePhrase()
	{
		String phrase = possessivePhrase;
		possessivePhrase = "";
		return phrase;
	}
}
