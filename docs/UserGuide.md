
Contact nUS is a **desktop app for managing NUS student's schedule, optimized for use via a Command Line Interface** 
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Contact nUS can get 
your contact management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ContactnUS.jar` from [here](https://github.com/se-edu/addressbook-level3/releases). 
[coming soon]

3. Copy the file to the folder you want to use as the _home folder_ for your ModuleTracker.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ContactnUS.jar` 
command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will 
open the help window.<br>
   Some example commands you can try: [all coming soon]

   * `list` : Shows all the items inside the module tracker.

   * `add n/CS2103T t/Tutorial e/Wednesday 10-11am a/COM1-0210` : Adds a lecture named `CS2103T` with `Tutorial` 
   on `Wednesday 10-11am` at `COM1-0210` to the Module Tracker.

   * `delete 3` : Deletes the 3rd item shown in the current list.

   * `edit 1 n/CS2101 t/Tutorial` : Edits the module name and item type of the 1st item to be `CS2101` and `Tutorial` 
   respectively.

   * `exit` : Exits the app.

<!--    * `clear` : Deletes all items. -->



1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Definition

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
    * e.g. in `add n/MODULE_NAME`, `MODULE_NAME` is a parameter which can be used as `add n/CS1231S`.

* Words in `UPPER_CASE` are usually in *string* format, except for `TIMESLOT` and `DEADLINE`, which are both in 
DateTime format, **ddMMyyyy HH:mm**.

* Items in square brackets are optional.<br>
    * e.g `n/MODULE_NAME [t/TAG]` can be used as `n/CS1010S t/Can attend online :)` or as `n/CS1010S`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  * e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/Lecture`, `t/Lecture t/Lab` etc.

* Parameters can be in any order.<br>
* e.g. if the command specifies `n/MODULE_NAME t/TAG`, `t/TAG n/MODULE_NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence 
of the parameter will be taken.<br>
  * e.g. if you specify `e/280323 10:00 e/290323 12:00`, only `e/290323 12:00` will be taken.

<!-- * Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`)
will be ignored.<br>
  * e.g. if the command specifies `help 123`, it will be interpreted as `help`. -->

* **MODULE_NAME** is the name of the module to be added into the module tracker.

* **TAG** is either one of the three, Lecture, Tutorial, or Lab.

* **TIMESLOT** represents when the event takes place.

* **VENUE** is the location of the classroom or auditorium the class is held.

* **TEACHER** is the name of the lecturer or TA conducting the class.
    * s stands for *sensei*, which means teacher in Japanese!

* **DEADLINES** contain the details of a task with deadline.

* **REMARKS** are additional details about the class you want to add.

* **RESOURCE** can be the module website on Canvas, or any other external links.
    * As you may have guessed, as an NUS student, c stands for *Canvas*, our favourite website.

--------------------------------------------------------------------------------------------------------------------

## Features

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help` 


### Adding a module: `add`

Adds an item to the module tracker.

Format: `add n/MODULE_NAME t/TAG [e/TIMESLOT] [a/VENUE] [s/TEACHER] [d/DEADLINES] [r/REMARKS] [c/RESOURCE]`

<!-- <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A module can have any number of tags (including 0)
</div> -->

Examples:
* `add n/CS2103T t/Lecture`
* `add n/CS1101S t/Tutorial e/03042023 10:00 a/COM1-0217 s/Sam Wan`
* `add n/CS2030S t/Lab a/COM1-B112 d/04042023 23:59 r/Attendance not compulsory :)`

### Listing all modules : `list`

Shows a list of all items in the module tracker.

Format: `list`

### Editing a module : `edit`

Edits an existing item in the module tracker.

Format: `edit INDEX [n/MODULE_NAME] [t/TAG] [e/TIMESLOT] [a/VENUE] [s/TEACHER] [d/DEADLINES] [r/REMARKS] [c/RESOURCE]`

* Edits the items at the specified INDEX. The index refers to the index number shown in the displayed module list. 
The index must be a positive integer 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the module will be removed i.e adding of tags is not cumulative.


Examples:
* `edit 1 n/CS2101 t/Tutorial` Edits the module name, item type and date of the 1st item to be `CS2101` and `Tutorial` 
respectively.
* `edit 2 a/COM3-B110 s/Professor Franklin Stein r/Funny lecturer haha` Edits the venue, teacher and remark of the 2nd 
item to be `COM3-B110`, `Professor Franklin Stein` and `Funny lecturer haha` respectively.
* `edit 5 n/CS1231S d/02042023 23:59` Edits the module name and deadline of the 5th item to be `CS1231S` 
and `02042023 23:59` respectively.

### Finding a module or type : `find`

finds the specified module or type from the module tracker.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The KEYWORD refers to the module name or type name such as CS2103T or tutorial.
* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the module name and type are searched.
* Only full words will be matched e.g. `CS2103` will not match `CS2103T`
* Modules matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `CS2103T tutorial` will return the CS2103T module and tutorials in the module tracker

Examples:
* `list` followed by `find CS2103T` finds all instances where there is a module named CS2103T in the list.
* `list` followed by `find tutorial` finds all instances of tutorials in the list
* `list` followed by `CS2103T tutorial` will return modules named CS2103T and all tutorial type in the module tracker

![image](https://user-images.githubusercontent.com/82088609/227960415-f3120f1c-6779-4d33-a41c-0eca0d85f285.png)


### Reminder for items

Shows all the deadlines and timeslots you have today

* You don't have to type anything, whenever the application launches it will tell you this information
* it will only look at time slots and deadlines

![image](https://user-images.githubusercontent.com/82088609/227960147-f26fae28-c2e7-44bf-bea1-a3d68a3539b5.png)

### Deleting a module : `delete`

Deletes the specified item from the module tracker.

Format: `delete INDEX`

* Deletes the module at the specified `INDEX`.
* The index refers to the index number shown in the displayed module list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd module in the module tracker.

<!-- ### Clearing all entries : `clear`

Clears all entries from the module tracker.

Format: `clear` -->

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Contact nUS data are saved in the hard disk automatically after any command that changes the data. There is no need to 
save manually.

### Editing the data file

ModuleTracker data are saved as a JSON file `[JAR file location]/data/moduletracker.json`. Advanced users are welcome to 
update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Contact nUS will discard all data and start with an empty 
data file at the next run.
</div> 

<!-- ### Archiving data files `[coming in v2.0]`

_Details coming soon ..._ -->

 --------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer? <br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous ContactnUS home folder.

--------------------------------------------------------------------------------------------------------------------
 
## Command summary

| Action                                                                                                                                                            | Format, Examples                                                                                                                                             |
|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **add**                                                                                                                                                           | `n/MODULE_NAME t/TAG [e/TIMESLOT] [a/VENUE] [s/TEACHER] [d/DEADLINES] [r/REMARKS] [c/RESOURCE]…​` <br>                                                       |
| e.g., `add n/CS2103T t/Lecture e/310323 14:00 a/i3-AUD s/Professor Damith d/07042023 13:59 r/Can attend online! c/https://nus-cs2103-ay2223s2.github.io/website/` |                                                                                                                                                              |
| **delete**                                                                                                                                                        | `delete INDEX`<br> e.g., `delete 3`                                                                                                                          |
| **edit**                                                                                                                                                          | `edit INDEX [n/MODULE_NAME] [t/TAG] [e/TIMESLOT] [a/VENUE] [s/TEACHER] [d/DEADLINES] [r/REMARKS] [c/RESOURCE] ​`<br> e.g.,`edit 2 s/Low Mai Khye r/Funny TA` |
| **list**                                                                                                                                                          | `list`                                                                                                                                                       |
| **exit**                                                                                                                                                          | `exit`                                                                                                                                                       |

<!-- **Clear** | `clear` -->
<!-- **Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake` -->
<!-- **Help** | `help` -->

</div>
