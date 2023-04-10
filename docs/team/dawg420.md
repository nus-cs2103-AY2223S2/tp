---
layout: page
title: Ma Yuchen's Project Portfolio Page
---

### Project: HospiSearch

HospiSearch is a desktop app for healthcare administrators to manage hospital/clinic patients' particulars and optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HospiSearch can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **Code Contributed**: [Code dashboard](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dawg420&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

**Features**
* New Feature: `backup`/`load` [#54](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/54)
  * What it does: Allows the user to backup to a specified slot, with an accompanying message.
  * The time when the backup is created will be reflected in the backup details.
  * Justification: This feature allows a user to save the current data state for safekeeping or exporting uses.

* New Feature: `viewbackups` [#89](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/89)
  * What it does: Allows the user to see which backups are currently available.
  * Justification: This interface gives tells users what backups are available and the details of each backup, for ease of keeping track.
  * Highlights: The index, accompanying message, date and time of backup will be shown in each backup card.
  * Difficulty in implementation
    * As this feature requires handling of the switching between the list of `personCard` and `backupCard`s, it was tricky to implement.
    * As this feature requires an extensive understanding of how Storage, Model and UI works, it was time-consuming to implement.


* New User field: `medicine` [$94](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/94)
  * Added medication fields for the patient.
  * Justification: Hospital staff and doctors need to keep track of a patient's prescription medication.
  * Highlights: This field is treated similarly to a `tag`. A patient can have either 0 or more medicines attributed to them.


* UI improvements: [#134](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/134)
  * Standardised the font and improved margins.
  * Highlighted the `medicine` and`gender` fields with colours that shows up clearly in both light and dark modes. [#134](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/134)
  
* Test cases:
  * Wrote test cases for the backup/load commands [#92](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/92)
  * Wrote test cases for the new `medicine` field
  * Wrote test cases for new methods created in `model` and `logic` [#94](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/94)

**Documentation**

* User Guide:
  * Provided annotations for all diagrams for better visibility. [#237](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/134)
  * Wrote the backup/load sections of the commands list.
* Developer Guide:
  * Gave a detailed explanation of the Backup/Load function and included an UML diagram explaining how it works [#113](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/113)

**Contributions to team based tasks**
* Ensured that hyperlinks are working throughout the UG and DG. [#154](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/154)
* Included tips throughout the UG that gives potentially useful nuggets of information to the user. [#134](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/134)


**Contributions beyond project team**:
* Reported a total of [6](https://github.com/dawg420/ped/issues) bugs and issues for PE-D.
