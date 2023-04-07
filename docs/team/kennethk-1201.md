layout: page
title: Kenneth Kiang's Project Portfolio
---

### Project: SudoHR

{% include ppp-summary.html %}

Given below are my contributions to the project.

* **New Feature**: `Department` class [[PR#84]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/84)
  * What it does: Represents a department in a company. It is identified by its `DepartmentName`,
  and contains a unique list of employees. I created a large bulk of the methods in the class and
  did the corresponding test cases.
  * Justification: This class is required to handle all the logic related to departments.
  * Highlights: The internal employee list within a `Department` object re-used the
  `UniquePersonList` class (which was renamed to the `UniqueEmployeeList` class), following the
  Separation of Concerns principle.
  * Credits: AddressBook - Level 3's codebase, which inspired the design for the `Department` class.

* **New Feature**: `adep`, `edep`, `aetd`, `refd` and `ldep` commands
  * What it does:
    * `adep` - Adds a department. [[PR#84]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/84)
    * `edep` - Edits a department (only `name` field for v1.4). [[PR#92]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/92)
    * `aetd` - Adds an employee to a department. [[PR#105]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/105)
    * `refd` - Removes an employee from a department. [[PR#112]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/112)
    * `ldep` - Lists all departments in SudoHR. [[PR#118]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/118)
  * Justification:
    * Handles a significant portion of the CRUD logic for departments which is essential to the application's
    value proposition.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=kennethk-1201&tabRepo=AY2223S2-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Update the `help` command for v1.4. [[PR#208]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/208)

* **Documentation**:
  * Add general command format for UG. [[PR#209]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/209)
  * Add placeholder and prefixes table for UG. [[PR#169]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/169)
  * Add documentation for department commands in UG and DG. [[PR#166]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/166) [[PR#154]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/154)
  * Update high-level architecture in DG [[PR#136]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/136)
  * Remove lingering portions of AB3 in UG and README [[PR#51]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/51) [[PR#42]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/42)
  * Set up template headers in UG [[PR#37]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/37)

* **Code Quality and Bug Fixing**:
  * Fixed bugs in the department tests [[PR#153]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/153)

* **Miscellaneous PRs**
  * Update config.yml [[PR#41]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/41)
  * Update SCSS config [[PR#36]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/36)
  * Update PPP [[PR#38]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/38)
