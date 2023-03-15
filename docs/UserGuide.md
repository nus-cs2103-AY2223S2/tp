---
layout: page
title: User Guide
---

Reroll is a **desktop app for managing tabletop RPG character, monster and item sheets, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Reroll can get your entity management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `reroll.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Reroll.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar reroll.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `view mob` : Lists all monster sheets in the database.

   * `make char John Cena` : Adds a character sheet named `John Cena` to the database.

   * `kill char John Cena` : Deletes previously created character `John Cena`.

   * `clear` : Deletes all entity sheets.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `make CLASSIFICATION NAME`, `CLASSIFICATION` and `NAME` are parameters which can be used as `add item gilded dagger`.

* CLASSIFICATION can be the following: .<br>
  Item: `item`, Monster:`mob`, Character: `char`.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an entity: `make`

Adds a new entity into the database.

Format: `make CLASSIFICATION NAME`


Examples:
* `make char John Cena `
* `make item Sword`

### Listing all entities of a classification : `view`

Shows a list of all entities in Reroll's database.

Format: `view CLASSIFICATION`

A detailed view of a single entity may be entered when specified.

Format: `view CLASSFICIATION NAME`

Detailed view may be left by entering the command: `back` or `b` while in detailed view.
### Editing a person : `edit`

Enter into edit mode for the specified entity.

Format: `edit CLASSIFICATION NAME`

* In edit mode, changes can be made with the following format: <br>
  * `FIELD NEW_VALUE`
  * e.g. `name gilded staff` or `hp 100`
  * If the specified field is unavailable (i.e. hp of an item), no values will be changed.
* Edit mode may be left by entering the command: `back` or `b` while in edit mode.

### Locating Entity by name: `find`

Finds Entity whose names contain any of the given keywords.

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

### Deleting an entity : `kill`

Deletes the specified entity from Reroll's database.

Format: `kill CLASSIFICATION NAME`

* Deletes the entity with both the specified `CLASSIFICATION` and `NAME`.

Examples:
* `kill item black sword` will delete only exactly an `item` with the name `black sword`.

### Clearing all entries : `clear`

Clears all entries from the database.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Reroll's database is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Reroll's data is saved as a JSON file `[JAR file location]/data/reroll.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Reroll will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Reroll home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                        | Format, Examples                                                  |
|-------------------------------|-------------------------------------------------------------------|
| **Make**                      | `make CLASSIFICATION NAME` <br> e.g., `make char BigMcLargeHuge`  |
| **Clear**                     | `clear`                                                           |
| **Delete**                    | `kill CLASSIFICATION NAME`<br> e.g., `delete char BigMcLargeHuge` |
| **Enter Edit Mode**           | `edit CLASSIFICATION NAME`<br> e.g.,`edit char BigMcLargeHuge`    |
| **Edit field (in Edit Mode)** | `FIELD NEW_VALUE` <br> e.g., `name SmallMcTinyMicro`              |
| **Find**                      | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Large`             |
| **List entities**             | `view CLASSIFICATION` <br> e.g., `view items`                     |
| **Help**                      | `help`                                                            |
