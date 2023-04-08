---
layout: page
title: Developer Guide
---
* Table of Contents 
{:toc}

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
<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

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

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The UI consists of a MainWindow that is made up of different parts.
For instance, `CommandBox`, `ResultDisplay`, `StudentListPanel`, `ScoreListPanel`,
`TaskListPanel`, `StatusBarFooter` etc. All theses, including the MainWindow,
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

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `MathutoringParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `MathutoringParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `MathutoringParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the mathutoring data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Mathutoring`, which `Student` references. This allows `Mathutoring` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

#### Task model
**API** : [`Task.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/model/task/Task.java)

<img src="images/TaskClassDiagram.png" width="280" />

* A `Student` has a `TaskList` object which holds all their `Task` objects.
* Each `Task` object has a `TaskStatus` assigned to it and can be any of `INPROGRESS`, `LATE` or `COMPLETE`.
* The `creationDate` will be hidden from the user and only be used for sorting the `TaskList`.

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103-W17-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both mathutoring data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `MathutoringStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

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

* Private math tuition teachers
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
| `* * *`  | user    | edit my student contacts                                    | my contact list is more extensive/flexible                                               |
| `* * *`  | user    | delete my student contacts                                  | remove contacts of students that I don't teach anymore                                   |
| `* * *`  | user    | use the help section                                        | learn the available commands in the application                                          |
| `* * *`  | user    | import my data                                              | backup data and open in another device                                                   |
| `* * *`  | user    | export my data                                              | load data into a new device                                                              |
| `* * *`  | user    | delete / mark student’s tasking(s)                          | identify what taskings are done/obsolete                                                 |
| `* * *`  | user    | check the student’s taskings                                | understand how good the student is doing                                                 |
| `* * `   | user    | filter my student contacts                                  | look up on a single student/students of the same level instead of reading through a list |
| `* * `   | user    | have a secure delete of my data                             | prevent myself from accidentally deleting information                                    |
| `* * `   | user    | create a progress report                                    | keep track of the student's progress                                                     |
| `* * `   | user    | edit the student’s tasking                                  | edit the information of the student's tasking                                            |
| `* * `   | user    | see a calendar                                              | view on which day I have classes                                                         |
| `* * `   | user    | extract students' progress report                           | show the parents their kids' performance                                                 |
| `* `     | user    | note down a more detailed class description                 | know what I need to do for a certain class                                               |
| `* `     | user    | filter the calendar                                         | see clearly how many classes I have within a period of time (week/month, etc.)           |
| `* `     | user    | be able to do a wildcard search                             | know what I can do on the app if I forgot the exact command I want to execute            |
| `* `     | user    | indicate whether a student has paid the tuition fee         | easily remember which student hasn't paid the tuition fee                                |
| `* `     | user    | export my data to the cloud                                 | save my data online                                                                      |
| `* `     | user    | export the calendar data                                    | backup the calendar data and import the data to a calendar application                                   |
| `* `     | user    | have a reminder                                             | remember what classes I have for tomorrow                                                |
| `* `     | user    | auto send an email to the student to confirm the attendance | know whether the student will attend the class or not and decide whether I should conduct the class     |
| `* `     | user    | indicate whether the student attends the class              | view the student's attendance record                                                     |
| `* `     | user    | auto send an email to remind the student about the tuition fee payment  | eliminate my task of manually reminding the student to pay the tuition fee                                                                |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `MATHUTORING` and the **Actor** is the `Tutor`, unless specified otherwise)

**Use case: Delete a student**

**MSS**

1.  Tutor requests to list students.
2.  MATHUTORING shows a list of students.
3.  Tutor requests to delete a specific student in the list.
4.  MATHUTORING deletes the student.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. MATHUTORING shows an error message.

      Use case resumes at step 2.

* 3b. The given command argument(s) are invalid.
    * 3b1. MATHUTORING shows an error message.

      Use case resumes at step 2.

**Use case: Update a student**

**MSS**

1.  Tutor requests to list students.
2.  MATHUTORING shows a list of students.
3.  Tutor requests to edit a specific student in the list.
4.  MATHUTORING edits the student.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. MATHUTORING shows an error message.

      Use case resumes at step 2.

* 3b. The given command argument(s) are invalid.

    * 3b1. MATHUTORING shows an error message.

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

**MSS**

*{More to be added}*

**Extensions**

*{More to be added}*

**Use case: Add a score**

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

    * 1c1. MATHUTORING informs the tutor that the score has already exited.

      Use case ends.

**Use case: Delete a score**

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

**Use case: Export a student's progress**

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

**Use case: Import application data via CLI**

**MSS**

1. Tutor requests to import application data.
2. MATHUTORING loads the data into the application.

    Use case ends.

**Extensions**
* 1a. MATHUTORING detects a command format error.

  Use case resumes at step 1.

* 1b. MATHUTORING detects the file does not follow the parsing format.

  Use case resumes at step 1.

**Use case: Import application data via GUI**

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

**Use case: Export application data via CLI**

**MSS**

1. Tutor requests to export application data.
2. MATHUTORING saves the file.

    Use case ends.

**Extensions**
* 1a. MATHUTORING detects a command format error.

  Use case resumes at step 1.

**Use case: Export application data via GUI**

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

**Use case: Export student's progress via CLI**

**MSS**

1. Tutor requests to export student's progress.
2. MATHUTORING saves the file.

   Use case ends.

**Extensions**
* 1a. MATHUTORING detects a command format error.

  Use case resumes at step 1.

* 1b. MATHUTORING detects that a file with the exact same name and type exists in the selected directory and is currently being opened.

    Use case ends.

**Use case: Export student's progress via GUI**

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

### Deleting a student

### Saving data

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancements**

### Solutions proposed for known feature flaws

Listed are some feature flaws that were found in the current implementation of MATHUTORING. To improve the features of
future implementations, some solutions have been provided.

#### 1. Score table being cut after resizing the application window

This problem might happen after user resizes the application window, which can result in the horizontal scroll bar covering
part of the displayed score table.<br>
**Suggestion to be able to view the entire table:** Try to enlarge the window size, or click on the table and scroll up and down.

**Solution proposed:**

* Although the entire table should be able to be seen by the user after following the above suggestion, it may still hinder the user.
* The main reason the table is cut is due to the usage of JavaFX TableView. By default, it comes with a scrollbar and is relatively hard to customize.
* To resolve the feature flaw, JavaFX label will be used to construct the table with CSS instead of using the JavaFX TableView.


#### 2. Numeric student names are allowed

Currently, student names can be numeric. i.e. student name can be "1". After some research we found out that most countries in the world do not allow for purely numeric name.

**Solution proposed:**
* Alter the behavior to only accept alphanumeric or alphabetic names
* Change the name regex to `[\\p{Alnum}]*[a-zA-Z][\\p{Alnum} ]*`


#### 3. Window does not reopen after it is minimized

After help/export/import window is minimized, clicking help/export/import on the top menu will not automatically show 
the window again.

**Solution proposed:**
* Similarly to how ```getRoot().isShowing()``` is used to show the window, there is also a function called ```getRoot().isIconified()``` to check whether the window is minimised (returns ```true``` if minimised). We can then return ```getRoot().setIconified(false)``` to restore the window to its previous state.

#### 4. Exams are not allowed to be on the same date

The aim of the score list is to keep track of a student's recent performance trend (especially the chart). 
If the majority of the exams are conducted on the same date, it defeats our purpose of having the score records.<br>
However, it is a reasonable assumption that some exams are conducted on the same date.

**Solution proposed:**

* Allow at most five exams on the same date.


#### 5. Students with the same name are not allowed

The reason why we do not allow any student to have the same name is to avoid potential confusion. Although the user will
still be able to distinguish them by checking their personal information (e.g. their email, their address etc.), it would
be more straight forward to see a difference in name, i.e. Jason 1 and Jason 2. <br>
Nevertheless, we should consider to allow student to have the same name.

**Solution proposed:**

* Instead of not allowing students to have the same name, we do not allow any students to have both the same name and the 
same email.
