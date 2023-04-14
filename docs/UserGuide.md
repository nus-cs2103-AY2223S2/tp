---
layout: page
title: User Guide
---

# Introduction

Streamline your patient management with lightning-fast efficiency using HospiSearch

HospiSearch is a **desktop app for managing hospital/clinic patients' particulars, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface (GUI)**. HospiSearch can also help you complete your patient management tasks faster than traditional GUI apps, regardless of your typing speed.

Our target audience is hospital and clinical administrative staff who deal with the management of large physical patient records and seek a more efficient and streamlined solution. Our app provides them with the ability to add, edit, delete, filter patient records and so much more.

## About this Guide

The purpose of this user guide document is to provide staff with a clear understanding of the features and benefits of HospiSearch. The guide aims to assist users in effectively utilizing HospiSearch's features to streamline their patient management tasks and improve overall efficiency.
<div markdown="span" class="alert alert-info">:information_source: **Note:** Messages in this format are important!
</div>
💡**Tip**:
<br/> Messages in this format include information that may be useful to the user.

<div style="page-break-after: always;"></div>

# Table of Contents:

- [Setup](#setup)
- [GUI Information](#gui-information)
- [Command Prefixes](#command-prefixes)
- [Features](#features)
  - [Add patient](#adding-a-patient-record-add): `add`
  - [Edit patient](#editing-a-patient-record-edit): `edit`
  - [View patient particulars](#viewing-a-patient-detailed-particulars-view): `view`
  - [Delete patient](#deleting-a-patient-record-delete): `delete`
  - [Filter patients](#filtering-patients-by-attribute-find): `find`
  - [List all patients](#listing-all-patients-list): `list`
  - [Backup patient records](#backing-up-patient-records-backup): `backup`
  - [Load backups](#loading-data-load): `load`
  - [View backups](#viewing-backup-data-viewbackups): `viewbackups`
  - [Delete backups](#deleting-backup-data-deletebackup): `deletebackup`
  - [Clear all data](#clearing-all-data-clear): `clear`
  - [Light mode](#switching-to-light-mode-light): `light`
  - [Dark mode](#switching-to-dark-mode-dark): `dark`
  - [Undo](#undoing-previous-command-undo): `undo`
  - [Redo](#redoing-previous-undo-redo): `redo`
  - [Help](#viewing-help--help): `help`
- [FAQ](#faq)
- [Command Summary](#command-summary)
- [Glossary](#glossary)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Setup

1. Ensure you have Java `11` or above installed in your Computer.


2. Download the latest `HospiSearch.jar` from [here](https://github.com/AY2223S2-CS2103T-T11-4/tp/releases).


3. Copy the file to the folder you want to use as the _home folder_ for  HospiSearch.


4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hospisearch.jar`command to run the application.<br>

Note:
  - For Mac Users:
    1. Click on the Finder icon in the Dock (the bar of icons at the bottom of the screen).
    2. Click on Applications in the left-hand sidebar.
    3. Open the Utilities folder.
    4. Double-click on the Terminal application to launch the command terminal.

  - For Windows Users:
    1. Click on the Start button in the bottom left corner of the screen.
    2. Type command prompt into the search bar.
    3. Click on the Command Prompt application that appears in the search results to launch the command terminal.


   A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
   <img src="images/Ui.png" alt="Ui" width="41%">


5. Type in a command in the command box to execute it. Some commands to try:
    1. `help` opens up the help menu.


6. Refer to the [Features](#features) below for details of each command.

Note: HospiSearch is compatible with Windows, MacOS and Ubuntu.

<sub>[return to table of contents](#table-of-contents)</sub>
<div style="page-break-after: always;"></div>

## GUI Information

![DefinedGUI](images/DefinedGUI.png)

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Command Prefixes

| Patient Record Field | Prefix |
|----------------------|--------|
| NRIC                 | i/     |
| Date Of Birth        | dob/   | 
| Name                 | n/     |  
| Phone                | p/     |  
| Gender               | g/     |  
| Doctor               | ad/    |  
| Email                | e/     |  
| Address              | a/     | 
| Tag                  | t/     |
| Drug Allergy         | d/     |
| Medicine             | m/     |
| Backup description   | b/     |

<sub>[return to table of contents](#table-of-contents)</sub>
<div style="page-break-after: always;"></div>

## Features

### Notes:

- Input words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.


- Commands in square brackets are optional. e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.


- Commands separated by `|` within `()` suggest that only one of the commands must be present to be valid.


- Items with `…` after them can be used multiple times, including zero. e.g., [t/TAG] …​ can be used
  (i.e. 0 or more times), t/friend t/family etc.


- Parameters can be in any order. e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.


- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored. e.g., if the command specifies `help 123`, it will be interpreted as `help`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** All commands from this point onwards are to be
executed in lower case. E.g., `find` is valid, but `FIND` or `FinD` is considered as invalid. Keywords following commands do
not need to be in lower case.
</div>
<div style="page-break-after: always;"></div>

### Adding a patient record: `add`

Let's say a patient is visiting your clinic for the first time! Use the `add` command to add the patient records to the record list.

Format: `add i/NRIC n/NAME dob/DATE OF BIRTH p/PHONE a/ADDRESS d/DRUGALLERGIES g/GENDER ad/DOCTOR [e/EMAIL] [t/TAG]…​ [m/MEDICINE]…​`

<div markdown="span" class="alert alert-info">:information_source: **Note:** Do take note which patient fields are
optional.
</div>

💡**Tip**:
<br/>The drug allergies field is required however if the patient you are registering does not have any drug allergies, type in NKDA(No Known Drug Allergies)
<br/>A patient can have any number of tags and medicine (including 0).
<br/>A patient can leave out the email prefix should they not want to share it.

Examples:

* `add i/T0012345A n/John Doe dob/20/12/2000 p/98765432 a/John street, block 123, #01-01 d/NKDA g/male ad/Alex t/Diabetic m/Lantus`
* `add i/T0012345B n/Betsy Crowe dob/18/12/1998 p/1234567 a/Newgate Prison d/Panadol g/female ad/Shannon e/betsycrowe@example.com t/Dyslexic`

<img src="images/command_result/Add.png" alt="Add" width="63%">

Above is the execution result of the input `add i/S1234567H n/Cedric Pei dob/03/04/2002 p/84655284 a/PGPR g/male d/NKDA ad/Shannon t/Diabetic m/Lantus m/Soliqua`.
<div style="page-break-after: always;"></div>

### Editing a patient record: `edit`

Let's say a patient has moved to a different address or undergone significant changes in their medical history. Use the `edit` command to edit their personal particulars.

Format: `edit INDEX [i/NRIC] [n/NAME] [dob/DATE OF BIRTH] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/DRUGALLERGIES] [g/GENDER] [ad/DOCTOR] [t/TAG]…​ [m/MEDICINE]…​`

<div markdown="span" class="alert alert-info">:information_source: **Note:** Only include the prefixes for the specific
patient field you would like to edit.
</div>

💡**Tip**:
<br/>You can remove all the patient’s tags/medicine by typing t/ or m/ respectively, without specifying any tags/medicine after it.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` edit the phone number and email address of the patient at index 1 to be 91234567 and johndoe@example.com respectively.

<img src="images/command_result/Edit.png" alt="Edit" width="80%">

Above is the execution result of the input `edit 7 i/G0000000A`.
<div style="page-break-after: always;"></div>

### Viewing a patient detailed particulars: `view`

To reduce clutter, we have placed the more in-depth information of a patient in the view panel to the right of the patient list.
Use the `view` command to view these additional details of a patient.

Format : `view i/NRIC`

<div markdown="span" class="alert alert-info">:information_source: **Note:** For other commands, the view pane will show the particulars of the first patient in our system by default.
But after ADD or EDIT command, the view pane will show the corresponding patient's particulars.
</div>

Examples:
* `view i/G0000000A` shows detailed information about the patient on the view pane.

![View](images/command_result/View.png)

Above is the execution result of the input `view i/S1234567H`.
<div style="page-break-after: always;"></div>

💡**Tip**:
Double-clicking on a patient achieves the same result!

### Deleting a patient record: `delete`

Let's say you have found an unintended duplicate in the patient records. You can delete this erroneous data using the `delete` command.

Format: `delete i/NRIC…​`

<div markdown="span" class="alert alert-info">:information_source: **Note:** If multiple deletes are made in a single
command, `undo` command will not undo all deletes made, but only **one** at a time.
</div>

Examples:

* `delete i/S1234567A` deletes the patient with NRIC S1234567A from patient records system.
<div style="page-break-after: always;"></div>

### Filtering patients by attribute: `find`

Let's say you want to filter for specific patients quickly during a busy shift. You can use the `find` command to search for patients
based on their name, age, medical condition, or any other relevant criteria.

Command Prefixes that can be searched:

* name(`n/`)
* nric(`i/`)
* tag(`t/`)
* doctor(`ad/`)
* medicine(`m/`)

Format: `find (n/NAME | i/NRIC | t/TAG | ad/DOCTOR | m/MEDICINE ) [MORE_KEYWORDS]`

<div markdown="span" class="alert alert-info">:information_source:
**Note:** `find` searches by **complete strings** and not **substrings**. The search will only be carried out for
**one** given attribute.
</div>

<div markdown="span" class="alert alert-info">:information_source:
**Note:** The search is **case-insensitive**. The order of the keywords does not matter.
</div>


Examples (The following results are based of the sample data provided):

* `find n/john` returns `John Lim` and `John Doe` who both contain the name `John` in their names.
* `find n/yu bernice` returns `Bernice Yu` as the order of keywords does not matter
* `find i/S0078957G` returns `Alice Tan` who has an NRIC of `S0078957G`.
* `find t/Diabetic Osteoporotic` returns all persons with the tag `Diabetic` or `Osteoporotic` or both.
* `find ad/Shannon` returns all persons with attending doctor `Shannon`.

<img src="images/command_result/Find.png" alt="Find" width="51%">

Above is the execution result of the input `find ad/Alex`.
<div style="page-break-after: always;"></div>

### Listing all patients: `list`

You can use this feature to get a list of all patients in the records.

Format: `list`

<div markdown="span" class="alert alert-info">:information_source: **Note:** List returns the entire current database
that you have loaded.
</div>

💡**Tip**:
<br/>After filtering for specific patients, you can view all patients by using the `list` command.

![List](images/command_result/List.png)

Above is the execution result of the input `list`.
<div style="page-break-after: always;"></div>

### Backing up patient records: `backup`

Let's say you want to safekeep your current data for future reference. You can use the `backup` command to save the patient records to a specified slot represented by an index and add a description for the backup.

Format: `backup INDEX_NO [b/DESCRIPTION]`


💡**Tip**:
<br/>Backing up without a description will leave the description field empty.

Format: `backup INDEX_NO [b/DESCRIPTION]`


Examples:


* `backup 3 b/Test` backups the data to the 3rd slot with description 'Test'.


<div markdown="span" class="alert alert-info">:information_source: **Note:**: INDEX_NO can only be an integer between 1 and 10
</div>

<img src="images/command_result/Backup.png" alt="Backup" width="80%">

Above is the execution result of the input `backup 1`.
<div style="page-break-after: always;"></div>

### Loading data: `load`

In the event of a data loss, you can load the backup data from a specified slot represented by an index.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Allows access to a specific backup.
</div>

Format: `load INDEX_NO`

Example:

* `load 3` loads the data from the 3rd slot.
<div style="page-break-after: always;"></div>

### Viewing backup data: `viewbackups`

You can use this feature to shows all the data you have backed up.

Format: `viewbackups`

💡**Tip**:
<br/>You can exit the backups page by typing the command `list`.

![Viewbackups](images/command_result/Viewbackups.png)

Above is the execution result of the input `viewbackups`.
<div style="page-break-after: always;"></div>

### Deleting backup data: `deletebackup`

You can use this feature to delete the backup data from a specified slot represented by an index.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Deleted backups cannot be retrieved after
deletion.
</div>

Format: `deletebackup INDEX_NO`

Example:

* `deletebackup 3` deletes the data from the 3rd slot.
<div style="page-break-after: always;"></div>

### Clearing all data: `clear`

Purges all data from the database.

<div markdown="span" class="alert alert-info">:information_source: **Note:** Data cannot be retrieved after clearing.
Use this command with caution.
</div>

Format: `clear`

![Clear](images/command_result/Clear.png)

Above is the execution result of the input `clear`.
<div style="page-break-after: always;"></div>

### Undoing previous command: `undo`

Let's say you have made an error when editing or deleting patient records. You can use this command to revert the patient records to the state before the previous command was executed.

Format: `undo`

<div markdown="span" class="alert alert-info">:information_source: **Note:** The initial starting state will be upon
launch of HospiSearch application. Undo can be executed up to the initial starting state.
</div>

<br/><br/>![Undo-1](images/command_result/Undo-1.png)

Changing the name of a patient above `edit 6 n/John Balakrishnan` (Previously Roy Balakrishnan)

<br/><br/>![Undo-2](images/command_result/Undo-2.png)

And above is the execution of `undo`
<div style="page-break-after: always;"></div>

### Redoing previous undo: `redo`

Let's say you have accidentally removed the changes you made. You can use this command to revert the patient records to the state before the previous undo was executed.

<div markdown="span" class="alert alert-info">:information_source:**Note:** Redo can only be executed if undo command
has been executed.
</div>

Format: `redo`

![Redo](images/command_result/Redo.png)


Following the above example for `undo`, `redo` would subsequently change the name of the patient again.
<div style="page-break-after: always;"></div>

### Switching to light mode: `light`

Switches the GUI to light mode. This mode is preferred during daytime as it allows for better visibaility.

Format: `light`

![Light](images/command_result/Light.png)

Above is the execution result of the input `light`.
<div style="page-break-after: always;"></div>

### Switching to dark mode: `dark`

Switches the GUI to dark mode. This mode is preferred at night as it reduces strain to the eyes.

Format: `dark`

💡**Tip**:
<br/>The default GUI is light mode. Use these commands and see which is your preferred GUI!

![Dark](images/command_result/Dark.png)

Above is the execution result of the input `dark`.
<div style="page-break-after: always;"></div>

### Viewing help : `help`

In case you need help navigating HospiSearch, you can use this feature which lists out all the commands available, along with a brief description.

Format: `help`

![Help](images/command_result/Help.png)

Above is the view of help window after the input `help`.

--------------------------------------------------------------------------------------------------------------------
<sub>[return to table of contents](#table-of-contents)</sub>
<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my data to another Computer? <br>
**A**: Install the app on the other computer and overwrite all files in the `data` directory it creates with the files from your previous HospiSearch `data` directory.

**Q**: Where is my HospiSearch `data` directory? <br>
**A**: The `data` directory is created in the same folder your HospiSearch Jar file is opened in.

--------------------------------------------------------------------------------------------------------------------
<sub>[return to table of contents](#table-of-contents)</sub>
<div style="page-break-after: always;"></div>

## Command summary

| Action             | Format, Examples                                                                                                                                                                                                                                                         |
|--------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|  
| **Add**            | `add i/NRIC n/NAME dob/DATE OF BIRTH p/PHONE a/ADDRESS d/DRUGALLERGIES g/GENDER ad/DOCTOR [e/EMAIL] [t/TAG]…​ [m/MEDICINE]…​` <br/> e.g. add i/T0012345A n/John Doe dob/20/12/2000 p/98765432 a/John street, block 123, #01-01 d/NKDA g/male ad/Alex t/Diabetic m/Lantus | 
| **Edit**           | `edit INDEX [i/NRIC] [n/NAME] [dob/DATE OF BIRTH] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/DRUGALLERGIES] [g/GENDER] [ad/DOCTOR] [t/TAG]…​ [m/MEDICINE]…​` <br/> e.g. edit 1 p/91234567 e/johndoe@example.com                                                                  |  
| **View**           | `view i/NRIC` <br/> e.g. find i/S1234567H                                                                                                                                                                                                                                |  
| **Delete**         | `delete i/NRIC…​` <br/> e.g. delete i/T0012345A                                                                                                                                                                                                                          |  
| **Find**           | `find attribute/KEYWORD [MORE_KEYWORDS]` <br/> e.g. find a/Alex  <br/> e.g. find t/diabetic                                                                                                                                                                              |  
| **List**           | `list`                                                                                                                                                                                                                                                                   |  
| **Backup**         | `backup INDEX_NO [b/Description]` <br/> e.g. backup 3 b/Day shift                                                                                                                                                                                                        |  
| **Load**           | `load INDEX_NO` <br/> e.g. load 3                                                                                                                                                                                                                                        |  
| **View backups**   | `viewbackups`                                                                                                                                                                                                                                                            |  
| **Delete backups** | `deletebackup INDEX_NO` <br/> e.g. deletebackup 3                                                                                                                                                                                                                        |  
| **Clear all**      | `clear`                                                                                                                                                                                                                                                                  |  
| **Undo**           | `undo`                                                                                                                                                                                                                                                                   |  
| **Redo**           | `redo`                                                                                                                                                                                                                                                                   |  
| **Light**          | `light`                                                                                                                                                                                                                                                                  |  
| **Dark**           | `dark`                                                                                                                                                                                                                                                                   |
| **Help**           | `help`                                                                                                                                                                                                                                                                   |  


<sub>[return to table of contents](#table-of-contents)</sub>

--------------------------------------------------------------------------------------------------------------------
<div style="page-break-after: always;"></div>

## Glossary

Below is a glossary table for all the technical terms defined in this user guide.

| Term                           | Definition                                                                                                                                              |
|--------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| Command                        | An instruction for HospiSearch to perform (See [Command Summary](#command-summary))                                                                     |
| Command Line Interface (CLI)   | A type of user interface that allows users to interact with a computer system by entering text-based commands.                                          |
| Graphical User Interface (GUI) | A type of user interface that uses graphics and visual indicators such as icons, buttons, and menus to enable users to interact with a computer system. |
| Field                          | Details that are attributed to each patient. For example, Name, NRIC, and Date of Birth are all patient fields.                                         |
| Prefix                         | A short form that is attributed to each field when inputing patient data. For example, the field 'Name' has the prefix `\n`.                            |


<sub>[return to table of contents](#table-of-contents)</sub>
