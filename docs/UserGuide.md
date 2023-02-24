---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding an order: `add_order / add_o`

Adds an order into the list of orders.

Syntax: `add_order n/CUSTOMER_NAME l/CUSTOMER_LOCATION {p/CUSTOMER_PHONE_NUMBER e/CUSTOMER_EMAIL} d/DEADLINE q/QUANTITY f/FOOD_NAME [s/STATUS] [r/REMARKS]...`

* An order can have any number of remarks.
* Either customer phone number or email address must be provided
Status available for setting are: Open, Preparing, Ready, Delivering, Delivered
* If no status is provided, it is defaulted to Open

Examples:
* `add_order n/John Doe l/John Street d/2023-12-12 q/10 f/Cupcakes p/91234567`
* `add_o r/Urgent f/Birthday Cake q/1 n/Betsy Cow l/Betsy Street d/2023-03-03 s/Ready`

### Editing an order : `edit_order / edit_o`

Edits an order that is present in the order list.

Syntax: ` edit_order INDEX [n/CUSTOMER_NAME] [l/CUSTOMER_LOCATION] [p/CUSTOMER_PHONE_NUMBER] [e/CUSTOMER_EMAIL] [d/DEADLINE] [q/QUANTITY] [f/FOOD_NAME] [s/STATUS] [r/REMARKS]...`

* Edits the order at the specific INDEX. The index refers to the number shown in the orders list displayed. The index must be a positive integer 1, 2, 3, … 
* User is required to key in at least one of the optional fields 
* Existing values will be replaced with the input values 
* When editing remarks, the existing remarks of the order will be removed and replaced with the given remarks (editing of tags is not accumulative)
* User can remove the remarks by typing r/ without specifying any remarks after it


Examples:
*  `edit_order 1 p/91234567 d/2023-05-05 ` Edits the phone number of the 1st order to 91234567 and changes the deadline to be 2023-05-05

*  `edit_o 3 q/20 r/` Edits the quantity of food for the 3rd order to 20 and clears all remarks

###Deleting a task: `delete_task / delete_t`

Syntax: `delete_order INDEX`

* Deletes the order at the specified `INDEX`.
* The index refers to the index number shown in the displayed order list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete_order 2` deletes the 2nd order in Trackr.
* `find Cake` followed by `delete_order 1` deletes the 1st order in the results of the `find` command.
 

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
