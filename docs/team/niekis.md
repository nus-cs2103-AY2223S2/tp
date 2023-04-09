---
layout: page
title: Fun Leon's Project Portfolio Page
---

### Project: Army Information Management System

Army Information Management System is a desktop address book application built to handle the personal information of military personnel. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a filter command that allows the user to filter out irrelevant contacts and only view desired ones.
  * What it does: Allows the user to filter through all existing contacts, only showing contacts with fields that contain the given keywords.
  * Justification: This feature is particularly helpful if there are many contacts. This feature allows users to compare contacts that have similar fields. It also helps forgetful users, who can only remember a specific field, to find contacts easily.

* **New Feature**: Added an import CSV command that allows the user to add multiple contacts at once using a CSV file.
  * What it does: Check if the given CSV file is proper (i.e. is an existing CSV file, contains same number of rows and columns, each cell contains valid information, contains no duplicates, has no duplicates with any existing contact), and if the file is valid, it converts each row's information to a new person in AIMS.
  * Justification: Many offices in the army still use Excel or Google sheets, therefore this feature will ensure a smooth transition to using this application. Some users may also find it easier to import massive amounts of data into a CSV file first. With this feature, users can collect data using Excel/Google sheets first, and rely on the ImportCsv command to validate the data given, since the data can only be imported if it is valid.
  * Credits: Used a third-party library (OpenCsv) to handle parsing of CSV files to a nested array.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=niekis&breakdown=true)

* **Project management**:
  * Managed releases `v1.2.1` and `v1.3.0` on GitHub

* **Enhancements to existing features**:
  * Changed the way duplicate persons are detected (System now disallows two different contacts to have the same email or phone number). Fix and adjusted all existing code and errors to reflect this new change. [\#79](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/79/files)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `filter` [\#61](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/61) and `importcsv` [\#79](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/79/files)
  * Developer Guide:
    * Added implementation details of the `filter` feature [\#67](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/67)

- **Community**:
  * PRs reviewed (with non-trivial review comments): [\#42](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/42), [\#65](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/65)
  * Reported bugs for [CS2103-W15-3](https://github.com/AY2223S2-CS2103t-W15-3/tp) and gave suggested improvements [here](https://github.com/niekis/ped/issues).

- **Tools**:
  - * Integrated a third party library (OpenCsv) to the project ([\#79](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/79/files))
