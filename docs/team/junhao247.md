---
layout: page
title: JunHao's Project Portfolio Page
---

## Overview

***ExecutivePro*** is a desktop app for Human Resource managers to manage their employee information,
optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, EP can get your employee management tasks done **faster** than traditional GUI apps.

ExecutivePro is a project adapted from AddressBook - Level 3.

Given below are my contributions to the project.

### Summary of Contributions

---

#### Code Contributed

* [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=junhao247&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Enhancements Implemented
* **New Features**
    * Implemented `batchadd` feature.
        * Created a `BatchAddCommand` class to handle the logic
          and `BatchAddCommandParser` class to handle the parsing of the user input.
        * Use Case: User can add multiple employees at once if they have the data in the form of a `.csv` file.
        * Why: Users will have to manually input each employee using an add command without this command. Hence, with
          this command, users get to transfer their current database into our database,
          allowing for a seamless transition.
        * Constraints: This implementation was rather difficult as it was rather different from all other commands
          which were implemented before. Moreover, having to read from an external file,
          especially when `.csv` file is involved, making it difficult to test.
    
  * Implemented `batchexport` feature.
      * Created a `BatchExportCommand` class to handle the logic
        and `BatchExportCommandParser` class to handle the parsing of the user input.
      * Use Case: User can export the database in the form of a `.csv` file into the `data` folder.
      * Why: Users get to export the database into a file in order to print out the current records for employees.
      * Constraints: This implementation was difficult because I had to ensure that the formats remain the same
        and is not affected by external files.
    * Wrote test cases for `BatchAddCommand` and `BatchExportCommand`.


#### Contributions to the UG
* Added documentation for following parts:
    * `batchadd` command.
    * `batchexport` command.
    * `clear` command.
    * Navigating User Guide.
    * Interface Layout.
    * Fields Format.

#### Contributions to the DG
* Added implementation details for BatchAdd Function
* Added implementation details for BatchExport Function
* Wrote use stories
* Wrote use cases
* Helped adapt the DG from the previous _AddressBook_ implementation to our _ExecutivePro_ implementation.


#### Contributions to the team-based tasks
* Coordinated the weekly meetings and ensure that agenda is being met.
* Keep track of tasks to be done by team members for weekly objectives.
* Helped to create and distribute issues.
* Helped with the sequence diagrams and adapting them to _ExecutivePro_ from the previous _AddressBook_ implementation.
* Did manual testing of the application to find bugs.
* Was in-charge of keeping track of deadlines and division of labour.

#### Reviewing Contributions
* Pull Requests reviewed:
  [#254](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/254),
  [#248](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/248),
  [#247](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/247),
  [#239](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/239),
  [#237](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/237),
  [#235](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/235),
  [#236](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/236),
  [#228](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/228),
  [#223](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/223),
  [#220](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/220),
  [#219](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/219),
  [#175](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/175),
  [#173](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/173),
  [#167](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/167),
  [#158](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/158),
  [#151](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/151),
  [#141](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/141),
  [#138](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/138),
  [#136](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/136),
  [#127](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/127),
  [#119](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/119),
  [#117](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/117),
  [#115](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/115),
  [#114](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/114),
  [#112](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/112),
  [#104](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/104),
  [#103](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/103),
  [#95](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/95),
  [#76](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/76),
  [#75](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/75),
  [#72](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/72),
  [#70](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/70),
  [#66](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/66),
  [#65](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/65),
  [#57](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/57),
  [#52](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/52),
  [#50](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/50),
  [#47](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/47),
  [#46](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/46),
  [#45](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/45),
  [#44](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/44),
  [#41](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/41),
  [#36](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/36),
  [#35](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/35),
  [#34](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/34),
  [#33](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/33),
  [#24](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/24),
  [#23](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/23),
  [#22](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/22),
  [#10](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/10),
  [#9](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/9)




**Contributions beyond the project team :**
- Reported numerous bugs for team [_CS2103T-F11-3(Vaccination Management System)_](https://github.com/AY2223S2-CS2103-F11-3/tp).
Some examples are: [#296](https://github.com/AY2223S2-CS2103-F11-3/tp/issues/296),
[#300](https://github.com/AY2223S2-CS2103-F11-3/tp/issues/300),
[#307](https://github.com/AY2223S2-CS2103-F11-3/tp/issues/307)

