---
layout: page
title: Markus Yeo's Project Portfolio Page
---

### Project: Dengue Hotspot Tracker

Dengue Hotspot Tracker is a desktop app for managing dengue cases. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 9.5 kLoC.

Given below are my contributions to the project.

* Given below are my contributions to the project.

* **New Feature**: Changed default file storage format to be CSV
  * Justification: As we deal with tabular data, we would like users to be able to easily view the data entered into `DengueHotspotTracker` offline.
  Additionally, NEA personnel are more likely to be familiar with CSV files than json files!
  * Highlight: It was challenging to write to CSV as it required exploring a new package `OpenCSV`. Exploring a new codebase and adding it into our project was nonetheless a very fruitful experience.
  I had to add a new `CsvUtil` file to handle all the reading and writing from CSV.
* **New Feature**: Added the ability to import and export data as a csv file
  * Justification: This feature allows users to edit csv files to be able to import into `DengueHotspotTracker` and export from.
  Additionally allowing users to export files, edit them offline, then import them back into the DHT, is more convenient for sharing information with those without direct access to the DengueHotspotTracker.
  * This feature also pairs with the `find` function with allows users to filter the data and also export that specific set of data to be shared with others.
* **New Feature**: Added the ability to checkout overview data as a csv file
  * Justification: This feature allows users store the overviewed data outside of `DengueHotspotTracker` this will mean that they will be able to share a much smaller file as compared to having to send all the persons in the case list.
* **New Feature:** Added the GUI for overview.
  * Justification: It looked unmatching initially and we wanted a neat way as well as one which blended with the rest of our GUI. I wrote the `BinCard` class which enabled us to have a unified UI as that of the personList it is displayed alongside.
  This allowed us to have a nice scaleable overview tab.
* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=markusyeo)
* **Project management:**
  * Setup the team organization as well as repo.
  * Managed `v1.1`-`v1.2` issues.
  * Handled product releases for `v1.1`, `v1.2` and `v1.3` (including uploading and testing of JAR files).
  * Facilitated weekly meetings.
* **Team Tasks**
  * Refactored the entire code base including test cases to be adapted for Dengue Hotspot Tracker.

* **Documentation:**
  * User Guide
    * Reformatted the User Guide to be more readable
    * Added documentation for the `import`, `export` and `checkout` command
    * Added `Navigating the User Guide` portion to help users to understand the Ui
  * Developer Guide
    * Added implementation details and UML diagrams of the multi-index `import`, `export` and `checkout` features
* **Community:**
  * PRs reviewed (with non-trivial review comments):
