---
layout: page
title: User Guide
---

Where Got Time (WGT) **a perfect desktop app** dedicate to managing your events and plan out your meetings with your friends and family. It is developed for university student, who can type fast to efficiently keep track of all of their events and their friends' events **via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, WGT can help you find a date that all your friends are free to meet instead of having to manually compare timetables with each other.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `wheregottime.jar` from [here](https://github.com/AY2223S2-CS2103T-T09-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your WhereGotTime.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar wheregottime.jar` command to run the application.<br>
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

### Creating a group : `group create`

Create a group in the address book.

Format: `group create g/GROUP_NAME`

* Creates a group with the specified group name `GROUP_NAME`.
* The group name cannot be empty

Examples:
* `group create g/CS2103T`
* `group create g/CS2101`

### Deleting a group : `group delete`

Deletes an existing group from the address book.

Format: `group delete g/GROUP_NAME`

* Deletes a group with the specified group name `GROUP_NAME`.
* The group name cannot be empty and must be an existing group

Examples:
* `group delete g/CS2103T`
* `group delete g/CS2101`


### Adding a person to a group : `group add`

Adds a person to an existing group from the address book.

Format: `group add n/NAME g/GROUP_NAME`

* Adds a person with given name `NAME` into a group with the specified group name `GROUP_NAME`.
* The group name cannot be empty and must be an existing group
* The person name cannot be empty and must be an existing contact

Examples:
* `group add n/Lyndon Lim g/CS2103T`
* `group add n/Lyndon Lim g/CS2101`

### Removing a person from a group : `group remove`

Removes a person from an existing group from the address book.

Format: `group remove n/NAME g/GROUP_NAME`

* Removes a person with given name `NAME` from a group with the specified group name `GROUP_PNAME`.
* The group name cannot be empty and must be an existing group
* The person name cannot be empty and must be an existing contact

Examples:
* `group remove n/Lyndon Lim g/CS2103T`
* `group remove n/Lyndon Lim g/CS2101`


### List all groups: `group list`

Shows a list of all existing groups' name in the address book.

Format: `group list`

### Find a group: `group find`

Finds group by name and list everyone in that group

Format: `group find g/GROUP_NAME`

* The group name cannot be empty and must be an existing group

Examples:
* `group find g/CS2103T`
* `group find g/CS2101`

### Creating an event: `event_create`
Creates a weekly recurring event or a non-recurring event

#### 1) Non-recurring Event
Format: `event_create INDEX ie/EVENT_NAME f/START_DATE t/END_DATE`

* Creates an event with the specified name `EVENT_NAME`
* The format of both `START_DATE` and `END_DATE` would be in `dd/MM/yyyy HH:mm`
* `EVENT_NAME`, `START_DATE` and `END_DATE` cannot be left empty

#### 2) Weekly Recurring Event
Format: `event_create recurring e/EVENT_NAME d/DAY_OF_WEEK f/START_TIME t/END_TIME`

* Creates a recurring event with the specified name `EVENT_NAME`
* The format `DAY_OF_WEEK` accepts the input `Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday`
* The format of `START_TIME` and `END_TIME` would be in `HH:mm`
* `EVENT_NAME`, `DAY_OF_WEEK`, `START_DATE` and `END_DATE` cannot be left empty

Examples:
* 'event create e/CS2101 Presentation f/28/02/2023 16:00 t/28/02/2023 18:00'
* 'event create recurring e/CS2103T Weekly Meeting d/Monday f/12:00 t/14:00'

### Deleting an event:
### 1) Delete an isolated event: `ie_delete`

Deletes an existing isolated event from person's isolated event list in the address book.

Format: `ie_delete [INDEX OF PERSON] [INDEX OF EVENT]}`

* Deletes an event with the specified event name `EVENT_NAME`
* The event name cannot be empty and must be an existing event

Examples:
* ie_delete 1 1

### Editing an event: 
### 1) Edit an isolated event: `ie_edit`

Edit an existing isolated event from person's isolated event list in the address book.

Format: `ie_delete [INDEX OF PERSON] [INDEX OF EVENT] ie/NAME f/START_DATE t/END_DATE`

* Deletes an event with the specified event name `EVENT_NAME`
* The event name cannot be empty and must be an existing event

Examples:
* ie_edit 1 1 ie/Biking
* ie_edit 1 1 f/09/03/2023 15:00

### List all events: `event list`  [coming soon]


### Find an event: `event find`

Find a specific event and list the details of the event.

Format: `event find e/EVENT_NAME`
* Finds an event with the specified event name `EVENT_NAME`
* The event name cannot be empty and must be an existing event.

Examples:
* event find e/CS2103T Weekly Meeting
* event find e/CS2101 Presentation

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

WhereGotTime data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

WhereGotTime data are saved as a JSON file `[JAR file location]/data/wheregottime.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, the app will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WhereGotTime home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                    | Format, Examples                                                                                                                                                      |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**                   | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**                 | `clear`                                                                                                                                                               |
| **Delete**                | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**                  | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                           |
| **Find**                  | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**                  | `list`                                                                                                                                                                |
| **Help**                  | `help`                                                                                                                                                                |
| **Group create**          | `group create g/GROUP_NAME`                                                                                                                                           |
| **Group delete**          | `group delete g/GROUP_NAME`                                                                                                                                           |
| **Group add**             | `group add n/NAME g/GROUP_NAME`                                                                                                                                       |
| **Group remove**          | `group remove n/NAME g/GROUP_NAME`                                                                                                                                    |
| **Group list**            | `group list`                                                                                                                                                          |
| **Group find**            | `group find g/GROUP_NAME`                                                                                                                                             |
| **Event create**          | `event_create e/EVENT_NAME f/START_DATE t/END DATE`<br> e.g.,`event create recurring e/EVENT_NAME d/DAY_OF_WEEK f/START_TIME t/END_TIME`                              |
| **Isolated Event create** | `event_create ie/EVENT_NAME f/START_DATE t/END_DATE`                                                                                                                  |
| **Isolated event delete** | `ie_delete [PERSON_INDEX] [EVENT_INDEX]`                                                                                                                              |
| **Isolated Event update** | `ie_edit [PERSON_INDEX] [EVENT_INDEX] [ie/NAME] [f/START_DATE] [t/END_DATE]`                                                                                          |
| **Event list**            | [coming soon]                                                                                                                                                         |
| **Event find**            | `event find e/EVENT_NAME`                                                                                                                                             |
