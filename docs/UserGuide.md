---
layout: page
title: User Guide
---

SudoHR is a **desktop app specially catered for HR professionals in managing manpower, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SudoHR can get your management tasks done faster than traditional GUI apps.

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

### Adding a person: `add`

Adds an employee to SudoHR.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An employee can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Shows a list of all employees in SudoHR.

Format: `list`

### Editing an employee : `edit`

Edits an existing employee in SudoHR.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the employee at the specified `INDEX`. The index refers to the index number shown in the displayed employees list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the employees will be removed i.e adding of tags is not cumulative.
* You can remove all the employee’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st employee to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd employee to be `Betsy Crower` and clears all existing tags.

### Locating employees by name: `find`

Finds employees whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Employees matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting an employee : `delete`

Deletes the specified employee from the address book.

Format: `delete INDEX`

* Deletes the employee at the specified `INDEX`.
* The index refers to the index number shown in the displayed employee list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd employee in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st employee in the results of the `find` command.

### Adding a department: `jonathan`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Updating a department: `jonathan`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Deleting a department: `jonathan`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all departments: `jonathan`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Add employee to a department: `jonathan`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Remove employee from a department: `jonathan`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### List all employees in a department: `jonathan`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Adding a project: `kenneth`

Adds a project to the system

Format: `ap n/NAME`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A project can have any number of employees (including 0)
</div>

Examples:
* `ap n/Human Resources`
* `ap n/Development`

### Updating a project: `[Coming soon]`

### Deleting a project: `[Coming soon]`

### Listing all projects: `[Coming soon]`

### Add user to a project: `[Coming soon]`

### Remove employee from a project: `[Coming soon]`

### List all employees in a project: `[Coming soon]`

### Adding a leave: `andre`

Tracks a person's leave.

Format: `add_leave n/NAME d/DATE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add_leave n/John Doe d/2023-03-05`
* `add_leave n/Betsy Crowe d/2023-03-05~2023-03-10`

### Updating a leave: `andre`

TBC. Seemingly only relevant if we introduce half-day leaves.

Format: `TBC`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `TBC`
* `TBC`

### Deleting a leave: `andre`

Removes leave dates that are now cancelled.

Format: `del_leave n/NAME d/DATE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `del_leave n/John Doe d/2023-03-05`
* `del_leave n/Betsy Crowe d/2023-03-06~2023-03-08`

### Listing all leaves for a person: `andre`

Lists all the leave date(s) of an employee.

Format: `ls_leave n/NAME`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `ls_leave n/John Doe `
* `ls_leave n/Betsy Crowe`

### Listing all users on leave for a given date: `andre`

Lists all personnel that are on leave for a given date(s).

Format: `ls_on_leave d/DATE`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `ls_on_leave d/2023-03-05`
* `ls_on_leave d/2023-03-05~2023-05-7`

### Adding a performance review statement: `Kwangjoo`


Adds a performance review statement for a specified employee. Allows option to specify the subsequent performance review date. If unspecified, the next performance review date is set to default interval.


Format: `addPR INDEX pr/REVIEW_STATEMENT d/DATE [nextPRd/DATE]​`


Examples:

* `addPR 1 pr/Met Expectations this month, expected to be promoted at the end of this year. d/2023-03-23`
* `addPR 2 pr/Below expectations. To be sent for counselling and training regarding low performance. Early performance review next week d/2023-03-23 nextPRd/2023-03-20`

### Listing all performance reviews done on employee: `Kwangjoo`

Lists all the performance review of the specified employee.

Format: `listPR INDEX`


Examples:

* `listPR 1`

### Deleting a performance review statement: `Kwangjoo`

Deletes erroneous performance reviews possibly due to administrative errors. There can only be at most one performance review per person in a given day.

<!-- It could be better to include a unique employee id so that the user wont mess up and delete a wrong performance review.  -->

Format: `deletePR INDEX d/DATE`

Examples:

* `deletePR 3 d/2023-03-23`

### Listing all employees due for a performance review: `Kwangjoo`

Lists all employees who have a performance review due within the next specified number of days. If the DAYS in unspecified, set to default number of days.

A date can be specified and SudoHR shows the employees who need to have their performance reviewed by the specified date.

If both DAYS and DATE is specified, DATE takes precedence. And If neither is specified, SudoHR displays the performance review due within the default number of days.

Format: `listPRdue [within/DAYS] [by/DATE]​`

Examples:

* `listPRdue within/5`
* `ListPRdue by/2023-04-05`

### Adding an event: `addEvent`

Adds an event to the HR management System.

Format: `addEvent t/title d/description s/date ​`

Examples:
* `addEvent t/Company Dinner d/Company dinner at Raffles Hotel d/2014-02-12`


### List all employees attending an event: `listEvent`

Lists the employees added to an event in the HR Management

Format: `listEvent`

Examples:
* `listEvent`


### Updating an event: `updateEvent`

Updates an event to the HR Management System.

Format: `updateEvent INDEX t/title d/description s/date ​`

* Edits the event at the specified `INDEX`. The index refers to the index number shown in the displayed event list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
* `updateEvent 2 t/Company Dinner d/Company dinner at Raffles Hotel d/2014-02-12`

### Deleting an event: `deleteEvent`

Deletes an event from the HR Management System.

Format: `deleteEvent INDEX`

* Edits the event at the specified `INDEX`. The index refers to the index number shown in the displayed event list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteEvent 1`

### Adding an employee to an event: `addEmployeeEvent`

Adds a person to the event in the HR Management System.

Format: `addEmployeeEvent EVENTINDEX EMPLOYEEINDEX`

* Add the employee at the specified `EMPLOYEEINDEX` to the event at the specified `EVENTINDEX`. The `EVENTINDEX` refers to the index number shown in the displayed event list. The `EMPLOYEEINDEX` refers to the index number shown in the displayed employee list.  Both indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `addEmployeeEvent 1 1`

### List all employees attending an event: `listEventEmployee`

Lists the employees added to an event in the HR Management

Format: `listEventEmployee INDEX​`

* List the employees attending the event at the specified `INDEX`. The index refers to the index number shown in the displayed event list. The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listEventEmployee 1`


### Remove employee from an event: `deleteEmployeeEvent`

Remove a person from a event in the HR Management System.

Format: `deleteEmployeeEvent EVENTINDEX EMPLOYEEINDEX`

* Remove the employee at the specified `EMPLOYEEINDEX` from the event at the specified `EVENTINDEX`. The `EVENTINDEX` refers to the index number shown in the displayed event list. The `EMPLOYEEINDEX` refers to the index number shown in the **displayed list of employees added to the event**.  Both indexes **must be a positive integer** 1, 2, 3, …​

Examples:
* `deleteEmployeeEvent 1 1`


### Clearing all entries : `clear`

Clears all entries from SudoHR.

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