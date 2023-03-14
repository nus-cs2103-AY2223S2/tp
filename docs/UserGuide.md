---
layout: page
title: User Guide
---

ExecutivePro (EP) is a **desktop app for Human Resource managers to manage their employee information, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, EP can get your employee management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ExecutivePro.jar` from here (TODO: insert link when available).

1. Copy the file to the folder you want to use as the _home folder_ for your ExecutivePro.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ExecutivePro.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`list`** and pressing Enter will list down all the current employees.<br>

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features
<div markdown="block" class="alert alert-info">

* Tags are optional.<br>
  e.g `[n/NAME] [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

</div>

### Viewing help : `help` [coming soon]


### Adding a employee: `add`

Adds a employee to the ExecutivePro database.

Format: `add [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

A person can have any number of tags (including 0)

</div>
Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Marketing`
* `add n/Betsy Crowe p/1234567 e/betsycrowe@example.com a/Newgate street, block 576, #01-02 d/Sales t/friend`


### Listing all employees : `list`

Shows a list of all employees and their details in the ExecutivePro database.

Format: `list`

### Editing a employee : `edit`

Edits an employee’s details in the ExecutivePro database.

Format: `edit EMPLOYEE_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...`

* Edits the details of the employee with the specified `EMPLOYEE_ID`. If such an employee doesn’t exist, an error message will be shown.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Changes the phone number and email address of the employee with ID `1` to be `91234567` and `johndoe@example.com` respectively.

### Locating employees by keyword: `find`

Shows a list of all employees in address book whose names match the keyword provided.

Format: `find KEYWORD [MORE_KEYWORDS]`

* Displays list of employees matching at least one keyword if multiple keywords are provided.
* Keyword is for the name only, not any other details

Examples:
* `find John` displays list of all employees whose full name contains a 'John' in it  


### Deleting a employee : `delete`

Deletes the specified employee from the ExecutivePro database.

Format: `delete EMPLOYEE_ID`

* Deletes the details of the employee with the specific `EMPLOYEE_ID`.
* The EMPLOYEE_ID refers to the id of an employee shown in the displayed employees list.
* The EMPLOYEE_ID **must be a positive integer** 1, 2, 3, …​

Examples:
`delete 2` deletes the employee with EMPLOYEE_ID 2 in ExecutivePro.

### Changing the UI theme : `theme`

Applies the specified theme to ExecutivePro's UI (either `dark` or `light`).

Format: `theme THEME_NAME`

* Applies the theme with the specified `THEME_NAME` to ExecutivePro's UI.
* `THEME_NAME` is either `dark` (white text on dark background) or `light` (black text on white background).

Examples:
`theme light` applies the Light theme to ExecutivePro's UI.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ExecutivePro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ExecutivePro data are saved as a JSON file `[JAR file location]/data/executivepro.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ExecutivePro will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                                                                                                                |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add EMPLOYEE_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...` <br> e.g., `add 1 n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Marketing t/friends` |
| **Delete** | `delete EMPLOYEE_ID`<br> e.g., `delete 3`                                                                                                                                                                       |
| **Edit**   | `edit EMPLOYEE_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...`<br> e.g.,`edit 1 p/91234567 e/johndoe@example.com`                                                                 |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                      |
| **List**   | `list`                                                                                                                                                                                                          |
| **Help**   | `help`                                                                                                                                                                                                          |
