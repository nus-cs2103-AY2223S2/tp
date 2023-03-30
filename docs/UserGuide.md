
Contact nUS is a **desktop app that NUS computing students can use to track their modules and schedules.** It is optimized for use via typing with a Command Line Interface (CLI) rather than clicking which is usually associated with a Graphical User Interface (GUI). However, it provides benefits of both interfaces. NUS computing students can be considered tech-savvy individuals who are also generally fast typists. As such, they stand to gain from the productivity boost that Contact nUS has to offer. 

This guide serves to help NUS computing students familiarise themselves with the use of Contact nUS. 

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer. To check if you have Java `11`, open up the `terminal` application (for both Windows and Mac OS users) and type in the command `java -version` and press enter. You should see `java version 11` with additional numbers at the back. 

2. Download the latest `ContactnUS.jar` from [here](www.google.com). 

3. Copy the file to the folder you want to use as the _home folder_ for your ModuleTracker. This can be `Desktop` for example.

4. Again, open up the `terminal` application. You should be in a directory. If you are not in the folder that you has `ContactnUS.jar`
downloaded, you can use the command `cd` to move into that folder. An example would be `cd Desktop`.

5. Now that you are in the same folder as `ContactnUS.jar`, type in the command `java -jar ContactnUS.jar` to run the application.

<br> A screen similar to the one below should appear in a few seconds. Note how there is already some sample data included.<br>
![Ui](images/Ui.png)

5. You should see a command box. You can now type commands into it and start using `contact nUS`! 

   A brief introduction of the commands List of commands available:
   
   * `list` : Shows all the modules. 
   
   * `add` : Adds a new module. 

   * `delete` : Deletes a module.

   * `edit` : Edits a module. 

   * `find` : Finds a module based on its description. 

   * `sort` : Sorts the modules based on the timeslot or deadline of the module.

   * `clear` : Clears all the modules in the list. Do this only if you are sure!

   * `exit` : Exits the application.


1. Refer to the [Features](#features) below for further details of each command.

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

![image](https://user-images.githubusercontent.com/82088609/227960415-f3120f1c-6779-4d33-a41c-0eca0d85f285.png)


### Reminder for items

Shows all the deadlines and timeslots you have today

* You don't have to type anything, whenever the application launches it will tell you this information
* it will only look at time slots and deadlines

![image](https://user-images.githubusercontent.com/82088609/227960147-f26fae28-c2e7-44bf-bea1-a3d68a3539b5.png)

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
