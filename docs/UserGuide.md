---
layout: page
title: User Guide
---

DengueHotspotTracker (DHT) is a **desktop app for managing Dengue Cases, optimized for**
**use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User
Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dht.jar` from [here](https://github.com/AY2223S2-CS2103-W17-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your DengueHotspotTracker.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dht.jar` command to run
the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all cases.

   * `add n/John Tan p/543299 d/2023-02-13 a/20` : Adds a case named `John Tan` to the Dengue Hotspot Tracker.

   * `delete 3` : Deletes the 3rd case shown in the current list.

   * `clear` : Deletes all cases.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [v/VARIANT]` can be used as `n/John Doe v/DENV1` or as `n/John Doe`.

* Items in curly brackets are features that are currently work in progress. <br>
  e.g `{t/TAG}` means that tagging someone has not yet been implemented, but is planned to be a feature.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[v/VARIANT]…​` can be used as ` ` (i.e. 0 times), `v/DENV1`, `v/DENV1 v/DENV2` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/POSTAL_CODE`, `p/POSTAL_CODE n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/123414 p/567878`, only `p/567878` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For postal codes, the user may choose to enter a sequence of 6 digits, or the letter `"S"` or `"s"` followed by the sequence of 6 digits.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a case: `add`

Adds a dengue patient to the dengue hotspot tracker.

Format: `add n/PATIENT_NAME p/POSTAL_CODE d/DATE a/AGE [v/DENGUE_VARIANT]…​`

Examples:
* `add n/John Tan p/543299 d/2023-02-13 a/20 v/DENV1`
* `add n/Desiree Lim p/519999 d/2023-02-13 a/18`

### Listing all cases : `list`

Shows a list of all cases in the Dengue Hotspot Tracker.

Format: `list`

### Editing a case : `edit`

Edits an existing case in the Dengue Hotspot Tracker.

Format: `edit INDEX [n/NAME] [p/POSTAL] [d/DATE] [a/AGE] [v/DENGUE_VARIANT]…​`

* Edits the case at the specified `INDEX`. The index refers to the index number shown in the displayed case list.
* The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing dengue variants, all existing variants of the case will be removed i.e adding of variants is not cumulative.
* You can remove all the case’s dengue variants by typing `v/` without
    specifying any variants after it.

Examples:
* `edit 1 p/912345 d/2001-01-01` Edits the postal code and date of the 1st case to be `S912345` and `2001-01-01`
respectively.
* `edit 2 n/Betsy Crower v/` Edits the name of the 2nd case to be `Betsy Crower` and clears all tagged dengue variants.

### Locating cases by name: `find`

Finds cases whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]` or {`find PARTIAL_POSTAL_CODE filter DENGUE_VARIANT`}

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* The name and postal codes are searched.
  * For names, partial words will be matched e.g. `Han` will match `Hans` and `Abrahan`
  * For postal codes, the beginning of the postal code will be matched e.g. `10` will match `S101234` but not `S123410`
  * For postal codes, the user may choose to include an `"S"` or `"s""` character or not e.g.
    * `S10` will match `S101234`
    * `s10` will also match `S101234`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Han Bo 101` will return `Abrahans Gruber`, `Boeing Yang` and all cases whose postal codes begin with `101`.

Examples:
* `find Boe` returns `Boeing` and `Wong Boe`
* `find alex david 101` returns `Alexander Peterson`, `Allison Tan` (postal code), `Davidson Li`<br>
  ![result for 'find alex david 101'](..%2F..%2F..%2FDownloads%2Fimage%20%281%29.png)

### Deleting a case : `delete`

Deletes the specified case from the Dengue Hotspot Tracker.

Format: `delete INDEX`

* Deletes the case at the specified `INDEX`.
* The index refers to the index number shown in the displayed case list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd case in the Dengue Hotspot Tracker.
* `find Betsy` followed by `delete 1` deletes the 1st case in the results of the `find` command.
* `find s666` followed by `delete 4` deletes the 4th case in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the Dengue Hotspot Tracker.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

DengueHotspotTracker data are saved in the hard disk automatically after any command that changes
the data. There is no need to save manually.

### Editing the data file

DengueHotspotTracker data are saved as a JSON file `[JAR file location]/data/denguehotspottracker.json`. Advanced users
are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, DengueHotspotTracker will discard all data and start with an
empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous DengueHotspotTracker home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format, Examples                                                                                                               |
|------------|--------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/NAME p/POSTAL_CODE d/DATE a/AGE [v/DENGUE_VARIANT]…​` <br> e.g., `add n/James Ho p/S222244 d/2000-11-11 a/123, v/DENV1` |
| **Clear**  | `clear`                                                                                                                        |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                            |
| **Edit**   | `edit INDEX [n/NAME] [p/POSTAL_CODE] [d/DATE] [a/AGE] [v/DENGUE_VARIANT]…​`<br> e.g.,`edit 2 n/James Lee d/2001-11-11`         |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                     |
| **List**   | `list`                                                                                                                         |
| **Help**   | `help`                                                                                                                         |
