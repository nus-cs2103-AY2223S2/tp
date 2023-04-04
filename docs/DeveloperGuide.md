---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

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

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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

* Defines its *API* in an `interface` with the same name as the Component.
* Implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, 
`RecipeListPanel`,
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.
* Keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* Depends on some classes in the `Model` component, as it displays `Recipe` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `CookHubParser` class to parse the user command.
2. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
3. The command can communicate with the `Model` when it is executed (e.g. to add a recipe).
4. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `CookHubParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `CookHubParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, `EditCommandParser`, `FindCommandParser`, `CookHubParser`) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* Stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* Stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* Stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* Does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](images/StorageClassDiagram.png)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both recipe book data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `RecipeBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)
* converts classes into their respective string formats in order to save them in json format

### Common classes

Classes used by multiple components are in the `seedu.recipebook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

* Currently, we are not doing undo/redo mechanism
The proposed undo/redo mechanism is facilitated by `VersionedCookHub`. It extends `CookHub` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedCookHub#commit()` — Saves the current address book state in its history.
* `VersionedCookHub#undo()` — Restores the previous address book state from its history.
* `VersionedCookHub#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitCookHub()`, `Model#undoCookHub()` and `Model#redoCookHub()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedCookHub` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitCookHub()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitCookHub()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitCookHub()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoCookHub()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial CookHub state, then there are no previous CookHub states to restore. The `undo` command uses `Model#canUndoCookHub()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoCookHub()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone CookHub states to restore. The `redo` command uses `Model#canRedoCookHub()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitCookHub()`, `Model#undoCookHub()` or `Model#redoCookHub()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitCookHub()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_
- Currently, we are not doing data archiving, however that will be in consideration for your future iteration v1.3



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

* Student chefs who favour their own collection of personal recipes rather than online recipes
* Student chefs on a tight budget and schedule
* Student chefs who have limited ingredients
* Student chefs who are capable in typing fast
* Prefers typing to mouse interaction
* Is reasonably comfortable using CLI applications

**Value proposition**:

* Increase efficiency of preparing ingredients and cooking given limited time and resources
* Provide convenience to student chefs with recipes catered to their needs
* Easily plan out a week's worth of meals with grocery lists and meal plans
* Helps student chefs easily access their personal vault of recipes instead of using a recipe notebook
* Easy customisation of personal recipes without wasting space (cancelling on the notebook or writing on a new page)
* Quick access to recipes while on the go


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                               | I want to …​                                                                                           | So that I can…​                                                                                                                                           |
|----------|---------------------------------------|--------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `* `     | cook                                  | easily view the recipes that I viewed recently                                                         | have easy access to my favourite recipes                                                                                                                  |
| `* * `   | cook                                  | assign diffulty tags                                                                                   | easily search up easy recipes when I am busy                                                                                                              |
| `* `     | cook on a limited budget              | filter recipes by cost                                                                                 | so I can use recipes that costs less                                                                                                                      |
| `* * `   | student who wants to stay fit         | assign healthy tags                                                                                    | make meals that are healthy                                                                                                                               |
| `* `     | cook                                  | add designs to my recipe                                                                               | to beautify it                                                                                                                                            |
| `* * * ` | cook                                  | add a title to the recipe                                                                              | refer to it easily                                                                                                                                        |
| `* * * ` | cook                                  | have a clear view of my recipe                                                                         | easily follow instructions to make a meal                                                                                                                 |
| `* * * ` | cook                                  | read the recipes I have created                                                                        ||
| `* * * ` | cook                                  | create a recipe                                                                                        ||
| `* * `   | student who has limited time          | search for the cooking time                                                                            | be better able to plan my time                                                                                                                            |
| `* `     | student with limited mental resources | create a meal plan for the week                                                                        | take less time to think                                                                                                                                   |
| `* `     | cook                                  | get a random recipe                                                                                    | get inspiration for what to cook                                                                                                                          |
| `* * * ` | cook                                  | see usage instructions                                                                                 | refer to instructions when I forget how to use the App                                                                                                    |
| `* * `   | cook                                  | duplicate recipes                                                                                      | easily add variations                                                                                                                                     |
| `* * `   | consumer of food                      | rate the recipes                                                                                       | know which ones are worth cooking again                                                                                                                   |
| `* * * ` | cook                                  | edit all parts of the recipe                                                                           | update and improve on the recipe after trying it out                                                                                                      |
| `* * `   | cook                                  | save my favourtie recipes                                                                              | easily find them again                                                                                                                                    |
| `* * `   | cook                                  | categorize my recipes with labels                                                                      | easily sort and filter them                                                                                                                               |
| `* * * ` | cook                                  | delete recipes                                                                                         ||
| `* * `   | cook with limited ingredients         | search up on recipes that contain only the ingredients I have in my fridge                             |                                                                                                                                                           |
| `* `     | cook                                  | get a list of groceries I need for the recipes for the week                                            | limit my trips to the grocery store                                                                                                                       |
| `* * `   | student with limited finanaces        | add in the price of the ingredients                                                                    | budget for my meals                                                                                                                                       |
| `* * * ` | cook                                  | add recipes step by step                                                                               | easily follow along when cooking easily                                                                                                                   |
| `* * * ` | novice cook                           | add recipes step by step                                                                               | easily follow along while cooking                                                                                                                         |
| `* * `   | cook                                  | input the number of servings wanted and have the amount of ingredients needed be updated automatically | input the number of servings wanted and have the amount of ingredients needed be updated automatically so that a different number of people can be served |
| `* * `   | cook                                  | see most recent recipe cooked                                                                          | to quickly cook                                                                                                                                           |
| `* * `   | cook                                  | easily search recipes based on the ingredients                                                         | so that I can always find something to cook                                                                                                               |
| `* * `   | friend                                | export recipes                                                                                         | family and friends can use my ideas                                                                                                                       |
| `* * `   | cook                                  | import recipes easily                                                                                  | save time adding recipes                                                                                                                                  |
| `* * `   | cook                                  | add ingredients to each recipe                                                                         | know what I need to cook                                                                                                                                  |
| `* * `   | cook                                  | get a list of groceries to buy                                                                         | know what groceries I need to cook my recipes                                                                                                             |



*{More to be added}*

### Use cases

(For all use cases below, the **System** is `CookHub` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a recipe**

**MSS**

1. User requests to add in a recipe
2. CookHub continually prompts the user for each recipe instruction
3. User tells CookHub that he/she has finished typing in all the recipe instructions
4. CookHub stores the recipe, and shows a success message

   Use case ends.


**Use case: Delete a recipe**

**MSS**

1. User requests to list the recipe names
2. CookHub shows a list of recipe names
3. User requests to delete a recipe from the list using its index
4. CookHub deletes the recipe at the specified index
5. CookHub shows a success message

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a.  The specified index is invalid
   *  3a1. CookHub shows an error message

      Use case resumes at step 2


*{More to be added}*

### Non-Functional Requirements

1. Should work on any mainstream OS as long as there is Java 11 installed
2. Should be able to support almost 500 to 1000 recipes without noticeable sluggishness in performance for typical usage, depending on the size of each recipe
3. A user with above average typing speed for regular English text should be able to accomplish most of the tasks faster using commands than using the mouse
4. Should be able to be scalable to store more aspects of a recipe, or more functionalities, such as tagging, sorting, etc
5. Should not be required to handle pictures of recipes or ingredients
6. Should be usable to someone who is not familiar with command line interface

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X


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

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

### Appendix: Planned Enhancements

1. Filter by price after find function will only apply the filter on the subset of recipes that have been generated by the "find" function

2. After sorting recipes by price, calling the list function will reset the list to its original order

3. The find command when used on two separate ingredients (eg. egg and tomatoes) will find all the recipes that use either eggs or tomatoes and not recipes that include both egg and tomatoes.

4. Modify edit function such that user cannot change a recipe title to one that already exists in the RecipeBook regardless of casing in the letters of the title.

5. Restrict ingredient name and unit of measurement to only valid inputs and not random string input or numerical input

6. All the command words, flags and arguments such as (sort asc or desc) should be case insensitive.

7. We want to update the edit feature to only edit only one field instead of every single field.

8. Allow ingredients prices to be more general, such as olive oil buy by bottle but in the recipe only allow to input price by tablespoon which is hard to calculate.

9. Allow find to work with multiple flags

10. Duplicate ingredients should not be allowed