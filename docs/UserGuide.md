---
layout: page
title: User Guide
---

E-Lister is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, E-Lister can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `eLister.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for E-Lister.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar eLister.jar` command to run the application.<br>
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

Shows a help message detailing the various commands available in E-Lister. 
A link can also bring the user to the online user guide for more detailed help.

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
* Partial words will also be matched. e.g. `Han` will match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Grub Ya` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)
* `find mart On` returns `Martin Henz`, `Ong Wai Kit`

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Adding a tag : `tag`

Adds a tag to any given person stored in the address book.

Format: `tag INDEX [t/TAG]`

* Adds `TAG` to the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `add_tag 2 banker` adds the tag “banker” to the 2nd person.

### Deleting a tag : `delete_tag`

Deletes a tag on a person.

Format: `delete_tag INDEX [t/TAG]`

* Deletes `TAG` on the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​
* The tag must be existed.

Examples:
* `list` followed by `delete_tag 3 teacher` deletes the tag “teacher” from the 2nd person.

### Filter by fields : `filter`

Search for persons whose fields all match one or more regexes.

Format: `filter [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INCOME] [t/TAG] [n/MORE_NAMES] ...`

* Displays the data of persons who's every field matches at least one respective regex,
if such a regex exists for that field.

Examples:
* `filter t/^banker$` will list all persons with exactly the tag “banker”.
* `filter e/.*\.org$ n/rin e/.*\.net$` will list all persons with the substring "rin" in their name,
as well as an email that ends in ".org" or ".net".

### Freezing the display : `freeze`

Freezes the current _selection_ of persons displayed. The details of these persons will still be updated,
if modified.

Format: `freeze`

* Commands that reference indices / the list of persons being displayed will be subject to `freeze`,
and will act on the display as it appears to you.

### Unfreezing the display : `unfreeze`

Unfreezes the current _selection_ of persons displayed. Any changes to the selection which were previously withheld
due to a `freeze` will now be applied.

Format: `unfreeze`

### Mass operations : `mass`

Takes in a command typically applied to a single target index, and applies it to all _displayed_ persons.

Format: `mass COMMAND [ARGS_WITHOUT_INDEX]`

Examples:
* `mass tag Noticed` will tag all displayed persons with "Noticed".
* `mass edit p/7773354` will edit all displayed persons to have the phone "7773354".
* `mass delete` will delete all displayed persons.
  * Contrast with `clear`, which deletes all persons, whether displayed or not.

### Undoing a command : `undo`

Undo one or more of the most recent commands done.

Format: `undo [NUM]`

* Undoes `NUM` of the most recent commands, or the 1 most recent if `NUM` is not specified.
* Only undoes commands which affect data or the display. E.g.:
  * `edit`, `filter`, `freeze`, and `import` can be undone
  * `help` and `export` cannot be undone; `undo` will skip them for the next most recent command.

Examples:
* `undo 3` will undo the last 3 commands.

### Redoing a command : `redo`

Redo one or more of the most recent commands undone.

Format: `redo [NUM]`

* Redoes `NUM` of the most recent undone commands, or the 1 most recent if `NUM` is not specified.

Examples:
* `redo 3` will redo 3 commands.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

E-Lister data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

E-Lister data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, E-Lister will discard all data and start with an empty data file at the next run.
</div>

### Import data from CSV : `import`

Opens a file chooser to select a CSV file containing relevant data and merges with the existing data.

Format: `import`

### Export data to CSV : `export`

Opens a file chooser to select a directory where you can save the data to a CSV file.

If you wish to export to a new file which does not exist, you may type its intended name in the **File Name** box and click **Save**. This will create the new CSV file with the exported data.

Format: `export`

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous E-Lister home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Export** | `export`
**Filter** | `filter [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [i/INCOME] [t/TAG] [n/MORE_NAMES] ...`<br> e.g., `filter e/.*\.org$ n/rin e/.*\.net$`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Freeze** | `freeze`
**Unfreeze** | `unfreeze`
**List** | `list`
**Mass** | `mass COMMAND [ARGS_WITHOUT_INDEX]`<br> e.g., `mass tag Noticed`
**Tag** | `tag INDEX t/TAG`
**Delete Tag** | `delete_tag INDEX t/TAG`
**Undo** | `undo [NUM]`
**Redo** | `redo [NUM]`
**Help** | `help`
