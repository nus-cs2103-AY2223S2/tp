---
layout: page
title: Tang Zong Hao's Project Portfolio Page
---

### Project: RIZZipe

RIZZipe is a desktop recipe book application used for busy cooks who want to track their recipes. The user interacts 
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=zhtang29&breakdown=true)

* **New Feature**: 
* **Substitution** ([\#196]()) 
  * What it does: Allows users to search for ingredient substitutions for any ingredient in their recipe
  * Justification: Enables users to find replacements for missing ingredients that they may be lacking while cooking
  * Highlights:
    * Implemented a preloaded list of popular condiments with their common substitutes
    * Substitution feature allows users to search up substitutions from those which they stored as well
    
* **Project management**:
    * Managed documentation for release `v1.3` on GitHub
    * Managed User Guide and oversaw documentation for release `v1.4` on GitHub

* **Enhancements to existing features**:
    * Initial refactoring of `model` package ([\#65](), [\#77]())
    * Wrote additional tests to ensure 100% coverage for the following classes in `model` package ([\#260]())
      * ModelManagerTest
      * RecipeBookTest
      * UserPrefsTest
      * RecipeDurationInvalidArgumentLengthExceptionTest
      * RecipeQuantityInvalidArgumentExceptionTest
      * IngredientBuilderTest
      * IngredientInformationTest
      * IngredientParserTest
      * IngredientQuantityTest
      * IngredientTest
      * UnitTest
      * IngredientUtilTest

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add` [\#28](), `edit` [\#179](), `addf` [\#179](), `find`[\#179]() and sub`[#201]()
    * Consistently refined additional tips and comments for all features
    * Added introduction and purpose of UG [\#266]()
    * Ensured outgoing tone is consistent throughout UG [\#266]()
    * Added colour coding/symbols to UG to improve reader experience [\#270]()
    * Added import/export instructions [\#179]()
    * Ensured readability and flow of document for final UG release [\#270]()
    * Formatted and updated User Guide to reflect Keyboard Shortcuts
    * Added Keyboard Shortcuts summary table
    * Added glossary of tech jargon terms
    
  * Developer Guide:
    * Added user stories table
    * Added UML for main architecture [\#109]()
    * Merged and formatted changes for the Design section
    * Improved and edited Use cases

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#114](), [\#146](), [\#194]()
    * * Reported 10 bugs in the Practical Exam dry run: [Bug Report](https://github.com/ZHTang29/ped/issues)

    
  
