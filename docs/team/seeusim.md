---
layout: page
title: Ong Seeu Sim's Project Portfolio Page
---

### **Project: RIZZipe**

RIZZipe is a desktop recipe book application used for busy cooks who want to track their recipes. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project:

* **Code contributed**: **[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=SeeuSim&breakdown=true)**

* **New Features:**
  * Add Gradle CI cache (in this [Commit](https://github.com/AY2223S2-CS2103T-T13-2/tp/commit/96bffd3c9a047abffd4586288b545dbdbf5d75c8))
    * What it does: Adds cache to Github Actions for builds with Gradle as well as JDK
    * Justification: Having the CI environment repeatedly pull the same resources as opposed to fetching from a cache wastes resources and slows down the CI runs. THis allows the Gradle and JDK to be refreshed from a cache on Github's server.
  * Add TestFX testing framework (in this [Commit](https://github.com/AY2223S2-CS2103T-T13-2/tp/commit/07ed5a8b5c3e383219d52d93d6ab39b81f8eefe1))
    * What it does: Allows for JUnit tests to mock JavaFX environments in unit testing
    * Justification: As our app is highly graphic in nature, some form of unit testing is required for the GUI components. However, these tests were unable to be implemented as there is no support on Ubuntu for TestFX, which interferes with our multiplatform non-functional requirement.
    * Going forward: When there is support for JUnit testing with JavaFX across all environments, the User Acceptance Testing will move to unit tests using this framework.
  * Add codecov, checkstyle, and basic test cases (Pull request [\#85](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/85))
    * What it does: Measures the coverage of test code for this app, as well as validate that new code passes style checks
    * Justification: Allows us to track which features are being tested and validated against a set of pre-defined behavior as per our user stories, and add new tests for new features.
    Furthermore, the checkstyle validates that code is written according to the Java style guide.
    * Highlights:
      * Added codecov and checkstyle config to project 
      * Made modifications to `UI`, `MainWindow` and other classes to clean up code quality, thanks to checkstyle
      * Modified and added tests for the Models and Recipe Packages to up codecov
      * Added Regex matching for `Ingredient`, `tag`, `Name`, `Step` class as well as other `model` classes to up codecov as well as validate behavior
      * Add coverage tests for `model` package 
      * Add coverage tests for `storage` package 
      * Fixed checkstyle issues for the app in this release
    * Credits: [Alson](alson001.md) for adding tests for the `logic` package
  * Add `HashMap` of `Ingredient` - `IngredientInformation` key-value pairs in the `Recipe` class (Pull request [\#137](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/137))
    * What it does: Separates out the recipe-specific information about an `Ingredient` from its common properties, such as Ingredient Name.
    * Justification: As an `Ingredient` may be shared among multiple recipes with different amounts and quantifiers, this allows for common `Ingredient` instances to be shared among different `Recipe` instances,
      while keeping their `Recipe` specific data as a table value within that `Recipe`.
    * Highlights:
      * Refactored `Ingredient` Field within the `models.Recipe` class from a List of `Ingredient` Objects to a Hash table using `HashMap`, consisting of `Ingredient` keys and `IngredientInformation` value (key-value) pairs
      * Refactored the tests that these changes affected

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (4 releases) on GitHub
* **Enhancement to existing features:**
  * Refactored `seedu.recipe.model.recipe.Recipe` and its related fields (Pull request [\#69](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/69))
    * Made use of `java.util` Collections, and the use of `java.util.function` lambdas in the following classes: 
      * `seedu.recipe.model.recipe.RecipeIngredient`
        * Constructor 
        * Field accessors
  * Refactored `seedu.recipe.logic.parser` Parser classes for the `add` command, as well as the commands for the `clear` and `exit` command (Pull Request [\#72](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/72))
    * Implemented commands and tweaked their messages 
      * `AddCommand` 
      * `ClearCommand`
      * `ExitCommand`
    * Added Functional Pipelines so that these parse functions may be passed as lambdas to the fields within the Command Parsers for `add`, `edit`, without the need for try-catch blocks
      * `TryUtil::safeParse`
  * Refactor Storage and UI classes (Pull Request [\#83](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/83))
    * Refactor Storage Adaptors (`JsonAdaptedRecipe`)
      * Add and serialize new `Recipe` fields 
    * Add sample `Recipe` data 
      * `SampleDataUtil` 
    * Refactored `RecipeCard` to display new data from `Recipe`
    * Added field validation to `Model` classes:
      * `RecipeDuration` 
      * `RecipePortion` 
    * Added new exceptions for those validation cases
  * Patched UI bugs (Pull request [\#189](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/189))
    * Patched scaling issues for Recipe Form UI, where the height, width, scrollbar were not scaling as the window for that form was not resizing appropriately 
    * Added adaptors for various model classes so that they could be displayed properly within the UI 
      * `IngredientBuilder`
    * Added fixes for UI navigation with arrow keys within the text edit components in the `RecipeForm`
  * Full Coverage Tests for Commons package (Pull request [\#246](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/246))
    * Contributed tests for the `commons` package 
  * Full Coverage Tests for Storage package (Pull request [\#252](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/252))
    * Contributed tests for the following classes:
      * ImportManager 
      * ExportManager 
  * Full Coverage Tests for Logic package (Pull request [\#255](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/255))
    * Contributed tests for the following classes:
      * `logic.command`:
        * `DeleteCommand`
        * `ExitCommand`
        * `ExportCommand`
        * `FindCommand`
        * `HelpCommand`
        * `ImportCommand`
        * `ListCommand`
        * `RecipeDescriptor`
        * `SubCommand`
      * `logic.parser`
        * `ArgumentMultimap`
        * `ArgumentTokenizer`
        * `CliSyntax`
        * `DeleteCommandParser`
        * `FindCommandParser`
        * `ParserUtil`
        * `PrefixTest`
        * `RecipeBookParser`
        * `SubCommandParser`
      * `LogicManager`
* **Documentation**:
  * **User Guide:**
    * Added documentation for the features `delete` and `list` [\#26](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/26)
    * Did cosmetic tweaks to existing documentation of features `add`, `view`: [\#30](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/30)
    * Added higher resolution screenshots, as well as tweaks for clarity of language/formatting [\#259](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/259)
  * **Developer Guide:**
    * Added user stories for the `delete`, `list` features [\#35](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/35)
    * Added documentation and diagrams for the `model` package [\#125](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/125)
      * Added updated pUML diagrams for the package 
      * Refactored existing documentation to help developers new to the project better understand the package and how to contribute to it
    * Added updated, higher resolution class diagrams, as well as tweaks for clarity of language/formatting [\#259](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/259)
* **Community**:
  * PRs reviewed (with non-trivial review comments):
    [\#30](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/30),
    [\#34](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/34),
    [\#37](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/37),
    [\#86](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/86),
    [\#90](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/90),
    [\#96](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/96),
    [\#91](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/91),
    [\#108](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/108),
    [\#116](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/116),
    [\#117](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/117),
    [\#118](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/118),
    [\#125](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/125),
    [\#127](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/127),
    [\#129](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/129),
    [\#131](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/131),
    [\#132](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/132),
    [\#137](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/137),
    [\#138](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/138),
    [\#139](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/139),
    [\#146](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/146),
    [\#147](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/147),
    [\#148](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/148),
    [\#154](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/154),
    [\#155](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/155),
    [\#185](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/185),
    [\#189](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/189),
    [\#194](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/194),
    [\#203](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/203),
    [\#252](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/252),
    [\#253](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/253),
    [\#254](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/254),
    [\#255](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/255)
  * Reported 12 bugs in the Practical Exam dry run: **[Bug Report](https://github.com/SeeuSim/ped/tree/main/files)**
