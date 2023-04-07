---
layout: page
title: Hee Jia Yuan's Project Portfolio Page
---
# coNtactUS 

## Overview
coNtactUS is a module tracker created specially for NUS computing students, by NUS computing students. It is optimised for use via typing 
instead of clicking, which has been shown to be faster. This helps NUS computing students to be more efficient in their work. Developed by a team of NUS computing students who are familiar with the problems that computing students face on a daily basis, coNtactUS is here to make module tracking an effortless process.

## Summary of my contributions
coNtactUS is written in Java, and has about 10 kLoC. The development team consists of 4 members, including myself. Given below are my contributions to this project.

#### Features and enhancements implemented
1. New Feature: Added the ability to add a deadline to a module in the module tracker.
   * What it does: allows the user to add a deadline to a module. 
   * Justification: Computing students have numerous deadlines that they have to keep track of for each module. Such a feature would help them to always be ahead of their tasks.
 
2. New Feature: Added a command for the user to sort the modules based on their timeslot or deadline.
   * What it does: Each module can have a deadline and/or timeslot attached to it. An example of a deadline would be 23rd Jan 2023. On the other hand, a timeslot is a recurring event with an example being Tuesday 2pm - 4pm. This feature allows the user to sort the modules based on the timeslot or deadline of the modules, based on which has a deadline due first or has a timeslot that is occuring next. 
   * Justification: This feature would let computing students quickly see what lectures, tutorials, or meetings they have for the day, as well as the deadlines due soon.
   * Highlights: This was a challenging task as it required an in-depth understanding of how the user interface displayed the modules. I found out that the user interface was actively "listening" for changes to the list of modules in the background to make sure that it always displayed the desired list. In order to get the user interface to display a sorted version of the list of modules when requested by the user, I had to understand the mechanism by which I could change the list of modules and ensure that the user interface was always aware of the change. 
  
3. New Feature: Changed the theme of the user interface 
   * What it does: Changed the theme of the user interface to make it cleaner, more minimalistic and conducive for work. The new theme utilises a mixture of light blue and white hues to achieve an aesthetic appeal. 
   * Highlights: The graphical user interface of the desktop application was built using `JavaFX`. As such, changing the theme of the user interface was a challenging task that required a strong understanding of `JavaFX` documentation and `FXML` files. Using the `Scene Builder` software proved particularly useful as it helped with visualing how the code interacted with the GUI across several layers. 
 
#### Contributions to the User Guide (UG) 

#### Contributions to the Developer Guide (DG) 

#### Contributions to team-based tasks
My contributions to team-based tasks include but are not limited to the following: 
1. Creation of issues and project milestones
2. 

#### Review/ Mentoring contributions 

#### Contributions beyond the project team


* Code contributed: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jayhee3&breakdown=true)

* Project management:
  * Managed releases v1.3 - v1.5rc (3 releases) on GitHub

* Enhancements to existing features:
  * Updated the GUI color scheme (Pull requests \#33)
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests \#36)

* Documentation:
  * User Guide:
    * Added documentation for the features delete and find [\#72]()
    * Did cosmetic tweaks to existing documentation of features clear, exit: [\#74]()
  * Developer Guide:
    * Added implementation details of the delete feature.

* Community:
  * PRs reviewed (with non-trivial review comments): \#12, \#19
  * Contributed to forum discussions (examples: 1, 3)
  * Reported bugs and suggestions for other teams in the class (examples: 1, 3
  * Some parts of the history feature I added was adopted by several other class mates (1)

* Tools:
  * Integrated a third party library (Natty) to the project (\#42
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
