---
layout: page
title: Developer Guide
---
## **About TeachMeSenpai**

TeachMeSenpai **is a student managing application** specially customised for **teaching assistants (TA) in NUS** who have a lot of students to keep track of. TeachMeSenpai is optimised for fast-typists with a **Command Line Interface (CLI)** with the benefits of a **Graphical User Interface (GUI)**.

This Developer Guide provides in-depth documentation on the design and implementation consideration behind TeachMeSenpai. This guide covers everything you need to know from the architecture down to the feature implementation details of TeachMeSenpai.

If you're eager to get started with TeachMeSenpai, head over to [Setting up, getting started](#setting-up-getting-started)! If you'd like to learn more about how TeachMeSenpai was implemented, you can head over to [Implementation](#implementation)! You may use this guide to evolve TeachMeSenpai to suit your needs.

## **Table of Contents**
{:.no_toc}

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative]
* Libraries used: [JavaFX], [Jackson], [JUnit5]
* All icons used are taken from [flaticon]
* The autocompletion feature was inspired by a similar feature in [AY2223S1-CS2103T-T12-2's tp][T12-2]. We started out with their code, and slowly _(and almost completely)_ overhauled, including refactoring, bug-fixing, adding asserts, and changing the behaviour of the feature to suit our needs.
* The idea of adding the placeholder text _(eg. the `NAME` in `n/NAME`)_ to the `Prefix` class also came from [AY2223S1-CS2103T-T12-2's tp][T12-2], which gave us the idea to further add more things to the prefix _(like whether the prefix is optional)_, although that was our idea.
* The undo and redo features were reused with modifications from [AY2223S1-CS2103T-W17-4's tp][W17-4], which was adapted from the proposed implementation in AB3's Developer Guide [DG][DG]. The changes include renaming, some different implementation, and modification to include of a variable to track the undone/redone commands in the `VersionedAddressBook` class.

[SE-EDU initiative]: https://se-education.org/
[JavaFX]: https://openjfx.io/
[Jackson]: https://github.com/FasterXML/jackson
[JUnit5]: https://github.com/junit-team/junit5
[flaticon]: https://www.flaticon.com/
[T12-2]: https://github.com/AY2223S1-CS2103T-T12-2/tp
[W17-4]: https://github.com/AY2223S1-CS2103T-W17-4/tp
[DG]: https://se-education.org/addressbook-level3/DeveloperGuide.html

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-success">
:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

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
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` _(`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`)_ which uses the other classes shown above to parse the user command and create a `XYZCommand` object _(e.g., `AddCommand`)_ which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes _(e.g., `AddCommandParser`, `DeleteCommandParser`, ...)_ inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* stores every modified version of the address book in `VersionedAddressBook`.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="block" class="alert alert-info">
:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` and `Module` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, and one `Module` object per unique module instead of each `Person` needing their own `Tag` and `Module` objects.

<img src="images/BetterModelClassDiagram.png" width="450" />
</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-W12-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

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

### Autocomplete feature

#### Implementation Details

The autocomplete feature is implemented through the `AutocompleteEngine` class, which is aggregated inside the `LogicManager` class. The `LogicManager` class implements the `Logic` interface, which exposes the `AutocompleteEngine` methods. The `LogicManager` initializes the `AutocompleteEngine` with a predefined `Model`, and the `AutocompleteEngine` utilizes methods from the `Model` interface to access existing tag, module, and education values.

The `CommandBox` UI class then integrates the autocomplete feature. It listens for user input and queries the `Logic` interface for suggestions based on the current input.

The public methods of the `AutocompleteEngine` class are:

- `suggestCommand(String userInput)` — Suggests a command _(including its arguments)_ based on the user input.
- `autocompleteCommand(String userInput, String commandSuggestion)` — Returns the new user input when the user autocompletes the command.

New methods in the `Model` interface for the autocomplete feature include:

- `getExistingTagValues()` — Returns a list of all existing tag values contained in the person list.
- `getExistingModuleValues()` — Returns a list of all existing module values contained in the person list.
- `getExistingEducationValues()` — Returns a list of all existing education values contained in the person list.

#### Feature Details

Given below is an example usage scenario and how the autocomplete mechanism works at each step:

- **Step 1.** The user launches the application for the first time. The `LogicManager` initializes the `AutocompleteEngine` with the predefined `Model`.

- **Step 2.** The user types the input `add t/`. `CommandBox` detects the change in user input, and calls the `Logic::suggestCommand` method to get suggestions based on the said input.

- **Step 3.** The `LogicManager` in turn calls the `AutocompleteEngine#suggestCommand` method, which in turn queries the `Model` for existing tag, module, and education values. In this example, `Model::getExistingTagValues()` returns the list `["tag1", "tag2"]`.

  <div markdown="span" class="alert alert-info">:information_source: **Note:** For **Step 3**, since the existing module and education values aren't used, they're omitted in this example.
  </div>

- **Step 4.** The `AutocompleteEngine` processes the user input and existing values to generate the suggestion `add n/tag1 | tag2`, and returns it to `CommandBox`, which displays it to the user in a shadow-like autocomplete suggestion.

Here's the suggestion sequence diagram showing the above 4 steps:

![suggestion_sequence](images/SuggestionSequenceDiagram.png)

<br>

- **Step 5.** The user then presses the `TAB` key, and `CommandBox` component calls the `Logic#autocompleteCommand` method to complete the user input `add t/`, based on the current suggestion `add t/tag1 | tag2`.

- **Step 6.** The `LogicManager` in turn calls the `AutocompleteEngine#autocompleteCommand` method to generate said suggestion, and returns the autocompleted user input `add t/tag1` to `CommandBox`, which sets that as the command box value.

Here's the autocompletion sequence diagram showing the above 2 steps:

![autocomplete_sequence](images/AutocompleteSequenceDiagram.png)

<br>

If the user types an invalid command-word or index, `AutocompleteEngine#suggestCommand` will throw a `ParseException`, which causes the text to be displayed in red.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The autocomplete feature does not actually validate whether the argument values are valid or indexes exist. It's merely a simple heuristic check, to help users avoid making obvious syntax mistakes in the commands.
</div>

#### Design considerations

**Aspect: Simple heuristic check vs. full validation of command arguments:**

* **Alternative 1 (current choice):** Simple heuristic check
  * Pros: Less computationally expensive, which avoids lagging the app and hindering fast typists. Easier to implement.
  * Cons: Does not provide full validation of command arguments.
* **Alternative 2:** Full validation of command arguments using each field's is-valid methods _(e.g., `Address::isValidAddress`, `Email::isValidEmail`)_
  * Pros: Provides full validation of command arguments.
  * Cons:
    * More computationally expensive, especially when using regex for validation, which can lag the app and hinder fast typists.
    * Harder to implement
    * Might constantly highlight the text red, which can be annoying for users who haven't finished typing.

We chose Alternative 1 because it aligns with the [project constraint][constraint-typing-preferred] of targeting users who can type fast. Furthermore, it is easier to implement and provides a smoother user experience without constantly highlighting the text red while typing.

[constraint-typing-preferred]: https://nus-cs2103-ay2223s2.github.io/website/admin/tp-constraints.html#constraint-typing-preferred

### Add Feature

#### Implementation Details

The implementation of the `add` command involves creating a new `Person` object and storing it in `AddressBook`.

Given below is a class diagram on the `Person` class and the classes related to its attributes:

![student_diagram](images/StudentClassDiagram.png)

The `Person` object is composed of attributes:

* `Name`: The name of the student.
* `Phone`: The phone number of the student.
* `Email`: The email address of the student.
* `Address`: The address of the student.
* `Education`: The education level of the student.
* `Telegram`: The telegram handle of the student.
* `Module`: The modules the TA is teaching the student.
* `Remark`: Remarks/notes the TA has about the student.
* `Tags`: Categories a student belong to.

To add details into an attribute, [prefixes](#prefix-summary) that represent the attributes can be specified. 
<div markdown="span" class="alert alert-info">:information_source: **Note:** Only the `Name` field is compulsory for the user to add a new entry. Other fields are optional and can be added at a later time.
</div>

The [`java.util.Optional<T>`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Optional.html) class was utilised to encapsulate the optional logic of the attributes.


Here is a sequence diagram showing the interactions between components when `add n/Alice edu/Year 1` is run.:

![add_sequence](images/AddSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddCommandParser` and `AddCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Feature details
1. The app will validate the parameters supplied by the user with pre-determined formats for each attribute.
2. If an input fails the validation check, an error message is provided which details the error and prompts the user for a corrected input.
3. If the input passes the validation check, a new `Person` entry is created and stored in the `VersionedAddressBook`.

#### General Design Considerations

Some additions made to the original AB3 attributes are the `Education`, `Module` and `Remark`.
1. `Education` is implemented similarly to the other attributes like `Address`, but is modified to fit the logic that a student can only have one education level.
2. `Module` is implemented similarly to `Tags` but has been modified to accommodate module names that are more than one word long.
3. Every attribute except `Name` has been made optional during the inputting of the command in case the student's details are unknown at the time of entry.

**Aspect: Optional fields**
* **Alternative 1 (current choice):** Only `Name` has to be specified to add a new `Person` entry.
    * Pros:
        * Improves the user's convenience by allowing them to add a `Person` entry even with limited knowledge about their details.
    * Cons:
        * Many cases of empty/*null* inputs in the optional fields have to be accounted for when saving the data and testing.
* **Alternative 2:** All parameters have to be filled in.
    * Pros:
        * Easier to implement as there is lesser room for errors when dealing with empty/*null* inputs among the optional fields
    * Cons:
        * `add` becomes a lengthy command to execute as unnecessary additional time is needed to enter dummy values to meet the input requirements.
        * Users are inconvenienced as "useful" entries that can be made are limited to students whose details are all known.

[↑ Back to top](#table-of-contents)

### Delete feature

#### Implementation Details

The `delete` implementation is similar to the implementation in AB3's codebase. However, we've made some additions to support deletion of multiple (unique) indexes.

Here is a sequence diagram showing the interactions between components when `delete 1 2` is run.:

![delete_sequence](images/DeleteSequenceDiagram2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Feature Details

1. The `delete` command supports deleting multiple entries at once by specifying multiple indexes. 
> For example, `delete 1 3 5` will delete the entries at indexes 1, 3 and 5 in the `AddressBook`. 
2. If any of the indexes given is not a positive integer or is out of bounds of the current list of entries, none of the entries will be deleted and an error will be shown.
3. If duplicate indexes are given, none of the entries will be deleted and an error will be shown.
4. If the command is valid, the entries at the specified indexes will be deleted from the `VersionedAddressBook`.

#### Design Considerations

Taking into consideration that users might make a typo as well as the time cost of using [`undo`](#undoredo-feature) (will be explained later) to restore the deleted entries, we believe that if a single invalid `INDEX` is given, the system should generate an error message.

**Aspect: Handling invalid indexes and duplicate indexes in delete**

* **Alternative 1: (Current choice)** Command is not executed and an error message for is shown.
  * Pros:
    * As the command input is not cleared in the case of an invalid command, this allows user to edit the erroneous command.
  * Cons:
    * Harder for users to find the invalid index and correct it.
* **Alternative 2:** Delete all valid `Person` entries out of the given indexes.
  * Pros:
    * If command only has minor typos, this might save the user time by not needing to editing their command.
  * Cons:
    * The index of the intended entry to delete might be invalid, so after the other entries with valid indexes are deleted, the user needs to run `delete` again anyway to fully delete their desired entries.
    * Reduces the defensiveness of the application, making it more susceptible to bugs and unexpected behaviours.

[↑ Back to top](#table-of-contents)

### Edit Feature

#### Implementation Details

The implementation of `edit` involves creating a new `Person` object with updated details to replace the previous `Person` object.
This is done using the `EditPersonDescriptor` class, which creates the new `Person` object.

The `edit` command has similar input fields represented by [prefixes](#prefix-summary) to the [`add`](#add-feature) command, with an additional `INDEX` parameter.
`INDEX` represents the index number of the student to be edited in the list.

<div markdown="span" class="alert alert-info">:information_source: **Note:** While all the fields are optional, at least one needs to be given.
</div>


Here is a sequence diagram showing the interactions between components when `edit 1 n/Bob edu/Year 2` is run.:

![edit_sequence](images/EditSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `EditCommandParser`, `EditCommand`, and `EditPersonDescriptor` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Feature details
1. Similar to `add`, the app will validate the parameters supplied by the user with pre-determined formats for each attribute.
2. If an input fails the validation check, an error message is provided which details the error and prompts the user for a corrected input.
3. If the input passes the validation check, the corresponding `Person` is replaced by a new `Person` object with the edited attributes and stored in the `VersionedAddressBook`.

#### General Design Considerations
Whether a new `Person` object should be created when editing a student entry.
* **Alternative 1 (Current choice):** `edit` will create a new `Person` object with the help of `EditPersonDescriptor`
    * Pros:
        * Meets the expectations of the immutable `Person` class.
    * Cons:
        * Inefficient as an entire `Person` object is created even if only one field is changed.

* **Alternative 2:** `edit` directly sets the updated values in the existing `Person` object.
    * Pros:
        * More timely option and space efficient.
    * Cons:
        * In order to execute this, `Person` cannot be immutable, this reduces the defensiveness of the program, making it more susceptible to errors.

[↑ Back to top](#table-of-contents)

### Find feature

#### Implementation Details
The implementation of `find` involves searching for entries that match all the fields specified.

The `find` feature supports matching of partial keywords using the `StringUtil::containsPartialIgnoreCase`, as well as specifying which field to match the keyword in using [prefixes](#add-feature).

The `find` feature uses `FullMatchKeywordsPredicate`, which implements the `Predicate<Person>` interface where the `test` method checks whether the data in the relevant field of a `Person` contains the specified keyword.
The reason for implementing this feature with `Predicate<Person>` is that it can be easily used to filter the entire list of `Person` collected into java's `FilteredList`.

Here is a sequence diagram showing the interactions between components when `find n/Alice p/12345678` is run.:

![find_sequence](images/FindSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `FindCommandParser` and `FindCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Feature details
1. The `find` command allows the matching of partial keywords.
> Take a person's name to be `Michelle Yeoh`.  \\
> An example of finding by partial keyword is using "Ye" or "miche" while `KEYWORD` would be "Michelle Yeoh".

2. Users are allowed to specify the field that they want to find in by using the default [prefixes](#prefix-summary) given to them.
   This then returns the entry that matches all the fields specified.

> The prefixes refer to those that the user input in the `Add` command, eg.
> ```
> add n/Bob p/98712345 edu/P5
> ```
> In the `find` command, users are then allowed to input their prefix of choice, eg.
> ```
> find n/bo
> find p/9871
> find edu/p5 a/Pasir Ris
> ```


3. If `find` is used without any fields, it will [list](#list-feature) all existing entries.
4. If no entries can be matched, nothing will be shown.



#### General Design Considerations
The implementation of `find` is built on top of the original `find` command in the AB3 codebase as we felt that the default implementation of `find` was too restrictive.

Our implementation has some modifications such as:

1. Allow matching by partial keyword so that the user do not have to input the full keyword everytime.
2. Specifying fields to search in by inputting the corresponding prefixes.

**Aspect: Command format:**
* **Alternative 1 (Current choice):** `find [PREFIX KEYWORD/PARTIAL_KEYWORD]...`
  * Pros:
    * Improves the user's convenience as they do not have to type the full keyword everytime.
    * Extensible across other attributes.
    * Narrows down the list to be very succinct and specific to the desired keyword.
  * Cons:
    * Adds complexity to the implementation as this implementation introduces a lot of potential errors in parsing the user's input.
    * Might be slightly challenging for new users to enter `PREFIX`.
* **Alternative 2:** `find [KEYWORD/PARTIAL_KEYWORD]...` (With no `PREFIX`)
  * Pros:
    * Easier to implement as there is lesser validating done by the app.
    * Provides the user flexibility in searching across all attributes by default.
    * Less syntax to input and learn by the users.
  * Cons:
    * The filtered list may not be what was desired as short partial keywords like `a` is unlikely to result in a succinct list.
    * Users will not be able to search keywords for a particular attribute.
    * The resulting filtered list will span across multiple different fields, where all attributes in all fields containing the specified keyword will be displayed.
    
[↑ Back to top](#table-of-contents)

### Filter feature

#### Implementation Details

The `filter` command involves searching and listing all entries which has a match in at least one field specified. 

Similar to [`find`](#find-feature), `filter` uses `StringUtil::containsPartialIgnoreCase` for matching of partial keywords, as well as `ContainsKeywordsPredicate` which implements the `Predicate<Person>` interface where the `test` method checks whether the data in the relevant field of a `Person` contains the specified keyword.

Here is a sequence diagram showing the interactions between components when `filter n/Alice p/12345678` is run:

![FilterSequenceDiagram](images/FilterSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `FilterCommandParser` and `FilterCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Feature details
1. Similar to [`find`](#find-feature), `filter` allows matching of partial keywords.
2. Similar to [`find`](#find-feature), `filter` allows different fields to be specified. 
This will return all entries that match at least one keyword in any fields specified. 
3. If no entries can be matched, an empty list will be shown.
4. If `filter` is used without any fields specified, an empty list will be shown.


#### General Design Considerations
The `filter` was implemented to improve the flexibility of filtering the student list, allowing easier categorisation of students.

Although the function of `filter` is rather similar to that of [`find`](#find-feature), there might be cases where [`find`](#find-feature) is unable to give the desired outcome.
Therefore, a `filter` feature is implemented on top of [`find`](#find-feature) to give users more flexibility in these kinds of scenarios.

> For example, take these two different scenarios:<br>
> 
> _**Scenario 1:**_ The TA saved the tutorial timeslot of each student in the [`Tag`](#add-feature) field and wants to view all students who are either in the 9am class or 1pm class.<br>
>
> _**Scenario 2:**_ The TA wants to see the students who have "lagging behind" as a tag, has a question asked by them saved in the remarks field, and are in the 9am class.
> 
> Both scenarios require a different implementation each in order to return the desired results. 

**Aspect: `find` or `find` + `filter`**
* **Alternative 1 (Current choice):** Separate into `find` and `filter`
  * Pros:
    * Allows for better filtering based on the needs of the user as both commands have scenarios that they are each useful in.
  * Cons:
    * Some of their functionalities might overlap, causing them to be indistinguishable in some scenarios.
    * Might be harder for new users to learn the differences between the two commands.
* **Alternative 2:** Only have the `find` command
  * Pros:
    * Easier to learn.
  * Cons:
    * A single command is unable to implement matching of all fields and matching of at least one field at the same time, so a single command might be inadequate for certain scenarios.

[↑ Back to top](#table-of-contents)

### List feature

#### Implementation Details
The `list` implementation is identical to the implementation in AB3's codebase.

The `list` command makes use of the `FilteredPersonList` by passing in a dummy `Predicate<T>` that always evaluates to true,
such that the `FilteredPersonList` returns every existing `Person`.

Here is a sequence diagram showing the interactions between components when `list` is run.:

![list_sequence](images/ListSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ListCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

#### Feature Details
1. `list` shows all existing entries at once.

#### Design Consideration
The `list` command does not accept any arguments in order to make it as convenient for users to view their full list of students after a prior command 
such as [`find`](#find-feature) or [`filter`](#filter-feature) which displays a filtered list.

### Remark feature

#### Implementation Details
The application provides users with two different methods of entering or editing a remark for a student.
* Using the pop-up text box implemented in this feature.
* Adding the remark through the [`add`](#add-feature) command.

Similar to [`edit`](#edit-feature), this feature makes use of the `EditPersonDescriptor` to create a new `Person` object with the updated remarks. Then, the previous `Person` object is replaced.

Here is a sequence diagram showing the interactions between components when `remark 1` is run (Assuming the existing command is "Absent", and is to be replaced with "Submitted lab 10").:

![remark_sequence](images/RemarkSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ListCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

In addition, we should take special note of the format for the `remark` command: `remark INDEX [REMARK]`.

The remark mechanism will be facilitated by a pop-up text box.
They can do this by simply omitting the optional `REMARK` parameter, which will cause the pop-up text box to appear.
This will allow users to format their remarks however they like, rather than being restricted to a single line in the command line.
Users can use the `CTRL` + `S` combination to close the remark box without using the mouse to click on the "X", thereby
staying true to the goal of catering to fast typists.

We implemented this by keeping the `remark INDEX` command when it is specified, while pulling up the text box.
When the text box is closed, the remark will be appended entirely to the saved `remark INDEX` command. This makes it
conform to the `remark INDEX REMARK` format and this command will be run again. 

However, should the user prefer it, they can also directly input their remarks into the pop-up text box
by directly specifying the optional `REMARK` parameter, thereby skipping the 2-step nature of the command.

Deletion of remarks will be done by specifying an empty remark from the text box.

Currently, to differentiate between deletion of the remark and the `remark INDEX` command to open the text box,
empty or whitespace only remarks are represented by the null character `\0`. This means that if the
user somehow types only a single `\0` character into the text box, it can be seen as a bug as it will be cleared 
upon closing the text box. However, we do not consider this to be a legitimate use of the program, and see no use in
changing the design.

#### Feature Details
1. The remark feature is facilitated by a pop-up text box brought up by using the command `remark INDEX`.
2. The contents of the pop-up text box are saved by pressing `Ctrl + S` on the keyboard.
3. If the content of the remarks is blank, the command will be treated as a remark deletion operation and any existing remarks will be deleted.
4. Similar to [`edit`](#edit-feature), a new `Person` object is created with the new remarks and replaces the old `Person` object in the `VersionedAddressBook`

#### General Design Considerations

In order to make this feature as versatile as possible, 
the `remark` feature should consider formatted inputs (eg. new lines to separate paragraphs).

Additionally, we opted for a pop-up text window as the command line only provides a restricted view and input option for users, hence it does not support formatted remarks.

**Aspect: Command input format**
* **Alternative 1:** Adding the `remark` through the command line.
  * Pros:
    * Easier to implement.
    * Fewer chances of bugs to appear
  * Cons:
    * Restricts users to a single line or continuous paragraph of remark.
    * Limits formatting options for remark.
* **Alternative 2: (Current implementation)** Adding remark through a pop-up text window
  * Pros:
    * Provides users flexibility in the format of their remarks.
    * Remarks are not restricted to a single line or continuous paragraph.
    * Allows the future implementation of enhanced formatting, like using Markdown.
  * Cons:
    * More complicated to implement as the format of the remarks have to be saved and loaded into `VersionedAddressBook` without any formatting errors.
    * Have to make sure the user does not enter any state-changing commands like `add` and even `find`/`list`.
* **Alternative 3:** Adding remark through an in-window text box
  * Pros:
    * Has the same level of flexibility as Alternative 2.
    * No need for a pop-up text window, which could be beneficial for cross-platform support.
  * Cons:
    * Less intuitive for the user as the user could try to input more commands into the application while editing remarks, which would cause bugs due to the 2-step nature of the command.

**Aspect: Remark display**
* **Alternative 1: (Current implementation)** Preview the first line (truncated) of a student's remarks under all the other attributes
  * Pros:
    * Short remarks are instantly visible to users.
    * Easy to implement.
  * Cons:
    * A short remark which has a length slightly over the character limit for truncation can only be viewed via the [`show`](#show-feature).
* **Alternative 2:** If a remark is present, simply display an indicator in `PersonCard`
  * Pros:
    * Easy to implement.
    * Viewing the remark in `ResultDisplay` is supported by the [`show`](#show-feature) command.
    * Supports formatting of `remark` since it is not restricted to the `PersonCard` view.
  * Cons:
    * An extra step for users may be inconvenient.
    * Inconvenient for short remarks compared to **Alternative 1**.
* **Alternative 3:** Show the full remark in `PersonCard` beside all the other attributes
  * Pros:
    * Remark is directly visible from the list.
    * Supports formatting in `remark`.
  * Cons:
    * Remarks are limited to the view of `PersonCard` and size of the window.
    * Remarks that are too long will be cut off and be not fully visible.

[↑ Back to top](#table-of-contents)

### Show feature

#### Implementation Details

The implementation of `show` retrieves and shows the information from the [`Remark` field](#add-feature) of the entry with the specified index.


#### Feature Details
1. If the `Remark` field is not empty, the contents of the `Remark` field are shown in the `ResultDisplay` box, and the `PersonListCard` of the entry with the specified index is shown below the `ResultDisplay`.
2. If there are no remarks, a message indicating such is shown in the `ResultDisplay`.

#### General Design Considerations
The `show` command was implemented to support the [`remark`](#remark-feature) command,
where this provides a way to view the remarks of an entry other than bringing up the pop-up text box.

Remarks longer than the width of `PersonListCard` in `PersonListPanel` will not be visible.
Hence, `show` allows users to view the full remark in the `ResultDisplay` where scrolling is supported.

**Aspect: Display output**
* **Alternative 1: (Current choice)** Display the entire `PersonCard` of the student chosen in the `ResultDisplay`
  * Pros:
    * Supports the `remark` command as intended since scrolling is possible.
    * Allows users to view the student details and remarks all at once.
  * Cons:
    * Harder to implement.
* **Alternative 2:** Display the entire `PersonCard` of the student chosen in `PersonListPanel`.
  * Pros:
    * Allows users to view the student details and remarks all at once.
    * Supports the `remark` command as intended.
  * Cons:
    * May reduce user convenience as `show INDEX` will likely always be followed with the `list` command to toggle back to the full list of students.
    * Harder to implement as the size of the `PersonCard` for the `Person` has to be updated every time `show` is executed.



[↑ Back to top](#table-of-contents)

### Undo/Redo feature

#### Implementation Details
This feature involves restoring the current address book to its previous version after a command altering the address book is executed.

Examples of such commands are:
* [`add`](#add-feature)
* [`delete`](#delete-feature) _(and also `clear`)_
* [`edit`](#edit-feature)
* [`remark`](#remark-feature)

The undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as a `versionStateHistory` and `currentVersionPointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history as well as the command that was last executed.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

#### Feature Details

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

**Step 1**. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentVersionPointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

**Step 2**. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentVersionPointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

**Step 3**. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

**Step 4**. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentVersionPointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentVersionPointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#checkUndoable()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the `undo` operation.

</div>

The following sequence diagram shows how the undo operation works (assuming `VersionedAddressBook` is undoable):

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentVersionPointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

The following sequence diagram shows how the redo operation works (assuming `VersionedAddressBook` is redoable):

![RedoSequenceDiagram](images/RedoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentVersionPointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#checkRedoable()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

**Step 5**. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentVersionPointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentVersionPointer` will be purged. Reason being it no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros:
    * Easy to implement.
  * Cons: 
    * May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: 
    * Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: 
    * We must ensure that the implementation of each individual command is correct.

**Aspect: Command History:**

* **Alternative 1 (current choice):** Only saves the commands that modify the address book.
  * Pros: 
    * Easy to implement.
  * Cons: 
    * Reduces user experience as keeping track of all commands will also allow us to improve the error messages by specifying the specific recent command which does not allow `undo` or `redo`

* **Alternative 2 :** Save every command executed regardless of whether it modifies the address book.
  * Pros:
    * Improves user experience by improving the quality of the error message for `undo` and `redo`.
  * Cons: 
    * Slightly more complicated to implement as a separate `currentStatePointer` for the command history will have to be added.

[↑ Back to top](#table-of-contents)

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

* Teaching Assistants (TAs)/tutors who have a class of students to manage and are preferably proficient typists.

**Value proposition**:

* TeachMeSenpai acts as an optimised application for tutors to manage their students' data and obtain insights on their students' data.

### User stories

**Priorities:**

- **`HIGH`** _(must have)_
- `MED` _(nice to have)_
- _`Low`_ _(unlikely to have)_

|  Priority  | As a …​ | I want to …​                                                                                                   | So that I can…​                                                             |
|:----------:|---------|----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| **`HIGH`** | tutor   | open the app                                                                                                   | begin using the app                                                         |
| **`HIGH`** | tutor   | close the app                                                                                                  | leave the app                                                               |
| **`HIGH`** | tutor   | add a student's name                                                                                           | track a student's progress by their name                                    |
| **`HIGH`** | tutor   | include student's education level when adding the student (eg. P6)                                             | keep track of a student's education level                                   |
| **`HIGH`** | tutor   | include student's phone number when adding the student (eg. 94206942)                                          | keep track of a student's phone number                                      |
| **`HIGH`** | tutor   | include student's email when adding the student (eg. iloveanimegirls@gmail.com)                                | keep track of a student's email                                             |
| **`HIGH`** | tutor   | include student's address when adding the student (eg. Block 69 S642069)                                       | keep track of a student's address and go to the place easily                |
| **`HIGH`** | tutor   | include the modules I'm teaching a student to their entry (eg. CS2101, CS4243)                                 | keep track of what modules I'm teaching the student                         |
| **`HIGH`** | tutor   | include optional student-specific notes when adding the student (eg. Good in Japanese)                         | store information for a particular student such as notes and remarks        |
| **`HIGH`** | tutor   | include tags on a student to group them by categories (eg. classes)                                            | categorise my students into groups.                                         |
| **`HIGH`** | tutor   | delete a student entry from my list by index(es)                                                               | remove all details related to a certain student                             |
| **`HIGH`** | tutor   | have my changes saved automatically                                                                            | be sure that I won't lose my changes if I crash/close the app               |
| **`HIGH`** | tutor   | view my list of students                                                                                       | keep track of who I'm currently teaching                                    |
| **`HIGH`** | tutor   | View the address of a student                                                                                  | know where to go if I need to provide tuition at their house                |
| **`HIGH`** | tutor   | have my data persist between use sessions                                                                      | continue my session where I left off                                        |
| **`HIGH`** | tutor   | find my students by searching their names                                                                      | quickly view that student's details                                         |
| **`HIGH`** | tutor   | edit a student's name                                                                                          | correct a student's name                                                    |
| **`HIGH`** | tutor   | edit the modules I'm teaching a particular student                                                             | update or correct a student's records                                       |
| **`HIGH`** | tutor   | edit a student's education level                                                                               | update or correct a student's records                                       |
| **`HIGH`** | tutor   | receive an appropriate and user-friendly error message when I enter the wrong inputs/parameters for a command | find out the correct input/parameter format and use the feature as intended |
| **`HIGH`** | tutor   | be able to ask for help                                                                                        | learn how to use the app                                                    |
|   `MED`    | tutor   | filter my students by education level (eg. all P6 students)                                                    | view my students of the same education level                                |
|   `MED`    | tutor   | filter my students by modules                                                                                  | view all the student's I'm teaching a particular module to                  |
|   `MED`    | tutor   | filter my students by address (eg. Ang Mo Kio)                                                                 | view all the students who live in a particular area                         |
|   `MED`    | tutor   | filter my students by email (eg. @gmail)                                                                       | view all the students with similar emails                                   |
|   `MED`    | tutor   | filter my students by tags (eg. active)                                                                        | view all my students with the same qualities                                |
|   `MED`    | tutor   | sort my students by their names                                                                                | view my students in a systematic manner                                     |
|   `MED`    | tutor   | sort my students by their education level                                                                      | view my students according to their education level                         |

[↑ Back to top](#table-of-contents)

### Use cases

For all use cases below, the **System** is the `TeachMeSenpai` app and the **Actor** is the `user`, unless specified otherwise.

#### Use case UC1: Add a student
{:.no_toc}

**MSS**

1.  User request to add a new student's name and particulars.
2.  System adds new student and their particulars as a new entry in the list

    Use case ends

**Extensions**

* 1a. The given name/particulars is invalid
    * 1a1. System shows an error message

      Use case resumes from step 1.

* 1b. The compulsory name field is missing
    * 1a1. System shows an error message

      Use case resumes from step 1.

* 1c. A student entry with the same name exists in the list
    * 1c1. System shows an error message

      Use case resumes from step 1.

* 1d. Some optional particulars are missing
    * 1d1. System adds new student, leaving their particulars blank

      Use case ends


#### Use case UC2: Find a student
{:.no_toc}

**MSS**

1. User requests to find a specific set of students based on a set of criteria
2. System shows a list of students that match the criteria

   Use case ends

**Extensions**

* 1a. The field to search in specified by the user is empty
  * 1a1. System shows an error message

    Use case resumes from step 1.

* 1b. The field to search in is not specified by the user
  * 1b1. System defaults to searching the keyword in the name field
  * 1b2. System shows a list of students whose names match the keyword

    Use case ends

* 1c. The field and keyword are not specified by the user
  * 1c1. System shows an error message

    Use case resumes from step 1

#### Use case UC3: Delete students
{:.no_toc}

**MSS**

1.  User requests to list students
2.  System shows a list of students
3.  User requests to delete multiple students in the list by their indexes from the list
4.  System deletes the students

    Use case ends

**Extensions**

* 1a. User requests to find a specific set of students based on a set of criteria
  * 1a1. System shows a list of students which matches the criteria input by the user
  * 1a2. User requests to delete a specific student in the list by their index from the list
  * 1a3. System deletes the student

    Use case ends

* 2a. The list is empty

  Use case ends

* 3a. A given index is invalid
  * 3a1. System shows an error message

    Use case resumes at step 2

* 3b. Duplicated indexes is given
  * 3b1. System shows an error message

    Use case resumes at step 2

#### Use case UC4: List student(s)
{:.no_toc}

**MSS**

1.  User requests to list all the students.
2.  System shows the list of all students.

    Use case ends.

**Extensions**

* 1a. Additional parameters are added behind `list`.
  * 1a1. System shows an error message.

    Use case ends.

#### Use case UC5: Update remarks
{:.no_toc}

**MSS**

1. User requests to list students
2. System shows a list of students
3. User requests to edit a student's remarks of a specific student in the list by their index from the list
4. System shows a pop-up text box
5. User enters remarks in the text box
6. User exits from the text box
7. System saves the remarks

   Use case ends

**Extensions**

* 2a. The list is empty

  Use case ends

* 3a. The given index is invalid
  * 3a1. System shows an error message

    Use case resumes at step 2

* 5a. The inputted remarks is empty
  * 5a1. System deletes the old remarks
  * 5a2. System saves with no remarks

    Use case ends

#### Use case UC6: Edit particulars
{:.no_toc}

**MSS**

1. User requests to edit a student's particulars based on their index in the list displayed
2. System replaces the specified fields with the new details

   Use case ends

**Extensions**

* 1a. The field to edit is not specified
  * 1a1. System shows an error message

    Use case resumes from step 1

* 1b. The index is given is invalid
  * 1b1. System shows an error message

    Use case resumes from step 1

* 1c. The field is specified but the details are empty
  * 1c1. System deletes the information in the specified field

    Use case ends

#### Use case UC7: Exiting the application
{:.no_toc}

**MSS**

1. User requests to exit the application
2. System saves all data into a local file
3. System exits from the application

   Use case ends

[↑ Back to top](#table-of-contents)

### Non-Functional Requirements

1. A user that is completely new to the application should be able to be familiar with the functionalities within 1 hour.
2. System should respond within 0.1 second of the user providing an input.
3. All systems must be able to access the _save file_ ie. Save file should be independent of the OS.
4. Any information displayed should be concise and structured in a logical manner such that it is easily understandable.
5. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
6. Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
7. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

[↑ Back to top](#table-of-contents)

### Glossary

* **Tutors**: (NUS) Teaching Assistants.
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Save File**: The file containing all the data (ie. Entries of student information) inputted by the user saved locally on the user's own computer.

[↑ Back to top](#table-of-contents)

### Prefix summary

| Prefix | Meaning                                 | Example                               |
|--------|-----------------------------------------|---------------------------------------|
| n/     | Name of student                         | `n/Shao Hong`                         |
| p/     | Phone number of student                 | `p/81234567`                          |
| e/     | Email of student                        | `e/e07123456@u.edu.sg`                |
| a/     | Address of student                      | `a/16 Bukit Timah Road, S156213`      |
| edu/   | Education level of student              | `edu/P6`                              |
| r/     | Remark for student                      | `r/Good in German`                    |
| t/     | Tag of student                          | `t/active` or `t/hardworking ...`     |
| m/     | Module that the student is being taught | `m/CS2101` or `m/CS2101 m/CS4243 ...` |
| tele/  | Telegram handle of the student          | `tele/@chuuchuu` or `tele/@sO_m4nY`   |


[↑ Back to top](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

Evolving AB3 into TeachMeSenpai had a set of challenges and obstacles our team had to work around.

The initial difficulty faced involved getting used to the forking workflow of the project as Git was still new to most of the group.
There were also concerns around `remark` feature as our team initially intended for it to be a text box within TeachMeSenpai's MainWindow.
However, as our team was not familiar with JavaFX, it was not as feasible as the pop-up text box window our team settled on. We also included keyboard shortcuts for exiting the pop-up window to not compromise our goal of creating an app targeted at fast-typists.

One challenge we ran into during the project surfaced after an initial testing of our app where we discovered some inconsistent error handling. However, due to time constraints, we were not able to enhance the specificity of our error messages.
If given more time, this would be an aspect our team would not have compromised. The time constraint also resulted in the inability to refactor a large portion of AB3's code to be specific to our app (Student, and StudentBook instead of Person, and AddressBook).
However, since our priority was to evolve AB3 to meet the needs of teaching assistants, we prioritised the functionality of our app over the backend details. If given more time, we would definitely have refactored the code.

Lastly, TeachMeSenpai was no easy feat and a portion of the app contains code adapted and reused from past semester's projects.

Our autocomplete feature was inspired by a similar feature in [AY2223S1-CS2103T-T12-2's tp][T12-2]. We started our with their code, and slowly but surely revamped the code.
This included refactoring, bug-fixing, adding asserts and changing the behaviour of the feature to suit our needs such as prefixes, commands, and parameters specific to our app.
The idea of adding the placeholder text to the `Prefix` class also came from [AY2223S1-CS2103T-T12-2's tp][T12-2], which gave us the idea to add onto the prefix (like whether the prefix is optional), although the initial idea for optional prefixes was our idea.

Finally, the undo and redo features were reused with modifications from  [AY2223S1-CS2103T-W17-4's tp][W17-4], which was adapted from the proposed implementation in AB3's Developer Guide [DG][DG] as well. The changes include renaming, some different implementation, bug fixing, and modification to include a variable to track the undone/redone commands in the `VersionedAddressBook` class.
We also encapsulated the `CommandHistory` within the `VersionedAddressBook`.



## **Appendix: Planned enhancements**

The current version of TeachMeSenpai certainly has its flaws and here are some of our plans for future enhancements to improve TeachMeSenpai further for TAs.

### Add/Edit
#### Feature flaw 1
Currently, name fields are case-sensitive so an input like `Shaun` and `shaun` will be considered as unique names. A planned enhancement for the name field is to check for case-insensitivity in `add` and `edit` in order to disallow such duplicate names.

#### Feature flaw 2
Currently, name fields allow alphanumeric characters only. This means that inputs like:

- `Mary 2` is allowed
- `Roy s/o Balakrishnan` is not allowed _(contains `/`)_
- `John D. Smith` is not allowed _(contains `.`)_
- `D'Angelo` is not allowed _(contains `'`)_
- Elon Musk's son's name `X AE A-XII` is not allowed _(contains `-`)_

In order to make the app more inclusive, the name field will be enhanced to allow the characters `.`, `'`, `'`, but not the `/` symbol as in `Roy s/o Balakrishnan`, as in this case it can be parsed as the `s/` prefix and cause other problems.

We still plan to allow numbers in names to allow users to number their student names _(eg. `Shaun 1`, `Shaun 2`)_ in the case where students having same names.

#### Feature flaw 3
Currently, duplicate Telegram handle and phone fields are allowed. This means that entries like `add n/Shaun p/000` and `add n/Shao Hong p/000` as well as `add n/Shaun tele/@sh123` and `add n/Shao Hong tele/@sh123` are valid.
However, in reality phone numbers and telegram handles are unique so our future implementations will check that the student list can only contain unique telegram handles and phone numbers.

#### Feature flaw 4
Currently, the `edit` feature allows editing all fields except for remarks and the only way to edit remarks is by using the `remark` feature which may inconvenience users. We planned to improve the `edit` feature to support `edit INDEX r/`, allowing users to edit their remarks directly from the command line.

### Autocomplete
#### Feature flaw
Currently, the autocomplete simply checks that the given prefixes and its parameters are valid, however for the `add` feature, the autocomplete doesn't check for the presence of the compulsory `n/NAME` input which
leads users to believe that their input (without `n/NAME`) is valid. Following the requirements of the `add` feature, we plan to improve autocomplete by ensuring it checks for `n/NAME`.

[↑ Back to top](#table-of-contents)

### Find/Filter
#### Feature flaw
Currently, we don't explicitly handle the case of argument-less `find`/`filter` nor do we disallow it, which results in the behaviour where argument-less `find` shows all users, while `filter` shows none.

Let's say argument-less `find`/`filter` is allowed, the possible behaviours could be:

* to list all persons _(which is the purpose of `list`)_
* to list no persons _(which is not useful)_

Both behaviours don't add value to the app. Thus, we plan to disallow argument-less `find`/`filter` commands and give an error message encouraging users to add arguments if they use `find`/`filter` without any arguments.

[↑ Back to top](#table-of-contents)

### Ui
#### Feature flaw
Currently, all the labels except for remarks are truncated. When the texts are too long, they do not wrap, especially for long tags and when the window is resized. To improve user experience, we plan to wrap text for long names, address, email, telegram handle, and the tags component.

[↑ Back to top](#table-of-contents)

### General
#### Feature Flaw
Currently, the user will experience noticeable performance issues starting from around ten entries, with the lag becoming more significant the more entries there are.
We plan to optimise the application by making saving, reading and writing data to and from the local save file more efficient, as well as optimise the commands to be more efficient 
to tackle this issue in the future.

[↑ Back to top](#table-of-contents)

### Error handling
#### Feature flaw
Currently, the error message for an invalid telegram handle is "Telegram handle can take any valid telegram handle, and it should not be blank." We plan to replace it with a more helpful error message 
such as "A telegram handle should be in the form @abc_hi." in addition to the current message.
There the requirements for a telegram handle can be detailed so users can reference the error message and improve their input.

[↑ Back to top](#table-of-contents)

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder
   2. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   2. Re-launch the app by double-clicking the jar file.  \\
      Expected: The most recent window size and location is retained.

### Command suggestion and autocompletion

1. Suggesting and autocompleting a command

   1. Test case: Type `a`, then press `TAB` key  \\
      Expected: It displays the suggestion for the `add` command along with all its arguments, then autocompletes the word "add" upon pressing `TAB`.

1. Suggesting and autocompleting arugment prefixes

   1. Test case: Type `add`, then press `TAB` key  \\
      Expected: It displays the suggestion for the `add` command's arguments, then autocompletes first argument `n/` upon pressing `TAB`.

   1. Test case: Type `add e`, then press `TAB` key  \\
      Expected: It displays the suggestion for the `add` command's arguments that start with "e", then autocompletes first such argument `e/` upon pressing `TAB`.

1. Suggesting and autocompleting existing arugment values

   1. Prerequisites: A student in the list has the tag `tag1`, and another having the tag `tag2`, and no other student has tags other than `tag1` and `tag2`.

   1. Test case: Type `add t/`, then press `TAB` key  \\
      Expected: It displays the suggestion for the 2 existing tags as `tag1 | tag2`, then autocompletes first existing tag `tag1` upon pressing `TAB`.

### Deleting a student / multiple students

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   2. Test case: `delete 1`  \\
      Expected: First entry is deleted from the list. Details of the deleted student shown in the status message.

   3. Test case: `delete 0` \\
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size) \\
      Expected: Similar to previous.
   
2. Deleting multiple students while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.
   
   2. Test case: `delete 1 2`  \\
      Expected: First and second entries are deleted from the list. Details of the deleted students shown in the status message.
   
   3. Test case: `delete 0 1`  \\
      Expected: No students are deleted. Error details shown in the status message. Status bar remains the same.

3. Deleting one / multiple student(s) while on a filtered list

   1. Prerequisites: Filter the students using the `find` or `filter` command such that there are at least **2** students in the filtered list.
   
   2. Test case: `delete 1`  \\
      Expected: First entry is deleted from the list. Details of the deleted student shown in the status message.
   
   3. Test case: `delete 1 2`  \\
      Expected: First and second entries deleted from the list. Details of the deleted students shown in the status message.
   
   4. Test case: `delete 0 1`  \\
      Expected: No students are deleted. Error details shown in the status message. Status bar remains the same.


### Adding a student

1. Adding a student while all students are being shown

   1. Prerequisites: List any existing students using the `list` command.

   2. Test case: `add n/Shaun` \\
      Expected: New student is added to the bottom of the student list. Details shown only include the name.
   
   3. Test case: `add n/Shaun n/Benny` \\
      Expected: New student is added to the bottom of the student list. Details shown only includes the last parameter specified, `Benny`.

   4. Test case: `add n/Shaun xxx/PARAMETER...` (where xxx is any of the valid prefixes, PARAMETER is any valid input for the respective prefix and ... indicates any number of optional prefixes etc.) \\
      Expected: Similar to previous, details included in the entry are the valid inputs specified.

   5. Test case: `add p/999` \\
      Expected: No student is added to the list. The error message shown details `Invalid command format!` followed by the `add` command format and example.

   6. Test case: `add` \\
      Expected: No student is added to the list. Same as previous.

   7. Test case: `add n/???` \\
      Expected: No student is added to the list. The error message shown details the requirements for a valid name parameter input.

   8. Test case: `add n/Shaun xxx/` \\
      Expected: No student is added to the list. Same as previous.

   9. Other incorrect `add` commands to try: `add n/Shaun xxx/INVALID_PARAMETER` (where xxx is any of the valid prefixes, INVALID_PARAMETER is an invalid parameter input for the respective prefix). \\
      Expected: Same as previous, except error message is xxx prefix specific.

### Editing a student entry
  
1. Editing a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.
   
   2. Test case: `edit 1 n/Shaun` \\
      Expected: Student entry at position 1 of the student list will have their name updated to `Shaun`
   
   3. Test case: `edit 1 n/Shaun n/Benny` \\
      Expected: Student entry at position 1 of the student list will have their name updated to the last parameter, `Benny`.

   4. Test case: `edit 1 xxx/PARAMETER...` (where xxx is a valid prefix and PARAMETER is a valid input for the respective prefix and ... indicates any number of additional xxx/PARAMETER) \\
      Expected: Same as before except updated/added detail(s) are the specified inputs.
   
   5. Test case: `edit` \
      Expected: No student in the list is edited. The error message shown details `Invalid command format!` followed by the `edit` command format and example.
   
   6. Test case: `edit 1 i/` \\
      Expected: Same as previous.

   7. Test case: `edit 1` \\
      Expected: No student in the list is edited. The error message shown states `The person index provided is invalid`.

   8. Test case: `edit xxx` (where xxx is any invalid index such as 0, -1, an index greater than the length of the list) \\
      Expected: Same as previous.
   
   9. Test case: `edit 1 n/???` \\
      Expected: No student in the list is edited. The error message shown details the requirements for a valid name parameter input.
   
   10. Test case: `edit 1 xxx/INVALID_PARAMETER...` (where xxx is a valid prefix and INVALID_PARAMETER is an invalid parameter input for the respective prefix) \\
       Expected: Similar to previous, except error message is specific to the first prefix with an invalid parameter.

### Find student entries

1. Finding students while all students are being shown

   1. Prerequisites: List all students using the `list` command.
   
   2. Test case: `find` \\
      Expected: All students will be listed.
      
   3. Test case: `find xxx/` (where xxx is any parameters other than t/ and m/) \\
      Expected: All students will be listed.
      
   4. Test case: `find x/` (where xxx is parameters t/ or m/) \\
      Expected: No students will be listed.
      
   5. Test case: `find yyy` (where yyy is anything) \\
      Expected: Error message shows `Invalid command format!` followed by the `find` command format and example.
      
   6. Test case: `find xxx/KEYWORD` (where xxx is any valid prefix except t/ and m/ and yyy is any keyword) \\
      Expected: Entries in the field of xxx with data partially matching with the whole of yyy will be shown.
      
   7. Test case: `find x/KEYWORD` (where xxx is either t/ or m/, yyy is any keyword) \\
      Expected: Entries in the x field with data that fully matches with yyy will be shown.
      
   8. Test case: `find xxx/KEYWORD...` (where aaa is any valid prefix, xxx is any keyword, ... refers to multiple aaa/xxx) \\
      Expected: Entries that matches with all fields of aaa will be shown.
      
   9. Test case: `find INVALID_PREFIX/KEYWORD` \\
      Expected: Error message shows `Invalid command format!` followed by the `find` command format and example.

### Filter student entries

1. Filtering students while all students are being shown

  1. Prerequisites: List all students using the `list` command.

  2. Test case: `filter` \\
     Expected: No students will be listed.

  3. Test case: `filter xxx/` (where xxx is any parameters other than t/ and m/) \\
     Expected: All students will be listed.

  4. Test case: `filter x/` (where xxx is parameters t/ or m/) \\
     Expected: No students will be listed.

  5. Test case: `filter yyy` (where yyy is anything) \\
     Expected: Error message shows `Invalid command format!` followed by the `find` command format and example.

  6. Test case: `filter xxx/KEYWORD` (where xxx is any valid prefix except t/ and m/ and yyy is any keyword) \\
     Expected: Entries in the field of xxx with data partially matching with the whole of yyy will be shown.

  7. Test case: `filter x/KEYWORD` (where xxx is either t/ or m/, yyy is any keyword) \\
     Expected: Entries in the x field with data that fully matches with yyy will be shown.

  8. Test case: `filter xxx/KEYWORD...` (where aaa is any valid prefix, xxx is any keyword, ... refers to multiple aaa/xxx) \\
     Expected: Entries that matches with at least one fields of aaa will be shown.

  9. Test case: `filter INVALID_PREFIX/KEYWORD` \\
     Expected: Error message shows `Invalid command format!` followed by the `find` command format and example.
     
### Showing a student entry

1. Showing a student entry while all students are being shown

   1. Prerequisites: List all students using the `list` command. At least 1 student in the list.
   
   2. Test case: `show 1` \\
      Expected: Student entry at position 1 shows up in the `ResultPersonListPanel` on the bottom right of the window. Details shown includes all non-empty fields and the full (non-truncated) remark if any.
   
   3. Test case: `show 1 EXTRANEOUS_INPUTS` (where EXTRANEOUS_INPUTS are irrelevant inputs) \\
      Expected: No student entry is shown. The error message shown details `Invalid command format!` followed by the `show` command format and example.

   4. Test case: `show` \\
      Expected: Same as previous.

   5. Test case: `show 0` \\
      Expected: No student entry is shown. The error message shown states `The person index provided is invalid`.
   
   6. Other incorrect show commands to try: `show xxx` (where xxx is an invalid number/characters such as -1, or a number greater than the list size) \\
      Expected: Same as previous.

### Editing a student's remarks

1. Editing a student's remarks while all students are being shown

   1. Prerequisites: List all students using the `list` command. At least 1 student in the list.
   
      1. Test case: `remark 1` followed by entering `This is a test remark`, then `CTRL` + `S` to save \
         Expected: Pop up window appears for the entering of remarks. Status message is "Editing remarks...". Upon pressing `CTRL` + `S`, pop up window closes and remarks for first entry edited to become "This is a test remark".
   
      2. Test case: `remark 0` \
         Expected: No pop-up box appears. The error message shown states `The person index provided is invalid`.
   
   2. Prerequisites: Student at index 1 has some remarks written.
   
      1. Test case: `remark 1` followed by removing all existing remarks, then `CTRL` + `S` to save \
         Expected: Pop up window appears for the entering of remarks. Status message is "Editing remarks...". The remarks for the first entry is displayed in the text box. After pressing `CTRL` + `S` after removing all existing remarks, pop up window closes and remarks for first entry is deleted.
   
      2. Test case: `remark 1 This is a test remark` \
         Expected: No pop up window appears. Status message displays details of first entry, with the remark changed to `This is a test remark`.
   
   3. Other incorrect remark commands to try: `remark`, `remark x`, `remark -1`. \
      Expected: Pop up window does not appear. No remarks are edited. Error details shown in the status message. Status bar remains the same.

[↑ Back to top](#table-of-contents)

### Saving data

1. Dealing with missing/corrupted data files

   1. Go into the (non-empty) data file, and remove the final `}` which should be on the last line. The json object is now invalid.
   2. Start up TeachMeSenpai again.
   3. Student list should now be populated with the dummy/default student data, regardless of the data that was in the data file previously. 

[↑ Back to top](#table-of-contents)

