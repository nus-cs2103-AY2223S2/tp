---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org/).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: get teaching assistant tasks done more conveniently and quickly as compared to traditional GUI apps

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​        | I want to …​                                          | So that I can…​                                                          |
|----------|----------------|-------------------------------------------------------|--------------------------------------------------------------------------|
| `* * *`  | TA             | group students by class                               | track participation/assignment statuses by students' own classes         |
| `* * *`  | TA             | add students to a class list                          | expand the class list if needed (e.g. new student joins)                 |
| `* * *`  | TA             | delete students from a class list                     | remove students who are no longer part of the class (e.g. dropped class) |
| `* * *`  | TA             | allocate assignments to students by class             | track the statuses of assignments assigned to each class                 |
| `* * *`  | careless TA    | remove assignments allocated to a class               | remove incorrect assignment entries                                      |
| `* * *`  | responsible TA | track students' participation statuses                | award participation marks to students fairly and accurately              |
| `* * *`  | forgetful TA   | track valid reasons for a student's absence           | record attendance for those with valid reasons                           |
| `* * *`  | forgetful TA   | track student assignment submissions/scores/deadlines | grade assignments easily                                                 |
| `* * *`  | user           | have CSV files storing my data                        | make backups to guard against data loss                                  |
| `* * *`  | user           | load in CSV files to restore data                     | avoid having to do manual data entry when using the app from scratch     |
| `* * *`  | user           | exit the app gracefully                               | avoid data corruption                                                    |
| `* *`    | TA             | assign individual assignment grades to students       | track the specific grade each student has obtained for each assignment   |
| `* *`    | forgetful TA   | track questions asked by students                     | answer them on time                                                      |
| `* *`    | forgetful TA   | keep a timeline/lesson plan for each lesson           | complete the lessons on time without missing out on any content          |
| `* *`    | forgetful TA   | be reminded to take attendance before class ends      | award attendance marks correctly                                         |
| `* *`    | TA             | group students quickly and fairly                     | reduce time wasted due to group formation                                |
| `* *`    | TA             | search students by keywords                           | lessen the mental load required to find a student                        |
| `* *`    | TA             | list all students in a class                          | see an overview of the students in a class at a glance                   |
| `*`      | head TA        | manage all other TAs                                  | track whether each TA has done their job correctly                       |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `TAA` and the **Actor** is the `user`, unless specified otherwise)

**Use case: delete a student**

**MSS**

1.  User requests to list all students in a class
2.  TAA shows a list of students
3.  User requests to delete a specific student in the list
4.  TAA deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TAA shows an error message.

      Use case resumes at step 2.


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
