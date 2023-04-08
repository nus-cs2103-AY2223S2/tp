---
layout: page
title: Lai Hui Qi's Project Portfolio Page
---

### Project: InternEase

InternEase is a **powerful and innovative desktop app designed to streamline the internship application process** for **Computer Science undergraduates**. It offers optimized **combination of a Command Line Interface (CLI) and Graphical User Interface (GUI)**. InternEase is a good choice for you who wants to concentrate on your internship preparation by helping to manage your internship applications' data.

* **Summary of Contributions**:
Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=laihuiqi&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Add the ability to plan an internship application by introducing a `task package`
  * What it does: 
    * It includes subpackages `todo` and `note`.
    * General features in `task` package : `find_task` and `list_task`.These general features have effects on both `task` and `note` during execution.
    * `Todo` allows user to plan future internship applications with company name(title), job title and application deadline, an optional field - note content is also provided.
      * Features implemented in `todo` package : `add_todo`, `clear_todo`, `delete_todo`, `edit_deadline`, `edit_content` and `list_todo`.
    * `Note` allows user to record long-lasting reminders to enhance future internship applications and interviews.
      * Features implemented in `todo` package : `add_note`, `clear_note`, `delete_note` and `list_note`.
  * Justification: This feature enhances the product significantly because it provides functional spaces for the user to record interested internship applications and take notes on their previous applications. It increases the efficiency of busy computer science students on planning their internships and preparing for their interviews.
  * Highlights: 
    * An independent structure of logic, model, storage and ui is used to implement this side features package to avoid ambiguity. This substructure integrates with the main structure of InternEase. 
    * Executions of the commands (include main features) in different interface panel is allowed to provide a more efficient use of InternEase. It will automatically switch to the desired panel according to the command type.
  * Credits: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).

**New Feature**: Clear the internship applications with specified attributes and keywords
* What it does:
  * Allow user to delete internship applications at once by `company name`, `job title` or `status` with keywords specified (`clear_by`).
* Justification: This feature improves the product by enabling more efficient cleaned-up for unwanted internship applications. 
* Highlights: The implementation of different class constructors and their respective parse functions is slightly challenging.
* Credits: Some code adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).

**New Feature**: Revert the recent deletion of internship application
* What it does:
  * Allow user to restore the most recent internship application from the CacheList.
* Justification: This feature improves the product significantly by resolving the situation when the user accidentally deletes an internship application with many important particulars.
* Highlights: A new CacheList is implemented to be the data structure to hold the deleted or cleared internship applications temporarily for the current session.

**New Feature**: Revert all deleted or cleared internship applications 
* What it does:
  * Allow user to restore all deleted or cleared internship applications from the CacheList.
* Justification: This feature enhances the revert feature of the product by resolving the situation when the user accidentally clears the entire internship application list. It increases the efficiency by providing an alternative to user rather than restoring the application entries one-by-one.
* Highlights: A new CacheList is implemented to be the data structure to hold the deleted or cleared internship applications temporarily for the current session.


* **Project management (Team-based tasks)**:
  * Added labels and modified label descriptions for issues.
  * Added product icon.
  * Update `Ui.png` according to the latest GUI image of InternEase [\#223](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/223).

* **Enhancements implemented**:
  * Enhanced `clear`, `delete` and `exit` features according to the InternEase model, added `CacheList` method to `clear` and `delete`.
  * Enhanced GUI interface for `MainWindow`, `QuickAccessToolbar`, `Result Dialog` and `task package` related GUI parts, many new components are added and the corresponding alignment settings are very challenging. [\#128](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/128), [\#162](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/162).
  * Implemented or enhance test cases for `Command class` and `Parser class` related to all assigned features stated above, `ui` related class and add `testutil` for `task package`, increased test coverage of all mentioned classes to at least 80%.[\#272](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/272).
  * Credits: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).


* **Documentation**:
  * User Guide:
    * Modified documentation for Quick Start, added table of contents and updated command summary table for all features stated below [\#47](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/47), [\#161](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/161).
    * Added documentation for the main features `clear`, `delete`, `exit`, `clear_by`, `revert`, `revert_all` and all side features in `task package` [\#47](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/47), [\#161](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/161).
    
  * Developer Guide:
    * Added use cases for the main features `clear`, `delete`, `exit`, `clear_by`, `revert`, `revert_all` and all side features in `task package`  [\#52](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/52), [\#274](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/274).
    * Added implementation details and UML diagrams for all features stated above [\#274](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/274).
    * Modified architecture documentation for the Ui component [\#274](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/274).
    
  * Updated README.md and index.md [\#59](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/59).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#145](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/145), [\#157](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/157), [\#173](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/173), [\#266](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/266).
  * Reported bugs and suggestions for other teams in PE-D: [PE-D Issues for Group W16-1](https://github.com/laihuiqi/ped/issues).

* **Tools**:
  * Java 11 and JavaFX
