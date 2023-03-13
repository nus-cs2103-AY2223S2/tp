---
layout: page
title: Developer Guide
---
## Table of Contents
- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#setting-up-getting-started)
- [Design](#design)
   <!-- - [Architecture](#architecture)
   - [UI component](#ui-component)
   - [Logic component](#logic-component)
   - [Model component](#model-component)
   - [Storage component](#storage-component)
   - [Common classes](#common-classes) -->
- [Implementation](#implementation)
- [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
- [Appendix: Requirements](#appendix-requirements)
   * [Product scope](#product-scope)
   * [User stories](#user-stories)
   * [Use cases](#use-cases)
   * [Non-Functional Requirements](#non-functional-requirements)
   * [Glossary](#glossary)
- [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## Design

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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.)

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

## Implementation

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

## Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## Appendix: Requirements

### Product scope

**Target user profile**:

* freelancer who wants to keep track of their events and contact person
* has a need to manage a significant number of events
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage events' information and respective contact person faster than a typical mouse/GUI driven app.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a/an...           | I can...                                         | so that...                                           |
| -------- | -------------------- | ------------------------------------------------ | ---------------------------------------------------- |
| `* * *`     | User                 | Click X to exit                                  | I can stop it                                        |
| `* * *`     | User                 | add client contact                               | I can refer to when needed                           |
| `* * *`     | User                 | View all my contacts                             | I can see who I can contact                          |
| `* * *`     | User                 | link client contact to event                     | I can have quick reference to POC (Point of Contact) |
| `* * *`     | User                 | See the rates I have discussed with the client   | I know how much money to collect                     |
| `* * *`     | User                 | Mark the event that is done                      | I know what I don't have to do anymore               |
| `* * *`     | User                 | Add events                                       | I can remember my events later                       |
| `* * *`     | User                 | View events                                      | I can see what events I have done or am going to do  |
| `* * *`     | User                 | Delete events                                    | I can remove unnecessary events                      |
| `* * *`     | User                 | Add a date to my events                          | I know when the event is due                         |
| `* * *`     | User                 | save my events after closing the app             | I can save for when I use the app another time       |
| `* * *`     | User                 | load my saved events when opening the app        | I can see my events when I reopen the app            |
| `* * *`     | User                 | Add a rate to an event                           | I can see how much I earn from an event              |
| `* * *`     | User                 | View information associated to each event        | I can see its details                                |
| `* * *`     | User                 | edit my existing event details                   | I can change the details whenever there are changes  |
| `* * *`     | User                 | See what I have added before                     | I don't need repeat myself                           |
| `* * *`     | User                 | Change a contact linked to an event              | I can link a different contact if the POC changes    |
| `* * *`     | New user             | See usage instructions                           | I know how to use the software                       |
| `* * *`     | User                 | View undone events                               | I can see what events I have to do                   |
| `* * *`     | Freelancer           | link venue to event                              | I can have quick reference                           |
| `* * *`     | Forgetful User       | Be reminded of upcoming events                   | I can remember                                       |
| `* * *`     | User                 | Delete contacts                                  | I can delete unnecessary contacts                    |
| `* *`   | User                 | Filter the events that has not been paid         | I can collect money                                  |
| `* *`   | User                 | View the total revenue I have earned this week   | I can manage my money                                |
| `* *`   | User                 | Set notes for contacts                           | I can keep track about them and rmb what not to do   |
| `* *`   | User                 | View timing of upcoming events                   | I know when to go                                    |
| `* *`   | User                 | See the most urgent events                       | I know what to do first                              |
| `* *`   | Careless user        | Undo accidental deletion                         | I can undo deleted events                            |
| `* *`   | Impatient person     | search contacts                                  | I can quick find specific POC                        |
| `* *`   | Impatient person     | Search events                                    | I can quick find specific event                      |
| `* *`   | Lazy person          | filter events to find similar events             | I can find similar events easily                     |
| `* *`   | User                 | Add all information to an event at the same time | I can save time on adding all the information        |
| `* *`   | User                 | View events associated to each contact           | I know what events to contact the POC for            |
| `*`      | User                 | toggle between light and dark mode               | I can use the theme i prefer                         |
| `*`      | User                 | Redo previous action                             | I can redo previous action                           |
| `*`      | User                 | Use abbreviation                                 | I can quickly insert items                           |
| `*`      | Goal oriented person | Set goals                                        | I can look at my goals                               |
| `*`      | Forgetful User       | set reminders/checklist for events               | I can remember things to bring etc.                  |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is `Paidlancers` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Add Event**

**MSS**

1.  User requests to add an event together with its information.
2.  System adds the event and displays information of the event added.

   Use case ends.

**Extensions**

* 1a. User enters the wrong command.
   * 1a1. System displays that the command is invalid and to try again.

      Use case resumes at step 1.

**Use case: UC2 - Delete Event**

**MSS**

1. User <u>lists events (UC4)</u>.
2. User requests to delete a specified event in the list.
3. System deletes the event and displays information of the event deleted.

   Use case ends.

**Extensions**

* 2a. User enters the wrong command.
   * 2a1. System displays that the command is invalid and to try again.

      Use case resumes at step 2.

* 2b. User enters an invalid index.
   * 2b1. System displays that the index is invalid and to try again.

      Use case resumes at step 2.

**Use case: UC3 - Mark Event**

**MSS**

1. User <u>lists events (UC4)</u>.
2. User requests to mark a specified event in the list.
3. System marks the event and displays information of the event marked.

   Use case ends.

**Extensions**

* 1a. User enters the wrong command.
   * 1a1. System displays that the command is invalid and to try again.

      Use case resumes at step 2.

* 1b. User enters an invalid index.
   * 1b1. System displays that the index is invalid and to try again.

      Use case resumes at step 2.

* 1c. User marks an event that is already marked.
   * 1c1. System displays that the event has already been marked.

      Use case ends.

**Use case: UC4 - List Events**

**MSS**

1. User requests the system to list events.
2. System displays information of all events with their respective index.

   Use case ends.

**Extensions**

* 1a. User enters the wrong command.
   * 1a1. System displays that the command is invalid and to try again.

      Use case resumes at step 1.

* 2a. The list is empty.
   * 2a1. System displays an empty list.

      Use case ends.
<!-- **Use case: UC5 - List Contacts**

**MSS**

1. User requests the system to list contacts.
2. System displays information of all contacts with their respective index.

   Use case ends.

**Extensions**

* 1a. User enters the wrong command.
   * 1a1. System displays that the command is invalid and to try again.

      Use case resumes at step 1.

* 2a. The list is empty.
   * 2a1. System displays an empty list.

      Use case ends. -->

**Use case: UC5 - Link contact and event**

**MSS**

1. User <u>lists events (UC4)</u>.
2. User requests to link a specified event and contact.
3. System links them and displays the event's and contact's information.

   Use case ends.

**Extensions**

* 2a. User enters the wrong command.
   * 2a1. System displays that the command is invalid and to try again.

      Use case resumes at step 3.

* 2b. User enters an invalid index.
   * 2b1. System displays that the index is invalid and to try again.

      Use case resumes at step 3.

* 2c. User enters an invalid contact information
   * 2c1. System displays that the contact information is invalid and to try again.

      Use case resumes at step 3.

* 3a. The event has a contact linked already.
   * 4a1. System replaces the previous contact with the new contact.

      Use case ends.

**Use case: UC6 - Display Rate**

**MSS**

1. User <u>lists events (UC4)</u>.
2. User requests to display the rate of a specified event in the list.
3. System displays the event and displays information of the rate.

   Use case ends.

**Extensions**

* 2a. User enters the wrong command.
   * 2a1. System displays that the command is invalid and to try again.

      Use case resumes at step 2.

* 2b. User enters an invalid index.
   * 2b1. System displays that the index is invalid and to try again.

      Use case resumes at step 2.

<!-- **Use case: UC7 - Tag a rate to an Event**

**MSS**

1. User <u>lists events (UC4)</u>.
2. User requests to tag a rate to a specified event in the list.
3. System tags the rate to the event and displays information of the event tagged.

   Use case ends.

**Extensions**

* 2a. User enters the wrong command.
   * 2a1. System displays that the command is invalid and to try again.

      Use case resumes at step 2.

* 2b. User enters an invalid index.
   * 2b1. System displays that the index is invalid and to try again.

      Use case resumes at step 2.

* 2c. User enters an invalid rate.
   * 2c1. System displays that the rate is invalid and to try again.

      Use case resumes at step 2.

* 3a. The event has a rate tagging already.
   * 3a1. System replaces the previous rate with the new rate.

      Use case ends. -->

**Use case: UC7 - Add Contact**

**MSS**

1.  User requests to add a contact to an event.
2.  System adds the contact to the event and displays information of the contact and event.

   Use case ends.

**Extensions**

* 1a. User enters the wrong command.
   * 1a1. System displays that the command is invalid and to try again.

      Use case resumes at step 1.

* 1b. User enters invalid contact details.
   * 1b1. System displays that the input is invalid and to try again.

      Use case resumes at step 1.

**Use case: UC8 - Add Date to Event**

**MSS**

1. User <u>lists events (UC4)</u>.
2. User requests to add a date to a specified event in the list.
3. System adds the date to the event and displays information of the event with new date added.

   Use case ends.

**Extensions**

 2a. User enters the wrong command.
   * 2a1. System displays that the command is invalid and to try again.

      Use case resumes at step 2.

* 2b. User enters an invalid index.
   * 2b1. System displays that the index is invalid and to try again.

      Use case resumes at step 2.

* 2c. User enters an invalid date.
   * 2c1. System displays that the date is invalid and to try again.

      Use case resumes at step 2.

* 3a. The event has a date linked already.
   * 3a1. System replaces the previous date with the new date.

      Use case ends.
      
**Use case: UC9 - Edit Event**

**MSS**

1. User <u>lists events (UC4)</u>.
2. User requests to edit a specified event in the list.
3. System updates the event with the edit and displays information of the event edited.

   Use case ends.

**Extensions**

* 2a. User enters the wrong command.
   * 2a1. System displays that the command is invalid and to try again.

      Use case resumes at step 2.

* 2b. User enters an invalid index.
   * 2b1. System displays that the index is invalid and to try again.

      Use case resumes at step 2.

* 2c. User enters an invalid argument format.
   * 2c1. System displays that the format is invalid and to try again.

      Use case resumes at step 2.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
3. Should be able to hold up to 1000 contacts without a noticeable sluggishness in performance for typical usage.
4. Should be able to hold up to 1000 events without a noticeable sluggishness in performance for typical usage.
5. The system should work on both 32-bit and 64-bit environments.
6. The source code should be open source.
7. The product is offered as a free product.
8. The system should respond within two seconds for events and persons up to 1000.
9. The user interface should be intuitive enough for users who are not as IT-savvy.
10. The dimensions should be at least `726px * 593px` so that it is readable for the user.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## Appendix: Instructions for manual testing

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


[Back to top](#)
