---
layout: page
title: User Guide
---

Dengue Hotspot tracker **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). It is based on the Address Book Level 3 (AB3). 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `dht.jar` from [here](https://github.com/AY2223S2-CS2103-W17-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Dengue Hotspot Tracker.

1. To use the Tracker, you may:
   - Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dht.jar` command to run the application.<br>
      A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
      ![Ui](images/Ui.png)
   - Click on `dht.jar`.

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all cases.

   * `add n/John Tan p/543299 d/2023-02-13 19:52 a/20` : Adds a contact named `John Tan` to the Dengue Hotspot Tracker.

   * `delete 3` : Deletes the 3rd case shown in the current list.

   * `clear` : Deletes all cases.

   * `exit` : Exits the app.

   * `find 654321 Tan` : Finds a case by either postal code, or name (case insensitive).

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/Police` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/CIVILLIAN`, `t/Government t/NEA` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/POSTAL_CODE`, `p/POSTAL_CODE n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/123412 p/567856`, only `p/567856` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a dengue patient to the dengue hotspot tracker.

Format: `add n/PATIENT_NAME p/POSTAL_CODE d/DATETIME a/AGE`

Examples:
* `add n/John Tan p/543299 d/2023-02-13 19:52 a/20`
* `add n/Desiree Lim p/519999 d/2023-02-13 13:12 a/18`

### Listing all persons : `list`

Shows a list of all persons in the dengue case list.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the dengue case list.

Format: `edit INDEX [n/NAME] [p/POSTAL_CODE] [d/DATETIME] [a/AGE] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/912345 a/22` Edits the phone number and age of the 1st person to be `912345` and `22` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Names and Postal Codes are searched.
* Partial words will be matched e.g. `Han` will match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Han Bo 123456` will return `Hans Gruber`, `Boe Yang`, and cases with postal codes starting with `123456`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from the dengue case list.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the dengue case list.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the dengue case list.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

DHT data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

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
**Add** | `add n/NAME p/POSTAL_CODE d/DATETIME a/AGE [t/TAG]…​` <br> e.g., `add n/James Ho p/222244 e/2023-11-11 00:00 a/12`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/POSTAL_CODE] [d/DATETIME] [a/AGE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee a/99`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Ber 16`
**List** | `list`
**Help** | `help`
