---
layout: page
title: User Guide
---

Pied Piper is a task management app to help project team leaders stay organized and focused on their priorities. With a clean and intuitive interface, this app will include features that will allow users to easily create tasks, assign them to team members, and set due dates. Whether you're managing personal projects or collaborating with a team, Pied Piper can help streamline your workflow and keep you on track.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `piedpiper.jar` from [here](https://github.com/AY2223S2-CS2103T-W15-3/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for Pied Piper.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar piedpiper.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)


1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>

* Words within `Curly Brackert {}` are must have parameters.

* Extraneous parameters for commands that do not take in parameters (such as `view`) will be ignored.<br>
  e.g. if the command specifies `view 123`, it will be interpreted as `view`.

</div>

### Creating a deadline: `deadline task`

Pied Piper creates a new deadline

Format: `deadline task/{TASK_NAME} by/{DD/MM/YYYY}`


Example:
* `deadline task/Complete Assignment by/12/09/2021`

### Assigning task to member: `assign`

Assign a task to a person

Format: `assign  t/{TASK_ID} n/{MEMBER_NAME}`

Examples:
*  `assign t/1 n/John Doe`


### Delete a person: `delete`

Deletes an existing person

Format: `delete {MEMBER_ID}`

Examples:
* `delete 1`

### Delete a task

Deletes an existing task

Format: `deletetask {TASK_ID}`

Examples:
* `deletetask 2`


### View tasks: `view`

Shows all tasks

Format: `view`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Pied Piper home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Deadline** | `deadline task/{TASK_NAME}  by/{DD/MM/YYYY}` <br> e.g., `deadline task/Complete Assignment by/12/09/2021`
**Role** | `role n/{MEMBER_NAME} r/{ROLE}` <br> e.g., `role n/John Doe r/Leader`
**Assign** | `assign  t/{TASK_ID} n/{MEMBER_NAME}`<br> e.g., `assign t/1 n/John Doe`
**Delete person** | `delete {MEMBER_ID}`<br> e.g.,`delete 1`
**Delete task** | `deletetask  {TASK_ID}`<br> e.g.,`deletetask 2`
**View** | `view`

