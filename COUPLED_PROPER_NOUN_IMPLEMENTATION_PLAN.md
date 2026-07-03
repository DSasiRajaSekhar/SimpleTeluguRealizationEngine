Coupled Proper Noun Implementation Plan
=======================================

Purpose
-------

This document gives a step-by-step implementation plan for coupled proper nouns
with relation `mariyu`.

The linguistic rule and XML design are documented in:

```text
COUPLED_PROPER_NOUN_RULES.md
```

This plan does not implement the feature. It only defines the controlled order
in which implementation should happen.

Target Feature
--------------

Input roots:

```text
sIwa + rAmudu
subbarAvu + suMxaraM
britan + ameVrikA
```

Target outputs:

```text
sIwA rAmulu
subbarAvu suMxarAlu
britan ameVrikAlu
```

Target XML shape:

```xml
<subject type="nominative">
<propernouncompound relation="mariyu">
<word pos="noun" type="proper" role="first" gender="nonmasculine" number="singular" person="third" casemarker="">sIwa</word>
<word pos="noun" type="proper" role="final" gender="masculine" number="plural" person="third" casemarker="">rAmudu</word>
</propernouncompound>
</subject>
```

Implementation Principles
-------------------------

```text
1. Do not place final surface forms directly in the XML.
2. Do not use <quantifier>, personcount.xml, or maMxi logic.
3. Do not change ordinary noun plural rules unless a real bug is found.
4. Keep the change optional so existing subject/object/complement XML remains valid.
5. Run regression tests after every meaningful step.
```

Step 1: Add Data Holder
-----------------------

Add a new element class:

```text
src/teluguNLG/elements/ProperNounCompoundElement.java
```

It should store:

```text
relation
first noun SOCElement or equivalent fields
final noun SOCElement or equivalent fields
```

Initial choice:

```text
Store first and final as SOCElement objects.
```

Reason:

```text
The final noun can then reuse existing NounClass.caseInput(...) for plural
realization.
```

Checkpoint:

```text
Code compiles.
No XML behavior changes.
Original 14 outputs unchanged.
Krishnamurti outputs unchanged.
```

Step 2: Extend SOCElement
-------------------------

Add an optional field to:

```text
src/teluguNLG/elements/SOCElement.java
```

New optional field:

```text
ProperNounCompoundElement properNounCompound
```

Add getter and setter:

```text
setProperNounCompoundElement(...)
getProperNounCompoundElement()
```

Checkpoint:

```text
Code compiles.
No XML behavior changes.
All previous outputs unchanged.
```

Step 3: Add XML Builder
-----------------------

Add:

```text
src/teluguNLG/phrase/ProperNounCompoundElementBuilder.java
```

Responsibilities:

```text
1. Read <propernouncompound relation="mariyu">.
2. Read exactly two direct <word> children.
3. Build first SOCElement from role="first".
4. Build final SOCElement from role="final".
5. Preserve all grammatical features from XML.
```

Important:

```text
Do not realize surface forms in this builder.
Only parse and store roots/features.
```

Checkpoint:

```text
Code compiles.
No XML behavior changes until SOCElementBuilder is wired.
```

Step 4: Wire SOCElementBuilder
------------------------------

Modify:

```text
src/teluguNLG/phrase/SOCElementBuilder.java
```

Add logic:

```text
if child node is propernouncompound:
    build ProperNounCompoundElement
    attach it to SOCElement
```

Also ensure:

```text
normal <word> handling remains unchanged
normal <modifier> handling remains unchanged
normal <quantifier> handling remains unchanged
```

Checkpoint:

```text
Code compiles.
Existing XML without <propernouncompound> remains unchanged.
```

Step 5: Add Rule-Based Combining Form
-------------------------------------

Do not add a lexical XML lookup for the first noun.

Use a small rule inside the proper-noun compound path:

```text
if the first proper noun ends in short final a:
    replace final a with A
otherwise:
    keep the first proper noun unchanged
```

Examples:

```text
sIwa -> sIwA
subbarAvu -> subbarAvu
britan -> britan
```

Checkpoint:

```text
No ordinary noun, pronoun, modifier, or quantifier behavior changes.
```

Step 6: Add Phrase Builder
--------------------------

Add:

```text
src/teluguNLG/phrase/ProperNounCompoundPhraseBuilder.java
```

Responsibilities:

```text
1. Realize first noun through the rule-based combining-form path.
2. Realize final noun through existing noun plural handling where possible.
3. Join first and final with a single space.
4. Do not insert overt mariyu.
```

Expected internal behavior:

```text
first: sIwa -> sIwA
final: rAmudu + plural -> rAmulu
result: sIwA rAmulu
```

For final proper nouns ending in `udu`, keep the rule inside this compound path:

```text
drop final du and add lu
```

Checkpoint:

```text
Code compiles.
Unit-style narrow checks can be performed through XML after Step 7.
```

Step 7: Wire NounPhraseBuilder
------------------------------

Modify:

```text
src/teluguNLG/phrase/NounPhraseBuilder.java
```

Add logic before ordinary noun/pronoun handling:

```text
if SOCElement has ProperNounCompoundElement:
    realize compound phrase
    return
```

This must come before ordinary noun/pronoun realization because the compound
contains its own two internal nouns.

Checkpoint:

```text
Existing XML without <propernouncompound> unchanged.
Count-noun quantifier examples unchanged.
```

Step 8: Extend Validator
------------------------

Modify:

```text
src/teluguNLG/utils/InputXmlValidator.java
```

Accept:

```text
<propernouncompound relation="mariyu"> inside subject/object/complement
exactly two direct word children
one role="first"
one role="final"
role="final" has number="plural"
```

Reject or warn:

```text
missing relation
relation other than mariyu
missing role
duplicate role
more or fewer than two direct word children
role="final" without number="plural"
```

Checkpoint:

```text
Invalid XML gives a clear message.
Valid old XML remains valid.
Valid count-noun XML remains valid.
```

Step 9: Update Only Three XML Tests
-----------------------------------

Update:

```text
evaluation-krishnamurti/kg_023_sIwA_rAmulu.xml
evaluation-krishnamurti/kg_024_subbarAvu_suMxarAlu.xml
evaluation-krishnamurti/kg_025_britan_ameVrikAlu.xml
```

Use the new controlled XML shape:

```text
subject -> propernouncompound relation="mariyu"
```

Do not update unrelated Krishnamurti examples.
Do not update original 14 examples.

Checkpoint expected outputs:

```text
kg_023_sIwA_rAmulu.xml -> sIwA rAmulu
kg_024_subbarAvu_suMxarAlu.xml -> subbarAvu suMxarAlu
kg_025_britan_ameVrikAlu.xml -> britan ameVrikAlu
```

Step 10: Regression Tests
-------------------------

Run:

```text
original 14 evaluation XML files
all Krishnamurti XML files
count-noun examples
coupled proper-noun examples
```

Expected:

```text
Original 14 unchanged.
Count-noun examples unchanged.
Non-compound Krishnamurti examples unchanged.
Coupled proper-noun examples still produce the same outputs through cleaner XML.
```

Step 11: Documentation Update
-----------------------------

After implementation and tests pass, update:

```text
COUPLED_PROPER_NOUN_RULES.md
evaluation-krishnamurti/BATCH_02_RESULTS.md
possibly Paper.docx
```

Paper update should happen only after the code is implemented and verified.

Do Not Do Yet
-------------

Do not implement:

```text
more than two coupled proper nouns
other relations beyond mariyu
semantic coordination in full sentence logic
broad combining-form rules for all names
```

These may be future extensions after the first controlled implementation.
