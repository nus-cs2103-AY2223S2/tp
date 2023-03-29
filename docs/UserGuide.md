---  
layout: page 
title: User Guide
---  

# Introduction

Streamline your patient management with lightning-fast efficiency using HospiSearch - the CLI optimized GUI desktop app.

HospiSearch is a **desktop app for managing hospital/clinic patients' particulars, optimized for use via a 
Command Line Interface** (CLI) while still having the benefits of a **Graphical User Interface (GUI)**. 
If you can type fast, HospiSearch can get your contact management tasks done faster than traditional GUI apps.

Our target audience is hospital and clinical administrative staff who deal with the management of large physical 
patient records and seek a more efficient and streamlined solution.

## About this Guide

The purpose of this user guide document is to provide staff with a clear understanding of the features and benefits 
of HospiSearch. The guide aims to assist users in effectively utilizing HospiSearch's features to streamline 
their patient management tasks and improve overall efficiency.

# Table of Contents:

- [Setup](#setup)
- [GUI Information](#setup)
- [Command Prefixes](#command-prefixes)
- [Features](#features)
    - [Help](#viewing-help--help) `help`
    - [Undo](#undoing-previous-command--undo) `undo`
    - [Redo](#redoing-previous-undo--redo) `redo`
    - [Add patient](#adding-patient-record--add): `add`
    - [Edit patient](#editing-a-patient-record--edit): `edit`
    - [Delete patient](#deleting-a-patient-record--delete): `delete`
    - [List all patients](#listing-all-patients--list): `list`
    - [Filter patients](#find-patients-by-nric-name-address-or-tags--find): `find`
    - [Backup patient records](#backup-patient-records--backup): `backup`
    - [Load backups](#load-data--load): `load`
    - [View backups](#view-backup-data--viewbackup): `viewbackup`
    - [Delete backup](#delete-backup-data--deletebackup): `deletebackup`
    - [Clear all data](#clearing-all-data--clear): `clear`
    - [Light mode](#switch-to-light-mode--light): `light`
    - [Dark mode](#switch-to-dark-mode--dark): `dark`
    - [FAQ](#faq)
- [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------  

## Setup

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `HospiSearch.jar` from [here](https://github.com/AY2223S2-CS2103T-T11-4/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your HospiSearch.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hospisearch.jar`  
   command to run the application.<br>  
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>  
   ![Ui](images/Ui.png)
5. Type in a command in the command box to execute it. Some commands to try:
   1. `help` opens up the help menu.
6. Refer to the [Features](#features) below for details of each command.

Note: HospiSearch is compitable with Windows, MacOS and Ubuntu.

<sub>[return to table of contents](#table-of-contents-)</sub>

## GUI Information

ADD PICS N STUFF HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!
  
--------------------------------------------------------------------------------------------------------------------  

## Command Prefixes

| Patient Record Field | Prefix |
|----------------------|--------|
| NRIC                 | i/     | 
| Name                 | n/     |  
| Phone                | p/     |  
| Gender               | g/     |  
| Email                | e/     |  
| Address              | a/     | 
| Tag                  | t/     |
| Drug Allergy         | d/     |
| Medicine             | m/     |
| Backup description   | b/     |

<sub>[return to table of contents](#table-of-contents-)</sub>

## Features

### Notes:

- Words in `UPPER_CASE` are the parameters to be supplied by the user. e.g. in `add n/NAME`, `NAME` is a parameter
  which can be used as `add n/John Doe`.

- Items in square brackets are optional. e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…` after them can be used multiple times, including zero. e.g. [t/TAG] …​ can be used
(i.e. 0 or more times), t/friend t/family etc.
- Parameters can be in any order. e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is
  also acceptable.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will
  be ignored. e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Viewing help : `help`

Lists out all the commands available, along with a brief description.

Format: `help`

### Undoing previous command: `undo`

Reverts the patient records to the state before the previous command was executed.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The initial starting state will be upon
launch of HospiSearch application. Undo can be executed up to the initial starting state.

Format: `undo`

### Redoing previous undo: `redo`

Reverts the patient records to the state before the previous undo was executed. 

Format: `redo`

### Adding patient record: `add`

Adds a person to the patient records.

Format: `add i/NRIC n/NAME p/PHONE a/ADDRESS d/DRUG ALLERGIES g/GENDER ad/DOCTOR [e/EMAIL] [t/TAG]…​ [m/MEDICINE]…​`

**Tip**:
A patient can have any number of tags and medicine (including 0)

Examples:

* `add i/T0012345A n/John Doe p/98765432 a/John street, block 123, #01-01 d/NKDA g/Male ad/Alex e/johnd@example.com t/Diabetic m/Lantus`
* `add i/T0012345B n/Betsy Crowe p/1234567 a/Newgate Prison d/Panadol g/Female ad/Shannon e/betsycrowe@example.com t/Dyslexic`

### Editing a patient record: `edit`

Edits an existing patient in the patient records.

Format: `edit INDEX [i/NRIC] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/DRUGALLERGY] [g/GENDER] [ad/DOCTOR] [t/TAG]…​ [m/MEDICINE]…​`

* You can remove all the patient’s tags/medicine by typing t/ or m/ respectively, without specifying any tags/medicine
  after it.

Examples:

* `edit 1 p/91234567 e/johndoe@example.com` edit the phone number and email address of the patient at index 1 to be  
  91234567 and johndoe@example.com respectively

### Deleting a patient record: `delete`

Deletes the specified patient from the patient records.

Format: `delete i/NRIC…​`

Examples:

* `delete i/T0012345A` deletes the patient with NRIC T0012345A from patient records system

### Listing all patients: `list`

Shows a list of all patients in the patient records.

Format: `list`

### Find patients by nric, name, address or tags: `find`

Find patients according to a particular attribute stated followed by the change.

Eg. name(`n/`), address(`a/`), nric(`i/`), tag(`t/`)

Format: `find attribute/keyword [MORE_KEYWORDS]`

* The search will only be carried out for the given attribute
* Only one attribute can be searched at one time
* The search is case-insensitive. e.g panadol will match pANAdol
* The order of the keywords does matters. e.g. "panadol" will match "medicine panadol"
* Can input multiple keywords for a given attribute and all matching persons will be returned

Examples:

* `find n/john` returns `John Lim` and `John Doe` who both contain the name `John` in their names
* `find a/serangoon` returns `Alice Tan` and `John Doe` who have an address located in `Serangoon`
* `find i/S0078957G` returns `Alice Tan` who has an NRIC of `S0078957G`
* `find a/ang mo kio serangoon` returns 'Alice Tan', 'John Doe', 'John Lim' who all stay either in `ang mo kio`  
  or  `serangoon`
* `find t/Diabetic` returns all persons with the tag `Diabetic`
* `find t/Diabetic Osteoporotic` returns all persons with the tag `Diabetic` or `Osteoporotic` or both.

### Backup patient records: `backup`

Backs up the patient records to a specified slot represented by an index

Format: `backup INDEX_NO`

Examples:

* `backup 3` backups the data to the 3rd slot

Tip: INDEX_NO can only be an integer between 1 and 10

### Load data: `load`

Loads the data from a specified slot represented by an index

Format: `load INDEX_NO`

Example:

* `load 3` loads the data from the 3rd slot

### View backup data: `viewbackup`

Shows all the backups available

Format: `viewbackup`

### Delete backup data: `deletebackup`

Deletes the data from a specified slot represented by an index

Format: `deletebackup INDEX_NO`

Example:

* `deletebackup 3` deletes the data from the 3rd slot.

### Clearing all data: `clear`

Purges all data from the database

Format: `clear`

### Switch to light mode: `light`

Switch to light mode.

Format: `light`

### Switch to dark mode: `dark`

Switch to dark mode.

Format: `dark`
  
--------------------------------------------------------------------------------------------------------------------  
<sub>[return to table of contents](#table-of-contents-)</sub>

## FAQ

**Q**: How do I transfer my data to another Computer?<br>  
**A**: Install the app on the other computer and overwrite the empty data file it creates with the file that contains  
the data of your previous HospiSearch home folder.
  
--------------------------------------------------------------------------------------------------------------------  
<sub>[return to table of contents](#table-of-contents-)</sub>

## Command summary

|    Action          | Format, Examples                                                                                                                                                                                              |
|--------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|  
| **Help**           | `help`                                                                                                                                                                                                        |  
| **Undo**           | `undo`                                                                                                                                                                                                        |  
| **Redo**           | `redo`                                                                                                                                                                                                        |  
| **Add**            | `add i/NRIC n/NAME p/PHONE a/ADDRESS d/DRUG ALLERGIES g/GENDER m/MEDICINE [e/EMAIL] [t/TAG]…​` <br/> e.g. add i/S1234567A n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 g/Male d/NKDA | 
| **Edit**           | `edit INDEX [i/NRIC] [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [d/DRUGALLERGY] [g/GENDER] [t/TAG]…​` <br/> e.g. edit 1 p/91234567 e/johndoe@example.com                                                        |  
| **Delete**         | `delete i/NRIC…​` <br/> e.g. delete i/T0012345A                                                                                                                                                               |  
| **List**           | `list`                                                                                                                                                                                                        |  
| **Find**           | `find attribute/KEYWORD [MORE_KEYWORDS]` <br/> e.g. find a/Alex  <br/> e.g. find t/diabetic                                                                                                                   |  
| **Backup**         | `backup INDEX_NO` <br/> e.g. backup 3                                                                                                                                                                         |  
| **Load**           | `load INDEX_NO` <br/> e.g. load 3                                                                                                                                                                             |  
| **View backups**   | `viewbackups`                                                                                                                                                                                                 |  
| **Delete backups** | `deletebackups INDEX_NO` <br/> e.g. deletebackups 3                                                                                                                                                           |  
| **Clear all**      | `clear`                                                                                                                                                                                                       |  
| **Light**          | `light`                                                                                                                                                                                                       |  
| **Dark**           | `dark`                                                                                                                                                                                                        |

<sub>[return to table of contents](#table-of-contents-)</sub>
