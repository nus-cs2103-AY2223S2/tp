---
layout: page
title: User Guide
---

recipe is a **command-based recipe database** that was designed with **versatile tagging** and **searching** features in mind so you can always find the recipe you need! Make use of recipe's many features to achieve your **culinary rizz**.

## Table of Contents
1. [Features](#features)
   1. [Adding a recipe](#adding-a-recipe--add)
   2. [Listing recipes](#listing-all-recipes--list)
   3. [Viewing recipes](#viewing-recipes--view)
   4. [Deleting a recipe](#deleting-a-recipe--delete)
   5. [Exiting the program](#exiting-the-program--exit)
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

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Honey Chicken`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [g/TAG]` can be used as `n/Honey Chicken g/Thai` or as `n/Honey Chicken`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[g/TAG]…​` can be used as ` ` (i.e. 0 times), `g/Asian`, `g/Fusion g/Malay` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PERSONS_SERVED`, `p/PERSONS_SERVED n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/3-4 p/5-6`, only `p/5-6` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### Adding a recipe: `add`

Come up with a new innovative recipe and want to store it for future reference,
and want to classify it by ingredients? Simply run the `add` command!

Format: 
```text
add n/recipe_NAME \ 
    t/time p/people served [g/tag1 g/tag2...] \
    [i/INGREDIENT1 -q QUANTITY1, i/INGREDIENT2 -q QUANTITY2...] \
    [s-1/STEP 1 s-2/STEP 2...`
```

> A recipe can have any number of Ingredients!
> :bulb: Tip: It is okay to not add a list of ingredients when you first upload
> your recipe (you can add them later!).
> We do however recommend you add quantity when you add ingredients
> The same applies for steps.

**Example(s) of usage**:

```text
add n/Honey Chicken Rice t/15 minutes p/3-4 g/Thai 

OR:

add n/Lemon-Infused Salmon Fillet \
    t/1 hour p/3-4 g/Western \
    i/Lemon -q 2, i/Salmon -q 3 150g fillet \
    s-1/De-scale and remove...`
 ```

**Expected Output**:

```text
Got it. I've added the recipe(s):
| 1. Honey Chicken Rice   | 2. Lemon-Infused Salmon Fillet       |
|    Feeds 3-4 Tags: Thai |    Feeds 3-4          Tags:  Western |
|    ~ 15 mins            |    ~ 15 mins                         |
|                         |    Ingredients:                      |
|    No Ingredients are   |    2 Lemon(s)                        |
|    added yet. Add some! |    3 150g Salmon fillet(s)           |
|    [Add Here - /a_i]    |    ...                               |
|                         |    Steps:                            |
|    No Steps are ...     |    1. De-scale and remove the ...    |
```

### Listing all recipes : `list`

Lists all recipes in the current cook book.

Ever forget how many recipes you have in your storage? Want to view 'em all? Or
simply want to pick a recipe at random? Just run the `list` command.

**Example of usage**:

```text
list
```

**Expected output**:

```text
| 1. Aglio e Olio                      |
|    Feeds 3-4          Tags:  Italian |
|    ~ 15 mins                         |
|    Ingredients:                      |
|    .....                             |
|--------------------------------------|
| 2. Egg Fried Rice                    |
|    Feeds 3-4          Tags:  Asian   |
|    ~ 10 mins                         |
|    Ingredients:                      |
|    .....                             |
|--------------------------------------|
|             ....                     |
```

Lists all recipes that are in the storage, in the chronological order they were
added. Depending on the size of the window, may add multiple columns to display
more recipes.

### Viewing recipes: `view`

View a selected recipe based on specified index on current list. Current list may
change when using find or filter(coming soon).

Example of usage:

```text
view 2
```

Expected outcome:

```text
| 2. Curry Chicken                  |
|    Feeds 3-4         Tags: Indian |
|    ~ 1 hour                       |
|    Ingredients:                   |
|    Curry paste 8 oz./250 g        |
| ......                            |
| Steps:                            |
| 1. Cut the chicken into pieces... |
| ......                            |
| ......                            |
```

Recipe descriptions are returned, which display mainly its ingredients and lists the steps
to cook it.

### Deleting a recipe: `Delete`
No longer like a certain recipe? Simply delete it from the database by its index!

Format: `delete [index]`
- Deletes the dish at the specified `INDEX`.
- The index **must be a positive integer** 1, 2, 3, …​

Example of usage:

`delete 1`

Expected output:
```
Deleted dish: Egg Fried Rice.
```

### Exiting the program : `exit`

Exits the program.

Example of usage: 

```text
exit
```

Expected output:
```text
We hope you have attained that ~chef RIZZ :) See you later (with rizz)
```

## Managing the Data 

### Saving the data

recipe data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

recipe data are saved as a JSON file `[JAR file location]/data/recipe.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, recipe will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous recipe home folder.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                      |
| ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Add**    | `add n/NAME t/TIME p/PEOPLE_SERVED [g/TAG...][i/INGREDIENT -q QUANTITY...][s-INDEX/STEP...]…​` <br> e.g., `add n/Lemon-Infused Salmon Fillet t/1 hour p/3-4 g/Western i/Lemon -q 2, i/Salmon -q 3 150g fillet s-1/De-scale and remove...` |                                                                                                                                                             |            |                                      |
| **List**   | `list`                               |                                                                                                                                                                                                                                                                                                                             |            |                                      |
| **View**   | `view INDEX`<br/> e.g., `view 2`     |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`  |                                                                                                                                                                          |            |                                      |
| **Exit**   | `exit`                               |     

