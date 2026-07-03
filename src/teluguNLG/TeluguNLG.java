package teluguNLG;

import teluguNLG.phrase.SentenceBuilder;
import teluguNLG.phrase.TeluguUnicode;
import teluguNLG.utils.InputXmlValidator;
import teluguNLG.utils.InvalidInputXmlException;

public class TeluguNLG {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: java -cp build teluguNLG.TeluguNLG <input-xml-file> [--unicode]");
			return;
		}

		String wxSentence;
		try {
			wxSentence = buildSentence(args[0]);
			System.out.println(wxSentence);
		} catch (InvalidInputXmlException e) {
			System.err.println(e.getMessage());
			System.exit(1);
			return;
		}

		if (args.length > 1 && "--unicode".equalsIgnoreCase(args[1])) {
			TeluguUnicode unicode = new TeluguUnicode();
			System.out.println(unicode.convert(wxSentence));
		}
	}

	public static String buildSentence(String xmlFile) {
		InputXmlValidator.validate(xmlFile);
		SentenceBuilder sentenceBuilder = new SentenceBuilder();
		sentenceBuilder.setSentence(xmlFile);
		return sentenceBuilder.getSentence();
	}
}
