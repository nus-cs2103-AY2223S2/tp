---
layout: page
title: Jacques Liao Project Portfolio Page
---

### Project: tuition center admin managing system (TCAMS)

Tuition center admin managing system (TCAMS) is a desktop application designed for admins working at tuition center managing courses, tutors and students, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). TCAMS utilizes your fast typing ability to execute your management tasks faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to filter fields,
  * What it does: allows the user to filter and display tutees by specifying fields and their prefix. (name, phone, email, address, subject, schedule, start time, end time, tags)
  * Justification: This feature improves the product significantly because a user is able to quickly locate and display tutees and their relevant information when a certain field is given.
  * Highlights: The implementation of `filterCommand` was inspired from `editCommand` as it required users to specify a prefix of the field together with an input. The `fieldContainsKeywordPredicate` was also inspired from the `nameContainsKeywordPredicate` that findCommand uses except that it accepts every field instead of just the name of the tutee.

* **Code contributed**: [link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jugsliao&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * To be added

* **Enhancements to existing features**:
  * Added subject, schedule, start time, end time and remark field to a tutee.
  * Modified add, edit and filter command to accept these new fields.
 
* **Documentation**:
  * Readme.md
  * UserGuide.md
    * Added documentation for the `filter` feature
  * DeveloperGuide.md
    * Added implementation detail of the `filter` feature

* **Community**:
  * Reported bugs for other teams during PE-Dry run

* **Tools**:
  * to be added
