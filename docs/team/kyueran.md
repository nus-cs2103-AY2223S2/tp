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

  * Highlights: Updating the Ingredient class is more complex compared to the other classes within our Recipe object as it is now a custom class with additional fields which behaves differently from existing classes in AB3. Since we are adding three additional fields to ingredient, the other components have to be modified to accomodate this change. First, I updated the parser to parse the fields in an Ingredient and perform input validation on each of them. Next, I updated the storage to work with JsonAdaptedIngredient which had multiple subfields that does not follow the normal @JsonCreator annotation like the other classes within our Recipe object (`Title`, `Description`, `Step`). I also updated the UI to display our Recipe object with the new Ingredient fields and the total cost of each Recipe (calculated by summing the cost of all ingredients). Lastly, the testcases and test inputs have to be modified to ensure that they work with the new Ingredient class.

* **New Feature**: 
  * Add the ability for user to filter recipe by a specified price condition
  [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * What it does: The `fp COMPARATOR PRICE` (`COMPARATOR = < or >` , `PRICE = +ve number`) will take in a comparator and a price and filter the list of recipes such that those remaining fulfils the price condition. E.g. `fp < 4.5` will return a list of all Recipes with total cost less than $4.50.
    * Justification: This command is meant to help student chefs on a low budget to filter the list of recipes by price so they can keep within their budget when cooking meals.
    * Highlights: This command was implemented in the same pull request in which I added the ingredients as the cost of a recipe is calculated based on the cost of its ingredients. This command involved working with the parser for input validation and the FilteredList to show the remaining recipes after updating it with our custom SatisfyPriceConditionPredicate.

    * After v1.3, I decided to enhance this feature to work together with our `find` command [\#211](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/211). This PR was originally merged but after clarifying with prof Damith that it fixed a feature flaw (which is not allowed in v1.4), the PR was unmerged and closed. Previously, if we call the `fp` command to filter recipes by price after the `find` command has found a subset of recipes, the filter will be applied on every recipe in our database rather than  the subset of recipes produced by `find`. For example, after using the `find` command to only obtain western food, calling `fp` on all the western food is the same as calling `fp` on all types of food and the result list may contain japanese or mexican food if they satisfy the price condition. To solve this problem, I experimented with chaining predicates together to use on our FilteredList. Simply calling getPredicate on our FilteredList is not type safe as it returns predicate of type Predicate<? super Recipe> rather than our custom predicates of type Predicate<Recipe>. To overcome this, I updated the ModelManager to keep track of current predicates acting on the FilteredList to be able to do chaining of predicates with the `logical AND` operator.

  * Add the ability for user to sort recipes according to price [\#149](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/149)
    * What it does: The `sort ORDER` (`ORDER = asc or desc`) feature will receive either `asc` to sort recipes in ascending order of price or `desc` to sort recipes in descending order of price.
    * Justification: Other than filtering recipe by price condition, we want to help student chefs on a tight budget decide what to cook by seeing the cheapest to most expensive recipes in the RecipeBook.
    * Highlights: To accomplish this, I first used the parser to perform input validation on the parameters. Next, I obtained the list of recipes from the model then used the `sort` function from `Java Collections`. Finally, I set the recipe book to the sorted version to be displayed in the UI.

* **Project management**:
  * Create the Github organisation, set milestones and setup code coverage for the repository. 
  * Liaise with team members on how to split the work for v1.2 and decided on working on individual components (`Model`, `Logic`, `Parser`, `UI`, `Storage`). I took the `Model` component and coordinated with the team on what fields and methods they required from `Model` which will define how the other components should interact with our custom Recipe objects (Morph project). The `Model` is the first code-related PR to be pushed for the other components to start work. [\#75](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/75)
  * After working on our individual components, I facilitated the integration of all the components through VS Code Live Share and we produced our first working version of CookHub. [\#92](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/92)
  * Help team members set-up PlantUML to draw UML diagrams after experimenting with the tool on IntelliJ and VS Code.

* **Modified Testing**: 
  * Change all Model test code to work for CookHub [\#100](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/100)
    * I changed the testing code for every function in Model to test for problems in our RecipeBook and Recipes. 
    * Justification: Since we morphed AB3 into a RecipeBook, we have to test for the all the different components within the RecipeBook and Recipe as they now have new behavior. I also had to change the test inputs to suit Recipe and Recipebook to ensure that that all the classes within Model have the correct behavior.

  * Change almost all test code after adding Ingredient class to Recipe [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)
    * I had to modify almost all the testcases after adding the Ingredient class into our Recipe since most of our testcases worked with Recipe objects in some way shape or form. I also had to change the sample Json data in the test folder in order to work with our Recipes which have a new structure after adding the Ingredient class to it.
    * Takeaway: Iterative development might be costly if we decide to make a change to the structure of our key items we are working with (Recipe and RecipeBook in our case) halfway into the project. After we decided we want to add price-related functionalities such as filter and sort by price in v1.2, I realised that I needed to add the `quantity`, `unit_of_measurement` and `price_per_unit` fields to our Ingredient class (as explained in the filter recipe by a specified price condition feature). This change in the Model led to a lot of changes in our main components (`Logic`, `Parser`, `Storage`, `UI`) and majority of the testcases have to be updated since the contract specified by model for the different components of our application to work together has been changed. To prevent this from happening, I should have anticipated this from the start based on the User Stories and specified Model in a way that could accomodate all of our planned functionalities from the get go.

* **Documentation**:
  * User Guide
    * Create initial draft of UserGuide.md for CookHub [\#43](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/43)
    * Add documentation for `fp` and `sort` feature, improve formatting and add glossary [\#154](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/154)
    * Improve on the description of CookHub, add separate sections for new user and returning user, improve readability of notes on command format [\#160](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/160)
    * Fix UG bugs and improve navigability [\#238](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/238)
  * Developer Guide
    * Create initial draft of DeveloperGuide.md for CookHub [\#48](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/48)
    * Contributed to the user stories in the Developer Guide
    * Add the UML diagram for Model
    * Add Apendix: Planned Enhancements [\#216] (https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/216)


* **Community**:
  * I set up PlantUML early and was faced with the problem of the image being rendered by PlantUML not being the same as that i the DG. After experimenting with the tool on IntelliJ and VS Code with different versions of PlantUML, I found that using IntelliJ IDEA 2021.3.1 (Community Edition) with v1.2022.12 PlantUML will resolve the issue so I made a [forum comment](https://github.com/nus-cs2103-AY2223S2/forum/issues/266#issuecomment-1477310179) to help others facing the same issue.
  * PRs reviewed (with non-trival review comments): [\#101](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/101),
  [\#140](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/140),
  [\#163](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/163)
  