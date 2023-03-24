---
layout: page
title: User Guide
---

Clock-Work is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clock-Work can get your assignment management tasks done faster than traditional GUI apps.

* Table of Contents
  * [1. Quick start](#1-quick-start)
  * [2. Features and commands](#2-features-and-commands)
    * [2.1 help](#21-viewing-help--help)
    * [2.2 add](#22-adding-a-task--add)
    * [2.3 list](#23-listing-all-tasks--list)
    * [2.4 edit](#24-editing-a-task--edit)
    * [2.5 find](#25-locating-tasks-by-name--find)
    * [2.6 delete](#26-deleting-a-task--delete)
    * [2.7 stats](#27-getting-statistics--stats)
    * [2.8 sort](#28-sorting-tasks--sort)
    * [2.9 alert](#29-get-alerts--alert-alert-window)
    * [2.10 plan](#210-plan-your-month--plan-effort)
  * [3. Storage](#3-storage)
    * [3.1 saving the data](#31-saving-the-data)
    * [3.2 editing the data](#32-editing-the-data-file)
  * [4. FAQ](#4-faq)
  * [5. Command summary](#5-command-summary)

--------------------------------------------------------------------------------------------------------------------

## 1. Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `clockwork.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-3/tp/releases). (Currently not available)

3. Copy the file to the folder you want to use as the _home folder_ for your Clock-Work.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar clockwork.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/Meeting d/CSXXXX project meeting` : Adds a task named Meeting to task book.

   * `delete 3` : Deletes the 3rd task shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features and commands](#2-features-and-commands) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 2. Features and Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>
* First word is assumed to be a command word (add/delete/list/find).
* Each task **must** have a corresponding description.
* Words in `ALL CAPS` are the parameters to be supplied by the user.<br>

* Items in square brackets are optional.<br>
  e.g `n/TASK d/DESCRIPTION [tag/TAG]` can be used as `n/Read Book d/Intro to Competitive Programming tag/relax` or as `n/Read Book d/Intro to Competitive Programming t/relax`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/important`, `tag/important t/urgent` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `t/TASK d/DESCRIPTION`, `d/DESCRIPTION t/TASK` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `d/do it fast d/do it slow`, only `d/do it slow` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* A valid Date (must be a legitimate date) must be in the format of `YYYY-MM-DD HHMM` such as `2023-07-13 1800` (13 July 2023, 6PM)

* A valid Date must have a valid time. A minimum of 4 characters must be supplied and only a maximum of 4 character will be parsed
  e.g. `2023-07-13 180` is invalid and `2023-07-13 18000000000` is understood as `2023-07-13 1800`

</div>

### 2.1 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### 2.2 Adding a task: `add`

:speech_balloon: PRO TIP: Parameters that comes after the command can be in any order!
:warning: You are unable to add any tasks (simpleTask, Deadline, Event) of the same name.

Adds a task to the address book. There are 3 types of tasks. `SimpleTask`, `Deadline` and `Event`.
For `Deadline` and `Event` date(s) are required.

Dates should be in the format `YYYY-MM-DD HHMM`. ([What is a valid Date?](#q2-what-is-a-valid-date))

By default, effort level is 24. ([What is an effort level?](#q3-what-is-an-effort-level))

A Task must have a description.([What is a valid Description?](#q4-what-is-a-valid-description))

Format:

* SimpleTask: `add n/TASKNAME d/DESCRIPTION [t/TAGS]…​ [E/EFFORT]…​`

* Deadline: `add n/TASKNAME d/DESCRIPTION D/DEADLINE [t/TAGS]…​ [E/EFFORT]…​`

* Event: `add n/TASKNAME d/DESCRIPTION F/FROMDATE T/TODATE [t/TAGS]…​ [E/EFFORT]…​`

You can add multiple tasks with the same parameters except for name with this command:`add n/TASKNAME1 n/TASKNAME2 d/DESCRIPTION [t/TAGS]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can add multiple Events and Deadlines as well! However, they have to share the same timings.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `add n/Read Book d/Make sure to take notes t/Leisure`
* `add n/Return Book d/NUS library t/Urgent D/2023-01-01 1800`

### 2.3 Listing all tasks : `list`
:speech_balloon: PRO TIP: Some commands (e.g. find) will trigger the UI to display a subset of tasks. Use `list` to return back to the original list.

Shows a list of all tasks in the address book.

Format: `list`

### 2.4 Editing a task : `edit`

Edits an existing task in the address book. 

One parameter field **must** be supplied in the argument.

Format: `edit INDEX [n/TASKNAME] [d/DESCRIPTION] [E/EFFORT]…​ [t/TAG]…​`

* Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
* You can remove all the task’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 t/CS2102 t/URGENT` Edits the tags of the first task to now be `CS2102` and `URGENT`
*  `edit 2 n/CS2102 Finals t/` Edits the name of the 2nd task to be `CS2102 Finals` and clears all existing tags.

### 2.5 Locating tasks by name: `find`

Find tasks whose attribute best match the user input string.

Format: `find n/NAME` OR `find d/DESCRIPTION` OR `find t/TAG...`

* The search is case-insensitive. e.g `book` will match `Book`
* Use only 1 attribute at a time.
* Substrings will be matched e.g. `book` will match `Books`
* For names and descriptions, you may use the `all/` prefix to search for a task that contains all of your inputs
  * e.g. `find all/ n/do n/homework` will match a task with a name called "do math homework.
* For tags, if you do not specify the `all/` prefix, as long as one tag matches with one of the tags you are searching for, it will be considered matched.
However, adding `all/` means that a task which contains all your tag inputs will be displayed.
  * e.g. `find t/very urgent t/important` will match with tags `t/very very urgent t/math t/hard` since it has `very urgent`.
  * e.g. `find all/ t/very urgent t/important` will match with tags `t/very urgent t/important` since it has both tags.
* For deadlines, you can only use a valid date(without the time input) such as `2023-03-10` to search for deadlines on that day.
  * e.g. `find D/2023-03-10` will give you all the deadlines on 2023-01-01.
* For events, you may either use `F/` or `T/` prefix(without the time input as well) to search for event that starts or ends on a certain date.
  * e.g. `find F/2023-03-10` will give you all the events starting from 2023-03-10.
  * e.g. `find T/2023-03-10` will give you all the event ending on 2023-03-10.

Examples:
* `find n/book` returns `read book` and `return books`

### 2.6 Deleting a task : `delete`

Deletes the specified task from the address book.

Format: `delete INDEX(S)`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​
* If multiple indices are entered, they must be written in ascending order.
* The command will reject all specified indices if one of them is invalid.

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the address book.
* `find Betsy` followed by `delete 1` deletes the 1st task in the results of the `find` command.

### 2.7 Getting statistics : `stats`

Prints the top 10 tags (if applicable) and its corresponding number of occurrences in the tasks.

Format: `stats`

### 2.8 Sorting tasks : `sort`

Sorts the list using the following format:

* SimpleTask is listed above Deadline and Event.
* Deadline is  listed below SimpleTask and above Event.
* Event is  listed below SimpleTask and Event.
* When comparing 2 tasks of the same class:
    * SimpleTask
        * The task with lesser tags is listed above the task with more tags.
        * Else if both tasks have the same number of tags, the task with a smaller lexicographical name is listed above the other.
    * Deadline
        * The task with the earlier deadline is listed above the task with later deadline.
        * Else if both tasks have the same deadline, the task with lesser tags is listed above the task with more tags.
        * Else if both tasks have the same number of tags, the task with a smaller lexicographical name is listed above the other.
    * Event
        * The task with the earlier `from` attribute is listed above the task with a later `from` attribute.
        * Else if both task have the same `from` attribute, the task with the earlier `to` attribute is listed above the task with later `to` attribute.
        * Else if both task have the same `to` attribute, the task with lesser tags is listed above the task with more tags.
        * Else if both tasks have the same number of tags, the task with a smaller lexicgraphical name is listed above the other.


Format: `sort`

### 2.9 Get alerts : `alert [ALERT WINDOW]`

Displays in another window the tasks that fall within the window specified. If not supplied, assumed to be 24 hours.
On opening of app, the alert window will open to show tasks which have deadlines within the latest window specified.
Have to specify `ALERT WINDOW` in hours and only integers.

Examples:
- `alert` followed by `48` will show the alert window with all tasks which end within 48 hours.
- `alert` alone will show the alert window with all tasks which end within 24 hours.

### 2.10 Plan your month : `plan EFFORT`

Automatically plans your month depending on your ideal `EFFORT` level per day. The planner will make an effort to
keep as close to your effort level as possible, however, if it has to overload to complete tasks on time it will.
Overloading will also be spread out as evenly as possible.

In order of priority, the planner will prioritise `Events`, then `Deadlines`, then `SimpleTasks`

Examples:
- `plan 5` will plan your month according to an ideal effort level of 5 per day.

### 2.11 Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### 2.12 Exiting the program : `exit`

Exits the program.

Format: `exit`

## 3. Storage 

### 3.1 Saving the data

TaskBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 3.2 Editing the data file

TaskBook data are saved as a JSON file `[JAR file location]/data/taskbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, taskBook will discard all data and start with an empty data file at the next run.
</div>

### 3.3 Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 4. FAQ

### Q1 How do I transfer my data to another Computer?
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

### Q2 What is a valid Date?
**A**: A valid Date must be in the format of `YYYY-MM-DD HHMM` such as `2023-07-13 1800` (13 July 2023, 6PM). A valid Date must have a valid time. A minimum of 4 characters must be supplied and only a maximum of 4 character will be parsed
  e.g. `2023-07-13 180` is invalid and `2023-07-13 18000000000` is understood as `2023-07-13 1800`

### Q3 What is an effort level?
**A**: [_More details to come_]

### Q4 What is a valid Description?
**A**: A valid Description is a text input that has at least one character.



--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

| Action     | Format, Examples                                                                                                    |
|------------|---------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add n/TASKNAME d/DESCRIPTION [t/TAGS]…​ [E/EFFORT]` <br> e.g., `add n/read book d/Lord of the Flies t/leisure E/5` |
| **Clear**  | `clear`                                                                                                             |
| **Delete** | `delete INDEX(S)`<br> e.g., `delete 3`                                                                              |
| **Edit**   | `edit INDEX [n/TASKNAME] [d/DESCRIPTION] [E/EFFORT] [t/TAG]…​`<br> e.g.,`edit 2 n/study d/CS2103T`                  |
| **Find**   | `find n/NAME` or `find d/DESCRIPTION`<br> e.g., `find n/read book`                                                  |
| **List**   | `list`                                                                                                              |
| **Help**   | `help`                                                                                                              |
| **Stats**  | `stats`                                                                                                             |
| **sort**   | `sort`                                                                                                              |
| **alert**  | `alert ALERT_WINDOW`                                                                                                |
| **plan**   | `plan EFFORT`                                                                                                       |
