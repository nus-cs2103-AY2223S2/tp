---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressBook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The * * *Architecture Diagram* * * given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressBook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressBook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressBook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `EmployeeListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressBook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressBook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Employee`, `Department` or `Leave` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](./images/LogicClassDiagram.png)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `SudoHRParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add an employee).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `SudoHRParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `SudoHRParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](./images/ModelClassDiagram.png)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the system data i.e., all `Employee`, `Department` and `Leave` objects (which are contained in `UniqueEmployeeList`, `UniqueDepartmentList` and `UniqueLeaveList` objects respectively).
* stores the currently 'selected' `Employee`, `Department` and `Leave` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Employee>`, `ObservableList<Department>` or `ObservableList<Leave>`, that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](./images/StorageClassDiagram.png)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both SudoHR data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `SudoHRStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.SudoHR.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Employee-related features

The 'Employee' object represents an Employee in the company. They are all stored in a `UniqueEmployeeList`.

The attributes of an Employee are:
* `Id`: The employee id, which is their unique identifier in the company.
* `Name`: The name of the employee.
* `Email`: The email of the employee, which should be unique.
* `Phone`: The phone of the employee, which should be unique.
* `Address`: The address of the employee.
* `Tags`: The tags assigned to the employee.


### Department-related features

The `Department` object represents a department in the company. They are all stored in a `UniqueDepartmentList`.



The attributes of a department are:
* `name`: The name of the department, which is also the unique identifier for a department.
* `employees`: The employees in a department, the list must not contain duplicate employees. It is implemented by reusing the `UniqueEmployeeList` datatype.

### Adding a department

![AddDepartmentCommand](./images/commands/department/AddDepartmentSequenceDiagram.png)

The call stack is the same as a typical command during parsing. It requires the department name prefix: `n/`.

Upon execution, it first checks if the department contains the employee being added. This is done to prevent the addition of duplicates. After the check is done, the model adds the employee to the department. 

After that, it returns the command result.

### Listing all departments

![ListDepartmentCommand](./images/commands/department/ListDepartmentSequenceDiagram.png)

The call stack is the same as a typical command except that it has no specified parser. Instead, `SudoHrParser` directly returns the command containing the required predicate.

Upon execution, it updates the department view.

After that, it returns the command result.

### Event-related features

The `Leave` object represents a leave date in the company. They are all stored in a `UniqueLeaveList`.

The attributes of a leave are:
* `date`: The date of the leave, which is also the unique identifier for a leave
* `employees`: The employees who applied for this leave, the list must not contain duplicate employees. It is implemented by reusing the `UniqueEmployeeList` datatype.

#### Design considerations:

### Employee
An important design consideration to note for Employee is the multiple different fields that qualify as a primary key (unique identity).

An employee is identified by his ID field, and this field is used to get an employee object.

However, there are other fields to guard against duplication, specifically email and phone number fields. 
For instance, two employees should not share email field or phone number as those two fields are known to be unique.

### Departments

### Leaves

### Cascading employee updates and deletion to department and leave
An important functionality is to ensure updates to employee is cascaded down to department-level and leave-level because
each department and leave has its own list of employees. This issue becomes more prominent during loading of storage files 
where employee objects are separately created for department's and leave's employee lists. 
Hence, any modification to an employee after SudoHR is initialized from storage needs to be cascaded down to modify the equivalent employee object.


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

**Target user profile**: HR in company who has a need to manage a significant number of employees

**Value proposition**: data management for employees faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                | I want to …​                                                   | So that I can…​                                                            |
|----------|----------------------------------------|----------------------------------------------------------------|----------------------------------------------------------------------------|
| `* * *`  | new user                               | see usage instructions                                         | refer to instructions when I forget how to use the App                     |
| `* * *`  | HR personnel                           | add a new employee                                             | ensure consolidation of information when an employee is hired              |
| `* * *`  | HR personnel                           | edit a new employee                                            | ensure consolidation of information when an employee has new details       |
| `* * *`  | HR personnel                           | delete an employee                                             | ensure consolidation of information when an employee left the company      |
| `* * *`  | HR personnel                           | find an employee by name                                       | locate details of employees without having to go through the entire list   |
| `* *`    | HR personnel                           | hide private contact details                                   | minimize chance of someone else seeing them by accident                    |
| `* * *`  | HR personnel                           | add an employee’s leave to SudoHR                              | ensure consolidation of information                                        |
| `* * *`  | HR personnel                           | remove an employee’s leave for SudoHR                          | ensure consolidation of information                                        |
| `* * *`  | HR personnel                           | view all leaves an employee has applied for                    | access an employee's availability easily                                   |
| `* * *`  | HR personnel                           | view all employees on leave today                              | know today's headcount                                                     |
| `* * *`  | HR personnel                           | view all leaves applied for a given day                        | better plan company events                                                 |
| `* * *`  | HR personnel                           | view all leaves applied for a given day for a given department | better plan depeartment events                                             |
| `* * *`  | HR personnel                           | add a department                                               | ensure consolidation of information when a new department is formed        |
| `* * *`  | HR personnel                           | edit a department                                              | ensure consolidation of information when a department's detail is changed  |
| `* * *`  | HR personnel                           | delete a department                                            | ensure consolidation of information when a department is disbanded         |
| `* * *`  | HR personnel                           | find a department by name                                      | locate details of departments without having to go through the entire list |
| `* * *`  | HR personnel                           | add an employee to a department                                | ensure consolidation of information when a department has a new employee   |
| `* * *`  | HR personnel                           | remove an employee from a department                           | ensure consolidation of information when an employee leaves a department   |
| `* * *`  | HR personnel                           | list all departments an employee is in                         |                                                                            |
| `* * *`  | HR personnel                           | list all employees in a department                             | view manpower size of a department                                         |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is  `SudoHR` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a new employee**

**MSS**

1.  User requests to list employees
2.  SudoHR shows a list of employees
3.  User requests to delete a specific employee in the list
4.  SudoHR deletes the employee

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SudoHR shows an error message.

      Use case resumes at step 2.

**Use case: UC1 - Create a department**

**MSS:**
1. User requests to create a department.
2. SudoHR creates the department.

    Use case ends.

**Extensions**

* 1a. The given argument is invalid.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 1.

**Use case: UC2 - Add employee to department**
**MSS:**
1. User lists department
2. User requests to add employee to the department.
3. SudoHR adds employee to the department.
4. Use case ends.

**Extensions**

* 1a. The given argument is invalid.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 2.

**Use case: UC3 - List Employees in department**

**MSS:**
1. User requests to list the employees in a specified department. 
2. SudoHr lists all the employees in the department.

   Use case ends.

**Extensions**

* 1a. The given department is invalid.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 1.

**Use case: UC4 - List Employees in a department present on a given day.**

**MSS:**
1. User requests to list all employees in a given department who are present.
2. SudoHR lists the employees.

   Use case ends.

**Extensions**

* 1a. The given department does not exist.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 1.

**Use case: UC5 - Delete a project**

**MSS:**
1. User lists all existing projects.
2. User requests to delete a project.
3. SudoHR deletes the project.

   Use case ends.

**Extensions**

* 1a. The given argument is invalid.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 2.

**Use case: UC6 - List projects**

**MSS:**
1. User lists all existing projects.

   Use case ends.

**Use case: UC7 - Add employee to a project**

**MSS:**
1. User lists all existing projects.
2. User lists all employees.
3. User requests to add employee to a specific project.
4. SudoHR adds employee to the project.

   Use case ends.

**Extensions**

* 1a. The given argument(s) is invalid.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 3.

**Use case: UC8 - Remove employee from a project**

**MSS:**
1. User lists all existing projects.
2. User lists all employees.
3. User requests to remove employee from a specific project.
4. SudoHR removes employee from the project.

   Use case ends.

**Extensions**

* 1a. The given argument(s) is invalid.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 3.

**Use case: UC9 - List all employees doing a project**

**MSS:**
1. User lists all existing projects.
2. User requests to list all employees doing a project.
3. SudoHR lists all the employees doing a project

   Use case ends.

**Extensions**

* 1a. The given argument(s) is invalid.

    * 1a1. SudoHR shows an error message.

      Use case resumes at step 2.

**Use case: UC10 - Add event**
**MSS:**
1. User request to add event with corresponding event title description and date
2. SudoHR adds event.
3. Use case ends.

**Extensions**

* 1a. The given argument has invalid fields.

   * 1a1. SudoHR shows an error message.

     Use case resumes at step 1.

* 1b. The given argument has missing fields.

    * 1b1. SudoHR shows an error message.

      Use case resumes at step 1.




**Use case: UC11 - Delete event**
**MSS:**
1. User request to list event
2. SudoHR shows all events
3. User requests to delete event from SudoHR
4. SudoHR deletes event.
5. Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SudoHR shows an error message.

      Use case resumes at step 2.


**Use case: UC12 - Update event**
**MSS:**
1. User resuest to list event
2. SudoHR shows all events and their details
3. User requests to update event details on SudoHR
4. SudoHR update event.
5. Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. SudoHR shows an error message.

      Use case resumes at step 2.

* 3b. The given argument is empty.

    * 3b1. SudoHR shows an error message.

      Use case resumes at step 2.


**Use case: UC13 - List event**
**MSS:**
1. User requests SudoHR to list all events
2. SudoHR shows all events and their details


**Use case: UC14 - Add employee to event**
**MSS:**
1. User requests to list event
2. SudoHR shows all events and their details
3. User requests to list employees
4. SudoHR shows all employees and their details
5. User requests to add employee to event on SudoHR
6. SudoHR add employee to event.
7. Use case ends.

**Extensions**

* 2a. The event list is empty.

  Use case ends.

* 4a. The employee list is empty.

  Use case ends.

* 5a. The given Employeeindex is invalid.

    * 5a1. SudoHR shows an error message.

      Use case resumes at step 4.

* 5b. The given Eventindex is invalid.

    * 5b1. SudoHR shows an error message.

      Use case resumes at step 4.

**Use case: UC15 - List employees in an event**
**MSS:**
1. User request to list events
2. SudoHR shows all events and their details
3. User requests to list employees added to an event
4. SudoHR shows all employees added to an event and their details
5. Use case ends.

**Extensions**

* 2a. The event list is empty.

  Use case ends.

* 3a. The given Index is invalid.

    * 3a1. SudoHR shows an error message.

      Use case resumes at step 4.

**Use case: UC16 - Delete employee from event**
**MSS:**
1. User requests to __list events(UC6)__
2. SudoHR shows all events and their details
3. User requests to __list employees in a event(UC8)__
4. SudoHR shows all employees added to an event and their details
5. User requests to delete employee to event on SudoHR
6. SudoHR delete employee from event.
7. Use case ends.

**Extensions**

* 2a. The event list is empty.

  Use case ends.

* 4a. The employee list is empty.

  Use case ends.

* 5a. The given Employeeindex is invalid.

    * 5a1. SudoHR shows an error message.

      Use case resumes at step 4.

* 5b. The given Eventindex is invalid.

    * 5b1. SudoHR shows an error message.

      Use case resumes at step 4.


*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 employees without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Comfortable working with CLI.

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

### Deleting an employee

1. Deleting an employee while all employees are being shown

   1. Prerequisites: List all employees using the `list` command. Multiple employees in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No employee is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
