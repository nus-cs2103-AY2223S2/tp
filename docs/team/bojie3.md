---
layout: page
title: Xu BoJie's Project Portfolio Page
---

## Project: Teaching Assistant Assistant
Teaching Assistant Assistant (TAA) is a desktop app for managing teaching assistant
activities, optimized for use via a Command Line Interface (CLI) while still having
the benefits of a Graphical User Interface (GUI). If you can type fast, TAA can get
your teaching assistant tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

- **Code Contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=bojie3&breakdown=true)
- **New Features**
  - **Attendance and Participation**
    - **What it does**: Allows users to include attendance and participation points for their students.
      - This feature allows user to easily mark, unmark attendance and allocate participation points
      - User can view the overall attendance and average participation points easily in the GUI
- **Enhancements**
  - **Updated help command UI to be more comprehensive** ([PR #140](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/140))
    - Included a summary of TAA features in the help command window
    - Updated the URL from AB3 to TAA's userGuide
    - Changed UI aspects of the help window
  - **Modified storage to store student's Attendance and Participation info** ([PR #85](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/130))
    - Updated the JsonAdaptedStudent to include student information such Attendance and participation
    - Fixed failing test cases due to the modification of student class
- **Bug Fixes**
  - Fixed bug where invalid attendace/participation value in JSON causes error in GUI startup ([PR #213](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/213))
  - Fixed bug where invalid user input casues error ([PR #213](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/213))
  - Fixed bug where user cannot add valid participation points ([PR #213](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/213))

- **Documentation**
  - **User Guide**
    - Updated feature explanations for the following commands
      ([PR #197](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/197)):
      - `markAtd`
      - `unmarkAtd`
      - `listAtd`
      - `insertPP`
      - `listPP`
  - **Developer Guide**
    - Added MSS and puml diagrams for the following commands
      - `markAtd/unmarkAtd` ([PR #117](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/117))
      - `insertPP`

- **Team-based Tasks**
  - Helped with discussions on project ideas and core features 
