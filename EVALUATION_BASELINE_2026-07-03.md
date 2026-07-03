# Evaluation Baseline - 2026-07-03

## Purpose

This file records the current baseline output after the hard-coded-output audit. It is intended as a regression checkpoint before adding the next linguistic extension.

## Verification

Compile command:

```text
javac -d build src\teluguNLG\TeluguNLG.java src\teluguNLG\features\*.java src\teluguNLG\elements\*.java src\teluguNLG\utils\*.java src\teluguNLG\word\*.java src\teluguNLG\phrase\*.java
```

Compilation result: passed.

Original evaluation files run: 14.

Krishnamurti evaluation files run: 36.

Possessive evaluation files run: 5.

Runtime failures: none.

## Original 14 Evaluation Outputs

| File | Output | Status |
| --- | --- | --- |
| testInput1.xml | vAlYlYu aMxamEna wotalo koVwwa kukkawo neVmmaxigA naduswunnAru | Supported |
| testInput2.xml | vAdini exxu kommulawo podicinAxi | Supported |
| testInput3.xml | subbArAvu rEpu ceruwAdu | Supported |
| testInput4.xml | kukka mAMsaM mukkawo parigeVwwiMxi | Supported |
| testInput5.xml | ameVrikA viyawnAMwo yuxxaM cesinAxi | Supported |
| testInput6.xml | nenu vAdiwo kusti patteVnu | Supported |
| testInput7.xml | Ayana nAwo eVppudu weVlugulone mAtlAdeVdu | Supported |
| testInput8.xml | kamala iMtiki veVlYlYinAxi | Supported |
| testInput9.xml | kamalaku kopam vacciMxi | Supported |
| testInput10.xml | rAmu veVlYlYAdu | Supported |
| testInput11.xml | vAdu nAku A saMgawi ceVppAdu | Supported |
| testInput12.xml | xoVMga dabbulasaMciwo parigeVwwAdu | Supported |
| testInput13.xml | veVMkayyaku picci | Supported |
| testInput14.xml | veVMkayyaku picci | Supported |

## Krishnamurti Evaluation Outputs

| File | Output | Status |
| --- | --- | --- |
| kg_001_ixi_goda.xml | ixi goda | Supported |
| kg_002_ivi_godalu.xml | ivi godalu | Supported |
| kg_003_axi_kitiki.xml | axi kitiki | Supported |
| kg_004_avi_kitikIlu.xml | avi kitikIlu | Supported |
| kg_005_ixi_gaxi.xml | ixi gaxi | Supported |
| kg_006_ivi_gaxulu.xml | ivi gaxulu | Supported |
| kg_007_axi_illu.xml | axi illu | Supported |
| kg_008_avi_ilYlYu.xml | avi ilYlYu | Supported |
| kg_009_ixi_pustakaM.xml | ixi pustakaM | Supported |
| kg_010_ivi_pustakAlu.xml | ivi pustakAlu | Supported |
| kg_011_axi_baMdi.xml | axi baMdi | Supported |
| kg_012_avi_balYlYu.xml | avi balYlYu | Supported |
| kg_013_ixi_velu.xml | ixi velu | Supported |
| kg_014_ivi_velYlYu.xml | ivi velYlYu | Supported |
| kg_015_ixi_ceVyyi.xml | ixi ceVyyi | Supported |
| kg_016_ivi_cewulu.xml | ivi cewulu | Supported |
| kg_017_ixi_Uru.xml | ixi Uru | Supported |
| kg_018_ivi_UlYlYu.xml | ivi UlYlYu | Supported |
| kg_019_axi_puli.xml | axi puli | Supported |
| kg_020_avi_pululu.xml | avi pululu | Supported |
| kg_021_ixxaru_prakAsAlu.xml | ixxaru prakAsAlu | Supported |
| kg_022_mugguru_rAmamurwulu.xml | mugguru rAmamurwulu | Supported |
| kg_023_sIwA_rAmulu.xml | sIwA rAmulu | Supported |
| kg_024_subbarAvu_suMxarAlu.xml | subbarAvu suMxarAlu | Supported |
| kg_025_britan_ameVrikAlu.xml | britan ameVrikAlu | Supported |
| kg_026_reMdu_pustakAlu.xml | reMdu pustakAlu | Supported |
| kg_027_iravE_Exu_maMxi.xml | iravE Exu maMxi | Supported |
| kg_028_vAdu_pillavAdu.xml | vAdu pillavAdu | Supported |
| kg_029_vIdu_nOkaru.xml | vIdu nOkaru | Supported |
| kg_031_awanu_rAmArAvu.xml | awanu rAmArAvu | Supported |
| kg_032_ImeV_sarasvawi.xml | ImeV sarasvawi | Supported |
| kg_038_awanu_nA_snehiwudu.xml | awanu nA snehiwudu | Supported |
| kg_040_axi_padakakurci.xml | axi padakakurci | Supported |
| kg_041_vAru_mA_vAru.xml | vAru mA vAru | Supported |
| kg_042_nA_peru_rAmArAvu.xml | nA peru rAmArAvu | Supported |
| kg_043_ixi_BAgavawaM.xml | ixi BAgavawaM | Supported |
| kg_045_ivi_picci_kAgiwAlu.xml | ivi picci kAgiwAlu | Supported |

## Possessive Evaluation Outputs

| File | Output | Status |
| --- | --- | --- |
| poss_001_nI_pustakaM.xml | nI pustakaM | Supported |
| poss_002_mI_pustakaM.xml | mI pustakaM | Supported |
| poss_003_vAdi_illu.xml | vAdi illu | Supported |
| poss_004_vAlYlYa_illu.xml | vAlYlYa illu | Supported |
| poss_005_rAmudi_pustakaM.xml | rAmudi pustakaM | Supported |

## Current Scope Notes

The active baseline covers:

- original 14 TeluguNLG evaluation examples,
- noun plural generation,
- selected irregular plural entries,
- numeral quantifiers for object count and person count,
- coupled proper nouns with `mariyu`,
- possessive modifiers generated from root pronouns/nouns using `casemarker="yoVkka"`,
- simple demonstrative/pronoun nominal sentences,
- adjective modifier plus noun phrases.

The following are deliberately not included as supported generated XML examples yet:

- honorific `gAru` forms,
- emphatic `-e` forms,
- negative equational forms,
- lexicalized predicate nominal forms not represented by the current XML design.

## Audit Note

The baseline should be used with `HARD_CODED_OUTPUT_AUDIT.md`. That audit found no evidence of full-output hardcoding or example-file-specific output manipulation in the active code.
