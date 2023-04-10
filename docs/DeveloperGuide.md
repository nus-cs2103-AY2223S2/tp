---
layout: page
title: Developer Guide
---
## **Table of Contents**

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## **Introduction to PetPal**
PetPal is a desktop application meant for Pet Daycare owners to manage their pet clients. It is optimized for typing
using the Command Line Interface (CLI) while also having a Graphical User Interface (GUI) to complement its use.

The use of PetPal can be scaled to include pet shelters, groomers or trainers.

PetPal uses Java 11 and can be run on most operating systems that support Java (e.g. Windows, macOS, Linux)



## **About the Developer Guide**
### Objectives
This guide is targeted towards potential developers that would want to contribute to the open-source project - PetPal.

This developer guide serves as a way for them to hopefully be able to learn about the overall design architecture of PetPal and
the current and proposed features, detailing the design considerations and how it is implemented/going to be implemented.

The Design section makes use of various UML diagrams which were created using [PlantUML](https://plantuml.com/)


<br>

### Instructions for use
#### General formatting conventions
* Text in [blue](#about-the-developer-guide) are hyperlinks that direct you to the relevant section of the page or other websites.
* Text in **bold** is used to emphasize important details to look out for or to distinguish headers from the rest of the text.
* Text in `code snippets such as this` is used to show inputs and their format.


<div markdown="block" class="alert alert-block alert-success">

* :bulb: **Note:**
  Information that is useful to note.
</div>

<div markdown="block" class="alert alert-block alert-danger">

* :heavy_exclamation_mark: **Caution:**
  Important information to note.
</div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Setting up & Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

After setting up, double-click the jar file to launch PetPal.


### Getting Familiar With Your User Interface

![Ui Breakdown](images/UI/UiHighlighted.png)

1. **Pet Cards**: Contain all the information of a pet. (Highlighted in red)
2. **Command Line**: Type in your commands here. (Highlighted in yellow)
3. **Result Display**: The result of your command execution appears here. (Highlighted in blue)
4. **Help Button**: Provides the URL of this user guide.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the
[diagrams](https://github.com/AY2223S2-CS2103T-T14-2/tp/tree/master/docs/diagrams) folder.
Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html)
to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/diagrams/ArchitectureDiagram.png" width="341"  alt=""/>

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of the main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/AY2223S2-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connect them up with each other.
* At shutdown: Shuts down the components and invoke cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/diagrams/ArchitectureSequenceDiagram.png" width="522"  alt=""/>

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside components from being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/diagrams/ComponentManagers.png" width="288"  alt=""/>

The sections below give more details about each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/diagrams/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `petListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFX UI framework. The layout of these UI parts is defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-T14-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-T14-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays a `pet` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/diagrams/LogicClassDiagram.png" width="737" alt=""/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `PetPalParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a pet).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/diagrams/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/diagrams/ParserClasses.png" width="648" alt=""/>

How the parsing works:
* When called upon to parse a user command, the `PetPalParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create an `XYZCommand` object (e.g., `AddCommand`) which the `PetPalParser` returns as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/diagrams/ModelClassDiagram.png" width="658"  alt=""/>


The `Model` component,

* stores the pet pal data i.e., all `pet` objects (which are contained in a `UniquepetList` object).
* stores the currently 'selected' `pet` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<pet>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `PetPal`, which `pet` references. This allows `PetPal` to only require one `Tag` object per unique tag, instead of each `pet` needing their own `Tag` objects.<br>

<img src="images/diagrams/BetterModelClassDiagram.png" width="437"  alt=""/>

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-T14-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/diagrams/StorageClassDiagram.png" width="567"  alt=""/>

The `Storage` component,
* can save both `PetPal` data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `PetPalStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.PetPal.commons` package.

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### **Remind Feature**

#### Current Implementation

The `remind` mechanism is facilitated by the `Deadline` and `RemindCommand`,  classes.
The `Deadline` class has a `deadline` field which is of type `LocalDateTime`. Additionally, it also has a description of type `String`.

'RemindCommand' extends from the abstract class `Command`. It overrides the `Command#execute()` method to filter the pet list to show only pets with `Deadline` that are within 3 days from today's date.

Given below is an example usage scenario and how the set file mechanism behaves at each step.
Given below is an example usage scenario and how the `remind` mechanism behaves at each step.

Step 1. The user launches the application for the first time.

Step 2. The user decides to add a pet to the pet list. The user executes `add o/Alice n/Doggo p/98765432 e/example@gmail.com a/311, Clementi Ave 2, #02-25 ts/2023-03-27 21:09:09 d/Feed dog - 2023-03-27 21:09:09 t/Dog t/Chihuahua` command to add a pet named `Doggo` with a reminder to feed the dog and deadline of `2023-03-27 21:09:09` to the pet list. The `add` command calls the `AddCommand#execute()` method.

Step 3. The user may exit and reopen PetPal at a future date. The user intends to see all deadlines that are due soon. The user executes the `remind` command to filter the pet list to show only pets with `Deadline` that are within 3 days from today's date. The `remind` command calls the `RemindCommand#execute()` method.

Step 4. The `RemindCommand#execute()` method calls the `Model#updateFilteredPetList()` method to filter the pet list to show only pets with `Deadline` that are within 3 days from today's date.

The following sequence diagram shows how the `remind` operation works:

![RemindSequenceDiagram](images/diagrams/RemindSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![RemindActivityDiagram](images/diagrams/RemindActivityDiagram.png)


#### Design Considerations:
**Choice 1 (Current Choice): Filter pet list upon command**
* Pros:
    * User can easily find upcoming deadlines.
* Cons:
    * Counterintuitive since reminders shouldn't need user input to be shown.

**Choice 2: Alert users of upcoming deadlines upon startup**
* Pros:
    * User will be reminded of upcoming deadlines upon startup.
* Cons:
    * Might be annoying to users who don't want to be reminded of upcoming deadlines.



### **Calculator Feature**

#### Current Implementation
The calculator mechanism is facilitated by the `AddCommand` class.
The calculator  calculates the amount of money owed by pet owners to the daycare owners,
based on an initial timestamp of type java `LocalDateTime` that is required to be non-null when
entering the `Add` command.

##### Given below is an example usage scenario and how the calculator mechanism behaves at each step:
Step 1. The user launches the application for the first time.

Step 2. The user decides to add a pet to the pet list. The user executes
`add o/Alice n/Doggo p/98765432 e/example@gmail.com a/311, Clementi Ave 2, #02-25 ts/2023-03-27 21:09:09 d/Feed dog -
2023-03-27 21:09:09 t/Dog t/Chihuahua` command to add a pet named `Doggo` a with reminder to feed the dog and the deadline of
`2023-03-27 21:09:09` to the pet list. The `add` command calls the `AddCommand#execute()` method.

Step 3. The user may exit and reopen PetPal at a future date. The user will see the amount due to them as a field in each pet card by their respective owners.

Step 4. The amount updates upon clicking on the PetCard on the panel, or upon restarting the client.

The calculator feature is not an additional command and does not have an activity or sequence diagram.




### **Archive Feature**

#### Current Implementation
The archive mechanism is facilitated by the `ArchiveCommand` class.
The `ArchiveCommand#execute()` adds the provided `Pet` into an archive list and deletes the `Pet` from the pet list,
the `Pet` must exist in the pet list, and the index provided must be a valid index of a pet in the current viewable pet list

##### Given below is an example usage scenario and how the set file mechanism behaves at each step:
```text
Step 1. The user launches the application for the first time
Step 2. The user decides to archive a pet in the pet list. The user executes `archive 1`
Step 3. The user can view the archived pets in the data/archive.json file
```
##### Extensions:
```text
Step 2a. The PetPal list does not have any pets, the pet at list position 1 does not exist
     3a. The PetPal returns an error message: `The provided index is out of bounds`
```

The following **sequence diagram** shows how the archive operation works:

![Archive Sequence Diagram](images/diagrams/ArchiveSequenceDiagram.png)

<br>
The following **activity diagram** summarizes what happens when a user executes a new archive command:

![Archive Activity Diagram](images/diagrams/ArchiveActivityDiagram.png)



### **Highlight Feature**

#### Current Implementation
The highlight mechanism is facilitated by the 'PetListPanel', 'Pet', and 'MarkCommand' classes.
This feature highlights pets that have not been marked and have a deadline within a day in the GUI.
The highlight feature will be executed automatically every certain time window without user input to support a real-time state.

#### Given below is an example usage scenario and how the highlight mechanism behaves at each step:
Step 1. The user launches the application.

Step 2. The user decides to add two pets to the pet list with a deadline due three days later.

Step 3. The given pet will not be highlighted at this moment.

Step 4. The user decides to mark the first pet as done.

Step 5. The user exit the application and decided to reopen it two days later.

Step 6. The second pet that has not been marked will be highlighted while the first pet will not be highlighted since it was already marked.

The following activity diagram summarizes what happens during the process:

![HighlightActivityDiagram](images/diagrams/HighlightDiagram.png)

#### Design Considerations:
**Aspect: How to reduce human error:**

**Alternative 1 (Current Choice): Automatically execute the feature after every certain period**
* Pros:
    * Shows real-time state.
    * Will not show outdated list state.
* Cons:
    * Uses more memory to execute the feature at every time.

**Alternative 2: Provide a Refresh button to update the pet list**
* Pros:
    * Use less memory since it will be executed only when needed.
* Cons:
    * User might forget to refresh to the updated state and shows the outdated instead.

### **Undo Feature**

#### Current Implementation
The undo mechanism is facilitated by the `ModelManager` and `UndoCommand`,  classes.
The `ModelManager` class is implemented by PetPal and has a `petPalCache` field which is of type`PetPal`.

'UndoCommand' extends from the abstract class `Command`. It overrides the `Command#execute()` method to filter the pet list to show only pets with `Deadline` that are within 3 days from today's date.
##### Given below is an example usage scenario and how the undo mechanism behaves at each step:
<pre>
Step 1. The user launches the application for the first time.

Step 2. The user decides to add a pet to the pet list. The user executes `add o/Alice n/Doggo p/98765432 e/example@gmail.com a/311, Clementi Ave 2, #02-25 ts/2023-03-27 21:09:09 d/Feed dog - 2023-03-27 21:09:09 t/Dog t/Chihuahua` command to add a pet named `Doggo` with a reminder to feed the dog and deadline of `2023-03-27 21:09:09` to the pet list. The `add` command calls the `AddCommand#execute()` method.

Step 3. The user realises that he has made a mistake and executes `undo`.

Step 4. The list displayed returns to the previous state without the new Doggo added.
</pre>
##### Extensions:
<pre>
Step 2a. The user decides to delete a pet from the pet list. The user executes `delete 1`.
     2b. The user realises that he has made a mistake and executes `undo`.
     2c. The list displayed returns to the previous state with item 1 which was just deleted.
</pre>

The following sequence diagram shows how the `undo` operation works:

![UndoSequenceDiagram](images/diagrams/UndoSequenceDiagram.png)

The following activity diagram summarizes what happens when a user executes a new command:

![UndoActivityDiagram](images/diagrams/UndoActivityDiagram.png)

#### Design considerations:
- **Current implementation** : Before any command, a cache of the previous state of the archive and PetPal is saved
into a PetPal object. Undo returns the `Archive` and `Model` to this previously saved state.
  - Pros: Easier to implement
  - Cons: Might be memory inefficient

### \[Proposed\] Add medical key information to pet (not shown in UI)
#### Proposed Implementation
The proposed function is an extension of the base `PetPal`, uses a `Medical` class to store medical information, and users will be able to input medical information.

#### Design considerations:
- **Alternative 1 (current choice)** : Store information such as vaccination information in the Medical Class
  Users will only be able to access it if they type in a keyword such as "vaccination" or "vaccine" followed by their password
  which they will have to set at the start of the application.
    - Pros: Provides a way for users to store medical information without having to worry about it being shown in the UI
    - Cons: Might be hard for users to remember the password

- **Alternative 2** : Show all the information in the UI
    - Pros: Easier and more intuitive for users to use
    - Cons: Not secure, anyone can see the information

### \[Proposed\] Importing data from Excel (CSV)

#### Proposed Implementation
The proposed importing function is an extension of the base `PetPal` and uses a `CsvToJsonParser` to convert CSV data
to application readable JSON data.

#### Design considerations:
- **Alternative 1 (current choice)**: Write an external script that parses the CSV data based on the column names
  into a JSON save file that works with PetPal, which they will then put into the data file before starting PetPal
  for PetPal to be able to read and modify the imported data
    - Pros: Might be easier to implement
    - Cons: Might be confusing for users to use (running an external script)

- **Alternative 2**: Provide an interface for users to upload their CSV data into PetPal and automatically parse
  the data into JSON format and refreshes the database.
    - Pros: Easier and more intuitive for users to use
    - Cons: Builds upon **Alternative 1**, requiring more work to implement

[Return to Table of Contents](#table-of-contents)

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

* needs to manage a significant number of pet details
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* prefers an app with GUI.

**Value proposition**: manage pet details better than a typical mouse-driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …              | I want to …                            | So that I can…                                                             |
|----------|---------------------|----------------------------------------|----------------------------------------------------------------------------|
| `* * *`  | new staff           | see usage instructions                 | refer to instructions when I forget how to use the app                     |
| `* * *`  | staff               | add a new pet                          |                                                                            |
| `* * *`  | staff               | input pet details                      | track the details of each pet                                              |
| `* * *`  | staff               | delete a pet                           | remove entries that I no longer need                                       |
| `* * *`  | staff               | find a pet by name                     | locate details of pets without having to go through the entire list        |
| `* * *`  | staff               | tag pets                               | take note of pet personalities or special requirements to know their needs |
| `* *`    | forgetful pet owner | get a reminder for my pet              | remember to fetch my pets from the daycare                                 |
| `* *`    | pet owner           | keep track of my own pets              | know that my pets are taken care of well                                   |
| `* *`    | staff               | keep track of pet locations            | account for missing pets                                                   |
| `* *`    | forgetful staff     | get a reminder for pets                | track pets that have overstayed their duration                             |
| `* *`    | staff               | input pet attendance                   | track pet attendance in the daycare                                        |
| `* *`    | staff               | view pet appointment dates             | bring pets to vet if necessary                                             |
| `* *`    | staff               | search for a pet via tags              | cater different services to different pets                                 |
| `* *`    | staff               | schedule appointment dates             | remember when pet owners are coming to pick up or drop off their pets      |
| `* *`    | staff               | look at vaccination status of pets     | know what vaccinations required are missing                                |
| `* *`    | staff               | archive pet information                | keep a record of older pet clients                                         |
| `* *`    | friendly staff      | send personal messages to pet owners   | update or help pet owners                                                  |
| `* *`    | general staff       | write and save notes for pets          | track additional information if needed                                     |
| `* *`    | staff               | export pet data                        | backup pet data                                                            |
| `* *`    | new staff           | import pet data                        |                                                                            |
| `*`      | staff               | input feedback for pet owners          | let the pet owners know how to better care for their pets' behaviours      |
| `*`      | pet owner           | give staff feedback                    | let the staff know how to improve their services                           |
| `*`      | pet owner           | check pet attendance                   | track how long my pet has been in daycare                                  |
| `*`      | pet owner           | look at staff comments on the feedback | acknowledge staff feedback and give clarification                          |
| `*`      | business owner      | have an overview on the pet welfare    | know that pet owners and staffs are satisfied                              |
| `*`      | staff               | receive pet owner feedback             | know how to take better care of pets                                       |
| `*`      | pet owner           | view daycare availability              | check if there are slots available to board my pet                         |
| `*`      | pet owner           | view facilities                        | know the available amenities for my pets                                   |
| `*`      | clueless pet owner  | look for pet daycare locations         | know which facilities are available                                        |
| `*`      | poor staff          | keep track of hours worked             | keep track of earnings                                                     |
| `*`      | business owner      | keep track of money owed by pet owners |                                                                            |

[Return to Table of Contents](#table-of-contents)

### Use cases

(For all use cases below, the **System** is `PetPal` and the **Actor** is the `user`, unless specified otherwise)

**Use case: View help**

1. Actor requests to view help
2. System shows help message<br>
   Use case ends.


**Use case: List the list of pets**

**MSS**

1. Actor requests to list pets
2. System displays list<br>
   Use case ends.

**Extensions**
* 2a. There are no pet details.<br>
  Use case ends.


**Use case: Add a pet**

**MSS**
1. Actor requests to add a pet
2. System deletes the pet<br>
   Use case ends.

**Extensions**
* 2a. Any required detail(s) are missing
    * 2a1. System shows an error message.<br>
      Use case ends.


**Use case: View reminders**

**MSS**
1. Actor requests to see reminders that are due soon
2. System filters the pet list to show desired pets<br>
   Use case ends.


**Use case: Find pet by name**

**MSS**
1. Actor requests to find pet(s) by name(s)
2. System filter the list to show the desired pet(s)<br>
   Use case ends.


**Use case: Edit a pet**

**Use case: View Cost of Pet**

**MSS**
1. Actor requests to change a pet's cost calculation rate and additional flat cost
2. System updates cost based on calculation
3. Update is done when the user clicks on another PetCard


**Use case: View Cost of Pet**

**MSS**
1. Actor requests to change a pet's cost calculation rate and additional flat cost
2. System edits the pet's cost calculation formula<br>
   Use case ends.

**Extensions**
* 2a. Any required detail(s) are missing
    * 2a1. System shows an error message.<br>
      Use case ends.


**Use case: Mark deadline**

**MSS**
1. Actor requests to list pets
2. System shows a list of pets 
3. Actor requests to mark deadline for a specific pet
4. System marks the pet<br>
    Use case ends.

**Extensions**
* 2a. The list is empty.<br>
  Use case ends.

* 3a. The given index is invalid.
    * 3a1. System shows an error message.<br>
      Use case resumes at step 2.

**Use case: Delete a pet**

**MSS**
1.  Actor requests to list pets
2.  System shows a list of pets
3.  Actor requests to delete a specific pet from the list
4.  System deletes the pet<br>
    Use case ends.

**Extensions**
* 2a. The list is empty.<br>
  Use case ends.

* 3a. The given index is invalid.
    * 3a1. System shows an error message.<br>
      Use case resumes at step 2.


**Use case: Archive a pet**

**MSS**
1. Actor requests to list all pets
2. System shows the list of all pets
3. Actor requests to archive a specific pet in the list
4. System archives the pet<br>
   Use case ends.

**Extensions**
* 2a. The list is empty.<br>
  Use case ends.

* 3a. The given index is invalid.
    * 3a1. System shows an error message.<br>
      Use case resumes at step 2.

* 3b. The given pet exists in the archive
    * 3b1. System shows an error message.<br>
      Use case resumes at step 2.

**Use case: Clear the pet list**

**MSS**
1. Actor requests to list all pets
2. System shows the list of all pets
3. Actor requests to clear the list
4. System clears the list


**Use case: Undo a command**

**MSS**
1. Actor requests a `add`, `delete` or `archive`
2. System executes
3. Actor realises he made a mistake and `undo`
4. System returns to the previous state

**Use case: Exit the System**

**MSS**
1. Actor requests to exit
2. System exits<br>
    Use case ends.


[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 500 pets without a noticeable sluggishness in performance for typical usage.
3.  A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.




### Glossary
| Term          | Definition                                                                                         |
|---------------|----------------------------------------------------------------------------------------------------|
| Mainstream OS | Refers to Windows, Linux, Unix, MacOS                                                              |
| JSON          | The data format used to store PetPal saves, consisting of a name/value pair                        |
| CSV           | Stands for Comma-seperated-values, a type of file where information/values are seperated by commas |

[Return to Table of Contents](#table-of-contents)



--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy it into an empty folder

   2. Double-click the jar file.<br>Expected: Shows the GUI with a set of sample contacts

2. Saving window preferences

   1.  Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location are retained.

### Deleting a pet

1. Deleting a pet while all pets are being shown

   1. Prerequisites: List all pets using the `list` or `l` command. Multiple pets in the list.

   2. Test case: `delete 1`<br>
      Expected: First Pet entry is deleted from the list. Details of the deleted pet entry are shown in the status message.
      Timestamp in the status bar is updated.

   3. Test case: `delete 0`<br>
      Expected: No pet is deleted. Error details are shown in the status message. The status bar remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x` (where x is an integer larger than the list size), `delete abc`, (where abc is a string or special characters)<br>
      Expected: Similar to previous.

2. _{ more test cases … }_

### Saving data

1. Viewing the save files

    1. Exit the app by typing `exit` or `e`.<br>Expected: A new folder called `data` is created in the folder where your jar file is at
    2. Double-click the `data` folder.<br>Expected: 2 files, `petpal.json` (which includes sample data) and `archive.json` present in the `data` folder

2. Dealing with missing data files

    1. To simulate a missing data file, go to the `data` folder and delete `petpal.json` and/or `archive.json`
    2. Restart PetPal and type `exit`
    3. The missing files are re-created in the `data` folder with the sample data populated.

    <div markdown="block" class="alert alert-block alert-success">

    * :bulb: **Note:**
    `archive.json` does not contain any pet entries by default
    </div>

3. Dealing with corrupted data files
    1. To simulate a corrupted data file, go to the `data` folder and edit the `petpal.json` and/or `archive.json` with random strings not of JSON format
    2. Start PetPal, the corrupted data file is replaced with the sample data
    3. The corrupted files are re-created in the `data` folder with sample data
    <div markdown="block" class="alert alert-block alert-success">

     * :bulb: **Note:**
       `archive.json` does not contain any pet entries by default
     </div>

[Return to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org)
* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)

[Return to Table of Contents](#table-of-contents)
