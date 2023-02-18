---
layout: page
title: User Guide
---

***RIZZ***ipe is a **command-based recipe database** that was designed with **versatile tagging** and **searching** 
features in mind so you can always find the recipe you need! Make use of ***RIZZ***ipe's many features to achieve your 
**culinary rizz**.

- Table of Contents
  {:toc}

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add NAME`, `NAME` is a parameter which can be used as `add Grilled Salmon`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a Rizz-ipe: `add`

Come up with a new innovative recipe and want to store it for future reference,
and want to classify it by ingredients? Simply run the `add` command, and follow the prompts!

Format: 
```text
add RECIPE_NAME
```

> A Rizz-ipe can have any number of Ingredients!
> :bulb: Tip: It is okay to not add a list of ingredients when you first upload
> your rizz-ipe (you can add them later!).
> We do however recommend you add quantity when you add ingredients
> The same applies for steps.

**Example(s) of usage**:

```text
add Honey Chicken Rice
```

**Expected Output**:

```text
>>> RECIPE NAME: Honey Chicken Rice
>>> Got it. How many people does it feed?
3-4

>>> Got it. What is the recipe duration?
1 hour

>>> Would you like to add any tags? 
>>> You can add multiple (separate them with a "|").
>>> Enter `-SKIP` to skip, and once done enter `-DONE`.
Thai | Fusion

>>> I've added these tags: "Thai", "Fusion". Would you like to add more?
-DONE

>>> INGREDIENTS
>>> Add some ingredients! 
>>> You can add multiple (separate them with a "|").
>>> To skip, enter `-SKIP`. Once done, enter `-DONE`.
1 Whole Chicken

>>> Added: 1 Whole Chicken
>>> Any more?
2 cups rice | 4 tbsp honey | 2 stalks lemongrass -DONE

>>> Added: "2 cups rice", "4 tbsp honey", "2 stalks lemongrass"

>>> STEPS
>>> Would you like to add some steps? To skip, enter `-SKIP`. Once done, enter `-DONE`.
>>> Add Step #1:
Soak the rice in water.

>>> `Step #1: Soak the rice in water` added.  
>>> Add Step #2:
-SKIP

>>> Got it. I've added the recipe:
|---------------------------------|
| 1. Honey Chicken Rice           |
|    Feeds 3-4 Tags: Thai, Fusion |
|    ~ 15 mins                    |
|                                 |
|    Ingredients:                 |
|    1 Whole Chicken              |
|    2 cups rice                  |
|    ...                          |
|    Steps:                       |
|    1. Soak the rice in water.   |
|    ...                          |
|---------------------------------|
>>> You may always come back to add/delete/edit steps, tags or ingredients.
```

### Listing all Recipes : `list`

Lists all Recipes in the current cook book.

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
more Recipes.

### Viewing Recipes: `view`

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

### Deleting a Recipe: `Delete`
No longer like a certain recipe? Simply delete it from the database by its index!

Format: `delete [index]`
- Deletes the dish at the specified `INDEX`.
- The index **must be a positive integer** 1, 2, 3, …

Example of usage:

`delete 1`

Expected output:
```
Deleted recipe: Egg Fried Rice.
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

### Saving the data

Recipe data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Recipe data are saved as a JSON file `[JAR file location]/data/rizzipe.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ***RIZZ***ipe will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ***RIZZ***ipe home folder.

---

## Command summary

| Action     | Format, Examples                          |
|------------|-------------------------------------------|
| **Add**    | `add NAME`<br/> e.g., `add Grilled Salmon` |                                                                                                                                                             |            |                                      |
| **List**   | `list`                                    |                                                                                                                                                                                                                                                                                                                             |            |                                      |
| **View**   | `view INDEX`<br/> e.g., `view 2`          |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`       |                                                                                                                                                                          |            |                                      |
| **Exit**   | `exit`                                    |   


