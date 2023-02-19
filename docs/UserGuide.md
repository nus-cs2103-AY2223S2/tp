---
layout: page
title: User Guide
---

Contact nUS is a **desktop app for managing NUS student's schedule, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Contact nUS can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ContactnUS.jar` from [here](https://github.com/se-edu/addressbook-level3/releases). [coming soon]

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ContactnUS.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try: [all coming soon]

   * `list` : Shows all the items inside the address book.

   * `add lecture CS2103T /date Friday 10am ` : Adds a lecture named `CS2103T` to the Address Book.

   * `delete 3` : Deletes the 3rd item shown in the current list.

   * `edit 1 m/CS2103T i/Tutorial d/22 Feb 2023` : Edits the module name, item type and date of the 1st item to be CS2103T, Tutorial and 22 Fev 2022 respectively.

<!--    * `clear` : Deletes all items.

   * `exit` : Exits the app. -->

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features [Coming soon]

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

<!-- * Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc. -->

<!-- * Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable. -->

<!-- * If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken. -->

<!-- * Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`. -->

</div>

<!-- ### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help` -->


### Adding a person: `add`

Adds an item to the address book.

Format: `add ITEMTYPE ITEMNAME [DETAILS] [d/DATE]`

* ITEMTYPE is either one of the three:  lecture, tutorial, or deadline

<!-- <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div> -->

Examples:
* `add lecture CS2103T /date Friday 10am`
* `add tutorial CS2103T /date Tuesday 10am`

### Listing all persons : `list`

Shows a list of all items in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing item in the address book.

Format: `edit Index [m/MODULE_NAME] [i/ITEM_TYPE] [d/DATE] [t/TIME]`

* Edits the items at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.


Examples:
*  `edit 1 m/CS2103T i/Tutorial d/22 Feb 2023 ` Edits the module name, item type and date of the 1st item to be CS2103T, Tutorial and 22 Fev 2022 respectively..
*  `edit 2 m/CS2103T i/Lecture` Edits the module name and item type of the 2nd item.

<!-- ### Locating persons by name: `find`

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
  ![result for 'find alex david'](images/findAlexDavidResult.png) -->

### Deleting a person : `delete`

Deletes the specified item from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.

<!-- ### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div> -->

<!-- ### Archiving data files `[coming in v2.0]`

_Details coming soon ..._ -->

<!-- --------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------
 -->
## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**List** | `list`


<!-- **Clear** | `clear` -->
<!-- **Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake` -->
<!-- **Help** | `help` -->
