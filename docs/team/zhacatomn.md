---
layout: page
title: Shaun Quek's Project Portfolio Page
---

### Project: FriendlyLink

FriendlyLink is a personnel information management tool designed for Voluntary Welfare Organisations to keep elderly and volunteer records, and pair them up efficiently and effectively.

Given below are my contributions to the project.

* **New Feature**: The `auto_pair` command
    * **What it does**: Automatically pairs unpaired elderly and volunteers together
    * **Justification**: It can be daunting to manually pair up the elderly and volunteers, especially when there are many factors that need to be considered, such as their region and availabilities. 
This command helps to give the user a starting point for how the unpaired elderly and volunteers can be paired up, allowing them to overcome the initial inertia. 
    * **Highlights**: It was tricky to figure out what algorithm we should use to pair up the elderly and volunteers. Ultimately, we decided on a simple, one-to-one matching, greedy algorithm to serve as a "starting point" for the user.
* **New Feature**: `edit` command by NRIC
    * **What it does**: Allows the user to edit the fields of an elderly/volunteer by identifying them through their NRIC
    * **Justification**: Since NRICs in FriendlyLink are unique, we thought it would be good to provide users an option to identify an elderly/volunteer using their NRIC, rather than just through the index. 
    * **Highlights**: It was tricky to find a way abstract the "editing" functionalities shared between the `edit_elderly`, `edit_volunteer` and `edit` commands. Eventually, I decided upon using an `EditDescriptor` class to encapsulate this logic. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=zhacatomn&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=zhacatomn&tabRepo=AY2223S2-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed release `v1.3.2` (1 release) on GitHub

* **Enhancements to existing features**:
    * Added the `PersonBuilderScaffold` class in tests for better abstraction
    * Created the `Volunteer` class, as well as the `add_volunteer` command
    
<div style="page-break-after: always;"></div>

* **Documentation**:
    * User Guide:
        * Added documentation for the command `add_volunteer`.
        * Added images for the `add_volunteer`, `add_elderly` and `edit` commands for better illustration.
    * Developer Guide:
        * Added implementation details of the edit feature.
        * Modified the "Logic component" section under "Design" to better describe FriendlyLink's architecture. 

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#184](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/184), 
  [\#180](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/180),
  [\#148](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/148),
  [\#112](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/112)
  

