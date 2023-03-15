# PowerConnect User Guide

PowerConnect is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PowerConnect can get your contact management tasks done faster than traditional GUI apps.

##### Table of Contents
1. [Quick Start](#quickstart)
2. [Features](#features)
    1. [Student Features](#student)
        1. [Add student: `add`](#addstudent)
        2. [Add students' grade: `grade`](#gradestudent)
        3. [Add comments to students: `comment`](#commentstudent)
        4. [Listing all students in a particular class: `list`](#liststudent)
        5. [Editing a student's particulars: `edit`](#editstudent)
        6. [Searching students: `find`](#findstudent)
        7. [Deleting a student: `delete`](#deletestudent)
    2. [Parent Features](#parent)
        1. [Add parent/guardian: `add`](#addparent)
        2. [Listing all parents: `list`](#listparent)
        3. [Delete a parent/ parent information: `delete`](#deleteparent)
3. [Viewing help: `help`](#help)
4. [Exiting program: `exit`](#exit)
5. [FAQ](#faq)
6. [Command Summary](#summary)

<a name="quickstart"/>
--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `powerconnect.jar` from [here]().

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

    * `delete 3` : Deletes the 3rd contact shown in the current list.

    * `clear` : Deletes all contacts.

    * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

   <a name="features"/>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Command lines supplied by the user are not case sensitive as the application will auto translate it into `UPPER_CASE`. <br>

  E.g. in the list feature, user can call it via either methods:
     1. student 5A list
     2. STUDENT 5A LIST
     3. Student 5A LiSt


* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `…add <NAME>...`, `NAME` is a parameter which can be used as `…add John…`.


* Items in `<>` brackets are the values that the user should field
  e.g. in `…add <NAME>…` , `NAME` is a parameter that needs to be included, can be used as `…add John…`.


* In the case of **COMPULSORY** parameters, there’s no need to type `XX/YY` where `XX` is the particular category and `YY` is the actual information for the `XX` category. <br>


* Compulsory parameters are not bounded by square brackets <br>


* All items in the square brackets are OPTIONAL.

  e.g. in `…add…[..c/<CCA>.. ]...` , `CCA` is an optional parameter that need not be given by the user and can be skipped, can be used as `…add…`  or `…add…c/Mathematics Club nok/…` .


* In the user guide, all optional parameters are denoted by ..opt/.. meaning that any zero or more of the optional particulars specified above can be used
  Eg. ..add..[..opt/..] means user can do …add..c/<CCA>..img/<IMG>..


* General Particulars:
    - Name <**NAME**>
        - String value of student's name
    - Class <**CLASS**>
        - String value of student's class
    - Index Number <**INDEX_NUMBER**>
        - Numbers (integer)
    - Sex <**SEX**>
        - M / m means male while F / f means female
  * Optional:
      - Image [**IMG**]
          - String value of absolute path to image
      - Age [**AGE**]
          - Numbers (integer)
      - Email [**EM**]
          - String value of email address
      - Phone number [**PH**]
          - String value of phone address


* Student Particulars:
    - Academics
        - Test <**TEST_NAME**>
            - String value of test name
        - Attendance <**ATTENDANCE**>
            - Mark as present/ not present
        - Homework <**HOMEWORK**>
            - String value of homework name
        - Grade <**GRADE**>
            - String value of grade results
    - Parents/ Next-of-kin <**NOK**>
      -String value of parent's name
  * Optional:
      - CCA [**CCA**]
      - Comments [**com**]


* Parent/ Guardians Particulars:
    - Relationship <**nok**>


</div>
<a name="student"/>

## General Command for Student Related features

* For all following features that are related to `students`, start first by typing `student <CLASS>` before adding the respective command for the feature.
  <a name="addstudent"/>

### Adding a Student: `add`

Adds a student to the database

Format: `add n/<NAME> in/<INDEX_NUMBER> s/<SEX> pn/<NOK_NAME> pnP/<NOK_CONTACT_NUMBER> rls<RELATIONSHIP> [ageS/<AGE> imgS/<ABSOLUTE_PATH_TO_IMAGE> eS/<EMAIL_ADDRESS> p/<PHONE_NUMBER> cca/<CCA>]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `student 1A add n/TanAhCow in/03 s/m pn/TanAhNiu pnP/91234567 rls/Father` <br>

  *Above is a situation where the student’s PHOTO_PATH and CCA are not provided!
* `student 1B add n/Mary Goh in/23 s/F pn/Goh Siew Mai pnP/91234567 rls/Mother a/Blk 456 Ang Mo Kio Avenue 6 #11-800 S(560456) ageS/15 imgS/C:\Users\teacher\OneDrive\Desktop\Pictures\marygoh.jpg eS/marygoh@gmail.com pnS/65656565 cca/Chess Club`

Note: <br>
User is able to leave the following categories unfilled by simply leaving a space
1. Age
2. Absolute path to image
3. Email Address
4. Phone Number
5. CCA <br><br>


<a name="gradestudent"/>

### Adding a grade for student : `grade`

Adds a test grade for the student corresponding to the INDEX_NUMBER in the CLASS

Format: `grade <INDEX_NUMBER> test/<TEST_NAME> gde/<GRADE>`

Examples:
* `student 3A grade 25 test/Mid-Terms gde/A` <br><br>

<a name = 'commentstudent'/>

### Adding comments for student : `comment`

Adds a comment for the student corresponding to the `INDEX_NUMBER` in the `CLASS`

Format: `comment <INDEX_NUMBER> note/<COMMENT>`

Examples:

* `student 3A comment 25 note/Quiet person, needs to interact more with classmates`

Note: <br>
If an existing comment is already available for the selected student, the new comment will OVERRIDE the old comment! Hence, users should check on existing comments before adding a new comment!
<a name = "liststudent"/> <br><br>

### Listing all students in the selected class: `list`

Shows a list of all students in the selected class in the database

Format: `find <INDEX_NUMBER>  `

Examples:
* `student 3B find 26`
* `student 3B find 27`

**Expected Outcome:**

* `<student Name> <id> <image> <nok name> <nok email> <nok number>`
* `ChanAhKow 21 ChanAhKow.png ChanMaiWoon chanmaiwoon@gmail.com 91234567`
  <a name = "editstudent" /> <br><br>

### Edit Student:  `edit`

Edits personal details of students

Format: `edit <INDEX_NUMBER (of student)> [name/<NAME> class/<CLASS> cca/<CCA> id/<INDEX_NUMBER> img/<IMAGE> age/<AGE> em/<EMAIL> ph/<PHONE_NUM> test/<TEST> att/<ATTENDANCE> hw/<ASSIGNMENTS> note/<COMMENTS> nok/<NAME>]   `

Examples:
* `student 3B edit 23 cca/badminton`
* `student 3B edit 23 cca/soccer att/2023-01-01 y`

**Expected Outcome:**
* Edited Student: Jennifer Lim cca: badminton
* Edited Student: Justina Lee attendance: cca:soccer 2023-01-01 y <br><br>

<a name = "findstudent"/>

### Locating persons by name:  `find`

Finds student by student id

Format: `find <INDEX_NUMBER>  `

Examples:
* `student 3B find 26`
* `student 3B find 27`

**Expected Outcome:**
* Student found: Russel Ong class:3B cca: swimming grade: [sci:A, maths:B] id:26 age:14 email:ro@outlook.com ph:85349633 attendance: 2023-01-01 [y] hw: ip [x] notes:extroverted nok: David Ong
* Student found: Joseph Tan class:3B cca: basketball grade: [sci:A, maths:C] id:27 age:14 email:jo@outlook.com ph:92103134 attendance: 2023-01-01 [x] hw: ip [x] notes:shy  nok: David Tan <br><br>

<a name = "deletestudent"/>

### Deleting student particulars: `delete`

Deleting student/ student information from the database

Format: `delete <INDEX_NUMBER> <GENDER> [a/<AGE> p/<ABSOLUTE_PATH_TO_IMAGE> c/<CCA> nok/<PARENT_NAME/NOK_NAME>]`

Examples:
* `student 1A delete TanAhCow 03 M a/14 p/ c/ nok/TanAhNiu`
  *Above is a situation where the student’s PHOTO_PATH and CCA are not provided!
* `student 1B delete Mary Goh 23 F a/15 p/ c/Chess Club nok/Goh Siew Mai`

Throws:
* WrongParticularException
  - The description field does not exist
  - Description is invalid (eg. age is not a number..)

User should follow the same format<br>
If no descriptions are given, the whole student will be removed from the database

<a name = "parent" />

## General Command for parent related features

* For all following features that are related to `parent`, start first by typing `parent`  before adding the respective command for the feature.

* Note that it is possible to have multiple students with the same parent and parents are identified via their phone number and *HENCE* `student class` is not used for identification.


<a name="addparent"/>

### Adding a parent: `add`

Adds a parent to the database

Format: `add n/<PARENT_NAME/NOK_NAME> pnP/<PHONE_NUMBER> [ageP/<AGE> imgP/<ABSOLUTE_PATH_TO_IMAGE> e/<EMAIL_ADDRESS> a/<RESIDENTIAL_ADDRESS>] `

Examples:
* `parent add n/TanAhNiu pnP/91234567`
* `parent add n/Tan Ah Niu pnP/91234567 ageP/30 imgP/C:// e/tanahcow@gmail.com a/Blk 456 Ang Mo Kio Avenue 6 #11-800 S(560456)` <br><br>


<a name = "listparent" />

### Listing all parents : `list`

Shows a list of all parent in the database

Format: `list`

Examples:
* `parent list` <br>
  Sample output: <parent name> <parent phone number> <parent email>
* `parent list` <br><br>

<a name = "deleteparent" />

### Deleting parent particulars: `delete`

Deleting parent/ specified parent information from the database

Format: `delete n/<PARENT_NAME/NOK_NAME> pnP/<PHONE_NUMBER> [ageP/<AGE> imgP/<ABSOLUTE_PATH_TO_IMAGE> e/<EMAIL_ADDRESS> a/<RESIDENTIAL_ADDRESS>] `

Examples:
* `parent delete n/TanAhNiu pnP/91234567` <br>
  Deletes TanAhNiu from powerConnect
* `parent 1A 03 delete TanAhCow Mother` <br>
  Deletes TanAhCow’s relationship of Mother with <Index number of student>

Throws:
* WrongParticularException
  - The description field does not exist
  - Description is invalid (eg. age is not a number..)

User should follow the same format <br>
`Warning:` If no descriptions are given, the whole parent will be removed from the database <br><br>

<a name = "help"/>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help` <br><br>

<a name = "exit"/>

### Exiting the program : `exit`

Exits the program.

Format: `exit` <br><br>

### Saving the data

PowerConnect data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually. <br><br>

### Editing the data file

PowerConnect data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. <br><br>
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------
<a name = "faq" />

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------
<a name = "summary" />

## Command Summary Student `student <CLASS>`

| Action      | Format, Examples                                                                                                                                                                                                    |
|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**     | `add <NAME> <INDEX_NUMBER> <SEX> <PARENT_NAME/NOK_NAME> [age/<AGE> img/<ABSOLUTE_PATH_TO_IMAGE> em/<EMAIL_ADDRESS> ph/<PHONE_NUMBER> cca/<CCA>]`                                                                    |
| **Grade**   | `grade <INDEX_NUMBER> test/<TEST_NAME> gde/<GRADE>`                                                                                                                                                                 |
| **Comment** | `comment <INDEX_NUMBER> note/<COMMENT>`                                                                                                                                                                             |
| **List**    | `list`                                                                                                                                                                                                              |
| **Edit**    | `edit <INDEX_NUMBER (of student)> [name/<NAME> class/<CLASS> cca/<CCA> id/<INDEX_NUMBER> img/<IMAGE> age/<AGE> em/<EMAIL> ph/<PHONE_NUM> test/<TEST> att/<ATTENDANCE> hw/<ASSIGNMENTS> note/<COMMENTS> nok/<NAME>]` |
| **Find**    | `find <INDEX_NUMBER> `                                                                                                                                                                                              |
| **Delete**  | `delete <INDEX_NUMBER> <GENDER> [a/<AGE> p/<ABSOLUTE_PATH_TO_IMAGE> c/<CCA> nok/<PARENT_NAME/NOK_NAME>]`                                                                                                            |

## Command Summary Parent `parent <CLASS>`

| Action     | Format, Examples                                                                                                                                  |
|------------|---------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**    | `add <INDEX_NUMBER (of student)> <(parent) NAME> <RELATIONSHIP> [a/<AGE> p/<ABSOLUTE_PATH_TO_IMAGE> ph/<PHONE_NUMBER> e/<EMAIL>] `                |
| **List**   | `list`                                                                                                                                            |
| **Delete** | `delete <INDEX_NUMBER (of student)> <(parent) NAME> <RELATIONSHIP WITH STUDENT> [a/<AGE> p/<ABSOLUTE_PATH_TO_IMAGE> ph/<PHONE_NUMBER> e/<EMAIL>]` |
