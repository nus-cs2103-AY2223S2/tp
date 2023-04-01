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
1. The command can communicate with the `Model` when it is executed (e.g. to add a tutee).
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

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Field Validation
Each `Tutee` has various fields (i.e. name, phone...). Validation from user input is done at the `Parser` level, but
when loading from a JSON file field validation is performed differently.\
The automatic validation of a tutee field requires the following three elements to be defined,
an example implementation of a tutee field is shown below:
```java
public class Phone {
   // Need at least one constructor with this signature
   public Phone(String value) {
      // ...
   }

   // Message to display to the user if the stored JSON data is invalid for the given field
   public static final String MESSAGE_CONSTRAINT = "Invalid phone number!";

   // Validation method that will return true if the value is valid for the field, false otherwise
   // If your field is named differently this method is named differently too, e.g. isValidRemark
   public static boolean isValidPhone(String value) {
      // ... details
   }
}
```
This automatic validation relies on Java reflection and will raise `RuntimeExceptions` if the automatic validation fails
for reasons other than the user input being invalid. The process is as follows:
1. The JSON file is read
1. The `isValid` method is called with the given input
1. If `isValid` returns false, the `MESSAGE_CONSTRAINT` is displayed to the user
1. Otherwise, the input is passed to the constructor to create an instance of the field

### Attendance Management
#### The Attendance Field
The `Attendance` field uses a hashset internally to store all the dates on which the tutee was present. The field is designed to be
immutable, which means updating a tutee's absence or presence will create a new attendance field instance.

#### Mark and Unmark Command
The mark and unmark commands are implemented similarly: both follow the format of `<mark|unmark> <index> [date]`. The
user's input is parsed by its respective parser (`MarkCommandParser` and `UnmarkCommandParser`) and then those arguments
are passed to the command.\
If the user does not specify a date, then the command will use the default value as returned by `LocalDate.now()`.\
If the command executes successfully, the specified tutee's `attendance` field will be updated accordingly. `mark` will add that
date to the attendance field, thereby marking them as present on that date. Unmark will remove it, thereby marking them as absent on that date.\
If tutee has already been marked present or absent on the specified date, the commands will have no effect.

#### Query Command
The date is represented as an `Optional<LocalDate>`. When the command is executed, if this optional is empty, then the command will return all the dates in which the tutee was present. Otherwise, the command will return whether the tutee was present by calling the
`didAttend()` method on the attendance field.

### \[Proposed\] Undo/redo feature

### Learn\Unlearn feature

The learn\unlearn mechanism is storing the `lessons` in a `Set`, adding\removing a `lesson` is editing this `Set`. It implements the following classes and operations:

* `Lesson`  —  Representing lesson fields, containting a `Set` of lessons .
* `Lesson#learn()`  —  Adds the new lesson to the Set.
* `Lesson#unlearn()` —  Removes a lesson from the Set.
* `LearnCommand`  —  Adds new lesson to the Index specified Tutee.
* `UnlearnCommand`  —  Removes lesson from the Index specified Tutee.


[//]: # (The following sequence diagram shows how the undo operation works:)

[//]: # ()
[//]: # (![UndoSequenceDiagram]&#40;images/UndoSequenceDiagram.png&#41;)

[//]: # ()
[//]: # (<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker &#40;X&#41; but due to a limitation of PlantUML, the lifeline reaches the end of diagram.)

[//]: # ()
[//]: # (</div>)

[//]: # ()
[//]: # (The following activity diagram summarizes what happens when a user executes a new command:)

[//]: # ()
[//]: # (<img src="images/CommitActivityDiagram.png" width="250" />)

#### Design considerations:

**Aspect: How learn & unlearn executes:**

* **Alternative 1 (current choice):** add/remove lesson to the `Set`.
  * Pros: Easy to implement, don't allow duplicates.
  * Cons: Does not show the learning order.

* **Alternative 2:** Combine Attendance and Learning.
  * Pros: Easier for user to keep track of time and lesson.
  * Cons: Harder to implement.

_{more aspects and alternatives to be added}_


### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### Filter feature

#### Filter Implementation

The logic of the filter implementation is found in `FilterCommand` class. The constructor of `FilterCommand` takes in 
a `FilterTuteeDescription` object and creates a `FieldContainsKeywordPredicate` based on the variables that are set in 
`FilterTuteeDescription`.  

`FilterTuteeDescription` encapsulates the fields of a tutee that the user wants to filter. 
The class contains all the fields of a tutee including: `name`, `phone`, `email`, `address`, `subject`, `schedule`, `start time`, `end time`, `tag`of a tutee. 
By default, `name`, `address`, `tags` are empty lists while the rest of the fields are empty strings. 

`FilterCommandParser` will set the appropriate fields in `FilterTuteeDescription` for the
fields that are to be filtered in.  Once `FilterTuteeDescription` has its fields set (e.g. nameToFilter = "alex"), 
`FieldContainsKeywordPredicate` will take in all the variables in `FilterTuteeDescription` and return a `FieldContainsKeywordPredicate` object.

`FieldContainsKeywordPredicate` implements `Predicate` and it overrides the `test` method. It returns true if the 
given field is empty (default) or when the tutee has the field equal to the field provided by the user when using the filter
command. 

Finally, `FilterCommand` will execute which will call the method `model.updateFilteredTuteeList(predicate)` using the 
`FieldContainsKeywordPredicate` object as the predicate. The feature will filter and display the tutees which have fields 
that are equal to what the user has provided. 

#### Design considerations

#### How filter executes

- Alternative 1 (current choice): Use prefix to filter what to search for.
  - Pros: More precise when filtering time (e.g. `filter st/10:30` will only return tutees whose lesson starting time is at
  10:30)
  - Pros: Using prefix can filter tutees more precisely (e.g. `filter s/math sch/monday` will only return tutees whose 
  lessons fall on `monday` **and** taking the `math` subject.) This is more useful as it is harder to find intersections between
  multiple fields than the union of all the fields.
  - Cons: Harder to implement.

- Alternative 2: Extend find feature and filter any field without specifying prefix.
  - Pros: Easier to implement.
  - Cons: Could cause confusion when filtering time (e.g. `filter 10:30` will return all tutees whose lesson start time and
  end time are at 10:30)
  - Cons: The extended find feature will not be as precise in filtering tutees (e.g. `filter math monday`) will return 
  all tutees whose lessons fall on `monday` **or** taking the `math` subject.) This is considered less useful as it is simple
  to find the union of multiple fields compared to finding the intersections. (i.e. users can perform filter for individual fields
  separately if they want to find the union of all of them)
   

### Copy feature

#### Copy Implementation
The copy feature is facilitated by `CopyCommand`. It extends `Command`. The constructor of `CopyCommand` takes in
an `index and an EditPersonDescripter` object and creates a `tutee` based on the variables that are set in
`EditPersonDescripter`. The `CopyCommand` class makes use of the `EditPersonDescripter`in the `EditCommand` class which contains all the fields of a tutee including:
`subject`, `schedule`, `start time`, `end time` of a tutee.
All of the fields are required to be filled in order for the command to make a copy of a tutee with a different lesson and schedule.

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

* has a need to manage a significant number of tutees
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage tutees faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                            | So that I can…​                                         |
|----------|--------------------------------------------|-----------------------------------------|---------------------------------------------------------|
| `* * *`  | tutor user managing students               | add students                            | add students that I am tutoring                         |
| `* * *`  | tutor user  managing students              | delete students                         | remove students that I no longer tutor                  |
| `* * *`  | tutor user managing students               | list students                           | display all students that are tutored by me             |
| `* * *`  | user                                       | save my work locally                    | come back to view or edit it in the future              |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `TMS` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a student**

**MSS:**

1. User is prompted to enter student’s details.
2. Student is added to the list of students.
   Use case ends.

**Use case: Delete a student**

**MSS:**

1. User requests to list students.
2. System shows a list of students.
3. User requests to edit a specific student in the list.
4. System prompts the user to choose an attribute to edit.
5. User chooses an attribute to edit and inputs new information about the student.
   Use case ends.

**Extensions:**

Extensions:
* 2a. The list is empty.
  Use case ends.
* 3a. The given index is invalid.

    * 3a1. System shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1. Should be standalone on Windows, Linux, and OS-X platforms, requiring only Java '11' or above to be installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be usable without an installer.
5. GUI Should work well (i.e., should not cause any resolution-related inconveniences to the user) for
   1. standard screen resolutions 1920x1080 and higher, and,
   2. for screen scales 100% and 125%.
6. GUI should be usable (i.e., all functions can be used even if the user experience is not optimal) for
   1. resolutions 1280x720 and higher, and,
   2. for screen scales 150%.
7. The application should be packed into a JAR file or include jar file and other files into one zip file.
8. The product size should not exceed 100MB.


*{More to be added}*

### Glossary

* **TMS**: Tutee managing system.
* **CLI**: command line interface.
* **GUI**: graphical user interface.
* **JAR**: Java ARchive.


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

### Deleting a tutee

1. Deleting a tutee while all tutees are being shown

   1. Prerequisites: List all tutees using the `list` command. Multiple tutees in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No tutee is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
