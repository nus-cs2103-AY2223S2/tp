---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* COVID-19 vaccination data - [MOH ｜ COVID-19 Vaccination](https://www.moh.gov.sg/covid-19/vaccination)
* Chemical name synonyms - [PubChem](https://pubchem.ncbi.nlm.nih.gov/)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/MainApp.java). It is responsible for,
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

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PatientListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Patient` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component execution queue works:

1. When `Logic` is called upon to queue a user command, it queues that user command into an internal queue in `LogicManager`.
2. `LogicManager` continues to parse and execute any previously queued command inputs until the queued command input in step 1 is next.
3. The next user command is polled from the queue and parsed using `VmsParser` to produce a `ParseResult`.
4. The `Command` object (more precisely, an object of one of its subclasses eg. `AddCommand`), within the resultant `ParseResult`, is executed.
5. During execution, the `Command` object communicates with `Model` to perform its task. When it is done, it produces a `CommandMessage`.
6. `LogicManager` combines the `CommandMessage` within `ParseResult` in 3 and the resultant `CommandMessage` in 5 into a list and passes it to its set `Consumer` to handle these `CommandMessages`. See [IU component](#ui-component) on this is handled.
7. If the command executed has follow up commands, a new `ParseResult` is created that encapsulates the follow up command and an empty `CommandMessage`. This `ParseResult` is sent to be executed and the process starts from 4.
8. Else, the execution for this user command ends and `LogicManager` executes the next user command in its internal queue if present, continuing from 3.
9. If there are no user commands waiting to be executed, `Logic` waits for a user command to be queued and the cycle repeats from 1.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

1. When called upon to parse a user command, `VmsParser` a `FeatureParser` or more specifically, one of its subclasses (eg `BasicParser`, `PatientParser`, etc) depending on the user command. Used input from the user command are removed.
2. The `FeatureParser` in 1 then parses the remaining user command to create a `CommandParser`. Similar to 1, the created `CommandParser` is more specifically one of its subclasses (eg. `AddCommandParser`) and used inputs from the user command are removed.
3. The resultant `CommandParser` parses the remaining user input to create a `Command` object, such as `AddCommand`, and optionally a `CommandMessage`. Both of which are encapsulated into a `ParseResult` and returned.

### Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The responsibilities of `Model` component,

* stores the runtime state of the other managers:
  * `PatientManager`
  * `VaxTypeManager`
  * `AppointmentManager`
  * `KeywordManager`
* stores the objects to be displayed as a separate filtered map which is exposed to outsiders as an unmodifiable `ObservableMap<K, V>` where `V` is the type of object being stored (eg. `IdData<Patient>`) and `K` is the type of the key the stored object is mapped to (for `Patient` and `Appointment`, this is an `Integer` and as for `VaxType`, this is a `String`).
* stores the object to be detailed as a `ObjectProperty<V>` where `V` is the type of the object to be displayed (eg. `IdData<Patient>`).
* store a `UserPref` object that represents the user's preferences. This is exposed to the outside as a `ReadOnlyUserPref` object.

### Patient component

<!-- TODO -->

### Vaccination component

Vaccinations are represented as `VaxType` objects and stored within `VaxTypeManager`.

#### VaxType

To represent a vaccination, `VaxType` contains the following attributes:

* A name represented as a `GroupName` object.
* A set of groups which the vaccination classifies under as a set of `GroupName` objects.
* A minimum age as an `Age` object.
* A maximum age as an `Age` object.
* A set ingredients of the vaccination as a set of `GroupName` objects.
* A list of requirements of vaccination groups that will have to be taken before this vaccination can be taken as a list of `Requirement` objects.

#### VaxTypeManager

On top of storing `VaxType` objects, `VaxTypeManager` ensures the uniqueness of `VaxType`. It also ensures that there are at most 30 `VaxType` objects stored.

### Appointment component

**API** : [`Appointment.java`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/model/appointment/Appointment.java)

The `Appointment` component,

* Contains the details of patients' appointment
  * The patients' `Patient id`
  * The duration of each appointment (Uses the `start time` and `end time`)
  * The type and dose of `vaccine` to be administered
  * The `status` of the appointment

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103-F11-3/tp/tree/master/src/main/java/seedu/vms/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component is responsible for the reading and writing of the states of the different managers in `Model` to and from the hard disk. As shown in the diagram above, it inherits from `PatientManagerStorage`, `UserPrefsStorage`, `VaxTypeStorage`, `AppointmentStorage` and `KeywordStorage`. As such, it can be treated as either one (if only the functionality of only one is needed).

### Keyword component

<!-- TODO -->

### Common classes

Classes used by multiple components are in the `seedu.vms.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Adding a Patient

The **Adding a Patient** mechanism is facilitated by `VMS`. The Patient created is stored inside `PatientManager` object.

##### Execution Sequence

Given below is an example usage scenario when a user enter `patient add --n John Doe --p 98765432 --d 2001-03-19 --b B+ --a catfur --a pollen --v covax` as a command.

1. The user enters the command in the `UI component`
2. It will be passed to the `Logic component`
3. When `AddCommandParser` receives the information from `PatientParser`, it will invoke the following methods to help with the parsing.
    1. `ParserUtil#parseName` will be called to create a Name object using "John Doe".
    2. `ParserUtil#parsePhone` will be called to create a Phone object using "98765432".
    3. `ParserUtil#parseDob` will be called to create a Dob object using "2001-03-19".
    4. `ParserUtil#parseBloodType` will be called to create a BloodType object using "B+".
    5. `ParserUtil#parseGroups` will be called to create GroupName[] object named allergies using ["catfur", "pollen"].
    6. `ParserUtil#parseGroups` will be called to create GroupName[] object named vaccines using ["covax"].
4. After successfully parsing the args, `AddCommandParser` will create a Patient using the new Name, Phone, Dob, BloodType, Allergies<GroupName>, Vaccines<GroupName>. Then it will create an `AddCommand` with the new Patient object.
5. When `AddCommand#execute` is called, `model#addPatient` will be called to add the new Patient into the model. `AddCommand` will then return `CommandMessage` to indicate it's success.

Note that `Allergies` and `Vaccines` are optional, so the user does not need to include `--a ` or `--v` if the it is not applicable.

The activity diagram below illustrates the workflow of patient `AddCommand` that is described above.

<img src="images/patient/AddPatientActivityDiagram.png" width="550" />

Given below is an sequence diagram that illustrates the **Adding a Patient** mechanism behaves at every step.

<img src="images/patient/AddPatientSequenceDiagram.png" width="550" />

### Listing Patients

The **Listing Patients** mechanism is facilitated by `VMS`. It will list all the Patients that are stored in the `PatientManager`.

##### Execution Sequence

Given below is an example usage scenario when a user enter `patient list` as a command.

1. The user enters the command in the `UI component`
2. It will be passed to the `Logic component`
3. `PatientParser` will invoke `ListCommand` directly without intemediary parser commands as `ListCommand` does not accept any argument.
4. When `ListCommand#execute` is called, `model#updateFilteredPatientList` will be called to update the list with the `PREDICATE_SHOW_ALL_PATIENTS` to display all Patients.

The activity diagram below illustrates the workflow of patient `ListCommand` that is described above.

<img src="images/patient/ListPatientsActivityDiagram.png" width="550" />

Given below is an sequence diagram that illustrates the **Listing Patients** mechanism behaves at every step.

<img src="images/patient/ListPatientsSequenceDiagram.png" width="550" />

### Finding a Patient

The **Finding a Patient** mechanism is facilitated by `VMS`. It will find specific list of Patient objects from `PatientManager` inside `VMS` object with the keywords provided.

The user can choose to add flags when searching, to search for the specific attributes of a Patient. If no flags are present, the mechanism will assume that it is searching the Patient's name.

##### Execution Sequence

Given below is an example usage scenario when a user enter `patient find --n John Doee --p 98765431 --d 2001-03-19 --b B+ --a catfur --v covax` as a command.

1. The user enters the command in the `UI component`
2. It will be passed to the `Logic component`
3. When `FindCommandParser` receives the information from `PatientParser`, it will invoke the following methods to help with the parsing. It will throw a `ParseExeception` if there are no args present.
    1. `ParserUtil#parseName` will be called to create a Name object using "John Doe".
    2. `ParserUtil#parsePhone` will be called to create a Phone object using "98765432".
    3. `ParserUtil#parseDob` will be called to create a Dob object using "2001-03-19".
    4. `ParserUtil#parseBloodType` will be called to create a BloodType object using "B+".
    5. `ParserUtil#parseGroups` will be called to create GroupName[] object named allergies using ["catfur", "pollen"].
    6. `ParserUtil#parseGroups` will be called to create GroupName[] object named vaccines using ["covax"].
4. After successfully parsing the args, the following will happen
    1. `FindCommandParser` will create an FindPatientDescriptor using the new Name, PhoNameContainsKeywordsPredicatene, Dob, BloodType, Allergies `<GroupName>`, Vaccines `<GroupName>`.
    1a. If none of the flags are present, it will take the entire arg as a `setNameSearch`.
    2. Then it will create an `FindCommand` with the new FindPatientDescriptor object.
5. When `FindCommand#execute` is called, the following will happen.
    1. It will check if the different attributes of FindPatientDescriptor is present.
    2. It will find the patient by creating different `Optional<AttributePredicate>`.
    3. The different predicates will be added into a `List<Predicate<Patient>>` and passed to `model#setPatientFilters` to display the filtered Patients.
    4. `FindCommand` will then return `CommandMessage` to indicate it's success and the number of patients found.

The activity diagram below illustrates the workflow of patient `FindCommand` that is described above.

<img src="images/patient/FindPatientActivityDiagram.png" width="550" />

Given below is an sequence diagram that illustrates the **Finding a Patient** mechanism behaves at every step.

<img src="images/patient/FindPatientSequenceDiagram.png" width="550" />

`FindCommandParser#parse` will call `String#trim` to trim the search request. If there is no additional flags, it will fall back to the default of using the search term to find Names.

### Editing a Patient

The **Editing a Patient** mechanism is facilitated by `VMS`. It will read and modify a target Patient object from `PatientManger` inside `VMS` object.

##### Execution Sequence

Given below is an example usage scenario when a user enter `patient edit 5 --n John Doee --p 98765431 --d 2001-03-19 --b B+ --a catfur --a pollen --v covax` as a command.

1. The user enters the command in the `UI component`
2. It will be passed to the `Logic component`
3. When `EditCommandParser` receives the information from `PatientParser`, it will invoke the following methods to help with the parsing. It will short circuit and throw a `ParseExeception` if 1. is not fulfilled.
    1. `ParserUtil#parseIndex` will be called to create a Index object using "5".
    2. `ParserUtil#parseName` will be called to create a Name object using "John Doe".
    3. `ParserUtil#parsePhone` will be called to create a Phone object using "98765432".
    4. `ParserUtil#parseDob` will be called to create a Dob object using "2001-03-19".
    5. `ParserUtil#parseBloodType` will be called to create a BloodType object using "B+".
    6. `ParserUtil#parseGroups` will be called to create GroupName[] object named allergies using ["catfur", "pollen"].
    7. `ParserUtil#parseGroups` will be called to create GroupName[] object named vaccines using ["covax"].
4. After successfully parsing the args, `EditCommandParser` will create an editPatientDescriptor using the new Name, Phone, Dob, BloodType, Allergies `<GroupName>`, Vaccines `<GroupName>`. Then it will create an `EditCommand` with the new editPatientDescriptor object with the index.
5. When `EditCommand#execute` is called, the following will happen.
    1. It will ensure that the Index given is within the list, else it will throw a CommandExeception
    2. It will edit the patient by creating a new patient with the new values from the Parser as Patients are Immuttable
    3. Then `model#setPatient` will be called to add the new Patient into the model.
    4. `EditCommand` will then return `CommandMessage` to indicate it's success.

The activity diagram below illustrates the workflow of patient `EditCommand` that is described above.

<img src="images/patient/EditPatientActivityDiagram.png" width="550" />

Given below is an example usage scenario and how **Editing a Patient** mechanism behaves at each step.

<img src="images/patient/EditPatientSequenceDiagram.png" width="550" />

### Deleting a Patient

The **Deleting a Patients** mechanism is facilitated by `VMS`. It will delete specific Patient objects from `PatientManager` inside `VMS` object with using the index provided.

##### Execution Sequence

Given below is an example usage scenario when a user enter `patient delete 5` as a command.

<!-- TODO -->

<img src="images/patient/DeletePatientActivityDiagram.png" width="550" />
<img src="images/patient/DeletePatientSequenceDiagram.png" width="550" />

### Clearing Patients

The **Clearing Patients** mechanism is facilitated by `VMS`. It will set the patient manager with a new empty patient manager, effectively clearing all the Patients

##### Execution Sequence

Given below is an example usage scenario when a user enter `patient clear` as a command.

1. The user enters the command in the `UI component`
2. It will be passed to the `Logic component`
3. `PatientParser` will invoke `ClearCommand` directly without intemediary parser commands as `ClearCommand` does not accept any argument.
4. When `ClearCommand#execute` is called, `model#setPatientManager` will be called to update the list a new PatientManger() with no patients.

The activity diagram below illustrates the workflow of patient `ClearCommand` that is described above.

<img src="images/patient/ClearPatientActivityDiagram.png" width="550" />

Given below is an sequence diagram that illustrates the **Clearing Patients** mechanism behaves at every step.

<img src="images/patient/ClearPatientSequenceDiagram.png" width="550" />

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedVms`. It extends `Vms` with an undo/redo history, stored internally as an `patientManagerStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedVms#commit()` — Saves the current patient manager state in its history.
* `VersionedVms#undo()` — Restores the previous patient manager state from its history.
* `VersionedVms#redo()` — Restores a previously undone patient manager state from its history.

These operations are exposed in the `Model` interface as `Model#commitVms()`, `Model#undoVms()` and `Model#redoVms()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedVms` will be initialized with the initial patient manager state, and the `currentStatePointer` pointing to that single patient manager state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th patient in the patient manager. The `delete` command calls `Model#commitVms()`, causing the modified state of the patient manager after the `delete 5` command executes to be saved in the `patientManagerStateList`, and the `currentStatePointer` is shifted to the newly inserted patient manager state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new patient. The `add` command also calls `Model#commitVms()`, causing another modified patient manager state to be saved into the `patientManagerStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitVms()`, so the patient manager state will not be saved into the `patientManagerStateList`.

</div>

Step 4. The user now decides that adding the patient was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoVms()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous patient manager state, and restores the patient manager to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial Vms state, then there are no previous Vms states to restore. The `undo` command uses `Model#canUndoVms()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoVms()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the patient manager to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `patientManagerStateList.size() - 1`, pointing to the latest patient manager state, then there are no undone Vms states to restore. The `redo` command uses `Model#canRedoVms()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the patient manager, such as `list`, will usually not call `Model#commitVms()`, `Model#undoVms()` or `Model#redoVms()`. Thus, the `patientManagerStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitVms()`. Since the `currentStatePointer` is not pointing at the end of the `patientManagerStateList`, all patient manager states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire patient manager.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the patient being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


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

* has a need to manage a significant number of patients
* has a need to manage patient schedules
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps for speed and efficiency

**Value proposition**: VMS provides an efficient solution for managing vaccine stock, determining patient suitability and scheduling appointments, and improving administrative efficiency and patient care. The system is optimised for typist, making it an ideal choice for busy administrative staff who type fast and prefer a CLI-first design.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                          | I want to …​                                                                               | So that I can …​                                                                  |
| -------- | -------------------------------- | ------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------- |
| `* * *`  | Receptionist                     | check the list of patients details                                                         | verify and mark the new arrival's attendance                                      |
| `* * *`  | Receptionist                     | check the patient's medical records                                                        | prepare the right type of vaccination                                             |
| `* * *`  | Receptionist                     | get the patient's contact easily                                                           | contact them when needed.                                                         |
| `* * *`  | Receptionist                     | be able to key in people with the same name                                                | save the data of people with the same name.                                       |
| `* * *`  | Receptionist                     | add a patient                                                                              |                                                                                   |
| `* * *`  | Receptionist                     | update a patient                                                                           |                                                                                   |
| `* * *`  | Receptionist                     | delete a patient                                                                           |                                                                                   |
| `* * *`  | Receptionist                     | view appointment details                                                                   | recall the details of an appointment                                              |
| `* * *`  | Receptionist                     | add an appointment                                                                         |                                                                                   |
| `* * *`  | Receptionist                     | delete an appointment                                                                      |                                                                                   |
| `* * *`  | Receptionist                     | update an appointment                                                                      |                                                                                   |
| `* * *`  | Receptionist                     | view vaccination type details                                                              | recall the details of a vaccination type                                          |
| `* * *`  | Receptionist                     | add a vaccination type                                                                     |                                                                                   |
| `* * *`  | Receptionist                     | delete a vaccination type                                                                  |                                                                                   |
| `* * *`  | Receptionist                     | update a vaccination type                                                                  |                                                                                   |
| `* * *`  | Receptionist                     | view allergy details                                                                       | recall the details of an allergy                                                  |
| `* * *`  | Receptionist                     | add an allergy                                                                             |                                                                                   |
| `* * *`  | Receptionist                     | delete an allergy                                                                          |                                                                                   |
| `* * *`  | Receptionist                     | update an allergy                                                                          |                                                                                   |
| `* * *`  | New user wanting to use the app  | purge all the current data                                                                 | get rid of sample/experiment data                                                 |
| `* *`    | Junior receptionist              | view a quick start guide easily                                                            | learn the basic features of the system quickly                                    |
| `* *`    | Receptionist                     | update the status of a patient                                                             | they can be tracked and not go missing                                            |
| `* *`    | Potential user exploring the app | see the app populated with sample data                                                     | use it as a tutorial for how the app will work                                    |
| `* *`    | Expert user                      | enter arguments in masses                                                                  | type less                                                                         |
| `* *`    | Receptionist                     | check what is the next free appointment slot                                               | choose the best time for the patient                                              |
| `* *`    | Expert user                      | enter shortened command names to perform the same command                                  | type less                                                                         |
| `* *`    | Receptionist                     | filter vaccination types                                                                   | find the vaccination type that I am looking for easier                            |
| `* *`    | Receptionist                     | add custom reference names to vaccination types                                            | refer to a vaccination type with shortened names                                  |
| `*`      | Receptionist                     | check the appointment schedule                                                             | see when an open slot is                                                          |
| `*`      | Receptionist                     | check the status of patients                                                               | know when to let the next waiting in line in                                      |
| `*`      | Overworked receptionist          | automate checking the number of patients that are still in                                 | know whether to let the next patient in quicker                                   |
| `*`      | Receptionist                     | view the appointments made for the day                                                     | roughly know how many patients to expect that day to allocate enough vaccinations |
| `*`      | Receptionist                     | see the status of all patients                                                             | know where they are                                                               |
| `*`      | Receptionist                     | check the list of patients                                                                 | prepare the correct number of vaccinations                                        |
| `*`      | New user                         | view the user guide easily                                                                 |                                                                                   |
| `*`      | Forgetful user                   | see what commands there are                                                                | know what commands I can use                                                      |
| `*`      | Forgetful user                   | see the syntax of commands                                                                 | know how to use the command                                                       |
| `*`      | Stock checker                    | see what vaccinations are expiring                                                         | know what I need to dispose                                                       |
| `*`      | Expert user                      | use bash script to generate reports                                                        | automate the process                                                              |
| `*`      | Receptionist                     | view the error occurred due to typographical errors                                        | understand exactly what went wrong                                                |
| `*`      | Receptionist                     | check if a patient is compatible with a vaccination                                        | am able to tell if he can take that vaccination                                   |
| `*`      | Receptionist                     | know which patients are done with the 30 min monitoring after vaccinate (to check allergy) | tell them to go home                                                              |
| `*`      | Receptionist                     | rely on the system to verify that the patient has done the order of vaccinations correctly | verify that they can be safe                                                      |
| `*`      | Forgetful receptionist           | view a list of commands entered into the application                                       | verify that the entries are correct                                               |
| `*`      | Receptionist                     | find patients who did not come                                                             | send them a reminder to take their vaccination                                    |
| `*`      | Receptionist                     | archive/hide unused data                                                                   | am not distracted by irrelevant data                                              |
| `*`      | Receptionist                     | undo my latest action                                                                      | do not accidentally nuke all data                                                 |
| `*`      | User                             | view the records that I have deleted                                                       | so that I can add them back when needed                                           |

### Use cases

For all use cases below, the **System** is the `VMS` and the **Actor** is the `user`, unless specified otherwise.

--------------------------------------------------------------------------------------------------------------------

#### UC-PAT-001 - Add patient

##### MSS

1. User requests to add patient.
2. VMS adds the patient.

  Use case ends.

--------------------------------------------------------------------------------------------------------------------

#### UC-PAT-002 - List patients

##### MSS

1. User requests to list patients.
2. VMS shows a the list of patients with their corresponding IDs.

  Use case ends.

--------------------------------------------------------------------------------------------------------------------

#### UC-PAT-003 - Update patient

##### MSS

1. User requests to list patients.
2. VMS shows a the list of patients with their corresponding IDs.
3. User requests to update a specific patient in the list with the args.
4. VMS updates the patients

    Use case ends.

##### Extensions

* 2a. The list is empty.

  Use case ends.

* 3a. The given ID is invalid.

  * 3a1. VMS shows an error message.

      Use case resumes at step 1.

#### UC-PAT-004 - Delete patient

##### MSS

1. User requests to list patients.
2. VMS shows a the list of patients with their corresponding IDs.
3. User requests to delete a specific patient in the list.
4. VMS soft deletes the patient.

    Use case ends.

##### Extensions

* 2a. The list is empty.

  Use case ends.

* 3a. The given ID is invalid.

  * 3a1. VMS shows an error message.

      Use case resumes at step 1.

#### UC-APT-001 - Add appointment

##### MSS

1. User request to add an appointment.
2. User enters the start and end timing of the appointment, and the associated patient.
3. VMS adds the appointment.

    Use case ends.

##### Extensions

* 2a. timing format or patient ID is invalid.

  * 2a1. VMS shows an error message.

      Use case resumes at step 1.

#### UC-APT-002 - View appointments

##### MSS

1. User requests to list appointments.
2. VMS shows a list of appointments.

    Use case ends.

#### UC-APT-003 - Update appointment

##### MSS

1. User requests to list appointments.
2. VMS shows a list of appointments.
3. User requests to update a specific appointment in the list using the ID with the args.
4. VMS update the appointment.

    Use case ends.

##### Extensions

* 2a. The list is empty.

  Use case ends.

* 3a. The given ID is invalid.

  * 3a1. VMS shows an error message.

      Use case resumes at step 1.

#### UC-APT-004 - Delete appointment

##### MSS

1. User requests to list appointments.
2. VMS shows a list of appointments.
3. User requests to delete a specific appointment in the list.
4. VMS deletes the appointment.

    Use case ends.

##### Extensions

* 2a. The list is empty.

  Use case ends.

* 3a. The given ID is invalid.

  * 3a1. VMS shows an error message.

      Use case resumes at step 1.

#### UC-VAC-001 - Add vaccination type

##### MSS

1. User enters command to add a vaccination type.
2. VMS adds the vaccination type.<br>
    Use case ends.

##### Extensions

* 2a. Command contains syntax errors
  * 2a1. VMS shows an error message.<br>
    Use case resumes from step 1.
* 2b. Vaccination type already exists.
  * 2b1. VMS shows an error message.<br>
      Use case resumes from step 1.

#### UC-VAC-002 - List vaccination types

##### MSS

1. User enters the command to list vaccination types.
2. VMS lists all available vaccination types.

##### Extensions

* 2a. Command contains syntax errors.
  * 2a1. VMS shows an error message.<br>
    Use case resumes from step 1.

#### UC-VAC-003 - Update vaccination type

##### MSS

1. User enters command to update a vaccination type.
2. VMS updates the vaccination type.

##### Extensions

* 2a. Command contains syntax errors.
  * 2a1. VMS shows an error message.<br>
    Use case resumes from step 1.
* 2b. Vaccination type does not exist.
  * 2a1. VMS shows an error message.<br>
    Use case resumes from step 1.

#### UC-VAC-004 - Delete vaccination type

##### MSS

1. User enters command to delete a vaccination type.
2. VMS deletes the vaccination type.

##### Extensions

* 2a. Command contains syntax errors.
  * 2a1. VMS shows an error message.<br>
    Use case resumes from step 1.
* 2b. Vaccination type does not exist.
  * 2a1. VMS shows an error message.<br>
    Use case resumes from step 1.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to handle the following limits without any noticeable sluggishness in performance on typical usage:
   1. 1000 patients, each with a limit of:
      1. 100 allergies
      2. 30 vaccines
   2. 30 vaccination types, each with a limit of:
      1. 10 groups
      2. 30 ingredients
      3. 10 requirements
   3. 1000 appointments.
3. On top of 2, should also be able to handle these names up to 30 characters without any noticeable sluggishness in performance on typical usage:
   1. Allergy
   2. Vaccination name
   3. Vaccination group name
   4. Vaccination ingredient name
4. On top of 2, should also be able to handle requirement set of sizes up to 30.
5. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
6. Should auto save to prevent data loss if application crashes.
7. All functionalities should be accessible through CLI.

### Glossary

* **VMS**: Vaccination Management System
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private patient detail**: A patient detail that is not meant to be shared with others
* **Feature**: Group of feature commands
* **Vaccination**: The Covid-19 vaccine
* **Patient**: Someone receiving the vaccine
* **Receptionist**: The user of VMS
* **Command parameter**: Arguments that affect how the command work

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

### Deleting a patient

1. Deleting a patient while all patients are being shown

   1. Prerequisites: List all patients using the `list` command. Multiple patients in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No patient is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
