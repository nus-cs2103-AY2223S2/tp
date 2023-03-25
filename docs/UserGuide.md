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

Action | Format, Examples
--------|------------------
**List** | `list`
**Add** | `add n/COMPANY_NAME r/ROLE s/STATUS d/DATE​` <br> e.g., `add n/Apple r/Software Engineer s/new`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [r/ROLE] [s/STATUS] [d/DATE]​`<br> e.g.,`edit 2 s/assessment r/SoftWare Developer`
**Help** | `help`
**Exit** | `exit`
