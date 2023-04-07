---
layout: page
title: Thennant Lim's Project Portfolio Page
---

### Project: MediConnect

MediConnect is a desktop address book application used for managing patients and doctors information.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=thennant&breakdown=true)

* **Project management**:
    * Contributed to the release of `v1.2` - `v1.4` on the team's repository on Github
    * Contributed to fixing some of the PE-D bugs for `v1.4`

* **Enhancements to existing features**:
    * Updated the delete command 
      * What was changed: The command allowed the user to delete by name in v1.2. This was later change to allow the user to delete by nric in v1.3.
      * Justification: Originally, the delete command deleted the person by index number in the list. This was fine by it can be difficult to search for the person
        if the list becomes very long. The team then decided to delete the person by name in v1.2 initially. In v1.3, the team then decided that we should
        delete by nric so that there is no need to check for duplicated person in a list since everyone has a unique nric number.
      * Highlights: The feature does not affect any of the existing command and now has a similar command input format as all the other commands

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `delete`.
    * Developer Guide:
        * Updated documentation for use cases.
        * Updated diagrams for the feature `delete`.
        * Added implementation details of the `delete` feature.

* **Community**:
    * Reported bugs and suggestions for other teams in the class during the PE-Dry run



