# TeluguNLG Stabilized Baseline Evaluation

Snapshot date: 2026-06-30

Command used:

```powershell
java -cp build teluguNLG.TeluguNLG evaluation\<file>.xml
```

Compile command used:

```powershell
javac -d build src\teluguNLG\TeluguNLG.java src\teluguNLG\features\*.java src\teluguNLG\elements\*.java src\teluguNLG\utils\*.java src\teluguNLG\word\*.java src\teluguNLG\phrase\*.java
```

| File | Status | Current WX output |
|---|---|---|
| `testInput1.xml` | Runs | `vAlYlYu aMxamEna wotalo koVwwa kukkawo neVmmaxigA naduswunnAru` |
| `testInput2.xml` | Runs | `vAdini exxu kommulawo podicinAxi` |
| `testInput3.xml` | Runs | `subbArAvu rEpu ceruwAdu` |
| `testInput4.xml` | Runs | `kukka mAMsaM mukkawo parigeVwwiMxi` |
| `testInput5.xml` | Runs | `ameVrikA viyawnAMwo yuxxaM cesinAxi` |
| `testInput6.xml` | Runs | `nenu vAdiwo kusti patteVnu` |
| `testInput7.xml` | Runs | `Ayana nAwo eVppudu weVlugulone mAtlAdeVdu` |
| `testInput8.xml` | Runs | `kamala iMtiki veVlYlYinAxi` |
| `testInput9.xml` | Runs | `kamalaku kopam vacciMxi` |
| `testInput10.xml` | Runs | `rAmu veVlYlYAdu` |
| `testInput11.xml` | Runs | `vAdu nAku A saMgawi ceVppAdu` |
| `testInput12.xml` | Runs | `xoVMga dabbulasaMciwo parigeVwwAdu` |
| `testInput13.xml` | Runs | `veVMkayyaku picci` |
| `testInput14.xml` | Runs | `veVMkayyaku picci` |

Summary:

- Total files: 14
- Files that run successfully: 14
- Validation/runtime errors: 0
- Current baseline scope: controlled XML inputs in `evaluation`
- Current output script: WX notation

Important baseline notes:

- `testInput2.xml` now uses the nominative complement for verb agreement when the subject is case-marked.
- `testInput4.xml` now uses `pasttense` for `parugu` and produces the irregular nonmasculine past form through the morphology rules.
- `testInput12.xml` also changed through the same `parugu` irregular rule and now produces `parigeVwwAdu`.
- `testInput3.xml` currently outputs `ceruwAdu`; in this project mapping, `ceru` corresponds to Telugu `చేరు`.
- `testInput7.xml` contains `eVppudu`; if the intended meaning is "always", the lexical item may need review as `eVppudU`.
- `testInput13.xml` and `testInput14.xml` are predicate nominal / verbless examples.
