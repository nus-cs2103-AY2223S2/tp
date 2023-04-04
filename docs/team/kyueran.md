---
layout: page
title: Yue Ran's Project Portfolio Page
---

### Project: CookHub

CookHub is a desktop application for student chefs to store and check their recipes, especially for those who only have limited budget and time to cook themselves.
It is written in Java and uses JavaFX to create GUI.

Given below are my contributions to the project.
* **Modified Components**: 

* Update Model component to work for Recipes instead of Adresses. [\#75](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/75)

  * What it does: Model all the fields within a Recipe and determine how how other components should interact with the Recipe related objects.
  * Justification: The previous Model works on persons and addresses. The Model component have to be changed to suit our application which works with Recipes before any of the other models can start handling Recipe related objects. The Model will define the type of objects the other components (i.e. `Logic`, `Ui`, `Storage`) will work with so it was the first component to be changed in our project.
  * Highlights: First, I learned how the Model component works regarding UniqueRecipeList to prevent duplicates and FilteredList to show users the updated view of recipes after certain commands. Next, I changed all the classes related to the previous AddressBook to those that will model Recipes and our RecipeBook. Our Recipe is now clearly defined as an object that requires the following objects (`Title`, `Description`, `Step`). Now the other components can work on the Recipe object individually to achieve the desired functionalities our application aims to achieve.

* Add Ingredient class which are required as class fields within Recipes. [\#147](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/147)

  * What it does: Models an ingredient required within a recipe. The ingredient contains the following components, name, quantity required, unit_of_measurement (i.e. `g`, `ml`, `tbsp`) and price_per_unit.
  
  * Justification: We have to model an Ingredient with the following fields to allow our application to inform users of the exact type and quantity of ingredients needed for a recipe and to calculate recipe prices as price-related functionalities is a main feature of our application.

  * Highlights: Modelling the Ingredient class is more complex compared to the other fields of our recipe as it is a customised class that was not present in AB3. To model an ingredient with the necessary fields (`name`, `quantity`, `unit_of_measurement`, `price_per_unit`), most of the other components have to be modified to accomodate this change. I updated ther parser to parse the individual fields of an Ingredient and perform input validation on each of them. I updated the storage to work with JsonAdaptedIngredient which had multiple subfields which does not follow the normal @JsonCreator annotation like the other fields within our Recipe object (`Title`, `Description`, `Step`). I updated the UI to display the ingredient and the price of the Recipe which is calculated by summing up the prices of all the ingredients within a recipe. Lastly, most of the testcases and test inputs have to be changed to ensure that they work with the new Recipe format with the new Ingredient class.

* **New Feature**: 
  * to be added soon
  * **Code contributed**: [Link]()
* **Project management**:
  * to be added soon
* **Enhancements to existing features**: 
  * to be added soon
* **Documentation**:
  * User Guide: Create initial v1.1 skeleton UG
  * Document Guide: Create initial v1.1 skeleton DG
* **Community**:
  * to be added soon
* **Tools**:
  * to be added soon
