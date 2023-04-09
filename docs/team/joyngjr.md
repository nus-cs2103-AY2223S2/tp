---
layout: page
title: Joy Ng Jing Ru's Project Portfolio Page
---

### Project: Clock-Work

Clock-Work is a desktop application for managing tasks and assignments, optimised for use via a Command Line Interface
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clock-Work can get your
assignment management tasks done faster than traditional GUI applications.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=joyngjr)
<br>
* **Refactoring** Added `Effort` component into Task [#85](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/85)
  * What it does: Effort serves as an estimate for students to gauge the amount of work required to complete a task.
  * Highlights:
    * Created classes for `Effort` and integrated it into TaskBook, refactoring relevant files and components as needed.
    * Modify UI of a Task so that effort for each task can be seen.
    * Modify `add` command and `edit` command to support `Effort`.
    * Add relevant unit testing for new classes.
<br>
* **New Feature** Added `Schedule` feature [#86](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/86)
  * What it does: `Schedule` creates a 30-day work plan for students based on tasks they entered in Clock-Work.
  * Highlights:
    * Created relevant classes for `Schedule`, from handling user input to data manipulation in storage.
    * Created `Plan` command and linked it to automatically run with `Schedule` as needed.
    * Created new Json files and storage classes `JsonPlannerStorage`, `JsonSerializablePlanner`, etc. to store generated plans so users can reload them.
    * Add relevant unit testing for newly created classes and integration testing for newly created storage.
<br>
* **New Feature** Added `Stats` feature [#63](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/63)
  * What it does: `Stats` displays the number of tasks for tag has in descending order, up to a maximum of 10.
  * Highlights:
    * Created relevant classes for `Stats`.
    * Add relevant unit testing for new classes.
<br>
* **Enhancements Implemented** Increased capability of `Delete` Feature [#46](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/46)
  * Original: Only allow deletion at specified index.
  * Current: Allow `Delete` to delete tasks from multiple indices at once.
  * Highlights:
    * Create new `IndexList` class to support feature.
    * Add relevant unit testing for new features.
<br>
* **Enhancements Implemented** Extend capability of `Find` command to support find by `Effort` [#150](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/150)
  * Original: `Find` by name, description, tag, deadline, from, to
  * Current: `Find` by name, description, tag, deadline, from, to, and effort
<br>

* **Documentation**:
    * User Guide:
        * Add pre-amble in introduction [#142](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/142)
        * Add feature introduction [#142](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/142)
        * Add 'A Task in Clock-Work' section [#156](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/156)
        * Wrote feature guide for `Stats` command.
        * Wrote feature guide for `Schedule` command.
        * Update feature guide for `Delete` command.
        * Re-organise command summary table
        * Add glossary section.
<br>
    * Developer Guide:
        * Add section on `Stats` command [#138](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/138)
          * Section consists of: Write-up, Sequence diagram, Activity diagram
        * Add section on `Schedule` command [#145](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/145)
          * Section consists of: Write-up, Sequence diagram, Activity diagram
        * Add section on `Delete` command [#113](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/113)
          * Section consists of: Write-up, Sequence diagram, Activity diagram
        * Add MSS for `Delete` command [#145](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/145)
        * Add planned enhancements on empty planner file error handling
        * Update class diagram for Storage to include Planner component [#152](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/152)
<br>
* **Project management**:
  * PRs reviewed (with non-trivial comment reviews): [#48](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/48), [#51](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/51), [#53](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/53), [#118](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/118).
  * Product demo slides for v1.2 and v1.3
  * Update CI link in README.md
  * Bug fixes: [Duplicate names](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/124), [Invalid event timings](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/134), [Integer overflow](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/200)
<br>
* **Community**:
  * Reported [bugs and suggestions](https://github.com/joyngjr/ped/issues) another team.
