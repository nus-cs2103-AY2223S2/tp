---
layout: page
title: Jerald Kiew's Project Portfolio Page
---

### Project: MedInfo

MedInfo is a desktop application for private hospital administrative staff. It helps manage patients,
their status, discharge dates and wards. MedInfo aims to solve the problem of slow, multiple step process of
documenting patient medical records during in-processing by zeroing in on the important details and provide simple,
fast access to a particular patient’s medical records for hospital admin staff.

The user interacts with MedInfo using a CLI, and it has a GUI created with JavaFX.
MedInfo is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature**

  - Ward implementation 
    - `editward` command is a core feature required to augment the name and capacities of existing wards, and ensure these changes reflect for the corresponding patients [\#163](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/163)
    - `Capacity` class together with `Ward` helped check that every ward's occupancy does not exceed its allocated capacity [\#138](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/138) 
  - Status implementation 
  - Patient Class implementation 
    - Refactoring `Patient` class to accomodate a `Ward`, so that patients can be assigned to their respective wards [\#109](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/109)

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jeraldkiew&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project management**:

  - Reviewed and merged teammate's PRs
  - Overviewed the design of classes under model
  - Spearheaded documentation 
  - Ensured team vision was aligned

- **Enhancements to existing features**:

  - Implemented total occupancy of MedInfo
    - Tracks and displays total number of patients currently in MedInfo [\#146](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/146)
  - Fixed response and error messages
    - Standardised and corrected messages concerning optional parameters [\#265](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/265)
    - Fixed `help` message linking user to User Guide [\#265](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/265)
  - Refactored initial `Person` and `Person`-related classes to `Patient`
    - Refactored existing classes as well as designed new classes for our new purposes in model [\#85](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/85) [\#86](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/86) [\#89](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/89)

- **Documentation**:

  - User Guide
    - Added documentation for `editward` and `deleteward` [\#171](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/171)
    - Added informative sections for `delete` and `deleteward` confirmation and constraints [\#265](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/265)
    - Added and corrected Ui screenshots [\#265](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/265)
  - Developer Guide:
    - Added various user stories [\#137](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/137)
    - Added and updated PUML diagrams [\#152](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/152) [\#251](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/251) [\#265](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/265)

<img src="../images/ArchitectureSequenceDiagram.png" width="574" />
<img src="../images/DeleteSequenceDiagram.png" width="574" />
<img src="../images/LogicClassDiagram.png" width="574" />
<img src="../images/ModelClassDiagram.png" width="574" />
<img src="../images/ParserClasses.png" width="574" />
<img src="../images/StorageClassDiagram.png" width="574" />
<img src="../images/UiClassDiagram.png" width="574" />


- **Contributions to Team-based tasks**:
    - Refactored Person to Patient throughout the code
    - Maintained issue tracker
    - Updated user and developer docs

- **Review/mentoring contributions**:

  - PRs reviewed (with non-trivial review comments): [\#136](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/136) [\#159](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/159) 

- **Contributions beyond the project team**:
  - Reported bugs and suggestion for another team in the class - [Ultron](https://github.com/AY2223S2-CS2103T-F12-4/tp) [\#154](https://github.com/AY2223S2-CS2103T-F12-4/tp/issues/154) [\#160](https://github.com/AY2223S2-CS2103T-F12-4/tp/issues/160) [\#167](https://github.com/AY2223S2-CS2103T-F12-4/tp/issues/167)
