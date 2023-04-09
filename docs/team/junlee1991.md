---
layout: page
title: Julio's Project Portfolio Page
---
# Project Portfolio Page for Julio

### Project: AutoM8

AutoM8 is a **desktop app for an auto repair shop, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AutoM8 can get your auto repair shop management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: Allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature enhances the application significantly because a user might make mistakes in what command he or she keys in. It should not be needed by the user to do different commands to rectify each mistake. Hence, this feature provides a convenient way to rectify those errors.
    * Consideration: This feature required numerous design and usability considerations due to how familiar most users are with this feature. In addition, the implementation as well required an in-depth analysis of design alternatives to determine the most optimal solution for this project.
    * Credits: [zhixianggg](https://github.com/zhixianggg) - I reused and adapted some of his code to implement the UndoRedoStack.
               [bakano98](https://github.com/bakano98) - I reused and adapted some of his code to implement the undo redo test.

* **New Feature**: Added the totalappointment command.
  * What it does: Finds the number of appointments on a specified date.
  * Justification: This feature enhances the application significantly because users can keep track of the number of appointments they have on any single date.
  * Consideration: This feature required in depth understanding of localDatesTime API to determine the most optimal solution for this project.
  * Credits: [stackoverflow](https://stackoverflow.com/questions/494180/how-do-i-check-if-a-date-is-within-a-certain-range) - I reused and adapted the code to check if a date is within a certain range.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=junlee&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17ional-code~test-code~other&since=2022-02-18)

**Project Management**:
* Set up [team repo](https://github.com/AY2223S2-CS2103-W17-4/tp) on github.
* Helped manage weekly milestones (ensure closing of issues, and milestone wrap-ups).

* **Enhancements to existing features**:
    * Fixed Bugs relating to undo and redo feature (Pull requests [\#91](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/91))
    * Added undo command test (Pull requests [\#139](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/139))
    * Added redo command test (Pull requests [\#141](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/141))


* **Documentation**:
    * User Guide:
        * Added documentation for the features `undo`, `redo` and `totalappointment` [\#117](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/117)

    * Developer Guide:
        * Added implementation details of the `undo` and `redo` feature [(PR #52)](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/52).
        * Added UML diagrams of the `undo` and `redo` feature [(PR #85)](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/85)
        * Added implementation details of the `totalappointment` feature [(PR #102)](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/102).
        * Added Product Scope, User Stories, Glossary and NFRs [(PR #34)](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/34)
        * Added UML diagram of the `totalAppointment` feature [(PR #159)](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/159)

- **Bugfixes**
   * [\#144](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/144), [\#147](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/147)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#88](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/88), [\#73](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/73)
    * Reported bugs and suggestions for other teams in the class (examples: [\#90](https://github.com/AY2223S2-CS2103-W17-4/tp/issues/90), [\#86](https://github.com/AY2223S2-CS2103-W17-4/tp/issues/86)




