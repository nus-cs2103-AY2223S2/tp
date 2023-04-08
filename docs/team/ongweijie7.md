---
layout: page
title: Ong Wei Jie's Project Portfolio Page
---

### Project: HospiSearch

HospiSearch is a desktop app for healthcare administrators to manage hospital/clinic patients' particulars and optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HospiSearch can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added ability to filter by `name` field. [#42](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/42)
    * What it does: Allows users to filter patient records by their `name`s.
    * Justification: This feature allows for filtering patient records by their `name`s before viewing it for more details.
    * Highlights: Can input multiple `name`s to filter by.


* **New Feature**: Added ability to filter by `nric` field. [#58](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/58)
    * What it does: Allows users to filter patient records by their `nric`s.
    * Justification: This feature allows for filtering patient records by their `nric`s before viewing it for more details.
    * Highlights: Can input multiple `nric`s to filter by.


* **New Feature**: Added `gender` field in patient records. [#93](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/93)
    * What it does: Allows users to store `gender` attribute of patients.
    * Justification: This feature allows for identification of gender given a patient.
    * Highlights: To prevent incorrect formats, the `gender` is only able to take in `male`, `female` or `others`.


* **New Feature**: Added ability to filter by `medicine` field. [#112](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/112)
    * What it does: Allows users to filter patient records by their `medicine` usage.
    * Justification: This feature allows for filtering patient records by their `medicine` usage before viewing it for more details.
    * Highlights: Can input multiple `medicine`s to filter by.


* **New Feature**: Added functionality to make email an optional field. [#119](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/119)
    * What it does: Allows users to create a patient record with the email as an optional field.
    * Justification: This feature allows for users to leave out the email field should the patient be unwilling to share it.
    * Highlights: Users can simply leave out the `e/` prefix when entering a user and it will be shown as a `-` in the patient's record or they can choose
  to enter `e/-` and the email will also be shown as a `-`.


* **New Feature**: Updated the `help` command pop-out. [#120](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/120)
    * What it does: Updated the list of commands on the `help` command pop-out window for users who require help
    * Justification: This feature allows for users get quick access to information should they struggle with any syntax of the commands.


* **New Feature**: Added `DateOfBirth` field in patient records. [#125](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/125)
    * What it does: Allows users to store `DateOfBirth` attribute of patients.
    * Justification: This feature allows for identification of the date of birth of patient.
    * Highlights: By inputting in date in the format `DD/MM/YYYY`, the date of birth shown in the record will be `DD MONTH YYYY`


### **Code contributed**:
  [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=ongweijie7&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


### **Enhancements to existing features**:

* Changed the syntax of `find` command to be aligned with that of `add` command. [#76](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/76)
* Changed `find` command from filtering by address to filtering by medicine. [#112](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/112)
* Added test cases for `find` command for the various prefixes to search for. [#42](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/42), [#58](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/58), [#112](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/112)
* Added more test cases to detect any bugs when adding `gender` field and executing `find` command. [#105](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/105)
* Added test cases for `Gender` classes. [#93](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/93)
* Added test cases to allow for `email` as an optional field which requires for `null` checking when email field is empty. [#119](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/119)
* Added test cases for `DateOfBirth` classes. [#125](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/125)


### **Documentation**:

User Guide:
* Added documentation to include details and example of the basic commands we were implementing. [#15](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/15)
* Added documentation to include command notes to take note for each command. [#129](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/129)


Developer Guide:
* Added implementation details and design considerations for the `find` command and its respective prefixes. [#95](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/95)
* Added sequence diagram for `find` command. [#95](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/95)
* Added test cases implementation for the `find` command. [#109](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/109)


### **Review contributions**:
* Organised and coordinated team meetings.
* Took video demo for `v1.3`.
* Reviewed and approved PRs for merging.
* PRs reviewed (with non-trivial comments): [#83](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/83), [#85](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/85), [#86](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/86)

### **Review contributions beyond the team**:
* Reported a total of [10](https://github.com/ongweijie7/ped/issues) bugs and issues for PE-D.
