Count Noun / Person-Count Notes
===============================

Purpose
-------

This note records the count-noun/person-count issue found while converting the
Krishnamurti/Gwynn "Plural of Proper Nouns" examples into TeluguNLG XML, and
the controlled implementation now used in TeluguNLG.

Observed Example
----------------

Source example:

```text
ixxaru prakAsAlu
```

Intended analysis:

```text
reMdu + person-count -> ixxaru
prakAsaM + plural -> prakAsAlu
```

Earlier XML test before count-noun implementation:

```xml
<subject type="nominative">
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">reMdu</word>
</subject>
<complement type="nominative">
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">prakAsaM</word>
</complement>
```

Earlier output before count-noun implementation:

```text
renlYlYu prakAsAlu
```

Expected output:

```text
ixxaru prakAsAlu
```

Current output after count-noun implementation:

```text
ixxaru prakAsAlu
```

Reason for Earlier Failure
--------------------------

The earlier noun plural path treated the count expression as an ordinary noun
and applied the generic noun plural rules.

This is not the correct linguistic mechanism. `ixxaru` is a person-count form,
not the ordinary plural of a noun.

Important Distinction
---------------------

Ordinary count nouns:

```text
goda -> godalu
kitiki -> kitikIlu
puli -> pululu
```

Person-count expressions:

```text
reMdu -> ixxaru
mUdu -> mugguru
nAlugu -> naluguru
```

Larger count expressions with `maMxi`:

```text
iravE Exu maMxi
nAlugu vela maMxi
aravE okka maMxi
```

Implemented XML Shape
---------------------

Forms such as `reMdu -> ixxaru` are not added to `plural.xml`.

The implemented representation uses an optional `<quantifier>` inside a noun
phrase. The quantifier remains separate from adjective modifiers and from the
head noun:

```xml
<subject type="nominative">
<quantifier type="numeral" counttype="person">
<word pos="numeral">reMdu</word>
</quantifier>
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">prakAsaM</word>
</subject>
```

Implemented Rule Sketch
-----------------------

If count type is `person` and the count expression has a special person-count
form:

```text
reMdu -> ixxaru
mUdu -> mugguru
```

If count type is `person` and no special compact form is used:

```text
count expression + maMxi
```

This feature should remain separate from ordinary noun plural generation.

Verified Count Outputs
----------------------

```text
kg_021_ixxaru_prakAsAlu.xml -> ixxaru prakAsAlu
kg_022_mugguru_rAmamurwulu.xml -> mugguru rAmamurwulu
kg_026_reMdu_pustakAlu.xml -> reMdu pustakAlu
kg_027_iravE_Exu_maMxi.xml -> iravE Exu maMxi
```
