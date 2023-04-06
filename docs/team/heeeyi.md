---
layout: page
title: Heyi's Project Portfolio Page
---

### Project: FriendlyLink

FriendlyLink is a personnel information management tool designed for 
Voluntary Welfare Organisations to keep elderly 
and volunteer records, and pair them up efficiently and effectively.

Given below are my contributions to the project.

* **New Enhancements**: Implemented multiple personal information entities 
including NRIC, BirthDate, RiskLevel, Region and MedicalQualificationTag.
    * What it does: Allows the relative information attributes for elderly and volunteers to be stored in the system, and displayed on user interface
    * Justification: 
      * This enhancement allows the administrative staff to keep information of various aspects of elderly and volunteers, 
so that they have a comprehensive view of their situation. Some attributes also play significant roles in the storage and pairing between elderly and volunteers.
      * NRIC is the person’s IC card number; BirthDate is the person’s date of birth; 
Region is the region the person lives in Singapore; RiskLevel is unique to elderly, recording the medical risk the elderly suffers; 
and MedicalQualificationTag is unique to volunteers, recording the medical skills the volunteer masters and the degree of proficiency of each.
      * NRIC is used to uniquely identify each elderly and volunteer, and is used to find a particular person; 
Region is used to optimise the pairing process, giving priority to pairing elderly and volunteer living close-by.
    * Highlights:
      * The NRIC attribute is used to check the existence of a certain person in FriendlyLink, and query the information of a particular elderly or volunteer.
      * FriendlyLink automatically processes BirthDate to display the current age of the person on the UI interface, 
preventing the feature flaw of updating members’ age every year.
      * Given the difference of the elderly’s medical risk, from low, medium or high, the elderly’s name on the UI interface is tagged with 
green, yellow and red respectively.


* **New Feature**: Added the ability to add elderly
  * What it does: Allows the addition and storage of new elderly information in FriendlyLink
  * Justification: This is one of the fundamental features of FriendlyLink, allowing the administrative staff to keep records of elderly, 
so that later their information could be queried, or they can be paired with volunteers.
  * Highlights: This feature makes use of the elderly’s NRIC to prevent duplicate persons from being added to either elderly or volunteer database.


* **New Feature**: Added the ability to delete elderly
  * What it does: Allows existing elderly records to be deleted from FriendlyLink
  * Justification: This is one of the fundamental features of FriendlyLink, allowing the administrative staff to remove 
elderly no longer associated with the institution, while complying with the Personal Data Protection Act and ensuring their information privacy.
  * Highlights: This feature ensures that when an elderly is deleted, all of his/her related pairs in the database are automatically removed as well.

* **New Enhancement**: Abstracting the Person class
  * What it does: Making the original Person class in AB3 abstract, with both elderly and volunteer objects its subclasses
  * Justification: This effectively reduces code redundancy between elderly and volunteer classes for storing overlapping information fields, 
thereby making future updates smoother and more efficient.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=heeeyi&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=heeeyi&tabRepo=AY2223S2-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed milestone `v1.3` tag on GitHub

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add_elderly` and `delete_elderly`: [\#67](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/67/files)
        * Added documentation for the features `edit` and `Command Recommendation`: [\#148](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/148);
        * Added documentation for the `Fields` section, and updated `Command Summary`;
    * Developer Guide:
        * Explained the Model component organisations and rationale, and include diagrams to show 
how different model classes interacts: [\#131](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/131)
        * Explained design of `add` and `delete` feature of elderly and volunteer: [\#121](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/121)
        * Added user stories and use cases related to managing elderly: [\#67](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/67)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#66](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/66), 
[\#81](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/81), [\#95](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/95)
    * Contributed to forum discussions (examples: [292](https://github.com/nus-cs2103-AY2223S2/forum/issues/292))

* Extracts from developer guide:

<img src="../images/developerGuide/ModelDiagram.png" width="600" />
