---
layout: page
title: Arkobrata Chaudhuri's Project Portfolio Page
---

### Project: MediMeet

**MediMeet** is a patient appointment management system for individual medical practitioners and small clinics.
With MediMeet, you can efficiently manage patient and appointment information in one place,
saving you both time and the hassle of maintaining records across multiple systems. <br>

MediMeet is a command-line interface (CLI) application with fast, easy-to-use commands which make it ideal for the
fast typist.
With MediMeet, we make it easy to add and edit patient information, appointments and so much more, making it
a one-stop solution to manage your individual medical practice or small clinic! <br>

Given below are my contributions to the project.

* **New Features**:
  * Add patient notes: Added a command to add notes to a particular patient.
    * **What it does**: Adds a patient notes (remark) field to a given patient.
    * **Justification**: We needed to help doctors quickly add some notes to a patient, which is why the implementation of this feature was necessary.
    * **Highlights**: Implementing this feature required a comprehensive understanding of how patient fields are stored, accessed and added, and allowed me to better understand the codebase.
  * Add appointments for patients: Added a command to add appointments for a particular patient.
    * **What it does**: Adds an appointment with a `Description`, `Timeslot` and `Doctor` fields to the list of appointments.
    * **Justification**: As our application is a patient-appointment management system geared towards individual practitioners and small clinics, adding appointments for a patient was the most crucial part of our application.
    * **Highlights**: Adding appointments allowed me to fully make sense of how to work on an existing codebase, managing to extend the original project given to us into an application of our own.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=redHat-arko&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

<div style="page-break-after: always;"></div>

* **Enhancements implemented**:
  * Changed the `Name` field in `Patient` to be non-case sensitive, which could be considered a bug from the original AB3 project.

* **Documentation**:
    * User Guide: Added documentation for the following commands implemented in MediMeet:
      * Add patient (`add_patient`)
      * Edit patient (`edit_patient`)
      * Find patient (`find_patient`)
      * Find patient details (`find_patient_details`)
      * Add notes to a patient (`remark_patient`)
      * Delete patient (`delete_patient`)
      * Add appointment (`add_appt`)
      * Edit appointment (`edit_appt`)
      * Find appointment (`find_appt`)
      * Delete appointment (`delete_appt`)
      * Also wrote general documentation for the User Guide and added images for the updated Ui.

    * Developer Guide: Added documentation for the following commands implemented in MediMeet:
      * Add patient (`add_patient`)
      * Add notes to a patient (`remark_patient`)
      * Add appointment (`add_appt`)

* **Contributions to team based tasks**:
    * PRs reviewed (with non-trivial review comments): 5 (e.g, pull request [#69](https://github.com/AY2223S2-CS2103T-W12-4/tp/pull/69))
    * Set up the pull request to the upstream repository.
