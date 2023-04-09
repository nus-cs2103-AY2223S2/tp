---
layout: page
title: Stefanos Avraam's Project Portfolio Page
 ---

### Project: MediConnect

Overview:
MediConnect is a desktop application used for managing patients and doctors' information.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=stefanosav&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Contributed to releases `v1.2` - `v1.4` (3 releases) on Github
    * Participated in weekly group meetings to discuss the project's progress
    * Assisted in the setting and maintenance of milestones and deadlines

* **New features**:
    * Add `addPatient` and `addDoctor` commands [(Pull Request #24)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/24)
      * What it does: Allows users to add people with specific roles (i.e Patients or Doctors) to MediConnect.
      * Justification: These features enable the distinction between patients treated and doctors working at the hospital.
      Therefore, other features can target one of the two groups and perform specific actions according to the person's role.
    * Add `listDoctors` and `listPatients` commands [(Pull Request #67)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/67)
      * What it does: Allows users to obtain a list of either only doctors or only patients.
      * Justification: These features enable the separation of the two groups to view the collective details of either group.

* **Enhancements to existing features**:
    * Add NRIC field to add commands [(Pull Request #24)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/24)
      * What was changed: Added an additional field to the existing features of each person.
      * Justification: Originally, each person could only be identified by their name. This was not practical for the 
      nature of a medical information management application, so by adding the NRIC field, we allow users to easily 
      identify the persons registered in MediConnect.
    * Update find command [(Pull Request #52)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/52)
      * What was changed: The find command was changed to find and display the person with the given NRIC number.
      * Justification: The original find command was using the person's name to display their details. This was not 
      practical for the nature of a medical information management application, so changing the command to use the 
      person's NRIC number, ensures that the correct person is shown every time.

* **Documentation**:
    * User Guide contributions:
      * Update 'Adding a person' section to include 'addPatient' and 'addDoctor' commands [(Pull Request #43)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/43)
      * Update 'Locating persons' section to reflect the `find` by NRIC command [(Pull Request #55)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/55)
      * Contributed in the sections related to the appointment commands and update the Ui screenshots for v1.3 [(Pull Request #65)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/65)
      * Add description for `listDoctors` and `listPatients` commands [(Pull Request #67)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/67)
      * Overall corrections and enhancements of the User Guide [(Pull Request #55)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/55)
  
    * Developer Guide contributions: [(Pull Request #152)](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/152)
      * Add implementation details for `addPatient` and `addDoctor` features
      * Add diagrams for `addDoctor` features
      * Overall corrections and enhancements of the Developer Guide

* **Community**:
    * **Contributions to team-based tasks:** 
      * PRs reviewed: [\#33](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/33), [\#57](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/57), [\#126](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/126), [\#131](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/131)
    * **Contributions beyond the team:**
      * Reported bugs and suggestions for other teams in class [(Issues Reported)](https://github.com/AY2223S2-CS2103T-T11-1/tp/issues?q=is%3Aissue+%22%5BTester+C%5D%22)

* **Tools**:
  * IntelliJ IDEA
  * Sourcetree
  * Github
