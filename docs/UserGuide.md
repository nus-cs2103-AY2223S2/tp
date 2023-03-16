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
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------


## Features

### 1. Create a new student profile

Creates a new profile for a student given the student’s name.

Format: `new-student [name/STUDENT_NAME] [address/STUDENT_ADDRESS] [phone/PHONE] [email/EMAIL] [school/SCHOOL] [level/GRADE_LEVEL]

Example:
`new-student name/John Doe address/21 Prince George’s Park email/jdoe@gmail.com phone/1234 5678 school/ACJC level/sec8`

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



### 5. View a student’s upcoming homework: `view-homework`

Displays the list of upcoming exams / school assignments of a student.

Format: `view-homework [name/STUDENT_NAME]`

* Can also be done by clicking the `Homework` button to view a student's upcoming homeworks.
* `name` is optional, but can be used to only view homework from a particular student.

Examples:
`view-homework s/John` Displays all homework of the student named `John`.
`view-homework` Displays all homework for all students.



### 6. Create a new Lesson plan for upcoming lesson: `new-lesson`

Creates a new lesson for a given student, with a lesson title and time.

Format: `new-lesson [/sSTUDENT_NAME] [t/LESSON_TITLE] [at/TIME]`

* All three fields are mandatory.
  *Time format is dd-mm-yy-hhmm

* if any parameters are missing in order, the command will display potential parameter options.

Examples:
`new-lesson s/John Doe t/The Water Cycle at/25-03-23-1300`



### 7. View Lesson History: `view-lesson`

Displays the lesson history for a given student/all students.

Format: `view-lesson [name/STUDENT_NAME]`

* Displays the lesson history for all the tutor’s students if no student name is specified.
* Displays the lesson history for a specific student if a student's name is specified with the `name/` prefix.
* The `STUDENT_NAME` must be an existing student of the tutor.

Examples:
* `view-lesson` Displays the lesson history for all the tutor’s students.
* `view-lesson name/John` Displays the lesson history for the student named John. If two students have the same name, then asks which one to show..


### 8. View the lesson details of a past lesson: `view-lesson`

Displays the date, time, and other information about a past lesson

Format: `view-lesson [l/LESSON_INDEX]

* The LESSON_INDEX is from the list of lessons displayed after calling the command `history` in feature 8.

* This command can only be used when the user has just used the command `View Lesson History`

Examples:
`view-lesson l/2` Displays the lesson history of the lesson with index 2

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How can I transfer my data to another computer in TutorPro? <br>
**A**: You can transfer your data to another computer by installing TutorPro on the new computer and replacing its empty data file with the one that contains the data from your previous TutorPro home folder.

--------------------------------------------------------------------------------------------------------------------
