---
layout: page
title: Lee Wei Chong Stefan's Project Portfolio Page
---

### Project: Army Information Management System

Army Information Management System (AIMS) is a desktop address book application built to handle the personal information of military personnel. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature**: Added the `undo` feature
  - What it does: allows the user to undo previous modifications done to AIMS.
  - Justification: The user is able to quickly undo his/her mistakes, rather than search through all the contacts to find the incorrect data entry and correct it.
  - Highlights: This command saves the state of AIMS after every **successful** command run -- this way, we do not have to rely on each command implementing its own "undo" capability correctly.

- **New Feature**: Added a command history to allow the user to view and (re-)run any previous commands
  - What it does: When the user presses the up arrow (`↑`), they can view an earlier command run; when the user presses the down arrow (`↓`), they can view a later command run.
  - Highlights: This command history feature synergises well with the `undo` command when the user makes a minor mistake.
    - e.g., If the user edits a person's contact details and makes a typo, he can run `undo` to undo the edit command, use the command history to get back the edit command, correct the typos in the edit command and press enter again.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=rexcyrio&breakdown=true)

- **Project management**:
  - Managed releases `v1.1`, `v1.2` and `v1.3.0` on GitHub

- **Enhancements to existing features**:
  - Updated the `list` command to show the total number of persons in AIMS [\#98](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/98)
  - Updated the `find` command to perform a full text search of all person fields (instead of just the name) [\#84](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/84)

- **Documentation**:
  - User Guide:
    - Added documentation for the `undo` command [\#58](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/58)
    - Added documentation for the command history and updated `find` command [\#105](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/105)

  - Developer Guide:
    - Added documentation for the `undo` command [\#66](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/66)

- **Community**:
  - PRs reviewed (with non-trivial review comments):
    - [\#42](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/42)
    - [\#43](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/43)
    - [\#48](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/48)
    - [\#50](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/50)
    - [\#67](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/67)
    - [\#78](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/78)
    - [\#81](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/81)
    - [\#142](https://github.com/AY2223S2-CS2103T-W10-3/tp/pull/142)
  - Contributed to forum discussions:
    - [\#20](https://github.com/nus-cs2103-AY2223S2/forum/issues/20#issuecomment-1396326000)
    - [\#131](https://github.com/nus-cs2103-AY2223S2/forum/issues/131#issuecomment-1416895604)
  - Reported bugs and suggestions for [CS2103T-W09-3](https://github.com/rexcyrio/ped/issues). Notable ones include:
    - [\#2](https://github.com/rexcyrio/ped/issues/2)
    - [\#4](https://github.com/rexcyrio/ped/issues/4)
    - [\#5](https://github.com/rexcyrio/ped/issues/5)
    - [\#12](https://github.com/rexcyrio/ped/issues/12)
