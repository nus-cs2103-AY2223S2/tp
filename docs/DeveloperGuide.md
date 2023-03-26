---
layout: page title: Developer Guide
---

* Table of Contents {:toc}

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

* has a need to manage many contacts, groups and classes
* relatively more tech-savvy than the average population, able to perform complex tasks on digital platforms
* prefers typing to mouse interactions
* need to be able to easily aggregate and analyze students' statistics
* is reasonably comfortable using CLI apps
* can type fast

**Value proposition**:

* Get teaching assistant tasks done more conveniently and quickly as compared to traditional GUI apps
* Features are fully customized for teaching assistants in NUS to ensure user-friendliness for this particular group

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …         | I want to …                                           | So that I can…                                                                                              |
|----------|----------------|-------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| `* * *`  | TA             | manage my classes in a separate, distinct system      | avoid being confused with the various command names that may overlap with that of non-class-related commands |
| `* * *`  | TA             | group students by class                               | track participation/assignment statuses by students' own classes                                             |
| `* * *`  | TA             | add students to a class list                          | expand the class list if needed (e.g. new student joins)                                                     |
| `* * *`  | TA             | delete students from a class list                     | remove students who are no longer part of the class (e.g. dropped class)                                     |
| `* * *`  | TA             | allocate assignments to students by class             | track the statuses of assignments assigned to each class                                                     |
| `* * *`  | careless TA    | remove assignments allocated to a class               | remove incorrect assignment entries                                                                          |
| `* * *`  | responsible TA | track students' participation statuses                | award participation marks to students fairly and accurately                                                  |
| `* * *`  | forgetful TA   | track valid reasons for a student's absence           | record attendance for those with valid reasons                                                               |
| `* * *`  | forgetful TA   | track student assignment submissions/scores/deadlines | grade assignments easily                                                                                     |
| `* * *`  | user           | have CSV files storing my data                        | make backups to guard against data loss                                                                      |
| `* * *`  | user           | load in CSV files to restore data                     | avoid having to do manual data entry when using the app from scratch                                         |
| `* * *`  | user           | exit the app gracefully                               | avoid data corruption                                                                                        |
| `* *`    | TA             | assign individual assignment grades to students       | track the specific grade each student has obtained for each assignment                                       |
| `* *`    | forgetful TA   | track questions asked by students                     | answer them on time                                                                                          |
| `* *`    | forgetful TA   | keep a timeline/lesson plan for each lesson           | complete the lessons on time without missing out on any content                                              |
| `* *`    | forgetful TA   | be reminded to take attendance before class ends      | award attendance marks correctly                                                                             |
| `* *`    | TA             | group students quickly and fairly                     | reduce time wasted due to group formation                                                                    |
| `* *`    | TA             | search students by keywords                           | lessen the mental load required to find a student                                                            |
| `* *`    | TA             | list all students in a class                          | see an overview of the students in a class at a glance                                                       |
| `*`      | head TA        | manage all other TAs                                  | track whether each TA has done their job correctly                                                           |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `TAA` and the **Actor** is the `user`, unless specified otherwise)

**use case: mark attendance of a student**

**use case: unmark attendance of a student**

**use case: insert participation points of a student**

**Use case: delete a student**

**Use case: Add Assignment**
asgn_add

**Use case: Delete Assignment**
asgn_delete

**Use case: Grade Student Submission of an Assignment**
grade

**Use case: Ungrade Student Submission of an Assignment**
ungrade


**Use case: Add Class List** classlist

**User case: List Student** list

**User case: SearchStudent** search

## **Mark/Unmark Attendance**

Mark and Unmark is very similar in nature and their implementation. <br>

Below is the main success scenario of Mark/Unmark


**MSS**

1. User requests to list all students in a class
2. TAA shows a list of students
3. User requests to mark attendance of a specific student in the list for a specified week
4. TAA marks attendance

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index/week is invalid.

    * 3a1. TAA shows an error message.

      Use case resumes at step 2.
* 3b. The given week's attendance is already marked

    * 3a1. TAA shows message that attendance for that week is already marked.

      Use case ends.

The Mark/Unmark commands are similar, and explanation will be done for Mark command

Mark command is facilitated by `MarkCommandParser`, `MarkAddressCommand` and `Model`

* `MarkCommandParser`-- Parse the input of users
* `MarkAttendanceCommand` -- Execute the command given the parsed user input
* `Model` -- Updates the attendance of student

Below is the sequence diagram for Marking attendane of a student

<img src="images/MarkAttendanceSequenceDiagram.png" width="574" />

## **Add Assignment**

Below is the main success scenario of adding an Assignment

**MSS**

1. User requests to add an assignment of a specific name and optionally, total marks.
2. TAA creates the assignment, with total marks having a default value of 100 if it is not specified above.

   Use case ends.

**Extensions**

* 1a. There are no assignments with that particular name, .

  Use case resumes from step 2.

* 1b. There is already an assignment with that particular name.

    * 1b1. TAA shows an error message

* 1c. Total marks given is negative.

    * 1c1. TAA shows an error message


AddAssignment command is facilitated by `AddAssignmentCommandParser`, `AddAssignmentCommand` and `Model`

* `AddAssignmentCommandParser`-- Parse the input of users
* `AddAssignmentCommand` -- Execute the command given the parsed user input
* `Model` -- Updates the assignment list.

Below is the sequence diagram for adding an assignment.

<img src="images/AddAssignmentSequenceDiagram.png" width="574" />

## **Students-Related Commands**

Below is the main success scenario of students-related commands.

**Adding a student**

1. User requests to add a user with a specific name into one or more classes.
2. TAA adds the student into the specified classes.

   Use case ends.

**Extensions**

* 1a. The given student/class name is invalid.
    * 1a1. TAA shows an error message.

      Use case resumes from step 1.

* 1b. The given class(es) does not exist.
  * 1b1. TAA creates a new class for each class that does not exist yet.
   
    Use case resumes from step 2.


**list student**
1. User requests to list all students in a class.
2. TAA shows a list of students and number of students listed.

    Use case ends.

**search student**
1.  User requests to search for a particular student
2.  TAA shows the information of the student searched

    Use case ends.

**Extensions**

* 2a. No student is found for the keyword.

  Use case ends.

**add classlist**
1.  User requests to add a new class list with a specific name.
2.  TAA adds the new class list with the name specified.
    Use case ends.

**Extensions**

* 2a. Class list with the same name already exists.
  * 2a1. TAA shows an error message.
    Use case ends.

## **Adding Alarm**

Below is the main success scenario of adding alarm.

**add alarm**
1.  User requests to add an alarm with specified time and comment.
2.  TAA adds the new alarm with the time and comment specified.
    Use case ends.

**Extensions**

* 2a. User does not provide sufficient arguments
    * 2a1. TAA shows an error message.
      Use case ends.


*{More to be added}*

### Non-functional requirements

* All aspects of the system should work on Mac, Windows, and Linux operating systems.
* The system should be easy to use by anyone who can type fast.
* The system should not crash or corrupt files under any input text.
* The system should respond within 1 second.

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

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases … }_

### Deleting a student

1. Deleting a student while all students are being shown

    1. Prerequisites: List all students using the `list` command. Multiple students in the list.

    1. Test case: `delete 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.
       Timestamp in the status bar is updated.

    1. Test case: `delete 0`<br>
       Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases … }_

### Loading/Saving data in CSV format

Our CSV files follow the following format:

1. All CSV files are header-less. Student data has exactly 2 columns: name, tags.
2. If a student has no tags, a comma representing the tags column is still required
   because [if a column is defined as optional, it means that the column is required to exist, but the value can be blank.](https://www.ibm.com/docs/en/atlas-policy-suite/6.0.3?topic=files-rules-creating-populating-csv)

Acceptable CSV format example:

```
Technoblade, Minecrafter Pig Anarchist
Meggy Spletzer,Inkling
John von Neumann,
```

1. Dealing with file access denial:
    * How to simulate: _TODO_
    * Expected behavior: Doesn't change anything and show message to user:
   ```
   Access to the specified file is denied by system.
   ```
1. Dealing with file not exists:
    * How to simulate: use any string that is not a directory of any real file.
    * Expected behavior: Doesn't change anything and show message to user:
   ```
   The specified file does not exist.
   ```
1. Dealing with file being a directory:
    * How to simulate: use a directory path.
    * Expected behavior: Doesn't change anything and show message to user:
   ```
   The specified file path is a directory.
   ```
1. Dealing with FileReader throwing FileNotFoundException:
    * How to simulate: _TODO_
    * Expected behavior: Doesn't change anything and show message to user:
   ```
   The specified file cannot be opened for reading.
   ```
1. Dealing with CSVParser throwing IOException:
    * How to simulate: _TODO_
    * Expected behavior: Doesn't change anything and show message to user:
   ```
   An IOException occurred while reading specified file.
   ```
1. Dealing with ill-formatted CSV entry:
    * How to simulate: use csv files that contains inconsistent entries, unmapped columns, or fields violating their
      constraints.
    * Expected behavior: Doesn't change anything and show message indicating detailed entry that caused the error to
      user:

1. Dealing with at least 2 students in CSV file having same name:
    * How to simulate: use csv files that contains at least students with same name.
    * Expected behavior: doesn't change anything and show message indicating the specific student existing twice in
      file.
1. Dealing with adding student whose name already existed in list:
    * How to simulate: use csv files that contains student whose name already existed in list.
    * Expected behavior: If -force is used, replace the student in list. <p>
      Otherwise, doesn't change anything and show message indicating the specific student already existed in list to
      user.
