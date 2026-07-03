Coupled Proper Noun Rules
=========================

Purpose
-------

This note documents the rule for coupled proper nouns that express the relation
`mariyu`. It is separate from the count-noun/person-count implementation.

Source Rule
-----------

Krishnamurti and Gwynn describe the plural of proper nouns and state that when
two proper nouns are coupled, the final noun takes plural marking. This is one
way of expressing the relation `mariyu` in Telugu.

All examples in this note are written in WX notation only.

Observed Pattern
----------------

The surface pattern is:

```text
proper noun 1 in combining form + proper noun 2 in plural form
```

Examples:

```text
sIwa + rAmudu -> sIwA rAmulu
subbarAvu + suMxaraM -> subbarAvu suMxarAlu
britan + ameVrikA -> britan ameVrikAlu
```

Important Distinction
---------------------

This is not a count-noun rule.

It must not use:

```text
<quantifier>
personcount.xml
maMxi fallback
```

This is also not simply the ordinary plural of a single noun phrase. It is a
coupled proper-noun phrase with relation `mariyu`.

Rule Sketch
-----------

For a coupled proper-noun expression with relation `mariyu`:

```text
1. Realize the first proper noun in its combining form.
2. Realize the final proper noun with plural number.
3. Do not insert an overt mariyu surface word.
```

The first proper noun may have a visible combining-form change:

```text
sIwa -> sIwA
```

In other cases, the combining form may look unchanged:

```text
subbarAvu -> subbarAvu
britan -> britan
```

The final proper noun receives plural realization through ordinary noun plural
formation:

```text
rAmudu -> rAmulu
suMxaraM -> suMxarAlu
ameVrikA -> ameVrikAlu
```

Current XML Limitation
----------------------

The current Krishnamurti XML examples produce the correct surface outputs, but
they do not explicitly represent the relation `mariyu`.

Current shape:

```xml
<complement type="nominative">
<word pos="noun" gender="masculine" number="plural" person="third" casemarker="">rAmu</word>
</complement>
<subject type="nominative">
<word pos="noun" gender="nonmasculine" number="singular" person="third" casemarker="">sIwA</word>
</subject>
```

This can produce:

```text
sIwA rAmulu
```

However, this XML shape does not say that `sIwA` and `rAmulu` are a coupled
proper-noun phrase with relation `mariyu`. It uses the existing
subject/complement ordering to obtain the surface sequence.

Controlled XML Design
---------------------

A future controlled representation should keep both lexical roots and mark the
relation explicitly. The compound should be inside the normal syntactic phrase
where it functions, such as `subject`, `object`, or `complement`. It should not
be a top-level sentence element.

Preferred shape:

```xml
<subject type="nominative">
<propernouncompound relation="mariyu">
<word pos="noun" type="proper" role="first" gender="nonmasculine" number="singular" person="third" casemarker="">sIwa</word>
<word pos="noun" type="proper" role="final" gender="masculine" number="plural" person="third" casemarker="">rAmudu</word>
</propernouncompound>
</subject>
```

The same design can be used inside `object` or `complement` if the whole
compound phrase has that syntactic function.

Required Element
----------------

The compound element is:

```xml
<propernouncompound relation="mariyu">
</propernouncompound>
```

Required attributes:

```text
relation="mariyu"
```

Only `mariyu` is in scope for the first implementation.

Required Children
-----------------

The first child word represents the first proper noun:

```xml
<word pos="noun" type="proper" role="first" gender="nonmasculine" number="singular" person="third" casemarker="">sIwa</word>
```

The final child word represents the final proper noun:

```xml
<word pos="noun" type="proper" role="final" gender="masculine" number="plural" person="third" casemarker="">rAmudu</word>
```

Required word attributes:

```text
pos="noun"
type="proper"
role="first" | "final"
gender
number
person
casemarker
```

The lexical root should be placed inside the word element. The input should not
place the final surface form directly in the XML.

Example XML Inputs
------------------

Example 1:

```xml
<subject type="nominative">
<propernouncompound relation="mariyu">
<word pos="noun" type="proper" role="first" gender="nonmasculine" number="singular" person="third" casemarker="">sIwa</word>
<word pos="noun" type="proper" role="final" gender="masculine" number="plural" person="third" casemarker="">rAmudu</word>
</propernouncompound>
</subject>
```

Expected output:

```text
sIwA rAmulu
```

Example 2:

```xml
<subject type="nominative">
<propernouncompound relation="mariyu">
<word pos="noun" type="proper" role="first" gender="masculine" number="singular" person="third" casemarker="">subbarAvu</word>
<word pos="noun" type="proper" role="final" gender="masculine" number="plural" person="third" casemarker="">suMxaraM</word>
</propernouncompound>
</subject>
```

Expected output:

```text
subbarAvu suMxarAlu
```

Example 3:

```xml
<subject type="nominative">
<propernouncompound relation="mariyu">
<word pos="noun" type="proper" role="first" gender="nonmasculine" number="singular" person="third" casemarker="">britan</word>
<word pos="noun" type="proper" role="final" gender="nonmasculine" number="plural" person="third" casemarker="">ameVrikA</word>
</propernouncompound>
</subject>
```

Expected output:

```text
britan ameVrikAlu
```

Why This XML Design
-------------------

This design is preferable to the current subject/complement workaround because:

```text
1. It explicitly records relation="mariyu".
2. It treats the two names as one noun phrase.
3. It can appear in subject, object, or complement position.
4. It keeps roots in the XML rather than precomputed surface forms.
5. It avoids using complement only to force word order.
```

Validation Expectations
-----------------------

A validator should accept:

```text
<propernouncompound relation="mariyu"> inside subject/object/complement
exactly two direct <word> children for the first implementation
one word with role="first"
one word with role="final"
role="final" with number="plural"
```

A validator should reject or warn about:

```text
missing relation
relation other than mariyu in the first implementation
missing role
more or fewer than two word children
role="final" without number="plural"
surface forms placed directly to bypass generation
```

Realization Expectations
------------------------

The realizer should produce:

```text
first proper noun combining form + final proper noun plural form
```

The first word should be handled by a combining-form path:

```text
sIwa -> sIwA
subbarAvu -> subbarAvu
britan -> britan
```

The final word should use ordinary noun plural realization:

```text
rAmudu -> rAmulu
suMxaraM -> suMxarAlu
ameVrikA -> ameVrikAlu
```

For proper names ending in `udu` in this construction, the final noun plural is
generated by dropping final `du` and adding `lu`:

```text
rAmudu -> rAmulu
```

No overt `mariyu` word should be inserted in the output for this construction.

Implementation Boundary
-----------------------

Before code changes, the implementation design must decide:

```text
1. Whether to add a new element such as <propernouncompound>.
2. How to generate the first noun combining form.
3. The first implementation derives the combining form by rule, not by lookup.
4. How the compound phrase should attach to subject/complement/object positions.
5. How regression tests will prove that ordinary noun plural and count-noun
   quantifier behavior are unaffected.
```

Initial Test Targets
--------------------

The initial test cases should be:

```text
sIwa + rAmudu -> sIwA rAmulu
subbarAvu + suMxaraM -> subbarAvu suMxarAlu
britan + ameVrikA -> britan ameVrikAlu
```

The current Krishnamurti output files corresponding to these are:

```text
kg_023_sIwA_rAmulu.xml -> sIwA rAmulu
kg_024_subbarAvu_suMxarAlu.xml -> subbarAvu suMxarAlu
kg_025_britan_ameVrikAlu.xml -> britan ameVrikAlu
```

Note: any spelling differences in file names should be checked against the WX
input forms before implementation.
