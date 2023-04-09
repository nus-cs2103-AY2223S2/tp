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
  * Highlights: I changed classes related to Person and AddressBook to model our Recipe and RecipeBook. Our Recipe is now clearly defined as an object that requires (`Title`, `Description`, `Step`, `Ingredient`).

* Add fields to Ingredient class to implement price-related functionalities. [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)

  * Changes made: The original Ingredient class only has an ingredient name. I updated it to contain the following fields (`name`, `quantity`, `unit_of_measurement`, `price_per_unit`). An example ingredient is `i/Flour, 4, Cup, 0.30` which translates into the recipe requiring 4 cups of flour at $0.30/cup.

  * Highlights: Adding the fields (`quantity`, `unit_of_measurement`, `price_per_unit`) to Ingredient requires the other components to be modified. First, I updated `Parser` to parse and perform input validation on these fields. Next, I updated `Storage` to work with the new JsonAdaptedIngredient which no longer follows the normal @JsonCreator annotation. I also updated the `UI` to display Recipe with the new Ingredient fields and the total cost of each Recipe (calculated using Recipe.getCost() which sums up cost of all ingredients).

* **New Features**: 
  * Add ability for user to filter recipe by a price condition
  [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * What it does: The `fp COMPARATOR PRICE` (`COMPARATOR = < or >` , `PRICE = +ve number`) filters the list of recipes such that those remaining fulfils the price condition. E.g. `fp < 4.5` will return a list of all Recipes with total cost less than $4.50.
    * Highlights: I modified parser for input validation and the updated the FilteredList with a custom SatisfyPriceConditionPredicate to show all Recipes that satisfy the price condition.

    * After v1.3, I enhanced this feature to work together with `find` command [\#211](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/211). Previously, if we call `fp` after `find`, the filter will be applied on all recipes. For example, after using `find t/Western` to view Recipes with Western tag, calling `fp` applies on all recipes and the result list may contain Recipes without the Western tag as long as they satisfy the price condition. I fixed this by tracking the predicates acting on the FilteredList to chain them with my SatisfyPriceConditionPredicate in a type safe manner. Now, `fp` will filter the subset of recipes produced by `find`.

  * Add ability for user to sort recipes according to price [\#149](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/149)
    * What it does: The `sort ORDER` (`ORDER = asc or desc`) feature sort recipes in ascending or descending order of price.
    * Highlights: First, I used the parser to perform input validation on the parameters. Next, I obtained the list of recipes from the model then used the `sort` function from `Java Collections`. Finally, I set the recipe book to the sorted version to be displayed in the UI.

* **Modified Testing**: 
  * Change all Model test code to work for CookHub [\#100](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/100)
    * Contribution: Since we morphed AB3 into a RecipeBook, I changed all the tests in Model to test for Recipe related objects.

  * Change majority of test code after modifying Ingredient class in Recipe [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * Contribution: Most components work with Recipe objects with Ingredients so the testcases have to be updated. I also changed our sample Json data in the test folder to work with the new Ingredient class.
  
* **Community**:
  * I set up PlantUML early and faced the problem of diagrams being wrongly rendered by PlantUML. After experimenting with different versions of the tool, I found that using IntelliJ IDEA 2021.3.1 (Community Edition) with v1.2022.12 PlantUML resolves the issue faced by others in the forum so I made this [forum comment](https://github.com/nus-cs2103-AY2223S2/forum/issues/266#issuecomment-1477310179).
  * PRs reviewed (with non-trival review comments): [\#101](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/101),
  [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140),
  [\#163](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/163)

* **Project management**:
  * Create the Github organisation, set milestones and setup code coverage for the repository. 
  * I coordinated with the team on what fields and methods they required from `Model` which will define how the other components should interact with our custom Recipe objects. I worked on these discussed changes to `Model` and pushed our first code-related PR for the team to work on other components (`Storage`, `Logic`, `Parser`, `UI`). [\#75](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/75)
  * After working on our individual components, I facilitated the integration of the components through VS Code Live Share and we produced our first working version of CookHub. [\#92](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/92)
  * Help team members set-up PlantUML to draw UML diagrams after experimenting with the tool on IntelliJ and VS Code.

* **Documentation**:
  * User Guide
    * Create initial draft of UserGuide.md for CookHub [\#43](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/43)
    * Add documentation for `fp` and `sort` feature, and add glossary table[\#154](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/154)
    * Write description of CookHub, add separate section for new user and returning user[\#160](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/160)
    * Fix UG bugs, improve readability and improve navigability [\#238](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/238)
    * Add tips, warnings and contraints for all commands [\#254](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/254) 
    * Add summary table for all commands [\#255](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/255)
  * Developer Guide
    * Create initial draft of DeveloperGuide.md for CookHub [\#48](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/48)
    * Contributed to the user stories in the Developer Guide and update `Model` UML diagram
    * Add Apendix: Planned Enhancements [\#216] (https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/216)
    * Add activity diagram for SortCommand and sequence diagram for FilterPrice command and elaborate on their implementation. [\#258] (https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/258)
