---
layout: page
title: Francis' Project Portfolio Page
---

### Project: Vaccination Management System

An increasing number of vaccinations now have more complicated prerequisites to take them, and Vaccination Management System (VMS) aims to make this validation easier. **VMS is a desktop application for validating and keeping track of vaccination appointments**; its operations are **optimized for fast typists who prefer using a Command Line Interface (CLI)** while maintaining the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project

* **New Feature**: Refactor person code into patient and updated its attributes
  * What it does: Patients now have Blood Type, Date of Birth, Allergies, and Vaccinations. New and attributes important to a patient at a vaccination center. Removed address and email fields.
  * Justification: 
  * Highlights: Our product needs to be used by a vaccine center, this are the attributes that would be more important for one to keep track of.
  
* **Enhancement to existing features**: Modified `findCommand` to allow specific searches based on attributes.
  * What it does: Allows users to search using the flags that are similar to the ones used for `addCommand`
  * Justification: This will allow the user to easily identify the patients that have specific allergy or vaccine to easily identify if they would have any problems with the vaccination.
  * Highlights: This enchancement still retain the older find command feature of searching using name if the flags are not present. Does not impact users that were once used to the previous command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=francisyzy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=francisyzy&tabRepo=AY2223S2-CS2103-F11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed group coordination and communication on Telegram.
  * Managed the allocation, and tagging for PE-D.
  * Started the use of Github [project board](https://github.com/orgs/AY2223S2-CS2103-F11-3/projects/1/views/1) for user stories.
  * Managed reviewing some PRs.
  * Set up Github team org/repo for F11-3.
  * Managed team meetings by updating meeting notes on Google Drive, and organising the Zoom link.

* **Documentation**:
  * User Guide:
    * Added documentation for the `patient` features and its attributes.
    * Did cosmetic tweaks to documentation made by others to standardise feature order.
  * Developer Guide:
    * Added implementation details for the `patient` features.
    * Updated architecture components. [\#145](https://github.com/AY2223S2-CS2103-F11-3/tp/issues/145)
  * Demo:
    * Added [v1.2 Demo](https://drive.google.com/drive/folders/18t3j1zrud4M8GuwkIIH2xhgmB5X2Ue-z).
    * Added [v1.3 Demo](https://drive.google.com/drive/folders/19Fovaw6w0l5GH4gBA6gQPlqvdpoVHfXS).

* **Community**:
  * Ip PRs reviewed (with non-trivial review comments): [\#145](https://github.com/nus-cs2103-AY2223S2/ip/pull/145), [\#253](https://github.com/nus-cs2103-AY2223S2/ip/pull/253)
  * Tp PRs reviewed (with non-trivial review comments): [\#49](https://github.com/AY2223S2-CS2103-F11-3/tp/pull/49), [\#204](https://github.com/AY2223S2-CS2103-F11-3/tp/pull/204), [\#211](https://github.com/AY2223S2-CS2103-F11-3/tp/pull/211), [\#260](https://github.com/AY2223S2-CS2103-F11-3/tp/pull/260), [\#344](https://github.com/AY2223S2-CS2103-F11-3/tp/pull/344).
  * Contributed to [forum discussions](https://github.com/nus-cs2103-AY2223S2/forum/issues?q=francisyzy). Details also @ [dashboard](https://nus-cs2103-ay2223s2.github.io/dashboards/contents/forum-activities.html#4-fran-yik-francisyzy-41-posts).
  * Reported 8 bugs and suggestions for other teams in the class during the PE-D. The reports can be found in [Team's Github Issue(s)](https://github.com/AY2223S2-CS2103-F10-2/tp/issues?q=francisyzy)

* **Tools**:
  * Updated the CI to not run on draft PRs. This is to improve group awareness of member's activities (lets people push non working code to a PR without it failing and notifying). ([\#205](https://github.com/AY2223S2-CS2103-F11-3/tp/pull/205))

