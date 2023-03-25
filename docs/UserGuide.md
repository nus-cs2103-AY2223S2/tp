---
layout: page
title: InternBuddy User Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------
## Introducing InternBuddy

InternBuddy is a desktop application for Computing undergraduates to manage their internship applications.
It is optimized for typing where it allows you to complete internship management tasks much more efficiently via
the keyboard as compared to using traditional Graphical User Interface (GUI) applications. If you are a fast typist
who is seeking for a one-stop platform to systematically organise your internship applications,
then InternBuddy is the perfect buddy to accompany you during your internship hunt.

InternBuddy runs using Java 11, and is available on the Windows, macOS and Linux operating systems.

<br/>
<p align="center">
  <img width="400" height="255" src="images/internbuddy-computer.png">
</p>


--------------------------------------------------------------------------------------------------------------------
## About the User Guide

### Objectives of the User Guide
This user guide aims to provide comprehensive instructions for users to learn how to use InternBuddy,
providing details on the installation process and features provided by InternBuddy. It also contains information
for advanced users to customise the usage of InternBuddy for a tailored user experience.

### Using the User Guide
This uses guide uses a set of formatting standards and visuals to better communicate information.

#### Information Box
<div markdown="span" class="alert alert-primary">

:information_source: **Info:** Provides useful information that supplements the main text
</div>

#### Tip Box
<div markdown="span" class="alert alert-success">

:bulb: **Tip:**  Suggestions on how to enhance your experience
</div>

#### Warning Box
<div markdown="span" class="alert alert-danger">

:warning: **Warning:**  Warns of a dangerous action that you should be aware of and to consider
carefully before committing
</div>

#### Syntax Highlighting
Commands, parameters, file paths and class names are highlighted.

`command`, `PARAMETER`, `filepath.json`, `ClassName`


#### Keyboard Actions
Keyboard keys are indicated using rounded buttons.

<button>Ctrl</button> <button>Alt</button> <button>Space</button> <button>Enter</button> <button>&uarr;</button>

--------------------------------------------------------------------------------------------------------------------

## Quick Start

1. Ensure you have Java `11` or above installed in your computer
<div markdown="span" class="alert alert-primary">

:information_source: **Info:** If you are unsure of whether you have Java 11 installed, or need help installing
it, you can refer to <a href="#appendix">Appendix A</a>.

</div>


2. Download the latest `internbuddy.jar` from [here](https://github.com/AY2223S2-CS2103T-T14-3/tp/releases).

3. Copy the file `internbuddy.jar` to the folder you want to use as the _home folder_ for InternBuddy.
<div markdown="span" class="alert alert-primary">

:information_source: **Info:** The home folder is the folder where you will navigate to in order to launch
InternBuddy, and it is where your InternBuddy data file will be stored in.

</div>

4. Double-click on the file `internbuddy.jar` to launch InternBuddy. A GUI similar to Figure 1 should
   appear in a few seconds. Note how the app contains some sample data.<br/><br/>
   ![Ui](images/Ui.png)
   <p style="text-align: center;">Figure 1: InternBuddy's GUI</p>

<br/>

5. You can interact with InternBuddy by typing into the box with the text `Enter command here...`, then pressing
   <button>Enter</button> to execute your command. For example, typing help and pressing <button>Enter</button> will open
   the help window.


6. Here are some other example commands you can try:

    - `list`: List all internship entries stored in InternBuddy
    - `add n/Food Panda r/Web Developer s/Applied d/2023-04-01`: Adds a new internship entry into InternBuddy.
    - `delete 3` : Deletes the 3rd internship entry of the current list displayed in InternBuddy.
    - `exit` : Exits InternBuddy.


Do refer to [Features](#features) below for a comprehensive list of supported features and their associated details.

--------------------------------------------------------------------------------------------------------------------
## Exploring the Graphical User Interface

![Graphical User Interface](images/gui-markup.png)
<p style="text-align: center;">Figure 2: Different parts of InternBuddy's GUI</p>
<br/>

| Part            | Usage                                                                                        |
|-----------------|----------------------------------------------------------------------------------------------|
| Input Box       | You can type in your commands here to interact with InternBuddy.                             |
| Results Display | This is where the results of your command will be displayed.                                 |
| List Panel      | Displays a list of internship entries.                                                       |
| View Panel      | Displays either the welcome message or detailed information of a specified internship entry. |
| Location Bar    | States where your InternBuddy data file is located on your computer.                         |

<p style="text-align: center;">Figure 3: Explanation of the different parts of InternBuddy's GUI</p>


<div markdown="span" class="alert alert-success">

:bulb: **Tip:**  The GUI is resizeable. You can resize it according to your preferences.

</div>


--------------------------------------------------------------------------------------------------------------------
## Command Information

### Notes about Commands and Parameters

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. If the command format is `add n/COMPANY_NAME`, you may input the command as `add n/Apple` where you supply the
  value `Apple` to the parameter `COMPANY_NAME`.

* Items in square brackets are optional.<br>
  e.g. If the command format is `edit INDEX [n/NAME] [c/COMMENT]`, you may input the command as `edit 2 n/Apple` where
  you omit the value for the parameter `COMMENT`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. If the command format is `n/NAME r/ROLE`, both `n/Apple r/Software Engineer` and `r/Software Engineer n/Apple`
  are acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. If the command format is `r/ROLE`, typing in `r/Front-end Developer r/Back-end Developer` will cause your
  input to be interpreted as `r/Back-end Developer`.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `exit`) will be
  ignored.<br>
  e.g. If the command format is `help`, typing in `help 123` will cause your input to be interpreted as `help`.


### Descriptions, Prefixes and Constraints for Parameters
In InternBuddy's commands, we refer to a range of parameters that you can replace with values to input information that
is customised to your internship applications.

There are 2 important things that you should note:
1. Most parameters have associated **prefixes**. Prefixes are convenient shorthands that allow you to easily identify
   which parameter does a value belong to. For example, in `add n/Apple`, the value `Apple` is associated with the
   parameter `COMPANY_NAME` since the `n/` prefix is used.
2. There are **constraints** to the values that you can replace parameters with. The constraints differ based on
   the parameters. If you do not adhere to these constraints in your input, your input will be valid and an error
   message will be shown in the Results Display when you type the input in and press <button>Enter</button>.


Figure 4 provides a summary of the parameters with their descriptions, prefixes and constraints.

| Parameter      | Description                                                                    | Prefix | Constraints                                                                                                                                             |
|----------------|--------------------------------------------------------------------------------|--------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `COMPANY_NAME` | The name of the company                                                        | `n/`   | Alphanumeric with spaces allowed                                                                                                                        |
| `ROLE`         | The role that you applied for                                                  | `r/`   | Alphanumeric with spaces allowed                                                                                                                        |
| `STATUS`       | The status of the internship application                                       | `s/`   | Must be one of the following: `New`, `Applied`, `Assessment`, `Interview`, `Offered`, `Accepted`, `Rejected`. Note that this is **not** case-sensitive. |
| `DATE`         | The date associated with the internship application                            | `d/`   | Must be a valid date in the format `YYYY-MM-DD `                                                                                                        |
| `COMMENT`      | A comment that you can make on an internship application                       | `c/`   | Cannot be blank                                                                                                                                         |
| `TAG`          | A label that you can give to an internship application                         | `t/`   | Cannot be blank and must be at most 30 characters.                                                                                                      |
| `INDEX`        | The index number of the internship entry as displayed in the List Panel        | -      | A positive integer that is smaller than or equal to the largest index number shown in the List Panel. Note that 0 is not a positive integer.            |

<p style="text-align: center;">Figure 5: Parameters with their descriptions, prefixes and constraints</p>

### Details on **`STATUS`** and **`DATE`**
The parameter `STATUS` is used to represent the current status of an internship application. It can only take on one
of the following values: `New`, `Applied`, `Assessment`, `Interview`, `Offered`, `Accepted` and `Rejected`.
Note that this is not case-sensitive. Figure 6 explains the meaning of each status.


| `STATUS` | Description                                                                                                                       |
|-------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| `New`             | You have recently saw this internship opportunity and would like to record it in InternBuddy. Also, you have yet to apply for it. |
| `Applied`         | You have applied for this internship opportunity and you are currently waiting for the company's response.                        |
| `Assessment`      | You are currently in the technical assessment stage of the application process.                                                   |
| `Interview`       | You are currently in the behavioral interview stage of the application process.                                                   |
| `Offered`         | You have been offered the internship opportunity.                                                                                 |
| `Accepted`        | You have accepted the internship opportunity.                                                                                     |
| `Rejected`        | You have either been rejected by the company, or that you have rejected the internshop offer.                                     |

<p style="text-align: center;">Figure 6: Description of statuses</p>


Depending on the status of the internship application, the `DATE` parameter will be interpreted differently. Figure 7
documents the meaning of `DATE` with respect to each `STATUS` value.

| `STATUS`     | Interpretation of `DATE`     |
|--------------|------------------------------|
| `New`        | Deadline of Application      |
| `Applied`    | Date Applied                 |
| `Assessment` | Date of Technical Assessment |
| `Interview`  | Date of Behavioral Interview |
| `Offered`    | Deadline of Offer Acceptance |
| `Accepted`   | Date of Acceptance           |
| `Rejected`   | Date of Rejection            |

<p style="text-align: center;">Figure 7: Description of dates</p>

--------------------------------------------------------------------------------------------------------------------
## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/COMPANY_NAME`, `COMPANY_NAME` is a parameter which can be used as `add n/Apple`.

* Items in square brackets are optional.<br>
  e.g. `edit INDEX [n/NAME]` can be used as `edit 2 n/CompanyXYZ` or as `edit 2`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/Apple r/Software Engineer`, `r/Software Engineer n/Apple` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of
  the parameter will be taken.<br>
  e.g. if you specify `r/Front-end Developer r/Back-end Developer`, only `r/Back-end Developer` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `exit`) will be
  ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* For any parameters that refer to a date (such as in `add`, `edit`), they must be specified in the format YYYY-MM-DD<br>
  e.g. if the command specifies `edit INDEX [d/DATE]`, then 1 March 2023 should be entered as `2023-03-01` for the
  parameter `DATE`.
</div>

### Listing all internship entries : `list`

Shows a list of all internship entries that have been added into InternBuddy.

Format: `list`

* The meaning of the date displayed for each internship entry will depend on the status of the internship. For example, if
the status of the internship is `new`, the `date` field refers to the deadline of application. A full reference table is
shown below.

### Adding an internship entry: `add`

Adds a new internship entry to the list of existing entries.

Format: `add n/COMPANY_NAME r/ROLE s/STATUS d/DATE`
- The `STATUS` field  must have one of the following values: `new`, `applied`,
  `assessment`, `interview`, `offered` or `rejected`.
- The meaning of `DATE` would be interpreted with respect to the value of `STATUS`.


| Status       | Interpretation of Date       |
|--------------|------------------------------|
| `new`        | Deadline of Application      |
| `applied`    | Date of Application          |
| `assessment` | Date of Technical Assessment |
| `interview`  | Date of Behavioral Interview |
| `offered`    | Date of Offer                |
| `rejected`   | Date of Rejection            |

Examples:
* `add n/Apple r/Software Engineer s/new d/2023-02-01` Adds a new internship entry with
  company name `Apple`, role `Software Engineer`, status `new` and deadline
  of application `2023-02-01`.
* `add n/Amazon r/Cloud Architect s/assessment d/2023-02-01` Adds a new internship entry
  with company name `Amazon`, role `Cloud Architect`, status `assessment` and
  date of technical assessment `2023-02-01`.
* `add n/Facebook s/new d/2023-02-01` Displays an error because the role is missing.


### Deleting an internship entry : `delete`
Deletes the specified internship entry from InternBuddy.

Format: `delete INDEX`

* Deletes the internship entry at the specified `INDEX`.
* The index refers to the index number shown in the displayed internship entries list.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list` followed by `delete 2` deletes the 2nd internship entry in InternBuddy.

### Editing an internship entry : `edit`

Edits an internship entry from the list of existing entries.

Format: `edit INDEX [n/COMPANY_NAME] [r/ROLE] [s/STATUS ] [d/DATE]`

* The internship entry whose entry number is `INDEX` would be updated. `INDEX` needs to be a valid entry number as specified in the internship list displayed using the`list` command. 
* At least one of the optional fields must be provided.
* `STATUS` must have one of the following values: `new`, `applied`, `assessment`, `interview`, `offered` or `rejected`.

Examples:
*  `edit 2 s/assessment r/Software Developer` Sets the status and role of the second internship entry as `assessment` and `Software Developer` respectively.
*  `edit 2` Displays an error because the command does not satisfy the criteria of having at least one optional field.

### Clearing all internship entries
Clears all internship entries from InternBuddy.

Format: `clear`<br>

![ClearEntriesWarningMessage](images/Clear-entries-warning-message.png)

### Getting help : `help`

Displays the list of commands supported by InternBuddy.

Format: `help`


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InternBuddy data are saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

### Loading the data

InternBuddy data is loaded from the hard disk automatically at the beginning of each run. There is no need to load manually.
If the data file is missing, InternBuddy will start with a data file containing the sample internship entries.
If the data file is invalid, InternBuddy will start with an empty data file.

### Editing the data file

InternBuddy data are saved as a JSON file `[JAR file location]/data/internbuddy.json`. Advanced users are welcome to
update the data directly by editing that data file.<br>

![EditDataWarningMessage](images/Edit_data_warning_message.png)


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install InternBuddy in the other computer and overwrite the empty data file it creates with the file that
contains the data of your previous InternBuddy home folder.

--------------------------------------------------------------------------------------------------------------------
## Command summary

| Action                        | Format, Examples                                                                                                                    |
|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| List                          | `list`                                                                                                                              |
| Add                           | `add n/COMPANY_NAME r/ROLE s/STATUS d/DATE [c/COMMENT] [t/TAG]...`  <br> e.g., `add n/Apple r/Software Engineer s/New d/2023-03-01` |
| Edit                          | `edit INDEX [n/NAME] [r/ROLE] [s/STATUS] [d/DATE] [c/COMMENT] [t/TAG]...`<br> e.g.,`edit 2 s/Assessment r/Software Developer`       |
| View                          | `view INDEX`<br> e.g., `view 1`                                                                                                     |
| Find                          | `find [n/COMPANY_NAME]... [r/ROLE]... [s/STATUS]... [d/DATE]... [t/TAG]...`<br/>e.g., `find n/Apple n/Google`                       |
| Get upcoming events/deadlines | `upcoming`                                                                                                                          |
| Delete                        | `delete INDEX`<br> e.g., `delete 3`                                                                                                 |
| Clear                         | `clear`                                                                                                                             |
| Help                          | `help`                                                                                                                              |
| Exit                          | `exit`                                                                                                                              |


--------------------------------------------------------------------------------------------------------------------
## Appendix A: Installing Java 11
Follow the following steps to set up Java 11 in your computer.
1. Open up a terminal on your computer.
    - For Windows users, click on the `Windows` icon at the bottom left of your computer. Then, type in `terminal` in
      the search bar and double-click the application called `Terminal`.
    - For macOS users, click on the `Spotlight` search icon at the top right of your computer. Then, type in `terminal`
      in the search bar and double-click the application called `Terminal`.
    - For Linux users, press <Button>CTRL</Button> + <Button>ALT</Button> + <Button>T</Button> to launch the terminal.
2. In the terminal, type in `java -version` and press <button>Enter</button>. The terminal will display the version of
   Java that you have installed on your computer.
3. If you do not have any versions of Java installed, or you have a version older than Java 11, download [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) here.
   You may then return to Step 1 to check whether you have the correct version of Java installed.

--------------------------------------------------------------------------------------------------------------------
## Appendix B: Customising the Data File
If you are an advanced user of InternBuddy, you can directly edit the contents of your data through the `internbuddy.json`
file without using the GUI.

<div markdown="span" class="alert alert-danger">

:warning: **Warning:**  If you are new to InternBuddy or are not confident in reading JSON files, we would advise you against
directly editing the `internbuddy.json` file. This is because if you accidentally make a mistake that leads to the JSON
file not having correct format, InternBuddy would restart with the sample data file, wiping out any data that you had
previously.

</div>

Follow the following steps to properly edit the `internbuddy.json` file:

--------------------------------------------------------------------------------------------------------------------

## **Glossary**


| Term                           | Definition                                                                                                                                                                                                              |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Alphanumeric                   | Any combination of letters and numbers                                                                                                                                                                                  |
| Command                        | An instruction for InternBuddy to perform an action.                                                                                                                                                                    |
| Command Line Interface (CLI)   | A CLI is the text-based interface that you can use to provide instructions to your computer. Examples of instructions include opening files and running programs.                                                       |
| Graphical User Interface (GUI) | A GUI is the visual interface that you see when an application launches, allowing you to interact with it by clicking on its various buttons and components.                                                            |
| Mainstream OS                  | Includes Windows, macOS, Linux and Unix.                                                                                                                                                                                |
| Parameter                      | A part of the command where you have to supply a value for the command to be valid.                                                                                                                                     |
| Prefix                         | A short form for the name of a parameter. It indicates which parameter does a value belongs to. For example, in `n/Apple`, the value `Apple` is supplied to the parameter `COMPANY_NAME` since the `n/` prefix is used. |


--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* InternBuddy is written in **Java 11**.
* It is adapted from the [AddressBook Level 3](https://github.com/se-edu/addressbook-level3) project created by
  the [SE-EDU initiative](https://se-education.org).
* Libraries and frameworks used: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson),
  [JUnit5](https://github.com/junit-team/junit5) and [TestFX](https://github.com/TestFX/TestFX).
* Other references: [AddressBook Level 4](https://github.com/se-edu/addressbook-level4)
  and [Please Hire Us](https://github.com/AY2223S1-CS2103T-W17-4/tp).
