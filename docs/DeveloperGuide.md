---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

------------------------------------------------------------------------------------------------------------------------

coNtactUS is a **university module tracker and timetable/deadline sorting application**, optimized for use via a
**Command Line Interface (CLI)** while still having the _benefits_ of a **Graphical User Interface (GUI)**. This
application uses the **AddressBook-Level3** project created by the [SE-EDU initiative](https://se-education.org/)
as its framework.


<!--## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation,
and third-party libraries -- include links tothe original source as well} -->

<!-- -------------------------------------------------------------------------------------------------------------- -->

<!-- ## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder.
Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to
learn how to create and edit diagrams.
</div>
-->

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called
[`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java).
It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class
* (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality
using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given
component through its interface rather than the concrete class (reason: to prevent outside component's being coupled
to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`,
`ModuleListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart`
class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files
that are in the `src/main/resources/view` folder. For example, the layout of the
[`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in
[`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Module` object residing in the `Model`.

### Logic component

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `ModuleTrackerParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is
executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a module).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")`
API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `ModuleTrackerParser` class creates an `XYZCommandParser` (`XYZ` is a
placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ModuleTrackerParser` returns back
as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** :
[`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the module tracker data i.e., all `Module` objects (which are contained in a `UniqueModuleList` object).
* stores the currently 'selected' `Module` objects (e.g., results of a search query) as a separate _filtered_ list
which is exposed to outsiders as an unmodifiable `ObservableList<Module>` that can be 'observed' e.g. the UI can be
bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain,
they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP)
model is given below. It has a `Tag` list in the `ModuleTracker`, which `Module` references. This allows `ModuleTracker`
to only require one `Tag` object per unique tag, instead of each `Module` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** :
[`Storage.java`](https://tinyurl.com/3dmsfunt)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both module tracker data and user preference data in json format, and read them back into corresponding
objects.
* inherits from both `ModuleTrackerStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.moduletracker.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Find feature

#### Find Command Implementation
This section will explain the implementation of the FindCommand and the FindCommandParser. The FindCommand allows users
to search for modules whose names or types contain any of the specified keywords (case-insensitive).

FindCommand Class
The FindCommand class is responsible for finding and listing all modules in the module tracker whose name contains any
of the argument keywords. Keyword matching is case insensitive.
The FindCommandParser is responsible for parsing the input given by the user.

The FindCommand utilizes the FilteredList from JavaFx and uses a predicate to initialize a FindCommand object. This
predicate is programmed such that it will match for the name of the module and type of the module.

The predicate passed to the FindCommand constructor is from the class NameContainsKeywordsPredicate. This class has a
test method which is used by the FilteredList.

Given below is an example usage scenario and how the find command behaves at each step.

Step 1. The user launches the application for the first time. The `ModuleTracker` will be initialized with the initial
module tracker state.

Step 2. The user executes `find CS3263` command. The 'find CS3263' will be handled by the LogicManager and
ModuleTrackerParser which will extract out the needed argument, more importantly the predicate.

Step 3. Now, the command is executed through the execute method which will update the list through
`Model#updateFilteredModuleList`

Step 4. The method set the predicate to the filtered list which will run the `NameContainsKeywordsPredicate#test()` to
find the items based on name or type

<!-- ### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedModuleTracker`. It extends `ModuleTracker` with an
undo/redo history, stored internally as an `moduleTrackerStateList` and `currentStatePointer`. Additionally, it
implements the following operations:

* `VersionedModuleTracker#commit()` — Saves the current module tracker state in its history.
* `VersionedModuleTracker#undo()` — Restores the previous module tracker state from its history.
* `VersionedModuleTracker#redo()` — Restores a previously undone module tracker state from its history.

These operations are exposed in the `Model` interface as `Model#commitModuleTracker()`, `Model#undoModuleTracker()`
and `Model#redoModuleTracker()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedModuleTracker` will be initialized with the
initial module tracker state, and the `currentStatePointer` pointing to that single module tracker state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th module in the module tracker. The `delete` command calls
`Model#commitModuleTracker()`, causing the modified state of the module tracker after the `delete 5` command executes to
be saved in the `moduleTrackerStateList`, and the `currentStatePointer` is shifted to the newly inserted module tracker
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new module. The `add` command also calls
`Model#commitModuleTracker()`, causing another modified module tracker state to be saved into the
`moduleTrackerStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will
not call `Model#commitModuleTracker()`, so the module tracker state will not be saved into the `moduleTrackerStateList`.

</div>

Step 4. The user now decides that adding the module was a mistake, and decides to undo that action by executing the
`undo` command. The `undo` command will call `Model#undoModuleTracker()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous module tracker state, and restores the module tracker to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0,
pointing to the initial ModuleTracker state, then there are no previous ModuleTracker states to restore. The `undo`
command uses `Model#canUndoModuleTracker()` to check if this is the case. If so, it will return an error to the user
rather than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end
at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoModuleTracker()`, which shifts the
`currentStatePointer` once to the right, pointing to the previously undone state, and restores the module tracker
to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index
`ModuleTrackerStateList.size() - 1`, pointing to the latest module tracker state, then there are no undone ModuleTracker
states to restore. The `redo` command uses `Model#canRedoModuleTracker()` to check if this is the case. If so, it will
return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the module tracker, such as
`list`, will usually not call `Model#commitModuleTracker()`, `Model#undoModuleTracker()` or `Model#redoModuleTracker()`.
Thus, the `moduleTrackerStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitModuleTracker()`. Since the `currentStatePointer` is not
pointing at the end of the `moduleTrackerStateList`, all module tracker states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" /> -->

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire module tracker.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the module being deleted).
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

* NUS Computing Students who are more tech savvy than the general population and are also fast typists
* computing students would have to refer to these details regularly throughout the course of the semester as they may
not be able to remember them
* has a need to manage a significant number of lecture
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​           | I want to …​                                  | So that I can…​                      |
|----------|-------------------|-----------------------------------------------|--------------------------------------|
| `* * *`  | computing student | see some sample data                          | know how to use the program          |
| `* * *`  | computing student | add lectures, deadlines, and tutorials        |                                      |
| `* * *`  | computing student | delete an item                                | remove entries that I no longer need |
| `* * *`  | computing student | edit an item                                  |                                      |
| `* * *`  | computing student | see all my lectures, deadlines, and tutorials |                                      |
| `* *`    | computing student | sort my lectures, deadlines, and tutorials    | prioritise certain modules           |
| `* *`    | computing student | sort my exams by time                         | view my exam schedule                |
| `* *`    | diligent student  | add notes and remarks to my sc                |                                      |
| `* *`    | unorganised user  | search for my modules by name or type         | find specific information quickly    |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Contact nUS` and the **Actor** is an `NUS computing student`, unless
specified otherwise)

**Use case 1: Delete a module**

**MSS**

1.  User requests to list items.
2.  Contact nUS shows a list of items such as lectures.
3.  User requests to delete a specific lecture in the list.
4.  Contact nUS deletes the module.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. Contact nUS shows an error message.

      Use case resumes at step 2.

**Use case 2: Edit a module**

**MSS**

1.  User requests to list items.
2.  Contact nUS shows a list of items such as lectures.
3.  User requests to edit a specific attribute module in the list.
4.  Contact nUS edits the specific attribute of the module.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. Contact nUS detects an incorrect format of an attribute(s)

    * 3a1. Contact nUS shows an error message.

    * 3a2. User enters the information again.

    * 3a3. Repeat steps 3a1 and 3a2 until the details provided are acceptable. <br>

      Use case resumes at step 4.

* 3b. Contact nUS detects missing information in the command.

    * 3b1. Contact nUS shows an error message.

    * 3b2. User enters the information again.

    * 3b3. Repeat steps 3b1 and 3b2 until there are no more missing information. <br>

      Use case resumes at step 4.

**Use case 3: Adding a module**

**MSS**

1.  User adds a new module detail by entering the command.
2.  Contact nUS adds and displays a newly added module detail to the user. <br>

    Use case ends.

**Extensions**

* 1a. Contact nUS detects an incorrect format of an attribute(s)

    * 1a1. Contact nUS shows an error message.

    * 1a2. User enters the information again.

    * 1a3. Repeat steps 1a1 and 1a2 until the details provided are acceptable. <br>

      Use case resumes at step 2.

* 1b. Contact nUS detects missing information in the command.

    * 1b1. Contact nUS shows an error message.

    * 1b2. User enters the information again.

    * 1b3. Repeat steps 1b1 and 1b2 until there are no more missing information. <br>

      Use case resumes at step 2.


*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 modules without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
be able to accomplish most of the tasks faster using commands than using the mouse.
4. Only language supported is English

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Module**: The module to be recorded in Contact nUS
* **Address**: The location/venue for the class of a module
* **Deadline**: The deadline for tasks of a module
* **Name**: Name of a module
* **Remark**: Additional information regarding a certain module
* **Resource**: External website or links for a module
* **Tag**: The type of the module (Lecture, Tutorial, Lab, etc.)
* **Teacher**: The name of the teacher conducting the module
* **TimeSlot**: The time of the class of a module

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting
point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample modules. The window size may not be
   optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. _{ more test cases …​ }_

### Deleting a module

1. Test case: `delete 1`<br>
      Expected: First module is deleted from the list. Details of the deleted module shown in the status message.
Timestamp in the status bar is updated.

2. Test case: `delete 0`<br>
      Expected: No module is deleted. Error details shown in the status message. Status bar remains the same.

3. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

4. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

2. _{ more test cases …​ }_

### Adding a module

1. Adding a module

    1. Test case: `add n/CS2103T p/Tutorial a/COM1`<br>
       Expected: A new module is added to the list with name being CS2103T and type being Tutorial. Details of the added
   module is shown in the status message. Empty optional fields are left as "None.".

    2. Test case: `add n/CS2101 p/Lecture a/COM3 e/210323 10:00 s/Mr Ng`<br>
       Expected:  A new module is added to the list with name being CS2101, type being Lecture, address being COM3,
   TimeSlot being 210323 10:00, teacher being Mr Ng. Details of the added module is shown in the status message.

    3. Test case: `add n/CS1101S`<br>
       Expected: No module is added as type, which is a compulsory field, is missing. Error details shown in the
   status message.

### Editing a module
1 Editing a module
  1. Test case: `edit 1 n/CS1101S` <br>
      Expected: The name of the first module in the list is edited, and is now `CS1101S`. Details of the updated module
is shown in the status message.

  2. Test case: `edit 2 e/310323 14:00` <br>
     Expected: The time slot of the first module in the list is edited, and is now `Friday 02:00PM`. Details of the
updated module is shown in the status message.

  3. Test case: `edit 1 t/Lecture` <br>
     Expected: The type of the first module in the list is edited, and is now `Lecture`. Details of the updated module
is shown in the status message.

### Finding a module/type
1. Finding a module

    1. Test case: `find CS2103T`
       Expected: A module is found on the list. Details of the found module is shown in the list. <br>

    2. Test case: `find tutorial`
       Expected: A tutorial type is found on the list. Details of the found tutorials are shown in the list. <br>

    3. Test case: `find tutorial lab`
       Expected: A tutorial or lab type is found on the list. Details of the found tutorials and labs are shown in
   the list. <br>

    4. Note: Make sure to list the modules again when you want to find another module.
