---
layout: page
title: Jia Ao's Project Portfolio Page
---

### Project: TrAcker

TrAcker - TrAcker is a desktop event management application for NUS CS2040 Teaching Assitants (TA). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add students into an event.
  * What it does: allows the user to add a student into an event (links a student with an event).
  * * Justification: This is a core functionality of the product as our target audience (student TAs) need to add students to their events (representing their classes) so that the students can be tracked on various indicators.
  * This feature was challenging to implement as many additional helper methods needed to be added into the existing code base. There were design aspects to consider as well.

* **New Feature**: Added the ability to delete students from an event.
  * What it does: allows the user to delete a student from an event.
  * Justification: This is a core functionality of the product as our target audience (student TAs) need to be able to delete students from their events (representing their classes) in case the student has been erroneously added or if the student is somehow not in the event anymore. 
  * This feature was challenging to implement as many additional helper methods needed to be added into the existing code base. There were design aspects to consider as well.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shijiaao&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)

* **Project management**:
    * Managed releases `v1.3.trial` - `v1.3` (2 releases) on GitHub.
    * Fixed important bugs pertaining to the JAR file on release.
    * Contributed code to all releases except `v1.1`.

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `deleteStudent` and `addStudent` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `deleteStudent` and `addStudent` features.
        * Drew activity diagrams for the `deleteStudent` and `addStudent` features.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#116]()
    * Contributed to forum discussions (examples: [\#292](https://github.com/nus-cs2103-AY2223S2/forum/issues/292))
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some helper methods I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo
