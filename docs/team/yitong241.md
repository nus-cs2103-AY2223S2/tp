---
layout: page
title: Sun Yitong's Project Portfolio Page
---

### Project: MedInfo

MedInfo is a desktop application for private hospital administrative staff. It helps manage patients,
their status, discharge dates and wards. MedInfo aims to solve the problem of slow, multiple step process of
documenting patient medical records during in-processing by zeroing in on the important details and provide simple,
fast access to a particular patient’s medical records for hospital admin staff.

The user interacts with MedInfo using a CLI, and it has a GUI created with JavaFX.
MedInfo is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * `status` attribute [#95](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/95)
    * represent the medical status of the patients with `status`
  * `find` command [#110](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/110)
    * find the patient by searching for specific `name`, `nric`, `status` and `ward`.
  * `addward` command [#143](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/131)
    * add new wards into the MedInfo system 
  * `deleteward` command [#156](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/156)
    * delete an existing ward in the system
  * `sort` command [#159](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/159)
    * sort the displayed list of patients by `name`, `status`, `ward name` and `discharge date` in `ascending` or `descending` order.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=yitong241&breakdown=true)
* **PR contributed**: [PR](https://github.com/AY2223S2-CS2103T-T12-2/tp/pulls?page=1&q=is%3Apr+is%3Aclosed+author%3Ayitong241)

* **Project management**:
  - Active reviewer for code-related Pull Requests;
  - Active contributor of issue tracker;
  - Manage the deadlines and tasks for teammates;

* **Enhancements to existing features**:
    *Modify the logic to adapt to MedInfo [#93](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/93)
    * `add` command 
      * add new patients with specific `name`, `nric`, `status` and `ward` [#115](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/115)
    * `find` command
      * modify FindCommand to find based on `name`, `nric`, or `status` [#110](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/110)
    * add confirmation windows when clearing [#151](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/131)

* **Documentation**:
  * User Guide:
    * `sort-by` command
    * refine overall structure (table of contents, summary table)
  * Developer Guide:
    * Table of content [#165](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/165)
    * `add` command and corresponding use cases [#131](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/131)
    * `sort` command and corresponding explanations [#165](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/165)

* **Community**:
  * Review many code-related PRs by the whole group with detailed insights and comments
    * [Reviewed PRs](https://github.com/AY2223S2-CS2103T-T12-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ayitong241)
  * Refactor the application from AB3 to MedInfo completely
    * [Link to PR](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/177)

* **Issues Fixed**:
  * Fix message constraints bug in Storage [#94](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/94)
  * Fix bugs caused by improper use of Enum [#95](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/95)
  * Fix bugs caused by inputting multiple prefixes when finding [#116](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/116)
  * Fix issue with sort command error message display [#167](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/167)
  * Fix issue with date time validation [#234](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/234)

* **Testing**:
  * Add test cases for find command [#123](https://github.com/AY2223S2-CS2103T-T12-2/tp/pull/123)

