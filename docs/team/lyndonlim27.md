---
layout: page
title: Lyndon Lim's Project Portfolio Page
---

### Project: Where Got Time

Where Got Time (WGT) a perfect desktop app dedicate to managing your events and plan out your meetings with your friends and family. It is developed for university student, who can type fast to efficiently keep track of all of their events and their friends' events via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, WGT can help you find a date that all your friends are free to meet instead of having to manually compare timetables with each other.

Contributions to my project are given below. [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=CS2103T-t09-2&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=lyndonlim27&tabRepo=AY2223S2-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### New Features:
* Group create and delete
  * What it does: Allows users to create and delete a group.
  * Justification: This allows users to create a group to add person into or delete any existing groups.
  * Contributions: Set up the group related models and integrated with the group related commmands.

* Group add and remove
  * What it does: Allows users to add or remove a person from a group.
  * Justification: This feature is crucial as a user should be able to add or remove a person from a group.
  * Contributions: Set up the group related models and integrated with the group related commmands.

* Export
  * What it does: Allows users to export one specified person in the addressbook.
  * Justification: This feature is useful as a user may want to send their friends their latest details.
  * Contributions: Set up import command and integrate with the existing storage related models.

### Enhancements implemented:

* Project management
  * Managed releases of `1.3.trial` and `1.3` 

* Edit command
  * What it does: Allows the editing of groups. Has a new prefix to allow merging of new tags/groups inputted.
  * Justification: This feature is useful as users may want to add on to the existing tags/groups instead of overwriting it.
  * Contributions: Implemented the new prefix and updated the `EditCommand` and `EditCommandParser` accordingly.

* Updated UI
  * Contributions: Changed the layout, colouring scheme and fixed UI bugs

* **Contributions to the UG**:
  * Added documentation for the features `group create`, `group delete`, `group add`, `group remove`, `group list`, `group find` and `export`.
  * Added details for `Command summary`, `About document` and `Quick Start`.

* **Contributions to the DG**:
  * Updated details for non-functional requirements
  * Added details for implementation of `Group Create`, `Group Delete`, `Group Find`, `Edit` and `Export` features, including justifications, sequence and activity diagrams. 
  * Added details in manual testing for `Editing a person`, `Creating a group`, `Deleting a group`, `Finding a group`, `Export a person` and `Find free time slot`.
  * Added details in Appendix: Planned enhancements for `Group name case-sensitive`, `Data file editable` and `Event names`.

* **Contributions to team-based tasks**:
  * Set-up team's organisation and repository.
  * Set-up Codecov for team's repository.
  * Contributed to team demo video.

* **Contributions beyond the project team**:
  * Reviewed and merged PRs for team members .
