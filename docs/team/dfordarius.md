---
layout: page
title: Darius Ng's Project Portfolio Page
---
### Project: FitBook

FitBook is a desktop tracking book application used for tracking the progress and information
for the clients. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java. Given below are my contributions to the project. <br>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=dfordarius&tabRepo=AY2223S2-CS2103T-T15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Class**: Added *Weight*, *Gender*, *Goal*  class to client and enhanced **Add** and **Edit** Command.
  * What it does: allows the user to add and edit his/her weight/gender/goal.
  * Justification: This feature improves the product significantly because a user may want to keep track of the
    details like weight/gender/goal of his/her clients which makes it easier the trainer to track.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.

* **New Feature**: Added *export* and *exportRoutine* functionality.
  * What it does: allows the user to export the compulsory data fields of a client which are Name, Phone Number,Email, Address, Weight and Gender. Also allows the user to export Routine details into a csv file.
  * Justification: This feature improves the product significantly because user gets to save their data into a csv format locally on his computer. Which he/she is then able to open the csv file using applications like Microsoft Excel to do further analysis.
  * Highlights: It required an in-depth analysis of design alternatives.

* **New Feature**: Added *deleteRoutine* and *deleteExercise* functionality for Routine Class.
  * What it does: allows the user to delete Routines and exercises in the Routine Storage, and it will be updated in the current model for Routine.
  * Justification: This feature improves the product significantly because user gets to delete a specific Routine in the Routine Storage.
  * Highlights: It required an in-depth analysis of design alternatives. The implementation too was challenging as it modifies the current Routine's storage.
  <div style="page-break-after: always;"></div>
* **New Feature**: Added *addExercise* and *listRoutines* functionality for Routine Class.
  * What it does: allows the user to list all routines as well as add exercises to the specific Routine in the Routine Storage, and it will be updated in the current model for Routine.
  * Justification: This feature improves the product significantly because it allows user to add new Exercises to the specific Routine in the Routine Storage as well as see the full Routine list stored in FitBook.
  * Highlights: It required an in-depth analysis of design alternatives. The implementation too was challenging as it modifies the current Routine's storage.

* **New Feature**: Added *findRoutines* and *clearRoutines* functionality for Routine Class.
  * What it does: allows the user to find specific Routines in FitBook by keywords as well as clear all Routines in FitBook.
  * Justification: This feature improves the product significantly because it allows user to filter and search Routines that have been stored in the Routine's Storage. Additionally, allows users to clear all Routines in the storage.
  * Highlights: It required an in-depth analysis of design alternatives.

* **Project management**:
  * Created organisation and set up project repository.
  * Managed and created labels to organise issues and pull requests.
  * Authored 38 issues, including User Stories.
  * Refactored entire code-base to suit naming conventions for FitBook.
  * Handled miscellaneous administrative tasks including:
    * Setting up project website.
    * Set up CodeCov to keep track of testing coverage
    * Enabling assertions in `build.gradle`.
    * `v1.3(final)`(1 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added documentation for the features `export`, `exportRoutine`, `findRoutine`, `clearRoutine`, `deleteRoutine`, `addExercise`, `deleteExercise`, `listRoutines` [\#146](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/146/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
    * Did cosmetic tweaks to existing documentation of features: [\#155](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/155/files)
  * Developer Guide:
    * Added user stories [\#64](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/64/files)
    * Added use case for `edit`, `listRoutines`, `export`, `exportRoutines`,`clearRoutines`, `deleteRoutine`, `deleteExercise`,`findRoutine`. [\#79](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/79/files) & [\#115](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/115/files)
    * Added Implementation for `export`, `addExercie`, `deleteRoutine`. [\#136](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/136/files)
    * Added manual testing for `delete`, `addRoutine`,`findRoutine`,`deleteExercise`, `view`, `graph` [\#202](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/202/files) & [\#208](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/208/files)
* **Community**:
   * PRs reviewed: [\#162](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/162), [\#117](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/117), [\#105](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/105), [\#99](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/99), [\#88](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/88), [\#84](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/84), [\#88](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/88), [\#82](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/82), [\#77](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/77), [\#74](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/74), [\#57](https://github.com/AY2223S2-CS2103T-T15-2/tp/pull/57)
   * Reported bugs and suggestions for other teams in the class (examples: [PE-DRY-RUN](https://github.com/dfordarius/ped))
