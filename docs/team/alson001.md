---
layout: page
title: Alson's Project Portfolio Page
---

### Project: RIZZipe

RIZZipe is a desktop recipe book application used for busy cooks who want to track their recipes. The user interacts 
with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=alson001&breakdown=true)

* **New Feature**:
* **Import** ([\#144](), [\#175]())
    * What it does: Allows users to import a JSON file that parses correctly to a RecipeBook
    * Justification: Enables users to quickly and easily add a large number of recipe at once from other sources
        * Implemented a file chooser to let user pick JSON file from their chosen directory
        * Made use of the existing `JsonUtil` class to parse the recipes information
* **Export** ([\#144](), [\#186]())
    * What it does: Allows users to export a JSON file that contains information detailing the current RecipeBook
    * Justification: Enables users to export JSON file to another user or other sources so they can import these recipes
    * with ease
        * Implemented a file chooser to let user a chosen directory to export the file to.
        * Made use of the existing data file to build the JSON file copy

* **Project management**:
    * Managed some CheckStyle for `v1.3` and  Testing for `v1.3` and `v1.4`

* **Enhancements to existing features**:
    * Initial refactoring of `person` folder and `model` package([\#57](), [\#63]())
    * Wrote additional tests to ensure 90%+ coverage for `logic` and `storage` package ([\#252](), [\#255]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `view` [\#29](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/29)
        * Added QuickStart guide
    * Developer Guide:
        * Added user stories for the `add`, `view` features [\#37](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/37)
        * Added UML for `logic` architecture and `import` and `export` features.

* **Community**:
    * PRs reviewed (with non-trivial review comments):
  [\#35](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/35),
  [\#40](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/40)
  [\#43](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/43)
    * * Reported 10 bugs in the Practical Exam dry run: [Bug Report](https://github.com/alson001/ped/issues)

