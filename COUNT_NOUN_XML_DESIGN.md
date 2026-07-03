Count Noun XML Design
=====================

Purpose
-------

This document defines the proposed XML representation for count nouns and
person-count expressions before any implementation begins.

No Java code is implemented by this document.

Design Goal
-----------

Represent numeral/count information in the input XML without storing the final
surface form.

For example, the XML should store:

```text
reMdu + person-count + prakAsaM plural
```

not:

```text
ixxaru prakAsAlu
```

The surface realizer should generate `ixxaru`.

Noun Phrase Structure
---------------------

A subject, object, or complement may contain:

```text
optional quantifier
optional modifier
required head word
```

Recommended order:

```text
quantifier -> modifier -> head word
```

This keeps quantifiers separate from adjective modifiers.

Subject Example
---------------

```xml
<subject type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">reMdu</word>
</quantifier>
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">prakAsaM</word>
</subject>
```

Expected output:

```text
ixxaru prakAsAlu
```

Complement Example
------------------

```xml
<complement type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">mUdu</word>
</quantifier>
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">rAmamurwi</word>
</complement>
```

Expected output:

```text
mugguru rAmamurwulu
```

Non-Person Object Count Example
-------------------------------

```xml
<complement type="nominative">
<quantifier type="numeral" counttype="object">
<word pos="numeral">reMdu</word>
</quantifier>
<word pos="noun" gender="nonmasculine" number="plural" person="third" casemarker="">pustakaM</word>
</complement>
```

Expected output:

```text
reMdu pustakAlu
```

Larger Person Count Example
---------------------------

```xml
<subject type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">iravE Exu</word>
</quantifier>
</subject>
```

Expected output:

```text
iravE Exu maMxi
```

Attributes
----------

`quantifier` attributes:

```text
type="numeral"
counttype="person" | "object"
```

`word` attributes:

```text
pos="numeral"
```

Initial implementation can focus only on:

```text
type="numeral"
counttype="person"
counttype="object"
```

Other count types are planned later, not in the first implementation:

```text
time
amount
measure
```

Person Count Realization
------------------------

For `counttype="person"`:

```text
reMdu -> ixxaru
mUdu -> mugguru
nAlugu -> naluguru
```

If the numeral expression is not in the special person-count table:

```text
numeral expression + maMxi
```

Examples:

```text
iravE Exu -> iravE Exu maMxi
nAlugu vela -> nAlugu vela maMxi
aravE okka -> aravE okka maMxi
```

Non-Person Count Realization
----------------------------

For the first implementation, `counttype="object"` keeps the quantifier as the
input numeral expression:

```text
reMdu pustakAlu
```

Later Extension: Time Quantifier
--------------------------------

Time quantifiers count time nouns:

```text
reMdu rojulu
mUdu nelalu
reMdu gaMtalu
```

Possible future XML:

```xml
<subject type="nominative">
<quantifier type="numeral" counttype="time">
<word pos="numeral">reMdu</word>
</quantifier>
<word pos="noun" gender="nonmasculine" number="plural" person="third" casemarker="">roju</word>
</subject>
```

Expected output:

```text
reMdu rojulu
```

This should not be part of the first implementation.

Later Extension: Amount Quantifier
----------------------------------

Amount quantifiers express how much of a mass, abstract quantity, or extent is
involved:

```text
koMwa kAlaM
cAla dabbu
sagaM pAlu
anni pustakAlu
```

These are not always numerals. They may require:

```text
type="amount"
```

or a later distinction between numeral quantifiers and non-numeral quantity
words.

This should not be part of the first implementation.

Later Extension: Measure Quantifier and Unit
--------------------------------------------

Measure quantifiers require one more internal feature: `unit`.

Example:

```text
reMdu kilolu biyyam
```

Analysis:

```text
number = reMdu
unit root = kilo
unit number = plural
measured noun = biyyam
```

Possible future XML:

```xml
<complement type="nominative">
<quantifier type="measure">
<number>
<word pos="numeral">reMdu</word>
</number>
<unit>
<word pos="noun" gender="nonmasculine" number="plural" person="third" casemarker="">kilo</word>
</unit>
</quantifier>
<word pos="noun" gender="nonmasculine" number="singular" person="third" casemarker="">biyyam</word>
</complement>
```

Expected output:

```text
reMdu kilolu biyyam
```

This should be a later implementation because it needs nested quantifier
structure and unit noun realization.

What This Design Avoids
-----------------------

Do not represent `reMdu` as an ordinary noun:

```xml
<word pos="noun" number="plural">reMdu</word>
```

Do not add person-count forms to `plural.xml`:

```xml
<reMdu>ixxaru</reMdu>
```

Do not represent numerals as descriptive adjectives:

```xml
<word pos="adjective" type="descriptive">reMdu</word>
```

Reason
------

Person-count forms are not ordinary noun plurals. They are numeral/count
realizations conditioned by the counted entity.

Implementation Boundary
-----------------------

When implementation begins, the change should be isolated to:

```text
quantifier parsing
quantifier data holder
quantifier phrase realization
noun phrase assembly
person-count lookup table
```

It should not change:

```text
noun plural rules
existing adjective modifier behavior
verb agreement
existing baseline XML behavior
```

First Implementation Scope
--------------------------

The first implementation should support only:

```text
counttype="person"
counttype="object"
```

Required test cases:

```text
reMdu + prakAsaM plural, person -> ixxaru prakAsAlu
mUdu + rAmamurwi plural, person -> mugguru rAmamurwulu
reMdu + pustakaM plural, object -> reMdu pustakAlu
iravE Exu, person -> iravE Exu maMxi
```

Step 3 Design: Internal Data Holder
-----------------------------------

When implementation begins, add a data holder for quantifier information.

Recommended class name:

```text
QuantifierElement
```

Purpose:

```text
Store quantifier information read from XML.
Do not generate surface forms.
Do not apply grammar rules.
```

Fields:

```text
type
countType
quantifier
```

Example values:

```text
type = numeral
countType = person
quantifier = reMdu
```

or:

```text
type = numeral
countType = object
quantifier = reMdu
```

Initial allowed values:

```text
type: numeral
countType: person, object
```

Fields that should not be added in the first implementation:

```text
unit
measure
amount subtype
time subtype
```

Reason:

```text
Those belong to later extensions after person/object count behavior is stable.
```

Step 4 Design: SOCElement Extension
-----------------------------------

The existing noun phrase element should receive an optional quantifier field,
parallel to the existing optional adjective/modifier field.

Current conceptual structure:

```text
SOCElement
  optional AdjectiveElement
  head word data
```

Future conceptual structure:

```text
SOCElement
  optional QuantifierElement
  optional AdjectiveElement
  head word data
```

Important behavior:

```text
If no quantifier is present, output must remain exactly as before.
If a quantifier is present, it is realized before the adjective modifier and head word.
```

Phrase order:

```text
quantifier -> modifier -> head word
```

Examples:

```text
reMdu + prakAsaM plural -> ixxaru prakAsAlu
reMdu + aMxamEna + pustakaM plural -> reMdu aMxamEna pustakAlu
```

Implementation boundary:

```text
SOCElement stores the quantifier.
SOCElementBuilder reads it from XML.
NounPhraseBuilder assembles the realized quantifier before the existing modifier/head noun phrase.
```

What must not happen:

```text
SOCElement should not pluralize reMdu.
SOCElement should not decide ixxaru.
NounClass should not receive reMdu as a noun for person-count realization.
```

Step 5 Design: XML Builder Support
----------------------------------

When implementation begins, subject/object/complement parsing should recognize
three possible child elements:

```text
quantifier
modifier
word
```

Allowed noun phrase pattern:

```xml
<subject type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">reMdu</word>
</quantifier>
<modifier>
<word pos="adjective" type="descriptive" suffix="">aMxamEna</word>
</modifier>
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">prakAsaM</word>
</subject>
```

The same pattern should be allowed inside:

```text
subject
object
complement
```

Builder behavior:

```text
if child node is quantifier:
    build QuantifierElement
    attach it to SOCElement

if child node is modifier:
    use existing AdjectiveElementBuilder
    attach it to SOCElement

if child node is word:
    read head noun/pronoun as existing code does
```

Required and optional elements:

```text
quantifier: optional
modifier: optional
head word: required
```

The builder must continue to support the existing pattern:

```xml
<complement type="nominative">
<modifier>
<word pos="adjective" type="descriptive" suffix="">A</word>
</modifier>
<word pos="noun" gender="masculine" number="singular" person="third" casemarker="">saMgawi</word>
</complement>
```

So existing modifier behavior must not change.

Recommended new builder:

```text
QuantifierElementBuilder
```

It should read:

```text
quantifier/@type
quantifier/@counttype
quantifier/word/@pos
quantifier/word text
```

Example input:

```xml
<quantifier type="numeral" counttype="person">
<word pos="numeral">reMdu</word>
</quantifier>
```

Expected parsed values:

```text
type = numeral
countType = person
quantifier = reMdu
```

Validation expectations:

```text
No quantifier present: valid
No modifier present: valid
No head word present: invalid
Unsupported counttype: invalid or clear validation warning
Unsupported quantifier type: invalid or clear validation warning
```

Initial allowed values:

```text
quantifier/@type = numeral
quantifier/@counttype = person | object
quantifier/word/@pos = numeral
```

Implementation boundary:

```text
Do not change AdjectiveElementBuilder behavior.
Do not change NounClass behavior.
Do not make quantifier mandatory.
Do not parse quantifier as a noun.
```

Step 6 Design: Quantifier Realizer
----------------------------------

When implementation begins, add a separate quantifier realization path.

Recommended class names:

```text
QuantifierPhraseBuilder
QuantifierClass
```

The realizer receives a `QuantifierElement`:

```text
type = numeral
countType = person
quantifier = reMdu
```

or:

```text
type = numeral
countType = object
quantifier = reMdu
```

Object Count Rule
-----------------

For `countType="object"`, return the numeral unchanged.

Examples:

```text
reMdu -> reMdu
mUdu -> mUdu
```

Full noun phrase examples:

```text
reMdu + pustakaM plural -> reMdu pustakAlu
mUdu + puli plural -> mUdu pululu
```

Person Count Rule
-----------------

For `countType="person"`, first look up the numeral in a separate
person-count table.

Recommended table:

```text
src/xml/personcount.xml
```

Recommended contents:

```xml
<?xml version="1.0" encoding="utf-8"?>
<personcount>
<reMdu>ixxaru</reMdu>
<mUdu>mugguru</mUdu>
<nAlugu>naluguru</nAlugu>
<Exu>Exuguru</Exu>
<Aru>Aruguru</Aru>
<edu>eduguru</edu>
<enimixi>enamanduguru</enimixi>
<wommixi>wommanduguru</wommixi>
</personcount>
```

If the numeral is found:

```text
reMdu -> ixxaru
mUdu -> mugguru
nAlugu -> naluguru
Exu -> Exuguru
Aru -> Aruguru
edu -> eduguru
enimixi -> enamanduguru
wommixi -> wommanduguru
```

If the numeral expression is not found:

```text
return numeral expression + " maMxi"
```

Examples:

```text
iravE Exu -> iravE Exu maMxi
nAlugu vela -> nAlugu vela maMxi
aravE okka -> aravE okka maMxi
```

Realizer Pseudocode
-------------------

```text
if quantifier is absent:
    return empty string

if type is not numeral:
    return quantifier unchanged for first implementation

if countType is object:
    return quantifier unchanged

if countType is person:
    mapped = lookup quantifier in personcount.xml
    if mapped exists:
        return mapped
    else:
        return quantifier + " maMxi"
```

Boundary
--------

The quantifier realizer must not:

```text
call NounClass for reMdu
add reMdu to plural.xml
change ordinary noun plural behavior
change adjective modifier behavior
```

The quantifier realizer may:

```text
read personcount.xml
return a quantifier surface form
leave object-count numerals unchanged
append maMxi for larger/unlisted person-count expressions
```

Step 8 Design: Final Person Count Rule Order
--------------------------------------------

The first implementation should follow this exact rule order.

Ordered rules:

```text
1. If the noun phrase has no quantifier:
       return no quantifier surface form.

2. If quantifier/@type is not "numeral":
       leave the quantifier unchanged in the first implementation.

3. If quantifier/@counttype is "object":
       return the numeral unchanged.

4. If quantifier/@counttype is "person":
       look up the numeral expression in personcount.xml.

5. If the person-count lookup is found:
       return the mapped person-count form.

6. If the person-count lookup is not found:
       return numeral expression + " maMxi".

7. After quantifier realization, realize the head noun normally.
```

Important separation:

```text
The quantifier is realized by quantifier logic.
The head noun is realized by existing noun/pronoun logic.
```

So:

```text
reMdu + prakAsaM plural
```

must become:

```text
ixxaru + prakAsAlu
```

not:

```text
ixxaru + prakAsaM
```

and not:

```text
reMdu + prakAsAlu
```

Required examples:

```text
reMdu + prakAsaM plural, person -> ixxaru prakAsAlu
mUdu + rAmamurwi plural, person -> mugguru rAmamurwulu
reMdu + pustakaM plural, object -> reMdu pustakAlu
iravE Exu, person -> iravE Exu maMxi
```

Regression expectation:

```text
All noun phrases without a quantifier must remain unchanged.
All existing adjective modifier behavior must remain unchanged.
All existing noun plural behavior must remain unchanged.
```

Step 9 Design: Target Count Test XML Updates
--------------------------------------------

After implementation is complete, update only the count-related test XML files
needed to verify the feature.

Do not update these XML files before implementation, because the current code
does not understand `<quantifier>`.

Existing file to update later:

```text
evaluation-krishnamurti/kg_021_ixxaru_prakAsAlu.xml
```

Target structure:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?><document>
<sentence type="">
<subject type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">reMdu</word>
</quantifier>
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">prakAsaM</word>
</subject>
</sentence>
</document>
```

Expected output:

```text
ixxaru prakAsAlu
```

Existing file to update later:

```text
evaluation-krishnamurti/kg_022_mugguru_rAmamurwulu.xml
```

Target structure:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?><document>
<sentence type="">
<subject type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">mUdu</word>
</quantifier>
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">rAmamurwi</word>
</subject>
</sentence>
</document>
```

Expected output:

```text
mugguru rAmamurwulu
```

New test file to add later:

```text
evaluation-krishnamurti/kg_026_reMdu_pustakAlu.xml
```

Purpose:

```text
Confirm that object-count `reMdu` remains `reMdu` and does not become `ixxaru`.
```

Target structure:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?><document>
<sentence type="">
<subject type="nominative">
<quantifier type="numeral" counttype="object">
<word pos="numeral">reMdu</word>
</quantifier>
<word pos="noun" gender="nonmasculine" number="plural" person="third" casemarker="">pustakaM</word>
</subject>
</sentence>
</document>
```

Expected output:

```text
reMdu pustakAlu
```

New test file to add later:

```text
evaluation-krishnamurti/kg_027_iravE_Exu_maMxi.xml
```

Purpose:

```text
Confirm that unlisted larger person-count expressions use the `maMxi` fallback.
```

Target structure:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?><document>
<sentence type="">
<subject type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">iravE Exu</word>
</quantifier>
</subject>
</sentence>
</document>
```

Expected output:

```text
iravE Exu maMxi
```

Important:

```text
Do not modify unrelated Krishnamurti examples.
Do not modify original 14 evaluation examples.
Do not encode surface forms such as ixxaru or mugguru directly in the XML.
```

Step 10 Design: Narrow Count Tests
----------------------------------

After implementation and after the count XML files are updated/added, run only
the count-specific examples first.

Files:

```text
evaluation-krishnamurti/kg_021_ixxaru_prakAsAlu.xml
evaluation-krishnamurti/kg_022_mugguru_rAmamurwulu.xml
evaluation-krishnamurti/kg_026_reMdu_pustakAlu.xml
evaluation-krishnamurti/kg_027_iravE_Exu_maMxi.xml
```

Expected outputs:

```text
kg_021_ixxaru_prakAsAlu.xml -> ixxaru prakAsAlu
kg_022_mugguru_rAmamurwulu.xml -> mugguru rAmamurwulu
kg_026_reMdu_pustakAlu.xml -> reMdu pustakAlu
kg_027_iravE_Exu_maMxi.xml -> iravE Exu maMxi
```

Purpose:

```text
Confirm person-count lookup.
Confirm object-count leaves numeral unchanged.
Confirm maMxi fallback for larger/unlisted person-count expressions.
Confirm head nouns still use ordinary noun realization.
```

Step 11 Design: Full Regression Tests
-------------------------------------

After the narrow count tests pass, run all existing examples.

Regression set 1:

```text
evaluation/testInput1.xml through evaluation/testInput14.xml
```

Expected:

```text
All outputs should match COUNT_NOUN_BASELINE_BEFORE.md.
```

Regression set 2:

```text
evaluation-krishnamurti/kg_001 through all existing Krishnamurti XML files
```

Expected:

```text
Existing outputs should remain unchanged except the deliberately updated count
examples.
```

Check especially:

```text
ordinary noun plural still works
kitiki -> kitikIlu still works
adjective modifier examples still work
verb agreement examples still work
no non-count XML becomes dependent on quantifier logic
```

Reference baseline:

```text
COUNT_NOUN_BASELINE_BEFORE.md
```

Step 12 Design: Result Documentation
------------------------------------

After implementation and successful tests, update:

```text
COUNT_NOUN_NOTES.md
BATCH_02_RESULTS.md
```

Do not update paper text until the feature is implemented and verified.
