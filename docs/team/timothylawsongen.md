---
layout: page
title: Timothy's Project Portfolio Page
---

### Project: *Fish Ahoy!*

*Fish Ahoy!* Is a **desktop app for managing your fish, fish tanks, and relevant tasks, optimized for use via a Command
Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). <br>

*Fish Ahoy!* **streamlines** the fish keeping experience by helping you keep track of your many **fishes**, **tanks** and
**weekly tasks**, such as feeding and cleaning.

Given below are my contributions to the project.

* **New Feature `TankFeedCommand`**: Added the ability for user to record when they have fed a particular tank [PR[\#83](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/83)]
  * What it does: allows the user to record when they have fed a particular tank.
  * Justification: This feature provides users with a quick way to update the last-fed times of all fishes in the tank.
  * Highlights: This enhancement required working with Java Date-Time classes, and was challenging to implement as changes to last-fed-date-time 
  of every fish in the model has to be reflected to the UI in real-time.

* **New Feature `TankViewCommand`**: Added the ability for the user to view a particular tank [PR[\#48](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/48)]
  * What it does: allows the user to view a particular tank.
  * Justification: This feature provides users with a quick way to view the details of that specific tank, which includes its fishes, tasks and reminders.
  * Highlights: The implementation for `TankViewCommand` was challenging as it required changes to existing commands.

* **New Feature**: Added profile pictures to fishes [PR[\#91](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/91)]
  * What it does: gives every fish a profile picture based on their species.
  * Justification: This feature enhances the visuals of FishAhoy!.
  * Highlights: This enhancement required careful selection of images as large image sizes would slow down the application significantly.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=TimothyLawSongEn&tabRepo=AY2223S2-CS2103T-T17-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Checked, approved and merged several PRs, and handled conflicts [PR[\#37](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/37), [\#58](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/58)]

* **Enhancements to existing features**:

  * Refactored `EditCommand` to `FishEditCommand` and `TankEditCommand` [PR[\#131](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/131)]
    * Changed the way `EditDescriptor` works since it was unable to directly get reference of new `Tank` as it does not have access to `model`

  * Refactored AddCommandTest, EditCommandTest, DeleteCommandTest to support fishes and new command formats [PR[\#241](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/241)]

* **Documentation**:
  * Developer Guide:
    * Added implementation details of the `TankFeedCommand` feature.
    * Added sequence diagram for logic and model portions of `TankFeedCommand`.
