---
layout: page
title: BagDownB3nny's Project Portfolio Page
---

### Project: Ultron

Ultron is an application to help students track their internship applications as openings. It is able to keep track of the details of an application and associated deadlines.

* [Code contributed](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=bagdownb3nny&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Code refactored**
  * Delete command
  * Clear command
  * Storage
  * Test common and util files. This was an important refactoring that took a while since the file was large. It allowed the rest of the team to refactor the other test files as many test files made use of the common and util test files.
  * Logic test files. This took a long time due to the amount of commands and parsers that have to be tested.

* **Bugs fixed**
  * Storage not working for our new model. This bug was tricky to find since we did not know which part of our model was causing our storage to not save. The bug was also tricky to fix due to my unfamiliarity with Jackson
  * Clear command not refreshing UI
  * Delete command not refreshing UI
  * Case sensitive position and company names

* **Enhancements implemented**:
  * Improvement to success message shown by add and edit
  * Better UI for show panel. This change was important in improving the look of our app. Before the fix, text did not wrap, and multiple deadlines were touching each other.
  * Better UI for status message

* **User Guide Contributions**:
    * Clear command usage

* **Developer Guide Contributions**:
    * Use cases
    * Non-functional requirements
    * Glossary
    * Manual testing

* **Team based tasks**:
    * Informal demo of V1.2
    * Informal demo of V1.3

* **Contributions beyond project team**:
  * Found 7 high priority bugs during the smoke catcher test session

* **Review Contributions**:
  * [About us page](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/4)
  * [Refactoring into Ultron](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/84)
  * [Find command refactor and user guide](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/78)
  * [New edit parser](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/77)
  * [Refactor into keydate](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/76)
  * [Refactor into opening model](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/64)
  * [Readme and PPP](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/12)
  * [DG delegation](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/194)
  * [PED UG mistakes and phrasing errors](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/192)
  * [Making upcoming sort](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/138)
  * [UG update](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/132)
  * [Model tests](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/127)
  * [Enabling assertions on Gradle](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/116)
  * [Making remarks optional](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/110)
  * [Remark UI fix](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/94)
  * [Find command fix](https://github.com/AY2223S2-CS2103T-F12-4/tp/pull/93)
