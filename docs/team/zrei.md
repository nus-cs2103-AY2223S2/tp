---
layout: page
title: Lim Zhe Rui's Project Portfolio Page
---

### Project: Artistic AddressBook

Artistic AddressBook (ArB) is a desktop app for artists, meant to help with efficiently managing clients and project information.

Given below are my contributions to the project.

* **Project management**:
  * Team Leader
    * Organised team meetings and kept meeting minutes
    * Reminded fellow team members of deadlines and kept them on task
    * Made decisions regarding what features to implement and what direction to go in
  * Reviewed PRs for team members, [providing suggestions](https://github.com/AY2223S2-CS2103T-T14-1/tp/pull/172) and [possible solutions](https://github.com/AY2223S2-CS2103T-T14-1/tp/pull/171)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=zrei&breakdown=true)
  * Contributed majority of the test code


* **Enhancements implemented**:
  * Ability to sort the client / project list
    * Wrapped the JavaFX filtered list in a JavaFX sorted list, sorted by a predicate that can be updated by the model
    * Coded the relevant functions to update the predicate of the sorted list
  * A list of tags that shows how many clients and projects a tag has been used with
    * This involved coding a unique tag list that had to be updated on every change of the model so that the information is always accurate
  * Ability to link a client to a project
    * Since the input patterns for linking is different from other command formats, this required creating a new parser that was to be run only when a certain flag is true
    * Implementing the backend link between a client and a project was complicated as the client and project list had to be kept updated whenever a linked client or project is edited so as to keep the UI updated.
  * Enhanced the existing find feature
    * Allowed more parameters to be used in the find feature, such as tags and linked clients (for projects)
    * This involved creating more predicate classes and the creation of a CombinedPredicate class that tests either a client or a project against all specified predicates


* **Documentation**:
  * Contributions to [User Guide](https://ay2223s2-cs2103t-t14-1.github.io/tp/UserGuide.html)
    * Preface and quick start sections
    * FAQ section and instructions on linking projects to clients
  * Contributions to [Developer Guide](https://ay2223s2-cs2103t-t14-1.github.io/tp/DeveloperGuide.html)
    * Updating the Design section and all UML diagrams in that section to match the design of our app
    * Describing the implementation of the sorting feature; adding UML diagrams to aid in understanding
    * Describing the implementation for linking projects to clients; adding UML diagrams to aid in understanding
    * Describing the implementation for the finding feature; adding UML diagrams to aid in understanding
    * Added user stories relevant to our app
    * Added use cases relevant to our app, such as use cases on finding clients and projects
