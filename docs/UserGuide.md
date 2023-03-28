---
layout: page
title: User Guide
---

SudoHR is a **desktop app specially catered for HR professionals in managing employees, departments and leaves data,
optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface
(GUI). If you can type fast, SudoHR can get your HR management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Features

There are 3 main types of data in SudoHR:
* Employees
* Departments
* Leaves

SudoHR allows you to manage these components by:
1. Creating, updating, listing and deleting of the 3 data types.
2. Adding/Removing employees in departments/leaves.
3. Apply different filters on the data.

<div markdown="span" class="alert alert-warning">**WARNING:**
SudoHR can only hold up to 10,000 employees, 10,000 departments, 10,000 leaves
and 10,000 tags! Exceeding this limit will result in slower performance or unforeseen
problems.
</div>

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `sudohr.jar`.

1. Copy the file to the folder you want to use as the _home folder_ for your application.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar sudohr.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Quick Reference Guide

### Layout

[//]: # (<-- Insert labelled UI here -->)

### Key definitions

[//]: # (GENERAL FORMAT OF DATA TYPE DEFINITION:)

[//]: # (1. Explain big idea)
[//]: # (2. Explain fields)
[//]: # (3. Explain constraints)

#### Employee

#### Department

A department is a group for employees. A department can have many employees and
an employee can be in many departments.

Every employee in a department must be unique.

The following attributes are stored for each department:
1. Department name

Departments are unique by name and case-sensitive. You cannot add more than one
department of the same name.

#### Leave

#### Prefixes

[//]: # (Explain prefixes in the command and their corresponding placeholders)

#### Placeholders

[//]: # (List placeholders in the command)
[//]: # (Talk about their constraints, type, format, etc)

### Command Format

[//]: # (Explain the general command format: command, prefixes, placeholders)

### Trying your first command

[//]: # (<-- Insert example context here -->)

--------------------------------------------------------------------------------------------------------------------

## Commands

### Viewing help : `help`

Shows a message explaining how to access the help page for SudoHR.

![help message](images/helpMessage.png)

Format: `help`

### Adding an employee: `add`

Adds an employee to SudoHR.

Format: `add id/ID n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An employee can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all employees : `list`

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

Deletes the specified employee from SudoHR.

Format: `delete INDEX`

* Deletes the employee at the specified `INDEX`.
* The index refers to the index number shown in the displayed employee list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd employee in SudoHR.
* `find Betsy` followed by `delete 1` deletes the 1st employee in the results of the `find` command.

### Adding a department: `adep`

Adds a department by name.

Format: `adep n/DEPARTMENT_NAME`

<div markdown="span" class="alert alert-primary">**NOTE:**
As of now, a department only has a department name field. In the future, we plan to add other
department-level details such as manager, department start date, parent department, etc.
</div>

Examples:
* `adep n/Software Engineering`
* `adep n/Marketing`

### Editing a department: `edep`

Edits an existing department.

Format: `edep OLD_DEPARTMENT_NAME n/NEW_DEPARTMENT_NAME`

Examples:
* `edep Software Engineering n/Software Development`
* `edep Marketing n/Sales`

### Deleting a department: `ddep`

Deletes an existing department.

Format: `ddep n/DEPARTMENT_NAME`

Examples:
* `ddep n/Software Engineering`
* `ddep n/Sales`

### Listing all departments: `ldep`

Lists all existing departments.

Format: `ldep`

### Add employee to a department: `aetd`

Adds an employee to a department using his ID.

Format: `aetd eid/EMPLOYEE_ID n/DEPARTMENT_NAME`

<div markdown="span" class="alert alert-primary">**NOTE:**
You cannot add an employee to a department twice.
</div>

Examples:
* `aetd eid/1 n/Software Engineering`
* `aetd eid/100 n/Sales`

### Remove employee from a department: `refd`

Removes an employee from a department using his ID.

Format: `refd eid/EMPLOYEE_ID n/DEPARTMENT_NAME`

Examples:
* `refd eid/1 n/Software Engineering`
* `refd eid/100 n/Sales`

### List an employee's departments: `led`

List all departments and employee is in.

Format: `led eid/EMPLOYEE_ID`

Examples:
* `leid eid/100`

### List all employees in a department: `leid`

List all employees in a department.

Format: `leid n/DEPARTMENT_NAME`

Examples:
* `leid n/Software Engineering`
* `leid n/Sales`

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

### Clearing all entries : `clear`

Clears all entries from SudoHR.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SudoHR data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SudoHR data are saved as a JSON file `[JAR file location]/data/sudohr.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SudoHR will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SudoHR home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                                | Format                                                           |
|---------------------------------------|------------------------------------------------------------------|
| **Add an employee**                   | `add id/ID n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`    |
| **List all employees**                | `list`                                                           |
| **Edit an employee**                  | `edit eid/ID [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​` |
| **Find employee by name**             | `find KEYWORD [MORE_KEYWORDS]`                                   |
| **Delete an employee**                | `delete eid/ID`                                                  |
| **Add a department**                  | `adep n/DEPARTMENT_NAME`                                         |
| **List all departments**              | `ldep`                                                           |
| **Edit a department**                 | `edep OLD_DEPARTMENT_NAME n/NEW_DEPARTMENT_NAME`                 |
| **Delete a department**               | `ddep n/DEPARTMENT_NAME`                                         |
| **Add employee to department**        | `aetd eid/EMPLOYEE_ID n/DEPARTMENT_NAME`                         |
| **Remove employee from department**   | `refd eid/EMPLOYEE_ID n/DEPARTMENT_NAME`                         |
| **List an employee's department**     | `led eid/EMPLOYEE_ID`                                            |
| **List all employees in a department** | `leid n/DEPARTMENT_NAME`                                         |