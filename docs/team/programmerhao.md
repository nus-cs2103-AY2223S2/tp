---
layout: page
title: Hao Zeyu's Project Portfolio Page
---

### Project:Clock-Work

Clock-Work is a desktop application for managing tasks and assignments, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clock-Work can get your assignment management tasks done faster than traditional GUI applications.
Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=programmerhao&breakdown=true)


* **New Feature**:`subsection` command that adds a subsection to a task [#135](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/135)
  * What it does: The command will add a subsection to an existing task. Each task will have a list of subsections.
  * Justification: We wish to allow the user to break down complex tasks into smaller sections to better plan the task.
  * Highlights:
    * Each of the task will be initialised with an empty list of subsections.
    * User can add to each task's subsections using `subsection INDEX n/Name d/Description`.
    * I also created a storage for the subsection, so that now each json file of task has a list of subsections.
    * Each subsection comes with an index for easier reference during deletion.
    * Added tests for the `JsonAdaptedSutask`.
    * Added unit tests for the command.
    * Created relevant classes for the command, such as using parser to handle user input.


* **New Feature**: `remove-subsection` command that removes a subsection from a task [#135](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/135)
  * What it does: Removes a subsection from the list of subsections of a task.
  * Highlights:
    * The user could remove the subsection by specifying the index of the main task followed by the index of the subsection inside.
    * Added unit tests for the command.
    * Created relevant classes for the command, such as using parser to handle user input.


* **Enhancements to existing features**: `find` command enhancements [#65](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/65)
  * What it does: Enables the user to find a task by either matching all inputs or one input.
  * Highlights:
    * The user can now use the `all/` prefix followed by `d/urgent d/homework` so that only a task whose description contains both "urgent" and "homework" will be displayed to the user.
    * I modified parser class to accommodate for the `all/` prefix.


* **Enhancements to existing features**: `add` command enhancements [#52](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/52)
  * What it does: Enables the user to add a task with different names but same descriptions, tags.....
  * Justification: We wish to make task creation more efficient by allowing user to create repetitive tasks such as homework for two subjects in one single command line.
  * Highlights:
    * Change the task in the `AddCommand` to a list of tasks so that multiple tasks can be created for a single command execution.
    * Modified the parser to accept multiple names.
    * Created parser tests to test the success of the modified `AddCommandParser`.


* **Enhancements to existing features**: `edit` command enhancements [#151](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/151)
  * What it does: Allows the user to edit all of `SimpleTask`, `Event` and `Deadline`. Previously the edit only applied to simple tasks.
  * Highlights:
    * previously the edit command only creates a new simple task to replace the old task, now it will accommodate for `Event` and `Deadline`.


* **Documentation**:
  * Contribution to the user guide:
    * Subsection command [#135](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/135)
    * Remove-subsection command [#135](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/135)
  * Contribution to the developer guide:
    * Add a section on `edit` command [#146](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/146)
      * Section includes activity diagram and sequence diagram, as well as the write-up.
    * Add a section on `subsection` command [#146](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/146)
      * Section includes activity diagram and sequence diagram, as well as the write-up.
    * Add a section on `remove-subsection` command [#146](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/146)
      * Section includes activity diagram and sequence diagram, as well as the write-up.
    * Modify the `model`'s class diagram [#146](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/146)
      * The diagram now reflects how each task can contain one or more subsections
    * Add a section on `add` command [#119](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/119)
      * Section includes activity diagram and sequence diagram, as well as the write-up.


* **Community**:
  * PRs reviewed:
    * [#36](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/36)
    * [#61](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/61)
    * [#68](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/68)
    * [#74](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/74)
    * [#138](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/138)
    * [#147](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/147)


* **Tools**:
  * Java 11
  * PlantUML
