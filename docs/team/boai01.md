---
layout: page
title: Ai Bo's Project Portfolio Page
---

### Project: Wingman

Wingman - Wingman is an application designed to help airline managers efficiently manage the allocation of their
resources.

Given below are my contributions to the project.

* **Feature 1**: Location mode-related commands, including `add`, `delete`, `list` `linkflight`.
  * What it does: allows the user to add, delete, and display current locations in the database. It also allows the 
  user to specify the start and destination locations of a flight. 
  * Justification: This is a must-have feature, because location is an important flight-related resource.
  * Highlights: We need to constrain each flight to only have two locations, i.e., the departure and arrival locations. 

* **Feature 2**: Link/Unlink `crew`, `pilot`, and `plane` to locations.
  * What it does: allows the users to specify the locations that flight-related resources might reside. 
  * Justification: This information is important, as flight managers must ensure the resources are in-place for a flight.
  * Highlights: We need to ensure such information is stored on disk properly, but it is not straightforward to 
  store object attributes. 
  * Credits: to Xiuxuan who implemented the `Link` class that enables easy storing and reading.

* **Feature 3**: Use index to specify objects instead of UUID for all command classes.
  * What it does: instead of asking users to specify objects with UUID, which is a long string, this allows them to 
  refer to an object with index in the list, e.g., `delete 2`. 
  * Justification: This significantly saves users' effort on typing the long UUID string, improving the 
  user-friendliness of the product.
  * Highlights: This is not hard to implement, but involves some labor.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=boai01&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=BoAi01&tabRepo=AY2223S2-CS2103T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Monitor and examine issues with existing products, e.g., issues 
  [#117](https://github.com/AY2223S2-CS2103T-W11-1/tp/issues/117), 
  [#113](https://github.com/AY2223S2-CS2103T-W11-1/tp/issues/113),
  [#110](https://github.com/AY2223S2-CS2103T-W11-1/tp/issues/110), 
  [#109](https://github.com/AY2223S2-CS2103T-W11-1/tp/issues/109),

* **Enhancements to existing features**:
  * Refactor, to rename `Identifiable` to `Item` and rename all related classes for naming consistency.
  * Remove magic literals in the program. Improve error message by using `String.format`.
  * Change zero-based indexing to one-based indexing.

* **Documentation**:
  * User Guide: 
    * Did cosmetic tweaks to existing QnA and intro.
  * Developer Guide:
    * Update user stories, use cases, NFR, glossary in the Developer Guide for location class
    * Add implementation details of the `add` command.

* **Community**: 
  * PRs reviewed: 15 in total, e.g., [\#26](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/26), 
  [\#39](https://github.com/AY2223S2-CS2103T-W11-1/tp/pull/39)
  * Contributed to forum discussions: 42 posts in total, ranked 3rd on 
  [Forum Dashboard](https://nus-cs2103-ay2223s2.github.io/dashboards/contents/forum-activities.html).
  * Reported bugs and suggestions for other teams in the class: [Mock PE](https://github.com/BoAi01/ped/issues).





