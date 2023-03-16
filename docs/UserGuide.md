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

   * `list` : Shows all the items inside the module tracker.

   * `add n/CS2103T t/Tutorial e/Wednesday 10-11am a/COM1-0210` : Adds a lecture named `CS2103T` with `Tutorial` on `Wednesday 10-11am` at `COM1-0210` to the Module Tracker.

   * `delete 3` : Deletes the 3rd item shown in the current list.

   * `edit 1 n/CS2101 t/Tutorial` : Edits the module name and item type of the 1st item to be `CS2101` and `Tutorial` respectively.

<!--    * `clear` : Deletes all items.

   * `exit` : Exits the app. -->

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features [Coming soon]

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/MODULE_NAME`, `MODULE_NAME` is a parameter which can be used as `add n/CS1231S`.

* Items in square brackets are optional.<br>
  e.g `n/MODULE_NAME [t/TAG]` can be used as `n/CS1010S t/Can attend online :)` or as `n/CS1010S`.

<!-- * Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Lecture`, `t/Lecture t/Lab` etc. -->

<!-- * Parameters can be in any order.<br>
  e.g. if the command specifies `n/MODULE_NAME t/TYPE`, `t/TYPE n/MODULE_NAME` is also acceptable. -->

<!-- * If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `e/Monday 10am-12pm e/Tuesday 2-4pm`, only `e/Tuesday 2-4pm` will be taken. -->

<!-- * Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`. -->

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help` 


### Adding a person: `add`

Adds an item to the module tracker.

Format: `add n/MODULE_NAME t/TYPE e/TIMESLOT a/VENUE [s/TEACHER] [d/DEADLINES] [r/REMARKS]`

* MODULE_NAME is the name of the module to be added into the module tracker.
* TYPE is either one of the three, Lecture, Tutorial, or Lab.
* TIMESLOT represents when the event takes place.
* VENUE is the location of the classroom or auditorium the class is held.
* TEACHER is the name of the lecturer or TA conducting the class.
* DEADLINES contain the details of a task with deadline.
* REMARKS are additional details about the class you want to add.

<!-- <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div> -->

Examples:
* `add n/CS2103T t/Lecture e/Friday 2-4pm a/i3-AUD`
* `add n/CS1101S t/Tutorial e/Monday 10am-12pm a/COM1-0217 s/Sam Wan`
* `add n/CS2030S t/Lab e/Thursday 12-2pm a/COM1-B112 d/LAB DUE TUESDAY 23:59 r/Attendance not compulsory :)`

### Listing all persons : `list`

Shows a list of all items in the module tracker.

Format: `list`

### Editing a person : `edit`

Edits an existing item in the module tracker.

Format: `edit INDEX [n/MODULE_NAME] [t/TYPE] [e/TIMESLOT] [a/VENUE] [s/TEACHER] [d/DEADLINES] [r/REMARKS]`

* Edits the items at the specified INDEX. The index refers to the index number shown in the displayed person list. The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.


Examples:
*  `edit 1 n/CS2101 t/Tutorial` Edits the module name, item type and date of the 1st item to be `CS2101` and `Tutorial` respectively.
*  `edit 2 a/COM3-B110 s/Professor Franklin Stein r/Funny lecturer haha` Edits the venue, teacher and remark of the 2nd item to be `COM3-B110`, `Professor Franklin Stein` and `Funny lecturer haha` respectively.
*  `edit 5 n/CS1231S d/Assignment 1: due 06/09/2023` Edits the module name and deadline of the 5th item to be `CS1231S` and `Assignment 1: due 06/09/2023` respectively.

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

Deletes the specified item from the module tracker.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the module tracker.

<!-- ### Clearing all entries : `clear`

Clears all entries from the module tracker. -->

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Contact nUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div> 

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
**Add** | `n/MODULE_NAME t/TYPE e/TIMESLOT a/VENUE [s/TEACHER] [d/DEADLINES] [r/REMARKS]…​` <br> e.g., `add n/CS2103T t/Lecture e/Friday 2-4pm a/i3-AUD s/Professor Damith d/Weekly Quiz due Friday 13:59 r/Can attend online!`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/MODULE_NAME] [t/TYPE] [e/TIMESLOT] [a/VENUE] [s/TEACHER] [d/DEADLINES] [r/REMARKS]​`<br> e.g.,`edit 2 s/Low Mai Khye r/Funny TA`
**List** | `list`
**Exit** | `exit`

<!-- **Clear** | `clear` -->
<!-- **Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake` -->
<!-- **Help** | `help` -->
