---
layout: page
title: User Guide
---

Pied Piper is a user-friendly task management application tailored for current and future university student leaders. 
The app helps them stay organised and prepare for real-world situations by helping them manage project groups, and 
simulate the real world review system. Key features include creating tasks, assigning tasks to team members, setting 
due dates, rating completed tasks out of 5, and providing feedback through comments. The ability to seamlessly toggle 
between a person list and task list also helps team leaders easily identify each team members' roles and their assigned 
task to complete. Ideal for personal projects and team collaboration, Pied Piper aims to improve productivity and equip 
university students with valuable skills for their future careers.

--------------------------------------------------------------------------------------------------------------------

* Table of Contents
  {:toc}

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

* Words in front of `/` are parameter prefixes that need to be included when inputting the parameters.<br>
e.g When adding a `todo` task, the format is `todo task/TASK_NAME`. If your task name is 'Homework', 
the full command will be `todo task/Homework`.

* As a result of the above, the character `/` cannot be used in any parameters.

* Words within `Curly Bracket {}` are must have parameters.

* Items in square brackets are optional.<br>
  e.g `a/{ADDRESS} [r/{ROLE}]` can be used as `a/John street, block 123 r/Member` or as `a/John street, block 123`.

* Extraneous parameters for commands that do not take in parameters (such as `view`) will be ignored.<br>
  e.g. if the command specifies `view 123`, it will be interpreted as `view`.

</div>

### Adding a person: `add`

Adds a person.

Format: `add n/{NAME} p/{PHONE} e/{EMAIL} a/{ADDRESS} [r/{ROLE}]`

Examples:
* `add n/John Doe p/98765432 e/johnd@nus.com a/John street, block 123`
* `add n/Jane Roe p/98123456 e/janer@nus.com a/Jane street, block 321 r/Member`


### Creating a regular task: `todo`

Creates a new task.

Format: `todo task/{TASK_NAME}`

Example:
* `todo task/Edit User Guide`


### Creating a task with a deadline: `deadline`

Creates a new deadline task.

Format: `deadline task/{TASK_NAME} by/{DD/MM/YYYY}`

Example:
* `deadline task/Complete Assignment by/12/09/2021`


### Creating a task that is an event: `event`

Creates a new event task.

Format: `event task/{TASK_NAME} from/{DD/MM/YYYY} to/{DD/MM/YYYY}`

Example:
* `event task/Manage Open House from/11/12/2023 to/16/12/2023`


### Commenting on a task: `comment`

Adds a comment to a task that exists in the task list. The task index follows <br>
The task list can be viewed by using the `view` command.

Format: `comment t/{TASK_INDEX} c/{COMMENTS} `

Example:
* `comment t/1 c/task was done well and on time`


### Editing a person: `edit`

Edits the properties of an existing person in the persons list.<br>
The person list can be viewed by using the `list` command.

Format: `edit INDEX [n/{NAME}] [p/{PHONE}] [e/{EMAIL}] [a/{ADDRESS}] [r/{ROLE}]`

Example:

* `edit 1 p/98761234 e/john@nus.com`

### Editing a task: `edittask`

Edits the details of the task identified by the index number used in the displayed task list. 
Existing values will be overwritten by the input values. The desired task type must be specified, and the date 
modifiers must match the format of the task type input.<br>
Todo tasks should not include a {DATE} field.<br>
The task list can be viewed using the `view` command.

Format: `edittask INDEX type/{TASK_TYPE} [task/{TASK_DESCRIPTION}] [c/{COMMENT}] [{DATE}]`

Note: 
* To denote todo, deadline and event tasks, parameters for `TASK_TYPE` are `T`, `D` and `E` respectively.
* When editing a task to change types, the {DATE} parameters are as follows:
  * `deadline`: `by/{DD/MM/YYYY}`
  * `event`: `from/{DD/MM/YYYY} to/{DD/MM/YYYY}`
* Editing a task removes the assigned member from the task.

Examples:
* Changing an existing task at index 1 to a `deadline` task, with a different name
  * `edittask 1 type/D n/Assignment 1 by/20/04/2023`
* Changing an existing task at index 2 to an `event` task, with the date changed
  * `edittask 2 type/E from/05/04/2023 to/10/04/2023`


### Assigning task to member: `assign`

Assign a task to a person. The indexes used follows the index on the task and person list respectively.<br>
The task list can be viewed using the `view` command, and the persons list can be viewed using the `list` command.

Note:
* For every task, only one person can be assigned to it.

Format: `assign  t/{TASK_INDEX} i/{MEMBER_INDEX}`

Examples:
*  `assign t/1 i/3`


### Marking a task: `mark`

Marks the target task as completed and give a score to the marked task.<br>
The task list can be viewed using the `view` command.

Format: `mark t/{TASK_INDEX} s/{PERFORMANCE_SCORE}`

Examples:
* `mark t/1 s/4`


### Unmarking a task: `unmark`

Sets the target task's status as incomplete and remove any given score.<br>
The task list can be viewed using the `view` command.

Format: `unmark t/{TASK_INDEX}`

Examples:
* `unmark t/1`


### Deleting a person: `delete`

Deletes an existing person at the given index.<br>
The task list can be viewed using the `view` command.

Format: `delete {MEMBER_INDEX}`

Examples:
* `delete 1`


### Deleting a task: `deletetask`

Deletes an existing task at the given index.<br>
The task list can be viewed using the `view` command.

Format: `deletetask {TASK_INDEX}`

Examples:
* `deletetask 2`


### Listing tasks: `list`

Shows all tasks.

Format: `list`


### Viewing persons: `view`

Shows all persons.

Format: `view`


### Viewing statistics: `review`

Shows task and score statistics of all persons in the persons list.

Format: `review`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Pied Piper home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/{NAME} p/{PHONE} e/{EMAIL} a/{ADDRESS} [r/{ROLE}]`<br> e.g., `add n/Jane Roe p/98123456 e/janer@nus.com a/Jane street, block 321 r/Member`
**Assign** | `assign  t/{TASK_ID} n/{MEMBER_NAME}`<br> e.g., `assign t/1 n/John Doe`
**Comment** | `comment t/{TASK_ID} c/{COMMENTS} ` <br> e.g., `comment t/1 c/task was done well and on time`
**Deadline** | `deadline task/{TASK_NAME}  by/{DD/MM/YYYY}` <br> e.g., `deadline task/Complete Assignment by/12/09/2021`
**Delete person** | `delete {MEMBER_ID}`<br> e.g., `delete 1`
**Delete task** | `deletetask  {TASK_ID}`<br> e.g., `deletetask 2`
**Edit person** | `edit INDEX [n/{NAME}] [p/{PHONE}] [e/{EMAIL}] [a/{ADDRESS}] [r/{ROLE}]` <br> e.g., `edit 1 p/98761234 e/john@nus.com`
**Edit task** | `edittask INDEX type/{TASK_TYPE} [task/{TASK_DESCRIPTION}] [{DATE}]` <br> e.g., `edittask 1 type/T`
**Event** | `event task/{TASK_NAME} from/{DD/MM/YYYY} to/{DD/MM/YYYY}` <br> e.g., `event task/Manage Open House from/11/12/2023 to 16/12/2023`
**List tasks** | `list`
**Mark** | `mark t/{TASK_ID} s/{PERFORMANCE_SCORE}` <br> e.g., `mark t/1 s/4`
**Todo** | `todo task/{TASK_NAME}` <br> e.g., `todo task/Edit User Guide`
**Unmark** | `unmark t/{TASK_ID}` <br> e.g., `unmark t/1`
**View persons** | `view`
**View statistics** | `review`
