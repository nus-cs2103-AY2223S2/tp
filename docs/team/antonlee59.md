---
layout: page
title: antonlee59's Project Portfolio Page
---

### Project: Ultron

Ultron is a system which helps students keep track of their internship applications by helping them
sort their internships and check the status of each application.

Given below are my contributions to the project.

* **Features**:
    * Upcoming feature (sorting element)
      - Created a custom Date Comparator to correctly implement the sorting aspect of upcoming
      - Modified the original FilteredList abstraction into a more flexible and versatile ObeservableList to support the sorting aspect of the Openings
    * Refactored the `Find` feature
      - Implemented 2 custom predicate classes to filter the ObservableList accordingly
      - Supported the filtering of Company and Positions
    * Merged the various refactorings
      - Combined all the different parts of the refactored code for the team
      - Created various new classes such as ModelManager, OpeningList etc.
    * Created custom error messages for the `Add` Command

* **Code contributed**:
  * You can find the report [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=antonlee&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
    * Documented the refactored Find Command
