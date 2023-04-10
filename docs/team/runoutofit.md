---
layout: page
title: Chua Wen Hong's Project Portfolio Page
---

### Project: MediMeet

**MediMeet** is a patient appointment management system for individual practitioners and small clinics. With MediMeet, you can efficiently manage patient information and appointment information in one place, saving you both time and money.
**MediMeet** combines the visual appeal of a GUI (graphical user interface) with the speed and convenience of a command-line interface. Our easy-to-use commands make it easy to add, edit patient information, appointments and so much more!

Given below are my contributions to the project.

* **New Feature**: `delete_appt`- Added a command to delete appointment by index.
  * **What it does**: Deletes an appointment based on the index from the last shown list.
  * **Justification**: When an appointment is finished or cancelled, the user can then be able to delete the appointment from MediMeet.

* **New Feature**: `view`- Added a command for user to view a patient card fully.
    * **What it does**: Creates a popup window displaying the patient card in full for user to see.
    * **Justification**: If the patient remarks are too long, it causes a text overflow on the original patient card and it will be shown as `...`, thus adding this command to create a popup window for the full patient card increases visibility for the user.
    * **Highlights**: Implementing this feature required a good understanding of `predicates` and how `model` is updated through the `predicate`. It also required a decent understanding of JavaFX to ensure the popup window is up to requirement.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=runoutofit&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements implemented**: Refactored `delete_appt` to use index instead of `AppointmentID`, a feature i originally implemented but was ultimately scrapped as it was not intuitive.

* **Project management**:
    * update project notes documents.

* **Documentation**:
    * User Guide:
        * update quick start section of the user guide.
        * add `view` command into the user guide.
        * add `delete_appt` command into the user guide.
        * fix the bugs found in the user guide during the practical exam dry run.
    * Developer Guide:
        * add and update `delete_appt` command.
        * add and update `delete_patient` command.
        * add `help` command.
        * add `view` command.
        * added sequence UML diagrams for `delete`, `delete_appt`, `help`, and `view` commands.

* **Contributions to team-based tasks**:
    * reviewed and approved PRs by other team members: total 15 reviews. For example, [#61](https://github.com/AY2223S2-CS2103T-W12-4/tp/pull/61)
