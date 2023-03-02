---
layout: page
title: User Guide
---

Docédex is a **desktop application for managing doctors and patients within hospitals**, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you type fast, Docédex can get your patient management tasks done faster than traditional GUI apps.<br>

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Make sure that you have **Java 11 or above** installed on your computer.

2. Download the latest jar file (`docedex.jar`) from our [Github Releases](https://github.com/AY2223S2-CS2103T-F12-1/tp).

3. Place the jar file into a new folder. This folder will be used as the home folder for the Docedex application.

4. Open a command terminal within the home folder.

5. Enter `java -jar docedex.jar` in the command terminal
   1. You should notice the GUI of the application pop up.<br>
   ![Ui](images/Ui.png)
   
6. Type the command in the command box and press Enter to execute it. e.g. typing **`list-doc`** and pressing Enter will display all doctors stored in Docedex.<br>
   1. Some example commands you can try:

      * `help` : Opens up the help menu.

      * `add-doc n/John Doe p/98765432` : Adds a doctor contact named `John Doe` to Docedex.

      * `del-doc 3` : Deletes the doctor with the associated ID of 3.
      
      * `find-doc Gabriel` : Finds all doctors contacts that have the keyword 'Gabriel'

      * `exit` : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

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

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list-doc`, `exit`) will be ignored.<br>
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


### Exiting the program

Exit the program.

Format: `exit`

### Saving the data

Docedex data is saved automatically after any command that changes data. There is no need to save manually.

### Editing the data file

Docedex data is saved as a JSON file at this specified path: `[JAR file location]/data/docedex.json`.

DO NOT modify data directly, as it might result in the malfunction of the application. In the worst case scenario, all your data may be reset. Only modify data directly if you know what you are doing and accept the risks of such actions.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How can I load data from another computer into Docedex installed on another computer?<br>
**A**: Delete the `docedex.json` file (stored at `[JAR file location]/data/docedex.json`) from the computer that you wish to use Docedex on. Then, copy over the `docedex.json` file from the computer which you no longer wish to use Docedex on. After which, boot up Docedex to check whether your doctor information is properly loaded into the new computer.
--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Doctor** | `add-doc n/NAME p/PHONE_NUMBER` <br> E.g. `add-doc n/Gabriel p/81119666`
**Delete Doctor** | `del-doc INDEX`<br> E.g. `del-doc 3`
**Edit Doctor** | `edit-doc INDEX [n/NAME] [p/PHONE_NUMBER]`<br> E.g. `edit-doc 3 n/Gabriel Tan p/12345678`
**Find Doctor** | `find-doc KEYWORD`<br> E.g. `find-doc Gabriel`
**List Doctors** | `list-doc`
**Help** | `help`
**Exit** | `exit`
