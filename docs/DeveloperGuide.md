---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Introduction to HMHero**

HMHero is an Applicant Managing System designed to be used by human resource professionals, hiring managers,
and recruiters who want to streamline the recruitment process and make it more efficient,
helping their organizations manage job applications and hiring processes more effectively.

This Developer Guide is a detailed documentation on HMHero's design and implementation, describing the architecture,
an outline of all parts of HMHero and how they work together, and specifications on
feature implementation and considerations.

This guide is intended to assist developers in maintaining, upgrading or evolving HMHero.

--------------------------------------------------------------------------------------------------------------------
## **How to use this Developer Guide**

#### Person in place of Applicant

In this Developer Guide, `Person` is used in place of Applicant. Take note that when mentioning `Person`, we are
always talking about an Applicant in HMHero.

--------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: <strong>Tip:</strong> The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-W14-4/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.1.1</b> Architecture diagram for high-design of the App
</div>

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): Manage the HMHero application User Interface
* [**`Logic`**](#logic-component): Manage the execution of commands.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from and writes data to the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete n/Alex p/91234567`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.1.2</b> Sequence Diagram for the command <code>delete n/Alex p/91234567</code>
</div>
<br>


Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.

* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.)


For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.1.3</b> Class Diagram for Component Managers
</div>

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.2.1</b> Class Diagram for UI Components
</div>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. 

Steps to start making changes to the UI:
1. The layout of these UI parts are defined in matching `.fxml` files that are in the [`src/main/resources/view`](https://github.com/AY2223S2-CS2103T-W14-4/tp/tree/master/src/main/resources/view) folder.
2. The managing classes of these UI parts are defined in matching `.java` files in the [`src/main/java/seedu/address/ui`](https://github.com/AY2223S2-CS2103T-W14-4/tp/tree/master/src/main/java/seedu/address/ui) folder.
3. The stylesheets for these UI parts can be found in `Extensions.css`, `HMHero.css`, and `Mainwindow.css`, except `UserProfilePanel` and `HelpWindow` with separate CSS files in matching `.css` files in the [`src/main/resources/view`](https://github.com/AY2223S2-CS2103T-W14-4/tp/tree/master/src/main/resources/view) folder.
4. For instance, the layout of the `HelpWindow` is specified in [`HelpWindow.fxml`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/resources/view/HelpWindow.fxml) with managing class at [`HelpWindow.java`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/java/seedu/address/ui/HelpWindow.java) and CSS file at [`HelpWindow.css`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/resources/view/HelpWindow.css).
 

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.3.1</b> Class diagram for Logic Components
</div>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `HMHeroParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a person).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete n/John p/91234567")` API call.

![Interactions Inside the Logic Component for the `delete n/John p/91234567` Command](images/DeleteSequenceDiagram.png)
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.3.2</b> Sequence Diagram for the command <code>delete n/Alex p/91234567</code>
</div>

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.3.3</b> Class diagram for Parser Components
</div>

How the parsing works:
* When called upon to parse a user command, the `HMHero` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `HMHeroParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.4.1</b> Class diagram for Model Component
</div>

The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Note` list in the `HMHero`, which `Person` references. This allows `HMHero` to only require one `Note` object per unique note, instead of each `Person` needing their own `Note` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.4.2</b> Class diagram for Model Components
</div>

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-W14-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />
<div style="width:80%;margin:0 10%;text-align:center">
    <b>Figure 1.5.1</b> Class diagram for Storage Components
</div>

The `Storage` component,
* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `HMHeroStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

#### Adding an Applicant

##### Overview

The `add` command creates a new `Person`, which represents an Applicant in HMHero's Applicant Managing System.

<div markdown="span" class="alert alert-info" role="alert">

:information_source: <strong>Command Format:<strong>
`add n/NAME p/PHONE e/EMAIL a/ADDRESS [applied/APPLIEDDATETIME] [note/NOTE]...` <br>
Refer to [Glossary](#glossary) for more information on Command format.
</div>

The activity diagram is as such:

![AddCommand activity diagram](images/AddApplicantActivityDiagram.png)

##### Feature Details

1. The user specifies Applicants details to create. Compulsory fields are name, phone, address, and email. Optional
fields include application date time and notes about the applicant.
1. If any of the compulsory fields are not provided, or the provided inputs are in the wrong format, an error message
   will be displayed. The user will be prompted to enter the command correctly via the error message.
1. All applicants added will start at the Applied Status.
1. If application date time was not provided, HMHero would assume the application date time of the Applicant to be now
   (the current time on the user's computer)
1. The applicant is cross-referenced in the `Model` to check if it already exists. If it does, an error is raised
to inform the user.
1. If step 5 completes without any exceptions, the new `Person` is created and stored inside HMHero.

##### Feature Considerations

When checking for duplicates in the `UniquePersonList` inside `Model`, any `Person` cannot have the same name and phone
as another. While it is unlikely applicants have the same full name, it is still possible in reality. However, it is
impossible for unique applicants to share the same number. Thus, HMHero checks name and phone number when checking for
duplicates.

When implementing this feature, we restricted users to only add applicants into the `APPLIED` status. This is because
we wanted HMHero to adhere to the flow of the hiring process. Allowing users to add applicants into specific statuses
could introduce confusion to how `add` command is used.


#### Advancing an Applicant

##### Overview

The `advance` command advances an `Person` in HMHero, which advances the `status` of a `Person`.

<div markdown="span" class="alert alert-info" role="alert">

:information_source: <strong>Command Format:<strong>
`advance n/NAME p/PHONE [d/INTERVIEWDATETIME]` <br>
Refer to [Glossary](#glossary) for more information on Command format and applicant status.
</div>

The activity diagram is as such:

![Advance activity diagram](diagrams/AdvanceActivityDiagram.puml)

Here is the activity diagram showing the process of the `advance` command:
![Advance activity diagram](images/AdvanceActivityDiagram.png)

##### Feature Details
1. The user specifies an applicant name and phone that represents a `Person` to be advanced.
2. If the name and phone is not provided, an error is thrown and the user is prompted to
enter the command correctly via an error message.
3. The name and phone are cross-referenced in the `Model` to check if an applicant with the corresponding name and phone exists. If it does not, then an error is raised to inform the user.
4. The status must be either Applied or Shortlisted. Else, then an error is raised to inform the user.
5. If the interview datetime is not provided when the status is Applied, the user will be prompted to enter the command
correctly via an error message.
6. If the interview datetime provided exists in the `Model`, the user will be prompted to enter the command
correctly via an error message.
7. If the interview datetime is provided when the status is Shortlisted, the user will be prompted to enter the command
correctly via an error message.
8. If step 7 is reached without any error message, HMHero advances the specified `Person`'s `status`.

##### Feature Considerations

When implementing this feature, we realised that we could model HMHero to track hiring process in the real world 
more effectively by enforcing the rule that the user can only advance applicants one stage at a time. 
We thus decided to provide a default behaviour when advancing an applicant's status.


#### Rejecting an Applicant

##### Overview

The `reject` command rejects a `Person` in HMHero, which changes the `status` of a `Person` to `REJECTED`.

<div markdown="span" class="alert alert-info" role="alert">

:information_source: <strong>Command Format:<strong>
`reject n/NAME p/PHONE` <br>
Refer to [Glossary](#glossary) for more information on Command format.
</div>
Here is the activity diagram showing the process of the `reject` command:
![RejectApplicantActivityDiagram](images/RejectApplicantActivityDiagram.png)

The activity diagram is as such:
[Add in later]()

Here is the activity diagram showing the process of the `reject` command:
[Add in later]()

##### Feature Details
1. The user specifies an applicant name and phone that represents an `Person` to be rejected.
2. If the name and phone is not provided, an error is thrown and the user is prompted to
enter the command correctly via an error message.
3. The name and phone are cross-referenced in the `Model` to check if an applicant with the corresponding name and phone exists.
    If it does not, then an error is raised to inform the user.
4. HMHero confirms that the applicant's status is not already `REJECTED`. Else, an error is raised to inform the user.
5. If step 4 completes without any exceptions, then the `Person` is successfully rejected.

##### Feature Considerations

It is feasible to implement the reject feature to successfully executes when `reject` command executes on 
applicants with `REJECTED` status. However, an absence of the error message provides little value to the user and 
may present confusingly to the user. <br>
Additionally, we realised that it is common for hiring managers to reject applicants without removing them. Thus, the
`reject` command does not perform the same functionality as the `delete` command.

#### Finding an Applicant

##### Overview

The `find` command filters applicants based on fields specified by the user. 
Fields have to be denoted by flags. Allowed fields for filtering are `name` and `phone`.

<div markdown="span" class="alert alert-info" role="alert">

:information_source: <strong>Command Format:<strong>
`find [n/NAME] [p/PHONE]` <br>
Refer to [Glossary](#glossary) for more information on Command format.
</div>

<div markdown="span" class="alert alert-info" role="alert">

:information_source: <strong>Command Format:<strong>
`find [n/NAME] [p/PHONE]` <br>
Refer to [Glossary](#glossary) for more information on Command format.
</div>


Here is the activity diagram showing the process of the `find` command:

![FindCommandActivityDiagram](images/FindCommandActivityDiagram.png)

The sequence diagram for `find n/John` in HMHero is as such:

![FindSequenceDiagram](images/FindSequenceDiagram.png)

<div markdown="span" class="alert alert-info" role="alert">

:information_source: <strong>Note:<strong>
The lifeline for `FindCommandParser` should end at the destroy marker (X) but due to a limitation of <strong>PlantUML<strong>, the lifeline reaches the end of the diagram.
Additionally, take note that interactions with utility classes such as `ArgumentTokenizer`, `ArgumentMultimap`, and `Messages` are excluded as including them would cause the UML diagram to be cluttered and too small to read.
</div>

##### Feature Details
1. The user specifies one or more fields to filter through the applicant list.
2. If the user specifies more than one field, the filtered applicant list has to match all fields.
3. If the user specifies a field more than once, only the last argument is considered when filtering applicants.

##### Feature considerations
The UI displays a `FilteredList` obtained from an immutable applicant list.
The `FindCommandParser` creates the `Predicate` used to filter the applicant list. When the `FindCommand` is executed,
the `FilteredList` sets its `Predicate` field to the created `Predicate`. The UI shows the new `FilteredList`.

Applicant fields are required as an input as it is helpful to reduce user confusion and facilitate finding applicants based on multiple fields.


#### Editing an Applicant

##### Overview

The `edit` feature edits the attached attributes of a specified `Person`,which is specified by the one-indexed `personList` presented to the user.

<div markdown="span" class="alert alert-info" role="alert">

:information_source: <strong>Command Format:<strong>
`edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/INTERVIEWDATETIME] [note/NOTE]...` <br>
Refer to [Glossary](#glossary) for more information on Command format.
</div>

The activity diagram is as such:
[add in later]()

Here is the activity diagram showing the process of the `edit` command:
[EditApplicantActivityDiagram]()


##### Feature Details

1. The user can perform commands that modify the shown applicant list. (Eg. `find` and `interview` commands)
2. The user specifies an index that represents a `Person` to be edited.
3. If a non-zero unsigned integer index is not provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
4. At least one field to be edited has to be provided. Else, the user will be prompted to enter the command correctly via an error message.
5. If an index that is not in the valid range of the list is provided, an error is thrown and the user is prompted to enter the command correctly via an error message.
6. The `edit` command creates a new applicant based on the given fields. The created applicant is cross-referenced in the `Model` to check if it already exists. If it already does, then an error is raised to inform the user.
7. If step 6 completes without any exceptions, then the `Person` at the specified index is successfully edited.

##### Feature Considerations

Similar to the `new` command, it should be noted that when checking for duplicates in the `UniquePersonList` inside the
`Model`, Applicants cannot have the same name and phone number. For example, if a `Person` with the name `Thomas` and
`91823452` already exists inside the list, then you cannot edit an existing `Person` to have the name `Thomas` and
`91823452`.

When providing multiple arguments with the same delimiter,
the last instance of the repeat delimiter is taken during the `parse` command.


#### Displaying the list

##### Overview
The `list` command displays the full list by HMHero.

Here is the activity diagram showing the process of the `list` command:

![ListActivityDiagram](images/ListActivityDiagram.png)

##### Feature Details

1. The user calls the `list` command.
1. HMHero performs the necessary calculations to obtain the statistics. HMHero displays the result to the user.

##### Feature Considerations

The five statistics were chosen as a baseline, and they are a good starting point for users to help
track the number of applicants. For example, the user can obtain the total number of applicants, and also provide
the total numbers of applicants for each status.

#### Showing all shortlisted applicants

##### Overview
The `interview` command displays the list of all shortlisted applicants, sorted by earliest interview date.

##### Feature Details
1. The user calls the `interview` command.
2. The applicant list is filtered for shortlisted applicants.
3. The resulting filtered applicant list is sorted by interview date, from earliest to latest.
4. HMHero displays the resulting `SortedList` to the user.

#### Filter applicants based on notes



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

* Has a need to manage a significant number of applicants who applied to their department
* Is a Hiring Manager
* Prefers desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Prefers using CLI to GUI to increase productivity

**Value proposition**: 
* Users can track and monitor the progress of each applicant in the application cycle very easily.
* Users can quickly search applicants with particular skill-sets.
* Users can obtain insights on their hiring practices to look for areas for improvement.


### User stories

**Note:** The following legend describes the symbols used in this section:

| Priority | Symbol  | Meaning          | 
|----------|---------|------------------|
| High     | `* * *` | Must have        | 
| Medium   | `* *`   | Nice to have     | 
| Low      | `*`     | Unlikely to have | 


| Priority | As a …                                | I want to …                                                       | So that …                                                                            |
|:---------|---------------------------------------|-------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| `* * *`  | Hiring Manager                        | List out all existing applicants                                  | I can have a glance of the status of the application cycle of all applicants.        |
| `* * *`  | Hiring Manager                        | View the number of applicants in each application stage           | I can have a glance of the status at each stage in the application cycle.            |
| `* * *`  | Hiring Manager                        | Advance the application stage of applicants                       | I can move an applicant into the next stage of the application cycle                 |
| `* * *`  | Hiring Manager                        | Add applicants into HMHero                                        | I can quickly add users who have applied to the department.                          |
| `* * *`  | Hiring Manager                        | Delete single applicant                                           | I can delete applicants that I do not wish to track in the application anymore.      |
| `* * *`  | Busy Hiring Manager                   | Search for applicants                                             | I can view details of specific applicants                                            |
| `* * * ` | Senior Hiring Manager                 | Identify duplicate applications from the same applicant           | I can prevent applicants from sending multiple applications                          |
| `* * `   | Busy Hiring Manager                   | View the dates of interviews for all shortlisted applicants       | I can better schedule future working days                                            |
| `* * `   | Hiring Manager                        | Take down some additional notes for each applicant                | I can note down additional details of an applicant, for future reference             |
| `* * `   | Forgetful Hiring Manager              | Remind myself of interviews that coming up                        | I will not forget to attend any interview that I have scheduled                      |
| `* * `   | Clumsy Hiring Manager                 | Check whether there are any clashes in interview date and timings | I can prevent myself from scheduling more than 1 interview in the same date and time |
| `* `     | Clumsy Hiring Manager                 | Get a confirmation message when deleting an applicant             | I can prevent accidental deletions of applicants                                     |
| `* `     | Old Hiring Manager                    | Highlight and enlarge the keywords that I am looking for          | I can easily see the keywords that I am looking for                                  |
| `* `     | Careless Hiring Manager               | Undo recent actions or commands                                   | I can reverse commands that I have mistakenly carried out                            |
| `* `     | Hiring Manager for multiple positions | Create jobs with required skill sets for each job                 | I can keep track of skill-sets needed for each job to match applicants               |

### Use cases

(For all use cases below, the **System** is the `HMHero` and the **Actor** is the `Hiring Manager`, unless specified otherwise)

MSS refers to *Main Success Scenario*.

**Use case: List all applicants**

Precondition: HMHero already has some applicants stored.

**MSS**

1. User enters command to view applicants in the list.
2. HMHero shows list of applicants.

   Use case ends.

**Use case: Add an applicant**

Precondition: Applicant does not exist in HMHero yet.

**MSS**

1.  User enters command to add a specific applicant in the list.
2.  HMHero adds the applicant.

    Use case ends.

**Extensions**

* 1a. Compulsory fields (name, phone, email, address) are not given.

    * 1a1. HMHero shows an error message.

      Use case resumes at step 1.


**Use case: Delete an applicant**

Precondition: Applicant to delete exists in HMHero.

**MSS**

1.  User requests to list all applicants.
2.  HMHero shows a list of applicants.
3.  User enters the command to delete a specific applicant in the list.
4.  HMHero deletes the applicant.

    Use case ends.

**Extensions**

* 3a. The given applicant's name does not exist.

    * 3a1. HMHero shows an error message.

      Use case resumes at step 3.


* 3b. The given applicant's phone number does not exist.

    * 3b1. HMHero shows an error message.

      Use case resumes at step 3.


* 3c. The given applicant's name and corresponding phone number combination for an applicant does not exist.

    * 3c1. HMHero shows an error message.

      Use case resumes at step 3.

**Use case: Advance an applicant’s status**

Precondition: Status of Applicant to advance is not `ACCEPTED` or `REJECTED` and HMHero already has some applicants 
stored.

**MSS**

1.  User requests to list applicants.
2.  HMHero shows a list of applicants.
3.  User enters the command to advance the status of a specific applicant in the list.
4.  HMHero advances the applicant’s status.

    Use case ends.

**Extensions**


* 3a. The given applicant's name does not exist.

    * 3a1. HMHero shows an error message.

      Use case resumes at step 3.


* 3b. The given applicant's phone number does not exist.

    * 3b1. HMHero shows an error message.

      Use case resumes at step 3.


* 3c. The given applicant’s current status is `APPLIED` but no interview date and time was given. 

    * 3c1. HMHero shows an error message.

      Use case resumes at step 3.


* 3d. The given applicant’s current status is not `APPLIED` but an interview date and time was given.

    * 3d1. HMHero shows an error message.

      Use case resumes at step 3.


* 3e. The given interview date time is of invalid formatting.

    * 3e1. HMHero shows an error message.

      Use case resumes at step 3.


**Use case: Reject an applicant’s status**

Precondition: Status of Applicant to reject is not `REJECTED` and HMHero already has some applicants stored.

**MSS**

1.  User requests to list applicants.
2.  HMHero shows a list of applicants.
3.  User enters the command to reject a specific applicant in the list.
4.  HMHero sets the applicant’s status as `REJECTED`.

    Use case ends.

**Extensions**


* 3a. The given applicant's name does not exist.

    * 3a1. HMHero shows an error message.

      Use case resumes at step 3.


* 3b. The given applicant's phone number does not exist.

    * 3b1. HMHero shows an error message.

      Use case resumes at step 3.


**Use case: Finding applicants**

Precondition: Applicant to find exists in HMHero.

**MSS**

1.  User requests to list applicants.
2.  HMHero shows a list of applicants.
3.  User enters the command to find an applicant.
4.  HMHero shows all applicants that matches the given parameters.

    Use case ends.

**Extensions**

* 3a. HMHero detects that the command entered is of invalid formatting.
    * 3a1. HMHero shows an error message
  
      Use case resumes at step 3.


**Use case: Finding applicants with specific skills**

Precondition: Applicants with skills that user gave exists in HMHero.

**MSS**

1.  User requests to list applicants.
2.  HMHero shows a list of applicants.
3.  User enters the command to find applicants that match specific skills.
4.  HMHero shows all applicants with notes that match the given skills.

    Use case ends.

**Extensions**

* 3a. HMHero detects that the command entered is of invalid formatting.
    * 3a1. HMHero shows an error message

      Use case resumes at step 3.


**Use case: Remind upcoming interviews**

Precondition: There are shortlisted applicants in HMHero.

**MSS**

1.  User requests to list applicants.
2.  HMHero shows a list of applicants.
3.  User enters the command to remind himself/herself of the upcoming interviews.
4.  HMHero shows all applicants with interview dates within the next three days.

    Use case ends.

**Extensions**

* 3a. HMHero detects that the command entered is of invalid formatting.
    * 3a1. HMHero shows an error message

      Use case resumes at step 3.


**Use case: View all interview dates**

Precondition: There are shortlisted applicants in HMHero.

**MSS**

1.  User requests to list applicants.
2.  HMHero shows a list of applicants.
3.  User enters the command to view interview dates and times of all `SHORTLISTED` applicants.
4.  HMHero shows the interview date and times of all `SHORTLISTED` applicants, sorted from earliest to latest.

    Use case ends.

**Extensions**

* 3a. HMHero detects that the command entered is of invalid formatting.
    * 3a1. HMHero shows an error message

      Use case resumes at step 3.


**Use case: View summary statistics of hiring cycle**

Precondition: There are applicants added into HMHero.

**MSS**

1. User enters the command to view summary statistics.
2. HMHero shows summary statistics.

    Use case ends.

**Extensions**

* 1a. HMHero detects that the command entered is of invalid formatting.
    * 1a1. HMHero shows an error message

      Use case resumes at step 1.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  Should not require any internet connection.
5.  Should be usable by a novice who has never used managed applicants on HMHero before.
6.  Should be for a single user (i.e. not a multi-user product where different users can run the application at different times on a shared computer).
7.  The data of the application is stored locally.
8.  No database management system should be used.
9.  The product is not required to handle the screening of resume for each applicant.

### Glossary
#### Quick Reference
- [ADDRESS (Placeholder)](#address-placeholder)
- [Address](#address)
- [Admonitions](#admonitions)
- [Applicant](#applicant)
- [Application Cycle](#application-cycle)
- [COMMAND_WORD (Placeholder)](#commandword-placeholder)
- [Command](#command)
- [Command Line (CLI)](#command-line)
- [Placeholder](#email-placeholder)
- [Email](#email)
- [Flags](#flags)
- [Graphical User Interface(GUI)](#graphical-user-interface)
- [Help Window](#help-window)
- [Hiring Manager](#hiring-manager)
- [Input](#input)
- [INTERVIEW_DATE (Placeholder)](#interviewdate-placeholder)
- [Interview Date](#interview-date)
- [KEYWORD (Placeholder)](#keyword-placeholder)
- [Main Operating System](#mainstream-os)
- [Main Success Scenario](#main-success-scenario)
- [NAME (Placeholder)](#name-placeholder)
- [Name](#name)
- [NOTES (Placeholder)](#notes-placeholder)
- [Notes](#notes)
- [Output](#output)
- [Parameter](#parameter)
- [PHONE_NUMBER (Placeholder)](#phonenumber-placeholder)
- [Phone Number](#phone-number)
- [Placeholder](#placeholder)
- [STATUS (Placeholder)](#status-placeholder)
- [Status](#status)
- [Syntax](#syntax)
- [URL](#url)
- [Use Case](#use-case)
- [User Story](#user-story)


### A
> #### ADDRESS Placeholder
> The ADDRESS is a text representing the address of the applicants.
> #### Address
> The address of the applicant provided by the user.
> #### Admonitions
> Admonition boxes (or simply “admonitions”) are coloured boxes containing highlighted pieces of text.
> #### Applicant
> The person who apply to the company for the job.
> #### Application Cycle
> All applicants added are at the Applied status by default. From there, hiring managers can advance their application status to Shortlisted, then to Accepted. Applicants can be rejected at any stage.
![Application Cycle](images/application_stage.png)

### C
> #### COMMAND_WORD Placeholder
> The COMMAND_WORD is a text indicating a command word of a command
> #### Command
> A feature or function that HMHero can perform.
> #### Command Line
> The typing interface that you use to interact with HMHero. It is represented as the box where you type in commands.

### E
> #### EMAIL Placeholder
> The EMAIL is a text representing the email address of the applicants.
> #### Email
> The email address of the applicant provided by the user.

### F
> #### Flags
> A marker to identify the type of input by the user. For instance, in `n/NAME`, `n/` is the flag.

### G
> #### Graphical User Interface
> A Graphical User Interface is a graphics-based interface that uses icons, menus and a mouse (to click on the icon or pull down the menus) to manage interaction with the system. In HMHero, this presents as the window that appears when launching it.

### H
> #### Help Window
> A pop-up window containing help information, shown only after calling a <kbd>help</kbd> command.
> #### Hiring Manager
> A person responsible for overseeing the recruitment process within an organization, from identifying staffing needs to making hiring decisions.

### I
> #### Input
> The text that a user would enter into HMHero
> #### INTERVIEW_DATE Placeholder
> The INTERVIEW_DATE is the date indicating when the applicant is having his/her interview.
> #### Interview Date
> Date where applicants are going to have their interviews. Interview date is in the format "day-month-year hour:minute", represented in the "DD-MM-YYYY HH:MM" format.

### K
> #### KEYWORD Placeholder
> The KEYWORD is the text we use search for an item. It can be `NAME` or `PHONE_NUMBER` of the applicant.

### M
> #### Mainstream OS
> Mainstream Operating Systems (OS) refers to Windows, Linux, Unix, OS-X
> #### Main Success Scenario
> The Main Success Scenario (MSS) describes the most straightforward interaction for a given use case, assuming that nothing goes wrong.

### N
> #### NAME Placeholder
> The NAME is a text representing the name of the applicants.
> #### Name
> The name of the applicant provided by the user.
> #### NOTES Placeholder
> The NOTES are some texts that use to represent the skill set of the applicants.
> #### Notes
> The skill set the applicant possesses provided by the user.

### O
> #### Output
> The result after calling a Command. Results are displayed in the GUI.

### P
> #### Parameter
> Values that are passed while calling the Commands. For instance, when calling `find n/NAME`, parameter here is `n/NAME`.
> #### PHONE_NUMBER Placeholder
> The PHONE_NUMBER is an integer representing the phone number of the applicant.
> #### Phone Number
> The phone number of the applicant provided by the user.
> #### Placeholder
> Placeholders in HMHero refers to the UPPER_CASE words that appear after the flags in commands that is provided by the user. For instance, `n/NAME`, `NAME` is a placeholder

### S
> #### STATUS Placeholder
> The STATUS is a text that represent the current state of the applicant.
> #### Status
> Status has four stages, which are APPLIED, SHORTLISTED, ACCEPTED and REJECTED. Status can only be modified by using <kbd>advance</kbd> or <kbd>reject</kbd> command.
> #### Syntax
> The structure of statements/inputs users type into the Command Line.

### U
> #### URL
> A hyperlink to a website.
> #### Use Case
> A use case describes an interaction between the user and the system for a specific functionality of the system.
> #### User Story
> User stories are short, simple descriptions of a feature told from the perspective of the person who desires the new capability, usually a user or customer of the system. It is typically in the format “As a {user type/role} I can {function} so that {benefit}”.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** 
These instructions only provide a starting point for testers to work on; testers are expected to do more *exploratory* testing.
</div>

### Launch

1. Initial launch

   1. Download the `hmhero.jar` file and copy into an empty folder.

   2. Double-click the jar file <br>
   Expected: Shows the GUI with a set of sample applicants.
   <br></br>

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   2. Re-launch the app by double-clicking the jar file.<br>
      Expected: The most recent window size and location is retained.

### Adding a person

Prerequisites: An applicant with the name Fred and phone number 999 does not exist in HMHero yet.

Test case: `add n/Fred p/999 e/test@gmail.com a/12345`<br>
Expected: Applicant named Fred and with phone number 999 is added to the list.
Details of the added applicant shown in the output box.
Application date follows the current date and time.

Test case: `add n/Fred p/999 e/test@gmail.com a/12345 applied/01-01-2023 18:00`<br>
Expected: Applicant named Fred and with phone number 999 is added to the list.
Details of the added applicant shown in the output box.
Application date is `01-01-2023 18:00`.

Test case: `add n/Fred p/999`<br>
Expected: No applicant is added. Error message for invalid command format.

### Deleting a person

Prerequisites: An applicant with the name Fred and phone number 999 exists in HMHero.

Test case: `delete n/Fred p/999`<br>
Expected: Applicant named Fred and with phone number 999 is deleted from the list. 
Details of the deleted applicant shown in the output box.

Test case: `delete n/Fred p/000`<br>
Expected: No applicant is deleted. Error message for not finding any applicant.

Test case: `delete n/Fred`<br>
Expected: No applicant is deleted. Error message for invalid command format.


### Advancing an applicant

Prerequisites: There are two applicants: 
1. Applicant with the name Fred, phone number 999 and status `APPLIED`. 
2. Applicant with the name Freddy, phone number 000, status `SHORTLISTED` and interview date time `05-05-2023 18:00`.

Test case: `advance n/Fred p/999 d/04-04-2023 18:00`<br>
Expected: Applicant named `Fred` and with phone number 999 is advanced to `SHORTLISTED`.

Test case: `advance n/Fred p/999 d/05-05-2023 18:00`<br>
Expected: Applicant is not advanced. Error message for clashing interview date time with Freddy

Test case: `advance n/Fred p/999`<br>
Expected: Applicant is not advanced. Error message for no interview date time given.

Test case: `advance n/Freddy p/000 d/05-05-2023 18:00`<br>
Expected: Applicant is not advanced. Error message for no interview date time is needed.

Test case: `advance n/Fred`<br>
Expected: Applicant is not advanced. Error message for invalid command format.

### Rejecting an applicant

Prerequisites: There are two applicants:
1. Applicant with the name Fred, phone number 999 and status `APPLIED`.
2. Applicant with the name Freddy, phone number 000, status `REJECTED`.

Test case: `reject n/Fred p/999`<br>
Expected: Applicant named Fred and with phone number 999 is rejected.

Test case: `reject n/Freddy p/999`<br>
Expected: Applicant is not rejected. Error message for applicant is already rejected.

Test case: `reject n/Fred`<br>
Expected: Applicant is not rejected. Error message for invalid command format.

### Finding applicants

Prerequisite: Applicants to find exist in HMHero

Test case: `find n/Fred`<br>
Expected: All applicants with name `Fred` listed.

Test case: `find p/999`<br>
Expected: All applicants with phone number `999` listed.

Test case: `find n/Fred p/999`<br>
Expected: Applicant with name `Fred` and phone number `999` listed.

Test case: `find n/`<br>
Expected: Error message for invalid command format.


### Finding applicants with specific skills

Test case: `skill java`<br>
Expected: All applicants with `java` written in `notes`

Test case: `skill`<br>
Expected: Error message for invalid command format.


### Remind upcoming interviews

Prerequisite: There are shortlisted applicants with scheduled interviews in the next 3 days. 

Test case: `remind`<br>
Expected: All applicants with interviews coming up in the next 3 days are listed (if any).


### View all interviews

Prerequisite: There are shortlisted applicants with scheduled interviews

Test case: `interview`<br>
Expected: All shortlisted applicants are listed, sorted by their interview dates from earliest
to latest.


### View summary statistics

Precondition: There are applicants added into HMHero.

Test case: `summary`<br>
Expected: Statistic of overall hiring process is shown in output box.


### Help

Test case: `help`<br>
Expected: The Help Window pops up and shows a general help message.

### Clear

Test case: `clear`<br>
Expected: All applicants in HMHero are deleted.

### Exit

Test case: `exit`<br>
Expected: HMHero application closes.

## Effort

It was not easy to develop HMHero. Our team struggled with ways to make our product unique and stand out from existing products in the market,
while at the same time ensuring that this product could be completed in the short span of time given.


