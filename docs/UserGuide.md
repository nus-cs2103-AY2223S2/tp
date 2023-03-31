---
layout: page
title: User Guide
---

# User Guide

## Table of Contents
*  **[Overview](#overview)**
*  **[Quick start](#quick-start)**
*  **[Features](#features)**
*  **[FAQ](#faq)**
*  **[Command summary](#command-summary)**

--------------------------------------------------------------------------------------------------------------------
## Overview

Finding it hard to manage your student contact details and keep track of their progress?

Finding it a hassle to keep a list of student tasks and scores?

**MATHUTORING** helps you solve these problems!

**MATHUTORING**, a centralised desktop application targeted to **private Math tuition teachers**, helps you manage student contact details and performance by keeping student contact details, tasks, and scores.
You can also see score charts and statistics!

What's more, you can generate a PDF report of your student containing the student's tasks and scores.
If you need to switch to a new device, you can also export and import your previous data in the application!

**MATHUTORING** is optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, **MATHUTORING** can get your contact management tasks done faster than traditional GUI apps.

## Quick start

1. Ensure you have `Java 11` or above installed in your Computer.

2. Download the latest `mathutoring.jar` (Stay tuned for our release!).

3. Copy the file to the folder you want to use as the _home folder_ for your MATHUTORING app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar mathutoring.jar` command to run the application.<br>
   <br>
   The inital GUI below should appear in a few seconds. Note how the app contains some sample data for your reference, you are free to delete the sample data after you get familiar with the MATHUTORING.<br>
   
   <img width="1440" alt="Screenshot 2023-03-30 at 12 51 28 PM" src="https://user-images.githubusercontent.com/97392685/228732862-ca3893da-f637-468c-9a9b-5f4e4a38a9cf.png">
   
5. Type the command in the command box and press `Enter` to execute it. e.g. typing **`help`** and pressing `Enter` will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all students in the student list.

   * `add n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 c/87849999 t/female t/primary : Adds a student named `John Doe` to the student list in MATHUTORING with two tags attached.

   * `delete 3` : Deletes the 3rd student (if the student exist) shown in the current list.

   * `clear` : Deletes all students.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

### **:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/primary` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/secondary`, `t/secondary4 t/secondary` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit`, `clear` and `switch`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


### 1. Viewing help : `help`

Prompts the help page link.

<img width="796" alt="Screenshot 2023-03-30 at 3 37 34 PM" src="https://user-images.githubusercontent.com/97392685/228764298-dccce25c-662f-41c6-9c91-6db2a8b44df7.png">

Format: `help`

### 2. Adding a student's contact: `add`

Adds a student to the student list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CONTACT_PARENT [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: Tip:
   A student can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/948372948`
* `add n/Betsy Crowe t/primary c/83927482 e/betsycrowe@example.com a/Downtown p/1234567 t/primary3`


### 3. Viewing students contacts as a list : `list`

Lists all the students in the student list.

Format: `list`


### 4. Editing a student : `edit`

Edits an existing student in the student list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/CONTACT_PARENT] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.


### 5. Locating students by name: `find`

Finds students whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)


### 6. Deleting a student : `delete`

Deletes the specified student from the student list.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd student in the student list.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.


### 7. Checking a student : `check`

Displays the information of the student being checked, including their task list and score list.

Format: `check INDEX`

* Checks the student at the specified `INDEX` and displays the task list and score list of the student.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `check 2` checks the 2nd student in the student list.


### 8. Adding a task for a student: `addtask`

Adds a task to a specific student.

Format: `addtask INDEX t/TASK_NAME`

* Adds the given task to the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​
* A task with the same name as a task already in the task list cannot be added into the task list.
* The name of a task is case-insensitive. e.g. `do homework` is the same as `Do Homework`

Examples:

* `list` followed by `addtask 2 t/finish Math Paper 1` adds the task `finish Math Paper 1` to the 2nd student of the
  student list.
* `check 2` followed by `addtask 1 t/Complete A Math Exercise` adds the task `Complete A Math Exercise` to the student
  being checked.


### 9. Deleting a task of a student: `deletetask`

Deletes the specified task from the task list of a student.

Format: `deletetask INDEX_OF_STUDENT INDEX_OF_TASK`

* Deletes the task at the specified `INDEX_OF_TASK` of a specified student (`INDEX_OF_STUDENT`).
* The first index refers to the index number shown in the displayed student list and
  the second index refers to the index of the task in the displayed task list of the student.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:

* `list` followed by `deletetask 2 3` deletes the third task of the 2nd student in the student list.
* `find Betsy` followed by `deletetask 1 6` deletes the sixth task of the 1st student in the results of the 
`find` command.


### 10. Marking a task of a student: `markcomplete`, `markinprogress`, `marklate`

Marks the specified task from the task list of a student as complete, in progress or late.

Format:
<br> Mark task as complete: `markcomplete INDEX_OF_STUDENT INDEX_OF_TASK`
<br> Mark task as in progress: `markinprogress INDEX_OF_STUDENT INDEX_OF_TASK`
<br> Mark task as late: `marklate INDEX_OF_STUDENT INDEX_OF_TASK`

* Marks the task at the specified `INDEX_OF_TASK` of a specified student (`INDEX_OF_STUDENT`) as complete, in progress or late, depending on the command called.
* The first index refers to the index number shown in the displayed student list and
  the second index refers to the index of the task in the displayed task list of the student.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:

* `list` followed by `markcomplete 2 3` marks the third task of the 2nd student in the student list as complete.
* `find Betsy` followed by `marklate 1 6` marks the sixth task of the 1st student in the results of the
  `find` command as late.


### 11. Adding a score for a student: `addscore`

Adds a score to a specific student.

Format: `addscore INDEX l/LABEL v/VALUE_OF_SCORE d/DATE`

* Adds the given score to the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​
* The given `VALUE_OF_SCORE` can be **any number from 0 to 100**.
* The given `VALUE_OF_SCORE` can either be **an integer or a number with one decimal place**

<div markdown="block" class="alert alert-info">

**:information_source: Note about format of `DATE`:**<br>
* The format of `DATE` must be `yyyy-MM-DD`. For example, `2022-02-20` represents 20 February 2022.

</div>

Examples:

* `list` followed by `addScore 2 l/Midterm Math Paper v/99.5 d/2023-03-02` adds a `Midterm Math Paper` score with a 
  value of `99.5` and dated `2022-03-02` to the 2nd student in the student list.
* `check 5` followed by `addScore 1 l/CA2 A Math v/50 d/2021-09-09` adds a `CA2 A Math` score with a
  value of `50` and dated `2021-09-09` to the student being checked.


### 12. Deleting a score of a student: `deletescore`

Deletes the specified score from the score list of a student.

Format: `deletescore INDEX_OF_STUDENT INDEX_OF_SCORE `

* Deletes the score at the specified `INDEX_OF_SCORE` of a specified student (`INDEX_OF_STUDENT`).
* The first index refers to the index number shown in the displayed student list and
  the second index refers to the index of the task in the displayed score list of the student.
* Both indexes **must be positive integers** 1, 2, 3, …​

Examples:

* `list` followed by `deletescore 2 1` deletes first score of the 2nd student in the student list.
* `find Betsy` followed by `deletescore 1 2` deletes second score of the 1st student in the results of the `find` command.


### 13. Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`


### 14. Exiting the program : `exit`

Exits the program.

Format: `exit`


### 15. Filtering students by tag: `filter`

Filters students whose tag contain any of the given keywords.

Format: `filter KEYWORD [MORE_KEYWORDS]`

* The filter is case-insensitive. e.g `primary` will match `Primary`
* The order of the keywords does not matter. e.g. `primary primary4` will match `primary4 primary`
* Only the tag is searched.
* Only full words will be matched e.g. `primary` will not match `primary4`
* Students whose tag matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `primary primary4` will return `primary`, `primary4`

Examples:
* `filter primary` returns `primary`, `Primary`
* `filter primary primary4` returns `primary`, `primary4`


### 16. Switch between tabs :`switch`

Switches between the score list and score chart tabs.

Format: There we support using CLI or mouse.
CLI - `switch`
Mouse - click the tab.


### 17. Export the student data out:`export`

Exports all the student's data out. User can export with or without specifing the path.
The default exported position for CLI will be under the _home folder_.
The exported file name is `data.json`.

Format: There we support using CLI or mouse.
CLI - `export [FILE_PATH]`
Mouse - Click the "File" on the top menu, then choose "Export" under the drop-down list. An export window will pop up, the user is required to specify which folder to store the exported file.

Examples:
* `export` will export the file under the _home folder_.
* For Windows user
  * `export` p/C:\bin export `data.json` to bin folder under C disk.
* For Mac user
  * `export` p//Users/username/Desktop export `data.json` to desktop.


### 18. Import the student data in: `import`

Imports student data into the application. User can import the file by dragging the file in or choose file path.
The imported file must be in `.json` format.

Format: There we support using CLI or mouse.
CLI - `import FILE_PATH`
Mouse - Click the "File" on the top menu, then choose "Import" under the drop-down list. An import window will pop up, the user can choose to either drag the file in or choose a specific file path.

Examples:
* For Windows user
  * `import` p/C:\bin\data.json.
* For Mac user
  * `import` p//Users/username/Desktop/data.json.

**:information_source: Note about format of `FILE_PATH`:**<br>
* For Windows user, the format of `FILE_PATH` must be `p/C:\bin\[FILE_NAME]`.
* For Mac user, the format of `FILE_PATH` must be `p//Users/username/Desktop/[FILE_NAME]`.


### 19. Export student progress out as PDF file: `exportp`


### Saving the data

MATHUTORING data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


### Editing the data file

MATHUTORING data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, MATHUTORING will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MATHUTORING home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action             | Format, Examples                                                                                                                                                                              |
|--------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Student**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/PARENT_PHONE [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/11112222 t/friend t/primary` |
| **Delete Student** | `delete INDEX`<br> e.g., `delete 1`                                                                                                                                                           |
| **Clear**          | `clear`                                                                                                                                                                                       |
| **Check**          | `check INDEX`<br> e.g. `check 1`                                                                                                                                                              |
| **Edit**           | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/PARENT_PHONE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com c/12348888`                                       |
| **Find**           | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                    |
| **Filter**         | `filter KEYWORD [MORE_KEYWORDS]`<br> e.g., `filter friends colleagues`                                                                                                                        |
| **List Students**  | `list`                                                                                                                                                                                        |
| **Add Task**       | `addTask INDEX t/TITLE`<br> e.g `addTask 2 t/Homework Assignment 1`                                                                                                                           |
| **Delete Task**    | `deleteTask INDEX_OF_STUDENT INDEX_OF_TASK`<br> e.g `deleteTask 2 1`                                                                                                                          |
| **Mark Task**      | `mark(STATUS) INDEX_OF_STUDENT INDEX_OF_TASK` (`mark(STATUS)` can be either `markComplete`, `markLate` or `markInProgress`)<br> e.g. `markComplete 1 2`<br>                                   |
| **Add Score**      | `addScore l/LABEL v/VALUE_OF_SCORE d/DATE`<br> e.g `addScore l/CA2 A Math v/70 d/2022-03-03`                                                                                                  |
| **Delete Score**   | `deleteScore INDEX_OF_STUDENT INDEX_OF_SCORE`<br> e.g. `deleteScore 3 4`                                                                                                                      |
| **Import Data**   | `import p/FILE_PATH`<br> e.g. `import p//Users/John/data.json`                                                                                                                      |
| **Export Data**   | `export [p/FILE_PATH]`<br> e.g. `export p//Users/John`                                                                                                                      |
| **Export Progress**   | `exportp INDEX [p/FILE_PATH]`<br> e.g. `export 1 p//Users/John`                                                                                                                      |
| **Help**           | `help`                                                                                                                                                                                        |


