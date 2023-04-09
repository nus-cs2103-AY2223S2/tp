# Xintong's Project Portfolio Page

## Overview
**CareFlow** is a desktop application for patient and drug inventory management, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
**CareFlow** can get your patient and drugs management tasks done faster than traditional practices.

## Code Contributed
Click [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=emrysil&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to see my code contributions.

## Summary of Contributions
### **Redesign of the Overall Architecture**
  - Redesign the overall architecture to accommodate patient and drug subsystems.
    - Created project skeleton files. [#39](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/39)
    - Modified the code structure and organisation of `model` component to allow for the store and access of patient and drug data. [#55](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/55)
      - Created `ReadOnlyDrugInventory`, `DrugInventory`, `ReadOnlyPatientRecord` and `PatientRecord` to handle drug and patient related operations respectively.
      - Created `CareFlow` to allow CRUD of patient and drug objects.
      - Created `CareFlowModel`, `CareFlowModelManager` to proxy manipulation of patient and drug data.
    - Modified the code structure and organisation of `logic` component to allow for the interpretation and execution of user commands.
      - Updated parser hierarchy by creating operational command parsers for drug and patient. [#50](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/50)
        - Updated the command syntax to use '**p**', '**d**' and '**g**' to differentiate between **patient**, **drug** and **general** commands.
        - Created `CareFlowParser` to handle user commands and further delegate the command to patient, drug and general operational command parsers.
        - Created `PatientParser`,`DrugParser`, `GeneralParser` to handle patient, drug and general operational commands and further delegate the command to specific command parsers.
      - Created `CareFlowLogicManager` to proxy command execution. [#55](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/55)
    - Modified the code structure and organisation of `storage` component to allow for the reading and writing of app data from and into the hard disk. [#79](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/79) [#80](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/80)
      - Created `JsonAdaptedPatient` to adapt the `Patient` model for storage in Json format.
      - Created `JsonSerializableDrugInventory` and `JsonSerializablePatientRecord` to adapt `DrugInventory` and `PatientRecord` model for storage in Json format. 
      - Created `JsonDrugInventoryStorage` and `JsonPatientRecordStorage` to facilitate the reading and writing of patient and drug data into the hard disk.
      - Created the `CareFlowStorageManager` to proxy storage related operations.
### **New Features**
  - Implemented the drug inventory in-app notification feature [#212](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/212)
    - This feature serves to remind users of low storage count to help users better plan for drug replenishment.
  - Implemented the `clear` drug and `clear` patient feature [#55](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/55)
    - This feature allows users to remove all drug or patient data entries at one go.
  - Implemented the `Drug` model [#42](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/42)
    - Created `ActiveIngredient`, `Direction`, and `Drug` classes to store relevant drug information.
    - Created `UniqueDrugList` to store unique Drug objects.
### **Enhancements to Application UI**
  - Designed and implemented the patient and drug information display panel to provide better information visualisation. [#176](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/176)
  - Implemented the UI status bar footer to allow display of source file paths. [#80](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/80)
  - Added the function to remember the theme selected by the user. [#106](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/106/files)
### **Testing**
  - Created JUnit test cases for storage components. [#133](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/133)
    - Created test methods for `CareFlowStorageManager` to test the reading and saving of
      - `DrugInventory`
      - `PatientRecord`
      - `UserPrefs`
    - Created test methods for `JsonAdaptedDrug` and `JsonAdaptedPatient` to test the conversion of `Patient` and `Drug` objects into json-friendly forms when:
      <details>
      <summary>Cases Tested </summary>
      <ul> 
        <li>the fields are in order</li>
        <li>the fields are null</li>
        <li>the fields are invalid</li>
      </ul>
      </details>
    - Created sample files and test methods for `JsonDrugInventoryStorage`, `JsonPatientRecordStorage` and `JsonUserPrefsStorage` to simulate the following scenarios:
      <details>
      <summary>Cases Tested </summary>
      <ul>
        <li>reading from normal json file</li>
        <li>reading from non-json format file</li>
        <li>reading from json file with extra fields</li>
        <li>reading from json file with missing fields</li>
        <li>reading from json file with invalid fields</li>
        <li>reading from json file with both vald and invaild fields</li>
        <li>reading from json file with null value for compulsory fields</li>
        <li>saving normal `DrugInventory` and `PatientRecord` objects</li>
        <li>saving when `DrugInventory` and `PatientRecord` objects are null</li>
        <li>saving when file path is null</li>
      </ul>
      </details> 
    - Created test methods for `JsonSerializableDrugInventory` and `JsonSerializablePatientRecord` to test the conversion of json format `DrugInventory` and `PatientRecord` objects back to java objects.
  - Created sample drug and patient data to be used in testings. [#133](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/133)
    - Created `DrugBuilder`, `PatientBuilder`, `TypicalDrugs` and `TypicalPatients` testutil classes.
### **User Guide**
  - Added detailed instruction on how to download and set up java 11 environment. [#176](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/176)
    - Created step-by-step guide on how to install java 11 on macOS and Windows operating systems.
    - Accompanied instructions with rich screenshots to further support non-tech users.
  - Added screenshots for various features to illustrate the expected interaction and outcome. [#193](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/193) [#221](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/221)
  - Reformatted each feature for better visual presentation. [#194](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/194)
  - Added notes and tips for various features to help user better understand the functionality of each feature. [#297](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/297)
### **Developer Guide**
  - Added implementation details and UML diagram for `storage` component. [#164](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/164)
  - Added execution detail and sequence diagram for `d find` command, using `d find Panadol` as the example input. [#213](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/213) [#219](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/219)
### **Issues Fixed**
  - Unable to store and read of patient record [#83](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/83)
  - Inconsistent command snytax [#151](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/151)
  - Wrong command syntax in UG [#283](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/283)
  - Wrong error message for `p view INDEX`  and `d view INDEX` commands [#280](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/280)

