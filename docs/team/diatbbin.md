---
layout: page
title: Lim Diat Bbin's Project Portfolio Page
---

### Project: PowerConnect

PowerConnect is a fully customized offline application for tuition and school teachers to manage students' and parents' administrative details. While it has limited features at this stage, plans for future PowerConnect releases to update features and usage capabilites have been made with detailed timeline.
The aim of this product is to eventually be a useful tool that is used in conjunction with Learning Managment System (LMS) tools currently in the market to aid teachers in managing students. <br>

PowerConnect is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). PowerConnect helps teachers in tracking and updating students' particulars.

Given below are my contributions to the project.

* **New Feature**: Added the ability to delete students from PowerConnect.
  * What it does: Allows users to delete students from PowerConnect via the Student Delete Command.
  * Justification: This feature contributes significantly to the product as one of the intended features in allowing teachers to delete students from their student list.
  * Highlights: The implementation was slightly challenging as it required changes to the ab3's deleteCommand to allow deletion of student by their class and index number instead of their index in the list.
  * * Credits: *AB3's delete Command*
    <br><br>

* **New Feature**: Added the ability to delete parents from PowerConnect.
  * What it does: Allows users to delete parents from PowerConnect via the Parent Delete Command.
  * Justification: This feature contributes significantly to the product as one of the intended features in allowing teachers to delete parents from their parent list.
  * Highlights: The implementation was slightly challenging as it required changes to the ab3's deleteCommand to allow deletion of parent by their name and phone number instead of their index in the list. For parent delete, 
  * * Credits: *AB3's delete Command*
      <br><br>

* **New Feature**: Added the ability to edit students from PowerConnect.
  * What it does: Allows users to edit students from PowerConnect via the Student Edit Command.
  * Justification: This feature contributes significantly to the product as one of the intended features in allowing teachers to edit students from their students list.
    * Highlights: The implementation was challenging as the student class has many prefixes that can be edited. If the parent name or phone number is edited with studentEditCommand, that parent/NOK and all its children has to be updated too.
  * * Credits: *AB3's edit Command*
      <br><br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=T09-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Diatbbin&tabRepo=AY2223S2-CS2103T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Participated in releases `v1.1` - `v1.4` (6 releases) on GitHub <br>
  * Helped to check documentation <br><br>

* **Enhancements to existing features**:
    * Wrote additional tests for parent delete to increase coverage from 20.09% to 24.13% [\#213](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/213) 
    * Improved implementation of ParentCommandParser [\#245](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/245)  <br><br>


* **Documentation**:
    * User Guide:
        * Added documentation for the features `student delete`, `student edit` and `parent delete` [\#204](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/204),  [\#233](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/233)
        * Check and amended errors in User Guide [\#144](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/144), [\#204](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/204)
    * Developer Guide:
      * Added implementation details of the `student/parent delete` feature. [\#233](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/233) <br><br>
      
* **Review Contributions during team project**:
  * PRs reviewed and merged: [\#96](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/96), [\#112](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/112), [\#215](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/215) 
  * Helped my teammate change command output message [\#230](https://github.com/AY2223S2-CS2103T-T09-1/tp/pull/230) <br><br>
  
* **Contributions beyond the project team**:
  * Reported 13 bugs for PED [[CS2103T-W12-2]](https://github.com/Diatbbin/ped/issues) <br><br>
