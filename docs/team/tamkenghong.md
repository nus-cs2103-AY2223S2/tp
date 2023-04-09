---
layout: page
title: Tam Keng Hong's Project Portfolio Page
---

## Project: Teaching Assistant Assistant
Teaching Assistant Assistant (TAA) is a desktop app for managing teaching assistant
activities, optimized for use via a Command Line Interface (CLI) while still having
the benefits of a Graphical User Interface (GUI). If you can type fast, TAA can get
your teaching assistant tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

- **Code Contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=TamKengHong&breakdown=true)
- **New Features**
    - **Assignment & Student Submissions**
        - **What it does**:
            - Allows user to easily add or delete assignments.
            - Allows user to easily grade and ungrade student submissions for an assignment.
            - Users can easily preform the operations with just one command.
            - User can view the list of assignments and student submissions easily in the GUI.
- **Enhancements**
    - **Implemented late submissions** ([PR #104](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/104))
        - Allows the user to indicate which student submissions are graded as a late submission.
        - Users can easily see the late submissions in the GUI.
    - **Modified storage to store student's Attendance and Participation info** ([PR #143](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/143))
        - Created InitStorage and CheckValidStorage in AssignmentList class to initialise assignments & 
          student submissions from their submission storage strings.
        - Fixed failing test cases due to the modifications.
- **Bug Fixes**
    - Fixed bug where grading a submission does not update the storage. ([PR #196](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/196))
    - Fixed bug where invalid user input causes error. ([PR #196](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/196))
    - Fixed bug where edited student data is not changed when listing assignments. ([PR #196](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/196))

- **Documentation**
    - **User Guide**
        - Updated feature explanations for the following commands
          ([PR #197](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/197)):
            - `add_asgn`
            - `delete_asgn`
            - `grade`
            - `ungrade`
            - `list_asgn`
    - **Developer Guide**
        - Added MSS and puml diagrams for the following commands ([PR #228](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/228]))
            - `add_asgn`
            - `delete_asgn`
            - `grade`
            - `ungrade`

- **Team-based Tasks**
    - Helped create the logo for TAA.
    - Helped with first draft of design screenshot for TAA app.
