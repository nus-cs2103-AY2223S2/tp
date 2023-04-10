---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the SE-Education initiative. See [se-education.org](https://se-education.org#https://se-education.org) for more info.
* Libraries used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)


--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user attempts to delete a customer/service/appointment/part/technician who has the id of 1  (i.e. `deletecustomer 1`).

This diagram is applicable also for other commands such as the add equivalent.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Omitted from this image is that the `XCommand`, being an  instance of`RedoableCommand`, methods related to it is handled here. For more information, scroll down to the relevant section below.

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here `X` can be `Customer/Appointment/Service/Vehicle/Part(Map.Entry<..>)/Technician` and `Y` can be `Customer/Appointment/Service/Vehicle/Technician`.
</div>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `XListPanel` (`X` is a placeholder for a specific model list panel  e.g., `CustomerListPanel`, `VehicleListPanel`), `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays specific objects residing in the `Model` such as the `Customer`, `Vehicle`, `Service`, `Appointment`, `Technician` and `Map.Entry<> (Part)` objects.
* also depends on some mapping classes in the `Model` component as certain objects have an integer array of object ids that refer to another object. One example is the Customer class, which has a `HashSet<Integer>` of `Vehicle` IDs. To associate each `Customer` object with the corresponding `Vehicle` objects, the Model uses an object called `CustomerDataMap`. The `UI` component then displays the relevant objects based on these mappings. In essence, the `Model` uses these mapping classes to ensure that objects with references to one another are properly connected and displayed in the user interface.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCustomerCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a customer).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("deletecustomer 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)


<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser`  and `DeleteCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

This diagram is applicable to all `deleteXCommand` commands except `deletePartCommand` on the point where instead of an integer (i.e. the ID), it takes in a String (i.e. the part name).

Omitted from this image is regarding `XCommand` are:
1. How Undo and Redo is utilized by the XCommand
2. How the cascading delete commands work (i.e. delete vehicles, delete appointments).
    * This is not added because it similar in nature as to how remove vehicle works.
    * Cascading may be applicable to other functions such as `addX`

For more information regarding undo and redo., scroll down to the relevant section below.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XCommandParser` (`X` is a placeholder for the specific command name e.g., `AddCustomerCommandParser`) which uses the other classes shown above to parse the user command and create a `XCommand` object (e.g., `AddCustomerCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XCommandParser` classes (e.g., `AddCustomerCommandParser`, `DeleteCustomerCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here `X` can be `Customer/Appointment/Service/Vehicle/Part(Map.Entry<..>)/Technician` and `Y` can be `Customer/Appointment/Service/Vehicle/Technician`.
</div>

The `Model` component,

* stores the AutoM8 shop data i.e., all `X` objects (which are contained in a `List` object), `Part` objects (are also contained in a `Map` object), `YDataMap`s that store mappings between `Y` and their associated entities.
* in the case of `X`, `ModelManager` stores filtered `X` objects as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<X>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* in the case of `Y`, `ModelManager` stores selected `Y` objects to store the user's current viewed entity
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103-W17-4/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="1000" />

The `Storage` component,
* can save both AutoM8 shop data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `ShopStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### ID generation

Each entity in AutoM8 has a unique ID (e.g. `Customer` has a unique `customerId`, `Appointment` has a unique `appointmentId`, etc.).
This is to ensure that each entity can be uniquely identified and referenced without each object storing references to other objects and vice versa.
Hence, it is important to ensure that each entity has a unique ID which is generated via an API call.

This ID generation is facilitated by the `IdGenerator` class.
The class consists of `SortedSets` and `PriorityQueues`, one for each entity.
The `SortedSets` are used to keep track of all the IDs that are in use while the `PriorityQueues` are used to update the `IdGenerator` about all the IDs that are not in use.
When the `generateXId()` method is called, the `IdGenerator` will first check if the `PriorityQueue` containing unused IDs is empty.
If it is empty, it will pop off the smallest ID from the `PriorityQueue` and return it.
If it is empty, the `IdGenerator` will check the largest ID in the `SortedSet` of used IDs, return a number one larger than that, and add it to the `SortedSet` of used IDs.
The API call `setXIdUnused(int)` is used to update the `PriorityQueue` of unused IDs when an entity is deleted.

### Entity Data

AutoM8 consists of 6 entities: `Customer`, `Appointment`, `Service`, `Vehicle`, `Part`, and `Technician` which are related to each other in the following way:

![Entity Relationship](images/EntityRelationshipClassDiagram.png)

All the entity data is stored in the `Shop` class which consists of the following (X is a placeholder for the entity name e.g. `Customer`):

![Shop Class Diagram](images/ShopClassDiagram.png)

The shop class simulates a relational database by automating all the cascading deletes and updates between entities.
The only way to modify the data is via the exposed `Shop` API calls (and other methods necessary for loading data from file).

#### Internal Logic

![Add Vehicle Activity Diagram](images/AddVehicleInternalActivityDiag.png)

Above is the activity diagram for adding a vehicle to the `Shop`. Adding other entities follow a similar process.
Related entities are updated automatically.

<img src="images/RemoveCustomerInternalActivityDiag.png" height="800">
<img src="images/RemoveVehicleInternalActivityDiag.png" height="700">
<img src="images/RemoveAppointmentInternalActivityDiag.png" height="700">
<img src="images/RemoveServiceInternalActivityDiag.png" height="1130">

Above are the activity diagrams relevant to removing a customer. The cascading effects are automated by the `Shop` class and entity relations are preserved.

**Undo/Redo**

As shown in the class and activity diagrams above, the undo/redo functionality is implemented using 2 stacks: `undoStack` and `redoStack`.
Whenever there is a modification on the data, a copy of the `Shop` before modification is pushed onto the `undoStack` and the `redoStack` is cleared to prevent conflicts.

The undo and redo commands call the `revert()` and `redo()` API of the `Shop`. Activity diagrams for the respective API calls are shown below.

![Undo Activity Diagram](images/UndoInternalActivityDiag.png)
![Redo Activity Diagram](images/RedoInternalActivityDiag.png)

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire shop.
    * Pros: Implementation is easy.
    * Cons: Memory usage may cause performance issues.

* **Alternative 2:** Individual command has an 'inverse' command which will perform the opposite action.
    * Pros: Will use less memory (e.g. No need to save entire shop every update).
    * Cons:
        * Must ensure that the implementation of each command is correct. Adds a lot of complexity that may not seem justified as it is to only accommodate the undo/redo feature.
        * Difficult to implement for commands that have cascading effects.

**Aspect: Data structure to support the undo/redo commands:**

* **Alternative 1 (current choice):** Use 2 stacks to store the history of the Models.
    * Pros: Implementation is easier and the logic would be much more manageable to debug.
    * Cons: Duplicated Logic.

* **Alternative 2:** Use `HistoryManager` for undo/redo.
    * Pros: Does not need to maintain separate stacks and able to use what is in the codebase.
    * Cons: Single Responsibility Principle and Separation of Concerns are violated as `HistoryManager` would need to handle more than one thing. For example, it would need to handle the undo and redo as well as the history of the application. This is in contrast with a HistoryManager which is only responsible for the history of the application.

**Aspect: How to structure and store Entity data**
* **Current:**
  * Each entity has a unique ID and each entity stores the IDs of the entities that it is related to.
  * The `Shop` class stores all the entities as various Observable Lists.
  * `Shop` class manages all the data modification. It manages all the cascading effects and relationships between entities and ID generation as well as input validation on primitive data input.
  * Pros:
    * Outer classes (e.g Commands) just have to call the relevant API of `Shop` to modify the data. They do not have to worry about relationships between entities.
    * `Shop` class has full 'low level' control of the data. It can ensure that the data is consistent and relationships between entities are preserved.
    * Isolate all data manipulation bugs to the `Shop` class. This makes it easier to debug and test.
  * Cons:
    * Shop class has very high responsibility. Relatively long and complex methods.
    * Certain constraints of `Shop` have to be violated to support loading data from file.
        `Shop` requires other classes to call its API to modify the data.
        However, when loading data from file, the `Storage` classes needs to modify the data directly.
      Some 'unsafe' methods of `Shop` are public to facilitate this.
      This could be avoided if `Shop` manages storing and loading data as well, but this would violate the Single Responsibility Principle and there was not enough time to implement this.
* **Alternative:**
  * Each entity stores references to the entities that it is related to.
  * `Shop` class stores all the top level entities (e.g. `Customer` and `Technician`) as various Observable Lists.
  * Commands and entities manage their own data and relationships.
  * Pros:
    * Commands and entities can freely manipulate the data and relationships.
    * `Shop` class has less responsibility.
    * `Shop` class does not need to violate personal constraints to facilitate loading data from file.
  * Cons:
      * Retrieving/modifying bottom level entities (e.g. `Service`) is very complex and hard to manage
      * Commands and entities have to manage the data and relationships themselves. This could lead to bugs and inconsistencies.
      * `Shop` class has no control over the data. It cannot ensure that the data is consistent and relationships between entities are preserved.
      * Bugs could be caused by any of the many commands and entities across different packages. This makes it extremely hard to debug and test.

### Mapping classes
We use nested integer object ID arrays to establish 1-to-many relationships between entities generated by an ID generator. However, this required multiple mapping classes to retrieve the nested objects by their IDs and display necessary information in the UI.

Below shows an activity diagram of how `CustomerDataMap` is iniatialised/reset everytime there's a necessary update to any entities involved. Most XDataMaps are implemented similarly*
<img src="images/CustomerDataMapInitActivityDiag.png" height="700">

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here `X` can be `Customer/Appointment/Service/Vehicle/Part/Technician`.
</div>

The XDataMap class represents a mapping between X and their associated entities. It maintains several maps to efficiently retrieve information based on unique IDs. The class also provides methods to initialize the mappings, modify them, and retrieve data from them.

Overall, the XDataMap classes provides a convenient way to maintain and access information about X and their associated entities

#### Design considerations:

**Aspect: The way mappings are implemented**

* **Alternative 1 (current choice):** Reset all mappings everytime there's an entity update
    * Pros: Easier to implement, eliminates potential errors of inaccurate data being displayed, nullpointer exceptions due to deprecated entities
    * Cons: Memory, Performance issues when dealing with more and more data

* **Alternative 2:** Update only affected object
    * Pros: Better app performance in the long run
    * Cons: Potential errors of inaccurate data being displayed, ensuring UI does not throw exceptions before the map has updated, Slightly more tedious implementation as we need to find a way to only let affected maps be updated

### Find/List Feature
#### Current Implementation
The List feature shows users the whole list of entities in their respective tabs. We have a specific List command and a global list command. The Find feature helps to find specific entities according to the keyword provided.

Both features are essentially implemented the same way as both will filter the displayed lists to the user thus we will be using the sequence diagram of the Find feature to show how the different components interact with one another to execute the command.

<div markdown="span" class="alert alert-info">:information_source: **Note:** For List commands `X` can be `Customer/Appointment/Service/Vehicle/Technician/Part`. For Find commands `X` can be `Customer/Appointment/Service/Vehicle/Technician`.
</div>

A key detail to the Find/List implementation is that the respective Find and List commands executes which calls Model#updateFilteredXList() while passing in a predicate to update the filtered list shown to the user in the UI according to a predicate. The Find command would pass in the predicate created from the FindCommandParser while the List command simply uses a static predicate called SHOW_ALL_X.

The Sequence Diagram below illustrates the interactions with the `Logic`, `Model` and `JavaFx` components for the `find KEYWORD` API call.

<img src="images/FindSequenceDiagram.png" width="1100" />

While both features are implemented similarly, it is also important to note that there is a slight difference between Find and List feature. It is shown in the partial sequence diagram of the ListXCommand below. The slight difference is the absence of a CommandParser for ListXCommands. \
Note: The rest of the sequence diagram of ListCommands are the same as the Find Feature sequence diagram shown above

<img src="images/ListXSequenceDiagramCropped.png" width="600" />

#### Design considerations:

**Aspect: The way find is implemented**

* **Alternative 1 (current choice):** Use a single find command to iterate through all entities with the give keyword
    * Pros: Implementation is easy, parsing is very similar to how previous find was implemented
    * Cons: Probably unable to use keywords that are non-string

* **Alternative 2:** Using separate find commands for respective entities, similar to how most other existing commands we have (e.g. FindVehicleCommand, FindCustomerCommand, etc.)
    * Pros: Better UX, users can now use keywords specific to that entity, increasing the effectiveness of find
    * Cons: Way more time needed for implementation as specific entity Find commands need parsers unique to the entities themselves

### Sort Feature
#### Current Implementation

The Sort feature sorts the respective entities according to some of their entity specific parameters.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here `X` can be `Customer/Appointment/Service/Vehicle/Part/Technician`.
</div>

A key detail to the Sort implementation is that the SortXCommandParser parses the arguments from the user input to create a comparator which is then passed onto the SortXCommand. The SortXCommand then executes which calls the Model#updateXComparator which updates a SortedList<X> according to the comparator. There is also an option `r\` flag which can reverse the sort direction in the comparator.

The Sequence Diagram below illustrates the interactions with the `Logic`, `Model` and `JavaFx` components for the `sortX by/PARAM` API call.

<img src="images/SortXSequenceDiagram.png" width="1100" />

#### Design considerations:

**Aspect: The way sort is implemented**

* **Alternative 1 (current choice):** Use a separate list to store sorted X lists
    * Pros: Ease of sorting with the use of comparators as SortedLists allow us to use comparators
    * Cons: Ensuring both original filtered lists and sorted filtered lists are in sync/both have the same data

* **Alternative 2:** Sorting the existing filtered lists
    * Pros: No need to worry about both original filtered lists and sorted filtered lists staying in sync/both have the same data
    * Cons: More difficulty implementing sort, we will have to implement a sort function that works for filtered lists since filtered lists do not take in comparators.


### Add Feature

#### Current Implementation
The add function is facilitated by `AddXCommand` (`X` is a placeholder for the specific entity to be added e.g. `AddCustomerCommand`)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here `X` can be `Customer/Appointment/Service/Vehicle/Part/Technician`.
</div>

The Sequence Diagram below illustrates the interactions within the Logic component for the `execute("addX args*")` API call, , where `argks*` represents the various arguments needed for the function to run.

<img src="images/AddXSequenceDiagram.png"/>

The `addX(x)` method of `Model` adds the entity into the system via adding the entity into `Shop`.

Limitations of plant UML prevents us from putting an X at the correct spot.

Omitted from the diagram above is:
1. How Undo and Redo is implemented
2. The other relevant add commands. For example, `addvehicle` requires `owner id` which would affect the relevant customer by adding the vehicle to that user.

### Edit Feature

#### Current Implementation
The edit function is facilitated by `EditXCommand` (`X` is a placeholder for the specific entity to be added e.g. `EditCustomerCommand`)

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here `X` can be `Customer/Appointment/Service/Vehicle/Technician`.
</div>

The Sequence Diagram below illustrates the interactions within the Logic component for the `execute("editX args*")` API call, where `argks*` represents the various arguments needed for the function to run.

<img src="images/EditXSequenceDiagram.png"/>

The `editX(x)` method of `Model` adds the entity into the system via adding the entity into `Shop`.

Limitations of plant UML prevents us from putting an X at the correct spot.

Omitted from the diagram above is:
1. How Undo and Redo is implemented
2. The other relevant add commands. For example, `addvehicle` requires `owner id` which would affect the relevant customer by adding the vehicle to that user.

### View Feature

#### Current Implementation

The view function is facilitated by `ViewXCommand` (`X` is a placeholder for the specific command name e.g., `ViewCustomerCommand`).

<div markdown="span" class="alert alert-info">:information_source: **Note:** Here `X` can be `Customer/Appointment/Service/Vehicle/Technician`.
</div>

The Sequence Diagram below illustrates the interactions within the Logic component for the execute("viewcustomer 1") API call.


Note: The way ViewXCommand is parsed is the same to how Find/List Commands are parsed, thus this is a cropped version to show the key details and difference in the implementation for ViewCommand. The full sequence diagram for ViewXComamand is still available as a puml file in the diagrams directory.

<img src="images/ViewXSequenceDiagramCropped.png"/>

While the first half of the execution of ViewXCommand is the same as Find/List Command as explained above, a key detail to how ViewXCommand is implemented is extra call of Model#selectX(), where a Function is passed into it as a selector. This Function then calls apply() to the sortedFilteredXs in the Model for the result to return the requested X to the replace the current selectedX object in the model.

### TotalAppointmentCommand Feature

##### Current Implementation

The following sequence diagram shows how the `totalAppointmentCommand` operation works:

![totalAppointmentSequenceDiagram](images/TotalAppointmentSequenceDiagram.png)

The `totalAppointmentCommand` feature mainly involves iterating through the appointment list and checking if the specified date falls on the same date as the appointment. The way that the validation check is done is by setting the previous day to be the start date and the next day to be the end date. Finally, we check if the current appointment is within the start and end date.

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

* Auto repair shop owners who want to keep track of their customers, vehicles, logistics and appointments
* prefers desktop apps over other types
* fast typist
* prefers typing to mouse interactions
* is comfortable interacting with CLI apps

**Value proposition**: AutoM8 provides a platform that allows auto repair shop owners to manage their customer information, service details and logistics


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                | I want to …​                                   | So that I can…​                                                           |
|----------|------------------------|------------------------------------------------|---------------------------------------------------------------------------|
| `* * *`  | Auto repair shop owner | add customer appointments                      | keep track of appointments in the day or subsequent days                  |
| `* * *`  | Auto repair shop owner | add vehicle I want to fix                      | keep track of vehicles to fix                                             |
| `* * *`  | Auto repair shop owner | add spare parts                                | keep track of how many spare parts remaining                              |
| `* * *`  | Auto repair shop owner | add customer                                   | keep track of customer details                                            |
| `* * *`  | Auto repair shop owner | add vehicle service                            | keep track of services to do for a vehicle                                |
| `* * *`  | Auto repair shop owner | know which car plate belongs to which customer | hand the right car to the appropriate owner                               |
| `* * *`  | Auto repair shop owner | delete a customer record                       | remove entries I no longer need                                           |
| `* * *`  | Auto repair shop owner | delete a vehicle record                        | remove entries I no longer need                                           |
| `* * *`  | Auto repair shop owner | delete a service record                        | remove entries I no longer need                                           |
| `* * *`  | Auto repair shop owner | delete an appointment record                   | remove entries I no longer need                                           |
| `* * *`  | Auto repair shop owner | delete a spare part                            | remove entries I no longer need                                           |
| `* * *`  | Auto repair shop owner | find a customer                                | find and view details of that customer without searching the entire list  |
| `* * *`  | Auto repair shop owner | find a vehicle                                 | find and view details of that vehicle without searching the entire list   |
| `* * `   | Auto repair shop owner | sort vehicles by brand                         | find vehicles of the same brand                                           |
| `* *`    | Auto repair shop owner | edit a customer                                | make changes in case of mistakes or update it if there's a change in data |
| `* *`    | Auto repair shop owner | edit a vehicle                                 | make changes in case of mistakes or update it if there's a change in data |
| `* *`    | Auto repair shop owner | view a specific customer                       | see more details related to that customer                                 |
| `* *`    | Auto repair shop owner | view a specific vehicle                        | see more details related to that vehicle                                  |

*{More to be added}*

### Use cases

AutoM8 provides the necessary features that support the management of customer, vehicle, servicing, appointment information such as adding, deleting, listing, sorting, finding and editing. The Use Cases listed below demonstrate their usages.

(For all use cases below, the **System** is `AutoM8` and the **Actor** is the `user`, unless specified otherwise)
<br/><br/>

**Use case: UC01 - Listing all customers**

**MSS**
1. User requests to list out all customers.
2. AutoM8 shows a list of all customers.

   Use case ends.

**Extensions**

- 2a. The list is empty.
  Use case ends.
  <br/><br/>

**Use case: UC02 - Listing all vehicles**

similar to use case one.

**Use case: UC03 - Listing all appointments**

similar to use case one.

**Use case: UC04 - Listing all spare parts**

similar to use case one.

**Use case: UC05 - Listing all services**

similar to use case one.

**Use case: UC06 - Listing all technicians**

similar to use case one.

**Use case: UC07 - Adding a customer**

**MSS**

1. User requests to add a customer as a contact.
2. User inputs the information of the customer.
3. AutoM8 adds the customer as a contact.

   Use case ends.

**Extensions**

- 3a. The given name already exists in AutoM8.
    - 3a1. AutoM8 shows an error message.
      Use case resumes at step 2.
      <br/><br/>

**Use case: UC08 - Adding a vehicle**

similar to use case seven.

**Use case: UC09 - Adding an appointment**

similar to use case seven.

**Use case: UC10 - Adding a spare part**

similar to use case seven.

**Use case: UC11 - Adding a service**

similar to use case seven.

**Use case: UC12 - Adding a technician**

similar to use case seven.

**Use case: UC13 - Adding a technician to a service**

**MSS**

1. User requests to add a technician to a service.
2. User inputs the information of the service.
3. AutoM8 adds the service into the book.

   Use case ends.

**Extensions**

- 3a. The given technician already has a service assigned in AutoM8.
    - 3a1. AutoM8 shows an error message.
      Use case resumes at step 2.
      <br/><br/>
- 3b. The provided index for technician and/or service is invalid.
    - 3a1. AutoM8 displays an error message.
      <br/><br/>

**Use case: UC13 - Adding a spare part to a service**

similar to use case thirteen.

**Use case: UC14 - Editing a customer's details**

**MSS**

1. User requests to <u>list contacts (UC01)</u>.
2. AutoM8 shows a list of customers.
3. User requests to edit a contact on the list.
4. User inputs the updated information.
5. AutoM8 updates the contact's details.

   Use case ends.

**Extensions**
- 2a. The list is empty.
  Use case ends.
- 3a. The provided index is invalid.
    - 3a1. AutoM8 displays an error message.

      Use case resumes at step 2.
      <br/><br/>

**Use case: UC15 - Editing a vehicle's details**

similar to use case fourteen.

**Use case: UC13 - Editing appointment details**

similar to use case fourteen.>

**Use case: UC14 - Editing an appointment details**

similar to use case fourteen.

**Use case: UC15 - Editing a service details**

similar to use case fourteen.

**Use case: UC16 - Editing a technician details**

similar to use case fourteen.

**Use case: UC17 - Deleting a customer**

**MSS**
1. User requests to <u>list customers (UC01)</u>.
2. AutoM8 shows a list of customers.
3. User requests to delete a customer at a given index.
4. AutoM8 deletes the customer at the index.

   Use case ends.

**Extensions**
- 2a. The list is empty.
  Use case ends.
- 3a. The provided index is invalid.
    - 3a1. AutoM8 displays an error message.

      Use case resumes at step 2.
      <br/><br/>

**Use case: UC18 - Deleting a vehicle**

similar to use case seventeen.

**Use case: UC19 - Deleting an appointment**

similar to use case seventeen.

**Use case: UC20 - Deleting a spare part**

similar to use case seventeen.

**Use case: UC21 - Deleting a service**

similar to use case seventeen.

**Use case: UC22 - Deleting a technician**

similar to use case seventeen.

**Use case: UC23 - Sorting customers**

**MSS**
1. User requests to <u>list customers (UC01)</u>.
2. AutoM8 shows a list of customers.
3. User requests to sort customers in list.
4. AutoM8 sorts vehicles according to user's requirements.

   Use case ends.

**Extensions**
- 2a. The list is empty.
  Use case ends.
- 3a. No fields are specified.
    - 3a1. AutoM8 displays an error message.

      Use case resumes at step 2.
      <br/><br/>

**Use case: UC24 - Sorting appointment**

similar to use case twenty-three.

**Use case: UC25 - Sorting appointment**

similar to use case twenty-three.

**Use case: UC26 - Sorting services**

similar to use case twenty-three.

**Use case: UC27 - Sorting technician**

similar to use case twenty-three.

**Use case: UC28 - Find a vehicle**

**MSS**

1. User requests to <u>list of customer as contacts (UC02)</u>.
2. AutoM8 shows a list of vehicles.
3. User requests to find vehicle on the list.
4. AutoM8 find customer according to user's requirements.

   Use case end.

**Extensions**
- 2a. The list is empty.
  Use case ends.
- 3a. The provided index is invalid.
    - 3a1. AutoM8 displays an error message.

      Use case resumes at step 2.
      <br/><br/>


**Use case: UC29 - Find a service**

similar to use case twenty-eight.

**Use case: UC30 - View a customer**

**MSS**
1. User requests to <u>list customers (UC01)</u>.
2. AutoM8 shows a list of customers.
3. User requests to view a customer at a given index.
4. AutoM8 views the customer at the index.

   Use case ends.

**Extensions**
- 2a. The list is empty.
  Use case ends.
- 3a. The provided index is invalid.
    - 3a1. AutoM8 displays an error message.

      Use case resumes at step 2.
      <br/><br/>

**Use case: UC31 - View an appointment**

similar to use case thirty.

**Use case: UC32 - View a service**

similar to use case thirty.

**Use case: UC33 - View a technician**

similar to use case thirty.

**Use case: UC33 - View a vehicle**

similar to use case thirty.

**Use case: UC34 - Undo previous command**

**MSS**

1.  User keys undo.
2.  System undoes the previous command executed.

Use case ends.

**Extensions**

* 1a. Previous command was `add`, `edit` or `delete` and user tries to undo.

    * 1a1. System will not the previous command and displays an error message.
      Use case ends.

**Use case: UC35 - Redo previous command**
1. User requests to redo.
2. System redoes the last action.

Use case ends.

**Extensions**
- 1a. There is no command to redo.
    - 3a1. System displays an error message.
      Use case ends.

**Use case: UC36 - Exiting the application**

**MSS**

1. User requests to exit AutoM8.
2. AutoM8 closes.

   Use case ends.
   <br/><br/>

### Non-Functional Requirements

1. The application should be _free_.
2. It should be easy to understand and use, even for users with little to no experience.
3. Offline application used by each person.
4. The application should be able to operate on any _mainstream OS_ such as Linux, MacOS and Windows so long as Java 11 or above is installed.
5. The product should be highly testable.
6. Use of clear and concise English should be observed in the documentation
7. This product does not necessarily need to be installed but can run as an executable.
8. Contain clear and easy to understand error messages
9. Should be able to support up to 1000 persons without any noticeable lag in performance for typical usage.
10. A user that possess above average typing speed for regular text (i.e. not code, not system admin commands) should be able to achieve majority of the task faster using commands than using the mouse.


### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Vehicle**: A 4-wheel or 2-wheel machine used to transport people
* **Plate number**: An identifier put on the front and back of a vehicle
* **Spare parts**: A part that can be used to replace or fix a broken part in a vehicle

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


### Deleting a customer

1. Deleting a customer while all customers are being shown

   1. Prerequisites: List all customers using the `listcustomer` command. Multiple persons in the list.

   1. Test case: `deletecustomer 1`<br>
      Expected: Customer with id of 1 is deleted from the list. Details of the deleted customer shown in the status message.

   1. Test case: `deletecustomer 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `deletecustomer`, `deletecustomer x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.


### Saving data

1. Dealing with missing/corrupted data files
   1. Prerequisites: There is an existing data file, in the wrong json format.
   2. User starts the AutoM8 app.
   3. The corrupted data file will be overwritten and sample data generated will be displayed to the user.
   4. To start the AutoM8 with sample data, delete the corrupted json file and restart the application.
2. Dealing with data files that do not have proper entity mappings
    1. Prerequisites: There is an existing data file, with the mapping not being accurate to current data. E.g. Customer has vehicleIds: 1, 3 but vehicle with id 3 does not exist
    2. User starts the AutoM8 app.
    3. The app may crash due to a `NullPointerException` being thrown which cause the app to catch the fatal exception and exit
    4. To successfully start the AutoM8, either manually add the missing entity and ensure all mappings are correct in the data json file and restart the application or delete the corrupted json file and restart the application.

## **Appendix: Effort**
### Effort Required
The implementation of AutoM8 required a substantial amount of effort, primarily due to the extensive feature implementation required across six entities: Customer, Vehicle, Service, Appointment, Technician, and Part. We aimed to provide each feature to all entities, which added to the complexity of the implementation process. Additionally, our entities had one-to-many relationships, which further complicated the implementation process.<br>
<br>
For example, a single customer could have multiple appointments, services, and vehicles, and a technician could perform many appointments across different customers and vehicles. This required careful consideration of how the features interacted with each other and how the data would be stored and retrieved efficiently. Overall, the implementation of AutoM8 was a significant undertaking that required a thorough understanding of the relationships between entities and the various features required to meet our goals.

### Comparison with AB3
* Every Command
  * AutoM8: Deals with up to **6 entities** with various parameters that had different validations and logic
  * AB3: Only dealt with 1 entity
* Storage Handling
    * AutoM8: Deals with up to **6 entities** with various parameters that had different validations and logic. Great effort required to create sample data for every entity
    * AB3: Only dealt with 1 entity
* View feature, Undo/Redo feature
    * AutoM8: Enables these features
    * AB3: Did not have these features
* Sort feature
  * AutoM8: Enables this feature and also goes the extra mile by allowing the sort based on multiple parameters of an entity
  * AB3: Did not have this feature
* GUI/State management during app runtime
  * AutoM8: Had 2 panels for each Tab which contains a different Object entity, tab navigation was automatic and messages related to certain entities are persisted in the command result component when user switches tabs
  * AB3: Only needed to handle states for 1 GUI panel

### Challenges faced:
* Heavy GUI Improvement
  * Significant styling changes from AB3
  * More GUI components explored: SplitPane, Tab, Circle, ImageView, etc
  * Dealing with the GUI with 6 entities, each entity has its respective tab, details panel and recent command result messages stored
  * Required lots of trial and error to find the best way to display selected entities in the details panel
  * Lots of effort on state management handling, due to our entities being closely related to one another (one-to-many relationships)
    * Testing to ensure objects are mapped correctly and are mapped to objects that still exist
    * A lot of time and effort used by multiple AutoM8 developers to debug incorrect mappings/delayed mapping resets to accurately reflect data after a CRUD command was executed
    * Additional Mapping classes implemented to assist in displaying entities and their associated objects in the panels

## **Appendix: Planned Enhancements** (Max 8*)
1. Less general error messages, especially those for invalid indexes. The current error message is too general for the user to understand that the error comes from invalid indexes. (E.g. Command format may be correct except index but error message show is `Input is not a number` instead of a more intuitive error message like `Invalid vehicle index input` to help the user correct the command format entered)
2. Implementing `Find`, `Edit`, `View` for Parts. As of v1.4, the respective command works for all entities except parts, we plan to implement this to ensure the feature consistency of the app for all entities.
3. `View` command should not filter the list panel, to allow users to continuously use the view command instead of needing to use the `list` command first to refer to the entity id, before using `view` again.
4. Data Archiving. To allow user to revert to a previous save (possibly max 5 or user settable).
5. Save file checks, to reduce the effects of malicious edit impacting program operations.
    * Example: Malicious user edits save file by adding `vehicle id` (i.e. vehicle id 5) to a customer, but the `vehicle` (with id 5) does not belong to the user.
    * Impact rating: CWE-20 with CVSS:3.1/AV:L/AC:L/PR:N/UI:N/S:U/C:N/I:H/A:L (6.8 Medium)
    * Impact: Due to how delete operations are cascades, this edit can cause a user to delete vehicles that are not originally assigned to the customer.
    * Plan: Implement post load save checks to ensure that vehicle to customer mapping is 1:1 on both vehicle and customer end.
    * Note: This is applicable for all entries that have some form of mapping.


