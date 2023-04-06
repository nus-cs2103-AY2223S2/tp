---
layout: page
title: Ou Chuhao's Project Portfolio Page
---

### Project: InternBuddy

InternBuddy provides a 1-stop platform for Computing undergraduates to manage and track their internship applications. It is optimized for typing which Computing undergraduates are comfortable and proficient in, allowing them to fully and efficiently exploit the application’s organisational capabilities.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=derricksaltfish&breakdown=true)

* **New Feature**: Added `copy` command [#130](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/130)
  * What it does: Allows users to copy the information of an internship entry onto the clipboard of the computer.
  * Justification:
    * Uses `SwingUtilities.invokeLater()` to wrap the clipboard code in a `Runnable` object, ensures the clipboard operation to be safe to run from a test or other non-GUI context.

* **Enhancements to existing features**:
  * Update help information for the help box opened by command `copy` and click on the Help button [#59](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/59)
  * Update Load Data feature to fit for InternBuddy [#59](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/59)

**Project Management**
* Reviewed and approved pull requests for merging.
* Testing of InternBuddy releases after each version evolution.

**Documentation**
* Side-Wide settings:
  * Update side-wide settings [#19](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/19)
* User Guide:
  * Initial update of Save data and Load data contents [#28](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/28)
  * Update descriptions for Load data, Edit data and Clearing all Internship entries [#51](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/51)
* Developer Guide:
  * Update of `find` command with inclusion of UML sequence diagrams [#88](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/88) [#90](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/90) [#95](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/95) [#123](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/123)
  * Update set up and get started section [#107](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/107)

**Community**
* PRs reviewed (with non-trivial review comments): [#79](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/79), [#80](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/80)
* Contributed to forum discussions (example: [1](https://github.com/nus-cs2103-AY2223S2/forum/issues/340))
