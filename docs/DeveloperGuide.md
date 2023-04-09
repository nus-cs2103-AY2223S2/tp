## PowerConnect Developer Guide

PowerConnect is a fully customized offline application for tuition and school teachers to manage students' and parents' administrative details. While it has limited features at this stage, plans for future PowerConnect releases to update features and usage capabilities have been made with detailed timeline.
The aim of this product is to eventually be a useful tool that is used in conjunction with Learning Management System (LMS) tools currently in the market to aid teachers in managing students. <br>

PowerConnect is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). PowerConnect helps teachers in tracking and updating students' particulars.

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "table-of-contents"/>

## **Table of Contents**
1. [Acknowledgements](#acknowledgements)
2. [Setting Up, Getting Started](#setting-up-getting-started)
3. [Design](#design)
   1. [Architecture](#architecture)
   2. [Ui Component](#ui-component)
   3. [Logic Component](#logic-component)
   4. [Model Component](#model-component)
   5. [Storage Component](#storage-component)
   6. [Common Classes](#common-classes)
4. [Implementation](#implementation)
   1. [Student/Parent Delete Feature](#delete-studentparent-feature)
   2. [Attendance Feature](#attendance-feature)
   3. [Grade Feature](#grade-feature)
   4. [Parent/NOK Add Feature](#parentnok-add-feature)
   4. [Parent/NOK Edit Feature](#parentnok-edit-feature)
   5. [Binding Parent/NOK and Student](#binding-student-and-parentnok-feature)
5. [Planned Enhancements](#planned-enhancements)
   1. [Enhancement 1 - Name Fix](#enhancement-1-name-fix)
   2. [Enhancement 2 - Age Fix](#enhancement-2-age-fix)
   3. [Enhancement 3 - Attendance Fix](#enhancement-3-attendance-fix)
6. [Documentation, Logging, Testing, Configuration, Dev-ops](#documentation-logging-testing-configuration-dev-ops)
7. [Appendix](#appendix-requirements)
   1. [Appendix-Requirements](#appendix-requirements)
      1. [Product Scope](#product-scope)
      2. [User Stories](#user-stories)
      3. [Use Cases](#use-cases)
         1. [UC01 - Adding a new student](#use-case-uc01---adding-a-new-student-to-an-existing-class)
         2. [UC02 - Adding a grade for a student](#use-case-uc02---adding-grade-for-a-student)
         3. [UC03 - Adding a comment for a student](#use-case-uc03---adding-comment-for-a-student)
         4. [UC04 - Listing all students in a class](#use-case-uc04---listing-all-students-in-the-selected-class)
         5. [UC05 - Finding a student in a class](#use-case-uc05---finding-a-student-in-a-class)
         6. [UC06 - Deleting a student from a class](#use-case-uc06---deleting-student-from-class)
         7. [UC07 - Adding a new parent/NOK](#use-case-uc07---adding-a-new-parentnok-to-the-system)
         8. [UC08 - Editing a parent/NOK](#use-case-uc08---editing-an-existing-parentnok-in-the-system)
         9. [UC09 - Deleting a parent/NOK](#use-case-uc09---deleting-a-parentnok-from-the-system)
      4. [Non-Functional Requirements](#non-functional-requirements)
      5. [Glossary](#glossary)
   2. [Appendix-Instructions for Manual Testing](#appendix-instructions-for-manual-testing)
      1. [Launch and Shutdown](#launch-and-shutdown)
      2. [Adding a student](#addingstudent)
      3. [Marking attendance](#markingattendance)
      4. [Adding a grade](#addinggrade)
      5. [Deleting a grade](#deletinggrade)
      6. [Adding a comment](#addingcomment)
      7. [Editing student](#editingstudent)
      8. [Listing student by class](#listingstudentbyclass)
      9. [Listing all student](#listingallstudent)
      10. [Finding student](#findingstudent)
      11. [Deleting student](#deletingstudent)
      12. [Adding a parent](#addingparent)
      13. [Listing all parents](#listingparent)
      14. [Editing a parent](#editingparent)
      15. [Finding a parent](#findingparent)
      16. [Deleting a parent](#deletingparent)
      17. [Saving preferences](#savingwindow)
      18. [Shutdown of application](#shutdown)
      19. [Saving data](#saving-data)
   3. [Appendix-Effort](#effort)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "acknowledgements"/>

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
* This project also took inspiration from [TeaPet](https://github.com/AY1920S2-CS2103T-W12-2/main) during the implementation of PowerConnect's Storage.

--------------------------------------------------------------------------------------------------------------------
<a name = "setting-up-getting-started"/>

## **Setting Up, Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "design"/>

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

--------------------------------------------------------------------------------------------------------------------
<a name = "architecture"/>

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

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `student delete in/13`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.)

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "ui-component"/>

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java).

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml).

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "logic-component"/>

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `PowerConnectParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `StudentAddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `student delete command` & `parent/NOK delete command`.

**Sequence Diagram for Student Delete Command**
![Interactions inside the Logic Component for the `Student Delete` Command](images/StudentDeleteSequentialDiagram.png)

**Sequence Diagram for Parent/NOK Delete Command**
![Interactions inside the Logic Component for the `Parent/NOK Delete` Command](images/ParentDeleteSequentialDiagram.png)



Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `PowerConnectParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `StudentAddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `StudentAddCommand`) which the `PowerConnectParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `StudentAddCommandParser`, `StudentDeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "model-component"/>

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "storage-component"/>

### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StoragePowerConnect.png" width="550" />

The `Storage` component,
* can save both PowerConnect data and user preference data in json format, and read them back into corresponding objects.
* inherits from both `PowerConnectStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`).

[Back to Table of Contents](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
<a name = "common-classes"/>

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "implementation"/>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

--------------------------------------------------------------------------------------------------------------------
<a name = "delete-student/parent-feature"/>

## Delete student/parent feature

### Current Implementation
PowerConnect allows users to delete:
* Student from `UniqueStudentList` of `Class` using their class and index number.
* Parent from `UniqueParentList` of `parents` using their name and phone number.

However, a parent can only be deleted if no student is attached to that parent.

#### General Flow for deleting a student and the parent for that student

1. The user launches the application and wants to delete a student, `Bernice`, and her parent, `Bob`, who are already stored inside.
2. The user tries deleting the parent first using `parent delete n/Bob pnP/91234567`.
3. PowerConnect displays an error saying `"The parent you are trying to delete has a student attached! You can't delete the parent!"`.
4. The user then tries to delete that student using `student 1A delete in/1`.
5. `PowerConnectParser` and `StudentCommandParser` will check if command provided by the user is valid before `StudentDeleteCommand#execute(Model)` is called.
6. This would call `Model#deleteStudent(Student)` method to delete the student.
7. It will then check if the student to be deleted exists in `UniqueStudentList` of `Class` before deleting the student.
8. The deleted student will also be removed from its parent's list of children.
9. The success message and resulting list of students will be displayed via the dashboard.
10. The user tries deleting the parent again, it follows `step 5-7` in a similar way to just that it is now for parent instead of student.
11. After step 7, it will check that `Bob` has no students attached before deleting him and showing the success message.


**Sequence diagram for Parent Delete**
![Sequence Diagram](images/ParentDeleteSequentialDiagram.png)

**Sequence diagram for Student Delete**
![Sequence Diagram](images/StudentDeleteSequentialDiagram.png)

<div style="page-break-after: always;"></div>

### Design considerations

#### Aspect 1: How to delete students

* **Alternative 1 (current choice):** Delete student from their class's student list.
    * Pros: Provides flexibility in adding new features. e.g. deleting all students in a class
    * Cons: Harder to implement

* **Alternative 2:** Delete student from a student list containing all students.
    * Pros: Uses less memory
    * Cons: Time performance will be worse when there are a lot of students

#### Aspect 2: Whether parent can be deleted if there is a student attached

Current implementation allows a parent can have any number of students attached.

* **Alternative 1 (current choice):** Parent cannot be deleted if there is a student attached to ensure that every student always have one parent.
    * Pros: Easier to implement, since we do not have to update multiple students when a parent is deleted
    * Pros: Easier to add features that involve both a student and a parent

* **Alternative 2:** Parent can be deleted if there is a student attached.
    * Pros: Deletion of parent will be more flexible
    * Cons: Can be time-consuming to update if there are a lot of students attached to one parent

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "attendance-feature"/>

## Attendance feature

### Current Implementation

The attendance feature is facilitated by `Attendance`. It is composed by a `Person` with an `Attendance` object.

Given below is an example usage scenario and how the attendance mechanism behaves at each step:

Step 1. The user launches the application for the first time. <br><br>
Step 2. The user creates a student using the Add command. The `Attendance` of `Students` will be initialized with the initial attendance state (F) indicating that the student is absent.

[//]: # (![Student Add Command]&#40;images/StudentAddCommand.png&#41;)
Step 3. The user wants to mark a particular student as present. The user executes the `Attendance` command with the index of the student and att/T. The `Attendance` of the student will be updated to the current date.

Step 4. The attendance is saved to the storage file automatically after each command. Attendance is saved as the string representation of LocalDate in JsonAdaptedAttendance.


**Activity Diagram**
![Activity Diagram](images/AttendanceActivityDiagram.png)

**Full implementation sequence diagram**
![Sequence Diagram](images/AttendanceSequenceDiagram.png)

<div style="page-break-after: always;"></div>

### Design considerations
We want to make it easy for the user to set current date as present. Thus, we allowed the user to set attendance as T which will automatically register as present today.

#### Aspect: How to store attendance

* **Alternative 1 (current choice):** Store attendance as a JsonAdaptedAttendance object that records the string representation of LocalDate.
    * Pros: Easy to access and manipulate attendance data
    * Pros: Easy to read and write to storage
    * Pros: Flexible to add more features if more features are added e.g. mark as MC, Late..
    * Cons: Hard to implement

* **Alternative 2:** Store attendance as a string representation of LocalDate.
    * Pros: Easy to implement
    * Pros: Use less memory
    * Cons: Hard to access and manipulate attendance data
    * Cons: Hard to read and write to storage
    * Cons: Hard to add more features if more features are added e.g. mark as MC, Late..
<br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "grade-feature"/>

## Grade feature

### Current Implementation

The grade feature is facilitated by `grade`. It is composed by a `Student` with an `Assignment` object.

Given below is an example usage scenario and how the attendance mechanism behaves at each step:

Step 1. The user launches the application for the first time. <br><br>
Step 2. The user creates a student using the Add command. The `Test` and `Homework` which extends `Assignment` of `Students` will be initialized with the initial name: Insert student test/homework here!, Score: -100, Weightage: -100, Deadline: No Deadline and Is Done(Homework): false.

[//]: # (![Student Add Command]&#40;images/StudentAddCommand.png&#41;)
Step 3. The user wants to add a test to a student. The user executes the `grade` command with the index of the student and test/TEST_NAME. The `test` of the student will be updated to the name.

![Grade Command](images/Grade.png)

Step 4. The test/homework is saved to the storage file automatically after each command.

**Full implementation sequence diagram**

![Sequence Diagram](images/GradeSequentialDiagram.png)
![Sequence Diagram2](images/GradeSequentialDiagram2.png)

<div style="page-break-after: always;"></div>

### Design considerations
We want to make it easy for the user to set tests without inputting all the details at one go, with the test/homework name being the only field compulsory.

#### Aspect: How to store test

* **Alternative 1 (current choice):** Store test as a JsonAdaptedTest object.
    * Pros: Easy to access and manipulate attendance data
    * Pros: Easy to read and write to storage
    * Pros: Flexible to add more features if more features are added
    * Cons: Hard to implement

* **Alternative 2:** Store attendance as a string representation of Test details.
    * Pros: Easy to implement
    * Pros: Use less memory
    * Cons: Hard to access and manipulate attendance data
    * Cons: Hard to read and write to storage
    * Cons: Hard to add more features if more features are added
<br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "parentnok-add-feature"/>

## Parent/NOK Add Feature

### Current Implementation
The add feature for parent/NOK is facilitated by `parent add`.

Given below are example usage scenarios and how the add mechanism behaves at each step.<br><br>
Scenario 1:
1. User launches the application.
2. User wants to add a new `Parent / NOK`.
3. User keys in the parent add command to add a new `Parent / NOK` to PowerConnect.
4. PowerConnect creates a new `Parent / NOK` and automatically saves its data into its storage file (parents.json).

Scenario 2:
1. As user executes the `Student Add` command, PowerConnect cannot locate the `Parent / NOK` to bind to the `Student`.
2. PowerConnect hence **AUTOMATICALLY** creates a new `Parent / NOK`.
3. PowerConnect retrieves the information required for `Parent Add` command from `Student Add` command.
4. PowerConnect automatically saves all changes into its storage files (parents.json & pcclass.json). <br><br>

**Activity Diagram**
![Activity Diagram](images/ParentAddActivityDiagram.png)

**Full implementation sequence diagram**
![Sequential Diagram](images/ParentAddSequentialDiagram.png)

**Reference to get parent particulars sequence diagram**
![Sequence Diagram](images/ParentAddParserUtilSequentialDiagram.png)

<div style="page-break-after: always;"></div>

### Design considerations
We want to make it simple for users to add a new `Parent / NOK` to PowerConnect without having the need to include students attached to him/her. <br><br>
We also want to have a centralized way and adopt defensive coding practices for binding of `Student` and `Parent / NOK`. <br><br>
Furthermore, we do not want to trouble users by forcing them to have **ALL** particulars of a `Parent / NOK`. Hence, we made the command such that it **ONLY** requires minimal information from users: <br>
1. Parent's / NOK's `Name`
2. Parent's / NOK's `Phone number` <br><br>

#### Aspect: How Parent Add executes
* **Alternative 1 (current choice):** Users provide **COMPULSORY** details about the parent / NOK only + binding of student and parent / NOK is done at `Student` side **ONLY**.
    * Pro: Defensive coding is applied, reducing chances of bugs
    * Pro: Users are **NOT FORCED** to come up with arbitrary values to fill up **OPTIONAL** details for the parent / NOK to create a new `Parent / NOK`.
    * Con: Hard to implement
* **Alternative 2:** Users provide **ALL** particulars of the `Parent / NOK` + binding of student and parent / NOK is done at `Student` side **ONLY**.
    * Pro: Slightly easier to implement as compared to Alternative 1
    * Pro: Defensive coding is applied, reducing chances of bugs
    * Con: Users are **FORCED** to come up with arbitrary values to fill up any missing values for **OPTIONAL** section.
* **Alternative 3:** Users provide **ALL** particulars of the `Parent / NOK` + binding of student and parent / NOK are done at **BOTH** `Student` and `Parent / NOK`.
    * Pro: Slightly easier to implement as compared to Alternative 2
    * Con: Defensive coding is **NOT** applied, increasing chances of bugs
    * Con: Users are **FORCED** to come up with arbitrary values to fill up any missing values for **OPTIONAL** section.
    * Con: Need to test the feature exhaustively / create sufficient automated tests to ensure `Students` and `Parents / NOKs` are properly bound together.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "parentnok-edit-feature"/>

## Parent/NOK Edit Feature

### Current Implementation
The edit feature for parent / NOK is facilitated by `parent edit`. <br>

Given below is an example usage scenario and how the edit mechanism behaves at each step.
<br>
1. User launches the application for the first time. <br>
2. User creates a new `Parent` using ***Parent Add Command*** or gets a new `Parent` created by PowerConnect while executing the ***Student Add Command***. <br>
3. During creation of `Parent`, User encountered one of the two scenarios and requires to edit the `Parent`:
   1. Did not include certain particulars of the `Parent`.
   2. Keyed in wrong particulars for the `Parent`.
4. User wants to edit the `Parent` particulars and hence executes the `parent edit` command with the `Name` and `Phone Number` of the corresponding `Parent` and the `Particulars` to be edited.<br>
   Example of parent edit command: `"parent edit n/Tan Ah Niu pnP/91234567 npnP/65656565"` <br>
5. Edited `Parent` is saved to the storage file automatically after the command. <br><br>

**Activity Diagram**
![Activity Diagram](images/ParentEditActivityDiagram.png)

**Full implementation sequence diagram**
![Sequence Diagram](images/ParentEditSequentialDiagram.png)

**Reference to get parent particulars sequence diagram**
![Sequence Diagram](images/ParentEditParserUtilSequentialDiagram.png)

<div style="page-break-after: always;"></div>

### Design considerations
We want to make it easy for users to edit `Parent / NOK` particulars without manually deleting the `Parent / NOK` and creating a new `Parent / NOK` and reassigning each `Student` attached to original `Parent / NOK` with the new `Parent / NOK`.
<br><br>
We also do not want to trouble user with inputting multiple **PREFIXES** to edit `Parent / NOK`. Hence, users only need to input ***PREFIXES*** belonging to the edited information and the following:
1. Parent's / NOK's `Name`
2. Parent's / NOK's `Phone Number`
3. Particulars that are being amended<br><br>

#### Aspect: How Parent Edit executes
* **Alternative 1 (current choice):** Run the command with `parent edit n/<NAME> pnP/<PARENT_PHONE_NUMBER>` + PREFIXES and details of information that are being changed.
    * Pro: Easy to use
    * Pro: Minimal input by user to edit `Parent / NOK` particulars
    * Con: Slightly harder than Alternative 2 to implement
    * Con: Need to test the ***Parent Edit Command*** exhaustively / come up with automated tests to ensure when creating the new `Parent / NOK`, system takes input from original `Parent / NOK` for particulars that are not amended.
* **Alternative 2:** User provides **ALL** particulars of the `Parent / NOK` to be edited even if **SOME** particulars are the **SAME** as original. From these details, system will create a new `Parent / NOK` and replace the original copy of `Parent / NOK` with it.
    * Pro: Easy to implement
    * Pro: Makes use of existing features **Parent Add** and **Parent Delete**
    * Con: Troublesome for the user as there is a need to input **ALL** particulars of a `Parent / NOK` even if he/she is just amending one of the particulars of the `Parent / NOK`.
    * Con: It may cause regressions, user may key in wrong details for some of the `Parent / NOK` particulars as he/she is keying in the command. This will cause much inconvenience for Users especially when the `Parent / NOK` they are amending has **TONS** of information.
    * Con: It is no longer **FAST** and **EASY** for the user to use.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "binding-student-and-parentnok-feature"/>

## Binding Student and Parent/NOK Feature

### Current Implementation
The binding feature is created with the consideration of defensive coding in mind. It is executed by PowerConnect when the following commands are executed: `Student Add`, `Student Edit`, `Student Delete`, `Parent Edit` commands.

Given below is an example usage scenario and how the edit mechanism behaves at each step.
<br>
1. User executes `Student Edit` command and decides to change the parent's/NOK's `Name`.
2. As PowerConnect executes the `Student Edit` command, PowerConnect will also retrieve the corresponding `parent/NOK` and perform similar behaviors to `Parent Edit` command to update the `parent/NOK` info in PowerConnect's database.
3. PowerConnect will also perform similar behaviors of `Student Edit` command to edit the `parent/NOK` information for all `Students` that are bound to the `Parent/NOK`.

### Design considerations
We wanted to adopt defensive coding for this feature to reduce any unwanted bugs. It is also complex when determining which party (students or parents/NOKs) should be the one performing the binding and unbinding and whether to allow both parties to perform.

#### Aspect: How Parent Edit executes
* **Alternative 1 (current choice):** Perform the binding and unbinding **MAINLY** on the `Student` end as the main concern of this binding is to allow users to know the parent/NOK of a particular student.
    * Pro: Logical and solves the issue of pairing `students` and `parents/NOKs`.
    * Pro: Defensive coding used.
    * Pro: Less likely to cause bugs as compared to other Alternatives.
    * Con: Difficult to implement.
* **Alternative 2:** Perform the binding and unbinding on the `Parent/NOK` end when commands pertaining to `Parents/NOKs` are executed.
    * Pro: Less information required for `Student Add` command.
    * Con: More information required for `Parent Add` command, resulting in requiring a long input from users.
    * Con: Troublesome for users.
    * Con: More likely to cause bugs as compared to Alternative 1.
    * Con: Difficult to implement.
* **Alternative 3:** Perform the binding and unbinding at **BOTH** `Student` and `Parent/NOK` commands.
    * Pro: Easy to implement.
    * Con: Most likely to cause bugs among the Alternatives.
    * Con: Did not adopt Defensive Coding.
    * Con: Requires exhaustive testing and automated tests to reduce bugs.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "planned-enhancements"/>

## Planned Enhancements

<a name = "enhancement-1-name-fix"/>

#### Enhancement 1: Name Fix
Bug Report: [PE-D][Tester B] Edit Student: name can be numbers, Issue #179.

Feature Flaw: User is able to input numbers into `Student`'s `Name` and `Parent/NOK`'s `Name`.

Fix: Update `Name.java` file's line 19 code such that it will **ONLY** accept alphabets and spacing.
```
From:
public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

To:
public static final String VALIDATION_REGEX = "[\\p{Alpha}][\\p{Alpha} ]*";
```
Note: It is perhaps possible for a person to have a name with numbers or symbols in the future. This can be seen in the case where Elon Musk tried naming his child to be [X Æ A-12](https://pagesix.com/article/elon-musk-children/).

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "enhancement-2-age-fix"/>

#### Enhancement 2: Age Fix
Bug Report: [PE-D][Tester B] Edit student: age can be 0 or any extreme value, Issue #178.

Feature Flaw: User is able to input `Age` values like `0` or `999` when logically speaking, there wouldn't be anyone with those `Age` values. Current implementation is self-reliant on users to provide **APPROPRIATE** values.

Fix: Update `Age.java` file such that it **DOES NOT** allow values like `0` and has a **SOFT CAP** of 200 as current longest lifespan of humans is only 122 years. Even after **50 years**, there shouldn't be anyone who **EXCEEDS** this soft cap value.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "enhancement-3-attendance-fix"/>

#### Enhancement 3: Attendance Fix
Bug Report: [PE-D][Tester C] Unclear options for marking students as absent, Issues #174, #166, #162.

Feature Flaw: User is unable to set `Attendance` of a `Student` to be `Absent` once he/she is marked as `Present.

Fix: Update `Attendance` feature such that it allows users to set it to `Absent` and other statuses set by users, such as `Late`, `Left Home Early`, `On medical leave` and reflects the correct displaying message as well in future releases.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "documentation-logging-testing-configuration-dev-ops"/>

## **Documentation, Logging, Testing, Configuration, Dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "appendix-requirements"/>

## **Appendix: Requirements**

<a name = "product-scope"/>

### Product Scope

**Target user profile**:

* This product mainly targets tuition teachers and in the future, secondary school teachers that prefer CLI to GUI,  manage large groups of students and require ease of access to students' information.

**Value proposition**: <br>
Provides teachers with the ability to manage students administration and academics efficiently by operating on CLI, to complement existing LMS.
* Our product uses CLI instead of GUI.
* Simple application for teachers to keep track of many students in different classes / co-curricular activities (CCAs) that have different phone numbers.
* Our application can be used individually without internet connection (currently supports single user usage ONLY).
* Helps to keep track of students’ parents / next-of-kins’ contact information for emergency purposes.
* Teachers may not be able to match students names and faces well and so this app serves as an easy way to identify students (since there are too many students).
* Streamline administration processes because there’s plenty of students’ information to keep track of (e.g. attendance, assignments, grades, contact details).

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "user-stories"/>

### User Stories

| No  | As a...                     | I can...                                                                    | So that...                                                                                                                                 | Notes                                                                                                                                     | Priority |
|-----|-----------------------------|-----------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|----------|
| 1   | Teacher                     | Record student’s attendance                                                 | I know that my student is present in class (in school).                                                                                    |                                                                                                                                           | High     |
| 2   | Teacher                     | Record students’ assessment and test grades.                                | I am able to manage my students’ performance.                                                                                              |                                                                                                                                           | High     |
| 3   | Teacher                     | Have an efficient way to view and track my students’ attendance.            | I will have an easier time managing my students’ attendance over the year.                                                                 |                                                                                                                                           | High     |
| 4   | Teacher                     | Have the option to leave some details empty                                 | I am able to key in my students’ information when there’s missing information.                                                             |                                                                                                                                           | High     |
| 5   | Teacher                     | Document students’ progress                                                 | I am able to record easily the students’ performance.                                                                                      |                                                                                                                                           | High     |
| 6   | Teacher                     | Organise my students by class                                               | I am able to identify which class they belong to.                                                                                          | Determine whether to create separate classes: “class” class and “student” class or merge                                                  | High     |
| 7   | Non tech-savvy teacher      | Learn about new commands                                                    | I am able to use the application effectively and save unnecessary time and effort in recording students’ particulars and searching for it. | Provide User Guide that is easy to understand                                                                                             | High     |
| 8   | Teacher                     | Recognise my students from different classes through their photos           | I am able to recognise my students' looks.                                                                                                 | Long setup process for user as they need to manually input images for individual student<p></p> Keeping separate file / folder for images | Medium   |
| 9   | Caring Teacher              | Provide comments for each student                                           | I am able to keep track of students who need meaningful feedback and support.                                                              | (comments section on each student)                                                                                                        | Medium   |
| 10  | Teacher / teacher assistant | Have an efficient way to retrieve my students’ contact details.             | I am able to contact them easily.                                                                                                          | Assumption: student details are known to teachers / TAs                                                                                   | Medium   |
| 11  | Teacher                     | Keep track of students who have submitted assignment                        | I am aware of which student missed the deadline for their work.                                                                            |                                                                                                                                           | Medium   |
| 12  | Motivational teacher        | Sort my students by overall grades                                          | I am able to foster a positive and supportive learning environment.                                                                        |                                                                                                                                           | Medium   |
| 13  | Teacher                     | Use an application that is easy to navigate about and has a nice interface. | I am able to save unnecessary time and effort in recording students’ particulars and searching for it.                                     | Issue under designs                                                                                                                       | Low      |
| 14  | Responsible Teacher         | Access student records, such as grades and attendance                       | I will be able to make informed decisions about student progress.                                                                          | Summary of all students grades for a particular test / class                                                                              | Low      |
| 15  | Busy teacher                | Have a todo list                                                            | I am able to keep track of what to do.                                                                                                     |                                                                                                                                           | Low / NA |
| 16  | Teacher                     | Use an application with a reminder system                                   | I am able to track the things that needs to be done.                                                                                       |                                                                                                                                           | NA       |
| 17  | Teacher                     | Efficiently and effectively assign assessments and assignments              | I am able to better assess my students’ learning and progress.                                                                             |                                                                                                                                           | NA       |
| 18  | Teacher                     | Managing students’ behaviours and actions                                   | I am able to promote a safe and productive learning space for all.                                                                         |                                                                                                                                           | NA       |
| 19  | Teacher                     | Add individual feedback to students                                         | I am able to provide feedback to parents/guardians effectively.                                                                            | Duplicate point to Point 7                                                                                                                | NA       |
| 20  | Teacher                     | Access my students contacts conveniently                                    | I am able to build a closer relationship with my students and their next of kin.                                                           | Too vague                                                                                                                                 | NA       |
| 21  | Busy Teacher                | Keep track of my teaching feedback                                          | I can keep improving my teaching practices.                                                                                                | Hard to get school admin team on board                                                                                                    | NA       |
| 22  | Efficient Teacher           | Integrate technology into my teaching                                       | I am able to enrich my student learning.                                                                                                   | Vague                                                                                                                                     | NA       |
| 23  | Teacher assistant           | Retrieve my students contact                                                | I am able to look them up easily.                                                                                                          | Duplicate Point to Point 10                                                                                                               | NA       |
| 24  | Teacher                     | Track the date and time of my classes                                       | I will be able to reach my classes on time.                                                                                                |                                                                                                                                           | NA       |
| 25  | Forgetful teacher           | Record attendance                                                           | I am able to keep track of my students.                                                                                                    | Duplicate to Point 5                                                                                                                      | NA       |
| 26  | Teacher                     | Identify weaker students                                                    | I can give them more attention in class.                                                                                                   | Duplicate to Point 20                                                                                                                     | NA       |
| 27  | Teacher                     | Manage my consultation timings                                              | I am able to organise my schedule.                                                                                                         |                                                                                                                                           | NA       |
| 28  | Teacher                     | Set reminders for my consultation timings                                   | I am able to ensure I am not late for consultations.                                                                                       |                                                                                                                                           | NA       |
| 29  | Teacher                     | Marks my students’ assignments automatically                                | I am able to use time and effort spent on it on other use, such as thinking of ways to improve my students’ learning.                      | Difficult to implement autograder                                                                                                         | NA       |
| 30  | Teacher                     | Have an application that is used as CLI instead of GUI                      | I am able to make use of my fast typing speed to sort out all the administrative work.                                                     | Too Vague                                                                                                                                 | NA       |
| 31  | Busy Teacher                | Easily manage my schedule                                                   | I am able to spend more time on other things.                                                                                              | Out of scope                                                                                                                              | NA       |
| 32  | Busy Teacher                | Know which tasks to prioritise                                              | I am able to ensure all of my responsibilities are taken care of in a timely and efficient manner.                                         |                                                                                                                                           | NA       |
| 33  | Teacher                     | Track the date and time of the classes that I have                          | I am able to reach on time and teach the correct module for the particular lesson slot.                                                    | Out of scope                                                                                                                              | NA       |
| 34  | Teacher                     | Amend date and time of certain lesson timings                               | I am able to change lesson dates and timings easily when lessons shift.                                                                    |                                                                                                                                           | NA       |

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-cases"/>

### Use Cases

***For all use cases below, the System is PowerConnect and the Actor is the teacher, unless specified otherwise.***

<a name = "use-case-uc01---adding-a-new-student-to-an-existing-class"/>

#### Use Case: UC01 - Adding a new `student` to an existing `class`.

**Preconditions:** User knows the `index number` for the `student` and the `class` the student belongs to have already been created.

**MSS:**
1. User keys in **ALL COMPULSORY** details and any other **OPTIONAL** details for student’s particulars.
2. System creates and adds student to respective `class` and indicates success.
   System creates and binds `parent` to the student if parent does not exist. <br>

**Extensions:**

+ 1a. User did not enter **ALL COMPULSORY** details.
    + 1a1. System displays an error message to the user indicating that there is insufficient information given to create the new `student` and provides an example of what to include. <br>
      Use case ends. <br><br>
+ 1b. User keys in invalid **COMPULSORY** or **OPTIONAL** information.
    + 1b1. User keys in invalid `SEX` type not supported by system. (m/f/M/F)
    + 1b2. User keys in `age` or `phone number` that are not of an integer.
    + 1b3. User keys in invalid path to `image`.
    + 1b4. System displays an error message to the user indicating that he/she has keyed in wrong information for the `student` along with a sample of the correct way to key in information for a new `student`. <br>
      Use case ends. <br><br>
+ 2a. User is trying to create a new `student` whose index number belongs to an existing `student` in the class.
    + 2a1. System displays an error message to the user indicating that a student with the same index number already exists in the class. <br>
      Use case ends.<br><br>
+ 2b. Parent already exists.
    + 2b1. System locates and binds the parent to the student. <br>
      Use case ends.<br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc02---adding-grade-for-a-student"/>

#### Use Case: UC02 - Adding `grade` for a `student`.

**Preconditions:** User knows `index number` and `class` of the `student` he/she wishes add `grade` for.

**MSS:**
1. User keys in the `test` name, `class`, `index number` of student and corresponding `grade`.
2. System displays feedback to the user that grade has been successfully added for the student. <br>
   Use case ends.

**Extensions:**

+ 1a. User keyed in an invalid `index number` or `class`.
    + 1a1. System displays an error message indicating the student does not exist.
    + 1a2. User checks for the index number and class of the student via the student list displayed on the application and keys in the correct value.<br>
      Use case ends.<br><br>
+ 1b. Test name is duplicate.
    + 1b1. System displays an error message indicating duplicated test name.
    + 1b2. User adds a new test name.
    + Use case resumes at step 2.<br><br>
+ 1c. User gave a `Grade` value that is not of an integer.
    + 1c1. System displays an error message indicating the wrong grade given.
    + 1c2. User checks and adds the grade input again. <br>
      Use case resumes at step 2.<br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc03---adding-comment-for-a-student"/>

#### Use Case: UC03 - Adding `comment` for a `student`.

**Preconditions:** User knows `index number` and `class` of the `student` he/she wishes to add `comment`.

**MSS:** Similar to UC02
1. User keys in the `index number` and `class` of student and the corresponding `comment` for the student.
2. System adds the comment for the student and indicates success. <br>
   Use case ends.

**Extensions:** Similar to UC02

+ 1a. User keyed in invalid `index number` or `class`.
    + 1a1. System displays an error message indicating the student does not exist.
    + 1a2. User checks for the index number and class of the student via the student list displayed on the application and keys in the correct value.<br>
      Use case ends. <br><br>

+ 2a. There is an existing comment for the student.
    + 2a1. System overrides the existing comment with the new comment. <br>
      Use case ends. <br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc04---listing-all-students-in-the-selected-class"/>

#### Use Case: UC04 - Listing all `students` in the selected `class`.

**MSS:**
1. User keys in the command to view the list of all `students` in the selected `class`.
2. System displays all `students` particulars in the `class`.<br>
   Use case ends.

**Extensions:**<br>
+ 1a. User keys in an invalid `class`.
    + 1a1. System displays an error message to the user informing him/her the `class` is invalid.<br>
      Use case ends.<br><br>
+ 2a. Selected `class` does not have any `students`.
    + 2a1. System displays an empty list.<br>
      Use case ends.<br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc05---finding-a-student-in-a-class"/>

#### Use Case: UC05 - Finding a `student` in a class.

**Preconditions:** User knows the `class` of the `student` he/she wishes to locate.

**MSS:**
1. User keys in a section of student’s name (e.g. Mary Goh - `Mary`) along with the `class` where the user wishes to find from.
2. System finds matching students and displays it to user.<br>
   Use case ends.

**Extensions:**
+ 1a. User did not enter **ALL COMPULSORY** details.
    + 1a1. System displays an error message to the user indicating that there is insufficient information given to create the new `student` and provides an example of what to include. <br>
      Use case ends. <br><br>
+ 2a. Student does not exist in the specified `class`.
    + 2a1. System displays an empty list.<br>
      Use case ends. <br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc06---deleting-student-from-class"/>

#### Use Case: UC06 - Deleting `student` from class.

**Preconditions**: User knows the `index number` for the `student` and the `class` the student belongs to have already been created.

**MSS:**
1. User keys in the student `index number` and `class`.
2. System deletes the student from that class and indicates success.<br>
   Use case ends.

**Extensions:**
+ 1a. User keyed in invalid `index number` or `class`.
    + 1a1. System displays an error message indicating the student does not exist.
    + 1a2. User checks for the index number and class of the student via the student list displayed on the application and keys in the correct value.<br>
      Use case ends.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc07---adding-a-new-parentnok-to-the-system"/>

#### Use Case: UC07 - Adding a new `parent/NOK` to the system.

**MSS:** Similar to UC01
1. User keys in **ALL COMPULSORY** details and any other **OPTIONAL** details for parent’s / NOK's particulars.
2. System creates parent/NOK and adds its data into system's storage and indicates success to user. <br>
   Use case ends.

**Extensions:**

+ 1a. User did not enter **ALL COMPULSORY** details.
    + 1a1. System displays an error message to the user indicating that there is insufficient information given to create the new `parent/NOK` and provides an example of what to include. <br>
      Use case ends. <br><br>
+ 1b. User keys in invalid **COMPULSORY** or **OPTIONAL** information.
    + 1b1. User keys in invalid `SEX` type not supported by system. (m/f/M/F)
    + 1b2. User keys in `age` or `phone number` that are not of an integer.
    + 1b3. User keys in invalid path to `image`.
    + 1b4. System displays an error message to the user indicating that he/she has keyed in wrong information for the `parent/NOK` along with a sample of the correct way to key in information for a new `parent/NOK`. <br>
      Use case ends. <br><br>
+ 2a. User is trying to create a new `parent/NOK` whose `phone number` belongs to an existing `parent/NOK` in the system.
    + 2a1. System displays an error message to the user indicating that a `parent/NOK` with the same `phone number` already exists in the system. <br>
      Use case ends.<br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc08---editing-an-existing-parentnok-in-the-system"/>

#### Use Case: UC08 - Editing an existing `parent/NOK` in the system.

**MSS:**
1. User keys in parent’s / NOK's name and phone number along with the details that are being amended.
2. System retrieves the original parent/NOK, creates a **NEW** parent/NOK with the amended details and original particulars. At the same time, system will automatically unbind and bind all students bound to **ORIGINAL** parent/NOK and bind them accordingly to the **NEW** parent/NOK.
3. System replaces the ***original*** parent/NOK with the ***new*** parent/NOK.
4. System proceeds to indicate success to user. <br>
   Use case ends.

**Extensions:**
+ 1a. User keyed in invalid `phone number` or `name` of the parent/NOK to be edited.
    + 1a1. System displays an error message indicating the parent does not exist.
    + 1a2. User checks for the `phone number` and `name` of the parent/NOK via the parent/NOK list displayed on the application and keys in the correct value.<br>
      Use case ends.

+ 1b. User keys in invalid **COMPULSORY** or **OPTIONAL** information.
    + 1b1. User keys in invalid `SEX` type not supported by system. (m/f/M/F)
    + 1b2. User keys in `age` or `phone number` that are not of an integer.
    + 1b3. User keys in invalid path to `image`.
    + 1b4. System displays an error message to the user indicating that he/she has keyed in wrong information for the `parent/NOK` along with a sample of the correct way to key in information for a new `parent/NOK`. <br>
      Use case ends. <br><br>
+ 2a. User is trying to edit the `parent's/NOK's` `phone number` to an existing `parent/NOK` in the system.
    + 2a1. System displays an error message to the user indicating that a `parent/NOK` with the same `phone number` already exists in the system. <br>
      Use case ends.<br><br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "use-case-uc09---deleting-a-parentnok-from-the-system"/>

#### Use Case: UC09 - Deleting a `parent/NOK` from the system.

**MSS:**
1. User keys in parent’s / NOK's `name` and `phone number`.
2. System locates the parent / NOK with matching `name` and `phone number` and removes him/her from the system.
3. System proceeds to indicate success to user along with deleted `parent/NOK` details. <br>
   Use case ends.

**Extensions:**
+ 1a. User keyed in invalid `phone number` or `name` of the parent/NOK to be deleted.
    + 1a1. System displays an error message indicating the parent does not exist.
    + 1a2. User checks for the `phone number` and `name` of the parent/NOK via the parent/NOK list displayed on the application and keys in the correct value.<br>
      Use case ends.

+ 2a. User is trying to delete a `parent/NOK` that currently has students attached.
    + 2a1. System displays an error message to the user indicating that the `parent/NOK` currently has students attached to him/her and hence **CANNOT** be removed from the system. <br>
      Use case ends.<br><br>

*{More to be added}* <br></br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "non-functional-requirements"/>

## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java `11` or above installed.
2. Should be able to handle up to 400 students without a noticeable sluggishness in performance for typical usage.
3. Should package it as a JAR file that is smaller than 50MB.
4. Should store data in a txt/csv file.
5. PowerConnect should be able to work without any internet access.
6. PowerConnect should only be used by a single user per installation and not by multiple users.
7. Users with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish the majority of the tasks faster using CLI commands than using the mouse.
8. PowerConnect should be able to handle all user input errors gracefully.
9. PowerConnect should be able to display all error messages in a user-friendly manner.
10. PowerConnect should be able to run on a 32-bit system with 8GB of RAM.
11. PowerConnect should be able to display all success messages within 1 second.

*{More to be added}* <br></br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "glossary"/>

## Glossary

**Attributes**: Information of a student / parent. <br>
For example, name, phone number, email address etc <br><br>
**CCA**: Co-curricular activities. <br><br>
**CLI**: Command Line Interface. <br><br>
**Hard disk**: Non-volatile data storage device, your OS's storage in short. <br><br>
**LMS**: Learning Management System, application used by schools to provide students a platform to access their lessons materials online. <br>
Examples of LMS: Canvas, LumiNUS, Blackboard, Google Classroom, Quizlet <br><br>
**NOK**: Refers to Next-of-Kin, could be either blood related or a guardian. <br><br>
**OS**: Operating Systems. <br>
Examples of OS: Windows, Linux, Unix, OS-X etc <br><br>
**Parameters**: The actual information of a student/parent. <br>
For example, Tan Ah Kow, 91234567 etc. <br><br>

*{More to be added}*

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "appendix-instructions-for-manual-testing"/>

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

<a name = "launch-and-shutdown"/>

### Launch and shutdown

1. Initial launch
   1. Ensure you have Java `11` or above installed in your Computer.
   2. Download the latest `PowerConnect.jar` from [here](https://github.com/AY2223S2-CS2103T-T09-1/tp/releases).
   3. Copy the file to the folder you want to use as the _home folder_ for PowerConnect.
   4. In the same folder, create a new folder named “image” and store all images to be used for the program in this folder. e.g. student image and parent image. For the student image, it should be named `<STUDENT_NAME><CLASS><INDEX NUMBER>.png`. For the parent image, it should be named `<PARENT_NAME>.png`.
   5. Double-click the jar file.
      <br> **OR** <br>
      Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar PowerConnect.jar` command to run the application.
   6. A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data. The window size may not be optimum.<br>
      ![Ui](images/Ui.png)
   <br><br>

<a name = "addingstudent"/>

2. Adding a student
   1. Prerequisites: There should be no student with the same class and index number as the student you are adding. Same name is allowed. List all the students with `list student` command.
   2. Test case 1: `student 1A add n/TanAhCow in/03 pn/TanAhNiu pnP/91234567 rls/Father`. <br>
      Expected: A student is added and displayed in the student list and a parent is added to the Parents/Next Of Kins(NOK) list.
   3. Test case 2: `student 1A add n/Tan Ah Cow in/13 s/M pn/Tan Ah Niu pnP/91234567 rls/Father a/Blk 456 Ang Mo Kio Avenue 6 #11-800 S(560456) ageS/14 eS/tanahcow@gmail.com pnS/91234567 cca/Captain Ball att/T`. <br>
      Expected: A student with more optional details is added and displayed in the student list and a parent is added to the Parents/Next Of Kins(NOK) list.
   <br><br>
<a name = "markingattendance"/>

3. Marking attendance
   1. Prerequisites: The date of attendance should be known, or you can mark it as T/F (True/False) for today's date. List all the students with `list student` command.
   2. Test case 1: `student 3A attendance in/25 att/T`. <br>
      Expected: The attendance of the student is marked as T (present) for today's date.
   3. Test case 2: `student 3A attendance in/25 att/25/02/2023`. <br>
      Expected: The attendance of the student is marked as F if today's date is not 25/02/2023. PowerConnect V1.4 only supports display of today's date for attendance.
   <br><br>
<a name = "addinggrade"/>

4. Adding grade (homework/test) for student
   1. Prerequisites: The test should not have duplicate names and must be known. List all the students with `list student` command.
   2. Test case 1: `student 1A grade in/13 test/CA1 score/75 weightage/10`. <br>
      Expected: A test named CA1, 75 score and 10 weightage is added to the student with index number 13.
   3. Test case 2: `student 1A grade in/13 hw/Chapter 1 score/75 deadline/25/04/2023 weightage/10 hwdone/true`. <br>
      Expected: A homework named Chapter 1, 75 score, 10 weightage and deadline of 25/04/2023 is added to the student with index number 13.
   <br><br>
<a name = "deletinggrade"/>

5. Deleting a grade (homework/test) for student
   1. Prerequisites: You know the deleted grade name and the student index number.
   2. Test case 1: `student 1A gradedelete in/13 hw/Chapter 1`. <br>
      Expected: The homework named Chapter 1 is deleted from the student with index number 13.
   <br><br>
<a name = "addingcomment"/>

6. Adding comments for a student
   1. Prerequisites: You know the student index number. List all the students with `list student` command.
   2. Test case 1: `student 1A comment in/25 com/Quiet person, needs to interact more with classmates`. <br>
      Expected: The comment is added to the student with index number 25.
   <br><br>
<a name = "editingstudent"/>

7. Edit a student
   1. Prerequisites: List all the students with `list student` command.
   2. Test case 1: `student 1A edit in/3 cca/basketball`. <br>
      Expected: The student with index number 3 will have the cca changed to basketball.
   <br><br>
<a name = "listingstudentbyclass"/>

8. Listing all students in a specified class
   1. Prerequisites: List all the students with `list student` command.
   2. Test case 1: `student 1A list`. <br>
      Expected: All students in class 1A will be listed.
   <br><br>
<a name = 'findingstudent'/>
   
9. Listing all students
   1. Test case 1: `list student`. <br>
      Expected: All students will be listed.
   <br><br>
 <a name = "listingallstudents"/>

10. Finding a student
    1. Prerequisites: List all the students with `list student` command.
    2. Test case 1: `student 1A find TanAhCow`. <br>
       Expected: All students with name TanAhCow in class 1A will be listed.
    <br><br>
<a name = "deletingstudent"/>
    
11. Deleting a student
    1. Prerequisites: List all the students with `list student` command.
    2. Test case 1: `student 1A delete in/3`. <br>
       Expected: The student with index number 3 will be deleted.
    <br><br>
<a name = "addingparent"/>

12. Adding a parent
    1. Prerequisites: List all the parents with `list parent` command. Parents can have the same name but phone numbers must be unique!
    2. Test case 1: `parent add n/TanAhNiu pnP/91234567`. <br>
       Expected: A parent is added and displayed in the parent list.
    <br><br>
<a name = "listingparent"/>

13. Listing all parents
    1. Test case 1: `list parent`. <br>
       Expected: All parents will be listed.
    <br><br>
<a name = "editingparent"/>

14. Editing a parent
    1. Prerequisites: List all the parents with `list parent` command.
    2. Test case 1: `parent edit n/TanAhNiu pnP/91234567 npnP/65656565`. <br>
       Expected: The parent with name TanAhNiu will have the phone number changed to 65656565.
    <br><br>
<a name = "findingparent"/>

15. Finding a parent
    1. Prerequisites: List all the parents with `list parent` command.
    2. Test case 1: `parent find TanAhNiu`. <br>
       Expected: All parents with name TanAhNiu will be listed.
    <br><br>
<a name = "deletingparent"/>

16. Deleting a parent
    1. Prerequisites: List all the parents with `list parent` command.
    2. Test case 1: `parent delete n/TanAhCow pnP/9123456`. <br>
       Expected: The parent with name TanAhCow and phone number 9123456 will be deleted.
    3. Note: You CANNOT delete the parent/NOK if the parent/NOK has students BOUND to him/her. System will display an error message for this. Delete the student using student delete command first.
    <br><br>
<a name = "savingwindow"/>

17. Saving window preferences
     1. Resize the window to an optimum size. Move the window to a different location. Close the window.
     2. Re-launch the app by double-clicking the jar file.<br>
        Expected: The most recent window size and location is retained.
     <br><br>
<a name = "shutdown"/>

18. Shutdown of Application
     1. Run the `exit` command: `exit` or press the `X` button located in the top right corner of the application.
     2. The application should close within 3 seconds.
     <br><br>

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** In the case, PowerConnect does not boot up with a similar UI display as the image above or PowerConnect is **NOT RESPONDING**, please kindly remove all files related and download the `jar` file again.
</div>

<div markdown="span" class="alert alert-info">:information_source: **CAUTION:** <br>
Testers **AVOID** terminating the application abruptly via **Task Manager**, closing the application process or any other methods that is **NOT** mentioned in the section above on Shutdown!

</div>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "saving-data"/>

### Saving data

**1. Dealing with corrupted files** <br><br>
Testers usually get corrupted files when they perform one of the **FOLLOWING** scenarios:
   1. Manually editing the storage files - `parents.json` and `pcclass.json`
   2. Stop the application abruptly without entering the `Exit` command. <br>

**Possible Fix**: <br>
Unfortunately there is no **FIX** for this issue at the moment. Tester will need to manually key in or input the data file again as PowerConnect will erase the data files (`parents.json` & `pcclass.json`) upon **NEXT** valid command execution.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** Always duplicate the data files being input into PowerConnect for testing for backup copies in case of file corruption.
</div>

<div markdown="span" class="alert alert-info">:information_source: **CAUTION:** <br>
Testers should **AVOID** manually amending the storage files **UNLESS** there is an error in the `edit` commands **AND** they are sure of how to amend the storage files!

</div>

<br>

**2. Dealing with missing files** <br><br>
Testers usually get missing files when they perform one of the **FOLLOWING** scenarios:
1. Changing the files / folders names (eg changing `parents.json`, `pcclass.json`, `/images/`, images names)
2. Changing the files / folders location

**Possible Fix:** <br>
Testers can attempt to manually locate the files and folders and ensure they are named correctly as per PowerConnect's requirements.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** <br>
Follow PowerConnect Requirements:
1. Images should be stored as `.png` format and inside `images/student` for students or `images/parent` for parents.
2. Storages files should be named as `parents.json` & `pcclass.json`.
</div>

<div markdown="span" class="alert alert-info">:information_source: **CAUTION:** <br>
Testers **SHOULD NOT** change directory name for `images/`, **ANY CHANGE** to image renaming should also be updated via the `edit` commands and storage files (`parents.json & `pcclass.json`) **SHOULD NOT** be renamed.

</div>

<br>

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
<a name = "effort" />

### Appendix: Effort
The hardest part of the project was modifying the whole storage class to fit our model correctly. As we created both
students and parents that inherited from persons, the AB3 model storage could not fit our model correctly. As such, we had
to overhaul the whole storage package, and we also supported class functionality for students which made things even harder.<br>

We also made linkages between parents and students to better model the relationship between students and parents. This was
quite time-consuming as we had to make sure each command we implemented adjusted this relationship accordingly.

The UI package also took us quite a while as we were not really familiar with JavaFX, and we had to make sure the information
displays correctly especially with expanding number of information (tests/homework).

The novelty of giving users both the option to specify an image path or use the images folder method to store images for students
or parents also took us some time as we were unsure to hard code the location or not.

[Back to Table of Contents](#table-of-contents)

<div style="page-break-after: always;"></div>

--------------------------------------------------------------------------------------------------------------------
