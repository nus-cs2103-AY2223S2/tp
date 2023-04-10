---
layout: page
title: Randall's Project Portfolio Page
---

### Overview

FastTrack is a desktop application to help you keep track of daily expenses, optimised for use via a command line interface (CLI). With this app, you can easily add expenses by category, view a summary of what has been spent in total, by category or for the week. The user interface is intuitive and easy-to-use. Overall, FastTrack aims to speed up the time taken to log expenses, saving valuable time for the user.

### Summary of Contributions

**Code contributed:** 

The following [link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=randallnhr&breakdown=true&sort=groupTitle+dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs%7Efunctional-code%7Etest-code%7Eother) is my code contribution.

#### **Enhancements implemented:**
* Implemented commands for `Category`
  * `addcat` - allows users of FastTrack to add a new `Category` into FastTrack.
  * `delcat` - allows users to delete an existing `Category` in FastTrack. 
    * Expenses with the deleted category will have its category replaced with the `MiscellaneuosCategory`.
  * `lcat` - allows users to list all added `Category`, used to determine index for edit and delete category commands.
* Implemented `delcat` command and parser. (PR)
  * What it does: This feature allows users to delete an existing `Category` in FastTrack. Expenses with the deleted category will have its category replaced with the `MiscellaneuosCategory`.
  * Justification: This allows users to delete unnecessary categories. 
* Implemented `lcat` command and parser. (PR)
  * What it does: This feature allows users to list all added `Category`.
  * Justification: Gives users the ability to see all `Category` added in a glance, it is also used in conjuction to `delcat` and `edcat` commands as index of the `Category` is needed.
* Implemented `Budget` class and linked it to the UI to update statistics. (PR)
  * What it does: Allows users to add a monthly budget into FastTrack. This is used in conjunction with the Statistics feature to allow users to have an easy way to see how much of the budget has been utilised.
* Implemented commands for `RecurringExpenseManager`
  * `addrec` - allows users to add `RecurringExpenseManager` objects into FastTrack
  * `delrec` - allows users to delete a `RecurringExpenseManager` object.
* Implemented `CLEAR` command to allow users to clear all existing data at ease. 

#### **Contributions to the UG:**
* Added comm

#### **Contributions to the DG:** to be added soon

#### **Contributions to team-based tasks:** to be added soon

#### **Review/Mentoring Contributions:** to be added soon

#### **Contributions beyond team project:** to be added soon
