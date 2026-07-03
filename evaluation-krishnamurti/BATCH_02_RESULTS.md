Krishnamurti XML batch 02 results
=================================

Source: "Plural of Proper Nouns" examples from
`krishnamurti telugu sentences_31.05.2026.docx`.

| File | Source WX used | Generated WX | Status |
|---|---|---|---|
| kg_021_ixxaru_prakAsAlu.xml | reMdu + person-count + prakAsaM plural | ixxaru prakAsAlu | matches after count-noun implementation |
| kg_022_mugguru_rAmamurwulu.xml | mUdu + person-count + rAmamurwi plural | mugguru rAmamurwulu | matches |
| kg_023_sIwA_rAmulu.xml | sIwA rAmulu | sIwA rAmulu | matches |
| kg_024_subbarAvu_suMxarAlu.xml | subbarAvu suMxarAlu | subbarAvu suMxarAlu | matches |
| kg_025_britan_amerikAlu.xml | britan amerikAlu | britan amerikAlu | matches |
| kg_026_reMdu_pustakAlu.xml | reMdu + object-count + pustakaM plural | reMdu pustakAlu | matches |
| kg_027_iravE_Exu_maMxi.xml | iravE Exu + person-count fallback | iravE Exu maMxi | matches |

Notes:

- Count-noun examples use the controlled `<quantifier>` XML shape.
- The plural noun/root is kept as a root with `number="plural"`.
- Person-count numerals are generated through `personcount.xml` or the `maMxi`
  fallback rule, not by placing surface forms directly in the input XML.
