---
layout: page
title: User Guide
---

ExecutivePro (EP) is a **desktop app for Human Resource managers to manage their employee information, optimized for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, EP can get your employee management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}
  
--------------------------------------------------------------------------------------------------------------------


## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
    1. If you have installed Java before, check that you have the right version, which is Java `11`.
        - If you are using Windows, open up command prompt and type `java -version` and enter.
        - If you are using Mac, open up terminal and type `java -version` and enter.
    2. If you do not have Java `11`:
        - If you are using Windows, you can install it from [here](https://www.oracle.com/java/technologies/downloads/).
        - If you are using Mac, you can install the Azul build of OpenJDK 11 version 
       from [here](https://www.azul.com/downloads/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx).
2. Download the latest `ExecutivePro.jar` from [here](https://github.com/AY2223S2-CS2103T-W09-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your ExecutivePro.

4. Double-click the file to start the app. A GUI similar to below should appear in a few seconds. 
   Note how the app contains some sample data.<br>

  <img class="centerImage" src="./images/UserGuide/Ui.png"/>

5. Type the command in the command panel and press Enter to execute it. e.g. 
   typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    - **`list`** : Lists all employees in the company.

    - **`add`** :`add n/Mark Doe p/98765432 e/markd@example.com a/311, Clementi Ave 2, #02-25 d/Marketing 
   t/SoftwareEngineer` : Adds an employee named `Mark Doe`, with fields phone number, email, address,
   department and tags to ExecutivePro's database.

    - **`delete 3`** : Deletes employee with ID 3.

    - **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------
## Symbols and Syntax

Here are some of the symbols to take note of when going through this user guide:

| Symbol               | Meaning                                      |
| -------------------- |----------------------------------------------|
| `code`               | Text relevant to commands or name of a file. |
| :bulb:               | Tips for ExecutivePro Users.                 |
| :warning:            | Be wary and proceed with caution.            |

--------------------------------------------------------------------------------------------------------------------

## Features
<div markdown="block" class="alert alert-info">

* Tags are optional.<br>
  e.g. `[n/NAME] [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

</div>

### Viewing help : `help`
There can be a lot of information to take in, so if you ever _feel lost_ while using ExecutivePro, 
getting help with the commands is just a simple step away.

Entering the `help` command will open up window with the command summary for the various functions of the application, 
and if you have more doubts, the _Help Window_ also contains a button to open up this User Guide in your browser.

Format: `help`

With this command, you should see a window like this appear.

![Help Window](./images/UserGuide/helpwindow.png)


### Adding an employee: `add`

How do we build an employee profile?

The first step is to add a new employee to the database, 
so ExecutivePro can begin managing their particulars and profile for you. 
To do this, use the add command, together with the employee particulars that you have available for this person.

Upon successfully adding a new employee, ExecutivePro will then keep track of the new profile and details in the 
database, and you are free to access and modify the particulars with other commands later on.

However, this function could fail (and ExecutivePro simply does not add any employee), if:

1. There are missing particulars which are compulsory. These mandatory details are listed below.
2. The particulars keyed in are in the wrong format. 
3. The new employee added is a duplicate, i.e. there is someone in the database who already shares the same name and
    details.

Format: `add [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...`

<div markdown="span" class="alert alert-primary">
:bulb: **For Tags:**
A person can have any number of tags (including 0)
</div>
Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Marketing`
* `add n/Betsy Crowe p/1234567 e/betsycrowe@example.com a/Newgate street, block 576, #01-02 d/Sales t/friend`


### Adding multiple employees at once: `batchadd`

Tired of adding new employees one by one?
ExecutivePro allows you to add multiple employees altogether from a `.csv` file.
This feature will come in handy when:

1. You are a new user and have your employee data stored in a `.csv` file.
2. There has been a recruitment cycle and the company has recruited multiple employees.

With this feature, you would not need to spend time to manually add each employee in!

Format: `batchadd FILENAME`

Example:`batchadd executivepro.csv`

Below are the steps to use this command:

**Step 1 (Creating CSV file) :**

Things to note:

- A header row is required to indicate the purpose of the field and must be the first row in the `.csv` file.
- For multiple tags for an employee, the tags should be separated by " \ ".

Order of headers is as such (**Order must be followed**):

| Index | Field          | Requirement     | 
|-------| -------------- |-----------------|
| 1.    | `NAME`         | **Compulsory**  | 
| 2.    | `PHONE`        | **Compulsory**  |
| 3.    | `EMAIL`        | **Compulsory**  | 
| 4.    | `ADDRESS`      | **Compulsory**  | 
| 5.    | `DEPARTMENT`   | **Compulsory**  | 
| 6.    | `TAGS`         | Optional        | 

Sample `.csv` file:
![](images/UserGuide/sampleCSV.png)


<div markdown="span" class="alert alert-warning">

:warning: 
**Caution:** No commas are to be used in the file. For the fields, do ensure that they follow the same specifications as in the [Field Formats below](#field-formats).

</div>

**Step 2 (Uploading CSV file) :**

1. Go to the folder where you stored the `jar` file.
2. Move CSV file to the `data` folder.
   ![](images/UserGuide/movingFile.png)

_If you are a new user (have not run any command yet), you will not see the `data` folder.
You can run the [`clear` command](#clearing-the-data-clear) to remove the sample employees first.
After this, you should be able to see the `data` folder._

**Step 3 (Running CSV file) :**

1. Once done, run `batchadd FILENAME` in the command panel.

If the command is successful, the employees in the file should be added to the database all at once, 
and it should look something like the below image.

![](images/UserGuide/BatchAddSuccess.png)

The command could be unsuccessful, and there are a few potential causes of this:

1. There could be a *duplicate* entry in the file, i.e. two employees sharing the same identity in the file.
2. If any of the particulars in the wrong format, ExecutivePro will not be able to read the file properly 
   and the command will not run.


In the case of an unsuccessful Batch Add, **NONE** of the employees in the `.csv` will be added.
Also note that as of version `1.3.0` , this feature only supports `.csv` files and adding employees with the fields mentioned above.

In the upcoming versions, we will expand `batchadd` feature to:

1. Support different types of files
2. Include more fields like performance and leaves



### Adding multiple employees at once: `batchexport`

ExecutivePro allows you to export the employees' data into a `.csv` file.

Format: `batchexport FILENAME`

Example:`batchexport exported_database.csv`

Below are the steps to use this command:

**Step 1 (Exporting to CSV file) :**

1. Run `batchexport FILENAME` in the command panel. The result should look like the image below.
![](images/UserGuide/exportedFile.png)

2. Go to the folder where you stored the `jar` file.
3. Locate the CSV file in the `data` folder.

If the command is successful, there should be CSV file that contains all the employees' details from the database,
and it should look something like the below image.

![](images/UserGuide/exportedFileLocation.png)

### Listing all employees : `list`

Shows a list of all employees and their details in the ExecutivePro database.

Format: `list`

### Editing an employee : `edit`

Edits an employee’s details in the ExecutivePro database.

Format: `edit EMPLOYEE_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...`

* Edits the details of the employee with the specified `EMPLOYEE_ID`. If such an employee doesn’t exist, an error message will be shown.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Changes the phone number and email address of the employee with ID `1` to be `91234567` and `johndoe@example.com` respectively.

### Locating employees by keyword: `find`

Shows a list of all employees in address book whose names match the keyword provided.

Format: `find [*] KEYWORD [MORE_KEYWORDS]`

* If asterisk (`*`) is inputted, it displays list of employees matching _all_ the given keywords. 
* If asterisk (`*`) is _not_ inputted, it displays list of employees matching _any_ of the given keywords. 
* Even if the keyword just partially matches a part of employees full name, it is considered a match.
* For finding department, the keyword has to be a full match.
* Keyword is to search for the name and department of the employee only, not any other details.

Examples:
* `find John Sales` displays list of all employees whose full name contains a 'John' in it, 
or they are in the 'Sales' department 
* `find * John Sales` displays list of all employees in the 'Sales' department who have a 'John' in their name


### Deleting an employee : `delete`

Deletes the details of the employee with the specified `EMPLOYEE_ID` from the ExecutivePro database.

Format: `delete EMPLOYEE_ID`

* You can delete the details of the employee with the specific `EMPLOYEE_ID`.
* The `EMPLOYEE_ID` refers to the id of an employee shown in the displayed employees list.
* The `EMPLOYEE_ID` **must be a positive integer** 1, 2, 3, …​

Examples:
`delete 2` deletes the employee with EMPLOYEE_ID 2 in ExecutivePro.

### Changing the UI theme : `theme`

Applies the specified theme to ExecutivePro's UI (either `dark` or `light`).

Format: `theme THEME_NAME`

* Applies the theme with the specified `THEME_NAME` to ExecutivePro's UI.
* `THEME_NAME` is either `dark` (white text on dark background) or `light` (black text on white background).

Examples:
`theme light` applies the Light theme to ExecutivePro's UI.

### Setting an employee's picture : `setpicture`

Opens the file explorer for the user to select a picture,
then sets the selected picture for the specified employee.

Format: `setpicture EMPLOYEE_ID`

* Sets the picture of the employee with the specific `EMPLOYEE_ID`.
* The `EMPLOYEE_ID` refers to the id of an employee shown in the displayed employees list.
* The `EMPLOYEE_ID` **must be a positive integer** 1, 2, 3, …​

Examples:
`setpicture 2` sets the picture for the employee with EMPLOYEE_ID 2 in ExecutivePro.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ExecutivePro data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ExecutivePro data are saved as a JSON file `[JAR file location]/data/executivepro.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ExecutivePro will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app on the other computer and overwrite the empty data file. This creates a new file that contains the data of your previous ExecutivePro home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary


| Action          | Format, Examples                                                                                                                                                                                                |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**        | `help`                                                                                                                                                                                                          |
| **Add**         | `add EMPLOYEE_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...` <br> e.g., `add 1 n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 d/Marketing t/friends` |
| **BatchAdd**    | `batchadd FILENAME` <br> e.g., `batchadd executivepro.csv`                                                                                                                                                      |
| **BatchExport** | `batchexport FILENAME` <br> e.g., `batchexport exported_database.csv`                                                                                                                                           |
| **List**        | `list`                                                                                                                                                                                                          |
| **Edit**        | `edit EMPLOYEE_ID [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [d/DEPARTMENT] [t/TAG]...`<br> e.g.,`edit 1 p/91234567 e/johndoe@example.com`                                                                 |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                      |
| **Delete**      | `delete EMPLOYEE_ID`<br> e.g., `delete 3`                                                                                                                                                                       |
| **Theme**       | `theme THEME_NAME` <br> e.g., `theme light`                                                                                                                                                                     |
| **SetPicture**  | `setpicture EMPLOYEEID` <br> e.g., `setpicture 2`                                                                                                                                                               |
| **Exit**        | `exit`                                                                                                                                                                                                          |

