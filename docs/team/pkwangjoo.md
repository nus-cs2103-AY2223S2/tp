---
layout: page
title: Park Kwangjoo's Project Portfolio Page
---

### Project: SudoHR

SudoHR is a desktop app specially catered for HR professionals in managing manpower,
optimized for use via a Command Line Interface (CLI) while still
having the benefits of a Graphical User Interface (GUI).
If you can type fast, SudoHR can get your
management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

{% include ppp-summary.html %}

Given below are my contributions to the project.

- **New Feature**: `Integration of Department and Leave` [[PR#84]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/84)

  - What was done: Integrated the usecases of Department level functionalities to allow users to query headcount for the departments by taking into account the leaves taken by each employee in the specific department.
  - Justification: This feature makes the user experience more cohesive.
  - Highlights:
    - Required deep understanding of both Department and Leave functionalities implementation as well as the base structure for integration.
    - Benefited from the open-close principle used for the design of Department and Leave Functionalities.
  - Relevant pull request: [PR#156](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/156), [PR#197](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/197)

- **New Feature**: `ldep`, `fdep`, `ddep`, `leid`, `llbe`, `sa`, `ldhc` commands

  - What it does:
    - `ldep` - Lists all department [PR#108](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/108)
    - `fdep` - Find department by keyword [PR#125](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/125)
    - `ddep` - Delete department [#PR107](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/107)
    - `leid` - Lists all employees within a department [PR#134](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/134)
    - `llbe` - Lists all the leaves taken by an employee [#PR145](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/145)
    - `sa` - Shows all the departments, leaves and employees [#PR203](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/203)
    - `ldhc` - Lists the department headcount for a specified date or the current date by default. [PR#156](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/156), [PR#197](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/197)

- **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=pkwangjoo&tabRepo=AY2223S2-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
- **Enhancements to existing features**:

  - What was done: Refactored the codebase and the testcases to fit our usecase of SudoHr and Employees.
  - Justification: Make our codebase consistent with the domain language that we are using to communicate and develop the application.
  - Highlights:
    - Deep understanding of the control flow to ensure that the refactored parts did not cause bugs in the original functionality.
    - Advanced use of refactoring tools in the IDE to ensure that all the components and their dependencies were refactored efficiently and accurately.
  - Relevant pull request: [PR#83](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/83), [PR#97](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/97)

- **Contributions to team-based tasks**

  - Reviewed a non-trivial PR changing the internal handling of employees. #93

- **Documentation**:

- **Code Quality and Bug Fixing**:
