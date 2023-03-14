<a id="top"></a>
---
layout: page
title: CareFlow User Guide
---

Careflow is a **desktop application for patient and drug inventory management, optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

If you are a receptionist for a GP clinic and you can type fast, Careflow can get your patient and drugs management tasks done faster than traditional applications.
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java 11 installed in your Computer.
   * [Installing Java 11 on Windows](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-371F38CC-248F-49EC-BB9C-C37FC89E52A0)
   * [Installing Java 11 on macOS](https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html)

2. Download the latest `careflow.jar` from [here](https://github.com/AY2223S2-CS2103T-W09-3/tp).

3. Copy the file to the folder you want to use as the home folder for your careflowBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar careflow.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   * `p list` : Lists all patients.

   * `d list` : Lists all drugs.

   * `add -n John Doe -ph 98765432 -em johnd@example.com -ad John Street, Block 123, #01-01 -dob 21-01-2000 -g male -ic T3871918C` : Adds a patient named `John Doe` to the patient records.

   * `p delete -in 3` : Deletes the 3rd patient record shown in the current list.

   * `p clear` : Deletes all patient records.

   * `d clear` : Deletes all drug records

   * `exit` : Exits the app.

#### Notes about command format:
* Words in UPPER CASE are the parameters to be supplied by the user.
e.g. in add -n NAME, NAME is a parameter which can be used as add -n John Doe.

[Back to top](#top)

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER CASE` are the parameters to be supplied by the user.
  e.g. in `add -n NAME`, NAME is a parameter which can be used as `add -n John Doe`.

</div>

[Back to top](#top)

### View help : `g help`
Shows all valid command formats and their functionalities.

![help message](images/helpMessage.png)

Format: `g help`

[Back to top](#top)

### Add a Patient record:  `p add`
Adds a patient to the list of patients.

Format: `p add -n PATIENT_NAME -ph PHONE_NUMBER -em EMAIL -ad ADDRESS -dob DATE_OF_BIRTH -g GENDER -ic NRIC [-da DRUG_ALLERGY] [-ec EMERGENCY_CONTACT_NUMBER]`

Examples:
* `p add -n John Doe -ph 98765432 -em johnd@example.com -ad John Street, Block 123, #01-01 -dob 21-01-2000 -g male -ic T3871918C `
* `p add -n Betsy Crowe -ph 92478963 -em betsycrowe@example.com -ad Sambal Street, Block 72, #04-12 -dob 01-01-2001 -g female -ic T8837191D -da Aspirin -ec 12345678`

[Back to top](#top)

### List all Patient records : `p list`

Shows a list of all patients in the CareFlow system in alphabetical order.

Format: `p list`

[Back to top](#top)

### Clear all Patient records : `p clear`

Clears all records for patients.

Format: `p clear`

[Back to top](#top)

### Retrieve a Patient record by name: `p find`
Finds patients whose names contain any of the given keywords.

Format: `p find -n PATIENT_NAME`
* The search is case-insensitive. e.g hans will match Hans
* Only the name is searched.

Examples:
* `p find John` returns john and John Doe
* `p find alex david` returns Alex Yeoh, David Li


### Delete a Patient record by NRIC: `p delete`
Deletes the specified patient from the patient list.

Format: `p delete -ic PATIENT_NRIC`

Examples:
* `p delete -ic T1234567B`

[Back to top](#top)

### Delete a Patient record by index: `p delete`
Deletes the person at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

Format: `p delete -in PATIENT_INDEX`

Example:
* list followed by p delete -in 2 will delete the 2nd person in the patient list.

[Back to top](#top)

### Update a patient by name: `p update`

Edits an existing patient in the careflow.

Format: `p update -n PATIENT_NAME [-n NEW_NAME] [-ph NEW_PHONE_NUMBER] [-em NEW_EMAIL] [-ad NEW_ADDRESS] [-dob NEW_DATE_OF_BIRTH] [-g GENDER] [-ic IC] [-da PATIENT_DRUG_ALLERGY] [-ec NEW_EMERGENCY_CONTACT_NUMBER]`

Example:
* p update John -ph 91234567 -em johndoe@example.com -ec 81234567 Edits the phone number, email address, emergency contact number of John to be 91234567, johndoe@example.com and 81234567 respectively.
* p update Bety -n Betsy Crower -da Aspirin Edits the name and drug allergy of Bety to be Betsy Crowers and Aspirin respectively.


[Back to top](#top)

### Add a Drug entry: `d add`
Adds a drug entry to the list of drugs.
Format: `d add -tn TRADE_NAME -ai ACTIVE_INGREDIENT -dir DIRECTIONS -pur PURPOSE -se SIDE_EFFECTS -sc STORAGE_COUNT`

Examples:
* `d add -tn Panadol -ai Paracetamol -dir Adults and children above 12 years old, 1-2 capsules -pur treat fever, headache, toothache, rheumatic and muscle pains -se dizziness, fatigue -sc 500`


[Back to top](#top)

### List all Drug entries: `d list`
Shows a list of all drug entries in the CareFlow system in alphabetical order.

Format: `d list`


[Back to top](#top)

### Retrieve a Drug entry by TRADE_NAME: `d find`
Finds drug(s) whose trade_name contains any of the given keywords.

Format: `d find -tn TRADE_NAME`
* The search is case-insensitive. e.g panadol will match Panadol.
* Only the trade name is searched.


[Back to top](#top)

### Delete a Drug entry by TRADE_NAME: `d delete`
Deletes the specified drug entry that matches the trade name keyword from the drug list.

Format: `d delete -tn TRADE_NAME`

Examples:
* `d delete -tn Panadol Flu Max`


[Back to top](#top)

### Delete a Drug entry by INDEX : `d delete`
Deletes the specified drug entry from the drug list based on the index of the drug list.

Format: `d delete -i DRUG_INDEX`

Examples:
* `d delete -i 7`
* This deletes the 7th drug entry in the most recently shown list


[Back to top](#top)

### Clear all Drug entries : `d clear`
Clears all entries for drugs.

Format: `d clear`


[Back to top](#top)

### Update storage count of a Drug entry: `d update`
Updates the storage count of specified drug entry based on trade name keyword from drug list.
* NOTE: concatenate + or - in front of value for addition/subtraction respectively

Format:
<br>`d update -tn TRADE_NAME -up +VALUE` <br> OR <br>
`d update -tn TRADE_NAME -up -VALUE`

Examples:
* `d update -tn Panadol Flu Max -up -10`
  * This **decreases** storage count of the drug entry with trade name matching "Panadol Flu Max" by **10**
* `d update -tn Tylenol PM -up +50`
  * This **increases** storage count of the drug entry with trade name matching "Tylenol PM" by **50**


[Back to top](#top)

### Exit the program : `g exit`
Exits the program.

Format: `g exit`


[Back to top](#top)

### Saving the data

CareFlow data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

[Back to top](#top)

### Editing the data file

All CareFlow data is saved as a JSON file `[JAR file location]/data/careflow.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CareFlow will discard all data and start with an empty data file at the next run.
</div>

[Back to top](#top)

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CareFlow home folder.

[Back to top](#top)

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                        | Format, Examples                                                                                                                                                                                                                                                                                |
|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**                      | `g help`                                                                                                                                                                                                                                                                                        |
| **Add patient**               | `p add -n PATIENT_NAME -ph PHONE_NUMBER -em EMAIL -ad ADDRESS -dob DATE_OF_BIRTH -g GENDER -ic NRIC [-da DRUG_ALLERGY] [-ec EMERGENCY_CONTACT_NUMBER]` <br> e.g., `add -n John Doe -ph 98765432 -em johnd@example.com -ad John Street, Block 123, #01-01 -dob 21-01-2000 -g male -ic T3871918C` |                                           |                                        |
| **List patient**              | `p list`                                                                                                                                                                                                                                                                                        |
| **Clear all patient**         | `p clear`                                                                                                                                                                                                                                                                                       |
| **Find patient by name**      | `p find -n PATIENT_NAME` <br> e.g., `p find -n John` returns `john` and `John Doe`                                                                                                                                                                                                              |
| **Delete patient by index**   | `p delete -in PATIENT_INDEX` <br> e.g., `delete -in 3`                                                                                                                                                                                                                                          |
| **Delete patient by NRIC**    | `p delete -ic PATIENT_NRIC` <br> e.g., `delete -ic T0021248C`                                                                                                                                                                                                                                   |
| **Update patient by name**    | `p update -n PATIENT_NAME [-n NEW_NAME] [-ph NEW_PHONE_NUMBER] [-em NEW_EMAIL] [-ad NEW_ADDRESS] [-dob NEW_DATE_OF_BIRTH] [-g GENDER] [-ic IC] [-da PATIENT_DRUG_ALLERGY] [-ec NEW_EMERGENCY_CONTACT_NUMBER]`                                                                                   |                                                                                                                                                                                                                                  |
| **Add a drug**                | `d add -tn TRADE_NAME -ai ACTIVE_INGREDIENT -dir DIRECTIONS -pur PURPOSE -se SIDE_EFFECTS -sc STORAGE_COUNT` <br> e.g., `d add -tn Panadol -ai paracetamol, sodium -dir Adults, 1-2 capsules -pur treat fever, headache -se dizziness -sc 500`                                                  |
| **List drug**                 | `d list`                                                                                                                                                                                                                                                                                        |
| **Clear all drugs**           | `d clear`                                                                                                                                                                                                                                                                                       |
| **Find drug by trade name**   | `d find -tn TRADE_NAME` <br> e.g., `d find -tn panadol`                                                                                                                                                                                                                                         |
| **Delete drug by trade name** | `d delete -tn TRADE_NAME`<br> e.g., `d delete -tn Panadol Flu Max`                                                                                                                                                                                                                              |
| **Delete drug by index**      | `d delete -i INDEX`<br> e.g.,`d delete -i 7`                                                                                                                                                                                                                                                    |
| **Update drug storage count** | `d update -tn TRADE_NAME -sc +-VALUE`<br> e.g.,`d update -tn Panadol Flu Max -sc +90`                                                                                                                                                                                                           |
| **Exit**                      | `g exit`                                                                                                                                                                                                                                                                                        |

[Back to top](#top)

