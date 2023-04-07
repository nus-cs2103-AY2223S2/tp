---
layout: page
title: Carmen Ang's Project Portfolio Page
---

### Project: SOCket

SOCket is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: `Meeting` Class to represent the meeting date of a `Project`
    * What it does: Stores the meetings of projects in `SOCket`
    * Justification: Allows for meeting dates for projects to be stored in `SOCket` so users can easily refer to meeting date for a certain project.
    * Highlights: Valid inputs are by the format: `DD/MM/YY HH:mm`
    * Credits: {-}

* **New Feature**: `editpj` command to edit existing projects
    * What it does: Edits fields of existing projects in `SOCket`
    * Justification: Users are able to correct any mistake of any fields in a `Project`
    * Highlights: `JUnit` tests to test command functionality
    * Credits: {-}

* **New Feature**: `removepj` command to remove fields of existing projects
    * What it does: Removes field descriptions of existing projects in `SOCket`
    * Justification: Users are able to easily empty fields of existing projects in `SOCket`
    * Highlights: `JUnit` tests to test command functionality
    * Credits: {-}

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cmang12&breakdown=true)

* **Enhancements to existing features**:
    * Updated `list` command to allow filtering by tags and languages
    * Updated HelpWindow to contain list of commands

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list`, `editpj`, `removepj`
        * Edit notes about command format under `Features`
    * Developer Guide:
        * Added use cases `UC07`, `UC08`, `UC10`
        * Added documentation for `list`
        * Added UML diagram for to show how `list` works
        * Updated "Appendix: Instructions for manual testing" with instructions for testing `list`, `editpj`, `removepj`
       

* **Community**:
    * *{`to be added`}*

