---
layout: page
title: User Guide
toc: true
---
Are you an _avid chef_ with too many delicious recipes to handle? Or ever just have
the desire to **store**, **access** and **share** your recipes easily? 

We understand that for brilliant and creative individuals like yourself, it is essential to properly manage your recipes,
which is why [***RIZZ***ipe](#what-is-rizzipe) is designed and built just for chefs like ***YOU*** to organize your recipes at the touch of a finger!

## Using this Guide
* If you want to run ***RIZZ***ipe for the first time, check out our handy [Quick Start guide](#quick-start).
* If you want to learn how to use ***RIZZ***ipe, refer to our [Features](#features) section for a detailed guide on 
* how to run each different command.
* If you are an experienced developer and want to learn more about our design decisions, refer to our Developer Guide.

For **experienced users**, you may refer to the [Command summary](#command-summary) and [Keyboard shortcuts summary](#keyboard-shortcuts-summary) 
for a summarised table of all the commands and keyboard shortcuts available.

## Table of Contents
* Table of Contents
{:toc}

---
<div style="page-break-after: always;"></div>

## Quick Start

Let's get you _rizzed_ up in the fastest way possible!


1. Ensure you have [Java](https://www.oracle.com/sg/java/technologies/downloads/) `11` or above installed in your computer.
2. Download the latest `rizzipe.jar` [here](https://github.com/AY2223S2-CS2103T-T13-2/tp/releases)
3. Copy the file to the folder you want to use as the _home folder_ for ***RIZZ***ipe.
4. Open a [command terminal](#glossary), [`cd`](#glossary) into the folder you put the jar file in, and input the `java -jar
   rizzipe.jar` command to run the application.
5. A GUI similar to the below should appear in a few seconds. Note that our experienced chefs have already crafted
some sample recipes for you!<br/><br/>
![UI QuickStart](images/UiQuickStart.png)<br/><br/>
6. Navigate through the main window easily using just your keyboard! Toggle between recipes simply by using 
   the <kbd>↑ Up</kbd> and <kbd>↓ Down</kbd>  arrow keys!
7. Type commands within the [command line interface (CLI)](#glossary) and press <kbd>Enter</kbd> to execute it. For a list of executable commands, refer to the [Features](#features) Section.
8. Some of the previously mentioned executable commands may have keyboard shortcuts to make your life easier! Refer to each command within
the [Features](#features) Section, or take a look at the [Keyboard Shortcuts summary](#keyboard-shortcuts-summary) to learn more about how to use them!

<div style="page-break-after: always;"></div>
---
## Overview
The following subsections detail the functionality of ***RIZZ***ipe and explain the various terminology and colour coding we will be using in this guide.

### What is ***RIZZ***ipe?

***RIZZ***ipe is a **command-based recipe database** that works mostly by commands and behaves like a Command Line Interface (CLI).

***RIZZ***ipe comes equipped with multiple features, but we love these **3** the most:
1. Versatile **tagging** and powerful **searching** function
2. Recipe **sharing**
3. Search function for **ingredient substitutions**

Give it a try and make use of ***RIZZ***ipe's many features to achieve your **culinary rizz** today!

### Common colour codes in this guide

<div markdown="block" class="alert alert-info">
This is used to display :information_source: **additional information** and **tips** that you may find useful!
</div>

<div markdown="span" class="alert alert-warning">
This is used to display :bulb: **keyboard shortcuts** that can help make your journey using ***RIZZ***ipe more smooth sailing!
</div>

<div markdown="span" class="alert alert-danger">
This is used to display :exclamation: **warnings** for you to heed in order for ***RIZZ***ipe to work as intended!
</div>

### Glossary
* **Command terminal**: A terminal is a text input and output environment. It is a program that acts as a wrapper and allows us to enter commands that the computer processes. Open your terminal by:
    * Searching and opening `Command Prompt` on Windows
    * Searching and opening `Terminal` on Mac
* `cd`: Command to change directory
* **Command Line Interface (CLI)**: A text-based user interface (UI) used to run programs, manage computer files and interact with the computer
* **JSON**: JSON is an open standard file format and data interchange format that uses human-readable text to store and transmit data objects

<div style="page-break-after: always;"></div>
--- 
## What is a recipe?

Recipes are the building blocks of RIZZipe! Each recipe comprises multiple fields, which are listed in the table below:

| Field           | Prefix | Accepted Format                                                                                                                                             | Compulsory? | Example                                                                                                                                 |
|-----------------|--------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| **Name**        | `n/`   | **`NAME`** <br> `NAME` can contain these characters: `A-Z a-z 0-9 - \ ' " and space`.                                                                       | Yes         | `Chicken Rice`, <br>`Broccoli Stir-Fry`                                                                                                 |
| **Duration**    | `d/`   | **`DURATION UNIT`** <br> `DURATION` is a positive integer, decimal number, or fraction <br> `UNIT` can contain these characters: `A-Z a-z and space`        | No          | `3 hours`, <br>`30.5 minutes`, <br>`2/5 Time Intervals`                                                                                 |
| **Portion**     | `p/`   | **`AMOUNT UNIT`** or **`AMOUNT "-" or "to" AMOUNT UNIT`** <br> `AMOUNT` is a positive integer <br> `UNIT` can contain these characters: `A-Z a-z and space` | No          | `1 person`, <br>`3-5 people`, <br>`4 to 6 hungry individuals`                                                                           |
| **Ingredients** | `i/`   | `-n INGREDIENT_NAME [-a INGREDIENT_AMOUNT] [-e ESTIMATED_AMOUNT] [-cn COMMON_NAME] [-r REMARKS]...[-s SUBSTITUTION]...`                                     | No          | Refer to the "Ingredients" section below!                                                                                               |
| **Steps**       | `s/`   | **`STEP`** <br> `STEP` consists of 2 or more space-separated words, and can contain these characters: `A-Z a-z 0-9 . , : ; ( ) - and space`                 | No          | `Serve well.`, <br>`Bring 3 quarts water to a boil in a 5-qt. pot.`, <br>`Season with salt; add pasta and cook, stirring occasionally.` |
| **Tags**        | `t/`   | **`TAG`** <br>  `TAG` can contain these characters: `A-Z a-z 0-9 and space`                                                                                 | No          | `Italian`, <br>`Comfort Food`, <br>`Gluten Free`                                                                                        |

Pay close attention to these formats, for they are crucial inputs to some of our [features](#features) mentioned below!

## What is an ingredient?

Ingredients are at the heart of recipes, and also comprise multiple fields. The specification for recipe ingredients are listed in the table below:

| Field                | Prefix | Accepted Format                                                                                                                                              | Compulsory? | Example                                                                         |
|----------------------|--------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|---------------------------------------------------------------------------------|
| **Name**             | `-n`   | **`INGREDIENT_NAME`** <br> `INGREDIENT_NAME` can contain these characters: `A-Z a-z - and space`                                                             | Yes         | `Salt`, <br> `Soy Sauce`, <br> `Barley-Wheat Bread`                             |
| **Amount**           | `-a`   | **`AMOUNT UNIT`** <br> `AMOUNT` is a positive integer, decimal number, fraction, "a", or "one" <br> `UNIT` can contain these characters: `A-Z a-z and space` | No          | `a pinch of`, <br> `one pint`, <br> `3 fl oz`, <br> `4.5 grams`, <br> `2/3 cup` |
| **Estimated Amount** | `-e`   | **`ESTIMATED_AMOUNT`** <br> No restrictions on `ESTIMATED_AMOUNT`                                                                                            | No          | `Around 3 tablespoons`                                                          |
| **Common Name**      | `-cn`  | **`COMMON_NAME`** <br> `COMMON_NAME` can contain these characters: `A-Z a-z - and space`                                                                     | No          | `Eggplant`, <br> `Saiyote`                                                      |
| **Remarks**          | `-r`   | **`REMARK`** <br> `REMARK` can contain these characters: `A-Z a-z and space`                                                                                 | No          | `Optional`, <br> `Diced`, <br> `thinly sliced`                                  |
| **Substitutions**    | `-s`   | **`SUBSTITUTION`** <br> `SUBSTITUTION` can contain these characters: `A-Z a-z - and space`                                                                   | No          | `Seaweed Flakes`, <br> `Capsicum`                                               |

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  i.e. in `add NAME`, `NAME` is a parameter which can be used as `add Grilled Salmon`.

* Parameters in square brackets `[]` refer to optional parameters that can be excluded.
  i.e. `n/RECIPE_NAME [d/RECIPE_DURATION]` can be used as `n/Cheese Sandwich d/10 minutes` or `n/Cheese Sandwich`.

* Parameters with `...` behind them can be used multiple times, including 0.
  i.e. `[t/TAGS]` can be used as ` ` (zero times), `t/HALAL`, `t/HALAL t/BREAKFAST` etc.

* Parameters can be in any order
  i.e. if a command specifies `n/RECIPE_NAME d/RECIPE_DURATION`, `d/RECIPE_DURATION n/RECIPE_NAME` is also acceptable.

* Excess parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* :bulb: Important tip: For increased readability, we have included an optional multi-line command format for commands 
that may require multiple inputs (add, edit). After each input,
you can include a line break just by simply entering a backslash <kbd>\</kbd> to move on to the next line to continue 
writing the next part of your command input! Note that you will not have to delete the backslash that appears
before continuing to type!

</div>

<div style="page-break-after: always;"></div>

### Adding a recipe: `add`

Come up with a new innovative recipe and afraid that you'll forget about it in the future,
or just want to share it with your friends? Simply run the `add` command, and follow the prompts to save different
information about your recipe such as ingredients and steps!

Format: `add n/RECIPE_NAME [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]... [i/-n INGREDIENT_NAME [-a INGREDIENT_AMOUNT] [-e ESTIMATED AMOUNT] [-cn COMMON NAME] [-r REMARKS]... [-s SUBSTITUTION]...]... [s/RECIPE_STEPS]...`

Example(s) of usage:
* `add n/Honey Chicken Rice`<br/><br/>
![AddCommandUI](images/AddCommandUI.png)<br/><br/>
* `add n/Chicken Noodles d/20 minutes p/1-2 people i/-n chicken thigh -a 300 g i/-n noodles i/-n soy sauce -a 2 tablespoons -s salt`
* `add n/Peanut Butter Sandwich t/breakfast s/Prepare bread and spread s/Using a knife, spread 2-3 scoops of peanut
butter s/Serve and enjoy.`

<div markdown="block" class="alert alert-info">

* A recipe can have any number of steps, tags and ingredients (including 0)!

* It is only compulsory to include the **recipe name** when you first add the recipe into recipe book!

* When adding an ingredient, it is only compulsory to include the **ingredient name**. However, you can also add in additional details such as amount (**RECOMMENDED**) and substitutions etc.!

* Although optional, we do however suggest adding in the other fields if possible for your own future reference.

* If you have multiple inputs for tags, steps or ingredients, treat each input as a separate field (i.e. `s/Step1 s/step2`).

* Similar to parameters, ingredient fields do not have to be input in order (`-a` can come before `-n` etc.).

* However, for steps, please input the steps in the order that they are intended to be performed.

</div>

### Adding a recipe through form: `addf`
Want to add a delicious new recipe but don't want to type the whole long string of inputs at one go? 
Use our interactive form feature to add your favorite recipe to the recipe book today!

Format: 
`addf`

<div markdown="block" class="alert alert-info">

* An empty pop-up form as shown in the image below will appear!

![AddCommandWindow](images/addfCommand.png)

* Tab to move on to the next input field, and shift+tab to go to the previous field!

* A new row automatically pops up when you reach the last row for ingredients and steps, and once you're done just click to the next field!

* Note that multiple tags can be added with commas separators. (e.g. `Vegetarian, Halal`)
 
* Click on Save to add your recipe to the recipe book!

</div>

### Listing all recipes: `list`

Ever forget how many recipes you have in your storage? Want to view 'em all? Or
simply want to pick a recipe at random? Just run the `list` command!

Format:
`list`

<div markdown="block" class="alert alert-info">

* Lists all recipes that are in the storage, in the chronological order they were added.

* Depending on the size of the window, the user may add multiple columns to display more recipes.

* The `list` command can be used to reset the most recently searched list to the full recipe list after performing a `find` command!

* In order to zoom in on a particular recipe in the list, user can double-click on the corresponding recipe.

</div>

<div markdown="span" class="alert alert-warning">

Keyboard shortcut: 
To navigate the list, you can use the <kbd>↑ Up</kbd> and <kbd>↓ Down</kbd> arrow keys (as previously mentioned in the QuickStart).
While the recipe to that you want to zoom in to is being selected, simply type <kbd>p</kbd> to view that particular recipe's details! 

</div>

<div style="page-break-after: always;"></div>

### Editing a recipe: `edit`

Made a mistake or just omitted some important details when you first stored your recipe in the recipe book? 
Don't fret, for the `edit` command is here for you to supplement these new details to increase the _rizz_ on your recipes!

Format: 
`edit INDEX [n/RECIPE_NAME] [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]...  [i/-n INGREDIENT_NAME [-a INGREDIENT_AMOUNT] [-e ESTIMATED AMOUNT] [-cn COMMON NAME] [-r REMARKS]... [-s SUBSTITUTION]...]... [s/RECIPE_STEPS]...`  

<div markdown="block" class="alert alert-info">

* Edits the recipe at the specified index. The index refers to the index number shown in the displayed recipe list. 
 
* The index **must be a positive integer** 1,2,3,...

* All existing values will be updated to the new input values!
 
* When editing tags, ingredients and steps, all the existing values for these fields will be removed (i.e. adding of tags, ingredients and steps is not cumulative!)
 
* You can remove the optional fields (all fields except for name) by simply typing the prefix without specifying any details after it (i.e. typing `i/` removes all ingredients)
 
* Alternatively, you can use the edit popup form (shown below) that can be triggered using the keyboard shortcut displayed below.
 
* Note that the tags specified in the fields above still apply for ingredients, like `-n`.
 
 ![EditFormWindow](images/EditForm.png)

</div>

<div markdown="span" class="alert alert-warning">

Keyboard shortcut:
As an alternate means to typing the full command, we provide a popup form to edit a recipe!
While the recipe to that you want to edit is being selected, simply type <kbd>f</kbd> to display the popup form for that particular recipe!

</div>

### Finding a recipe by name: `find`

Have a certain recipe at the back of your mind that you want to refer to?
Or want to look up all recipes associated with a specific tag or those containing a specific ingredient that you're desperate to clear but have no idea what to pair it with?

`find` helps to save your time scrolling through your whole list of recipes by displaying
only those that match any of your specified keywords straight away!

Format:
`find [PROPERTY] KEYWORD [ADDITIONAL KEYWORDS]...`
Tip: Supported properties: `name`, `tag` and `ingredient`

i.e. `find name KEYWORD [ADDITIONAL KEYWORDS]...`, `find tag KEYWORD [ADDITIONAL KEYWORDS]...`, `find ingredient KEYWORD [ADDITIONAL KEYWORDS]...`

Example(s) of usage:
* `find cheese` returns all recipes with the keyword `cheese` in their recipe name
* `find name pancakes sandwich` returns all recipes with the keyword `pancakes` and/or `sandwich` in their names

![FindCommandUI](images/FindCommandUI.png)

* `find tag western` returns all recipes with the tag `western`
* `find ingredient tofu` returns all recipes with the ingredient `tofu`

<div markdown="block" class="alert alert-info">

* Adding a property behind `find` is optional, and if no property is specified, `find` defaults to filtering by `name`.

* All keyword queries are case-insensitive. e.g. `chicken` will match `Chicken`

* Recipes matching at least one keyword will be returned, e.g. searching `sandwich fries` will match recipes named `cheese fries` and `ham sandwich`

* Recipes are listed in the chronological order that they were added.

* The order of the keywords does not matter. e.g. `ham sandwich` will match a recipe named `sandwich ham`

* Only complete words will be matched. e.g. `chick` will **not** match `chicken`

</div>

### Deleting a recipe: `delete`

No longer like a certain recipe? Simply `delete` it from the database by its index!

Format:
`delete INDEX`

Example(s) of usage:
* `list` followed by `delete 2` deletes the 2nd item stored in the recipe book.
*  `find chicken` followed by `delete 1` will delete the 1st recipe in the displayed results of the find command.

<div markdown="block" class="alert alert-info">

* Deletes the dish at the specified `INDEX` of the current list that is displayed.

* The index **must be a positive integer** 1, 2, 3, …

</div>

<div markdown="span" class="alert alert-warning">

Keyboard shortcut:
While the recipe you want to delete is being selected, simply hit the <kbd>Delete</kbd> or 
<kbd>Backspace</kbd> key and click on confirm to delete that particular recipe!

</div>

### Searching for substitutions for an ingredient: `sub`

Short of a particular condiment or ingredient to complete your favourite recipe? Have no fear, for the `sub` command
provides you with a hassle-free way to solve your problem by suggesting some common substitutions so that you can complete your
dish with an alternative ingredient!

Format:
`sub INGREDIENT_NAME`

Example(s) of usage:
* `sub chicken` returns a list of suggested substitutions for the ingredient `chicken`

<div markdown="block" class="alert alert-info">

* `sub` searches across your stored recipes and a preloaded suggested substitutions list to provide you with the most accurate and extensive list of substitutions!

* Adding a substitution together with an ingredient is recommended since it will boost the number of substitutions listed when that ingredient is queried in the future!

* The search is case-insensitive, i.e. `sub salt` will return all stored substitutions for `Salt` and `salt` across recipes.

* Only full words will be matched i.e. `sub chick` will **not** return stored substitutes for `chicken`

* For inputs with multiple words, it will only match stored ingredients with the same full multi-word input (case-insensitive) i.e. `sub golden syrup` does not return substitutes for `syrup`

* The returned list of ingredients returned will not contain any duplicates.

</div>

### Clearing the recipe book: `clear`

Want to change things up and start a new recipe book afresh? Simply run the `clear` command to wipe the memory
and start afresh!

Format:
`clear`

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
Running `clear` will clear your entire recipe book and there's no going back!
</div>

<div markdown="block" class="alert alert-info">
* If you wish to save your data elsewhere first and then start afresh, consider using the [Export](#export) feature first!
</div>

### Asking for assistance: `help`

Unsure or unable to remember our list of commands and how to format your inputs? Fret not, as simply inputting
`help` will save you from your woes!

Format:
`help`

<div markdown="block" class="alert alert-info">

*  `help` links to this user guide, which is a quick way to refresh your memory on any command that might have slipped your mind!<br/><br/>
![HelpCommandUI](images/HelpCommandUI.png)

</div>

### Exiting the program: `exit`

Done with cooking for the day?
Running `exit` exits the program and closes the window!

Format:
`exit`

---

<div style="page-break-after: always;"></div>

## Managing the Data

### Saving the data

Recipe data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

Recipe data are saved as a [JSON file](#glossary) `[JAR file location]/data/recipebook.json`

For advanced users, you can change the default location which the recipebook data file is saved to. This can be done by creating a `preferences.json` file in the base directory where the app is stored, and specifying `userPrefsFilePath` in `preferences.json` with the desired file path.

### Editing the data file

Advanced users are welcome to update data directly by editing that data file located at the address above. 

<div markdown="span" class="alert alert-danger">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ***RIZZ***ipe will discard all data and start with an empty data file at the next run.
</div>

### Importing data

The keyboard input <kbd>F3</kbd> will open an import window from the main window (as shown in the image below). From there, select the JSON
file to be imported using the selector.

![ImportDataWindow](images/ImportWindow.jpg)

<div markdown="block" class="alert alert-info">
* Only files with recipes that pass our formatting checks, and files with no duplicate recipes will be imported
* Any imported recipes will be added to your current recipe book!
* Alternatively, you can access the import function from the <kbd>File</kbd> tab in the top left corner of the main window.
</div>

![ImportExportFromTopLeftOfWindow](images/ImportExportDisplay.jpg)


### Exporting data

The keyboard input <kbd>F4</kbd> will open an export window from the main window (as shown in the image below). From there, input the file name of the
JSON file to be exported and select the desired location for it to be saved!

![ExportDataWindow](images/ExportWindow.jpg)

<div markdown="block" class="alert alert-info">
* Alternatively, like the import function, you can access the export function via the <kbd>File</kbd> tab in the top left corner of the main window.
</div>

---
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous recipe home folder.

---
<div style="page-break-after: always;"></div>

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                                                                                                                                                                                   |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/RECIPE_NAME [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]...  [i/-n INGREDIENT_NAME [-a INGREDIENT_AMOUNT] [-e ESTIMATED AMOUNT] [-cn COMMON NAME] [-r REMARKS]... [-s SUBSTITUTION]...]... [s/RECIPE_STEPS]...` <br/> **e.g.** `add n/Chicken Noodles d/20 minutes p/1-2 people i/-n chicken thigh -a 300 g i/-n noodles i/-n soy sauce -a 2 tablespoons -s salt` |
| **List**   | `list`                                                                                                                                                                                                                                                                                                                                                                             |
| **Edit**   | `edit INDEX [n/RECIPE_NAME] [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]...  [i/-n INGREDIENT_NAME [-a INGREDIENT_AMOUNT] [-e ESTIMATED AMOUNT] [-cn COMMON NAME] [-r REMARKS]... [-s SUBSTITUTION]...]... [s/RECIPE_STEPS]...` <br/> **e.g.** `edit 2 n/Duck Rice p/1 person`                                                                                           |
| **Find**   | `find [PROPERTY] KEYWORD [ADDITIONAL KEYWORDS]...    ` <br/> **Valid properties:** `name`, `tag`, `ingredient` <br/> **e.g.** `find cheese rice`, `find name popcorn`, `find tag western`, `find ingredient tofu`                                                                                                                                                                  |
| **Delete** | `delete INDEX`<br/> **e.g.** `delete 2`                                                                                                                                                                                                                                                                                                                                            |
| **Sub**    | `sub INGREDIENT_NAME`<br/> **e.g.** `sub salt`                                                                                                                                                                                                                                                                                                                                     |
| **Clear**  | `clear`                                                                                                                                                                                                                                                                                                                                                                            |
| **Help**   | `help`                                                                                                                                                                                                                                                                                                                                                                             |
| **Exit**   | `exit`                                                                                                                                                                                                                                                                                                                                                                             |

## Keyboard shortcuts summary

| Shortcut                                                              | Functionality                                      |
|-----------------------------------------------------------------------|----------------------------------------------------|
| **<kbd>↑ Up</kbd> and <kbd>↓ Down</kbd> arrow keys in Main Window**   | Navigate through different recipes                 |
| **<kbd>\</kbd> while inputting a long command**                       | Provide line break for increased readability       |
| **<kbd>p</kbd> while a recipe is selected**                           | Open recipe details of selected recipe             |
| **<kbd>f</kbd> while a recipe is selected**                           | Open edit recipe form to edit the selected recipe  |
| **<kbd>Delete</kbd>/<kbd>Backspace</kbd> while a recipe is selected** | Delete the selected recipe (requires confirmation) |
| **<kbd>F3</kbd> while in Main Window**                                | Import a recipe book                               |
| **<kbd>F4</kbd> while in Main Window**                                | Export the current recipe book                     |
