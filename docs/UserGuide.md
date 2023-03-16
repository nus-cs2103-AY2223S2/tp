---
layout: page
title: User Guide
---

SOCket is a **desktop app for NUS Software Engineering Students to manage the contact information of their peers and professors, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, SOCket can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `socket.jar` from [here](https://github.com/AY2223S2-CS2103T-T12-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your SOCket.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar socket.jar` command to run the application.<br>
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

* `INDEX` must be a positive integer starting from 1 and ending with corresponding number of Person in SOCket. e.g. `1,2,...`

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

Adds a person to SOCket.

Format: `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GITHUB_PROFILE] [l/LANGUAGE] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of languages/tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/johndoe l/Python`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list`

Lists all persons in SOCket or based on language and tag.

Format: `list [l/LANGUAGE] [t/TAG]`

* The search for language or tag is case sensitive. 
* If no language or tag are given, all persons are displayed. 
  * e.g. `list` will list out all persons 
* There can be one or more keywords for each field (language/ tag).
  * e.g. `list l/Python l/Java` will match out all persons whose language contains `Python` AND `Java`
  * e.g. `list l/Python t/friend` will match out all persons whose language contains `Python` AND tag contains `friend`
* Languages and tags given are specific.
  *  e.g. `list t/school` will not match `list t/school friend`
* Persons with field values matching all keyword for that respective field will be returned (i.e. `AND` search). 
  * e.g. `list t/friend l/C++` will return Persons containing tag `friend` AND language `C++`
* Each person is accompanied by an index number in the list
* The list by default is sorted by time added  i.e most recently added person being last in the list

### Editing a person : `edit`

Edits an existing person in SOCket.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GITHUBPROFILE] [l/LANGUAGE] [t/TAG]…​`

* Edits the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing languages, the existing languages of the person will **not** be removed i.e. adding of languages is cumulative
* When editing tags, the existing tags of the person will be removed i.e adding of tags is **not** cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by keyword(s): `find`

Finds persons stored in address book based on the given keyword(s) for the respective fields.

Format: `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GITHUBPROFILE] [l/LANGUAGE] [t/TAG]`

* The search for keyword(s) is case-insensitive. 
  * e.g `find n/hans` will match `find n/Hans`
  * e.g `find t/cs2103t` will match `find t/CS2103T`
* There can be one or more keyword(s) for each field. 
  * e.g. `find n/Hans Bo` will match all persons whose name contains either `Hans` or `Bo`
* The order of the keyword(s) and field(s) does not matter.
  * e.g. `find n/Hans Bo` will match `find n/Bo Hans`
  * e.g. `find n/Hans Bo t/cs2103t` will match `find t/cs2103t n/Hans Bo`
* Only full words will be matched.
  * e.g. `Han` will not match `Hans`
  * e.g. `t/2103t` will not match `t/cs2103t` 
* Persons with field values matching at least one keyword for that respective field will be returned (i.e. `OR` search). 
  * e.g. `find n/Hans Bo` will return `Hans Gruber`, `Bo Yang`
  * e.g. `find n/Hans l/Java` will return persons whose name contains `Hans` or language contains `Java` or both
  * e.g. `find t/friend` will return persons who have either tag `friend` or `best friend` or both
* If no field is specified, zero persons will be returned.


  Examples:
* `find n/John` returns `john` and `John Doe`
* `find n/alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete`

Deletes the specified person from SOCket.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Removing a person's field : `remove`

Removes the specific field value based on the given input

Format: `remove INDEX [p/[PHONE]] [p/[GITHUBPROFILE]] [e/[EMAIL]] [a/[ADDRESS]] [l/[LANGUAGE]] [t/[TAG]]...`

* Removes field value of person at the specific `INDEX`.
* Removes all the corresponding field value in respect of the `KEYWORD`.
  * e.g. `l/Java` will remove `Java` in person’s **Language** field.
* Removes the entire field value when that field has no keyword provided.
  * e.g. `t/` will remove all the tags associate to the person.

### Clearing all persons or tags : `clear`

Clears all persons' entries from the SOCket based on the given tags; if tag is not included, clears all persons in SOCket.

Format: `clear [t/TAG]...`

* Removes all the persons related to the specific tags.
* Tag included is **case-insensitive**.
  * e.g. `t/CS2103T` is equivalent to `t/cs2103t`.
* The tags **must be an existing tag** in SOCket.
* If tags are provided, only remove existing tags.
  * e.g. `clear t/cs2103t t/cs2103` will only remove the persons associated with `t/cs2103t` if there exists the `cs2103t` tag but not `cs2103` in SOCket.
* If no tag is provided, remove all the persons in SOCket.
* A confirmation prompt will be asked before removal of persons.

### Sorting persons (by other fields) : `sort`

Sorts and displays the persons according to the provided category. Sorts the list of persons by name if no argument is provided.

Format: `sort [CATEGORY]`
* If no category is provided, the persons are sorted by their names alphanumerically
* If a category is provided, the persons are sorted by that category alphanumerically
  * e.g. sort address will sort the persons by their addresses alphanumerically. Persons without addresses will be at the bottom.

### Undoing a change : `undo`

Undoes the last change made to SOCket.

Format: `undo`
* A message is shown if no changes were made to SOCket

### Redoing an undone change : `redo`

Restores a previously undone change made to SOCket

Format: `redo`
* A message is shown if no undone changes exist

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Changes made to SOCket are **not** saved upon exit.
</div>

### Viewing a person's detailed infomation: `view`

Views a person's details whose in the filtered list

Format: `view INDEX`
* Views the person's detailed information at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

SOCket data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

SOCket data is saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, SOCket will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous SOCket home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

|  Action    | Format, Examples                                                                                                                                                                                                 |
|------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------| 
| **Add**    | `add n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GITHUB_PROFILE] [l/LANGUAGE] [t/TAG]…​` <br> e.g., `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 g/johndoe l/Python`  |
| **Clear**  | `clear`                                                                                                                                                                                                          |
| **Delete** | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                              |
| **Remove** | `remove INDEX [p/[PHONE]] [p/[GITHUBPROFILE]] [e/[EMAIL]] [a/[ADDRESS]] [l/[LANGUAGE]] [t/[TAG]]…​` <br> e.g., `remove 1 t/ l/Java e/`                                                                        |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [g/GITHUBPROFILE] [l/LANGUAGE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                    |
| **Sort**   | `sort [CATEGORY]`<br> e.g. `sort address`                                                                                                                                                                        |
| **Find**   | `find KEYWORD [MORE_KEYWORDS]…​ [t/TAG]…​`<br> e.g., `find James Jake t/cs2103t`                                                                                                                           |
| **List**   | `list [t/tag]`                                                                                                                                                                                                   |
| **Help**   | `help`                                                                                                                                                                                                           |
| **Undo**   | `undo`                                                                                                                                                                                                           |
| **Redo**   | `redo`                                                                                                                                                                                                           |
| **View**   | `view INDEX`<br> e.g., `view 1`                                                                                                                                                                                  |
| **Exit**   | `exit`                                                                                                                                                                                                           |
