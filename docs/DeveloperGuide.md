---
layout: page
title: Developer Guide
---
<p align="center">
<img src="images/logo_DG.png" width="300">
</p>

* Table of Contents 
{:toc}
<div style="page-break-after: always;"></div>

## About

*Fish Ahoy!* is a desktop CLI-focused application, designed to help users take better care of their
aquatic pets. It allows fish keepers to:

1. Keep track of their tanks and fishes in a hierarchical view, sorted by tanks.
2. Keep track of their fishes' attributes such as the last time it was fed and how often it needs to be fed
3. Consolidate fish-up-keeping tasks and automatically remind users to feed their fish according to information
given by the user

This developer guide aims to provide instructions and guidelines for developers to understand how to
effectively use and contribute to this project by explaining design considerations for certain key features. Moreover,
new developers can use this guide as an entry point for navigating this extensive code base.

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
* Task implementation was inspired by [SoConnect](https://github.com/AY2223S1-CS2103T-W15-1/tp) and fxml file was reused. However, our implementation is entirely new.
* Help implementation (including the code) was reused with minimal changes from the [CLIMods](https://github.com/AY2223S1-CS2103-F14-1/tp) project. Html files were not reused.

--------------------------------------------------------------------------------------------------------------------

## Getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

-------------------------------------------------------------------------------------------------------------------- 
<div style="page-break-after: always;"></div>

## Design

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.
<div style="page-break-after: always;"></div>

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `fish delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.
<div style="page-break-after: always;"></div>

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.
<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-T17-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `MainContent`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from a generic abstract `UiPart` class (not shown above) which captures the commonalities between classes that represent parts of the visible GUI.

<img src="images/UiMainWindowLabelled.png" width="600" />

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Fish`, `Tank`, and `Task` objects residing in the `Model`.
* does not depend on the `Storage` component, as it does not read or save data to files.

#### Displaying Panels
All information about the fish, tanks, and tasks are displayed in their respective panels. 
These panels have a `ListView` that displays the information in a scrollable list. 
The `ListView` is populated by an `ObservableList` that is stored in the `Model` component. 
The `ListView` is automatically notified when the `ObservableList` is updated, so that the `ListView` can be updated with the new information. 
This is not triggered if any items inside the `ObservableList` are modified, so the `Logic` component calls the `refresh` functions of each pane when a command is executed.

Shown below is the class diagram for all the relevant classes for a `TankListPanel`. The other panels are similar.

![Class diagram for the tank panel](images/UiTankPanelOverview.png)

`TankListPanel` has a `ListView` that displays the `TankCard` objects. Since these are custom objects, they require a custom cell factory to be created. 
`TankListViewCell` is the custom cell factory that creates the `TankCard` objects. 
All the required JavaFX components are described in the corresponding `TankListCard.fxml` file.

#### Displaying Tank Readings
Unlike the rest of the UI, the charts used to display the tank readings are not described in any `.fxml` file. 
Instead, they are created in the `TankCard` class. This was done to allow for greater flexibility in terms of which chart should be displayed for any given tank.
While this version of the app does not have this functionality, future versions of the app could allow the user to choose which chart to display for each tank.
It may also allow the user to add custom tank readings based on the equipment that they have.

To populate the charts, the `TankCard` class uses the `Tank` object stored in the `Model` component passed to it by the `TankListPanel`.
Each of the three types of readings has their own `ObservableList` which is then looped through to create the data points for the chart.

A possible improvement to the way that readings are currently stored is to use a `HashMap` instead of three separate `ObservableList`s.
This could allow the user to supply their own types of readings (which would be the key of the `HashMap`) and the corresponding values.
Combining this with the ability to choose which chart to display for each tank would allow for a lot of flexibility in terms of how the user can view their tank readings.
However, this would requite significant changes to the way readings are stored. It might require a rework of the entire `readings` package to make it more flexible.
<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a fish).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.
<div style="page-break-after: always;"></div>

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("fish delete 1")` API call.

![Interactions Inside the Logic Component for the `fish delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `FishDeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>
<div style="page-break-after: always;"></div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `FishAddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `FishAddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `FishAddCommandParser`, `TaskDeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.
* The 3 main types of `XYZCommandParser` are divided into command parsers for `Fish`, `Tank` and `Task`.
<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/FishTankTaskClassDiagram.png" />


The `Model` component,

* stores the address book data i.e., all `Fish` objects (which are contained in a `UniqueFishList` object).
* stores the currently 'selected' `Fish` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Fish>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)
* the `Task` and `Tank` model is implemented similarly to the `Fish` part of the model.
<div style="page-break-after: always;"></div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Fish` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Fish` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>
<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="500" />

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Feature Implementations

This section describes some noteworthy details on how certain features are implemented.

### Automatic Feeding Reminders
#### Implementation

The entrypoint of this feature is in the `start()` method of MainApp, which is automatically called when the user opens
*Fish Ahoy!*. We then access the `Logic` component to access the `Model` component to find out which `Tank` have unfed
`Fish`. For each tank with unfed `Fish`, we create a `TaskFeedingReminder`. We then return an `ArrayList` of
`TaskFeedingReminders` as `feedingReminders`. In the `Logic` component, we create a `TaskFeedingReminderCommand` for
each `TaskFeedingReminder`, then execute these commands, updating the `Model` component before saving the states
if the various lists.

![FeedingReminderSequenceDiagram](images/FeedingReminderSequenceDiagram.png)

#### Design considerations:
* Alternative 1: Create a command parser and other relevant files to allow the user to execute this command
  * Pros: user can update Reminders with commands like other features
  * Cons: will be redundant most of the times as Fish feeding intervals are not that short. Even if user calls this
command, the reminders likely do not need to be updated.
<div style="page-break-after: always;"></div>

### FishSortCommand feature

#### Implementation
The fish sorting feature leverages `SortedList` functionality of Javafx. By creating custom comparators to compare fish
attributes, we are able to use a `SortedList` to sort its list by the specified order.
Specifically, it currently sorts by the five compulsory fields of a fish:

* Name
* Last Fed Interval
* Species
* Feeding Interval
* Tank

Currently, upon instantiation of `ModelManager`, it creates a `SortedList` from a `AddressBook`.
A `Filteredlist` is created based off the same `SortedList`. Hence, when we perform sorting operations, we are able to
manipulate and display the filtered list. As a result, we are able to sort the fish list and filter at the same time.

Given below is an example usage scenario and how the sort mechanism behaves at each step.

Step 1. The user is currently using the application, and there are three entries currently existing in the
`AddressBook`, `Marlin, Nemo, Dory`, added in that order to a tank, named `Ocean tank`.

Step 2. The user executes `fish sort by/n tk/1`. `FishParser` receives the `sort` keyword and calls `FishSortCommandParser#parse()`,
in which the keyword `n` is used to select a Comparator. In this case, the `NameComparator`, which compares the names between fish,
is passed to `FishSortCommand` and returned.

![FishSortCommandDiagram](images/FishSortDiagram.png)

Step 3. `FishSortCommand#execute()` first calls `Model#sortFilteredFishList()`, which in turn calls `SortedList#setComparator()`.
This call triggers the SortedList to sort the current list using the given comparator. In this case, `Marlin, Nemo, Dory` sorts into `Dory, Marlin, Nemo`.

Step 4. `FishSortCommand#execute()` then retrieves the associated tank if `tk/` is present. In this case, a tank index of `1`
is received. The `Ocean tank` is then retrieved through `Model#getFilteredTankList()` and `List<Tank>#get()`.

Step 5. `FishSortCommand#execute()` then calls `Model#updateFilteredFishList()`, which sets a predicate to only display fish in `Ocean tank`.

Step 6. `FishSortCommand#execute()` then finally returns and triggers a GUI change in `MainWindow` to display the sorted `FilteredFish` of `Dory, Marlin, Nemo`.

#### Design considerations:

**Aspect: Where Sorting takes place :**

* **Alternative 1 (current choice):** Use a SortedList and comparators to sort within the list.
    * Pros: Easy to implement.
    * Cons: Requires a separate list or wrapping.

* **Alternative 2:** Sorts a list externally before replacing the `AddressBook` list.
    * Pros: More customization and control over sorting.
    * Cons: Requires a duplicate list to be made each time.
<div style="page-break-after: always;"></div>

### TaskAddCommand feature
#### Motivation
As a FishTracker application, on top of tracking fishes, we provide a way for fishkeepers to keep track of
their tasks such as changing of tank water or repairing certain tank equipments

#### Implementation
Adding a task can be done via the command "task add".
You are able to specify paramters like:
* Description
* Tank Index (to relate task to a tank)
* Priority Level (Low / Medium / High)
<div style="page-break-after: always;"></div>

#### How `TaskAddCommand` is executed
When the command `task add <description>` is invoked it first goes through the main parser `AddressBookParser`, after
which it is delegated to command-specific parsers, namely TaskCommandParser -> TaskAddCommandParser.

Thus, `LogicManager` will invoke `execute` on `TaskAddCommand` with the following code
`command.execute(model);`, where `model.addTask(taskToAdd)` will be called.

![TaskAddDiagram](images/TaskAddDiagram.png)

#### Design Considerations
* Alternative 1 (current choice): Create a parser for task commands and a taskAdd parser for add-specific task commands
    * Pros: Easy to manage TaskList functionalities.
    * Cons: May have performance issues due to numerous calls down command-specific parsers

* Alternative 2 : Do not abstract task command parsing to a `TaskParser`
    * Pros: Easy to implement
    * Cons: Not well abstracted. Difficult to implement other task commands in the future like marking / deleting of
    tasks. Will also result in an extremely long parser of all types of commands which may be difficult to maintain.
<div style="page-break-after: always;"></div>

### TankFeedCommand feature
#### Motivation
As a FishTracker application, we provide a way for fishkeepers to track the `LastFedDate` of all their fishes,
as having multiple tanks and fishes makes feeding difficult to keep track of without a tracking system.

#### Implementation summary
As such, every `Fish` contains a `LastFedDateTime` object, which contains a date field which records their `lastFedDate`.

When the fishkeeper decides to feed a particular tank by invoking the command `tank feed <index>`,
the program will feed all fishes in that tank, changing `LastFedDate`  of all fishes in that tank.

#### How `TankFeedCommand` is executed
When command `tank feed <index>` is invoked, it first passes through the main parser `AddressBookParser`,
before being delegated to command-specific parsers, namely `TankParser` -> `TankFeedCommandParser`,
which returns a `TankFeedCommand` to `LogicManager`.

With this, `LogicManager` will invoke `execute` on `TankFeedCommand` with the following code `command.execute(model);`,
where `model.setLastFedDateFishes(tankToFeed, formattedDate);` will be called.

The `setLastFedDateTimeFishes(tankToFeed, formattedDate)` function will be called down the chain of classes
[`ModelManager` -> `Tank` -> `AddressBook` -> `UniqueFishList`].

`UniqueFishList#setLastFedDateFishes(String newDate)` will then call
`internalList.stream().forEach(fish -> fish.setLastFedDateTime(newDate));`,
creating a new stream from `internalList` containing references to all Fish objects in `internalList`.

Every `Fish` object will call `fish.setLastFedDateTime(newDate)`, where a new `LastFedDateTime` object with the updated date
will be created and replace the `Fish`'s `lastFedDateTime` field.

![TankFeedDiagram](images/TankFeedDiagram.png)

#### Design Considerations
* Alternative 1 (current choice): Create a setLastFed(tankToFeed, formattedDate) which allows for flexible date
    * Pros: Allows for flexible setting of `LastFedDateTime` for future implementations.
    * Cons: Higher chance of bugs as function allows for variability of date-time.
* Alternative 2: Create a setLastFedToNow(tankToFeed) which hard codes `LastFedDateTime` to current date time
    * Pros: Straightforward behaviour of function, since `TankFeedCommand` sets the `LastFedDateTime` to the current date-time.
    * Cons: Not flexible, may not be useful for future implementations which requires variable settings of date-time.

--------------------------------------------------------------------------------------------------------------------

## Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Appendix: Requirements

### Product scope

**Target user profile**:

> This product is for fish enthusiasts who wants to keep track of their numerous fishes' details, such as their health, tank status, feeding requirements etc.

**Value proposition**:

> This product makes it easier for fish keepers to keep track of numerous tasks to maintain the health of the fish and fish tanks. There are many fine details that go into maintaining an environment for live aquatic animals, and this product will help track them.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                          | So that I can…​                      |
|---------|---------|---------------------------------------|------------------------------------------------|
| `* * *` | user    | be able to create weekly tasks        | have a record of my tasks throughout the week |
| `* * *` | user    | keep track of the individual tanks    | so that I can manage them in the future        |
| `* * *` | user    | access a local fish database          | know more about my fish                        |
| `* * *` | user    | delete my fish from the system easily | get rid of dead fish                           |
| `* * *` | user    | delete a task                         | check off completed tasks                      |
| `* * *` | user    | delete a tank                         | keep my tank status updated if I retire a tank |
| `* * *` | user    | view all my fishes at a glance                          |  keep track of their numbers |
| `* * *` | user    | view my weekly tasks I need to fulfill for each of my fish tanks | not miss any tasks throughout the week |
| `* * *` | user    | transfer my data from one system to another | change my systems |
| `* * *` | fish keeper | delete fish from a tank | move them to different tanks |
| `* * *` | fish keeper | know which food can be fed to each fish | keep them well fed |
| `* * *` | owner of multiple fish tanks | know the number and type of fish in each tank | monitor their condition |
| `* *` | fish keeper | know the number of fish for each species | know if any species requires help |
| `* *` | fish keeper | know the health conditions of my fish | be updated on their conditions |
| `* *` | fish keeper | attach a picture to the fish's entry | identify it and track it's growth |
| `*` | expert user | customize my entries fields | suit the app to my use |
| `*` | inexperienced fish keeper | know what are the required equipment for keeping fish | my fish won't die |
| `*` | inexperienced fish keeper | know what equipment is compatible with my tanks | |
| `*` | fish keeper | know the conditions of my equipment | maintain them |
| `*` | fish keeper | know ammonia levels in fish tanks | adjust the amount at the end of every week |
| `*` | fish keeper | know nitrate levels in fish tanks | adjust the amount at the end of every week |


### Use cases

(For all use cases below, the **System** is `*Fish Ahoy!*` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a fish**

**MSS**

1. User adds a fish
2. Fish is added to *Fish Ahoy!*

    Use case ends.

**Extensions**

* 1a. User specifies a tank.

    * 1a1. Fish is added to the tank.

  Use case ends.

**Use case: Add a tank**

**MSS**

1. User adds a tank
2. Tank is added to *Fish Ahoy!*

  Use case ends.


**Use case: Add a task**

**MSS**

1. User adds a task
2. Task is added to *Fish Ahoy!*

   Use case ends.

**Extensions**

* 1a. User specifies if the task is a `todo`.

    * 1a1. `todo` task is created.

    Use case ends.

* 1b. User specifies if the task is a `deadline`.

  * 1b1. `deadline` task is created.

    Use case ends.

* 1c. User specifies if the task includes a `fish`.

    * 1b1. task is created and `fish` is tagged to it.

      Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 fish, tanks and tasks without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  A user with average experience of CLI should be able to use the system.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Fish**: A fish owned by the user to be added to *Fish Ahoy!*
* **Tank**: A fish tank owned by the user to house fish to be added to *Fish Ahoy!*
* **Task**: A weekly task of the user regarding fish-keeping to be added to *Fish Ahoy!*

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>


## Appendix: Instructions for manual testing

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.
</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample data. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Deleting a fish

1. Deleting a fish while all fish are being shown

   1. Prerequisites: List all fish using the `list fishes` command. Multiple fish in the list.

   1. Test case: `fish delete 1`<br>
      Expected: First fish deleted from the list. Details of deleted fish shown in status message.

   1. Test case: `fish delete 0`<br>
      Expected: No fish deleted. Error details shown in status message. Fish panels remains the same.

   1. Other incorrect delete commands to try: `fish delete`, `fish delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Readings graph feature

1. Checking that graphs of each tank can display readings correctly
   1. Prerequisites: The tanks are the default sample tanks, "freshwater tank" and "saltwater tank"
   2. Test case: paste the following test values into `readings.json`: <br>
 
  
```json
{
  "readingLevels" : [ {
  "tankName" : "freshwater tank",
  "commaSeperatedValuesAmmonia" : "0.2,0.3,0.0",
  "commaSeperatedDatesAmmonia":"03/04/2023 14:00,04/04/2023 20:01,05/04/2023 14:02",
  "commaSeperatedValuesPH": "7.1,6.8,7.0",
  "commaSeperatedDatesPH" : "03/04/2023 14:00,04/04/2023 20:01,05/04/2023 14:02",
  "commaSeperatedValuesTemp" : "26.0,25.0,26.2",
  "commaSeperatedDatesTemp":"03/04/2023 14:00,04/04/2023 20:01,05/04/2023 14:02"
  }, {
    "tankName" : "saltwater tank",
    "commaSeperatedValuesAmmonia" : "",
    "commaSeperatedDatesAmmonia" : "",
    "commaSeperatedValuesPH" : "",
    "commaSeperatedDatesPH" : "",
    "commaSeperatedValuesTemp" : "",
    "commaSeperatedDatesTemp" : ""
  } ]
}
```
Expected: <br>
![expectedReadingFromSample](images/expectedReadingFromSample.png)


:exclamation: Warning: If you want to test with more of your own values, you should ensure all 3 types of
readings come as a set e.g. if any readings were to be made, it would be all 3 and they are made at the same time
as the app ensures this

### Saving data

1. Dealing with missing/corrupted data files

   1. Missing data files are automatically created. If data files are corrupted, delete all data files
   so new ones will be created, containing our sample data.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Appendix: Planned Enhancements

1. The current error message for an invalid index is too general. We plan to make the error message also more accurate
   by filtering by why the index is invalid.
    1. \[-MAX_INT....0]: Index must be a positive non-zero integer
    2. \[4..MAX_INT]: Index is out-of-bounds. Index must correspond to a valid tank.

2. The current parameter parsing does not check for nonsensical or invalid values. We plan do execute sanity checks to 
    protect the user from receiving unintentional results for the following parameters:
   1. Last fed dates: Check for invalid dates like 30th February and reject them
   2. Feeding interval: While it makes sense that a feeding interval can be 0 days and 25 hours, it 
   makes our `fish sort by/fi` less intuitive as ours sorts by days then hours. `0d25h` will appear before `1d0h`
   . We can automatically convert feeding intervals such that hours is less than 24 in future iterations


3. Alphanumeric fields may be truncated if they are too long as we do not limit their length. They 
   may also become truncated if the user's app window size is too small. A side effect of this can be for example, 
   fish with long names, which are supposed to be distinct, will appear as the same after truncation. 
   We will limit the length of such parameters in future enhancements to avoid this. 
    

4. Logging messages on the terminal are not optimized for Fish Ahoy! We plan to improve this by simple changes from 
   "Addressbook" to "Fish Ahoy", as well as fixing the fxml versions in fxml files. Furthermore, we can add more useful 
   logging messages related to Fish Ahoy commands such as information on newly added commands, so the user may gain more 
   information. For instance, Tank readings could display the current list of values, so the user can understand the graph.
   

5. Make the offline version of the User Guide more user-friendly by adding a css file for style. 
   Also fix links so that they work for navigation.

6. Current command semantic is not flexible. For example, when adding a fish, 5 values have to be filled by the user. We 
   can improve this by making certain fields optional, such as Last Fed Date (assumes last fed date is the date time of 
   addition). 
