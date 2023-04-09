---
layout: page
title: Noah's Project Portfolio Page
---

### Project: sprINT

**sprINT** is an **internship-tracking application** that was created to assist students in their internship hunt.

Students often face a great administrative burden in keeping track of the high volume of job or internship
applications. With sprINT, students can easily manage details of their internship applications, and enjoy functionalities
like sorting their application entries by deadlines, or finding a specific application with a keyword.

sprINT is a desktop application that is optimised for Command Line Interface (CLI) users, while still providing the benefits
of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=noahxinjie&breakdown=true)


* **Project management**:
  * Help tag issues to appropriate milestone
  * Help close issues when they are done
  * Help review and merge **12** PRs
  

* **New feature**:
  * Implement the `sort` feature, with the options to sort **alphabetically**, or with respect
  to the **deadline** of an outstanding task. (See PR [#116](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/116))
  * Extend the functionality of `sort` feature so that users also have the option to sort in either ascending
  or descending order. (See PR [#162](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/162))
  

* **Testing**: 
  * Add test cases for the following to improve test coverage and test robustness of code
    * `ModelManager` (See PR [#228](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/228))
    * `sort` and `list` commands, as well as the parser for these commands (See PR [#241](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/241))


* **Documentation**:
  * **User Guide**:
    * Add [section on the usage of the `sort` feature](https://ay2223s2-cs2103t-t13-3.github.io/tp/UserGuide.html#433-sorting-applications--sort) under Features
    * Add commands and a column to indicate which commands can be undo-ed in the [Command summary](https://ay2223s2-cs2103t-t13-3.github.io/tp/UserGuide.html#7-command-summary)
    * Make parts of the guide adopt a more friendly and personable tone; 
    e.g., in the example given for the section on [adding a task](https://ay2223s2-cs2103t-t13-3.github.io/tp/UserGuide.html#421-adding-a-task--add-task)
    * Do general proofreading and editing for the overall document
  * **Developer Guide**:
    * Add [section on the implementation of the `sort` feature](https://ay2223s2-cs2103t-t13-3.github.io/tp/DeveloperGuide.html#54-sort-applications-feature) under Implementation
    * Add [user stories](https://ay2223s2-cs2103t-t13-3.github.io/tp/DeveloperGuide.html#82-user-stories) 
    and [use cases](https://ay2223s2-cs2103t-t13-3.github.io/tp/DeveloperGuide.html#83-use-cases) in the Appendix for Requirements
    * Add section on manual testing for the [sort](https://ay2223s2-cs2103t-t13-3.github.io/tp/DeveloperGuide.html#sorting-the-application-list)
    and [list](https://ay2223s2-cs2103t-t13-3.github.io/tp/DeveloperGuide.html#listing-all-applications) commands in the Appendix
    for Manual Testing
    * Add a [feature flaw](https://ay2223s2-cs2103t-t13-3.github.io/tp/DeveloperGuide.html#1-command-to-sort-by-deadline-does-not-inform-user-when-there-are-no-applications-with-deadlines-to-display-and-sort) 
    and propose a possible fix in the Appendix for Planned Enhancements
    * Do general proofreading and editing for the overall document, which includes but not limited to checking
    of diagrams to check for any misnamed classes, checking the links if they are working and checking for typos


* **Team-based tasks**:
  * Refactor AB3 test code to align with our codebase
  * Help [release JAR files](https://github.com/AY2223S2-CS2103T-T13-3/tp/releases) for download during each milestone
  * Help check for deadlines and requirements needed for each week

