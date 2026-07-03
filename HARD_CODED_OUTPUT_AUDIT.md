# Hard-Coded Output Audit

## Purpose

This audit checks whether the TeluguNLG code is manipulating results or directly hard-coding the required output sentences.

## Checks Performed

1. Searched the Java source and XML rule tables for complete expected output sentences.
2. Searched the Java source for example-specific names such as `testInput`, `kg_`, `expected`, and direct output shortcuts.
3. Inspected the generation flow from `TeluguNLG` through `SentenceBuilder`, phrase builders, noun/pronoun/verb classes, and XML rule tables.
4. Separated legitimate lexical/rule resources from sentence-level hardcoding.

## Findings

No complete expected output sentence was found in the active source code or XML rule tables. The code does not appear to branch on evaluation file names, example numbers, or expected output strings.

The generation path is:

```text
input XML
-> InputXmlValidator
-> SentenceBuilder
-> SOCElementBuilder / VAElementBuilder
-> NounPhraseBuilder / VerbPhraseBuilder
-> NounClass / PronounClass / VerbClass / QuantifierClass
-> sentence assembly
```

This means the output is generated from XML structure, features, rules, and lexical exception tables.

## Legitimate Rule and Exception Tables

The project contains rule and exception tables. These are not the same as hard-coded final outputs.

- `src/xml/plural.xml` stores irregular noun plurals, such as `kitiki -> kitikIlu`.
- `src/xml/personcount.xml` stores person-count forms, such as `reMdu -> ixxaru` and `mUdu -> mugguru`.
- `src/xml/palterations.xml` stores verb stem alternations, such as `parugu -> parigeVww`.
- `src/xml/genderinf.xml` stores gender/person/tense suffix information.
- `src/xml/tensemodesuffixes.xml` and `src/xml/tensemodeidentification.xml` support verb TAM processing.

These tables support morphological realization. They are controlled lexical resources, not full-sentence output manipulation.

## Source-Level Rule Logic

Some Java classes contain explicit linguistic rules:

- `PronounClass.java` handles pronoun-specific oblique forms such as `nenu -> nA`.
- `Plural.java` applies general plural formation rules and consults `plural.xml` for irregular plurals.
- `VerbClass.java` checks irregular stem alternations before falling back to regular verb classes.
- `QuantifierClass.java` handles person-count numerals using `personcount.xml`, with a general fallback of numeral plus `maMxi`.
- `ProperNounCompoundPhraseBuilder.java` handles coupled proper noun formation with rule-based suffix behavior, such as first-name final `a -> A` and final `udu` plural handling.

These are rule-based or lexical-exception mechanisms. They do not print required example outputs directly.

## Conclusion

The current code does not show evidence of hard-coded final outputs or manipulation to force required answers.

The outputs are produced through:

```text
input XML features
+ rule classes
+ lexical exception tables
+ sentence assembly
```

The code is therefore suitable for continued controlled extension, provided every new behavior is added as a documented rule, validator check, or clearly justified lexical exception table entry.

## Caution

Some rules are narrow and should be documented when used in the paper. A narrow linguistic rule is acceptable if it is described and tested. What should be avoided is adding complete expected sentence strings or branching on evaluation file names.
