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

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-T15-4/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/java/tfifteenfour/clipboard/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/java/tfifteenfour/clipboard/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/java/tfifteenfour/clipboard/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/java/tfifteenfour/clipboard/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/java/tfifteenfour/clipboard/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `RosterParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete course 1")` API call.

![Interactions Inside the Logic Component for the `delete course 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `RosterParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `RosterParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/java/tfifteenfour/clipboard/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the roster data i.e., all `Course` objects (which can subsequently be traversed to access all stored data)
* stores a `CurrentSelection` object that is a container object for pointing to the various `Course`/`Group`/`Student`/`Session`/`Task` objects that the user has selected.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* stores the `Command` object that was last executed, as well as its corresponding command string input, for purposes of accessing previous states (i.e like in the case of `undo` command)
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Course` list in the `CLIpboard`. This allows `CLIpboard` to only require one `Course` object.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-T15-4/tp/blob/master/src/main/java/tfifteenfour/clipboard/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both roster data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `RosterStorage` and `UserPrefsStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `tfifteenfour.clipboard.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Undo feature
`undo` allows restoring up to 5 previous states, but can be modified to better suit performance needs. It is important to note that allowing more states to be saved, or adding more information to be tied to a state, will deteriorate CLIpboard's performance.

If new commands are to be added, it's interaction with `undo` must be kept in mind.
For typical commands that do CRUD operations on roster data, `undo` can handle them.

For navigating and UI related commands, some extra handling may be needed to also restore and refresh the UI to the previous state (e.g `back` command).

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

* has a need to manage a significant number of students from different classes
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage their students’ particulars, grades and class attendance, in one centralised platform.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​     | I want to …​                                                                          | So that I can…​                                                                          |
|----------|----------------|---------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| `* * *`  | user           | add new students with their particulars                                               | create a new profile for a student                                                                |
| `* * *`  | user           | remove students from my class                                                         | update my class list if anyone needs to be removed                                                |
| `* * *`  | user           | list out all students in a particular class                                           | retrieve a list with all of my students’ names and their particulars                              |
| `* * *`  | user           | list all students being taught by me in the semester                                  | memorize my students' name                                                                        |
| `* * *`  | user           | view personal information for a particular student                                    | easily locate particulars for a single student                                                    |
| `* * *`  | user           | edit students' information easily                                                     | update the new information easily for viewing                                                     |
| `* * *`  | user           | filter students based on data such as results or class                                | find and compare student data                                                                     |
| `* *`    | user           | remove all students from a class                                                      | save time and don’t have to delete them one by one                                                |
| `* *`    | user           | leave a note under a student's profile                                                | keep track of additional information of students                                                  |
| `* *`    | returning user | familiarise myself with the app‘s UI again with a quick refresher                     | use the app again without needing to re-learn the whole UI                                        |
| `* *`    | forgetful user | see photos of the student associated with their name                                  | remember their names better and refer to the correct student during class                         |
| `* *`    | user           | be able to leave a note under a student's profile                                     | keep track of additional information of students                                                  |
| `* *`    | user           | have a list of all emails of my students from the same class                          | contact my students from that class                                                               |
| `* *`    | user           | Group and view certain students                                                       | keep track of their grades for group project                                                      |
| `* *`    | new user       | see the app populated with sample data                                                | get a sense how the app work when it is in use                                                    |
| `* *`    | new user       | learn more about the available features through tooltips                              | gain proficiency at using the program                                                             |
| `*`      | user           | toggle the app between light and dark mode                                            | change the application environment to suit the current light settings for less strain on the eyes |
| `*`      | user           | have a chart / graph of the assessments grades for every student                      | view students that may require more help                                                          |
| `*`      | user           | keep track of class materials such as slides                                          | share these materials with my students                                                            |
| `*`      | user           | save my most used commands                                                            | save time and don’t have to type them out again                                                   |
| `*`      | user           | see notifications/alert of upcoming events                                            | be reminded of assignments that are due soon                                                      |
| `*`      | expert user    | see a graph with the students’ performances from this semester and previous semesters | compare the overall performance of my students from this semester and last semester               |
| `*`      | expert user    | have an export function that allows me to export data from the app to an excel sheet  | easily transfer student data from the app to excel sheet if my higher ups require it              |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `CLIpboard` and the **Actor** is the `user`, unless specified otherwise)

<h3>Use case 1: Add a new course</h3>

**Preconditions: User is on the Course Page**

**MSS**

1.  User requests to add a new course into the list
2.  CLIpboard updates the list with a new course

    Use case ends.

<h3>Use case 2: Edit an existing course</h3>

**Preconditions: User is on the Course Page**

**MSS**

1.  User requests to edit an existing course in the list
2.  CLIpboard updates the course with the new name provided by the user

    Use case ends.

**Extensions**

* 1a. The list is empty

    * 1a1. CLipboard shows an error message.

      Use case ends.

* 1b. The course index does not exist in the list

    * 1b1. CLipboard shows an error message.

      Use case ends.

<h3>Use case 3: Delete en existing course</h3>

**Preconditions: User is on the Course Page**

**MSS**

1.  User requests to delete a course in the list
2.  CLIpboard updates the course list without the course

    Use case ends.

**Extensions**

* Similar to UC2 extension

<h3>Use case 4: Add a new student</h3>

**Preconditions: User is on the Student Page**

**MSS**

1.  User requests to add a new student into the list
2.  CLIpboard adds the particulars of the student into the list

    Use case ends.

**Extensions**

* 1a. The list is empty
  * 1a1. CLIpboard shows an error message.

    Use case ends.

* 1b. A student with the same student ID already exists

    * 1b1.  CLIpboard shows an error message.

      Use case ends.

<h3>Use case 5: Edit an existing student's information</h3>

**Preconditions: User is on the Student Page**

**MSS**

1.  User requests to edit a specific student in the list
2.  CLIpboard updates the particulars of the student

    Use case ends.

**Extensions**

* Similar to UC2 extension, for student index

<h3>Use case 6: Delete a student</h3>

**Preconditions: User is on the Student Page**

**MSS**

1.  User requests to delete a specific student in the list
2.  CLIpboard deletes the student

    Use case ends.

**Extensions**

* Similar to UC2 extension, for student index

<h3>Use case 7: Update a remark under a student's profile</h3>

**Preconditions: User is on the Student Page**

**MSS**

1.  User requests to update a remark under a specific student in the list
2.  CLIpboard updates the particulars of the student to the new remark

    Use case ends.

**Extensions**

* Similar to UC2 extension, for student index


*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Personal information**: Student particulars including `name`, `phone number`, `student id`, `email`, and can include `remark`.
* **UCx**: Represents Use Case x, where x is a use case number

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

---

### Launch and Shutdown

1. Initial launch.

   1. Download the latest jar file from our [releases](https://github.com/AY2223S2-CS2103T-T15-4/tp/releases) and copy into an empty folder.

   1. Open terminal/command prompt and `cd` to the empty folder. Run the application with `java -jar clipboard.jar`. The window size may not be optimum.

1. Saving window preferences.

   1. Resize the window to an optimum size. Move the window to a preferred location. Close the window by typing in `exit` in the command box.

   1. Re-launch the app by following step 1(ii).<br>
      Expected: The most recent window size and location is retained.

1. Exit application.

    1. Type in `exit` on any page in CLIpboard.

    1. Clicking on the `File` tab at the navigation bar above and then clicking `Exit` also exits the app.

---

### General Commands
These commands can be tested on any page. Specific pages are given for this section as a starting point.

1. Displaying the home page while on the Course Page.

    1. Test case: `home`<br>
       Expected: Course Page is displayed.

1. Displaying the previous page while on the Group Page.

    1. Test case: `back`<br>
       Expected: Course Page is displayed.

1. Displaying the previous page while on the Course Page.

    1. Test case: `back`<br>
       Expected: Error message shows up on the log box as the Course Page is the home page.<br>
       The application begins from the home page.

1. Undoing a select command from the Course Page (the current page to run the test case is the Group Page).

    1. Test case: `undo`<br>
       Expected: Course Page is displayed.

1. Opening the help window from the Course Page.

    1. Test case: `help`<br>
       Expected: Help window for Course Page pops up.

---

### Course Page Commands
These commands should be tested on the Course Page.

#### Adding a course

1. Adding a course while the course list is empty or the course does not exist in the course list.

    1. Test case: `add course CS3223`<br>
       Expected: New course is added into the list. Details of the added course shown in the log box.

    1. Other incorrect `add course` commands to try: `add`, `add course` (where the course code is empty)<br>
       Expected: No course is added. Error details shown in the log box.

1. Adding a course which already exists in the list.

    1. Test case: `add course CS3223`<br>
       Expected: No course is added. Error details shown in the log box.

#### Deleting a course

1. Deleting a course while all courses are being shown from the Course Page. At least 1 course exists in the list.

    1. Test case: `delete course 1`<br>
       Expected: First course is deleted from the list. Details of the deleted course shown in the log box.

    1. Test case: `delete course 0`<br>
       Expected: No course is deleted. Error details shown in the log box.

    1. Other incorrect `delete course` commands to try: `delete`, `delete course x` (where x is larger than the list size)<br>
       Expected: Similar to previous step 1(ii) under `Deleting a course`.

1. Deleting a course while the course list is empty in the Course Page.

    1. Incorrect `delete course` commands to try: `delete`, `delete course x` (where x is any number)<br>
       Expected: No course is deleted. Error details shown in the log box.

#### Editing a course

1. Editing a course which exists in the course list.

    1. Test case: `edit course 1 CS4225`<br>
       Expected: First course is edited to the new course code. Details of the edited course shown in the log box.

    1. Test case: `edit course 0 CS4225`<br>
       Expected: No course is edited. Error details shown in the log box.

    1. Other incorrect `edit course` commands to try: `edit`, `edit course x` (where x is any number), `edit course x CS4225` (where x is larger than the list size) <br>
       Expected: Similar to previous step 1(ii) under `Editing a course`.

1. Editing a course while the course list is empty in the Course Page.

    1. Refer to step 1(iii) above under `Editing a course`.

#### Selecting a course

1. Selecting a course while on the Course Page.

    1. Test case: `select 1`<br>
       Expected: First course is selected. Details of the selected course shown in the log box. Page redirected to the 
       corresponding Group Page.

    1. Test case: `select x` (where x is larger than the list size)<br>
       Expected: No course is selected. Error details shown in the log box.


#### Finding a course

1. Finding a course while on the Course Page, with the following courses - `CS3223`, `CS1101`, `PL1101` in the list.

    1. Test case: `find course CS`<br>
       Expected: `CS3223` and `CS1101` is displayed. Details of the found courses shown in the log box.

    1. Test case: `find course 1101`<br>
       Expected: `CS1101` and `PL1101` is displayed. Details of the found courses shown in the log box.

    1. Test case: `find course CS4225` (this course does not exist in the list)<br>
       Expected: The list is not filtered. Details of no found courses shown in the log box.

    1. Other incorrect `find course` commands to try: `find`, `find course`<br>
       Expected: The list is not filtered. Error details shown in the log box.
---

### Group Page Commands
These commands should be tested on the Group Page.

#### Adding a group

1. Adding a group while the group list is empty, or the group does not exist in the group list.

    1. Test case: `add group T10`<br>
       Expected: New group is added into the list. Details of the added group shown in the log box.

    1. Other incorrect `add group` commands to try: `add`, `add group` (where the group name is empty)<br>
       Expected: No group is added. Error details shown in the log box.

1. Adding a group into the list containing the following groups - `T01`, `T02`, `L01`.

    1. Test case: `add group L02`<br>
       Expected: New group is added. Details of the added group shown in the log box.

    1. Test case: `add group T01`<br>
       Expected: No group is added. Error details shown in the log box.

#### Deleting a group

1. Deleting a group while all groups are being shown from the Group Page. At least 1 group exists in the list.

    1. Test case: `delete group 1`<br>
       Expected: First group is deleted from the list. Details of the deleted group shown in the log box.

    1. Test case: `delete group 0`<br>
       Expected: No group is deleted. Error details shown in the log box.

    1. Other incorrect `delete group` commands to try: `delete`, `delete group x` (where x is larger than the list size)<br>
       Expected: Similar to previous step 1(ii) under `Deleting a group`.

1. Deleting a group while the group list is empty in the Group Page.

    1. Incorrect `delete group` commands to try: `delete`, `delete group x` (where x is any number)<br>
       Expected: No group is deleted. Error details shown in the log box.

#### Editing a group

1. Editing a group which exists in the group list.

    1. Test case: `edit group 1 T02`<br>
       Expected: First group is edited to the new group name. Details of the edited group shown in the log box.

    1. Test case: `edit group 0 T02`<br>
       Expected: No group is edited. Error details shown in the log box.

    1. Other incorrect `edit group` commands to try: `edit`, `edit group x` (where x is any number), `edit group x T02` (where x is larger than the list size) <br>
       Expected: Similar to previous step 1(ii) under `Editing a group`.

1. Editing a group while the group list is empty in the Group Page.

    1. Refer to step 1(iii) above under `Editing a group`.

#### Selecting a group

1. Selecting a group while on the Group Page.

    1. Test case: `select 1`<br>
       Expected: First group is selected. Details of the selected group shown in the log box. Page redirected to the
       corresponding Students Page.

    1. Test case: `select x` (where x is larger than the list size)<br>
       Expected: No group is selected. Error details shown in the log box.

#### Displaying sessions of a group

1. Selecting a group to display its sessions while on the Group Page.

    1. Test case: `session 1`<br>
       Expected: First group is selected to view its sessions. Details of the selected group to view its
       sessions shown in the log box. Page redirected to the corresponding session page for the selected group.

    1. Test case: `session x` (where x is larger than the list size)<br>
       Expected: No group is selected to view its sessions. Error details shown in the log box.

#### Displaying tasks of a group

1. Selecting a group to display its tasks while on the Group Page.

    1. Test case: `task 1`<br>
       Expected: First group is selected to view its tasks. Details of the selected group to view its
       tasks shown in the log box. Page redirected to the corresponding Task Page for the selected group.

    1. Test case: `task x` (where x is larger than the list size)<br>
       Expected: No group is selected to view its tasks. Error details shown in the log box.

#### Finding a group

1. Finding a group while on the Group Page, with the following groups - `T01`, `T02`, `L01` in the list.

    1. Test case: `find group T`<br>
       Expected: `T01` and `T02` is displayed. Details of the found groups shown in the log box.

    1. Test case: `find group 01`<br>
       Expected: `T01` and `L01` is displayed. Details of the found groups shown in the log box.

    1. Test case: `find group T03` (this group does not exist in the list)<br>
       Expected: The list is not filtered. Details of no found groups shown in the log box.

    1. Other incorrect `find group` commands to try: `find`, `find group`<br>
       Expected: The list is not filtered. Error details shown in the log box.

---

### Students Page Commands
These commands should be tested on the Students Page.

#### Deleting a student

1. Deleting a student while all students are being shown from the Students Page.

    1. Test case: `delete student 1`<br>
       Expected: First student is deleted from the list. Details of the deleted student shown in the log box.

    1. Test case: `delete student 0`<br>
       Expected: No student is deleted. Error details shown in the log box.

    1. Other incorrect `delete student` commands to try: `delete`, `delete student x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _{ more test cases …​ }_

---

### Session Page Commands
These commands should be tested on the Session Page.

---

### Attendance Page Commands
These commands should be tested on the Attendance Page.

---

### Task Page Commands
These commands should be tested on the Task Page.

#### Adding a task

1. Adding a task while the task list is empty, or the task does not exist in the task list.

    1. Test case: `add task PE1`<br>
       Expected: New task is added into the list. Details of the added task shown in the log box.

    1. Other incorrect `add task` commands to try: `add`, `add task` (where the task name is empty)<br>
       Expected: No task is added. Error details shown in the log box.

1. Adding a task into the list containing the following tasks - `CA1`, `CA2`, `Critical Reflection 1`.

    1. Test case: `add task CA3`<br>
       Expected: New task is added. Details of the added task shown in the log box.

    1. Test case: `add task CA1`<br>
       Expected: No task is added. Error details shown in the log box.

#### Deleting a task

1. Deleting a task while all tasks are being shown from the Task Page. At least 1 task exists in the list.

    1. Test case: `delete task 1`<br>
       Expected: First task is deleted from the list. Details of the deleted task shown in the log box.

    1. Test case: `delete task 0`<br>
       Expected: No task is deleted. Error details shown in the log box.

    1. Other incorrect `delete task` commands to try: `delete`, `delete task x` (where x is larger than the list size)<br>
       Expected: Similar to previous step 1(ii) under `Deleting a task`.

1. Deleting a task while the task list is empty in the Task Page.

    1. Incorrect `delete task` commands to try: `delete`, `delete task x` (where x is any number)<br>
       Expected: No task is deleted. Error details shown in the log box.

#### Editing a task

1. Editing a task which exists in the task list.

    1. Test case: `edit task 1 CA4`<br>
       Expected: First task is edited to the new task name. Details of the edited task shown in the log box.

    1. Test case: `edit task 0 CA4`<br>
       Expected: No task is edited. Error details shown in the log box.

    1. Other incorrect `edit task` commands to try: `edit`, `edit task x` (where x is any number), `edit task x CA4` 
       (where x is larger than the list size) <br>
       Expected: Similar to previous step 1(ii) under `Editing a task`.

1. Editing a task while the task list is empty in the Task Page.

    1. Refer to step 1(iii) above under `Editing a task`.

#### Selecting a task

1. Selecting a task while on the Task Page.

    1. Test case: `select 1`<br>
       Expected: First task is selected. Details of the selected task shown in the log box. Page redirected to the
       corresponding Grades Page.

    1. Test case: `select x` (where x is larger than the list size)<br>
       Expected: No task is selected. Error details shown in the log box.

#### Finding a task

1. Finding a task while on the Task Page, with the following tasks - `CA1`, `CA2`, `Critical Reflection 1` in the list.

    1. Test case: `find task 1`<br>
       Expected: `CA1` and `Critical Reflection 1` is displayed. Details of the found tasks shown in the log box.

    1. Test case: `find task CA`<br>
       Expected: `CA1` and `CA2` is displayed. Details of the found tasks shown in the log box.

    1. Test case: `find task CA3` (this task does not exist in the list)<br>
       Expected: The list is not filtered. Details of no found task(s) shown in the log box.

    1. Other incorrect `find task` commands to try: `find`, `find task`<br>
       Expected: The list is not filtered. Error details shown in the log box.


---

### Grades Page Commands
These commands should be tested on the Grades Page.

#### Assigning a grade to a student

1. Assigning a grade to a student, with at least one student in the list.

    1. Test case: `assign 1 80`<br>
       Expected: First student is assigned the grade. Details of the student assigned a grade shown in the log box.

    1. Other incorrect `assign` commands to try: `assign`, `assign x` (where x is larger than the student list size)
       , `assign x -1` or `assign x 101` (where x is a valid index from the student list) <br>
       Expected: No student is assigned a mark. Error details shown in the log box.

---

### Saving data

1. Dealing with missing/corrupted data files

   1. Test case: Corrupted data file. This includes any invalid fields in `roster.json` located in `tp/data`. <br>
      Expected: A new set of roster sample data from `sampleRoster.json` will be loaded into the original file. Do exit the app for the new
      data from the uncorrupted sample file to overwrite `roster.java`.

   1. Test case: Missing data file. This indicates that `roster.java` does not exist in `tp/data`. <br>
      Expected: A set of roster sample data from `sampleRoster.json` will be copied and loaded into a newly created `roster.java` 
      Do exit the app for `roster.java` to be created.

