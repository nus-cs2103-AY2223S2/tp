---
layout: page
title: Yue Ran's Project Portfolio Page
---

### Project: CookHub

CookHub is a desktop application for student chefs to store and check their recipes, especially for those who only have limited budget and time to cook themselves.
It is written in Java and uses JavaFX to create GUI.

Given below are my contributions to the project.
* **Modified Components**: 

* Change Model component to work for Recipes instead of Adresses. [\#75](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/75)

  * Changes made: Model all the fields within a Recipe and determine how how other components should interact with Recipe related objects.
  * Justification: The previous Model works on persons and addresses. The Model component have to be changed to suit our application which works with Recipes to define how the other components (i.e. `Logic`, `Ui`, `Storage`) should handle these objects.
  * Highlights: First, I learned how the Model component works regarding UniqueRecipeList to prevent duplicates and FilteredList to show users the updated view of recipes after certain commands. Next, I changed every class related to Person and AddressBook to model our Recipe and RecipeBook. Our Recipe is now clearly defined as an object that requires the following objects (`Title`, `Description`, `Step`).

* Add fields to Ingredient class to implement price-related functionalities. [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)

  * Changes made: The original Ingredient class only has an ingredient name. I modified the class to contain the following compulsory fields (`name`, `quantity`, `unit_of_measurement`, `price_per_unit`). An example of a way to input an ingredient is `i/Flour, 4, Cup, 0.30` which translates into the recipe requiring 4 cups of flour at $0.30/cup.
  
  * Justification: We have to model an Ingredient with the following fields to allow our application to inform users of the exact type and quantity of ingredients needed for a recipe. Price-related functionalities which is a key feature of our application can also be implemented by obtaining the total cost of a Recipe by summing up the prices of its ingredients.

  * Highlights: Updating the Ingredient class is more complex compared to the other classes within our Recipe object as it is a custom class with different behavior from existing classes in AB3. Since we are adding additional fields (`quantity`, `unit_of_measurement`, `price_per_unit`), the other components have to be modified to accomodate this change. First, I updated the parser to parse thse fields and perform input validation them. Next, I updated the storage to work with the new JsonAdaptedIngredient which no longer uses the normal @JsonCreator annotation. I also updated the UI to display our Recipe object with the new Ingredient fields and the total cost of each Recipe (Recipe.getCost() sum up the cost of all ingredients).

* **New Features**: 
  * Add the ability for user to filter recipe by a specified price condition
  [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * What it does: The `fp COMPARATOR PRICE` (`COMPARATOR = < or >` , `PRICE = +ve number`) will take in a comparator and a price and filter the list of recipes such that those remaining fulfils the price condition. E.g. `fp < 4.5` will return a list of all Recipes with total cost less than $4.50.
    * Justification: This command is meant to help student chefs on a low budget filter recipes by price so they can keep within their budget when cooking meals.
    * Highlights: This command involved working with the parser for input validation and the FilteredList to show the remaining recipes after updating it with our custom SatisfyPriceConditionPredicate.

    * After v1.3, I decided to enhance this feature to work together with our `find` command [\#211](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/211). This PR was unmerged after clarifying with prof Damith that it fixed a feature flaw (which is not allowed in v1.4). Previously, if we call the `fp` command after the `find` command has found a subset of recipes, the filter will be applied on all recipes. For example, after using the `find t/Western` command to only see western food, calling `fp` on the western food is akin to calling `fp` on all recipes and the result list may contain japanese or mexican food as long as they satisfy the price condition. To solve this problem, I updated the ModelManager to keep track of current predicates acting on the FilteredList to chain them with my SatisfyPriceConditionPredicate in a type safe manner. The updated behavior of `fp` will filter the subset of recipes produced by `find` by the price condition.

  * Add the ability for user to sort recipes according to price [\#149](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/149)
    * What it does: The `sort ORDER` (`ORDER = asc or desc`) feature will receive either `asc` to sort recipes in ascending order of price or `desc` to sort recipes in descending order of price.
    * Justification: Other than filtering recipe by price condition, we want to help student chefs on a tight budget decide what to cook by seeing the cheapest to most expensive recipes in the RecipeBook.
    * Highlights: To accomplish this, I first used the parser to perform input validation on the parameters. Next, I obtained the list of recipes from the model then used the `sort` function from `Java Collections`. Finally, I set the recipe book to the sorted version to be displayed in the UI.

* **Modified Testing**: 
  * Change all Model test code to work for CookHub [\#100](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/100)
    * I changed all the testing code in Model to test for problems in our RecipeBook and Recipes. 
    * Justification: Since we morphed AB3 into a RecipeBook, we have to test all the new components and the test inputs have to be changed to suit Recipe and Recipebook.

  * Change majority of test code after modifying Ingredient class in Recipe [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * I had to modify most of our testcases after modifying the Ingredient class in Recipe since they work with Recipe objects that contained Ingredients. I also had to change the sample Json data in the test folder in order to work with our new Recipes which contain additonal fields in the Ingredient class.
  
* **Community**:
  * I set up PlantUML early and was faced with the problem of the image being rendered by PlantUML not being the same as those in the DG. After experimenting with the tool on IntelliJ and VS Code with different versions of PlantUML, I found that using IntelliJ IDEA 2021.3.1 (Community Edition) with v1.2022.12 PlantUML will resolve the issue so I made a [forum comment](https://github.com/nus-cs2103-AY2223S2/forum/issues/266#issuecomment-1477310179) to help others facing the same issue.
  * PRs reviewed (with non-trival review comments): [\#101](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/101),
  [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140),
  [\#163](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/163)

* **Project management**:
  * Create the Github organisation, set milestones and setup code coverage for the repository. 
  * Liaise with team members on how to split the work for v1.2 and decided on working on individual components (`Model`, `Logic`, `Parser`, `UI`, `Storage`). I took the `Model` component and coordinated with the team on what fields and methods they required from `Model` which will define how the other components should interact with our custom Recipe objects (Morph project). The `Model` is the first code-related PR to be pushed for the other components to start work. [\#75](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/75)
  * After working on our individual components, I facilitated the integration of all the components through VS Code Live Share and we produced our first working version of CookHub. [\#92](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/92)
  * Help team members set-up PlantUML to draw UML diagrams after experimenting with the tool on IntelliJ and VS Code.

* **Documentation**:
  * User Guide
    * Create initial draft of UserGuide.md for CookHub [\#43](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/43)
    * Add documentation for `fp` and `sort` feature, improve formatting and add glossary [\#154](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/154)
    * Improve on the description of CookHub, add separate sections for new user and returning user, improve readability of notes on command format [\#160](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/160)
    * Fix UG bugs and improve navigability [\#238](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/238)
    * Add tips, warnings and contraints with proper formatting for all commands [\#254](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/254) 
    * Add summary table for all commands [\#255](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/255)
  * Developer Guide
    * Create initial draft of DeveloperGuide.md for CookHub [\#48](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/48)
    * Contributed to the user stories in the Developer Guide
    * Add the UML diagram for Model
    * Add Apendix: Planned Enhancements [\#216] (https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/216)
