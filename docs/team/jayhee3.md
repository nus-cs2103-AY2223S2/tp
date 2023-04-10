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

#### Features and enhancements implemented by me
* New Feature: Added the ability to add a deadline to a module in the module tracker. 
   * What it does: allows the user to add a deadline to a module. 
   * Justification: Computing students have numerous deadlines that they have to keep track of for each module. Such a feature would help them to always be ahead of their tasks.
 
<br>

* New Feature: Added a command for the user to sort the modules based on their timeslot or deadline. [\#78](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/78)
   * What it does: Each module can have a deadline and/or timeslot attached to it. An example of a deadline would be 23rd Jan 2023. On the other hand, a timeslot is a recurring event with an example being Tuesday 2pm - 4pm. This feature allows the user to sort the modules based on the timeslot or deadline of the modules, based on which has a deadline due first or has a timeslot that is occuring next. 
   * Justification: This feature would let computing students quickly see what lectures, tutorials, or meetings they have for the day, as well as the deadlines due soon.
   * Highlights: This was a challenging task as it required an in-depth understanding of how the user interface displayed the modules. I found out that the user interface was actively "listening" for changes to the list of modules in the background to make sure that it always displayed the desired list. In order to get the user interface to display a sorted version of the list of modules when requested by the user, I had to understand the mechanism by which I could change the list of modules and ensure that the user interface was always aware of the change. 
  
<br> 

* New Feature: Changed the theme of the user interface [\#84](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/84)
   * What it does: Changed the theme of the user interface to make it cleaner, more minimalistic and conducive for work. The new theme utilises a mixture of light blue and white hues to achieve an aesthetic appeal. 
   * Highlights: The graphical user interface of the desktop application was built using `JavaFX`. As such, changing the theme of the user interface was a challenging task that required a strong understanding of `JavaFX` documentation and `FXML` files. Using the `Scene Builder` software proved particularly useful as it helped with visualing how the code interacted with the GUI across several layers. 

--------------------------------------------------------------------------------------------------------------------

#### Additional code contributions [\#153](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/153)
* Improving code quality by utilising the KISS and SLAP principles
* Employing greater use of exceptions, assertions and defensive programming
* Fixing of failing test cases
* Fixing checkstyle errors 

--------------------------------------------------------------------------------------------------------------------
#### Contributions to the User Guide (UG) [\#158](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/158)
  * Added and improved documentation for the following sections: 
     * About the User Guide
     * How to use the User Guide
     * Familiarising with a module
     * Features in coNtactUS
     * Format restrictions for input values by the user
     * Product limitations
     * Coming soon
  
--------------------------------------------------------------------------------------------------------------------

#### Contributions to the Developer Guide (DG) 
  * Added and improved documentation for the following sections: 
      * High level details of the overall architecture for coNtactUS
      * Implemtation details for SortCommand
      * Evaluation of effort for SortCommand
      
--------------------------------------------------------------------------------------------------------------------

#### See my code contribution in detail
* Code contributed: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jayhee3&breakdown=true)

 --------------------------------------------------------------------------------------------------------------------

#### Contributions to team-based tasks
My contributions to team-based tasks include but are not limited to the following: 
* Creating of issues and project milestones
* Setting priority of issues 
* Closing of issues
* Creating and management of releases  
* Organising internal project documents to make them cleaner and better structured 

 --------------------------------------------------------------------------------------------------------------------

#### Review/ Mentoring contributions 
* Reviewing pull requests from team members [\#153](https://github.com/AY2223S2-CS2103T-W10-1/tp/pull/153)
* Helping team members to better understand the code base and assisting them with difficulties they faced implementing features or bug fixing 

 --------------------------------------------------------------------------------------------------------------------

#### Contributions beyond the project team
* Participated in software testing of other project teams to catch bugs and help them improve their product 

 --------------------------------------------------------------------------------------------------------------------
