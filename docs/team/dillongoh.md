---
layout: page
title: Dillon Goh's Project Portfolio Page
---

### Project: SOCket

SOCket is a desktop application for NUS Software Engineering Students to manage the contact information of their peers and professors. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: `sort` command [\#78](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/78)
    * What it does: Allows the user to sort the list of contacts by name, phone number, email, address or GitHub username.
    * Justification: Users are able to organize the list of contacts in a way that is more convenient for them.
    * Highlights: Places the contacts with empty fields for the selected category at the end of the list.
    * Credits: *{-}*

* **New Feature**: `Project` class and related classes (in collaboration with [@chia-yh](https://github.com/chia-yh)) [\#124](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/124)
    * What it does: Allows the user to store information about the projects they are working on, including details on the project name, deadline, meeting, repository host, repository name and members.
    * Justification: Users are able to store and manage information on their projects.
    * Highlights: Makes use of a `UniqueProjectList` as well as various `isValid` methods to ensure that the projects stored is valid and unique.
    * Credits: *{This feature was implemented similar to AB3's `Person` and `UniquePersonList`}*

* **New Feature**: `sortpj` command [\#152](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/152)
    * What it does: Allows the user to sort the list of projects by name, deadline, repository host or repository name.
    * Justification: Users are able to organize the list of projects in a way that is more convenient for them.
    * Highlights: Places the projects with empty fields for the selected category at the end of the list.
    * Credits: *{-}*

* **New Feature**: `unassign` command [\#169](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/169)
    * What it does: Allows the user to unassign a member from a project.
    * Justification: Users are able to remove a member from a project if they are no longer involved.
    * Highlights: Checks if the member is assigned to the project before removing them.
    * Credits: *{-}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dillongoh&breakdown=true)


* **Documentation**:
    * User Guide:
        * Added documentation for the command `sort` [\#49](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/49)
        * Added documentation for the command `sortpj` [\#169](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/169)
        * Added documentation for the command `unassign` [\#169](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/169)
    * Developer Guide:
        * Added User Stories [/#58](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/58)
        * Added Use Cases UC03, UC04, UC12 [\#59](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/59)
        * Added implementation details of the `sort` and `sortpj` command [\#160](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/160)
        * Added Use Cases UC19, UC21 [\#171](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/171)
        * Added manual testing for `sort`, `sortpj`, `unassign` [\#266](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/266)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#61](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/61) [\#94](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/94)
  [\#130](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/130) [\#172](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/172) [\#179](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/179)
    * Assisted team members with debugging
    * Carried out testing and reported bugs for other teams: [\#132](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/132) [\#153](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/153) [\#158](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/158)

* **Tools**:
    * Sourcetree
    * IntelliJ IDEA
    * CheckStyle
