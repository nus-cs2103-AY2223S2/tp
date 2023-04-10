---
layout: page
title: Jamie's Project Portfolio Page
---

## **coNtactUS**   

#### Overview

CoNtactUS is a module tracker designed specifically for NUS computing students.
It is optimised for use via typing rather than clicking which proves to be faster, allowing NUS computing students
to study more efficiently and effectively.
coNtactUS is developed by a team of NUS computing students that understand the struggles faced,
making this module tracker simple and easy for use.


***

### **Contributions**

coNtactUS is written in Java. Given below are my contributions to the project.  

#### Features and Enhancements

* **Modify Feature**: Allow te edit command to work on new attributes.
[#35](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/35)
  * What it does: allows the user to edit all the fields.
  * Justification: This feature improves the module tracker significantly because a user can make mistakes while 
  entering their input and the app should provide a convenient way to rectify them.
  User should also be able to add information to an existing module and the app
  should have a command allowing additional details to be added.  
<br>  
* **Feature Enhancement**: Modify Deadline and TimeSlot attributes to store LocalDateTime rather than a String.
[#53](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/53) [#56](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/56)
  * What it does: allows comparison of deadlines and timeslots among modules.
  * Justification: This features enhances the module tracker because a user should be able to sort and view the
  modules in chronological order. Hence, the app should provide a way for that,
  making it necessary for deadlines and timeslots to be comparable.
  * Highlights: This enhancement affects existing commands, specifically the command of adding and editing
  modules. It required an in-depth analysis of how the LocalDateTime will affect the user input and how the commands
  are being parsed. The implementation was challenging as it required changes to existing commands.  
<br>  
* **Feature Enhancement**: Improve help command. [#69](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/69)
  * What it does: shows user how to make use of the features available in the module tracker.  
  * Justification: A user might not be able to remember every single command available in the module tracker and 
  the app should provide a convenient way to see command usages for the user to make good use of
  the module tracker. It also provides a link to the User Guide with a ```Copy URL``` button
  that allows users to easily access our User Guide for more indepth information about the module tracker.
  

#### Additional Code Contributions  

* Fixing of bugs in the development of the application [#148](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/148)
* Fixing checkstyle errors [#80](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/80)
* Fixing of automated testings [#79](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/79)  
* Refactoring of names for consistency [#46](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/46)  


#### Code Contribution

My code contributions can be accessed via: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jamieeeleow&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=jamieeeleow&tabRepo=AY2223S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

#### Project management
  * Contributed to the releases `v1.3 trial` - `v1.4` (3 releases) on GitHub.  
  * Managed the release of `v1.3 trial`.  

<div style="page-break-after: always;"></div>  

#### Documentation

* **User Guide**
  * Contributed to glossary and other details in the user guide.  
  * Wrote the user guide based on HTML code.
  * Uploaded images to be used in the user guide as well as aligned and labeled them.
  * Format the user guide for a neater document upon conversion to PDF version.  
<br>  
* **Developer Guide**
  * Contributed to the user stories in the guide.
  * Contributed to the manual testing instructions provided in the guide.
  * Ensured consistency for terms used throughout the guide.  
  * Improved minor details such as consistency in numberings throughout the guide.

#### Contribution to team-based tasks

My contribution to team-based tasks include but is not limited to the following:
* Creating of issues and milestones
* Managing and wrapping up milestones
* Ensuring deadlines are met
* Creating and managing the releases
* Creating agenda and tasks for weekly meeting
* Closing of issues
* Tagging and labelling of issues with priority and type
* Creating the product demos for v1.2 and v1.3

#### Review/Mentoring Contributions

My contribution towards my team members include but is not limited to the following:
* Peer-reviewed the pull requests created
* Assisting team members in difficulties they faced while developing the application

#### Community

My contribution beyond my project team includes but is not limited to the following:
  * Contributed to other team by reporting bugs in their code and suggesting improvements
  * Participated in software testing of other team products beyond my class from `v1.2` - `v1.4`
  
