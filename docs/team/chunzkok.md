---
layout: page
title: Kok Chun Zhi's Project Portfolio Page
---

## Project: Teaching Assistant Assistant
Teaching Assistant Assistant (TAA) is a desktop app for managing teaching assistant
activities, optimized for use via a Command Line Interface (CLI) while still having
the benefits of a Graphical User Interface (GUI). If you can type fast, TAA can get
your teaching assistant tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

- **Code Contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=chunzkok&breakdown=true)
- **New Features**
  - **Class Statistics** ([PR #142](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/142))
    - **What it does**: Allows users to view statistics regarding the active class list's attendance and grades.
      - The attendance chart displays the number of students that have been marked as 'present' for each of the 12 weeks.
      - The grades chart displays a normal distribution generated using the mean and standard deviation of the scores of the students in the class, for a specific assignment.
    - Imported and integrated library `JFreeChart` to help with chart drawing.
    - Created helper methods to aggregate across different systems (i.e. `Assignment` and `Attendance` systems) to generate the relevant statistics.
- **Enhancements**
  - **Refactored Person class to the Student class** ([PR #67](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/67))
    - Replaced all usages of `Person` and its related classes from original AB-3 code to use `Student` instead
    - Removed redundant fields in Person (`Address`, `Phone`, `Email`)
    - Update JavaFX view for `StudentCard` (previously `PersonCard`) to follow mockup of the product
    - Tweaked all failing tests to pass with the new `Student` class (e.g. replacing reference .json files and expected outputs)
  - **Modified `add` command into `add_student`** ([PR #85](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/85))
    - Changed the name to avoid clashes with other systems
    - Removed unnecessary parameters (address, phone, email) and added new ones (class tags)
  - **Modified `edit` command into `edit_student`** ([PR #87](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/87))
    - Changed the name to avoid clashes with other systems
    - Removed unnecessary parameters (address, phone, email) and added new ones (class tags)
  - **Modified `delete` command into `delete_student`** ([PR #87](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/87))
  - **Integrated `Assignment` commands with the GUI to update relevant fields after they are executed** ([PR #101](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/101))
    - Involves commands `addAsgn`, `grade`, `ungrade`, `deleteAsgn`
  - **Refactored code to weed out traces of AB-3** ([PR #194](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/194))
- **Bug Fixes**
  - Fixed bug where `Attendance` and `Assignment` systems were not in migrated `taa` package ([PR #71](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/71))
  - Fixed bug where `add_student` does not add the student into the Class Lists specified by their class tags ([PR #99](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/99))
  - Fixed bug where `add_student` throws an `InvocationTargetException` in certain execution paths ([PR #108](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/108))
  - Fixed bug where `delete_student` throws an `InvocationTargetException` when executed ([PR #195](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/195))

- **Documentation**
  - **User Guide**
    - Fixed Table of Contents so that it builds automatically ([PR #132](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/132))
    - Added purpose and target audience of User Guide ([PR #132](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/132))
    - Added instructions on how to use the User Guide (Navigation, special formats) ([PR #132](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/132))
    - Added GUI section with labelled GUI with a table explaining the various components of our GUI ([PR #132](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/132))
    - Added explanation of command syntax used in command formats under the 'Features' section ([PR #132](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/132))
    - Added Glossary ([PR #132](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/132))
    - Added feature explanations for `add_student`, `edit_student`, `delete_student`, `class_statistics`
  - **Developer Guide**
    - Added value proposition and initial set of user stories ([PR #32](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/32), [PR #35](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/35))
    - Added use case for `add_student` command ([PR #119](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/119))
    - Added Introduction section ([PR #234](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/234))
    - Modified AB-3 UML diagrams to fit TAA context ([PR #234](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/234))
    - Added implementation details and design considerations for `add_student` command ([PR #234](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/234))

- **Team-based Tasks**
  - Enabled assertions in project Gradle file ([PR #106](https://github.com/AY2223S2-CS2103T-T14-4/tp/pull/106))
  - Created releases for [v1.2](https://github.com/AY2223S2-CS2103T-T14-4/tp/releases/tag/v1.2), [v1.2.1](https://github.com/AY2223S2-CS2103T-T14-4/tp/releases/tag/v1.2), [v1.3](https://github.com/AY2223S2-CS2103T-T14-4/tp/releases/tag/v1.3), [v1.4](https://github.com/AY2223S2-CS2103T-T14-4/tp/releases/tag/v1.4)
  - Created and closed milestones [v1.1](https://github.com/AY2223S2-CS2103T-T14-4/tp/milestone/1), [v1.2](https://github.com/AY2223S2-CS2103T-T14-4/tp/milestone/2), [v1.2b](https://github.com/AY2223S2-CS2103T-T14-4/tp/milestone/3), [v1.3](https://github.com/AY2223S2-CS2103T-T14-4/tp/milestone/4), [v1.4](https://github.com/AY2223S2-CS2103T-T14-4/tp/milestone/5)
