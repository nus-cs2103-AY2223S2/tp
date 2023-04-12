---
layout: page
title: Developer Guide
---
## Table of Contents
* **[Acknowledgements](#acknowledgements)**
* **[Setting up, getting started](#setting-up-getting-started)**
* **[Design](#design)**
    * **[Architecture](#architecture)**
    * **[UI component](#ui-component)**
    * **[Logic component](#logic-component)**
    * **[Model component](#model-component)**
        * [Task Model](#task-model)
        * [UniqueScoreList Model](#uniquescorelist-model)
    * **[Storage component](#storage-component)**
    * **[Common classes](#common-classes)**
* **[Appendix: Requirements](#appendix-requirements)**
    * **[Product scope](#product-scope)**
    * **[User stories](#user-stories)**
    * **[User cases](#use-cases)**
    * **[Non-Functional Requirements](#non-functional-requirements)**
    * **[Glossary](#glossary)**
* **[Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)**
* **[Appendix: Effort](#appendix-effort)**
* **[Appendix: Planned Enhancements](#appendix-planned-enhancements)**

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5), [Mockito](https://site.mockito.org/), [Apache PDFBox](https://pdfbox.apache.org/)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture
<img src="images/DG-images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of MATHUTORING.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/DG-images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name} Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/DG-images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

**API** : [`Ui.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<img src="images/DG-images/UiClassDiagram.png" width="350" />

More detailed Class diagram for Ui component:

<img src="images/DG-images/BetterUiClassDiagram.png" width="500" />

The UI consists of a MainWindow that is made up of different parts.
For instance, `CommandBox`, `ResultDisplay`, `StudentListPanel`, `ScoreListPanel`,
`TaskListPanel`, `StatusBarFooter`, `HelpWindow`, `ImportWindow`, `ExportWindow` and `ExportProgressWindow` etc. All theses, including the MainWindow,
inherit from the abstract UiPart class which captures the commonalities between
classes that represent parts of visible GUI.

The UI component uses the JavaFx UI framework. The layout of these UI
parts are defined in matching `.fxml` files that are in the `src/main/resources/view`
folder. For example, the layout of the `MainWindow` is specified in `MainWindow.fxml`.

The UI component,
* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be
updated with the modified data.
* keeps a reference to the `Logic` component, because the UI relies on the `Logic`
to execute the commands.
* depends on some classes in the `Model` component, as it displays `Student` object
residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/DG-images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `MathutoringParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a student).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DG-images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/DG-images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `MathutoringParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `MathutoringParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/DG-images/ModelClassDiagram.png" width="600" />


The `Model` component,

* stores the mathutoring data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Mathutoring`, which `Student` references. This allows `Mathutoring` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

</div>

<img src="images/DG-images/BetterModelClassDiagram.png" width="600" />

#### Task model
**API** : [`Task.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/model/task/Task.java)

<img src="images/DG-images/TaskClassDiagram.png" width="400" />

* A `Student` has a `UniqueTaskList` object which holds all their `Task` objects.
* Each `Task` object has a `TaskStatus` assigned to it and can be any of `INPROGRESS`, `LATE` or `COMPLETE`.
* The `creationDate` will be hidden from the user and only be used for sorting the `UniqueTaskList`.
* The `UniqueTaskList` is sorted according to `TaskStatus`, and if two tasks have the same `TaskStatus`, they will be compared using their `creationDate`.

#### UniqueScoreList model
**API** : [`UniqueScoreList.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/model/score/UniqueScoreList.java)

<img src="images/DG-images/ScoreClassDiagram.png" width="400" />

* A `Student` has only one `UniqueScoreList` object which holds all their `Score` objects.
* `UniqueScoreList` is a separate filtered list with recent score at front which is exposed to outsiders as an unmodifiable ObservableList<Student> that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* There is an inner class `ScoreSummary` inside `UniqueScoreList` class which holds the summary of recent five scores.

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/DG-images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both mathutoring data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `MathutoringStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

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

* Private Math tuition teachers
* Has a need to manage a number of students' contacts and performance
* Prefer desktop apps over other types
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**:

Tutors tend to use multiple applications to keep track of their student's contact details and performance. MATHUTORING comes into a centralised desktop application with a contact management system to track students' contact details and performance by keeping student contact details, tasks, and scores which subsequently allows the tutors to plan their lesson plan for future lessons and overall view of their schedule for ease of planning.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                | So that I can…​                                                                          |
|----------|---------|-------------------------------------------------------------|------------------------------------------------------------------------------------------|
| `* * *`  | user    | see a list of my students                                   | know who my students are and how many students I have |
| `* * *`  | user    | purge all current data                                      | get rid of sample/experimental data I used for exploring the app |
| `* * *`  | user    | create my student contacts                                  | add new students into my contact list |
| `* * *`  | user    | edit my student contacts                                    | my contact list is more extensive/flexible |
| `* * *`  | user    | delete my student contacts                                  | remove contacts of students that I don't teach anymore |
| `* * *`  | user    | use the help section                                        | learn the available commands in the application |
| `* * *`  | user    | import my data                                              | backup data and open in another device |
| `* * *`  | user    | export my data                                              | load data into a new device |
| `* * *`  | user    | add / delete / mark student’s tasking(s)                    | keep track of the task(s) that assign to a student and identify what taskings are done/in progress/overdue |
| `* * *`  | user    | add / delete student’s score(s)                             | record down the student's score(s) |
| `* * *`  | user    | check the student’s taskings                                | understand how good the student is doing |
| `* * *`  | user    | check the student’s score(s)                                | understand the student's performance in school |
| `* * `   | user    | filter my student contacts                                  | easily find a group of students based on the tag given |
| `* * `   | user    | generate a progress chart                                   | keep track of the student's progress |
| `* * `   | user    | extract students' progress report                           | show the parents their kids' performance |

### Use cases

(For all use cases below, the **System** is the `MATHUTORING` and the **Actor** is the `Tutor`, unless specified otherwise)

**1. Use case: Add a task**

**MSS**

1.  Tutor requests to list students.
2.  MATHUTORING shows a list of students.
3.  Tutor requests to add a task with a task name to a specific student in the list.
4.  MATHUTORING creates the task with given task name, task status and creation date.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given student index or task name is missing.

    * 3a1. MATHUTORING shows an error message.

      Use case resumes at step 2.

* 3b. The given student index is invalid.

    * 3b1. MATHUTORING shows an error message.

      Use case ends.

* 3c. The given command argument is invalid.

    * 3c1. MATHUTORING shows an error message.

      Use case ends.

* 3d. The given task is a duplicate of a task already in the task list of the specific student.

    * 3d1. MATHUTORING shows an error message.

      Use case ends.

**2. Use case: Delete a task**

**MSS**

1.  Tutor requests to list students.
2.  MATHUTORING shows a list of students.
3.  Tutor requests to delete a specific task from a specific student in the list.
4.  MATHUTORING deletes the task from the task list of the specific student.

Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given student index or task index is missing.

    * 3a1. MATHUTORING shows an error message.

      Use case resumes at step 2.

* 3b. The given student index is invalid.

    * 3b1. MATHUTORING shows an error message.

      Use case ends.

* 3c. The given task index is invalid.

    * 3c1. MATHUTORING shows an error message.

      Use case ends.

**3. Use case: Mark a task as late**

**MSS**

1.  Tutor requests to list students.
2.  MATHUTORING shows a list of students.
3.  Tutor requests to mark a specific task from a specific student in the list as late.
4.  MATHUTORING marks the task from the task list of the specific student as late.

Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given student index or task index is missing.

    * 3a1. MATHUTORING shows an error message.

      Use case resumes at step 2.

* 3b. The given student index is invalid.

    * 3b1. MATHUTORING shows an error message.

      Use case ends.

* 3c. The given task index is invalid.

    * 3c1. MATHUTORING shows an error message.

      Use case ends.

**4. Use case: Add a score**

**MSS**

1. Tutor requests to add a score.
2. MATHUTORING creates the score with score label, score value and score date.
3. MATHUTORING stores the score to the score list storage.

    Use case ends.

**Extensions**

* 1a. MATHUTORING detects that the score label, score value or score date is missing.

    * 1a1. MATHUTORING informs the tutor that there is missing element.

      Use case resumes at step 2.

* 1b. MATHUTORING detects that score label, score value or score date has an invalid format.

  * 1b1. MATHUTORING informs the tutor that the form of new score is invalid.

    Use case ends.

* 1c. MATHUTORING detects that the score has already exited.

    * 1c1. MATHUTORING informs the tutor that the score already exists.

      Use case ends.

**5. Use case: Delete a score**

**MSS**

1. Tutor requests to list students.
2. MATHUTORING shows a list of students.
3. Tutor requests to check specific student.
4. MATHUTORING shows a list of scores for that student.
5. Tutor requests to delete a specific score of a specific student.
6. MATHUTORING deletes the score.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given student's index is invalid.

    * 3a1. MATHUTORING informs the tutor that the index is invalid.

      Use case resumes at step 2.

* 4a. The score list is empty.

  Use case ends.

* 5a. The given student's index is invalid.

    * 5a1. MATHUTORING informs the tutor that the index is invalid.

      Use case resumes at step 1.

* 5b. The given score's index is invalid.

    * 5b1.  MATHUTORING informs the tutor that the index is invalid.

      Use case resumes at step 3.

**6. Use case: Export a student's progress**

**MSS**

1. Tutor requests to export a student's progress.
2. MATHUTORING shows an export progress window.
3. Tutor requests to save the student's progress.
4. MATHUTORING shows a browse files window.
5. Tutor requests the directory and file name of the student's progress file.
6. MATHUTORING saves the file.

    Use case ends.

**Extensions**

* 5a. The given file name is invalid.

  * 5a1. File manager informs the tutor that the file name is invalid.

    Use case resumes at step 5.

* 5b. A file with the exact same name and type exists and is currently being opened.

  * 5b1. MATHUTORING informs the tutor that the file cannot be saved because the file with the same name exists and is
  currently being opened.

    Use case resumes at step 5.

**7. Use case: Import application data via CLI**

**MSS**

1. Tutor requests to import application data.
2. MATHUTORING loads the data into the application.

    Use case ends.

**Extensions**
* 1a. MATHUTORING detects a command format error.

  Use case resumes at step 1.

* 1b. MATHUTORING detects the file does not follow the parsing format.

  Use case resumes at step 1.

**8. Use case: Import application data via GUI**

**MSS**

1. Tutor requests to import application data.
2. MATHUTORING opens Import GUI window.
3. Tutor request to upload file.
4. MATHUTORING opens the OS file explorer.
5. Tutor selects a directory to upload the data.
6. MATHUTORING saves the file.
7. MATHUTORING loads the data into the application.

    Use case ends.

**Extensions**
* 6a. MATHUTORING detects the file does not follow the parsing format.

  Use case resumes at step 3.

**9. Use case: Export application data via CLI**

**MSS**

1. Tutor requests to export application data.
2. MATHUTORING saves the file.

    Use case ends.

**Extensions**
* 1a. MATHUTORING detects a command format error.

  Use case resumes at step 1.

**10. Use case: Export application data via GUI**

**MSS**

1. Tutor requests to export application data.
2. MATHUTORING opens the OS file explorer.
3. Tutor selects a directory to save the data.
4. MATHUTORING saves the file.

    Use case ends.

**Extensions**
* 2a. File explorer closed by Tutor by mistake.

  Use case resumes at step 1.

* 2b. File explorer closed by Tutor.

  Use case ends.

**11. Use case: Export student's progress via CLI**

**MSS**

1. Tutor requests to export student's progress.
2. MATHUTORING saves the file.

   Use case ends.

**Extensions**
* 1a. MATHUTORING detects a command format error.

  Use case resumes at step 1.

* 1b. MATHUTORING detects that a file with the exact same name and type exists in the selected directory and is currently being opened.

    Use case ends.

**12. Use case: Export student's progress via GUI**

**MSS**

1. Tutor requests to export student's progress.
2. MATHUTORING shows an export progress window.
3. MATHUTORING opens the OS file explorer.
4. Tutor selects a directory and specifies the file name to save the PDF file.
5. MATHUTORING saves the file.

   Use case ends.

**Extensions**
* 2a. Export progress window closed by Tutor by mistake.

  Use case ends.

* 2b. Export progress window closed by Tutor by mistake.

  Use case ends.

* 3a. File explorer closed by Tutor by mistake.

  Use case resumes at step 2.

* 3b. File explorer closed by Tutor.

  Use case resumes at step 2.

* 4a. File name specified is invalid.

  * 4a1. File explorer informs the tutor that the file name is invalid.

    Use case resumes at step 2.

* 4b. A file with the exact same name and type exists in the selected directory and is currently being opened.

  * 4b1. MATHUTORING informs the tutor that the file cannot be saved due to a file with the same name and type in the same directory is being opened.

    Use case ends.
   
**13. Use case: Switch panel via CLI**

**MSS**
   
1. Tutor requests to switch panel.
2. MATHUTORING switch the panel.

   Use case ends.

**Extensions**
* 1a. The given command is invalid.
   
  * 1a1. MATHUTORING shows an error message.
   
    Use case resumes at step 1.
   
**14. Use case: Switch panel via GUI**

**MSS**

1. Tutor requests to switch panel.

   Use case ends.

**Extensions**
* 1a. GUI not able to render.
   
   Use case ends.
   
**15. Use case: Filter student list by student tag/s**

**MSS**
   
1. Tutor requests to filter the student/s by student tag/s.
2. MATHUTORING shows the filtered result.

   Use case ends.

**Extensions**
   
* 1a. The given command is invalid.
   
  * 1a1. MATHUTORING shows an error message.
   
    Use case resumes at step 1.
   
* 1b. The given command argument(s) are invalid.
   
  * 1b1. MATHUTORING shows an error message.
   
    Tutor case resumes at step 1.
   
**16. Use case: Check a student**

**MSS**
   
1. Tutor requests to list students.
   
2. MATHUTORING shows a list of students.
   
3. Tutor requests to check a student.
   
2. MATHUTORING shows the repsective student's task list and score list.

   Use case ends.

**Extensions**
* 2a. The student list is empty.
   
    Use case ends.
   
* 3a. The given command is invalid.
   
  * 3a1. MATHUTORING shows an error message.
   
    Use case resumes at step 2.
   
* 3b. The given index is invalid.
   
  * 3b1. MATHUTORING shows an error message.
   
    Use case resumes at step 2.
   
### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students' information without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The application should work on both 32-bit and 64-bit environments.
5.  The application should respond within 3 seconds.
6.  The user interface should be intuitive enough for users who are not IT-savvy.
7.  The product is free of charge.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Tutors**: Private math tuition teachers that will be using the application
* **Students' progress**: For our current version, the progress of a student is tracked through
the number of tasks the student has completed
* **Students' performance**: For our current version, the performance of a student is tracked through a view
of the student's scores as well as a progress chart of the latest 5 scores.
* **Index**: The number of the item shown beside its name, on the list being referred to (e.g. "index of student" refers to the index number of the student (shown beside the student's name)
  in the student list shown in the application)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: Note: These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder
   2. Double-click the jar file <br>
   Expected: Shows the GUI with a set of sample students. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   2. Re-launch the app by double-clicking the jar file.<br>
   Expected: The most recent window size and location is retained.

3. Typing `exit` into the command box
   1. Type `exit` in the command box.<br>
   Expected: The application window closes

### Adding a task for a student

1. Adding a task for a student in the student list when the student is being checked

   1. Prerequisites: Check a student using `check` command.

   2. Test case: `addtask x t/Complete Math Exercise` (where x is the index of the student being checked)<br>
   Expected: The task is added to the task list of the student. Student's name and details of the added task shown in the status message.
   The displayed task list of the checked student is updated with the added task.

2. Adding a task for a student in the student list when the student is not being checked

   1. Prerequisites: Student with index x is not being checked.

   2. Test case: `addtask x t/Complete Math Exercise` (where x is the index of student not being checked)<br>
   Expected: The task in added to the task list of the student. Student's name and details of the added task shown in the status message.
   The displayed task list is not updated since the student is not being checked.

### Deleting a task for a student

1. Deleting a task for a student in the student list when the student is being checked

   1. Prerequisites: Check a student with index x using `check` command. Checked student have one or more tasks.
   
   2. Test case: `deletetask x 1`<br>
   Expected: The first task is deleted from the task list of the checked student. Student's name and details of the deleted task shown in the status message.
      The deleted task is removed from the displayed task list of the checked student.
   
   3. Test case: `deletetask x 0`
   Expected: No task is deleted from the task list of the checked student. Error details shown in the status message.
   
   4. Test case: Other incorrect delete commands to try:`deletetask`, `deletetask x`, `deletetask x y`, `...` (where y is larger than the checked student's task list size)<br>
   Expected: Similar to previous.

### Marking a task of a student

1. Marking a task of a student as late, in progress or complete

   1. Prerequisites: Check a student with index x using `check` command. Checked student have one or more tasks.
   
   2. Test case: `markinprogress x 1`<br>
   Expected: The first task in the task list of the checked student is marked as in progress. Student's name and details of the marked task shown in the status message.
      The symbol representing the status of the task selected to be marked is yellow.

   3. Test case: `marklate x 1`<br>
   Expected: The first task in the task list of the checked student is marked as late. Student's name and details of the marked task shown in the status message.
      The symbol representing the status of the task selected to be marked is red.

   4. Test case: `markcomplete x 1`<br>
   Expected: The first task in the task list of the checked student is marked as complete. Student's name and details of the marked task shown in the status message.
      The symbol representing the status of the task selected to be marked is green.

   5. Test case: `markinprogress x 0`, `marklate x 0` or `markcomplete x 0`
   Expected: No task in the task list of the checked student is marked. Error details shown in the status message.

   6. Test case: Other incorrect delete commands to try:`markinprogress`, `marklate x`, `markcomplete x y`, `...` (where y is larger than the checked student's task list size)<br>
      Expected: Similar to previous.

### Adding a score for a student

1. Adding a score which is one of the recent five scores for a student in the student list when the student is being checked

   1. Prerequisites: Check a student using `check` command.

   2. Test case: `addscore x l/Math Miderm Paper v/96 d/2022-12-07` (where x is the index of the student being checked)<br>
      Expected: The score is added to the score list of the student and is being placed by date. 
      If it is a recent score, it will be shown at the front, otherwise, in the middle based on the date.
      Score chart and score table statistic summary will be updated accordingly.
      Student's name and details of the added score shown in the status message.
      The displayed score list of the checked student is updated with the added score.

2. Adding a score which is not one of the recent five scores for a student in the student list when the student is being checked

    1. Prerequisites: Check a student using `check` command.

    2. Test case: `addscore x l/Math Miderm Paper v/96 d/2022-12-07` (where x is the index of the student being checked)<br>
       Expected: The score is added to the score list of the student and is being placed by date.
       Since it is not a recent five scores, it will be shown in the middle or at back based on the date.
       Score chart and score table statistic summary will remain the same.
       Student's name and details of the added score shown in the status message.
       The displayed score list of the checked student is updated with the added score.

3. Adding a score for a student in the student list when the student is not being checked.

    1. Prerequisites: Student with index x is not being checked.

    2. Test case: `addscore x l/Math Miderm Paper v/96 d/2022-12-07` (where x is the index of student not being checked)<br>
       Expected: The score in added to the score list of the student and is being placed by date.
       Student's name and details of the added score shown in the status message.
       The displayed score list is not updated since the student is not being checked.

### Deleting a score for a student

1. Deleting a score which is one of the recent five scores for a student in the student list when the student is being checked

   1. Prerequisites: Check a student with index x using `check` command. Checked student have one or more scores.

   2. Test case: `deletescore x 1` (where x is the index of the student being checked)<br>
      Expected: The first score is deleted from the score list of the checked student.
      Since it is one of the five recent scores, score chart and score table statistic summary will be updated accordingly.
      Student's name and details of the deleted score shown in the status message.
      The deleted score is removed from the displayed score list of the checked student.

   3. Test case: `deletescore x 0`
      Expected: No score is deleted from the score list of the checked student. Error details shown in the status message.

   4. Test case: Other incorrect delete commands to try: `deletescore`, `deletescore x`, `deletescore x y`, `...` (where y is larger than the checked student's score list size)<br>
      Expected: Similar to previous.

2. Deleting a score which is not one of the recent five scores for a student in the student list when the student is being checked

    1. Prerequisites: Check a student with index x using `check` command. Checked student have one or more scores.

    2. Test case: `deletescore x 6` (where x is the index of the student being checked)<br>
       Expected: The sixth score is deleted from the score list of the checked student.
       Since it is not one of the five recent scores, score chart and score table statistic summary will remain the same.
       Student's name and details of the deleted score shown in the status message.
       The deleted score is removed from the displayed score list of the checked student.
   
### Checking a student

1. Checking a student for his/her task list and score list
   
   1. Test case: `check x 1`<br>
   Expected: The first student in the student list will be checked. Student's task list and score list will then be displayed on the right side of the windows.
   
   2. Test case: `check x 0`<br>
   Expected: No student will be checked, the task list and score list will not be updated. Error details shown in the status message.
   
   3. Test case: `check x -10`<br>
   Expected: No student will be checked, the task list and score list will not be updated. Error details shown in the status message.
   
   4. Test case: Other incorrect check commands to try: `check`, `check x`, `check x y`, `...` (where y is larger than the checked student's task list size)<br>
   Expected: Similar to previous.
   
2. Check a student for his/her task list and score list after filter
   
   1. Prerequisites: Filter the student list with one or more keywords k using `filter` command. 
   Expected: Similar to checking a student for his/her task list and score list, just that the check command is now working on the filtered student list produced by the filter command.
   
3. Check a student for his/her task list and score list after find
   
   1. Prerequisites: Check a student with index x using `check` command. Checked student have one or more tasks.
   Expected: Similar to checking a student for his/her task list and score list, just that the check command is now working on the filtered student list prodcued by the find command.
   
### Filtering student list
   
1. Filtering out a group of students based on the keyword/s given
   
   1. Test case: `filter x primary`<br>
   Expected: All students that has `primary` tag will be filtered out.
   
   2. Test case: `filter x primary secondary`<br>
   Expected: All students that has `primary` or `secondary` or both tag will be filtered out.
   
   3. Test case: Other incorrect check commands to try: `filter`, `filter x`, `...`
   Expected: The student list will not be filtered. Error details shown in the status message.

### Switching between score text panel and score chart panel
   
1. Switching between score text panel and score chart panel using CLI
   
   1. Test case: `switch`<br>
   Expected: The panel will be switched.
   
2. Switching between score text panel and score chart panel through mouse click
   
   1. Test case: Click on either the "text" or "chart" tab under score list<br>
   Expected: The panel will be switched.

### Exporting out student(s) data

1. Exporting student data using CLI
   1. Test case (Windows): `export p/data`
   Expected: `data` directory stored in the home folder containing the `data.json` file.

   2. Test case (Mac): `export p/data`
   Expected: `data` directory stored in the home folder containing the `data.json` file.

2. Exporting student data using GUI

   1. Test case: Click on the `File` Menu on the top left, followed by `Export`. Subsequently select a folder directory to export on then after click the export button.
   Expected: `data.json` should appear in the folder you have selected.

### Importing in student(s) data

1. Importing student data using CLI
   1. Test case (Windows): `import p/data\data.json`
   Expected: New dataset should be reflected in the application under Student List. Tasklist and Scorelist panels should be resetted as well.

   2. Test case (Mac): `import p/data/data.json`
   Expected: New dataset should be reflected in the application under Student List. Tasklist and Scorelist panels should be resetted as well.

2. Importing student data using GUI

   1. Test case: Click on the `File` Menu on the top left, followed by `Import`. Subsequently select the `data.json` from the file explorer **OR** drag and drop the file used to import in then after click the import button.
   Expected: New dataset should be reflected in the application under Student List. Tasklist and Scorelist panels should be resetted as well.

### Exporting a student's progress

1. Exporting a student's progress using CLI

    1. Test case: `exportp x` (where x is the index of the student being checked) <br>
       Expected: The student's progress will be exported into a PDF file in the default directory:
       `<JAR file location>/data`.

    2. Test case: `exportp x p/<CUSTOM_DIRECTORY>` (where x is the index of the student being checked) <br>
       Expected: The student's progress will be exported into a PDF file in the `<CUSTOM_DIRECTORY>`.

    3. Test case: Other incorrect check commands to try: `exportp`, `...` <br>
       Expected: The student's progress will not be exported. Error details shown in the status message of export progress window.

2. Exporting a student's progress through mouse click

    1. Test case: Click on the "Export Student's Progress" button in the student card. An export progress window will pop up. Click the "Click to select folder" button to select a folder to export the PDF file. Click the "Save" button to save the file.<br>
       Expected: The student's progress will be exported in the selected folder using the default file name `<STUDENT_NAME>'s Progress Report.pdf`.

    2. Test case: Click on the "Export Student's Progress" button in the student card. An export progress window will pop up. Click the "Click to select folder" button to select a folder to export the PDF file. Change the file name. Click the "Save" button to save the file.<br>
       Expected: The student's progress will be exported in the selected folder using the specified file name.

    3. Test case: Click on the "Export Student's Progress" button in the student card. An export progress window will pop up. Click the "Click to select folder" button to select a folder to export the PDF file. Change to an invalid file name. Click the "Save" button to save the file.<br>
       Expected: The student's progress will not be exported. Error details shown in the status message of export progress window.

### Saving data
The data will be automatically saved by MATHUTORING.
   
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

### Difficulty level

This is the first time our team has worked on a Brownfield project where the AB3 codebase was the largest we have encountered in our careers. At the beginning, everyone was overwhelmed by the sudden increase in the number of classes compared to our previous projects, including our most recent project (referred to as "IP"). It took us quite a while to slowly digest the purpose of each class. Additionally, for most of us, this is one of the first projects where we have had to collaborate with team members using version control tools such as Github or SourceTree.

### Challenges faced

We faced several challenges when we were working on this application, the following are some examples and not limited to:

* As we lack experience in working with the pre-existing codebase, and AB3 is a relatively large project that we did not encounter before,
we had to take some time to learn from how AB3 works, such as how the user input is handled by AB3.
* Since we are relatively new to JavaFx, we also had to spend quite some time looking at the JavaFx library and learning how to use some
specific structures when designing the GUI.
* We also had to spend time searching CSS format in order to tweak the GUI to meet our requirements.
* Thinking how to design the structure of our application based on existing AB3 structure to make it follow Object-oriented programming.
* Making use of a new library to export PDF files.

### Effort required

As a result, the development of this application required a significant amount of effort due to the challenges mentioned above where we have to:

* Debugging and looking up on both Java and JavaFX documentations.
* Researching on the usage of the new library (PDFBox).
* Reading on guides such as tweaking the GUI to fulfil our requirements and an overall better user experience (Drag-and-Drop/CSS).

### Achievements

We were able to produce a complete application that fulfills our user requirements as well as overcame the challenges we have faced. With MATHUTORING, private Math tuition teachers can:

* Manage student contact details and track their performance with task and score records.
* View score charts and statistics to easily evaluate student progress.
* Generate PDF reports containing a student's tasks and scores for easy sharing.
* Export and import data to easily transfer to a new device.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

### Solutions proposed for known feature flaws

Listed are some feature flaws that were found in the current implementation of MATHUTORING. To improve the features of
future implementations, some solutions have been provided.

#### 1. Students with the same name are not allowed

The reason why we do not allow any student to have the same name is to avoid potential confusion. Although the user will
still be able to distinguish them by checking their personal information (e.g. their email, their address etc.), it would
be more straight forward to see a difference in name, i.e. Jason 1 and Jason 2. <br>
Nevertheless, we should consider to allow student to have the same name.

**Solution proposed:**

* Instead of not allowing students to have the same name, we do not allow any students to have both the same name and the
  same email.


#### 2. Numeric student names are allowed

Currently, student names can be numeric. i.e. student name can be "1". After some research we found out that most countries in the world do not allow for purely numeric name.

**Solution proposed:**

* Alter the behavior to only accept alphanumeric or alphabetic names
* Change the name regex to `[\\p{Alnum}]*[a-zA-Z][\\p{Alnum} ]*`


#### 3. Window does not reopen after it is minimized

After help/export/import window is minimized, clicking help/export/import on the top menu will not automatically show
the window again.

**Solution proposed:**

* Similarly to how ```getRoot().isShowing()``` is used to check if the window is shown, there is also a function called
```getRoot().isIconified()``` to check whether the window is minimised (returns ```true``` if minimised).
We can then return ```getRoot().setIconified(false)``` to restore the window to its previous state.


#### 4. Exams are not allowed to be on the same date

The aim of the score list is to keep track of a student's recent performance trend (especially the chart).
If the majority of the exams are conducted on the same date, it defeats our purpose of having the score records.<br>
However, it is a reasonable assumption that more than one exam is conducted on the same date.

**Solution proposed:**

* Allow at most five exams on the same date.


#### 5. Score table being cut after resizing the application window

This problem might happen after user resizes the application window, which can result in the horizontal scroll bar covering
part of the displayed score table.<br>
**Suggestion to be able to view the entire table:** Try to enlarge the window size, or click on the table and scroll up and down.

**Solution proposed:**

* Although the entire table should be able to be seen by the user after following the above suggestion, it may still hinder the user.
* The main reason the table is cut is due to the usage of JavaFX TableView. By default, it comes with a scrollbar and is relatively hard to customize.
* To resolve the feature flaw, JavaFX label will be used to construct the table with CSS instead of using the JavaFX TableView.


#### 6. Edit the tags will overwrite the whole list of tags

* Currently, if the user wants to add tags to students in the list, they can only use `edit` command and will need to input all existing tags along with the new tag.
* Also, if the user wants to edit a specific tag, they will also be required to input all existing tags along with the one they want to edit.
* There are currently no delete operations for tags.
* Without these operations, it is very hard to manage tags since the only way to do it is by using the `edit` command which is deteriorates the user experience.

**Solution proposed:**

* To solve this issue, at least two more commands can be created which are `addtag` and `deletetag`.
* With these new commands, the user will no longer be required to input the existing tags when adding a new tag.

#### 7. Inconsistency in score color in Score Chart and PDF file

There is an inconsistency for score color between score chart and PDF:
* Score chart:
  * Green color is used to indicate score values that are greater than or equal to 80.
  * Yellow color is used to indicate score values that are greater than or equal to 50.
  * Red color is used to indicate score values that are smaller than 50.
* PDF:
  * Green color is used to indicate score values of 100.
  * Yellow color is used to indicate score values that are smaller than 80.
  * Red color is used to indicate score values that are smaller than 50.

**Solution proposed:**

* To solve this issue, the criteria for the score color in the PDF file can be changed to follow the criteria of score color in the score chart.


#### 8. Change the tags of the sample data

Currently, the sample data contains tags, like `colleagues` and `friends`, that do not really fit the purpose of our application.

**Solution proposed:**

* To solve this issue, the tag can be changed to grade (primary, secondary, etc.) and gender.
