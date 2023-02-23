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

### Viewing help : `help`

Shows all valid command formats and their functionalities.

![help message](images/helpMessage.png)

Format: `help`


### Adding a patient:  `p add`

Adds a patient to the list of patients.

Format: `p add -n PATIENT_NAME -ic NRIC -p PHONE_NUMBER -e EMAIL -a ADDRESS`

Examples:
* `add -n John Doe -ic T3871918C -p 98765432 -e johnd@example.com -a John Street, Block 123, #01-01`
* `add -n Betsy Crowe -ic T8837191D -p 92478963 -e betsycrowe@example.com -a Sambal Street, Block 72, #04-12`

### Listing all persons : `p list`

Shows a list of all patients in the CareFlow system in alphabetical order.

Format: `p list`

### Retrieve a patient record by name: `p find`
Finds patients whose names contain any of the given keywords.
Format: `p find -n PATIENT_NAME`
* The search is case-insensitive. e.g hans will match Hans
* Only the name is searched.

Examples:
* `find John` returns john and John Doe
* `find alex david` returns Alex Yeoh, David Li
 


### Retrieve a patient record by NRIC: `p find`
Finds patient whose NRIC matches given keyword
Format: `p find -ic PATIENT_NRIC`
Examples:
* p find -ic T3871918C 
* Returns John Doe | T3871918C | 98765432 | johnd@example.com | John Street, Block 123, #01-01

### Delete a patient record by NRIC: `p delete`
Deletes the specified patient from the patient list.

Format: `p delete -ic PATIENT_NRIC`

Examples: 
* `p delete -ic T1234567B`

### Deleting a patient by index: `p delete`

Deletes the person at the specified INDEX.
* The index refers to the index number shown in the displayed person list.
* The index must be a positive integer 1, 2, 3, …​

Format: `p delete -in PATIENT_INDEX`

Example:
* list followed by p delete -in 2 will delete the 2nd person in the patient list.


### Adding a drug: `d add`
Adds a drug to the list of drugs.
Format: `d add -dn DRUG_NAME -tn TRADE_NAME -s STOCK`

Examples:
* `d add -dn paracetamol -tn Panadol -s 500`


### Retrieve a Drug by TRADE_NAME: `d find`
Finds drugs whose trade_name contains any of the given keywords.

Format: `d find -tn TRADE_NAME`
* The search is case-insensitive. e.g panadol will match Panadol.
* Only the trade name is searched.

### Retrieve a Drug by DRUG_NAME : `d find`
Finds drugs whose drug_name contains any of the given keywords.

Format: `d find -dn DRUG_NAME`

* The search is case-insensitive. e.g Famotidine will match famotidine.
* Only the drug name is searched.

Examples:
* `d find -dn paracetamol` 
* This returns Panadol 500 and Tylenol 300


### Delete a Drug by TRADE_NAME: `d delete`
Deletes the specified drug that matches the tradename keyword from the drug list.

Format: `d delete -tn TRADE_NAME`

Examples:
* `d delete -tn Panadol Flu Max`


### Delete a Drug by INDEX : `d delete`

Deletes the specified drug from the drug list based on the index of the drug.

Format: `d delete -i DRUG_INDEX`

Examples:
* `d delete -i 7`
* This deletes the 7th drug in the most recently shown list

### Clearing all drug entries : `d clear`

Clears all entries for drugs.

Format: `d clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

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

Action | Format, Examples
--------|------------------
**Help**| `help`
**Add patient** | `add -n NAME -p PHONE_NUMBER -e EMAIL -a ADDRESS` <br> e.g., `add -n John Doe -p 98765432 -e johnd@example.com -a John street, block 123, #01-01`
**List patient** | `p list`
**Find patient by name** | `p find -n PATIENT_NAME` <br> e.g., `p find -n John` returns `john` and `John Doe`
**Find patient by NRIC** | `p find -ic PATIENT_NRIC` <br> e.g., `find -ic T0021248C` 
**Delete patient by index** | `p delete -in PATIENT_INDEX` <br> e.g., `delete -in 3`
**Delete patient by NRIC** | `p delete -ic PATIENT_NRIC` <br> e.g., `delete -ic T0021248C`
**Add a drug** | `d add -dn DRUG_NAME -tn TRADE_NAME -s STOCK` <br> e.g., `add -dn paracetamol  -tn Panadol -s 500`
**Find drug by trade name** |`d find -tn TRADE_NAME` <br> e.g., `d find -tn panadol`
**Find drug by drug name** | `d find -dn DRUG_NAME` <br> e.g., `d find -dn paracetamol`
**Delete drug by trade name** | `d delete -tn TRADE_NAME`<br> e.g., `d delete -tn Panadol Flu Max`
**Delete drug by index** | `d delete -i INDEX`<br> e.g.,`d delete -i 7`
**Clear all drugs** | `d clear`
**Exit** | `exit`

