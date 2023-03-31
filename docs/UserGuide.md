---
layout: page
title: User Guide
---

## Introduction
MedInfo is a **desktop app for managing patients and wards for private hospitals, 
optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, MedInfo can get your patient management tasks done faster than traditional GUI apps.

This is a User Guide for MedInfo that will guide you through installing the app to getting familiarised
with its features and using it. You are a **hospital staff member** with decent typing ability and prefer typing
over voice or mouse commands to do your tasks.

Navigating this User Guide is simple with the Table of Contents with hyperlinks to every segment below, and a hyperlink
on every page to take you back to the Table of Contents. To further augment your navigation, press
<kbd>Ctrl</kbd> + <kbd>F</kbd> to search for a keyword directly.

## Table of Contents
* Table of Contents
{:toc}

---
## About MedInfo
MedInfo is a very simple and intuitive application that will help you manage your patients' **status, ward,
discharge date** and your wards' **occupancies**. <br>
<br>
With the time-critical nature of a private hospital's services, patient and ward management are
of utmost priority, where the smallest delays or lapses in updating information can affect
how your hospital attends to your patients. **MedInfo** has simplified, focused patient and ward management down to a
tee, allowing you to in-process, update, and move patients around while looking at the overall stats of the hospital.<br>
<br>
[Back to Table of Contents](#table-of-contents)

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `medinfo.jar` from [here](https://github.com/AY2223S2-CS2103T-T12-2/tp/releases/tag/v1.3-trial).

1. Copy the file to the folder you want to use as the _home folder_ for your MedInfo.

1. Run the application by
   2. Double-clicking the jar file or 
   2. Opening a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar medinfo.jar` command to run the application.<br>
1. A GUI similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br>
      ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all patients.

   - `add nric/S1234567A name/John Doe` : Adds a patient named `John Doe` to MedInfo.

   - `delete 1` : Deletes the first patient on the currently displayed list

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

[Back to Table of Contents](#table-of-contents)

---
## User Interface Overview

![UiOverview](images/UiOverview.png)
## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add nric/NRIC name/NAME`, `NRIC` and `NAME` are parameters which can be used as `add nric/S1234567A name/John Doe`.

- Items in square brackets are optional.<br>
  e.g `name/NAME [s/STATUS]` can be used as `name/John Doe s/GREEN` or as `name/John Doe`.

[//]: # 'Might be used in future features'
[//]: # '- Items with `…` after them can be used multiple times including zero times.<br>'
[//]: # '  e.g. `[t/TAG]…` can be used as `` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.'

- Parameters can be in any order.<br>
  e.g. if the command specifies `name/NAME s/STATUS`, `s/STATUS name/NAME` is also acceptable.

- If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `nric/S1234567X nric/S1234567A`, only `nric/S1234567A` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

<!-- INSERT SCREENSHOT PREVIEW HERE -->
![help message](images/helpMessage.png)

Format: `help`

### Adding a patient to the system: `add`

Adds the patient (NRIC, name and status).

Format: `add nric/NRIC name/NAME [s/STATUS]`

<!-- EXAMPLE OF TIP -->
<div markdown="span" class="alert alert-primary">:bulb: **Tips:** <br/>
- The default condition is set to `GRAY`. <br/>
- The default ward is set to `Waiting Room`. <br/>
- No discharge date is added by default.
</div>

Examples:

- `add nric/S1234567A name/John Doe s/RED`

### Listing all patients in the system: `list`

Shows a list of all patients with their details in the system.

Format: `list`

### Editing a patient’s details in the system: `edit`

Edit an existing patient’s status or ward or discharge date-time.

Format: `edit INDEX [s/STATUS] [w/WARD] [d/DISCHARGE]`

- Edits the patient's details at the specified index as of the currently displayed list.
- The status of a patient is either `GRAY` or `GREEN` or `YELLOW` or `RED`.
- The ward allocated to a patient is represented as an alphanumeric string. E.g `A01`.
- The discharge date-time is of the `dd/MM/yyyy HHmm` format. E.g `12/03/2023 1200` is interpreted as 12th March 2023 1200hrs.

Examples:

- `edit 1 s/GREEN` Edits the status of the first currently displayed patient to be `GREEN`.
- `edit 5 w/A01` Edits the ward of the fifth currently displayed patient to be `A01`.
- `edit 4 d/27/07/2023 1600` Edits the discharge date-time of the fourth currently displayed patient to be `27/07/2023 1600` which is read as 27th July 2023 1600hrs.

### Sorting all patients in the system: `sort`

Sorts all the patients with the specified field and order in the system.

Format: `sort FIELD/ORDER`

Examples:

- `sort s/asc`

### Finding patients by name in the system: `find`

Shows a list of all patients with their details that match input name or NRIC.

Format: `find name/NAME`, `find nric/NRIC`, `find s/STATUS`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Either only the name or only the NRIC is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find name/John` returns `john` and `John Smith`
- `find name/john carlos` returns `John Smith`, `Carlos Lopez`<br>
  ![result for 'find alex david'](images/findJohnCarlosResult.png)

### Deleting a patient from the system: `delete`

Deletes a patient by index.

Format: `delete INDEX`

- Deletes the patient at the specified index as of the currently displayed list.

Examples:

`delete 1`

### Adding a ward to the system: `addward`

Adds the ward (name and capacity).

Format: `addward w/WARD [c/CAPACITY]`

- The ward name is represented as an alphanumeric string. E.g `A01`.
- The capacity is a positive integer. E.g `50`.

<!-- EXAMPLE OF TIP -->
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The default capacity is set to 10.
</div>

Examples:

- `addward w/A01 c/25` Adds the ward with name `A01` and capacity `25` to the system.

### Editing a ward's details in the system: `editward`

Edit an existing ward's name or capacity.

Format: `editward INDEX [w/WARD] [c/CAPACITY]`

- Edits the ward's details at the specified index as of the currently displayed list.
- The given capacity has to be an integer.
- The given capacity has to be greater or equal to the ward's current occupancy

Examples:

- `editward 1 w/A02` Edits the name of the first currently displayed ward to be `A02`.
- `editward 5 c/35` Edits the capacity of the fifth currently displayed ward to be `35`.

### Deleting a ward from the system: `deleteward`

Deletes a ward by index.

Format: `deleteward INDEX`

- Deletes the ward at the specified index as of the currently displayed list.

Examples:

`deleteward 1`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data
MedInfo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

MedInfo data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update the data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file make its format invalid, MedInfo will not be able to run.
</div>

---

## FAQ :raising_hand:

**Q**: I keep forgetting the commands, is there a quick way to get help?<br>

**A**: Yes! Entering the `help` command will show a message explaining how to access the help page.<br>
<br>

**Q**: Why can't I remove the waiting room?<br>

**A**: As every hospital would have some pre-screening room for patients to wait in, and to make it easier 
to start entering patients into the system, the waiting room is made un-deletable.<br>
<br>

**Q**: How do I transfer my data to another device/computer?<br>

**A**: Install the app in the other device/ computer and overwrite the empty data file it creates with 
the file that contains the data of your previous MedInfo home folder.<br>
<br>

---

## Command summary

| Action          | Format, Examples                                                                           |
|-----------------|--------------------------------------------------------------------------------------------|
| **Add**         | `add nric/NRIC name/NAME [s/STATUS]` <br> e.g., `add nric/S1234567A name/John Doe s/GREEN` |
| **Delete**      | `delete INDEX`<br> e.g., `delete 1`                                                        |
| **Edit**        | `edit INDEX [s/STATUS] [w/WARD] [d/DISCHARGE]`<br> e.g.,`edit 1 s/GREEN`                   |
| **Find**        | `find name/NAME` or `find nric/NRIC` or `find s/STATUS`<br> e.g., `find name/John`         |
| **Add Ward**    | `addward w/WARD [c/CAPACITY]` <br> e.g., `addward w/S1234567A c/25`                        |
| **Edit Ward**   | `editward INDEX [w/WARD] [c/CAPACITY]` <br> e.g., `editward 1 w/A02 c/35`                  |
| **Delete Ward** | `deleteward INDEX` <br> e.g., `deleteward 1`                                               |
| **List**        | `list`                                                                                     |
| **Help**        | `help`                                                                                     |
| **Sort**        | `sort FIELD/ORDER` <br> e.g., `sort name/asc`                                              |
| **Delete Ward** | `deleteward INDEX` <br> e.g., `deleteward 1`                                               |
