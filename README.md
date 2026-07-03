# TeluguNLG Surface Realization

This repository contains the active rule-based Telugu surface realization code,
XML rules, and evaluation inputs.

## Compile

```powershell
javac -d build src\teluguNLG\TeluguNLG.java src\teluguNLG\features\*.java src\teluguNLG\elements\*.java src\teluguNLG\utils\*.java src\teluguNLG\word\*.java src\teluguNLG\phrase\*.java
```

## Run One Input

```powershell
java -cp build teluguNLG.TeluguNLG evaluation\testInput1.xml
```

To also print Telugu Unicode output:

```powershell
java -cp build teluguNLG.TeluguNLG evaluation\testInput1.xml --unicode
```

## Evaluation Sets

- `evaluation/` contains the original 14 baseline XML inputs.
- `evaluation-krishnamurti/` contains Krishnamurti-based noun plural,
  numeral quantifier, and coupled proper-noun examples.

Generated build files, temporary render files, archived web-application files,
and reference PDFs are intentionally excluded from Git.
