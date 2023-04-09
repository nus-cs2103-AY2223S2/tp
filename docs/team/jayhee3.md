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
 

2. New Feature: Added a command for the user to sort the modules based on their timeslot or deadline. [\#78](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/78)
   * What it does: Each module can have a deadline and/or timeslot attached to it. An example of a deadline would be 23rd Jan 2023. On the other hand, a timeslot is a recurring event with an example being Tuesday 2pm - 4pm. This feature allows the user to sort the modules based on the timeslot or deadline of the modules, based on which has a deadline due first or has a timeslot that is occuring next. 
   * Justification: This feature would let computing students quickly see what lectures, tutorials, or meetings they have for the day, as well as the deadlines due soon.
   * Highlights: This was a challenging task as it required an in-depth understanding of how the user interface displayed the modules. I found out that the user interface was actively "listening" for changes to the list of modules in the background to make sure that it always displayed the desired list. In order to get the user interface to display a sorted version of the list of modules when requested by the user, I had to understand the mechanism by which I could change the list of modules and ensure that the user interface was always aware of the change. 
  

1. New Feature: Changed the theme of the user interface [\#84](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/84)
   * What it does: Changed the theme of the user interface to make it cleaner, more minimalistic and conducive for work. The new theme utilises a mixture of light blue and white hues to achieve an aesthetic appeal. 
   * Highlights: The graphical user interface of the desktop application was built using `JavaFX`. As such, changing the theme of the user interface was a challenging task that required a strong understanding of `JavaFX` documentation and `FXML` files. Using the `Scene Builder` software proved particularly useful as it helped with visualing how the code interacted with the GUI across several layers. 
 
#### Additional code contributions [\#153](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/153)
1. Improving code quality by utilising the KISS and SLAP principles
2. Employing greater use of exceptions, assertions and defensive programming
3. Fixing of failing test cases
4. Fixing checkstyle errors 

--------------------------------------------------------------------------------------------------------------------
#### Contributions to the User Guide (UG) 

--------------------------------------------------------------------------------------------------------------------
#### Contributions to the Developer Guide (DG) 

--------------------------------------------------------------------------------------------------------------------
#### See code contribution in detail
* Code contributed: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jayhee3&breakdown=true)

 --------------------------------------------------------------------------------------------------------------------
#### Contributions to team-based tasks
My contributions to team-based tasks include but are not limited to the following: 
1. Creating of issues and project milestones
2. Setting priority of issues 
3. Closing of issues
4. Creating and management of releases  
5. Organising internal project documents to make them cleaner and better structured 

 --------------------------------------------------------------------------------------------------------------------
#### Review/ Mentoring contributions 
1. Reviewing pull requests from team members (Example: [\#153](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/153)
2. Helping team members to better understand the code base and assisting them with difficulties they faced implementing features or bug fixing 

 --------------------------------------------------------------------------------------------------------------------
#### Contributions beyond the project team
1. Participated in software testing of other project teams to catch bugs and help them improve their product 
 --------------------------------------------------------------------------------------------------------------------
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

