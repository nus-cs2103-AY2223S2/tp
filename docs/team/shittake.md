---
layout: page
title: Eric's Project Portfolio Page
---

### Project: TrAcker

TrAcker - TrAcker is a desktop event management application for NUS CS2040 Teaching Assitants (TA). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to filter students depending on their performance level and/or urgency level
  * What it does: Filters the students with lower performance or higher urgency than a threshold specified by the user
  * Justification: This feature improves the product significantly because the user can see the students who are currently struggling, in a very short time
  * Highlights: This enhancement is challenging because it affects the visibility of the different roles in our databases, without mutating the lists so that the execution of other commands can still allow all the other rows to be seen

* **New Feature**: Added the ability to sort students in all groups by their particulars
  * What it does: Sorts the students in the specified group, according to a specified metric such as name, email, address, remark or performance level
  * Justification: This feature helps in attendance taking, or when the user wishes to view students who are performing weaker/stronger than their peers when recommending them for teaching assistant positions, or when deciding who to focus their attention to
  * Highlights: This enhancement is challenging because it should not mutate the list in a way that removes the original ordering, so that other commands can sitll function as expected. The avatar icons of each user in each group should also be ordered for better user experience when this command is executed.

* **Code contributed**: [RepoSense link](https://github.com/shittake/tp)

* **Project management**:
  * Managed releases `v1.2` - `v1.5rc` (4 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI color scheme for performance levels of users (Pull request [\#92](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/92))
  * Allowed sorting of avatars together with student names (Pull request [\#187](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/187))
  * Wrote additional tests for existing features to increase coverage (Pull request [\#263](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/263))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `sort` and `filter`
  * Developer Guide:
    * Drew class diagram for comparators and the `sort` command [\#310](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/310)
    * Added implementation details of the `filter` feature [\#132](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/132)

* **Community**:
  * Participated in team discussions on our overview of the final product and suggested the sort and filter features
  * Discussed on ways to avoid cyclic dependencies in students and their respective lab/tutorial/consultation groups
  * Gave feedback on ways to improve code quality and to pass Java Continuous Integration tests
  
* **Tools**:
  * Integrate colors into table cells for visuals [\#92](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/92)

