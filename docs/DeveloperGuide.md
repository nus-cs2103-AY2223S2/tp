### PowerConnect Developer Guide

PowerConnect is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PowerConnect can get your contact management tasks done faster than traditional GUI apps.

##### Table of Contents
1. [Quick Start](#quickstart)
2. [Features](#features)
    1. [Student Features](#student)
        1. [Add student: `add`](#addstudent)
        2. [Add students' grade: `grade`](#gradestudent)
        3. [Add comments to students: `comment`](#commentstudent)
        4. [Listing all students in a particular class: `list`](#liststudent)
        5. [Editing a student's particulars: `edit`](#editstudent)
        6. [Searching students: `find`](#findstudent)
        7. [Deleting a student: `delete`](#deletestudent)
    2. [Parent Features](#parent)
        1. [Add parent/guardian: `add`](#addparent)
        2. [Listing all parents: `list`](#listparent)
        3. [Delete a parent/ parent information: `delete`](#deleteparent)
3. [Viewing help: `help`](#help)
4. [Exiting program: `exit`](#exit)
5. [FAQ](#faq)
6. [Command Summary](#summary)

--------------------------------------------------------------------------------------------------------------------
## **Target User Profile**

* This product mainly targets secondary school teachers that prefer CLI over GUI,  manage large groups of students and require ease of access to students' information.

## **Value Proposition**

* Have many students in different classes / co-curricular activities (CCAs) that have different phone number that needs to be kept track of
* Need to keep track of students’ parents / next-of-kins’ contact details for consent forms
* Teachers may not be able to match students names and faces well and so this app serves as an easy way to identify students (since there are too many students)
* Keep track of homework given and deadline
* Streamline administration processes because they have a lot of stuff to keep track of (eg. Attendance, assignments, grades, contact details)

--------------------------------------------------------------------------------------------------------------------

## **User Stories**

| No  | As a...                                                                     | I want to... | So that I can... | Notes | Priority |
|-----|-----------------------------------------------------------------------------| ------------- | ------------- | ------------- | ------------- |
| 1   | Teacher                                                                     | Have an application that can be used CLI instead of GUI  | Make use of my fast typing speed to sort out all the admin work | Too Vague | NA|
| 2   | Busy Teacher                                                                | Be able to easily manage my schedule | Spend more time on other things | Out of scope | NA
| 3   |  Busy Teacher                                                               | Know which tasks to prioritise | Ensure that all of my responsibilities are taken care of in a timely and efficient manner.|| NA |
| 4   | Teacher                     | Record student’s attendance                                                 |Know that my student is present in class (in school). | | High|
| 5   | Teacher                     | Record students’ assessment and test grades.                                |Manage my students’ performance| |High|
| 6   | Teacher                     | Have an efficient way to view and track my students’ attendance.            |Easier time managing my students’ attendance over the year| | High|
| 7   | Caring Teacher              | Have a way to provide comments for each student                             |Keep track of students who need meaningful feedback and support.|(comments section on each student)|Medium|
| 8   | Teacher                     | Track the date and time of the classes that I have                          |Be on time and teach the correct module for the particular lesson slot.| |Out of scope| NA |
| 9   | Teacher                     | Amend date and time of certain lesson timings                               | Change easily when lessons shift | | NA|
| 10  | Teacher / teacher assistant | Have an efficient way to retrieve my students’ contact details.             | Contact them easily| Assumption: student details are known to teachers / TAs| Medium |
| 11  | Teacher                | Keep track of students who have submitted assignment                        |Know which student missed the deadline for their work| |Medium |
| 12  | Teacher                | Use an application with a reminder system                                   |Track the things I have to do | | NA|
| 13  | Teacher                | Efficiently and effectively assign assessments and assignments              |Better assess my students’ learning and progress| | NA|
| 14  | Teacher                | Resources to aid me in managing students’ behaviours and actions            | Promote a safe and productive learning space for all | |NA|
| 15  | Teacher                | Document students’ progress                                                 |Easily record the students’ performance| | High|
| 16  | Teacher                | Add individual feedback to students                                         |Provide feedback to parents/guardians effectively| Duplicate point to Point 7| NA|
| 17  | Teacher                | Access my students contacts conveniently                                    |Build a closer relationship with my students and their next of kin|Too vague| NA|
| 18  | Busy Teacher           | Keep track of my teaching feedback                                          | Keep improving my teaching practices | Hard to get school admin team on board|NA|
| 19  | Efficient Teacher      | Integrate technology into my teaching                                       | Enrich my student learning| Vague| NA|
| 20  | Responsible Teacher    | Access student records, such as grades and attendance                       | Make informed decisions about student progress| Summary of all students grades for a particular test / class| Low |
| 21  | Busy teacher           | Have a todo list                                                            | Keep track of what to do | | Low/NA|
| 22  | Teacher assistant      | Retrieve my students contact                                                | Look them up easily| Duplicate Point to Point 10| NA|
| 23  | Motivational teacher   | Sort my students by overall grades                                          | Foster a positive and supportive learning environment| |Medium|
| 24  | Teacher                | Organise my students by class                                               | Identify which class they belong to| Determine whether to create separate classes: “class” class and “student” class or merge| High|
| 25  | Teacher                | Have the ability to recognise my students from different classes            | Know what my students look like| Long setup process for user as they need to manually input images for individual student| Keeping separate file / folder for images| Medium|
| 26  | Teacher                | Track the date and time of my classes                                       | Be on time for my classes| | NA|
| 27  | Teacher                | Have the option to leave some details empty                                 | Still key in my students’ information when there’s missing information| | High|
| 28  | Forgetful teacher      | Record attendance                                                           | Keep track of my students| Duplicate to Point 5| NA|
| 29  | Teacher                | Identify weaker students                                                    | Give them more attention in class|Duplicate to Point 20|NA
| 30  | Non tech-savvy teacher | Learn about new commands                                                    | Use the application effectively and save unnecessary time and effort in recording students’ particulars and searching for it.| Provide User Guide that is easy to understand |High |
| 31  | Teacher | Use an application that is easy to navigate about and has a nice interface. | Save unnecessary time and effort in recording students’ particulars and searching for it.|Issue under designs|Low|
| 32  | Teacher | Be able to manage my consultation timings                                   | Organise my schedule| | NA|
| 33  | Teacher | Set reminders for my consultation timings                                   | Ensure I am not late for consultations | |NA|
| 34  | Teacher | Have an application that marks my students’ assignments automatically       | Use time and effort spent on it on other use, such as thinking of ways to improve my students’ learning| Difficult to implement Autograder| NA|


--------------------------------------------------------------------------------------------------------------------

## **Use Cases**
***For all use cases below, the System is PowerConnect and the Actor is the teacher, unless specified otherwise***

**Use Case: UC01 - Adding `grade` for a `student`**

**Preconditions:** User knows `index number` of the `student` he/she wishes add `grade` for

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

**Use Case: UC02 - Adding `comment` for a `student`**

**Preconditions: User knows `index number` of the `student` he/she wishes add `comment` for**

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

**Use Case: UC03 - Adding a new `student` to an existing `class`**

**Preconditions: User knows the `index number` for the `student` and the `class` the student belongs to has already been created**

**MSS:**
1. User keys in **ALL COMPULSORY** details and any other **OPTIONAL** details as part of student’s particulars
2. System displays feedback to the user that the `student` has been successfully been created and added to the respective `class`

   Use case ends.

**Extensions:**

+ 1a. User did not enter **ALL COMPULSORY** details.
  + 1a1. System displays an error message to the user indicating that there is insufficient information given to create the new `student`.

    Use case ends.

+ 1b. User keys in invalid **COMPULSORY** or **OPTIONAL** information
  + 1b11. User keys in invalid `SEX` type not supported by system or `SEX` type contain numbers.
  + 1b12. User keys the same information for the student's `name` and NOK’s `name`.
  + 1b13. User keys in `age` or `phone number` that are not of an integer.
  + 1b14. User keys in invalid path to `image`.
  + 1b2. System displays an error message to the user indicating that he/she has keyed in wrong information for the `student` along with a sample of the correct way to key in information for a new `student`.
    
    Use case ends.
    
+ 2a. User is trying to create a new `student` whose index number belongs to an existing `student` in the class.
  + 2a1. System displays an error message to the user indicating that a `student` with the same `index number` already exists in the `class`.
  
    Use case ends. <br><br>

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

*{More to be added}* <br><br>


--------------------------------------------------------------------------------------------------------------------

## **Non-functional requirements**
1. Should work on any mainstream OS as long as it has Java `11` or above installed.
2. Should be able to handle up to 400 students without a noticeable sluggishness in performance for typical usage.
3. PowerConnect should be able to work without any internet access.
4. PowerConnect should only be used by a single user per installation and not by multiple users.
5. Users with above average typing speed for regular English text (i.e. not code, not system admin commands) should be 
able to accomplish the majority of the tasks faster using CLI commands than using the mouse.

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------

## **Glossary**
**Attributes**: Information of a student / parent. <br> For example, name, phone number, email address etc <br><br>
**CLI**: Command Line Interface <br><br>
**Mainstream OS**: Windows, Linux, Unix, OS-X <br><br>

*{More to be added}*

--------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

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

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

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

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new person               |                                                                        |
| `* * *`  | user                                       | delete a person                | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a person by name          | locate details of persons without having to go through the entire list |
| `* *`    | user                                       | hide private contact details   | minimize chance of someone else seeing them by accident                |
| `*`      | user with many persons in the address book | sort persons by name           | locate a person easily                                                 |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to delete a specific person in the list
4.  AddressBook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

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
