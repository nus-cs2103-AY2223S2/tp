---
layout: page
title: Weng Kit's Project Portfolio Page
---

### Project: PetPal

PetPal is your **furry and dependable assistant** who **reminds you of deadlines** and **consolidates crucial information** like pet names, tags dietary needs all in an **aesthetically pleasing** GUI! The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**Cost Feature**: Added an cost indication feature
  * Justification: Owners have a lot of customers, this simple feature helps owners know how much money is due to them by each customer.
  * Highlights: Cost indication is updated on click, and on runtime based on a rate, and flat charge my teammate implemented
  * PRs : [#43](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/43)
  * Credits:

* **Undo Feature**: Added undo feature
  * Justification: Adding and deleting pets off list take a lot of time, an undo feature lets users undo commands they may have done on accident, such that they do not lose progress.
  * Highlights: Undo works with archive command as well.
  * PRs :  [#48](https://github.com/AY2223S2-CS2103T-T14-2/tp/pull/48)
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=wengkit1&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * [Issues](https://github.com/AY2223S2-CS2103T-T14-2/tp/issues?q=is%3Aissue+author%3Awengkit1+is%3Aclosed) added by me

* **Enhancements to existing features**:
  * Changed previous commands from AB3 to suit PetPal's needs as well as added functionality to include adding and editing timestamps.
* **Documentation**:
  * User Guide:
    * Added documentation for the features of cost indication and `undo`
  * Developer Guide:
    * Changed UML diagrams of the following diagrams :
      * ArchitectureSequenceDiagram
      * UiClassDiagram
      * LogicClassDiagram
    * Added UML diagrams :
      * UndoSequenceDiagram
      * UndoActivityDiagram
