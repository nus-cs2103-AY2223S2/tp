---
layout: page
title: Li Yuxuan's Project Portfolio Page
---

### Project: MATHUTORING

MATHUTORING is a desktop application used for private Math tuition teachers to manage their students' contacts and performances. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  1. Switch command<br>
      * What it does: Help the user to easily switch between score text panel and score chart panel. Support both CLI and mouse click.
      * Justification: This feature is convenient for the user to switch between score text panel and score chart panel.
      * Highlights: Since the switch support both CLI and mouse click. It is important to make the behaviour consistence, so the application need to remember the panel status and switch based on it.
  2. Check command<br>
      * What it does: For the user to check a specific student's task/s and score/s information.
      * Justification: This feature is important as it indicate which student the user want to check and display the selected student's information.
      * Highlights: The check result is normally continue to display. The panel will reset to no student being checked when the currently checked student is being deleted/edited, or the entire student list is cleared, or a new student list is being imported in.
  3. Filter command<br>
      * What it does: To filter out student/s from the existing list. Filter key is the tag/s. 
      * Justification: This feature help the user to quickly filter out a group of students based on their given tags. 
      * Highlights: To allow more flexibility, when filter by tag, it is fully case-insensitive.
  4. Score list chart<br>
      * What it does: Created the score list chart to track the student's performance. 
      * Justification: The chart can clearly show the trend of a student's recent performance, whether his/her grade is improving or decreasing. 
      * Highlights: The user can hover over the chart node to see tooltip that contains score label and score value.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=carrieli1015&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Individual
      * Completed weekly requirements on time. 
      * Created several GitHub branches for different increments.
    * Group
      * Met the milestone deadlines. 
      * Used GitHub for progress tracking. 

* **Enhancements to existing features**:
    * Revamped the existing student list UI. (Pull requests [#71](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/71) [#72](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/72))
    * UI integration. (Pull requests [#81](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/81))
    * Revamped the overall UI layout and make it more responsive (Pull requests [#117](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/117))
      * Individual scroll bar for different panels.
      * Allow text wrap and set up display restriction to avoid unwanted text truncate.
    * Wrote additional tests for existing features. (Pull requests [#170](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/170))

* **Documentation**:
    * Updated AboutUs page and README page.
    * Added a quick user guide inside the help window.
    * User Guide (Pull requests [#116](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/166) [#176](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/176))
      * Added in missing items. 
      * Updated UG with more details.
    * Developer Guide (Pull requests [#176](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/176))
      * Helped with generate the UML diagram.
      * Tweaked the DG and relevant guides.

* **Contributions to team-based tasks**:
    * Conceptualized the ideas and came up with the overall UI design.
    * Added several GitHub issues and helped manage the issues through adding labels and milestones.
    * Organized target user stories. 
    * Created illustrations. Including the avatars, student list icons, task list icons, and import/export window icons.

* **Review/mentoring contributions**:
  * Reviewed other teammates' pull requests.

* **Community**:
    * Shared idea and gave suggestions to another group.
    * Contributed to forum discussions
      * [#206](https://github.com/nus-cs2103-AY2223S2/forum/issues/206)
      * [#290](https://github.com/nus-cs2103-AY2223S2/forum/issues/290)

* **Tools**:
    * JavaFX framework
    * Adobe Illustrator
    * Figma
