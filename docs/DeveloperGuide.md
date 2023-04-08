---
layout: page
title: Developer Guide
---
- Table of Contents
- [**Setting up, getting started**](#setting-up-getting-started)
- [**Design**](#design)
  - [Architecture](#architecture)
  - [UI component](#ui-component)
  - [Logic component](#logic-component)
  - [Model component](#model-component)
  - [Storage component](#storage-component)
  - [Common classes](#common-classes)
- [**Implementation**](#implementation)
  - [Add remark feature](#add-remark-feature)
  - [Show opening details feature](#show-opening-details-feature)
  - [Upcoming keydates feature](#upcoming-keydates-feature)
  - [Filter by status feature](#filter-by-status-feature)
- [**Appendix: Requirements**](#appendix-requirements)
  - [Product scope](#product-scope)
  - [User stories](#user-stories)
  - [Use cases](#use-cases)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)
- [**Appendix: Instructions for manual testing**](#appendix-instructions-for-manual-testing)
  - [Launch and shutdown](#launch-and-shutdown)
  - [Adding an opening](#adding-an-opening)
  - [Editing an opening](#editing-an-opening)
  - [Showing an opening](#showing-an-opening)
  - [Adding, editing or deleting a remark](#adding-editing-or-deleting-a-remark)
  - [Deleting an opening](#deleting-an-opening)
  - [Clearing all openings](#clearing-all-openings)
  - [Finding an opening by company or position](#finding-an-opening-by-company-or-position)
  - [Finding an opening by status](#finding-an-opening-by-status)
  - [Getting openings with upcoming deadlines](#getting-openings-with-upcoming-deadlines)
- [**Appendix: Effort**](#appendix-effort)
  - [Evaluation of Implementation](#evaluation-of-implementation)
  - [Difficulties faced](#difficulties-faced)
  - [Achievements](#achievements)
- [**Appendix: Planned Enhancements**](#appendix-planned-enhancements)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2223S2-CS2103T-F12-4/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/ultron/Main.java) and [`MainApp`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/ultron/MainApp.java). It is responsible for,

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
* implements its functionality using a concrete `{Component Name}Manager` class which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/ultron/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `OpeningListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/ultron/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Opening` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/ultron/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it uses the `UltronParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add an opening).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:

* When called upon to parse a user command, the `UltronParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `UltronParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/ultron/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />

The `Model` component,

* stores the address book data i.e., all `Opening` objects (which are contained in a `UniqueOpeningList` object).
* stores the currently 'selected' `Opening` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Opening>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<!-- <div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Ultron`, which `Opening` references. This allows `Ultron` to only require one `Tag` object per unique tag, instead of each `Opening` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div> -->

### Storage component

**API** : [`Storage.java`](https://github.com/AY2223S2-CS2103T-F12-4/tp/blob/master/src/main/java/seedu/ultron/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,

* can save both address book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `UltronStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.ultron.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add remark feature
<!-- Darren -->
![UI Interaction for the `remark 1 r/2 OAs` Command](images/RemarkSequenceDiagram.png)

The `RemarkCommandParser` class parses the user input and returns a `RemarkCommand` object. The `RemarkCommand` object then accesses the `Model` component to get its filteredOpenings.

The `RemarkCommand` object accesses the filteredOpenings to get the opening at the specified index. It then uses the details of the retrieved opening along with a new remark to create a new opening.

The `RemarkCommand` object then calls `setOpening()` on `Model`, passing in the openingToEdit and editedOpening. The `Model` will then set the opening at the index specified to be the editedOpening.

Once the opening in the model has been changed, the result indicating the command's success will be returned to `LogicManager` and `MainWindow`



### Show opening details feature
<!-- Yu Fei -->
![UI Interaction for the `show 1` Command](images/ShowSequenceDiagram.png)

The `show` command was outlined with existing commands which take in indexes. It is similar to both the `delete` and `edit` commands, accessing the displayed list in  the `Model` component.

The `ShowCommandParser` class parses the user input and returns a `ShowCommand` object. The `ShowCommand` object then accesses the `Model` component to get the opening at the specified index from the openings list.

An `Index` is tracked by the model component and updated on every `ShowCommand` execution. A `CommandResult` object is then returned to the `LogicManager`.

The `MainWindow` class then receives the `CommandResult` object and calls the handler `handleShow`. The method gets the opening at the specified index from the `LogicManager` which in turn accesses the `ModelManager`.

A new `OpeningDetailsPanel` is created and displayed to the user. The `OpeningDetailsPanel` is a `UiPart` which is a `JavaFX` component. The `OpeningDetailsPanel` is created with the retrieved opening.

A simplified sequence was employed for mouse clicks. The `OpeningListPanel` class handles the mouse click event and calls the logic component to set the selected opening. The `MainWindow` class then calls the `handleShow` method to recreate the `OpeningDetailsPanel` with the selected opening.

### Upcoming keydates feature
<!-- Anton, Alex -->
### Filter by status feature
<!-- Kevin -->
## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of internship applications
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage openings faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I can …​                                                   | So that I can…​                                     |
| ------ | ------------------------------------------ |------------------------------------------------------------|-----------------------------------------------------|
| `* * *` | potential user exploring the app           | see the app populated with sample openings                 | easily see how the app will look when it is in use. |
| `* * *` | user                                       | add an opening regarding the internship I am interested in | keep note of it                                     |
| `* * *` | user                                       | delete an opening                                          | remove unwanted openings                            |
| `* * *` | user                                       | see all applications                                       | track my progress                                   |
| `* * *` | user                                       | add multiple keydates                                      | store all important dates for each opening          |
| `* * *` | user                                       | link certain key events of an opening to a date            | remember which event happens on what date           |
| `* * *` | user                                       | sort my keydates in order                                  | see which keydate of which opening to focus on      |
| `* * *` | user                                       | see openings of a specific status                          | view specific kinds of openings at once             |
| `* * *` | user                                       | see what keydates are nearing                              | avoid missing any keydates of any openings          |
| `* *`  | user                                       | add remarks to my openings                                 | add any addtional details tied to specific openings |
| `* *`  | user                                       | see submission deadlines                                   | check them                                          |
| `*`    | user | see the total number of accepted applications              | celebrate my success                                |
| `*` | user | see all the outcomes of my application                     | properly assess my options                          |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is `Ultron` and the **Actor** is the `user`, unless specified otherwise)

**Use case:  UC 1 - Adding an opening**

**MSS**

1. User enters a command to add an opening
2. Ultron adds the opening to its list of openings
   Use case ends.

**Extensions**

1a. Ultron detects an error in the entered data.
    1a1. Ultron informs the user that the data is invalid
    Use case ends

**Use case:  UC 2 - Deleting an opening**

**MSS**

1. User enters a command to delete an opening
2. Ultron deletes the opening from its list of openings
   Use case ends.

**Extensions**

1a. Ultron detects an error in the entered data.
    1a1. Ultron informs the user that the data is invalid
    Use case ends

**Use case:  UC 3 - Editing an opening**

**MSS**

1. User enters a command to edit an opening
2. Ultron edits the opening based on the user input parameters
   Use case ends.

**Extensions**

1a. Ultron detects an error in the entered data.
    1a1. Ultron informs the user that the data is invalid
    Use case ends

**Use case:  UC 4 - See all openings**

**MSS**

1. User enters a command to list all openings
2. Ultron shows the user a list of all the openings that the user has added
   Use case ends.

**Extensions**

1a. List is empty
    1a1. Ultron informs the user that the list is currently empty
    Use case ends

**Use case:  UC 5 - See all openings with a specific status**

**MSS**

1. User enters a command to list all openings of a specific status
2. Ultron shows the user a list of all the openings that match the status given by the user
   Use case ends.

**Extensions**

1a. No opening contains the status specified by the user
    1a1. Ultron informs the user that no openings of that status exist
    Use case ends

**Use case:  UC 6 - Sort openings by deadline**

**MSS**

1. User enters a command to sort all openings by deadline in ascending order
2. Ultron shows the user a list of openings that contain deadlines, sorted by deadlines in ascending order
   Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. The app should be fast and respond within 100ms when users input commands.
3. The app should start up and load the list from a saved file within 1 second of opening.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Opening**: Item representing an internship opening
* **Keydate**: Item representing a event tied to a particular date or deadline

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample openings. The window size may not be optimum.

2. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. Exiting via command line

   1. Test case: `exit`<br>
      Expected: App exits with no issues and window closes

### Adding an opening

1. Adding an opening that does not already exist in the list

    1. Prerequisites: Openings that are being added do not already exist in the list. Meaning that the opening does not share both the company name and position name with another opening

    2. Test case: `add p/position c/company e/example@example.com s/applied`<br>
       Expected: An opening is added to the end of the list. Success message shown in the status message. Opening added contains fields that match those given in the command line.

    3. Test case: `add p/position c/company s/applied`<br>
       Expected: No opening is added. Error details shown in the status message. Command line remains the same.

    4. Other incorrect add commands to try: `add`<br>
       Expected: Similar to previous.

2. Trying to add an opening that already exists in the list

    1. Prerequisites: Openings that are trying to be added already exist in the list. Meaning that the opening shares both the company name and position name with another opening

    2. Test case: `add p/position c/company e/example@example.com s/applied`<br>
       Expected: No opening is added. Error details shown in the status message. Command line remains the same.

### Editing an opening

1. Editing an opening to change a field

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list.

    2. Test case: `edit 1 p/new position`<br>
       Expected: First opening gets edited. Success message shown in the status message. First opening now has "new position" in its position field.

    3. Test case: `edit 0 p/new position`<br>
       Expected: No opening is edited. Error details shown in the status message. Command line remains the same.

    4. Other incorrect edit commands to try: `edit`, `edit x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

2. Editing an opening to add a deadline

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list.

    2. Test case: `edit 1 d/Online Assessment@2023-04-01`<br>
       Expected: First opening gets edited. Success message shown in the status message. First opening now contains a deadline named "Online Assessment" due 2023-04-01.

3. Editing an opening to replace a deadline

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list. First opening contains a deadline

    2. Test case: `edit 1 d/Interview@2023-04-05`<br>
       Expected: First opening gets edited. Success message shown in the status message. First opening now contains a deadline named "Interview" due 2023-04-05.

4. Editing an opening to remove a deadline

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list. First opening contains a deadline

    2. Test case: `edit 1 d/`<br>
       Expected: First opening gets edited. Success message shown in the status message. First opening now contains no deadlines

### Showing an opening

1. Viewing the details of an opening while all openings are being shown

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list.

    2. Test case: `show 1`<br>
       Expected: Details of first opening is shown in the right panel. Details of the shown opening shown in the status message.

    3. Test case: click on an opening on the left panel<br>
       Expected: Details of clicked opening is shown in the right panel.

    4. Test case: `show 0`<br>
       Expected: No new opening is shown. Error details shown in the status message. Command line remains the same.

    5. Other incorrect show commands to try: `show`, `show x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Adding, editing or deleting a remark

1. Adding a remark

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list. 

    2. Test case: `remark x r/3 rounds of interviews` (where x is the index of an opening without an existing remark) <br>
       Expected: Opening gets edited. Success message shown in the status message. Opening now has "3 rounds of interviews" in its remark field, visible after using the show command.

    3. Test case: `remark 0 r/3 rounds of interviews`<br>
       Expected: No opening is edited. Error details shown in the status message. Command line remains the same.

    4. Other incorrect remark commands to try: `remark`, `remark x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

2. Deleting a remark

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list.

    2. Test case: `remark x r/` (where x is the index of an opening with an existing remark)<br>
       Expected: Opening gets edited. Success message shown in the status message. Opening now has no remarks in its remark field.

3. Editing a remark

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list. First opening contains a deadline

    2. Test case: `remark x r/Has difficult online assessment` (where x is the index of an opening with an existing remark) <br>
       Expected: Opening gets edited. Success message shown in the status message. Opening now has "Has difficult online assessment" in its remark field, visible after using the show command.

### Deleting an opening

1. Deleting an opening while all openings are being shown in list

   1. Prerequisites: List all openings using the `list` command. Multiple openings in the list.

   2. Test case: `delete 1`<br>
      Expected: First opening is deleted from the list. Details of the deleted opening shown in the status message.

   3. Test case: `delete 0`<br>
      Expected: No opening is deleted. Error details shown in the status message. Command line remains the same.

   4. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

2. Deleting an opening currently being shown in the right panel

    1. Prerequisites: List all openings using the `list` command. Multiple openings in the list. Show the opening to be deleted using `show x` command (where x is index of opening to be deleted)

    1. Test case: `delete x`<br>
       Expected: Opening with index x is deleted from the list. Details of the deleted opening shown in the status message. Right panel is now empty.

### Clearing all openings

1. Clearing all openings

    1. Test case: `clear`<br>
       Expected: All openings are deleted from the list. Success message shown in status message.

### Finding an opening by company or position

1. Finding an opening by keyword

    1. Prerequisites: At least one opening with the word "developer" in its position or company name exists in the list

    2. Test case: `find developer`<br>
       Expected: List filters out all openings that do not have the word "developer" in its position or company name. Number of found openings is shown in the status message. Command line remains the same

   3.Test case: `find`<br>
   Expected: List remains the same. Error details shown in the status message. Command line remains the same.

### Finding an opening by status

1. Finding an opening by status

    1. Prerequisites: At least one opening with "interviewing" as its status

    2. Test case: `status interviewing`<br>
       Expected: List filters out all openings that do not have "interviewing" as its status. Number of found openings is shown in the status message. Command line remains the same.

   3.Test case: `status`<br>
   Expected: List remains the same. Error details shown in the status message. Command line remains the same.

### Getting openings with upcoming deadlines

1. Getting openings with upcoming deadlines

    1. Prerequisites: Multiple openings in the list. Openings contain deadlines not passed due within 5 days of today.

    2. Test case: `upcoming 5`<br>
       Expected: List shows openings that contain deadlines not passed within 5 days of today. Openings are sorted in order, with the openings with the closer deadlines on top. Number of found openings is shown in the status message.

    3. Test case: `upcoming`<br>
   Expected: List remains the same. Error details shown in the status message. Command line remains the same.

## **Appendix: Effort**

### Evaluation of Implementation

This table summarises the difficulty level and effectiveness of each feature on a scale of 1 to 5.

| **Feature** | **Difficulty** | **Effectiveness** |
| --- | --- | --- |
| Keydates | 3 | 5 |
| Keydate sorting | 3 | 4 |
| Status command | 2 | 3 |
| Remark | 1 | 2 |
| Upcoming command | 4 | 4 |
| UI enhancements | 4 | 3 |

### Difficulties faced

1. Keydates

   1. Keydates were implemented using the existing 'Tag' class as a baseline. Refactoring was done to both the 'Tag' model and 'TagParser' to allow for the creation of keydates.

   2. There were more issues with the 'Tag' model than expected, since the key and date had to be further extracted from the input arguments. This lead to multiple separator characters being tested out before settling on the current implementation. The tokenisation of the input arguments was also haphazardly inserted, since the alternative would require a complete overhaul of the 'ArgumentTokenizer' class.

   3. Input validation was often overlooked and only discovered during testing. Empty or untrimmed keys were initially accepted and dates were not checked for validity. This was fixed by adding additional checks in the parser.

2. Sorting of keydates

   1. The initial collection of keydates was implemented using a HashSet, which does not allow for ordering. This was changed an ArrayList, which allowed for ordering of keydates. There are further issues with the logic component and its use of a filtered list which we eventually identified and replaced with an observable list.

   2. Comparison of keydates was initially implemented with a custom comparator. This was changed to implementing the 'Comparable' interface and defining a 'compareTo' method, which allowed for the sorting of keydates to be done in the 'Keydate' class itself.

3. Status command

   1. The status command was rather straightforward and made use of existing Predicate classes. The only issue was the lack of a 'Status' class, which was rectified by creating a new class.

4. Remark command

   1. The existing implementation from our team project tutorial was used as a baseline. Minor changes were made hence did not pose any major issues.

5. Upcoming command

   1. This command required the most effort to implement. The initial proposal was to have a sorting command and an upcoming command, but this was changed to a single upcoming command.

   2. A opening predicate and a opening comparator had to be implemented for the two-step process of filtering and sorting

6. UI enhancements

   1. The initial list view limited the amount of information that could be displayed. A details panel was added to display more information about the opening.

   2. This required restructuring of the UI components and the logic components. The logic components had to be modified to allow for the details panel to be updated when a new opening is selected.

### Achievements

A large part of the project was spent on refactoring the codebase and ensuring practical design. Many features were remade after testing and discussion concluded that the current implementation was not ideal. We also invested significant time into improving the UI, which was not a requirement of the project.

## **Appendix: Planned Enhancements**

### **Enhancement 1: Optional Contact Field**

1. The current email field for each opening is not optional. This is not ideal as some openings do not require an email address and users may find it inconvenient to have to enter a dummy email address. An email address might also not be the most appropriate contact information for some openings.

2. We propose to replace the email field with an optional contact field. This field can be used to store any contact information, e.g. phone number, email address, LinkedIn profile link, career portal link. This will allow users to store any contact information they wish to have for each opening.
