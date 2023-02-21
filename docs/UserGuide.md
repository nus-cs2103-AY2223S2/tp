

---
layout: page
title: User Guide
---

CookHub is a **desktop app for managing recipes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). This recipe tracking app is targeted for student chefs on a tight budget and schedule with limited ingredients. 


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `cookhub.jar` from [here](https://github.com/AY2223S2-CS2103T-W09-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your CookHub.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar cookhub.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add n/NAME` : Add a new recipe.
   * `delete` : Delete a new recipe.
   * `list` : Lists all recipes.
   * `view {recipe no.}` : View a recipe in greater detail.


1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g.

* Items in square brackets are optional.<br>
  e.g 

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. 

* Parameters can be in any order.<br>
  e.g. 

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. 

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. 

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Add recipe : `add`

Add a new recipe, step by step:

Enter the title 
Enter the ingredients, line by line. After all the ingredients have been entered, use the /finish command to proceed to the next step.
Enter the cooking instructions, step by step. After all the instructions, have been entered, use the /finish command

### Delete recipe : `delete {recipe no.}`
Remove a recipe
- Recipe removed from list, number in the list shifted up 

### View recipe : `view {recipe no.}`
Gets a detailed view of the recipe specified by the {recipe no.}
The {recipe no.} refers to the index number shown in the displayed recipe list.
The {recipe no.} must be a positive integer (eg. 1, 2, 3).

Format: `view {recipe no.}`

![view recipe](images/viewRecipe.png)

### List recipe : `list`

Lists out all the recipes that you have added to CookHub

Format: `list`

Example: list
Expected outcome:

Here are all your recipes in CookHub:
1. Lasagna
2. Risotto
3. Kimchi Soup



---

### Saving the data

CookHub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/cookhub.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CookHub will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CookHub home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------