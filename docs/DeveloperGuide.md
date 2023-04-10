---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
## **Preamble**
This is the developer guide for Patientist, a hospital management system that focuses on CLI-based user interface.
This guide will provide developers looking to extend the codebase with a diagram-oriented overview of the system's
implementation, detailing high level architecture all the way down to fine-grained component implementation. Class
diagrams are colour coded as
* Green: UI
* Blue: Logic
* Red: Model
* Yellow: Storage

and object diagrams are in black and white.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

<div style="page-break-after: always;"></div>

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

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/java/seedu/patientist/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/java/seedu/patientist/MainApp.java). It is responsible for,
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

<div style="page-break-after: always;"></div>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/java/seedu/patientist/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `DetailsPopup`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/java/seedu/patientist/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

<div style="page-break-after: always;"></div>

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/java/seedu/patientist/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `PatientistParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddPatientCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a `Patient`).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `PatientistParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddPatientCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddPatientCommand`) which the `PatientistParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddPatientCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

<div style="page-break-after: always;"></div>

### Model component
**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/java/seedu/patientist/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the patientist data, i,e all `Ward` objects (contained in a singleton `WardList` object),
all `Person` objects (contained within `UniquePersonList` objects within their respective wards)
* differentiates between `Patient` and `Staff` objects within each `Ward`, each are kept in their
separate `UniquePersonList`.
* stores the currently 'selected' `Person` objects (e.g. results of search, lsward, lsstf, lspat etc)
as separate filtered list which is available to outsiders as an unmodifiable `ObservableList<Person>`
that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when
the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside
as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities
of the domain, they should make sense on their own without depending on other components)

<div style="page-break-after: always;"></div>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-T12-1/tp/tree/master/src/main/java/seedu/patientist/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both patientist data and user preference data in json format, and read them back into corresponding objects.
* reads patientist data into a patientist object which will contain a list of ward objects which individually contain a list of patient and staff objects.
* inherits from both `PatientistStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.patientist.commons` package.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Wards feature
#### Implementation
The mechanism will extend the functionality provided by AddressBook's base code to provide for functionality
that allows `Staff` and `Patient`s to be grouped logically according to the `Ward` they are assigned to. This allows
us to implement the following functionalities:
* `contains(Patient)`
* `addPatient(Patient)`
* `deletePatient(Patient)`
* `setPatient(Patient)`

and similar functionality for `Staff` as well. This makes for easy implementation of searching for a particular
person within a ward and other operations involving transferring between wards, through providing a clean
abstraction that performs checking of duplicates, checking for presence/absence of a person, etc. These
changes reflect in the modification of the API supplied by `Model`, which requires specification of which ward
a `Staff` or `Patient` is to be added to, and also implements operations to add, remove and edit wards, and transfer
`Person` between wards.

Of course, in order to implement this functionality, we need to have the ability to add and delete `Ward` objects
as well. As such, all wards are stored in a `WardList` that enforces uniqueness of the `Ward` objects contained. This list
provides support for lookup, add, delete and modification of elements.

Other alternatives considered for this functionality were simply assigning the ward and role of a `Person` through the
`Tag` field that already exists in the base application. However, this makes for very poor object oriented design as
there would not exist any trace of wards in the model, which is intuitively a container object for `Staff` and `Patient`.
Furthermore, operations such as searching and deleting will become extremely counterintuitive as to delete a specific
ward along with all patients and staff inside, we would have to search through all `Patient` and `Staff` objects, look through
their tags and delete them one by one. Wards will thus become a fully abstract concept not modelled anywhere in the code,
which is not ideal as it is a core part of what our application seeks to manage.


Given below is a sample usage scenario and how the mechanism behaves at each step. For the sake of focus in the
illustration, we will only look at a subset of the Model package involved in maintaining this feature. Higher level
management logic such as `ModelManager` are not explicitly included, but obviously exist in the system nonetheless.

Step 1. The user first needs to add a `Ward` to the empty `Patientist`. The user input is parsed by `Logic`, eventually
making a call to `addWard()` specified by `Model`. Let's say the user first adds a `Ward` called `Block A Ward 1`.

![AddWardState1](images/WardFeatureStep1-After_adding_first_Ward.png)

Step 2. The user decides to add another `Ward` similar to in step 1, but this new ward is called `Block B Ward 1`.

![AddWardState2](images/WardFeatureStep2-After_adding_second_Ward.png)

Step 3. The user adds a `Staff`, let's say `Amy` and a `Patient`, let's say `Bob` to `Block A Ward 1`. Note that UniquePersonList
is a container for multiple `Person`. `Staff` and `Patient` inherit from `Person`, and are thus instances of `Person`.

![AddWardState3](images/WardFeatureStep3-After_adding_Patient_and_Staff.png)



Further notes about the wards feature:
* The identity of `Person` objects added to the ward system must be unique. This uniqueness is enforced by `Person::isSamePerson`
rather than `Person::equals`. The same person cannot exist in the same ward or in 2 or more different wards.
* As in the above point, it is illegal to modify an existing `Person` such that his or her identity is equal to another
`Person` object's identity. Doing this programmatically through `Model::setPatient` or `Model::setStaff` will throw a `DuplicatePersonException`, and doing this by
modifying json data files will result in undefined behaviour.
* `Ward` objects contained in `WardList` have their uniqueness enforced by name. In other words, there cannot exist 2 or
more wards with the same name.

For these data to be stored for subsequent sessions, the `Storage` module had to be modified to store a list of wards containing
`Patient` and `Staff` instead of a list of `Person`.

Given below is an overview of how the `Storage` module behaves after launching the Patientist app.

Step 1. The `Storage` module reads the data `JSON` file and deserialises it to form a `JsonSerializablePatientist` Object
which contains a list of `JsonAdaptedWard` which is made for each `Ward` that is stored. Each `JsonAdaptedWard` contains
a list of `JsonAdaptedPatient` and `JsonAdaptedStaff` which represents the numerous `Patient` and `Staff` that are in
the `Ward`

Step 2. The `Storage` module then calls creates the `Ward`, `Staff` and `Patient` using the blueprint provided by
`JsonAdaptedWard`, `JsonAdaptedStaff` and `JsonAdaptedPatient` respectively. Once done, the newly created `Ward` objects
are then stored in a new `Patientist` object, ready to be used.

The opposite occurs after a command, allowing us to save the data into a `JSON` file.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant amount of data
  * this can be in the form of patients and staff, and tracking personnel assignment in wards
* uses desktop/PC apps as a standard at the workplace
* can type fast and are comfortable with CLI
  * not necessarily a prerequisite to use the app
  * users that are required to use the app on a daily basis will become familiar with commands and CLI quickly
* has limited space for the PC
  * mobile workstations used in hospitals have limited space and can be decluttered by removing need for a mouse
* has a need to have access to both fine-grained detail and the big picture in the facility

**Value proposition**: Manage patient and staff data more efficiently than GUI app, given constraints
on workstation space. This app has a higher skill cap than GUI apps, but is more rewarding as users
familiar with the system can work faster and eliminate the need for an unwieldly mouse that they have
no space for.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                   | I want to …​                                                                            | So that I can…​                                                                                            |
|----------|-------------------------------------------|-----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| `* * *`  | new user                                  | see usage instructions                                                                  | refer to instructions when I forget how to use the app                                                     |
| `* * *`  | ward admin processing patients            | add and delete a patient                                                                | inpro and outpro the patients as they are hospitalised and discharged                                      |
| `* * *`  | ward doctor attending to patient          | add and delete new instructions and prescriptions for a patient                         | have a convenient record to access when treating patients                                                  |
| `* * *`  | ward admin recording manpower assignments | add and delete new doctor/nurse from the ward                                           | have a convenient record of which staff handles each ward                                                  |
| `*`      | ward doctor getting ready for shift       | add and delete events from my personal duty schedule                                    | track my schedule from the centralised system and easily access records of patients as I check my schedule |
| `*`      | lab assistant                             | add test results to a patient's information                                             | easily update the medical staff with the information they need for treatment                               |
| `* * *`  | ward nurse/doctor                         | view and edit todo list for each patient                                                | have a reliable reference for tasks I need to facilitate for each patient                                  |
| `* *`    | ward nurse/doctor administering medicine  | see a list of medical allergies of the patient on the patient's main page               | be reminded of which medicines cannot be prescribed at a glance                                            |
| `* * *`  | user unfamiliar with the hospital         | list all patients the system                                                            | find a particular patient without knowing their full name or ID                                            |
| `* * *`  | user                                      | list all patients in a particular ward                                                  | easily tell how many people are in the ward and the identities of patients in a ward                       |
| `* *`    | user looking for a patient                | search for patient by name                                                              | look up a patient without knowing his/her ID number                                                        |
| `* *`    | user looking for a patient                | search for patient by ID                                                                | look up a patient without knowing his/her name                                                             |
| `* * *`  | pharmacist issuing medication             | view prescription of patients                                                           | dispense medication while knowing the most updated list of medicine                                        |
| `* *`    | ward nurse/doctor moving a patient        | view important notes about a patient, such as being a fall risk                         | be reminded about each patient's unique needs                                                              |
| `* *`    | ward doctor in an emergency               | view the patient's status code on the patient's main page                               | know at a glance during an emergency if the patient has a Do Not Resuscitate order                         |
| `* *`    | user liasing with family members          | update the patient's status code                                                        | update the treatment/resuscitation requirements as the patient or family instructs                         |
| `* *`    | user                                      | view a patient's Next of Kin contacts                                                   | update the family members of the patient should issues arise                                               |
| `* *`    | ward admin registering NOK's contact      | edit a patient's Next of Kin contacts                                                   | change the contact number to call in an emergency should there be a need                                   |
| `* * *`  | user                                      | list all staff members                                                                  | have a high level view of manpower allocation                                                              |
| `* * *`  | user unfamiliar with the hospital         | list all ward names                                                                     | have a high level view of the facility's wards                                                             |
| `* *`    | doctor treating a patient                 | update list of medical allergies of a patient                                           | have the most recent information in the system and be assured it is correct                                |
| `* * *`  | ward admin                                | move patients between wards                                                             | keep the system up to date as patients get transferred around                                              |
| `* * *`  | doctor treating a patient                 | view and edit the full status page of a patient                                         | keep record of a comprehensive description of the patient for reference when making complicated decisions  |
| `* *`    | doctor treating a patient                 | view and edit the care priority status of each patient displayed on patient's main page | tell at a glance which patient needs more attention at a glance in emergencies                             |



### Use cases

(For all use cases below, the **System** is the `Patientist` and the **Actor** is the `user`, unless specified otherwise)

**Use case: See usage instructions**

**Actor: New user**

**MSS**

1.  User requests for instructions
2.  Patientist lists down instructions of how to use Patientist

    Use case ends.
___
**Use case: Add a patient**

**Actor: Ward admin**

**MSS**

1.  Ward admin chooses to add a patient.
2.  Patientist requests for details of the patient to be added.
3.  Ward admin enters the requested details.
4.  Patientist requests for confirmation.
5.  Ward admin confirms.
6.  Patientist adds the patient and displays the details of the patient.

    Use case ends.

**Extensions**

* 2a. Any of the requested details are missing.
    * 2a1. Patientist requests for the missing details.
    * 2a2. Ward admin enters the requested details.
    Steps 2a1-2a2 are repeated until all details are collected.
    Use case resumes from step 4.

      Use case ends.

* 3a. Any of the requested details are in wrong format.
    * 3a1. Patientist shows an error message indicating the details with wrong format.
    * 3a2. Patientist requests for the corrected details.
    * 3a3. Ward admin enters the requested details.
    Steps 3a1-3a3 are repeated until all details have correct format.
    Use case resumes from step 4.

      Use case ends

* *a. At any time, Ward admin chooses to cancel the addition of patient.
    * a1. Patientist requests to confirm the cancellation.
    * a2. Ward admin confirms the cancellation.

      Use case ends.
___
**Use case: Delete a patient**

**Actor: Ward admin**

**MSS**

1.  Ward admin chooses to delete a patient.
2.  Patientist requests for the name of the patient to be deleted.
3.  Ward admin enters the name of the patient to be deleted.
4.  Patientist shows the list of patients with the entered name.
5.  Ward admin enters the index number of the patient to be deleted.
6.  Patientist requests for confirmation.
7.  Ward admin confirms.
8.  Patientist deletes the patient and shows a message indicating deletion.

    Use case ends.

**Extensions**

* *a. At any time, Ward admin chooses to cancel the deletion of patient.
    * a1. Patientist requests to confirm the cancellation.
    * a2. Ward admin confirms the cancellation.

      Use case ends.
___
**Use case: Add a new instruction or prescription to a patient**

**Actor: Ward doctor**

**MSS**

1. Ward doctor <u>searches for a patient</u>.
2. Ward doctor chooses to add instruction or prescription to the selected patient.
3. Patientist requests for the content of the instruction or prescription.
4. Ward doctor enters an instruction or prescription.
5. Patientist adds the instruction or prescription to the details of the patient.

    Use case ends.
___
**Use case: Delete an instructions or a prescription of a patient**

**Actor: Ward doctor**

**MSS**

1. Ward doctor <u>searches for a patient</u>.
2. Ward doctor chooses an instruction or a prescription to be deleted.
3. Patientist requests to confirm the deletion.
4. Ward doctor confirms the deletion.
5. Patientist deletes the instruction or prescription from the details of the patient.

    Use case ends.
___
**Use case: Add a new doctor/nurse to the ward**

**Actor: Ward admin**

**MSS**

1. Ward admin <u>searches for a patient</u>.
2. Ward admin chooses to assign a new doctor/nurse to the patient.
3. Patientist requests for the name of doctor/nurse to be added.
4. Ward admin enters the name of the doctor/nurse.
5. Patientist adds the doctor/nurse to the patient.

    Use case ends.

**Extensions**

* 5a. The doctor/nurse with the entered name does not exist.
  Use case ends.
___
**Use case: Delete a doctor/nurse from the ward**

**Actor: Ward admin**

**MSS**

1. Ward admin <u>searches for a patient</u>.
2. Ward admin chooses to delete a doctor/nurse from the patient.
3. Patientist asks for the name of doctor/nurse to be deleted.
4. Ward admin enters the name of the doctor/nurse.
5. Patientist requests to confirm the deletion.
6. Ward admin confirms the deletion.
7. Patientist deletes the doctor/nurse from the patient.

**Extensions**

* *a. At any time, Ward admin chooses to cancel the deletion of patient.
    * a1. Patientist requests to confirm the cancellation.
    * a2. Ward admin confirms the cancellation.

    Use case ends.
___
**Use case: View todo list for a patient**

**Actor: Ward doctor/nurse**

**MSS**

1. Ward doctor/nurse <u>searches for a patient</u>.
2. Ward doctor/nurse chooses to view the todo list of the patient.
3. Patientist shows the patient.

Use case ends.
___
**Use case: Edit todo list for a patient**

**Actor: Ward doctor/nurse**

**MSS**

1. Ward doctor/nurse chooses to <u>view todo list for a patient</u>.
2. Ward doctor/nurse chooses a task to edit from todo list of the patient.
3. Patientist asks for how to edit the task.
4. Ward doctor/nurse enters the new content of the task.
5. Patientist saves the changed content of the task to the todo list of the patient.

    Use case ends.
___
**Use case: List all patient in the system**

**Actor: User unfamiliar with the hospital**

**MSS**

1. User chooses to list all patients in the system
2. Patientist shows the list of all patients in the system.

    Use case ends.
___
**Use case: List all patient in a particular ward**

**Actor: User**

**MSS**

1. User chooses to list all patients in a particular ward.
2. Patientist shows the list of all patients in the ward.

   Use case ends.
___
**Use case: Search for a patient by name**

**Actor: User**

**MSS**

1. User chooses to search for a patient by name.
2. Patientist requests for the name of the patient.
3. User enters the name of the patient.
4. Patientist shows the details of the patient.

   Use case ends.
___
**Use case: Search for a patient by ID**

**Actor: User**

**MSS**

1. User chooses to search for a patient by ID.
2. Patientist requests for the ID of the patient.
3. User enters the ID of the patient.
4. Patientist shows the details of the patient.

   Use case ends.
___
**Use case: List all staff members in the system**

**Actor: User**

**MSS**

1. User chooses to list all staff members in the system.
2. Patientist shows the list of all staff members in the system.

   Use case ends.
___
**Use case: List all ward names in the system**

**Actor: User**

**MSS**

1. User chooses to list all ward names in the system.
2. Patientist shows the list of all ward names in the system.

   Use case ends.
___
**Use case: Move patients between wards**

**Actor: Ward admin**

**MSS**

1. Ward admin chooses to move a patient to another ward.
2. Patientist requests for the name of the patient and ward to be moved.
3. Ward admin enters the name of the patient and ward to be moved.
4. Patientist moves the patient to the new ward.
5. Patientist shows the updated state of the patient.

   Use case ends.
___

<div style="page-break-after: always;"></div>


### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A first time user should be able to easily find help to learn how to use the app.
5. Should not take longer than a second to retrieve patient information.
6. Should be lightweight and be able to run on any computer with decently recent hardware.
7. Should be reliable and able to perform without failure in the majority of use cases during a month.
8. Should be available to be accessed by medical staff 24/7/365.
9. Should be easily troubleshot in the event of a failure. 
10. Should be easily scaled with different number of users.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **System**: The whole hospital system that uses Patientist.
* **User**: All people who have access to Patientist such as ward admin, ward doctor, and ward nurse.

<div style="page-break-after: always;"></div>

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

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. _{ more test cases …​ }_

---

# Planned Enhancements

1. Currently, the `addward` command is case-sensitive, so `addward n/Block A Ward 1` and `addward n/block A ward 1`
   would result in two different wards being added, even though it is likely that they should refer to the same ward.
   We plan to make this command case-insensitive to ensure duplicate wards are not added by accident.
2. The commands that add or delete todos and statuses from patients, `addpattodo`, `delpattodo`, `addpatstatus`, `delpatstatus`,
   currently change the order of patients in the list of patients. We plan to change this behaviour such that the index and
   position of patients is preserved when using these commands, so users would have an easier time tracking the patients.
3. The `view` command currently does not update in real time, so if a change is made using a command lke `addpattodo`,
   the change will not be visible until the `view` command is used again. We plan to change it such that the `view` command updates
   the information shown in the GUI after every change so that the correct information is always reflected in the view panel.
4. The `lsward` command also does not update in real time like `view`. Commands that change state of the ward like `addpat`
   may not have their changes reflected until `lsward` is called again. We plan to change this in the future such that the information
   shown in ward list always reflects the internal state of Patientist.
5. Currently, `help` only links the URL to this User Guide. We plan to change it in the future to show a command summary as well
   to simplify the process of finding the syntax for a certain command.
