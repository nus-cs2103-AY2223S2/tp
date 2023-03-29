---
layout: page
title: User Guide
---
# Patientist User Guide
Patientist is a desktop app used for managing patients and staff within a medical facility. This application is optimised
for use via a Command Line Interface (CLI), while still having a Graphical User Interface (GUI) for users to fall back
on. If you are a strong typist and are familiar with the system, Patientist can get your tasks done faster and more
reliably than traditional GUI based apps.

![MainScreen](../docs/images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

#### Using this guide
* If you are setting up for the first time, please have a look at our [Quick Start](#quick-start) section.
* If you are unsure how to use Patientist, the [Features](#features) section might be a good place to start.
* For ease of navigation, clicking the hyperlinks at the bottom of each section to go back to the table of contents.


## Table of Contents (OUTDATED TABLE!!!!!)
- **[Quick Start](#quick-start)**
- **[Features](#features)**
  * [Viewing help](#viewing-help--help)
  * [Adding a patient](#adding-a-patient--addpat)
  * [Adding a staff member](#adding-a-staff-member--addstf)
  * [Adding prescriptions/instructions for a patient](#adding-prescriptionsinstructions-for-patient--addpresc)
  * [Adding a ward to the system](#adding-a-ward-to-the-system--addward)
  * [List all patients](#listing-all-patients--lspat)
  * [List all staff members](#listing-staff-members--lsstf)
  * [List a patient's prescriptions](#listing-a-patients-prescriptions--lspresc)
  * [List all wards](#listing-all-wards--lsward)
  * [View a patient's details](#viewing-the-details-of-a-specific-patient--view)
  * [Update patient's particulars](#update-patient-particulars--editpat)
  * [Delete a patient](#deleting-a-patient-from-the-system--delpat)
  * [Delete a staff member](#deleting-a-staff-member-from-a-ward--delstf)
  * [Delete a prescription from a patient](#deleting-an-entry-from-a-patients-list-of-prescriptions--delpresc)
  * [Delete a ward from the system](#deleting-a-ward-from-the-system--delward)
  * [Exit the program](#exiting-the-program--exit)
- **[Frequently asked questions](#FAQ)**
- **[Command Summary](#command-summary)**


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 installed on your computer. If you are unsure you can download it from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest release of Patientist from [here](https://github.com/AY2223S2-CS2103T-T12-1/tp/releases/latest).
3. Copy the file to the folder you'd like to use as the _home folder_ for your Patientist app.
4. Double-click the .jar file to open the app. There will be some sample data to play around with.
5. Type in commands and press ENTER to execute the command. `help` would be a good first command to use to get a sensing of how to use this app.
6. Refer to the [Features](#features) or [Command Summary](#command-summary) sections to see summary of commands.

--------------------------------------------------------------------------------------------------------------------

## Features


## Notes about formatting
* Words in UPPER_CASE are user supplied parameters, e.g. `addpat n/NAME`: `NAME` is a parameter, and the command can be used as add `n/John Doe`
* Items in square brackets are optional parameters, e.g. `addpat n/NAME [t/TAG]` can be used as `addpat n/John Doe t/urgent` or simply as `addpat n/John Doe`
* Items with … after them can be specified 0 or more times, e.g. `[t/TAG]...` means it is valid to not include a tag, or you can include 1 or more `t/TAG` expressions
* Extraneous parameters for commands that take in exactly 0 parameters will be ignored
---

## Viewing help: help
Shows a popup explaining how to access the user guide, which is the help page.

**Format: `help`**

[Go back to [Table of Contents](#table-of-contents)]

---

## Clearing the patientist: clear
Clears the current session of patientist and restores it to empty state.

**Format: `clear`**

[Go back to [Table of Contents](#table-of-contents)]

___

## Adding a patient: addpat (Should still be right, delete this note if it is)
Adds a new patient to the system, and places them in the `WARD_NAME` assigned.
Tags attached to a user are meant to be short notes that do not fit into any other category of patient details that can be added.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`.

**Format: `addpat n/PATIENT_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME [t/TAG]...`**

`ID_NUMBER` should be unique to each patient. This is not case-sensitive. A123456789B is identical to a123456789b.
**This input will be capitalised automatically.**\
`PATIENT_NAME` need not be unique.

**Examples:**
`addpat n/John Doe id/A12345B w/Block B Ward 2 p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends`

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding a staff member: addstf (Should still be right, delete this note if it is)
Assigns specified `STAFF_NAME` to the specified `WARD_NAME`.
The STAFF_NAME will be displayed in the list of personnel in charge of the ward.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `addstf n/STAFF_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME`**

**Examples:**
`addstf n/Dr. Mallory Wong id/A987654321H p/97365482 e/mwong@example.com a/390 Geylang Rd w/block B ward 2`\
`addstf n/Nurse Joy id/A345678Z p/81623753, e/nurse.joy@example.com a/900 Still Rd w/block B ward 2`

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding a ward to the system: addward (Should still be right, delete this note if it is)
Creates an empty ward with the specified `WARD_NAME`.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `addward n/WARD_NAME`**
`WARD_NAME` must be unique and cannot be the same as any existing name. This field is case-sensitive.

**Examples:**
`addward n/block B ward 2` will create a new empty ward called block B ward 2\
`addward n/block C ward 1` will create a new empty ward called block C ward 1

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing all patients: lspat (Is WARD_NAME still a valid argument?)
Lists all the patients’ names and corresponding patient ID, displaying any tags attached to them and showing the ward they are in.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `lspat [w/WARD_NAME]`**
`WARD_NAME` can be specified to only list patients in the specified ward. This field is case-sensitive. This is an optional parameter.

**Examples:**
`lspat` will list all patients in each ward on the GUI. Every ward will be displayed in order, with all patients in each ward.\
`lspat w/block B ward 2` will display the list of patients that are assigned to block B ward 2 in the GUI

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing staff members: lsstf (Is WARD_NAME still a valid argument?)

Lists staff members’ names and the name of the ward they are assigned to.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `lsstf [w/WARD_NAME]`**
`WARD_NAME` can be specified to only list staff in the specified ward. This is an optional parameter.

**Examples:**
`lsstf` will list all staff assigned to each ward on the GUI. The lists will be grouped according to ward, and each staff
can appear more than once if they are assigned to more than 1 ward.\
`lsstf w/block A ward 1` will list all staff assigned to block A ward 1 on the GUI.

[Go back to [Table of Contents](#table-of-contents)]

---
## Finding a person: find (Should still be right, delete this note if it is)

Finds all persons with names containing any of the specified keywords and displays them on the GUI.

**Warning: `NAME` is case-insensitive.** Using an argument `Alice` will match names `aLice`, `alice`, `ALICE` etc.

**Format: `find NAME`**

**Examples: `find Alice Tan`**

---
## Finding a patient by ID number: findpat (TODO)

**Format:**

**Examples:**

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding patient status: addpatstatus (TODO)
Adds an entry to the list of patient statuses. This list can be viewed by the [view](#viewing-the-details-of-a-specific-patient--view)
command.

**Format:**

**Examples:**

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting patient status: delpatstatus (TODO)
Deletes the specified entry in the list of patient statuses. This list can be viewed by the [view](#viewing-the-details-of-a-specific-patient--view)
command.

**Format:**

**Examples:**

[Go back to [Table of Contents](#table-of-contents)]

---
## Viewing the details of a specific patient: view (Should still be right, delete this note if it is)
Lists the full detail of a specific patient, including their statuses, name, patient ID and tags in the main window.

**Format: `view INDEX`**

**Examples:**
`view 1` will display all the information associated with the 1st patient shown on the GUI.

[Go back to [Table of Contents](#table-of-contents)]

---

## Listing the names of all wards: lsward (Do we actually have this feature?)
Lists all existing wards on the GUI. Only ward names will be displayed.

**Format: `lsward`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing all patients in a particular ward: lswardpat (outdated implementation)
Lists all the patients found in the given `WARD_NAME`. 1 or more ward names may be provided.

**Format: `lswardpat WARD_NAME [WARD_NAME]...`**

**Examples: `lswardpat BlockAWard1`**

---
## Update a person's particulars: edit (Should still be right, delete this note if it is. I changed it to edit both staff and patients.)
This overwrites the specified person’s particulars with new particulars. The person to be edited must exist, i.e. there must exist a
person with the given `ID_NUMBER`. This command works on both patients and staff.

**Format: `edit id/ID_NUMBER [n/PATIENT_NAME] [w/WARD_NAME] [t/TAG] [p/PHONE_NUMBER] [kn/NOK_NAME] [kp/NOK_PHONE_NUMBER] [a/ADDRESS]`**

**Examples:**
`edit id/A0123456789B w/block B ward 2 p/81234567`\
`edit id/A0123456789B kn/John Tan kp/91234567`

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a person from the system based on list on GUI: delete (Does this still exist? or is DeleteCommand a remnant of AB3?I added this section because there is no documentation but the class exists)
This deletes the person specified by `INDEX`. This index is the number beside the person on the list of persons on screen.

**Format: `delete INDEX`**

**Examples: `delete 3`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a patient from the system: delpat (Should still be right, delete this note if it is)
This removes the patient from the system as specified by `ID_NUMBER`.
The patient must currently exist for this command to be successfully executed.
This will remove the patient from his or her assigned ward as well.

**Format: `delpat id/ID_NUMBER`**

**Examples:**
`delpat id/A0123456789B` will delete all records of patient with ID number A0123456789B from the system.

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a staff member from a ward: delstf (Outdated implementation)
This removes `STAFF_NAME` from the list of persons in charge of `WARD_NAME`. The staff must be currently assigned to the ward for this command to be successfully executed.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `delstf n/STAFF_NAME w/WARD_NAME`**

**Examples:**
`delstf n/Dr. Mallory Wong w/block B ward 2` will remove Dr. Mallory Wong from the list of staff attending to block B ward 2.

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a ward from the system: delward (Should still be right, delete this note if it is)
This deletes the `WARD_NAME` specified from the system. The ward being deleted must be empty for this command to be successfully executed.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `delward n/WARD_NAME`**

**Examples:**
`delward n/block B ward 2` will remove block B ward 2 from the system. The ward must have been empty before deletion.

[Go back to [Table of Contents](#table-of-contents)]

---
## Exiting the program: exit
Exits the program.

**Format: `exit`**

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Patientist home folder.

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------
## Command summary (OUTDATED TABLE!!!!!)

| Action                           | Format, Examples                                                                                                                                                                                                           |
|----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add patient**                  | `addpat n/PATIENT_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME [t/TAG]...` <br> e.g., `addpat n/John Doe id/A12345B w/Block B Ward 2 p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends` |
| **Add staff**                    | `addstf n/STAFF_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME`<br> e.g., `addstf n/Dr. Mallory Wong id/A987654321H p/97365482 e/mwong@example.com a/390 Geylang Rd w/block B ward 2`                          |
| **Add ward**                     | `addward n/WARD_NAME`<br> e.g.,`addward n/block B ward 2`                                                                                                                                                                  |
| **List Patients**                | `lspat [w/WARD_NAME]`<br> e.g., `lspat block B ward 2`, `lspat`                                                                                                                                                            |
| **List patient's prescriptions** | `lspresc id/ID_NUMBER`<br> e.g., `lspresc id/A0123456789B`                                                                                                                                                                 |
| **List staff members**           | `lsstf [w/WARD_NAME]`<br> e.g., `lsstf w/block A ward 1`, `lsstf`                                                                                                                                                          |
| **List all ward names**          | `lsward`<br>                                                                                                                                                                                                               |
| **Edit patient particulars**     | `editpat id/ID_NUMBER [n/PATIENT_NAME] [w/WARD_NAME] [t/TAG] [p/PHONE_NUMBER] [kn/NOK_NAME] [kp/NOK_PHONE_NUMBER] [a/ADDRESS]`<br> e.g., `editpat id/A0123456789B w/block B ward 2 p/81234567`                             |
| **Deleting ward**                | `delward n/WARD_NAME`<br> e.g., `delward n/block B ward 2`                                                                                                                                                                 |
| **Deleting staff member**        | `delstf n/STAFF_NAME w/WARD_NAME`<br> e.g., `delstf n/Dr. Mallory Wong w/block B ward 2`                                                                                                                                   |
| **Deleting patient from system** | `delpat id/ID_NUMBER`<br> e.g., `delpat id/A0123456789B`                                                                                                                                                                   |
| **Exit**                         | `exit`<br>                                                                                                                                                                                                                 |
| **Clear**                        | `clear`<br>                                                                                                                                                                                                                |

[Go back to [Table of Contents](#table-of-contents)]

