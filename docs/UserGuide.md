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

Shows a message explaining how to access the help page, as well as a quick rundown of what commands can be used.

![help message](images/helpMessage.png)

Format: `help`

## Adding a category: `addcat`

Adds a new expense category. If the category already exists, this command will not execute.

Format: `addcat c/CATEGORY_NAME s/SUMMARY`

| Parameter       | Description                                         |
|-----------------|-----------------------------------------------------|
| `CATEGORY_NAME` | Title of the category to be added.                  |
| `SUMMARY`        | Short summary of what this category keeps track of. |


Examples:
* `addcat c/Groceries s/for living` creates a new `Groceries` category with the summary of `for living`.
* `addcat c/Entertainment s/for fun!` creates a new `Entertainment` category with the summary of `for fun!`.

## Deleting a category: `delcat`

Deletes an expense category at the specified `INDEX`.

Format: `delcat INDEX`

| Parameter | Description                                                                                                                                                                                                                                                                               |
|-----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`    | The index number shown in the displayed categories list.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ...<br/><br/>Expenses previously categorised under the category at the specified index will no longer be part of that category, and will be re-categorized under the `MISC` category.<br/> |


Examples:
* `lcat` followed by `delcat 2` deletes the second category in the log
* `lcat` followed by `delcat 1` deletes the first category in the log


## Adding an expense: `add`

Adds an expense to the expense tracker.

Format: `add c/CATEGORY_NAME n/ITEM_NAME p/PRICE [d/DATE]`

| Parameter       | Description                                                                                                                                                                       |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CATEGORY_NAME` | The category which the expense should be classified under.<br/><br/>If there is no such category, a new category will be created with the specified category name.                |
| `ITEM_NAME`     | Name of the expense being added.                                                                                                                                                  |
| `PRICE`         | The price of the expense being added.<br/><br/>The specified price should be a `double`, e.g. 4, 4.50.                                                                            |
| `DATE`           | The date of the expense being added.<br/><br/> This is an optional input, and if left unspecified, the date of which the command is issued will be the expense's date by default. |

Examples:
* `add c/groceries n/milk p/4.50 `
* `add c/entertainment p/20 n/movie night d/14/2/23`

## Deleting an expense : `delete`

Deletes an expense at the specified `INDEX` from the expense tracker.

Format: `delete INDEX`

| Parameter | Description                                                                                                                                                                                                                                                                               |
|-----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`    | The index number shown in the displayed categories list.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ...<br/><br/>Expenses previously categorised under the category at the specified index will no longer be part of that category, and will be re-categorized under the `MISC` category.<br/> |

Examples:
* `list` followed by `delete 2` deletes the second expense in the log
* `find movie` followed by `delete 1` deletes the first expense in the results of the `find` command

## Listing Categories: `lcat`
Shows a list of categories in the log.

Format: `lcat`

## Listing expenses : `list`

Shows a list of expenses in the expense tracker based on the specified `CATEGORY_NAME` and `TIMEFRAME`.

If `CATEGORY_NAME` and `TIMEFRAME` are left unspecified, all expenses in the expense tracker will be listed.

Format: `list [c/CATEGORY_NAME] [t/TIMEFRAME]`

| Parameter       | Description                                                                                                               |
|-----------------|---------------------------------------------------------------------------------------------------------------------------|
| `CATEGORY_NAME` | The category of which expenses are classed under.<br/><br/>Optional to specify.                                           |
| `TIMEFRAME`      | The timeframe of which expenses were added. <br/><br/>The timeframes available are:<br/>1. week <br/>2. month<br/>3. year |

Examples:
* `list c/Groceries t/week`
* `list c/Entertainment t/month`

## Editing a category : `ecat`

Edits the category at the specified `INDEX`

Format: `ecat INDEX [c/CATEGORY_NAME] [s/SUMMARY]`

Both `CATEGORY_NAME` and `SUMMARY` are optional by themselves, but at least one of them MUST be specified in addition
to `INDEX`, otherwise the command will not go through.

| Parameter       | Description                                                                                       |
|-----------------|---------------------------------------------------------------------------------------------------|
| `INDEX`         | The index of the category to be edited.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ...  |
| `CATEGORY_NAME` | The new name of the category being edited at the specified index.<br/><br/>Optional parameter.    |
| `SUMMARY`        | The new summary of the category being edited at the specified index.<br/><br/>Optional parameter. |


## Editing an expense : `eexp`

Edits the expense at the specified `INDEX`

Format: `eexp INDEX [c/CATEGORY_NAME] [n/EXPENSE_NAME] [d/DATE] [p/PRICE]`

Every parameter except for `INDEX` is optional by themselves, but at least one of other parameters MUST be
specified, otherwise the command will not go through.

| Parameter       | Description                                                                                     |
|-----------------|-------------------------------------------------------------------------------------------------|
| `INDEX`         | The index of the expense to be edited.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ... |
| `CATEGORY_NAME` | The new category name of the expense to be changed to.<br/><br/>Optional parameter.             |
| `EXPENSE_NAME`  | The new expense name of the expense to be changed to.<br/><br/>Optional parameter.              |
| `DATE`          | The new date of the expense to be changed to.<br/><br/>Optional parameter.                      |
| `PRICE`          | The new price of the expense to be changed to.<br/><br/>Optional parameter.                     |

## Search for an expense by name: `find`

Find expenses whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `dinner` will match `Dinner`
* The order of the keywords does not matter. e.g. `ramen Dinner` will match `Dinner ramen`
* Only the name of the expense is searched
* Only full words will be matched e.g. `dinn` will not match `dinner`
* Expenses matching at least one keyword will be returned (i.e. `OR` search)
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
