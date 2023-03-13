---
layout: page
title: User Guide
---

**Bookopedia** is a desktop application specifically built for parcel delivery riders, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
This enables delivery riders to be able to quickly plan their deliveries by typing in commands and still be able to view them in an organized manner.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `bookopedia.jar` from [here](https://github.com/AY2223S2-CS2103-W16-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Bookopedia.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar bookopedia.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Start using Bookopedia!

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [e/EMAIL]` can be used as `n/John Doe e/jd@gmail.com` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[pc/PARCEL]…​` can be used as ` ` (i.e. 0 times), `pc/shopee`, `pc/shopee pc/lazada` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME a/ADDRESS`, `a/ADDRESS n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `n/oldName n/newName`, only `n/newName` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the user guide.

![help message](images/helpMessage.png)

Format: `help`


### Add delivery: `add`

Adds a person to deliver to.

Format: `add n/NAME a/ADDRESS [p/PHONE_NUMBER] [e/EMAIL] [pc/PARCEL]…​`

Examples:
* `add n/Yusof a/Yusof Ishak House, #04-420 e/yusof@gmail.com pc/lazada123 pc/lazada456`
* `add n/Linus a/Utown, #01-01 pc/shopee234`

### Add parcel to a delivery: `add_pc`

Adds a parcel to an existing delivery at the specified `INDEX`. The index refers to the index number shown in the displayed delivery list. The index **must be a positive integer** 1, 2, 3, …​

Format: `add_pc INDEX pc/PARCEL`

Examples:
* `add 1 pc/shopee234` Adds parcel shopee234 to 1st delivery.
* `add 2 pc/lazada456` Adds parcel lazada456 to 2nd delivery.

### Listing all deliveries : `list`

Show all deliveries and their respective details.

Format: `list`

### Mark delivery : `mark`

Mark a delivery with a status.

Format: `mark INDEX s/STATUS`

* Edits the delivery at the specified `INDEX`. The index refers to the index number shown in the displayed delivery list. The index **must be a positive integer** 1, 2, 3, …​
* `STATUS` must be of the following
  * `otw` for in progress delivery
  * `fail` for failed delivery
  * `done` for done delivery

Examples:
* `mark 1 s/otw` Marks the 1st delivery as in progress.
* `mark 1 s/fail` Marks the 1st delivery as failed.
* `mark 1 s/done` Marks the 1st delivery as done.

### Viewing a delivery : `view`

View more details about a delivery.

Format: `view INDEX`

* View more details about a delivery at the specified `INDEX`. The index refers to the index number shown in the displayed delivery list. The index must be a **positive integer**.

Examples:
* `view 1` View more details about the 1st delivery in the list.
* `view 2` View more details about the 2nd delivery in the list.

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [a/ADDRESS] [p/PHONE] [e/EMAIL] [pc/PARCEL]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing parcels, the existing parcels of the person will be removed i.e adding of parcels is not cumulative.
* You can remove all the person’s parcels by typing `pc/` without
    specifying any parcels after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower pc/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing parcels.

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

### Deleting a delivery : `delete`

Deletes the specified delivery from the delivery list.

Format: `delete INDEX`

* Deletes the delivery at the specified `INDEX`.
* The index refers to the index number shown in the displayed delivery list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd delivery in the task manager.

### Clearing all entries : `clear`

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
**Add** | `add n/DELIVERY a/ADDRESS` <br> e.g., `add n/Shopee a/Yusof Ishak House, #04-420`
**Mark** | `mark INDEX s/STATUS` <br> e.g., `mark 1 s/otw`
**View** | `view INDEX` <br> e.g., `view 1`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
