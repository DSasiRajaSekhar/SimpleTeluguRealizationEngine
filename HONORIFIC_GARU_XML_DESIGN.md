Honorific gAru XML design
=========================

Purpose
-------

This note defines a controlled design for Telugu honorific noun forms with
`gAru`.

The immediate motivation comes from Batch 03 of the Krishnamurti examples:

```text
Ayana mARtArugAru
vAru dAktarugAru
vIru murwigAru
Ame sIwammagAru
```

The XML should not place final surface forms such as `dAktarugAru` or
`sIwammagAru` directly as the root.  The input should preserve the lexical root
and specify that the noun phrase has an honorific marker.

Current Code Observation
------------------------

The current noun phrase path can realize ordinary noun/pronoun complements, but
it has no controlled feature for honorific marking.

If the XML currently contains:

```xml
<word pos="noun" ...>dAktarugAru</word>
```

the system can print the expected surface form only because the surface form was
already placed in the input.  That is not acceptable for this extension.

Design Decision
---------------

Add an optional honorific attribute to noun/pronoun head words:

```xml
honorific="gAru"
```

The head word should remain the lexical root:

```xml
<complement type="nominative">
<word pos="noun" type="profession" gender="masculine" number="singular" person="third" casemarker="" honorific="gAru">dAktaru</word>
</complement>
```

Expected output:

```text
dAktarugAru
```

This keeps the input root separate from the honorific surface marker.

Controlled XML Shape
--------------------

For a professional noun:

```xml
<sentence type="">
<subject type="nominative">
<word pos="pronoun" gender="masculine" number="singular" person="third" casemarker="">vAru</word>
</subject>
<complement type="nominative">
<word pos="noun" type="profession" gender="masculine" number="singular" person="third" casemarker="" honorific="gAru">dAktaru</word>
</complement>
</sentence>
```

For a proper noun:

```xml
<sentence type="">
<subject type="nominative">
<word pos="pronoun" gender="nonmasculine" number="singular" person="third" casemarker="">Ame</word>
</subject>
<complement type="nominative">
<word pos="noun" type="proper" gender="nonmasculine" number="singular" person="third" casemarker="" honorific="gAru">sIwamma</word>
</complement>
</sentence>
```

Expected output:

```text
Ame sIwammagAru
```

First Implementation Boundary
-----------------------------

The first implementation should support:

```text
one honorific marker on a noun/pronoun head word
honorific="gAru"
subject/complement/object noun phrase positions
nominative honorific complements
roots ending in a vowel, where gAru is appended directly
```

Do not implement yet:

```text
honorific agreement effects on finite verbs
multiple honorific markers
honorific plus coupled proper-noun compounds
honorific plus possessive marker
case-marked honorific forms such as gAriki or gArini
```

Reasoning
---------

The design uses an attribute instead of a new element because `gAru` belongs to
the head noun/proper noun itself.  It is not an independent modifier like an
adjective and not a possessor like the `possessive` element.

This is similar to the way the head word already carries features such as:

```text
gender
number
person
casemarker
```

The honorific feature should be another head-word feature:

```text
root + honorific marker -> honorific surface form
```

Implementation Plan
-------------------

```text
1. Add an optional honorific field to SOCElement.
2. Update SOCElementBuilder to read the optional honorific attribute from a head <word>.
3. Keep the attribute optional so all existing XML files remain valid.
4. Add an HonorificClass or a small NounPhraseBuilder helper to apply gAru after the head noun/pronoun is realized.
5. Extend InputXmlValidator:
   - allow honorific only on direct head <word> in subject/object/complement,
   - accept only honorific="gAru" in the first implementation,
   - reject honorific on quantifier, possessive, modifier, and propernouncompound internal words for now.
6. Add XML examples for kg_034, kg_035, kg_036, and kg_037 only after the code compiles.
7. Run full regression:
   - original 14,
   - Krishnamurti examples,
   - possessive examples,
   - new honorific examples.
```

Expected Examples
-----------------

```text
kg_034 Ayana mARtArugAru
root: mARtAru, honorific="gAru"

kg_035 vAru dAktarugAru
root: dAktaru, honorific="gAru"

kg_036 vIru murwigAru
root: murwi, honorific="gAru"

kg_037 Ame sIwammagAru
root: sIwamma, honorific="gAru"
```

No-Hardcoding Requirement
-------------------------

The Java code must not check for:

```text
mARtAru
dAktaru
murwi
sIwamma
kg_034
kg_035
kg_036
kg_037
```

The only acceptable first rule is:

```text
if head word has honorific="gAru", append gAru after normal head realization
```

This makes the extension rule-based and reusable.
