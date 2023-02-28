---
layout: page
title: User Guide
---

MediConnect is a desktop app for managing patients, doctors and bills. 
It can be used with either a command line interface (CLI) or a graphical user interface (GUI). 
Users experienced with the CLI may get your tasks done faster than traditional GUI apps.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `MediConnect.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-1/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your MediConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar MediConnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to MediConnect.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Adding a person: `add patient`, `add doctor`

Adds a person (patient/doctor) to MediConnect.

Format: `add r/ROLE n/NAME b/BILL p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add r/patient n/John Doe b/Invoice#001 p/98765432 e/johnd@example.com a/John street, block 123, #01-01`

### Retrieve person information : `list`

Shows a list of all persons in MediConnect.

Format: `list`

### Prescribing patient’s medication : `prescribe medication to patient`

Prescribe a patient’s medication.

Format: `prescribe m/MEDICATION to n/NAME`

Examples:
*  `prescribe m/paracetamol to n/John Tan` Prescribes paracetamol to John Tan


### Finding persons (patient or doctor) by name : `find`

Finds persons who contains any of the given names.

Format: `find r/ROLE n/NAME`

* Search is case-insensitive
* Both role and name are searched


Examples:
* `find Doctor John` returns `john` and `John`
* `find Patient Tim` returns `tim`, `Tim`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person by name : `delete`

Deletes the specified person from MediConnect.

Format: `delete name`

* Shows a list of person with the specified `name`
* User then enters the number assigned to the person to be deleted

Examples:
* `delete John` shows a list of people name John in MediConnect.
* MediConnet prompts: "Enter number to be deleted:"
* User enters `number` to be deleted

### Book appointment : `appointment`

Set an appointment date to the patient and doctor

Format: `appointment r/ROLE n/NAME d/Date`

Examples:
* `appointment patient John 2020-10-12` attaches the date 12 October 2020 to the patient John

### Clearing all entries : `clear`

_Details coming soon ..._

### Exiting the program : `exit`

_Details coming soon ..._

### Saving the data

_Details coming soon ..._

### Editing the data file

_Details coming soon ..._

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add r/ROLE n/NAME b/BILL p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Prescribe** | `prescribe medication to patient`

