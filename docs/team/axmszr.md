---
layout: page
title: Samuel Murugasu's Project Portfolio Page
---

### Project: DengueHotspotTracker (DHT)

DengueHotspotTracker (DHT) is a desktop app for managing dengue cases. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the overview, and the ability to change the way cases are classified into bins. The options are by location (`p/`), by age group (`a/`), and by variant (`v/`).
  * Justification: The goal of DHT is to provide NEA personnel with a comprehensive view of dengue outbreaks in Singapore. This can be and is achieved by allowing these users to focus on and analyse specific aspects of dengue cases. This is crucial in obtaining insights about the spread of the disease. We chose these three aspects for the following reasons:
    * A location-based overview allows users to track the spread of the Dengue outbreak in specific geographical areas and identify hotspots that require urgent attention.
    * An age-based overview allows users to identify age groups that are most affected by the outbreak. This allows NEA to adjust prevention and control measures based on which age group they wish to prioritise. This is particularly useful to protect vulnerable demographics (i.e. children and the elderly).
    * A variant-based overview allows users to monitor and track the spread of different viral dengue variants. This allows NEA to adjust prevention and control measures accordingly based on the most prevalent strains.
  * Highlights: This was a very good exercise in OOP, especially as this is a new feature we were implementing from scratch. The main difficulty came from having to edit the UI to accommodate the new overview and the related commands. Abstracting out each layer of the computation made it much easier for us to tweak it as we made modifications along the way. I added two additional packages:
    * `logic.analyst`, which contains the classes `AgeAnalyst`, `Analyst`, `DataBin`, `PostalAnalyst`, and `VariantAnalyst`. These handle the sorting and calculation of the number of cases in each of the overview categories.
    * `model.overview`, which contains the classes `AgeOverview`, `Overview`, `PostalOverview`, and `VariantOverview`. These serve as an interface for the model to access the `analyst` class and store, split, and calculate.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=axmszr)

* **Project Management:**
  * Managed pull request reviews for `v1.1` - `v1.4` (4 milestones) on GitHub.
  * Ensured that the project team is working effectively and efficiently to meet project goals.
  * Facilitated the project’s closure, being the main final project reviewer, ensuring that the project is sufficiently completed with all the appropriate documentation.

* **Team Tasks:**
  * Refactored tags to variants as a follow-up to Markus' overall refactoring.

* **Documentation:**
  * User Guide
    * Added documentation for the `overview` command.
    * Updated the screenshots with the newest GUI.
    * Touched up the formatting, and other general small nit fixes.
  * Developer Guide
    * Added implementation details and UML diagrams for the `overview` command.
    * Updated the entire `Architecture` section, since we refactored and added some classes.

* **Enhancements implemented:**
  * Revamped the GUI as the AB3 GUI did not really suit our needs in terms of tone and aesthetics.

* **Community:**
  * PRs reviewed (with non-trivial review comments): [#68](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/68), [#78](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/78), [#104](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/104), [#259](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/259), [#262](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/262), [#269](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/269), [#280](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/280), [#283](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/283)
