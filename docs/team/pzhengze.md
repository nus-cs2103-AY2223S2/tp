---
layout: page
title: Zheng Ze's Project Portfolio Page
---

## Project: Patientist - hospital management system

Patientist is a patient management system that aims to streamline data management in medical facilities such as Hospitals.
The app is fast and lightweight to use due to its optimisation for Command Line Interface (CLI) interaction, while
providing many features tailored to Medical Staffs.

### Contributions

**Code contributed**: 

Check out my code contributions [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=pzhengze&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=pzhengze&tabRepo=AY2223S2-CS2103T-T12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Model Component**
  * Updated `filteredPersonList` in Patientist to be able to update in real time.
  * Added `PatientNameContainsKeywordsPredicate` and `PidContainsKeywordsPredicate` later renamed to `PatientIdContainsKeywordsPredicate`

* **Storage Component**

  Changes made by [`Lin Chieh`](https://ay2223s2-cs2103t-t12-1.github.io/tp/team/euph00.html) to the `Model`
  component to cater to the new hierarchial structure of `Patientist` necessitated a change to the `Storage`
  component to be able to save into a Json file and load the saved Json file back into `Patientist`.
  * Added capability to convert `Ward`s into a Json file.
  * Added capability to save `Person`s in wards while saving `Ward`s.
  * Added capability to save `ID`, `Status` and `Priority` while saving `Patient` or `Staff`.
  * Added capability to save `Status` and `Priority` while saving `Patient`.


* **Logic Component**

  Changes to the `Model` component necessitated a change to the `Logic` component to make use of the new methods.
  * Updated `Help` command to `Patientist` UG instead of `AB3` UG.
  * Updated `Add` command to create new `Patient`s.
  * Updated `Edit` command to edit all personal details(`Name`, `Phone`, `Email`, `Address`, `Tag`) of a `Person` except their `ID`.
  * Updated `Delete` command to be able to delete a `Person` from `Patientist`.
  * Updated `ListWardPatients` command created by [`Glenn`](https://ay2223s2-cs2103t-t12-1.github.io/tp/team/glennongjunjie.html)
    to work with updated `Model`.
  * Updated `View` command created by [`Jin`](https://ay2223s2-cs2103t-t12-1.github.io/tp/team/jinnieshin.html)
    to display by index.
  * Added capability to add/delete `Status` to/from a `Patient`.
  * Added capability to add/delete `Ward` to/from `Patientist`.
  * Added capability to list `Patients`.
  * Added capability to find `Patient` by `Name` and `ID`.
  * Added capability to list all wards.

* **Ui Component**

  Our group wanted a minimalistic theme for `Patientist` to create less strain to the eye.
  * Updated `Ui` to have less sharp corners and be more rounded, inspired by Googles's [Material Design](https://m3.material.io/).
  * Updated `Person Card` to be 2 different separate ui elements `Patient Card` and `Staff Card`.
  * Updated `PatientCard`, `StaffCard`, `ResultDisplay` and `DetailsPopUp` to wrap text when fields are too long.
  * Added `RoleTag` to `PatientCard`, `StaffCard` and `DetailsPopUp`.
  * Added `Status`, `Ward`, `Priority` field in `DetailsPopup`.
  * Added `Priority` tag to `PatientCard`.
  * Added `ID` and `Ward` field to both `PatientCard` and `StaffCard`.
  * Added `WardList` and `WardCard` to display `Ward`s.
  * Added capability to remember if displaying `PersonListPanel` or `WardListPanel` within current session.

**Project management**:
* Created GitHub Organisation and Team Repository
* Reported 5 Bugs: [link](https://github.com/AY2223S2-CS2103T-T12-1/tp/issues?q=is%3Aissue+author%3Apzhengze+label%3Abug)
* Reviewed 31 PRs: [link](https://github.com/AY2223S2-CS2103T-T12-1/tp/pulls?q=is%3Apr+reviewed-by%3Apzhengze+)

**Documentation**:
  * User Guide:
    * Updated and edited sections for:
      * `Add Patient`
      * `Delete Patient`
      * `Add Staff`
      * `Delete Staff`
      * `Add Ward`
      * `Delete Ward`
      * `Add Patient Status`
      * `Delete Patient Status`
      * `List Patient`
      * `List Staff`
      * `List Ward`
      * `Find`
      * `Find Patient`
      * `View`
      * `Edit`
    * Edited Summary

  * Developer Guide:
    * Added Non-Functional Requirements
    * Updated Logic Component in DG
    * Updated Storage Component in DG
