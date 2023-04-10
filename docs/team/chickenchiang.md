---
layout: page
title: Joshua Chiang's Project Portfolio Page
---

### Project: Team Builder

Team Builder is a desktop personal contacts book application used by the user to form a team for any event. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: TeamTags
  * What it does: TeamTags allow for users to add a person to a team. It also allows you to see the teams a contact is in at a glance.
  * Justification: This is directly in line with our product description.
  * Highlights: This feature is not implemented optimally for user experience. Currently, in order to add a person to the team, the user has to edit a contact and type in full team names manually. Additionally, adding a person to a team requires the user to enter all the teams the person already belong to, on top of the new team we wish to add them in.
  * Credits: TeamTags takes inspiration for the Tags class from AB3 and heavily mirrors the methods it uses there.

* **New Feature**: JsonAdaptedTeams and JsonAdaptedNames
  * What it does: For TeamBuilder to both read and store Teams and their respective members from/into the data file in JsonFormat. 
  * Justification: This feature is necessary for TeamBuilder to reach a minimum viable product stage, as teams will not be lost after users exit the program.
  * Credits: This feature heavily references the JsonAdaptedPerson class provided by AB3.

* **New Feature**: UniqueTeamList
  * What it does: Allows for TeamBuilder's model component to hold a list of Teams. 
  * Justification: This feature is necessary for TeamBuilder to function and store teams created by the user.
  * Credits: This feature heavily reference the UniquePersonsList from AB3.

* **New Feature**: Allow contacts to have optional fields
  * What it does: Allows for users to add or edit a contact such that certain fields are empty, e.g. address, phone number.
  * Justification: TeamBuilder is meant for students primarily. Outside a corporate context, it is unlikely that our users will have/require all details regarding a contact.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=T17-1&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)

* **Project management**:
  * Managed TeamBuilder releases for [v1.2](https://github.com/AY2223S2-CS2103T-T17-1/tp/releases/tag/v1.2), [v1.2.1](https://github.com/AY2223S2-CS2103T-T17-1/tp/releases/tag/v1.2.1), and [v1.3](https://github.com/AY2223S2-CS2103T-T17-1/tp/releases/tag/v1.3) on Github.
  * Creation of milestones for workflow control.

* **Enhancements to existing features**:
  * Updated GUI for tags, team tags, skill tags, and member tags.
  * Changed GUI such that person empty person fields are hidden and take up no space.

* **Documentation**:
  * User Guide:
    * Added future features section.
    * Added sample screen shot of Ui as well as description for users to understand what they are looking at.
  * Developer Guide:
    * Documented AddCommand implementation and created an activity diagram to aid understanding.
    * Added Future Enhancement section.
    * Added documentation for how optional fields are achieved for person class. 

* **Bug fixes**:
  * Fixed Ui issue where the TeamList Ui is cutoff when the TeamBuilder window is resized too small.
  * Fixed bug where the wrong error is displayed when an invalid index is passed as an argument.

* **Community**:
  * Contributed to forum discussions (examples: [#232](https://github.com/nus-cs2103-AY2223S2/forum/issues/232), [#239](https://github.com/nus-cs2103-AY2223S2/forum/issues/239), [#286](https://github.com/nus-cs2103-AY2223S2/forum/issues/286))
  * Reported bugs and suggestions for other teams in the class (examples: To be added)


