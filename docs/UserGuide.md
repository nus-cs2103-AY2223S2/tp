---
layout: page
title: User Guide
---

NextBigFish (NBF) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<<<<<<< HEAD
### Viewing help : 'help'
=======
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `nextbigfish.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your nextbigfish.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar nextbigfish.jar` command to run the application.<br>
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
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help` [coming soon]
>>>>>>> v1.2-help,edit

Brings users to the help page.

Format: `help`

<<<<<<< HEAD
### Adding a person: 'add'
=======
### Adding a person: `add` [coming soon]
>>>>>>> v1.2-help,edit

Adds a person to the address book.

Format: `add n/NAME p/PHONE\_NUMBER e/EMAIL a/ADDRESS s/BUSINESS\_SIZE c/COMPANY\_NAME [x/ACTION] [d/DEADLINE] [t/TAG]`

Examples:

* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 s/100 c/DBS`
* `add n/Betsy Crowe p/1234567 t/friend e/betsycrowe@example.com a/Newgate Prison s/5000 c/Maybank t/criminal`

<<<<<<< HEAD
### Listing all persons : 'list'
=======
### Listing all persons : `list` [coming soon]
>>>>>>> v1.2-help,edit

Shows a list of all persons in the contact list.

Format: list

<<<<<<< HEAD
### Editing a person : 'edit'
=======
### Editing a person : `edit` [coming soon]
>>>>>>> v1.2-help,edit

Edits an existing person in the contact list.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/BUSINESS\_SIZE] [t/TAG]…`

- Edits the person at the specified 'INDEX'. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
- You can remove all the person's tags by typing t/ without specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be 91234567 and johndoe@example.com respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.

### Filtering contacts : `filter` [coming soon]

Filters the contact list.

Format: `filter [s/BUSINESS\_SIZE] [c/COMPANY\_NAME] [d/DEADLINE] [t/TAG]`

- The filter keyword must be followed by one of these: `[s/BUSINESS\_SIZE] [c/COMPANY\_NAME] [d/DEADLINE] [t/TAG]`

Examples:

- `filter` followed by `s/\>5000` retrieves contacts with BUSINESS\_SIZE more than 5000.
- `filter` followed by `c/Maybank` retrieves contacts working in Maybank.
- `filter` followed by `t/criminal` retrieves contacts that are criminal.

### Marking contacts : `mark` [coming soon]

Marks the contact at specified index as requiring follow up action.

Format : `mark INDEX x/ACTION d/DEADLINE`

- Marks a particular contact as requiring follow up action
- This action can be specified by a String of arbitrary length

Examples :

- `mark 1 x/text message d/2002-03-01` marks a contact at index one as requiring a follow up text message by 1st March of 2002.

### Marking as done : `markDone` [coming soon]

Marks the contact with specified index as having completed the follow up action.

Format : `markDone` INDEX

Examples:

- `markDone 3` marks the contact at index 3 as having already been followed up with.

###


### Unmarking : `unmark` [coming soon]

Unmarks the contact with specified index. Which means the contact no longer requires any follow up action.

Format : `unMark INDEX`

Examples:

- `unMark 3` unmarks the contact at index 3 as not requiring any further follow up action.

<<<<<<< HEAD
### Deleting a person : 'delete'
=======
### Deleting a person : `delete` [coming soon]
>>>>>>> v1.2-help,edit

Deletes the specified person from the contact list.

Format: `delete INDEX`

- Deletes the person at the specified INDEX.
- The index refers to the index number shown in the displayed person list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `delete 2` deletes the 2nd person in the address book.
- find Betsy followed by `delete 1` deletes the 1st person in the results of the find command.

<<<<<<< HEAD
### Finding via keywords : 'find'
=======
### Finding via keywords : `find` [coming soon]
>>>>>>> v1.2-help,edit

Finds the entries with the relevant keywords

Format: `find KEYWORD1 KEYWORD2 …`

- Finds the entry/s with the keywords / else shows error message

Examples:

- 'find' followed by `KEYWORD1 KEYWORD2 …` returns all the
- 'find Betsy' followed by delete 1 deletes the 1st person in the results of the find command.

<<<<<<< HEAD
### Clearing all entries : 'clear'
=======
### Clearing all entries : `clear` [coming soon]
>>>>>>> v1.2-help,edit

Clears all entries from the address book.

Format: `clear`

<<<<<<< HEAD
### Exiting the program : 'exit'
=======
### Exiting the program : `exit` [coming soon]
>>>>>>> v1.2-help,edit

Exits the program.

Format: `exit`

### Saving the data

NextBigFish data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
