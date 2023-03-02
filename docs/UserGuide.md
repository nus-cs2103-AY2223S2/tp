---
layout: page
title: User Guide
---

InternBuddy is a **desktop app for Computing undergraduate students to manage their internship applications**. It is **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, InternBuddy can efficiently track your internship applications.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/AY2223S2-CS2103T-T14-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : List All Internship Entries.

   * `add n/CompanyXYZ r/Software engineering intern s/applied d/2023-03-03` : Adds an entry to track the internship application for Company XYZ for a software engineering role.

   * `delete 3` : Deletes the 3rd internship entry shown in the current list.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add n/Apple`.

* Items in square brackets are optional.<br>
  e.g `edit INDEX [n/NAME]` can be used as `edit 2 n/CompanyXYZ` or as `edit 2`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/Apple r/Software Engineer`, `r/Software Engineer n/Apple` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For any parameters that refer to a date (such as `add`, `edit`), they must be specified in the format YYYY-MM-DD<br>
e.g. if the command specifies `edit INDEX [d/DATE]`, then 1 March 2023 should be entered as `2023-03-01` for the parameter `DATE`.

</div>

### Adding an internship entry: `add`

Adds a new internship entry to the list of existing entries.

Format: `add n/COMPANY_NAME r/ROLE s/STATUS d/DATE`
- The `STATUS` field  must have one of the following values: `new`, `applied`,
  `assessment`, `interview`, `offered` or `rejected`.
- The meaning of `DATE` would be interpreted with respect to the value of `STATUS`.

| Status       | Interpretation of Date       |
|--------------|------------------------------|
| `new`        | Deadline of Application      |
| `applied`    | Date of Application          |
| `assessment` | Date of Technical Assessment |
| `interview`  | Date of Behavioral Interview |
| `offered`    | Date of Offer                |
| `rejected`   | Date of Rejection            |

Examples:
* `add n/Apple r/Software Engineer s/new d/2023-02-01` Adds a new internship entry with
  company name `Apple`, role `Software Engineer`, status `new` and deadline
  of application `2023-02-01`.
* `add n/Amazon r/Cloud Architect s/assessment d/2023-02-01` Adds a new internship entry
  with company name `Amazon`, role `Cloud Architect`, status `assessment` and
  date of technical assessment `2023-02-01`.
* `add n/Facebook s/new d/2023-02-01` Displays an error because the role is missing.

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

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InternBuddy data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Loading the data

InternBuddy data are loaded from the hard disk automatically at the beginning of each run. There is no need to load manually. If the data file is invalid or missing, InternBuddy will discard all data and start with an empty data file at the next run.

### Editing the data file

InternBuddy data are saved as a JSON file `[JAR file location]/data/internbuddy.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InternBuddy will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InternBuddy home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List** | `list`
**Add** | `add n/COMPANY_NAME r/ROLE s/STATUS d/DATE​` <br> e.g., `add n/Apple r/Software Engineer s/new`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [r/ROLE] [s/STATUS] [d/DATE]​`<br> e.g.,`edit 2 s/assessment r/SoftWare Developer`
**Help** | `help`
**Exit** | `exit`


