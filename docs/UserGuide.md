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

Format: `new-student [s/STUDENT_NAME]` [ad/STUDENT_ADDRESS] [sch/SCHOOL] [lvl/GRADE_LEVEL]

Example:
`new-student s/John Doe ad/21 Prince George’s Park sch/ACJC lvl/sec 8`

* STUDENT_ADDRESS, SCHOOL and GRADE_LEVEL are optional.


### 2. Update student information: 

Updates the student's information given the student's label, field to change, and updated field value 

Format: `update-info [s/STUDENT_NAME] [f/FIELD] [v/VALUE]`

* if any parameters are missing in order, the command will display potential parameter options.
* The available field parameters are “Address”, “School”, and “Level”.

Examples:
* `update-info` Displays a list of all available student profiles
* `update-info s/John` Displays all students of the name “John” and prompts the user for clarification
* `update-info st/John f/address` Displays the value stored in the Address Field and prompts the user for new Address.
* `update-info st/John f/address v/Block 123 #12-34` Updates student info and displays the new value to the user.





### 3. Assign homework to a student

Creates a homework assignment with a deadline for a student

Format: `assign-homework [s/STUDENT_NAME] [hw/HOMEWORK_NAME] [d/DEADLINE]’

* The `STUDENT_NAME` must be an existing student of the tutor.
* DEADLINE  must be in a dd-mm-yy OR dd-mm-yy-hhmm format.

Examples:
`assign-homework s/John hw/listening comprehension ex1 /d 02-12-2023-2359` adds the assignment `listening comprehension ex1` to the student named `John. The deadline is 02 Dec 2023 at 23:25.



### 4. Viewing homework: `view-homework`

Displays a list of homework with the ability to filter by student name and homework status.

Format: `view-homework [s/STUDENT_NAME] [st/STATUS]`

* By default, all homework will be displayed.
* To view homework for a specific student, specify the name using `s/STUDENT_NAME`.
* To view homework with a specific status, specify the status using `st/STATUS`.
* It is possible to filter by both student name and status simultaneously.
* The available status values are `completed` and `pending`.

Examples:
* `view-homework` Displays a list of all homework.
* `view-homework s/John` Displays homework for a student named `John`.
* `view-homework st/completed` Displays all completed homework.
* `view-homework s/John st/pending` Displays pending homework for a student named `John`.



### 5. Viewing Student Profile: `view-profile`

Allows a user to view a student's home address and phone number.

Format: `view-profile [s/STUDENT_NAME]`

* Click on the "view profile" button next to a student to access the student's profile.
* The student's home address and phone number will be displayed.
* Profile can also be accessed by clicking on a student’s name.


### 6. View a student’s upcoming tasks: `schooltasks`

Displays the list of upcoming exams / school assignments of a student.

Format: `schooltasks [s/STUDENT_NAME]`

* Can also click the `View School Tasks` button to view his/her upcoming school exams/assignments.

Examples:
`schooltasks s/John` Displays all upcoming exams / school assignments of the student named `John`



### 7. Create a new Lesson plan for upcoming lesson: `new-lesson`

Creates a new lesson for a given student, with a lesson title and time.

Format: `new-lesson [/sSTUDENT_NAME] [t/LESSON_TITLE] [at/TIME]`

* All three fields are mandatory.
  *Time format is dd-mm-yy-hhmm

* if any parameters are missing in order, the command will display potential parameter options.

Examples:
`new-lesson s/John Doe t/The Water Cycle at/25-03-23-1300`



### 8. View Lesson History: `history`

Displays the lesson history for a given tutor’s students.

Format: `history [s/STUDENT_NAME]`

* Displays the lesson history for all the tutor’s students if no student name is specified.
* Displays the lesson history for a specific student if a student's name is specified with the `s/` prefix.
* The `STUDENT_NAME` must be an existing student of the tutor.
* The lesson history should include the date of the lesson, the topic taught, and the duration of the lesson.

Examples:
* `history` Displays the lesson history for all the tutor’s students.
* `history s/John` Displays the lesson history for the student named John. If two students have the same name, then asks which one to show..


### 9. View the lesson details of a past lesson: `view-lesson`

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
