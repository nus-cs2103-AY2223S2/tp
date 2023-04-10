---
layout: page
title: Lim Zhe Rui's Project Portfolio Page
---

### Project: Artistic AddressBook

Artistic AddressBook (ArB) is a desktop app for freelance artists, meant to help with efficiently managing client and project information.

Given below are my contributions to the project.

* **Project management**:
  * Team Leader
    * Organised team meetings and kept meeting minutes
    * Reminded fellow team members of deadlines and kept them on task
    * Made decisions regarding what features to implement and what direction to go in
  * Reviewed PRs for team members, [providing suggestions](https://github.com/AY2223S2-CS2103T-T14-1/tp/pull/172) and [possible solutions](https://github.com/AY2223S2-CS2103T-T14-1/tp/pull/171)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=zrei&breakdown=true)
  * Contributed majority of the test code and maintained code coverage at 75% and above


* **Enhancements implemented**:
  * Ability to sort the client/project list
    * Had to learn how to use JavaFX's sorted list so that I could utilise it to sort entities by a predicate that can be supplied by the model
    * Created new SortClientCommand and SortProjectCommand classes and their relevant parsers
  * A list of tags that shows how many clients and projects a tag has been used with
    * This involved coding a `UniqueTagMappingList` that had to be updated on every change of the model so that the information is always accurate
  * Ability to link a client to a project
    * Since the input patterns for linking is different from other command formats, this required creating a new function in `AddressBookParser` that was to be run only when a certain flag is true
    * Implementing the backend link between a client and a project was complicated as there were many ways to do it and I wanted to find a way that reduced complexity and coupling. It was also difficult since the client and project list had to be kept updated whenever a linked client or project is edited so as to keep the UI up-to-date
  * Enhanced the existing find feature
    * Allowed more parameters to be used in the find feature, such as tags and linked clients (for projects)
    * This involved creating more predicate classes and the creation of a `CombinedPredicate<T>` class that tests either a client or a project against all specified predicates
  * Aliases for command words and prefixes


* **Documentation**:
  * Contributions to [User Guide](https://ay2223s2-cs2103t-t14-1.github.io/tp/UserGuide.html)
    * Preface and quick start sections
    * Instructions on [linking projects to clients](https://ay2223s2-cs2103t-t14-1.github.io/tp/UserGuide.html#linking-a-project-to-a-client)
    * Created the diagrams in the [command format section](https://ay2223s2-cs2103t-t14-1.github.io/tp/UserGuide.html#command-format)
  * Contributions to [Developer Guide](https://ay2223s2-cs2103t-t14-1.github.io/tp/DeveloperGuide.html)
    * Updated the [Design section](https://ay2223s2-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#Design) and all UML diagrams in that section to match the design of our app
    * Described the [implementation of the sorting feature](https://ay2223s2-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#Sorting); adding UML diagrams to aid in understanding
    * Described the [implementation for linking projects to clients](https://ay2223s2-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#linking-projects-to-clients); adding UML diagrams to aid in understanding
    * Described the implementation for the [finding feature](https://ay2223s2-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#better-filtering); adding UML diagrams to aid in understanding
    * Updated [appendix for manual testing](https://ay2223s2-cs2103t-t14-1.github.io/tp/DeveloperGuide.html#appendix-instructions-for-manual-testing)
    * Added user stories relevant to our application
    * Added use cases relevant to our application, such as use cases on finding clients and projects
