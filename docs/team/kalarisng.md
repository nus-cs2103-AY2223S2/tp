---
layout: page
title: Kalaris Ng's Project Portfolio Page
---

### Project: FitBook

Fitbook is a desktop tracking book application used by gym or personal fitness trainers to track the progress and information
of their clients. With FitBook, trainers are able to efficiently manage their clients' information in a centralised database. 
FitBook is written in Java and features a combination of both CLI and GUI applications.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to find clients.
    * What it does: allows the user to filter and find clients by a predicate.
    * Justification: This feature improves the product significantly because a user can find clients effectively and efficiently based on certain characteristics of a client.
    * Highlights: This enhancement required an in-depth analysis of design alternatives to find the most effective and intuitive way for the user to find their client.

* **New Feature**: Added the ability to add new weight to clients.
  * What it does: allows the user to add new weights to a client tagged with a date and time.
  * Justification: This feature improves the product significantly as it allows modification of a client's current weight and stores a client's old weight in their weight history. This weight history can then be converted into a line graph to view a client's progress.
  * Highlights: The implementation of this enhancement was challenging as it required an in-depth analysis of the existing classes. The implementation was challenging as it required modifications to the existing weight class.
  
* **New Feature**: Added the ability to view a weight history graph of a client.
  * What it does: allows the user to view their weight history using a line graph with a single command.
  * Justification: This feature improves the product significantly as it allows the user to generate a weight history graph that can show their client's progress throughout their time with them.
  * Highlights: This enhancement required an in-depth analysis of the design of the weight history class.

* **New Class**: Add **WeightHistory** class to client.
  * What it does: allows the user to store their weight history.
  * Justification: This feature improves the product significantly as it allows the user to store a client's weight history. Clients may view their fitness progress with their weight history.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=kalarisng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=kalarisng&tabRepo=AY2223S2-CS2103T-T15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.3` (1 release) on GitHub
    * Led the team in project meetings as team lead to ensure that every task is completed before the set due date.

* **Enhancements to existing features**:
    * Enhanced `find` command to filter by more than 1 predicate and in any order.
    * Enhanced `graph` command to sort the weight history of a client in order of the date and time, and remove data for weights added 1 month before the current date. This allows the graph to only show the client's weight history within 1 month of the current date.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `find` [\#106](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/106)
        * Added documentation for the features `addWeight` and `graph` [\#151](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/151)
    * Developer Guide:
        * Added implementation details of the `find` feature with the usage of UML diagrams [\#130](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/130)
        * Added Use cases for `find`, `addWeight` and `graph`. [\#201](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/201/files)

* **Community**:
    * PRs reviewed (non-exhaustive): [\#102](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/102), [\#134](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/134), [\#136](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/136)
    * Reported bugs and suggestions for other teams in the class (examples: [PE-DRY-RUN](https://github.com/kalarisng/ped))
