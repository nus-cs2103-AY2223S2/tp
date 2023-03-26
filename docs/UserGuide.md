---
layout: page
title: User Guide
---

The Intern’s Ship (TinS) is a **desktop app for managing internships application, optimized for use via a Command Line
Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you can type fast, TinS can
help you manage and keep track of your internship applications faster than traditional GUI apps.

* Features (v1.3)
  * Adding an internship application: `add`
  * Listing all the company and position of the application : `list`
  * Editing an internship application: `edit`
  * Locating internship by name: `select`
  * Deleting an internship : `delete`
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
  e.g. in `add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION`, `POSITION` is a parameter which can be
  used as `add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python and Java`.

* Items in square brackets are optional.<br>
  e.g `[TAG]` means that the user input is optional. User can press the enter key to skip the input.

</div>

### Adding an internship : `add`

Adds an internship and its details to TinS

Format: `add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION [t/TAG] ...`

* `POSITION`: Name of Internship Position
* `COMPANY_NAME` : Name of Hiring Company

> Note: `POSITION` and `COMPANY_NAME` have to be unique. <br>
> If there is an Internship with `POSITION` as `Software Engineer` and `COMPANY_NAME` as `Grab` already saved in TinS:
> * Adding an Internship with `POSITION` as `Software Engineer` and `COMPANY_NAME` as `Grab` is not valid
> * Adding an Internship with `POSITION` as `Software Engineer 1` and `COMPANY_NAME` as `Grab` is valid
> * Adding an Internship with `POSITION` as `Software Engineer` and `COMPANY_NAME` as `Grab 1` is valid

* `APPLICATION_STATUS` : Status of Application
  * `APPLICATION_STATUS` should be an Integer value from 0 to 3. Here are the statuses for the corresponding integer
  values:
    * `0` : Interested
    * `1` : Applied
    * `2` : Offered
    * `3` : Rejected
* `DESCRIPTION` : Additional details about the Internship (E.g. Contact Details, Link to Webpage, Requirements of
Internship)
* `TAG` : Customised Tag
  * This is optional.
  * An Internship can have more than one tag.

Example:
```
add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python and Java t/Important t/Priority
```

### Listing all internships : `list`

List all the internships in Internship List panel of TinS.

* After keying in the `list` command, TinS displays **all** the Internships stored in TinS in the Internship List panel on
the left side of TinS Application.
* Only the `POSITION`, `COMPANY_NAME`, `APPLICATION_STATUS` and `TAG` are display in the Internship List panel for each
Internship.

Example:
![internship_list_panel](images/internship_list_panel.png)

### Viewing Details of a Particular Internship : `select`

View details of the internship selected by ID.

Format: `select ID`

* `ID`: The number of the selected internship in the Internship List Panel
* After keying in the `select` command, the program will return the all details of the selected internship in the right
panel.

Example:
![view_internship](images/view_internship.png)

### Editing Details of internship : `edit`

Edit details of the internship selected by ID.

Format: `edit ID p/[POSITION] c/[COMPANY] s/[STATUS] d/[DESCRIPTION] t/[TAGS]`

* `ID`: Identification number of the Internship.
* `[POSITION]`: Name of Internship Position (optional).
* `[COMPANY]` : Name of hiring company (optional).
* `[STATUS]` : Status of Application (`ACCEPTED`, `APPLIED`, `PENDING`, `REJECTED`) (optional).
* `[DESCRIPTION]`: Additional details of the internship application (optional).
* `[TAGS]`: Labels for the internship application (optional).
* After keying in the `edit` command, along with the selected fields the user wishes to edit, TinS will update the
  corresponding fields of the internship application accordingly.

Example: <br>
After keying in the `edit` command followed by an existing `ID` and the fields `STATUS` and `DESCRIPTION`, TinS
will update the `STATUS` and `DESCRIPTION` fields of the internship application with the specified `ID`, then display
the newly updated internship application

~~~
// input
edit 1 s/1 d/Learn C++
~~~
~~~
//initial
INTERNSHIP ID: 1
POSITION: SOFTWARE ENGINEER
COMPANY NAME: GOOGLE
APPLICATION STATUS: INTERESTED
DESCRIPTION: Should apply

//output
INTERNSHIP ID: 1
POSITION: SOFTWARE ENGINEER
COMPANY NAME: GOOGLE
APPLICATION STATUS: APPLIED
Description: Learn C++
~~~

### Deleting an Internship : `delete`

Deletes an internship along with its details.

Format: `delete ID`
* Deletes the person at the specified `ID` in the Internship List Panel.

Examples:

### Exiting the program : `exit`

Exits and closes the TinS application.

Format: `exit`

### Saving the data

The Intern Ship’s data is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary
<table>

<tr>
<th>Action</th>
<th>Format</th>
</tr>

<tr>
<td><b>Add</b></td>
<td><code>add p/POSITION c/COMPANY_NAME s/APPLICATION_STATUS d/DESCRIPTION [t/TAG] ...</code><br>
E.g. <code>add p/Software Engineer c/Grab s/1 d/Requires knowledge of Python t/Important t/Priority</code></td>
</tr>

<tr>
<td><b>List</b></td>
<td><code>list</td>
</tr>

<tr>
<td><b>Edit</b></td>
<td><code>edit ID [p/POSITION] [c/COMPANY_NAME] [s/APPLICATION_STATUS] [d/DESCRIPTION] [t/TAG] ...</code><br>
E.g. <code>edit 1 p/Data Analyst</code> edits the <code>POSITION</code> of first internship in the Internship List panel
to Data Analyst</td>
</tr>

<tr>
<td><b>Delete</b></td>
<td><code>delete ID</code><br>
E.g. <code>delete 1</code> deletes the first internship in the Internship List panel</td>
</tr>

<tr>
<td><b>Select</b></td>
<td><code>select ID</code><br>
E.g. <code>select 1</code> selects the first internship in the Internship List panel</td>
</tr>

<tr>
<td><b>Help</b></td>
<td><code>help</code></td>
</tr>

<tr>
<td><b>exit</b></td>
<td><code>exit</code></td>
</tr>

</table>
