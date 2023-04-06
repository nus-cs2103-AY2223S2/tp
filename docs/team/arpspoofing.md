---
layout: page
title: Reyaaz's Project Portfolio Page
---

### Project: TrAcker

TrAcker - TrAcker is a desktop event management application for NUS CS2040 Teaching Assitants (TA).
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to Create, Read, Update and Delete Events that do not clash.
    * What it does: 
      * Allows the CS2040 TA to create tutorial events, lab events, and consultation events.
      * Allows the CS2040 TA to delete tutorial events, lab events, and consultation events.
      * Allows the CS2040 TA to edit tutorial events, lab events, and consultation events.
    * Justification: This feature allows the CS2040 TA to schedule events for student attendance taking, which is the main purpose of TrAcker.
    * Highlights: This enhancement allows the TA to add students to an event as well. So, it was difficult to ensure that the dependencies between the two features worked seamlessly.

* **New Feature**: Changed the basic list view GUI to a tableview for the student / person details.
  * What it does: Allows the CS2040 TA to better see the required details of the student without having to look through the entire list.
  * Justification: This feature allows the CS2040 TA to easily check for certain particulars, such as nus email, when the TA wants to relay information via nus email.
  * Highlights:  This enhancement affects the display of performance indicator and sort functionality since the tableview columns are used for both the features as well.

* **New Feature**: Implemented the display of student profiles that can be seen in the tableview, as well as in the event cards when students are added to the events.
  * What it does: Allows the TA to recognise students faces during tutorials, labs or consultation. This way, the TA might remember the student's name easily.
  * Justification: This feature is done to simulate the list of student photos TA or teachers often take with them to recognise student's faces with their names.
  * Highlights:  This enhancement makes addStudent to events and deleteStudent from events dependent on it.

* **New Feature**: Implemented Tabs for separate display of students and events.
  * What it does: Allows the CS2040 TA better visualise what to see without too much clutter.
  * Justification: This feature allows the TA to easily view details of students, or details of events in different pages without having to see everything in a single page. This way, there will be much less clutter in the screen.
  * Highlights: This enhancement affects the TA's ability to navigate between tabs using commands.

* **New Feature**: Implemented the feature that does not allow the clash between event dates
  * What it does:
    * Informs the TA that an event cannot be created if the TA has already created an event during that same period.
    * Tutorials are fixed to be 1 hour long, Labs are fixed to be 2 hours long, and Consultation is fixed to be 1 hour long to adhere to CS2040 timetable.
  * Justification: This feature allows the TA to not mistakenly schedule a conflicting event.
  * Highlights: This enhancement further caters TrAcker towards its feature design.

* **Code contributed**: [RepoSense link](https://github.com/ARPspoofing/tp)

* **Project management**:
    * Managed releases `v1.3 Trial` - `v1.3.1` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
