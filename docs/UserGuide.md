---
# User guide

MATHUTORING is a **centralised desktop application used for private Math tuition teachers** to manage their students' contacts and performances which subsequently allows the tutors to plan their lesson plan for future lessons and overall view of their schedule for ease of planning. While the user interactions are conducted through using a CLI, it still has the benefits of a Graphical User Interface (GUI). If you can type fast, MATHUTORING can get your contact management tasks done faster than traditional GUI apps.
## Table of Contents
*  **[Quick start](#quick-start)**
*  **[Features](#features)**
*  **[FAQ](#faq)**
*  **[Command summary](#command-summary)**

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `MATHUTORING.jar` (Stay tuned for our release!).

3. Copy the file to the folder you want to use as the _home folder_ for your MATHUTORING app.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar MATHUTORING.jar` command to run the application.<br>
   <br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data for your reference, you can also choose to delete the sample data after you get familiar with the MATHUTORING.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `listStudents` : Lists all students in the student list.

   * `add n/John p/86232384 a/Ang Mo Kio Ave 10 e/JOHN_08@gmail.com c/98763928` : Adds a student named `John` to the student list in MATHUTORING.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listStudents`, `listTasks`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student's contact: `add`

Adds a student to the student list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CONTACT_PARENT [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/948372948`
* `add n/Betsy Crowe t/friend c/83927482 e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Viewing students contacts as a list : `listStudents`

Lists all the students in the student list.

Format: `listStudents`

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

Deletes the specified student from the student list.

Format: `delete INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listStudents` followed by `delete 2` deletes the 2nd student in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st student in the results of the `find` command.

### Listing tasks of a student : listTasks

Lists the tasks of the specified student from the student list.

Format: `listTasks INDEX`

Examples: 
* `listTasks 1`

### Adding a task for a student: `addTask`

Adds a task to a specific student.

Format: `addTask INDEX t/TITLE `

Examples:

* `addTask 2 t/finish Math Paper 1`

### Deleting all tasks of a student: `deleteTasks`

Deletes all tasks of a student.

Format: `deleteTasks INDEX`

* Deletes all tasks of a student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `listStudents` followed by `deleteTasks 2` deletes all tasks of the 2nd student in the student list.
* `find Betsy` followed by `deleteTasks 1` deletes all tasks of the 1st student in the results of the `find` command.

### Adding a score for a student: `addScore`

Adds a score to a specific student.

Format: `addScore INDEX l/Label v/Value d/Date  `

Examples:

* `addScore 2 l/Midterm Math Paper v/99.5 d/2023-03-02`

### Deleting a score of a student: `deleteScore`

Deletes a score of a student.

Format: `deleteScore INDEX_OF_STUDENT INDEX_OF_SCORE `

* Deletes a score of a student at the specified `INDEX_0F_SCORE`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `listStudents` followed by `deleteScore 2 1` deletes first score of the 2nd student in the student list.
* `find Betsy` followed by `deleteScore 1 2` deletes second score of the 1st student in the results of the `find` command.

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

| Action            | Format, Examples                                                                                                                                                                                |
|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**           | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/PARENT_PHONE [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/11112222 t/friend t/colleague` |
| **Clear**         | `clear`                                                                                                                                                                                         |
| **Delete**        | `delete INDEX`<br> e.g., `delete 1`                                                                                                                                                             |
| **Edit**          | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/PARENT_PHONE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com c/12348888`                                         |
| **Find**          | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                      |
| **List Students** | `listStudents`                                                                                                                                                                                  |
| **Add Task**      | `addTask INDEX t/TITLE` e.g `addTask INDEX Homework Assignment 1`                                                                                                                               |
| **Delete Task**   | `deleteTasks INDEX` e.g `deleteTasks 2`                                                                                                                                                         |
| **List Tasks**    | `listTasks INDEX` e.g `listTasks 1`                                                                                                                                                             |
| **Help**          | `help`                                                                                                                                                                                          |

