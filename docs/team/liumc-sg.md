---
layout: page
title: Liu Muchen's Project Portfolio Page
---

### Project: Trackr

Trackr is a desktop tracking application used to keep track of order, suppliers and tasks. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=liumc-sg&breakdown=true)

* **Contributions to code base**:
  * Enabled assertions in gradle.
  * Refactored models and commands, together with abstracting out certain components.
    * Justification: There were a lot of repeated code for commands (such as `add`, `edit` and `delete`) for each model (such as `Supplier` and `Task`) and . Hence, this would reduce duplicate code for similar functionality just for different models.
  * Abstracted out common models (such as `Name` and `Deadline`).
    * Justification: There are repeated use of similar classes with the same functionality. Hence, this reduces duplicate code.
  * Completed find and edit command for task, together with the respective descriptor and predicate.
    * Justification: Allows the user to be able to find tasks by multiple fields.
    * Highlights: Was used to make the predicate and command for find command for order.

* **Contributions to the User Guide**:
  * Overhaul of the structure of how the User Guide is designed and implemented.
  * Added in figures and tips to allow for better user experience.
  * Fix all formatting and informational errors in the entire User Guide.

* **Contributions to the DG**:
  * Updated diagrams to svg for better readability and quality, together with labelling of the diagrams.
  * Added UML diagrams and wrote the content for `Model`, `Item`, `Person-Supplier-Customer`, `Task` and `FindXYZCommand`.
  * Updated target user profile, user stories and use cases.

* **Review/Mentoring contributions**:
  * Provided recommendations on how to implement respective features.
  * Help teammates with issues with their code base.

* **Project management**:
  * Initialised GitHub Project, issues tracking and flow of PRs.
  * Lead weekly meetings and work delegation.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#89](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/89), [\#97](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/97), [\#105](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/105), [\#120](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/120)
