---
layout: page
title: User Guide
---

InternBuddy is a **desktop app for Computing undergraduate students to manage their internship applications**.
It is **optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User
Interface (GUI). If you can type fast, InternBuddy can efficiently track your internship applications.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `internbuddy.jar` from [here](https://github.com/AY2223S2-CS2103T-T14-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for InternBuddy.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar internbuddy.jar`
   command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>
   Some example commands you can try:

   * `list` : List All Internship Entries.

   * `add n/CompanyXYZ r/Software Engineer s/applied d/2023-03-03` : Adds an entry to track the internship
      application for Company XYZ for the role of a Software Engineer.

   * `delete 3` : Deletes the 3rd internship entry shown in the current list.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add n/Apple`.

* Items in square brackets are optional.<br>
  e.g. `edit INDEX [n/NAME]` can be used as `edit 2 n/CompanyXYZ` or as `edit 2`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/Apple r/Software Engineer`, `r/Software Engineer n/Apple` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `r/Front-end Developer r/Back-end Developer`, only `r/Back-end Developer` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `exit`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For any parameters that refer to a date (such as in `add`, `edit`), they must be specified in the format YYYY-MM-DD<br>
  e.g. if the command specifies `edit INDEX [d/DATE]`, then 1 March 2023 should be entered as `2023-03-01` for the
  parameter `DATE`.
</div>

### Listing all internship entries : `list`

Shows a list of all internship entries that have been added into InternBuddy.

Format: `list`

* The meaning of the date displayed for each internship entry will depend on the status of the internship. For example, if
the status of the internship is `new`, the `date` field refers to the deadline of application. A full reference table is
shown below.

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


### Deleting an internship entry : `delete`
Deletes the specified internship entry from InternBuddy.

Format: `delete INDEX`

* Deletes the internship entry at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship entries list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list` followed by `delete 2` deletes the 2nd internship entry in InternBuddy.

### Editing an internship entry : `edit`

Edits an internship entry from the list of existing entries.

Format: `edit INDEX [n/COMPANY_NAME] [r/ROLE] [s/STATUS ] [d/DATE]`

* The internship entry whose entry number is `INDEX` would be updated. `INDEX` needs to be a valid entry number as specified in the internship list displayed using the`list` command. 
* At least one of the optional fields must be provided.
* `STATUS` must have one of the following values: `new`, `applied`, `assessment`, `interview`, `offered` or `rejected`.

Examples:
*  `edit 2 s/assessment r/Software Developer` Sets the status and role of the second internship entry as `assessment` and `Software Developer` respectively.
*  `edit 2` Displays an error because the command does not satisfy the criteria of having at least one optional field.

### Clearing all internship entries
Clears all internship entries from InternBuddy.

Format: `clear`<br>

![ClearEntriesWarningMessage](images/Clear-entries-warning-message.png)

### Getting help : `help`

Displays the list of commands supported by InternBuddy.

Format: `help`


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InternBuddy data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Loading the data

InternBuddy data is loaded from the hard disk automatically at the beginning of each run. There is no need to load manually.
If the data file is missing, InternBuddy will start with a data file containing the sample internship entries.
If the data file is invalid, InternBuddy will start with an empty data file.

### Editing the data file

InternBuddy data are saved as a JSON file `[JAR file location]/data/internbuddy.json`. Advanced users are welcome to
update the data directly by editing that data file.<br>

![EditDataWarningMessage](images/Edit_data_warning_message.png)


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install InternBuddy in the other computer and overwrite the empty data file it creates with the file that
contains the data of your previous InternBuddy home folder.

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
