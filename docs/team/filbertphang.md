---
layout: page
title: Filbert Phang's Project Portfolio Page
---

### Project: RIZZipe

RIZZipe is a desktop recipe book application used for busy cooks who want to track their recipes. The user interacts
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=filbertphang&breakdown=true)

**New Features:**
* **Find-by-property** ([\#127](), [\#165]())
  * What it does: Allow users to filter recipes by various criteria, e.g. recipe names, tags, or ingredients.
  * Justification: Enables users to efficiently look for recipes that they want.
  * Highlights:
    * Implemented the find-by-property feature using generics, so that any additional filter criteria in the future can be easily introduced.

**Enhancements to existing features:**
* **Refactored logic and parser for Edit, Delete, Find, Help commands** ([\#74](), [\#75]())
* **Refactored JSON serialization/deserialization of recipe data** ([\#86]())
* **Refactored Add/Edit form** ([\#197]())
  * What it does: 
    * Refactored Add/Edit forms to use the same underlying logic, instead of having separate implementations.
    * Reworked the execution of the resultant `edit` command from the Edit form to be triggered via a JavaFX event.
  * Justification: Add/Edit forms shared a lot of similar behaviour, so it was a suitable candidate for refactoring (in the spirit of DRY).
  * Credits: [James](jamesliuzx.md) for implementing the original Add/Edit forms.
* **Refactored regex parsing pipeline** ([\#245]())
  * What it does: Reworked parsing process so that regex validation for `RecipeDuration` and `RecipePortion` is only done once.
  * Justifcation: Previous implementation of the parsers repeated the same regex validation at multiple stages of the parsing process, which is redundant.
* **Fixed and assisted others in fixing non-trivial UI and Logic bugs** ([\#189](), [\#245](), [\#257]())
  * What it does: Added fixes for window resizing, error message propagation, command parsing, and recipe card highlighting for selected recipes.
  * Justification: Bug fixes improve RIZZipe's user experience

**Project management:**
* Moved user stories to GitHub
* Set up skeleton issues in every iteration 
* Created release for [v1.3](https://github.com/AY2223S2-CS2103T-T13-2/tp/releases/tag/v1.3)

**Documentation:**
* **User Guide:**
  * Tidied up and standardised formatting
  * Added field summary for recipe and ingredients
  * Added styling for `<kbd>` tags
* **Developer Guide:**
  * Added user profile
  * Improved developer guide based on peer feedback ([\#259]())
  * Added documentation for Storage component ([\#130](), [\#139]())
  * Added documentation for "Find-by-property" feature ([\#139]())
  * Tidied up and standardised formatting
  * Added and formatted UAT section
* **Code Quality and Code Style:**
  * Cleaned up code quality and style for release `v1.2` ([\#80]())
  * Refactored multiple components of the code to improve code quality (see above)

**Community:**
* PRs reviewed (with non-trivial review comments): [\#75](), [\#77](), [\#87](), [\#94](), [\#117](), [\#138](), [\#147](), [\#257]()
* Reported 11 bugs in the Practical Exam dry run: [Bug Report](https://github.com/filbertphang/ped/issues)

**Tools:**
* Integrated a new dependency (jackson-datatype-jdk8) to the project ([\#86]())
