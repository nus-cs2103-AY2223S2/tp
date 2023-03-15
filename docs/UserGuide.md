---
layout: page
title: User Guide
---

MedInfo is a **desktop app for managing patients, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MedInfo can get your patient management tasks done faster than traditional GUI apps.

- Table of Contents
  {:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `medinfo.jar` from [here](https://github.com/AY2223S2-CS2103T-T12-2/tp).

1. Copy the file to the folder you want to use as the _home folder_ for your MedInfo.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar medinfo.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all patients.

   - `add nric/S1234567A name/John Doe cond/Ligma` : Adds a patient named `John Doe` to MedInfo.

   - `delete nric/S1234567A` : Deletes the newly added patient named `John Doe`'

   - `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [cond/CONDITION]` can be used as `n/John Doe cond/Ligma` or as `n/John Doe`.

[//]: # 'Might be used in future features'
[//]: # '- Items with `…`​ after them can be used multiple times including zero times.<br>'
[//]: # '  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.'

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `nric/S1234567X nric/S1234567A`, only `nric/S1234567A` will be taken.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

<!-- INSERT SCREENSHOT PREVIEW HERE -->
<!-- ![help message](images/helpMessage.png) -->

Format: `help`

### Adding a patient to the system: `add`

Adds the patient (NRIC, name and condition).

Format: `add nric/NRIC name/NAME cond/CONDITION​`

<!-- EXAMPLE OF TIP -->
<!-- <div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A patient can have any number of tags (including 0)
</div> -->

Examples:

- `add nric/S1234567A name/John Doe cond/Ligma`

### Listing all patients in the system: `list`

Shows a list of all patients with their details in the system.

Format: `list`

### Editing a patient’s condition in the system: `edit`

Edit an existing patient’s condition.

Format: `edit nric/NRIC cond/NEW_CONDITION​`

Examples:

- `edit nric/S1234567A cond/Pneumonia` Edits the condition of the patient with NRIC `S1234567A` to be `Pneumonia`.

### Finding patients by name in the system: `find`

Shows a list of all patients with their details that match input name or NRIC.

Format: `find name/NAME`, `find nric/NRIC`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Either only the name or only the NRIC is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a patient from the system: `delete`

Delete patient by NRIC.

Format: `delete nric/NRIC`

- Deletes the patient at the specified `NRIC`.

Examples:

`delete nric/S1234567A`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data [coming soon]

MedInfo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file [coming soon]

_Details coming soon ..._

---

<!-- MedInfo data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file. -->

<!-- <div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div> -->

### Archiving data files `[coming soon]`

_Details coming soon ..._

---

## FAQ

**Q**: I keep forgetting the commands, is there a quick way to get help?<br>

**A**: Yes! Entering the `help` command will show a message explaining how to access the help page.

[//]: # 'Coming soon'
[//]: # '**Q**: How do I transfer my data to another Computer?<br>'
[//]: #
[//]: # '**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous MedInfo home folder.'

---

## Command summary

| Action     | Format, Examples                                                                                  |
| ---------- | ------------------------------------------------------------------------------------------------- |
| **Add**    | `add nric/NRIC name/NAME cond/CONDITION​` <br> e.g., `add nric/S1234567A name/John Doe cond/Ligma |
| **Delete** | `delete nric/NRIC`<br> e.g., `delete nric/S1234567A`                                              |
| **Edit**   | `edit nric/NRIC cond/NEW_CONDITION​`<br> e.g.,`edit nric/S1234567A cond/Pneumonia`                |
| **Find**   | `find name/NAME` or `find nric/NRIC`<br> e.g., `find name/John`                                   |
| **List**   | `list`                                                                                            |
| **Help**   | `help`                                                                                            |
