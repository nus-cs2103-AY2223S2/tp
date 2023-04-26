---
layout: page
title: User Guide
---
<a id="top"></a>
OfficeConnect is a task management tool designed specifically for managerial role personnels based in Singapore.

As managers in the current office environment, it is often not easy to grasp the workload of subordinates. This may result in:
* work overload among subordinates
* difficulties in coordinating tasks with a large number of employees

OfficeConnect offers a solution to these problems by providing better visibility into subordinates’ workloads, allowing
managers to efficiently delegate tasks in an organised manner. 

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 1. Getting started

#### 1.1 Accessing the app

1. For **Windows** users, ensure you have Java **11** or above installed in your Computer.  For **MacOS** users, ensure you have **OpenJDK Runtime Environment Zulu11.60+19-CA (build 11.0.17+8-LTS)** installed in your computer.

2. Download the latest **officeconnect.jar** from [OfficeConnect Release Page](https://github.com/AY2223S2-CS2103-F10-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your OfficeConnect.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar officeconnect.jar` command to run the application. 

5. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.
   ![startup_whitebackground.jpg](images/product-screenshots/startup_whitebackground.jpg)
    <p align="center">
    <em>Landing Page</em>
    </p><br>

6. If you're a new user, we recommend checking out our [Tutorial](#2-tutorial) section to get yourself familiar with the
   commands. Otherwise, refer to the [Features](#3-features) below to learn more about each command in detail.

#### 1.2 Prefixes and parameters used in commands
Below are the prefixes and parameters we use in commands.

| Prefix     | Parameter    | Parameter Meaning         | Example Usage              | Input Restrictions                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                |
|------------|--------------|---------------------------|----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **n/**     | NAME         | Name of person            | n/Peter                    | NAME given to persons must be unique, should only contain alphanumeric characters and spaces, and should not be blank.                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **p/**     | PHONE_NUMBER | Phone number of person    | p/94738484                 | PHONE_NUMBER should only contain numbers, should begin with 6, 8 or 9, should be 8 digits long (without spaces) and should not be blank. It is also assumed the country code (e.g +65) is not necessary.                                                                                                                                                                                                                                                                                                                                                                                          |
| **e/**     | EMAIL        | Email of person           | e/nancy@gmail.com          | EMAIL should be of the format local-part@domain and should not be blank. The local-part should only contain alphanumeric characters and at most one of these special characters, excluding the parentheses, (+_.-). The local-part may not start or end with any special characters. The domain name is made up of domain labels separated by periods. The domain name must end with a domain label at least 2 characters long, have each domain label start and end with alphanumeric characters and have each domain label consist of alphanumeric characters, separated only by hyphens, if any. |
| **a/**     | ADDRESS      | Address of person         | a/Sims Avenue 6            | ADDRESS can take any values, and it should not be blank.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **tag/**   | TAG          | Tags given to person      | tag/Logistics              | TAG should be alphanumeric and should not be blank. It is case insensitive and will be converted to lower case.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| **t/**     | TITLE        | Title of task             | t/Work on Project X        | TITLE given to tasks must be unique, should only contain alphanumeric characters and spaces, and should not be blank.                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |                                                                         
| **c/**     | CONTENT      | Content of task           | c/Complete slides for Mr Y | CONTENT can take any values, and it should not be blank.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **st/**    | STATUS       | Completion status of task | st/true                    | STATUS only takes true or false values, and should not be blank.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| **dl/**    | DEADLINE     | Deadline set for task     | dl/2023-05-23 20:00:00     | DEADLINE format is given by yyyy-mm-dd HH:MM:SS, time(HH:MM:SS) is optional but cannot be incomplete (i.e if time is included, it must follow HH:MM:SS format). If time is not included, the default time of 00:00:00 will be used. There must be trailing zeros for digits lesser than 10. It should not be blank.                                                                                                                                                                                                                                                                               |
| **ti/**    | INDEX        | Index of task             | ti/2                       | INDEX should be a valid integer, should be more than 0 and should follow the index displayed in displayed task list. It should not be blank.                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| **pi/**    | INDEX        | Index of person           | pi/4                       | INDEX should be a valid integer, should be more than 0 and should follow the index displayed in displayed person list. It should not be blank.                                                                                                                                                                                                                                                                                                                                                                                                                                                    |

- After entering a prefix and its parameter, there should be a space before the next prefix and its parameter.
  (e.g `addp n/Peterp/95748483e/pter@gmail.coma/Sims Drive 6` is an invalid command format. The valid command format should be `addp n/Peter p/95748483 e/pter@gmail.com a/Sims Drive 6`)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 2. Tutorial

Welcome to OfficeConnect! 

This section is designed specifically for newcomers who are looking to get started with our platform. 
Here, you'll find all the essential information you need to start using OfficeConnect and make the most out of 
its features. So without any further ado, let's dive in!

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
OfficeConnect comes with preloaded data that aims to help you get familiar with the platform when you first launch it.
</div>

Let's first look at how you can add a new employee's contact to OfficeConnect. Enter this into the command line: <br> 
`addp n/Johnny Ackles p/95967755 e/jensona@example.com a/512, Mary Streeth #01-01` <br>
This command adds a person named `Johnny Ackles` who lives at `512, Mary Streeth #01-01` whose phone number is `95967755`
and email address is`jensona@example.com`. If you scroll down the contact list, you will see that the new contact is
added.
![add_whitebackground.jpg](images%2Fproduct-screenshots%2Fadd_whitebackground.jpg)
<p align="center">
<em>New contact added!</em>
</p>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
With OfficeConnect, you can experience a real-time search functionality where the search results update dynamically
as you type into the input field.
</div>

Suppose now you want to find Alice's details and her assigned task. Simply type `findp Alice` and you
should be able to see the list being updated as such:
![find_whitebackground.jpg](images%2Fproduct-screenshots%2Ffind_whitebackground.jpg)
<p align="center">
<em>Alice found not slacking;)</em>
</p>

<div style="page-break-after: always;"></div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You may notice that there are progress bars of different 
   <span style="color:red">**c**</span><span style="color:orange">**o**</span><span style="color:yellow">**l**</span><span style="color:green">**o**</span><span style="color:blue">**r**</span><span style="color:violet">**s**</span> within each task, as well as circles besides each person with a percentage(%) written below it. Let's look at what these colors and circle mean! Let's see an example with all the different types of progress bars below. <br>
   ![image](images/product-screenshots/color_bars.png)
<br>
<br>- The <span style="color:green">**green**</span> colored progress bar indicates that the task is done, be it before or after the deadline.
<br>- The <span style="color:red">**red**</span> colored progress bar indicates that the task is not done, and that the deadline has passed.
<br>- The <span style="color:blue">**blue**</span> colored progress bar indicates that the task is not done, but the deadline has not passed.
<br>- The circles indicate the percentage of assigned tasks a person has completed. In the example above, Alice Pauline has completed 50% of the tasks assigned to her! Meanwhile, those who either have no tasks assigned or have completed all tasks assigned will have a green circle with the word "done" below!
</div>

<div style="page-break-after: always;"></div>

If you find yourself forgetting some commands over time, there's no need to worry. Memory leak happens to the best of 
us. You can simply access the help menu using a simple command: `help` to refresh your memory or even 
learn more about new features. 
![help_whitebackground.jpg](images%2Fproduct-screenshots%2Fhelp_whitebackground.jpg)
<p align="center">
<em>Help is on the way!</em>
</p>

----------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 3. Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:** <br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user. Refer to the [[Prefix and parameters](#12-prefixes-used-in-commands)] section to recap the meaning of these parameters and how they should be used!<br>
  e.g. in `addp n/NAME`, `NAME` is a parameter which can be used as `addp n/John Doe`.

* Parameters in square brackets are optional, while those not in square brackets are compulsory.<br>
  e.g. `n/NAME [tag/TAG]` can be used as `n/John Doe tag/logistics` or as `n/John Doe`.

* For any command, all prefixes of **optional AND compulsory** parameters cannot be used as an input in **ANY** parameter of the **same** command.<br>
  e.g In `addt t/TITLE c/CONTENT st/STATUS [dl/DEADLINE]`, `CONTENT` cannot be `complete presentation t/by today` as `t/` is a prefix of a compulsory parameter.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[tag/TAG]…` can be used as (i.e. 0 times), `tag/logistics`, `tag/logistics tag/marketing` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/94392211 p/98760021`, only `p/98760021` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `listp`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For specific constraints of each parameter, refer back to the [[Prefix and parameters]((#12-prefixes-used-in-commands))] section.

* To differentiate between commands intended for a specific person or task, a tag (-t or -p) is added to the end of the keyword.
</div>

### 3.1 Utility commands

#### 3.1.1 Quickstart Guide: `quickstart`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The keyboard shortcut for the quickstart function is assigned to the F5 key.
</div>

Gives you a simple and brief initial run-through of the OfficeConnect app.

If this is your first time using OfficeConnect, this window will be the first window to pop up. After being closed by the user, it is reaccesible by the calling the command `quickstart`.

Format: `quickstart`

![quickstart_whitebackground.jpg](images%2Fproduct-screenshots%2Fquickstart_whitebackground.jpg)
<p align="center">
<em>Virtual guide to app mastery</em>
</p>

<div style="page-break-after: always;"></div>

#### 3.1.2 Viewing help : `help`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The keyboard shortcut for the help function is assigned to the F4 key.
</div>

Displays a comprehensive window detailing the outline of commands executable by user.

Presents a hierarchical view which lists the different available commands, along with the description of each command.

Format: `help`

![help_whitebackground.jpg](images%2Fproduct-screenshots%2Fhelp_whitebackground.jpg)
<p align="center">
<em>Assistance at your fingertips</em>
</p>

### 3.2 Person Commands

#### 3.2.1 Adding a Person: `addp`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0).
</div>

Adds a person to OfficeConnect.

Format: `addp n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tag/TAG]…`

Examples:
* `addp n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `addp n/Betsy Crowe tag/logistics e/betsycrowe@example.com a/Newgate Prison p/98776544 tag/marketing`

#### 3.2.2 Deleting a Person : `deletep`

Deletes the specified person from OfficeConnect.

Format: `deletep INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `listp` followed by `deletep 2` deletes the 2nd person in the address book.
* `findp Betsy` followed by `deletep 1` deletes the 1st person in the results of the `findp` command.

#### 3.2.3 Editing a Person : `editp`

Edits an existing person in OfficeConnect.

Format: `editp INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tag/TAG]…`

* Edits the person at the specified `INDEX`. 
* The index refers to the index number shown in the displayed person list. 
* The index **must be a positive integer** 1, 2, 3, …
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e. adding of tags is not cumulative.
* You can remove all the person’s tags by typing `tag/` without specifying any tags after it.

Examples:
*  `editp 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `editp 2 n/Betsy Crower tag/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

#### 3.2.4 Listing all Persons : `listp`

Displays a list of all persons in OfficeConnect.

Format: `listp`

#### 3.2.5 Locating Persons by Name: `findp`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

Upon entering the `findp` command, all persons and tasks would be listed together with the message "Listed all persons and tasks".
</div>

Finds persons whose name contains any of the given keywords.

Format: `findp KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`.
* Does not require full word to match e.g. `Han` will match `Hans`.
* Persons whose name contains the input will be returned.
  e.g. `Han` will return `Han Gruber`, `Han Sum`.
* Input is order-sensitive. e.g. `Han S` will match `Han Sum` but will not match `Sum Han`.

Examples:
* `findp John` returns `john` and `John Doe`.
* `findp alice` returns `Alice Pauline`.<br>
![find_whitebackground.jpg](images%2Fproduct-screenshots%2Ffind_whitebackground.jpg)
<p align="center">
<em>Find Alice's assigned tasks</em>
</p>

### 3.3 Task Commands


####  3.3.1 Adding a Task: `addt`

Adds a task to OfficeConnect. 

Format: `addt t/TITLE c/CONTENT st/STATUS [dl/DEADLINE]`

* Each task contains a creation date, which is the date and time the task is added into OfficeConnect.
* The DEADLINE set for tasks must not be before the creation date of the task (i.e you cannot set deadlines in the past).

Examples:
- `addt t/Complete slides c/Finish slides for meeting st/false dl/2024-03-15 20:02:01` 

#### 3.3.2 Deleting a Task: `deletet`

Deletes the specified task from OfficeConnect.

Format: `deletet INDEX`

* Deletes the task at the specified INDEX.
* The INDEX refers to the INDEX shown in the displayed task list.
* The INDEX **must be a positive integer** 1, 2, 3, ...

Examples:
- `listt` followed by `deletet 2` deletes the 2nd task in the task list.
- `findt book` followed by `deletet 1` deletes the 1st task in the results of the `findt` command.

#### 3.3.3 Editing a Task: `editt`

Edits the specified task from OfficeConnect.

Format: `editt INDEX [t/TITLE] [c/CONTENT] [st/STATUS] [dl/DEADLINE]`

* Edits the task at the specified `INDEX`. 
* The index refers to the index shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
- `listt` followed by `editt 2 t/Submit report` edits the title of the 2nd task in the task list.
- `findt book` followed by `editt 1 c/Claim $200 from Bob` edits the content of the 1st task in the results of the `findt` command.

#### 3.3.4 Listing all Tasks: `listt`

Displays a list of all tasks in OfficeConnect.

Format: `listt`

#### 3.3.5 Locating Tasks by Title: `findt`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**

Upon entering the `findt` command, all persons and tasks would be listed together with the message "Listed all persons and tasks".
</div>

Finds persons whose name contains any of the given keywords.

Finds the task based on given keyword.

Format: `findt KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `complete` will match `Complete`.
* Do not require full word to match e.g. `Complete` will match `Complete X`.
* Tasks whose title contains the input will be returned.
  e.g. `Complete` will return `Complete X`, `Complete Y`.
* Input is order-sensitive. e.g. `Complete X` will match `Complete X` but will not match `X Complete`.

Examples:
- `findt complete project`

#### 3.3.6 Marking a Task as Completed: `mark`

Marks an existing task in OfficeConnect.

Format: `mark INDEX`

* Changes the status of the task at the specified `INDEX` to completed.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `mark 2` marks task 2 as completed.

#### 3.3.7 Unmarking a Task as not Completed: `unmark`

Unmarks a task in OfficeConnect

Format: `unmark INDEX`

* Changes the status of the task at the specified `INDEX` to uncompleted.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `unmark 1` unmarks task 1 as not completed yet.

### 3.4 Assignment Commands

#### 3.4.1 Assigning a Task to a Person: `assign`

Assigns an existing task to an existing person in OfficeConnect.

Format: `assign ti/INDEX pi/INDEX`

* Assigns the task at specified `INDEX` to the person at specified `INDEX`.
* The index refers to the index number shown in the displayed person/task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `assign ti/ 2 pi/ 3` assigns task 2 to person 3.

#### 3.4.2 Removing a Task Assignment from a Person: `unassign`

Removes the assignment of an existing task from an existing person in OfficeConnect.

Format: `unassign ti/INDEX pi/INDEX`

* Remove assignment of the task at specified `INDEX` from the person at specified `INDEX`.
* The index refers to the index number shown in the displayed person/task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
- `unassign ti/ 2 pi/ 3` unassigns task 2 from person 3.

### 3.5 Filter Commands

#### 3.5.1 Listing all Persons and Tasks: `listall`

Displays a list of all persons and a list of all tasks in OfficeConnect.

Format: `listall`

#### 3.5.2 Viewing Assigned Persons: `viewassignedp`

Displays a list of all persons who have been assigned to any task.

Format: `viewassignedp`

#### 3.5.3 Viewing Assigned Tasks: `viewassignedt`

Displays a list of all tasks that have been assigned to any person.

Format: `viewassignedt`

#### 3.5.4 Viewing Unassigned Persons: `viewunassignedp`

Displays a list of all persons who have not been assigned to any task.

Format: `viewunassignedp`


#### 3.5.5 Viewing Unassigned Tasks: `viewunassignedt`

Displays a list of all tasks that have not been assigned to any person.

Format: `viewunassignedt`


#### 3.5.6 Viewing Assigned Persons and Tasks: `viewassignedall`

Displays a list of all persons who have been assigned to any task and a list of all tasks that have been 
assigned to any person.

Format: `viewassignedall`

<div style="page-break-after: always;"></div>

#### 3.5.7 Viewing Unassigned Persons and Tasks: `viewunassignedall`

Displays a list of all persons who have not been assigned to any task and a list of all tasks that have not been 
assigned to any person.

Format: `viewunassignedall`

#### 3.5.8 Filtering Persons: `filterp`

Displays a list of all persons with the assigned tag. Only ONE tag can be keyed as input.

Format: `filterp tag/TAG`

Examples: `filterp tag/Logistics` shows all persons with the Logistics tag.

#### 3.5.9 Viewing a Person : `pi`

Shows a list of tasks assigned to the person at the specified index.

Format: `pi INDEX`

* Views the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
- `pi 2` displays person 2 and all tasks assigned to that person.

#### 3.5.10 Viewing a Task : `ti`

Shows a list of persons assigned to the task at the specified index.

Format: `ti INDEX`

* Views the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Example:
- `ti 1` displays task 1 and all persons assigned to that task.

<div style="page-break-after: always;"></div>

### 3.6 Others

#### 3.6.1 Exiting the program : `exit`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The keyboard shortcut for the exit function is assigned to the F1 key.
</div>

Exits the program.

Format: `exit`

#### 3.6.2 Saving the data

All OfficeConnect data is saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

#### 3.6.3 Editing the data file

Data for OfficeConnect is stored in JSON format within the `[JAR file location]/data/` directory. 
Please do not tamper with the data as it might cause corruption of the data. 

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, OfficeConnect will discard all data and start with an empty data file at the next run.
</div>

<div style="page-break-after: always;"></div>

#### 3.6.4 Light theme support
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The keyboard shortcut for the light mode function is assigned to the F2 key.
</div>

Sets the overall theme of OfficeConnect to light mode. This mode is chosen as the default theme.

![startup_whitebackground.jpg](images%2Fproduct-screenshots%2Fstartup_whitebackground.jpg)
<p align="center">
<em>The Days</em>
</p>

<div style="page-break-after: always;"></div>

#### 3.6.5  Dark theme support
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The keyboard shortcut for the dark mode function is assigned to the F3 key.
</div>

Sets the overall theme of OfficeConnect to dark mode.

![darktheme_whitebackground.jpg](images%2Fproduct-screenshots%2Fdarktheme_whitebackground.jpg)
<p align="center">
<em>The Nights</em>
</p>

### 3.7 Archiving data files `[coming in v1.5]`

_Details coming soon ..._

### 3.8 Clearing data files `[coming in v1.5]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 4. Frequently Asked Questions (FAQ)

1. **What is OfficeConnect?**
   OfficeConnect is a productivity app designed to help you manage tasks, persons, and assignments more efficiently. It offers a variety of features to improve organization and collaboration within your team.

2. **How can I view all tasks and persons in OfficeConnect?**
   OfficeConnect provides various filter and list commands to display tasks and persons based on different criteria, such as assigned, unassigned, or by specific tags.

3. **Can I edit task and person details after they have been added?**
   Yes, OfficeConnect allows you to edit the details of tasks and person after they have been added to the system. This helps to ensure that your information is always up-to-date and accurate.

4. **How do I assign a task to a person?**
   You can assign tasks to persons using the assignment commands. This feature helps to distribute work efficiently and track the progress of each task.

5. **What if I accidentally delete or modify data in the OfficeConnect data file?**
   Please do not edit the data file, as any changes to the data file might cause data corruption and lead to the app malfunctioning. If the data file becomes corrupted, OfficeConnect will start with an empty data file on the next run.

6. **Does OfficeConnect support different themes?**
   Yes, OfficeConnect offers both light and dark themes for your preference. You can easily switch between themes using the respective keyboard shortcuts.

7. **How do I transfer my OfficeConnect data to another computer?**
   To transfer your data to another computer, first, install the app on the new computer. Next, replace the empty data file created on the new computer with the data file from your previous OfficeConnect data folder.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## 5. Command summary

| Action                                                                  | Format, Examples                                                                                                                                                                |
|-------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Add Person](#321-adding-a-person-addp)                                 | `addp n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [tag/TAG]…` <br> e.g., `addp n/James Ho p/98774488 e/jamesho@example.com a/123, Clementi Rd, 1234665 tag/logistics tag/marketing` |
| [Add Task](#331-adding-a-task-addt)                                     | `addt t/TITLE c/CONTENT st/STATUS [dl/DEADLINE]` <br> e.g., `addt t/Draft proposal c/Complete proposal by 1st March st/false dl/2024-01-03 23:02:03`                            |
| [Assign](#341-assigning-a-task-to-a-person-assign)                      | `assign ti/INDEX pi/INDEX`<br>e.g. `assign ti/1 pi/2`                                                                                                                           |
| [Delete Person](#322-deleting-a-person--deletep)                        | `deletep INDEX`<br> e.g., `deletep 3`                                                                                                                                           |
| [Delete Task](#332-deleting-a-task-deletet)                             | `deletet INDEX`<br> e.g. `deletet 2`                                                                                                                                            |
| [Edit Person](#323-editing-a-person--editp)                             | `editp INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [tag/TAG]…`<br> e.g.,`editp 2 n/James Lee e/jameslee@example.com`                                                  |
| [Edit Tasks](#333-editing-a-task-editt)                                 | `editt INDEX [t/TITLE] [c/CONTENT] [st/STATUS] [dl/DEADLINE]`<br> e.g.,`editt 2 t/Submit report st/true`                                                                        |
| [Exit](#361-exiting-the-program--exit)                                  | `exit`                                                                                                                                                                          |
| [Filter Person](#358-filter-persons-filterp)                            | `filterp tag/TAG`<br> e.g.,`filterp tag/Logistics`                                                                                                                              |
| [Find Person](#325-locating-persons-by-name-findp)                      | `findp NAME`<br> e.g., `findp James Jake`                                                                                                                                       |
| [Find Task](#335-locating-tasks-by-title-findt)                         | `findt TITLE`<br> e.g., `findt CS2103 TP`                                                                                                                                       |
| [Help](#312-viewing-help--help)                                         | `help`                                                                                                                                                                          |
| [List All](#351-list-all-persons-and-tasks-listall)                     | `listall`                                                                                                                                                                       |
| [List Persons](#324-listing-all-persons--listp)                         | `listp`                                                                                                                                                                         |
| [List Tasks](#334-listing-all-tasks-listt)                              | `listt`                                                                                                                                                                         |
| [Mark Task](#336-marking-a-task-as-completed-mark)                      | `mark INDEX`<br> e.g. `mark 3`                                                                                                                                                  |
| [Quick Start](#311-quickstart-guide-quickstart)                         | `quickstart`                                                                                                                                                                    |
| [Unassign](#342-remove-a-task-assignment-from-a-person-unassign)        | `unassign pi/INDEX ti/INDEX`<br> e.g. `unassign pi/1 ti/3`                                                                                                                      |
| [Unmark Task](#337-unmarking-a-task-unmark)                             | `unmark INDEX` <br> e.g. `unmark 2`                                                                                                                                             |
| [View Assigned All](#356-view-assigned-person-and-task-viewassignedall) | `viewassignedall`                                                                                                                                                               |
| [View Assigned Persons](#352-view-assigned-persons-viewassignedp)       | `viewassignedp`                                                                                                                                                                 |
| [View Assigned Tasks](#353-view-assigned-tasks-viewassignedt)           | `viewassignedt`                                                                                                                                                                 |
| [View Person](#359-viewing-a-person--pi)                                | `pi INDEX`<br/> e.g. `pi 2`                                                                                                                                                     |
| [View Task](#3510-viewing-a-task--ti)                                   | `ti INDEX`<br/> e.g. `ti 2`                                                                                                                                                     |
| [View Unassigned All](#357-view-unassigned-viewunassignedall)           | `viewunassignedall`                                                                                                                                                             |
| [View Unassigned Persons](#354-view-unassigned-persons-viewunassignedp) | `viewunassignedp`                                                                                                                                                               |
| [View Unassigned Tasks](#355-view-unassigned-tasks-viewunassignedt)     | `viewunassignedt`                                                                                                                                                               |

<a href="#top">Back to top</a>
