---
layout: page
title: User Guide
---

The Intern’s Ship (TinS) is a **desktop app for managing internships application, optimized for use via a Command Line
Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TinS can
help you manage and keep track of your internship applications faster than traditional GUI apps.

* Features (v1.2)
  * Adding an internship application: add
  * Listing all the company and position of the application : list
  * Locating internship by name: view
  * Deleting an internship : delete
  * Saving the data

-----------------
## Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `tins.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TinS.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tins.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add JOBNAME`, `JOBNAME` is a parameter which can be used as `add Software Engineer`.

* Items in square brackets are optional.<br>
  e.g `[CONTACT_DETAILS]` meansthat the user input is optional. User can press the enter key to skip the input.

* Question prompts are written in red.<br>
  e.g. in `INPUT COMPANY NAME: COMPANY_NAME`, `INPUT COMPANY NAME:` is a question prompt by the program. Users are
  required to input the necessary information after the prompt.

</div>

### Adding an internship : `add`

Adds an internship and its details to TinS

Format: `add POSITION`
```
add software engineer
INPUT COMPANY_NAME: COMPANY_NAME
INPUT APPLICATION STATUS: APPLICATION_STATUS
INPUT CONTACT DETAILS: [CONTACT_DETAILS]
```

* `POSITION`: Name of Internship Position
* `COMPANY NAME` : Name of hiring company
* `APPLICATION_STATUS` : Status of Application (`ACCEPTED`, `APPLIED`, `PENDING`, `REJECTED`)
* `CONTACT DETAILS` : Contact details of hiring manager (optional)
* After keying in the  add command, the user will be prompted with these fields:
    * `COMPANY_NAME`
    * `APPLICATION_STATUS`
    * `CONTACT_DETAILS (optional)`

Example:
```
add software engineer
INPUT COMPANY_NAME: Google
INPUT APPLICATION STATUS: applied
INPUT CONTACT DETAILS: BobTheManager@gmail.com
```

### Listing all internships : `list`

List all the internships (auto-generated ID, position, company name) in TinS.

* After keying in the `list` command, TinS returns the list of internships in the form of
  `1. (<ID>) <POSITION>, <COMPANY NAME>`
   * `ID` refers to the auto-generated ID created by TinS for this particular internship

Example: TinS should display a similar list to the one below, after the `list` command:

~~~
1. (SE_G1) SOFTWARE ENGINEER, GOOGLE
2. (DA_S1) DATA ANALYST, SHOPBACK
3. (SE_G2) SOFTWARE ENGINEER, GRAB
4. (DS_A1) DATA SCIENTIST, ARTEFACT
~~~

### Viewing Details of internship : `view`

View details of the internship selected by ID.

Format: `view ID`

* `ID`: Identification number of the Internship.
* After keying in the `VIEW` command, the program will return the details of the selected internship.

Example: <br>
After keying in the `view` command followed by an existing `ID`, TinS will output
all information of the internship with the specified `ID`, including position, company
name, application status and contact info.

~~~
// input
view SE_G1
~~~
~~~
//output
INTERNSHIP ID: SE_G1
POSITION: SOFTWARE ENGINEER
COMPANY NAME: GOOGLE
APPLICATION STATUS: APPLIED
CONTACT: BOBTHEMANAGER@GMAIL.COM
~~~

### Deleting a person : `delete`

Deletes an internship along with its details.

Format: `delete ID`
* Deletes the person at the specified `ID`.
* The ID the  Auto-Generated ID created by TInS.

> Note: After keying in the  delete command, the user will be asked to confirm their action by typing in `YES` or `NO`.

Examples:

`delete SE_G1`
`ARE YOU SURE YOU WANT TO DELETE SOFTWARE ENGINEER, GOOGLE` : `YES`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

The Intern Ship’s data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add JOBNAME` (input other details when prompted) <br> e.g., `add software engineer`
**Delete** | `delete ID`<br> e.g., `delete SE_G1`
**List** | `list`
**View** | `view ID`<br> e.g., `view SE_G1`

