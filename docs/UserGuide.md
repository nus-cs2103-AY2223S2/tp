---
layout: page
title: User Guide
---

ConnectUS is a desktop app for **managing contacts, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConnectUS can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ConnectUS.jar` from [here](https://github.com/AY2223S2-CS2103T-W15-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ConnectUS app.

4. Double-click the `.jar` file to start the app.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/James p/12345678 e/james@example.com tele/@itsjameshere bd/14/02/2000` : Adds a contact named `James` to ConnectUS.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [bd/BIRTHDAY]` can be used as `n/John Doe bd/14/02/2000` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[mod/MODULE_CODE]…​` can be used as ` ` (i.e. 0 times), `mod/CS2103T`, `mod/CS2103T mod/CS2107` etc.

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

Adds a person to the ConnectUS app.

Format: `add n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tele/TELEGRAM] [bd/BIRTHDAY] [mod/MODULE_NUMBER]…​ [cca/CCA]…​ [ccap/CCA: POST]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of modules and CCAs. (including 0)
</div>

Examples:
* `add n/James` would create a contact named James without any other contact information.
* `add n/James p/12345678` would create a contact named James with a phone number 12345678.
* `add n/James e/james@example.com tele/@itsjameshere` would create a contact named James with an email james@example.com and Telegram `@itsjameshere`.
* `add n/James tele/@itsjameshere mod/CS2103T mod/CS2101 CCA/NUS Hackers` would create a contact named James with Telegram `@itsjameshere`, the module tags of CS2103T and CS2101, and the CCA of NUS Hackers.

### Listing all persons : `list`

Shows a list of all persons in the ConnectUS app.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the ConnectUS app.

Format: `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tele/TELEGRAM] [bd/BIRTHDAY] [mod/MODULE_NUMBER]…​ [cca/CCA]…​ [ccap/CCA: POST]…​ -t`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** e.g. 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove all the person’s tags by typing `edit -t` without specifying any tags after it.

Examples:
*  `edit 1 p/12345678 e/james@example.com` Edits the phone number and email address of the 1st person to be `12345678` and `james@example.com` respectively.
*  `edit 2 n/Betsy Crower -t` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Deleting a person : `delete`

Deletes the specified person from the ConnectUS app.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** e.g. 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the ConnectUS app.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the ConnectUS app.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ConnectUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ConnectUS data is saved as a JSON file `[JAR file location]/data/ConnectUS.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action     | Format                                                                                                                                             | Examples                                                                      |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| **Add**    | `add n/NAME [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tele/TELEGRAM] [bd/BIRTHDAY] [mod/MODULE_NUMBER]…​ [cca/CCA]…​ [ccap/CCA: POST]…​`             | `add n/James p/12345678 e/james@example.com tele/@itsjameshere bd/14/02/2000` |
| **Clear**  | `clear`                                                                                                                                            |                                                                               |
| **Delete** | `delete INDEX`                                                                                                                                     | `delete 3`                                                                    |
| **Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL] [tele/TELEGRAM] [bd/BIRTHDAY] [mod/MODULE_NUMBER]…​ [cca/CCA]…​ [ccap/CCA: POST]…​ -t` | `edit 1 p/12345678 e/james@example.com tele/@itsjameshere`                    |
| **List**   | `list`                                                                                                                                             |                                                                               |
| **Help**   | `help`                                                                                                                                             |                                                                               |
| **Exit**   | `exit`                                                                                                                                             |                                                                               |
