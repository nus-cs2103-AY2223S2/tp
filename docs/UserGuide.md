USER GUIDE

INTRODUCTION (Duong)

tuition center admin managing system (TCAMS) is a **desktop application designed for admins working at tuition center managing courses, tutors and students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). TCAMS utilizes your fast typing ability to execute your management tasks faster than traditional GUI apps.

# TABLE OF CONTENT (Duong)

[[TOC]]

# FEATURES

## Add course : add course (Jacques)

Adds a course to the tuition center managing system.

Format: add course n/NAME s/SUBJECT sc/SCHEDULE t/TUTOR st/tutor

Examples:

* add course n/2A s/Math sc/Mon 2-4pm t/john st/joe, doe, joeydoey  

* add course n/4C s/java basics sc/Saturday 6-8pm t/David st/Danny, Daniel, Daniella

## List courses: list courses (Jacques)

Shows a list of all courses in the managing system.

Format: list course

## Delete course : delete (Jacques)

Deletes the specified course from the managing system.

Format: delete INDEX

* Deletes the course at the specified INDEX.

* The index refers to the index number shown in the displayed course list.

* The index **must be a positive integer **1, 2, 3, …

Examples:

* list course followed by delete 1 deletes the 1st course in the list of courses in the managing system.

## display people in one course : display INDEX (Jacques)

list the specified people in the course from the managing system.

Format: display INDEX

* display the people in the course at the specified INDEX.

* The index refers to the index number shown in the displayed course list.

* The index **must be a positive integer **1, 2, 3, …

Examples:

* list course followed by display 1 lists the people in the 1st list of coursees in the managing system.

## Add student (Jethro)

Adds a student to the tuition center managing system.

Format: add student n/NAME p/PHONE_NUMBER e/EMAIL s/SUBJECT y/YEAR_OF_STUDY 

Examples:

* add student n/John Doe p/98765432 e/[johnd@example.com](mailto:johnd@example.com) s/math y/sec2  

## Delete student (Jethro)

Removes a student from the tuition center managing system.

Format: delete student n/NAME p/PHONE_NUMBER 

Examples:

* delete student n/John Doe p/98765432 

## Assign student (Jethro)

Assigns a student to a course in the tuition center managing system.

Format: assign student n/NAME c/CLASS s/SUBJECT

Examples:

* assign student n/John Doe c/2a s/math 

## List students (Jethro)

Lists students in the tuition center managing system.

Format: list student 

Examples:

* list student

## Add tutor (Lee Yi)

Adds a student to the tuition center managing system.

Format: add tutor n/NAME p/PHONE_NUMBER e/EMAIL s/SUBJECT y/YEAR_OF_STUDY 

Examples:

* add tutor n/John Doe p/98765432 e/[johnd@example.com](mailto:johnd@example.com) s/math y/sec2  

## Delete tutor (Lee Yi)

Removes a tutor from the tuition center managing system.

Format: delete tutor n/NAME p/PHONE_NUMBER 

Examples:

* delete tutor n/John Doe p/98765432 

## Assign tutor (Lee Yi)

Assigns a tutor to a course in the tuition center managing system.

Format: assign tutor n/NAME c/CLASS s/SUBJECT

Examples:

* assign tutor n/John Doe c/2a s/math 

## List tutors (Lee Yi)

Lists tutors in the tuition center managing system.

Format: list tutor 

## local save (Duong)

Saves the current state of the program on the hard disk upon exit.

Done automatically.

Example: upon exiting, this message will show up to indicate your data is saved.

* Your data is saved!

## local load (Duong)

Loads the saved state of the program (if there is any) on the hard disk.

Creates an empty file if there is none.

Done automatically.

Example: 

* upon starting, this message will show up to indicate your data is loaded.

Your previous work is loaded!

* or this message if there is no file saved and a new file is created.

This is a new file!

## exit program (Duong)

Exit the program.

Format: exit

