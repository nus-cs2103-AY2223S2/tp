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


## Table of Contents
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
6. Refer to the [Features](#features) or [Command Summary](#command-summary) sections to see the details for each command.

--------------------------------------------------------------------------------------------------------------------

## Features


## Notes about formatting
* Words in UPPER_CASE are user supplied parameters, e.g. `addpat n/NAME`: `NAME` is a parameter, and the command can be used as add `n/John Doe`
* Items in square brackets are optional parameters, e.g. `addpat n/NAME [t/TAG]` can be used as `addpat n/John Doe t/urgent` or simply as `addpat n/John Doe`
* Items with … after them can be specified 0 or more times, e.g. `[t/TAG]...` means it is valid to not include a tag, or you can chain 1 or more `t/TAG` expressions
* Extraneous parameters for commands that take in exactly 0 parameters will be ignored
---

## Viewing help: help
Shows a popup explaining how to access the user guide, which is the help page.

Format: `help`

[Go back to [Table of Contents](#table-of-contents)]

___

## Adding a patient: addpat
Adds a new patient to the system, and places them in the `WARD_NAME` assigned.
Tags attached to a user are meant to be short notes that do not fit into any other category of patient details that can be added.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`.

### Format: `addpat n/PATIENT_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME [t/TAG]...`

`ID_NUMBER` should be unique to each patient. This is not case-sensitive. A123456789B is identical to a123456789b.
**This input will be capitalised automatically.**\
`PATIENT_NAME` need not be unique.

### Examples:
`addpat n/John Doe id/A12345B w/Block B Ward 2 p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends`

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding a staff member: addstf
Assigns specified `STAFF_NAME` to the specified `WARD_NAME`.
The STAFF_NAME will be displayed in the list of personnel in charge of the ward.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

### Format: `addstf n/STAFF_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME`

### Examples:
`addstf n/Dr. Mallory Wong id/A987654321H p/97365482 e/mwong@example.com a/390 Geylang Rd w/block B ward 2`\
`addstf n/Nurse Joy id/A345678Z p/81623753, e/nurse.joy@example.com a/900 Still Rd w/block B ward 2`

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding prescriptions/instructions for patient: addpresc
Adds `PRESCRIPTION_OR_INSTRUCTION` in the prescriptions and instructions field for the `PATIENT` specified.
The patient can be specified by `ID_NUMBER`, the patient’s ID number.
(Consider changing this in future iterations to not having to key in prescription in CLI, instead a new editpresc command opens a text editor for the patient’s txt prescription file)

### Format: `addpresc id/ID_NUMBER p/PRESCRIPTION_OR_INSTRUCTION`

### Examples:
`addpresc id/A0123456789B pr/paracetamol 500mg`

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding a ward to the system: addward
Creates an empty ward with the specified `WARD_NAME`.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

### Format: `addward n/WARD_NAME`
`WARD_NAME` must be unique and cannot be the same as any existing name. This field is case-sensitive.

### Examples:
`addward n/block B ward 2` will create a new empty ward called block B ward 2\
`addward n/block C ward 1` will create a new empty ward called block C ward 1

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing all patients: lspat
Lists all the patients’ names and corresponding patient ID, displaying any tags attached to them and showing the ward they are in.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

### Format: `lspat [w/WARD_NAME]`
`WARD_NAME` can be specified to only list patients in the specified ward. This field is case-sensitive. This is an optional parameter.

### Examples:
`lspat` will list all patients in each ward on the GUI. Every ward will be displayed in order, with all patients in each ward.\
`lspat w/block B ward 2` will display the list of patients that are assigned to block B ward 2 in the GUI

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing staff members: lsstf

Lists staff members’ names and the name of the ward they are assigned to.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

### Format: `lsstf [w/WARD_NAME]`
`WARD_NAME` can be specified to only list staff in the specified ward. This is an optional parameter.

### Examples:
`lsstf` will list all staff assigned to each ward on the GUI. The lists will be grouped according to ward, and each staff
can appear more than once if they are assigned to more than 1 ward.\
`lsstf w/block A ward 1` will list all staff assigned to block A ward 1 on the GUI.

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing a patient’s prescriptions: lspresc
Lists the prescriptions and instructions list for a patient in numbered list form for the patient specified by `ID_NUMBER`.

### Format: `lspresc id/ID_NUMBER`

### Examples:
`lspresc id/A0123456789B` will display the list of tasks and prescriptions of patient with ID A0123456789B on the GUI

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing all wards: lsward
Lists all existing wards on the GUI. Only ward names will be displayed.

### Format: `lsward`

[Go back to [Table of Contents](#table-of-contents)]

---
## Viewing the details of a specific patient: view
Lists the full detail of a specific patient, including their prescriptions/instructions, name, patient ID and tags in the main window.

### Format: `view INDEX`

### Examples:
`view 1` will display all the information associated with the 1st patient shown on the GUI.

[Go back to [Table of Contents](#table-of-contents)]

---
## Update patient particulars: editpat
This overwrites the specified patient’s particulars with new particulars. The patient to be edited must exist, i.e. there must exist a
patient with the given `ID_NUMBER`. **All existing information about the patient will be deleted, and replaced with
the new information.**

### Format: `editpat id/ID_NUMBER [n/PATIENT_NAME] [w/WARD_NAME] [t/TAG] [p/PHONE_NUMBER] [kn/NOK_NAME] [kp/NOK_PHONE_NUMBER] [a/ADDRESS]`

### Examples:
`editpat id/A0123456789B w/block B ward 2 p/81234567`\
`editpat id/A0123456789B kn/John Tan kp/91234567`

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a patient from the system: delpat
This removes the patient from the system as specified by `ID_NUMBER`.
The patient must currently exist for this command to be successfully executed.
This will remove the patient from his or her assigned ward as well.

### Format: `delpat id/ID_NUMBER`

### Examples:
`delpat id/A0123456789B` will delete all records of patient with ID number A0123456789B from the system.

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a staff member from a ward: delstf
This removes `STAFF_NAME` from the list of persons in charge of `WARD_NAME`. The staff must be currently assigned to the ward for this command to be successfully executed.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

### Format: `delstf n/STAFF_NAME w/WARD_NAME`

### Examples:
`delstf n/Dr. Mallory Wong w/block B ward 2` will remove Dr. Mallory Wong from the list of staff attending to block B ward 2.

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting an entry from a patient’s list of prescriptions: delpresc
Deletes the list entry specified at `INDEX` on the patient’s list of prescriptions and instructions, for the patient
specified by `ID_NUMBER`. The patient must currently exist and the value of `INDEX` must represent a valid entry
on the list.

### Format: `delpresc id/ID_NUMBER idx/INDEX`

### Example:
`delpresc id/A0123456789B idx/1` will delete the top item from the prescriptions and instructions list of patient with
ID A0123456789B.

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a ward from the system: delward
This deletes the `WARD_NAME` specified from the system. The ward being deleted must be empty for this command to be successfully executed.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

### Format: `delward n/WARD_NAME`

### Examples:
`delward n/block B ward 2` will remove block B ward 2 from the system. The ward must have been empty before deletion.

[Go back to [Table of Contents](#table-of-contents)]

---
## Exiting the program: exit
Exits the program.

### Format: `exit`

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Patientist home folder.

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------
## Command summary

| Action                           | Format, Examples                                                                                                                                                                                                           |                                                                                                                                                                           
|----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add patient**                  | `addpat n/PATIENT_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME [t/TAG]...` <br> e.g., `addpat n/John Doe id/A12345B w/Block B Ward 2 p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25 t/friends` |                 
| **Add prescription**             | `addpresc id/ID_NUMBER p/PRESCRIPTION_OR_INSTRUCTION` <br> e.g., `addpresc id/A0123456789B p/paracetamol 500mg`                                                                                                            |                     
| **Add staff**                    | `addstf n/STAFF_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME`<br> e.g., `addstf n/Dr. Mallory Wong id/A987654321H p/97365482 e/mwong@example.com a/390 Geylang Rd w/block B ward 2`                          |             
| **Add ward**                     | `addward n/WARD_NAME`<br> e.g.,`addward n/block B ward 2`                                                                                                                                                                  |           
| **List Patients**                | `lspat [w/WARD_NAME]`<br> e.g., `lspat block B ward 2`, `lspat`                                                                                                                                                            |                        
| **List patient's prescriptions** | `lspresc id/ID_NUMBER`<br> e.g., `lspresc id/A0123456789B`                                                                                                                                                                 |                             
| **List staff members**           | `lsstf [w/WARD_NAME]`<br> e.g., `lsstf w/block A ward 1`, `lsstf`                                                                                                                                                          |                  
| **List all ward names**          | `lsward`<br>                                                                                                                                                                                                               |                  
| **Edit patient particulars**     | `editpat id/ID_NUMBER [n/PATIENT_NAME] [w/WARD_NAME] [t/TAG] [p/PHONE_NUMBER] [kn/NOK_NAME] [kp/NOK_PHONE_NUMBER] [a/ADDRESS]`<br> e.g., `editpat id/A0123456789B w/block B ward 2 p/81234567`                             | 
| **Deleting ward**                | `delward n/WARD_NAME`<br> e.g., `delward n/block B ward 2`                                                                                                                                                                 |
| **Deleting staff member**        | `delstf n/STAFF_NAME w/WARD_NAME`<br> e.g., `delstf n/Dr. Mallory Wong w/block B ward 2`                                                                                                                                   |       
| **Deleting prescription entry**  | `delpresc id/ID_NUMBER idx/INDEX`<br> e.g., `delpresc id/A0123456789B idx/1`                                                                                                                                               |            
| **Deleting patient from system** | `delpat id/ID_NUMBER`<br> e.g., `delpat id/A0123456789B`                                                                                                                                                                   |            
| **Exit**                         | `exit`<br>                                                                                                                                                                                                                 |

[Go back to [Table of Contents](#table-of-contents)]

