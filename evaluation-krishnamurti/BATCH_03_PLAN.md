Krishnamurti XML batch 03 plan
==============================

Source section: `Demonstrative Pronouns` from
`krishnamurti telugu sentences_31.05.2026.docx`.

Purpose
-------

This batch should not be converted blindly into XML.  The examples in this
section test several different phenomena:

```text
simple demonstrative pronoun + noun predicate
plural demonstrative pronouns
proper-name predicates
human plural nouns
honorific noun forms with gAru
possessive modifiers
lexicalized predicate forms
emphatic clitic -e
polite particles and negative equational clauses
```

The current engine can cover only a subset cleanly without placing final
surface forms directly in the XML.  The remaining examples should be documented
as either in-scope future extensions or out of scope for the present version.

Batch 03 classification
-----------------------

| Planned id | Source WX | Phenomenon | Status | Reason |
|---|---|---|---|---|
| kg_028 | vAdu pillavAdu | demonstrative pronoun + noun predicate | supported now | Can be represented as subject pronoun plus nominative noun complement. |
| kg_029 | vIdu nOkaru | demonstrative pronoun + noun predicate | supported now | Same simple subject-complement pattern. |
| kg_030 | vAlYlYu maMcivAlYlYu | plural demonstrative + lexicalized good-person predicate | needs extension | Current modifier+noun path would tend to produce a separated phrase, not the lexicalized form `maMcivAlYlYu`. |
| kg_031 | awanu rAmArAvu | demonstrative/person pronoun + proper noun predicate | supported now | Can be represented as subject pronoun plus proper noun complement. |
| kg_032 | ImeV sarasvawi | feminine demonstrative/person pronoun + proper noun predicate | supported now | Same simple subject-complement pattern. |
| kg_033 | vIlYlYu biccagAlYlYu | plural demonstrative + human plural noun | needs extension | Requires root-to-plural handling for a human noun such as `biccagAdu -> biccagAlYlYu`; should not place `biccagAlYlYu` directly as the root. |
| kg_034 | Ayana mARtArugAru | respectful pronoun + honorific/professional noun | needs XML design | Current code can print `gAru` only if it is already in the lexical item; a clean solution needs an honorific feature or honorific noun rule. |
| kg_035 | vAru dAktarugAru | respectful/plural pronoun + honorific/professional noun | needs XML design | Same `gAru` honorific issue. |
| kg_036 | vIru murwigAru | respectful/proximal pronoun + honorific proper noun | needs XML design | Same `gAru` honorific issue. |
| kg_037 | Ame sIwammagAru | feminine pronoun + honorific proper noun | needs XML design | Same `gAru` honorific issue, with a proper-name base. |
| kg_038 | awanu nA snehiwudu | pronoun subject + possessive modifier + noun predicate | supported now | Uses the new `possessive` element; `nA` is generated from root `nenu` with `casemarker="yoVkka"`. |
| kg_039 | nenu badipaMwulni | first-person pronoun + predicate nominal form | needs extension | This is not a plain nominative noun complement; it needs a predicate nominal/person-marked analysis. |
| kg_040 | axi padakakurci | demonstrative pronoun + compound noun predicate | supported now | Can be represented as subject pronoun plus noun complement if `padakakurci` is treated as a lexical noun. |
| kg_041 | vAru mA vAru | pronoun + possessive/pronominal predicate | supported now | Uses the new `possessive` element; `mA` is generated from root `nenu` with plural first-person features. |
| kg_042 | nA peru rAmArAvu | possessive noun phrase subject + proper noun predicate | supported now | Uses the new `possessive` element inside the subject noun phrase. |
| kg_043 | ixi BAgavawaM | demonstrative pronoun + noun predicate | supported now | Simple subject-complement pattern. |
| kg_044 | nenu maMgalivANNi | first-person pronoun + lexicalized predicate nominal | needs extension | Similar to `nenu badipaMwulni`; should not be treated as an already-inflected surface form. |
| kg_045 | ivi picci kAgiwAlu | plural demonstrative + adjective + plural noun | supported now | Existing adjective and plural noun paths should cover this cleanly. |
| kg_046 | mA illaxe | possessive + emphatic/deictic predicate | out of current scope | Requires possessive phrase plus emphatic clitic `-e`; no current XML feature. |
| kg_047 | ixe cOka | emphatic demonstrative + predicate adjective/noun | out of current scope | Requires emphatic clitic `-e` handling. |
| kg_048 | ive nA puswakAlu | emphatic plural demonstrative + possessive noun phrase | out of current scope | Requires both emphatic clitic `-e` and possessive modifier support. |
| kg_049 | avunaMdi axi puswakame | polite particle + emphatic predicate | out of current scope | Contains discourse/polite particle and emphatic clitic. |
| kg_050 | avunaMdi ivi kurcIle | polite particle + emphatic predicate | out of current scope | Contains discourse/polite particle and emphatic clitic. |
| kg_051 | kAxaMdi ixi illu kAxu | polite negative equational sentence | in-scope future extension | Negative equational clauses are linguistically relevant, but require a controlled negative-copula design before XML generation. |

Immediate XML candidates
------------------------

The first controlled XML subset from this batch should be:

```text
kg_028 vAdu pillavAdu
kg_029 vIdu nOkaru
kg_031 awanu rAmArAvu
kg_032 ImeV sarasvawi
kg_038 awanu nA snehiwudu
kg_040 axi padakakurci
kg_041 vAru mA vAru
kg_042 nA peru rAmArAvu
kg_043 ixi BAgavawaM
kg_045 ivi picci kAgiwAlu
```

These examples test demonstrative pronouns and simple noun/adjective phrase
realization without needing new features.

Do not generate XML yet for:

```text
honorific gAru forms
emphatic -e forms
negative equational forms
lexicalized predicate nominal forms
```

Those should become separate planned extension batches.

Suggested next extension order
------------------------------

```text
1. Generate and run the immediate XML candidates above.
2. Design honorific handling for gAru forms.
3. Design human plural lexical classes such as biccagAdu -> biccagAlYlYu.
4. Design negative equational clauses such as ixi illu kAxu.
5. Decide whether emphatic -e belongs in this project version or should be
   documented as out of scope.
```

Possessive modifier design
--------------------------

The design note for possessive modifiers is documented in:

```text
POSSESSIVE_MODIFIER_DESIGN.md
```

The implemented decision is to add a `possessive` XML element and reuse the existing
noun/pronoun oblique logic with `casemarker="yoVkka"`, rather than placing
surface possessive forms such as `nA` and `mA` directly in the XML.

Honorific gAru design
---------------------

The design note for honorific `gAru` forms is documented in:

```text
HONORIFIC_GARU_XML_DESIGN.md
```

The proposed decision is to keep the lexical root in the XML and add an
optional head-word attribute such as `honorific="gAru"`, rather than placing
surface forms such as `dAktarugAru` or `sIwammagAru` directly in the XML.
