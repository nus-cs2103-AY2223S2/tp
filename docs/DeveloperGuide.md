---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

The set-up guide is still **_in progress_**

--------------------------------------------------------------------------------------------------------------------

## Design

The documentation for Design is still **_in progress_**

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

### Add Doctor Feature

### What it does

Adds a doctor to the bottom of the list of currently existing doctors. Users are able to add any valid doctor to the list. If a record of the same doctor already exists in the list, the command will not be allowed and an error will be thrown to alert user.

Example Use: `add-doc n/John Doe p/98765432 e/johnd@example.com s/Cardiology y/5 t/surgeon`

### Implementation

Upon entry of the add doctor command, an `AddDoctorCommand` class is created. The `AddDoctorCommand` class extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, a `Doctor` object is added to the model’s list of doctors if all the attributes provided are valid and a duplicate instance does not exist.

Given below is an example usage scenario of how the add doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `add-doc n/John Doe p/98765432 e/johnd@example.com s/Cardiology y/5 t/surgeon` to save a doctor.

Step 3. The doctor is added to the model’s list of doctors if valid.

The following sequence diagram illustrates how the add doctor operation works:

![](images/AddDoctorSequenceDiagram.png)
*args: Refers to a valid sequence of arguments provided by the user. Example: "n/John Doe p/98765432 e/johnd@example.com s/Cardiology y/5 t/surgeon"

### Add Patient Feature

### What it does

Adds a patient to the bottom of the list of currently existing patients. Users are able to add any valid patient to the list. If a record of the same patient already exists in the list, the command will not be allowed and an error will be thrown to alert user.

Example Use: `add-ptn n/John Doe p/98765432 e/jdoe@gmail.com h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends`

### Implementation

Upon entry of the add patient command, an `AddPatientCommand` class is created. The `AddPatientCommand` class extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, a `Patient` object is added to the model’s list of patients if all the attributes provided are valid and a duplicate instance does not exist.

Given below is an example usage scenario of how the add doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `add-ptn n/John Doe p/98765432 e/jdoe@gmail.com h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends` to save a patient.

Step 3. The patient is added to the model’s list of patients if valid.

The following sequence diagram illustrates how the add doctor operation works:

![](images/AddPatientSequenceDiagram.png)
*args: Refers to a sequence of valid arguments provided by the user. Example: "n/John Doe p/98765432 e/jdoe@gmail.com h/1.85 w/70.5 d/Fever st/Outpatient r/Patient was given paracetamol for fever t/friends"

### Edit Doctor Feature

### What it does

Users can edit specific doctors in the clinic by providing at least one of the optional fields. Existing values will be 
updated to the input values and all other values will remain the same. The doctor to be edited can be specified through
the doctor's index.

Example Use: `edit-doc 2 n/Gabriel Tan p/12345678 s/Cardiology`

### Implementation

Upon entry of the edit doctor command, an `EditDoctorCommand` class is created. The `EditDoctorCommand` class extends
the abstract `Command` class and implements the `execute()` method. The `EditDoctorDescriptor` is created with the arguments given
by the user. A new `Doctor` object is created with the new arguments, with the attributes of the old `Doctor` object copied over
if the argument for that specific attribute is not provided by the user. `EditDoctorDescriptor` is then passed to `EditDoctorCommandParser`. 
The `EditDoctorCommand` is created using the `EditDoctorDescriptor`. Upon execution of `EditDoctorCommand`, a `Doctor` object is added to the model’s list of doctors if all the attributes provided are valid and a duplicate instance does not exist.

The following activity diagram illustrates the user flow for editing a doctor:

![](images/EditDoctorActivityDiagram.png)

Given below is an example usage scenario of how the add doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `edit-doc n/Simon` to edit a doctor.

Step 3. The doctor is edited and saved to the model’s list of doctors if valid.

The following sequence diagram illustrates how the edit doctor operation works:

![](images/EditDoctorSequenceDiagram.png)

### Delete Features

---

### Delete Doctor Feature

### What it does

Deletes a doctor at the specified **one-based index** of list of currently existing/found doctors. Users are able to delete any doctor in the list. If an index larger than or equal to the size of the doctor’s list is provided, the command will not be allowed and an error will be thrown to alert user.

Example Use: `del-doc 1`

### Implementation

Upon entry of the delete doctor command, a `DeleteDoctorCommand` class is created. The `DeleteDoctorCommand` class extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, the doctor at specified **one-based index** is removed if the index provided is valid.

Given below is an example usage scenario of how the delete doctor command behaves at each step.

Step 1. User launches the application

Step 2. User executes `del-doc 1` to delete the doctor at index 1 (one-based indexing).

Step 3. The doctor at this index is removed if the index provided is valid.

The following sequence diagram illustrates how the delete doctor operation works:

![](images/DeleteDoctorSequenceDiagram.png)


### Delete Patient Feature

### What it does

Deletes a patient at the specified **one-based index** of list of currently existing/found patient. Users are able to delete any patient in the list. If an index larger than or equal to the size of the patient’s list is provided, the command will not be allowed and an error will be thrown to alert user.

Example Use: `del-ptn 1`

### Implementation

Upon entry of the delete doctor command, a `DeletePatientCommand` class is created. The `DeletePatientCommand` class extends the abstract `Command` class and implements the `execute()` method. Upon execution of this method, the patient at specified **one-based index** is removed if the index provided is valid.

Given below is an example usage scenario of how the delete patient command behaves at each step.

Step 1. User launches the application

Step 2. User executes `del-ptn 1` to delete the patient at index 1 (one-based indexing).

Step 3. The patient at this index is removed if the index provided is valid.

The following sequence diagram illustrates how the delete patient operation works:

![](images/DeletePatientSequenceDiagram.png)

### GUI Features

---

### Enlarged Info Card feature
As triage staff manage the contacts of doctors and patients, they may wish to pull up
the personal information of the doctor or patient. Therefore, the right-most column within
Docedex has been reserved to show the personal information of the selected doctor or patient.

![](images/enlarged-contact-card-display.png)

#### Brief introduction to the components involved
Let's call the card which displays this information **info cards**. However, the information
displayed for a doctor compared to a patient has a few differences. Thus, two different info cards
are required - one to display patient information and one to display doctor information.

Let's call these cards `EnlargedDoctorInfoCard` and `EnlargedPatientInfoCard`. However, we
only have one `StackPane` to display the information of the queried doctor or patient.
So, we need a way to toggle between displaying either card, depending on whether the user
has selected a doctor or patient to view.


#### Exploring the user journey
To explore how this is implemented, we will focus on the user clicking on a `DoctorListViewCell`
representing a doctor, though the ideas below can be extended to the user clicking on a
`PatientListViewCell`, as well as other ways of querying for a doctor or patient
(ie. through select-doc or select-ptn command).

Below, we see the sequence diagram of how a mouse click from the user on the `DoctorListViewCell`
causes the display of information related to the doctor card through the `EnlargedDoctorInfoCard`.

![](images/UserClickDoctorCardSequenceDiagram.png)

#### More details on implementation
When the user clicks on a `DoctorListViewCell`, the `displayDoctor()` call sets the state of the
`EnlargedInfoCardDisplayController` to show the doctor. After which, the `ContactDisplay`
is prompted to feedback this change to the user, by displaying the `EnlargedDoctorInfoCard`
containing the information of the doctor represented by the clicked `DoctorListViewCell`.

A similar process happens when the user clicks on a `PatientListViewCell`.

#### How is the state of the application stored
Within `EnlargedInfoCardDisplayController`, two booleans corresponding to displaying doctor
and patient information respectively store the state of the application.

<div markdown="span" class="alert alert-primary">
These booleans should never contain the same value for the following reasons:
1) If both booleans are `false`, then no information is displayed.
2) If both booleans are `true`, then both doctor and patient information will be
displayed over each other.
</div>

#### Alternatives considered
*_This section is still in progress_*


--------------------------------------------------------------------------------------------------------------------


## **Appendix: Requirements**

### Product scope

**Target user profile**
We hope to target admin staff within a clinic who have to settle triaging of patients.<br>
Here are some characteristics of our target user profile: <br>
* needs to assign patients to the appropriate doctors quickly
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Perform quick lookup and assignment of appropriate doctors to each patient in triage, faster than a typical mouse/GUI driven app.


### User stories

In the table below, **_user_** refers to the triage admin staff.

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​                                                      | So that I can…​                                                                  |
|----------|---------|-------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `* * *`  | user    | add doctor contacts into my address book                          | store their contacts in case I need them in the future                           |
| `* * *`  | user    | look up doctors by their name, specialty, department and position | assign patients to relevant doctors                                              |
| `* * *`  | user    | sort doctors based on specialty                                   | assign patients to doctors according to nature of condition                      |
| `* * *`  | user    | sort doctors based on years of experience                         | assign patients to doctors according to severity of condition                    |
| `* * *`  | user    | sort doctors based on availability                                | assign patients to doctors currently present in the clinic                       |
| `* * *`  | user    | edit the doctor contacts in Docedex                               | keep the doctor contacts up to date                                              |
| `* * *`  | user    | delete doctors that have left the hospital or have retired        | ensure all doctor contacts reflect doctors in the hospital                       |
| `* * *`  | user    | save my doctor contacts in my desktop                             | refer to doctor contacts in the future                                           |
| `* * *`  | user    | load my contacts from a file when I boot up the application       | refer to doctor contacts created in the past                                     |
| `* * *`  | user    | add patient contacts into my address book                         | store their contacts in case I need them in the future                           |
| `* * *`  | user    | look up patients by their name                                    | view medical history of the patient including doctors that have treated them     |
| `* * *`  | user    | tag patients with a priority (triage)                             | determine assignment priorities to doctors                                       |
| `* * *`  | user    | sort patients according to their priority                         | tag patients according to severity of condition                                  |
| `* * *`  | user    | detect duplicate entries                                          | either halt operation or update information.                                     |
| `* *`    | user    | access the help menu                                              | know how to use the commands within Docedex                                      |
| `* *`    | user    | tag patients to the doctor                                        | keep track of patients treated by the doctor                                     |
| `* *`    | user    | tag doctors to the patients                                       | keep track of doctors treating the patient                                       |
| `* *`    | user    | exit the application through the CLI                              | terminate use of the application                                                 |
| `* *`    | user    | access the help menu                                              | know how to use the commands within Docedex                                      |
| `*`      | user    | add remarks for doctors                                           | store additional information on each doctor                                      |
| `*`      | user    | star certain doctors as important                                 | perform quick retrieval of information pertaining to important doctors           |
| `*`      | user    | see the history of doctors I viewed recently                      | re-access recently queried doctor contacts quickly                               |
| `*`      | user    | retrieve the size of my contact book for doctors                  | verify the number of doctors in my clinic                                        |
| `*`      | user    | archive doctors that are no longer working                        | retain information about such doctors without having them appear in my searches  |
| `*`      | user    | classify patients by compliance                                   | keep track of non-compliant cases and warn doctors                               |
| `*`      | user    | archive patients that have died or no longer visit this hospital. | retain information about such patients without having them appear in my searches |
| `*`      | user    | self-destruct my address book                                     | protect clinic's information in the event of a cyber-attack (last-ditch effort). |
| `*`      | user    | create a new address book instance for a new clinic               | track doctors and patients across different clinics                              |

### Use cases

(For all use cases below, the **System** is `Docedex` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC1 - Add Doctor**

Actor: User
Precondition: User is logged in and has access to the add doctor feature.

**MSS**

1. User requests to add a doctor by specifying their contact information, including name, department, specialty, years of experience, and any non-whitespace separated tags.
2. Docedex confirms the addition of the doctor contact.<br>

   Use case ends.

**Extensions**

* 1a. User enters invalid command.
  * 1a. Docedex detects error in command.
    * 1a1. Docedex prompts user to correct the format of the command. <br>
    * 1a2. User enters command and information to add a doctor.<br>
    Steps 1a1-1a2 are repeated until a valid add command is entered.<br>
    Use case resumes from step 2.
* 1b. Docedex detects duplicate information within the address book.
  * 1b1. Docedex prompts user to not enter duplicate information <br>
  * 1b2. User enters command and information to add a doctor.<br>
  Steps 1b1-1b2 are repeated until a unique entry is entered.<br>
  Use cases resumes from step 2.


**Use case: UC2 - Delete Doctor**

Actor: User

**MSS**

1. User request to delete a specific doctor.
2. Docedex confirms the deletion of the doctor contact.<br>
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command.
  * 1a1. Docedex requests to correct the format of the command.
  * 1a2. User enters command to delete a doctor.<br>
  Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
  Use case resumes from step 2.


**Use case: UC3 - Find Doctor**

Actor: User

**MSS**

1. User requests to find doctors that meet a particular criteria, such as years of experience.
2. Docedex shows a list of doctors that meet the criteria requested by user.
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command.
  * 1a1. Docedex requests to correct the format of the command.
  * 1a2. User enters command to delete a doctor.<br>
    Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
    Use case resumes from step 2.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 doctor contacts and 30,000 patient contacts without noticeable reduction in performance.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should not utilize any network to transmit any information.
5. The average time required to boot up the application should be under 5 seconds.
6. Feedback from Docedex should be displayed within 2 seconds of the user's input.
7. The file size of the application's `jar` should not exceed 100MB.
8. Should utilize less than 300MB of memory when in use.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **User**: Triage Admin Staff within the clinic.
* **Contact**: Data entry that stores the contact information of a doctor or patient in Docedex.

--------------------------------------------------------------------------------------------------------------------
