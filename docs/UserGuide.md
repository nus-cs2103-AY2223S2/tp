---
layout: page
title: User Guide
---

Trackr is a **desktop app for managing deliveries for your business, optimised for the use of Command Line Interface**, while still having the benefits of a Graphical User Interface (GUI). If you are a busy home business owner who hates excel or is on a time crunch, this is for you.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `trackr.jar` from [here](https://github.com/AY2223S2-CS2103T-W15-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Trackr.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar trackr.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `add_order n/John Doe l/John Street d/2023-12-12 q/10 f/Cupcakes p/91234567` : Adds an order for John Doe to the order list.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add_supplier n/NAME`, `NAME` is a parameter which can be used as `add_supplier n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items in curly brackets need to have at least 1 item supplied.
  e.g. `{p/PHONE_NUMBER e/EMAIL}` can be used as `p/91234567` or `e/john@example.com` but cannot be left blank.

* Items with `…​` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Syntax: `help`

### Adding a supplier: `add_supplier` / `add_s`

Adds a supplier to the list of suppliers.

Syntax: `add_supplier n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]…​`

* A supplier can have any number of tags (including 0).

Examples:
* `add_supplier n/John Doe p/98765432 e/johnd@example.com a/John Street`
* `add_s n/Betsy Cow t/diary e/betsycow@example.com a/Betsy Street p/1234567 t/meat`


### Adding an order: `add_order` / `add_o`

Adds an order into the list of orders.

Syntax: `add_order n/CUSTOMER_NAME l/CUSTOMER_LOCATION {p/CUSTOMER_PHONE_NUMBER e/CUSTOMER_EMAIL} d/DEADLINE q/QUANTITY f/FOOD_NAME [s/STATUS] [r/REMARKS]…​`

* An order can have any number of remarks.
* Either customer phone number or email address must be provided.
* Status available for setting are: Open, Preparing, Ready, Delivering, Delivered.
* If no status is provided, it is defaulted to Open.

Examples:
* `add_order n/John Doe l/John Street d/2023-12-12 q/10 f/Cupcakes p/91234567`
* `add_o r/Urgent f/Birthday Cake q/1 n/Betsy Cow l/Betsy Street d/2023-03-03 s/Ready`

### Adding a task: `add_task` / `add_t`

Adds a task to the list of tasks.

Syntax: `add_task n/TASK_DESCRIPTION d/DEADLINE [s/STATUS]`

* Status available for setting are: `N` (Not done), `D` (Done).
* If no status is provided, it is defaulted to `N` (Not done).

Examples:
* `add_task n/Buy cookie cutter d/2022-12-22`
* `add_t n/Buy a card d/2023-12-23 s/D`

### Adding tags to a supplier: `tag_supplier` / `tag_s`

Add tags to an existing supplier entry.

Syntax: `tag_supplier INDEX t/TAG`

* Tags the supplier at the specified `INDEX`.
* The index refers to the number shown in the suppliers list displayed.
* The index **must be a positive integer** 1, 2, 3, …​
* More than one tag can be given, but at least one tag must be given.

Examples:
`tag_supplier 1 t/Supplies Sugar t/100kg per order` adds the tags `Supplies Sugar` and `100kg per order` onto of the existing tags for the 1st supplier
`tag_s 4 t/Minimum 10kg per order` adds the tag `Minimum 10kg per order` onto of the existing tags of the 4th supplier

### Editing a supplier: `edit_supplier` / `edit_s`

Edit an existing supplier’s information.

Syntax: `edit_supplier INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the supplier at the specified `INDEX`.
* The index refers to the number shown in the suppliers list displayed.
* The index **must be a positive integer** 1, 2, 3, …​
* User is required to key in at least one of the optional fields.
* Existing values will be replaced with the new values given.
* When editing tags, the existing tags of the supplier will be removed and replaced with the given tag (editing of tags is not accumulative).
* Typing `t/` without any input will remove all the existing tags.

Examples:
`edit_supplier 1 n/Johnny p/90138482 t/` edits the 1st supplier's name to `Johnny`, phone number to `90138482` and removed all of its tags
`edit_s 3 t/Supplies Flour e/mark@example.com` replaced the 3rd supplier's tags to `Supplies Flour` and edited its email to `mark@example.com`


### Editing an order : `edit_order` / `edit_o`

Edits an order that is present in the order list.

Syntax: ` edit_order INDEX [n/CUSTOMER_NAME] [l/CUSTOMER_LOCATION] [p/CUSTOMER_PHONE_NUMBER] [e/CUSTOMER_EMAIL] [d/DEADLINE] [q/QUANTITY] [f/FOOD_NAME] [s/STATUS] [r/REMARKS]…​`

* Edits the order at the specific INDEX. The index refers to the number shown in the orders list displayed. The index must be a positive integer 1, 2, 3, …
* User is required to key in at least one of the optional fields.
* Existing values will be replaced with the input values.
* When editing remarks, the existing remarks of the order will be removed and replaced with the given remarks (editing of tags is not accumulative).
* User can remove the remarks by typing r/ without specifying any remarks after it.

Examples:
*  `edit_order 1 p/91234567 d/2023-05-05` edits the phone number of the 1st order to 91234567 and changes the deadline to be 2023-05-05
*  `edit_o 3 q/20 r/` edits the quantity of food for the 3rd order to 20 and clears all remarks

### Editing a task : `edit_task` / `edit_t`

Edits a task present in the task list.

Syntax: `edit_task INDEX [n/TASK_DESCRIPTION] [d/DEADLINE] [s/STATUS]`

* Edits the task at the specific INDEX. The index refers to the number shown in the tasks list displayed. The index must be a positive integer 1, 2, 3, …
* User is required to key in at least one of the optional fields.
* Existing values will be replaced with the input values.
* When editing status, the existing status of the order will be removed and replaced with the given status.

Examples:
* `edit_task 1 n/Get creamer` edits the 1st task description to be get creamer
* `edit_t 3 d/2023-12-31 s/N` edits the 3rd task deadline to 2023-12-31 and sets the status as not done

### Finding a supplier : `find_supplier` / `find_s`

Find suppliers whose information matches with any of the given parameters.

Syntax: `find_supplier [n/NAME] [t/TAG]…​`

* Search is case-insensitive, e.g. `mark` will match `Mark`.
* The order of the keywords does not matter, e.g. `n/Mark Lee` will match with `Lee Mark`.
* At least one of the optional fields must be keyed in.
* More than one tag can be given.
* Only full words will match e.g. `Mar` will not match with `Mark`.
* People matching with at least one keyword will be returned (i.e. `OR` search).
  e.g. `n/Mark Lee` will return `Mark Tan`, `Lee Chan`.

Examples:
* `find_task n/PHOON HUAT` returns supplier `Phoon Huat` and `John Phoon`
* `find_s n/PHOON t/eggs t/flour` returns supplier `Phoon Huat` that supplies both `eggs` and `flour`

### Finding a task : `find_task` / `find_t`

Find tasks with information that matches with any of the given parameters.

Syntax: `find_task [n/TASK_DESCRIPTION] [d/DEADLINE] [s/STATUS]`

* Search is case-insensitive, e.g. `match` will match `Match`.
* The order of the keywords does not matter, e.g. `n/Mark Lee` will match with `Lee Mark`.
* At least one of the optional fields must be keyed in.
* Only full words will match e.g. `Mar` will not match with `Mark`.
* Tasks matching with at least one keyword will be returned (i.e. `OR` search).
  e.g. `n/order flour` will match with `order sugar` and `order 10kg flour`.

Examples:
* `find_task n/order flour` returns `Order milk` and `mix flour`
* `find_t n/buy eggs d/2023-02-17` returns tasks with `buy` or `egg` with deadline of `2023-02-17`
* `find_t s/N` returns all tasks not done

### Deleting a supplier: `delete_supplier` / `delete_s`

Deletes the specified supplier from the contact list.

Syntax: `delete_supplier INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the number shown in the task list displayed.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete_supplier 2` deletes the first supplier
* `find_s n/John` followed by `delete_s 3` deletes the 1st supplier in the results of the `find_s` command with name `John`

### Deleting an order: `delete_order` / `delete_o`

Syntax: `delete_order INDEX`

* Deletes the order at the specified `INDEX`.
* The index refers to the index number shown in the displayed order list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete_order 2` deletes the 2nd order in Trackr.
* `find Cake` followed by `delete_order 1` deletes the 1st order in the results of the `find` command.

### Deleting a task: `delete_task` / `delete_t`

Deletes the specified task from the task list.

Syntax: `delete_task INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the number shown in the task list displayed.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete_task 2` deletes the first task
* `find_t flour` followed by `delete_t 3` deletes the 1st task in the results of the `find_t` command

### Switching tabs: `tab`

Switch to another tab.

Syntax: `tab TAB`

* The available tabs are: `Home`, `Orders`, `Suppliers`

Examples:
* `tab t/Home` switches the tab to the `Home` tab

### Exiting the program : `exit`

Exits the program.

Syntax: `exit`

### Saving the data

Save changes after any command executed successfully. There is no need to save manually.

### Editing the data file

Trackr data are saved as a JSON file `[JAR file location]/data/trackr.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Trackr will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v1.3]`

* Different tabs for `Orders`, `Suppliers`
* Sort orders by date to keep track of orders.
* Highlight overdue orders.
* View list of all orders and tasks to prioritise your workload.
* View sales (tabulated or GUI) to track your business’s growth.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Trackr home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add_supplier / add_s` <br> e.g., `add_s n/Betsy Cow t/diary e/betsycow@example.com a/Betsy Street p/1234567 t/meat` <br> <br> `add_order / add_o` <br> e.g., `add_o n/John Doe l/John Street d/2023-12-12 q/10 f/Cupcakes` <br> <br> `add_task / add_t` <br> e.g., `add_t d/Buy a card d/2023-12-23 s/Completed`
**Edit** | `edit_supplier / edit_s` <br> e.g., `edit_s 3 t/Supplies Flour e/mark@example.com` <br> <br> `edit_order / edit_o` <br> e.g., `edit_o 3 q/20 r/` <br> <br> `edit_task / edit_t` <br> e.g., `edit_t 1 s/`
**Delete** | `delete_supplier / delete_s` <br> e.g., `delete_s 2` <br> <br> `delete_order / delete_o` <br> e.g., `delete_o 1` <br> <br> `delete_task / delete_t` <br> e.g., `delete_t 4`
**Find** | `find_supplier / find_s` <br> e.g., `find_s n/PHOON t/eggs` <br> <br> `find_order / find_o` <br> e.g., `find_order r/No almonds r/No frosting` <br> <br> `find_task / find_t` <br> e.g., `find_t s/N`
**Tab** | `tab` <br> e.g., `tab Home`
**Help** | `help`
**Exit** | `exit`
