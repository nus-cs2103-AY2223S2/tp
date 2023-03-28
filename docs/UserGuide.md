---
layout: page
title: User Guide
---

TutorPro is a **desktop app designed to help private tutors manage their student information effectively**. With TutorPro, tutors can easily keep track of their students' addresses, contact details, lessons, homework, and progress, all in one place. This app is optimised for use via a Graphical User Interface (GUI), allowing tutors to interact with the app using easy-to-understand buttons and menus. However, TutorPro also provides a Command Line Interface (CLI) for those who prefer a faster way of getting things done. Whether you're managing a handful of students or hundreds, TutorPro can help you streamline your workflow and make your tutoring experience more efficient.

--------------------------------------------------------------------------------------------------------------------


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `TutorPro.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------


## Features

### 1. Create a new student profile

Creates a new profile for a student given the student’s name.

Format: `new-student [name/STUDENT_NAME] [address/STUDENT_ADDRESS] [phone/PHONE] [email/EMAIL] [school/SCHOOL] [level/GRADE_LEVEL]`

Example:
`new-student name/John Doe address/21 Prince George’s Park email/jdoe@gmail.com phone/12345678 school/ACJC level/sec8`

* SCHOOL and GRADE_LEVEL are optional.
* SCHOOL and GRADE_LEVEL consist of numbers and letters only (no symbols or spaces).


### 2. Update student information: 

Updates the student's information given the student's label, field to change, and updated field value 

Format: `update-info [name/STUDENT_NAME] [f/FIELD] [v/VALUE]`

* if any parameters are missing in order, the command will display potential parameter options.
* The available field parameters are “Address”, “School”, and “Level”.

Examples:
* `update-info` Displays a list of all available student profiles
* `update-info name/John` Displays all students of the name “John” and prompts the user for clarification
* `update-info name/John f/address` Displays the value stored in the Address Field and prompts the user for new Address.
* `update-info name/John f/address v/Block 123 #12-34` Updates student info and displays the new value to the user.





### 3. Assign homework to a student

Creates a homework assignment with a deadline for a student

Format: `assign-homework [name/STUDENT_NAME] [homework/HOMEWORK_NAME] [deadline/DEADLINE]`

* The `STUDENT_NAME` must be an existing student of the tutor.

Examples:
`assign-homework name/John homework/listening comprehension ex1 deadline/02-12-2023-2359` adds the assignment `listening comprehension ex1` to the student named `John. The deadline is 02 Dec 2023 at 23:25.



### 4. Viewing homework: `view-homework`

Displays a list of homework with the ability to filter by student name and homework status.

Format: `view-homework [name/STUDENT_NAME] [status/STATUS]`

* By default, all homework will be displayed.
* To view homework for a specific student, specify the name using `name/STUDENT_NAME`.
* To view homework with a specific status, specify the status using `status/STATUS`.
* It is possible to filter by both student name and status simultaneously.
* The available status values are `completed` and `pending`.

Examples:
* `view-homework` Displays a list of all homework.
* `view-homework name/John` Displays homework for a student named `John`.
* `view-homework status/completed` Displays all completed homework.
* `view-homework name/John status/pending` Displays pending homework for a student named `John`.



### 6. Create a new Lesson plan for upcoming lesson: `new-lesson`

Creates a new lesson for a given student, with a lesson title and time.

Format: `new-lesson [name/STUDENT_NAME] [lesson/LESSON_TITLE] [start/START_TIME] [end/END_TIME]`

* All fields are mandatory.

Examples:
`new-lesson name/John Doe lesson/The Water Cycle start/25-03-23-1300 end/25-03-23-1500`



### 7. View Lesson History: `view-lesson`

Displays the lesson history for a given student/all students.

Format: `view-lesson [name/STUDENT_NAME]`

* Displays the lesson history for all the tutor’s students if no student name is specified.
* Displays the lesson history for a specific student if a student's name is specified with the `name/` prefix.
* The `STUDENT_NAME` must be an existing student of the tutor.

Examples:
* `view-lesson` Displays the lesson history for all the tutor’s students.
* `view-lesson name/John` Displays the lesson history for the student named John. If two students have the same name, then asks which one to show..

### 8. Add an Exam to be tracked: `add-exam`

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
* `new-exam name/John Doe exam/Math MYE start/2023-05-21 12:00 end/2023-05-21 14:00`
* `new-exam name/John Doe name/Faye Doe exam/Science MYE start/2023-05-22 12:00 end/2023-05-22 14:00`

### 9. Remove an exam: `delete-exam`

Format: `delete-exam [name/STUDENT_NAME_1] (optional)[name/STUDENT_NAME_2].. [index/INDEX_OF_EXAM]`

* Removes an exam that TutorPro is currently tracking.
* At least one student name must be provided.
* `INDEX_OF_EXAM` is in reference to the indexing of the exams listed when invoking the `view-exam` command on a 
student.

Examples:
* `delete-exam name/John Doe index/1`
* `delete-exam name/John Doe name/Faye Doe index/1`

### 10. View exams tracked by TutorPro: `view-exam`

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

### 11. Edit exam details: `update-exam`

Format: `update-exam [name/STUDENT_NAME] [index/INDEX] (optional)[exam/NEW_EXAM_NAME] (optional)[start/START_TIME] 
(optional)[end/END_TIME] (optional)[grade/GRADE]`

* Updates the details of an exam tracked by TutorPro
* Of the optional fields, one must be provided in order to update the exam.
* `START_TIME` and `END_TIME` provided must be in any of the supported date-time formats (see appendix).
* `INDEX` is in reference to the indexing of the exams listed when invoking the `view-exam` command on a
student.

Examples:
* `update-exam name/John index/1 grade/20/25`

### 12. Calculate grade:  `calculate-grade`

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

## Appendix
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
