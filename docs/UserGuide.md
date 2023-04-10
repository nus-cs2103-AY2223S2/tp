---
layout: page
title: User Guide
---

Introducing **MediConnect** - the hospital management application designed to  **streamline patient management,
doctor coordination, and hospital billing.** As a centralised platform, **MediConnect** offers healthcare
professionals and administrative staff an efficient solution to manage administrative matters in local clinics.
The app simplifies the organization and maintenance of patient and doctor data, appointments and billing records. <br>
**MediConnect** can be used with either a command line interface (CLI) or a graphical user interface (GUI).
Users experienced with the CLI may get their tasks done faster than traditional GUI apps. <br>

_With MediConnect, managing your healthcare practice has never been easier._

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
   * If you are unsure of your system's Java version, you may use [this guide](https://www.java.com/en/download/help/version_manual.html) to find out.

2. Download the latest `MediConnect.jar` from [here](https://github.com/AY2223S2-CS2103T-W13-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your MediConnect.

4. Double-click the file to start the app.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all stored patients and doctors.

   * `addPatient n/Ben Smith p/98353535 e/ben@abc.com ic/S1234567A a/Ben Street, block 13, #01-01` : Adds a patient named `Ben Smith` to MediConnect.

   * `delete ic/S1234567A` : Deletes the person with NRIC `S1234567A` from the current list.

   * `clear` : Deletes all persons stored in the application.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command, but it's specified multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if the command specifies `p/12341234 p/56785678`, only `p/56785678` will be stored.

* Parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.
    * **All other items must be filled, following the restrictions in the table below.**

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

</div>

| Field            | Prefix | Input Restrictions                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
|------------------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Address**      | a/     |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| **Cost**         | c/     | 1. Should either be a number larger or equal to 0. <br> 2. Supports up to 2 decimal places.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| **Date**         | d/     | 1. Format should be DD-MM-YYYY HH:mm, e.g., 31-03-2023 14:00. <br> 2. 'DD' must be between 01-31 and 'MM' between 01-12. <br> 3. HH:mm should follow the 24-hour notation"                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **Email**        | e/     | 1. Should be of the format local-part@domain <br> 2. The local-part should only contain alphanumeric characters and these special characters (+_.-). The local-part may not start or end with any special characters. <br> 3. This is followed by a '@' and then a domain name. The domain name is made up of domain labels separated by periods. <br> The domain name must: <br> - end with a domain label at least 2 characters long <br> - have each domain label start and end with alphanumeric characters <br> - have each domain label consist of alphanumeric characters, separated only by hyphens, if any. |
| **Medication**   | m/     | Should only contain alphanumeric characters and spaces.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **Name**         | n/     | Should only contain alphanumeric characters and spaces.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |
| **NRIC**         | ic/    | 1. Should be 9 characters long. <br> 2. The first character must be one of [S,T,F,G,M], followed by 7 numerical digits and ending with [A-Z].                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| **Phone Number** | p/     | 1. Should only contain numbers. <br> 2. Should be at least 3 digits long.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| **Tag**          | t/     | Should be alphanumeric.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              |

### Adding a person: `add a patient`, `add a doctor`

Adds a person (patient/doctor) to MediConnect.

Format: `addPatient n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]`  <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`addDoctor n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]`

* Adds the specified person to MediConnect.
* Each person can be added only once and can be **either** a Patient or a Doctor.
* Each person's NRIC must be distinct.
* There is no limit to the number of digits for phone number. It is left completely up to the user's discretion.

Examples:
* `addPatient n/Ben Smith p/98353535 e/ben@abc.com ic/S1234567A a/1 Ben Street, block 13, #01-01` adds the patient `Ben Smith` to the list.
* `addDoctor n/Sarah Tan p/99123456 e/sarah@abc.com ic/T7654321P a/Sarah Rd t/Pediatrician` adds the doctor `Sarah Tan` to the list.

![AddPatient](images/AddPatient.png)

![AddDoctor](images/AddDoctor.png)

### Deleting a person by NRIC : `delete`

Deletes the specified person from MediConnect.

Format: `delete ic/NRIC`

* Deletes the person with the specified `NRIC`
* Note that this also deletes all the appointment data associated with that patient/doctor
  * When a patient is deleted from the system, all appointments from their appointment list will be removed. This also removes the respective appointments from the doctor's appointment list
  * When a doctor is deleted from the system, all appointments from their appointment list will be removed. This also removes the respective appointments from the patient's appointment list

Examples:
* `delete ic/S9876543K` deletes the person with the NRIC number S9876543K in MediConnect.

![DeleteBefore](images/Delete_before.png)
![DeleteAfter](images/Delete_after.png)

### Editing a person : `edit`

Edits the details of an existing person stored in MediConnect.

Format `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [ic/NRIC] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified INDEX. The index refers to the index number shown in the displayed person list. The *index must be a positive integer* 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* Tags can be removed by typing t/ without specifying any tags after it.

Examples:
* `edit 9 n/John Smith` will edit the name of the 9th person listed to "John Smith".

![Edit](images/Edit.png)

### Display patient's information : `display`
Displays personal particulars, appointments and prescription for patients.

Format: `display ic/NRIC`

* For patients:
  * Displays personal particulars, appointments and prescription for the patient with the specified `NRIC`.
* For doctors:
  * Displays personal particulars, and appointments for the doctor with the specified `NRIC`.

Restrictions:
* `ic/NRIC`
    * The patient/doctor specified by `ic/NRIC` should exist in MediConnect

Examples:
* `display ic/S1234567A` displays the information for the patient with NRIC number `S1234567A`.

![Display](images/Display.png)

### Finding a person (patient or doctor) by NRIC : `find`

Finds and displays information about the specified person.

Format: `find ic/NRIC`

* Finds the person with the specified `NRIC`
* Search is case-insensitive. e.g `s1234567a` will match with `S1234567A`.
* Only the NRIC is searched, additional input (e.g. n/NAME) will be ignored.
* Only full NRIC entries can be searched.

Examples:
* `find ic/S1234567A` returns the details for the person with NRIC number S1234567A.

![Find](images/Find.png)

### Book appointment : `appointment`

Schedules an appointment for a patient with a specific doctor.

Format: `appointment ic/NRIC d/DATE dric/NRIC`

* Schedules an appointment for the patient with `ic/NRIC` and the doctor with `dric/NRIC` on the specified `d/DATE`
* Note that this updates the appointment list for the specified patient, and the specified doctor in the detailed person view panel when `display` command is used.

Restrictions:
* `ic/NRIC`
  * The patient specified by `ic/NRIC` should exist in MediConnect
* `dric/NRIC`
  * The doctor specified by `dric/NRIC` should exist in MediConnect
* `d/DATE`
  * Adheres to `DD-MM-YYYY HH:MM` format
  * Date is valid to the calendar (eg. `30-13-2023 10:00` is not a valid `DATE`)
  * Time is in 24-hour notation
  * Accepts a past, current or future date
  * The patient does not have any prior booking on this date, **and** the doctor is not scheduled to meet any other patients on the same date.

Examples:
* `appointment ic/S1234567A d/01-04-2023 10:00 dric/S7654321Z` schedules an appointment for the patient with the NRIC number `S1234567A` and the doctor with the NRIC number `S7654321Z` on `01-04-2023 10:00`.

![Appointment](images/Appointment.png)

### Delete appointment : `deleteAppointment`

Deletes an appointment specified by the index of the patient’s appointment list.

Format: `deleteAppointment INDEX ic/NRIC`

* Deletes the appointment indicated by `INDEX` from the list of appointments for the person specified by `NRIC`
* `INDEX` refers to the index number shown in the appointment list of the patient.
* Note that this updates the appointment list for the specified patient, and the specified doctor in the detailed person view panel when `display` command is used.

Restrictions:
* `ic/NRIC`
    * Nric must belong to a patient (not doctor)
    * The patient specified by `ic/NRIC` should exist in MediConnect
* `INDEX`
  * The index must be a positive integer (eg. 1, 2, 3, …​)

Examples:
* `deleteAppointment 1 ic/S1234567A` deletes the first appointment displayed on the appointment list of the patient with NRIC number `S1234567A`."

![DeleteAppointment](images/DeleteAppointment.png)

### Prescribing patient’s medication : `prescribe`

* Prescribes medication to a patient, noting the medication's name and cost.
  * If the patient is already prescribed to that medication, its cost is updated to the new value instead.

Format: `prescribe ic/NRIC m/MEDICATION c/COST`

Examples:
*  `prescribe m/paracetamol ic/S1234567X c/0.7` prescribes paracetamol at a cost of $0.7 to the patient with NRIC number S1234567X.
*  `prescribe m/Cough Syrup ic/S1234567X c/10.7` prescribes Cough Syrup at a cost of $10.7 to the patient with NRIC number S1234567X.

![Prescribe](images/Prescribe.png)

### Removing patient’s medication : `unprescribe`

Removes a chosen medication from a patient.

Format: `unprescribe ic/NRIC m/MEDICATION`

Examples:
*  `unprescribe ic/S1234567X m/paracetamol` removes paracetamol prescription from patient with NRIC number S1234567X.
*  `unprescribe m/Cough Syrup ic/S1234567X` removes Cough Syrup prescription from patient with NRIC number S1234567X.

![Unprescribe](images/Unprescribe.png)

### Bill : `bill`

Calculates the cost of all medication given a Patient's prescription.

Format: `bill ic/NRIC`

Examples:
* `bill ic/S1234567X` calculates the cost of all medication for patient with NRIC S1234567X.

![Bill](images/Bill.png)

### Retrieve persons information : `list`

Shows a list of all persons in MediConnect.

Format: `list`

![List](images/List.png)

### Retrieve doctors information : `listDoctors`

Shows a list of all doctors in MediConnect.

Format: `listDoctors`

![ListDoctors](images/ListDoctors.png)

### Retrieve patients information : `listPatients`

Shows a list of all patients in MediConnect.

Format: `listPatients`

![ListPatients](images/ListPatients.png)

### Clearing all entries : `clear`

Deletes all patients' and doctors' data from the system.

Format: `clear`

* Data cannot be retrieved after `clear` is performed.
* Note that this also deletes all the appointment data that has been previously stored in `MediConnect`

Example:
* `clear` permanently deletes all data stored in the system.

![Clear](images/Clear.png)

### Requesting help : `help`

Provides the user with a link to this user guide.

Format: `help`

Examples:
* `help` opens a mini window with a url link of this user guide which can be copied by the user.

![help message](images/helpMessage.png)

### Exiting the program : `exit`

Closes the `MediConnect` application.

Format: `exit`

Examples:
* `exit` closes the `MediConnect` application immediately.

### Sending automated messages to patients `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**1. What can I do if MediConnect.jar does not open when double-clicked?** <br>
Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar MediConnect.jar` command to run the application.

**2. Do I need to manually store the data?** <br>
No, MediConnect data is saved on the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                 | Format, Examples                                                                                                                                                                                                                                                                                                                                         |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add Person**         | 1. `addPatient n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]…​` <br> e.g., `addPatient n/Ben Smith p/98353535 e/ben@abc.com ic/S1234567A a/1 Ben Street, block 13, #01-01` <br> 2. `addDoctor n/NAME p/PHONE_NUMBER e/EMAIL ic/NRIC a/ADDRESS [t/TAG]…​` <br> e.g., `addDoctor n/Sarah Tan p/99123456 e/sarah@abc.com ic/T7654321P a/Sarah Rd` |
| **Bill**               | `bill ic/NRIC` <br> e.g.,                               bill ic/S1234567X                                                                                                                                                                                                                                                                                |
| **Book Appointment**   | `appointment ic/NRIC d/DATE dric/NRIC` <br> e.g., `appointment ic/S1234567A d/01-04-2023 10:00 dric/S7654321Z`                                                                                                                                                                                                                                           |
| **Clear**              | `clear`                                                                                                                                                                                                                                                                                                                                                  |
| **Delete Appointment** | `deleteAppointment INDEX ic/NRIC` <br> e.g., `deleteAppointment 2 ic/S1234567A`                                                                                                                                                                                                                                                                          |
| **Delete Person**      | `delete ic/NRIC`<br> e.g., `delete ic/S1234567A`                                                                                                                                                                                                                                                                                                         |
| **Display**            | `display ic/NRIC` <br> e.g., `display ic/S1234567A`                                                                                                                                                                                                                                                                                                      |
| **Edit**               | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [ic/NRIC] [a/ADDRESS] [t/TAG]…​` <br> e.g., `edit 2 n/James Lee e/jameslee@example.com`                                                                                                                                                                                                                  |
| **Exit**               | `exit`                                                                                                                                                                                                                                                                                                                                                   |
| **Find**               | `find ic/NRIC`<br> e.g., `find ic/S1234567A`                                                                                                                                                                                                                                                                                                             |
| **Help**               | `help`                                                                                                                                                                                                                                                                                                                                                   |
| **List**               | `list` <br> `listDoctors` <br> `listPatients`                                                                                                                                                                                                                                                                                                            |
| **Prescribe**          | `prescribe ic/NRIC m/MEDICATION c/COST` <br> e.g, prescribe ic/S1234567X m/paracetamol c/10.7                                                                                                                                                                                                                                                            |
| **Unprescribe**        | `unprescribe ic/NRIC m/MEDICATION` <br> e.g., unprescribe ic/S1234567X  m/paracetamol                                                                                                                                                                                                                                                                    |
