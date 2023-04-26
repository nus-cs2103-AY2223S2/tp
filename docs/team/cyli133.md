---
layout: page
title: Li Chengyue's Project Portfolio Page
---

## Project: Teaching Assistant Assistant
Teaching Assistant Assistant (TAA) is a desktop app for managing teaching assistant
activities, optimized for use via a Command Line Interface (CLI) while still having
the benefits of a Graphical User Interface (GUI). If you can type fast, TAA can get
your teaching assistant tasks done faster than traditional GUI apps.

### Summary of Contributions
- **Code Contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cyli133&breakdown=true)
- **New Features**
    - **Alarm Scheduling** ([PR #137](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/137))
        - **What it does**:
          - Allows users to schedule alarms that will ring at the scheduled time.
          - This feature greatly helps the TAs to manage their time during class, which is an existing problem faced
            by many TAs.
          - Users can create alarms with specified time and comment, upon time-up the alarm will sound off,
            with a notification window popping up.
          - Users can delete scheduled alarm as well. Upon deletion, the alarm deleted will no longer ring.
- **Enhancements**
    - **Refactored AddressBook Class to ClassList class** ([PR #75](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/75))
        - Implemented unique class lists.
        - Adapted the Student class to become the Tutor class.
        - Made the Student class compatible with the Tutor class and the ClassList class.
        - Tweaked all failing tests to pass with the new `Tutor` `ClassList` classes.
    - **Allowed multiple ClassLists to be supported** ([PR #94](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/94))
        - Implemented the create class list command.
        - Modified the default storage loader to correct load demo data into the class list.
        - Allowed users to list students in particular class using the `classlist` command.
        - Implemented helper functions to be used for adding/deleting students from a specified class.
        - Tweaked the UI to load students from specified class.
- **Bug Fixes**
    - Fixed bug where `add_alarm` does not report errors properly for negative or non-numeric input.
      ([PR #212](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/212))
    - Fixed bug where  `create_class` does not behave as expected when optional student names is added.
      ([PR #212](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/212))
    - Fixed bug where `add_alarm`, no error thrown when parameters are empty.
      ([PR #212](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/212))

- **Documentation**
    - **User Guide**
        - Added feature explanations for the following commands
          ([PR #60](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/60)):
          - `list`
          - `find`
          - `add_alarm`
          - `delete_alarm`
          - `list_alarms`
    - **Developer Guide**
        - Added MSS for the following commands
          ([PR #118](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/118)):
            - `list`
            - `find`
            - `create_class`
        - Added sequence diagrams and MSS for the following commands:
          ([PR #218](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/218)):
            - `add_alarm`
            - `delete_alarm`
            - `list_alarms`
- **Team-based Tasks**
  - Suggested the idea of Teaching Assistant Assistant
  - Suggested the features of csv import and alarms, apart from the other main features.
