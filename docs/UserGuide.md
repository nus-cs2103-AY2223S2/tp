---
layout: page
title: User Guide
---
<img src="./images/sprINT.png" />

* Table of Contents 
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **1. Introduction**

### 1.1 What is sprINT

Welcome to **sprINT's User Guide**. sprINT is a **desktop application** for managing internship applications,
optimized for use via a Command Line Interface (CLI) while incorporating the benefits of a Graphical User
Interface (GUI).

If you're a *fast typer*, sprINT is the perfect tool. The app is designed to maximise speed and efficiency,
making it a great asset in your internship application journey.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **2. Quick start**

Step 1: Ensure you have Java `11` or above installed in your Computer.

Step 2: Download the latest `sprint.jar` from [here](https://github.com/AY2223S2-CS2103T-T13-3/tp/releases/tag/v1.3).

Step 3: Copy the file to the folder you want to use as the _home folder_ for your sprINT application.

Step 4: Open a command terminal, enter `cd` to the folder you put the jar file in, and use the `java -jar sprINT.jar` 
command to run the application.<br>

A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data already.<br>
![Ui](images/Ui.png)

Step 5: Type the command in the command box and press Enter to execute it.
e.g. typing **`help`** and pressing Enter will open the help window.<br>
Some example commands you can try:

* `list` : Lists all applications.

* `add-app r/SWE Intern c/Goggle e/goggle_careers@gmail.com s/interested d/01-01-2023` : Adds an
  application for Software Engineer Intern position @ Goggle that I'm interested in. Deadline of application is 1st of
  January.

* `delete-app 3` : Deletes the 3rd application shown in the current list.

* `clear` : Deletes all application entries.

* `exit` : Exits the app.

Refer to the [Command summary](#5-command-summary) section for a complete list of commands.

Step 6: Refer to the [Features](#3-features) section for more details of each command.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **3. Features**

Before you begin reading, here are some useful notations that you should know.

**Tips**

Tips are useful suggestions that will help you become a seasoned sprINT user more quickly.

<div markdown="span" class="alert alert-primary">:bulb:
**Tip:** Tips are useful information. They can give you a better experience with sprINT.
</div>

**Notes**

Notes are important information that you should pay attention to when using sprINT.

<div markdown="span" class="alert alert-info">:information_source:
**Note:** Take notes when you see this icon.
</div>

**Caution**

Cautions are in place to warn you of potential pitfalls new users may encounter.

<div markdown="span" class="alert alert-warning">:exclamation:
**Caution:** Stop and read carefully when you see this!
</div>

<div style="page-break-after: always;"></div>
<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add c/COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add c/John Doe's Company`.

* Items in square brackets are optional.<br>
  e.g. `c/COMPANY_NAME [t/TAG]` can be used as `c/John Doe's Company t/highsalary` or as `c/John Doe's Company` only.

* Items that come with `(s)` means multiple parameters for the same prefix can be added, separated by space.<br>
  e.g. `[t/TAG(s)]` can be used as `t/creditBearing t/highSalary` or as `t/creditBearing` only.

* Items encapsulated within square brackets (`[]`) are optional arguments.<br>
  e.g. `[t/TAG]​` can be used as ` ` (i.e. 0 times), or `t/highSalary` (i.e. 1 time).

<div markdown="span" class="alert alert-warning">:exclamation:
**Caution:** Be aware of the difference between `(s)` and `[]`. The former allows for multiple arguments, the latter represents
an optional argument.
</div>

* Command words and prefixes are case-sensitive.<br>
  e.g. `UNDO` will not be recognised as a valid command for the undo operation. Similarly, `bY/` (instead of `by/`) 
  will not be recognised as a valid prefix for the `add-task` command. 

* Parameters can be in any order.<br>
  e.g. if the command specifies `r/ROLE c/COMPANY_NAME`, `c/COMPANY_NAME r/ROLE` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `r/SWE Intern r/Software Intern`, only `r/Software Intern` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### 3.1 Managing applications

#### 3.1.1 Adding an application : `add-app`

Adds an internship application to be tracked.

Format: `add-app r/ROLE c/COMPANY_NAME e/EMAIL s/STATUS [t/TAG(s)]​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Tag is an optional field when adding an application.
An application can have multiple tags (including 0).
</div>

Examples:
* `add-app r/SWE Intern c/Goggle e/goggle_careers@gmail.com s/interested t/creditBearing`
* `add-app r/Data Analyst Intern c/Bloomberg e/bloomberg_hires@bloomberg.com`

#### 3.1.2 Editing an application : `edit-app`

Edits an existing internship application to be tracked.

Format: `edit-app INDEX [r/ROLE] [c/COMPANY_NAME] [e/COMPANY EMAIL] [s/STATUS] [t/TAG(s)]​`

* Edits the internship application at the specified `INDEX`. The index refers to the index number shown in the displayed application list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing corresponding values of the tag field in the application will be removed. i.e. adding of tags is not cumulative.

<div markdown="span" class="alert alert-info">:information_source:
**Note:** This means that if an application has an existing `highSalary` tag. Executing command `edit-app 1 t/creditBearing` would remove the original
`highSalary` tag, replacing it with the `creditBearing` tag.
</div>

* You can remove all the application’s tags by typing `t/` without specifying any values after it.

Examples:
*  `edit-app 1 r/Cloud Engineer e/goggleHR@example.com` Edits the role and email address of the 1st application to be `Cloud Engineer` and `goggleHR@gmail.com` respectively.
*  `edit-app 2 s/Rejected t/` Edits the status of the 2nd application to be `Rejected` and clears all existing tags.

#### 3.1.3 Deleting an application : `delete-app`

Deletes the specified application from the internship book.

Format: `delete-app INDEX`

* Deletes the application at the specified `INDEX`.
* The index refers to the index number shown in the displayed application list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete-app 2` deletes the 2nd application in the internship book.
* `find Google` followed by `delete-app 1` deletes the 1st application shown in the results of the `find` command.

### 3.2 Managing application tasks

#### 3.2.1 Adding an application task : `add-task`

Adds an upcoming task to an existing application.

Format: `add-task d/DESCRIPTION by/DEADLINE`
* The deadline must be in **DD-MM-YYYY** format.

Example:
Suppose that you have recently applied to `Goggle` for their `Software Engineering Intern` role, and made an entry
for it in the internship book.
Then, `Goggle` reaches out to you for a technical interview on the 24th July 2023. You can choose to add this
as a task to the application entry you have created (suppose it is showing up as the first one in the list) by typing:
`add-task 1 d/Technical Interview by/24-07-2023`

#### 3.2.2 Editing an application task : `edit-task`

Edits an upcoming task for an application entry.

Format: `edit-task INDEX [d/DESCRIPTION] [by/DEADLINE]`

* Edits the internship application at the specified `INDEX`. The index refers to the index number shown in the displayed
  application list. The index **must be a positive integer** 1, 2, 3, …​
* Existing values will be updated to the input values.

Example:
`edit-task 1 d/Accept offer by/09-07-2023` Edits the description and deadline of the task for the 1st application to be
`Accept offer` and `09-07-2023` respectively.

#### 3.2.3 Deleting an application task : `delete-task`

Deletes the task of the specified application.

Format: `delete-task INDEX`

* Deletes the task of the application at the specified `INDEX`.
* The index refers to the index number shown in the displayed application list.
* The index **must be a positive integer** 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Deleting an application's task will not delete the underlying application entry itself.
Refer to the command delete-app if you wish to delete an application entry and all its information (including its task).
</div>

Examples:
* `list` followed by `delete-task 2` deletes the task for the 2nd application.
* `find Google` followed by `delete-task 1` deletes the task of the 1st application to be shown in the results of the `find` command.

### 3.3 Managing display for applications

#### 3.3.1 Listing all applications : `list`

Shows a list of all internship applications, in the order of when they are added. Application
entries that are added more recently will be shown on top.

Format: `list`

Example:
* `list` Returns the current list of internship applications tracked by sprINT.

#### 3.3.2 Finding applications : `find`

Finds internship applications with information containing any of the given keywords.

Format: `find keywords(s)` or `find [r/keyword(s)] [c/keyword(s)] [s/keyword(s)]`

* The search is case-insensitive. e.g. `goggle` will match `Goggle`.
* In `find keyword(s)`, when none of the prefixes is specified, the keyword(s) will be searched globally in all prefixes.
* In `find [r/keyword(s)] [c/keyword(s)] [s/keyword(s)]`, `r/`, `c/` and `s/` are prefixes that stand for `ROLE`, `COMPANY NAME` and `STATUS` respectively.
* When at least one prefix is provided, the keyword(s) is searched according to the information under that particular prefix.
* Only full words will be matched e.g. `Han` will not match `Hans` but `Goggle` will match with `Goggle LLC`.

Examples:
* `find Goggle` returns internship applications for `Goggle` and `Goggle LLC`.
* `find Goggle Mata` returns internship applications for `Goggle LLC`, `Mata Platforms`.<br>
* `find r/SWE Intern c/Mata s/Offered` returns internship application(s) for the role of `SWE Intern` at `Mata` that is of the status `Offered`.<br>

#### 3.3.3 Sorting applications : `sort`

Sorts internship applications in the order you want.

Format: `sort ORDER`: `sort alphabetical` or `sort deadline`

Currently, there are two orders you can choose:
1. `sort alphabetical` sorts applications by their company names in alphabetical order (from A to Z). Should
   there be multiple applications to the same company, they will be ranked alphabetically by their roles.
2. `sort deadline` shows applications with tasks (and therefore, deadlines) and sorts them by their task's deadline. 
Those with earlier deadlines will be higher up in the list.

### 3.4 Miscellaneous

#### 3.4.1 Viewing help : `help`

Displays a pop-up window with a summary of all the commands and their formats.

Format: `help`

#### 3.4.2 Undoing a command : `undo`

Undo the previous command that you have typed in.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can use this command if you have accidentally deleted an application entry or the entire internship book, and you wish to retrieve them back.
</div>

Format: `undo`

#### 3.4.3 Clearing all applications : `clear`

Clears all entries from the internship book.

Format: `clear`

<div markdown="span" class="alert alert-warning">:exclamation:
**Caution:** The `clear` command must be used with extreme caution, as it might potentially lead to highly undesirable outcomes.
If you accidentally issued the `clear` command, use the `undo` command to revert back to the previous state.
</div>

#### 3.4.4 Exiting the program : `exit`

Exits the program.
Alternatively, you can exit the program by clicking the top-right X button to close the window. sprINT updates
your data [periodically](#345-saving-the-data), so you don't have to worry about unsaved or possible loss of data when exiting the program.

Format: `exit`

#### 3.4.5 Saving the data

Data in the internship book are automatically saved in your local storage after any command that modifies the data. There is no need to save manually.

#### 3.4.6 Editing the data file

Data in the internship book are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update their data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, the internship book will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **4.FAQ**

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous internship book's home folder.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **5. Command Summary**

| Action                       | Format, Examples                                                                                                                                                 |
|------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add** <br/> Application    | `add-app r/ROLE c/COMPANY_NAME e/EMAIL s/STATUS [t/TAG(s)]​` <br> e.g. `add-app r/Teaching Assistant c/NUS SOC e/ta_portal@nus.edu.sg s/Offered t/creditBearing` |
| **Edit**<br/> Application    | `edit-app INDEX [r/ROLE] [c/COMPANY_NAME] [e/COMPANY EMAIL] [s/STATUS] [t/TAG(s)]` <br/> e.g. `edit-app 1 r/Cloud Engineer e/goggleHR@example.com`               |
| **Delete**<br/> Application  | `delete-app INDEX` <br/> e.g. `delete-app 1`                                                                                                                     |
| **Add** <br/> Task           | `add-task d/DESCRIPTION by/DEADLINE` <br> e.g. `add-task d/Technical Interview by/01-05-2023`                                                                    |
| **Edit** <br/> Task          | `edit-task INDEX [d/DESCRIPTION] [by/DEADLINE]` <br/> e.g. `edit-task 1 d/Accept offer by/09-07-2023`                                                            |
| **Delete** <br/> Task        | `delete-task INDEX` <br/> e.g. `delete-task 1`                                                                                                                   |
| **List**                     | `list`                                                                                                                                                           |
| **Find**                     | `find [search term]` <br/> e.g. `find Mata` <br/> `find [r/search term] [c/search term] [s/search term]` <br/> e.g. `find r/SWE Intern c/Mata s/Offered`         |
| **Sort**                     | `sort ORDER` <br/> e.g. `sort alphabetical` or `sort deadline`                                                                                                   |
| **Help**                     | `help`                                                                                                                                                           |
| **Undo**                     | `undo`                                                                                                                                                           |
| **Clear**                    | `clear`                                                                                                                                                          |
| **Exit**                     | `exit`                                                                                                                                                           |


<div style="page-break-after: always;"></div>