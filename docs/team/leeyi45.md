---
layout: page
title: LEE YI Project Portfolio Page
---

### Project: tuition center admin managing system (TCAMS)

Tuition center admin managing system (TCAMS) is a desktop application designed for admins working at tuition center managing courses, tutors and students, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). TCAMS utilizes your fast typing ability to execute your management tasks faster than traditional GUI apps.

Given below are my contributions to the project.

<!-- * **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}* -->

* **New Features**: 
  * Added attendance tracking
      * Added attendance field to Tutee
      * Added attendance commands (mark, unmark, query)

* **Code contributed**: [link](https://github.com/AY2223S2-CS2103T-W10-4/tp/pulls?q=is%3Apr+author%3Aleeyi45)

* **Enhancements to existing features**:
  * Refactored code for validating fields on load to remove repetition
  * Made command parsing more modular (moved from switch statement to map)
  * Fixed tutee being able to have a start time later than an end time
  * Changed schedule parser to be able to accept valid days of the week while being case insensitive
  * Changed subject parser to be able to accept valid subjects while being case insensitive.

* **Documentation**:
  * UserGuide.md
    * Updated documentation for the attendance commands (mark, unmark, query)
  * DeveloperGuide.md
    * Added implementation details for the attendance commands, and also for field validation

* **Community**:
  * Reported bugs for other teams during PE-Dry run

* **Tools**:
  * Set up CI for the master branch for the team repository.