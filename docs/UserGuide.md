---
layout: page
title: User Guide
---
SportSync is a **desktop app for managing training sessions and athletes, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SportSync can get your training management tasks done faster than traditional GUI apps.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Quick start**

### Prerequisites
Ensure that you have Java 11 or above installed on your computer. If you don't have Java installed, you can download it from the official Java website [here](https://www.oracle.com/java/technologies/downloads/).

1. Download the latest `sportsync.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-2/tp/releases).

2. Copy the file to the folder you want to use as the _home folder_ for your SportSync.

3. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar sportsync.jar` command to run the application.<br>
   e.g. `cd Desktop\New_Folder` and then `java -jar sportsync.jar`<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

4. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all athletes.

    * `add n/John Doe p/98765432 a/311, Clementi Ave 2, r/35 t/friends t/owesMoney` : Adds athlete `John Doe` to SportSync.

    * `delete 3` : Deletes the 3rd athlete shown in the current list.

    * `clear` : Deletes all athletes.

    * `exit` : Exits the app.

Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## **Features**

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* Parameters are used to specify information that is required for the command to be executed correctly.

* Words in `UPPER_CASE` are the parameters to be supplied by the coach. <br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe…​`.

* Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but is specified multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The help menu can also be accessed by pressing the F1 key.
</div>

### Undoing a previous command : `undo`

Undoes a previously entered command.

Format: `undo`

* Returns the state of the athlete list to the state before the last entered command.
* Cannot be used if no commands have been entered yet.

### Redoing a previous command : `redo`

Redoes a previously entered command.

Format: `redo`

* Returns the state of the athlete list to the state before undoing the last entered command.
* Cannot be used if no commands have been entered yet.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If the athlete list is changed after an undo command, a redo cannot be done.
</div>

### Adding an athlete: `add`

Adds an athlete to SportSync.

Format: `add n/NAME p/PHONE_NUMBER a/ADDRESS r/PAY_RATE [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An athlete can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 r/44 a/UTown Residences, #01-01`
* `add n/Betsy Crowe t/friend a/Sheares Hall p/1234567 t/basketball r/5`

### Listing all athletes : `list`

Shows a list of all athletes.

Format: `list`

### Editing an athlete : `edit`

Edits an existing athlete in the athlete list.

Format: `edit INDEX [n/NAME] [p/PHONE] [r/PAY_RATE] [a/ADDRESS] [t/TAG]…​`

* Edits the athlete at the specified `INDEX`. The index refers to the index number shown in the displayed athlete list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the athlete will be removed. <br>(i.e. adding of tags is not cumulative)
* You can remove all the athlete’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 r/3` Edits the phone number and pay rate of the 1st athlete to be `91234567` and `3` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd athlete to be `Betsy Crower` and clears all existing tags.

### Locating athletes by name: `find`

Finds athletes whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* Full and partial words will be matched e.g. `Han` and `Hans` will both match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Athletes matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find carl alice` returns `Alice Pauline`, `Carl Kurz`<br><br>
  ![result for 'find alex david'](images/findCarlAliceResult.png)

### Deleting an athlete : `delete`

Deletes the specified athlete from the athlete list.

Format: `delete INDEX`

* Deletes the athlete at the specified `INDEX`.
* The index refers to the index number shown in the displayed athlete list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd athlete in the athlete list.
* `find Betsy` followed by `delete 1` deletes the 1st athlete in the results of the `find` command.

### Clearing all athletes : `clear`

Clears all athletes from the athlete list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Sorting the athlete list : `sort`

Sorts all athletes in the athlete list according to provided attribute.

Format: `sort ATTRIBUTE ORDER`

* Sorts the athlete according to specified attribute `ATTRIBUTE` and in specified `ORDER`.
* Attributes:
  * 1 - Name
  * 2 - Pay rate
* Order:
  * 1 - Ascending
  * 2 - Descending

Examples:
* `sort 1 1` sorts the athlete list by name in alphabetical order.
* `sort 2 1` sorts the athlete list according to pay rate, from cheapest to most expensive.

### Creating/Deleting a group : `group`

Creates a group to add athletes to. / Deletes an existing group.

Format: `group m/MODIFICATION g/GROUPNAME`

* Creates a group of name `GROUPNAME` when used with modification `add`.
* Deletes the group of name `GROUPNAME` when used with modification `delete`.
* Modification field can only be either `add` or `delete`.
* Name field g/ and modification field m/ **must be provided.**
* Only groups that already exist can be deleted.

Examples:
* `group m/add g/Team Dynamite` adds a group of name `Team Dynamite`.
* `group m/delete g/Tennis` deletes the existing group named `Tennis`.

### Add athletes to a group / Delete athletes from a group: `groupmod`

Adds an athlete to a group /Deletes an athlete from a group.

Format: `groupmod INDEX m/MODIFICATION g/GROUPNAME`

* Adds an athlete at the specified `INDEX` to the group with specified `GROUPNAME` when used with modification `add`.
* Modification field can only be either `add` or `delete`.
* Both index and group **must already exist and be provided.**
* Modification field m/ **must be provided.**
* An athlete cannot be added to a group they are already in.
* An athlete cannot be removed from a group they do not belong to.

Examples:
* `groupmod 2 m/add g/Team Dynamite` adds the 2nd athlete in the athlete list to the group named `Team Dynamite`.
* `groupmod 5 m/delete g/Bowling` deletes the 5th athlete in the athlete list from the group named `Bowling`.

### Showing athletes from a group : `show`

Shows all athletes belonging to at least one of the groups specified.

Format: `show [GROUP1]…​`

* Filters list of athletes to only contain athletes belonging to one or more of the specific group(s).
* At least one group name **must be provided.**

Examples:
* `show varsity` shows people belonging to group `varsity`.
* `show hockey tennis` shows people belonging to either group `hockey`, `tennis` or both.

### Listing all groups in SportSync : `display`

Lists all groups created by the coach.

Format: `display`

* Displays all existing coach-created groups.
* Only the group names themselves are displayed, not the athletes belonging to those groups.

### Saving the data

SportSync data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SportSync data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced coaches are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SportSync will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## **FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SportSync home folder.

## **Glossary**

* **Pay rate**: The amount of fees paid by the athlete per training session.
* **Athlete**: A student of the Coach.
* **Attendance**: A record of the presence or absence of an athlete at a training session.
* **Coach**: A person who trains and directs athletes or a team.
* **Session**: A training period for athletes conducted by a coach.

Tag: A label attached to an athlete in SportSync, used to group athletes together for easier management.
--------------------------------------------------------------------------------------------------------------------

## **Command summary**

| Action        | Format, Examples                                                                                                                                       |
|---------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**       | `add n/NAME p/PHONE_NUMBER r/PAY_RATE a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 r/7 a/123, Clementi Rd, 1234665 t/friend t/colleague` |
| **Clear**     | `clear`                                                                                                                                                |
| **Delete**    | `delete INDEX`<br> e.g., `delete 3`                                                                                                                    |
| **Edit**      | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [r/PAY_RATE] [a/ADDRESS] [t/TAG]…​`<br> e.g., `edit 2 n/James Lee r/3`                                           |
| **Find**      | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                             |
| **List**      | `list`                                                                                                                                                 |
| **Help**      | `help`                                                                                                                                                 |
| **Sort**      | `sort ATTRIBUTE ORDER`<br> e.g., `sort 1 2`                                                                                                            |
| **Group**     | `group m/MODIFICATION g/GROUPNAME`<br> e.g., `group m/add g/Team Dynamite`                                                                             |
| **Group Mod** | `groupmod INDEX m/MODIFICATION g/GROUPNAME`<br> e.g., `groupmod 2 m/add g/Team Dynamite`                                                               |
| **Show**      | `show [TAG1]…​`<br> e.g., `show Hall…​`                                                                                                                |
| **Display**   | `display`                                                                                                                                              |
| **Undo**      | `undo`                                                                                                                                                 |
| **Redo**      | `redo`                                                                                                                                                 |
