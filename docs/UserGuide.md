---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** 
(CLI) while still having the benefits of a **Graphical User Interface (GUI)**. If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>

[//]: # (   ![Ui]&#40;images/Ui.png&#41;)


1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

####Notes:
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.
e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…` after them can be used multiple times including zero times.
e.g. [t/TAG]…​ can be used as   (i.e. 0 times), t/friend, t/friend t/family etc.
- Parameters can be in any order.
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.




  
### Viewing help : `help`

Shows a message explaning how to access the help page.


Format: `help`


### Adding a person: `add`

Adds a person to the patient records.

Format: `add n/NAME i/NRIC p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…`


**Tip**: A person can have any number of tags (including 0)


Examples:
* `add n/John Doe i/T0012345A p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe i/T0012345B e/betsycrowe@example.com a/Newgate Prison p/1234567 t/diabetic`



### Adding a person: `add`

Edits an existing person in the patient record.

Format: `edit INDEX [n/NAME] [i/NRIC] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`


* Edits the person at the specified `INDEX`. 
* The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided. 
* Existing values will be updated to the input values. 
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative. 
* You can remove all the person’s tags by typing t/ without specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com`.Edits the phone number and email address of the 1st person to be 91234567 and johndoe@example.com respectively.
* `edit 2 n/Betsy Crower t/`.Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.


### Deleting a person: `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`


* Edits the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the find command.

### Deleting a person: `filter`

Filters people according to a particular attribute. Eg. Medicine usage or health conditions.

Format: `filter KEYWORD [MORE_KEYWORDS]`


* The search is case-sensitive. e.g panadol will match panadol 
* The order of the keywords does matters. e.g. panadol will match panadol 
* Only the name of the attribute is searched
* Only full words will be matched e.g. Han will not match Hans

Examples:
* `filter panadol` returns `john` and `John Doe` who both use panadol
* `filter diabetes` returns `john` and `John Doe` who both have diabetes as a health condition

### Get a person: `get`
Get a person from the records system.

Format: `get i/NRIC`

Examples:
* `get i/T0012345A`
  

###Clearing all data: `clearAll`
Purges all data from the database

Format: `clearAll`

###Save data: `save`
Save the data to different save files

Format: `save FILE_NO`

Example: `save 3` saves the data to the 3rd slot

###Help menu:
Help function lists out all the commands available, along with a brief description
Format: `help`


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME i/NRIC p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g. `add n/John Doe i/T0012345A p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
**Get** | ` get i/T0012345A`
**Clear all** | `clearAll`
**Delete** | `delete INDEX` e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [i/NRIC] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…`  e.g.,edit 2 n/James Lee e/jameslee@example.com
**Find** | `filter KEYWORD [MORE_KEYWORDS]` <br/> e.g., find panadol <br/> e.g., find diabetes
**Save** | `save FILE_NO` <br/> eg. save 3
**List** | `list`
**Help** | `help`
