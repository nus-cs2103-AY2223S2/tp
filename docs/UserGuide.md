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
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Creating a group: `create`

Pied Piper creates a new group

Format: `create n/{GROUP_NAME}`


Example:
* `create n/CS2105TGroup5`

### Adding people to a group: `add`

Adds a person to a group with a role

Format: `add n/{NAME} i/{MEMBER_ID} r/{ROLE}`

Example:
* `add n/John Doe i/1001 r/Leader`

### Assigning task to person: `assign`

Assign a task to a person

Format: `assign n/{GROUP_NAME} t/TASK_NAME} i{MEMBER_ID}`

Examples:
*  `assign n/CS2103TGroup5 t/Create Github repository i/2`


### Delete a task: `delete`

deletes an existing task

Format: `delete n/{GROUP_NAME} t/{TASK_NAME} i/{MEMBER_ID}`

Examples:
* `delete n/T15 i/1`


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
**Create** | `create n/{GROUP_NAME}` <br> e.g., `create n/CS2103TGroup5`
**Add** | `add n/{NAME} i/{MEMBER_ID} r/{ROLE}` <br> e.g., `add n/John Doe i/1001 r/Leader`
**Assign** | `assign n/{GROUP_NAME} t/{TASK_NAME} i/{MEMBER_ID}`<br> e.g., `assign n/CS2103TGroup5 t/Create repository i/2`
**Delete** | `delete n/{GROUP_NAME} i/{TASK_INDEX}`<br> e.g.,`delete n/T15 i/1`
**View** | `view`

