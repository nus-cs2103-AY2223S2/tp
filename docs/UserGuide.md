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


<!-- TABLE OF CONTENTS -->
<details open>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#installation">Installation</a>
    </li>
    <li>
      <a href="#quick-start">Quick Start</a>
        <ul>
            <li><a href="#for-windows">For Windows</a></li>
            <li><a href="#for-mac-(M1-chip)">For Mac (M1 Chip)</a></li>
        </ul>
      </details>
    </li>
    <li>
      <a href="#commands">Commands</a>
      <details open>
        <summary>General Commands</summary>
            <ol>
                <li><a href="#exiting-the-program--exit">Exit NUSearch</a></li>
                <li><a href="#view-help--help">View Help Page</a></li>
            </ol>
        </details>
      <details open>
        <summary>Basic Commands</summary>
            <ol>
                <li><a href="#add-a-contact-add-">Add a contact</a></li>
                <li><a href="#clear-all-contacts--clear">Clear all contacts</a></li>
                <li><a href="#delete-a-contact--delete-">Delete a contact</a></li>
                <li><a href="#edit-a-contact--edit-">Edit a contact</a></li>
                <li><a href="#list-all-contacts--list">List all contacts</a></li>
                <li><a href="#undo-a-command--undo">Undo a command</a></li>
                <li><a href="#redo-a-command--redo">Redo a command</a></li>
            </ol>
        </details>
        <details open>
            <summary>Find Commands</summary>
                <ol>
                    <li><a href="#find-contacts-matching-all-keywords--find-">Find contacts matching ALL keywords</a></li>
                    <li><a href="#find-contacts-matching-any-keywords--find-wide-">Find contacts matching ANY keywords</a></li>
                    <li><a href="#find-contacts-by-tags-tag-">Find contacts by tags</a></li>
                </ol>
        </details>
        <details open>
        <summary>Favourite Commands</summary>
            <ol>
                <li><a href="#favourite-a-contact--fav-">Favourite a contact</a></li>
                <li><a href="#list-all-favourite-contacts--list-fav">List all favourite contacts</a></li>
                <li><a href="#unfavourite-a-contact--unfav-">Unfavourite a contact</a></li>
            </ol>
        </details>
      <details open>
        <summary>Copy Commands</summary>
            <ol>
                <li><a href="#copy-email-address--copy-email-">Copy a contact's email address</a></li>
                <li><a href="#copy-phone-number--copy-phone-">Copy a contact's phone number</a></li>
            </ol>
        </details>
    </li>
    <li>
        <a href="#data-matters">Data matters</a>
        <ul>
            <li><a href="#saving-the-data">Saving the data</a></li>
            <li><a href="#editing-the-data-file">Editing the data file</a></li>
        </ul>
    </li>
    <li>
        <a href="#frequently-asked-questions-faq">Frequently Asked Questions (FAQ)</a>
        <ul>
        </ul>
    </li>
    <li>
        <a href="#command-summary">Command Summary</a>
        <ul>
        </ul>
    </li>
  </ol>
</details>

---


## Installation 

1. Download the latest `Vimification.jar` from [here](https://github.com/AY2122S2-CS2103T-W11-4/tp/releases).


2. Save the file in your intended folder.


## Quick Start

### For Windows 

1. Ensure that you have Java `11` or above installed in your Computer.


2. Double-click the file to start the app.


3. The application should launch, and a GUI similar to the below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)


### For Mac (M1 Chip)

1. Ensure that you have Java `11` Zulu SDK installed in your Computer.


2. Open your Mac Terminal, and `cd` into the folder you put the jar file in.

3. Run `java -jar Vimification.jar`.

4. The application should launch, and a GUI similar to the below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)


## Using the Command-Line Interface (CLI)


---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding a to-do task

Adds a to-do to Vimification.

Format: `:i todo <description>`
| parameter | description | examples |
| ------------- | ----------------------------------------------- | ------------ |
| `description` | description of the task. | `CS2103T UG` |

Examples:

`:i todo CS2130T UG`
**Before**

**After**

`:i todo CS2103T DG`
**Before**

**After**

### Adding a task with deadline

Adds a task with `deadline` to Vimification.

Format: `:i deadline [description] /[deadline]`

| parameter     | description                                     | examples     |
| ------------- | ----------------------------------------------- | ------------ |
| `description` | description of the task.                        | `CS2103T UG` |
| `deadline`    | The deadline of the task in `YYYY-MM-DD` format | `2023-03-31` |

Examples:

`:i deadline CS2130T v1.3 /2022-03-31`
**Before**

**After**

### Deleting a task `:d`

Deletes the specified person from the address book.

Format: `:d [task_index]`

- Deletes the task at the specified `task_index`.
- The index refers to the index number shown in the displayed `TaskList`.
- The index **must be a positive integer**, i.e 1, 2, 3, …​
- The index must not exceed the number of tasks in `TaskList`, otherwise Vimification will show an error message.

Examples:

**Before**
![](images/ug-images/deleteCommand/before.png)

**After**
![](images/ug-images/deleteCommand/after.png)

### Exiting the program

Similar to Vim, we can use the `q` command with write `w` to exit the program.

Format: `:wq!`, `:q!`,,`:wq`,`:q`

Examples:

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

## Command summary

| Action           | Format, Examples                                                                         |
| ---------------- | ---------------------------------------------------------------------------------------- | --- |
| **Add To-do**    | `:i todo [description]` <br> e.g., `i todo CS2103T UG`                                   |
| **Add Deadline** | `:i deadline [description] /[deadline]` <br> e.g., `i deadline CS2103T v1.3 /2022-03-31` |     |
| **Delete**       | `:d [index]`<br> e.g., `:d 3`                                                            |
| **Exit**         | `:wq!`, `:q!`,,`:wq`,`:q`                                                                |
| **Help**         | `help`                                                                                   |
