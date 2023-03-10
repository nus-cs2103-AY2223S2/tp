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

   * `p add -n John Doe -g male -p 98765432 -e johnd@example.com -a John street, block 123, #01-01` : Adds a patient named `John Doe` to the patient records.

   * `p delete -in 3` : Deletes the 3rd patient record shown in the current list.

   * `p clear` : Deletes all patient records.

   * `d clear` : Deletes all drug records

   * `exit` : Exits the app.

#### Notes about command format:
* Words in UPPER CASE are the parameters to be supplied by the user.
e.g. in add -n NAME, NAME is a parameter which can be used as add -n John Doe.


--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER CASE` are the parameters to be supplied by the user.
  e.g. in `add -n NAME`, NAME is a parameter which can be used as `add -n John Doe`.

</div>

### View help : `g help`
Shows all valid command formats and their functionalities.

![help message](images/helpMessage.png)

Format: `g help`


### Add a Patient record:  `p add`
Adds a patient to the list of patients.

Format: `p add -n PATIENT_NAME -ic NRIC -p PHONE_NUMBER -e EMAIL -a ADDRESS`

Examples:
* `add -n John Doe -ic T3871918C -p 98765432 -e johnd@example.com -a John Street, Block 123, #01-01`
* `add -n Betsy Crowe -ic T8837191D -p 92478963 -e betsycrowe@example.com -a Sambal Street, Block 72, #04-12`


### List all Patient records : `p list`

Shows a list of all patients in the CareFlow system in alphabetical order.

Format: `p list`


### Clear all Patient records : `p clear`

Clears all records for patients.

Format: `p clear`


### Retrieve a Patient record by name: `p find`
Finds patients whose names contain any of the given keywords.
Format: `p find -n PATIENT_NAME`
* The search is case-insensitive. e.g hans will match Hans
* Only the name is searched.

Examples:
* `find John` returns john and John Doe
* `find alex david` returns Alex Yeoh, David Li


### Retrieve a Patient record by NRIC: `p find`
Finds patient whose NRIC matches given keyword
Format: `p find -ic PATIENT_NRIC`
Examples:
* p find -ic T3871918C 
* Returns John Doe | T3871918C | 98765432 | johnd@example.com | John Street, Block 123, #01-01


### Delete a Patient record by NRIC: `p delete`
Deletes the specified patient from the patient list.

Format: `p delete -ic PATIENT_NRIC`

Examples:
* `p delete -ic T1234567B`

### Delete a Patient record by index: `p delete`
Deletes the person at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

Format: `p delete -in PATIENT_INDEX`

Example:
* list followed by p delete -in 2 will delete the 2nd person in the patient list.

### Update a patient by name: `p update`

Edits an existing patient in the careflow.

Format: `p update -n PATIENT_NAME -ph PATIENT_PHONE_NUMBER -em PATIENT_EMAIL -ad PATIENT_ADDRESS -dob DATE_OF_BIRTH -g GENDER -ic PATIENT_IC -da PATIENT_DRUG_ALLERGY -ec EMERGENCY_CONTACT_NUMBER`

Example:
* p update John -ph 91234567 -em johndoe@example.com -ec 81234567 Edits the phone number, email address, emergency contact number of John to be 91234567, johndoe@example.com and 81234567 respectively.
* p update Bety n/Betsy Crower -da Aspirin Edits the name and drug allergy of Bety to be Betsy Crowers and Aspirin respectively.


### Add a Drug entry: `d add`
Adds a drug entry to the list of drugs.
Format: `d add -tn TRADE_NAME -ai ACTIVE_INGREDIENT -dir DIRECTIONS -pur PURPOSE -se SIDE_EFFECTS -sc STORAGE_COUNT`

Examples:
* `d add -tn Panadol -ai Paracetamol, sodium - dir Adults and children above 12 years old, 1-2 capsules - pur treat fever, headache, toothache, rheumatic and muscle pains -se dizziness, fatigue -sc 500`


### List all Drug entries: `d list`
Shows a list of all drug entries in the CareFlow system in alphabetical order.

Format: `d list`


### Retrieve a Drug entry by TRADE_NAME: `d find`
Finds drug(s) whose trade_name contains any of the given keywords.

Format: `d find -tn TRADE_NAME`
* The search is case-insensitive. e.g panadol will match Panadol.
* Only the trade name is searched.


### Delete a Drug entry by TRADE_NAME: `d delete`
Deletes the specified drug entry that matches the trade name keyword from the drug list.

Format: `d delete -tn TRADE_NAME`

Examples:
* `d delete -tn Panadol Flu Max`


### Delete a Drug entry by INDEX : `d delete`
Deletes the specified drug entry from the drug list based on the index of the drug list.

Format: `d delete -i DRUG_INDEX`

Examples:
* `d delete -i 7`
* This deletes the 7th drug entry in the most recently shown list


### Clear all Drug entries : `d clear`
Clears all entries for drugs.

Format: `d clear`


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


### Exit the program : `g exit`
Exits the program.

Format: `g exit`


### Saving the data

CareFlow data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

All CareFlow data is saved as a JSON file `[JAR file location]/data/careflow.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, CareFlow will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous CareFlow home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

| Action                        | Format, Examples                                                                                                                                                                                                                               |
|-------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Help**                      | `g help`                                                                                                                                                                                                                                       |
| **Add patient**               | `p add -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS` <br> e.g., `add -n John Doe -p 98765432 -e johnd@example.com -a John street, block 123, #01-01`                                                                                            |
| **List patient**              | `p list`                                                                                                                                                                                                                                       |
| **Clear all patient**         | `p clear`                                                                                                                                                                                                                                      |
| **Find patient by name**      | `p find -n PATIENT_NAME` <br> e.g., `p find -n John` returns `john` and `John Doe`                                                                                                                                                             |
| **Find patient by NRIC**      | `p find -ic PATIENT_NRIC` <br> e.g., `find -ic T0021248C`                                                                                                                                                                                      |
| **Delete patient by index**   | `p delete -in PATIENT_INDEX` <br> e.g., `delete -in 3`                                                                                                                                                                                         |
| **Delete patient by NRIC**    | `p delete -ic PATIENT_NRIC` <br> e.g., `delete -ic T0021248C`                                                                                                                                                                                  |
| **Add a drug**                | `d add -tn TRADE_NAME -ai ACTIVE_INGREDIENT -dir DIRECTIONS -pur PURPOSE -se SIDE_EFFECTS -sc STORAGE_COUNT` <br> e.g., `d add -tn Panadol -ai paracetamol, sodium -dir Adults, 1-2 capsules -pur treat fever, headache -se dizziness -sc 500` |
| **List drug**                 | `d list`                                                                                                                                                                                                                                       |
| **Clear all drugs**           | `d clear`                                                                                                                                                                                                                                      |
| **Find drug by trade name**   | `d find -tn TRADE_NAME` <br> e.g., `d find -tn panadol`                                                                                                                                                                                        |
| **Delete drug by trade name** | `d delete -tn TRADE_NAME`<br> e.g., `d delete -tn Panadol Flu Max`                                                                                                                                                                             |
| **Delete drug by index**      | `d delete -i INDEX`<br> e.g.,`d delete -i 7`                                                                                                                                                                                                   |
| **Update drug storage count** | `d update -tn TRADE_NAME -sc +-VALUE`<br> e.g.,`d update -tn Panadol Flu Max -sc +90`                                                                                                                                                          |
| **Exit**                      | `g exit`                                                                                                                                                                                                                                       |

