---
layout: page
title: See Chen Jiarui's Project Portfolio Page
---

### Project: Team Builder

Team Builder is a desktop personal contacts book application used by the user to form a team for any event. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Show
  * What it does: allows the user to list all people under certain specified teams.
  * Justification: Users might want to list all people under a certain team they created. Rather than checking everyone's person card, they can just use `show` followed by the target team name. This is especially useful when users want to compare a team's skill requirements with skills possessed by the members.
  * Highlights: Able to show all members who belong to multiple teams, use `show` followed by names of multiple teams that are separated by spaces.

* **New Feature**: Major
  * What it does: allows the user to add a person with a given major.
  * Justification: This is useful when a user want to create a team of students with specified major. A major field also help the user to create cross-disciplinary teams.
  * Highlights: This enhancement affects existing commands such as `find` `add` and `edit`.

* **New Feature**: GUI for Team List
  * What it does: adds a TeamListPanel and a TeamCard to allow the user to navigate through the existing teams.
  * Justification: This feature improves the product significantly because it is necessary for a user to see the existing teams to use our product.
  * Highlights: This feature allows the team list to automatically update regarding the command `create` and `remove` for teams and `edit` for adding people into teams.

* **Enhancements to existing features**:
  * Update `find` command so that the user can use `find` to search for people by their names, skills (tags), and major.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=chen-jerry-junior&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)

* **Testing**:
    * Add TeamBuilder to facilitate testing of Team
    * Update PersonUtil, EditPersonDescriptorBuilder, TypicalPersons, PersonBuilder to facilitate testing for Major and TeamTag
    * Add unit tests for ShowCommandTest, ShowCommandParserTest
    * Add unit tests for RemoveCommandTest, RemoveCommandParserTest
    * Add unit tests for TeamTest, TeamContainKeywordsPredicateTest
    * Add new unit tests for FindCommandTest, FindCommandParserTest
    * Add new unit tests for AddCommandTest, AddCommandParserTest

* **Bug Fixing**:
  * Modified the error message of command `add` `edit` `remove` `show` `sort` to consist with the description in the UserGuide.
  * Import prefix of TeamTag in add command parser and added comparison of TeamTag in edit command to enable `add` and `edit` on TeamTags.

* **Project management**:
  * Managed TeamBuilder releases for [v1.3.1](https://github.com/AY2223S2-CS2103T-T17-1/tp/releases/tag/v1.3.1) on GitHub.

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `show`
        * Updated documentation for the feature `find`
        * Replaced all "address book" with "TeamBuilder"
    * Developer Guide:
        * Added implementation details of the `show` feature.
        * Added activity diagram for show command and show command parser
        * Added manual testing of `show` and `find`

* **Community**:
  * Reported bugs and suggestions for other teams in the class (for PE-D: [17 issues](https://github.com/chen-jerry-junior/ped/issues))
