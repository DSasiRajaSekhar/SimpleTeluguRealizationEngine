# Stabilized Baseline Report for TeluguNLG

Snapshot date: 2026-06-30

## Purpose

This report records the current stabilized baseline for the TeluguNLG project. The goal is to provide a reproducible starting point for the paper before adding larger extensions such as new XML diagnostics, more test cases, UD input conversion, sandhi handling, or hybrid morphology.

## Baseline Claim

The current project establishes a runnable, auditable baseline for a rule-based Telugu surface realizer over controlled XML input.

This baseline is not claimed to be a complete modern Telugu NLG system. It is a stabilized version of the legacy TeluguNLG code that can be compiled, run from the command line, and evaluated consistently on the available 14 XML examples.

## What Has Been Stabilized

- The active TeluguNLG code has been separated from the old web application files.
- The old web files have been moved out of the active working area into `archive-webapp`.
- A command-line runner is available through `teluguNLG.TeluguNLG`.
- XML validation runs before generation.
- All 14 evaluation XML files currently run without runtime errors.
- Current WX outputs have been recorded in `BASELINE_EVALUATION.md`.
- Telugu renderings have been recorded in `TELUGU_TEXT_OUTPUTS.md`.
- Agreement controller handling has been added for case-marked subjects with nominative complements/objects.
- `parugu` irregular verb handling has been corrected through rule-based palteration lookup rather than hardcoded final output.

## Current Evaluation Results

| File | Current WX output | Telugu rendering |
|---|---|---|
| `testInput1.xml` | `vAlYlYu aMxamEna wotalo koVwwa kukkawo neVmmaxigA naduswunnAru` | `వాళ్ళు అందమైన తోటలో కొత్త కుక్కతో నెమ్మదిగా నడుస్తున్నారు` |
| `testInput2.xml` | `vAdini exxu kommulawo podicinAxi` | `వాడిని ఎద్దు కొమ్ములతో పొడిచినది` |
| `testInput3.xml` | `subbArAvu rEpu ceruwAdu` | `సుబ్బారావు రేపు చేరుతాడు` |
| `testInput4.xml` | `kukka mAMsaM mukkawo parigeVwwiMxi` | `కుక్క మాంసం ముక్కతో పరిగెత్తింది` |
| `testInput5.xml` | `ameVrikA viyawnAMwo yuxxaM cesinAxi` | `అమెరికా వియత్నాంతో యుద్ధం చేసినది` |
| `testInput6.xml` | `nenu vAdiwo kusti patteVnu` | `నేను వాడితో కుస్తి పట్టెను` |
| `testInput7.xml` | `Ayana nAwo eVppudu weVlugulone mAtlAdeVdu` | `ఆయన నాతో ఎప్పుడు తెలుగులోనే మాట్లాడెడు` |
| `testInput8.xml` | `kamala iMtiki veVlYlYinAxi` | `కమల ఇంటికి వెళ్ళినది` |
| `testInput9.xml` | `kamalaku kopam vacciMxi` | `కమలకు కోపం వచ్చింది` |
| `testInput10.xml` | `rAmu veVlYlYAdu` | `రాము వెళ్ళాడు` |
| `testInput11.xml` | `vAdu nAku A saMgawi ceVppAdu` | `వాడు నాకు ఆ సంగతి చెప్పాడు` |
| `testInput12.xml` | `xoVMga dabbulasaMciwo parigeVwwAdu` | `దొంగ డబ్బులసంచితో పరిగెత్తాడు` |
| `testInput13.xml` | `veVMkayyaku picci` | `వెంకయ్యకు పిచ్చి` |
| `testInput14.xml` | `veVMkayyaku picci` | `వెంకయ్యకు పిచ్చి` |

## Implemented Baseline Fixes

### Command-line execution

The project can now be compiled and run without the old servlet/web application path.

### XML validation

The validator catches structurally invalid XML before generation. This prevents confusing runtime errors for malformed examples.

### Agreement controller selection

The verb agreement path no longer blindly uses the XML subject when the subject is case-marked and a nominative complement/object is available as the agreement controller.

This affects `testInput2.xml`, where the verb now agrees with the nominative complement.

### Irregular verb palteration

`VerbClass.java` now checks exact verb-specific palteration entries before falling back to regular class regexes. This allows `parugu` to use the special altered stem `parigeVww` while regular verbs continue through the existing class rules.

This affects:

- `testInput4.xml`: `parigeVwwiMxi`
- `testInput12.xml`: `parigeVwwAdu`

No final sentence output has been hardcoded.

## Known Review Points

- `testInput2.xml`: The current output uses `exxu`; whether the surface noun should be `eddu` or another form should be reviewed as a noun-realization issue.
- `testInput3.xml`: The output is `ceruwAdu`; in this project mapping, `ceru` renders as `చేరు`. The input XML still contains `cEru`, but regular class processing currently yields `ceru`.
- `testInput7.xml`: `eVppudu` renders as `ఎప్పుడు`. If the intended meaning is "always", the lexical input may need `eVppudU`.
- `testInput5.xml` and `testInput8.xml`: These use `pastparticiple`, producing `cesinAxi` and `veVlYlYinAxi`, while `testInput9.xml` uses `pasttense`, producing `vacciMxi`. This records a TAM/style difference rather than a runtime error.
- `testInput13.xml` and `testInput14.xml`: These are verbless predicate nominal examples.

## Paper Framing

The defensible paper statement is:

> We recovered and stabilized the original rule-based Telugu surface realization baseline, separated it from its legacy web deployment, added command-line reproducibility and XML validation, and established a 14-example regression baseline with documented linguistic corrections.

The stronger journal extensions proposed in the planning document remain future work:

- larger benchmark suite
- standard input conversion, such as UD or Paninian dependency input
- sandhi module
- expanded morphology tests
- neural or hybrid comparison
- reproducible public package

## Next Recommended Step

Add regression protection so that future code changes can automatically compare new outputs against the current baseline table.
