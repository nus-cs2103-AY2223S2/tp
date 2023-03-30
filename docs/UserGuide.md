---
layout: page
title: User Guide
---
# Patientist User Guide
Patientist is a desktop app used for managing patients and staff within a medical facility. This application is optimised
for use via a Command Line Interface (CLI), while still having a Graphical User Interface (GUI) for users to fall back
on. If you are a strong typist and are familiar with the system, Patientist can get your tasks done faster and more
reliably than traditional GUI based apps.

![MainScreen](images/Ui.png)

--------------------------------------------------------------------------------------------------------------------

## Using this guide
* If you are setting up for the first time, please have a look at our [Quick Start](#quick-start) section.
* If you are unsure how to use Patientist, the [Features](#features) section might be a good place to start.
* For ease of navigation, clicking the hyperlinks at the bottom of each section to go back to the table of contents.

___

## Table of Contents
* Table of Contents {:toc}


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 installed on your computer. If you are unsure you can download it from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).
2. Download the latest release of Patientist from [here](https://github.com/AY2223S2-CS2103T-T12-1/tp/releases/latest).
3. Copy the file to the folder you'd like to use as the _home folder_ for your Patientist app.
4. Double-click the .jar file to open the app. There will be some sample data to play around with.
5. Type in commands and press ENTER to execute the command. `help` would be a good first command to use to get a sensing of how to use this app.
6. Refer to the [Features](#features) or [Command Summary](#command-summary) sections to see summary of commands.

--------------------------------------------------------------------------------------------------------------------

# Features

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

## Adding a patient: addpat
Adds a new patient to the system, and places them in the `WARD_NAME` assigned.
Tags attached to a user are meant to be short notes that do not fit into any other category of patient details that can be added.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`.

**Format: `addpat n/PATIENT_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME [t/TAG]...`**

`ID_NUMBER` should be unique to each patient. This is not case-sensitive. A123456789B is identical to a123456789b.
**This input will be capitalised automatically.**\
`PATIENT_NAME` need not be unique.

**Examples:**
**`addpat n/John Doe id/A12345B w/Block B Ward 2 p/98765432 e/johnd@example.com a/311, Clementi Ave 2, #02-25`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding a staff member: addstf
Assigns specified `STAFF_NAME` to the specified `WARD_NAME`.
The STAFF_NAME will be displayed in the list of personnel in charge of the ward.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `addstf n/STAFF_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME`**

**Examples:**
**`addstf n/Dr Mallory Wong id/A987654321H p/97365482 e/mwong@example.com a/390 Geylang Rd w/block B ward 2`**\
**`addstf n/Nurse Joy id/A345678Z p/81623753 e/nurse.joy@example.com a/900 Still Rd w/block B ward 2`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding a ward to the system: addward
Creates an empty ward with the specified `WARD_NAME`.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `addward n/WARD_NAME`**
`WARD_NAME` must be unique and cannot be the same as any existing name. This field is case-sensitive.

**Examples:**
**`addward n/block B ward 2`** will create a new empty ward called block B ward 2\
**`addward n/block C ward 1`** will create a new empty ward called block C ward 1

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing all patients: lspat
Lists all the patients’ names and corresponding patient ID, displaying any tags attached to them and showing the ward they are in.

**Format: `lspat`**

**Examples:**
**`lspat`** will list all patients in each ward on the GUI. Every ward will be displayed in order, with all patients in each ward.

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing staff members: lsstf

Lists staff members’ names and the name of the ward they are assigned to.

**Format: `lsstf`**

**Examples:**
**`lsstf`** will list all staff assigned to each ward on the GUI. The lists will be grouped according to ward, and each staff
can appear more than once if they are assigned to more than 1 ward.\

[Go back to [Table of Contents](#table-of-contents)]

---
## Finding a person: find

Finds all persons with names containing any of the specified keywords and displays them on the GUI.

**Warning: `NAME` is case-insensitive.** Using an argument `Alice` will match names `aLice`, `alice`, `ALICE` etc.

**Format: `find NAME`**

**Examples: `find Alice Tan`**

---
## Finding a patient by ID number: findpat

Finds all patients with names containing any of the specified keywords or id matching the specified keyword and displays
them on the GUI.

**Format: `findpat [n/NAME] [id/ID_NUMBER]`**

**Note:** Either NAME or ID_NUMBER must be present.

**Examples:**
**`findpat n/Alex Bob Charles`** Finds all patients with names containing Alex, Bob or Charles.\
**`findpat id/A12345B`** Finds all patients with IDs matching A12345B.

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding patient status: addpatstatus
Adds an entry to the list of patient statuses. This list can be viewed by the [view](#viewing-the-details-of-a-specific-person--view)
command.

**Note:** If the person indicated by the INDEX must be a `Patient`.

**Format: `addpatstatus INDEX s/STATUS [s/STATUS]...`**

**Examples: `addpatstatus 1 s/Feeling alright s/Eating well`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting patient status: delpatstatus
Deletes the specified entry in the list of patient statuses. This list can be viewed by the [view](#viewing-the-details-of-a-specific-person--view)
command.

**Note:** If the person indicated by the PATIENT_INDEX must be a `Patient`.

**Format: `delpatstatus PATIENT_INDEX STATUS_INDEX`**

**Examples: `delpatstatus 1 1`** will delete the first status of the patient at the top of the patient list.

[Go back to [Table of Contents](#table-of-contents)]

---
## Adding patient todo: addpattodo
Adds an entry to the list of patient todos. This list can be viewed by the [view](#viewing-the-details-of-a-specific-person--view)
command.

**Note:** If the person indicated by the INDEX must be a `Patient`.

**Format: `addpattodo INDEX td/TODO [td/TODO]...`**

**Examples: `addpattodo 1 td/Take medicine td/physio at 2`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting patient todo: delpattodo
Deletes the specified entry in the list of patient todos. This list can be viewed by the [view](#viewing-the-details-of-a-specific-person--view)
command.

**Note:** If the person indicated by the PATIENT_INDEX must be a `Patient`.

**Format: `delpattodo PATIENT_INDEX TODO_INDEX`**

**Examples: `delpattodo 1 1`** will delete the first todo of the patient at the top of the patient list.

[Go back to [Table of Contents](#table-of-contents)]

---
## Viewing the details of a specific person: view
Lists the full detail of a specific person, including their name, patient ID and tags in the main window.

**Format: `view INDEX`**

**Examples:**
**`view 1`** will display all the information associated with the 1st person shown on the GUI.

[Go back to [Table of Contents](#table-of-contents)]

---

## Listing the names of all wards: lsward
Lists all existing wards on the GUI. Only ward names will be displayed.

**Format: `lsward`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Listing all patients in a particular ward: lswardpat
Lists all the patients found in the given `WARD_NAME`.

**Format: `lswardpat WARD_NAME`**

**Examples: `lswardpat Block A Ward 1`** will list all patients in Block A Ward 1.

---
## Update particulars of a person: edit
This overwrites the specified person’s particulars with new particulars. This command works on both patients and staff.

**Format: `edit INDEX [n/PATIENT_NAME] [t/TAG] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL]`**

**Examples:**
**`edit 1 p/81234567`**\
**`edit 2 n/Bob Tan p/91234567`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a person from the system based on list on GUI: delete
This deletes the person specified by `INDEX`. This index is the number beside the person on the list of persons on screen.

**Format: `delete INDEX`**

**Examples: `delete 3`**

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a patient from the system: delpat
This removes the patient from the system as specified by `ID_NUMBER`.
The patient must currently exist for this command to be successfully executed.
This will remove the patient from his or her assigned ward as well.

**Format: `delpat id/ID_NUMBER`**

**Examples:**
**`delpat id/A0123456789B`** will delete all records of patient with ID number A0123456789B from the system.

[Go back to [Table of Contents](#table-of-contents)]

---
## Deleting a staff member from the system: delstf
This removes the staff from the system as specified by `ID_NUMBER`.
The staff must currently exist for this command to be successfully executed.
This will remove the staff from his or her assigned ward as well.

**Format: `delstf id/ID_NUMBER`**

**Examples:**
**`delstf id/A12345B`** will delete all records of staff with ID number A12345B from the system.

[Go back to [Table of Contents](#table-of-contents)]

---

## Deleting a ward from the system: delward
This deletes the `WARD_NAME` specified from the system. The ward being deleted must be empty for this command to be successfully executed.

**Warning: `WARD_NAME` is case-sensitive.** `block B ward 2` will refer to a different ward from `Block b Ward 2`

**Format: `delward n/WARD_NAME`**

**Examples:**
**`delward n/block B ward 2`** will remove block B ward 2 from the system. The ward must have been empty before deletion.

[Go back to [Table of Contents](#table-of-contents)]

---

## Exiting the program: exit
Exits the program.

**Format: `exit`**

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------
# FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Patientist home folder.

**Q**: How do I ensure that I have Java 11 on my computer?<br>
**A**: Check how to find the Java version in your computer [here](https://www.java.com/en/download/help/version_manual.html).

[Go back to [Table of Contents](#table-of-contents)]

--------------------------------------------------------------------------------------------------------------------
# Command summary

| Action                                                                                                        | Format, Examples                                                                         |
|---------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------|
| **[View help](#viewing-help--help)**                                                                          | `help`                                                                                   |
| **[Clear patientist](#clearing-the-patientist--clear)**                                                       | `clear`                                                                                  |
| **[Add patient](#adding-a-patient--addpat)**                                                                  | `addpat n/PATIENT_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME [t/TAG]...` |
| **[Add staff](#adding-a-staff-member--addstf)**                                                               | `addstf n/STAFF_NAME id/ID_NUMBER p/PHONE_NO e/EMAIL a/ADDRESS w/WARD_NAME`              |
| **[Add ward](#adding-a-ward-to-the-system--addward)**                                                         | `addward n/WARD_NAME`                                                                    |
| **[List patients](#listing-all-patients--lspat)**                                                             | `lspat`                                                                                  |
| **[List staff members](#listing-staff-members--lsstf)**                                                       | `lsstf`                                                                                  |
| **[Find person](#finding-a-person--find)**                                                                    | `find NAME`                                                                              |
| **[Find patient by ID](#finding-a-patient-by-id-number--findpat)**                                            | `findpat [n/NAME] [id/ID_NUMBER]`                                                        |
| **[Add patient status](#adding-patient-status--addpatstatus)**                                                | `addpatstatus INDEX s/STATUS [s/STATUS]... `                                             |
| **[Delete patient status](#deleting-patient-status--delpatstatus)**                                           | `delpatstatus PATIENT_INDEX STATUS_INDEX`                                                |
| **[Add patient todo](#adding-patient-todo--addpattodo)**                                                      | `addpattodo INDEX td/TODO [td/TODO]...`                                                  |
| **[Delete patient todo](#deleting-patient-todo--delpattodo)**                                                 | `delpattodo PATIENT_INDEX TODO_INDEX`                                                    |
| **[View details of a person](#viewing-the-details-of-a-specific-person--view)**                               | `view INDEX`                                                                             |
| **[List wards](#listing-the-names-of-all-wards--lsward)**                                                     | `lsward`                                                                                 |
| **[List patients in a ward](#listing-all-patients-in-a-particular-ward--lswardpat)**                          | `lswardpat WARD_NAME`                                                                    |
| **[Update person's particulars](#update-particulars-of-a-person--edit)**                                      | `edit INDEX [n/PATIENT_NAME] [t/TAG] [p/PHONE_NUMBER] [a/ADDRESS] [e/EMAIL]`             |
| **[Delete person from system based on GUI](#deleting-a-person-from-the-system-based-on-list-on-gui--delete)** | `delete INDEX`                                                                           |
| **[Delete patient from system](#deleting-a-patient-from-the-system--delpat)**                                 | `delpat id/ID_NUMBER`                                                                    |
| **[Delete staff member from system](#deleting-a-staff-member-from-the-system--delstf)**                       | `delstf id/ID_NUMBER`                                                                    |
| **[Delete ward from system](#deleting-a-ward-from-the-system--delward)**                                      | `delward n/WARD_NAME`                                                                    |
| **[Exit the program](#exiting-the-program--exit)**                                                            | `exit`                                                                                   |

[Go back to [Table of Contents](#table-of-contents)]

