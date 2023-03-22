---
layout: page
title: FastTrack User Guide
---

FastTrack is an easy-to-use **financial management desktop application** designed for NUS undergraduate students who are living on a tight budget.
With a combination of a Command Line Interface (CLI) and Graphical User Interface (GUI), our app provides a user-friendly and efficient way to track your expenses and manage your finances.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `fastTrack.jar` from [here](https://github.com/AY2223S2-CS2103T-W09-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for FastTrack.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar fastTrack.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list -t` : Lists all expenses

   * `add c/groceries n/milk p/4.50 d/14/2/23` : Adds an expense named `milk` to the expenses list with a price of $4.50 and a date of 14/02/2023

   * `delete 3` : Deletes the 3rd expense shown in the current list

   * `exit` : Exits the app

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add c/CATEGORY_NAME`, `CATEGORY_NAME` is a parameter which can be used as `add c/groceries`.

* Items in square brackets are optional.<br>
  e.g `p/PRICE [d/DATE]` can be used as `p/4.50 d/14/2/2023` or as `p/4.50`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[c/CATEGORY_NAME]…​` can be used as ` ` (i.e. 0 times), `c/groceries`, `c/groceries c/food` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/CATEGORY_NAME p/PRICE`, `p/PRICE c/CATEGORY_NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/4.50 p/5.80`, only `p/5.80` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

## Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

## Adding a category: `cat`

Adds a new expense category. If the category already exists, this command will not execute.

Format: `cat CATEGORY_NAME`
Examples:
* `cat groceries` creates a new `Groceries` category
* `cat entertainment` creates a new `Entertainment` category

## Deleting a category: `delcat`

Deletes an expense category at the specified `INDEX`.

* The index refers to the index number shown in the displayed categories list.
* The index **must be a positive integer** 1, 2, 3, …​
* If expenses previously categorised under the specified category will no longer be part of that category and remain uncategorized

Format: `delcat INDEX`
Examples:
* `lcat` followed by `delcat 2` deletes the second category in the log
* `lcat` followed by `delcat 1` deletes the first category in the log


## Adding an expense: `add`

Adds an expense to the log.

Format: `add c/CATEGORY_NAME n/ITEM_NAME p/PRICE [d/DATE]`

Examples:
* `add c/groceries n/milk p/4.50 `
* `add c/entertainment p/20 n/movie night d/14/2/23`


## Listing Categories: `lcat`
Shows a list of categories in the log.

Format: `lcat`

## Listing expenses : `list`

Shows a list of expenses in the log.

### List all expenses
Format: `list -t`

### List expenses filtered by categories
Format: `list -c c/CATEGORY_NAME [c/CATEGORY_NAME]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
`list -c` can have 1 or more categories to filter by
</div>

### List expenses from the past week
Format: `list -w`

## Editing an expense : `edit`

- To be implemented in next iteration

## Search for an expense by name: `find`

Find expenses whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `dinner` will match `Dinner`
* The order of the keywords does not matter. e.g. `ramen Dinner` will match `Dinner ramen`
* Only the name of the expense is searched
* Only full words will be matched e.g. `dinn` will not match `dinner`
* Persons matching at least one keyword will be returned (i.e. `OR` search)
  e.g. `movie dinner` will return `dinner with Alex`, `movie with friends`

Examples:

```
Suppose you have 3 expenses logged:

Date: 2023-03-02, Category: Dining, Name: McDonald's, Price: $7.50
Date: 2023-03-02, Category: Dining, Name: KFC, Price: $6.00
Date: 2023-03-03, Category: Groceries, Name: Milk, Price: $4.00
```
* `find kfc milk` returns `Milk` and `KFC`
* `find mcdonald's` returns `McDonald's`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

## Deleting an expense : `del`

Deletes the specified expense from the log.

Format: `delete INDEX`

* Deletes the expense at the specified `INDEX`
* The index refers to the index number shown in the displayed expense list
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the second expense in the log
* `find movie` followed by `delete 1` deletes the first expense in the results of the `find` command

## Clearing all entries : `clear`

Clears all entries from the expense log.

Format: `clear`

## Exiting the program : `exit`

Exits the program.

Format: `exit`

## Saving the data

All data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

Expenses data are saved as a JSON file `[JAR file location]/data/fastTrack.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FastTrack will discard all data and start with an empty data file at the next run.
</div>

## Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FastTrack home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Expense** | `add c/CATEGORY_NAME n/ITEM_NAME p/PRICE [d/DATE]` <br> e.g., `add c/entertainment n/movie night p/20 d/14/2/23`
**Delete Expense** | `delete INDEX`<br> e.g., `delete 3`
**List Expenses** | All expenses: `list -t`<br>Filter by categories:`list -c c/CATEGORY_NAME [c/CATEGORY_NAME]…​`<br>From past week: `list -w`
**List Categories** | `lcat`
**Find Expense** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find movie`
**Add Category** | `cat CATEGORY_NAME`
**Delete Category** | `delcat CATEGORY_NAME`
**Help** | `help`
