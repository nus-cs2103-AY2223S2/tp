---
layout: page
title: Nicholas's Project Portfolio Page
---
### Overview

FastTrack is a desktop application to help you keep track of daily expenses, optimised for use via a command line interface (CLI). With this app, you can easily add expenses by category, view a summary of what has been spent in total, by category or for the week. The user interface is intuitive and easy-to-use. Overall, FastTrack aims to speed up the time taken to log expenses, saving valuable time for the user.

### Summary of Contributions

- Code contributed: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=nicleejy&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)


Enhancements implemented:
- Implemented new category autocomplete feature which allows users to autocomplete category names from a list of suggestions using arrow/enter/tab keys (Pull request [#148](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/148))
- Added new UI screen for recurring expense feature (Pull request [#145](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/145))
- Implemented the expense summary statistics feature (Pull request [#111](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/111))
  - Added new UI section for summary statistics data 
  - Implemented new `AnalyticModelManager` class to manage expense data state
  - Utilised Observer Pattern to integrate expense statistics data into `StatisticsPanel` UI component, ensuring statistics are updated in real time 
- Implemented add expense feature  (Pull request [#72](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/72))
  - Implemented `AddExpenseCommand` which encapsulates the command request details
  - Added `ExpenseCommandParser` containing various parser methods to parse dates and prices to interpret the command
- Added sample data for recurring expenses (Morphed from AB3's given sample data) (Pull request [#224](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues/224))

Contributions to the UG:
- Wrote introduction section for the initial draft of the UG (Pull request [#124](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/124))
- Updated second draft of UG (Pull request [#213](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/213))
  - Update all feature sections
  - added FAQ section
  - Added annotated diagrams for each command and GUI walkthrough

Contributions to the DG:
- Add expense summary feature and implementation details to DG (Pull request [#110](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/110))
- Sketched and implemented Activity Diagrams in PlantUML for recurring expense (Pull request [#218](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/218))
- Add writeup for autocompletion feature in the DG (Pull request [#218](https://github.com/AY2223S2-CS2103T-W09-2/tp/pull/218))

Contributions to team-based tasks:
- Participated in weekly (sometimes biweekly) meetings to discuss project structure and direction.
- Took part actively in debugging other teammate's issues.
- Fixed [several bugs](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Anicleejy+pe-d) reported from PE Dry Run

Review/Mentoring Contributions:
- Reviewed [23 PRs](https://github.com/AY2223S2-CS2103T-W09-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me) made by teammates

Contributions beyond team project:
- Reported bugs for another team during the PE-Dry run (F10-1): OfficeConnect.
