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
      * Allows the CS2040 TA to create tutorial events (`Tutorial`, `AddTutorialCommand`, `AddTutorialParser`), lab events (`Lab`, `AddLabCommand`, `AddLabParser`), and consultation events (`Consultation`, `AddConsultationCommand`, `AddConsultationParser`).
      * Allows the CS2040 TA to delete tutorial events, lab events, and consultation events.
      * Allows the CS2040 TA to edit tutorial events, lab events, and consultation events.
    * Justification: This feature allows the CS2040 TA to schedule events for student attendance taking, which is the main purpose of TrAcker.
    * Highlights: This enhancement allows the TA to add students to an event as well. So, it was difficult to ensure that the dependencies between the two features worked seamlessly.

* **New Feature**: Changed the basic list view GUI to a tableview for the student / person details.
  * What it does: Allows the CS2040 TA to better see the required details of the student without having to look through the entire list.
  * Justification: This feature allows the CS2040 TA to easily check for certain particulars, such as nus email, when the TA wants to relay information via nus email.
  * Highlights:  This enhancement affects the display of performance indicator and sort functionality since the tableview columns are used for both the features as well.

* **New Feature**: Implemented the display of student profiles (`Photo`) that can be seen in the tableview, as well as in the event cards when students are added to the events.
  * What it does: Allows the TA to recognise students faces during tutorials, labs or consultation. This way, the TA might remember the student's name easily.
  * Justification: This feature is done to simulate the list of student photos TA or teachers often take with them to recognise student's faces with their names.
  * Highlights:  This enhancement makes addStudent to events and deleteStudent from events dependent on it.

* **New Feature**: Implemented Tabs for separate display of students and events and new gui for events in `EventListPanel`.
  * What it does: Allows the CS2040 TA better visualise what to see without too much clutter.
  * Justification: This feature allows the TA to easily view details of students, or details of events in different pages without having to see everything in a single page. This way, there will be much less clutter in the screen.
  * Highlights: This enhancement affects the TA's ability to navigate between tabs using commands.

* **New Feature**: Implemented the feature that does not allow the clash between event dates.
  * What it does:
    * Informs the TA that an event cannot be created if the TA has already created an event during that same period.
    * Tutorials are fixed to be 1 hour long, Labs are fixed to be 2 hours long, and Consultation is fixed to be 1 hour long to adhere to CS2040 timetable.
  * Justification: This feature allows the TA to not mistakenly schedule a conflicting event.
  * Highlights: This enhancement further caters TrAcker towards its feature design.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=arp&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=ARPspoofing&tabRepo=AY2223S2-CS2103-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).

* **Project management**:
    * Managed releases `v1.3 Trial`, `v1.3`, `v1.3.1`, `v1.4` (all 4 releases) on GitHub.

* **Enhancements to existing features**:
    * Changed the GUI from list view to table view (Pull requests [\#59](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/59), [\#61](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/61), [\#67](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/67), [\#70](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/70)).
    * Ensured the color and display of the tableview suits the theme (Pull requests  [\#67](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/67).
    * Improved gui of search bar, and TrAcker logo.
    * Improved gui with tabs navigation.
    * Improved gui with icons in events tab.
    * Improved validate checkers for email, phone (telegram), and name.
 
* **Documentation**:
    * User Guide:
        * Added documentation for the features `touch`, `vim`, `mkdir`, `editEvent`, `rm`, `delete`.
        * Ensured documentation has enough visuals.
        * Ensured documentation has a command summary.
        * Added table of contents to documentation.
        * Ensured examples are correct.
        * Ensured formatting of different command sections are consistent.
        * Added icons such as warning, information, and bulb to the documentation to improve readability.
        * Did the skeleton for all parts and added table of content.
        * Added visualisations for commands.
    * Developer Guide:
        * Added implementation details of the `touch`,`vim`, `mkdir`, `editEvent`, `rm`, `delete` features.
        * Formatted activity diagrams for all parts.
        * Formatted User Stories for all parts.
        * Formatted Use Cases for all parts.
        * Ensured all diagrams are correct.
        * Added Planned Enhancements for `Photo` and addressbook.json data.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#36](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/36#discussion_r1130206910), [\#51](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/51#discussion_r1133201310), [\#66](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/66#discussion_r1135239043), [\#69](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/69#discussion_r1136402309).
    * Authored [113 Pull Requests](https://github.com/pulls?page=5&q=is%3Apr+author%3AARPspoofing+archived%3Afalse+user%3AAY2223S2-CS2103-F11-1+is%3Aclosed) with mostly non-trivial changes.
    * Authored [41 Issues](https://github.com/AY2223S2-CS2103-F11-1/tp/issues?q=is%3Aissue+author%3AARPspoofing+is%3Aclosed) to keep track of changes with the team.
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S2/forum/issues/31#issuecomment-1398077018), [2](https://github.com/nus-cs2103-AY2223S2/forum/issues/41#issuecomment-1399662570),
  [3](https://github.com/nus-cs2103-AY2223S2/forum/issues/67#issuecomment-1406302593), [4](https://github.com/nus-cs2103-AY2223S2/forum/issues/95#issuecomment-1408418577),
  [5](https://github.com/nus-cs2103-AY2223S2/forum/issues/114#issuecomment-1411232549), [6](https://github.com/nus-cs2103-AY2223S2/forum/issues/104#issuecomment-1417208852),
  [7](https://github.com/nus-cs2103-AY2223S2/forum/issues/146#issuecomment-1421676487), [8](https://github.com/nus-cs2103-AY2223S2/forum/issues/154#issuecomment-1425018257),
  [9](https://github.com/nus-cs2103-AY2223S2/forum/issues/146#issuecomment-1425019736), [10](https://github.com/nus-cs2103-AY2223S2/forum/issues/231#issuecomment-1449100936)).

* **Tools**:
    * JavaFX TableView.
    * JavaFX ImageView.
    * JavaFX ListView.
