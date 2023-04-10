---
layout: page
title: Isaac's Project Portfolio Page
---

### Overview

FastTrack is a desktop application to help you keep track of daily expenses, optimised for use via a command line interface (CLI). With this app, you can easily add expenses by category, view a summary of what has been spent in total, by category or for the week. The user interface is intuitive and easy-to-use. Overall, FastTrack aims to speed up the time taken to log expenses, saving valuable time for the user.

### Summary of Contributions

- Code contributed: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=gitsac&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

Enhancements implemented:
- Helped with implementation of `Category` class.
    
    - Defined the `Category` class with its fields. (Pull request [#30](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Agitsac))
    - Edited storage system to accommodate `Category` class. (Pull request [#29](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/29))


- Helped with the implementation of `RecurringExpenseManager` class.

  - Edited storage system to accommodate `Category` class. (Pull request [#95](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/95))


- Implemented `edit` function for all 3 main classes used (`Category`, `Expense` and `RecurringExpenseManager`)

  - Implemented `EditExpenseCommand` along with its necessary helper parser class. (Pull request [#77](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/79))
  - Implemented `EditCategoryCommand` along with necessary parser class. (Pull request [#78](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/78))
  - Implemented `EditRecurringExpenseManagerCommand` along with necessary parser class. (Pull request [#130](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/130))


- Added basic startup data that was adapted for FastTrack's usage. (Morphed from AB3's given sample data) (Pull request [#107](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/107))


- Added functionality that causes list of `Expense` in FastTrack to be sorted by date upon any operations (adding/deleting expenses) (Pull request [#136](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/136))


Contributions to the UG:
- Wrote about features in initial draft and added tables denoting the parameters used as well as simple explanations.

Contributions to the DG:
- Worked on the Implementations portion of the DG.
- Wrote about the Effort section of the DG.
- Sketched multiple sequence diagrams that were translated through PlantUML to be used in the DG.

Contributions to team-based tasks:
- Participated in weekly (sometimes biweekly) meetings to discuss project structure and direction.
- Took part actively in debugging other teammate's issues.
- Fixed several bugs reported from PE Dry Run ([#167](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/167), [#170](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/170), [#179](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/179)).

Review/Mentoring Contributions:
- Reviewed multiple PRs made by teammates ([#101](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/101), [#90](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/90), [#72](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/72)).

Contributions beyond team project:
- Reported bugs for another team during the PE-Dry run (T11-4): HospiSearch.
