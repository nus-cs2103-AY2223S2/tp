---
layout: page
title: User Guide
---

ExpressLibrary is a **desktop app for managing library users and books, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ExpressLibrary gets your tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `expressLibrary.jar` from [here](https://github.com/AY2223S2-CS2103T-T12-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ExpressLibrary.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar expressLibrary.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `listPerson` : Lists all contacts.

   * `addPerson 1 n/Bob Tan p/97450597 e/btan@mail.com`: 
   Adds a contact named `Bob Tan` to records

   * `deletePerson 3` : Deletes the 3rd user shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addPerson n/NAME`, `NAME` is a parameter which can be used as 
`addPerson n/John Doe`.

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

### Person Commands

### Adding a user: `addPerson`

Adds a person to the ExpressLibrary.

Format: `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `addPerson n/Bob Tan p/91112222 e/btan@mail.com a/ 313, Jurong East Street 32 b/Harry Potter`

### Deleting a person : `deletePerson`

Delete a student given a student ID.

Format: `deletePerson STUDENT_ID`

* Deletes the person at the specified STUDENT_ID.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

Examples:
* `listPerson` followed by `deletePerson 2` deletes the 2nd person in the user records.

### Listing all users : `listPerson`

Shows a list of all users in the user records.

Format: `listPerson`

### Editing a person : `editPerson`


Edits an existing person in the address book.

Format: `editPerson INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

[//]: # ()
* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​

* At least one of the optional fields must be provided.

* Existing values will be updated to the input values.

* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.

* You can remove all the person’s tags by typing `t/` without
 specifying any tags after it.


Examples:

*  `editPerson 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.

*  `editPerson 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `findPerson` 

[//]: # (_Details coming soon ..._)

Finds persons whose names contain any of the given keywords.

[//]: # ()
Format: `findPerson KEYWORD [MORE_KEYWORDS]`

[//]: # ()
* The search is case-insensitive. e.g `hans` will match `Hans`

* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`

* Only the name is searched.

[//]: # (* Only full words will be matched e.g. `Han` will not match `Hans`)

* Persons matching at least one keyword will be returned (i.e. `OR` search).

  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`


Examples:

* `findPerson John` returns `john` , `John Doe` and `johnston`.

[//]: # (* `findPerson alex david` returns `Alex Yeoh`, `David Li`<br>)

[//]: # (  ![result for 'find alex david']&#40;images/findAlexDavidResult.png&#41;)

### Book Commands

### Listing all books : `listBook`

Shows a list of all books in the user records.

Format: `listBook`

### Common Functions

### Clearing all entries : `clear`

[//]: # (_Details coming soon ..._)

Clears all entries from the ExpressLibrary.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ExpressLibrary data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

_Details coming soon ..._

ExpressLibrary data are saved as a JSON file `[JAR file location]/data/expresslibrary.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">

:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ExpressLibrary will discard all data and start with an empty data file at the next run.

</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ExpressLibrary home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

#### Person

Action | Format, Examples
--------|------------------
**AddPerson** | `addPerson n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**DeletePerson** | `deletePerson INDEX`<br> e.g., `deletePerson 3`
**EditPerson** | `editPerson INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`editPerson 2 n/James Lee e/jameslee@example.com`
**FindPerson** | `findPerson KEYWORD [MORE_KEYWORDS]`<br> e.g., `findPerson James Jake`
**ListPerson** | `listPerson`
**Help** | `help`

#### Book
Action | Format, Examples
--------|------------------
**List**| `listBook`
