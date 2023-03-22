---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

We would like to thank:

* [CS2103T AY22/23 Semester 2 teaching team](https://nus-cs2103-ay2223s2.github.io/website/admin/instructors.html) for imparting valuable knowledge, skills and guidance
* [SE-edu AddressBook 3](https://github.com/se-edu/addressbook-level3) which `QuickContacts` is built on
* Open-source libraries used in `QuickContacts`:
  * [JavaFX](https://openjfx.io/)
  * [Jackson](https://github.com/FasterXML/jackson)
  * [JUnit](https://junit.org/junit5/)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

#### Main components of the architecture

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

#### Interaction between architecture components

The **_Sequence Diagram_** below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) **_class diagram_** below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `QuickContactsParser` class to parse the user command.
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
* When called upon to parse a user command, the `QuickContactsParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `QuickContactsParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `QuickContacts`, which `Person` references. This allows `QuickContacts` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `QuickContactsStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.QuickContacts.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedQuickContacts`. It extends `QuickContacts` with an undo/redo history, stored internally as an `QuickContactsStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedQuickContacts#commit()` — Saves the current address book state in its history.
* `VersionedQuickContacts#undo()` — Restores the previous address book state from its history.
* `VersionedQuickContacts#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitQuickContacts()`, `Model#undoQuickContacts()` and `Model#redoQuickContacts()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedQuickContacts` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitQuickContacts()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `QuickContactsStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitQuickContacts()`, causing another modified address book state to be saved into the `QuickContactsStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitQuickContacts()`, so the address book state will not be saved into the `QuickContactsStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoQuickContacts()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial QuickContacts state, then there are no previous QuickContacts states to restore. The `undo` command uses `Model#canUndoQuickContacts()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoQuickContacts()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `QuickContactsStateList.size() - 1`, pointing to the latest address book state, then there are no undone QuickContacts states to restore. The `redo` command uses `Model#canRedoQuickContacts()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitQuickContacts()`, `Model#undoQuickContacts()` or `Model#redoQuickContacts()`. Thus, the `QuickContactsStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitQuickContacts()`. Since the `currentStatePointer` is not pointing at the end of the `QuickContactsStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

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

* Business People on their computers most of their days
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                             | I want to …​                                                                | So that I can…​                                                 |
| -------- | ---------------------------------------------------------------------------| -----------------------------------------------------------------------------------|----------------------------------------------------------------------- |
| `* * *`  | new user                                                                   | see usage instructions                                                             | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                                                       | add a new person                                                                   |                                                                        |
| `* * *`  | user                                                                       | delete a person                                                                    | remove entries that I no longer need                                   |
| `* * *`  | user                                                                       | find a person by name                                                              | locate details of persons without having to go through the entire list |
| `* *`    | user                                                                       | hide private contact details                                                       | minimize chance of someone else seeing them by accident                |
| `*`      | user with many persons in the contact book                                 | sort persons by name                                                               | locate a person easily                                                 |
|`*`       | user                                                                       | Find a person by tag                                                               | I can filter the contacts by tags                                      |
|`*`       | user                                                                       | Sort by tag priority                                                               | I can find the most important contacts                                 |
|`*`       | user                                                                       | Assign tag priority                                                                |                                                                        |
|`*`       | user                                                                       | Retrieve deleted contacts                                                          |                                                                        |
|`*`       | user                                                                       | Set a date for deletion of contacts                                                | Remove the contacts automatically                                      |
|`*`       | user                                                                       | Add a contact                                                                      | Store my contact in app                                                |
|`*`       | user                                                                       | Delete a contact                                                                   | Remove unwanted contacts                                               |
|`*`       | user                                                                       | Edit a contact                                                                     | Change details of existing contacts                                    |
|`*`       | user                                                                       | Assign tag to contact                                                              | Categorise my contacts                                                 |
|`*`       | user                                                                       | Use the help command                                                               | To see available commands                                              |
|`*`       | power user                                                                 | Assign shortcuts to different actions                                              | I can cut down on the time taken to type                               |
|`*`       | User with many connections                                                 | Export my contacts                                                                 | I can share my contacts easily                                         |
|`*`       | User with many connections                                                 | Copy the details of my contacts                                                    | I can share my contacts easily                                         |
|`*`       | User with many existing contacts                                           | Import my contacts automatically                                                   | I don\'t have to spend too much time creating contacts one by one  |
|`*`       | User with many meetups with people                                         | Sort meetings based on the date and time                                           | I can prioritize my time well                                          |
|`*`       | Busy user with many meetups                                                | Receive notifications about meetups with contacts                                  | I won\'t be late for meetups                                       |
|`*`       | User with many meetups with people                                         | Create a meeting                                                                   | Schedule a meeting                                                     |
|`*`       | User with many meetups with people                                         | Edit a meeting                                                                     | Change meeting details                                                 |
|`*`       | User with many meetups with people                                         | Delete a meeting                                                                   | Remove cancelled or completed meetings                                 |
|`*`       | User with many meetups with people                                         | View all meetings                                                                  | See in a glance the meetings that I have                               |
|`*`       | User with many meetups with people                                         | View meeting details                                                               | Understand what my meeting is about                                    |
|`*`       | Users with meetings                                                        | Add a reminder to meeting                                                          | So I do not forget the meeting                                         |
|`*`       | Users with meetings                                                        | Edit reminder of meeting                                                           | Change how frequent my reminders are                                   |
|`*`       | Users with meetings                                                        | Delete a reminder                                                                  | So I am not spammed with reminders                                     |
|`*`       | Users with many meetings                                                   | Tag meeting                                                                        | Organize they types of meetings                                        |
|`*`       | User who are very familiar with the keyboard                               | Add custom keybinds | So that I am faster at organizing contacts                   |                                                                        |
|`*`       | User with many meetings                                                    | See how many days left to a meeting                                                | I don\'t forget to attend one                                      |
|`*`       | User in a hurry                                                            | Undo previous action up to 3 previous actions                                      | I can be fast and a bit sloppy without worrying                        |
|`*`       | User who use the app for a long time                                       | Set a reminder to tag people | In future I can better organize people              |                                                                        |
|`*`       | User who forget what is in contacts                                        | Ask if person/meeting still relevant | So that the contact remain relatively clean |                                                                        |
|`*`       | User assign name to priority tag                                           | Customise the tags                                                                 | I can remember more easily who is ranked higher                        |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `QuickContacts` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a person**

**MSS**

1.  User requests to add a person
2.  QuickContacts adds that person
3.  QuickContacts shows new person in list

    Use case ends.

**Extensions**

* 1a. String in a field illegal.
    * 1a1. Show error message

  Use case resumes at step 1.

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  QuickContacts shows a list of persons
3.  User requests to delete a specific person in the list
4.  QuickContacts deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. QuickContacts shows an error message.

      Use case resumes at step 2.

**Use case: Find a Person**

**MSS**

1.  User requests searches person by name
2.  QuickContacts shows that person

    Use case ends.

**Extensions**

* 1a. String in a field illegal.
    * 1a1. Show error message

  Use case continues at step 1.

**Use case: Edit an existing person's details**

**MSS**

1.  User requests to edit a person
2.  QuickContacts shows the person with the updated details

    Use case ends.

**Extensions**

* 1a. String in a field illegal.
    * 1a1. Show error message

* 1b. Person cannot be found.
    * 1b1. Show error message

  Use case continues at step 1.

**Use case: User want to list all**

**MSS**

1.  User request list
2.  QuickContacts shows all the person and meetings

    Use case ends.

**Extensions**

* 1a. list is empty

  Use case ends.


**Use case: User wants to clear everything**

**MSS**

1.  User requests to clear
2.  QuickContacts deletes everyone and all the meetings

    Use case ends.

**Use case: User wants to exit**

**MSS**

1.  User requests to exit
2.  QuickContacts closes

    Use case ends.

**Use case: Add a meeting**

**MSS**

1.  User requests to add a meeting
2.  QuickContacts adds that meeting
3.  QuickContacts shows new meeting in list

    Use case ends.

**Extensions**

* 1a. String in a field illegal.
    * 1a1. Show error message

  Use case resumes at step 1.

**Use case: Delete a meeting**

**MSS**

1.  User requests to list meetings
2.  QuickContacts shows a list of meetings
3.  User requests to delete a specific meeting in the list
4.  QuickContacts deletes the meeting

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. QuickContacts shows an error message.

      Use case resumes at step 2.

**Use case: Edit a meeting**

**MSS**

1.  User requests to edit a meeting
2.  QuickContacts shows the meeting with the updated details

    Use case ends.

**Extensions**

* 1a. String in a field illegal.
    * 1a1. Show error message

* 1b. Meeting cannot be found.
    * 1b1. Show error message

  Use case continues at step 1.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Can support 1000 meetings with same amount of lag as 10 meeting
5. Commands should be intuitive to not technical people
6. Should be clear that meeting and people are 2 seperate things

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Meetings**: Important dates with a duration and a place
* **Find**: Searches by name field, case-insensitive, match all matching words individually
* **GUI**: Graphic User Interface
* **MSS**: Main Success Scenario
* **OS**: Operating System
* **Java**: Programming Language by SUN Oracle
* **CLI**: Command Line Interface


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Deleting a contact

1. Deleting a contact while all contacts are being shown

   1. Prerequisites: List all contacts using the `list` command. Multiple contacts in the list.

   2. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No contact is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Creating a meeting

1. Creating a meeting with a contact:

    1. Prerequisites: Contact `Alex Yeo` exists in QuickContacts.

    2. Test case: `addm m/Lunch with Alex dt/2003 15:00 p/Alex Yeo`<br>
       Expected: New meeting with `Alex Yeo` on 20 March (of the current year) at 3PM is created, and it is displayed in the meetings list.

    3. Test case: `addm m/Lunch with Alex dt/2003 25:00 p/Alex Yeo l/The Deck des/Weekly catch-up`<br>
       Expected: Meeting is not created since `25:00` is an invalid time. Error details shown in the status message. Status bar remains the same.

2. Creating a meeting without a contact:
    
    1. Test case: `addm m/Dinner at home dt/2003222`<br>
       Expected: New meeting is created for `200322` (20 March 2022).

    2. Test case: `addm m/Dinner at home`<br>
       Expected: Meeting is not created as a date(and time) is required. Error details shown in the status message. Status bar remains the same.

### Saving data

1. To simulate data file is not found:

   1. Prerequisite: `QuickContacts` is currently not running and the data files have been generated.

   2. Rename the file `quickcontacts.json` in the same directory to `quickcontacts.json.backup`.

   3. Launch `QuickContacts`.

   Expected: `QuickContacts` launches normally and re-generates the sample default data.

2. To simulate data file is corrupted:

   1. Prerequisite: `QuickContacts` is currently not running and the data files have been generated.

   2. Open `quickcontacts.json` with a text editor, add a few random characters and save it.

   3. Launch `QuickContacts`.

   Expected: A warning message will be displayed and `QuickContacts` will start from an empty data file.

3. To simulate restoring data from a backup data file:

   1. Prerequisite: `QuickContacts` is currently not running and the data files have been generated and you have the backup file ready. Assume that the backup file is named `quickcontacts.json.backup`.

   2. Delete `quickcontacts.json` from the directory that contains `quickcontacts.jar`.

   3. Move `quickcontacts.json.backup` into the same directory as `quickcontacts.jar`.

   4. Rename `quickcontacts.json.backup` to `quickcontacts.json`.

   5. Launch `QuickContacts`.

   Expected: `QuickContacts` will launch normally with the data restored from the backup.
