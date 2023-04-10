# Nevin's Project Portfolio Page

## Overview

***CareFlow*** is a desktop application for _patient and drug inventory management of medical clinics_, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
***CareFlow*** can get your patient and drugs management tasks done **faster** than traditional applications.

## <ins>Code contributed: </ins>
* RepoSense Link: [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=nevinlim&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) 

## <ins>Enhancements implemented: </ins>
* A summary of the enhancements you implemented.
* ### Enhancement to Application UI:
  * ***Implementation of Light/Dark Theme:***
    * Added a `lighttheme.css` stylesheet and implemented a toggle theme method `setTheme` within the `MainWindow` class for UI.
    * Created shortcut keys accelerators which allows users to use shortcut keys combinations to toggle theme: 
    **CTRL+SHIFT+L** for light and **CTRL+SHIFT+D** for dark.
    * Created `MenuItem` for the respective Dark and Light theme for additional mode of input to toggle themes.
  * ***Altered the button for Help popup Window:***
    * Changed the '*CopyURL*' button in the help window to a more user-friendly '*Open URL*' button which opens 
    the address link in a browser on user's computer directly.
* ### Enhancement to Application Main Features:
  * ***Implementation of Drug command classes for Drug subsystem:***
    * Created `DeleteCommand`, `FindCommand`, `ListCommand`, `UpdateCommand` and helped implement `AddCommand`, 
    `ClearCommand` in the `drugcommands` directory (located in `commands` in `logic`)
    * Specified the respective message usages for each command
    * Implemented the constructor methods for the aforementioned classes
    * Implemented the `execute` methods for the various classes as well as exception handling in each
    of these classes 
  * ***Implementation of Drug command Parser classes for the Drug subsystem:***
    * Created `AddCommandParser`, `DeleteCommandParser`, `FindCommandParser` and `UpdateCommandParser`classes
    in the `drugparser` directory (located in `parser` in `logic`)
    * Implemented the `parse` method for the aforementioned classes to handle the various user inputs for the
    respective commands.
    * Implemented the various parsing methods for the Drug Parser classes such as `parseTradeName`, 
    `parseActiveIngredient`, `parseDirection`, `parsePurpose`, `parseSideEffect` and `parseStorageCount` to parse 
    user inputs into the necessary Objects that the respective Drug Command requires.

## <ins>Contributions to the UG: </ins>
* Contributions to User Guide:
  * Added the sample screenshots for every command
  * Implemented some part of the documentation for `Drug` Commands such as `d add`, `d find`, `d update`
  * Improved the description of most commands in section 4, Commands.
  * Helped in the formatting and tidying up some parts of the UG such as removing white-spaces and adding line breaks.

## <ins>Contributions to the DG: </ins>
* Contributions to Documentation Guide:
  * added UML Sequence diagram for execute("p delete 1") in CareFlowLogic component

## <ins>Contributions to team-based tasks</ins>
* Helped fix several bugs
[#259](https://github.com/AY2223S2-CS2103T-W09-3/tp/issues/259),
[#253](https://github.com/AY2223S2-CS2103T-W09-3/tp/issues/253),
[#248](https://github.com/AY2223S2-CS2103T-W09-3/tp/issues/248),
[#227](https://github.com/AY2223S2-CS2103T-W09-3/tp/issues/227),
[#237](https://github.com/AY2223S2-CS2103T-W09-3/tp/issues/237)
after bugs were reported from PE-Dry Run.

* Wrote java test files for `AddCommandParserTest`, `DeleteCommandParserTest`, `FindCommandParserTest`
and `UpdateCommandParserTest` in `drugparser` to improve the code-coverage of our program.

* Contributed in the brainstorming process for user stories and certain essential features.

## <ins>Review/mentoring contributions: </ins>
* PRs reviewed: 
[#29](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/29), 
[#34](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/34), 
[#39](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/39),
[#42](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/42),
[#55](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/55),
[#78](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/78),
[#79](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/79),
[#83](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/83),
[#118](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/118),
[#127](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/127),
[#166](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/166),
[#171](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/171),
[#193](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/193),
[#276](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/276)

## <ins>Contributions beyond the project team: </ins>
* Bugs reported for another team F10-3 (MODCheck): 
[#1](https://github.com/nevinlim/ped/issues/1),
[#2](https://github.com/nevinlim/ped/issues/2),
[#3](https://github.com/nevinlim/ped/issues/3),
[#4](https://github.com/nevinlim/ped/issues/4),
[#5](https://github.com/nevinlim/ped/issues/5),
[#6](https://github.com/nevinlim/ped/issues/6),
[#7](https://github.com/nevinlim/ped/issues/7)

* Lent a helping hand to W09-4(ExecutivePro) by clarifying some queries regarding UI, certain JavaFX functions and behaviour.
