---
layout: page
title: Nguyen Viet Anh's Project Portfolio Page
---

# Project: Vimification

**Vimification** is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). It is written in Java, and the GUI is created with JavaFX.

# Contributions

Given below are my contributions to the project:

### Code contribution

* [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=vietanh1010&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Features implemented

#### Parsers

* Ported the parsers used in iP to tP, and added some enhancement to theirs core implementation.
* The parsers are written in a _declarative style_, instead of using _regex_ - this improves maintainability as the requirements constantly change during different stages of the project, and _regex_ is not easily modifiable for new changes.
* Implemented the command parsers using basic parser combinators, and combined them to create more powerful parsers. The parser used in the application is implemented by combining all parsers used for different commands.

#### Macro-related commands

* Allows the user to define, list and delete macros.
* These commands are extremely powerful (and can be considered the selling points of the application). They significantly increase the speed of using the application.
* This feature also interacts nicely with weekly deadlines - the user can reuse the same macro to create new data every week.

#### Undo command

* Allows the user to undo the previous command that modifies the underlying data of the application.
* This feature provides a convenient way to rectify mistakes in commands.

### Enhancements implemented

#### Atomic execution of commands

* A command that modifies the underlying data of the application should succeed or fail entirely without making any modification. It must not leave the data in an intermediate (and potentially invalid) state.
* Refactored the command classes written by **DerenC** and **liujiayue314**.

#### Data modification after filtering and sorting data

* In **v1.3**, after filtering and sorting displayed data, commands that modify the underlying data do not work until `refresh` command is executed.
* This is a huge flaw. I reimplemented `TaskList` with some new interfaces to fix it.

#### Redesigning the project's architecture

* The initial project's architecture (from **AB3**) is high in coupling and low in cohesion - this is shown by the API structure of `Model`, where both data management and ui management are put in the same class.
* Redesigned the project's architecture for a clearer barrier between pure business logic and ui management, and separated implementation details from higher-level API.

#### Testing

* Improved and wrote more testing, for better coverage.

#### Tooling

* Set up GitHub actions to be suitable for the team's workflow.
* Set up CodeCov to keep track of testing coverage.

### Contribution to the UG

### Contribution to the DG

### Contribution to team-based tasks

* Managed the team's GitHub repo and general workflow. Changed from forking workflow to feature branching workflow in **v1.3**.
* Helped resolve merge conflicts between team members to ensure a working code base on `master` branch.
* Created milestones, reminded team members to create issues and pull requests as per the requirement of the module.

### Review/mentoring contributions

* Reviewed multiple pull requests: [#41](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/41), [#44](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/44), [#60](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/60), [#70](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/70), [#90](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/90), etc.

* Answered issues: [#49](https://github.com/AY2223S2-CS2103T-T15-3/tp/issues/49), [#68](https://github.com/AY2223S2-CS2103T-T15-3/tp/issues/68), [#87](https://github.com/AY2223S2-CS2103T-T15-3/tp/issues/87), [#95](https://github.com/AY2223S2-CS2103T-T15-3/tp/issues/95), etc.

* Gave suggestions: [#44](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/44), [#70](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/70), [#90](https://github.com/AY2223S2-CS2103T-T15-3/tp/pull/90), [#95](https://github.com/AY2223S2-CS2103T-T15-3/tp/issues/95), etc.

### Contribution beyond the project team:

* Answered questions on the forum: [#294](https://github.com/nus-cs2103-AY2223S2/forum/issues/294)
