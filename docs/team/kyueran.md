---
layout: page
title: Kang Yue Ran's Project Portfolio Page
---

### Project: CookHub

CookHub is a desktop application for student chefs to store and check their recipes, especially for those who only have limited budget and time to cook themselves.
It is written in Java and uses JavaFX to create GUI.

Given below are my contributions to the project.

* **Code contributed**: [Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=kyueran&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Modified Components**: 

* Change Model component to work for Recipes instead of Adresses. [\#75](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/75)

  * Changes made: Model fields within a Recipe and determine how other components should interact with Recipe related objects.
  * Highlights: Changed classes related to Person and AddressBook to model our Recipe and RecipeBook.

* Add fields to Ingredient class to implement price-related functionalities. [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)

  * Changes made: Update Ingredient class to contain (`name`, `quantity`, `unit_of_measurement`, `price_per_unit`).

  * Highlights: Adding fields to Ingredient requires the other components to be modified. I updated `Parser` to parse and perform input validation on these fields, `Storage` to work with the new JsonAdaptedIngredient, and `UI` to display Recipe with Ingredients and total cost.

* **New Features**: 
  * Add ability for user to filter recipe by a price condition
  [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * What it does: The `fp COMPARATOR PRICE` (`COMPARATOR = < or >` , `PRICE = +ve number`) filters the list of recipes such that those remaining fulfils the price condition.
    * Highlights: I modified parser and updated the FilteredList with a custom SatisfyPriceConditionPredicate to show Recipes that satisfy the price condition.

    * After v1.3, I enhanced this feature to work together with `find` command [\#211](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/211). Previously, if we call `fp` after `find`, the filter will be applied on all recipes. I fixed this by chaining the current predicates on FilteredList with my SatisfyPriceConditionPredicate.

  * Add ability for user to sort recipes according to price [\#149](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/149)
    * What it does: The `sort ORDER` (`ORDER = asc or desc`) feature sort recipes in ascending or descending order of price.

* **Modified Testing**: 
  * Change all Model test code to work for CookHub [\#100](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/100)
    * Contribution: Since we morphed AB3 into a RecipeBook, I changed all the tests in Model to test for Recipe related objects.

  * Change test code after modifying Ingredient class in Recipe [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * Contribution: Most components work with Recipe objects with Ingredients so testcases have to be updated. I also changed the sample Json data to work with the new Ingredient class.
  
* **Community**:
  * Made [forum comment](https://github.com/nus-cs2103-AY2223S2/forum/issues/266#issuecomment-1477310179) to help others facing trouble wiht PlantUML.
  * PRs reviewed (with non-trival review comments): [\#101](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/101),
  [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140),
  [\#163](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/163)
  * Found 15 bugs in team [CS2103T-T12-2’s](https://github.com/AY2223S2-CS2103T-T12-2/tp)  product during PE-D.

* **Project management**:
  * Create the Github organisation, set milestones and setup code coverage for the repository. 
  * Discussed and pushed changes to `Model` for the team to work on other components. [\#75](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/75)
  * After working separately for v1.2, I facilitated the integration of our components through VSCode Live Share to produce first working version of CookHub. [\#92](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/92)

* **Documentation**:
  * User Guide
    * Create initial draft of UserGuide.md for CookHub [\#43](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/43)
    * Add documentation for `fp` and `sort` feature, and add glossary table[\#154](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/154)
    * Write description of CookHub, add separate section for new user and returning user[\#160](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/160)
    * Fix UG bugs, improve readability and improve navigability [\#238](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/238)
    * Add tips, warnings and constraints for all commands [\#254](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/254) 
    * Add summary table for all commands [\#255](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/255)
  * Developer Guide
    * Create initial draft of DeveloperGuide.md for CookHub [\#48](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/48)
    * Contributed to the user stories in the Developer Guide and update `Model` UML diagram
    * Add Apendix: Planned Enhancements [\#216](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/216)
    * Add activity diagram for SortCommand and sequence diagram for FilterPrice command and their implementation detals. [\#258](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/258)
