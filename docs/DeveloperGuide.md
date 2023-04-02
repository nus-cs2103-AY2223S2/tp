## PowerConnect Developer Guide

PowerConnect is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PowerConnect can get your contact management tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## **Table of Contents**
1. [Acknowledgements](#acknowledgements)
2. [Setting Up, Getting Started](#setting-up-getting-started)
3. [Design](#design)
4. [Implementation](#implementation)<br>
   1. [Student Delete Feature](#delete-student-feature)
   2. [Attendance Feature](#attendance-feature)<br>
   3. [Grade Feature](#grade-feature)<br>
   4. [Parent/NOK Edit Feature](#parentnok-edit-feature)<br>
5. [Proposed Features](#proposed-features)
6. [Documentation, Logging, Testing, Configuration, Dev-ops](#documentation-logging-testing-configuration-dev-ops)
7. [Appendix](#appendix-requirements)<br>
   1. [Appendix-Requirements](#appendix-requirements)<br>
      1. [Product Scope](#product-scope)<br>
      2. [User Stories](#user-stories)<br>
      3. [Use Cases](#use-cases)<br>
      4. [Non-Functional Requirements](#non-functional-requirements)<br>
      5. [Glossary](#glossary)<br>
   2. [Appendix-Instructions for Manual Testing](#appendix-instructions-for-manual-testing)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting Up, Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StoragePowerConnect.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

--------------------------------------------------------------------------------------------------------------------

### Delete student feature

#### Current Implementation
PowerConnect allows users to delete a student from the `UniqueStudentList` of `Class`.

When the user enters the delete student command, `MainWindow#executeCommand()` will be called. It will then call
`Logic#execute` which will return a `CommandResult`. The `execute` method is facilitated by `StudentDeleteCommand`
class. When `StudentDeleteCommand#execute` is called, it will call `deleteStudent()` method from model. This would call
`removeStudent` method from Class which in turn deletes the student from the `UniqueStudentList`.

##### General Flow for StudentDeleteCommand

The flow for StudentDeleteCommand#execute is as such:

The `StudentClass` and `IndexNumber` to be used for deleting is retrieved from the user input

The user inputs will be parsed into the `StudentCommandParser` which will then return a new `StudentDeleteCommand`

The StudentDeleteCommand will then be immediately executed to delete the student from their UniqueStudentList via class and index number

The delete success message and result list of students will then be shown back to the user via the dashboard

Full implementation sequence diagram

![Sequence Diagram](images/DeleteSequentialDiagram.png)

### Design considerations
We want to keep it simple for the user to delete students, using students' class and index number is sufficient to
identify the student that needs to be deleted.

1.Exception is thrown immediately if either student class or index number is invalid
2.Remove the student from the parent's list of children too so the necessary changes will be displayed

#### Aspect: How to delete students

* **Alternative 1 (current choice):** Having a studentList for each class
    * Pros: When deleting students, we do not have to go through all the students stored, improving its time complexity
    * Pros: More flexible to add more class related features
    * Pros: Displayed list of students will be neater
    * Cons: Difficult to manage
    * Cons: Harder to implement
    * Cons: Uses more memory
    * Cons: Harder to access and manipulate student data

* **Alternative 2:** Allowing the users to delete a particular field of the student
    * Pros: More flexibility for users
    * Cons: Hard to implement, need to check the different prefixes to determine which field to delete

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Attendance feature

#### Current Implementation

The attendance feature is facilitated by `Attendance`. It is composed by a `Person` with an `Attendance` object.

Given below is an example usage scenario and how the attendance mechanism behaves at each step

Step 1. The user launches the application for the first time
Step 2. The user creates a student using the Add command. The `Attendance` of `Students` will be initialized with the initial attendance state (F) indicating that the student is absent.

[//]: # (![Student Add Command]&#40;images/StudentAddCommand.png&#41;)
Step 3. The user wants to mark a particular student as present. The user executes the `Attendance` command with the index of the student and att/T. The `Attendance` of the student will be updated to the current date.

![Attendance Command](images/MarkAttendance.jpg)

Step 4. The attendance is saved to the storage file automatically after each command. Attendance is saved as the string representation of LocalDate in JsonAdaptedAttendance.

Full implementation sequence diagram

![Sequence Diagram](images/AttendanceSequenceDiagram.jpg)

### Design considerations
We want to make it easy for the user to set current date as present. Thus we allowed the user to set attendance as T which will automatically register as present today

#### Aspect: How to store attendance

* **Alternative 1 (current choice):** Store attendance as a JsonAdaptedAttendance object that records the string representation of LocalDate
    * Pros: Easy to access and manipulate attendance data
    * Pros: Easy to read and write to storage
    * Pros: Flexible to add more features if more features are added eg. mark as MC, Late..
    * Cons: Hard to implement

* **Alternative 2:** Store attendance as a string representation of LocalDate
    * Pros: Easy to implement
    * Pros: Use less memory
    * Cons: Hard to access and manipulate attendance data
    * Cons: Hard to read and write to storage
    * Cons: Hard to add more features if more features are added eg. mark as MC, Late..
<br><br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Grade feature

#### Current Implementation

The grade feature is facilitated by `grade`. It is composed by a `Student` with an `Assignment` object.

Given below is an example usage scenario and how the attendance mechanism behaves at each step

Step 1. The user launches the application for the first time
Step 2. The user creates a student using the Add command. The `Test` and `Homework` which extends `Assignment` of `Students` will be initialized with the initial name: Insert student test/homework here!, Score: -100, Weightage: -100, Deadline: No Deadline and Is Done(Homework): false.

[//]: # (![Student Add Command]&#40;images/StudentAddCommand.png&#41;)
Step 3. The user wants to add a test to a student. The user executes the `grade` command with the index of the student and test/TEST_NAME. The `test` of the student will be updated to the name.

![Grade Command](images/Grade.png)

Step 4. The test/homework is saved to the storage file automatically after each command.

Full implementation sequence diagram

![Sequence Diagram](images/GradeSequentialDiagram.png)

### Design considerations
We want to make it easy for the user to set tests without inputting all the details at one go, with the test/homework name being the only field compulsory.

#### Aspect: How to store test

* **Alternative 1 (current choice):** Store test as a JsonAdaptedTest object
    * Pros: Easy to access and manipulate attendance data
    * Pros: Easy to read and write to storage
    * Pros: Flexible to add more features if more features are added
    * Cons: Hard to implement

* **Alternative 2:** Store attendance as a string representation of Test details
    * Pros: Easy to implement
    * Pros: Use less memory
    * Cons: Hard to access and manipulate attendance data
    * Cons: Hard to read and write to storage
    * Cons: Hard to add more features if more features are added
<br><br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Parent/NOK Add Feature

#### Current Implementation
The add feature for parent/NOK is facilitated by `parent add`.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Parent/NOK Edit Feature

#### Current Implementation
The edit feature for parent / NOK is facilitated by `parent edit`. <br>

Given below is an example usage scenario and how the edit mechanism behaves at each step.
<br>
1. User launches the application for the first time. <br>
2. User creates a new `Parent` using ***Parent Add Command*** or gets a new `Parent` created by PowerConnect while executing the ***Student Add Command***. <br>
3. During creation of `Parent`, User encountered one of the two scenarios and requires to edit the `Parent`:
   1. Did not include certain particulars of the `Parent`. 
   2. Keyed in wrong particulars for the `Parent`.
4. User wants to edit the `Parent` particulars and hence executes the `parent edit` command with the `Name` and `Phone Number` of the corresponding `Parent` and the `Particulars` to be edited.<br>
   Example of parent edit command: `"parent edit n/Tan Ah Niu pnP/91234567 npnP/65656565"` <br>
5. Edited `Parent` is saved to the storage file automatically after the command. <br><br>

**Activity Diagram**
![Activity Diagram](images/ParentEditActivityDiagram.png)

**Full implementation sequence diagram**
![Sequence Diagram](images/ParentEditSequentialDiagram.png)

**Reference to get parent particulars sequence diagram**
![Sequence Diagram](images/ParentEditParserUtilSequentialDiagram.png)


### Design considerations
We want to make it easy for users to edit `Parent / NOK` particulars without manually deleting the `Parent / NOK` and creating a new `Parent / NOK` and reassigning each `Student` attached to original `Parent / NOK` with the new `Parent / NOK`.
<br><br>
We also do not want to trouble user with inputting multiple **PREFIXES** to edit `Parent / NOK`. Hence, users only need to input ***PREFIXES*** belonging to the edited information and the following:
1. Parent's / NOK's `Name`
2. Parent's / NOK's `Phone Number`
3. Particulars that are being amended<br><br>

#### Aspect: How Parent Edit executes
* **Alternative 1 (current choice):** Run the command with `parent edit n/<NAME> pnP/<PARENT_PHONE_NUMBER>` + PREFIXES and details of information that are being changed
    * Pro: Easy to use
    * Pro: Minimal input by user to edit `Parent / NOK` particulars
    * Con: Slightly harder than Alternative 2 to implement
    * Con: Need to test the ***Parent Edit Command*** exhaustively / come up with automated tests to ensure when creating the new `Parent / NOK`, system takes input from original `Parent / NOK` for particulars that are not amended.
* **Alternative 2:** User provides **ALL** particulars of the `Parent / NOK` to be edited even if **SOME** of the particulars are the **SAME** as original. From these details, system will create a new `Parent / NOK` and replace the original copy of `Parent / NOK` with it.
    * Pro: Easy to implement
    * Pro: Makes use of existing features **Parent Add** and **Parent Delete**
    * Con: Troublesome for the user as there is a need to input **ALL** particulars of a `Parent / NOK` even if he/she is just amending one of the particulars of the `Parent / NOK`.
    * Con: It may cause regressions, user may key in wrong details for some of the `Parent / NOK` particulars as he/she is keying in the command. This will cause much inconvenience for Users especially when the `Parent / NOK` they are amending has **TONS** of information.
    * Con: It is no longer **FAST** and **EASY** for the user to use.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## Proposed Features

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Documentation, Logging, Testing, Configuration, Dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product Scope

**Target user profile**:

* This product mainly targets secondary school teachers that prefer CLI over GUI,  manage large groups of students and require ease of access to students' information.

**Value proposition**:

* Have many students in different classes / co-curricular activities (CCAs) that have different phone number that needs to be kept track of
* Need to keep track of students’ parents / next-of-kins’ contact details for consent forms
* Teachers may not be able to match students names and faces well and so this app serves as an easy way to identify students (since there are too many students)
* Keep track of homework given and deadline
* Streamline administration processes because they have a lot of stuff to keep track of (eg. Attendance, assignments, grades, contact details)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### User Stories

| No  | As a...                     | I can...                                                                    | So that...                                                                                                                                 | Notes                                                                                                                                     | Priority |
|-----|-----------------------------|-----------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|----------|
| 1   | Teacher                     | Record student’s attendance                                                 | I know that my student is present in class (in school).                                                                                    |                                                                                                                                           | High     |
| 2   | Teacher                     | Record students’ assessment and test grades.                                | I am able to manage my students’ performance                                                                                               |                                                                                                                                           | High     |
| 3   | Teacher                     | Have an efficient way to view and track my students’ attendance.            | I will have an easier time managing my students’ attendance over the year                                                                  |                                                                                                                                           | High     |
| 4   | Teacher                     | Have the option to leave some details empty                                 | I am able to key in my students’ information when there’s missing information                                                              |                                                                                                                                           | High     |
| 5   | Teacher                     | Document students’ progress                                                 | I am able to record easily the students’ performance                                                                                       |                                                                                                                                           | High     |
| 6   | Teacher                     | Organise my students by class                                               | I am able to identify which class they belong to                                                                                           | Determine whether to create separate classes: “class” class and “student” class or merge                                                  | High     |
| 7   | Non tech-savvy teacher      | Learn about new commands                                                    | I am able to use the application effectively and save unnecessary time and effort in recording students’ particulars and searching for it. | Provide User Guide that is easy to understand                                                                                             | High     |
| 8   | Teacher                     | Recognise my students from different classes through their photos           | I am able to recognise my students' looks                                                                                                  | Long setup process for user as they need to manually input images for individual student<p></p> Keeping separate file / folder for images | Medium   |
| 9   | Caring Teacher              | Provide comments for each student                                           | I am able to keep track of students who need meaningful feedback and support.                                                              | (comments section on each student)                                                                                                        | Medium   |
| 10  | Teacher / teacher assistant | Have an efficient way to retrieve my students’ contact details.             | I am able to contact them easily                                                                                                           | Assumption: student details are known to teachers / TAs                                                                                   | Medium   |
| 11  | Teacher                     | Keep track of students who have submitted assignment                        | I am aware of which student missed the deadline for their work                                                                             |                                                                                                                                           | Medium   |
| 12  | Motivational teacher        | Sort my students by overall grades                                          | I am able to foster a positive and supportive learning environment                                                                         |                                                                                                                                           | Medium   |
| 13  | Teacher                     | Use an application that is easy to navigate about and has a nice interface. | I am able to save unnecessary time and effort in recording students’ particulars and searching for it.                                     | Issue under designs                                                                                                                       | Low      |
| 14  | Responsible Teacher         | Access student records, such as grades and attendance                       | I will be able to make informed decisions about student progress                                                                           | Summary of all students grades for a particular test / class                                                                              | Low      |
| 15  | Busy teacher                | Have a todo list                                                            | I am able to keep track of what to do                                                                                                      |                                                                                                                                           | Low / NA |
| 16  | Teacher                     | Use an application with a reminder system                                   | I am able to track the things that needs to be done                                                                                        |                                                                                                                                           | NA       |
| 17  | Teacher                     | Efficiently and effectively assign assessments and assignments              | I am able to better assess my students’ learning and progress                                                                              |                                                                                                                                           | NA       |
| 18  | Teacher                     | Managing students’ behaviours and actions                                   | I am able to promote a safe and productive learning space for all                                                                          |                                                                                                                                           | NA       |
| 19  | Teacher                     | Add individual feedback to students                                         | I am able to provide feedback to parents/guardians effectively                                                                             | Duplicate point to Point 7                                                                                                                | NA       |
| 20  | Teacher                     | Access my students contacts conveniently                                    | I am able to build a closer relationship with my students and their next of kin                                                            | Too vague                                                                                                                                 | NA       |
| 21  | Busy Teacher                | Keep track of my teaching feedback                                          | I can keep improving my teaching practices                                                                                                 | Hard to get school admin team on board                                                                                                    | NA       |
| 22  | Efficient Teacher           | Integrate technology into my teaching                                       | I am able to enrich my student learning                                                                                                    | Vague                                                                                                                                     | NA       |
| 23  | Teacher assistant           | Retrieve my students contact                                                | I am able to look them up easily                                                                                                           | Duplicate Point to Point 10                                                                                                               | NA       |
| 24  | Teacher                     | Track the date and time of my classes                                       | I will be able to reach my classes on time                                                                                                 |                                                                                                                                           | NA       |
| 25  | Forgetful teacher           | Record attendance                                                           | I am able to keep track of my students                                                                                                     | Duplicate to Point 5                                                                                                                      | NA       |
| 26  | Teacher                     | Identify weaker students                                                    | I can give them more attention in class                                                                                                    | Duplicate to Point 20                                                                                                                     | NA       |
| 27  | Teacher                     | Manage my consultation timings                                              | I am able to organise my schedule                                                                                                          |                                                                                                                                           | NA       |
| 28  | Teacher                     | Set reminders for my consultation timings                                   | I am able to ensure I am not late for consultations                                                                                        |                                                                                                                                           | NA       |
| 29  | Teacher                     | Marks my students’ assignments automatically                                | I am able to use time and effort spent on it on other use, such as thinking of ways to improve my students’ learning                       | Difficult to implement Autograder                                                                                                         | NA       |
| 30  | Teacher                     | Have an application that is used as CLI instead of GUI                      | I am able to make use of my fast typing speed to sort out all the administrative work                                                      | Too Vague                                                                                                                                 | NA       |
| 31  | Busy Teacher                | Easily manage my schedule                                                   | I am able to spend more time on other things                                                                                               | Out of scope                                                                                                                              | NA       |
| 32  | Busy Teacher                | Know which tasks to prioritise                                              | I am able to ensure all of my responsibilities are taken care of in a timely and efficient manner.                                         |                                                                                                                                           | NA       |
| 33  | Teacher                     | Track the date and time of the classes that I have                          | I am able to reach on time and teach the correct module for the particular lesson slot.                                                    | Out of scope                                                                                                                              | NA       |
| 34  | Teacher                     | Amend date and time of certain lesson timings                               | I am able to change lesson dates and timings easily when lessons shift                                                                     |                                                                                                                                           | NA       |

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Use Cases

***For all use cases below, the System is PowerConnect and the Actor is the teacher, unless specified otherwise***

**Preconditions:** `class` and `index number` of the `student`

**Use Case: UC01 - Adding `grade` for a `student`**

**MSS:**
1. User keys in the `test` name, `index number` of student and corresponding `grade`
2. System displays feedback to the user that grade has been successfully added for the student

   Use case ends.

**Extensions:**

+ 1a. User keyed in invalid `index number` / does not **SATISFY** Precondition.
    + 1a1. System displays an error message indicating `index number` is invalid.
    + 1a2. System output of all `students` particulars in his/her class.
    + 1a3. User checks for the `index number` of the `student` and keys into the system.

      Use case resumes at step 2.
+ 1b. Test name is invalid.
    + 1b1. System displays an error message indicating wrong test name.
    + 1b2. User adds a new test name.

      Use case resumes at step 2.
+ 1c. User gave a `Grade` value that is not of an integer.
    + 1c1. System displays an error message indicating the wrong grade given.
    + 1c2. User checks and adds the `grade` input again.

      Use case resumes at step 2. <br><br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

**Use Case: UC02 - Adding `comment` for a `student`**

**MSS:**
1. User keys in the `index number` of student and the corresponding `comment` for the student
2. If the student has an existing `comment`, the system will request the user to confirm the change of comments. Otherwise, skip to step 3.
3. System displays feedback to the user that the comment has been successfully added for the `student`.

   Use case ends.

**Extensions:**

+ 1a. User keyed in invalid `index number` / does not **SATISFY** Precondition.
    + 1a1. System displays an error message indicating the `index number` is invalid.
    + 1a2. System output of all `students` particulars in his/her class.
    + 1a3. User checks for the index number of the `student` and keys into the system.

      Use case resumes at step 2.

+ 2a. User informs the system to **NOT** change the existing comment for the `student`.
    + 2a1. System displays an error message indicating the current process of adding a new comment for the `student` has
      ended.

      Use case ends. <br><br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

**Use Case: UC03 - Adding a new `student` to an existing `class`**

**MSS:**
1. User keys in **ALL COMPULSORY** details and any other **OPTIONAL** details as part of student’s particulars
2. System displays feedback to the user that the `student` has been successfully been created and added to the respective `class`

   Use case ends.

**Extensions:**

+ 1a. User did not enter **ALL COMPULSORY** details.
    + 1a1. System displays an error message to the user indicating that there is insufficient information given to create the new `student`.

      Use case ends.

+ 1b. User keys in invalid **COMPULSORY** or **OPTIONAL** information
    + 1b1. User keys in invalid `SEX` type not supported by system or `SEX` type contain numbers.
    + 1b2. User keys the same information for the student's `name` and NOK’s `name`.
    + 1b3. User keys in `birthdate` or `phone number` that are not of an integer.
    + 1b4. User keys in invalid path to `image`.
    + 1b5. System displays an error message to the user indicating that he/she has keyed in wrong information for the `student` along with a sample of the correct way to key in information for a new `student`.

      Use case ends.

+ 2a. User is trying to create a new `student` whose index number belongs to an existing `student` in the class.
    + 2a1. System displays an error message to the user indicating that a `student` with the same `index number` already exists in the `class`.

      Use case ends. <br><br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

**Use Case: UC04 - Listing all `students` in the selected `class`**

**MSS:**
1. User keys in the command to view the list of all `students` in the selected `class`
2. System displays all `students` particulars in the `class`

   Use case ends.

**Extensions:**

+ 1a. User keys in an invalid `class`.
    + 1a1. System displays an error message to the user informing him/her the `class` is invalid.

      Use case ends.

+ 2a. Selected `class` does not have any `students`
    + 2a1. System displays an empty list.

      Use case ends.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

**Use Case: UC05 - Editing `personal details` of `students`**

**MSS:**
1. User enters the `type` of personal detail and corresponding `detail` to edit
2. System displays that the personal detail of the student has been changed successfully

   Use case ends.

**Extensions:**

+ 1a. User did not enter **ALL COMPULSORY** details.
    + 1a1. System displays an error message

      Use case ends.

+ 1b. User keys in invalid **COMPULSORY** or **OPTIONAL** information
    + 1b1. System displays an error message with an example on how to use the command.

      Use case ends.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

**Use Case: UC06 - Finding `student` by `student id`**

**MSS:**
1. User keys in the `class` and `index number` of the student he/she is finding
2. System displays the student with all his/her corresponding personal details

   Use case ends.

**Extensions:**

+ 1a. User did not enter **ALL COMPULSORY** details.
    + 1a1. System displays an error message.

      Use case ends.

+ 1b. User keys in invalid **COMPULSORY** information
    + 1b1. System displays an error message with an example on how to use the command.

      Use case ends.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

**Use Case: UC07 - Deleting `student` or `student information` from the `database`**

**MSS:**
1. User keys in the command to delete a student or student information
2. System displays that the student or student information has been deleted successfully

   Use case ends.

**Extensions:**

+ 1a. User did not enter **ALL COMPULSORY** details.
    + 1a1. System displays an error message.

      Use case ends.

+ 1b. User keys in invalid **COMPULSORY** or **OPTIONAL** information
    + 1b1. System displays an error message with an example on how to use the command.

      Use case ends.

*{More to be added}* <br></br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java `11` or above installed.
2. Should be able to handle up to 400 students without a noticeable sluggishness in performance for typical usage.
3. Should package it as a JAR file that is smaller than 50mb
4. Should store data in a txt/csv file
5. PowerConnect should be able to work without any internet access.
6. PowerConnect should only be used by a single user per installation and not by multiple users.
7. Users with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish the majority of the tasks faster using CLI commands than using the mouse.
8. PowerConnect should be able to handle all user input errors gracefully.
9. PowerConnect should be able to display all error messages in a user-friendly manner.
10. PowerConnect should be able to run on a 32-bit system with 8GB of RAM.
11. PowerConnect should be able to display all success messages within 1 second.

*{More to be added}* <br></br>

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Glossary

**Attributes**: Information of a student / parent. <br> 
For example, name, phone number, email address etc <br><br>
**CCA**: Co-curricular activities <br><br>
**CLI**: Command Line Interface <br><br>
**Hard disk**: Non-volatile data storage device, your OS's storage in short <br><br>
**LMS**: Learning Management System, application used by schools to provide students a platform to access their lessons materials online. <br>
Examples of LMS: Canvas, LumiNUS, Blackboard, Google Classroom, Quizlet <br><br>
**NOK**: Refers to Next-of-Kin, could be either blood related or a guardian <br><br>
**OS**: Operating Systems <br>
Examples of OS: Windows, Linux, Unix, OS-X <br><br>
**Parameters**: The actual information of a student/parent
For example, Tan Ah Kow, 91234567 etc.

*{More to be added}*

<div style="page-break-after: always;"></div>

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

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

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

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------