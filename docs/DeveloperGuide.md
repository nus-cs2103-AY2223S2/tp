---
layout: page
title: Developer Guide
---
## **Overview**

GoodMatch (GM) is a **desktop app for managing applicants and job listings, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI), specifically catering to HR managers in charge of tracking job listings across many platforms. If you can type fast, GM can get your applicant and job listing management tasks done faster than traditional GUI apps

---

### Table of Contents

- [Overview](#overview)
  - [Table of Contents](#table-of-contents)
  - [Purpose of this guide](#purpose-of-this-guide)
  - [How to use this guide](#how-to-use-this-guide)
  - [Legends](#legends)
- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#setting-up-getting-started)
- [Design](#design)
  - [Architecture](#architecture)
- [Appendix: Requirements](#appendix-requirements)
  - [Product scope](#product-scope)
  - [User stories](#user-stories)
  - [Use cases](#use-cases)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
- [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)
  - [Launch and shutdown](#launch-and-shutdown)
  - [Deleting a listing](#deleting-a-listing)
  - [Saving data](#saving-data)

---

### Purpose of this guide

Welcome to the developer guide for GoodMatch. This guide will provide you with the information to work on GoodMatch by helping you understand how GoodMatch is designed. The guide also includes information on the requirements of GoodMatch as well as the instructions to manually test GoodMatch on your local machines.

### How to use this guide

To make the most of this guide, start by reading it from beginning to end. We recommend that you familiarize yourself with the basic concepts before moving on to the advanced topics.

Use the interactive [table of contents](#table-of-contents) to navigate through the document quickly. Simply click on the bullet points to be taken to the relevant subsection. Follow the step-by-step instructions, screenshots, and examples to get the most out of the guide.

### Legends

💡 **Tip:** You can find additional tips about the developer guide here.

ℹ️ **Notes**: You can find additional information about the command or feature here.

❗ **Caution**: Be careful not to make this deadly mistake.

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

Codebase foundation by AB3.

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-W14-3/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<p>
  <img src="images/ArchitectureDiagram.png" />
  <em>Architecture Diagram for GoodMatch</em>
</p>

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

<p>
  <img src="images/ArchitectureSequenceDiagram.png" />
  <em>Architecture Sequence Diagram for GoodMatch</em>
</p>

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<p>
  <img src="images/ComponentManagers.png" />
  <em>Sequence Diagram for the Managers in GoodMatch</em>
</p>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<p>
  <img src="images/UiClassDiagram.png" />
  <em>Structure of the UI Component</em>
</p>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ListingListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-W14-3/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-W14-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Listing` object residing in the `Model`.

---

## **Appendix: Requirements**

### Product scope

**Target user profile: Recruiters (Private or from small businesses)**

- Has a need to manage a significant number of job listings.
- Prefer desktop apps over other types.
- Can type fast.
- Prefers typing to mouse interactions.
- Is reasonably comfortable using CLI apps

**Value proposition**: All-in-one app that is free for managing job listings with an intuitive user experience

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| No. | Priority | As a …​ | I want to …​ | So that… |
| --- | --- | --- | --- | --- |
| 1 | * * * | Recruiter who receives thousands of applications each day.  | store all information about my applicants | I don't lose track of any applicants. |
| 2 | * * * | Recruiter | post a new job posting | I can start attracting applicants. |
| 3 | * * * | Recruiter | list out information regarding a job offer | I can confirm the information |
| 4 | * * * | Recruiter | update job listings | it reflects the most updated information |
| 5 | * * * | Recruiter | view all job listings |  |
| 6 | * * * | Recruiter | delete outdated job listing | the information is accurate |
| 7 | * * | Recruiter | sort job listings by expiry date | finding most urgent job listing can be easy |
| 8 | * * * | Recruiter | view all applicants |  |
| 9 | * * * | Recruiter | view applicants for each job listing | I know who has applied for each job |
| 10 | * * * | Recruiter | add applicants to job listing |  |
| 11 | * * * | Recruiter | remove disqualified applicant | the database stays clean |
| 12 | * * * | Recruiter | view applicant details | I can make sure it’s updated |
| 13 | * * * | Recruiter | update applicant status | it’s easy for me to track the progress of each applicant |
| 14 | * * * | Recruiter | update applicant details | each applicant’s info is updated |
| 15 | * * * | Recruiter | auto-remove job listings from view if they are expired | viewing job listings won't be cluttered |
| 16 | * * * | Recruiter | auto-remove job listings from view if vacancy fulfilled | the current list of jobs are all still available |
| 17 | * * * | Recruiter | search job listings by title |  |
| 18 | * * * | Recruiter | prevent applicants from applying to the same job more than once | the list of applicants is not cluttered |
| 19 | * * | Recruiter | add tag indicating relevant hiring department to the job listing |  |
| 20 | * * | Recruiter | view the tag attached to the job listing | it's easy to find types of job listings |
| 21 | * * | Recruiter | change the tag attached to the job listing | update the tag to reflect the most updated information |
| 22 | * * | Recruiter from a particular department | filter the job listings according to the tag | easily find jobs |
| 23 | * * * | Recruiter | search for applicants in each job listing |  |
| 24 | * * | Recruiter | sort job listings by the number of applicants |  |
| 25 | * * * | Recruiter | update the status of applicants | it's easy for us to know how to contact the applicant |
| 26 | * * * | Recruiter | filter status of applicants | easily find the types of applicants I’m looking for |
| 27 | * * * | Recruiter | sort the applicant by status |  |
| 28 | * * | Recruiter | add notes about applicants | it’s easy to remember details about each applicant |
| 29 | * * | Recruiter | get help with how to use the program | I know what commands I have available |
| 30 | * * * | Recruiter | come back to the program and continue from where I left off | I won't lose my progress  |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `ListingBook` and the **Actor** is the `Recruiter` unless specified otherwise)

**Use case: Delete a Listing**

**MSS**

1. Recruiter requests to list listings.
2. ListingBook shows a list of listings.
3. The recruiter requests to delete a specific listing from the list.
4. ListingBook deletes the listing.

    Use case ends.

**Extensions**

- 2a. The list is empty.

    Use case ends.

- 3a. The given index is invalid.
    - 3a1. ListingBook shows an error message.
    - Use case resumes at step 2.



**Use case: List all job listings**

**MSS**

1. Recruiter requests to list job listings.
2. ListingBook shows a list of job listings.

    Use case ends.

**Extensions**

- 2a. The list is empty.

    Use case ends.


**Use case: Add a new job listing**

**MSS**

1. The recruiter requests to add a new job listing.
2. ListingBook adds the job listing to the list of job listings.

    Use case ends.

**Extensions**

- 2a. The placeholders used are invalid.
    - 2a1. ListingBook shows an error message.
    - Use case resumes at step 1.

- 2b. The new job title is invalid.
    - 2b1. ListingBook shows an error message.
    - Use case resumes at step 1.

- 2c. The new job description is invalid.
    - 2c1. ListingBook shows an error message.
    - Use case resumes at step 1.

- 2d. There is a duplicate listing in the listing book.
    - 2d1. ListingBook shows an error message.
    - Use case resumes at step 1.

**Use case: Update a job listing**

**MSS**

1. Recruiter requests to update a job listing.
2. ListingBook shows a list of job listings.
3. The recruiter requests to update a specific listing from the list.
4. ListingBook updates the job listing.

    Use case ends.

**Extensions**

- 2a. The list is empty.

    Use case ends.

- 3a. The given index is invalid.
    - 3a1. ListingBook shows an error message.
    - Use case resumes at step 2.
 
- 3b. The placeholders used are invalid.
    - 3b1. ListingBook shows an error message.
    - Use case resumes at step 2.

- 3c. The new job title is invalid.
    - 3c1. ListingBook shows an error message.
    - Use case resumes at step 2.

- 3d. The new job description is invalid.
    - 3d1. ListingBook shows an error message.
    - Use case resumes at step 2.

- 3e. There is a duplicate listing in the listing book.
    - 3e1. ListingBook shows an error message.
    - Use case resumes at step 2.

**Use case: Find a job listing**

**MSS**

1. Recruiter requests to find a job listing by keyword(s).
2. ListingBook displays a list of job listings that match the keyword.

    Use case ends.

**Extensions**

- 2a. No job listings match the keyword.

    Use case ends.
- 2b. The list is empty.

    Use case ends.


**Use case: Sort job listings**

**MSS**

1. Recruiter requests to sort job listings.
2. ListingBook sorts the job listings according to the selected option.
3. ListingBook displays the sorted list of job listings.

    Use case ends.

**Extensions**

- 2a. The list is empty.

    Use case ends.

### **Use case: Undo**

**MSS**
1. Recruiter requests for an undo.
2. ListingBook reverses the last command.
3. ListingBook displays reversed list of job listings.

    Use case ends.
 
**Extensions**
- 2a. Previous command does not change the ListingBook.

    Use case ends.

**Use case: Filter job listings**

**MSS**

1. Recruiter requests to filter job listings.
2. ListingBook filters the job listings according to the selected option.
3. ListingBook displays the filtered list of job listings.

    Use case ends.

**Extensions**

- 4a. No job listings match the filter criteria.

    Use case ends.
- 4b. The list is empty.

    Use case ends.

**Use case: Delete an applicant from a job listing**

**MSS**

1. Recruiter requests to delete an applicant from a job listing.
2. ListingBook remove the existing applicant from the job listing.
3. ListingBook displays the job listings with the applicant removed from the specified listing.
Use case ends.

**Extensions**

- 1a. Specified job listing not found.

    Use case ends.
- 1b. Specified applicant not found in the job listing.

    Use case ends.
- 1c. There are two or more applicants that match the keywords.
  - 1c1. ListingBook requests user to provide a more specific keyword.
  - 1c2. User enters new request.
  - Steps 1c1-1c2 are repeated until the data entered are correct.
  - Use case resumes from step 2.

**Use case: Edit an applicant from a job listing**

**MSS**

1. Recruiter requests to edit an applicant from a job listing.
2. ListingBook changes the details of the existing applicant from the job listing.
3. ListingBook displays the job listings with the edited applicant from the specified listing.

    Use case ends.

**Extensions**

- 1a. Specified job listing not found.

    Use case ends.
- 1b. Specified applicant not found in the job listing.

    Use case ends.
- 1c. There are two or more applicants that match the keywords.
    - 1c1. ListingBook requests user to provide a more specific keyword.
    - 1c2. User enters new request.
    - Steps 1c1-1c2 are repeated until the data entered are correct.
    - Use case resumes from step 2.

### Non-Functional Requirements

1. Should work on any *mainstream OS* as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 listings without a noticeable sluggishness in performance for typical usage.
3. A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be maintainable and have a clear code structure and documentation, so new updates and bug fixes can be easily implemented.
5. Should be easy to use with clear instructions and meaningful error messages.

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Private contact detail**: A contact detail that is not meant to be shared with others

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

### Deleting a listing

1. Deleting a listing while all listings are being shown

  1. Prerequisites: List all listings using the `view` command. Multiple listings in the list.

  1. Test case: `delete 1`<br>
     Expected: First listing is deleted from the list. Details of the deleted listing shown in the status message. Timestamp in the status bar is updated.

  1. Test case: `delete 0`<br>
     Expected: No listing is deleted. Error details shown in the status message. Status bar remains the same.

  1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
     Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

  1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
