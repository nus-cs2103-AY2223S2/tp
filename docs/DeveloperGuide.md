---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

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

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `MeetingListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` and `Meeting` objects residing in the `Model`.

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

* stores the address book data i.e., all `Person` and has references to all the `Meeting` objects of every `Person`. (which are all contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` and `Meeting` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` and `ObservableList<MeetingWithPerson>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* Note: `MeetingWithPerson` is a child class of `Meeting` that also stores a reference to the parent `Parent` object. This is done so the UI can display information about the `Person` to meet.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

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

### 1. Meeting Feature

Every `Person` has a list that can hold `Meeting` objects. `Meeting` encapsulates a meeting for the client by the user.
Each `Person` have at least more than one `Meeting`, as long as the period of meeting does not clash the period of existing
meetings scheduled for that `Person`.

### 2. Add Meeting Feature

#### 2.1 Implementation

The `MainWindow#executeCommand()` calls `LogicManager#execute()` method, which proceeds to call `AddressBookParser#parseCommand()`.
`AddMeetingCommandParser#parse()` is called, which returns an `AddMeetingCommand` object.

- Checks that the command contains `AddMeetingCommand.COMMAND_WORD`
- `AddMeetingCommand` - Represents add meeting command executed by FAid

  - Takes in a `String` as the description of the meeting to be added
  - Takes in an `Index` object to assign a meeting to a person at the specified index.
  - Takes in a `Meeting` object to assign to the specified person.
  - `AddMeetingCommand#execute()` ensures that the index specified is valid and does not add meeting if it clashes with existing meetings scheduled
  for the day specified

After being parsed, the `AddMeetingCommand#execute()` method is called, scheduling a meeting for the specified person.
The following sequence diagrams illustrates the description for adding meeting. Diagram 1 is a continuation of Diagram 2:

**Diagram 1**
![AddMeetingSequenceDiagram](images/AddMeetingParseSequenceDiagram.PNG)

**Diagram 2**
![AddMeetingExecuteSequenceDiagram](images/AddMeetingExecuteSequenceDiagram.PNG)

#### 2.2 Design Consideration

**Aspect: meetingAdd format:**

- **Alternative 1**: ` INDEX md/ [DESC] mdt/ [DATE] [START TIME] [END TIME]` command format
  - Pros: Shorter command for user to type
  - Cons: Harder to implement to address for cases where meeting goes past midnight (signifiying a new day).

- **Alternative 2(current choice)**: `INDEX md/ [DESC] ms/ [START DATE & TIME] me/ [END DATE & TIME]` command format

  - Pros: Simpler to implement; can account for meetings that go past midnight
  - Cons: Longer command for user to type

### 3. Remove Meeting Feature

#### 3.1 Implementation

The `MainWindow#executeCommand()` calls `LogicManager#execute()` method, which proceeds to call `AddressBookParser#parseCommand()`.
`RemoveMeetingCommandParser#parse()` is called, which returns an `RemoveMeetingCommand` object.

- Checks that the command contains `RemoveMeetingCommand.COMMAND_WORD`
- `RemoveMeetingCommand` - Represents remove meeting command executed by FAid

    - Takes in 2 `Index` objects:
      - Index of person to find
      - Index of meeting to find

After being parsed, the `RemoveMeetingCommand#execute()` method is called, remove a meeting at the specified index
for the specified person. The following sequence diagram illustrates the description for removing meeting:

![RemoveMeetingSequenceDiagram](images/RemoveMeetingSequenceDiagram.PNG)

#### 3.2 Design consideration

**Aspect: meetingRemove format:**

- **Alternative 1(current choice)**: `meetingRemove CLIENT_INDEX MEETING_INDEX` command format; MEETING_INDEX refers to the internal meeting list of `Person`
    - Pros: Simpler to implement
    - Cons: Can cause more confusion

- **Alternative 2(future plan)**: `meetingRemove MEETING_INDEX` command format; MEETING_INDEX refers to the meeting list in Meeting List Panel instead
    - Pros: Easier to use
    - Cons: Required overhaul of implementation of meetingRemove (To be implemented in future version)

### 4. Region Feature

Every `Address` is composed with an additional `Regions` Enumeration, which represents all the 5 regions in Singapore.
When an `Address` object is created, the `Region` class processes the address and allocates a `Regions` Enum to the `Address` object.
Internally, the `Region` class has a list of all major town names in Singapore, and attempts to match one of them to the actual address string.

![RegionSequenceDiagram](images/RegionSequenceDiagram.png)

### 5. Update Meeting Feature

#### 5.1 Implementation

The update meeting feature is handled by the following classes:
* `UpdateMeetingCommandParser` - Checks that the command is in the right format, then
  parses the input to extract PersonID, MeetingID and updated Meeting details.
    * After doing so, an `editMeetingDescriptor` object is created. The `editMeetingDescriptor` object
      stores the details to edit the Meeting's description, start or end with.
    * Thereafter, `UpdateMeetingCommandParser#parse()` is called and returns an
      `UpdateMeetingCommand` object with the extracted PersonID, MeetingID and `editMeetingDescriptor`
* `UpdateMeetingCommand` - The update Meeting command that will be executed by FAid
    * The `UpdateMeetingCommand` extends the `Command` interface and implements the `Command#execute()`
      method.

Just like other commands, the `Command#execute()` method of `UpdateMeetingCommand` is handled by
`Logic` component. Please refer to the 'Logic component' under 'Design' for more
information on how the `Logic` component handles a command.

The parsing and execution of updateMeeting command can be shown with the following
sequence diagrams. Note that Diagram 3 is a continuation of Diagram 4. <br>

**Diagram 3**
![UpdateMeetingSequenceDiagram](images/UpdateMeetingSequenceDiagram1.png)

**Diagram 4**
![UpdateMeetingSequenceDiagram](images/UpdateMeetingSequenceDiagram2.png)

### 6. Find Meeting Feature

#### 6.1 Implementation

The find meeting feature is handled by the following classes:
* `FindMeetingCommandParser` - Checks that the command is in the right format, then
  parses the input to extract PersonID, LocalDate.
* `FindMeetingCommandParser#parse()` is called and returns an
  `FindMeetingCommand` object with the extracted PersonID or LocalDate
* `FindMeetingCommand` - The update Meeting command that will be executed by FAid
    * The `FindMeetingCommand` extends the `Command` interface and implements the `Command#execute()` method.

Just like other commands, the `Command#execute()` method of `FindMeetingCommand` is handled by
`Logic` component. Please refer to the 'Logic component' under 'Design' for more
information on how the `Logic` component handles a command.

The parsing and execution of FindMeeting command can be shown with the following
sequence diagram:

![FindMeetingSequenceDiagram](images/FindMeetingSequenceDiagram.png)

### 7. Policy Tag Feature

#### 7.1 Implementation

Every `Person` contains a `PolicyTag`, which represents financial policies adopted by the user's clients and prosepctive clients.
Every time a new `Person` is created, it contains a list of `PolicyTag` objects, which can be empty or non-empty. A new `PolicyTag` object
is created with the use of `add` command when a new `Person` is added or `edit` command when a new policy needs to be added
under an existing `Person`.

### 8. Find Policy Feature

#### 8.1 Implementation

The `MainWindow#executeCommand()` calls `LogicManager#execute()` method, which proceeds to call `AddressBookParser#parseCommand()`.
`FindPolicyCommandParser#parse()` is called, which returns an `FindPolicyCommand` object.

* `FindPolicyCommandParser`:
  * checks that the command contains `FindPolicyCommand.COMMAND_WORD`.
  * checks that the arguments given are Strings.
* `FindPolicyCommand`:
  * updates list of `Person` objects with a filtered list of `Person` objects with matching policy names

After being parsed, the `FindPolicyCommand#execute()` method is called, a filtered list of `Person` objects with matching
policy names are displayed. The following sequence diagram illustrates the description for finding policy. Diagram 5 is followed by Diagram 6:

**Diagram 5**
![FindPolicyParseSequenceDiagram](images/FindPolicyParseSequenceDiagram.png)

**Diagram 6**
![FindPolicyExecuteSequenceDiagam](images/FindPolicyExecuteSequenceDiagram.PNG)

#### 8.2 Design consideration

**Aspect: How PolicyTag works:**

- **Alternative 1(current choice)**: Convert `Tag` class to `PolicyTag`
    - Pros: Just simple refactoring of class name
    - Cons: Allows keywords not related to financial policies to be parsed

- **Alternative 2(future plan)**: Ensure `PolicyTag` accepts a set of names only

    - Pros: Ensures PolicyTag names have financial policies only

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
* Financial advisors who has to manage a significant number of contacts and meetings
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* has a large number of meetings to keep track of
* wants to minimise time spent commuting

**Value proposition**: manage clients and meetings faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                                             | I want to …​                                       | So that I can…​                                                           |
|----------|---------------------------------------------------------------------|----------------------------------------------------|---------------------------------------------------------------------------|
| `* * *`  | financial advisor                                                   | see all my meetings for today                      | plan and prepare for my meetings today effectively                        |
| `* * *`  | financial advisor                                                   | add a new meeting                                  | keep track of a meeting with a client using this app                      |
| `* * *`  | financial advisor                                                   | filter my meetings                                 | find clients based on some condition                                      |
| `* * *`  | financial advisor                                                   | remove a meeting from my schedule                  | remove meetings that are no longer relevant                               |
| `* * *`  | financial advisor who has to travel to meet their client physically | set which region of singapore my client is in      | keep track of the general location of my client                           |
| `* * *`  | financial advisor who prefers physical meetings with clients        | see all meetings in a specific region of singapore | minimize travel time by meeting all clients that live close to each other |
| `* *`    | financial advisor                                                   | know when the last meeting with a client was       | avoid losing a client if they find me too annoying                        |
| `* *`    | financial advisor                                                   | classify my meetings into different types          | organise my meetings better                                               |
| `* `     | financial advisor                                                   | record my meetings                                 | look back to reflect and improve on my skills                             |
| `* *`    | financial advisor                                                   | add notes about a client                           | keep track of things that are important to a client                       |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `FAid` and the **Actor** is the `user`, unless specified otherwise)

**Use case: *UC01 Delete a person***

**Actor: User**

**MSS**

1.  User requests to list persons
2.  FAid shows a list of persons
3.  User requests to delete a specific person in the list
4.  FAid deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

**Use case: *UC02 Schedule a meeting with new client***

**Actor: User**

**MSS:**

1. User request to add a new client with relevant information
2. FAid adds new client’s information to the list
3. User request to add meeting date and time for the same client
4. FAid adds meeting date and time for that client

   Use Case Ends

**Extensions:**

* 2a. FAid detects clash in meeting date and time
   * 2a1. FAid prompts User again to add a different meeting date and time
   * 2a2. User enters a new meeting date and time

     Use Case resumes at step 4.

**Use Case: *UC03 View meetings for current day***

**Actor: User**

**MSS:**

1. User opens FAid
2. FAid notifies user of all upcoming meetings for the day
3. User chooses to view all meetings for the current day
4. FAid shows all meetings that start on the current day

   Use case ends

**Extensions:**
* 2a. There are no meetings for the day
  * 2a1. FAid notifies that there are no upcoming meetings for the day

     Use case ends

**Use Case: *UC04 Find clients residing in a certain region of Singapore***

**Actor: User**

**MSS:**

1. User opens the app
2. User requests to find clients in a specific region
3. FAid shows all clients residing in a specific region

   Use case ends

**Extensions:**

* 3a.  FAid cannot find the specified region entered
  * 3a1. User enters new region name
  * 3a2. Steps 3a1 and 3a are repeated until a valid region name is provided

    Use case resumes from step 3

**Use Case: *UC05 Get list of clients based on a policy***

**Actor: User**

**MSS:**

1. User opens the app
2. User wants to see all persons with a certain policy name
3. System requests for the policy name
4. User enters the policy name
5. System displays all persons with the given policy name

   Use Case Ends

**Extensions:**
* 4a. System cannot find the policy name supplied by the user
  * 4a1. System requests for the correct policy name
  * 4a2. User enters a new policy name

    Steps 4a1-4a2 are repeated until the user provides a correct policy name
    Use Case resumes from step 5

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A new version of FAid is released every month.
5. Data stored is not wiped out outside the program's runtime or in the event of an interrupt.
6. All non-helper functions implemented in FAid have JavaDoc documentation.
7. No command should require more than 5 arguments/options.
8. Every function is deterministic and has a test case with an expected output.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Region** - General Areas in Singapore separated into North, South, East, West, Central
* **Town** - An area defined by the National Environment Agency
* **Meeting** - An appointment with the user’s client starting and ending at stipulated times
* **Day** - Spans from 00:00 to 23:59. Not by conventional office hours.
* **Client** - Potential or actual buyers of insurance sold by the use


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

2. _{ more test cases …​ }_

## Appendix: Planned/Proposed Enhancements to known Feature Flaws

1. Currently, the meetings in the list shown by `listMeeting` command is unsorted as it can affect other commands adversely. We plan to sort meetings in that list by chronological order without affecting other commands.
2. Currently, meetingRemove and meetingUpdate has `CLIENT_INDEX` and `MEETING_INDEX` as inputs. `MEETING_INDEX` refers to the the index of meeting list given by `meetingFind CLIENT_INDEX`. We plan to rework meetingRemove and meetingUpdate to take in only one index instead of two indexes - the sole index will refer to
the list shown by `listMeeting`.
3. Currently, PolicyTag accepts any keywords to be set as the name. We plan to rework PolicyTag to only allow a set of PolicyTag names to accept only financial policies
4. Currently there is no way to manually set the region of a person, and addresses that the detector doesn't recognise will be
   defaulted to Unknown. One possible enhancement is to allow a `setRegion` command that allows users to set region manually
   and also edit regions that the detector has detected wrongly.
5. The `DESCRIPTION` parameter of all meeting commands will take in more than just alphanumberical inputs. However, we intend to
rework this so that it will only be able to accept alphanumerical inputs.
