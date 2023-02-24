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

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

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
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items in curly brackets need to have at least 1 item supplied.
  e.g. `{p/PHONE_NUMBER e/EMAIL}` can be used as `p/91234567` or `e/john@example.com` but cannot be left blank.

* Items with `…​` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Finding a supplier : `find_supplier` / `find_s`

Find suppliers whose information matches with any of the given parameters.

Syntax: `find_supplier [n/NAME] [t/TAG]…​`

* Search is case-insensitive, e.g. `mark` will match `Mark`
* The order of the keywords does not matter, e.g. `n/Mark Lee` will match with `Lee Mark`
* At least one of the optional fields must be keyed in.
* More than one tag can be given.
* Only full words will match e.g. `Mar` will not match with `Mark`
* People matching with at least one keyword will be returned (i.e. `OR` search)
  e.g. `n/Mark Lee` will return `Mark Tan`, `Lee Chan`

Examples:
* `find_task n/PHOON HUAT` returns supplier `Phoon Huat` and `John Phoon`
* `find_s n/PHOON t/eggs t/flour` returns supplier `Phoon Huat` that supplies both `eggs` and `flour`

### Finding a task : `find_task` / `find_t`

Find tasks with information that matches with any of the given parameters.

Syntax: `find_task [n/TASK_DESCRIPTION] [d/DEADLINE] [s/STATUS]`

* Search is case-insensitive, e.g. `match` will match `Match`
* The order of the keywords does not matter, e.g. `n/Mark Lee` will match with `Lee Mark`
* At least one of the optional fields must be keyed in.
* Only full words will match e.g. `Mar` will not match with `Mark`
* Tasks matching with at least one keyword will be returned (i.e. `OR` search).
  e.g. `n/order flour` will match with `order sugar` and `order 10kg flour`

Examples:
* `find_task n/order flour` returns `Order milk` and `mix flour`
* `find_t n/buy eggs d/2023-02-17` returns tasks with `buy` or `egg` with deadline of `2023-02-17`
* `find_t s/N` returns all tasks not done.

### Deleting a supplier: `delete_supplier` / `delete_s`

Deletes the specified supplier from the contact list.

Syntax: `delete_supplier INDEX`

* Deletes the task at the specified `INDEX`
* The index refers to the number shown in the task list displayed.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete_supplier 2` deletes the first supplier.
* `find_s n/John` followed by `delete_s 3` deletes the 1st supplier in the results of the `find_s` command with name `John`.

### Deleting a task: `delete_task` / `delete_t`

Deletes the specified task from the task list.

Syntax: `delete_task INDEX`

* Deletes the task at the specified `INDEX`
* The index refers to the number shown in the task list displayed.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `delete_task 2` deletes the first task.
* `find_t flour` followed by `delete_t 3` deletes the 1st task in the results of the `find_t` command.

### Switching tabs: `tab`

Switch to another tab.

Syntax: `tab TAB`

* The available tabs are: `Home`, `Orders`, `Suppliers`

Examples:
* `tab t/Home` switches the tab to the `Home` tab

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Saves changes after any command executed successfully. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/trackr.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
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
