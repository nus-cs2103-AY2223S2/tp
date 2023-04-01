---
layout: page
title: User Guide
---

TutorPro is a **desktop app designed to help private tutors manage their student information effectively**. With TutorPro, tutors can easily keep track of their students' addresses, contact details, lessons, homework, and progress, all in one place. This app is optimized for use via a Graphical User Interface (GUI), allowing tutors to interact with the app using easy-to-understand buttons and menus. However, TutorPro also provides a Command Line Interface (CLI) for those who prefer a faster way of getting things done. Whether you're managing a handful of students or hundreds, TutorPro can help you streamline your workflow and make your tutoring experience more efficient.

--------------------------------------------------------------------------------------------------------------------
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed on your Computer.

2. Download the latest `TutorPro.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your address book.

4. Open a command terminal, `cd` into the folder in which you put the jar file, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
### Glossary

In the user guide, you may come across some terms you do not understand. The following table provides clarification
of the terms commonly used in PetCode.

|       Term       | Description                                                                                                                                                                                                                                                                                                                                                  |
|:----------------:|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Alphanumeric** | Digits and letters only. For example, `AB3`, `PetCode`, `coco123`, and `2103` are alphanumeric. `#01-04`, `email@domain.com`, and `white    spaces` are not.                                                                                                                                                                                                 |
|  **Attribute**   | Words that follow prefixes to describe properties, states, characteristics, and traits. Examples are price, weight, name, and order status.                                                                                                                                                                                                                  |
|   **Command**    | A command is a specific instruction you can give to PetCode to perform an action. You can view the list of commands available [here](#command-summary).                                                                                                                                                                                                      |
|   **Contact**    | A contact is an information entry in PetCode. There are three types of contacts you can add - `Buyer`, `Supplier` and `Deliverer`. You can add a contact with the [`add` command](#adding-a-contact-or-item-add).                                                                                                                                            |
|     **CLI**      | Command-Line Interface (CLI) receives commands from a user in the form of lines of text. It refers to the input textbox in this context.                                                                                                                                                                                                                     |
|     **GUI**      | GUI stands for Graphical User Interface. It refers to the display window of the PetCode application.                                                                                                                                                                                                                                                         |
|    **Index**     | The index of the contact or item in the display list for contacts/items.                                                                                                                                                                                                                                                                                     |
|   **Integer**    | Whole number                                                                                                                                                                                                                                                                                                                                                 |
|  **Parameter**   | A parameter refers to the information you need to give to your command such that it can execute an action based on that information. <br/> For example, the [`list` command](#listing-contacts-or-items--list) requires a KEY parameter to know what kind of list to display. `list buyer` displays your list of buyers, where the KEY parameter is `buyer`. |
|    **Prefix**    | A prefix indicates the kind of information you are keying in. You can view the list of prefixes available [here](#list-of-prefixes).                                                                                                                                                                                                                         |
|  **Whitespace**  | An empty character, or a placeholder character ` `.                                                                                                                                                                                                                                                                                                          |
|   **Student**    | A `Student` whom the user (who is a tutor) teaches                                                                                                                                                                                                                                                                                                           |
|   **Homework**   | A `Homework` assignment whom the user (who is a tutor) has assigned to a `Student`                                                                                                                                                                                                                                                                           |
|    **Lesson**    | A `Lesson` that the user has scheduled with a `Student`                                                                                                                                                                                                                                                                                                      |
|     **Exam**     | An `Exam` that a Student is scheduled to sit for                                                                                                                                                                                                                                                                                                             |


--------------------------------------------------------------------------------------------------------------------

## Features



### Create a new student profile

Creates a new profile for a student given the student’s name.

Format: `new-student [name/STUDENT_NAME] [address/STUDENT_ADDRESS] [phone/PHONE] [email/EMAIL] [school/SCHOOL] [level/GRADE_LEVEL]`

Example:
`new-student name/John Doe address/21 Prince George’s Park email/jdoe@gmail.com phone/12345678 school/ACJC level/sec8`

* SCHOOL and GRADE_LEVEL are optional.
* SCHOOL and GRADE_LEVEL consist of numbers and letters only (no symbols or spaces).


### Update Student Information

Updates the student's information given the student's label, field to change, and updated field value 

Format: `update-info [name/STUDENT_NAME] [f/FIELD] [v/VALUE]`

* if any parameters are missing in order, the command will display potential parameter options.
* The available field parameters are “Address”, “School”, and “Level”.

Examples:
* `update-info` Displays a list of all available student profiles
* `update-info name/John` Displays all students with the name “John” and prompts the user for clarification
* `update-info name/John f/address` Displays the value stored in the Address Field and prompts the user for a new Address.
* `update-info name/John f/address v/Block 123 #12-34` Updates student info and displays the new value to the user.


### Assign Homework to a Student

Creates a homework assignment with a deadline for a student

Format: `new-homework [name/STUDENT_NAME] [homework/HOMEWORK_NAME] [deadline/DEADLINE]`

* The `STUDENT_NAME` must be an existing student of the tutor.

Examples:
* `assign-homework name/John homework/listening comprehension ex1 deadline/02-12-2023-2359` adds the assignment `listening comprehension ex1` to the student named `John. The deadline is 02 Dec 2023 at 23:25.


### View the Homework of Students

Displays a list of homework with the ability to filter by student name and homework status.

Format: `view-homework [name/STUDENT_NAME] [status/STATUS]`

* By default, all homework will be displayed.
* To view homework for a specific student, specify the name using `name/STUDENT_NAME`.
* To view homework with a specific status, specify the status using `status/STATUS`.
* It is possible to filter by both student name and status simultaneously.
* The available status values are `completed` and `pending`.

Examples:
* `view-homework` displays a list of all homework.
* `view-homework name/John` displays homework for a student named `John`.
* `view-homework status/completed` displays all completed homework.
* `view-homework name/John status/pending` displays pending homework for a student named `John`.

### Delete Homework from a Student

Deletes a homework assignment for a student.

Format: `delete-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX]`

* The `STUDENT_NAME` must be an existing student of the tutor. Note that there can only be one student's name.
* The `HOMEWORK_INDEX` must be the index of an existing homework assignment for the specified student.
* A success message will be displayed if the homework assignment is successfully deleted. Otherwise, an error message will be displayed.

Examples:

* `delete-homework name/John index/1` deletes the first homework assignment for the student named John.
* `delete-homework name/Susan index/3` deletes the third homework assignment for the student named Susan.

### Mark the Homework of a Student as Done

Marks homework of a student as done.

Format: `mark-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX]`

* The `STUDENT_NAME` must be an existing student of the tutor. Note that there can only be one student's name.
* The `HOMEWORK_INDEX` must be the index of an existing homework assignment for the specified student.
* A success message will be displayed if the homework assignment is successfully deleted. Otherwise, an error message will be displayed.

Examples:

* `mark-homework name/John index/1` marks the first homework assignment for the student named John.
* `mark-homework name/Susan index/3` marks the third homework assignment for the student named Susan.


### Unmark Homework of a Student as Undone

Marks homework of a student as undone.

Format: `unmark-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX]`

* The `STUDENT_NAME` must be an existing student of the tutor. Note that there can only be one student's name.
* The `HOMEWORK_INDEX` must be the index of an existing homework assignment for the specified student.
* A success message will be displayed if the homework assignment is successfully deleted. Otherwise, an error message will be displayed.

Examples:

* `unmark-homework name/John index/1`unmarks the first homework assignment for the student named John.
* `unmark-homework name/Susan index/3` unmarks the third homework assignment for the student named Susan.


### Update Homework of a Student

Updates the information on a homework of a student

Format: `update-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX] [homework/HOMEWORK_NAME] [deadline/DEADLINE]`

* The `STUDENT_NAME` must be an existing student of the tutor. Note that there can only be one student's name.
* The `HOMEWORK_INDEX` must be the index of an existing homework assignment for the specified student.
* At least one homework name and deadline must be in the command.
* A success message will be displayed if the homework assignment is successfully deleted. Otherwise, an error message will be displayed.

Examples:

* `update-homework name/John index/1 homework/Math Assignment 1` updates the name of homework 1 of John to be `Math Assignment 1`.
* `updates-homework name/Susan index/3 deadline/2023-05-12 23:59` updates the deadline of homework 3 of Susan to be `2023-05-12 23:59`.
* `updates-homework name/Donald index/2 homework/Math Assignment 1 deadline/2023-05-12 23:59` updates the name of homework 2 of Donald to be `Math Assignment 1` and updates the deadline of homework 2 of Donald to be `2023-05-12 23:59`.


### Create a New Lesson for a Student

Creates a new lesson for a given student, with a lesson title, start time, and end time.

Format: `new-lesson [name/STUDENT_NAME_1] (optional)[name/STUDENT_NAME_2] [lesson/LESSON_TITLE] [start/START_TIME] [end/END_TIME]`

* All fields, except for additional student names, are mandatory.

Examples:
`new-lesson name/John Doe lesson/The Water Cycle start/25-03-23 1300 end/25-03-23 1500` creates a new lesson for the student named `John Doe` with the lesson title `The Water Cycle` starting at `25 Mar 2023 13:00` and ending at `25 Mar 2023 15:00`.


### View Lessons

Displays the lessons for a given student/all students, filtered by subject and/or student name and/or date and/or whether the lesson has been completed

Format: `view-lesson (optional)[name/STUDENT_NAME] (optional)[subject/SUBJECT] (optional)[date/DATE] (optional)[done/DONE]`

* Displays the lesson history for all the tutor’s students if no student name is specified.
* Displays the lesson history for a specific student if a student's name is specified with the `name/` prefix.
* The `STUDENT_NAME` must be an existing student of the tutor.

Examples:
* `view-lesson` Displays the lesson history for all the tutor’s students.
* `view-lesson name/John subject/Math date/2023-05-03` Displays the lessons for student John, which are of subject Math, on the day 2023-05-03.
* `view-lesson done/done` Displays all lessons that have been completed

### Remove a Lesson
Deletes a lesson for a given student.

Format: `delete-lesson [name/STUDENT_NAME] [index/LESSON_INDEX]`

* The `STUDENT_NAME` must be an existing student of the tutor. Note that there can only be one student's name.
* Deletes the lesson at the specified `LESSON_INDEX`.

Example:
* `delete-lesson name/John Doe index/1` deletes the first lesson for the student named John Doe.

### Update a Lesson
Updates a lesson for a given student. This includes the lesson title, start time, and end time.

Format: `update-lesson (optional)[name/STUDENT_NAME] (optional)[lesson/LESSON_TITLE] (optional)[start/START_TIME] (optional)[end/END_TIME]`

* The `STUDENT_NAME` must be an existing student of the tutor. Note that there can only be one student's name.
* Updates the specified Lesson with the new information (lesson name and/or start time and/or end time).
* At least one of the fields must be specified.

Example:
* `update-lesson name/John Doe lesson/The Water Cycle start/25-03-23 1300 end/25-03-23 1500` updates the lesson for John Doe with the new information.

### Add an Exam to be tracked: `add-exam`

Creates an Exam within TutorPro to be tracked for a given student.

Format: `new-exam [name/STUDENT_NAME_1] (optional)[name/STUDENT_NAME_2].. [exam/EXAM_NAME] [start/START_TIME] 
[end/END_TIME] (optional)[weightage/WEIGHTAGE] (optional)[grade/GRADE]`

* Creates an exam that is attributed to one or more students.
* At least one student name must be provided.
* `START_TIME` and `END_TIME` provided must be in any of the supported date-time formats (see appendix).
* the format of `GRADE` should be `grade/ACTUAL_SCORE/TOTAL_SCORE`

💡 **Tip:** the subject of the exam should be included in the `EXAM_NAME` field to facilitate grade calculations. 
See `calculate-grade` below:

Examples:
* `new-exam name/John Doe exam/Math MYE start/2023-05-21 12:00 end/2023-05-21 14:00` creates an exam named `Math MYE` for the student named John Doe, which starts at 12:00 on 21 May 2023 and ends at 14:00 on 21 May 2023.
* `new-exam name/John Doe name/Faye Doe exam/Science MYE start/2023-05-22 12:00 end/2023-05-22 14:00` creates an exam named `Science MYE` for the students named John Doe and Faye Doe, which starts at 12:00 on 22 May 2023 and ends at 14:00 on 22 May 2023.

### Remove an exam: `delete-exam`

Format: `delete-exam [name/STUDENT_NAME_1] (optional)[name/STUDENT_NAME_2].. [index/INDEX_OF_EXAM]`

* Removes an exam that TutorPro is currently tracking.
* At least one student name must be provided.
* `INDEX_OF_EXAM` is in reference to the indexing of the exams listed when invoking the `view-exam` command on a 
student.

Examples:
* `delete-exam name/John Doe index/1` deletes the first exam for the student named John Doe.
* `delete-exam name/John Doe name/Faye Doe index/1` deletes the first exam for the students named John Doe and Faye Doe.

### View exams tracked by TutorPro: `view-exam`

Format: `view-exam (optional)[name/STUDENT_NAME] (optional)[date/DATE] (optional)[exam/NAME_OF_EXAM] (optional)
[done/IS_DONE]`

* Lists out exams TutorPro is currently tracking, while filtering for the specified predicates
* All predicates are optional, leaving all parameters blank will list all currently tracked exams
* Field `[IS_DONE]` when filled with parameter 'done' `eg. done/done` will list all completed exams. Leave this field 
blank `eg. done/` when filtering for upcoming exams 

Examples:
* `view-exam` -lists all exams currently being tracked by TutorPro
* `view-exam name/John date/2023-05-01 exam/MYE done/` -list exams attributed to student 'John' on date '2023-05-01' 
with description 'MYE' which are undone.

### Edit exam details: `update-exam`

Format: `update-exam [name/STUDENT_NAME] [index/INDEX] (optional)[exam/NEW_EXAM_NAME] (optional)[start/START_TIME] 
(optional)[end/END_TIME] (optional)[grade/GRADE]`

* Updates the details of an exam tracked by TutorPro
* Of the optional fields, one must be provided in order to update the exam.
* `START_TIME` and `END_TIME` provided must be in any of the supported date-time formats (see appendix).
* `INDEX` is in reference to the indexing of the exams listed when invoking the `view-exam` command on a
student.

Examples:
* `update-exam name/John index/1 grade/20/25` updates the grade of the first exam for the student named John Doe to 20/25.
* `update-exam name/John index/1 exam/Science MYE` updates the name of the first exam for the student named John Doe to Science MYE.
* `update-exam name/John index/1 start/2023-05-21 12:00 end/2023-05-21 14:00` updates the start and end time of the first exam for the student named John Doe to 12:00 on 21 May 2023 and ends at 14:00 on 21 May 2023.

### Calculate grade:  `calculate-grade`

Format: `calculate-grade [name/STUDENT_NAME] [subject/SUBJECT]`

* Calculates the grade of a subject using the weightages and scores tracked by TutorPro and outputs the report.
* the `[SUBJECT]` field matches the name and/or description of the tracked exam and factors the exam that matches the 
description into the score calculation.
* Should there be undefined weightages for exams, the overall report will state the grade as undefined. 

Examples:
* `calculate-grade name/John subject/Econs`

--------------------------------------------------------------------------------------------------------------------



## FAQ

**Q**: How can I transfer my data to another computer in TutorPro? <br>
**A**: You can transfer your data to another computer by installing TutorPro on the new computer and replacing its empty data file with the one that contains the data from your previous TutorPro home folder.

--------------------------------------------------------------------------------------------------------------------

## Summary

### List of Commands
| Action                       | Command Format                                                                                                                                                       | Example                                                                                                                |
|------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| Create new student profile   | `new-student [name/STUDENT_NAME] [address/STUDENT_ADDRESS] [phone/PHONE] [email/EMAIL] [school/SCHOOL] [level/GRADE_LEVEL]`                                          | `new-student name/John Doe address/21 Prince George’s Park email/jdoe@gmail.com phone/12345678 school/ACJC level/sec8` |
| Update student information   | `update-info [name/STUDENT_NAME] [f/FIELD] [v/VALUE]`                                                                                                                | `update-info name/John f/address v/Block 123 #12-34`                                                                   |
| Assign homework to a student | `new-homework [name/STUDENT_NAME] [homework/HOMEWORK_NAME] [deadline/DEADLINE]`                                                                                      | `assign-homework name/John homework/listening comprehension ex1 deadline/02-12-2023-2359`                              |
| View student's homework      | `view-homework [name/STUDENT_NAME] [status/STATUS]`                                                                                                                  | `view-homework name/John status/pending`                                                                               |
| Delete student's homework    | `delete-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX]`                                                                                                         | `delete-homework name/John index/1`                                                                                    |
| Mark homework as done        | `mark-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX]`                                                                                                           | `mark-homework name/John index/1`                                                                                      |
| Unmark homework as undone    | `unmark-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX]`                                                                                                         | `unmark-homework name/John index/1`                                                                                    |
| Update student's homework    | `update-homework [name/STUDENT_NAME] [index/HOMEWORK_INDEX] [homework/HOMEWORK_NAME] [deadline/DEADLINE]`                                                            | `update-homework name/John index/1 homework/Math Assignment 1`                                                         |
| Create new lesson plan       | `new-lesson [name/STUDENT_NAME] [lesson/LESSON_TITLE] [start/START_TIME] [end/END_TIME]`                                                                             | `new-lesson name/John Doe lesson/The Water Cycle start/25-03-23-1300 end/25-03-23-1500`                                |
| View lessons history         | `view-lesson [name/STUDENT_NAME]`                                                                                                                                    | `view-lesson name/John`                                                                                                |
| Add an exam                  | `new-exam [name/STUDENT_NAME_1] [name/STUDENT_NAME_2].. [exam/EXAM_NAME] [start/START_TIME] [end/END_TIME] (optional)[weightage/WEIGHTAGE] (optional)[grade/GRADE]`  | `new-exam name/John Doe exam/Math MYE start/2023-05-21 12:00 end/2023-05-21 14:00`                                     |
| Remove an exam               | `delete-exam [name/STUDENT_NAME_1] [name/STUDENT_NAME_2].. [index/INDEX_OF_EXAM]`                                                                                    | `delete-exam name/John Doe index/1`                                                                                    |
| Update an exam               | `update-exam [name/STUDENT_NAME] [index/INDEX_OF_EXAM] (optional)[exam/NEW_EXAM_NAME] (optional)[start/START_TIME] (optional)[end/END_TIME] (optional)[grade/GRADE]` | `update-exam name/John Doe index/1 exam/Math MYE`                                                                      |
| View exams                   | `view-exam [name/STUDENT_NAME] (optional)[date/DATE] (optional)[exam/EXAM_NAME] (optional)[done/DONE_STATUS]`                                                        | `view-exam name/John Doe date/2023-05-01 exam/MYE done/`                                                               |
| Calculate grade              | `calculate-grade [name/STUDENT_NAME] [subject/SUBJECT]`                                                                                                              | `calculate-grade name/John Doe subject/Math`                                                                           |

### List of Prefixes
| Prefix      | Meaning     | Usage                                               | Example                           |
|-------------|-------------|-----------------------------------------------------|-----------------------------------|
| `name/`     | Name        | Student name                                        | `name/John Doe`                   |
| `phone/`    | Phone       | Phone number of a Student                           | `phone/12345678`                  |
| `email/`    | Email       | Email address of a Student                          | `email/johndoe@gmail.com`         |
| `address/`  | Address     | Home address of a Student                           | `address/21 Prince George's Park` |
| `level/`    | Grade Level | Grade level of a Student                            | `level/sec8`                      |
| `school/`   | School      | School name of a Student                            | `school/ACJC`                     |
| `tag/`      | Tag         | Tag on a Student                                    | `tag/favorite`                    |
| `homework/` | Homework    | name of Homework assigned to a Student              | `homework/Math Assignment`        |
| `deadline/` | Deadline    | Due date                                            | `deadline/02-12-2023 2359`        |
| `exam/`     | Exam        | Exam name                                           | `exam/Math MYE`                   |
| `status/`   | Status      | Indicates whether a homework is completed           | `status/pending`                  |
| `index/`    | Index       | Index of a homework assignment/lesson/exam          | `index/1`                         |
| `lesson/`   | Lesson      | Lesson title                                        | `lesson/The Water Cycle`          |
| `start/`    | Start Time  | Start time of a lesson/exam                         | `start/2025-03-23 1300`           |
| `end/`      | End Time    | End time of a lesson/exam                           | `end/2025-03-23 1500`             |
| `date/`     | Date        | Date of a lesson/exam                               | `date/2023-03-29`                 |
| `subject/`  | Subject     | Subject of a lesson                                 | `subject/Mathematics`             |
| `done/`     | Done        | indicates if a lesson/exam is past the current time | `done/done`                       |

### Supported date-time formats:
* `MMM dd yyyy HHmm`
* `MMM dd yyyy HH:mm`
* `yyyy-MM-dd'T'HH:mm `
* `dd/MM/yyyy HHmm`
* `dd/MM/yyyy HH:mm`
* `yyyy/MM/dd HHmm`
* `yyyy/MM/dd HH:mm `
* `yyyy/MM/dd'T'HHmm`
* `yyyy/MM/dd'T'HH:mm `
* `yyyy-MM-dd HHmm`
* `yyyy-MM-dd HH:mm `
* `dd MMM yyyy HHmm`
* `dd MMM yyyy HH:mm `
* `MMM dd, yyyy HHmm`
* `MMM dd, yyyy HH:mm `
* `dd-mm-yyyy HHmm`
