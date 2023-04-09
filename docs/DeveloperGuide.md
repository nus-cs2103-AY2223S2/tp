---
layout: page
title: Developer Guide
---

## **Table of Contents**

- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#setting-up-getting-started)
- [Design](#design)
- [Implementation](#implementation)
- [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
- [Appendix: Requirements](#appendix-requirements)
- [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)

---




## **Acknowledgements**

<!-- - {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well} -->

<!-- Mentioned Jackson for serialization -->

---






## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

---






## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-T15-3/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>







### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The **_Architecture Diagram_** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,

- At app launch: Initializes the components in the correct sequence, and connects them up with each other.
- At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Common`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its _API_ in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-T15-3/tp/blob/master/src/main/java/vimification/taskui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The `UI` consists of a `MainScreen` that is made up of parts e.g.`CommandInput`, `TaskDetailPanel`, `TaskListPanel`, `CommandInput` etc. All these, including the `MainScreen`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework but is modeled to mimic after the structure of the `React.js` framework as closely as possible. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainScreen`](https://github.com/AY2223S2-CS2103T-T15-3/tp/blob/master/src/main/java/vimification/taskui/MainScreen.java) is specified in [`MainScreen.fxml`](https://github.com/AY2223S2-CS2103T-T15-3/tp/blob/master/src/main/resources/view/MainScreen.fxml)

The `UI` component,

- communicates with back-end via a single-entry point `Logic` component to exectue user commands.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands and to display the list of tasks.
- updates the UI every time a command is executed.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `VimificationParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with multiple model objects (`TaskList`, `MacroMap`, etc.) when it is executed (e.g. to add a task).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

<!-- The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call. -->

<!-- ![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/> -->

How the parsing works:

- Each command has a dedicated parser, `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) to parse it.
- The main entry point of the parser, `VimificationParser` , combines the command parsers together.
- When called upon to parse a user command, the `VimificationParser` class tries to parse different prefixes. Each prefixes maps to a single `XYZCommandParser` which parses the remaining user input and create a `XYZCommand` object (e.g., `AddCommand`) which the `VimificationParser` returns back as a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `CommandParser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

Compared to the original design of AB3, we decided to split the model components into multiple classes and interfaces with different purposes.

We argue that the original `Model` component from AB3 handles too many responsibilities - which result in high coupling and low cohesion. Therefore, we decided to split the `Model` components into different classes and interfaces, following the (interface segregation principle)[https://en.wikipedia.org/wiki/Interface_segregation_principle].

<!-- **API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java) -->

<!-- <img src="images/ModelClassDiagram.png" width="450" /> -->

The `Model` component,

- stores the data i.e., all `Task` objects.
- stores the currently 'selected' `Task` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Vimification`, which `Person` references. This allows `Vimification` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

- can save both user preference data, task data and macro data in JSON format, and read them back into corresponding objects.
- inherits from both `TaskListStorage`, `MacroMapStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`).

### Common classes

Classes used by multiple components are in the `vimificationbook.common` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

<!-- ### Architechture modification to AB3

Vimification uses the **Model–view–controller (MVC)** design pattern. One detail we observed is that the `Model` uses `ObservableList` to carry application data, however, we argued that this is not the optimal design, since `ObservableList` should be used for `view`. Vimification implementation makes the view and bussiness logic bundled together, hinder our development speed.

Therefore, we wish to improve the current design of the application. \<The design will be finalized soon\>. -->


### ApplicativeParser\<T\>

`ApplicativeParser` is an idea from function programming language, where we have a set of **combinators**, and we can combine these combinators to form more powerful combinators.

#### Motivation

The parser of Vimification is implemented with `ApplicativeParser` instead of `Regex`. The main reason why we use `ApplicativeParser` becauses it allows a more declarative style to write the parser, which is (arguably) easier to read and maintain, compared to a long `Regex` expression.

#### Implementation overview

`ApplicativeParser` is implemented as a wrapper around a function that accepts some input sequence, and returns an container that:

- Is empty, if the parser fails.
- Contains the remaining input sequence, together with the parsing result, if the parser succeeds.

For example, consider a parser that tries to parse the string `"foo"`:

- If the input sequence is `"bar"`, the parser will fail (no `"foo"` to parse). The returned container after running the parser will be empty.
- If the input sequence is `"foo bar"`, the parser will succeed. The returned container after running the parser will contain the remaining input sequence `" bar"`, and the parsing result `"foo"`.

The parsing result can be further transformed into objects of the desired type, using some methods such as `ApplicativeParser#map()` or `ApplicativeParser#flatMap()`.

<!-- insert sequence diagram -->

#### Concrete implementation

The current signature of the wrapped function is:

```java
private Function<StringView, Optional<Pair<StringView, T>>> runner;
```

Where `T` is the type of the parsing result.

For example, consider an `ApplicativeParser<Integer>` that tries to parse an integer - given the input sequence `"10"`, the returned container will contain the remaining sequence `""` and the parsing result `10`.

The input sequence used (internally) by `ApplicativeParser` is `StringView`, a thin wrapper class representing a slice on a `String`:

```java
public class StringView {

    private String value;
    private int offset;

    // constructors and methods
}
```

This choice is purely for performance reason - consuming input with `StringView` is much faster as we only need to change the offset stored in the `StringView` in `O(1)`, instead of having to copy the entire substring into a new `String` in `O(n)`.

In the current implementation, the container used is `Optional` from the Java standard library. One problem with `Optional` is that it cannot contain error infomation, and we currently have to use `Exception` for that purpose. However, `Exception` may create unpredictable control flow, and must be used with care. When an `Exception` is thrown, the parser will stop immediately and control is returned to the caller.

The only way to create new `ApplicativeParser` instances is to use static factory methods. This is to ensure that the implementation of `ApplicativeParser` is hidden, and allows us to change the internal implementation of the parser to a more suitable one (in the future, if necessary) without breaking the exposed **API**.



### Command parsers

All command parsers implements a common interface:

```java
public interface CommandParser<T extends Command> { /* implementation details */ }
```

Where `T` is the type of the command returned by the parser.

<!-- insert diagram here -->

#### Implementation overview

The combinators of `ApplicativeParser` will be combined and used in `CommandParser` to parse different commands of the application.

A class implementing `CommandParser` must provide an implementation for `CommandParser#getInternalParser()`. This method will return the appropriate `ApplicativeParser` to be used by `CommandParser#parse()`.




### Command implementations

All command classes in the application inherit from a common interface.

```java
public interface Command {}
```

#### Inheritance hierarchy

Currently, there are 3 kinds of commands in the application:

- `LogicCommand`: responsible for modifying the internal data.
- `MacroCommand`: responsible for handing macro-related features.
- `UiCommand`: responsible for chaging the GUI.

<!-- insert diagram here -->

Each kind has a single interface, with a single abtract method:

```java
public CommandResult execute(/* parameters */);
```

#### Motivation

This desgin allows the parameters required by different kinds to be different. For example, the signature of `LogicCommand#execute()` is:

```java
public CommandResult execute(LogicTaskList taskList, CommandStack commandStack);
```

While the signature of `UiCommand#execute()` is:

```java
public CommandResult execute(MainScreen mainScreen);
```

This is a big modification compared to the original design in AB3. The reason behind this is because we want to reduce the coupling between different kinds of command - `LogicCommand` does not need to know about UI details to be executed.

Concrete classes will implements the corresponding interfaces.

#### Design considerations

The current downside of this design is that, in order to execute a certain command, we need to check the runtime type (using `instanceof`) operator and cast the instance to the appropriate type before calling its `execute` method (by providing the correct arguments).

This is very error prone, due to some reasons:

- Mismatch between the type used with `instanceof` operator and the type used to cast instance:

```java
if (command instanceof LogicCommand) {
    UiCommand castedCommand = (UiCommand) command; // ClassCastException
}
```

- Missing a kind of commands:

```java
if (command instanceof LogicCommand) {
    // statements
} else if (command instanceof UiCommand) {
    // statements
} else {
    // throw Exception
}
// missing MacroCommand, the compiler cannot catch this
```

Java 11 compiler cannot check for these problems, and we risk having serious bugs. However, we argue that the reduction of coupling is worth it, as it prevents other kind of bugs that are harder to catch (such as modification of logic in ui-related command).

Future versions of Java contain features that can handle the problems mentioned above: sealed class and pattern matching. In the future, we may consider upgrading Java and refactor the current implementation with these new features to eliminates these problems.

### Atomic data modification

#### Reasons

Modifications to data inside the application must be atomic. Within a single operation, if there is a failure, then all of the changes completed so far must be discarded. This ensures that the system is always left in a consistent state.

<!-- insert diagram here -->

#### Implementation details


### Undo feature

#### Current implementation

lol this section is sooooooo \*\*\*\*ing verbose

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `Vimification` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

- `VersionedAddressBook#commit()` — Saves the current address book state in its history.
- `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
- `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th task in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new task. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the task was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial Vimification state, then there are no previous Vimification states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone Vimification states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />



### Macro feature

#### Current implementation






### Storage

<!--
`Task` and its subclasses have can be converted from and to JSON format using `JsonAdoptedXYZ` (`XYZ` is a placeholder for the specific task name e.g., `Todo`).

We need to translate the inheritance relationship between `Task` and its subclasses from and to JSON. The current implementation uses 2 annotations (from the Jackson library), `@JsonTypeInfo` and `@JsonSubTypes` to solve this.

One downside of this implementation is that `JsonAdoptedTask` must know all of its subclasses, which increases coupling. However, after considering another alternative (manually setup the json parser), this seems to be the most suitable implementation for the current scale of the project. The increase in coupling is compensated by the ease of implementation. -->



### Syncing view with internal logic







### Using more unchecked exceptions







### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

---







## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- has a need to manage a significant number of contacts
- prefer desktop apps over other types
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable using CLI apps

**Value proposition**: manage contacts faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                   | I want to …​                                                     | So that I can…​                                                                                         |
| -------- | ------------------------- | ---------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------- | --- |
| `* * *`  | SoC Student who knows Vim | use my task planner fast and efficiently                         | reduce time spent on editing the task planner                                                           |
| `* * *`  | SoC Student who knows Vim | list down all the tasks on my to do list                         | get an overview of all the things I need to do at one glance                                            |
| `* * *`  | SoC Student who knows Vim | add entries of task for the things to do into the task planner   | keep track of things to do                                                                              |
| `* * *`  | SoC Student who knows Vim | add priority to a task                                           | give higher priority to more important tasks which should be completed first                            |
| `* * *`  | SoC Student who knows Vim | add tag to a task                                                | categorize the tasks                                                                                    |
| `* * *`  | SoC Student who knows Vim | add deadline to a task                                           | keep track of the date that to complete the task                                                        |
| `* * *`  | SoC Student who knows Vim | mark a task as completed                                         | keep track of tasks to is completed                                                                     |
| `* * *`  | SoC Student who knows Vim | unmark a task as not completed                                   | change the status of the task to be not completed                                                       |
| `* *`    | SoC Student who knows Vim | add recurrence to a task                                         | save time as I do not need to repeatedly create the same entries of tasks                               |
| `* *`    | SoC Student who knows Vim | undo an action                                                   | revert to the previous state if something is done wrongly                                               |
| `* *`    | SoC Student who knows Vim | edit a task’s description, tag, priority, or deadline            | change the details if added wrongly                                                                     |
| `* * *`  | SoC Student who knows Vim | delete a task                                                    | remove tasks that I no longer want to track                                                             |
| `* * *`  | SoC Student who knows Vim | delete a task’s description, tag, priority, or deadline          | delete the details if no longer needed                                                                  |     |
| `* * *`  | SoC Student who knows Vim | delete all completed task                                        | remove tasks that are completed to save memory                                                          |
| `* * *`  | SoC Student who knows Vim | search for tasks which the descriptions contain certain keywords | find all task with the same keyword                                                                     |
| `* * *`  | SoC Student who knows Vim | search for a task based on the specified priority level          | identify tasks with higher priority to complete them first                                              |
| `* * *`  | SoC Student who knows Vim | search for tasks based on a specified list of tags               | find all the tasks in the specified categories                                                          |
| `* * *`  | SoC Student who knows Vim | search for all tasks that are not completed                      | identify tasks to are not completed                                                                     |
| `* * *`  | SoC Student who knows Vim | search for tasks by deadlines before a certain date and time     | find all tasks that need to be done before a certain date and time                                      |
| `* * *`  | SoC Student who knows Vim | search for tasks by deadlines after a certain date and time      | find all tasks that need to be done after a certain date and time                                       |
| `* * *`  | SoC Student who knows Vim | search for tasks by deadlines within a specified period of time  | find all tasks that need to be done within that specified period of time                                |
| `* * *`  | SoC Student who knows Vim | sort tasks by upcoming deadlines                                 | view all the tasks in the order of upcoming deadlines and know which tasks I should be completing first |
| `* * *`  | SoC Student who knows Vim | sort tasks by priorities in descending order                     | see which are the more important tasks I should focus on completing first                               |
| `* *`    | SoC Student who knows Vim | config the storage location of the file                          | customize the location to my own preference for easy reference                                          |
| `* * *`  | SoC Student who knows Vim | view tasks with priorities in different color                    | visualize the important tasks more easily                                                               |
| `* * *`  | New user                  | I can use :help                                                  | to access a brief user guide of all the commands and intended use cases of each command                 |

### Use cases

(For all use cases below, the **System** is the `Vimification` and the **Actor** is the `user`, unless specified otherwise)

**Use case 1: Create a new task**

**MSS**

1.  User specifies entries of the new task with the description, priority level (optional), tags (optional) and deadline (optional).
2.  Vimification uses these entries to create a new task.
3.  Vimification adds the new task to the end of the current list of tasks.

    Use case ends.

**Extensions**

- 1a. The command format is invalid.

  - 1a1. Vimification shows an error message.

    Use case ends.

- 1b. The description is empty.

  - 1b1. Vimification shows an error message.

    Use case ends.

- 1c. The priority level is invalid.

  - 1c1. Vimification shows an error message.

    Use case ends.

- 1d. The deadline is invalid.

  - 1d1. Vimification shows an error message.

    Use case ends.

**Use case 2: Delete a task**

**MSS**

1.  User indicates which task he wants to delete by specifying the index of the task.
2.  Vimification uses this index to remove the chosen task from the current list of tasks.

    Use case ends.

**Extensions**

- 1a. The command format is invalid.

  - 1a1. Vimification shows an error message.

  Use case ends.

- 1b. The index is invalid.

  - 1b1. Vimification shows an error message.

  Use case ends.

**Use case 3: Mark a task as done**

**MSS**

1.  User indicates which task he wants to mark by specifying the index of the task.
2.  Vimification uses the specified index to locate the chosen task.
3.  Vimification uses this index to mark the chosen task as done.

    Use case ends.

**Extensions**

- 1a. The command format is invalid.

  - 1a1. Vimification shows an error message.

  Use case ends.

- 1b. The index is invalid.

  - 1b1. Vimification shows an error message.

  Use case ends.

- 1c. The task is already marked/completed.

  - 1c1. Vimification shows an error message.

  Use case ends.

**Use case 4: Add some tags to a task**

**MSS**

1.  User indicates which task he wants to add the tags to by specifying the index of the task.
2.  User adds a list of additional tags that he wants to add.
3.  Vimification uses the specified index to locate the chosen task.
4.  Vimification adds the list of additional tags to the existing tag set of the chosen task.

    Use case ends.

**Extensions**

- 1a. The command format is invalid.

  - 1a1. Vimification shows an error message.

  Use case ends.

- 1b. The index is invalid.

  - 1b1. Vimification shows an error message.

  Use case ends.

**Use case 5: Filter or search for tasks based on certain conditions**

**MSS**

1.  User specifies the attribute and the conditions for the search. The attribute can be either description, priority levels, tags, completion status or a specified range of date and time.
2.  Vimification converts the conditions into a predicate.
3.  Vimification uses this predicate to filter and search for the tasks that satisfy the specified conditions.

    Use case ends.

**Extensions**

- 1a. The command format is invalid.

  - 1a1. Vimification shows an error message.

  Use case ends.

- 1b. The attribute is invalid.

  - 1b1. Vimification shows an error message.

  Use case ends.

- 1c. The attribute is is empty.

  - 1c1. Vimification shows an error message.

  Use case ends.

- 1d. The condition is invalid.

  - 1d1. Vimification shows an error message.

  Use case ends.

- 1e. The condition is empty.

  - 1e1. Vimification shows an error message.

    Use case ends.

**Use case 6: Edit certain attribute of an existing task**

**MSS**

1.  User indicates which task he wants to edit by specifying the index of the task.
2.  User specifies which attribute of the task he wants to edit.
3.  User inputs the new value of the attribute.
4.  Vimification uses the specified index to locate the chosen task.
5.  Vimification updates the specified attribute to the new value.

    Use case ends.

**Extensions**

- 1a. The command format is invalid.

  - 1a1. Vimification shows an error message.

    Use case ends.

- 1b. The index is invalid.

  - 1b1. Vimification shows an error message.

  Use case ends.

- 1c. The attribute is invalid.

  - 1c1. Vimification shows an error message.

  Use case ends.

- 1d. The attribute is empty.

  - 1d1. Vimification shows an error message.

  Use case ends.

- 1e. The new value is empty.

  - 1e1. Vimification shows an error message.

  Use case ends.

- 1f. The new value is invalid.

  - 1f1. Vimification shows an error message.

  Use case ends.

**Use case 7: Sort the tasks based on certain attribute**

**MSS**

1.  User specifies which attribute he wants to sort the tasks on. The attribute can be either description, priority levels, tags, completion status or deadline.
2.  Vimification sorts the task list by the attribute.
3.  Vimification displays the sorted list to the user.

    Use case ends.

**Extensions**

- 1a The command format is invalid.

  - 1a1 Vimification shows an error message.

    Use case ends.

- 1b The attribute is invalid.

  - 1b1 Vimification shows an error message.

    Use case ends.

- 1c The attribute is empty.

  - 1c1 Vimification shows an error message.

    Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 tasks without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

_{More to be added}_

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Private contact detail**: A contact detail that is not meant to be shared with others

---

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

### Deleting a task

1. Deleting a task while all tasks are being shown

   1. Test case: `:d 1`<br>
      Expected: First task is deleted from the list.

   1. Test case: `delete -1`<br>
      Expected: No task is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
