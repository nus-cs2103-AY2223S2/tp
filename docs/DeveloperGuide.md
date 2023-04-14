---
layout: page
title: Developer Guide
---

<!-- omit from toc -->
## **Table of Contents**

- [**Legend**](#legend)
- [**Acknowledgements**](#acknowledgements)
- [**Setting up, getting started**](#setting-up-getting-started)
- [**Design**](#design)
  - [Architecture](#architecture)
  - [UI component](#ui-component)
  - [Logic component](#logic-component)
  - [Model component](#model-component)
  - [Storage component](#storage-component)
  - [Common classes](#common-classes)
- [**Implementation**](#implementation)
  - [Undo feature](#undo-feature)
    - [Undo: Current implementation](#undo-current-implementation)
    - [Undo: Design considerations](#undo-design-considerations)
  - [Filter feature](#filter-feature)
    - [Filter: Current implementation](#filter-current-implementation)
  - [Copy feature](#copy-feature)
    - [Copy: Current implementation](#copy-current-implementation)
  - [Favorite feature](#favorite-feature)
    - [Favorite: Current implementation](#favorite-current-implementation)
  - [New army-specific fields](#new-army-specific-fields)
    - [New army fields: Current implementation](#new-army-fields-current-implementation)
- [**Documentation, logging, testing, configuration, dev-ops**](#documentation-logging-testing-configuration-dev-ops)
- [**Appendix: Requirements**](#appendix-requirements)
  - [Product scope](#product-scope)
  - [User stories](#user-stories)
  - [Use cases](#use-cases)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
- [**Appendix: Instructions for manual testing**](#appendix-instructions-for-manual-testing)
  - [Launch and shutdown](#launch-and-shutdown)
  - [Adding a person](#adding-a-person)
  - [Editing a person](#editing-a-person)
  - [Deleting a person](#deleting-a-person)
  - [Toggling the favorite status of a person](#toggling-the-favorite-status-of-a-person)
  - [Saving data](#saving-data)
- [**Appendix: Effort**](#appendix-effort)
- [**Appendix: Planned Enhancements**](#appendix-planned-enhancements)
  - [Feature Flaw 1: Tags are case-sensitive](#feature-flaw-1-tags-are-case-sensitive)
  - [Feature Flaw 2: Error message for invalid `add` command is not specific](#feature-flaw-2-error-message-for-invalid-add-command-is-not-specific)
  - [Feature Flaw 3: Lack of input validation in `filter` feature](#feature-flaw-3-lack-of-input-validation-in-filter-feature)
  - [Feature Flaw 4: Slashes can be used in the `unit`, `platoon`, and `company` fields](#feature-flaw-4-slashes-can-be-used-in-the-unit-platoon-and-company-fields)
  - [Feature Flaw 5: Error message from importing CSV file with invalid fields does not show row number of problematic fields](#feature-flaw-5-error-message-from-importing-csv-file-with-invalid-fields-does-not-show-row-number-of-problematic-fields)

---

## **Legend**

Here are some symbols used throughout the user guide to inform you of additional details.

:information_source: **Notes:** Notes aim to provide you with extra information.

:bulb: **Tip:** Tips are useful suggestions that you can follow.

[Back to Top ↑](#table-of-contents)

---

## **Acknowledgements**

- This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
- Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5), [Opencsv](https://opencsv.sourceforge.net).

[Back to Top ↑](#table-of-contents)

---

## **Setting up, getting started**

Refer to the guide [*Setting up and getting started*](SettingUp.md).

[Back to Top ↑](#table-of-contents)

---

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-W10-3/tp/tree/master/docs/diagrams/) folder. Refer to the [*PlantUML Tutorial* at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

[Back to Top ↑](#table-of-contents)

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,

- At app launch: Initializes the components in the correct sequence, and connects them up with each other.

- At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

- [**`UI`**](#ui-component): The UI of the App.
- [**`Logic`**](#logic-component): The command executor.
- [**`Model`**](#model-component): Holds the data of the App in memory.
- [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

- defines its *API* in an `interface` with the same name as the Component.
- implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point)

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

[Back to Top ↑](#table-of-contents)

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- executes user commands using the `Logic` component.
- listens for changes to `Model` data so that the UI can be updated with the modified data.
- keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
- depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

[Back to Top ↑](#table-of-contents)

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

- When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
- All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

[Back to Top ↑](#table-of-contents)

### Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

- stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
- stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate *filtered* list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- stores a `UserPref` object that represents the user's preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
- does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">

:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

[Back to Top ↑](#table-of-contents)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

- can save both address book data and user preference data in json format, and read them back into corresponding objects.
- inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
- depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

[Back to Top ↑](#table-of-contents)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

[Back to Top ↑](#table-of-contents)

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Undo feature

#### Undo: Current implementation

The current undo mechanism is handled by `AddressBook`. It stores the address book history internally as an `addressBookStateList` and uses a `currentStatePointer` to track where the current address book state is in the history. Additionally, it implements the following operations:

- `AddressBook#commit()` — Saves the current address book state in its history.
- `AddressBook#undo()` — Restores the previous address book state from its history.

These operations are exposed in the `Model` interface as `Model#commit()` and `Model#undo()` respectively.

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

<br>

Given below is an example usage scenario and how the undo mechanism behaves at each step:

1. The user launches the application for the first time. The `AddressBook` will be initialized with the initial address book state, and the `currentStatePointer` will point to that single address book state.

   <div style="text-align: center;">

      <img src="images/UndoState0.png">

      <p style="font-style: italic;"><br>Initial state of the address book history upon startup</p>

      <br>

   </div>

1. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commit()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

   <div style="text-align: center;">

      <img src="images/UndoState1.png">

      <p style="font-style: italic;"><br>State of the address book history after calling <code>delete 5</code></p>

      <br>

   </div>

1. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commit()`, causing another modified address book state to be saved into the `addressBookStateList` as shown below.

   <div style="text-align: center;">

      <img src="images/UndoState2.png">

      <p style="font-style: italic;"><br>State of the address book history after calling <code>add n/David …​</code></p>

      <br>

   </div>

   <div markdown="span" class="alert alert-info">

   :information_source: **Note:** If a command fails its execution (e.g., the `add` command syntax was incorrect), it will not call `Model#commit()`, so the address book state will not be saved into the `addressBookStateList`.

   </div>

1. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undo()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

   <div style="text-align: center;">

      <img src="images/UndoState3.png">

      <p style="font-style: italic;"><br>State of the address book history after calling <code>undo</code></p>

      <br>

   </div>

1. The user then decides to execute the command `list`. Commands that do not modify the address book (such as `list`) will usually not call `Model#commit()` or `Model#undo()`. Thus, the `addressBookStateList` remains unchanged.

   <div style="text-align: center;">

      <img src="images/UndoState4.png">

      <p style="font-style: italic;"><br>State of the address book history after calling <code>list</code>. Note that it hasn't changed from the previous figure above</p>

      <br>

   </div>

1. The user decides to call the `undo` command again.

   <div style="text-align: center;">

      <img src="images/UndoState5.png">

      <p style="font-style: italic;"><br>State of the address book history after calling <code>undo</code> again</p>

      <br>

   </div>

   <div markdown="span" class="alert alert-info">

   :information_source: **Note:** The `undo` command will first call `Model#canUndo()` to check if there are address book states to restore. If there are none (i.e., the `currentStatePointer` is at index 0 already), it will return an error to the user rather than attempt to perform the undo.

   </div>

   <br>

1. The user executes `clear`, which calls `Model#commit()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

   <div style="text-align: center;">

      <img src="images/UndoState6.png">

      <p style="font-style: italic;"><br>State of the address book history after having "overwritten" old states</p>

      <br>

   </div>

#### Undo: Design considerations

**Aspect: How undo executes:**

- **Alternative 1 (current choice):** Saves the entire address book.

  - Pros: Easy to implement.
  - Cons: May have performance issues in terms of memory usage.

- **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  - Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  - Cons: We must ensure that the implementation of each individual command are correct.

[Back to Top ↑](#table-of-contents)

### Filter feature

#### Filter: Current implementation

The current filter feature is facilitated by `FilterCommand` which extends `Command`. The `FilterCommand`
has a constructor that requires a non-null `FilterDescriptor`, which is an inner class of `FilterCommand`.
It is used to store the desired filter's information. `FilterDescriptor` has all the fields that a `Person`
object has (i.e. `Phone`, `Email`,`Rank`, etc), except that the field values can be empty
and do not need to follow any format or restriction.

When `FilterCommand` receives a valid `FilterDescriptor`, it creates a `FieldContainsPartialKeywordsPredicate`
using all of the `FilterDescriptor`'s information. This `Predicate` is used go through all the `Person` objects that are
currently in the `Model`. A `Person` is filtered out if it does not contain the keyword in the corresponding field.

The following sequence diagram shows an example of how the filter feature runs with user input:
`filter e/gmail r/3sg`.

![FilterSequenceDiagram](images/FilterSequenceDiagram.png)

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifeline for `FilterCommandParser` and `FilterCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

[Back to Top ↑](#table-of-contents)

### Copy feature

#### Copy: Current implementation

The copy feature is facilitated by `CopyCommand` which extends `Command`. It is implemented by extracting information of the specified `Person` and then copied into the user's clipboard. The clipboard is first accessed by making use of the Java Abstract Window Toolkit (AWT), followed by setting the extracted information as the contents of the clipboard by invoking the `Clipboard#setContents()` method.

Since the information of a `Person` is required, the `Model#getFilteredPersonList()` operation is invoked to retrieve the specified `Person` and the information is extracted and copied into the user's system's clipboard.

The following sequence diagram shows how the copy operation works:
![CopySequenceDiagram](images/CopySequenceDiagram.png)

<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifeline for `CopyCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

In the scenario where the user's system's clipboard is not accessible, the requested information will be displayed in the UI for the user to manually copy it.

[Back to Top ↑](#table-of-contents)

### Favorite feature

#### Favorite: Current implementation

The favorite feature is implemented by adding a new field `isFavorite` to the `Person` class.
The favorite mechanism is facilitated by `FavoriteCommand` which extends `Command`. When the user executes
a `FavoriteCommand`, the specified `Person` will be edited so that the `isFavorite` field of the specified `Person` will be toggled between `true` and `false`.
The `ModelManager` maintains a `favoritedPersons` list which contains all the `Person` objects that have been favorited.
The `favoritedPersons` list can be kept up-to-date because it uses a predicate to select all the `Person` objects
that have the `isFavorite` field set to `true`.

The UI listens to changes to this `favoritedPersons` list and updates the UI accordingly.

[Back to Top ↑](#table-of-contents)

### New army-specific fields

#### New army fields: Current implementation

The new army-specific fields are `rank`, `unit`, `company` and `platoon`.

- We made the `rank` field compulsory since we are only dealing with army personnel (i.e., everyone should have a `rank`).
  - `rank` is not a free-response field as `"ABCDEF"` is *not* a valid rank. For now, `rank` can only take on the values `"REC"`, `"PTE"`, `"CPL"`, `"CFC"`, `"3SG"`, `"2SG"`, `"1SG"`, `"SSG"`, `"MSG"`, `"3WO"`, `"2LT"`, `"LTA"`, `"CPT"`, `"MAJ"` and `"CIV"` -- we intend to expand this list to include all valid ranks in the future.
- We made the `unit`, `company` and `platoon` fields optional as military personnel might not always be assigned to a unit, company, and/or platoon.
  - If the user omitted the `unit`, `company` and/or `platoon` fields when creating a new contact, they will be automatically set to `"N/A"`.

[Back to Top ↑](#table-of-contents)

---

## **Documentation, logging, testing, configuration, dev-ops**

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

[Back to Top ↑](#table-of-contents)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**: Military admin clerk who handles information of other military personnel

**Value proposition**: Contains dedicated fields in a contact card that manages military-specific information (e.g., rank, company, unit, etc.)

[Back to Top ↑](#table-of-contents)

### User stories

Priority:

- 1 = must have
- 2 = nice to have
- 3 = unlikely to have

<br>

| Priority | As a/an...                | I can...                                                                                | so that...                                                                                                     |
| -------- | ------------------------- | --------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| 1        | fast typer                | type in a command and the contact details to create a new contact                       | I can save time (instead of using the mouse to interact with the software which is slower)                     |
| 1        | fast typer                | type in a command to delete a contact                                                   | I can save time (instead of using the mouse to interact with the software which is slower)                     |
| 1        | fast typer                | type in a command to view all contacts                                                  | I can save time (instead of using the mouse to interact with the software which is slower)                     |
| 2        | beginner                  | view the user guide                                                                     | I look up the syntax of a command that I had forgotten                                                         |
| 2        | beginner                  | search for a contact by name                                                            | I can save time (instead of searching manually)                                                                |
| 2        | beginner                  | edit an existing contact                                                                | I don't have to create an entirely new contact just because of a small typo                                    |
| 2        | beginner                  | tag contacts with extra information (e.g., leader of XYZ)                               | I can find the leader of XYZ easily                                                                            |
| 2        | seasoned user             | filter and sort contacts                                                                | I can quickly find contacts that meet the criteria (instead of searching manually)                             |
| 3        | security conscious person | set up an app password                                                                  | I can prevent others from reading the sensitive information on the military personnel                          |
| 3        | security conscious person | change the app password                                                                 | I can ensure the security of the password                                                                      |
| 3        | new user                  | import contacts from a CSV file                                                         | I can easily migrate from other contacts management systems                                                    |
| 3        | clumsy user               | undo the previous action                                                                | I can rectify grave mistakes quickly                                                                           |
| 3        | forgetful user            | search for a contact by any identifying details                                         | I can still find a contact even if I have forgotten the person's name                                          |
| 3        | busy user                 | see recently viewed contacts                                                            | I can revisit previously accessed contacts quickly (instead of searching for them again)                       |
| 3        | busy user                 | generate a nicely formatted text template that contains all of the person's information | I do not need to re-type the same headers / tables whenever I send a new email                                 |
| 3        | seasoned user             | save commonly used contacts as favourites                                               | I can quickly look up commonly used contacts                                                                   |
| 3        | seasoned user             | hide unavailable personnel (using filters)                                              | I can see only those people that are currently available                                                       |
| 3        | seasoned user             | be shown daily tips on how to use some advanced features of AIMS                       | I can continuously learn how to save even more time (and become an advanced user)                              |
| 3        | seasoned user             | see a count of the number of people in the current list                                 | I can quickly calculate the number of people in each subset of the military                                    |
| 3        | advanced user             | export all AIMS data to a AIMS data file                                              | I don't lose my data when I change computers / departments                                                     |
| 3        | advanced user             | import all AIMS data from a AIMS data file                                            | I don't lose my data when I change computers / departments                                                     |
| 3        | advanced user             | delete all the data on the AIMS                                                        | I can ensure that the sensitive data will not remain on the old computer when I change computers / departments |
| 3        | advanced user             | automatically see the ORD date of a recruit                                             | I can inform them of the administrative processes that must completed before they ORD                          |
| 3        | advanced user             | mark that a person is on leave from `start_date` to `end_date`                          | I can tell if the soldier is available or not (and when he will be available)                                  |
| 3        | advanced user             | make simultaneous edits to multiple contacts at once (e.g., update rank)                | I can save time by rectifying mass mistakes / save time by updating multiple people's information quickly      |
| 3        | advanced user             | view two contacts side by side                                                          | I can compare two contacts side by side easily (instead of having to open up another instance of the app)      |
| 3        | advanced user             | save commonly used combinations of filters as favourites                                | I can view the updated data quickly without having to apply the same combination of filters again              |
| 3        | inaccurate typer          | search for contacts even with typos (fuzzy search)                                      | I can search fast even with minor typos                                                                        |
| 3        | slower typer              | see suggested names when searching                                                      | I can easily autocomplete my search query                                                                      |
| 3        | slower typer              | see all contacts that match my current search query even before I press ENTER           | I do not need to type out my complete search query to start seeing results                                     |
| 3        | ration manager            | filter and count the number of people that need halal meals / are allergic to seafood   | I know how many halal / non-seafood food packs to order                                                        |
| 3        | artistic user             | edit the theme of the app/font of the UI                                                | I can personalize the look of the app to be more aesthetically pleasing to me                                  |
| 3        | person with bad eyesight  | increase the font size/UI size of the whole app                                         | I can see text better                                                                                          |

[Back to Top ↑](#table-of-contents)

### Use cases

(For all use cases below, the **System** is the `Army Information Management System (AIMS)`, unless specified otherwise)

**Use case: UC2 - Create a new military personnel**

**Actor: Admin clerk**

**MSS**

1. Admin clerk creates a new military personnel contact by entering the command.
1. AIMS creates and displays the newly created military personnel contact to the admin clerk.

   Use case ends.

**Extensions**

- 1a. AIMS detects an error in the command (e.g. missing information, incorrect syntax).
  - 1a1. AIMS displays an error message to the admin clerk.
  - 1a2. Admin clerk re-enters the information to create the military personnel contact.
  - Steps 1a1-1a2 are repeated until the information provided is acceptable.
  - Use case resumes at step 2.

---

**Use case: UC101 - Update the rank of person named "Lawrence Tay"**

**Actor: Admin clerk**

**MSS**

1. Admin clerk <ins>searches for a person named "Lawrence Tay" (UC5)</ins>.
1. Admin clerk <ins>edits the *rank* information of "Lawrence Tay" (UC6)</ins>.
1. AIMS displays the updated *rank* information of "Lawrence Tay".

   Use case ends.

**Extensions**

- 1a. After searching, there is more than one "Lawrence Tay" in the displayed list.
  - 1a1. Admin clerk scrolls through the list of "Lawrence Tay"s and mentally notes down the index of the exact "Lawrence Tay" person she is looking for.
  - Use cases resumes at step 2.

---

**Use case: UC102 - Import contacts from CSV file**

**Actor: Admin clerk**

**MSS**

1. Admin clerk chooses to import a CSV file containing the personal information of military personnel from her computer.
1. AIMS imports the CSV file.
1. AIMS prompts the admin clerk whether the information in the CSV file should *replace* or be *added* to the existing list of contacts.
1. Admin clerk chooses one of the options.
1. If the option was to *replace*, AIMS will delete all existing all contacts. Otherwise, AIMS does nothing in this step.
1. AIMS adds the CSV contacts to the existing list of contacts.

   Use case ends.

**Extensions**

- 2a. AIMS is unable to automatically match the CSV column names to AIMS contact fields.
  - 2a1. AIMS prompts the admin clerk to decide which CSV column refer to which AIMS contact field (e.g., the CSV might have a column called "mobile_number" whereas AIMS has a field called "phone").
  - Use case resumes at step 3.

[Back to Top ↑](#table-of-contents)

### Non-Functional Requirements

1. AIMS must store the personal data of military personnel securely.
1. AIMS must only allow the authorised admin clerk access to the military personnel data (in compliance with PDPA).
1. AIMS must not leak the personal data of military personnel even during a computer crash.
1. AIMS must not connect to the Internet.
1. AIMS must be able to handle at least 1000 military personnel contacts.
1. AIMS should be easy to use even for non-technical persons like an admin clerk.
1. AIMS should be efficient enough that it performs well even on lower-tier hardware (e.g., like those found in everyday office computers that admin clerks use).
1. AIMS should respond within 1 second for any command the user inputs.
1. AIMS must not lose critical data on military personnel even during a computer crash.
1. AIMS must be accessible to military personnel with disabilities.

[Back to Top ↑](#table-of-contents)

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Private contact detail**: A contact detail that is not meant to be shared with others
- **Non-technical person**: Someone who is able to execute basic tasks using the computer's point-and-click interface and has the ability to use simple GUI applications.

[Back to Top ↑](#table-of-contents)

---

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch
   1. Download the jar file and copy into an empty folder
   1. Double-click the jar file <br>
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.
1. Saving window preferences
   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

[Back to Top ↑](#table-of-contents)

### Adding a person

1. Adding a person while all existing persons are being shown
   1. Prerequisites: The existing list contains a person with the following information:

   ``` text
    Rank: REC
    Name: Alex Yeoh
    Unit: 2 SIR
    Company: Charlie
    Platoon: 2
    Phone: 87438807
    Email: alexyeoh@example.com
    Address: Blk 30 Geylang Street 29, #06-40
    Tags: [friends]
   ```

   2. Test case: `add n/Jack Wang r/3SG c/Alpha u/2 SIR p/98310925 e/jackywang@gmail.com a/Blk 19 Ghim Moh Rd, #04-10`<br>
   Expected: A person with the specified information will be added and displayed in the main list of the GUI.

   3. Test case: `add n/Alex Yeoh r/CPL c/Bravo u/1 SIR p/89043761 e/alexyeoh@example.com a/Blk 21 Buona Vista Rd, #11-01`<br>
   Expected: Error details shown in the message box, indicating that duplicate emails or phones are not allowed.

   4. Test case: `add n/Zachary Tan r/CPL`<br>
   Expected: Error details shown in the message box, indicating that the command entered is invalid.

[Back to Top ↑](#table-of-contents)

### Editing a person

1. Editing a person while all persons are being shown
   1. Prerequisites: The second person in the existing list contains the following information:

   ``` text
    Rank: CPL
    Name: Bernice Yu
    Unit: 1 GDS
    Company: Charlie
    Platoon: 2
    Phone: 99272758
    Email: berniceyu@example.com
    Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18
    Tags: [colleagues][friends]
   ```

   2. Test case: `edit 2 n/Sally Wee e/sallywee@gmail.com`<br>
      Expected: The name and email of the second person in the list will be changed to `Sally Wee` and `sallywee@gmail.com` respectively, with the other information left unchanged.

   3. Test case: `edit 2 t/`<br>
      Expected: All existing tags of the second person in the list will be removed, with the other information left unchanged.

[Back to Top ↑](#table-of-contents)

### Deleting a person

1. Deleting a person while all persons are being shown
   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.
   1. Test case: `delete 1`<br>
      Expected: First person is deleted from the list. Details of the deleted person shown in the status message. Timestamp in the status bar is updated.
   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.
   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

[Back to Top ↑](#table-of-contents)

### Toggling the favorite status of a person

1. Toggling the favorite status of a person while all persons are being shown
   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list. No current favorited persons in the favorites list.
   2. Test case: `favorite 1`<br>
      Expected: First person is added to the favorites list. Person is pinned in the favorites list on the left.
   3. Test case: `favorite 1` followed by `favorite 1`<br>
      Expected: First person is added to the favorites list and then removed. Person is pinned in the favorites list on the left after the first command and then unpinned after the second command.
   4. Test case: `favorite 0`<br>
      Expected: No person is toggled as favorite. Error details shown in the message box.

[Back to Top ↑](#table-of-contents)

### Saving data

1. Dealing with missing/corrupted data files
   1. Prerequisite: The current data file `aims.json` exists in the `./data` folder.
   2. Test case: Delete the `aims.json` file in the `./data` folder and launch AIMS again.<br>
      Expected: AIMS loads a default list of persons upon launch and a new data file `aims.json` will be created and saved in the `./data` folder after exiting AIMS.

[Back to Top ↑](#table-of-contents)

---

## **Appendix: Effort**

- While adding new features, we realized that we needed to rethink some of the existing features. For example, when adding the `filter` feature, we needed to re-think the `find` feature as well since `find` originally only supports full word match (as opposed to partial word match) and only searches for name. To ensure consistency with `filter`, we augmented `find` to support partial word match and enabled it to search through all fields.
- Navigating the multiple levels of abstraction for AB3 was a challenge as well. Many of us were used to all logic being co-located in the same class or file, but AB3 employed various software engineering best practices such as separation of concerns, abstraction of details, etc. that took some time to understand and modify.
- Effort in implementing the `importcsv` command was reduced by reusing a library called Opencsv. Our work on adapting Opencsv to our product is contained in class Csv.java.

[Back to Top ↑](#table-of-contents)

---

## **Appendix: Planned Enhancements**

### Feature Flaw 1: Tags are case-sensitive

**Brief description:** When using the `add` or `edit` feature in AIMS, tags that are provided with the same names but in different cases (e.g. 1 in lowercase and 1 in uppercase), are treated as different tags which should not be the case.

**Example:** `add n/Jack Wang r/3SG c/Alpha u/2 SIR p/98310925 e/jackywang@gmail.com a/Blk 19 Ghim Moh Rd, #04-10 t/friends t/Friends` <br>
    In this command, the tags given are `friends` and `Friends` which have the same tag name but treated as 2 different tags because of an uppercase letter in `Friends`.

![featureflaw1](./images/featureflaw1.png)

**Proposed Enhancement:** We plan on improving the validation by making sure that tags are case-insensitive so that when 2 tags of same name and different cases are provided, an error message will be displayed to the user: `Duplicate tags are provided!`.

### Feature Flaw 2: Error message for invalid `add` command is not specific

**Brief description:** Our [`add`](./UserGuide.md#adding-a-person--add) command requires the user to provide several compulsory fields (rank, name, phone, email, address) in order to add a person to AIMS. However, in the case where the user happens to miss out on any fields, a generic invalid command format error message is displayed to them, not specifying which field is missing. As a result, this can cause inconvenience for the user as they may need to carefully look through the provided example to identify which field(s) is/are needed.

**Example:**
`add n/James Lee p/99190258 e/jameslee@email.com r/CPL c/Charlie` <br>
Upon entering the command above, a generic error message is displayed to the user, not specifying which field(s) is/are missing.
![featureflaw2](./images/featureflaw2.png)

**Proposed enhancement:**
Instead of just showing a valid example, we plan to change the error message such that it tells the user which specific field(s) is/are missing so that they can know at one glance what is further required.

### Feature Flaw 3: Lack of input validation in `filter` feature

**Brief description:** The `filter` feature allow users to search invalid values. For instance, for the phone number field, we have an input validation in the `add` feature which checks that the provided phone number is **valid** with only numbers and at least 3 digits long. However, the user can search for a phone number `abcd` using the `filter` command which is illogical and does not align with our implementation of the `add` feature.

**Example:** `filter p/abcd`<br>
Even though AIMS does give a correct result that there are 0 persons with the phone number `abcd` but this will always be the case since AIMS will never allow a person with phone number `abcd` to be added, so we should include the input validation in `filter` as well.
![featureflaw3](./images/featureflaw3.png)

**Proposed enhancement:**
We plan to include the same input validation that we used in the `add` feature for the `filter` feature. This will ensure that the user is informed when they are filtering using an invalid value.

### Feature Flaw 4: Slashes can be used in the `unit`, `platoon`, and `company` fields

**Brief description:** Slashes can be used in the `unit`, `platoon`, and `company` although it should not be allowed in these fields as it is a special character used to delimit command parameters.

**Example:** `edit 1 u/n/pl/alpha`<br>
Edit command for unit works even though the unit field contains slashes.
![featureflaw4](./images/featureflaw4.png)

**Proposed enhancement:**
We plan to make the default value of unit, rank, and platoon "NA" instead of "N/A" so that we can remove slashes from the list of allowed characters for `unit`, `platoon`, and `company`.

### Feature Flaw 5: Error message from importing CSV file with invalid fields does not show row number of problematic fields

**Brief description:** If any field is invalid when importing a CSV file using the `importcsv` command (i.e phone number has letter), the error message shown does not specify which row the problematic field is in.

**Example:** `importcsv`<br>
Importing a CSV file with an invalid phone number field shows an error message without specifying which row the invalid phone number is on.
![featureflaw5](./images/featureflaw5.png)

**Proposed enhancement:**
We plan to enhance the error message to tell the user which row number the invalid field is at, so that they can easily make changes to it without having to manually look for the error.

[Back to Top ↑](#table-of-contents)
