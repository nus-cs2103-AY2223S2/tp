---
layout: page
title: See Wei Xun's Project Portfolio Page
---

### Project: Team Builder

Team Builder is a desktop app primarily for cross-faculty students to manage their contacts and build a multidisciplinary team based on soft skills and technical skills. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Sort Person's list
  * Justification:
    * Useful for users to organize their unordered contact list, makes selecting team members easier
    * Allows users to find who has the most or least number of skill tags easily
  
  * Highlights:
    * Able to sort Person's list using `sort` command 
    * Implemented sorting by Person's number of tags using `tcount` as the parameter to `sort` command
    * Able to sort in both orders, descending (`desc` as parameter) or ascending (`asc` as parameter) 
  
* **New Feature**: Create/Remove Team and TeamList
  * Justification:
    * For users to create/remove team and assign team members.
    * TeamList for users to track the status/description of all teams created
    
  * Highlights:
    * Allows users to specify Team's name, description and skill requirements (as tags)
    * Able to create teams using `create` command and remove teams using `remove` command
    * After a new team is added in TeamList, users are able to add members to team (by attach corresponding team tag to the Person)


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=swx0&breakdown=true)

* **Testing**:
  * Add unit tests for CreateCommandTest, CreateCommandParserTest
  * Add new unit tests for ParserUtilTest, TeamBuilderParserTest
  * Add unit tests for SortCommandTest, UniqueTeamListTest

* **Bug Fixing**:
  * Added exception handling for sort command parameters
  * Added a note in User Guide under the sort command section to specify the need to execute `list` before `sort` if a `find` command was performed before
  * Add clarifying note in User Guide to specify which SORT_BY could be used
  * Add clarifying note in User Guide to specify that the Add Command may not add persons in chronological order.

* **Project management**:
  * Generate PE-D bugs report
  * Added labels to Pull Requests and Issues and create v1.4 milestone

* **Team based tasks**:
  * Brainstorming of target users, user stories, features to implement
  * Update post-mortem v1.2

* **Enhancements to existing features**:
  * Existing `edit` and `delete` commands
    * Edit person's team tags / Delete person would cascade changes to TeamList
  * Existing `add` and `edit` commands
    * Add / Edit person with Team tags only if teams created beforehand

* **Documentation**:
  * User Guide:
    * Add sort command section (command format, examples)
    * Add 'Create team', 'Remove a team' and 'Add a person to a team' sections (command format, examples)
    * Add screenshots for Find, Show and Create commands
  * Developer Guide:
    * Update target user profile, value proposition, user stories, use cases
    * Add implementation and design considerations for sort function
    * Add activity diagram for sort command and sort command parser
    * Update Model component diagram to add Team and TeamList, update attributes of Person
    * Add description for create team feature
    * Remove data archiving section

* **Community**:
  * Contributed to forum discussions ([\#303](https://github.com/nus-cs2103-AY2223S2/forum/issues/303))
  * Reported bugs and suggestions for other teams in the class (for PE-D: [\#1](https://github.com/swx0/ped/issues/1) [\#2](https://github.com/swx0/ped/issues/2) [\#3](https://github.com/swx0/ped/issues/3) [\#4](https://github.com/swx0/ped/issues/4) [\#5](https://github.com/swx0/ped/issues/5) [\#6](https://github.com/swx0/ped/issues/6))

* _{you can add/remove categories in the list above}_
