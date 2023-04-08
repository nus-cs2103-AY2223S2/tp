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
    * Syntaxes displayed are aligned vertically to enable better reading and makes the displayed texts less overwhelming (PR: [\#171]())

* **Code contributed**: [RepoSense link](https://github.com/nerdyboy98/tp)

* **Project management**:
  * Managed releases `v1.3 Trial` - `v1.3.1` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the existing help button to display 'User Guide' rather than 'Help' to better explain what the buttons does (PR: [\#65]())
  * Updated the welcome message to prompt the user to get help by typing `help` into the command box (PR: [\#125]())
  * Changed the app title to display the app name instead of the default AB3 (PR: [\#65]())
  * Expanded on the existing help-command-parser to take in more help related commands(PR: [\#65](), [\#90](), [\#125]())
  * Updated the error message to display the correct syntax in the event a user inputs something wrong (PR: [\#128]())
* **Documentation Contributions**:
  * User Guide:
    * Added documentation for the `help` feature
    * Updated the new syntax for several other features
  * Developer Guide:
    * Added implementation details of the `help` feature

* **Community**:
  * Suggested several intuitive command words to make certain feature's command word easier to remember (examples: `addNote`, `deleteNote`, `editNote`)
  * Helped teammates test the usability of the UserGuide by trying out the syntaxes listed there and reporting any bug/discrepancies
  * Ensured that the UserGuide tallies with the help commands displayed in the app (PR: [\#190]())
  * Helped to test the User Interface after modification to ensure that images and boxes do not go out of alignment

