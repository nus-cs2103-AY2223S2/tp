---
layout: page
title: Xiao Yan's Project Portfolio Page
---

### Project: Medimate

MediMate (MM) is a cross-platform desktop application for medical professionals, specifically for private doctors or their receptionists, who are experienced with computers and currently using paper records to store patient information. With this solution, they will be able to better manage their patient data, including updating, accessing and adding new patient details easily. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, MM can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Add Age attribute to a patient for recording
    * What it does: This new feature allows users to record patient's age when adding a patient
    * Justification: This feature is important because it provides users extra information on patients, making the prescription more precise
    * Highlights: The add command can be used to add patient records with Age with prefix `ag/`. It can be also edited using `edit` command.


* **New Feature**: Make appointment with a patient
  * What it does: Users can now make an appointment with a patient by a specified date and time duration
  * Justification: This feature is considered to be one of the core features of our product. It provides a super convenient way for users to make new appointment with patients or reschedule that appointment.
  * Highlights: At this version, we constrain that one patient's can have at most one appointment which is practical. Also, when adding an appointment, there will be some automatic clash check so that we ensure, with high possibility, there will be no clashes for users' schedule. Besides, there are strict checks for the format and value of startTime and endTime, trying to make the appointment reasonable.

* **New Feature**: Mark appointment with a patient
  * What it does: User can now mark an appointment when it's done or cancelled by patients.
  * Justification: This feature enables users to mark an appointment as done so that it's better for them to schedule next appointment with patients
  * Highlights: Users can not mark a patient without appointment. Users should specify an index to indicate a patient. Marked appointment will be cancelled and patients will now have no appointment


* **New Feature**: List patients with appointment on a specified date where time sorted
  * What it does: User can list all patients with appointment on a specified date with scheduled time sorted
  * Justification: This feature helps users to check availability when wanting to make an appointment with a specified patient. Also, by ordering time, it makes it clearer for users to check availability
  * Highlights: date format is yyyy-MM-dd, invalid date format is not allowed and will cause exceptions thrown


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=windy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=WindyCall&zR=AY2223S2-CS2103T-W11-4%2Ftp%5Bmaster%5D&zACS=126&zS=2023-02-17&zFS=windy&zU=2023-04-08&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
  * Help manage releases `v1.2` - `v1.4` (5 releases) on GitHub
  * Manage issues
  * Manage PR review and fianl merge

* **Enhancements to existing features**:
    * Wrote additional tests for existing features
    * Add Age attribute to existing patient's profile

* **Documentation**:
    * User Guide:
        * All `Appointment` related features. [\#188](https://github.com/AY2223S2-CS2103T-W11-4/tp/pull/188)
        * Modify Add Command to support new attributes. [\#98](https://github.com/AY2223S2-CS2103T-W11-4/tp/pull/98)
    * Developer Guide:
        * Add detailed Appointment implementation to `Implementation` part of DG. [\#258](https://github.com/AY2223S2-CS2103T-W11-4/tp/pull/258)
        * Modify `DG-Design-Model` part. [\#333](https://github.com/AY2223S2-CS2103T-W11-4/tp/pull/333)
        * Modify `DG-Appendix-Instructions` part. [\#334](https://github.com/AY2223S2-CS2103T-W11-4/tp/pull/334)

* **Tools**:
  * IntelliJ IDEA CE
  * Source Tree
  * Plant UML