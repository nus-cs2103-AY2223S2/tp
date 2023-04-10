---
layout: page
title: Jia Ao's Project Portfolio Page
---

### Project: TrAcker

TrAcker - TrAcker is a desktop event management application for NUS CS2040 Teaching Assistants (TA). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add students into an event.
  * What it does: allows the user to add a student into an event (links a student with an event).
  * Justification: This is a core functionality of the product as our target audience (student TAs) need to add students to their events (representing the classes they taught) so that the students can be tracked on performance indicators.
  * The implementation of this feature was challenging to design as there was a surprising amount of effort that needed to go into thinking about the abstraction barrier and what kind of information should be exposed at which class/point of the code.
  * This feature was challenging to implement as many additional helper methods needed to be added into various points in the existing code base.

* **New Feature**: Added the ability to delete students from an event.
  * What it does: allows the user to delete a student from an event.
  * Justification: This is a core functionality of the product as our target audience (student TAs) need to be able to delete students from their events (representing the classes they taught) in case the student has been erroneously added or if the student is somehow not in the event anymore. 
  * This feature was challenging to implement while adhering to good code quality as it was difficult not to repeat code due to all the event types requiring similar implementations. 
  * Highlights: Reduced the amount of duplicated/similar code with polymorphism in the lower-level classes.

* **Tests**: Added tests to the classes and methods that I wrote, including new methods in existing classes.
  * Full account of test methods are in the RepoSense Link in the `Code contributed` section.
  * Tests reached a minimum of 80% code coverage on the classes I added according to CodeCov reports.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shijiaao&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)

* **Project management**:
    * Contributed code from release `v1.2` to `v1.4`.
    * Published releases and managed release deadlines for `v1.3.trial` - `v1.3` (2 releases) on GitHub. [\#136](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/136)
    * Fixed important bugs pertaining to the JAR file on the releases I published. [\#140](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/140), [\#191](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/191)
    * Converted UG and DG to PDF and submitted them for the final project submission.
  
* **Enhancements to existing features**:
    * Updated the AddressBookParser class with my own commands (`addStudent` and `deleteStudent`) and added tests for them as well.
    * Added methods for adding students to events and deleting students from events within the addressBook, Model, and existing event classes that my group members wrote.

* **Documentation**:
    * [User Guide](https://ay2223s2-cs2103-f11-1.github.io/tp/UserGuide.html#add-student-to-event):
        * Added documentation for the features `deleteStudent` and `addStudent`, excluding the addStudent screenshot. [\#167](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/167/files)
    * [Developer Guide](https://ay2223s2-cs2103-f11-1.github.io/tp/DeveloperGuide.html#add-students-to-events-feature):
        * Added implementation details of the `Add Students to Events` and `Delete Students from Events` features. [\#134](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/134/files), [\#168](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/168/files)
        * Drew activity and sequence diagrams for the aforementioned features. [\#324](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/324), [\#326](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/326), [\#327](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/327)
          * Contributed to User Stories with an equal share, focusing on user stories related to student-progress. 

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#49](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/49), [\#116](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/116)
    * Contributed to forum discussions (examples: [\#292](https://github.com/nus-cs2103-AY2223S2/forum/issues/292))
