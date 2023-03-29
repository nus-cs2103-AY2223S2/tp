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

2. Download the latest `MediConnect.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your MediConnect.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar MediConnect.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to MediConnect.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

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

### Adding a person: `add a patient`, `add a doctor`

Adds a person (patient/doctor) to MediConnect.
- Each person can be added only once.
- Each person's NRIC must be distinct.

Format: `addPatient n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]`  <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`addDoctor n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]​`

| Field            | Prefix | Input Restrictions                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
|------------------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Name**         | n/     | 1. Should only contain alphanumeric characters and spaces.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **Phone Number** | p/     | 1. Should only contain numbers. <br> 2. Should be at least 3 digits long.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **Email** | e/     | 1. Should be of the format local-part@domain <br> 2. The local-part should only contain alphanumeric characters and these special characters (+_.-). The local-part may not start or end with any special characters. <br> 3. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods. <br> The domain name must: <br> - end with a domain label at least 2 characters long <br> - have each domain label start and end with alphanumeric characters <br> - have each domain label consist of alphanumeric characters, separated only by hyphens, if any. |
| **NRIC** | ic/    | 1. Should be 9 characters long. <br> 2. The first character must be one of [S,T,F,G,M], followed by 7 numerical digits and ending with [A-Z].                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| **Address** | a/     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
|**Tag** | t/     | 1. Should be alphanumeric.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |

<div markdown="span" class="alert alert-primary">:bulb: **Tips:** <br>
- A person can have any number of tags (including 0) <br>
- All other fields must be filled <br>
- In case of multiple inputs of the same field, only the last one will be stored.
</div>

Examples:
* `addPatient n/Ben Smith p/98353535 e/ben@abc.com ic/S1234567L a/1 Ben Street`
* `addDoctor n/Sarah Tan p/99123456 e/sarah@abc.com ic/T7654321P a/Sarah Rd t/Pediatrician`

### Retrieve person information : `list`

Shows a list of all persons in MediConnect.

Format: `list`

### Prescribing patient’s medication : `prescribe`

Prescribes medication to a patient, and records its cost.

Format: `prescribe ic/NRIC m/MEDICATION c/COST`

Examples:
*  `prescribe m/paracetamol ic/S1234567X c/10` Prescribes paracetamol the patient of IC S1234567X at a cost of $10
*  `prescribe m/Cough Syrup ic/S1234567X c/0.1` Prescribes cough syrup to the patient of IC S1234567X at a cost of $0.10

### Removing patient’s medication : `unprescribe`

Removes a chosen medication from a patient.

Format: `unprescribe ic/NRIC m/MEDICATION`

Examples:
*  `unprescribe ic/S1234567X m/paracetamol` Remove patient of IC S1234567X's paracetamol prescription
*  `unprescribe m/Cough Syrup ic/S1234567X` Remove patient of IC S1234567X's cough syrup prescription

### Bill : `bill`

Calculates the cost of the prescription

Format: `bill ic/NRIC`

Examples:
* `bill ic/S1234567X` Calculates the cost of patient's (of IC S1234567X) medication.

### Finding persons (patient or doctor) by NRIC : `find`

Finds persons with the given NRIC.

Format: `find ic/NRIC`

* Search is case-insensitive. e.g `s1234567x` will match with `S1234567X`.
* Only the NRIC is searched.
* Only full NRIC entries can be searched.

Examples:
* `find ic/S1234567X` returns the details for the person with the given NRIC.

### Deleting a person by name : `delete`

Deletes the specified person from MediConnect.

Format: `delete ic/nric`

* Deletes the person with the specified `nric`

Examples:
* `delete ic/S9876543K` deletes the person with the nric number S9876543K in MediConnect.

### Book appointment : `appointment`

Set an appointment date to the patient and doctor

Format: `appointment r/ROLE n/NAME d/Date`

Examples:
* `appointment patient John 2020-10-12` attaches the date 12 October 2020 to the patient John

### Clearing all entries : `clear`

Deletes all patients' and doctors' data from the system.

Format: `clear`

Example:
* `clear` permanently deletes all data stored in the system.


### Exiting the program : `exit`

Closes the `MediConnect` application

Format: `exit`

Examples:
* `exit` closes the `MediConnect` application immediately.

### Requesting help : `help`

Provides the user with a link to the user guide

Format: `help`

Examples:
* `help` opens a mini window with a url link of the user guide which can be copied by the user.

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

| Action          | Format, Examples                                                                                                                                                                                                                                                                                                                 |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `addPatient n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]…​` <br> e.g., `addPatient n/Ben Smith p/98353535 e/ben@abc.com ic/S1234567L a/1 Ben Street` <br> `addDoctor n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]…​` <br> e.g., `addDoctor n/Sarah Tan p/99123456 e/sarah@abc.com ic/T7654321P a/Sarah Rd` |
| **Delete**      | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                                                                                                                                                                              |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                                                                                                                                                       |
| **List**        | `list`                                                                                                                                                                                                                                                                                                                           |
| **Prescribe**   | `prescribe ic/NRIC m/MEDICATION c/COST` <br> e.g.,     prescribe m/paracetamol ic/S1234567X c/10                                                                                                                                                                                                                                 |
| **Unprescribe** | `unprescribe ic/NRIC m/MEDICATION` <br> e.g.,           unprescribe m/paracetamol ic/S1234567X                                                                                                                                                                                                                                   |
| **Bill**        | `bill ic/NRIC` <br> e.g.,                               cost ic/S1234567X                                                                                                                                                                                                                                                        |
| **Clear**       | `clear`                                                                                                                                                                                                                                                                                                                          |
