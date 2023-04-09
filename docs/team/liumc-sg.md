---
layout: page
title: Liu Muchen's Project Portfolio Page
---

### Project: Trackr

Trackr is a desktop tracking application used to keep track of order, suppliers and tasks. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=liumc-sg&breakdown=true)

* **Contributions to code base**:
  * Enabled assertions in gradle. [\#156](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/156)
  * Updated link in help command. [\#80](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/80)
  * Completed `find` and `delete` command for task, together with the respective descriptor and predicate. [\#98](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/98)
    * Justification: Allows the user to be able to find tasks by multiple fields instead of only the name.
    * Highlights: Was used to make the predicate and command for find command for order.
    * Credit: Code is adapted from [AddressBook-Level3](https://github.com/nus-cs2103-AY2223S2/tp) created by [SE-EDU initiative](https://se-education.org)
  * Completed `clear` command for task. [\#102](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/102)
    * Justification: Allows the user to be able to delete all tasks in one command.
    * Credit: Code is adapted from [AddressBook-Level3](https://github.com/nus-cs2103-AY2223S2/tp) created by [SE-EDU initiative](https://se-education.org)
  * Refactored models and commands, together with abstracting out certain components. [\#138](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/138), [\#139](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/139), [\#187](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/187)
    * Justification: There were a lot of repeated code for commands (such as `add`, `edit` and `delete`) for each model (such as `Supplier` and `Task`) and . Hence, this would reduce duplicate code for similar functionality just for different models.
  * Abstracted out common models (such as `Name` and `Deadline`). [\#138](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/138)
    * Justification: There are repeated use of similar classes with the same functionality. Hence, this reduces duplicate code.
  * Add in missing test cases. [\#283](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/283)
    * Justification: Improve test coverage 

* **Contributions to the User Guide**:
  * Drafted initial documentation for `tab`, `delete_task`, `delete_supplier`, `find_task`, `find_supplier`, `edit_task`. [\#47](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/47)
  * Drafted initial documentation for `add_supplier`, `edit_supplier`. [\#72](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/72)
  * Drafted initial documentation for `list_supplier`, `list_order`, `list_task`, `list_item`, `clear_supplier`, `clear_order`, `clear_task`, `clear_item`, `find_order`. [\#198](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/198)
  * Overhaul of the structure of how the User Guide is designed and implemented. [\#267](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/267)
  * Added in figures and tips to allow for better user experience. [\#267](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/267)
  * Fix formatting and informational errors in the entire User Guide. [\#267](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/267)

* **Contributions to the Developer Guide**:
  * Added UML diagrams and wrote the content for `Model`, `Item`, `Person-Supplier-Customer`, `Task` and `FindXYZCommand`. [\#150](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/150)
  * Updated diagrams to svg for better readability and quality, together with labelling of the diagrams. [\#157](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/157), [\#166](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/166)
  * Updated target user profile, user stories and use cases. [\#166](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/166)
  * Added manual test cases. [\#294](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/294)

* **Review/Mentoring contributions**:
  * Provided recommendations on how to implement respective features.
  * Help teammates with issues with their code base.

* **Project management**:
  * Initialised GitHub Project, issues tracking and flow of PRs.
  * Lead weekly meetings and work delegation.
  * Keep track of datelines and give timely reminders.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#89](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/89), [\#97](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/97), [\#105](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/105), [\#120](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/120)

* **Tools**:
  * Java 11, JavaFX, Grade, JUnit 
