---
layout: page
title: ShanHng's Project Portfolio Page
---

### Project: The Intern's Ship

**The Intern's Ship (TinS)** is a desktop application for computing students looking for internships to more efficiently
manage their numerous internship applications. Suited for fast typers, TinS uses a combination of Command Line Interface
(CLI) to collect user inputs and Graphic User Interface (GUI) to display relevant information for ease of use.

Given below are my contributions to the project.

* **New Feature**: Added new UI component called `Page`.
  * What it does: `Page` is an abstract class that acts as the blueprint for the various sub-Pages (to display results of different commands).  
  * Justification: many of our commands in TinS require a separate display to show the output (e.g. viewing detailed internship info, view clashes, view statistics and view calendar)
 
* **New Feature**: Created Internship Information Page, Home Page, Clashing Events Page and Find Events Results Page.
  * What it does: allows the user to view information requested by user with a command. E.g. Internship Information Page shows information of a selected internship with `select` command, Clashing Events Page shows results for `clash` and etc.
  * Justification: Each command need a `Page` of its own to showcase results as the best format to showcase results of different command vary
  * Highlight: Each of these pages are subclasses of `Page`
  
* **New Feature**: Add `calendar` command and `CalendarPage` 
  * What it does: allows the user to view all events in a `Calendar` 
  * Justification: user can visualize the upcoming events they have in a more effective manner
  * Credit: `CalendarPage` makes use JavaFX controls from the CalendarFX library, an open source calendar framework for JavaFX 8.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shanhng&breakdown=true)

* **Enhancements to existing features**:
  * Change the appearance of GUI to fit the theme of TinS.
  * Change existing model to fit TinS

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `select`.
  * Developer Guide:
    * Formatting
    * Authored "Viewing all events in a calendar" segment under Implementation 
    * Edit "UI component" segment under Architecture
      * Created relevant class diagrams and sequence diagrams for this segment
