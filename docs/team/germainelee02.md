---
layout: page
title: Germaine Lee's Project Portfolio Page
---

### Project: CookHub

CookHub is a desktop application for student chefs to store and check their recipes, especially for those who only have limited budget and time to cook themselves.
It is written in Java and uses JavaFX to create GUI.

Given below are my contributions to the project.

* **Modified Components**: Restructured the Parser component when modifying Address Book 3 to CookHub. [\#78](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/78)
  * What it does: This component is in charge of parsing the string received in the command line.
  * Justification: The Parser component needs to be restructured, along with all the components in the code base (i.e. `Logic`, `Ui`) because we are changing the model completely, 
  from a `Person` in AB3, to a `Recipe` in CookHub. 
  * Highlights: The learning curve of handling the Parser can be steep as first. When first faced with the code base, I had to learn how the different subcomponents of Parser works.
  Some notable ones are `ArgumentMultimap`, `ArgumentTokenizer`, and `ParserUtil`. Reading the Developer Guide and tracing the entire code base when a command is passed in helped tremendously.

* **Enhancements to existing features**: Modified the `find` command to handle a greater variety of inputs and command flags. [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140)
  * What it does: The modified `find` command will be able to take in a command flag (e.g. `t/`, `d/`, `s/`, `i/`) and will be able to find through the respective components of a `Recipe`.
    For example, `find i/Eggs` will search through all the recipe ingredients, and list all the recipes with eggs as an ingredient. Similar to the `add` and `edit`,
  the modified`find` command uses the pre-existing utility functions in `ArgumentMultimap` and `ArgumentTokenizer`.
    This command also has its own function to validate that only one command flag can be present and the function
    to get the predicate of its respective command flag.
  * Justification: Previously, the `find` command only searches through the titles of the recipes.
    During planning, my team decided that one important aspect of CookHub would be that a user can search through CookHub by its ingredients. The previous implementation of `find` does not cover that requirement.
    For example, if a user has eggs in the fridge that are near-expiry and wants to find a recipe that uses eggs, finding by title would result in recipes titled as
    `Omelette` not showing up. Hence, we want the user to be able to search through the entirety of a recipe, as well as its respective components.
  * Highlights: The challenge in this command is in using the existing utility class `ArgumentMultimap` and `ArgumentTokenizer` when adding the functionality of parsing the command flags given. 
  It also had to check that only one command flag was given. I also created the respective classes to handle the predicates for each recipe component.

* **Enhancements to existing features**: Modified the input rules for `Step` and `Description` [\#163](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/163)
  * What it does: After modification, `Step` and `Description` can now include punctuation marks. Initially, the inputs can only be alphanumeric or spaces.
  * Justification: Punctuation marks occurs naturally in steps and descriptions.
  * Highlights: This was a fairly simple task. This feature flaw was brought up 30 minutes before the feature freeze deadline. Luckily, I hurriedly relaxed the regex before the deadline.

* **New Feature**: Added the ability for a user to find all the ingredients that can be made with only a set of ingredients. [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140)
  * What it does: The `only` feature receives a list of ingredients, then lists the recipes that can be made with only those ingredients.
    For example, the input `only eggs bacon tomatoes` will return a list of recipes that have a set of ingredients that are a subset of the ingredients mentioned. 
  For example, if the user only has eggs and bacon, any recipe including rice cannot be made.
  * Justification: This command is aimed in helping our target audience, student chefs. This `only`  command helps to find recipes that can be made with only
    the ingredients mentioned, and nothing more.
  * Highlights: This command was more novel. It works similarly to the original find command, but the respective predicate loops through all the recipe ingredients,
  and rejects a recipe if it finds an ingredient that is not in the mentioned list.

* **Modified Testing**: Redid the entire test code for Parser [\#108](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/108), [\#99](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/99)
  * I was in charge of redoing the entire Parser subsection of the test code. I had to redo all the command parser tests and modify the files under the  
  `testutil` directory, as the test code uses the classes in the `testutil` directory heavily.
  * Justification: When restructuring the existing AB3 to fit our CookHub agenda, our test code started to break. It would be better to have a passing CI
  so that problems can be more easily detected in the workflow. If the CI is constantly failing due to failing tests, other problems could go unnoticed. 

* **Modified Testing**: Redid the `CommandTestUtil` file [\#99](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/99)
  * Decided all the parameters in `CommandTestUtil`, that will be used in the other test classes too
  * Justification: The parameters in the `CommandTestUtil` will be reused over and over again in the other test classes.


* **Code contributed**: [Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=germainelee02&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **Project management**:
  * Updated README.md with our new repository name, and made the original AB3 CI point to our CookHub project [\#76](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/76)
  * Managed releases `v1.3.trial` and `v1.3` (2 releases) on GitHub
  * Updated the target user profile in the Developer Guide [\#124](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/124)
  * Updated the value proposition in the Developer Guide [\#124](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/124)
  * Contributed to the user stories in the Developer Guide
  * Added the use cases in the Developer Guide
  * Fixed the grammar mistakes in the User Guide and changed the language in the User Guide to sound more welcoming [\#212](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/212)

* **Documentation**:
  * User Guide
    * Added documentation for the features `add`, `edit`, `list`, `clear`, `find`, and `only` [\#146](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/146/files)
    * Added documentation for the command format [\#146](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/146/files)
  * Developer Guide
    * Added the UML diagram for Parser
    * Added the description, usage, and implementation for the features `add`, `find`, and `only` [\#252](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/252)
    * Created the UML sequence diagrams for `add`, `find`, and `only` [\#261](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/261)
* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#94](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/94#discussion_r1131842351),
  [\#94](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/94#discussion_r1131842857),
  [\#213](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/213#discussion_r1155910263),
  [\#213](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/213#discussion_r1155911769)


