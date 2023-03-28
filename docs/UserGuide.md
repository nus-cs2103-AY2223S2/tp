---
layout: page
title: User Guide
---

## Introduction

Welcome to Vimification, the ultimate task tracker for Vim enthusiasts! If you are a student at NUS, you know how hectic it can get to manage different deadlines and schedules for different classes and modules. With VimPlanner, you can now manage your tasks and deadlines with ease, using the powerful and efficient Vim-like commands that you already know and love.

Vimification is designed to help you stay organized and productive by providing a simple and intuitive interface that allows you to keep track of all your tasks and deadlines in one place. Whether you are a seasoned Vim user or just starting out, you will find Vimification to be an easy and efficient way to manage your daily tasks.

With Vimification, you can quickly and easily create new tasks, set due dates, prioritize your work, and track your progress. Whether you are working on a group project, studying for an exam, or just trying to stay on top of your assignments, VimPlanner has everything you need to stay organized and focused.

So why waste time fumbling around with a mouse and keyboard? Try Vimification today and experience the power of Vim-like productivity for yourself!

## Purpose of User Guide

The purpose of this user guide is to provide you with a comprehensive resource that will guide you through the various features and functionality of the app, so that you can efficiently manage your tasks and deadlines using VimPlanner's powerful and intuitive interface.

The guide will walk you through the process of creating and managing tasks, setting due dates, prioritizing work, and tracking progress, among other topics. It will provide you with clear and concise instructions that are easy to follow, whether you are a new Vim user or an experienced Vim enthusiast.

Additionally, it will offer troubleshooting tips and solutions to common issues that you may encounter while using VimPlanner, and provide you with a comprehensive resource that will help you optimize your productivity and stay on top of your tasks and deadlines.

Overall, we hope to empower you to streamline your daily workflows and increase your productivity. So, let's get started!

---

## Installation

1. Download the latest `vimification.jar` from [here](https://github.com/AY2223S2-CS2103T-T15-3/tp/releases).

2. # Save the file in your intended folder.
   Vimification is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Vimification can get your task management tasks done faster than traditional GUI apps. However, for users that are not fast typers or are unfamiliar with vim, we also provide a Graphical User interface (GUI) to assist you.

## Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help](#viewing-help)
  - [Adding a todo task](#adding-a-todo-task)
  - [Adding a task with deadline](#adding-a-task-with-deadline)
  - [Deleting a task](#deleting-a-task)
  - [Exiting the program](#exiting-the-program)
  - [Saving the data](#saving-the-data)

---

## Quick Start

### For Windows

1. Ensure that you have Java `11` or above installed in your Computer.

2. Double-click the file to start the app.

3. The application should launch, and a GUI similar to the below should appear in a few seconds. <br>

### For Mac (M1 Chip)

1. Ensure that you have Java `11` Zulu SDK installed in your Computer.

2. Open your Mac Terminal, and `cd` into the folder you put the jar file in.

3. Run `java -jar vimification.jar`.

4. The application should launch, and a GUI similar to the below should appear in a few seconds. <br>
   <img class="ui-img" src="images/Ui.png" alt="Ui Image" width="1203" height="599" title="Overall User Interface">

## Using the Command-Line Interface (CLI)

=======

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `:i todo CS2130T UG` : Adds a todo task with description `CS2130T UG` to Vimification.

   - `:i deadline CS2130T tp /2023-04-01` : Adds a todo task with description `CS2130T tp` and deadline '2023-04-01' to Vimification.

   - `:d 2` : Deletes the 2nd task shown in the current list.

   - `:wq` : Exits the app.

2. Refer to the [Features](#features) below for details of each command.

---

## Navigating Vimification

Vimification is designed to mimic Vim as closely as possible. Navigate Vimification as you would in Vim by using `h`,`j,`,`k`,`l` to move to the left, down, up, right respectively.

## Using commands

Similar to Vim, you can bring up the command input by pressing the `:` key on your keyboard.
e.g:
<img class="ui-img" src="images/ug-images/showCommand/commandExample.png" alt="Example of Show Command Image" width="1203" height="599" title="How to bring up the command input">

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in UPPER_CASE are the parameters to be supprised by the users.<br>
  e.g. `:i todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `:i todo quiz`.

- Words in square brackets are compulsory parameters to be supplied by the user.<br>
  e.g. in `:i todo [DESCRIPTION]`, `DESCRIPTION` is a parameter must be provided.

- Items with `…`​ after them can be used multiple times including zero times.<br>
<!-- TODO: Improve clarity -->
  e.g. `[-t TAG]…​` can be used as ` ` (i.e. 0 times), `-t cs2103t`, `-t cs2103t -t groupwork` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `-p PRIORITY -t TAG`, `-t TAG -p PRIORITY` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `:help`

### Adding a todo task

Adds a to-do to Vimification.

Format: `:i todo [DESCRIPTION]`
| parameter | description | examples |
| ------------- | ----------------------------------------------- | ------------ |
| `description` | description of the task. | `CS2103T UG` |

Examples:

`:i todo CS2130T UG`

**After**
<img class="ui-img" src="images/ug-images/AddTaskCommandDemo.png" alt="Add Task Command Demo Image" width="1203" height="599" title="Add Task Command Demo">

### Adding a task with deadline

Adds a task with `deadline` to Vimification.

Format: `:i deadline [DESCRIPTION] /[DEADLINE]`

| parameter     | description                                     | examples     |
| ------------- | ----------------------------------------------- | ------------ |
| `description` | description of the task.                        | `CS2103T UG` |
| `deadline`    | The deadline of the task in `YYYY-MM-DD` format | `2023-03-31` |

Examples:

`:i deadline CS2130T v1.3 /2022-03-31`

**After**
![](images/ug-images/AddDeadlineCommandDemo.png)
<img class="ui-img" src="images/ug-images/AddDeadlineCommandDemo.png" alt="Add Deadline Command Demo Image" width="1203" height="599" title="Add Deadline Command Demo">

### Deleting a task

Deletes the specified person from the address book.

Format: `:d [TASK_INDEX]`

- Deletes the task at the specified `task_index`.
- The index refers to the index number shown in the displayed `TaskList`.
- The index **must be a positive integer**, i.e 1, 2, 3, …​
- The index must not exceed the number of tasks in `TaskList`, otherwise Vimification will show an error message.

Examples:

**Before**
<img class="ui-img" src="images/ug-images/deleteCommand/before.png" alt="Delete Command Before Image" width="1203" height="599" title="Before Delete Command">

**After**
<img class="ui-img" src="images/ug-images/deleteCommand/before.png" alt="Delete Command After Image" width="1203" height="599" title="After Delete Command">

### Exiting the program

Similar to Vim, we can use the `q` command with write `w` to exit the program.

Format: `:wq!`, `:q!`,,`:wq`,`:q`

Examples:
<img class="ui-img" src="images/ug-images/quitCommand/quitExample1.png" alt="Quit Example 1 Image" width="1203" height="599" title="Quit Example 1">

### Saving the data

Vimification data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Vimification data are saved as a JSON file `[JAR file location]/data/tasklist.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Vimification will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Vimification home folder.

---

<!-- TODO: Complete theis command summary box -->
## Command summary

| Action           | Format, Examples                                                                         |
| ---------------- | ---------------------------------------------------------------------------------------- | --- |
| **Add To-do**    | `:i todo [description]` <br> e.g., `i todo CS2103T UG`                                   |
| **Add Deadline** | `:i deadline [description] /[deadline]` <br> e.g., `i deadline CS2103T v1.3 /2022-03-31` |     |
| **Delete**       | `:d [index]`<br> e.g., `:d 3`                                                            |
| **Exit**         | `:wq!`, `:q!`,,`:wq`,`:q`                                                                |
| **Help**         | `:help`                                                                                  |
