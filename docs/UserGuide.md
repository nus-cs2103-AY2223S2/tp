---
layout: page
title: User Guide
---

Teaching Assistant Assistant (TAA) is a **desktop app for managing teaching assistant activities, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TAA can get your teaching assistant tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start
_Details coming soon ..._

## Feature summary
1. [Attendance](#Attendance)
    * mark
    * unmark
    * exit
1. [Assignments](#Assignments)
1. [Class List](#Class-List)
1. [CSV Parser](#csv-parser)

--------------------------------------------------------------------------------------------------------------------

# Features

## Attendance
Directs you to the attendance system. Within the attendance system, you can call:
* mark
* unmark
* exit

### Mark attendance: `mark`
Marks the attendance of a student for that week<br>
Format: `mark {student_name} {week_number}`<br>
Examples
* `mark john 1`
* `mark james 1`

### Unmark attendance: `unmark`
Unmarks the attendance of a student for that week<br>
Format: `unmark {student_name} {week_number}`<br>
Examples
* `unmark john 1`
* `unmark james 1`

### Exit attendance system: `exit`
Exits the attendance system.<br>
Format: `exit`

## Assignments

## Class List
`class_list`

Directs you to the class list system.
In this system, you can call the following commands:

- Create class list: `create`
- (Random) Groupings: `rand_grp`
- Adding a student: `add`
- Deleting a student: `delete`
- Listing all students: `list`
- Find student: `find`

### Create a class list: `create` [coming soon]
Creates a class list to store the information about a group of students.

Format: `create LIST_NAME [STUDENT_NAMES]`
- The argument `LIST_NAME` should be the name of the new class list
The argument `[STUDENT_NAMES]` should consist of a sequence of student names, separated by commas.


Examples:
- `create cs2103t-t14 Alex, John, Bonnie, Clyde` creates a class list of size 4 with 4 students: Alex, John, Bonnie and Clyde.
- `create cs6244` creates an empty class list.


### Random grouping of students: `rand_grp` [coming soon]
Forms random groups of a specified size within a given class list.

Format: `rand_grp CLASS_LIST GROUP_SIZE`
- The argument `CLASS_LIST` should be the name of a given class list
- The search for `CLASS_LIST` is case-insensitive. e.g. cs2103T will match CS2103T
- The argument `GROUP_SIZE` will determine the size of the groups to be formed.


Examples:
- `rand_grp cs2103t-t14 2` returns: Group 1: Alex, John; Group 2: Bonnie, Clyde
- `rand_grp cs2103t-t14 3` returns: Group 1: Alex, John, Clyde; Group 2: Bonnie

### Adding a student: `add` [coming soon]
Adds a student to a given class list.

Format: `add STUDENT_NAME CLASS_LIST`
The argument `STUDENT_NAME` should be the name of the student to be added
The argument `CLASS_LIST` should be the name of a given class list
The search for `CLASS_LIST` is case-insensitive. e.g. cs2103T will match CS2103T


Examples:
- `add Tom cs2103t-t14` adds Tom to the class list CS2103T-T14
- `add Harry cs6244` adds Harry to the class list CS6244


### Deleting a student: `delete` [coming soon]
Deletes a student from a given class list.

Format: `delete STUDENT_NAME CLASS_LIST`
- The argument `STUDENT_NAME` should be the name of the student to be deleted
- The search for `STUDENT_NAME` is case-insensitive. e.g. bOb will match BOB
- The argument `CLASS_LIST` should be the name of a given class list
- The search for `CLASS_LIST` is case-insensitive. e.g. cs2103T will match CS2103T


Examples:
- `delete Tom cs2103t-t14` removes Tom from the class list CS2103T-T14
- `delete Harry cs6244` removes Harry from the class list CS6244


## CSV Parser

--------------------------------------------------------------------------------------------------------------------

## FAQ

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

_Details coming soon ..._
