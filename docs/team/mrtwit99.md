---
layout: page
title: Yeo Wen Jue's Project Portfolio Page
---

### Project: PowerConnect

PowerConnect is a fully customized offline application for tuition and school teachers to manage students' and parents' administrative details. While it has limited features at this stage, plans for future PowerConnect releases to update features and usage capabilites have been made with detailed timeline.
The aim of this product is to eventually be a useful tool that is used in conjunction with Learning Managment System (LMS) tools currently in the market to aid teachers in managing students. <br>

PowerConnect is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). PowerConnect helps teachers in tracking and updating students' particulars.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add new parents/NOKs (Next-of-Kins) to PowerConnect.
  * What it does: Allows users to add new parents/NOKs to PowerConnect via the Parent Add Command.
  * Justification: This feature contributes significantly to the product as one of the intended features is allowing teachers to have the ability to access information on students' parent/NOK so that they are able to contact them in case of emergencies.
  * Highlights: The implementation was slightly challenging as it required changes to the Student Add command to allow parsing in of Parent/NOK information and perform its tasks based on the Binding of Student and Parent/NOK internal feature. This command is also implemented at the same time with the Binding of Student and Parent/NOK internal feature.
  * Credits: *AB3's Add Command, Student Add Command*
  <br><br>
* **New Feature**: Added the ability to edit new parents/NOKs information
  * What it does: Allows user to amend existing personal particulars of parents/NOKs in PowerConnect via the Parent Edit Command. It removes the need for users to remove a parent/NOK and re-adding the parent/NOK themselves whenever they want to amend a parent/NOK info.
  * Justification: This features contributes slightly to the product as providing users with the ability to edit parents/NOKs information is something that is essential and not a requirement.
  * Highlights: This enhancement removes the need for users to manually deleting and re-adding the parent/NOK whenever an amendment must be made.
  * Credits: *nil*
  <br><br>
* **New Feature**: Binding of Student and Parent/NOK (internal feature)
  * What it does: Binds/Unbinds a student to/from an existing parent/NOK in PowerConnect during Student Add, Edit and Delete Commands. If parent/NOK does not exist when needed for binding, it will get automatically created!
  * Justification: This feature contributes significantly to the product as it is crucial for users to be able to see the relationship of parents/NOKs to students and students to parents/NOKs and have the binding of parent/NOK and student be done automatically by the product and not done manually by the user.
  * Highlights: The implementation was challenging as it required changes to Student Add/Edit/Delete commands as well as Parent Add/Edit commands during its implementation to ensure the binding of relationship of students and parents/NOKs are performed properly. There were multiple cases to consider during its implementation. This implementation also made use of defensive coding for its implementation.
  * Credits: *nil*
  <br><br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=AY2223S2-CS2103T-T09-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=MrTwit99&zR=AY2223S2-CS2103T-T09-1%2Ftp%5Bmaster%5D&zACS=269.13953488372096&zS=2023-02-17&zFS=AY2223S2-CS2103T-T09-1&zU=2023-04-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false) <br><br>

* **Project management**:
  * Participated in releases `v1.1` - `v1.4` (6 releases) on GitHub <br>
  * Participated in the review of Bug Reports sent by testers in PE-D phase <br>
  * Helped in resolving some of the issues from Bug Reports <br><br>

* **Enhancements to existing features**:
  * Improved implementation of Student Edit command (Pull request [\#150]())
  * Provided parser for Parent commands by creating ParentCommandParser during the creation of Parent Add Command
  * Updated logging messages to PowerConnect's logging messages (Pull request [\#147]())
  * Provided binding of Student and Parent/NOK internal feature for Student and Parent/NOK <br><br>

* **Documentation**:
  * User Guide:
    * Added documentation for the features `parent add` and `parent edit`
    * Added documentation for `Prefix summary` [\#158]()
    * Added hyperlinks to abbreviations
    * Added `Glossary`
    * Added jump links such as `Back To Table of Contents`
    * Amended formatting of User Guide for PDF versions [\#141]() [\#142]() [\#153]() [\#154]()
    * Amended errors in User Guide [\#129]() [\#155]() [\#158]() <br><br>
  * Developer Guide:
    * Added implementation details for `parent add`,`parent edit` and `binding of student and parent/NOK` features.
    * Added UML diagrams for `parent add` and `parent edit` features.
    * Added Use Cases: UC07, UC08, UC09.
    * Added Table of Content section with working jump links.
    * Added Planned Enhancements into Developer Guide.
    * Amended Saving Data section in Developer Guide.
    * Amended formatting of Developer Guide for PDF versions
    * Transferred Glossary, Use Case and User Stories from Google Docs to Developer Guide. <br><br>

* **Review Contributions during team project**:
  * PRs reviewed and merged: 34<br><br>

* **Contributions beyond the project team**:
  * iP PRs reviewed (with non-trivial review comments):
    * [[Cyrus-Krispin] iP #217](https://github.com/nus-cs2103-AY2223S2/ip/pull/217)
    * [[gohyongjing] iP #69](https://github.com/nus-cs2103-AY2223S2/ip/pull/69)
    * [[Anunaya Joshi] iP #79](https://github.com/nus-cs2103-AY2223S2/ip/pull/79)
    * [[huixuant] iP #82](https://github.com/nus-cs2103-AY2223S2/ip/pull/82)
    * [[WillCWX] iP #49](https://github.com/nus-cs2103-AY2223S2/ip/pull/49) <br><br>

  * tP PRs reviewed (with non-trivial review comments):
    * [[CS2103-T15-3] Vimification #151](https://github.com/nus-cs2103-AY2223S2/tp/pull/151) <br><br>

