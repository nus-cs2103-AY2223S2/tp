---
layout: page
title: Jacques Liao Project Portfolio Page
---

### Project: Tutee Managing System (TMS)

Tutee Managing System (TMS) is a **desktop application designed for private tutors managing students, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). TMS utilizes your fast typing ability to execute your management tasks faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to filter fields.
  * What it does: allows the user to filter and display tutees by specifying fields and their prefix. (name, phone, email, address, subject, schedule, start time, end time, tags)
  * Justification: This feature improves the product significantly because a user is able to quickly locate and display tutees and their relevant information when certain field(s) are given.
  * Highlights: The implementation of `FilterCommand` was adapted from `EditCommand` as it required users to specify a prefix of the field together with an input. It takes in a predicate, `FieldContainsKeywordPredicate` which was also adapted from `NameContainsKeywordPredicate` that `FindCommand` uses except that it is enhanced so that it tests every field instead of just the name of the tutee.
  * Credits: Solution adapted from [filter-command](https://github.com/AY2223S2-CS2103T-W10-3/tp/blob/master/src/main/java/seedu/address/logic/commands/FilterCommand.java). I read the code to understand the approach before implementing it myself with the fields customized for the Tutee Managing System.

* **Code contributed**: [link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jugsliao&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancements to existing features**:
  * Added subject, schedule, start time, end time and remark field to the tutee model.
  * Modified add, edit and filter command to accept these new fields.
 
* **Documentation**:
  * Readme.md
  * UserGuide.md
    * Added documentation for the `filter` feature.
    * Added documentation for the new `subject`, `schedule`, `start time`, `end time` and `remark` field.
  * DeveloperGuide.md
    * Added implementation detail of the `filter` feature.
    * Updated the appendix to include more details.

* **Community**:
  * Reported bugs for other teams during PE-Dry run
