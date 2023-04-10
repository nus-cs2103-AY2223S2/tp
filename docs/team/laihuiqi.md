---
layout: page
title: Lai Hui Qi's Project Portfolio Page
---
### Project: InternEase

InternEase is a **powerful and innovative desktop app designed to streamline the internship application process** for **Computer Science undergraduates**. It is a good choice for you to concentrate on your internship preparation as it helps to manage your internship applications' data.

Given below are my contributions to the project. <br/>
**Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=laihuiqi&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) <br/>
**Credits**: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).

**New Feature**: Adds the ability to plan an internship application by introducing a `task package` with subpackages `todo` and `note`.
* Allows user to plan future internship applications and record long-lasting reminders to enhance future internship applications and interviews.
* `task` package : `find_task` and `list_task`. 
* `todo` package : `add_todo`, `clear_todo`, `delete_todo`, `edit_deadline`, `edit_content` and `list_todo`.
* `note` package : `add_note`, `clear_note`, `delete_note` and `list_note`.
* Justification: Provides functional spaces for planning internships and preparing interviews.
* Highlights: An independent structure of logic, model, storage and ui is implemented for this side features package to avoid ambiguity.
* **Credits**: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).
  
**New Feature**: Clears the internship applications with specified attributes and keywords
* Allows user to delete internship applications at once by `company name`, `job title` or `status` with keywords specified (`clear_by`).
* Justification: Enable more efficient cleaned-up for unwanted internship applications. 
* Highlights: The implementation of different class constructors and their respective parse functions is slightly challenging.
* **Credits**: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).

**New Feature**: Reverts the recent deletion of internship application
* Allows user to restore the most recent internship application from the CacheList.
* Justification: Resolves the situation when the user accidentally deletes an internship application with many important particulars.
* Highlights: CacheList is implemented to be the data structure to hold the deleted or cleared internship applications temporarily. 
* **Credits**: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).

**Project management (Team-based tasks)**:
* Added labels and modified label descriptions for issues, updated README.md and index.md. [\#59](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/59)
* Modified documentation for Quick Start and added table of contents for User Guide. [\#47](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/47), [\#161](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/161)

**Enhancements implemented**:
* Enhanced `clear`, `delete` and `exit` features according to the InternEase model.
* Enhanced GUI interface for `MainWindow`, `Result Dialog` and `task package` related GUI parts. [\#128](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/128), [\#162](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/162)
* Enhanced test cases for assigned features, increased related class test coverage to at least 80%.[\#272](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/272)
* **Credits**: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).
  
**Documentation**:
* User Guide:
  * Updated documentation for main features and side features stated above [\#47](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/47), [\#161](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/161)
* Developer Guide:
  * Added use cases, implementation details and UML diagrams for all features stated above and `Ui component`. [\#52](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/52), [\#274](https://github.com/AY2223S2-CS2103T-W15-4/tp/issues/274)
* **Credits**: Some code reused and adapted from [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).

**Community**:
* PRs reviewed (with non-trivial review comments): [\#145](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/145), [\#157](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/157), [\#173](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/173), [\#266](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/266)
* Reported bugs and suggestions for other teams in PE-D: [PE-D Group W16-1](https://github.com/laihuiqi/ped/issues)
