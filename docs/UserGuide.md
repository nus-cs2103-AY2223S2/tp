---
layout: page
title: User Guide
---

***RIZZ***ipe is a **command-based recipe database** that was designed with **versatile tagging** and **searching** 
features in mind so you can always find the recipe you need! Make use of ***RIZZ***ipe's many features to achieve your 
**culinary rizz**.


## Table of Contents
1. [Features](#features)
   1. [Adding a recipe](#adding-a-recipe-add)
   2. [Listing recipes](#listing-all-recipes-list)
   3. [Finding a recipe by name](#finding-a-recipe-by-name-find)
   4. [Deleting a recipe](#deleting-a-recipe-delete)
   5. [Clearing the recipe book](#clearing-the-recipe-book-clear)
   6. [Asking for assistance](#asking-for-assistance-help)
   7. [Exiting the program](#exiting-the-program-exit)
2. [Managing Data](#managing-the-data)
   1. [Saving the data](#saving-the-data)
   2. [Editing the data file](#editing-the-data-file)
   3. [Archiving data files](#archiving-data-files-coming-in-v20)
3. [FAQ](#faq)
4. [Command Summary](#command-summary)

---

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.



</div>

### Adding a recipe: `add`

Come up with a new innovative recipe and want to store it for future reference,
and want to classify it by ingredients? Simply run the `add` command, and follow the prompts!

Format:
`add n/RECIPE_NAME [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]... [s/RECIPE_STEPS]... [i/RECIPE_INGREDIENTS]...`

> :bulb: Tip: A recipe can have any number of steps, tags and ingredients (including 0)!
> :bulb: Tip: It is only compulsory to include the **recipe name** when you first add the recipe into recipe book!
> Although optional, we do however still suggest adding in the other fields if possible for your own convenience.
> We recommend you add in your own units of measurement for the optional fields! (i.e g/kg for ingredient weights)
> If you have multiple inputs for tags, steps or ingredients, treat each input as a separate field (i.e s/Step1 s/step2)
> Each field does not have to be input in order (/p can come before /d etc.)
> However, for steps, please input the steps in the order that they are intended to be performed

**Example(s) of usage**:
* `add n/Honey Chicken Rice`
* `add n/Chicken Noodles d/20 minutes p/1-2 people i/chicken thigh i/noodle i/soy sauce`
* `add n/Peanut Butter Sandwich t/breakfast s/Prepare bread and spread s/Using a knife, spread 2-3 scoops of peanut
butter s/Serve`


### Listing all recipes: `list`

Ever forget how many recipes you have in your storage? Want to view 'em all? Or
simply want to pick a recipe at random? Just run the `list` command.

Format:
`list`

> Lists all recipes that are in the storage, in the chronological order they were
added. 
> Depending on the size of the window, the user may add multiple columns to display
more recipes.
> :bulb: Tip: The `list` command can be used to reset the most recently searched list to the full recipe list after performing a `find` command!
> In order to zoom in on a particular recipe in the list, user can double-click on the corresponding recipe


### Finding a recipe by name: `find`

Have a certain recipe at the back of your mind that you want to refer to? `find` helps to save your time scrolling through your whole list of recipes by displaying
only those that match any of your specified keywords straight away.

Format:
`find KEYWORD [KEYWORDS]...`

> Recipes matching at least one keyword will be returned, e.g. searching `sandwich fries` will return the search results `cheese fries` and `ham sandwich`
> Recipes are listed in the chronological order that they were added.
> Only the name of the recipe is searched
> The search is case-insensitive. e.g. chicken will match Chicken
> The order of the keywords does not matter. e.g. ham sandwich will match sandwich ham
> Only full words will be matched. e.g. chick will **not** match chicken

Example of usage:
* `find cheese` returns all recipes with the keyword `cheese`
* `find prawn tofu` returns all recipes with the keyword `prawn` and all recipes with the keyword `tofu`


### Deleting a recipe: `delete`

No longer like a certain recipe? Simply delete it from the database by its index!

Format: 
`delete INDEX`

> Deletes the dish at the specified `INDEX` of the latest list that was displayed.
> The index **must be a positive integer** 1, 2, 3, …

Example of usage:
* `list` followed by `delete 2` deletes the 2nd item stored in the recipe book.
*  `find chicken` followed by `delete 1` will delete the 1st recipe in the displayed results of the find command.


### Clearing the recipe book: `clear`

Want to change things up and start a new recipe book afresh? Simply run the `clear` command to wipe the memory
and start afresh!

Format:
`clear`


### Asking for assistance: `help`

Unsure or unable to remember our list of commands and how to format your inputs? Fret not, as simply inputting
`help` will save you from your woes.

Format:
`help`

> :bulb: Tip: `help` links to this user guide, which is a quick way to refresh your memory on any command that might have slipped your mind!


### Exiting the program: `exit`

Exits the program and closes the window. 

Format:
`exit`


## Managing the Data 

### Saving the data

Recipe data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Recipe data are saved as a JSON file `[JAR file location]/data/recipes.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ***RIZZ***ipe will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous recipe home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                              |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/RECIPE_NAME [d/RECIPE_DURATION] [p/RECIPE_PORTION] [t/RECIPE_TAGS]... [s/RECIPE_STEPS]... [i/RECIPE_INGREDIENTS]...`<br/> e.g., `add n/Chicken Noodles d/20 minutes p/1-2 people i/chicken thigh i/noodle i/soy sauce` |                                                                                                                                                             |            |                                      |
| **List**   | `list`                                                                                                                                                                                                                        |                                                                                                                                                                                                                                                                                                                             |            |                                      |
| **Find**   | `find KEYWORD [KEYWORDS]...    `<br/> e.g., `find cheese rice`                                                                                                                                                                |
| **Delete** | `delete INDEX`<br/> e.g., `delete 2`                                                                                                                                                                                          |
| **Clear**  | `clear`                                                                                                                                                                                                                       |                                                                                                                                                                          |            |                                      |
| **Help**   | `help`                                                                                                                                                                                                                        |
| **Exit**   | `exit`                                                                                                                                                                                                                        |   



