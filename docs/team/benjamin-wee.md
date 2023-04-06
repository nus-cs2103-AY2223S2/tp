---
layout: page
title: Benjamin Wee's Project Portfolio Page
---

### Project: InternEase

InternEase is a **powerful and innovative desktop app designed to streamline the internship application process** for **Computer Science undergraduates**. With its optimized **combination of a Command Line Interface (CLI) and Graphical User Interface (GUI)**, InternEase offers users the best of both worlds - the speed and efficiency of a CLI for those who can type quickly, and the user-friendly experience of a GUI for those who prefer a visual interface. InternEase is a good choice for you who wants to concentrate on your internship preparation by helping to manage your internship applications' data.

Listed below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=benjamin-wee&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Features**:
  * Manage the interview date of an internship application
    * What it does: Users are able to add an interview date to an internship application
    * Justification: This feature allows the user, an internship applicant in this case to quickly retrieve the interview date of an application.
    * Highlights: A Date Time validation feature had to be implemented to ensure that the date and time provided by the user is valid.
    * Credits: Some parts of the code was reused and adapted from the [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).
  * Remind the user of the internship application with the most imminent interview date
    * What it does: Users are able to view the details of the internship application with the interview that is the nearest to the current date and time in a pop-up window every time they launch the application/execute the `remind` command.
    * This feature allows users to quickly identify the application with an interview date that is closest to the current date and time, so that they will not miss the interview. 
    * Credits: Some parts of the code was reused and adapted from the [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).


* **Project management**:
    * Managed release `v1.2` on GitHub.

* **Enhancements to existing features**:
    * Enhanced `list` feature according to the InternEase model in [#122](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/122)
    * Added implementation of the reminder button as part of our GUI enhancement.

* **Documentation**:
    * User Guide:
        * Added documentation for the features below in [#208](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/208), [#68](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/68)
          * `add_date`, `remind`
          * `list`
    * Developer Guide:
        * Added use cases for `add_date` in [#150](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/150)

* **Community**:
    * Reported bugs and suggestions for other teams in the class : [Team CS2103T-F12-2](https://github.com/benjamin-wee/ped/issues)

* **Tools**:
    * Java 11 and JavaFX
