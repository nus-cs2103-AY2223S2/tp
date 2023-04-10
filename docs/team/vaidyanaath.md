---
layout: page
title: Suresh Vaidyanaath's Project Portfolio Page
---

# Project: Calidr

### Overview
Calidr is a time-management and scheduling calendar application, optimized for use via a Command Line Interface (CLI)
while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

### Contributions
* **New Command**: `edit`
  * *What it does*: allows the user to edit the fields of a task including the title, associated datetime, description,
    location, priority, tags.
  * *Justification*: This feature is integral to the core functionality of the product because when there are changes to the task information, the app should provide a convenient way to modify them.
  * *Highlights*: It was challenging in the beginning since it required changes to be made in several components of the codebase.
  * *Credits*: The code was based on AB3 Address-book's `editCommand`, especially the decision to have a `EditTaskDescriptor` class that holds the modified information.

* **New Commands**: `mark` and `unmark`
  * *What it does*: allows the user to mark the completion status of each task as *done* or *not done*.
  * *Justification*: This feature is integral to the core functionality of the product because when the user completes any task, the app should provide a convenient way to track and edit its completion status.
  * *Credits*: The code was built upon flash161203's implementation of `mark` and `unmark`.

* **New Feature**: Added optional fields to tasks.
  * What it does: allows the user to add an optional description, location, and tags.
  * Justification: This feature improves the product significantly because a user might want to store extra information about the task.
  * Highlights: Each optional field added affects existing commands and possibly new commands to be added in the future.
  
* **Other Enhancements**: Refactor Model and Logic Components to integrate TaskList.

* **Code Contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=vaidyanaath&breakdown=true)
* **Documentation**
  * Contributions to the UG: 
    * Added information on *how to use this guide* section.
    * Added documentation for features `edit`, `mark`, `unmark`.
  * Contributions to the DG:
    * Added implementation details of `edit`, `mark`, and `unmark` commands.
* **Community**
  * PRs authored (with non-trivial comments): [\#44](https://github.com/AY2223S2-CS2103T-W10-2/tp/pull/44), [\#51](https://github.com/AY2223S2-CS2103T-W10-2/tp/pull/51), [\#68](https://github.com/AY2223S2-CS2103T-W10-2/tp/pull/68), [\#70](https://github.com/AY2223S2-CS2103T-W10-2/tp/pull/70) 
