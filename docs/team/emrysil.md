# Xintong's Project Portfolio Page

## Overview
**CareFlow** is a desktop application for patient and drug inventory management, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
**CareFlow** can get your patient and drugs management tasks done faster than traditional practices.

## Code Contributed
Click [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=emrysil&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) to see my code contributions.

## Summary of Contributions
### **Redesign of the Overall Architecture**
  - Redesign the overall architecture to accommodate patient and drug subsystems.
    - Modified the code structure and organisation of `model` component to allow for the store and access of patient and drug data.
      - Created `DrugInventory` and `PatientRecord` to handle drug and patient related operations respectively.
      - Created `CareFlowModelManager` to proxy manipulation of patient and drug data.
    - Modified the code structure and organisation of `logic` component to allow for the interpretation and execution of user commands.
      - Created operational command parsers for drug and patient
        - Created `CareFlowParser` to handle user commands and further delegate the command to patient, drug and general operational command parsers.
        - Created `PatientParser`,`DrugParser`, `GeneralParser` to handle patient, drug and general operational commands and further delegate the command to specific command parsers.
      - Created `CareFlowLogicManager` to proxy command execution.
    - Modified the code structure and organisation of `storage` component to allow for the reading and writing of app data from and into the hard disk.
      - Created `JsonAdaptedDrug` and `JsonAdaptedPatient` to adapt the `Drug` and `Patient` model for storage in Json format.
      - Created `JsonSerializableDrugInventory` and `JsonSerializablePatientRecord` to adapt `DrugInventory` and `PatientRecord` model for storage in Json format.
      - Created `JsonDrugInventoryStorage` and `JsonPatientRecordStorage` to facilitate the reading and writing of patient and drug data into the hard disk.
      - Created the `CareFlowStorageManager` to proxy storage related operations.
### **New Features**
  - Implemented the drug inventory in-app notification feature 
    - This feature serves to remind users of low storage count to help users better plan for drug replenishment.
  - Implemented the `clear` drug and `clear` patient feature
    - This feature allows users to remove all drug or patient data entries at one go.
  - Implemented the `drug` model
    - Created `ActiveIngredient`, `Direction`, `Purpose` and `SideEffect` classes to store relevant drug information.
### **Enhancements to Application UI**
  - Designed and implemented the patient and drug information display panel to provide better information visualisation.
### **Testing**
  - Created test cases for storage components
### **User Guide**
  - Added detailed instruction on how to download and set up java 11 environment.
    - Created step-by-step guide on how to install java 11 on macOS and Windows operating systems.
    - Accompanied instructions with rich screenshots to further support non-tech users.
  - Added screenshots for various features to illustrate the expected interaction and outcome.
  - Reformatted each feature for better visual presentation.
### **Developer Guide**
  - Added implementation details and UML diagram for `storage` component.
  - Added execution detail and sequence diagram for `d find` command, using `d find Panadol` as the example input.
