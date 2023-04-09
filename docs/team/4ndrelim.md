---
layout: page
title: Andre's Project Portfolio Page
---

### Project: SudoHR

{% include ppp-summary.html %}

Given below are my contributions to the project.

* **Revamped Feature**: `Employee` class [[PR#88]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/88)
  * What it does: Introduce Id field, which serves as a unique identifier, for `Employee` instances. 
  SudoHR bans adding of employees with the same Id as an existing employee in SudoHR.
  Testing was also done to ensure existing features and implementation do not break.
  * Justification: SudoHR should support tracking employees with duplicate names, but it also needs some form of
  identifier to uniquely retrieve or manipulate data fields of an employee. Id field is crucial in almost all internal
  operations that require identifying a specific employee.

* **New Feature**: `llve` and `feid` commands
  * What it does:
    * `llve` - Lists all leaves with at least 1 employee on leave on that date [[PR#152]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/152)
    * `feid` - Finds and displays the employee with the specified Id [[PR#207]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/207)
  * Justification:
    * Handles retrieving data about leaves and employees which are essential to the application's value proposition.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=4ndrelim&tabRepo=AY2223S2-CS2103T-T17-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**:
  * Enforce uniqueness of phone number and email address fields alongside heavy testing [[PR#93]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/93)
  * Enforce use of employee Id across all operations that require identifying employees. For example, the delete employee command,`del`, 
  has been refactored to use employee Id rather than current index view which limits flexibility in usage [[PR#117]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/117)
  * Cascade changes to an Employee object via `del` and `edit` commands to `UniqueEmployeeList` in `Department` and `Leave` classes. [[PR#135]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/135)
    * This is necessary because `Department` and `Leave` objects have their own `UniqueEmployeeList`, following the Separation of Concerns principle, and
    the representation of an employee instance should be consistent across SudoHR 
  * Implement methods to get size of departments and number of people on leave on a given date as abstraction at the Model-level to be used by the UI [[PR#167]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/167)
  * Implement a more comprehensive equivalent of `toString()` method of Employee class to display all fields [[PR#200]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/200)
  * Enforce 8-digit requirement for phone number field [[PR#204]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/204)

* **Documentation**:
  * Add design considerations for `Employee` class in DG [[PR#146]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/146)
  * Add documentation for `Employee` fields and commands in UG [[PR#177]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/177)
  * Add documentation for `Employee` fields, commands, and design consideration in DG [[PR#295]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/295)

* **Code Quality and Bug Fixing**:
  * Fixed issue where changes cascaded to `Leave` and `Department` classes were not reflected in UI [[PR#205]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/205)
  * Fixed PE-D testers bug reports [[PR#285]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/285) 

* **Miscellaneous PRs / Team-based Contribution**
  * Refactoring of code and documentation to remove references to `Person` and use `Employee` class instead. [[PR#106]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/106)
  * One of the main PR reviewers. Helped to review > 1/3 of the team's PRs.
  * Comprehensive test cases in several PRs as per the Equivalence Partitioning and Boundary Value Analysis principles. For example, see [[PR#93]](https://github.com/AY2223S2-CS2103T-T17-2/tp/pull/93)
