---
layout: page
title: FastTrack User Guide
---

FastTrack is an easy-to-use **financial management desktop application** designed for NUS SoC undergraduate students who are living on a tight budget.
With a combination of a Command Line Interface (CLI) and Graphical User Interface (GUI), our app provides a user-friendly and efficient way to track your expenses and manage your finances.

* Table of Contents
  {:toc}
1. [Quick Start](#quick-start)
2. Getting Familiar with CLI
3. [Features](#features)
    1. Commands
        1. [Viewing Help](#viewing-help--help)
        2. [Adding a Category](#adding-a-category-addcat)
        3. [Deleting a Category](#deleting-a-category-delcat)
        4. [Adding an Expense](#deleting-an-expense--delete)
        5. [Deleting an Expense](#deleting-an-expense--delete)
        6. [Listing Categories](#listing-categories-lcat)
        7. [Listing Expenses](#listing-expenses--list)
        8. [Editing a Category](#editing-a-category--ecat)
        9. [Editing an Expense](#editing-an-expense--eexp)
        10. [Searching for an expense by name](#search-for-an-expense-by-name-find)
        11. [Clearing all Entries](#clearing-all-entries--clear)
        12. [Exiting the Program](#exiting-the-program--exit)
    2. [Saving Data](#saving-the-data)
    3. [Editing the Data File](#editing-the-data-file)
    4. [Archiving Data Files](#archiving-data-files-coming-in-v20)
4. [Frequently Asked Questions](#faq)
5. [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `fastTrack.jar` from [here](https://github.com/AY2223S2-CS2103T-W09-2/tp/releases).

3. Drag the file into a folder you want to use as the _home folder_ for FastTrack.

4. Double-click the FastTrack JAR file to run the application.

5. Alternatively, you can open the file through a command terminal like Windows Powershell on Windows, Terminal on MacOS.
    1. Open your command terminal.
    2. `cd` into the folder you put the jar file in and press Enter. Example: `cd [FILEPATH]`, where `[FILEPATH]` can be obatined from checking the *Properties* of your fastTrack.jar file by right-clicking.
    3. Use the `java -jar fastTrack.jar` command and press Enter to run the application.

   After Step 4 or Step 5, A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

![Ui](images/ui.png)

6. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all expenses

    * `add c/groceries n/milk p/4.50 d/14/2/23` : Adds an expense named `milk` to the expenses list with a price of $4.50 and a date of 14/02/2023

    * `delete 3` : Deletes the 3rd expense shown in the current list

    * `exit` : Exits the app

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Getting Familiar with the Command Line Interface (CLI)

If you have never used a **Command Line Interface** before, please read this quick guide before using the application.
Familiarization with the CLI will be beneficial for entering commands for expenses more efficiently,
saving time in the long run over the usage of FastTrack.

The **Command Line Interface (CLI)** is interacted primarily through single-line text commands. This means that any expense
can be added with just one line.

Commands are in the form
```
command [tag][parameter for tag] [tag2][parameter] ...
```

The name of the command, the first word in the text specifies what command you wish the application to execute, while
the following tags behind and their parameters further provide information for the program to execute the program
properly.

For example, adding an expense into FastTrack:
```
add n/Apple p/2.0 c/Food d/1/1/23
```
`add` is the **name** of the command you wish to execute, in this case, adding an expense.
</br>
`n/` is a **tag** to specify which parameter the further instructions you add are referring to. In this case, we are
specifying that the next words we enter will be the **name** of the expense, Apple.

Similarly, `p/`, `c/` and `d/` are also **tags** to specify that we are entering the values for the _Price_, _Category_ and _Date_
respectively.

For some commands, some of these **tags** are optional. Therefore, if the **tag** is not present in the command text,
FastTrack will use a _default_ option, with no need to specify the values.



## About
This section gives an overview of the layout of FastTrack and defines terminologies and conventions used throughout this user guide.

### Graphical User Interface (GUI)
The following diagrams highlight the different sections of the _Graphical User Interface (GUI)_ of FastTrack.

![FastTrack GUI](images/demo/intro/fasttrack_labeled_1.png)
![FastTrack GUI](images/demo/intro/fasttrack_labeled_2.png)
![FastTrack GUI](images/demo/intro/fasttrack_labeled_3.png)


| Part of FastTrack         | Description                                                                                                                                                                                                                         |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Expense Display           | Displays the list of saved one-time expenses with filters applied (if any). This display occupies the _Main View_ section. (Refer to the [Expense Features](#expense-features) below for more details).                             |
| Category Display          | Displays the list of saved categories, including the number of expenses associated with each category. This display occupies the _Main View_ section. (Refer to the [Category Features](#category-features) below for more details) |
| Recurring Expense Display | Displays the list of saved recurring expenses. This display occupies the _Main View_ section. (Refer to the [Expense Features](#expense-features) below for more details)                                                           |
| Results Display           | Displays the feedback from the application after entering a command, which can be used to indicate that a command has succeeded or failed. It's role is to provide textual guidance for the user.                                   |
| Command Box               | A text input field where the user can type in a command for FastTrack to execute.                                                                                                                                                   |
| Expense Summary Display   | A visual display containing spending statistics (Refer to the feature [Expense Statistics](#expense-statistics-feature) below for more details on these statistics)                                                                 |
| Toolbar                   | Contains buttons which allow the user to access the user guide and exit from the application.                                                                                                                                       |



## What are categories, expenses and recurring expenses?
The following section contains detailed explanations of common terms used in FastTrack, namely **Category**, **Expense**, and a **Recurring Expense** and how they work in detail. It is important to familiarise yourself with these behaviors, so you can use FastTrack to its fullest potential.

### Category
A category is represented with a _name_ such as `Groceries` or `Entertainment` and is used to classify an expense. A category can have **multiple expenses** under it. This is extremely useful when you want to find out what expenses fall under a specific category.
In FastTrack, you can also set an optional _summary_, which is a brief text description containing more information about the category.

Note that a default miscellaneous category with the name `Misc` exists within FastTrack, but is not modifiable or accessible to the user. Unclassified expenses fall under this default `Misc` category.

See more detailed instructions in [Category Commands](#Category Features).

<div markdown="block" class="alert alert-info">

**:information_source: Info**<br>
Note that category names in FastTrack are case-insensitive. For example, a category named "Groceries" will be treated as the exact same category as "groceries".
</div>

### Expense
Represents a single expense entry which comprises the _name_ of the expense, it's _price_, the _category_ it falls under and _date_ of expense. In FastTrack, an expense must have **one category** associated with it.

FastTrack allows for expenses containing the exact same details to be duplicated, giving you the freedom to record down multiple repeated expenses within the same day. For example, if you bought 3 coffees at the exact same price of `$3.40` on `11/2/2023` and name the expense `Coffee` under the category `Food`, FastTrack allows you to log all 3 repeated expenses without issues - how convenient!


### Recurring Expense
Do you have a recurring cloud-based service subscription? How about a Netflix subscription? These expenses would fall under the **Recurring Expense** section of FastTrack.

A recurring expense is able to generate expenses automatically at specified intervals, such as daily, weekly, monthly, or yearly.

Recurring expenses are very similar to a typical expense, except that they require additional details such as a _start date_ and _interval_, with an optional _end date_ (if you would like to terminate the recurring expense).


See more detailed instructions in [Expense Commands](#Expense Features).

## Features

The features of FastTrack can be divided into 4 groups, **Category Features**, **Expense Features**, **General Features** and **Expense Statistics Feature**. With these 4 groups in mind, remembering the different commands becomes extremely convenient, as each group contains mainly 4 types of operations - add, delete, edit and list!
1. [**Expense features**](#expense-features)
    * Add an expense
    * Edit an expense
    * Delete an expense
    * Find an expense by keyword
    * List expenses
        * Filter by category
        * Filter by time-span
    * Add a recurring expense
    * Edit a recurring expense
    * Delete a recurring expense
    * List recurring expenses
2. [**Category features**](#category-features)
    * Add a category
    * Edit a category
    * Delete a category
    * List categories
   
3. [**General features**](#general-features)
    * Set a budget
    * Category autocompletion
    * Clear all entries
    * Exit FastTrack
    * View help
    * Saving the data
    * Editing the data file
   
4. [**Expense Statistics Feature**](#expense-statistics-feature)
   * Monthly spending statistic
   * Monthly remaining statistic
   * Monthly percentage change statistic
   * Weekly spending statistic
   * Weekly remaining statistic
   * Weekly percentage change statistic
   * Total spent statistic
   * Budget utilisation percentage statistic

   
You can make use of these features by typing in commands to FastTrack. The following are some handy tables which show summarise all available commands in FastTrack. The tables also come with examples on how to use these commands.

### Category Features Command Summary

| Feature                                            | Command Format                              | Examples                           |
|----------------------------------------------------|---------------------------------------------|------------------------------------|
| [**List Categories**](#listing-categories-lcat)    | `lcat`                                      | `lcat`                             |
| [**Add Category**](#adding-a-category-addcat)      | `addcat c/CATEGORY_NAME s/SUMMARY`          | `addcat c/Groceries s/for living`  |
| [**Delete Category**](#deleting-a-category-delcat) | `delcat INDEX`                              | `delcat 1`                         |
| [**Edit Category**](#editing-a-category-edcat)     | `edcat INDEX [c/CATEGORY_NAME] [s/SUMMARY]` | `edcat 1 c/New Name s/New Summary` |

### Expense Features Command Summary

| Feature                                                              | Command Format                                                                        | Examples                                                       |
|----------------------------------------------------------------------|---------------------------------------------------------------------------------------|----------------------------------------------------------------|
| [**List Expenses**](#listing-expenses-list)                          | `list [c/CATEGORY_NAME] [t/TIMEFRAME]`                                                | `list c/Food t/month`                                          |
| [**Add Expense**](#adding-an-expense-add)                            | `add c/CATEGORY_NAME n/ITEM_NAME p/PRICE [d/DATE]`                                    | `add c/Food p/20 n/Mac d/14/2/23`                              |
| [**Delete Expense**](#deleting-an-expense-delete)                    | `delete INDEX`                                                                        | `delete 1`                                                     |
| [**Edit Expense**](#editing-an-expense-edexp)                        | `edexp INDEX [c/CATEGORY_NAME] [n/EXPENSE_NAME] [d/DATE] [p/PRICE]`                   | `edexp 1 c/Food n/Mac d/20/4/23 p/10`                          |
| [**Find Expense**](#search-for-an-expense-by-keyword-find)           | `find KEYWORD [MORE_KEYWORDS]`                                                        | `find KFC chicken`                                             || [**List Recurring Expense**](#listing-recurring-expense-lrec)        | `lrec`                                                                                | `lrec`                                                         |
| [**List Recurring Expense**](#listing-recurring-expenses-lrec)       | `lrec`                                                                                | `lrec`                                                         |
| [**Add Recurring Expense**](#adding-a-recurring-expense-addrec)      | `addrec c/CATEGORY_NAME n/ITEM_NAME p/PRICE t/INTERVAL sd/START_DATE [ed/END_DATE]`   | `addrec c/Shows n/Netflix p/10 t/month sd/10/3/23 ed/10/03/24` |
| [**Delete Recurring Expense**](#deleting-a-recurring-expense-delrec) | `delrec INDEX`                                                                        | `delrec 1`                                                     |
| [**Edit Recurring Expense**](#editing-a-recurring-expense-edrec)     | `edrec INDEX [c/CATEGORY_NAME] [n/EXPENSE_NAME] [p/PRICE] [t/INTERVAL] [ed/END_DATE]` | `edrec 1 c/Show n/Disney Plus p/2 t/week ed/10/5/24`           |

### General Features Command Summary

| Feature                                       | Command Format | Examples     |
|-----------------------------------------------|----------------|--------------|
| [**Set Budget**](#setting-a-budget-set)       | `set p/AMOUNT` | `set p/1000` |
| [**Help**](#viewing-help-help)                | `help`         | `help`       |
| [**Exit program**](#exiting-fasttrack-exit)   | `exit`         | `exit`       |
| [**Clear data**](#clearing-all-entries-clear) | `CLEAR`        | `CLEAR`      |

<div markdown="block" class="alert alert-info">

**:information_source: Information about the command format**<br>

Before diving further into the guide, here are some things to take note about the way we formatted commands for FastTrack in this user guide.
* Words in `UPPER_CASE` are called parameters. These are inputs that need to be supplied by the user.<br>
  e.g. in `add c/CATEGORY_NAME`, `CATEGORY_NAME` is a parameter, which the user decides is "groceries". The final command will be entered as `add c/groceries`.

* Items in square brackets are optional.<br>
  e.g `p/PRICE [d/DATE]` means the `DATE` parameter is optional, and can be omitted from the command.`p/4.50 d/14/2/2023` can also be used as `p/4.50`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `c/CATEGORY_NAME p/PRICE`, `p/PRICE c/CATEGORY_NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the **last occurrence** of the parameter will be taken.<br>
  e.g. if you specify `p/4.50 p/5.80`, only `p/5.80` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
</div>

# Category Features

## Listing Categories `lcat`

Displays the list of categories in FastTrack.

Format: `lcat`

### Demonstration
1. Type `lcat` into the command box
2. FastTrack displays the list of categories with the confirmation message `Listed all categories`

![FastTrack lcat](images/demo/category/lcat.png)

## Adding a category `addcat`

Adds a new category to FastTrack. If a category with the same name already exists, this command will not execute.

Format: `addcat c/CATEGORY_NAME s/SUMMARY`

| Parameter       | Description                                         |
|-----------------|-----------------------------------------------------|
| `CATEGORY_NAME` | Title of the category to be added.                  |
| `SUMMARY`       | Short summary of what this category keeps track of. |


### Examples
* `addcat c/Groceries s/for living` creates a new `Groceries` category with the summary of `for living`.
* `addcat c/Entertainment s/for fun!` creates a new `Entertainment` category with the summary of `for fun!`.

### Demonstration
1. Enter the command `lcat` to switch to the **Category Display**
2. Enter the command `addcat c/Groceries s/for living` into the command box
3. FastTrack adds the new category to the category list with the confirmation message `New category added: groceries`

![FastTrack addcat](images/demo/category/addcat.png)

## Deleting a category `delcat`

Deletes the category at the specified `INDEX` in the category list.

Format: `delcat INDEX`

| Parameter | Description                                                                                                                                                                                                                                                |
|-----------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`   | The index number shown in the displayed category list.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ...<br/><br/>Expenses previously categorised under the specified category will be automatically re-categorized under the `Misc` category.<br/> |


### Examples
* `lcat` followed by `delcat 2` deletes the second category in the category list
* `lcat` followed by `delcat 1` deletes the first category in the category list

### Demonstration
1. Enter the command `lcat` to switch to the **Category Display**
2. Enter the command `delcat 7` into the command box
3. FastTrack deletes the seventh category `Food` from the category list with the confirmation message `Deleted category: food`

![FastTrack delcat](images/demo/category/delcat.png)

## Editing a category `edcat`

Edits the category at the specified `INDEX` in the category list.

Format: `edcat INDEX [c/CATEGORY_NAME] [s/SUMMARY]`

Both `CATEGORY_NAME` and `SUMMARY` are optional by themselves, but **at least** one of them must be specified in addition
to `INDEX`, otherwise the command will not be executed.

| Parameter       | Description                                                                                               |
|-----------------|-----------------------------------------------------------------------------------------------------------|
| `INDEX`         | The index of the category to be edited.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ...          |
| `CATEGORY_NAME` | The new name of the category being edited at the specified index.<br/><br/>This parameter is optional.    |
| `SUMMARY`       | The new summary of the category being edited at the specified index.<br/><br/>This parameter is optional. |

### Examples
- `edcat 1 c/Drink` changes the name of the first category in the category list to `Drink`
- `edcat 2 c/Food s/Eating out` changes the name and summary of the second category in the category list to `Food` and `Eating out` respectively.

### Demonstration
1. Enter the command `lcat` to switch to the **Category Display**
2. Enter the command `edcat 1 c/Drink` into the command box
3. FastTrack edits the name of the first category `Food` from the category list to `Drink` with the confirmation message `Edited category: Drink`

![FastTrack edcat1](images/demo/category/edcat1.png)
![FastTrack edcat2](images/demo/category/edcat2.png)

# Expense Features

## Listing expenses `list`

Displays the list of expenses in FastTrack with the option of applying filters based on the specified `CATEGORY_NAME`, `TIMEFRAME`.

If `CATEGORY_NAME` filter is specified, only the expenses categorized under that specific category will be displayed.
If `TIMEFRAME` filter is specified, only the expenses that fall within that timeframe are displayed.

If `CATEGORY_NAME` and `TIMEFRAME` are left unspecified, all expenses in the expense tracker will be listed by default.

Format: `list [c/CATEGORY_NAME] [t/TIMEFRAME] [r/RECUR_PERIOD]`

| Parameter       | Description                                                                                                                                                                                                 |
|-----------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CATEGORY_NAME` | The name of the category of which expenses are classed under.<br/><br/>This parameter is optional.                                                                                                          |
| `TIMEFRAME`     | The timeframe of which expenses were added. <br/><br/>The timeframes available are:<br/>1. `week` (alias: `w`) <br/>2. `month` (alias: `m`)<br/>3. `year` (alias: `y`)<br/><br/>This parameter is optional. |

<div markdown="block" class="alert alert-info">
**:information_source: What is a timeframe?**<br>

A `TIMEFRAME` allows you to set a specific interval to filter your expenses. `t/w` or `t/week` is a timeframe representing the current week, while `t/m` or `t/month` is a timeframe representing the current month, and `t/y` or `t/year` is a timeframe representing the current year.

</div>

### Examples
* `list`
* `list c/Groceries t/week`
* `list c/Entertainment t/month`
* `list c/Food`
* `list t/w`
* `list c/Entertainment t/year`

### Demonstration
1. Enter the command `list c/drink` into the command box
2. FastTrack displays the expenses under the category `Drink` with the confirmation message `2 expenses listed`. The number of expenses may differ for every user.

![FastTrack list1](images/demo/expense/list1.png)

1. Enter the command `list` into the command box
2. FastTrack displays all expenses with the confirmation message `5 expenses listed`. The number of expenses may differ for every user.

![FastTrack list2](images/demo/expense/list2.png)

1. Enter the command `list t/w` into the command box
2. FastTrack displays all expenses within the current week with the confirmation message `2 expenses listed`. The number of expenses may differ for every user.

![FastTrack list3](images/demo/expense/list3.png)

<div markdown="block" class="alert alert-info">
**:information_source: Using both `CATEGORY_NAME` and `TIMEFRAME` filters:**<br>
* Using both the category and timeframe filters will only display expenses that satisfy both the filter conditions.<br>
  e.g. in `list c/food t/week`, only expenses with both the category name "Food" and date falling within the current week will be displayed.
</div>


## Adding an expense `add`

Adds a new one-time expense to FastTrack.

Format: `add c/CATEGORY_NAME n/ITEM_NAME p/PRICE [d/DATE]`

| Parameter       | Description                                                                                                                                                                                                          |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CATEGORY_NAME` | The category which the expense should be classified under.<br/><br/>If there is no such category in FastTrack, a new category will be created with the specified category name.                                      |
| `ITEM_NAME`     | Name of the expense being added.                                                                                                                                                                                     |
| `PRICE`         | The price of the expense being added.<br/><br/>The specified price should be a number, e.g. 4, 4.50.                                                                                                                 |
| `DATE`          | The date of the expense being added.<br/><br/> The date format should be d/m/yyyy.<br/><br/> This is an optional input, and if left unspecified, the date of the expense will be set to the current date by default. |


### Examples
* `add c/groceries n/milk p/4.50 `
* `add c/entertainment p/20 n/movie night d/14/2/23`

### Demonstration
1. Enter the command `add c/groceries n/milk p/4.50` into the command box
2. FastTrack adds the new expense under the new category `Groceries` with the confirmation message `New expense added: Name: milk, Amount: $4.5, Date: 2023-04-07, Category: groceries`.

![FastTrack add1](images/demo/expense/add1.png)

3. Enter the command `lcat` to switch to the **Category Display**. Notice how FastTrack has automatically created a new category `Groceries` in the category list!

![FastTrack add2](images/demo/expense/add2.png)



## Deleting an expense `delete`

Deletes an expense at the specified `INDEX` in the expense list.

Format: `delete INDEX`

| Parameter | Description                                                                                                       |
|-----------|-------------------------------------------------------------------------------------------------------------------|
| `INDEX`   | The index number shown in the displayed categories list.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ... |

### Examples
* `list` followed by `delete 2` deletes the second expense in the log
* `find movie` followed by `delete 1` deletes the first expense in the results of the `find` command

### Demonstration
1. Type the command `list` to switch to the **Expense Display**
2. Enter the command `delete 2` into the command box
3. FastTrack deletes the second expense in the expense list with the confirmation message `Deleted expense: Name: milk, Amount: $4.5, Date: 2023-04-07, Category: groceries`.


## Editing an expense `edexp`

Edits the expense at the specified `INDEX` in the expense list.

Format: `edexp INDEX [c/CATEGORY_NAME] [n/EXPENSE_NAME] [d/DATE] [p/PRICE] [r/RECUR_PERIOD]`

Every parameter except for `INDEX` is optional by themselves, but **at least** one of them must be specified in addition
to `INDEX`, otherwise the command will not be executed.

| Parameter       | Description                                                                                                                                        |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`         | The index of the expense to be edited.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ...                                                    |
| `CATEGORY_NAME` | The new category name of the expense to be changed to.<br/><br/>This parameter is optional.                                                        |
| `EXPENSE_NAME`  | The new expense name of the expense to be changed to.<br/><br/>This parameter is optional.                                                         |
| `DATE`          | The new date of the expense to be changed to.<br/><br/> The date format should be d/m/yyyy.<br/><br/>This parameter is optional.                   |
| `PRICE`         | The new price of the expense to be changed to.<br/><br/>The specified price should be a number, e.g. 4, 4.50.<br/><br/>This parameter is optional. |

### Examples
* `edexp 1 c/groceries` changes the category of the first expense in the expense tracker
* `edexp 2 p/20 n/movie night` changes the price and name of the second expense in the expense tracker


### Demonstration
1. Type the command `list` to switch to the **Expense Display**
2. Enter the command `edexp 2 p/20 n/movie night c/entertainment`
3. FastTrack edits the second expense in the expense list with the confirmation message `Edited expense: Name: movie night, Amount: $20.0, Date: 2023-04-03, Category: entertaiment`.

![FastTrack edexp1](images/demo/expense/edexp1.png)
![FastTrack edexp2](images/demo/expense/edexp2.png)

## Search for an expense by keyword `find`

Find expenses whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `dinner` will match `Dinner`
* The order of the keywords does not matter. e.g. `ramen Dinner` will match `Dinner ramen`
* Only the name of the expense is searched
* Only full words will be matched e.g. `dinn` will not match `dinner`
* Expenses matching at least one keyword will be returned
  e.g. `movie dinner` will return `dinner with Alex`, `movie with friends`

### Examples

Suppose you have 3 expenses logged:
```
Date: 2023-03-02, Category: Food, Name: McDonald's, Price: $7.50
Date: 2023-03-02, Category: Food, Name: KFC, Price: $6.00
Date: 2023-03-03, Category: Groceries, Name: Milk, Price: $4.00
```
* `find kfc milk` returns `Milk` and `KFC`
* `find mcdonald's` returns `McDonald's`<br>

### Demonstration
1. Enter the command `find movie` into the command box to find expenses with the keyword `movie`
2. FastTrack filters the expense list to show only the expenses matching the given keyword, with the confirmation message `Edited expense: Name: movie night, Amount: $20.0, Date: 2023-04-03, Category: entertaiment`.

![FastTrack find](images/demo/expense/find.png)


## Listing Recurring Expenses `lrec`

Displays the list of recurring expenses in FastTrack.

Format: `lrec`

### Demonstration
1. Type `lrec` into the command box
2. FastTrack displays the list of recurring expenses with the confirmation message `Listed all recurring expenses`

![FastTrack lrec](images/demo/recurring_expense/lrec.png)

## Adding a Recurring Expense `addrec`

Adds a recurring expense to FastTrack.

Format: `addrec c/CATEGORY_NAME n/ITEM_NAME p/PRICE t/INTERVAL sd/START_DATE [ed/END_DATE]`

| Parameter       | Description                                                                                                                                                                  |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CATEGORY_NAME` | The category which the recurring expense should be classified under.<br/><br/>If there is no such category, a new category will be created with the specified category name. |
| `ITEM_NAME`     | Name of the recurring expense being added.                                                                                                                                   |
| `PRICE`         | The price of the recurring expense being added.<br/><br/>The specified price should be a number, e.g. 4, 4.50.                                                               |
| `INTERVAL`      | The period with which the expense is recurring.<br/><br/> The timeframes available are:<br/>1. `day` <br/>2. `week` <br/>3. `month` <br/> 4. `year`                          |
| `START_DATE`    | The starting date of the recurring expense. <br/><br/> The date format should be d/m/yyyy.                                                                                   |
| `END_DATE`      | The ending date of the recurring expense.<br/><br/> The date format should be d/m/yyyy. <br><br> This parameter is optional.                                                 |

<div markdown="block" class="alert alert-info">

**:information_source: Info**<br>

Note that once a recurring expense is added, it automatically adds a series of expenses to the expense list at the specified interval until the `END_DATE`. If an `END_DATE` is not yet specified, the expenses will be added up to the current date.
</div>


<div markdown="block" class="alert alert-warning">

**:exclamation: Caution**<br>
If possible, avoid using an `END_DATE` which is too far in the future, or a `START_DATE` which is too far in the past. 
For example, if the current date is `3/2/2023` and the `START_DATE` is set to `3/2/2000` (23 years ago) with an `INTERVAL` of `day` -  this will lead to a huge number of (more than 8000) expenses being automatically generated and this may cause FastTrack to become temporarily unresponsive.
</div>


### Examples
* `addrec n/milk c/groceries p/4.50 sd/20/3/2023 t/month`
* `addrec n/milk c/groceries p/4.50 sd/20/3/2023 ed/15/5/2023 t/w`

### Demonstration
1. Enter the command `addrec n/milk c/groceries p/4.50 sd/20/1/2023 t/week` to create a weekly recurring expense starting on 20/1/2023.
2. FastTrack creates the new recurring expense with the confirmation message `New recurring expense added: Recurring Expense: milk, Amount: 4.5, Category: groceries, Start Date: 2023-01-20, End Date: Ongoing, Recurring Expense Type: WEEKLY`

![FastTrack addrec1](images/demo/recurring_expense/addrec1.png)


3. Enter the command `list` to switch to the **Expense Display**. Notice that FastTrack has automatically added the weekly expenses in the expense list!

![FastTrack addrec2](images/demo/recurring_expense/addrec2.png)

## Deleting a recurring expense `delrec`

Deletes an expense category at the specified `INDEX` in the recurring expense list.

Format: `delrec INDEX`

| Parameter | Description                                                                                                              |
|-----------|--------------------------------------------------------------------------------------------------------------------------|
| `INDEX`   | The index number shown in the displayed recurring expense list.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ... |

### Examples
* `lrec` followed by `delrec 2` deletes the second recurring expense in the log
* `lrec` followed by `delrec 1` deletes the first recurring expense in the log


### Demonstration
1. Enter the command `lrec` to switch to the **Recurring Expense Display**
2. Enter the command `delrec 2`
3. FastTrack deletes the second recurring expense in the recurring expense list with the confirmation message `Deleted recurring expense: Recurring Expense: Netflix, Amount: 16, Category: entertainment, Start Date: 2023-01-01, End Date: Ongoing, Recurring Expense Type: MONTHLY`.

![FastTrack delrec](images/demo/recurring_expense/delrec.png)

## Editing a recurring expense `edrec`

Edits the expense at the specified `INDEX`

Format: `edrec INDEX [c/CATEGORY_NAME] [n/EXPENSE_NAME] [p/PRICE] [t/INTERVAL] [ed/END_DATE]`

Every parameter except for `INDEX` is optional by themselves, but at least one of other parameters MUST be
specified, otherwise the command will not go through.

| Parameter       | Description                                                                                                                                                                                |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `INDEX`         | The index of the recurring expense to be edited.<br/><br/>It must be a positive integer i.e. 1, 2, 3, ...                                                                                  |
| `CATEGORY_NAME` | The new category name of the recurring expense to be changed to.<br/><br/>This parameter is optional.                                                                                      |
| `EXPENSE_NAME`  | The new expense name of the recurring expense to be changed to.<br/><br/>This parameter is optional.                                                                                       |
| `PRICE`         | The new price of the recurring expense to be changed to.<br/><br/>The specified price should be a number, e.g. 4, 4.50.<br/><br/>This parameter is optional.                               |
| `INTERVAL`      | The new recurrence period of the expense to be changed. <br>The timeframes available are:<br/>1. `day` <br/>2. `week` <br/>3. `month` <br/> 4. `year`<br/><br/>This parameter is optional. |
| `END_DATE`      | The new ending date of recurring expense.<br/><br/> The date format should be d/m/yyyy. <br/><br/>This parameter is optional.                                                              |


<div markdown="block" class="alert alert-warning">

**:exclamation: Caution**<br>
If you **edit** a recurring expense to terminate it, with the `END_DATE` before the current date, this does not remove the expenses that were generated when the recurring expense was first added. This is because FastTrack always charges a recurring expense up to the current date.
If you plan on terminating a recurring expense, do so before the intended `END_DATE` so that FastTrack knows when to stop adding expenses.

</div>

### Examples
* `edrec 1 c/groceries t/week` updates the category and recurrence period first recurring expense in the expense tracker.
* `edrec 2 p/4.50 ed/15/5/2023` updates the price and ending date of the second recurring expense in the expense tracker.

### Demonstration
1. Enter the command `lrec` to switch to the **Recurring Expense Display**
2. Say you have upgraded to a Netflix yearly subscription plan - enter the command `edrec 2 p/200 t/year` 
3. FastTrack edits the second recurring expense in the recurring expense list with the confirmation message `Edited recurring expense generator: Recurring Expense: Netflix, Amount: 200.0, Category: entertainment, Start Date: 2023-01-20, End Date: Ongoing, Recurring Expense Type: YEARLY`.

![FastTrack edrec1](images/demo/recurring_expense/edrec1.png)
![FastTrack edrec2](images/demo/recurring_expense/edrec2.png)


# General Features


## Setting A Budget `set`

Sets a monthly budget for FastTrack. For first-time users of FastTrack, no budget is set and some expense statistics are not updated. 

In order to view all the expense statistics, you must first set a budget using this command.

FastTrack derives the weekly budget from this monthly budget by dividing the monthly budget by 4.

Format `set p/AMOUNT`

| Parameter | Description                                                                              |
|-----------|------------------------------------------------------------------------------------------|
| `AMOUNT`  | The monthly budget amount to set. The specified budget should be a number, e.g. 4, 4.50. |


<div markdown="block" class="alert alert-warning">

**:exclamation: Caution**<br>
FastTrack does not allow setting a budget of $0
</div>


### Examples
* `set p/500` sets the monthly budget of FastTrack to $500

### Demonstration
1. Enter the command `set p/500` to set the monthly budget of FastTrack to $500
2. FastTrack updates the monthly budget to $500 with the confirmation message `Monthly budget successfully set to $500.0`

![FastTrack set](images/demo/general/set.png)

## Category Autocompletion

FastTrack will provide a list of suggested category names, displayed as a popup above the command box when `c/` is entered.

### Select a Suggested Category in List
- Press the `UP` arrow key on your keyboard to navigate into the suggestions list. 
- Choose a suggested category name using both the `UP` and `DOWN` arrow keys. 
- To select the highlighted category, press the `ENTER` key on the keyboard. The command box will autocomplete the selected category name suggestion.
- To navigate out of the suggestions list, press the `DOWN` arrow key until reaching the bottom of the suggestions list, then press the `DOWN` arrow key once again to return to the command box.

### Select the first Suggested Category
To select the first (bottom-most) suggested category option without having to navigate into the suggestions list, press the `TAB` key on your keyboard to trigger the autocompletion.

<div markdown="block" class="alert alert-warning">
**:exclamation: Caution**<br>
For category autocompletion to work, there should not be any text in front of `c/` in the command box. i.e. `c/` must be the last 2 characters the user has keyed in.
</div>


### Demonstration

1. Enter `list c/` into the command box
2. A list of suggested categories appear in a popup above the command box
3. Navigate into the suggestion list and press `ENTER` on the desired category `Transportation`. 
4. This autocompletes the category name

![FastTrack autocomplete_a1](images/demo/general/autocomplete_a1.png)
![FastTrack autocomplete_a2](images/demo/general/autocomplete_a2.png)

1. Enter `list c/` into the command box
2. A list of suggested categories appear in a popup above the command box
3. If the desired category `Shopping` is the first suggestion in the list (the bottom-most suggestion), press `TAB` within the command box.
4. This autocompletes the category name

![FastTrack autocomplete_b1](images/demo/general/autocomplete_b1.png)
![FastTrack autocomplete_b2](images/demo/general/autocomplete_b2.png)

# Expense Statistics Feature


![FastTrack expense_statistic](images/demo/general/summary.png)

## Clearing all entries `CLEAR`

Clears all entries from FastTrack. This command removes all stored expenses, recurring expenses and categories.

Format: `CLEAR`

<div markdown="block" class="alert alert-warning">
**:exclamation: Caution**<br>

This command will delete **all** the data stored in FastTrack apart from the stored monthly budget. To minimise the risk of accidentally using this command, we have made it such that the command only works when the `CLEAR` is fully uppercase.

Exercise caution before using this command.
</div>

### Demonstration

1. Enter `CLEAR` in the command box
2. FastTrack clears all previously logged expenses, recurring expenses and categories, with the confirmation message `Deleted all prior entries`.

![FastTrack clear](images/demo/general/clear.png)

## Exiting FastTrack `exit`

After logging your expenses, you might want to close the application and ensure your data is saved.
This command closes FastTrack and saves the data to the `fastTrack.json` file located on computer's hard disk.

Format: `exit`


## Viewing help `help`

Shows a message explaining how to access the help page, as well as a quick rundown of what commands can be used.

![help message](images/helpMessage.png)

Format: `help`

## Saving the data

All data in FastTrack are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

## Editing the data file

FastTrack's data are saved as a JSON file `[JAR file location]/data/fastTrack.json`. Advanced users who are familiar with JSON (JavaScript Object Notation) are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, FastTrack will discard all data and start with an empty data file at the next run.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous FastTrack home folder.

--------------------------------------------------------------------------------------------------------------------
