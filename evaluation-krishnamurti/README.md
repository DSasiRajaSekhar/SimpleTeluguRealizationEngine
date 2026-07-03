Krishnamurti Telugu sentence XML inputs
======================================

These XML files are derived from `krishnamurti telugu sentences_31.05.2026.docx`.

Most files intentionally follow the same basic XML shape as the current
evaluation inputs. Count-noun examples use the controlled `<quantifier>` element
inside the existing noun phrase structure.

For plural nouns and pronouns, the XML keeps the lexical root in the word text
and uses `number="plural"` so that the existing TeluguNLG code attempts the
inflection.
