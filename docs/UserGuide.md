---

Title: HospiSearch User Guide
---

HospiSearch is a **desktop app for managing contacts, optimized for use via a Command Line Interface** 
(CLI) while still having the benefits of a **Graphical User Interface (GUI)**. If you can type fast, HospiSearch can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents: coming soon

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `HospiSearch.jar` from [here](https://github.com/AY2223S2-CS2103T-T11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your HospiSearch.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hospisearch.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
5. Type in a command in the command box to execute it. Some commands to try:
   1. `help` opens up the help menu
6. Refer to the [Features](#features) below for details of each command.

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

Format: `add n/NAME i/NRIC p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/DIAGNOSIS] [t/TAG]…`


**Tip**: A person can have any number of tags (including 0)


Examples:
* `add n/John Doe i/T0012345A p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/cancer`
* `add n/Betsy Crowe i/T0012345B e/betsycrowe@example.com a/Newgate Prison p/1234567 d/diabetes d/osteoporosis`



### Editing a person: `edit`

Edits an existing person in the patient record.

Format: `edit INDEX [n/NAME] [i/NRIC] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/DIAGNOSIS] [t/TAG]…​`

* You can remove all the person’s tags by typing t/ without specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com`.Edits the phone number and email address of the 1st person to be 91234567 and johndoe@example.com respectively.
* `edit 2 n/Betsy Crower t/`.Edits the name of the 2nd person to be Betsy Crower and clears all existing tags.


### Deleting a person: `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the find command.

### Filtering a person: `filter`

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

###Backup data: `backup`
Backs up the data to different indexes

Format: `backup INDEX_NO`

Examples:
* `backup 3` backups the data to the 3rd slot

Tip: INDEX_NO can only be an integer between 1 and 10

###Load data: `load`
Loads the data from different slots

Format: `load INDEX_NO`

Example: `load 3` loads the data from the 3rd slot

###Help menu:
Help function lists out all the commands available, along with a brief description

Format: `help`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`




--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HospiSearch home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME i/NRIC p/PHONE_NUMBER e/EMAIL a/ADDRESS [d/diagnosis] [t/TAG]…​` <br> e.g. `add n/John Doe i/T0012345A p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/depression`
**Get** | ` get i/T0012345A`
**Clear all** | `clearAll`
**Delete** | `delete INDEX` e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [i/NRIC] [p/PHONE] [e/EMAIL] [d/DIAGNOSIS] [a/ADDRESS] [t/TAG]…`  e.g.,edit 2 n/James Lee e/jameslee@example.com
**Find** | `filter KEYWORD [MORE_KEYWORDS]` <br/> e.g., find panadol <br/> e.g., find diabetes
**Save** | `save FILE_NO` <br/> eg. save 3
**List** | `list`
**Help** | `help`
