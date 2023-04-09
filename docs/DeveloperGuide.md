---
layout: page
title: Developer Guide
---

## Table Of Contents
{: .no_toc}

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started._](./SettingUp.md)

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the
[diagrams](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/docs/diagrams/) folder.
Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html)
to learn how to create and edit diagrams.

</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called
[`Main`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/Main.java) and
[`MainApp`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/MainApp.java).
It is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `del-doc 1`.

<img src="images/ArchitectureSequenceDiagramUpdated.png" width="574" alt="ArchitectureSeqDiagramUpdated" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

[Scroll back to Table of Contents](#table-of-contents)

### UI component

**API** : [`Ui.java`](https://github.com/AY2223S2-CS2103T-F12-1/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of multiple smaller components. Some notable examples are
`CommandBox`, `ResultDisplay` and `ContactDisplay`. All these, including the `MainWindow`,
inherit from the abstract `UiPart` class which captures the commonalities between classes that
represent parts of the visible GUI.

The `UI` component uses the JavaFX UI framework. The layout of these UI parts are defined in matching
`.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the
[`MainWindow`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/resources/##view/MainWindow.fxml)

The `UI` component does the following
* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Doctor` or `Patient` object residing in the `Model`.

[Scroll back to Table of Contents](#table-of-contents)

#### Main Window

The `MainWindow` houses all the components that make up the visual display of Docedex. Its primary
function is to listen to user input through the `CommandBox`, initiate the execution of the command,
and display the result through the `ResultDisplay` and/or `ContactDisplay`.

Here is a table containing a brief description of the purpose of the smaller components within `MainWindow`.

| **Name of component** | **Description**                                                                                                                                                                            |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `CommandBox`          | Allows users to enter Docedex commands.                                                                                                                                                    |
| `ResultDisplay`       | Provides CLI-based feedback upon a user command.<br/>Allows users to see if their command was successful or not.<br/>Provides error messages to guide user on how to use Docedex commands. |
| `ContactDisplay`      | Contains components that provide visual feedback upon manipulation of doctors and patients.<br/><br/>More details about these components can be found [here](#contact-display).            |
| `HelpWindow`          | Displays a help window containing a link to the User Guide.                                                                                                                                |
| `StatusBarFooter`     | Shows the location of the Docedex storage.                                                                                                                                                 |


The implementations of `CommandBox`, `ResultDisplay`, `StatusBarFooter`, and `HelpWindow` are relatively
straightforward. Therefore, this guide will not dive deeper into how these components are implemented.

You may refer to their implementations here
* [Classes](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/ui)
* [FXML](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/resources/view)

[Scroll back to Table of Contents](#table-of-contents)

#### Contact Display

The `ContactDisplay` houses all the components that provide visual feedback after the manipulation
of doctors and patients within Docedex.

Here is a table containing a brief description of the purpose of the smaller components within `ContactDisplay`.

| **Name of component**       | **Description**                                                                                                                                                                                                                                                             |
|-----------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `DoctorListPanel`         | Shows a list of `DoctorCard`. This list can be manipulated through commands.<br/><br/>Upon starting the app, this list will reflect all doctors in Docedex.<br/><br/>Upon selection of a `PatientCard`, this list will filter to show doctors assigned to said patient.  |
| `DoctorCard`              | Displays key information about a doctor, such as name, phone number, email and tags.                                                                                                                                                                                        |
| `PatientListPanel`        | Shows a list of `PatientCard`. This list can be manipulated through commands.<br/><br/>Upon starting the app, this list will reflect all patients in Docedex.<br/><br/>Upon selection of a `DoctorCard`, this list will filter to show patients assigned to said doctor. |
| `PatientCard`             | Displays key information about a patient, such as name, phone number, email and tags.                                                                                                                                                                                       |
| `EnlargedDoctorInfoCard`  | Displays all information about a selected doctor.                                                                                                                                                                                                                           |
| `EnlargedPatientInfoCard` | Displays all information about a selected patient.                                                                                                                                                                                                                          |

Here is a class diagram of how these components come together within the `ContactDisplay`.

![Structure of the Contact Display](images/ContactDisplayClassDiagram.png)

How the `ContactDisplay` works:
1. Upon a user command through the `CommandBox`, the `setFeedbackToUser(CommandResult commandResult)` method
  takes in the result of the command entered. The `CommandResult` contains information on whether the command
  requires an update to the GUI. If such an update is required, `ContactDisplay` will proceed to update
  all relevant components. 
2. Upon a mouse click on a `DoctorCard` or `PatientCard`, the following sequence of actions is similar to
  that described above. However, instead of `setFeedbackToUser(CommandResult commandResult)` being called,
  either `setFeedbackUponSelectingDoctor(Doctor doctor)` or `setFeedbackUponSelectingPatient(Patient patient)`
  is called respectively.

To illustrate how these interactions work, let's say that the user selects a doctor through the `sd` command.
The Sequence Diagram below illustrates the interactions within the `ContactDisplay` component upon such a command.

![Sequence Diagram of the Contact Display Upon Command](images/ContactDisplaySequenceDiagram.png)

Upon a user command, we see from the diagram that the `setFeedbackToUser(CommandResult commandResult)` method
accomplished the following:
- Update filtered patients list in `LogicManager` to show the patients assigned to the selected doctor.
- Select the requested doctor within the `DoctorListPanel`. (This is a purely cosmetic selection, to provide
visual feedback to the user)
- Update the `EnlargedDoctorInfoCard` to display the information of the selected doctor.
- Place the updated `EnlargedDoctorInfoCard` onto the placeholder `StackPane` which resides on the right-most
column of the `ContactDisplay`. For more information on this specific step, click [here](#enlarged-info-card-feature).

[Scroll back to Table of Contents](#table-of-contents)

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="450" alt="LogicClassDiagram"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddDoctorCommand`)
   which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a doctor).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete-doc 1")`
API call.

![Interactions Inside the Logic Component for the `del-doc 1` Command](images/DeleteDoctorSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteDoctorCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600" alt="ParserClasses"/>

How the parsing works:

* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddDoctorCommandParser`) which uses the other classes shown above to
  parse the user command and create a `XYZCommand` object (e.g., `AddDoctorCommand`) which the `AddressBookParser`
  returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddDoctorCommandParser`, `DeleteDoctorCommandParser`, ...) inherit from
  the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

[Scroll back to Table of Contents](#table-of-contents)

### Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/UpdatedModelClassDiagram.png" width="450" alt="UpdatedModelClassDiagram"/>

<img src="images/PersonPackageClassDiagram.png" alt="PersonPackageClassDiagram" />

The `Model` component,

* stores the address book data i.e., all `Doctor` and `Patient` objects (which are contained in a `UniqueDoctorList`
  and `UniquePatientList` object).
* stores the currently 'selected' `Doctor` and `Patient` objects (e.g., results of a search query) as a separate
  _filteredDoctors_ and _filteredPatients_ list which is exposed to outsiders as an
  unmodifiable `ObservableList<Doctor>` and `ObservableList<Patient>` that can be 'observed' e.g. the UI can be bound to
  this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as
  a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)


[Scroll back to Table of Contents](#table-of-contents)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)


![Structure of the Storage](images/StorageClassDiagram.png)


The `Storage` component does the following:
* Saves both address book and user preference data in JSON format after every command
* Read saved data and create the corresponding objects when starting up Docedex.

Notes about the `Storage` component
* Inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as
either one (if only the functionality of only one is needed).
* Depends on some classes in the `Model` component (because the `Storage` component's
job is to save/load objects that belong to the `Model`)

#### Notes about storing assignments between doctors and patients
{: .no_toc}

In our `Model`, the `Doctor` contains a list of `Patient` that they are assigned to,
and the `Patient` contains a list of `Doctor` that they are assigned to.

However, this causes issues when we try to store these objects as is, since the bidirectional
navigability will result in an endless loop if we try to convert these objects into their JSON
format. While the creation of an association class to store information about assignment is ideal,
this will be implemented in the future due to time constraints.

Therefore, as of v1.4, `Storage` only stores the information of assignment within the `JsonAdaptedDoctor`.
In essence, it stores the doctors together with their assigned patients. So each doctor will have a JSON key
named `patients` that stores a dictionary of `JsonAdaptedPatient` that represent each assigned patient.

That leaves us with the unassigned patients within the `Model`. These patients are stored separately
under another JSON key named `unassignedPatients`.

`JsonAdaptedPatient` does not store any information about the doctors that were assigned to each patient.

[Scroll back to Table of Contents](#table-of-contents)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

[Scroll back to Table of Contents](#table-of-contents)

---

## Implementation

### Add Doctor Feature

#### What it does

{: .no_toc}

Adds a doctor to the bottom of the list of currently existing doctors. Users are able to add any valid doctor to the
list. If a record of the same doctor already exists in the list, the command will not be allowed and an error will be
thrown to alert user.

Example Use: `add-doc n/John Doe p/98765432 e/johnd@example.com s/Cardiology y/5 t/surgeon`

#### Implementation

{: .no_toc}

Upon entry of the add doctor command, an `AddDoctorCommand` class is created. The `AddDoctorCommand` class extends the
abstract `Command` class and implements the `execute()` method. Upon execution of this method, a `Doctor` object is
added to the model’s list of doctors if all the attributes provided are valid and a duplicate instance does not exist.

Given below is an example usage scenario of how the add doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `add-doc n/John Doe p/98765432 e/johnd@example.com s/Cardiology y/5 t/surgeon` to save a doctor.

Step 3. The doctor is added to the model’s list of doctors if valid.

The following sequence diagram illustrates how the add doctor operation works:

![](images/AddDoctorSequenceDiagram.png)
*args: Refers to a valid sequence of arguments provided by the user. Example: "n/John Doe p/98765432 e/johnd@example.com
s/Cardiology y/5 t/surgeon"

[Scroll back to Table of Contents](#table-of-contents)

### Add Patient Feature

#### What it does

{: .no_toc}

Adds a patient to the bottom of the list of currently existing patients. Users are able to add any valid patient to the
list. If a record of the same patient already exists in the list, the command will not be allowed and an error will be
thrown to alert user.

Example
Use: `add-ptn n/John Doe p/98765432 e/jdoe@gmail.com h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends`

#### Implementation

{: .no_toc}

Upon entry of the add patient command, an `AddPatientCommand` class is created. The `AddPatientCommand` class extends
the abstract `Command` class and implements the `execute()` method. Upon execution of this method, a `Patient` object is
added to the model’s list of patients if all the attributes provided are valid and a duplicate instance does not exist.

Given below is an example usage scenario of how the add doctor command behaves at each step.

Step 1. User launches the application

Step 2. User
executes `add-ptn n/John Doe p/98765432 e/jdoe@gmail.com h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends`
to save a patient.

Step 3. The patient is added to the model’s list of patients if valid.

The following sequence diagram illustrates how the add doctor operation works:

![](images/AddPatientSequenceDiagram.png)
*args: Refers to a sequence of valid arguments provided by the user. Example: "n/John Doe p/98765432 e/jdoe@gmail.com
h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends"

[Scroll back to Table of Contents](#table-of-contents)

### Edit Doctor Feature

#### What it does
{: .no_toc}

Users can edit specific doctors in the clinic by providing at least one of the optional fields. Existing values will be
updated to the input values and all other values will remain the same. The doctor to be edited can be specified through
the doctor's index.

Example Use: `edit-doc 2 n/Gabriel Tan p/12345678 s/Cardiology`


#### Implementation
{: .no_toc}

Upon entry of the edit doctor command, an `EditDoctorCommand` class is created. The `EditDoctorCommand` class extends
the abstract `Command` class and implements the `execute()` method. The `EditDoctorDescriptor` is created with the
arguments given
by the user. A new `Doctor` object is created with the new arguments, with the attributes of the old `Doctor` object
copied over
if the argument for that specific attribute is not provided by the user. `EditDoctorDescriptor` is then passed
to `EditDoctorCommandParser`.
The `EditDoctorCommand` is created using the `EditDoctorDescriptor`. Upon execution of `EditDoctorCommand`, a `Doctor`
object is added to the model’s list of doctors if all the attributes provided are valid and a duplicate instance does
not exist.

The following activity diagram illustrates the user flow for editing a doctor:

![](images/EditDoctorActivityDiagram.png)

Given below is an example usage scenario of how the add doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `edit-doc n/Simon` to edit a doctor.

Step 3. The doctor is edited and saved to the model’s list of doctors if valid.

The following sequence diagram illustrates how the edit doctor operation works:

![](images/EditDoctorSequenceDiagram.png)

[Scroll back to Table of Contents](#table-of-contents)

### Delete Doctor Feature

#### What it does

{: .no_toc}

Deletes a doctor at the specified **one-based index** of list of currently existing/found doctors. Users are able to
delete any doctor in the list. If an index larger than or equal to the size of the doctor’s list is provided, the
command will not be allowed and an error will be thrown to alert user.

Example Use: `del-doc 1`

#### Implementation

{: .no_toc}

Upon entry of the delete doctor command, a `DeleteDoctorCommand` class is created. The `DeleteDoctorCommand` class
extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, the doctor at
specified **one-based index** is removed if the index provided is valid.

Given below is an example usage scenario of how the delete doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `del-doc 1` to delete the doctor at index 1 (one-based indexing).

Step 3. The doctor at this index is removed if the index provided is valid.

The following sequence diagram illustrates how the delete doctor operation works:

![](images/DeleteDoctorSequenceDiagram.png)

[Scroll back to Table of Contents](#table-of-contents)

### Delete Patient Feature

#### What it does

{: .no_toc}

Deletes a patient at the specified **one-based index** of list of currently existing/found patient. Users are able to
delete any patient in the list. If an index larger than or equal to the size of the patient’s list is provided, the
command will not be allowed and an error will be thrown to alert user.

Example Use: `del-ptn 1`

#### Implementation

{: .no_toc}

Upon entry of the delete doctor command, a `DeletePatientCommand` class is created. The `DeletePatientCommand` class
extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, the patient
at specified **one-based index** is removed if the index provided is valid.

Given below is an example usage scenario of how the delete patient command behaves at each step.

Step 1. User launches the application

Step 2. User executes `del-ptn 1` to delete the patient at index 1 (one-based indexing).

Step 3. The patient at this index is removed if the index provided is valid.

The following sequence diagram illustrates how the delete patient operation works:

![](images/DeletePatientSequenceDiagram.png)

[Scroll back to Table of Contents](#table-of-contents)

### Assign Patient Feature

#### What it does
{: .no_toc}

Assigns a patient at the specified **one-based index** of list of currently existing/found patient to a doctor at the specified **one-based index** of list of currently existing/found doctor. If indexes provided are larger or equal to the size of their respective lists, the command will not be allowed and an error will be thrown. If specified patient is already assigned to specified doctor, the command will nto be allowed and an error will be thrown.

Example Use: `assign-ptn ptn/1 doc/1`

#### Implementation
{: .no_toc}

Upon entry of the assign patient command, a `AssignPatientCommand` class is created. The `AssignPatientCommand` class extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, the patient at specified **one-based index** is assigned if the doctor at specified **one-based index** if both indexes provided are valid and the patient is not assigned to the doctor yet. The `AssignPatientCommand` class creates a new `Patient` object with `Doctor` reference and a new `Doctor` object with `Patient` reference. The previous `Patient` and `Doctor` objects are then replaced with the new `Patient` and `Doctor` objects

Given below is an example usage scenario of how the assign patient command behaves at each step.

Step 1. User launches the application

Step 2. User executes `assign-ptn ptn/1 doc/1` to assign the patient at index 1 to the doctor at index 1 (one-based indexing).

Step 3. If the indexes provided are valid and patient is not assigned to doctor yet, the patient at index 1 is replaced with a patient with doctor reference and the doctor at index 1 is replaced with a doctor with patient reference.

The following sequence diagram illustrates how the assign patient operation works:

![](images/AssignPatientSequenceDiagram.png)

### GUI Features

#### Enlarged Info Card feature

{: .no_toc}

As triage staff manage the contacts of doctors and patients, they may wish to pull up
the information related to the doctor or patient. Therefore, the right-most column within
Docedex has been reserved to show the personal information of the selected doctor or patient.

![](images/NewUi.png)

##### Brief introduction to the components involved

{: .no_toc}

Let's call the card which displays this information **info cards**. However, the information
displayed for a doctor compared to a patient has a few differences. Thus, two different info cards
are required - one to display patient information and one to display doctor information.

Let's call these cards `EnlargedDoctorInfoCard` and `EnlargedPatientInfoCard`. However, we
only have one `StackPane` to display the information of the queried doctor or patient. This 
`StackPane` spans over the right-most column seen in the GUI above, and serves as a placeholder
for the info cards. So, we now need a way to toggle between displaying either card, depending
on whether the user has selected a doctor or patient to view.

[Scroll back to Table of Contents](#table-of-contents)

##### Exploring the user journey

{: .no_toc}

To explore how this is implemented, we will focus on the user clicking on a `DoctorListViewCell`
representing a doctor, though the ideas below can be extended to the user clicking on a
`PatientListViewCell`, as well as other ways of querying for a doctor or patient
(ie. through [`sd`](./UserGuide.md#select-doctor) or [`sp`](./UserGuide.md#select-patient) command).

Below, we see the sequence diagram of how a mouse click from the user on the `DoctorListViewCell`
causes the display of information related to the doctor card through the `EnlargedDoctorInfoCard`.

![](images/UserClickDoctorCardSequenceDiagram.png)

[Scroll back to Table of Contents](#table-of-contents)

##### More details on implementation

{: .no_toc}

Before diving into the details, here are a few key points to note:
- The `ContactDisplay` contains all three columns shown in the middle of the GUI.
These columns represent the doctors list, patients list and an info card respectively,
from left to right.
- There is always only one instance of the `EnlargedDoctorInfoCard`,
`EnlargedPatientInfoCard`, `ContactDisplay` and placeholder `StackPane`.

[Scroll back to Table of Contents](#table-of-contents)

When the user clicks on a `DoctorListViewCell`, the `setFeedbackUponSelectingDoctor()`
call initiates the process of updating the relevant UI components to display
information about the doctor. As part of this function, the `ContactDisplay` updates the
doctor displayed in the `EnlargedDoctorInfoCard`. Lastly, the `ContactDisplay` clears the
current display within the placeholder `StackPane`, and adds the `EnlargedDoctorInfoCard`
as the child node of the `StackPane`.

At the end of this journey, the `EnlargedDoctorInfoCard` containing the information
of the selected doctor is displayed on the right-most column on the Docedex GUI.

A similar process happens when the user clicks on a `PatientListViewCell`, with
the `EnlargedPatientInfoCard` being populated with the appropriate data and displayed instead.

[Scroll back to Table of Contents](#table-of-contents)

##### Alternatives considered

{: .no_toc}

In the past, we had a different implementation of this feature, where the `DoctorListViewCell`
called a function in a controller named `EnlargedInfoCardController` instead of the `ContactDisplay`.
The controller would be in charge of keeping the state of whether to display the doctor or patient.
All UI components would then refer to this controller to update themselves.

However, upon implementation, it was realised that the `ContactDisplay` contained all the UI components
that needs to be updated when a user selects a doctor or patient. Therefore, it was simpler and more
purposeful to let the `ContactDisplay` handle this update. Hence, the controller was removed and the
current design was favoured.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## **Documentation, Logging, Testing, Configuration, Dev-Ops**

Here are some useful links on the following!

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

## Appendix A: Planned Enhancements

### User Interface

#### Problem 1: Clearing selections on execution of other commands

- Scenario: User selected a patient, before using the [`list-doc` command](./UserGuide.md#listing-all-doctors).
- What you see: The user interface does not clear the selection on the previously selected card. However, all the
  doctors are listed on the GUI.
- Why did it happen: `list-doc` command does not involve selection of patient cards.
- Potential issue: Confusion as to whether the listed doctors are assigned to the previously selected patient or not.

Note to users and developers: This problem exists if you select a doctor and then enter `list-ptn` too

**Solution:** The list commands will clear any selection of patient or doctor cards.

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 2: Non-descriptive patient/doctor list titles

- Scenario: User adds a doctor using the [`add-doc` command](./UserGuide.md#adding-a-doctor).
- What you see: Docedex automatically selects the newly added doctor. The patients list disappears, as the newly added
  doctor has no assigned patients.
- Why did it happen: Detailed [here](./UserGuide.md#adding-a-doctor).
- Potential issue: No visual feedback that the patients list is reflecting the newly added doctor's assigned
  patients/confusion that all patient's have been deleted.

Note to users and developers: This problem exists if you add a patient using `add-ptn` too
**Solution:** The title of the patients list will be updated to display "XXX's Patients" on affected doctor commands.
The same fix will be done for the displayed doctors list on affected patient commands.
Note: XXX is the name of the newly added doctor/patient

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 3: The command usage message is not consistent across the UserGuide and EditXYZCommands

- Scenario: User enters an invalid variation of the `edit-doc` or `edit-ptn` commands
- What you see: Error message that does not indicate that the entry of only one update parameter is compulsory while all
  others are optional.
- Why did it happen: Implementation of feature freeze prior to resolution
- Potential issue: User assumes that all fields are compulsory

- **Solution:** Update the `MESSAGE_USAGE` field
  in [`EditDoctorCommand`](https://github.com/AY2223S2-CS2103T-F12-1/tp/tree/master/src/main/java/seedu/address/logic/commands/EditDoctorCommand.java), [`EditPatientCommand`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/commands/EditPatientCommand.java)
  and associated
  tests ([`EditDoctorCommandTest`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/test/java/seedu/address/logic/commands/EditDoctorCommandTest.java), [`EditPatientCommandTest`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/test/java/seedu/address/logic/commands/EditPatientCommandTest.java))
  with parameter information and notation present in the [UserGuide](./UserGuide.md#editing-a-doctor) and the
  line `"At least one parameter other than INDEX should be provided\n"`.

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 4: Use of association classes instead of lists to capture doctor-patient relationship on assignment

<div markdown="span" class="alert alert-danger">
To be added
</div>

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 5: Extending duplicates validation to other parameters

<div markdown="span" class="alert alert-danger">
To be added
</div>

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 6: Adding input validation to find commands

<div markdown="span" class="alert alert-danger">
To be added
</div>

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 7: Improving regex validation

<div markdown="span" class="alert alert-danger">
To be added
</div>

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 8: Use of enums as patient statuses

<div markdown="span" class="alert alert-danger">
To be added
</div>

[Scroll back to Table of Contents](#table-of-contents)


### Appendix B: Product scope

**Target user profile**
We hope to target admin staff within a clinic who have to settle triaging of patients.<br>
Here are some characteristics of our target user profile: <br>

* needs to manipulate patient and doctor information quickly
* needs to assign patients to the appropriate doctors quickly
* prefer desktop apps over other mediums
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Perform quick lookup and assignment of appropriate doctors to each patient in triage,
faster than a typical mouse/GUI driven app.

[Scroll back to Table of Contents](#table-of-contents)

### Appendix C: User stories

In the table below, **_user_** refers to the triage admin staff.

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`


| Priority | As a …​ | I want to …​                                                              | So that I can…​                                                                  |
|----------|---------|---------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `* * *`  | user    | add doctor/patient contacts into my address book                          | store their contacts in case I need them in the future                           |
| `* * *`  | user    | look up doctors by their name, specialty and/or years of experience       | assign patients to relevant doctors                                              |
| `* * *`  | user    | look up patients by their name,                                           | view which doctors treated found patients or verify their diagnosis              |
| `* * *`  | user    | edit the doctor/patient contacts in Docedex                               | keep the doctor/patient contacts up to date                                      |
| `* * *`  | user    | delete doctors/patients that have left the hospital, died or have retired | ensure all doctor/patient contacts are relevant                                  |
| `* * *`  | user    | save my doctor/patient contacts in my desktop                             | refer to doctor/patient contacts in the future                                   |
| `* * *`  | user    | load my contacts from a file when I boot up the application               | refer to doctor/patient contacts created in the past                             |
| `* * *`  | user    | tag patients with a status                                                | determine assignment priorities to doctors                                       |
| `* * *`  | user    | detect duplicate entries                                                  | either halt operation or update information.                                     |
| `* *`    | user    | access the help menu                                                      | know how to use the commands within Docedex                                      |
| `* *`    | user    | tag patients to the doctor                                                | keep track of patients treated by the doctor                                     |
| `* *`    | user    | tag doctors to the patients                                               | keep track of doctors treating the patient                                       |
| `* *`    | user    | exit the application through the CLI                                      | terminate use of the application                                                 |
| `*`      | user    | see the history of doctors I viewed recently                              | re-access recently queried doctor contacts quickly                               |
| `*`      | user    | self-destruct my address book                                             | protect clinic's information in the event of a cyber-attack (last-ditch effort). |
| `*`      | user    | select doctors/patients through the CLI                                   | minimize the use of a mouse/pointing device                                      |

[Scroll back to Table of Contents](#table-of-contents)

### Appendix D: Use cases

For all use cases below, we assume the following unless specified otherwise

- The **System** is `Docedex`
- The **Actor** is the `user`
- The following preconditions
    - The `user` has launched the `Docedex` application.

Furthermore, a lot of **use cases are similar when manipulating
doctors and patients**. Therefore, to keep the developer guide concise, the
use cases elaborated upon below are only detailed for doctors. Nonetheless, they
can be extrapolated for patients too, without changes to the major details within
the use case. Such associated pairs of use cases are listed in the table below.

| **Doctor Use Case**            | **Respective Patient Use Case** |
|--------------------------------|---------------------------------|
| UC1 - Add Doctor               | UC7 - Add Patient               |
| UC2 - Delete Doctor            | UC8 - Delete Patient            |
| UC3 - Edit Doctor              | UC9 - Edit Patient              |
| UC4 - Find Doctor              | UC10 - Find Patient             |
| UC5 - List Doctor              | UC11 - List Patient             |
| UC6 - Assign Doctor to Patient | UC12 - Assign Patient to Doctor |

#### Use case: UC1 - Add Doctor

**MSS**

1. User requests to add a doctor by specifying information about the doctor.
2. Docedex confirms the addition of the doctor.<br>
   Use case ends.

**Extensions**

* 1a. User enters invalid command.
    * 1a. Docedex detects error in command.
        * 1a1. Docedex prompts user to correct the format of the command. <br>
        * 1a2. User enters command and information to add a doctor.<br>
          Steps 1a1-1a2 are repeated until a valid add command is entered.<br>
          Use case resumes from step 2.
* 1b. Docedex detects duplicate doctor entry.
    * 1b1. Docedex prompts user to not enter duplicate information <br>
    * 1b2. User re-enters command to add a doctor.<br>
      Steps 1b1-1b2 are repeated until a unique entry is entered.<br>
      Use cases resumes from step 2.

[Scroll back to Table of Contents](#table-of-contents)

#### Use case: UC2 - Delete Doctor

**MSS**

1. User requests to delete a specific doctor.
2. Docedex confirms the deletion of the doctor contact.<br>
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command format.
    * 1a1. Docedex requests to correct the format of the command.
    * 1a2. User enters command to delete a doctor.<br>
      Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
      Use case resumes from step 2.
* 1b. Docedex detects that the requested doctor does not exist.
    * 1b1. Docedex alerts the user that the requested doctor does not exist.
    * 1b2. User re-enters the command.<br>
      Steps 1b1-1b2 are repeated until the user enters a doctor that exists in Docedex.<br>
      Use case resumes from step 2.

[Scroll back to Table of Contents](#table-of-contents)

#### Use case: UC3 - Edit Doctor

**MSS**

1. User requests to edit a doctor's information by specifying the updated information.
2. Docedex confirms the update of the doctor's information.<br>
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command format.
    * 1a1. Docedex requests to correct the format of the command.
    * 1a2. User enters command to delete a doctor.<br>
      Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
      Use case resumes from step 2.
* 1b. Docedex detects duplicate doctor entry.
    * 1b1. Docedex prompts user to not enter duplicate information <br>
    * 1b2. User re-enters command to edit a doctor.<br>
      Steps 1b1-1b2 are repeated until the edited doctor does not exist in Docedex.<br>
      Use cases resumes from step 2.
* 1c. Docedex detects that the requested doctor does not exist.
    * 1c1. Docedex alerts the user that the requested doctor does not exist.
    * 1c2. User re-enters the command.<br>
      Steps 1c1-1c2 are repeated until the user enters a doctor that exists in Docedex.<br>
      Use case resumes from step 2.

[Scroll back to Table of Contents](#table-of-contents)

#### Use case: UC4 - Find Doctor

**MSS**

1. User requests to find doctors that meet a particular criteria
2. Docedex shows a list of doctors that meet the criteria requested by user.
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command format.
    * 1a1. Docedex requests to correct the format of the command.
    * 1a2. User enters command to delete a doctor.<br>
      Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
      Use case resumes from step 2.

[Scroll back to Table of Contents](#table-of-contents)

#### Use case: UC5 - List Doctor

**MSS**

1. User requests to list all doctors in Docedex.
2. Docedex shows a list of all doctors stored.
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command format.
    * 1a1. Docedex requests to correct the format of the command.
    * 1a2. User enters command to delete a doctor.<br>
      Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
      Use case resumes from step 2.

[Scroll back to Table of Contents](#table-of-contents)

#### Use case: UC6 - Assign Doctor To Patient

**MSS**

1. User requests to assign a doctor to a patient in Docedex.
2. Docedex confirms the assignment of the doctor to the patient.
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command format.
    * 1a1. Docedex requests to correct the format of the command.
    * 1a2. User enters command to delete a doctor.<br>
      Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
      Use case resumes from step 2.
* 1b. Docedex detects that the requested doctor does not exist.
    * 1b1. Docedex alerts the user that the requested doctor does not exist.
    * 1b2. User re-enters the command.<br>
      Steps 1b1-1b2 are repeated until the user enters a doctor that exists in Docedex.<br>
      Use case resumes from step 2.
* 1c. Docedex detects that the requested patient does not exist.
    * 1c1. Docedex alerts the user that the requested patient does not exist.
    * 1c2. User re-enters the command.<br>
      Steps 1c1-1c2 are repeated until the user enters a patient that exists in Docedex.<br>
      Use case resumes from step 2.
* 1d. Docedex detects that the patient and doctor are already assigned to each other.
    * 1d1. Docedex alerts the user that the patient and doctor are already assigned to each other.<br>
      Use case ends.

[Scroll back to Table of Contents](#table-of-contents)

### Appendix E: Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 doctor contacts and 1000 patient contacts without noticeable reduction in
   performance.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands)
   should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should not utilize any network to transmit any information.
5. The average time required to boot up the application should be under 10 seconds.
6. Feedback from Docedex should be displayed within 2 seconds of the user's input.
7. The file size of the application's `jar` should not exceed 100MB.
8. Should utilize less than 2GB of memory when in use.

[Scroll back to Table of Contents](#table-of-contents)

### Appendix F: Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **User**: Triage Admin Staff within the clinic.
* **Contact**: Data entry that stores the contact information of a doctor or patient in Docedex.

[Scroll back to Table of Contents](#table-of-contents)

### Appendix G: Effort

This section is still being updated!

[Scroll back to Table of Contents](#table-of-contents)

### Appendix H: Instructions for manual testing

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source:
**Note:** These instructions only provide a starting point for testers to work on.
Testers are encouraged to do more *exploratory* testing.
</div>


#### Launch and shutdown

1. Initial launch

1. Download the jar file and copy into an empty folder

1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

1. Resize the window to an optimum size. Move the window to a different location. Close the window.

1. Re-launch the app by double-clicking the jar file.<br>
   Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

[Scroll back to Table of Contents](#table-of-contents)

#### Deleting a doctor

1. Deleting a doctor while all doctors are being shown

1. Prerequisites: List all doctors using the `list-doc` command. Multiple doctors in the list.

1. Test case: `del-doc 1`<br>
   Expected: First doctor contact is deleted from the displayed doctors list. Details of the deleted contact shown in
   the status message.

1. Test case: `del-doc 0`<br>
   Expected: No doctor is deleted. Error details shown in the status message. Status bar remains the same.

1. Other incorrect delete commands to try: `delete`, `del-doc x`, `...` (where x is larger than the list size)<br>
   Expected: Similar to previous.

1. _{ more test cases …​ }_

[Scroll back to Table of Contents](#table-of-contents)

#### Saving data

1. Dealing with missing/corrupted data files

1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
