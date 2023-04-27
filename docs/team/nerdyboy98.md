---
layout: page
title: Chun Kiat's Project Portfolio Page
---

### Project: TrAcker

TrAcker - TrAcker is a desktop event management application for NUS CS2040 Teaching Assitants (TA). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to request help in the app and learn about the syntax.
  * What it does: allows the user to quickly narrow down to the input syntax they want to know and quickly start using the app.
  * Justification: This feature improves the product significantly because a user can now get help inside the app itself instead of having to scroll through the entire UG on an external webpage.
  * Highlights: 
    * Since the syntax to be used is displayed in the output box right below the input box, users can easily follow rather than scrolling around in the UserGuide to find the syntax the user wants to know. 
    * Help is divided into 3 different categories: `help student`, `help event`  & `help organisation`, and this reduces the clutter of displaying every single syntax available.
    * To select the desired help category, chaining of keyword is used. Say the categories under events are Tutorial, Lab and Consultation, the user will enter `help event tutorial`, `help event lab` or `help event consultation` to select their desired category.
    * Syntaxes displayed are aligned vertically to enable better reading and makes the displayed texts less overwhelming (PR: [\#170](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/170))
    * Headers added to further split all the syntaxes into sub-categories (PR: [\#171](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/171))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=nerdyboy98&tabRepo=AY2223S2-CS2103-F11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.3 Trial` - `v1.4` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the existing help button to display 'User Guide' rather than 'Help' to better explain what the buttons does (PR: [\#65](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/65))
  * Updated the welcome message to prompt the user to get help by typing `help` into the command box (PR: [\#125](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/125))
  * Changed the app title to display the app name instead of the default AB3 (PR: [\#65](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/65))
  * Expanded on the existing help-command-parser to take in more help related commands(PR: [\#65](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/65), [\#90](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/90), [\#125](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/125))
  * Updated the error message to display the correct syntax in the event a user inputs something wrong (PR: [\#128](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/128))
* **Documentation Contributions**:
  * User Guide:
    * Added documentation for the `help` feature
    * Updated the new syntax for several other features
  * Developer Guide:
    * Added implementation details of the `help` feature

* **Community**:
  * Suggested several intuitive command words to make certain feature's command word easier to remember (examples: `addNote`, `deleteNote`, `editNote`)
  * Helped teammates test the usability of the UserGuide by trying out the syntaxes listed there and reporting any bug/discrepancies
  * Ensured that the UserGuide tallies with the help commands displayed in the app (PR: [\#190](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/190))
  * Helped to test the User Interface after modification to ensure that images and boxes do not go out of alignment

