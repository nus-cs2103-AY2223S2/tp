---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

### UI component

### Logic component

### Model component

### Storage component

### Common classes

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

#### Design considerations:

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* private math tuition teachers
* has a need to manage a number of students' contacts and performance
* prefer desktop apps over other types
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**:

Tutors tend to use multiple applications to keep track of their schedule/progress work. MATHUTORING comes in to centralise the features into a single application with a contact management system to track the students’ progress report which subsequently allows the tutors to plan their lesson plan for future lessons and overall view of their schedule for ease of planning.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                | So that I can…​                                                                          |
|----------|---------|-------------------------------------------------------------|------------------------------------------------------------------------------------------|
| `* * *`  | user    | see a list of my students                                   | know who my students are and how many students I have                                    |
| `* * *`  | user    | purge all current data                                      | get rid of sample/experimental data I used for exploring the app                         |
| `* * *`  | user    | create my student contacts                                  | add new students into my contact list                                                    |
| `* * *`  | user    | filter my student contacts                                  | look up on a single student/students of the same level instead of reading through a list |
| `* * *`  | user    | edit my student contacts                                    | my contact list is more extensive/flexible                                               |
| `* * *`  | user    | delete my student contacts                                  | remove contacts of students that I don't teach anymore                                   |
| `* * *`  | user    | use the help section                                        | learn the available commands in the application                                          |
| `* * *`  | user    | import my data                                              | backup data and open in another device                                                   |
| `* * *`  | user    | export my data                                              | load data into a new device                                                              |
| `* * *`  | user    | have a secure delete of my data                             | prevent myself from accidentally deleting information                                    |
| `* * `   | user    | create a progress report                                    | keep track of the student's progress                                                     |
| `* * `   | user    | check the student’s taskings                                | understand how good the student is doing                                                 |
| `* * `   | user    | edit the student’s tasking                                  | edit the information of the student's tasking                                            |
| `* * `   | user    | delete / mark student’s tasking(s)                          | identify what taskings are done/obsolete                                                 |
| `* * `   | user    | see a calendar                                              | view on which day I have classes                                                         |
| `* * `   | user    | extract students' progress report                           | show the parents their kids' performance                                                 |
| `* `     | user    | note down a more detailed class description                 | know what I need to do for a certain class                                               |
|  `* `    | user    | filter the calendar                                         | see clearly how many classes I have within a period of time (week/month, etc.)           |
|  `* `    | user    | be able to do a wildcard search                             | know what I can do on the app if I forgot the exact command I want to execute            |
|  `* `    | user    | indicate whether a student has paid the tuition fee         | easily remember which student hasn't paid the tuition fee                                |
|  `* `    | user    | export my data to the cloud                                 | save my data online                                                                      |
|  `* `    | user    | export the calendar data                                    | backup the calendar data and import the data to a calendar application                                   |
|  `* `    | user    | have a reminder                                             | remember what classes I have for tomorrow                                                |
|  `* `    | user    | auto send an email to the student to confirm the attendance | know whether the student will attend the class or not and decide whether I should conduct the class     |
|  `* `    | user    | indicate whether the student attends the class              | view the student's attendance record                                                     |
|  `* `    | user    | auto send an email to remind the student about the tuition fee payment  | eliminate my task of manually reminding the student to pay the tuition fee                                                                |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `MATHTUTORING` and the **Actor** is the `Tutor`, unless specified otherwise)

**Use case: Delete a student**

**MSS**

1.  Tutor requests to list students.
2.  MATHTUTORING shows a list of students.
3.  Tutor requests to delete a specific student in the list.
4.  MATHTUTORING deletes the student.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. MATHTUTORING shows an error message.

      Use case resumes at step 2.
      
* 3b. The given command argument(s) are invalid.
    * 3b1. MATHTUTORING shows an error message.

      Use case resumes at step 2.

**Use case: Update a student**

**MSS**

1.  Tutor requests to list students.
2.  MATHTUTORING shows a list of students.
3.  Tutor requests to edit a specific student in the list.
4.  MATHTUTORING edits the student.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. MATHTUTORING shows an error message.

      Use case resumes at step 2.

* 3b. The given command argument(s) are invalid.
    * 3b1. MATHTUTORING shows an error message.

      Use case resumes at step 2.

**Use case: Delete a task**

**MSS**

*{More to be added}*

**Extensions**

*{More to be added}*

**Use case: Update a task**

**MSS**

*{More to be added}*

**Extensions**

*{More to be added}*


*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students' information without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The application should work on both 32-bit and 64-bit environments.
5.  The application should respond within 3 seconds.
6.  The user interface should be intuitive enough for users who are not IT-savvy.
7.  The product is free of charge.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Tutors**: Private math tuition teachers that will be using the application
* **Students' progress**: For our current version, the progress of a student is tracked through 
the number of tasks the student has completed 
* **Students' performance**: For our current version, the performance of a student is tracked through a view
of the student's scores. In future implementation, a student's performance will be shown in a line chart

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

### Launch and shutdown

### Deleting a person

### Saving data
