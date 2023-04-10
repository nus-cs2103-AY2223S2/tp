---
layout: page
title: Developer Guide
---

## <a id="table"></a>**Table of Contents**
* Table of Contents
{:toc}



<div style="page-break-after: always;"></div>



# **About coNtactUS**

## 1. Introduction

### 1.1 About coNtactUS

Welcome!
coNtactUS is a module tracker made by NUS computing students, for NUS computing students. It provides a centralised
platform for students to conveniently store and access information about their modules. coNtactUS is optimised for use 
via typing instead of clicking, allowing NUS computing students to store and retrieve information faster than ever before.
Overall, coNtactUS helps students to be the most productive and organised versions of themselves. 

coNtactUS provides support for NUS computing students to keep track of a variety of information related to their modules,
including but not limited to the following... 

* Lecture and tutorial timeslots
* Lecture and tutorial venues
* Deadlines
* Module official resources
* Module notes
* Contact details of Professors and Teaching assistants and more...

### 1.2 About the developer guide

This developer guide describes the high-level software architecture and how user inputs are parsed into corresponding
commands in coNtactUS.


If you simply want to start using coNtactUS in your daily university life, look no further and jump
straight into our user-friendly [user guide](https://ay2223s2-cs2103t-w10-1.github.io/tp/UserGuide.html). 


If who want to understand how the software works, or you want to further
develop coNtactUS for yourself, you may feel free to proceed with this guide.


If you have any difficulties understanding what some terminologies mean at any point, you may refer 
to the [glossary](#10-glossary) for that.

To help you better navigate through our developer guide, the [Return to ToC](#a-idtable-a-table-of-contents) at the end
of each section allows you return to the Table of Contents. From there, you can quickly access another section by clicking the
respective header.

------------------------------------------------------------------------------------------------------------------------

## **2. Acknowledgements**

* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson),
  [JUnit5](https://github.com/junit-team/junit5)

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

------------------------------------------------------------------------------------------------------------------------

## **3. Setting up, getting started**

If you want to get started with developing your own coNtactUS, you may refer to the guide 
[_Setting up and getting started_](SettingUp.md).

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

------------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **4. Overview of the Design**

### 4.1 The components in the architecture

This section gives you a quick overview of the architecture components of coNtactUS. The diagram shown below gives you
a high-level description of the overall architecture.


<p align="center">
    <img height = "440" src="images/ArchitectureDiagram.png" width="280" /> </p>
<div align="center"> Architecture of the module tracker. </div>

The ***Architecture Diagram*** given above explains the high-level design of the module tracker.

Architecture of the module tracker.


### `Main`
[`Main`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/Main.java)
works with  [`MainApp`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/MainApp.java)
and is responsible for: 
* Initializing the other components in the correct sequence at software launch, and connecting them up with each other.
* Shutting down the components when the program is closed and invoking cleanup methods where necessary.

### `Commons`
[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

### `UI`
[**`UI`**](#ui-component) is responsible for the user interface of the module tracker.

### `Logic`
[**`Logic`**](#logic-component) is responsible for executing commands.

### `Model`
[**`Model`**](#model-component) holds the data of the module tracker in memory.

### `Storage`
[**`Storage`**](#storage-component) reads data from, and writes data to, the hard disk.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 4.2 How the architecture components interact with each other

To illustrate how the components interact with each other, let us take a look at the scenario where 
the user issues the command `delete 1` to delete the first module from the list.

The *Sequence Diagram* below illustrates this in detail: 

<p align="center">
    <img src="https://ay2223s2-cs2103t-w10-1.github.io/tp/images/ArchitectureSequenceDiagram.png" width="580" />
</p>


<div align="center">Figure 1: How the different components interact with each other upon the command `delete 1` </div>

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 4.3 Interfaces that are implemented by the architecture components

Each of the four components `UI`, `Logic`, `Model` and `Storage` (also shown in Figure 2 above),

* defines its *API* in an `interface` with the same name as the component.
* implements its functionality using a concrete `{Component Name}Manager` class
which follows the corresponding API `interface` mentioned in the previous point.


For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality
using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given
component through its interface rather than the concrete class (reason: to prevent outside component's being coupled
to the implementation of a component), as illustrated in the (partial) class diagram below.


<br>

<p align="center">
    <img src="images/ComponentManagers.png" width="300" />
</p>
<div align="center"> Relationship between the Interfaces and their concrete classes </div>

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

------------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **5. An in-depth look at each architecture component**

### 5.1 The UI component

The **API** of this component is specified in
[`Ui.java`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The diagram below provides a detailed description of the `UI` component.

<p align="center">
    <img src = "images/UiClassDiagram.png">
</p>

<div align="center"> The structure of the UI Component. </div>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`,
`ModuleListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart`
class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files
that are in the `src/main/resources/view` folder. For example, the layout of the
[`MainWindow`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in
[`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Module` object residing in the `Model`.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 5.2 The Logic component

The **API** of this component is specified in
[`Logic.java`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

The diagram below provides a detailed description of the `Logic` component.

<p align="center">
    <img src="images/LogicClassDiagram.png" width="650"/> </p>
<div align="center"> The partial structure of the Logic component. </div>

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


<p align="center">
    <img src="images/ParserClasses.png" width="600"/> </p>
<div align="center"> Classes in the Logic component used for parsing a user command </div>

How the parsing works:
* When called upon to parse a user command, the `ModuleTrackerParser` class creates an `XYZCommandParser` (`XYZ` is a
placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `ModuleTrackerParser` returns back
as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
interface so that they can be treated similarly where possible e.g, during testing.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 5.3 The Model component
The **API** of this component is specified in
[`Model.java`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<p align="center">
    <img src="images/ModelClassDiagram.png" width="800" /> </p>

<div align="center"> The structure of the Model component. </div>

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


<img src="images/BetterModelClassDiagram.png" width="690">

A more OOP model of the Model component.

</div>

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 5.4 The Storage component

The **API** of this component is specified in
[`Storage.java`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<p align="center">
    <img src="images/StorageClassDiagram.png" width="550" /></p>

<div align="center"> The structure of the Storage component.</div>

The `Storage` component,
* can save both module tracker data and user preference data in json format, and read them back into corresponding
objects.
* inherits from both `ModuleTrackerStorage` and `UserPrefStorage`, which means it can be treated as either one (if only
the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects
that belong to the `Model`)

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 5.5 Common classes

Classes used by multiple components are in the 
[`seedu.moduletracker.commons`](https://github.com/AY2223S2-CS2103T-W10-1/tp/tree/master/src/main/java/seedu/address/commons) 
package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## **6. Implementation**

This section describes some noteworthy details on how certain features of coNtactUS are implemented.

### 6.1 Sort Feature

#### 6.1.1 Sort Command Implementation
This section will explain the implementation of the `SortCommand`. The `SortCommand` allows users to **sort the modules
in the displayed list by either the module's `timeslot`, or the module's `deadline`**. The `SortCommand` will
automatically take into consideration the **current time** of your computer. 

For example, if the current day on your computer is Monday, a module with timeslot Tuesday 05:00 - 07:00 will be shown 
before a module with `timeslot` `Wednesday 06:00 - 08:00`. Similarly, if the current day on your computer is Tuesday, 
then a module with `timeslot` `Wednesday 05:00 - 07:00` will be shown before a module with `timeslot` 
`Thursday 06:00 - 08:00`. For sorting by `deadline`, a module with `deadline` `250623` will be shown before a module
with `deadline` `260623`.

The `SortCommand` relies on the fact that the `ObservableList<e>` in 
[`ModelManager.java`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/model/ModelManager.java) which is later on displayed to the 
user interface, is actually a `FilteredList<e>`. In order to accomplish the sorting feature, we wrapped a `SortedList<e>` 
inside the `FilteredList<e>`. Using the `setComparator()` method for a `SortedList<e>`, we are able to dictate the order 
in which the modules are sorted. When the program is launched, the 
comparator that is used does not impose any ordering on the modules. If the user executes the command `sort timeslot`, 
a comparator that imposes a timeslot ordering is passed to the `SortedList<e>`. `sort deadline` works in the same way. 

Both `FilteredList<e>` and `SortedList<e>` extends `ObservableList<e>`, which is the list that is eventually passed to
the user interface in [`MainWindow.java`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java). 
If you are interested to create a particular ordering of the modules, you may go
to [`ComparatorUtil.java`](https://github.com/AY2223S2-CS2103T-W10-1/tp/blob/master/src/main/java/seedu/address/commons/util/ComparatorUtil.java) 
to implement a `Comparator<Module>` of your choice. 

Below is an example usage scenario and how the sort command behaves at each step:

**Step 1:** The user launches the application for the first time. The **ModuleTracker** will be initialized with the
initial module tracker state.

**Step 2:** The user writes `sort timeslot` in the terminal. This command will be handled by the `LogicManager` and
`ModuleTrackerParser`, which will extract the needed argument.

**Step 3:** The command is executed through the execute method, which updates the list through
`Model#updateSortedModuleList`.

**Step 4:** The method passes the Comparator to the `SortedList`, updating the displayed list. 

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 6.2 Find feature

#### 6.2.1 Find Command Implementation
This section will explain the implementation of the `FindCommand` and the `FindCommandParser`. The `FindCommand` allows
users to **search for modules whose `name` or `description` contain any of the specified keywords** (case-insensitive).

##### FindCommand Class
The `FindCommand` class is responsible for finding and listing all modules in the module tracker whose name contains any
of the argument keywords. Keyword matching is case-insensitive. The `FindCommandParser` is responsible for parsing the
user input.

The `FindCommand` utilizes the `FilteredList` from **JavaFX** and uses a predicate to initialize a `FindCommand` object.
This predicate is programmed to match both the `name` and the `description` of the module.

The predicate passed to the `FindCommand` constructor is from the `NameContainsKeywordsPredicate` class. This class has
a test method, which is used by the `FilteredList`.

Below is an example usage scenario and how the find command behaves at each step:

**Step 1:** The user launches the software for the first time. The `ModuleTracker` will be initialized with the initial
module tracker state.

**Step 2:** The user writes `find CS3263` in the terminal. This command will be handled by the `LogicManager` and
`ModuleTrackerParser`, which will extract the needed argument, most importantly the predicate.

**Step 3:** The command is executed through the execute method, which updates the list through
`Model#updateFilteredModuleList`.

**Step 4:** The method sets the predicate to the filtered list, which runs the `NameContainsKeywordsPredicate#test()`
method to find the items based on `name` or `description`.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 6.3 Reminder feature

#### Reminder Implementation
This section explains the implementation of the reminder feature. The reminder allows users to view all the timeslots
and deadlines on the current day (based on the system's time).

The `LogicManager` class is responsible for finding all the modules in the module tracker whose date (`timeslot` or
`deadline`) matches the current date. The `UiManager` class is responsible for alerting the user of the deadlines and
timeslots for today.

There are 3 methods in the `LogicManager` class:
1. A method that deals with finding the timeslots for the day.
2. A method that deals with finding the deadlines for the day.
3. A method that combines and parses everything into a string.

The string is then passed to a method in the `UiManager` that handles showing the alert to the user upon launching the
software. Exceptions and null values are handled to avoid processing null dates, as they are optional fields.

Exceptions and null values are handled to avoid looking at null dates as they are optional.

Below is an example usage scenario of the reminder feature:

**Step 1:** The user launches the software for the first time. The **`ModuleTracker`** will be initialized with the
initial module tracker state.

**Step 2:** An alert pops up, showing the user the timeslots and deadlines for the day, along with the module name and
type. The title of the alert is "Work Today."

**Step 3:** The user can now close the alert and continue using the software.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

------------------------------------------------------------------------------------------------------------------------

## **7. \[Proposed\] Planned Enhancements**

### 7.1 Reminder command.
We propose extending the reminder feature to allow users to call it at any time, instead of only at the software's
launch. Additionally, we plan to enable users to view reminders for a specific day, week, or month.

To implement this, we plan to add a new command, similar to the existing commands, by creating a `ReminderCommand` class
and a `ReminderCommandParser` class, analogous to the `FindCommand` and `FindCommandParser`.

These enhancements should provide users with more flexibility in accessing and managing their reminders, leading to an
improved user experience.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 7.2 Introduce a horizontal scroll bar for long attributes

We propose to include a horizontal scroll bar so that users can see their entire module details without being forced
to resize the software window (e.g: Extremely long website names)

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## **8. Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

--------------------------------------------------------------------------------------------------------------------

## **9. Appendix: Requirements**

### 9.1 Product scope

**Target user profile**

* ***NUS Computing students*** who are more tech-savvy than the general population and are also fast typists
* ***NUS Computing students*** would have to refer to these details regularly throughout the course of the semester as
they may not be able to remember them
* ***NUS Computing students*** that have a need to manage a significant number of lecture
* ***NUS Computing students*** who prefer desktop apps over other types
* ***NUS Computing students*** who can type fast
* ***NUS Computing students*** who prefers typing to mouse interactions
* ***NUS Computing students*** who is reasonably comfortable using CLI apps

**Value proposition**
* Text-based university module tracker with the ability to:
  * Add modules to be tracked
  * List out all tracked modules
  * Edit attributes on existing modules
  * Delete modules
  * Find a specific module by name or type
  * Sort existing modules based on their deadline or class time slots

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 9.2 User stories

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


<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 9.3 Use cases

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

**Use case 7: Opening `help`**

**MSS**

1.  User requests to open the help menu by using the command.
2. coNtactUS opens up the help menu. <br>

Use case ends.

**Extensions**

* 1a. coNtactUS detects missing information in the command.
  * 1a1. coNtactUS shows an error message.
  * 1a2. User enters the information again.
  * 1a3. Repeat steps 1a1 and 1a2 until there are no more missing information.

    Use case resumes at step 2.

**Use case 8: Exiting the software `exit`**

**MSS**

1.  User requests to exit the software by using the command.
2. coNtactUS exits from the user's computer. <br>

Use case ends.

**Extensions**

* 1a. coNtactUS detects missing information in the command.
    * 1a1. coNtactUS shows an error message.
    * 1a2. User enters the information again.
    * 1a3. Repeat steps 1a1 and 1a2 until there are no more missing information.

      Use case resumes at step 2.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 9.4 Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 modules without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should
be able to accomplish most of the tasks faster using commands than using the mouse.
4. Only language supported is English
5. The software size (including the data file with up to 1000 modules) should not exit 100MB.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

------------------------------------------------------------------------------------------------------------------------

## **10. Glossary**

### 10.1 coNtactUS related terminology

* **Module Tracker**: The coNtactUS application
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

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 10.2 Miscellaneous

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **CLI**: Command-Line Interface
* **GUI**: Graphics-based User Interface
* **Software**: The coNtactUS application
* **XYZCommand**: Represents all commands. E.g: `AddCommand`, `DeleteCommand`
* **API**: Application Programming Interface
* **UML**: Unified Modelling Language
* **Parser**: A class that translates user inputs to machine language to run the corresponding commands.
* **JAR**: Java ARchive

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

--------------------------------------------------------------------------------------------------------------------

## **11. Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting
point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### 11.1 Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   2. Double-click the jar file Expected: Shows the GUI with a set of sample modules. The window size may not be
   optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 11.2 Deleting a module

1. Test case: `delete 1`<br>
      Expected: First module is deleted from the list. Details of the deleted module shown in the status message.
Timestamp in the status bar is updated.

2. Test case: `delete 0`<br>
      Expected: No module is deleted. Error details shown in the status message. Status bar remains the same.

3. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 11.3 Adding a module
 1. Test case: `add n/CS2103T t/Tutorial a/COM1`<br>
     Expected: A new module is added to the list with name being `CS2103T` and description being `Tutorial`. Details of
     the added module is shown in the status message. Empty optional fields are left as "None.".

 2. Test case: `add n/CS2101 t/Lecture a/COM3 e/Tuesday 10:00 12:00 s/Mr Ng`<br>
     Expected:  A new module is added to the list with name being `CS2101`, description being `Lecture`, venue being
     `COM3`, TimeSlot being `Tuesday 10:00 12:00`, and teacher being `Mr Ng`. Details of the added module is shown
     in the status message.

 3. Test case: `add n/CS1101S`<br>
    Expected: No module is added as description, which is a compulsory field, is missing. Error details shown in the
    status message.

 4. Other incorrect add commands to try: `add`, `add x`, `...` (where x is the attribute you want to add, without
     using the prefixes) <br>
     Expected: Similar to previous.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 11.4 Editing a module
  1. Test case: `edit 1 n/CS1101S` <br>
   Expected: The name of the first module in the list is edited, and is now `CS1101S`. Details of the updated module
   is shown in the status message.

2. Test case: `edit 2 e/Saturday 15:00 16:00` <br>
   Expected: The time slot of the first module in the list is edited, and is now `Saturday 15:00 16:00`. Details of the
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

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 11.5 Finding a module/description

1. Test case: `find CS2103T` <br>
   Expected: Module(s) with the name `CS2103T` is (are) found. Details of the found module(s) are shown in the list.
   <br>

2. Test case: `find tutorial`
Expected: Module(s) with the description `tutorial` Details of the found tutorial(s) are shown in the list. <br>

3. Test case: `find tutorial lab` <br>
Expected: A tutorial or lab description is found on the list. Details of the found tutorials and labs are shown in
the list. <br>

4. Test case: `find CS` <br>
Expected: Module(s) with the name or description `CS` is (are) found. Details of the found module(s) are shown in the
list.
<br>

5. Test case: `find` <br>
Expected: No modules are found, because the keyword is not specified.

6. Test case: `find x`(where x is the keyword that none of the existing modules have) <br>
Expected: No modules are found, because the keyword does not exist in the existing module list.

7. Note: Make sure to list the modules again when you want to find another module.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 11.6 Sorting a module by `timeslot` or `deadline`

1. Test case: `sort timeslot` <br>
Expected: If there are modules with different `timeslot`, the modules will be sorted by earliest first and latest last.
The modules without `timeslot` will be unsorted at the very end of the list.

2. Test case: `sort deadline` <br>
Expected: If there are modules with different `deadline`, the modules will be sorted by earliest first and latest last.
The modules without `deadline` will be unsorted at the very end of the list.

3. Test case: `sort x` (where x is empty, or anything other than `timeslot` or `deadline`) <br>
Expected: The modules will not be sorted.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

### 11.7 Saving data

1. Dealing with missing/corrupted data files

    1. If the data file is _missing_, coNtactUS will create a new data file, with sample data.
    2. Similar outcome for _corrupted_ data files.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>

------------------------------------------------------------------------------------------------------------------------

## 12. Appendix: Effort

**Overall Difficulty level: 7 / 10**

**Overall effort required: 8 / 10**

In general, our team members have contributed equal effort to turn `coNtactUS` into a functional, intuitive software.
We strived to meet weekly project deadlines, making sure that all the important features of our module tracker
were completed and implemented perfectly by each specified milestones.

### 12.1 Challenges faced:

1. Refactoring the models of `AB3` to fit the functional requirements of `coNtactUS` (Difficulty: 6 / 10, Effort
required: 9 / 10)
   * Function names such as `AddressBook`, `addressbook` and `ab` have to be refactored to fit `coNtactUS`.
   * It is not difficult, but highly tedious to refactor, as there were different forms of `addressbook`, hence it is
   not possible to refactor everything in one go.

2. Implementation of `add`, `edit`, `delete` command for `Module`. (Difficulty: 6 / 10, Effort required: 9 / 10)
   * More attributes were added to fit `coNtactUS`, such as `Timeslot`, `Deadline` and `Teacher`.
   * The difficulty is moderate, since we do not have to code out the said commands ourselves from scratch, as they
   are already implemented in `AddressBook3`.
   * However, it is tedious to change the attributes of `AddressBook3` that do not fit `coNtactUS`, as well as adding
   new attributes to the commands.

3. Enhancements of `find` command. (Difficulty: 6 / 10, Effort required: 6 / 10)
   * While transitioning the application from `AddressBook3` to a module tracker, it was essential to modify the `find` command to align with the new theme. The process involved the following steps:
        * Understanding `JavaFX`
        * Exploring `FilteredLists`
        * Implementing the Custom Predicate Test
    * Although implementing the updated `find` command was not overly difficult, the process was somewhat time-consuming due to the need to learn and understand new aspects of the program.

4. Enhancement of `timeslot` and `deadline` attributes from `String` to `DateTime` format. (Difficulty: 8 / 10,
Effort required: 8 / 10)
   * When refactoring from `AddressBook3`'s attributes, `timeslot` and `deadline` were parsed as `String`. However, to
   allow users to `sort` these attributes based on whichever modules come first (and thus improve their time-keeping
   ability in NUS), we further developed them to take in `DateTime` as user inputs.
   * It is not highly arduous and tough, but we decided to use different `DateTime` formats for both `timeslot` and
   `deadline` (more specifically, `Day HH:mm /*START_TIME*/ HH:mm /**END_TIME*/` for `timeslot`, `ddMMyy HH:mm` for
   `deadline`) because it does not really make sense to store date for weekly-recurring `timeslot`.

5. Addition of `sort` command. (Difficulty: 9 / 10, Effort required: 8 / 10)
   * This was a challenging task as it required an in-depth understanding of how the user interface displayed the
   modules. After studying the code, it was observed that the user interface was actually actively "listening" for
   changes to the list of modules in the background, through the use of an ObservableList<e> to make sure that it
   always displayed the desired list. In order to get the user interface to display a sorted version of the list of
   modules when requested by the user, one had to understand the mechanism by which one could change the list of modules
   and ensure that the user interface was always aware of the change.

6. Addition of a `Reminder` window (Difficulty: 7 / 10, Effort required: 7 / 10)
   * Similar to the `find` command. Much of the time is spent on understanding new concepts and libraries. The `reminder` window goes through all the tasks and extract the `DateTime` and compares it to the current system time. Exceptions are checked and then the string is parsed for output. 
   * Although implementing the reminder window was not overly difficult, the process took some time due to the need to learn and understand new aspects of the program.

<p align="right"><a style="text-align:right" href="#table">Return to ToC</a></p>
