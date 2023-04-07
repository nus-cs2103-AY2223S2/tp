---
layout: page
title: Ou Chuhao's Project Portfolio Page
---

### Project: InternBuddy

InternBuddy provides a 1-stop platform for Computing undergraduates to manage and track their internship applications. It is optimized for typing which Computing undergraduates are comfortable and proficient in, allowing them to fully and efficiently exploit the application’s organisational capabilities.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: 
My code contributions can be found on:
[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=derricksaltfish&breakdown=true)


## Feature and Enhancements

* **New Feature**: Added `copy` command [#130](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/130)
  * What it does: Allows users to copy the information of an internship entry onto the clipboard of the computer.
  * Justification:
    * Uses `SwingUtilities.invokeLater()` to wrap the clipboard code in a `Runnable` object, ensures the clipboard operation to be safe to run from a test or other non-GUI context.

* **Enhancements to existing features**:
  * Update help information for the help box opened by command `help` and click on the Help button [#59](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/59)
  * Update Load Data feature to fit for InternBuddy [#59](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/59)

**Project Management**
* Reviewed and approved pull requests for merging.
* Testing of InternBuddy releases after each version evolution.

## Documentation

**Documentation**
* Side-Wide settings: 
  * Update site-wide settings in `[JAR file location]\docs\_config.yml` and `[JAR file location]\docs\index.md` [#19](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/19)
* User Guide:
  * Initial update of command `exit`, Save data and Load data contents [#28](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/28)
  * Update descriptions for Load data, Edit data and Clearing all Internship entries [#51](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/51)
* Developer Guide:
  * Update content in common classes and AddressBook related content to InternBuddy [#99](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/99)
  * Update of `copy` command with inclusion of UML sequence diagrams [#205](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/205)
  * Update appendix A content [#104](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/104)

## Others

**Community**
* PRs reviewed (with non-trivial review comments): [#33](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/33) [#204](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/204)
