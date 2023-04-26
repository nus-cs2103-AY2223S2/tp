---
layout: page
title: Developer Guide
---
<a id="top"></a>

* Table of Contents 
{:toc}

<div style="page-break-after: always;"></div>

## 1. Introduction

### 1.1 Product Overview
OfficeConnect is a task management tool designed specifically for managerial role personnels based within Singapore.

As managers in the current office environment, it is often not easy to grasp the workload of subordinates. This may result in:
* work overload among subordinates,
* difficulties in coordinating tasks with a large number of employees.

OfficeConnect offers a solution to these problems by providing better visibility into subordinates’ workloads, allowing
managers to efficiently delegate tasks in an organised manner. 

### 1.2 Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

### 1.3 Acknowledgements

OfficeConnect is a brownfield Java project based on
the [AB3 project template](https://github.com/se-edu/addressbook-level3)
by [se-education.org](https://se-education.org).

* Libraries used include: [JavaFx](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson),
* Images retrieved from freepik and ICON8.
* [JUnit5](https://junit.org/junit5/)

### 1.4 Setting up, getting started

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 2. Design

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103-F10-1/tp/tree/master/docs/diagrams) folder.
Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html)
to learn how to create and edit diagrams.
</div>


### 2.1 Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.


#### 2.1.1 Main components of the architecture

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/MainApp.java).
It is responsible for
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.
[**`Commons`**](#27-common-classes) represents a collection of classes used by multiple other components.
<div style="page-break-after: always;"></div>

The rest of the App consists of four components.

* [**`UI`**](#22-ui-component): The UI of the App.
* [**`Logic`**](#23-logic-component): The command executor.
* [**`OfficeConnectModel`**](#24-officeconnectmodel-component): Holds the data of the App in memory.
* [**`Storage`**](#25-storage-component): Reads data from, and writes data to, the hard disk.

#### 2.1.2 How the architecture components interact with each other

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `deletet 1` to delete the task at index 1. The command `deletet 1` is represented as commandText in the 
diagram below. 

<img src="images/ArchitectureSequenceDiagramTwo.png" width="574" />

OfficeConnectModel is a concrete class that contains two repository model managers and a person model manager. 
Of the two repository model managers, one is used to keep track of tasks (i.e. `Task` objects) and the other is used 
to keep track of the assignment of tasks to persons (i.e `AssignTask` objects). Meanwhile, 
the person model manager implements the Model interface and is used to keep track of persons stored in OfficeConnect.

Each of the other three main components (excluding OfficeConnectModel), defines its API in an `interface` with the 
same name as the Component. The components implement their functionality using a concrete `{Component Name}Manager` class.
<div style="page-break-after: always;"></div>

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality
using the `LogicManager.java` class which follows the `Logic` interface. The components work together as illustrated 
in the (partial) class diagram below.

<img src="images/OfficeComponentManagers.png" width="750" />
<div style="page-break-after: always;"></div>

### 2.2 UI component

The **API** of this component is specified in
[`Ui.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java).
![Structure of the UI Component](images/UiClassDiagramNew.png)

The UI consists of a `MainWindow` that is made up of Ui parts that will be encapsulated here as `UiComponents`. 
Examples of its members are visual elements that you will see on screen such as `CommandBox`, `ResultDisplay`, `PersonListPanel`, 
`HelpWindow`, `QuickstartWindow`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from 
the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml`
files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,
* executes user commands using the `Logic` component.
* listens for changes to `OfficeConnectModel` data so that the UI can be updated with the modified data. 
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands. 
* depends on some classes in the `OfficeConnectModel` component, as it displays `Person` and `Task` objects residing in
the `OfficeConnectModel`.
<div style="page-break-after: always;"></div>

### 2.3 Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddTaskCommand`) 
which is executed by the `LogicManager`.
3. The command can communicate with `OfficeConnectModel` when it is executed (e.g. to add a person or
a task). 
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("deletep 1")`
API call, which deletes the person located at index 1. The command `deletep 1` is represented by commandText in the 
diagram below.

![Interactions Inside the Logic Component for the `deletep 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser`
should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an 
`XYZCommandParser`(`XYZ` is a placeholder for the specific command name e.g., `AddTaskCommandParser`) which uses the 
other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddTaskCommand`) which the 
`AddressBookParser` returns back as a `Command` object. 
* All `XYZCommandParser` classes (e.g., `AddTaskCommandParser`, 
`DeleteTaskCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.
<div style="page-break-after: always;"></div>

### 2.4 OfficeConnectModel component

**API** : [`OfficeConnectModel.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/model/OfficeConnectModel.java)

<img src="images/OfficeConnectModelClassDiagram.png" width="800" />

* Stores the task list data and task assignment data i.e., all `Task` and `AssignTask` objects (which are contained in a `UniqueItemList` object).
* Stores the currently 'selected' `Task` and `AssignTask` objects (e.g., results of a search query) as separate _filtered_ lists
  which is exposed to outsiders as an unmodifiable `ObservableList<Task>` and `ObservableList<AssignTask>` that can be
  'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* Stores data of all persons in OfficeConnect using a concrete implementation of the `Model` interface. Greater elaboration 
of the `Model` component is provided below.
* Does not depend on any of the other three components (as the `OfficeConnectModel` represents data entities of the domain,
they should make sense on their own without depending on other components)
<div style="page-break-after: always;"></div>

### 2.4.1 Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="550" />

The `Model` component 
* Is implemented by a person model manager in OfficeConnectModel.
* Stores the address book data for persons i.e., all `Person` objects (which are contained in a `UniquePersonList` object). 
* Stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate filtered list,
which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. 
the UI can be bound to this list so that the UI automatically updates when the data in the list changes. 
* Stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a`ReadOnlyUserPref` 
object.

<div style="page-break-after: always;"></div>
### 2.5 Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103-F10-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

OfficeConnect Storage Component

<img src="images/StorageClassDiagram.png" width="750" />

The initial storage component was specifically designed to accommodate the address book model. However, OfficeConnect
necessitates the inclusion of two additional storage types, namely task storage and assignment storage.

To address this requirement, a new generic class called `RepositoryStorage` has been introduced to the storage component.
This addition allows for increased extensibility and flexibility in the storage component's functionality.

The enhanced design is extendable and also capable of supporting the integration of additional databases into the
application in the future, if required.

The `Storage` component saves address book data, task list data, task assignment data and user preference data in json format 
and reads them back into corresponding objects. It inherits from both `AddressBookStorage` and `UserPrefStorage`, which 
means it can be treated as either (if the functionality of only one is needed). It depends on some classes in the 
`OfficeConnectModel` component (because the `Storage` component's job is to save/retrieve objects that belong to `OfficeConnectModel`).

### 2.6 Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 3. Implementation
This section describes some noteworthy details on how certain features are implemented.

Notation: inputs placed in closed brackets [] are optional.

### 3.1 Adding a Task
Syntax: `addt t/TITLE c/CONTENT st/STATUS [dl/DEADLINE]`  
Purpose: Allows users to add tasks into OfficeConnect.

#### 3.1.1 Implementation
The implementation of this feature is supported by `AddTaskCommand` and `AddTaskCommandParser`.
Below is a sequence diagram that illustrates how a user adds new tasks into OfficeConnect.  

![AddTaskSequenceDiagram](images/AddTaskSequenceDiagram.png)

#### 3.1.2 Design Considerations
**Aspect: Setting deadline as optional input**

* **Alternative 1 (current choice):** Deadline is optional.
  * Pros: Increase convenience for users, as they will have to key in one less input. Not all tasks may have a set deadline.
  * Cons: As it is optional, users may choose to prioritise convenience and not enter a deadline even if it will benefit the task description. 

* **Alternative 2:** Make deadline compulsory.
  * Pros: Easier to implement.
  * Cons: Inconveniences users, as they have to key in one more input to create a task, especially if there is no set deadline 
  for the task. They will have to create a dummy deadline just to create a task without a deadline.

#### 3.1.3 Constraints:
**Title must be unique:**  
We felt that the title should be unique as it improves organisation and visual clarity for the user. By mandating unique
titles, we encourage users to be specific in the title (purpose) of the task (e.g. they will set title as
"Complete slides for Mr X" rather than "Complete Slides"), which will benefit them greatly, as they will be
able to clearly distinguish the purpose of each task just by looking at the title.  

Suppose that the title was not unique. Users might have many tasks with the same title, which would impair their ability
to distinguish between the tasks unless they read each of the task content individually. It would also reduce the visual
clarity when searching for tasks, as tasks with similar titles might clutter up the GUI.
Hence, our approach in mandating unique titles are geared towards improving organisation and visual clarity for users in
both the short and long term.

### 3.2 Deleting a Task
Syntax: `deletet INDEX`  
Purpose: Allows users to delete the task at the specified index in OfficeConnect.

#### 3.2.1 Implementation
The implementation of this feature is supported by `ListTaskCommand`, `DeleteTaskCommand` and `DeleteTaskCommandParser`.
Below are the steps required to delete a task in OfficeConnect.  

Step 1: User keys in `listt`, which will display the index of all tasks.
The user can thus obtain the index of the task that they want to delete.

Step 2: User keys in `deletet INDEX` to delete the task at the specified index.
If the index is invalid, an error will be thrown.

Below is an activity diagram showcasing the 2 steps:  
![DeleteTaskActivityDiagram](images/DeleteTaskActivityDiagram.png)


#### 3.2.2 Design Considerations
**Aspect: Implementation of Delete Task Command**

* **Alternative 1 (current choice):** Users have to call `listt` to find the index of the task they wish to delete.
  * Pros: Increase convenience for users, as they do not have to remember the index of each task while being easier to implement.
  * Cons: Increases coupling within OfficeConnectModel, as any bug with `listt` could render users incapable of
    obtaining the index needed for `deletet`.

* **Alternative 2:** Allow users to key in the index of each task when creating tasks, after which they can
  use this index when deleting tasks.
  * Pros: If the user remembers the index of each task, they will not need to call `listt`. Hence, it will be less
    troublesome for them to delete tasks as the number of steps required is reduced by one.
    Also reduces coupling, as `deletet` will not have to depend on `listt` to function properly.
  * Cons: The cons of this alternative lies in the difficulty of managing indexes when adding and deleting tasks.  
    If the user does not keep track of the indexes they have used for previous tasks, they may have to still
    call `listt` to find the index of the task they wish to delete or to find unused indexes to add tasks, which will not
    give it an advantage over the first alternative.  
    It would also be harder to keep track of invalid indexes. When tasks are deleted, their index should be invalid. Using
    this alternative, we would have to constantly update a list of invalid indexes when adding or deleting tasks, which
    would be troublesome and could lead to bugs. In alternative 1, all indexes are flushed to the front (i.e. first task
    has index 1, second task has index 2 etc.) and thus the invalid indexes can be easily obtained.
  
### 3.3 Finding a Person's Assigned Tasks
Syntax: `findp NAME`  
Purpose: Allow users to search and review the list of tasks assigned to the specified person in OfficeConnect.

#### 3.3.1 Implementation
The implementation of this feature is supported by `FindCommand` and `FindCommandParser`.

Below is an activity diagram that illustrates the control flow for the Find feature.  

![FindTaskActivityDiagram](images/FindActivityDiagram.png)

#### 3.3.2 Design Considerations

**Aspect: Updating GUI**

* **Alternative 1 (current choice):** Displays empty list when no persons are found.
  * Pros: It provides a consistent user experience, as users can expect the same behavior when searching for people throughout the application.
  * Cons: An empty list can sometimes appear visually unappealing and may give the impression that the application is not functioning correctly.

<div style="page-break-after: always;"></div>

* **Alternative 2:** Displays most recent valid search result list when no persons are found.
  * Pros: Saves time for users who may have previously searched for similar or related information and want to quickly access those results again.
  * Cons: Could lead to frustration or confusion if users don't understand why they're seeing old results instead of new ones.

### 3.4 Finding a Task's Assignees
Syntax: `findt TITLE` 

Purpose: Allow users to search and review the group of individuals assigned to the specified task in OfficeConnect.

#### 3.4.1 Implementation
The implementation of this feature is supported by `FindTaskCommand` and `FindTaskCommandParser`.

Below is an activity diagram that illustrates how a user finds who are assigned to a task.

![FindTaskSequenceDiagram.png](images%2FFindTaskSequenceDiagram.png)

<div style="page-break-after: always;"></div>

#### 3.4.2 Design Considerations

**Aspect: Form of query**

* **Alternative 1 (current choice):** Query using `TASK_TITLE`
    * Pros: More intuitive as users do not have to keep track of the list index of the tasks.
      Able to query for tasks that are already logged in OfficeConnect using the title of the task.
    * Cons: Length of commands are dependent on length of title. Users have to remember the name of the tasks.

* **Alternative 2:** Query using `INDEX`
    * Pros: Shorter command to type out.
    * Cons: Less intuitive and less user-friendly. Users would be forced to list all the tasks before being able
      to execute the findt command if the current display is empty.

### 3.5 Editing a Task
Syntax: `editt INDEX [t/TITLE] [c/CONTENT] [st/TRUE] [dl/DEADLINE]`

Purpose: Allow users to edit tasks that are currently listed in OfficeConnect.

#### 3.5.1 Implementation
The implementation of this feature is supported by `EditTaskCommand` and `EditTaskCommandParser`.

#### 3.5.2 Design Considerations

**Aspect: Parameters required**

* **Alternative 1 (current choice):** Edit using at least one of the fields of the task.
  * Pros: More intuitive and convenient for users as they do not have to retype every field.
  * Cons: Users have to keep in mind which task the task index is referring to.

* **Alternative 2:** Edit by retyping all the fields of the task to be edited.
  * Pros: Forces users to be aware of the task to be edited instead of relying on subconscious mapping between index and tasks.
  * Cons: Less intuitive and less user-friendly. Users would be forced to retype all the fields of the task before being able
    to edit a single field.

<div style="page-break-after: always;"></div>

### 3.6 Assigning a Task
Syntax: `assign pi/INDEX ti/INDEX` 
<br>
Purpose: Allows users to assign a person to a task in OfficeConnect.

#### 3.6.1 Implementation
The implementation of this feature is supported by `AssignCommand`, `AssignCommandParser`, `ListCommand`, `ListAssignment`
`ListTaskCommand`, `ListAllCommand`, `FindCommand` and `FindTaskCommand`. 

Below are the steps required to assign a task to a person in OfficeConnect.

Step 1: User executes `listall` to list all the people and tasks in OfficeConnect.
* Alternative steps to Step 1:
  * User executes `findp David` to search for David in the contact list followed by `listt` to lists all the tasks.
  * User executes `findt slides`to search for a task containing the word `slides` in its title followed by `listp`
    to list all the persons.
  * User executes `viewunassignedall` to list all the persons that have not been assigned to any tasks and all tasks
    that have not been assigned to any persons.
  * User executes `findp David` to search for David in the contact list followed by `viewunassignedt` to list all tasks
    that have not been assigned to any person.
  * User executes `findt slides` to search for a task containing the word `slides` in its title followed by 
    `viewunassignedp` to list all persons that have not been assigned to any tasks.
* After this step, the target task to assign and the target person to be assigned to said task will appear in the list
  of persons and tasks displayed.

<div style="page-break-after: always;"></div>

Step 2: User executes `assign pi/1 ti/1` to assign the first task on the list of tasks to the first person on the list
of tasks.

The following activity diagram summarizes what happens when a user wants to execute an `assign` command:

![Assign Activity Diagram](images/AssignActivityDiagram.png)

#### 3.6.2 Design Considerations
**Aspect: Steps Leading to Execution of Assign Command**

* **Alternative 1 (current choice 1):** Users execute `listp` or `findp` to display a list of persons and `listt` or
  `findt` to display a list of tasks on the application.
  * Pros: Users can search for a specific person or task before assigning.
  * Cons: If `listp` and `listt` are used, users may have to scroll through a long list to search for the desired
    person or task. The results of `findp` and `findt` cannot be displayed simultaneously. Thus, if `findp` was executed
    to search for a person, the user will then have to execute `listt` to access the full list of tasks in order to
    identify the task that the user wants to be assigned to that person.

* **Alternative 2 (current choice 2):** Users execute a command to display a list tasks that have not been
  assigned to any person or to display a people that have not been assigned to that task.
  * Pros: Users can easily view all the tasks that are currently unassigned at the same time, instead of having to execute
    `findt` to search for unassigned tasks one at a time.
  * Cons: None, because this is an additional feature proposed that will complement Alternative 1.

* **Alternative 3:** Users execute a command to generate recommendations of persons or tasks to be assigned.
  * Pros: Additional convenience for user, as the recommendations generated will consist of persons who have 
  completed all their assigned tasks or have not yet been assigned any tasks. Similarly, when generating a list
  of recommended tasks, we will display a list of tasks that have not yet been completed or assigned to anyone.
  * Cons: From a manager's perspective, the generated recommendations for persons or tasks may not be suitable 
  for the given task or person. Thus, it may be better to give users full control over what they choose to display
  or search for before they choose to assign a task to a person.

### 3.7 Help Window

Syntax: `help`
<br>
Purpose: Provides users with a bird's eye view of the various methods usable in OfficeConnect.

#### 3.7.1 Implementation

This window was made by the `HelpWindow` class, which ties in its respective `fxml` and `css` files to display the relevant UI to the user.
To provide the instructions to the user, a `HelpStrings` class is maintained to give instructions stored in lengthy strings.
<div style="page-break-after: always;"></div>

A Help Window will be opened either by entering the `help` command, or by clicking on "Help" button in the toolbar.
The Activity Diagram below details the workflow of a user who wishes to access the HelpWindow. If the Help Window does not provide enough
information, the user can choose to view the comprehensive User Guide instead.

<img src="images/HelpActivityDiagram.png" width="330" />

#### 3.7.2 Design Considerations

**Aspect: Contents of help window**

* **Alternative 1 (Current choice):** Display as a separate window, with concise details included in the sheet.
    * Pros: Serves as a quick reference for the user, without having to go online to view the full comprehensive User Guide.
    * Cons: Major updates in command implementations will have to be updated in the help sheet too, in addition to the online User Guide.

* **Alternative 2:** Provide the user with the link to access the online User Guide.
    * Pros: More comprehensive, able to give user the complete detailing of each command.
    * Cons: Troublesome, requires user to go online to view the User Guide. User may also be overwhelmed by length of guide.

**Aspect: Viewing of various command instructions**

* **Alternative 1 (Current choice):** Display all executable commands in a hierarchical tree, with description of method selected in a separate area.
    * Pros: Providing a hierarchical structure to the list of commands available. This provides users with more ease of visualising the various methods as different groups of commands.
    * Cons: More complex structures to be used when implementing the Ui of the help window. Restructuring of tree also may be necessary when big changes are made to structure of commands.

* **Alternative 2:** Display all executable commands in a list.
    * Pros: Adding/Restructuring of commands only involves deleting/modifying the line the command is on, no restructuring of the list needed
    * Cons: Design not too intuitive, user may need to eyeball through all the commands in order to find what he/she is looking for.

### 3.8 Unassigning a Task from a Person

Syntax: `unassign pi/INDEX ti/INDEX`  
Purpose: Allows users to unassign a task from a person in OfficeConnect.


The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/UnassignActivity.png" width="550" />
<div style="page-break-after: always;"></div>

Below is a sequence diagram that illustrates how a user unassign a tasks from a person in the OfficeConnectModel. 

![AddTaskSequenceDiagram](images/UnassignSequenceDiagram.png)

#### 3.8.1 Implementation

The implementation of this feature is supported by `UnassignTaskCommand`, `UnassignTaskCommandParser`, `ListCommand`, `ListAssignment`
`ListTaskCommand`, `ListAllCommand`, `FindCommand` and `FindTaskCommand`.

#### 3.8.2 Design Considerations

**Aspect: Unassigning a task from a person**

- **Alternative 1 (current choice):** Unassign tasks using the index of the person and the index of the task.
    - Pros: Easier for users to locate the task and person they want to unassign, especially if the list of tasks or persons is long.
    - Cons: Requires users to first obtain the index of the task and person by listing them, which might increase the steps required for the user.

- **Alternative 2:** Unassign tasks using the task title and person name.
    - Pros: No need to obtain the index of the task and person, which could reduce the steps required for the user.
    - Cons: Task titles and person names might be long, making it more difficult for users to input the command. There could also be issues with names that are not unique.

### 3.9 Listing all Tasks
Syntax: `listt`  
Purpose: Displays all tasks stored in OfficeConnect.

#### 3.9.1 Implementation
The implementation of this feature is supported by `OfficeConnectModel`.
Below is a sequence diagram that illustrates how a user can see all tasks stored in OfficeConnect.

![ListTaskSequenceDiagram](images/ListTaskSequenceDiagram.png)

### 3.10 Filtering Persons according to tag
Syntax: `filterp tag/TAG`  
Purpose: Allows users to find all persons with the specified tag.

#### 3.10.1 Implementation
The implementation of this feature is supported by `FilterCommand` and `FilterCommandParser`.
Below is an activity diagram that illustrates how a user finds all persons with the specified tag.

![FilterTaskActivityDiagram](images/FilterTaskActivityDiagram.png)
<div style="page-break-after: always;"></div>

#### 3.10.2 Design Considerations
**Aspect: Number of tags that users can input**

* **Alternative 1 (current choice):** Only one tag is allowed.
  * Pros: Encourage users to be specific in the tag they are searching for. Easier implementation.
  * Cons: Users will have to use the command as many times as the number of tags they want to search.

* **Alternative 2:** Allow multiple tags
  * Pros: Allows users to search for more than one tag at once, which increases convenience and flexibility.
  * Cons: Could produce many search results which may clutter up the GUI (as opposed to searching for only one task). More prone to bugs.

### 3.11 Quickstart Window
Syntax: `quickstart`  
Purpose: Allows new users to better understand how OfficeConnect works.

#### 3.11.1 Implementation
Similar to Help Window in section 3.7, this window is maintained by `QuickstartWindow` class, which also maintains 
similarly named `fxml` and `css` to display the relevant quickstart information.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 4. Planned Enhancements

### 4.1 Support for overseas domains
#### 4.1.1 Description
As we slowly improve OfficeConnect to provide support for managers and companies outside of Singapore, we plan to include additional support to allow for usage of OfficeConnect in other countries. This includes support for phone numbers with area codes, dashes, and the + sign which indicates which country the number is from.

### 4.2 Shortened or customisable keywords that maps to the same command
#### 4.2.1 Description
The current chosen keywords can be considered lengthy or verbose for some users. While this does not pose any problems
to our target users who are generally fast typists, we plan to include alternative shortened keywords or the ability to
customise keywords to improve the user experience.

#### 4.2.2 Consideration
The initial commands were very short and consisted of abbreviations like "lu", "la", "ap", and "ap". However, these
abbreviations can be confusing for new users as they do not provide any information about what the command does which 
would result in a constant need to refer to the User Guide for assistance. Likewise, this requires the user to put 
in extra effort to learn and memorize the commands.

<div style="page-break-after: always;"></div>

### 4.3 Improving data processing
#### 4.3.1 Description
Our current data structure for storing input data in the application is Java's observable filter list. While this structure has performed well for smaller volumes of data, we have observed that it is not suitable for larger datasets, particularly when updating the user interface. As a result, we are exploring the possibility of switching to a more efficient data structure that can better handle large volumes of data and process only the necessary portion of it on the screen using techniques such as data buffering and paging. This proposed change is expected to significantly improve the overall performance of our application, leading to a much-improved user experience.

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 5. Documentation, logging, testing, configuration, dev-ops

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------
## 6. Appendix: Requirements

### 6.1 Product scope

**Target user profile**:
* Holds a managerial role
* Has a need to manage a significant number of subordinates
* Has a need to assign large number of tasks to subordinates
* Prefer desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**:

* Manage tasks and contacts faster than a typical mouse/GUI driven app
* Able to view all upcoming tasks to be completed at one glance
* Allows efficient delegation of tasks to subordinates in an organised and centralised manner
<div style="page-break-after: always;"></div>

### 6.2 User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​        | I want to …​                                        | So that I can…​                                                             |
|----------|----------------|-----------------------------------------------------|-----------------------------------------------------------------------------|
| `* *`    | new user       | see a brief and simple guide                        | quick-start the application without reading long documentations             |
| `* * *`  | forgetful user | see usage instructions                              | refer to instructions when I forget how to use the App                      |
| `* * *`  | manager        | add tasks                                           |                                                                             |
| `* * *`  | manager        | edit tasks                                          | keep the task updated with the most updated information                     |
| `* * *`  | manager        | delete tasks                                        | remove tasks that I no longer need                                          |
| `* * *`  | manager        | mark tasks as done and undone                       | keep track of tasks that are completed                                      |
| `* * *`  | manager        | find tasks assigned to specific subordinate         | better manage my subordinates workload                                      |
| `* * *`  | manager        | check all ongoing tasks available                   | better delegate my tasks                                                    |
| `* * *`  | manager        | assign tasks to a subordinate                       | keep track of which subordinate is in charge of which task                  |
| `* * *`  | manager        | unassign tasks from a subordinate                   | assign this task to other subordinates                                      |
| `* * *`  | manager        | add a subordinate                                   |                                                                             |
| `* * *`  | manager        | delete a subordinate                                | remove subordinates that I no longer need                                   |
| `* * *`  | manager        | find a subordinate by name                          | locate details of a subordinate without having to go through the entire list |
| `* * *`  | manager        | filter subordinates based on their department (tag) | locate subordinates based on their department (tag)                         |
| `* * *`  | manager        | view assigned tasks and persons                     | have an overview of tasks and persons that are assigned                     |
| `* * *`  | manager        | view unassigned tasks and persons                   | have an overview of tasks and persons that are not assigned                 |


### 6.3 Use cases

<div markdown="span" class="alert alert-info">

:information_source: **Note:** For all use cases below, the **System** is the `OfficeConnect` and the
**Actor** is the `user`, unless specified otherwise.

</div>

---
#### 6.3.1 Use case 1: Add a Person

**Main Success Scenario (MSS):**

1. User requests to add a person.

2. OfficeConnect adds the person to the contact list.

3. OfficeConnect informs user has been successfully added.

   Use case ends.

**Extensions**

* 1a. User enters incomplete or invalid data.

    * 1a1. OfficeConnect shows an error message.

      Use case ends.

---
<div style="page-break-after: always;"></div>
#### 6.3.2 Use case 2: Delete a Person

**Main Success Scenario (MSS):**

1. User requests to list persons.

2. OfficeConnect shows a list of persons.

3. User requests to delete a specific person in the list.

4. OfficeConnect deletes the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

---
#### 6.3.3 Use case 3: Delete a Task

**Main Success Scenario (MSS):**

1. User requests to list tasks.

2. OfficeConnect shows a list of tasks.

3. User requests to delete a specific task in the list.

4. OfficeConnect deletes the task.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

---
#### 6.3.4 Use case 4: Assign a Task to a Person

**Main Success Scenario (MSS):**

1. User requests to list all persons.

2. OfficeConnect shows a list of persons.

3. User requests to list all tasks.

4. OfficeConnect shows a list of tasks.

5. User requests to assign a specific task to a specific person.

6. OfficeConnect assigns the task to the person.

   Use case ends.

**Extensions**

* 1a. User requests to find persons according to their name.
  
  Use case resumes at step 2.

* 1b. User requests to list all persons and tasks at the same time.

  * 1b1. OfficeConnect shows a list of tasks and persons.
  
    Use case resumes at step 5.

* 2a. The list is empty.

  Use case ends.

* 3a. User requests to find tasks according to their title.

  User case resumes at step 4. 

* 4a. The list is empty.

  Use case ends.

* 5a. The given index is invalid.

    * 5a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

---
#### 6.3.5 Use case 5: Remove assignment of Task from a Person

**Main Success Scenario (MSS):**

1. User requests to list persons.

2. OfficeConnect shows a list of persons.

3. User requests to list tasks.

4. OfficeConnect shows a list of tasks.

5. User requests to unassign a specific task from a specific person.

6. OfficeConnect unassign the task from the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 4a. The list is empty.

  Use case ends.

* 5a. The given index is invalid.

    * 5a1. OfficeConnect shows an error message.

      Use case resumes at step 2.


---

#### 6.3.6 Use case 6: Consulting Help Window

**Main Success Scenario (MSS):**

1. User requests for help.

2. OfficeConnect opens help interface with dedicated help instructions.

3. User navigates to command he wishes to get help on.

4. OfficeConnect presents information on said command to user.

5. User reads closes window after retrieving required info.

   Use case ends.

**Extensions**

* 4a. User requests for more info on command.

    * 4a1. Help interface provides link to user guide.

    * 4a2. User retrieves user guide providing more detail on command.

      Use case resumes at step 5.

---
#### 6.3.7 Use case 7: Add a Task

**Main Success Scenario (MSS):**

1. User requests to add a task.

2. OfficeConnect adds the task to the task list.

3. OfficeConnect informs user has been successfully added.

   Use case ends.

**Extensions**

* 1a. User enters incomplete or invalid data.

    * 1a1. OfficeConnect shows an error message.

  Use case ends.

---
#### 6.3.8 Use case 8: List all Tasks

  **Main Success Scenario (MSS):**

1. User requests for a list of all tasks.

2. OfficeConnect displays all tasks stored.

  Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

---
#### 6.3.9 Use case 9: Mark a Task

  **Main Success Scenario (MSS):**

1. User requests for a [list of all tasks](#638-use-case-8-list-all-tasks).

2. OfficeConnect displays all tasks stored.

3. User requests to mark a specific task.

4. OfficeConnect marks the task as completed.

  Use case ends.

**Extensions**

* 1a. User requests to find tasks according to their titles.

  Use case resumes at step 2.

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

  Use case resumes at step 2.

* 3b. The task at the given index is already marked as completed.

    * 3b1. OfficeConnect shows an error message.

  Use case ends.

---
#### 6.3.10 Use case 10: Unmark a Task

  **Main Success Scenario (MSS):**

1. User requests for a [list of all tasks](#638-use-case-8-list-all-tasks).

2. OfficeConnect displays all tasks stored.

3. User requests to unmark a specific task.

4. OfficeConnect unmarks the task as uncompleted.

  Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. OfficeConnect shows an error message.

      Use case resumes at step 2.

* 3b. The task at the given index is already marked as uncompleted.

    * 3b1. OfficeConnect shows an error message.

      Use case ends.

---
#### 6.3.11 Use case 11: Find Tasks assigned to an individual

**Main Success Scenario (MSS):**

1. User requests for the list of tasks assigned to a specific person.

2. OfficeConnect displays all the tasks assigned to the person.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

---
#### 6.3.12 Use case 12: Find the group of individuals assigned to a Task

**Main Success Scenario (MSS):**

1. User requests for the list of persons assigned to a specific task.

2. OfficeConnect displays all the individuals assigned to the task.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

---
#### 6.3.13 Use case 13: Filter Persons based on tag

**Main Success Scenario (MSS):**

1. User requests to filter persons based on a specific tag

2. OfficeConnect displays all persons assigned to the tag

   Use case ends.

**Extensions**

* 1a. The tag is invalid
  
  * 1a1. OfficeConnect throws an error.

    Use case ends.
  
---
#### 6.3.14 Use case 14: View all assigned Persons and Tasks

**Main Success Scenario (MSS):**

1. User requests to view all assigned persons and tasks.

2. System displays a list of all assigned persons and tasks.

   Use case ends.

---
#### 6.3.15 Use case 15: View assigned Persons

**Main Success Scenario (MSS):**

1. User requests to view assigned persons.

2. System displays a list of assigned persons.

   Use case ends.

---
#### 6.3.16 Use case 16: View assigned Tasks

**Main Success Scenario (MSS):**

1. User requests to view assigned tasks.

2. System displays a list of assigned tasks.

   Use case ends.

---
#### 6.3.17 Use case 17: View a Person's details

**Main Success Scenario (MSS):**

1. User requests to view a person's details by specifying their index.

2. System displays the details of the specified person.

   Use case ends.

---
#### 6.3.18 Use case 18: View a Task's details

**Main Success Scenario (MSS):**

1. User requests to view a task's details by specifying its index.

2. System displays the details of the specified task.

   Use case ends.

---
#### 6.3.19 Use case 19: View all unassigned Persons and Tasks

**Main Success Scenario (MSS):**

1. User requests to view all unassigned persons and tasks.

2. System displays a list of all unassigned persons and tasks.

   Use case ends.

---
<div style="page-break-after: always;"></div>

#### 6.3.20 Use case 20: View unassigned Persons

**Main Success Scenario (MSS):**

1. User requests to view unassigned persons.

2. System displays a list of unassigned persons.

   Use case ends.

---
#### 6.3.21 Use case 21: View unassigned Tasks

**Main Success Scenario (MSS):**

1. User requests to view unassigned tasks.

2. System displays a list of unassigned tasks.

   Use case ends.

---
#### 6.3.22 Use case 22: Edit a Task

**Main Success Scenario (MSS):**

1. User requests to [list all tasks](#638-use-case-8-list-all-tasks).

2. OfficeConnect displays all tasks stored.

3. User requests to edit a specific task.

4. OfficeConnect edits the task and display editted task.

   Use case ends.

**Extensions**

* 1a. User requests to find tasks according to their titles.

  Use case resumes at step 2.

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

  * 3a1. OfficeConnect shows an error message.

    Use case resumes at step 2.

* 3b. The deadline provided is after the date created.

  * 3b1. OfficeConnect shows an error message.

    Use case ends.


---
### 6.4 Non-Functional Requirements

1. Performance: The system shall respond to user input within 0.1 seconds. 
2. Performance: The system should be able to hold up to 1000 entries/inputs, whichever is bigger, without a noticeable sluggishness in performance.
3. Compatibility: The system shall be compatible to operating systems with java 11 runtime (e.g. Windows, MacOS, Linux). For MacOS users specifically, **OpenJDK Runtime Environment Zulu11.60+19-CA (build 11.0.17+8-LTS)** is required.
4. Usability: The system shall have a user interface that is intuitive and easy to use, with a learning curve of no more
   than 2 hours for a new user.
5. Accessibility: The system shall be operable even without an internet connection.

<div style="page-break-after: always;"></div>
### 6.5 Glossary

#### *A*
  - All
    - All task and person.
  - Assign
    - Assign a task to a person.

#### *M*
  -  Mark
    - Mark a task as completed.
  -  Mainstream OS
    - Windows, Linux, Unix, OS-X

#### *P*
  - P
    - A person.
  - PI
    - View a person's details.

#### *Q*
  - Quickstart: 
    - Launch the application with sample data.

#### *T*
  - T
    - A Task.
  - TI
    - View a task's details.

#### *U*
  - Unassign
    - Unassign a task from a person.
  - Unmark
    - Unmark a completed task.

#### *V*
  - View
    - A term used in the app to refer to the action of accessing and reviewing detailed information about records, such as person details or task details.


--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## 7. Appendix: Instructions for manual testing

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting
point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### 7.1 Launch

1. Initial launch

   1. Download the jar file and copy into an empty folder.
   2. Double-click the jar file <br>
      Expected: Shows the GUI with a set of sample contacts and sample tasks. The window size may not be
      optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   2. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.


### 7.2 Deleting a Person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `listp` command. Multiple persons in the list.
   2. Test case: `deletep 1`<br>
      Expected: First person is deleted from the list. Details of the deleted person shown in the feedback message.
   3. Test case: `deletep 0`<br>
      Expected: No person is deleted. Error details shown in the feedback message.
   4. Other incorrect delete commands to try: `deletep`, `deletep x` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### 7.3 Assigning a Task to a Person

1. Assigning a task to a person while all tasks and persons are being shown

   1. Prerequisites: List all persons and tasks using the `listall` command. Multiple tasks and persons
      in the list.
   2. Test case: `assign pi/1 ti/1`<br>
      Expected: Details of the assigned person and task shown in the feedback message. The task assigned will
      be shown when after using `findp NAME`, where `NAME` is the full name of the first person in the list. <br>
      * Follow-up test case: `listall`, followed by `assign pi/1 ti/1`<br>
        Expected: Error details shown in feedback message indicating that the first person has already been 
        assigned to the first task.
   3. Test case: `assign pi/1`<br>
      Expected: No tasks or persons are assigned. Error details shown in feedback message.
   4. Other incorrect assign commands to try: `assign`, `assign 1 1`, `assign pi/x ti/y` (where x or y is
      larger than the number of persons or tasks contained in the list displayed)<br>
      Expected: similar to previous.
      
### 7.4 Unassigning a Task from a Person

1. Unassigning a task from a person while all tasks and persons are being shown

   1. Prerequisites: List all persons and tasks using the `listall` command. Multiple tasks and persons in the list. First person must already be assigned to the first task.
   2. Test case: `unassign pi/1 ti/1`<br>
      Expected: Details of the unassigned person and task shown in the feedback message. The task should no longer be assigned when using the `findp NAME` command, where `NAME` is the full name of the first person in the list.
      * Follow-up test case: `listall`, followed by `unassign pi/1 ti/1`<br>
        Expected: Error details shown in feedback message indicating that the first person has not been assigned to the first task.
   3. Test case: `unassign pi/1`<br>
      Expected: No tasks or persons are unassigned. Error details shown in feedback message.
   4. Other incorrect `unassign` commands to try: `unassign`, `unassign 1 1`, `unassign pi/x ti/y` (where `x` or `y` is larger than the number of persons or tasks contained in the list displayed).<br>
      Expected: Similar to previous.

     
### 7.5 Marking a Task as Completed

1. Marking a task that has not been assigned to any persons as completed

   1. Prerequisites: List all tasks using the `listt` command. At least one task in the list. First task
      has not been assigned to any person, which can be verified by using `findt TITLE` (where `TITLE` is
      the title of the first task) which should display an empty list. First task is currently not marked as
      completed (red cross appears below task).
   2. Test case: `mark 1`<br>
      Expected: Details of the task that is marked shown in the feedback message. A green tick will appear
      under the task.
   3. Test case: `mark 0`<br>
      Expected: No tasks are marked. Error details shown in feedback message.
   4. Other incorrect mark commands to try: `mark`, `mark x` (where x is larger than the list size).
      Expected: Similar to previous.

2. Marking a task that has been assigned to one or more persons as completed

   1. Prerequisites: Find persons assigned to a task using the `findt TITLE` (where `TITLE` is the title of 
      a task). At least one person in the list. Task is not marked as completed (red cross appears below task).
   2. Test case: `mark 1`<br>
      Expected: Details of the task that is marked shown in the feedback message. A green tick will appear under the
      task. The progress indicator of the persons assigned to this task will be updated.

### 7.6 Unmarking a Task as not Completed

1. Unmarking a task that has not been assigned to any persons as not completed yet

   1. Prerequisites: List all tasks using the `listt` command. At least one task in the list. First task
      has not been assigned to any person, which can be verified by using `findt TITLE` (where `TITLE` is
      the title of the first task) which should display an empty list. First task is currently marked as
      completed (green tick appears below task).
   2. Test case: `ummark 1`<br>
      Expected: Details of the task that is unmarked shown in the feedback message. A red cross will appear
      under the task.
   3. Test case: `unmark 0`<br>
      Expected: No tasks are unmarked. Error details shown in feedback message.
   4. Other incorrect unmark commands to try: `unmark`, `unmark x` (where x is larger than the list size).
      Expected: Similar to previous.

2. Unmarking a task that has been assigned to one or more persons as not completed yet

   1. Prerequisites: Find persons assigned to a task using the `findt TITLE` (where `TITLE` is the title of
      a task). At least one person in the list. Task is marked as completed (red cross appears below task).
   2. Test case: `unmark 1`<br>
      Expected: Details of the task that is unmarked shown in the feedback message. A red cross will appear under the
      task. The progress indicator of the persons assigned to this task will be updated.


### 7.7 Adding a Task

1. Adding a task with no deadline
   1. Prerequisites: There are no tasks with the same title present. 
   2. Test case: `addt t/Project X c/Complete slides for Mr X st/false`<br>
      Expected: Details of the task that is added is shown in the feedback message. A new task will appear in the displayed task list.
   3. Test case: `addt t/Project X c/Complete slides for Mr X st/hello`<br>
      Expected: No tasks are added. Error details shown in feedback message.
   4. Other incorrect add task commands to try: `addt t/Project X c/Complete slides for Mr X st/INPUT`, where INPUT is anything that is not true/false.
      Expected: Similar to previous.

2. Adding a task with deadline
   1. Prerequisites: There are no tasks with the same title present.
   2. Test case: `addt t/Project X c/Complete slides for Mr X st/false dl/2024-01-01`<br>
      Expected: Details of the task that is added is shown in the feedback message. A new task will appear in the taskbar of the GUI.
   3. Test case: `addt t/Project X c/Complete slides for Mr X st/false dl/2024-01-01 02:05:00`<br>
      Expected: Details of the task that is added is shown in the feedback message. A new task will appear in the taskbar of the GUI.
   4. Test case: `addt t/Project X c/Complete slides for Mr X st/false dl/2024-01-01 02:05:0`<br>
      Expected: No tasks are added. Error details shown in feedback message.
   5. Other incorrect add task commands to try: `addt t/Project X c/Complete slides for Mr X st/false dl/2023-01-01 005:0`, or any input with incorrect datetime format (datetime format should be YYYY-MM-DD HH-MM-SS, where HH-MM-SS is optional). Deadlines that are set in the past (before the current date and time) will also throw an error. <br>
      Expected: Similar to previous

### 7.8 Deleting a Task

1. Deleting a task while all tasks are being shown.
   1. Prerequisites: List all tasks using the `listt` command. At least 1 task in the list.
   2. Test case: `deletet 1`<br>
      Expected: First task is deleted from the list. Details of the deleted task shown in the feedback message.
   3. Test case: `deletet 0`<br>
      Expected: No task is deleted. Error details shown in the feedback message.
   4. Other incorrect delete task commands to try: `deletet`, `deletet x` (where x is larger than the largest index in displayed task list). <br>
      Expected: Similar to previous.


### 7.9 Listing all Tasks

1. Listing all tasks.
   1. Prerequisites: None
   2. Test case: `listt`
      <br>Expected: All tasks stored in OfficeConnect are listed. "Listed all tasks" shown in feedback message.


### 7.10 Filtering Persons by Tag

1. There are persons in OfficeConnect with the specified tag.
   1. Prerequisites: Only one tag can be specified. 
   2. Test case: `filterp tag/Logistics` <br>
      Expected: All persons with the tag are displayed. "Filtered all persons with the tag: [tag]" shown in feedback message.
   3. Test case: `filterp ` <br>
      Expected: No changes in displayed person list. Error details shown in feedback message.
   4. Other incorrect filter persons commands to try: `filterp tag/`). <br>
      Expected: Similar to previous.

2. There are no persons in OfficeConnect with the specified tag.
   1. Prerequisites: Only one tag can be specified.
   2. Test case: `filterp tag/Logistics` <br>
      Expected: No persons are displayed. "There are no persons with the tag: [tag]" shown in feedback message.
   3. Test case: `filterp ` <br>
      Expected: No changes in displayed person list. Error details shown in feedback message.
   4. Other incorrect filter persons commands to try: `filterp tag/`). <br>
      Expected: Similar to previous.


### 7.11 Viewing Assigned and Unassigned Tasks and Persons

  1. There are assigned tasks and persons in OfficeConnect.
     1. Prerequisites: There are tasks and persons with assignments.
     2. Test case: `viewassignedall` <br>
        Expected: All assigned tasks and persons are displayed. "Listed all assigned person(s) and task(s)" shown in feedback message.
     3. Test case: `viewassignedp` <br>
        Expected: All assigned persons are displayed. "Listed all assigned person(s)" shown in feedback message.
     4. Test case: `viewassignedt` <br>
        Expected: All assigned tasks are displayed. "Listed all assigned task(s)" shown in feedback message.

  2. There are unassigned tasks and persons in OfficeConnect.
     1. Prerequisites: There are tasks and persons without assignments.
     2. Test case: `viewunassignedall` <br>
        Expected: All unassigned tasks and persons are displayed. "Listed all unassigned person(s) and task(s)" shown in feedback message.
     3. Test case: `viewunassignedp` <br>
        Expected: All unassigned persons are displayed. "Listed all unassigned person(s)" shown in feedback message.
     4. Test case: `viewunassignedt` <br>
        Expected: All unassigned tasks are displayed. "Listed all unassigned task(s)" shown in feedback message.


### 7.12 Viewing Task and Person details

  1. Task and person details are available in OfficeConnect.
     1. Prerequisites: The specified task and person indices are valid.
     2. Test case: `ti 2` <br>
        Expected: Task details are displayed.
     3. Test case: `pi 2` <br>
        Expected: Person details are displayed.

  2. Task or person details are not available in OfficeConnect.
     1. Prerequisites: The specified task or person indices are invalid.
     2. Test case: `ti 7` <br>
        Expected: No task details are displayed, error message is displayed instead.
     3. Test case: `pi 7` <br>
        Expected: No person details are displayed, error message is displayed instead.

### 7.13 Editing Task 

  1. Task to be edited is available in OfficeConnect
     1. Prerequisites: There is at least 1 task in the list.
     2. Test case: `editt 1 t/Project X c/Complete slides st/true dl/2024-12-19`
     <br>Expected: Task details are displayed. 
     3. Test case: `editt 0`
     <br>Expected: "The task index provided is invalid" shown in feedback message.

### 7.14 Finding Persons by Name

  1. There are persons in OfficeConnect with the specified name.
     1. Prerequisites: Only one person can be specified.
     2. Test case: `findp NAME` <br>
        Expected: All persons whose name contains given input are displayed. "[number] person found" shown in feedback message.
     3. Test case: `findp` <br>
        Expected: All persons and tasks in OfficeConnect are displayed. "Listed all persons and tasks" shown in feedback message.
     4. Other incorrect find persons commands to try: `findp !@#`)<br>
        Expected: No persons are displayed. "No such person found" shown in feedback message.
  
  2. There are no persons in OfficeConnect with the specified name.
     1. Prerequisites: Only one person can be specified.
     2. Test case: `findp NAME` <br>
        Expected: No persons are displayed. "No such person found" shown in feedback message.
     3. Test case: `findp` <br>
        Expected: All persons and tasks in OfficeConnect are displayed. "Listed all persons and tasks" shown in feedback message.
     4. Other incorrect find persons commands to try: `findp !@#`)<br>
        Expected: No persons are displayed. "No such person found" shown in feedback message.

### 7.15 Finding Tasks by Title

  1. There are tasks in OfficeConnect with the specified title.
     1. Prerequisites: Only one title can be specified.
     2. Test case: `findt TITLE` <br>
        Expected: All tasks in which title contains given input are displayed. "[number] task/s found" shown in feedback message.
     3. Test case: `findt` <br>
        Expected: All persons and tasks in OfficeConnect are displayed. "Listed all persons and tasks" shown in feedback message.
     4. Other incorrect find tasks commands to try: `findt !@#`)<br>
        Expected: No tasks are displayed. "No such task found" shown in feedback message.
  
  2. There are no tasks in OfficeConnect with the specified title.
     1. Prerequisites: Only one title can be specified.
     2. Test case: `findt TITLE` <br>
        Expected: No tasks are displayed. "No such task found" shown in feedback message.
     3. Test case: `findt` <br>
        Expected: All persons and tasks in OfficeConnect are displayed. "Listed all persons and tasks" shown in feedback message.
     4.  Other incorrect find tasks commands to try: `findt !@#`)<br>
         Expected: No tasks are displayed. "No such task found" shown in feedback message.

--------------------------------------------------------------------------------------------------------------------
## 8. Appendix: Effort
OfficeConnect is a project built upon AB3, which was built out of the SE-EDU initiative. Our group has been actively working on OfficeConnect for the past 9 weeks, holding meetings to stay on task ever since the group has been formed, **meeting all weekly checkpoints punctually.** <br> <br>
Having written more than **12,000 of LOC cumulatively, coupled with appropriate, concise documentation**, we also have endeavored to abide by the code quality and design patterns that were taught throughout the span of CS2103, and shown in AB3. We have also strived to maintain good code coverage, applying what was taught in the later weeks, resulting in a **code coverage of near 70% in Codecov.**

In the section, we will detail some of the hurdles that we faced through the iterations of our tP, both design and technical wise.

<div style="page-break-after: always;"></div>

### 8.1 Design Challenges
Although OfficeConnect is a brownfield project building on the AB3, it was necessary to go over the AB3, identify weak points within AB3's interface, and reflect on which aspects of the interface needed a re-implementation. Some big design changes includes:
* Inclusion of a quickstart guide: After going through our user stories and reflecting on how the user may experience OfficeConnect, we felt it may be more appropriate to include a guide that gives users a brief rundown on the basics of the app, something that was not considered in AB3.
* Modifications for the help guide. Instead of just a URL link to the online UserGuide, we felt there was a need for a more "immediate" and quick reference guide that should be available, especially in settings where internet connection may not be available (which is also one of our NFRs.) By including a more detailed but sufficiently concise guide, we aim to ease the user into OfficeConnect more smoothly.
* Integrating OfficeConnectModel with Model. We wanted to change AB3 to include tasks, but we did not want to modify the old Model component as it was specialised for persons. Hence, we decided to create a new OfficeConnectModel that helps us store tasks. Initially, they existed as two separate entities, which was easier to implement, but was of considerably poorer design as we realised that Model could be contained in OfficeConnectModel. Hence, we decided to include Model into OfficeConenctModel. However, this required changing a lot of test cases, which was very tedious and time consuming.

### 8.2 Technical Challenges
* As AB3 is quite a big project, it was quite difficult for us to understand the structure and details behind the code initially. To overcome this, we decided to start project meetings on our project early to allow us more time to understand AB3's implementation, and also help each other clarify our doubts regarding AB3. This understanding helped reduce the learning curve when trying to implement OfficeConnect's new features, as some of the features had a similar concept and understanding how it was implemented in AB3 significantly lowered the difficulty level when implementing those methods.

<a href="#top">Back to top</a>
