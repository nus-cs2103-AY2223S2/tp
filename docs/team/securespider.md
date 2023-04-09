---
layout: page
title: Vincent Pang's Project Portfolio Page
---

[github](https://github.com/securespider)
### Project: PowerConnect

PowerConnect is a fully customized offline application for tuition and school teachers to manage students' and parents' administrative details. While it has limited features at this stage, plans for future PowerConnect releases to update features and usage capabilites have been made with detailed timeline.
The aim of this product is to eventually be a useful tool that is used in conjunction with Learning Managment System (LMS) tools currently in the market to aid teachers in managing students. <br>

PowerConnect is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). PowerConnect helps teachers in tracking and updating students' particulars.

Given below are my contributions to the project.


* **New Feature**: Added the ability to add comments for students and parents/NOKs to PowerConnect.
  * What it does: Allows user to add comments for the students and parents/NOKs created in PowerConnect via the Student Comment command. This comment is stored as a string and can be viewed by the user.
  * Justification: This feature contributes significantly to the product as it is crucial for users to be able to add comments for students and parents/NOKs and have the comments be displayed in the UI for the user to view.
  * Highlights: This implementation was the first command created by following the steps of the tutorial add command and I was not very used to AB3's codebase. This implementation also made use of defensive coding for its implementation.
  * Credits: *Tutorial add command: Remark Command*
<br><br>

* **New Feature**: Enhanced the Class class to record additional information about the class.
  * What it does: Allows user to add additional information about each class a student is in in PowerConnect.
  * Justification: Every student and parent is part of a class, but original implementation simply saves the name of the class. This feature allows the user to add additional information about the class, such as the other students in the class, the parents present and other methods that contribute to knowing about the class.
  * Highlights: This implementation was challenging to design and understand as some of the other features were going to use this class. This implementation also made use of defensive coding for its implementation.
  * Credits: *nil*
<br><br>

* **New Feature**: Added the ability to add attendance for the students created. Additionally, implemented the ability to add attendance during add, edit person that fits in with the expected behaviour.
  * What it does: Allows user to add attendance for the students created in PowerConnect via the Attendance Add Command. This attendance is stored as a date which will be automatically displayed as present or absent based on current date.
  * Justification: This feature contributes significantly to the product as it is crucial for users to be able to add attendance for students and have the attendance be added automatically by the product and not done manually by the user.
  * Highlights: The implementation was challenging as it required changes to Attendance Add Command as well as Student Add/Edit commands during its implementation to ensure the attendance of students are performed properly. There were multiple cases to consider during its implementation. This implementation also made use of defensive coding for its implementation.
  * Credits: *nil*
<br><br>

* **New Feature**: Added abstraction of Person class to Student and Parent/NOK classes
  * What it does: Store the information of students and parents/NOKs in separate classes that extends from the Person class.
  * Justification: This feature contributes significantly to the product as it is crucial for users to be able to add students and parents/NOKs and have the information of students and parents/NOKs contains separate information of each other, but are all persons.
  * Highlights: Had to create the class quickly as it is required by almost all the other features. This implementation also made use of defensive coding for its implementation.
  * Credits: *Person class in AB3*
    <br><br>

* **New Feature**: Storage for Student and Parent/NOK classes (internal feature
  * What it does: Allows user to store the information of students and parents/NOKs in the storage file. Abstraction for all attributes of Students and parents/NOKs and important information to keep about them.
  * Justification: This feature contributes significantly to the product as it is crucial for users to be able to add students and parents/NOKs and have the information of students and parents/NOKs be stored in the storage file for offline retrieval.
  * Highlights: The implementation was challenging as it required changes to the storage file to store the information of students and parents/NOKs. Additionally, the implementation of the storage was done after a lot of the features was already created, meaning I had to change a lot of the implementation of the other features which were coupled together.
  * Credits: *AB3 Storage implementation*
  <br><br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=securespider&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17) <br><br>

* **Project management**:
  * Participated in releases `v1.1` - `v1.4` (6 releases) on GitHub
  * Helped in creation of project board and milestones for the project
  * Managed issue tracking and assigning of issues to team members <br><br>

* **Enhancements to existing features**:
  * Create templates for the grade command in [\#51](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/51)
  * Fixed the student add command bug in [\#104](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/104)
  * Fixed the image implementation in [\#124](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/124)

<br><br>

* **Documentation**:
    * User Guide:
        * Improved on the table of contents and the legend [\#127](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/127)
        * Updated front page of the user guide [\#138](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/138)
        * Updated formatting issues [\#157](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/157)
    * Developer Guide:
        * Added implementation details for the student attendance command [\#109](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/109)

* **Community**:
    * Reviewed 20 PRs and provided feedback
    * Reported 14 bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2223S2-CS2103T-W09-1/tp/issues?q=is%3Aissue+securespider))
    * Reviewed 4 other IPs and provided feedback



