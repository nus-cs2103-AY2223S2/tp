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
2. [Assignments](#Assignments)
   * add
   * delete
   * grade
   * ungrade
   * exit
3. [Class List](#Class-List)
   * create
   * rand_grp
   * add
   * delete
   * list
   * find
4. [CSV Parser](#csv-parser)
   * import
   * export

--------------------------------------------------------------------------------------------------------------------

# Features

## Attendance
Directs you to the attendance system. Within the attendance system, you can make the following calls:
* mark
* unmark

### Mark attendance: `mark`
Marks the attendance of a student for that week<br>
Format: `mark {student_number} w/{week_number}`<br>
Examples
* `markAtd 1 w/1`
* `markAtd 2 w/1`

### Unmark attendance: `unmark`
Unmarks the attendance of a student for that week<br>
Format: `unmark {student_number} w/{week_number}`<br>
Examples
* `unmarkAtd 1 w/1`
* `unmarkAtd 2 w/1`

## Assignments

Directs you to the assignment system. Within the assignment system, you can call:
* add
* delete
* grade
* ungrade
* list
* exit

### Add Assignment: `add`
Adds an assignment with name, start_date, end_date, percent_weightage, total marks. All assignments will initially be ungraded.
<br>
Format: `Format: add {name} {start_date} {end_date} {percent_weightage} {total_marks}`<br>
Example:
* `add lab1 01-03-2023 15-03-2023 20 100`

### Delete Assignment: `delete`
Deletes the assignment of assignment_id you provided.<br>
Format: `delete {assignment_id}`<br>
Example:
* `delete 1`

### Grade Assignment: `grade`
Grades the assignment of assignment_id and student_id with a score you provide.<br>
Format: `grade {assignment_id} {student_id} {score}`<br>
Example:
* `grade 1 2 20`

### Ungrade Assignment: `ungrade`
Removes the grade of the assignment of assignment_id and student_id.<br>
Format: `ungrade {assignment_id} {student_id}`<br>
Example:
* `ungrade 1 2`

### List all assignments: `list`
Lists all assignments and their respective information.
Format: `list`

### Exit assignment system: `exit`
Exits the assignment system.<br>
Format: `exit`

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

### Listing all students : `list` [coming soon]
List the students in the class.

Format: `list CLASS_NAME`
- List the students in tutorial class indicated by the argument class name
- There should only be one string following list and nothing else.​
- The argument class name is not case-sensitive.


Examples:
 - Li Chengyue A0123456K


### Find a particular student : `find` [coming soon]
List the students in the class.

Format: `find FLAG STUDENT_NUMBER` or `find FLAG STUDENT_NAME`
- Flag -id refers to find by student number
- Flag -n refers to find by name
- The format of the command follows find flag String
- The name or student number of the student are not case-sensitive


Examples:
- find -name john lee
- find -id a0123456b


## CSV Parser

Stores and loads data in with CSV files.

* Import data in CSV format: `import`
* Export data in CSV format: `export`

Our CSV files follow the following format:
1. All CSV files are header-less. Student data has exactly 2 columns: name, tags.
2. If a student has no tags, a comma representing the tags column is still required because [if a column is defined as optional, it means that the column is required to exist, but the value can be blank.](https://www.ibm.com/docs/en/atlas-policy-suite/6.0.3?topic=files-rules-creating-populating-csv) 

Acceptable CSV format example:
```
Technoblade, Minecrafter Pig Anarchist
Meggy Spletzer,Inkling
John von Neumann,
```

### Import data in CSV format: `import` [coming soon]
Import data in CSV format from file.

Format: `import [flag] [file path]`
* Flag -force overwrites existing records.
* Nothing is changed if file does not exist or file access denied.

### Export data in CSV format: `export` [coming soon]
Export data in CSV format to file.

Format: `export [flag] [file path]`
* If file exists, export is blocked unless -force flag is used. Otherwise, create file and export.
* Flag -force overwrites existing file.
* Nothing is changed if file access denied.





--------------------------------------------------------------------------------------------------------------------

## FAQ

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

_Details coming soon ..._
