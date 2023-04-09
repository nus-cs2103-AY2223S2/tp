---
layout: page
title: Jun Da's Project Portfolio Page
---

### Project: FitBook

FitBook is a desktop tracking book application used for tracking the progress and information
for the clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added attributes and classes (eg, *Calorie*, *Appointment*(first version) and *Routine*) to client and enhanced **Add** and **Edit** Command.
  * What it does: allows the user to add and edit the recommended calorie intake, appointment and routine of the client. (Optional attribute)
  * Justification: This feature improves the product significantly because a user may want to keep track of the appointment dates and time,
    recommended calorie's intake of its clients and routine which makes it easier for the user to keep track.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  
* **New Feature**: Added *Routine* and *Exercise* storage and model to client.
  * What it does: allows the user to make changes to Routine and exercises, and it will be stored in the exerciseroutine.json file and reflect it in the current model for FitBookExerciseRoutine.
  * Justification: This feature improves the product significantly because user gets to save their data into the storage system for future uses.
  * Highlights: This enhancement affects existing storage to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required to add new commands and updated storage and model.

* **New Feature**: Added *WeightHistory* storage to client.
  * What it does: allows the user to make changes to WeightHistory, and it will be stored in the client's storage and reflect it in the current model for clients.
  * Justification: This feature improves the product significantly because user gets to save their client's weight history data in the storage system for future uses.
  * Highlights: This enhancement affects existing storage to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it modifies the current client's storage and required to new commands.

* **New Feature**: Added tests for Model and Storage for Exercise Routines.
  * What it does: run automated checks for Model and Storage for Exercise Routines for this app to mitigate some bugs.
  * Justification: This feature improves the product significantly by mitigating any potential bugs.
  * Highlights: Testing has to be done on a case to case basis. Testings for storages are not simple and required an in-depth analysis of design alternatives.

* **New Feature**: Added different commands like (*AddRoutine and EditRoutine commands*).
  * What it does: allows the user to add or edit Exercise Routines.
  * Justification: This feature improves the product significantly because user gets to add or change the exercise routines in the storage system for future uses.
  * Highlights: This enhancement affects existing storage to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it modifies the current client's storage and required to new commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jundatan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=jundatan&zR=AY2223S2-CS2103T-T15-2%2Ftp%5Bmaster%5D&zACS=355.44&zS=2023-02-17&zFS=jundatan&zU=2023-04-04&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
  * Managed releases `v1.3` (1 releases) on GitHub.
  * Managed milestones and issues on GitHub.
  * Managed work assignments for team members in general.

* **Enhancements to existing features**:
  * Adding more error checks like addWeight Command where multiple weights can affect the graph.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add` [\#62](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/62)
    * Added documentation for the features `addRoutine` and `editRoutine` [\#148](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/148)
    * Fix and update visuals for UG [\#193](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/193)
    * Rearranging of commands for UG [\#193](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/193)
    * Adding table for prefixes for `Features` [\#193](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/193)
    * Add `Menu Items` subsection [\#193](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/193)
    * Fix documentation bugs [\#193](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/193)
  * Developer Guide:
    * Added Use case for `add` [\#67](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/67)
    * Added Use case for `addRoutine` and `editRoutine` [\#117](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/117)
    * Added Implementation for `addRoutine` and `editRoutine` [\#134](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/134)
    * Update UML diagrams and descriptions for Storage and Model part of FitBook [\#145](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/145)
    * Added Implementation for `add` and `edit` clients [\#206](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/206)
    * Added Appendix for Challenges and future enhancements [\#206](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/206)

* **Community**:
  * PRs reviewed (Not all are added:
  [\#165](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/165),
  [\#158](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/158),
  [\#156](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/156),
  [\#146](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/146),
  [\#142](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/142),
  [\#141](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/141),
  [\#139](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/139),
  [\#138](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/138),
  [\#137](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/137),
  [\#135](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/135),
  [\#133](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/133),
  [\#132](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/132),
  [\#131](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/131),
  [\#130](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/130),
  [\#129](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/129),
  [\#124](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/124),
  [\#115](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/115),
  [\#114](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/114),
  [\#113](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/113),
  [\#112](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/112),
  [\#108](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/108),
  [\#107](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/107),
  [\#106](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/106),
  [\#99](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/99),
  [\#95](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/95),
  [\#94](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/94),
  [\#94](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/94),
  [\#91](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/91),
  [\#89](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/89),
  [\#88](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/88),
  [\#85](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/85),
  [\#82](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/82),
  [\#81](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/81),
  [\#75](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/75),
  [\#58](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/58),
  [\#48](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/48)
  * Contributed to forum discussions
  [\#109](https://github.com/nus-cs2103-AY2223S2/forum/issues/109),
  [\#114](https://github.com/nus-cs2103-AY2223S2/forum/issues/114),
  [\#150](https://github.com/nus-cs2103-AY2223S2/forum/issues/150)
  * Reported bugs and suggestions for other teams in the class (examples: [PE-DRY-RUN](https://github.com/jundatan/ped))
  
