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


<!-- -------------------------------------------------------------------------------------------------------------- -->

------------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

No external sources or libraries were used.

------------------------------------------------------------------------------------------------------------------------
 ## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md) to start developing your own coNtactUS.

--------------------------------------------------------------------------------------------------------------------

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />
Architecture of the module tracker.

The ***Architecture Diagram*** given above explains the high-level design of the module tracker.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called
[`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java).
It is responsible for,
* At software launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the module tracker consists of four components.

* [**`UI`**](#ui-component): The UI of the module tracker.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the module tracker in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="https://ay2223s2-cs2103t-w10-1.github.io/tp/images/ArchitectureSequenceDiagram.png" width="574" />

Figure 1: How a `delete 1` function is parsed through the different components.

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
The structure of the UI Component

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

<img src="images/LogicClassDiagram.png" width="650"/>
The partial structure of the Logic component 

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `ModuleTrackerParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is
executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a module).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")`
API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)
How an `execute("delete 1")` function is processed in the Logic Component

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>
The structure of the Logic Component, displaying how a Parser works

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

<img src="images/ModelClassDiagram.png" width="800" />
The structure of the Model component


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

<img src="images/BetterModelClassDiagram.png" width="650" />
A more OOP model of the Model component

</div>


### Storage component

**API** :
[`Storage.java`](https://tinyurl.com/3dmsfunt)

<img src="images/StorageClassDiagram.png" width="550" />
The structure of the Storage component

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

### Sort Feature

#### Sort Command Implementation
Some txt here.

### Find feature

#### Find Command Implementation
This section explains the implementation of the **FindCommand** and **FindCommandParser**. The **FindCommand** allows users to search for modules whose names or types contain any of the specified keywords (case-insensitive).

##### FindCommand Class
The **FindCommand** class is responsible for finding and listing all modules in the module tracker whose name contains any of the argument keywords. Keyword matching is case-insensitive. The **FindCommandParser** is responsible for parsing the user input.

The **FindCommand** utilizes the **FilteredList** from JavaFX and uses a predicate to initialize a **FindCommand** object. This predicate is programmed to match both the name and the type of the module.

The predicate passed to the **FindCommand** constructor is from the **NameContainsKeywordsPredicate** class. This class has a test method, which is used by the **FilteredList**.

Below is an example usage scenario and how the find command behaves at each step:

**Step 1:** he user launches the application for the first time. The **ModuleTracker** will be initialized with the initial module tracker state.

**Step 2:** The user writes "find CS3263" in the terminal. This command will be handled by the **LogicManager** and **ModuleTrackerParser**, which will extract the needed argument, most importantly the predicate.

**Step 3:** The command is executed through the execute method, which updates the list through **Model#updateFilteredModuleList**.

**Step 4:** The method sets the predicate to the filtered list, which runs the **NameContainsKeywordsPredicate#test()** method to find the items based on name or type.

### Reminder feature

#### Reminder Implementation
This section explains the implementation of the reminder feature. The reminder allows users to view all the timeslots and deadlines on the current day (based on the system's time).

The **LogicManager** class is responsible for finding all the modules in the module tracker whose date (timeslot or deadline) matches the current date. The **UiManager** class is responsible for alerting the user of the deadlines and timeslots for today.

There are 3 methods in the **LogicManager** class:
1. A method that deals with finding the timeslots for the day.
2. A method that deals with finding the deadlines for the day.
3. A method that combines and parses everything into a string.

The string is then passed to a method in the **UiManager** that handles showing the alert to the user upon launching the application. Exceptions and null values are handled to avoid processing null dates, as they are optional fields.

Exceptions and null values are handled to avoid looking at null dates as they are optional.

Below is an example usage scenario of the reminder feature:

**Step 1:** The user launches the application for the first time. The **`ModuleTracker`** will be initialized with the initial module tracker state.

**Step 2:** An alert pops up, showing the user the timeslots and deadlines for the day, along with the module name and type. The title of the alert is "Work Today."

**Step 3:** The user can now close the alert and continue using the application.


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

## \[Proposed\] Planned Enhancements

### Reminder command.
We propose extending the reminder feature to allow users to call it at any time, instead of only at the application's launch. Additionally, we plan to enable users to view reminders for a specific day, week, or month.

To implement this, we plan to add a new command, similar to the existing commands, by creating a **ReminderCommand** class and a **ReminderCommandParser** class, analogous to the **FindCommand** and **FindCommandParser**.

These enhancements should provide users with more flexibility in accessing and managing their reminders, leading to an improved user experience.


* More checks to teacher, remarks and one more i forgot.

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

**Target user profile**

* NUS Computing Students who are more tech savvy than the general population and are also fast typists
* computing students would have to refer to these details regularly throughout the course of the semester as they may
not be able to remember them
* has a need to manage a significant number of lecture
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**
* Text-based university module tracker with the ability to:
  * Add modules to be tracked
  * List out all tracked modules
  * Edit attributes on existing modules
  * Delete modules
  * Find a specific module by name or type
  * Sort existing modules based on their deadline or class time slots


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
| `* *`    | diligent student  | add notes and remarks to my tracked module    |                                      |
| `* *`    | unorganised user  | search for my modules by name or type         | find specific information quickly    |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `coNtactUS` and the **Actor** is an `NUS computing student`, unless
specified otherwise)

**Use case 1: Delete a module**

**MSS**

1.  User requests to list items.
2.  coNtactUS shows a list of items such as lectures.
3.  User requests to delete a specific lecture in the list.
4.  coNtactUS deletes the module.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. coNtactUS shows an error message.

      * 3a1. coNtactUS shows an error message.

      * 3a2. User enters the information again.

      * 3a3. Repeat steps 3a1 and 3a2 until the details provided are acceptable. <br>

        Use case resumes at step 4.

* 3b. coNtactUS detects missing information in the command.

  * 3b1. coNtactUS shows an error message.

  * 3b2. User enters the information again.

  * 3b3. Repeat steps 3b1 and 3b2 until there are no more missing information. <br>

    Use case resumes at step 4.

**Use case 2: Edit a module**

**MSS**

1.  User requests to list items.
2.  coNtactUS shows a list of items such as lectures.
3.  User requests to edit a specific attribute module in the list.
4.  coNtactUS edits the specific attribute of the module.

      Use case ends.

      **Extensions**

* 2a. The list is empty.

    Use case ends.

* 3a. coNtactUS detects an incorrect format of an attribute(s)

  * 3a1. coNtactUS shows an error message.

  * 3a2. User enters the information again.

  * 3a3. Repeat steps 3a1 and 3a2 until there the formats of the attributes are correct. <br>
  Use case resumes at step 4.

* 3b. The given index is invalid.

    * 3b1. coNtactUS shows an error message.

        * 3b1. coNtactUS shows an error message.

        * 3b2. User enters the information again.

        * 3b3. Repeat steps 3b1 and 3b2 until the details provided are acceptable. <br>
  Use case resumes at step 4.

**Use case 3: Adding a module**

**MSS**

1.  User adds a new module detail by entering the command.
2.  coNtactUS adds and displays a newly added module detail to the user. <br>

    Use case ends.

**Extensions**

* 1a. coNtactUS detects an incorrect format of an attribute(s)

    * 1a1. coNtactUS shows an error message.

    * 1a2. User enters the information again.

    * 1a3. Repeat steps 1a1 and 1a2 until the details provided are acceptable. <br>

      Use case resumes at step 2.

* 1b. coNtactUS detects missing information in the command.

    * 1b1. coNtactUS shows an error message.

    * 1b2. User enters the information again.

    * 1b3. Repeat steps 1b1 and 1b2 until there are no more missing information. <br>

      Use case resumes at step 2.
  

**Use case 4: Finding a specific module**

**MSS**

1.  User requests to list all the modules.
2.  coNtactUS shows a list of all the modules added into the module tracker. 
3.  User requests to find a specific module in the list with a specified keyword.
4.  coNtactUS shows a filtered list of the modules that contain the specified keyword.

    Use case ends.

**Extensions**

* 2a. The list is empty.

    Use case ends.

* 4a. None of the existing modules contain the keyword.
   
    Use case ends.

**Use case 5: Sorting the modules by `timeslot`**

**MSS**

1.  User requests to list all the modules.
2.  coNtactUS shows a list of all the modules added into the module tracker.
3.  User requests to sort the list by `timeslot`.
4.  coNtactUS shows a filtered, reordered list of modules with the earliest `timeslot` at the top and latest at the
bottom. Modules without `timeslot` will be pushed to the back, unsorted.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. coNtactUS detects missing information in the command.

    * 3a1. coNtactUS shows an error message.

    * 3a2. User enters the information again.

    * 3a3. Repeat steps 3a1 and 3a2 until there are no more missing information. <br>

      Use case resumes at step 4.

* 4a. None of the existing modules contain `timeslot`.

  Use case ends.

**Use case 6: Sorting the modules by `deadline`**

**MSS**

1.  User requests to list all the modules.
2.  coNtactUS shows a list of all the modules added into the module tracker.
3.  User requests to sort the list by `deadline`.
4.  coNtactUS shows a filtered, reordered list of modules with the earliest `deadline` at the top and latest at the
    bottom. Modules without `deadline` will be pushed to the back, unsorted.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. coNtactUS detects missing information in the command.

    * 3a1. coNtactUS shows an error message.

    * 3a2. User enters the information again.

    * 3a3. Repeat steps 3a1 and 3a2 until there are no more missing information. <br>

      Use case resumes at step 4.

* 4a. None of the existing modules contain `deadline`.

  Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 modules without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
be able to accomplish most of the tasks faster using commands than using the mouse.
4. Only language supported is English
5. The software size (including the data file with up to 1000 modules) should not exit 100MB.
6. 

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private module detail**: A module detail that is not meant to be shared with others
* **Module**: The module to be recorded in coNtactUS
* **Address**: The location/venue for the class of a module
* **Deadline**: The deadline for tasks of a module
* **Name**: Name of a module
* **Remark**: Additional information regarding a certain module
* **Resource**: External website or links for a module
* **Description**: The type of the module (Lecture, Tutorial, Lab, etc.)
* **Teacher**: The name of the teacher conducting the module
* **TimeSlot**: The time of the class of a module
* **XYZCommand**:
* **Software**:
* **API**:
* **CLI**:
* **GUI**:
* **Module Tracker**:
* **UML**:
* **Parser**:

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


### Adding a module

1. Adding a module

     1. Test case: `add n/CS2103T t/Tutorial a/COM1`<br>
   Expected: A new module is added to the list with name being CS2103T and type being Tutorial. Details of the added
   module is shown in the status message. Empty optional fields are left as "None.".

     2. Test case: `add n/CS2101 t/Lecture a/COM3 e/210323 10:00 s/Mr Ng`<br>
   Expected:  A new module is added to the list with name being CS2101, type being Lecture, address being COM3,
   TimeSlot being 210323 10:00, teacher being Mr Ng. Details of the added module is shown in the status message.

     3. Test case: `add n/CS1101S`<br>
   Expected: No module is added as type, which is a compulsory field, is missing. Error details shown in the
   status message.

     4. Other incorrect add commands to try: `add`, `add x`, `...` (where x is the attribute you want to add, without
   using the prefixes)<br>
   Expected: Similar to previous.

### Editing a module
  1. Test case: `edit 1 n/CS1101S` <br>
   Expected: The name of the first module in the list is edited, and is now `CS1101S`. Details of the updated module
   is shown in the status message.

2. Test case: `edit 2 e/310323 14:00` <br>
   Expected: The time slot of the first module in the list is edited, and is now `Friday 02:00PM`. Details of the
   updated module is shown in the status message.

3. Test case: `edit 1 t/Lecture` <br>
   Expected: The type of the first module in the list is edited, and is now `Lecture`. Details of the updated module
   is shown in the status message.

4. Test case: `edit s/Mr Lee` <br>
   Expected: No module is edited, because the index number is not provided.

5. Test case: `edit 1` <br>
   Expected: No module is edited, because none of the optional fields are provided.

6. Test case: `edit 0 r/Pay attention in class` <br>
   Expected: No module is edited, because the index number is invalid.

7. Other incorrect edit commands to try: `edit`, `edit x`, `edit x y` (where x is larger than the list size,
   and y is the attribute you want to edit, either without using the prefixes, or the format is incorrect).
   Expected: Similar to previous.

### Finding a module/type

1. Test case: `find CS2103T` <br>
   Expected: Module(s) with the name `CS2103T` is (are) found. Details of the found module(s) are shown in the list.
   <br>

2. Test case: `find tutorial`
Expected: Module(s) with the description `tutorial` Details of the found tutorial(s) are shown in the list. <br>

3. Test case: `find tutorial lab` <br>
Expected: A tutorial or lab type is found on the list. Details of the found tutorials and labs are shown in
the list. <br>
   
4. Test case: `find CS` <br>
Expected: Module(s) with the name or description `CS` is (are) found. Details of the found module(s) are shown in the list.
<br>

5. Test case: `find` <br>
Expected: No modules are found, because the keyword is not specified.
   
6. Test case: `find x`(where x is the keyword that none of the existing modules have) <br>
Expected: No modules are found, because the keyword does not exist in the existing module list.
   
7. Note: Make sure to list the modules again when you want to find another module.
   
### Sorting a module by `timeslot` or `deadline`

1. Test case: `sort timeslot` <br>
Expected: If there are modules with different `timeslot`, the modules will be sorted by earliest first and latest last.
The modules without `timeslot` will be unsorted at the very end of the list.

2. Test case: `sort deadline` <br>
Expected: If there are modules with different `deadline`, the modules will be sorted by earliest first and latest last.
The modules without `deadline` will be unsorted at the very end of the list.

3. Test case: `sort x` (where x is empty, or anything other than `timeslot` or `deadline`) <br>
Expected: The modules will not be sorted.

### Saving data

1. Dealing with missing/corrupted data files

    1. If the data file is _missing_, coNtactUS will create a new data file, with sample data.
    2. Similar outcome for _corrupted_ data files.

------------------------------------------------------------------------------------------------------------------------

## Appendix: Effort

### **Overall Difficulty level: 7 / 10**

### **Overall effort required: 8 / 10**

#### Challenges faced:

1. Refactoring the models of `AB3` to fit the functional requirements of `coNtactUS` (Difficulty: 6 / 10, Effort
required: 9 / 10)
   * Bla bla 

2. Implementation of `add`, `edit`, `delete` command for `Module`. (Difficulty: 6 / 10, Effort required: 9 / 10)
   * Bla bla

3. Enhancements of `find` command. (Difficulty: 7 / 10, Effort required: 7 / 10)
   * Bla bla

4. Enhancement of `timeslot` and `deadline` attributes from `String` to `DateTime` format. (Difficulty: 8 / 10,
Effort required: 8 / 10)
   * Bla bla

5. Addition of `sort` command. (Difficulty: 9 / 10, Effort required: 8 / 10)
   * Bla bla

6. Addition of a Reminder window (Difficulty: 7 / 10, Effort required: 7 / 10)
   * Bla bla

