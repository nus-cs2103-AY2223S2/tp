---
layout: page
title: He Shuimei's Project Portfolio Page
---

### Project: PetPal

PetPal is a desktop pet management application used for teaching Software Engineering principles.
The user interacts with it using a CLI, and information is displayed on a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

The main target users for PetPal are pet daycare owners who have a large pet clientele to manage and provides
an open-source, free-to-use application for them to do so.

PetPal can also be scaled to target users such as pet shelters, pet shops, pet walkers and more!

Given below are my contributions to the project.

* **New Feature**: Added the ability to archive pets
    * What it does: Moves the pet data to another save file (archive.json) and removes it from the active pet list (petpal.json)
    * Justification: Business owners might have older inactive customers that they wish to keep a record of, while also removing from their current active client list to not clutter the list.
    * Highlights: Archive feature works with the undo feature, which can restore the pet data to the current pet list
    * PR: [#36](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/36), [#47](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/47), [#65](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/65)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shuimeihe&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
    * Contributed functional code for Archive Command and other bug fixes
    * Contributed documentation code for User Guide and Developer Guide
    * Contributed test codes for Archive Command and other java classes

* **Project management**:
    * Managed release `v1.3` on GitHub


* **Enhancements to existing features**:
    * Added shortcut words for new and existing commands (PR: [#73](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/73))
    * Wrote additional tests for new commands (PR: [#36](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/36), [#150](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/150))


* **Documentation**:
    * User Guide:
        * Added documentation for the features `archive`, `undo`, `changecost`, `edit`
        * Updated shortcut words for relevant features, `changecost`, `list`, `help`, `find`
        * Contributed to the introduction and command summary
        * General formatting and documentation reading enhancements
          * Improving language use
          * Adding alert boxes for important information
          * Proofreading
    * Developer Guide:
        * Added documentation for the features `archive`
        * Added documentation for other future proposed features
        * Added brainstormed user stories
        * Added use cases
        * General formatting and proofreading
    * Index:
        * Updated the index to introduce PetPal to potential users.
        * Designed and created the logo for PetPal [here](https://ay2223s2-cs2103t-t14-2.github.io/tp/images/UI/logo2-alt.png) and [here](https://ay2223s2-cs2103t-t14-2.github.io/tp/images/UI/logo2.png) in Adobe Illustrator
          * Credits: The logo used references the [Dog icons created by Flat Icons - Flaticon](https://www.flaticon.com/free-icons/dog)

* **Team contribution**:
    * Setting up tP organization & team repo
    * Contributed to the postmortem of iteration v1.2

* **Community contribution**:
    * Participated in the PED and helped bug test for another team's product [Pied Piper](https://github.com/AY2223S2-CS2103T-W15-3/tp/issues) and UG as Tester D
