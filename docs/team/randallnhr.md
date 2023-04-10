---
layout: page
title: Randall's Project Portfolio Page
---

### Overview

FastTrack is a desktop application to help you keep track of daily expenses, optimised for use via a command line interface (CLI). With this app, you can easily add expenses by category, view a summary of what has been spent in total, by category or for the week. The user interface is intuitive and easy-to-use. Overall, FastTrack aims to speed up the time taken to log expenses, saving valuable time for the user.

### Summary of Contributions

**Code contributed:** 

The following [link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=randallnhr&breakdown=true&sort=groupTitle+dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs%7Efunctional-code%7Etest-code%7Eother) is my code contribution.

#### **Enhancements implemented:**
* Implemented commands for `Category`
  * `addcat` - allows users of FastTrack to add a new `Category` into FastTrack. (PR [#68](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/68))
    * Allow users to add category without summary (PR [#118](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/118))
  * `delcat` - allows users to delete an existing `Category` in FastTrack. (PR [#68](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/68)) 
    * Expenses with the deleted category will have its category replaced with the `MiscellaneuosCategory`. (PR [#109](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/109))
  * `lcat` - allows users to list all added `Category`, used to determine index for edit and delete category commands. (PR [#68](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/68))
  * `sumcat` - allows users to view category summary. (PR [#119](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/119))
* Implemented `CLEAR` command. (PR [#120](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/120))
  * What it does: Wipes the storage of FastTrack to a clean slate. This is useful when the user first opens FastTrack and wants to delete the sample data.
* Implemented `Budget` class and linked it to the UI to update statistics. (PR [#138](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/138))
  * What it does: Allows users to add a monthly budget into FastTrack. This is used in conjunction with the Statistics feature to allow users to have an easy way to see how much of the budget has been utilised.
* Implemented commands for `RecurringExpenseManager`
  * `addrec` - allows users to add `RecurringExpenseManager` objects into FastTrack. (PR [#140](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/140))
  * `delrec` - allows users to delete a `RecurringExpenseManager` object. (PR [#140](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/140))

#### **Contributions to the UG:**
* Added command summary for:
  * Expense commands
  * Category commands
  * General commands

#### **Contributions to the DG:**
* Added several use cases. (PR [#37](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/37))
* Added purpose of the guide, how to use this guide and acknowledgement. (PR [#209](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/209))
* Added Recurring Expense implementation. (PR [#222](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/222))
* Added Budget implementation and linked to Statistics implementation. (PR [#222](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/222))
* Added writeup for category features: (PR [#222](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/222))
  * Adding a category
  * Deleting a category
* Created sequence diagrams for `set` and Recurring Expense feature. (PR [#235](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/235))
* Created PlantUML diagrams for high level architecture and class diagrams. (PR [#249](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/249))
#### **Contributions to team-based tasks:**
* Organised weekly meetings to discuss project structure and direction. 
* Took part actively in debugging other teammate's issues.

#### **Review/Mentoring Contributions:**
* Reviewed several [PRs](https://github.com/AY2223S2-CS2103T-W09-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me) made by teammates.

#### **Contributions beyond team project:**
