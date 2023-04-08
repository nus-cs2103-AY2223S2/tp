---
layout: page
title: Kalaris Ng's Project Portfolio Page
---

### Project: FitBook

Fitbook is a desktop tracking book application used for tracking the progress and informations
for the clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find clients.
    * What it does: allows the user to filter and find clients by a predicate.
    * Justification: This feature improves the product significantly because a user can find clients effectively and efficiently based on certain characteristics of a client.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added the ability to add new weight to clients.
  * What it does: allows the user to add new weights to a client tagged with date and time.
  * Justification: This feature improves the product significantly as it allows modification of a client's current weight and stores a client's old weight in their weight history. This weight history can then be converted into a line graph to view a client's progress.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  
* **New Feature**: Added the ability to view a weight history graph of a client.
  * What it does: allows the user to view their weight history using a line graph with a single command.
  * Justification: This feature improves the product significantly as it allows the user to generate a weight history graph that can show their client's progress throughout their time with them.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Add **WeightHistory** class to client.
  * What it does: allows the user to store their weight history.
  * Justification: This feature improves the product significantly as it allows the user to store a client's weight history. Clients may view their fitness progress with their weight history.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=kalarisng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=kalarisng&tabRepo=AY2223S2-CS2103T-T15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Enhanced `find` command to filter by more than 1 predicate and in any order.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `find`, `addWeight` and `graph` [\#106](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/106), [\#151](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/151)
    * Developer Guide:
        * Added implementation details of the `find` feature. [\#130](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/130)
        * Added Use cases for `find`, `addWeight` and `graph`. [\#201](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/201/files)

* **Community**:
    * PRs reviewed: [\#102](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/102), [\#134](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/134), [\#136](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/136)
    * Reported bugs and suggestions for other teams in the class (examples: [PE-DRY-RUN](https://github.com/kalarisng/ped))
