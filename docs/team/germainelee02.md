---
layout: page
title: Germaine Lee's Project Portfolio Page
---

### Project: CookHub

CookHub is a desktop application for student chefs to store and check their recipes, especially for those who only have limited budget and time to cook themselves.
It is written in Java and uses JavaFX to create GUI.

Given below are my contributions to the project.

* **Modified Components**: Restructured Parser component during morphing. [\#78](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/78)
  * What it does: Parses the string received in the command line.
  * Justification: The Parser component has to be redone when restructuring the model.
  * Highlights: The learning curve of handling the Parser is steep. I had to learn how the different subcomponents of Parser works (`ArgumentMultimap`, `ArgumentTokenizer`, `ParserUtil`). 

* **Enhancements to existing features**: Modified the `find` command to handle more variety of inputs and command flags. [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140)
  * What it does: The modified `find` command will take in a command flag and search through the `Recipe` components.
  It uses the pre-existing utility functions in `ArgumentMultimap` and `ArgumentTokenizer`.
    It validates that only one command flag can be present and has a function
    to get the predicate of its respective command flag.
  * Justification: The original`find` command only searches through the titles.
  * Highlights: The challenge is in using the existing utility class `ArgumentMultimap` and `ArgumentTokenizer` when parsing the command flags. 
  I also created the respective classes to handle the predicates for each recipe component.

* **Enhancements to existing features**: Modified the input rules for `Step` and `Description` [\#163](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/163)
  * What it does: `Step` and `Description` can include punctuation marks, alphanumeric and spaces.
  * Justification: Punctuation marks occurs naturally in steps and descriptions.
  * Highlights: This feature flaw was brought up 30 minutes before the feature freeze deadline. I hurriedly fixed it.

* **New Feature**: Added the feature for a user to find all the ingredients that can be made with only a set of ingredients. [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140)
  * What it does: The `only` feature takes in ingredients, then lists the recipes that contain a subset of those ingredients.
  * Justification: It helps to find recipes that can be made with only the limited ingredients mentioned, and nothing more.
  * Highlights: This command was novel. It works similarly to the original find command, but the respective predicate loops through all the recipe ingredients,
  and rejects a recipe if it finds an ingredient that is not in the mentioned list.

* **Modified Testing**: Did the entire test code for Parser [\#108](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/108), [\#99](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/99)
  * Redid all the command parser tests and modified the `testutil` directory, as the test code relies on `testutil` heavily.
  * Justification: It would be better to have component testing and a passing CI
  so that problems can be more easily detected in the workflow. 

* **Modified Testing**: Redid the `CommandTestUtil` file [\#99](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/99)
  * Did `CommandTestUtil`, that will be used in the other test classes too
  * Justification: The parameters in the `CommandTestUtil` will be reused over and over again in the other test classes.

* **Code contributed**: [Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=germainelee02&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Project management**:
  * Updated README.md with our new repository name, and made the original AB3 CI point to our CookHub project [\#76](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/76)
  * Managed releases `v1.3.trial` and `v1.3` (2 releases) on GitHub
  * Updated the target user profile in the Developer Guide [\#124](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/124)
  * Updated the value proposition in the Developer Guide [\#124](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/124)
  * Contributed to the user stories in the Developer Guide
  * Added the use cases in the Developer Guide
  * Added CLI tutorial to the User Guide [\#240](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/240)
  * Fixed the grammar mistakes in the User Guide [\#212](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/212)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#94](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/94#discussion_r1131842351),
  [\#94](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/94#discussion_r1131842857),
  [\#213](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/213#discussion_r1155910263),
  [\#213](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/213#discussion_r1155911769)
  * Found 16 bugs in [CS2103T-T11-4](https://github.com/AY2223S2-CS2103T-T11-4/tp/issues?q=is%3Aissue+B+)'s product during PE-D.

* **Documentation**:
  * User Guide
    * Added documentation for the features `add`, `edit`, `list`, `clear`, `find`, and `only` [\#146](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/146/files)
    * Added documentation for the command format [\#146](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/146/files)
    * Added to the command glossary. [\#252](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/252)
  * Developer Guide
    * Added the UML diagram for Parser
    * Added the description, usage, and implementation for the features `add`, `find`, and `only` [\#252](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/252)
    * Created the UML sequence diagrams for `add`, `find`, and `only` [\#261](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/261)
    * Updated the Planned Enhancements [\#225](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/225)


