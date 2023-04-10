---
layout: page
title: Markus Yeo's Project Portfolio Page
---

### Project: DengueHotspotTracker (DHT)

Dengue Hotspot Tracker is a desktop app for managing dengue cases. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* Given below are my contributions to the project.

* **New Feature**: Changed default file storage format to be CSV
  * Justification: As we deal with tabular data, we would like users to be able to easily view the data entered into `DengueHotspotTracker` offline.
  Additionally, NEA personnel are more likely to be familiar with CSV files than json files!
  * Highlight: It was challenging to write to CSV as it required exploring a new package `OpenCSV`. Exploring a new codebase and adding it into our project was nonetheless a very fruitful experience.
  I had to add a new `CsvUtil` class to handle all the reading and writing from CSV.
* **New Feature**: Added the ability to import and export data as a CSV file
  * Justification: This feature allows users to export, edit, and import CSV files into `DengueHotspotTracker`. 
  Additionally, allowing users to export files, edit them offline, then import them back into the `DengueHotspotTracker` is more convenient for sharing information with those without direct access to the app.
  * This feature also pairs with the `find` function, as users can use `find` to filter the data before exporting that specific set of data to share with others.
* **New Feature**: Added the ability to checkout overview data as a CSV file
  * Justification: This feature allows users to store the aggregated data from the overview tab and export it outside of `DengueHotspotTracker` into a CSV file.
  This means that they will be able to checkout a much smaller file compared to exporting the entire persons case list when they want to just get the aggregated numbers.
* **New Feature:** Added the GUI for overview.
  * Justification: Our original way of including the overview in our GUI did not match the rest of the GUI. We wanted a design that looked neat and blended with the rest of our GUI. I wrote the `BinCard` class which enabled us to have a UI for the overview section that matched that of the case list it is displayed alongside.
  This allowed us to have a nice scaleable overview tab.
* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=markusyeo)
* **Project management:**
  * Set up the team organisation and repository.
  * Managed `v1.1`-`v1.2` issues.
  * Handled product releases for `v1.1`, `v1.2` and `v1.3` (including uploading and testing of JAR files).
  * Facilitated weekly meetings.

* **Team Tasks**
  * Refactored the entire code base including test cases to be adapted for DengueHotspotTracker.

* **Documentation:**
  * User Guide
    * Reformatted the User Guide to be more readable
    * Added documentation for the `import`, `export` and `checkout` commands
    * Added the `Navigating the User Interface` section to help users understand the UI
  * Developer Guide
    * Added implementation details and UML diagrams of the `import`, `export` and `checkout` features
* **Community:**
  * PRs reviewed (with non-trivial review comments):
    * [#72](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/72),
    [#74](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/74),
    [#79](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/79),
    [#124](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/124),
    [#131](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/131),
    [#138](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/138),
    [#142](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/142),
    [#234](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/234),
    [#260](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/260),
    [#277](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/277),
    [#284](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/284),
    [#286](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/286)
  
