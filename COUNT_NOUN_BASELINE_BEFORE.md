Count Noun Baseline Before Implementation
=========================================

Date: 2026-07-02

Purpose
-------

This file freezes the current outputs before any count-noun/person-count
implementation is started.

No count-noun code has been implemented at this point.

Compilation
-----------

The project compiles successfully with:

```powershell
javac -d build src\teluguNLG\TeluguNLG.java src\teluguNLG\features\*.java src\teluguNLG\elements\*.java src\teluguNLG\utils\*.java src\teluguNLG\word\*.java src\teluguNLG\phrase\*.java
```

Original 14 Evaluation Outputs
------------------------------

```text
testInput1.xml -> vAlYlYu aMxamEna wotalo koVwwa kukkawo neVmmaxigA naduswunnAru
testInput2.xml -> vAdini exxu kommulawo podicinAxi
testInput3.xml -> subbArAvu rEpu ceruwAdu
testInput4.xml -> kukka mAMsaM mukkawo parigeVwwiMxi
testInput5.xml -> ameVrikA viyawnAMwo yuxxaM cesinAxi
testInput6.xml -> nenu vAdiwo kusti patteVnu
testInput7.xml -> Ayana nAwo eVppudu weVlugulone mAtlAdeVdu
testInput8.xml -> kamala iMtiki veVlYlYinAxi
testInput9.xml -> kamalaku kopam vacciMxi
testInput10.xml -> rAmu veVlYlYAdu
testInput11.xml -> vAdu nAku A saMgawi ceVppAdu
testInput12.xml -> xoVMga dabbulasaMciwo parigeVwwAdu
testInput13.xml -> veVMkayyaku picci
testInput14.xml -> veVMkayyaku picci
```

Krishnamurti Batch Outputs
--------------------------

```text
kg_001_ixi_goda.xml -> ixi goda
kg_002_ivi_godalu.xml -> ivi godalu
kg_003_axi_kitiki.xml -> axi kitiki
kg_004_avi_kitikIlu.xml -> avi kitikIlu
kg_005_ixi_gaxi.xml -> ixi gaxi
kg_006_ivi_gaxulu.xml -> ivi gaxulu
kg_007_axi_illu.xml -> axi illu
kg_008_avi_ilYlYu.xml -> avi ilYlYu
kg_009_ixi_pustakaM.xml -> ixi pustakaM
kg_010_ivi_pustakAlu.xml -> ivi pustakAlu
kg_011_axi_baMdi.xml -> axi baMdi
kg_012_avi_balYlYu.xml -> avi balYlYu
kg_013_ixi_velu.xml -> ixi velu
kg_014_ivi_velYlYu.xml -> ivi velYlYu
kg_015_ixi_ceVyyi.xml -> ixi ceVyyi
kg_016_ivi_cewulu.xml -> ivi cewulu
kg_017_ixi_Uru.xml -> ixi Uru
kg_018_ivi_UlYlYu.xml -> ivi UlYlYu
kg_019_axi_puli.xml -> axi puli
kg_020_avi_pululu.xml -> avi pululu
kg_021_ixxaru_prakAsAlu.xml -> renlYlYu prakAsAlu
kg_022_mugguru_rAmamurwulu.xml -> mugguru rAmamurwulu
kg_023_sIwA_rAmulu.xml -> sIwA rAmulu
kg_024_subbarAvu_suMxarAlu.xml -> subbarAvu suMxarAlu
kg_025_britan_ameVrikAlu.xml -> britan ameVrikAlu
```

Known Count-Noun Gap
--------------------

Before count-noun implementation, `kg_021_ixxaru_prakAsAlu.xml` used
`reMdu`-style count input as an ordinary plural noun path and produced:

```text
renlYlYu prakAsAlu
```

Expected after a proper person-count implementation:

```text
ixxaru prakAsAlu
```

This should be fixed through a separate quantifier/person-count mechanism, not
through `plural.xml`.
