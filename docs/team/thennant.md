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
    * Contributed to the release of `v1.1` - `v1.4` on the team's repository on Github
    * Contributed to fixing of PE-D bugs for `v1.4`
    * Planned the team's distribution of work during team meetings

* **Enhancements to existing features**:
    * Updated the delete command 
      * What was changed: The command allowed the user to delete by name in v1.2. This was later change to allow the user to delete by nric in v1.3.
      * Justification: Originally, the delete command deleted the person by index number in the list. This was fine by it can be difficult to search for the person
        if the list becomes very long. The team then decided to delete the person by name in v1.2 initially. In v1.3, the team then decided that we should
        delete by nric so that there is no need to check for duplicated person in a list since everyone has a unique nric number.
      * Highlights: The feature does not affect any of the existing command and now has a similar command input format as all the other commands.

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `delete` (Pull request [#49](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/49)).
    * Developer Guide:
        * Updated documentation for use cases (Pull request [#63](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/63)).
        * Updated diagrams for the all existing features. (Pull request [#131](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/131))
        * Added implementation details of the `delete` feature (Pull request [#47](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/47)).

* **Contributions to team-based tasks**:
    * Fix feature flaws (Pull request [#123](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/123), [#125](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/125)).
    * Reviewed and merged PRs (Pull request [#121](https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/121)).

* **Community**:
    * Reported bugs and suggestions for other teams in the class during the PE-Dry run

* **Tools**:
    * IntelliJ IDEA
    * Sourcetree
    * GitHub


