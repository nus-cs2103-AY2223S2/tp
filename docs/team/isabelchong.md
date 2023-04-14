---
layout: page
title: Isabel Chong's Project Portfolio Page
---
### Project: CLIpboard

## Overview
CLIpboard is a desktop application that helps educators, especially those that tutor multiple classes, by managing their students’ particulars in an organised manner.

## Summary of Contributions

### Code Contributed
View my code contribution here: [Code Dashboard](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=isabelchong&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements Implemented
* Implemented initial change of `add` command in v1.2 ([#47](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/47),
  [#74](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/74),
  [#59](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/59))
    * Updated `AddCommand`, `EditCommand` and `Student` class to include `module` field
* Implemented features of assigning grades ([#133](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/133),
    [#142](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/142),
    [#264](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/264))
    * Created classes `Task`, `UniqueTasksList` and `SerializedTask`
    * Implemented commands under the `Task Page` and `Grades Page` - `task`, `add task`, `edit task`, `delete task`
      and `assign`
    * Enhanced existing code with test cases - `TaskCommandTest` and `AssignCommandTest`
* Implemented custom `HelpCommand` to have customised help windows for the five pages on CLIpboard ([#161](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/161))

### Contributions to User Guide (UG)
* Handled the initial refactoring for v1.2
* Contributed to UG for v1.4 ([#141](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/141),
  [#239](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/239),
  [#266](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/266),
  [#267](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/267))
  * Added in the user instructions for commands under `Task Page` and `Grades Page`
  * Added in guiding examples under `Navigation Guide` and the description of elements under `Visual Guide`
* Helped to review PRs about UG [here](https://github.com/AY2223S2-CS2103T-T15-4/tp/pulls?q=is%3Apr+commenter%3A%40me+UG+-author%3A%40me)

### Contributions to Developer Guide (DG)
* Contributed to the initial draft for v1.2 [#24](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/24)
  * Handled the `Appendix: Requirements` section
* Contributed to DG for v1.4 ([#221](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/221),
  [#254](https://github.com/AY2223S2-CS2103T-T15-4/tp/pull/254))
  * Refactored mentions of `AB3` to `CLIpboard`, including descriptions and links
  * Added instructions for `Manual Testing` section for the following commands or sections - `Launch and Shutdown`,
    `General Commands`, `Course Page Commands`, `Group Page Commands`, `Task Page Commands`, `Grades Page Commands`,
    `Saving Data`
* Helped to review PRs about DG [here](https://github.com/AY2223S2-CS2103T-T15-4/tp/pulls?q=is%3Apr+commenter%3A%40me+DG+-author%3A%40me)

### Contributions to Team-Based Tasks
* Handled setting up of `codecov`

### Review/Monitoring Contributions
* Reviewed [PRs](https://github.com/AY2223S2-CS2103T-T15-4/tp/pulls?q=is%3Apr+commenter%3A%40me) on code and
  documentation quality

### Contributions Beyond the Project Team
* Participated in PE-D bug-catching exercise, which consists of reviewing and testing another teams' project. You can
  view the issues found [here](https://github.com/AY2223S2-CS2103-F11-1/tp/issues?q=Tester+B+).
