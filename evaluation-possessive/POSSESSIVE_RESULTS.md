Possessive evaluation results
=============================

Purpose
-------

This set checks that possessive forms are generated from root nouns/pronouns
inside the `possessive` XML element.  The XML files do not place the surface
possessive forms directly as adjectives or noun heads.

| File | Root possessor in XML | Expected/generated WX |
|---|---|---|
| poss_001_nI_pustakaM.xml | nuvvu, singular, `yoVkka` | nI pustakaM |
| poss_002_mI_pustakaM.xml | nuvvu, plural, `yoVkka` | mI pustakaM |
| poss_003_vAdi_illu.xml | vAdu, singular, `yoVkka` | vAdi illu |
| poss_004_vAlYlYa_illu.xml | vAdu, plural, `yoVkka` | vAlYlYa illu |
| poss_005_rAmudi_pustakaM.xml | rAmudu, singular, `yoVkka` | rAmudi pustakaM |

Notes
-----

- Pronoun possessors are realized through `PronounClass`.
- Noun possessors are realized through `NounClass`.
- The visible `yoVkka` marker is removed after the oblique/genitive form is
  produced by the existing case logic.
