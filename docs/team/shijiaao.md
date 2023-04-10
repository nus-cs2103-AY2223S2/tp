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
  * This feature was challenging to test
  * This feature was challenging to implement as many additional helper methods needed to be added into the existing code base. There were design aspects to consider as well.

* **New Feature**: Added the ability to delete students from an event.
  * What it does: allows the user to delete a student from an event.
  * Justification: This is a core functionality of the product as our target audience (student TAs) need to be able to delete students from their events (representing their classes) in case the student has been erroneously added or if the student is somehow not in the event anymore. 
  * This feature was challenging to implement as many additional helper methods needed to be added into the existing code base. There were design aspects to consider as well.

* **Tests**: Added tests to the classes and methods that I wrote, including new methods in existing classes.
  * Full account of test methods are in the RepoSense Link in the `Code contributed` section.
  * Reached a minimum of 77.78% and a maximum of 100% coverage on the classes I added.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shijiaao&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)

* **Project management**:
    * Contributed code from release `v1.2` to `v1.4`.
    * Published releases `v1.3.trial` - `v1.3` (2 releases) on GitHub. [\#136](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/136), 
    * Fixed important bugs pertaining to the JAR file on the releases I published. [\#140](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/140), [\#191](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/191)
  
* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())
    * Updated the AddressBookParser class with my own commands and added tests for them as well.

* **Documentation**:
    * [User Guide](https://ay2223s2-cs2103-f11-1.github.io/tp/UserGuide.html#add-student-to-event):
        * Added documentation for the features `deleteStudent` and `addStudent`, excluding the addStudent screenshot. [\#167](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/167/files)
    * [Developer Guide](https://ay2223s2-cs2103-f11-1.github.io/tp/DeveloperGuide.html#add-students-to-events-feature):
        * Added implementation details of the `Add Students to Events` and `Delete Students from Events` features. [\#134](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/134/files), [\#168](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/168/files)
        * Drew activity and sequence diagrams for the aforementioned features. [\#324](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/324), [\#326](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/326), [\#327](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/327)
        * Contributed to User Stories pertaining to the theme of student progress tracking. (edit)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#49](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/49), [\#116](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/116)
    * Contributed to forum discussions (examples: [\#292](https://github.com/nus-cs2103-AY2223S2/forum/issues/292))
    * Some helper methods I added was adopted by several other class mates ([1](), [2]())
