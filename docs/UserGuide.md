---
layout: page
title: User Guide
---

SportSync is a **desktop app for managing training sessions and athletes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SportSync can get your training management tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `sportsync.jar` from [here](https://github.com/se-edu/SportSync/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SportSync.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar sportsync.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 a/311, Clementi Ave 2, r/35 s/10-03-2022 10:00 to 10-03-2022 11:00 t/friends t/owesMoney` : Adds athlete `John Doe` to SportSync.

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

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to SportSync.

Format: `add n/NAME p/PHONE_NUMBER a/ADDRESS r/PAY_RATE s/SESSION [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 r/44 a/John street, block 123, #01-01, s/22-12-2022 10:00 to 22-12-2022 11:00`
* `add n/Betsy Crowe t/friend a/Newgate Prison p/1234567 t/criminal r/5, s/11-05-2023 11:00 to 11-05-2023 13:00`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [r/PAY_RATE] [a/ADDRESS] [s/SESSION] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 r/3` Edits the phone number and payRate of the 1st person to be `91234567` and `3` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Full and partial words will be matched e.g. `Han` and `Hans` will both match `Hans`
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

### Sorting the contact list : `sort`

Sorts all entries in the address book.

Format: `sort`

### Creating a group : `group`

Creates a group to add persons to.

Format: `group t/GROUPNAME`

* Creates a group of name `GROUPNAME`
* Name field **must be provided.**

Examples:
* `group n/Team Dynamite` creates a group of name `Team Dynamite`.

### Adding persons to a group : `groupadd`

Adds a person to a group.

Format: `groupadd INDEX t/GROUPNAME`

* Adds a person at the specified `INDEX` to the group with specified `GROUPNAME`.
* Both index and group **must already exist and be provided.**
* A person cannot be added to a group they are already in.

Examples:
* `groupadd 2 n/Team Dynamite` adds the 2nd person in the address book to the group named `Team Dynamite`.

### Showing persons from a tag : `show`

Shows all persons belonging to at least one of the groups specified.

Format: `show [TAG1]…​`

* Filters list of contacts to only contain persons belonging to the specific tag(s).
* At least one tag **must be provided.**

Examples:
* `show n/neighbours` shows people belonging to tag `neighbours`.

### Listing all groups in SportSync : `display`

Lists all groups created by the user.

Format: `display`

* Displays all existing user-created groups in the command message.


### Saving the data

SportSync data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SportSync data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SportSync will discard all data and start with an empty data file at the next run.
</div>

### Group by `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SportSync home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action        | Format, Examples                                                                                                                                                                                        |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**       | `add n/NAME p/PHONE_NUMBER r/PAY_RATE a/ADDRESS s/SESSION [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 r/7 a/123, Clementi Rd, 1234665 s/10-03-2022 10:00 to 10-03-2022 11:00 t/friend t/colleague` |
| **Clear**     | `clear`                                                                                                                                                                                                 |
| **Delete**    | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                     |
| **Edit**      | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [r/PAY_RATE] [a/ADDRESS] [s/SESSION] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee r/3`                                                                                 |
| **Find**      | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                              |
| **List**      | `list`                                                                                                                                                                                                  |
| **Help**      | `help`                                                                                                                                                                                                  |
| **Sort**      | `sort`                                                                                                                                                                                                  |
| **Group**     | `group t/GROUPNAME`<br> e.g., `group t/Team Dynamite`                                                                                                                                                   |
| **Group Add** | `groupadd INDEX t/GROUPNAME`<br> e.g., `groupadd 3 t/Varsity`                                                                                                                                           |
| **Show**      | `show [TAG1]…​`<br> e.g., `show Hall…​`                                                                                                                                                                 |
| **Display**   | `display`                                                                                                                                                                                               |
