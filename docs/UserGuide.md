# PowerConnect User Guide

Greetings! Welcome to PowerConnect! <br>

Looking for a simple and all in one solution for your teaching needs? Look no futher. <br>

PowerConnect is a fully customized platform for tuition teachers and school teachers with the primary focus on 
tuition teachers now as we are still working to scale our features. School teachers, do still give us a try. While our 
features are limited at this stage, we are working hard to scale them to better compete with other Learning Managment
System (LMS) tools currently in the market. <br>

We aim to make PowerConnect as simple as possible for you to use with a focus on command line interface where you don't
have to navigate complex user menus to access the features you need, especially if you can type fast. <br>

Without further ado, let's get started!


##### Table of Contents
1. [Quick Start](#quickstart)
2. [Features](#features)
    1. [Student Features](#student)
        1. [Add student: `add`](#addstudent)
        2. [Add students' grade: `grade`](#gradestudent)
        3. [Deletes students' grade: `gradedelete`](#gradedelete)
        4. [Add comments to students: `comment`](#commentstudent)
        5. [Listing all students in a particular class: `list`](#liststudent)
        6. [Editing a student's particulars: `edit`](#editstudent)
        7. [Searching students: `find`](#findstudent)
        8. [Deleting a student: `delete`](#deletestudent)
    2. [Parent Features](#parent)
       1. [Add Parent/Next-of-Kin: `add`](#addparent)
       2. [Listing all Parents/Next-of-Kins: `list`](#listparent)
       3. [Editing a Parent/Next-of-Kin particulars: `edit`](#editparent)
       4. [Deleting a Parent/Next-of-Kin: `delete`](#deleteparent)
3. [Viewing help: `help`](#help)
4. [Exiting program: `exit`](#exit)
5. [Additional Features](#addfeatures)
6. [FAQ](#faq)
7. [Command Summary](#summary)

<a name="quickstart"/>
--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `powerconnect.jar` from [here](https://github.com/AY2223S2-CS2103T-T09-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for PowerConnect.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all contacts.

    * `student 1A add n/Tan Ah Cow in/13 s/M pn/Tan Ah Niu pnP/91234567 rls/Father` : Adds a student in class 1A named `Tan Ah Cow` to PowerConnect.

    * `student 1A delete in/13` : Deletes the student with index number 13 in class 1A

    * `student 1A grade in/13 test/CA1 score/75 deadline/15/05/2023 weightage/20`: Adds a test named CA1, score of 75, deadline of 15/05/2023, weightage 20% to student index number 13 in class 1A
    * `student 1A gradedelete in/13 test/CA1`: Deletes a test named CA1 for student in class 1A with index 13
    * `clear` : Deletes all contacts.
    * `exit` : Exits the app.


6. Refer to the [Features](#features) below for details of each command.

   <a name="features"/>

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Command lines supplied by the user are case sensitive and should be supplied in lower case. <br>

  E.g. in the list feature, user can call it via either methods:
     1. student 5A list


* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `…add <NAME>...`, `NAME` is a parameter which can be used as `…add John…`.


* Items in `<>` brackets are the values that the user should fill in and is compulsory.
  e.g. in `…add <NAME>…` , `NAME` is a parameter that needs to be included, can be used as `…add John…`.


* All items in the square brackets are OPTIONAL.

  e.g. in `…add…[..c/<CCA>.. ]...` , `CCA` is an optional parameter that need not be given by the user and can be skipped, can be used as `…add…`  or `…add…c/Mathematics Club nok/…` .



* General Particulars:
      - Name <**NAME**>
        - String value of student's name
      - Phone <**PH**>
          - Numbers (integer)
      - Sex <**SEX**>
          - M / m means male while F / f means female
  * Optional:
      - Age [**AGE**]
          - Numbers (integer)
      - Phone number [**PH**]
          - String value of phone address
      - Address <**ADDR**>
          - String value of student's address
      - Email <**EM**>
          - String value of email address


* Student Particulars:
    - Class <**CLASS**>
        - String value of student's class
    - Index Number <**INDEX_NUMBER**>
        - Numbers (integer)
    - Parents/ Next-of-kin <**NOK**>
      - String value of parent's name
    - Parents/ Next-of-kin phone number <**NOK_CONTACT_NUMBER**>
        - Integer value of parent phone number
    - Parents/ Next-of-kin relationhip to student <**NOK_RELATIONSHIP_TO_STUDENT**>
        - String relationship of parent/ next-of-kin with student
  * Optional:
      - Academics
          - Test <**TEST_NAME**>
              - String value of test name
          - Attendance <**ATTENDANCE**>
              - Mark as present/ not present
          - Homework <**HOMEWORK**>
              - String value of homework name
          - Grade <**GRADE**>
              - String value of grade results
      - Image [**img**]
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

Format: `add n/<NAME> in/<INDEX_NUMBER> s/<SEX> pn/<NOK_NAME> pnP/<NOK_CONTACT_NUMBER> rls<NOK_RELATIONSHIP_TO_STUDENT> [a/<REISDENTIAL_ADDRESS> ageS/<AGE> imgS/<ABSOLUTE_PATH_TO_IMAGE> eS/<EMAIL_ADDRESS> pnS/<PHONE_NUMBER> cca/<CCA>]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person with the same name and same class but different index number can exist.
</div>

Examples:
* `student 1A add n/TanAhCow in/03 s/m pn/TanAhNiu pnP/91234567 rls/Father` <br>

  *Above is a situation where the student’s PHOTO_PATH and CCA are not provided!
* `student 1B add n/Mary Goh in/23 s/F pn/Goh Siew Mai pnP/91234567 rls/Mother a/Blk 456 Ang Mo Kio Avenue 6 #11-800 S(560456) ageS/15 imgS/C:\Users\teacher\OneDrive\Desktop\Pictures\marygoh.jpg eS/marygoh@gmail.com pnS/65656565 cca/Chess Club`

Note: <br>
User is able to leave the following categories unfilled by simply not putting their respective "/"
1. Address
2. Age
3. Absolute path to image
4. Email Address
5. Phone Number
6. CCA <br><br>



<a name="gradestudent"/>

### Adding a grade for student : `grade`

Adds a test/homework assignment for the student corresponding to the INDEX_NUMBER in the CLASS

Format: `grade in/<INDEX_NUMBER> [test/<TEST_NAME> OR hw/<HOMEWORK_NAME>] [score/<score> deadline/<DEADLINE(DD/MM/YYYY)> weightage/<WEIGHTAGE>] [hwdone/<HOMEWORK_DONE(true/false)>]`

Examples:
* `student 3A grade 25 test/Mid-Terms`
* `student 1A grade in/13 test/CA1 score/75 weightage/10`
* `student 1A grade in/13 hww/homework1 score/75 deadline/25/04/2023 weightage/10 hwdone/true`<br><br>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
1. Test or Homework name is compulsory for the command to work and must not be duplicate of current tests/homework in student.
2. hwdone is compulsory for homework.
3. Score is out of 100
4. Weightage is out of 100%
</div>

<a name = "gradedelete">

### Deleting a grade for student : `gradedelete`

Deletes a test/homework assignment for the student corresponding to the INDEX_NUMBER in the CLASS

Format: `gradedelete in/<INDEX_NUMBER> [test/<TEST_NAME> OR hw/<HOMEWORK_NAME>]`

Examples:
* `student 3A gradedelete 25 test/Mid-Terms`
* `student 1A gradedelete in/13 test/CA1`

<a name = 'commentstudent'/>

### Adding comments for student : `comment`

Adds a comment for the student corresponding to the `INDEX_NUMBER` in the `CLASS`

Format: `comment in/<INDEX_NUMBER> com/<COMMENT>`

Examples:

* `student 3A comment in/25 com/Quiet person, needs to interact more with classmates`

Note: <br>
If an existing comment is already available for the selected student, the new comment will OVERRIDE the old comment! Hence, users should check on existing comments before adding a new comment!
<a name = "liststudent"/> <br><br>

### Listing all students in the selected class: `list`

Shows a list of all students in the selected class in the database

Format: `list`

Examples:
* `student 1A list`
* `student 1B list`

**Expected Outcome:**

* `<student Name> <id> <image> <nok name> <nok email> <nok number>`
* `ChanAhKow 21 ChanAhKow.png ChanMaiWoon chanmaiwoon@gmail.com 91234567`
  <a name = "editstudent" /> <br><br>

### Edit Student:  `edit`

Edits personal details of students

Format: `edit in/<INDEX_NUMBER (of student)> [nn/<NEWNAME> nin/<NEWINDEXNUMBER> nc/<NEWCLASS> s/<SEX> ageS/<STUDENT AGE>
imgS/<IMAGE> cca/<CCA> att/<ATTENDANCE> com/<COMMENTS> pnS/<STUDENT PHONE NUMBER> eS/<STUDENT EMAIL> a/<ADDRESS>
pn/<PARENT NAME> pnP/<PARENT PHONE NUMBER> rls/<RELATIONSHIP>]`

Examples:
* `student 1A edit in/03 cca/basketball`
* `student 1A edit in/03 cca/badminton ageS/23`

**Expected Outcome:**
* Edited student: TanAhCow; Student Class: class seedu.address.model.person.student.Student; Index Number: 03; Sex: M;
* Student Age: Insert student age here!; Image Path: Insert student image here!;
* Student Email: Insert student email here!; Student Phone: Insert student phone number here!; CCA: basketball
* Edited student: TanAhCow; Student Class: class seedu.address.model.person.student.Student; Index Number: 03; Sex: M;
* Student Age: 23; Image Path: Insert student image here!; Student Email: Insert student email here!;
* Student Phone: Insert student phone number here!; CCA: badminton

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

Format: `delete in/<INDEX_NUMBER>`

Examples:
* `student 1A delete in/2`

The whole student will be removed from the database

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

<a name = "editparent" />

### Edit Parent:  `edit`

Edits personal details of students

Format: `edit n/<PARENT_NAME/NOK_NAME> pnP/<PHONE_NUMBER> [nn/<NEW_NAME> npnP/<NEW_PHONE_NUMBER> ageP/<NEW_AGE> imgP/<NEW_IMAGE> e/<NEW_EMAIL_ADDRESS> a/<NEW_RESIDENTIAL_ADDRESS>]   `

Examples:
* `parent edit n/Tan Ah Niu pnP/91234567 npnP/65656565`
* `parent edit n/Tan Ah Niu pnP/91234567 nn/Tan Ah Seng npnP/91274444 ageP/31 imgP/C:// e/tanahcow@gmail.com a/Blk 245 Ang Mo Kio Avenue 1 #11-800 S(560245)`

**Expected Outcome:**
* `Edited Parent: Tan Ah Niu; Phone: 65656565`
* `Edited Parent: Tan Ah Niu; Parent Age: 31; Address: Blk 245 Ang Mo Kio Avenue 1 #11-800 S(560245); Image Path: C://; Parent Email: tanahcow@gmail.com; Parent Phone: 91234567; Parent/NOK of: Tan Ah Cow; Student Class: ...` <br><br>

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

<a name = "addfeatures"/>

### Additional Features

### Images 

In the same folder as your PowerConnect.jar file, create a new file called `images` if it does not exist. <br>

Place all your student images in this format: `images/student/<STUDENT_NAME><STUDENT_CLASS>.png` <br>

Place all your parent images in this format: `images/parent/<PARENT_NAME>.png` <br>

--------------------------------------------------------------------------------------------------------------------
<a name = "faq" /></a>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------
<a name = "summary" /> </a>


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
