---
layout: page
title: Developer Guide

---

## Table of Contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Acknowledgements

* Libraries used: [JavaFX](https://openjfx.io/),
  [Jackson](https://github.com/FasterXML/jackson),
  [JUnit5](https://github.com/junit-team/junit5)
* With inspiration from: [fzf](https://github.com/junegunn/fzf) (for fuzzy
  finding), [Vim](https://www.vim.org/) (for hotkeys)
* With GUI testing structure based on: [Address book-level4](https://github.com/se-edu/addressbook-level4)
* Originally forked from: [AddressBook
  3](https://github.com/nus-cs2103-AY2223S2/tp)

## Using this Guide

This guide is written for anyone who wishes to hack on the Mycelium codebase.
It assumes basic knowledge of Java as well as the ability to read UML diagrams.

### Icons and conventions

The following typographical conventions are used in this guide.

* (KEYCAP) - Indicates a literal set of keys, e.g. (CTRL+F) refers to the
  combination of the 'Control' and 'F' keys.

Throughout this guide, you might encounter certain boxes which like the ones
below. Here is what each of them means.

<div markdown="span" class="alert alert-success">
:bulb: This box signifies a tip or suggestion.
</div>

<div markdown="span" class="alert alert-info">
:information_source: This box signifies a general note.
</div>

<div markdown="span" class="alert alert-danger">
:warning: This box indicates a warning or caution.
</div>

### Content overview

This guide has been divided into five main parts.

* [Design](#design) discusses the high-level architecture of Mycelium
* [User Interface](#user-interface) discusses GUI concerns
* [Command Handling](#command-handling) discusses the execution of commands
* [Storage](#storage) briefly explains Mycelium's approach to persisting data
* [Keyboard Interaction](#keyboard-interaction) discusses hotkeys and fuzzy search

We strive to write each section in a relatively self-contained manner, but some
cross-referencing might be necessary.

### Setting up locally

This guide assumes that you have already set up Mycelium on your computer. For
information on how to do this, see the [setting up guide](/tp/SettingUp.html).

--------------------------------------------------------------------------------------------------------------------

## Design

### Architecture

![Architecture Class Diagram](images/archi/ArchitectureClassDiagram.png "Architecture Class Diagram")

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**


**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/MainApp.java).
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

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.)

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

![Components Class Diagram](images/archi/ComponentsClassDiagram.png)

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/logic/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddProjectCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a project).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("dp -pn Mycelium")` API call.

![Interactions Inside the Logic Component for the `dp -pn Mycelium` Command](images/logic/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteProjectCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/logic/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddProjectCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddProjectCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddProjectCommandParser`, `DeleteProjectCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

***Classes:*** [`Client.java`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/client/Client.java), [`Project.java`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/project/Project.java)

![ClientAndProjectClassDiagram](images/model/ModelClassDiagram.png)

The `Model` is the central component of the Mycelium's data. It contains the
`Client`s and `Project`s which are used to store the data of each entity. Note
the attributes of the `Client` and `Project` classes are never allowed to be
`null`. The optional attributes are correspondingly wrapped in an `Optional`.

Operations related to `Client`s and `Project`s are separately defined in the
`ClientModel` and `ProjectModel` interfaces, to improve separation of concerns.
The `Model` interface then extends these two interfaces.

Moreover, each client or project is also stored in a generic `UniqueList` which
ensures that the list does not contain duplicates. The `UniqueList`s are then
stored in `AddressBook`, which contains the overarching methods for handling
each type of list.

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/storage/Storage.java)

![Structure of the Storage Component](images/storage/StorageClassDiagram.png)

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

<div markdown="span" class="alert alert-danger">
:warning: If the JSON file storing the data becomes unreadable or invalid due to corruption or tampering,
Mycelium will ignore the data file and start an empty application with no projects and clients. Commands
performed that will modify the data file will overwrite the corrupted file. This would lead to a
complete and unrecoverable loss of data prior to the corruption.
</div>

### Common classes

Classes used by multiple components are in the `mycelium.mycelium.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div markdown="span" class="alert alert-info">
:information_source: The following three sections discuss implementation
details. We have divided them into three overarching themes: [User
Interface](#user-interface), [Command Handling](#command-handling), and
[Keyboard Interactions](#keyboard-interactions).
</div>

## User Interface

### Statistics Dashboard

Statistics Dashboard displays statistics regarding Projects. There are three
main statistics: projects that are due soon, projects that are overdue
and a pie chart showing progress overview. The goal of this feature is to provide users
with useful information related to productivity to make adjustments accordingly.

For projects that are due soon, all projects that have deadlines within the current week
and next week will be displayed (Week starts from Sunday). For example, if the current
date is 11/04/2023, all projects with deadlines from 09/04/2023 to 22/04/2023 will be
shown on the **Due soon project list**.

For overdue list, all overdue projects will be displayed to make sure that users can
keep track of all the deadlines they have missed. Any deadlines before the current date will be
counted as overdue. For example, if the current date is 11/04/2023, projects with deadlines on 11/04/2023
will not be shown in **Overdue project list**, but projects with deadlines on 10/04/2023 will be shown in the **Overdue
project list**.

Both **Due and Overdue project lists** utilise the `java.time` package to perform various operations on dates.

For Progress Overview Pie chart, there will be at most three segments. The three segments
will correspond to three project statuses, which are `not_started`, `done` and `in_progress`.
The size of each segment is proportional to the number of projects with its corresponding segment
label.
<div markdown="span" class="alert alert-danger">
:warning: The color of each segment is not related to the status.
</div>


#### Updating the UI

For **Due soon project lists**, **Overdue project lists** and **Pie chart**, the `FilteredList` retrieved by
using `Logic#getFilteredProjectList` cannot be used because it will affect the UI. Besides,
all the statistics need filtering out, making it not possible to use `FilteredList#setPredicate`.
Thus, for the dashboard to update accordingly as changes are made to project list, a `ListChangeListener`
will be attached to the original list of projects.

Whenever there is a change in the project list (e.g.
a new project is added), all the statistics will be updated as well. These changes could be due to
an increase in the number of projects (i.e. a new project is created), or a modification to an existing project.
Moreover, when there are no projects available matching the requirements of Due soon and Overdue project lists,
there will be messages displayed under the tab heading to indicate this.

![NoOverdueProjectsMessage](images/NoOverdueProjectsMessage.png)

The diagram below shows us that the `MainWindow` is responsible for instantiating the `StatisticsBox`.
The `fillInnerParts()` method is part of the UI's initialization routine. The `ObservableList<Project>#addListener()`
will be called to listen for changes in project list. During the initialization of the `StatisticsBox` instance, data
for the **Due soon**, **Overdue** project list and **Pie chart** is also loaded.

![StatisticsBoxActivityDiagram](images/statisticsbox/StatisticsBoxActivityDiagram.png)


### Tabs panel
In Mycelium, there are four main tabs: **Projects**, **Clients**, **Due soon** and **Overdue**.
The **Projects** tab will be responsible for displaying all projects created while the **Clients** tab
will display all clients created. Each of these two tabs is an `EntityTab` object and contains an `EntityList` of
`Project`s for the **Projects** tab and `Client`s for the **Clients** tab. Both will be in the same
panel `EntityPanel` on the left side of the application view.

The **Due soon** tab will be responsible for displaying all projects that are due soon while the **Overdue** tab
will display all projects that are overdue as mentioned in the [Statistics Dashboard](#statistics-dashboard) section.
Each of these two tabs is a `StatisticsTab` object and contains a `EntityList` of `Project`. Both of these two
tabs will be in the same `StatisticsPanel` on the right side of the application view.

![Tabs](images/Tabs.png)

For demonstration purposes, the following is the Activity Diagram when a `EntityPanel` is initialized:

![EntityTabPanelActivityDiagram](images/TabActivityDiagram.png)


For more information about interacting with the tabs using hotkeys, please refer to the
[Hotkeys with UiEvents](#hotkeys-with-uievents) section.


## Keyboard Interaction

### Hotkeys with UiEvents

UiEvents is an abstraction of keyboard events that can trigger changes in
user interface or perform some action. These keyboard events are handled by
UiEvent handlers that are bundled together within the `UiEventManager` class.

<div markdown="span" class="alert alert-info">
:information_source: We will occasionally refer to each of these event handlers as a `Key`
</div>

The following is the class diagram of the
`UiEventManager` class.

![UiEventManager class diagram](images/uievent/UiEventManager.png)

There are currently 10 registered event handlers, namely:
* `HelpKey` Help (F1)
* `QuitKey` Quit (CTRL+Q)
* `StartOfLineKey` Start of Line (CTRL+W)
* `EndOfLineKey` End of Line (CTRL+E)
* `ClearKey` Clear line (CTRL+D)
* `SwitchPanelKey` Switch Panels (CTRL+S)
* `SwitchTabKey` Switch Tabs (CTRL+L)
* `NextItemKey` Select next (CTRL+J)
* `PrevItemKey` Select previous (CTRL+K)
* `FindKey` Search (CTRL+F)

Each of these event handler perform an action associated with a keyboard event.
The action performed by each event handler can be found and modified in their respectively
named files found in the `java/mycelium/mycelium/logic/uievent` folder.

To find out more about the supported keyboard events and its associated actions
in Mycelium, please refer to the [User Guide](UserGuide.md#hotkeys).

#### UiEvent Handling
The `UiEventManager` is responsible for calling the appropriate event handler to handle
the incoming event. When a keyboard input is registered, the `UiEventManager#catchAndExecute(KeyEvent)`
method will be called with the incoming event as the argument.

![EventHandling sequence diagram](images/uievent/EventHandling.png)

The above seqeuence diagram shows what happens when a keyboard event is
registered. To keep the diagram simple, we only explicitly show two
out of the 10 registered event handlers as the other event handlers
work in a similar manner.
The key combination that triggered the event will be checked against
each of the registered event handlers to decide which event handler to invoke.

![GenericKey sequence diagram](images/uievent/GenericKey/GenericKey.png)

The above sequence diagram shows what happens once there is a match with a
generic event handler. An instance of the respective `Key` will be created
and executed. The event is then consumed to prevent the event from propagating
any further to the inner UI elements.

![GenericKeyExecute sequence diagram](images/uievent/GenericKey/GenericKeyExecute.png)

The above sequence diagram shows how the user interface can be modified during
the execution of the `Key` instance. The `Key` calls the respective method of
the `MainWindow` which in turns calls the respective UI component to perform
the action associated with the event.

We will use the `SwitchTabKey` event handler as a concrete example. The following
sequence diagrams show what happens when the `SwitchTabKey` event handler is invoked.

![SwitchTabKey sequence diagram](images/uievent/SwitchTabKey/SwitchTabKey.png)
![SwitchTabKeyExecute sequence diagram](images/uievent/SwitchTabKey/SwitchTabKeyExecute.png)

Note that not all event handlers are built the same. An example of a more
complicated event handler will be `FindKey` which we will elaborate more
in the next section.

### Command Box

The command box in Mycelium can be in one of 2 `Mode` of operation, namely, `CommandMode` and `SearchMode`,
and supports switching between these 2 modes.
A `Mode` is modular component that is attached to the command box which
dictates the behaviour of the command box on input change and on submit.

The following is class diagram of the command box.

![CommandBox class diagram](images/CommandBoxClassDiagram.png)

#### Changing Modes

The user can toggle between `SearchMode` and `CommandMode` with a keyboard event
invoking the `FindKey` event handler. Please refer to the
[UiEvent Handling](#uievent-handling) section for more information of how Mycelium
handles keyboard event.

The following sequence diagrams show what happens when `FindKey` event handler
is invoked.

![FindKey sequence diagram](images/uievent/FindKey/ToggleMode.png)
![FindKeyExecute sequence diagram](images/uievent/FindKey/ToggleModeExecute.png)

Depending on the current `Mode` of the command box, triggering the `FindKey` event handler
creates the other `Mode` and calls `MainWindow#setCommandBoxMode(Mode)` which sets `Mode`
of the command box by calling `CommandBox#setMode(Mode)`.

![CommandBoxSetMode sequence diagram](images/commandbox/CommandBoxSetMode.png)

When `Mode#setMode(Mode)` is called, the command box will call `Mode#teardownMode()`
on its outgoing `Mode` to perform the necessary clean-up which includes
reverting the input to its prior state. The command box then calls `Mode#setupMode(String)`
on the incoming `Mode` which will perform the necessary setup for the incoming `Mode`.
This includes caching the current input of the command box so that it can return the input
to its original state when incoming `Mode` is torn down in the future.

#### On submit

The following sequence diagram shows what happens when the user submits in the
command box.

![CommandBoxSubmit sequence diagram](images/commandbox/CommandBoxSubmit.png)

When the user submits, the command box will call `Mode#onInputSubmit(String)`
on its current `Mode` with the text input. An `Optional<Mode>` instance will be
returned which indicate the next `Mode` to change to if there is a `Mode`. Otherwise,
it will remain in keep its current `Mode`. This is utilised to switch back the command
box to `CommandMode` when submitting in `SearchMode`.

#### On input change

The following sequence diagram shows what happens when the user edits the input
of the command box.

![CommandBoxInputChange sequence diagram](images/commandbox/CommandBoxInputChange.png)

When the user edits the input, the command box will call `Mode#onInputChange(String)`
on its current `Mode` with the text input. This is utilised in `SearchMode` to allow
for interactive changes to the displayed projects and clients as the user types.

### Fuzzy searching

A fuzzy search searches for text that matches a term closely instead of exactly.
In Mycelium, this is implemented using a modified version of [Levenshtein
distance](https://en.wikipedia.org/wiki/Levenshtein_distance), which measures
the "distance" between two strings. A lower distance corresponds to a better
match; a higher distance corresponds to a worse match. The goal of this feature
is to provide interactive fuzzy searching and display sorted results such that
the best match is at the top; here, "interactive" means that results are ranked
and displayed *as* the user types their query.

<div markdown="span" class="alert alert-info">
:information_source: **Note:** We will use the terms "fuzzy search" and "fuzzy
find" interchangeably in this document. The term "fuzzy ranking" refers to the
entire routine of processing items, computing their Levenshtein distance
against some input, and sorting them such that the closest matches are at the
front.
</div>

This section will briefly cover the idea behind the fuzzy ranking algorithm.
For full details regarding how the scoring is done, it is best to refer to [the
code](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/util/Fuzzy.java)
directly.

The scoring algorithm is implemented in the `Fuzzy` class in two pure
functions - `Fuzzy#delta` and `Fuzzy#levenshtein`, both returning a `double`
between 0 and 1 inclusive. A score of 1 indicates a perfect match, and lower
scores indicate poorer matches.

* `Fuzzy#delta` is a simpler algorithm which takes a query string and a target
  and expects to find the query string as a subsequence of the target. In other
  words, it expects to find every character of the query within the target, and
  in the same order.
* `Fuzzy#levenshtein` computes the Levenshtein distance between two strings,
  and then normalizes it into the range [0, 1].

Suppose we have a query string and a list of items to rank, according to how
well they match the query. In broad strokes, the algorithm proceeds as such:

1. Compute `Fuzzy#delta` for each item.
1. For each item which received a score above zero, sort them by descending
   scores and add them to the resulting list.
1. Compute `Fuzzy#levenshtein` for the *remaining items* only.
1. Repeat step (2) for the remaining items using the scores obtained from
   `Fuzzy#levenshtein`.
1. Return the resulting list.

<div markdown="span" class="alert alert-success">
:bulb: The algorithm outlined above actually filters out all items which
receive a **zero** score from both `Fuzzy#delta` and `Fuzzy#levenshtein`. These
items would subsequently not be displayed to the user. If we raised this number
to, say, 0.1, then we can impose a stricter requirement on how well the items
match any given query, thus potentially filtering out more items. Hence, this
is a point for possible fine-tuning in the future.
</div>

<div markdown="span" class="alert alert-info">
:information_source: In Mycelium, fuzzy ranking is *not* case-sensitive. All
strings are converted to lowercase before any comparison.
</div>

#### `FuzzyManager`

In order to use fuzzy search from the application, we also have the
`FuzzyManager` class which exposes a more convenient API for us to rank clients
and projects. The class diagram below shows a high-level overview of the
classes involved.

![FuzzyManagerHighLevelClassDiagram](images/fuzzy/FuzzyManagerHighLevelClassDiagram.png)

The `FuzzyManager#rankItems` method is just a convenient pure function which,
when given a list of clients or projects and a query string, constructs a new
sorted and filtered list based on how well each item matches the query. It
relies on the algorithm implemented within the `Fuzzy` class.

Note that part of this logic is implemented in the `SearchMode` class, which
has already been mentioned [above](#command-box). The `SearchMode` class
encapsulates the logic required to handle search requests (via a change in user
input) as well as applying updates to the UI. The following sequence diagrams
illustrate this in further detail.

![FuzzyManagerSequenceDiagramA](images/fuzzy/FuzzyManagerSequenceDiagramA.png)
![FuzzyManagerSequenceDiagramB](images/fuzzy/FuzzyManagerSequenceDiagramB.png)

From above, we see that the `onInputChanged()` method on `SearchMode` is
invoked with the current contents of the command box. From here, it is a
three-step process.

1. Retrieve an unmodified view of the clients and projects from a `Logic` instance
1. Pass the two lists through the `FuzzyManager#rankItems` method, which
   performs fuzzy ranking on the lists of clients and projects
1. Apply the two ranked lists to the `MainWindow`

#### Updating the UI

From the sequence diagram above, we see that updates to the UI after fuzzy
ranking is achieved by having `SearchMode` *set* the list of items in
`MainWindow`. This departs from the approach used in our CRUD operations, where
(immutable) references to a `FilteredList` of clients and projects were
obtained upon UI initialization, and any changes such as the creation or
deletion of a client were automatically propagated to the UI with no additional
setters required in our application's code.

However, the inclusion of fuzzy search introduces an important requirement -
*arbitrary filtering and sorting* based on user input. Furthermore, upon the
user exiting fuzzy search mode, we need to revert the user's view of clients
and projects back to the way it was, before fuzzy searching began.

In order to decouple these requirements from the more basic ones of CRUD, we
avoid modifying the original `FilteredList` owned by the UI. The general idea
is to replace the two lists of projects and clients every time the fuzzy
ranking is performed. Thus, the fuzzy ranking is free to perform any kind of
sorting and filtering it requires without worrying about any unintentional
side effects on the UI. After the user exits from fuzzy finding mode, the UI
then retrieves a clean reference to the lists of clients and projects from the
address book, which automatically reverts it to its pre-fuzzy state.

#### `CommandBox` state

The section on the [changing Command Box modes](#changing-modes) introduced the
role of the `CommandBox` class in managing state between `SearchMode` and
`CommandMode`. We can now contextualize the differences between these two modes
in relation to fuzzy searching. This is illustrated in the activity diagram
below.

![FuzzyManagerActivityDiagram](images/FuzzyManagerActivityDiagram.png)

## Command Handling

### Command Sequence

When the command box is in `CommandMode`, the user can enter commands to perform various operations in Mycelium. The following sequence diagram shows how the command is handled.

![CommandHandling Sequence Diagram](images/commandbox/CommandHandling.png)

When the user enters a command, the command box calls `Mode#onInputSubmit(String)` on its current `CommandMode`.
The input from the command box is then propagated down to the `MainWindow`, the `Logic` and finally the `Parser` to be parsed.

The `Parser` then returns a `Command` instance to `Logic` which then executes the `Command` instance. The execution returns a `CommandResult` which is then passed back to the `MainWindow`.

The `MainWindow` updates the `CommandLog` based on the feedback from `CommandResult` and executes the `UiAction`.

The `CommandMode` then clears the `CommandBox` input and is now ready for accepting the next command.

### UiActions

The `UiAction` class is an abstract class that represents an action to be performed by the `MainWindow`. Every `CommandResult` returned from a `Command` execution contains a `UiAction` that is to be executed by the `MainWindow`.

This is used to perform actions such as switching the tab to Projects tab when a Project related command is executed or exiting the application when the `ExitCommand` is executed.

The following sequence diagram shows how the `UiAction` that switches the tab to Projects tab.

![SwitchToProjectsUiAction sequence diagram](images/uiaction/SwitchToProjectsUiAction.png)

Upon being executed, `UiAction` instance calls the appropriate method in `MainWindow` to perform the action from a successful command. In this case, the `UiAction` calls `MainWindow#selectProjectTab` to switch the tab to the Projects tab.

--------------------------------------------------------------------------------------------------------------------

## Appendix: Requirements

### Product scope

**Target user profile**:
**Freelance Devs**

Freelance web developers with postings on multiple online marketplaces for digital services (e.g. Fiverr) who want to manage projects and clients easily while tracking their contract terms and hours spent per project.

**Value proposition**:
Mycelium strives to be a one-stop shop for freelance web developers to consolidate projects from multiple sources. Manage descriptive yet concise information about each client, all through an intuitive console-first interface. Mycelium is tailored for the modern web developer, enabling you to build strong and trusted relationships with clients.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ...               | I want to ...                                                                                                 | So that ...                                                                          |
|----------|------------------------|---------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| * * *    | user                   | easily access each project by name for convenience.                                                           |                                                                                      |
| * * *    | user                   | create new projects and contacts.                                                                             |                                                                                      |
| * * *    | user                   | delete projects and contacts I no longer need.                                                                |                                                                                      |
| * * *    | user                   | use the application on different platforms and operating systems.                                             |                                                                                      |
| * * *    | user                   | update details of existing projects and contacts                                                              | I can keep up with changing requirements of clients.                                 |
| *        | new user               | experience an intuitive user-interface. There should be help messages to guide me around the features.        | I can refer to instructions when I forget how to use the App                         |
| *        | user                   | efficiently log information about a new client                                                                | I minimise the effort and time needed to start a project.                            |
| *        | new user               | login using my GitHub account                                                                                 | it is convenient to login.                                                           |
| *        | new user               | sign up using email and password                                                                              | I can avoid using third party authentication sources, like GitHub or Google.         |
| *        | forgetful user         | be reminded of upcoming deadlines                                                                             | I will not miss out any deliverables set by clients.                                 |
| *        | user                   | easily categorise the type of each project I have been working on                                             | it is easier to reflect on in the future and navigate.                               |
| *        | user                   | view statistics related to productivity (which channel I gain most projects, money from)                      | I know which project to put more focus on to earn better money.                      |
| *        | user                   | track my client’s payment status                                                                              | transaction management would be fuss-free.                                           |
| *        | user                   | track my project’s progress                                                                                   | I can provide timely updates to my clients.                                          |
| *        | user                   | track the time I have worked on for each project                                                              | I can ensure I have allocated time for the different projects I am working on.       |
| *        | user                   | easily view all of my freelance job requests from Fiverr and Upwork in one place.                             |                                                                                      |
| *        | user                   | update my availability status for each platform in one place                                                  | I don't miss out on job offers due to scheduling conflicts.                          |
| *        | user                   | store client information, such as contact details and project specifications, in one place                    | I can quickly access it when needed.                                                 |
| *        | user with many clients | easily communicate with clients through the product                                                           | I can streamline my workflow and avoid having to switch between different platforms. |
| *        | user                   | receive notifications when I receive new job offers or when deadlines are approaching                         | I can stay on top of my workload.                                                    |
| *        | user                   | easily generate invoices for each freelance job and track payment status                                      | I can manage my finances more effectively.                                           |
| *        | user                   | easily rate and review clients                                                                                | I can make informed decisions about which jobs to accept in the future.              |
| *        | user                   | follow the work of other developers                                                                           | I can find opportunities to collaborate.                                             |
| *        | user                   | use the app to estimate the amount of time that I will need for a project                                     | I can determine if I have the capacity to take on new projects.                      |
| *        | user                   | keep in contact with other developers                                                                         | I can work on larger projects efficiently.                                           |
| *        | user                   | personalize my contacts                                                                                       | I can know each client better                                                        |
| *        | user                   | export data from the product in different formats, such as CSV or Excel                                       | I can use the information elsewhere.                                                 |
| *        | user                   | securely store confidential information, such as client details                                               | I can keep sensitive information safe.                                               |
| *        | user                   | use the product offline                                                                                       | I can access my information when I don't have an internet connection.                |
| *        | user                   | easily search and filter projects based on specific criteria, such as deadline, client name, or project status | I can quickly find the information I need.                                           |

### Use cases

(For all use cases below, the **System** is the `Mycelium` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Create a project**

**MSS**

1. User enters command and submits details for new project
2. Mycelium creates the project
3. User can view the new project listed in panel

   Use case ends.

**Extensions**

* 1a. Required project details are not provided.
   * 1a1. Mycelium shows an error message.

   Use case resumes at step 1.

* 1b. Some project details are invalid.
   * 1b1. Mycelium shows an error message.

   Use case resumes at step 1.

**Use case: Create a client**

**MSS**

1. User enters command and submits details for new client
2. Mycelium creates the client
3. User can view the new client listed in panel

   Use case ends.

**Extensions**

* 1a. Required client details are not provided.
   * 1a1. Mycelium shows an error message.

   Use case resumes at step 1.

* 1b. Some project details are invalid.
   * 1b1. Mycelium shows an error message.

   Use case resumes at step 1.

**Use case: Delete a project**

**MSS**

1. User enters command and submits name of project to delete
2. Mycelium deletes the project
3. User can no longer see project listed in panel

   Use case ends.

**Extensions**

* 1a. Project with submitted name does not exist.
   * 1a1. Mycelium shows an error message.

   Use case resumes at step 1.

**Use case: Delete a client**

**MSS**

1. User enters command and submits email of client to delete
2. Mycelium deletes the client
3. User can no longer see client listed in panel

   Use case ends.

**Extensions**

* 1a. Client with submitted email does not exist.
   * 1a1. Mycelium shows an error message.

   Use case resumes at step 1.

**Use case: Edit a project**

**MSS**

1. User enters command and submits name of project to edit and new details for the project.
2. Mycelium edits the project
3. User can view the edited project listed in panel

   Use case ends.

**Extensions**

* 1a. Project with submitted name does not exist.
   * 1a1. Mycelium shows an error message.

   Use case resumes at step 1.

* 1b. Some project details are invalid.
   * 1b1. Mycelium shows an error message.

    Use case resumes at step 1.

**Use case: Edit a client**

**MSS**

1. User enters command and submits email of client to edit and new details for the client.
2. Mycelium edits the client
3. User can view the edited client listed in panel

   Use case ends.

**Extensions**

* 1a. Client with submitted email does not exist.
   * 1a1. Mycelium shows an error message.

   Use case resumes at step 1.

* 1b. Some client details are invalid.
   * 1b1. Mycelium shows an error message.

   Use case resumes at step 1.

**Use case: Find a project**

**MSS**

1. User toggles into search mode
2. Mycelium switches into search mode
3. User navigates to the project display and enters search query
4. Mycelium displays a list of projects ranked by closeness to the search query
5. User can view the list of projects listed in panel.

   Use case ends.

**Extensions**

* *a. At any time, User chooses to toggle out of search mode.
   * *a1. Mycelium switches back to normal command mode.

   Use case ends.

**Use case: Find a client**

**MSS**

1. User toggles into search mode
2. Mycelium switches into search mode
3. User navigates to the client display and enters search query
4. Mycelium displays a list of clients ranked by closeness to the search query
5. User can view the list of clients listed in panel.

   Use case ends.

**Extensions**

* *a. At any time, User chooses to toggles out of search mode.
   * *a1. Mycelium switches back to normal command mode.

   Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
1.  Should be able to hold up to 1000 clients without a noticeable sluggishness in performance for typical usage.
1.  Should be able to hold up to 1000 projects without a noticeable sluggishness in performance for typical usage.
1.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
1.  All user operations should complete under 100ms.
1.  Should not lose any work in case the application crashes.
1.  Data persists when the application restarts.

*{More to be added}*

## Glossary

The terms in this glossary are sorted in alphabetical order.

* **Client**: An individual associated with a particular project, presumably
  the one who posted or requested it
* **Console-first interface**: An interface with interactions primarily through
  text commands or hotkeys
* **Draw.io**: A drag-and-drop diagramming tool. Available at
  [draw.io](https://app.diagrams.net/).
* **Fuzzy search**: A feature to search for items which partially match a query
  rather than exactly
* **Hotkey**: A convenient combination of key presses meant to invoke some app
  functionality
* **PlantUML**: A text-based diagramming tool. See
  [plantuml.com](https://plantuml.com/) for more information.
* **Project**: A freelance software development gig

--------------------------------------------------------------------------------------------------------------------

## Appendix: Instructions for manual testing

<div markdown="span" class="alert alert-success">
:bulb: When testing commands, it is recommended to test different combinations
of the optional arguments to ensure that the program can handle different use
cases.
</div>

### Client commands

**Creating a client**

* Prerequisite: there is no client with email *spiderman@gmail.com*
* Command: `c -cn spider man -e spiderman@gmail.com`
* The output box should display a message confirming that the new client has
  added
* The client should be appended to the bottom of the Clients tab

<!-- -->

* Pre-requisite: none
* Command: `c -cn spider man`
* The output box should display an error message on invalid command format

**Deleting a client**

* Prerequisite: there is a client with email *spiderman@gmail.com*
* Command: `dc -e spiderman@gmail.com`
* The output box should display a message confirming the deletion
* There should not be a client with the email *spiderman@gmail.com* listed in
  the Clients panel

<!-- -->

* Prerequisite: none
* Command: `dc -cn spider man`
* The output box should display an error message on invalid command format

**Updating a client**

* Pre-requisite: there is a client with email *spiderman@gmail.com*, but no
  client with email *spoderman@gmail.com*
* Command: `uc -e spiderman@gmail.com -e2 spoderman@gmail.com`
* The output box should display a message confirming the update
* There should be no client with the email *spiderman@gmail.com*, and one
  client with email *spoderman@gmail.com* listed in the Clients panel

<!-- -->

* Pre-requisite: there is a client with email *spiderman@gmail.com*
* Command: `uc -e spiderman@gmail.com -e2 spiderman@gmail.com`
* The output box should display a message indicating that no update was made

<!-- -->

* Pre-requisite: there is a client with email *spiderman@gmail.com*, and a
  client with email *batman@gmail.com*
* Command: `uc -e spiderman@gmail.com -e2 batman@gmail.com`
* The output box should display a message indicating that a client with the new
  email already exists

### Project commands

**Creating a project**

* Prerequisite: there is no project with name *project1*
* Command: `p -pn project1 -e foo@bar.baz`
* The output box should display a message confirming that the new project has
  been added
* The project should be appended to the bottom of the Projects tab

<!-- -->

* Prerequisite: none
* Command: `p -pn project1`
* The output box should display an error message on invalid command format

**Deleting a project**

* Prerequisite: there is a project with name *project1*
* Command: `dp -pn project1`
* The output box should display a message confirming the deletion
* There should not be a project with the name *project1* listed in the Projects
  panel

<!-- -->

* Prerequisite: none
* Command: `dp -e foo@bar.baz`
* The output box should display an error message on invalid command format

**Updating a project**

* Pre-requisite: there is a project with name *project1*, but no project with
  name *project2*
* Command: `up -pn project1 -pn2 project2`
* The output box should display a message confirming the update
* There should be no project with the name *project1*, and one project with
  name *project2* listed in the Projects panel

<!-- -->

* Pre-requisite: there is a project with name *project1*
* Command: `up -pn project1 -pn2 project1`
* The output box should display a message indicating that no update was made

<!-- -->

* Pre-requisite: there is a project with name *project1*, and a project with
  name *project2*
* Command: `up -pn project1 -pn2 project2`
* The output box should display a message indicating that a project with the
  new name already exists

### Fuzzy search

As fuzzy search is a more involved feature different from the other commands, we
provide a more detailed suggested test plan for it.

1. Press (CTRL+F) to toggle to search mode and note that the command box
   should switch color as an indication.
1. Type a query into the command box that partially matches a project name and
   verify that the projects with matching names are displayed in the UI. Note
   that closer matches should be placed at the top.
1. Verify that the search is interactive, and the UI automatically updates as
   you type your query.
1. Verify that only projects that have a partial match with the query are
   shown. Projects that don't match at all should not be shown.
1. Verify that the search is not case-sensitive.
1. Test that exiting search mode by pressing (CTRL+F) again returns the command
   box to its original color.
1. Repeat the above steps for client names.

The following steps test the feature for extracting project names and client
emails with (ENTER).

1. Press (CTRL+F) to enter search mode.
1. In the search box, type a query for a project or client name that exists in
   the list, but do not press enter.
1. Use (CTRL+J) and (CTRL+K) to navigate and select the desired project or
   client in the search results.
1. Press (ENTER) to exit search mode and append the project's name or client's
   email to the command box.
1. Verify that the selected name or email is appended to the command box
   correctly.

### Hotkeys

The testing of hotkeys is simple, and you can reference the [user
guide](https://ay2223s2-cs2103t-w14-1.github.io/tp/UserGuide.html#hotkeys) on
the expected behaviour of each hotkey.

## Appendix: Documentation, Logging, Testing, Configuration, and DevOps

* [Documentation guide](/tp/Documentation.html)
* [Logging guide](/tp/Logging.html)
* [Testing guide](/tp/Testing.html)
* [Configuration guide](/tp/Configuration.html)
* [DevOps guide](/tp/DevOps.html)

--------------------------------------------------------------------------------------------------------------------

## Appendix: Planned Enhancements

This section documents some known feature flaws and our planned solutions for
each.

**1. Lost all data after modifying data file**

We plan to utilise checksum on JSON file to prevent manual tampering of data
and display explicit exceptions when the JSON file is corrupted or tampered
with.

* A checksum could be generated in `checksum.txt` in the same directory as the
  JSON file.
* Any modification to the JSON file would result in a different checksum, which
  can be detected by comparing with the checksum stored in `checksum.txt`.
* If the checksums do not match or is missing, the user will be notified about
  a possible corruption in their data and be prompted to restore the JSON file
  from a backup.
* The JSON file will then proceed to be read by Mycelium. If the JSON file is
  unreadable or contains invalid values, the JSON file will be ignored, and the
  application will start as an empty application with no projects and clients.
* Possible checksum algorithms are MD5, SHA-1, SHA-256, SHA-512.

**2. Long strings are cut off**

We are considering two alternatives: limiting the number of characters for a
field or wrapping text to fix the issue

* First solution: Limit the number of characters for a field. This will make
  sure that the number of characters will not exceed the screen size and will
  not take up too much space. For example, for a screen of 1366 pixels wide,
  can fix approximately 170 characters at regular font size. As around 40%
  width of the screen size is used by a panel such as entity panel, around 68
  characters can fit in the full screen size. To make up for the field name
  such as 'Name: ', we put 60 as the maximum capacity for number of characters
  for a field.
* Second solution: Wrap the text around using `TextFlow` and `Text` in JavaFX,
  even if the word needs to be broken. For this approach, the number of
  characters that can be fit onto a line will still be needed. Basically, a
  string will be checked if it exceeds the limit of characters on a line. If it
  does, the `Text` including all the characters up to the limit will be added
  to the `TextFlow` node. The same process will be done repeatedly for the rest
  of the string until all characters run out. This is to make sure that even if
  a word is too long, the text will still be wrapped to the card size.

**3. Pie chart for project status spins even when no statuses change**

We plan to add a check to prevent pie chart from refreshing when no changes to
status of any project were made

* The current hashmap of project status along with the count of the
  corresponding status will be stored. If there are any new changes to the list
  of projects, a new hashmap will be generated. This new hashmap will then be
  compared with the current one. If there are any changes in the status count,
  the pie chart will be refreshed. Else, the current pie chart will be kept.
* This will make sure that only changes related to project status will trigger
  the refreshing of pie chart.

**4. Case sensitivity of emails**

Currently, it is possible to create clients with the same email but in
different cases, e.g. *foo@bar.com* and *FOO@bar.com*. However, in the real-world
emails are [not case-sensitive](https://mailchimp.com/resources/are-email-addresses-case-sensitive/).
Thus, the application should reflect this. Thankfully, the fix is easy, as it
involves changing the `equals()` method on the `Email` class.

We can change it from this:

```java
 public boolean equals(Object other) {
     return other == this || (other instanceof Email
         && this.value.equals(((Email) other).value));
 }
 ```

to this:

```java
 public boolean equals(Object other) {
     return other == this || (other instanceof Email
         && this.value.toLowerCase().equals(((Email) other).value.toLowerCase()));
 }
 ```

**5. Insufficient constraints on year of birth**

Currently, a client's year of birth is permitted to be any year from 0000 to
9999 inclusive. However, this may not make sense from a usage perspective, and
additional validation would be useful to guard against typos. As such, we plan
to adopt the following fixes:

1. Fix 1800 as a lower bound. Since Mycelium's target users are software
   developers, 1800 is a very reasonable lower bound, after accounting for the
   age of modern computing as well as the average human lifespan.
1. Allow years up to 10 years into the future. Why do we allow this? Consider
   the following (rare) use case: a family member is expecting a child and has
   enlisted your help to set up a self-hosted photo storage system in
   preparation for keeping memories of his/her childhood.
   <br>
   As this is not a wholly improbable use case, we plan to set the upper bound
   to ten years from whenever the present time is. It also does not cause
   problems for other use cases.

The validation for this is simple and goes into the `isValidYearOfBirth`
method of the [`YearOfBirth`
class](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/client/YearOfBirth.java).
In pseudocode, the check becomes

1. Ensure exactly four digits are specified (same as before)
1. Get current year as an integer; also convert the four digits into a base 10
   integer
1. Ensure that the year is within [1800, current year + 10]

**6. Insufficient contraints for project's accepted date and deadline**

*This issue is similar to the one above.* Currently, the range of valid dates for
a project's accepted date and deadline are from 1st January -9999 to 31st
December 9999. However, this is not intuitive from a user's perspective. As
such, we plan to add the following validations:

1. Fix 1800 as a lower bound for both accepted date and deadline, for the same
   reason as above.
1. Fix up to 10 years into the future as the upper bound for a project's
   accepted date, for the same reasons as above.

Note that we do not plan to fix an upper bound for a project's deadline, as it
is hard to make reasonable assumptions about what a user's deadlines might look
like. Furthermore, some users may wish to use 31st December 9999 to represent a
project with "a deadline so far into the future it is not worth considering".

**7. No upper bound on a mobile number's length**

Currently, the only validation done for mobile numbers is that they must be at
least 3 digits. However, realistically there is a limit on how long a mobile
number can be, and thus it would be good to add an upper bound.

We have decided on 35 characters as the inclusive upper bound, based on [this
suggestion](https://stackoverflow.com/questions/723587/whats-the-longest-possible-worldwide-phone-number-i-should-consider-in-sql-varc#comment40058364_4729239).
This allows us to comfortably store a prefix, suffix, and the mobile number
itself with a little room to spare for future-proofing.

The validation is currently done using this regexp string: `\\d{3,}` via
`String#matches`. As long as there is a match, the number is considered valid.
We can simply update the regexp to `\\d{3,35}` to enforce this upper bound.

**8. Potentially confusing error messages when wrong flags are used**

Consider the following (wrong) command:

```
uc -e foo@bar.com -n totoro
```

The intention of the command is to update the client whose email is
*foo@bar.com* to have the name *totoro*. Unfortunately, the `-n` flag is wrong,
and should instead be `-cn`. Mycelium reads `foo@bar.com -n totoro` as the
email and responds with an error message that the email is in the wrong
format.

Firstly, we would like to note that with regards to what we have specified in
the user guide regarding the [command
layout](https://ay2223s2-cs2103t-w14-1.github.io/tp/UserGuide.html#command-layout),
this is working as intended. It is not Mycelium's responsibility to assume the
user's intention in situations like the example above; suppose our email is
`foo@bar-n.com`, but we accidentally inserted some spaces to become `foo@bar -n
.com`. What is the right course of action for Mycelium's parser? We have three
options:

1. Throw an error about invalid email format (current behaviour)
1. "Intelligently" suggest that the `-n` flag might have been intended as `-cn`
1. "Intelligently" piece together the email

It is not clear which is the best option. Thus, we have decided that the best
solution is to *revamp the command layout* to match that used by the [Bash
shell](https://www.gnu.org/software/bash/manual/bash.html#Shell-Syntax),
requiring that arguments with whitespace be wrapped in quotation marks. The
ambiguity here clearly arises from the existence of whitespace. Following
Bash's command syntax thus solves the problem entirely. Since Mycelium is
targeted at developers, this syntax should also feel right at home.

With the new syntax, the command above can be very clearly parsed:

* *foo@bar.com* is the client's email
* `-n` is an unknown flag, with argument *totoro*

Thus we can respond with the correct error message: that `-n` is an unknown
argument flag. If the user really wanted (for some reason) to enter a wrong
email, they would need to do the following:

```
uc -e 'foo@bar.com -n totoro'
```

To which Mycelium would respond with an invalid email error message.

**9. Error message not specific when multiple wrong dates are given**

Consider the following command:

```
p -pn foo -e foobar@baz.com -ad qwerty -dd 14/03/2023
```

The *accepted date* is invalid while the deadline is valid. Mycelium currently
responds with the message "The date entered is invalid" and does not specify
which one. This can be easily remedied by adding an overload for the method
which parses dates. In brief, the new overload will

1. Expect to receive the argument's name, e.g. "project deadline", as a string
1. If it fails to parse the argument data, an exception explicitly referencing
   the argument's name is thrown
1. This message is displayed to the user

**10. Unable to unset an optional field**

Assume the following scenario:

1. We create a project with a deadline 14/03/2024
1. Later, our client informs us that there is no deadline, and we can take as
   long as we like
1. We would like to remove the deadline from the project in Mycelium

Currently, we would have two options.

1. Delete the project, then create a new one with the same fields but without a
   deadline
1. Use 31/12/9999 to represent "no deadline"

Neither of these solutions are ideal. Thus, we would like to propose an
enhancement to the `uc` and `up` commands which allow us to unset optional
attributes of clients and projects. The command below demonstrates how we can
unset the deadline of project *foo*:

```
up -pn foo -dd
```

The `-dd` argument flag has no data following it, expressing the user's
intention to unset the deadline field. This enhancment would not be difficult,
as internally, these attributes are already typed as `Optional`s. At a high
level, the new parser for the `uc` and `up` commands would work like this:

1. Parse arguments as per usual
1. For the arguments which received no data, check if they are indeed optional
   fields
1. If they are *not* optional, throw an error and inform the user
1. Otherwise, proceed to set the fields to `Optional.empty()`

## Appendix: Effort

AB3 was only able to support a single entity type, which was the `Person` class.
We had to modify the codebase to support multiple entity types,
which are the `Project` and `Client` classes. We also decided to stray away from the default
command syntax in favour of a more Unix-like syntax. This was a challenge as we had to extend the parser
support a different and larger set commands.

One of the challenges we faced was how to display 2 entity types in the same panel.
We decided to use tabs to display the different entity types. This was a challenge as we had
to learn how to use the JavaFX `TabPane` and `Tab` classes, and structure the
components to follow good design principles.

This brings us to the next challenge, which was how ensure that Mycelium remained keyboard-centric.
The addition of tabs meant that the user had to use the mouse to switch between tabs. To solve
this problem, we decided to allow the user to switch between tabs using the keyboard with vim-like
shortcuts. This was a challenge as we had to learn how to use the JavaFX `KeyEvent` class and
implement the shortcuts in a way that was extensible for new shortcuts. This led us to come up
with the `UiEvent` class as a single place to add new shortcuts.

By supporting two types of entities, we would need to have commands related to each entity.
We wanted Mycelium to have the intuitive behaviour of automatically switching between the
project tab and the client tab depending on which tab is relevant to the command. To solve this problem,
we came up with the `UiAction` class which allowed us to hook actions such as switching the tab
onto the response of a command execution.

Another challenge was to enable easy and intuitive searching of projects and clients.
We were inspired by the command-line fuzzy finder `fzf` which ranks the options according
to how well it matches the query as the user searches. When the command box is in its
default command mode, it only executes commands when the input is submitted; when the command box is in search mode,
it will read the input as the user types and rank the projects and clients based on how well
it matches said input. This was quite challenging as the behaviour of the command box of AB3
had to be drastically modified to support different modes. This was done with the addition
of the `Mode` class which is a component attached to the command box that
dictates the command box behaviour when the user input is changed or submitted.

We decided to utilise a keyboard shortcut to allow the user to toggle between command mode
and search mode easily. This was done by leveraging off the existing `UiEvent`
class that we have implemented earlier.

We wanted the user to be able to get an overview of their projects.
To do this, we created another panel to display

* a pie chart to show the proportions of projects in each status, and
* a table to show which projects are overdue and which projects are due soon.

This was a challenge as it was adding complexity to the UI and required us to learn
how to use the `PieChart` class. With another panel, we now also need an additional
keyboard shortcut to switch focus between the 2 main panels to ensure Mycelium
remained keyboard-centric.

Overall, the team project was moderately challenging. Navigating the codebase was difficult
at first as there were many levels of abstractions and many classes to understand.
We got our hands dirty by trying to understand and starting with the easier features
such as:

* Implementing the basic CRUD functionality of projects and clients,
* Adding tabs to the application, and
* Tweaking the parser to support the new commands.

The process of adding small features allowed to get a better understanding of the codebase
and gave us confidence in adding more advanced features.
By splitting up the work and specialising in different parts of the codebase, each of us
gain a stake in the project and were more motivated constantly improve the project.
We also reviewed each other's code, helped each other out when we encountered problems, and
hold weekly meetings to discuss our progress and to plan out the next week's work.

