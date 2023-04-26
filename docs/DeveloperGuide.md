---
layout: page
title: Developer Guide
---

## **Table Of Contents**
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
is specified
in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component does the following

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Doctor` or `Patient` object residing in the `Model`.

[Scroll back to Table of Contents](#table-of-contents)

#### Main Window
{: .no_toc}

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
{: .no_toc}

The `ContactDisplay` houses all the components that provide visual feedback after the manipulation
of doctors and patients within Docedex.

Here is a table containing a brief description of the purpose of the smaller components within `ContactDisplay`.

| **Name of component**     | **Description**                                                                                                                                                                                                                                                          |
|---------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `DoctorListPanel`         | Shows a list of `DoctorCard`. This list can be manipulated through commands.<br/><br/>Upon starting the app, this list will reflect all doctors in Docedex.<br/><br/>Upon selection of a `PatientCard`, this list will filter to show doctors assigned to said patient.  |
| `DoctorCard`              | Displays key information about a doctor, such as name, phone number, email and tags.                                                                                                                                                                                     |
| `PatientListPanel`        | Shows a list of `PatientCard`. This list can be manipulated through commands.<br/><br/>Upon starting the app, this list will reflect all patients in Docedex.<br/><br/>Upon selection of a `DoctorCard`, this list will filter to show patients assigned to said doctor. |
| `PatientCard`             | Displays key information about a patient, such as name, phone number, email and tags.                                                                                                                                                                                    |
| `EnlargedDoctorInfoCard`  | Displays all information about a selected doctor.                                                                                                                                                                                                                        |
| `EnlargedPatientInfoCard` | Displays all information about a selected patient.                                                                                                                                                                                                                       |

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

## **Implementation**

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

Step 4. filteredDoctorList is updated so that the UI can display the edited doctor.

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

Assigns a patient at the specified **one-based index** of list of currently existing/found patient to a doctor at the specified **one-based index** of list of currently existing/found doctor. If indexes provided are larger or equal to the size of their respective lists, the command will not be allowed and an error will be thrown. If specified patient is already assigned to specified doctor, the command will not be allowed and an error will be thrown.

Example Use: `assign-ptn ptn/1 doc/1`

#### Implementation
{: .no_toc}

Upon entry of the assign patient command, a `AssignPatientCommand` class is created. The `AssignPatientCommand` class extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, the patient at specified **one-based index** is assigned to the doctor at specified **one-based index** if both indexes provided are valid and the patient is not assigned to the doctor yet. The `AssignPatientCommand` class creates a new `Patient` object with the specific `Doctor` reference and a new `Doctor` object with the specific `Patient` reference. The previous `Patient` and `Doctor` objects are then replaced with the new `Patient` and `Doctor` objects.

Given below is an example usage scenario of how the assign patient command behaves at each step.

Step 1. User launches the application

Step 2. User executes `assign-ptn ptn/1 doc/1` to assign the patient at index 1 to the doctor at index 1 (one-based indexing).

Step 3. If the indexes provided are valid and patient is not assigned to doctor yet, the patient at index 1 is replaced with a patient with doctor reference and the doctor at index 1 is replaced with a doctor with patient reference.

The following sequence diagram illustrates how the assign patient operation works:

![](images/AssignPatientSequenceDiagram.png)

[Scroll back to Table of Contents](#table-of-contents)

### Unassign Patient Feature

#### What it does
{: .no_toc}

Unassigns a patient at the specified **one-based index** of list of currently existing/found patient from a doctor at the specified **one-based index** of list of currently existing/found doctor. If indexes provided are larger or equal to the size of their respective lists, the command will not be allowed and an error will be thrown. If specified patient is not assigned to specified doctor, the command will not be allowed and an error will be thrown.

Example Use: `unassign-ptn ptn/1 doc/1`

#### Implementation
{: .no_toc}

Upon entry of the unassign patient command, a `UnassignPatientCommand` class is created. The `UnassignPatientCommand` class extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, the patient at specified **one-based index** is unassigned from the doctor at specified **one-based index** if both indexes provided are valid and the patient is assigned to the doctor. The `AssignPatientCommand` class creates a new `Patient` object without the specific `Doctor` reference and a new `Doctor` object without the specific `Patient` reference. The previous `Patient` and `Doctor` objects are then replaced with the new `Patient` and `Doctor` objects.

Given below is an example usage scenario of how the unassign patient command behaves at each step.

Step 1. User launches the application

Step 2. User executes `unassign-ptn ptn/1 doc/1` to unassign the patient at index 1 from the doctor at index 1 (one-based indexing).

Step 3. If the indexes provided are valid and patient is assigned to doctor, the patient at index 1 is replaced with a patient without doctor reference and the doctor at index 1 is replaced with a doctor without patient reference.

The following sequence diagram illustrates how the unassign patient operation works:

![](images/UnassignPatientSequenceDiagram.png)

[Scroll back to Table of Contents](#table-of-contents)

### Find Doctor Feature

#### What it does
{: .no_toc}

Finds a doctor with the specified parameters. 

Example Use: `find-doc n/Alice`

#### Implementation
{: .no_toc}

Upon entry of the find doctor command, a `FindDoctorCommand` class is created. The `FindDoctorCommand` class takes a predicate created by `DoctorContainsKeywordsPredicate` class. The `FindDoctorCommand` class extends the abstract `Command` class and implements the `execute()` method which updates the model's list of filtered doctors.

Given below is an example usage scenario of how the find doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `find-doc n/Alice` to find a doctor with the name Alice.

Step 3. The model's list of filtered doctors is updated.

The following sequence diagram illustrates how the find doctor operation works:

![](images/FindDoctorSequenceDiagram.png)

*args: Refers to a valid sequence of arguments provided by the user. Example: "n/John Doe p/98765432 e/johnd@example.com
s/Cardiology y/5 t/surgeon"

[Scroll back to Table of Contents](#table-of-contents)

### Find Patient Feature

#### What it does
{: .no_toc}

Finds a patient with the specified parameters.

Example Use: `find-ptn n/Bob`

#### Implementation
{: .no_toc}

Upon entry of the find patient command, a `FindPatientCommand` class is created. The `FindPatientCommand` class takes a predicate created by `PatientContainsKeywordsPredicate` class. The `FindPatientCommand` class extends the abstract `Command` class and implements the `execute()` method which updates the model's list of filtered patients.

Given below is an example usage scenario of how the find patient command behaves at each step.

Step 1. User launches the application

Step 2. User executes `find-ptn n/Bob` to find a patient with the name Bob.

Step 3. The model's list of filtered patients is updated.

The following sequence diagram illustrates how the find patient operation works:

![](images/FindPatientSequenceDiagram.png)

*args: Refers to a sequence of valid arguments provided by the user. Example: "n/John Doe p/98765432 e/jdoe@gmail.com
h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends"

[Scroll back to Table of Contents](#table-of-contents)

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

## **Appendix A: Planned Enhancements**

### Feedback to User

#### Problem 1: Selection of doctor or patient cards is not cleared upon certain commands

- **Scenario**: User selected a patient on Docedex, before entering `list-doc`.
- **What you see**: All the doctors in Docedex are listed on the GUI, as requested by the `list-doc` command.
  However, the user interface does not clear the selection of the previously selected patient card.
- **Potential issue**: Confusion may arise on whether the listed doctors are assigned
  to the previously selected patient.
- **Why did it happen**: `list-doc` did not clear the previous selection of patient cards.

Note: This issue exists for the all commands that do not involve selection of doctor
or patient cards (ie. [`list-doc`](./UserGuide.md#listing-all-doctors),
[`find-doc`](./UserGuide.md#finding-a-doctor), [`del-doc`](./UserGuide.md#deleting-a-doctor),
[`list-ptn`](./UserGuide.md#listing-all-patients), [`find-ptn`](./UserGuide.md#finding-a-patient)
or [`del-ptn`](./UserGuide.md#deleting-a-patient)).

**Solution:** Any commands that do not involve selection of doctor or patient cards will
clear selection of all patient and doctor cards.

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 2: Titles of patient/doctor lists do not well describe its contents

- **Scenario**: User adds a doctor using the [`add-doc` command](./UserGuide.md#adding-a-doctor).
- **What you see**: Docedex automatically selects the newly added doctor. Selection of a doctor
  will result in an update to the patients list, as described [here](./UserGuide.md#selecting-doctors-or-patients-through-commands).
  Since the newly added doctor has no assigned patients, the patients list is cleared.
- **Potential issue**: Confusion may arise as no visual feedback is provided that the patients list is
  reflecting the newly added doctor's assigned patients. Previously, some users have confused this
  behaviour with all patients being deleted from Docedex.
- **Why did it happen**: Titles of patient/doctor lists is not updated to describe the contents of
  the list upon user input.

Note to users and developers: This problem exists if you add a patient using `add-ptn` too.

**Solution:** The title of the patients list will be updated to display "XXX's Patients"
when any doctor is selected, where XXX is the name of the doctor. The same fix will be done for the
displayed doctors list upon selection of patients.

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 3: The command usage message is not consistent across the UserGuide and EditXYZCommands

- **Scenario**: User enters an invalid variation of the `edit-doc` or `edit-ptn` commands
- **What you see**: Error message that does not indicate that the entry of only one update parameter is compulsory while all
  others are optional.
- **Potential issue**: User assumes that all fields are compulsory
- **Why did it happen**: Implementation of feature freeze prior to resolution

**Solution:** Update the `MESSAGE_USAGE` field
in [`EditDoctorCommand`](https://github.com/AY2223S2-CS2103T-F12-1/tp/tree/master/src/main/java/seedu/address/logic/commands/EditDoctorCommand.java), [`EditPatientCommand`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/main/java/seedu/address/logic/commands/EditPatientCommand.java)
and associated
tests ([`EditDoctorCommandTest`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/test/java/seedu/address/logic/commands/EditDoctorCommandTest.java), [`EditPatientCommandTest`](https://github.com/AY2223S2-CS2103T-F12-1/tp/blob/master/src/test/java/seedu/address/logic/commands/EditPatientCommandTest.java))
with parameter information and notation present in the [UserGuide](./UserGuide.md#editing-a-doctor) and the
line `"At least one parameter other than INDEX should be provided\n"`.

[Scroll back to Table of Contents](#table-of-contents)

#### Problem 4: Better error/exception handling for invalid command inputs
- **Scenario**: User enters an invalid variation of a command
- **What you see**: Error message that does not indicate which parameter/section of the command is invalid.
- **Potential issue**: User has to guess which parameter/section of the command is invalid.
- **Why did it happen**: Implementation of feature freeze prior to resolution

**Solution**: Allow Command classes and associated parser utils to throw error messages of parameter/section of command that is invalid.

[Scroll back to Table of Contents](#table-of-contents)

### Scalability

#### Enhancement 1: Use of association classes instead of lists to capture doctor-patient relationship on assignment

- **Current**: Doctor and patient assignments are currently being stored using a list inside each instance of a Doctor and Patient class. For example, if patient1 is assigned to doctor1, patient1 will have doctor1 in its list of doctors and vice versa.
- **Proposed**: A DoctorPatient class which models assignments between doctors and patients.
- **Justification**: Although the current solution is sufficient to capture the relationships between a doctor and a person at this version of Docedex, in future iterations of Docedex, we may want to store more information about the details of the assignment between a doctor and a patient, such as the date. We may even want to want the patient's case details to be specific based on the assignment. An association class can help make creating these new features more scalable and easier to implement.
  ![Proposed new DoctorPatient class](images/ProposedNewModelDiagram.png)


[Scroll back to Table of Contents](#table-of-contents)

#### Enhancement 2: Validation for find command

- **Current**: Users are allowed to type any search query using the find command.
- **Proposed**: Limit search queries according to the parameter constraints.
- **Justification**: The current find command gives an empty list when search queries which do not fit the original parameter constraints are given. This is the expected behavior. An enhancement to the find query would be to give users a more specific error message as to why the find command gives an empty list. 

[Scroll back to Table of Contents](#table-of-contents)


## **Appendix B: Product scope**

**Target user profile:**
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

## **Appendix C: User stories**

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

## **Appendix D: Use cases**

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
{: .no_toc}

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
{: .no_toc}

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
{: .no_toc}

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
{: .no_toc}

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
{: .no_toc}

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
{: .no_toc}

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

## **Appendix E: Non-Functional Requirements**

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

## **Appendix F: Glossary**

| Term                 | Definition                                                                                                |
|----------------------|-----------------------------------------------------------------------------------------------------------|
| AddressBook-Level3   | A project created by the SE-EDU initiative, which this project is based on.                               |
| App                  | An application created by the project.                                                                    |
| API                  | An application programming interface.                                                                     |
| Architecture Diagram | A diagram that explains the high-level design of the App.                                                 |
| Class diagram        | A diagram that shows the classes of the App and the relationships between them.                           |
| CLI                  | Command Line Interface, for users to perform actions through typing commands.                             |
| CommandBox           | A component of the UI that allows users to enter commands.                                                |
| Commons              | A collection of classes used by multiple other components.                                                |
| Component            | A modular part of the App that serves a specific function.                                                |
| ContactDisplay       | A component of the UI that displays contact information.                                                  |
| GUI                  | Graphical User Interface, a visual way for users to interact with a software program                      |
| Logic component      | The component responsible for executing user commands.                                                    |
| Main                 | A class with two subclasses, Main and MainApp, that initializes and shuts down the components of the App. |
| MainWindow           | The main window of the UI that houses all the UI components.                                              |
| Model                | The data model used by the App.                                                                           |
| Navigability         | A concept referring to instances of one class holding a reference to instances of another class.          |
| PlantUML             | A tool used to create diagrams.                                                                           |
| ResultDisplay        | A component of the UI that displays the results of commands.                                              |
| Sequence Diagram     | A diagram that shows the sequence of events between components.                                           |
| UML                  | [Unified Modeling Language](https://en.wikipedia.org/wiki/Unified_Modeling_Language)                      |


[Scroll back to Table of Contents](#table-of-contents)

## **Appendix G: Effort**

| Feature       | AB3 | Docedex                                |
|---------------|-----|----------------------------------------|
| Effort        | 10  |
| Lines of Code | 10  |

The Docedex project involved a significant effort to adapt the AddressBook-Level3 (AB3) application to a new domain of doctors and patients. One of the major changes was the addition of two new models, Doctor and Patient, which required modifying the existing data model and the associated logic components. Another significant change was the complete redesign of the user interface using JavaFX, which required a significant amount of time and effort to learn and implement.

While Doctor and Patient models extended AB3's Patient model, there were several challenges to overcome. The most significant challenge was to map a many-to-many relationship between doctors and patients during assignment. To solve this, the team decided to create a list of patients inside each Doctor instance (and vice versa for patients). This change required us to modify multiple components to update the lists after patient assignment and display them onto the UI.

Another challenge was redesigning the interface to display more information. Changing the interface of an application can be a major undertaking. In the case of Docedex, this involved designing new UI components, updating existing components to reflect the new models and data, and potentially creating new navigation and layout structures to support the new workflows. Significant time was taken to learn JavaFX and implementing the components to fit Docedex use cases.

Our work on implementing the assignment feature is the most prominent in the AssignDoctorCommand and AssignPatientCommand, and their relevant parsers and model methods.



[Scroll back to Table of Contents](#table-of-contents)

<!--- Appendix H reused from from https://ay2021s2-cs2103t-t12-4.github.io/tp/DeveloperGuide.html with modifications --->

## **Appendix H: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source:
**Note:** These instructions only provide a starting point for testers to work on.
Testers are encouraged to do more *exploratory* testing.
</div>

#### Launch and shutdown
{: .no_toc}

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file<br>
      Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

1. Exiting the app
   
   1. With the app still open, enter `exit` in the command box or click on the close window button. <br>
      Expected: The app closes.

[Scroll back to Table of Contents](#table-of-contents)

#### Adding a doctor
{: .no_toc}

1. Adding a doctor

   1. Test case: `add-doc n/John Doe p/98765432 e/johnd@example.com s/Cardiology y/5 t/surgeon`<br>
      Expected: New doctor added. Command Success status message shown with doctor details.

   1. Test case: `add-doc n/John Doe p/98765432 e/johnd@example.com s/Cardiology y/5 t/surgeon` (Existing doctor with same parameters has already been added)<br>
      Expected: No doctor added. Duplicate doctor error message shown.

   1. Test case: `add-doc n/John Doe`<br>
      Expected: No doctor added. Error message shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Adding a patient
{: .no_toc}

1. Adding a patient

    1. Test case: `add-ptn n/John Doe p/98765432 e/jdoe@gmail.com h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends`<br>
       Expected: New patient added. Command Success status message shown with patient details.

    1. Test case: `add-ptn n/John Doe p/98765432 e/jdoe@gmail.com h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends` (Existing patient with same parameters has already been added)<br>
       Expected: No patient added. Duplicate patient error message shown.

    1. Test case: `add-ptn n/John Doe`<br>
       Expected: No patient added. Error message shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Deleting a doctor
{: .no_toc}

1. Deleting a doctor while all doctors are being shown

    1. Prerequisites: List all doctors using the `list-doc` command. Multiple doctors in the list.

    1. Test case: `del-doc 1`<br>
       Expected: First doctor contact is deleted from the displayed doctors list. Details of the deleted contact shown in the status message.

    1. Test case: `del-doc 0`<br>
       Expected: No doctor is deleted. Error details shown in the status message.

    1. Test case: `delete`<br>
       Expected: No doctor is deleted. Unknown command error shown in the status message.

    1. Test case: `del-doc a`<br>
       Expected: No doctor is deleted. Error details shown in the status message.

    1. Test case: `del-doc x` (where x is larger than the doctor list size)<br>
       Expected: No doctor is deleted. Invalid index error shown in the status message.

1. Deleting a doctor while doctor list is filtered

    1. Prerequisites: Filter doctors using the `find-doc` command. At least one doctor must be in the doctor list.

    1. Test case: `del-doc 1`<br>
       Expected: First doctor contact in the filtered doctor list is deleted. Details of the deleted contact shown in the status message.

    1. Test case: `del-doc x` (where x larger than the filtered doctor list size, but smaller than the unfiltered doctor list from `list-doc` command)<br>
       Expected: No doctor is deleted. Invalid index error shown in the status message.

    1. Other test cases to try: `delete`, `del-doc 0`, `del-doc a`.
       Expected: Similar outputs as deleting a doctor while all doctors are being shown.

[Scroll back to Table of Contents](#table-of-contents)

#### Deleting a patient
{: .no_toc}

1. Deleting a patient while all patients are being shown

    1. Prerequisites: List all patients using the `list-ptn` command. Multiple patients in the list.

    1. Test case: `del-ptn 1`<br>
       Expected: First patient contact is deleted from the displayed patients list. Details of the deleted contact shown in the status message.

    1. Test case: `del-ptn 0`<br>
       Expected: No patient is deleted. Error details shown in the status message.

    1. Test case: `delete`<br>
       Expected: No patient is deleted. Unknown command error shown in the status message.

    1. Test case: `del-ptn a`<br>
       Expected: No patient is deleted. Error details shown in the status message.

    1. Test case: `del-ptn x` (where x is larger than the patient list size)<br>
       Expected: No patient is deleted. Invalid index error shown in the status message.

1. Deleting a patient while patient list is filtered

    1. Prerequisites: Filter patients using the `find-ptn` command. At least one patient must be in the patient list.

    1. Test case: `del-ptn 1`<br>
       Expected: First patient contact in the filtered patient list is deleted. Details of the deleted contact shown in the status message.

    1. Test case: `del-ptn x` (where x larger than the filtered patient list size, but smaller than the unfiltered patient list from `list-ptn` command)<br>
       Expected: No patient is deleted. Invalid index error shown in the status message.

    1. Other test cases to try: `delete`, `del-ptn 0`, `del-ptn a`.
       Expected: Similar outputs as deleting a patient while all patients are being shown.

[Scroll back to Table of Contents](#table-of-contents)

#### Editing a doctor
{: .no_toc}

1. Editing an existing doctor
    
   1. Prerequisites: Filter doctors using `find-doc` command or list all doctors using `list-doc` command. At least one doctor must be in the doctor list.
    
   1. Test case: `edit-doc 1 n/Bob`<br>
      Expected: Name of first doctor contact in the filtered doctor list changed to Bob. Command success status message shown with new doctor details.

   1. Test case: `edit-doc 0 n/Bob` <br>
      Expected: No doctor is edited. Invalid doctor index error shown in status message.

   1. Test case: `edit-doc x n/Bob` (where x is larger than doctor list size)<br>
      Expected: No doctor is edited. Invalid doctor index error shown in status message.

   1. Test case: `edit-doc 1` <br>
      Expected: No doctor is edited. Missing field error shown in status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Editing a patient
{: .no_toc}

1. Editing an existing patient

    1. Prerequisites: Filter patients using `find-ptn` command or list all patient using `list-ptn` command. At least one patient must be in the patient list.

    1. Test case: `edit-ptn 1 n/Bob`<br>
       Expected: Name of first patient contact in the filtered patient list changed to Bob. Command success status message shown with new patient details.

    1. Test case: `edit-ptn 0 n/Bob` <br>
       Expected: No patient is edited. Invalid patient index error shown in status message.

    1. Test case: `edit-ptn x n/Bob` (where x is larger than patient list size)<br>
       Expected: No patient is edited. Invalid patient index error shown in status message.

    1. Test case: `edit-ptn 1` <br>
       Expected: No patient is edited. Missing field error shown in status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Assigning a patient to a doctor
{: .no_toc}

1. Assigning a patient to a doctor

   1. Test case: `assign-ptn ptn/1 doc/1` (where patient at index `1` is not assigned to doctor at index `1`)<br>
      Expected: Patient at index `1` is assigned to Doctor at index `1`. Command success status message with patient and doctor names shown.

   1. Test case: `assign-ptn ptn/1 doc/1` (where patient at index `1` is already assigned to doctor at index `1`)<br>
      Expected: No assignment done. Already assigned error shown in the status message.

   1. Test case: `assign-ptn ptn/0 doc/1`
      Expected: No assignment done. Error details shown in the status message.

   1. Test case: `assign-ptn ptn/1 doc/0`
      Expected: No assignment done. Error details shown in the status message.

   1. Test case: `assign-ptn 1 1`
      Expected: No assignment done. Error details shown in the status message.

   1. Test case: `assign-ptn ptn/x doc/1` (where x is larger than patient list size)<br>
      Expected: No assignment done. Invalid patient index error shown in the status message.

   1. Test case: `assign-ptn ptn/1 doc/x` (where x is larger than doctor list size)<br>
      Expected: No assignment done. Invalid doctor index error shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Removing an assignment of a patient from a doctor
{: .no_toc}

1. Removing an assignment of a patient from a doctor

    1. Test case: `unassign-ptn ptn/1 doc/1` (where patient at index `1` is assigned to doctor at index `1`)<br>
       Expected: Assignment of Patient at index `1` from Doctor at index `1` removed. Command success status message with patient and doctor names shown.

    1. Test case: `unassign-ptn ptn/1 doc/1` (where patient at index `1` is not assigned to doctor at index `1`)<br>
       Expected: No unassignment done. Not assigned error shown in the status message.

    1. Test case: `unassign-ptn ptn/0 doc/1`
       Expected: No unassignment done. Error details shown in the status message.

    1. Test case: `unassign-ptn ptn/1 doc/0`
       Expected: No unassignment done. Error details shown in the status message.

    1. Test case: `unassign-ptn 1 1`
       Expected: No unassignment done. Error details shown in the status message.

    1. Test case: `unassign-ptn ptn/x doc/1` (where x is larger than patient list size)<br>
       Expected: No unassignment done. Invalid patient index error shown in the status message.

    1. Test case: `unassign-ptn ptn/1 doc/x` (where x is larger than doctor list size)<br>
       Expected: No unassignment done. Invalid doctor index error shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Finding a doctor
{: .no_toc}

1. Finding a doctor with specific parameters

   1. Test case: `find-doc n/Bob`
      Expected: All doctor contacts with the name containing `Bob` is filtered and shown. Command success status message shown with number of doctors listed (can be 0 if no valid doctors found).

   1. Test case: `find-doc t\surgeon`
      Expected: All doctor contacts that contains tag `surgeon` is filtered and shown. Command success status message shown with number of doctors listed (can be 0 if no valid doctors found).

   1. Test case: `find-doc`
      Expected: No change to doctor list. Error details shown in the status message.

   1. Test case: `find`
      Expected: No change to doctor list. Unknown command error shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Finding a patient
{: .no_toc}

1. Finding a patient with specific parameters

    1. Test case: `find-ptn n/Bob`
       Expected: All patient contacts with the name containing `Bob` is filtered and shown. Command success status message shown with number of patients listed (can be 0 if no valid patients found).

    1. Test case: `find-ptn t\pendingReview`
       Expected: All patient contacts that contains tag `pendingReview` is filtered and shown. Command success status message shown with number of patients listed (can be 0 if no valid patients found).

    1. Test case: `find-ptn`
       Expected: No change to patient list. Error details shown in the status message.

    1. Test case: `find`
       Expected: No change to patient list. Unknown command error shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Listing all doctors
{: .no_toc}

1. Listing all doctors while Docedex is showing only filtered doctors

   1. Prerequisite: At least one doctor has been added to Docedex. Doctor list is being filtered using `find-doc` command or `sp` command.

   1. Test case: `list-doc`<br>
      Expected: All doctors in Docedex are shown. Command success status message shown.

   1. Test case: `list`<br>
      Expected: No change to doctor list. Unknown command error shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Listing all patients
{: .no_toc}

1. Listing all patients while Docedex is showing only filtered patients

    1. Prerequisite: At least one patient has been added to Docedex. Patient list is being filtered using `find-ptn` command or `sd` command.

    1. Test case: `list-ptn`<br>
       Expected: All patients in Docedex are shown. Command success status message shown.

    1. Test case: `list`<br>
       Expected: No change to patient list. Unknown command error shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Selecting a doctor
{: .no_toc}

1. Selecting a doctor from doctor list using index

   1. Prerequisites: Filter doctors using `find-doc` command or list all doctors using `list-doc` command. At least one doctor must be in the doctor list.

   1. Test case: `sd 1`<br>
      Expected: First doctor in doctor list is selected. The doctor's details are shown in the enlarged contact card. Patient list filtered to Patients assigned to selected doctor. Command success status message shown with doctor's details.
   
   1. Test case: `sd 0`<br>
      Expected: No new doctor selected. Error details shown in the status message.

   1. Test case: `sd x` (where x is larger than the filtered doctor list size)<br>
      Expected: No new doctor selected. Invalid index error shown in the status message.

   1. Test case: `sd`<br>
      Expected: No new doctor selected. Error details shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Selecting a patient
{: .no_toc}
1. Selecting a patient from patient list using index

    1. Prerequisites: Filter patients using `find-ptn` command or list all patient using `list-ptn` command. At least one patient must be in the patient list.

     1. Test case: `sd 1`<br>
       Expected: First patient in patient list is selected. The patient's details are shown in the enlarged contact card. Doctor list filtered to doctors assigned to selected patient. Command success status message shown with patient's details.

    1. Test case: `sd 0`<br>
       Expected: No new patient selected. Error details shown in the status message.

    1. Test case: `sd x` (where x is larger than the filtered patient list size)<br>
       Expected: No new patient selected. Invalid index error shown in the status message.

    1. Test case: `sd`<br>
       Expected: No new patient selected. Error details shown in the status message.

[Scroll back to Table of Contents](#table-of-contents)

#### Saving data
{: .no_toc}

1. Dealing with missing/corrupted data files

   1. Prerequisite: Have opened and closed the application and confirmed that the files `data/docedex.json` have been created in the same directory that docedex was run from.

   1. Manually edit the file `data/docedex.json` with any text editing software to break the JSON format or edit the keys of JSON file. Alternatively, you may delete the `data/docedex.json` file.

   1. Re-launch the app.

   1. Test case: `data/docedex.json` was deleted. <br>
      Expected: Docedex starts with empty doctor and patient list. All contacts have been deleted.

   1. Test case: `data/docedex.json` was corrupted. <br>
      Expected: Docedex starts with empty doctor and patient list. All contacts have been deleted.

[Scroll back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
