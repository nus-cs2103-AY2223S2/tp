---
layout: page
title: Karen Lam's Project Portfolio Page
---

### Project: The Intern's Ship

**The Intern's Ship (TinS)** is a desktop application for **internship-seeking university students** to more efficiently
and conveniently manage, coordinate and keep track of their numerous internship applications. Despite being optimised
for use via Command Line Interface (CLI), TinS also provides its user with a Graphical User Interface (GUI) to enable
its users to navigate the application with ease.

Given below are my contributions to the project.

* **New Feature**: Added the ability to store Internship Applications and its associated Events
  * What it does:
    * The first iteration enables users to store internship applications after each modification to the existing data
      [(#79)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/79)
    * The second iteration enables user to store events associated with the internship application
      [(#110](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/110), 
      [#111)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/111)
  * Justification: This feature is vital for users to use TinS, as it ensures that changes made to TinS data by the user
    is can be saved and retrieved after and before each command.

* **New Feature**: Added the ability to view all internships 
    * What it does: allows the user to view all internships saved in TinS. Only internship position, company and
      associated tags are displayed in the List Panel ().
    * Justification: This feature is vital for users to efficiently use TinS as it displays only essential information
      of all internship a user has, avoiding screen clutter.

* **New Feature**: Added the ability to find all events with clashing timing
    * What it does: finds all Events with clashes in timings
      [(#117](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/117),
      [#121](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/121), 
      [#133)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/133)
    * Justification: This feature is helpful for users as TinS allows users to add events to internships even if the
      events result in clashes with other event. As an internship managing application, users require TinS to provide
      them with a way to find the clashes in their schedule.

* **Code contributed**: [link](https://github.com/Karen-Lam/tp)

* **Project management**:
  * Merging of code of newly implemented features for v1.3 [(#125](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/125), 
    [#134](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/134), [#139)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/139)
  * Merging and Formatting of User Guide
  
* **Documentation**:
    * User Guide:
      * Modification for User Guide Command Summary:
        * Crafted initial documentation for command summary of v1.2 [(#4)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/4)
        * Update command summary to accommodate newer features 
      * Modified documentation for User Guide Features: 
        * Crafted initial documentation for the features `list` [(#4)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/4)
        * Modified feature description to match newer iterations of TinS [(#102)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/102)
        * Refine feature description to become more user-oriented
      * Crafted the "About the User Guide" and "About TinS" Section in User Guide [(#202)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/202)
      * Overall Styling of User Guide 

    * Developer Guide:
      * Crafted Use case and User story for `find` function [(#47)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/47)
      * Crafted Non-functional requirements for TinS application [(#47)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/47)
      * Crafted Clash Feature Implementation Section [(#217)](https://github.com/AY2223S2-CS2103T-W11-2/tp/pull/217)
