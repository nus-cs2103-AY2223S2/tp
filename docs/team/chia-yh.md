---
layout: page
title: Chia Yu Hong's Project Portfolio Page
---

### Project: SOCket

SOCket is a desktop application for NUS Software Engineering Students to manage the contact information of their peers and professors. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: `GitHubProfile` class to represent the GitHub username of a `Person` [\#74](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/74)
  * What it does: Stores the GitHub username of a `Person`, and validates the input
  * Justification: Allows the GitHub username of contacts in `SOCket` to be stored, allowing users to easily retrieve the information needed to find the contact on GitHub
  * Highlights: `VALIDATION_REGEX` ensures the validity of any input stored as a `GitHubProfile` (does not guarantee that it exists, only that it is valid)
  * Credits: *{Input validation based on form validation from [Join GitHub](https://github.com/join)}*

* **New Feature**: `Language` class to represent proficiency in a programming language of a `Person` [\#75](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/75)
  * What it does: Stores the programming languages a `Person`, and validates the input
  * Justification: Allows the programming language proficiencies of contacts in `SOCket` to be stored, allowing users to easily view the proficiencies of a contact
  * Highlights: Simple input validation using `VALIDATION_REGEX` (does not guarantee existence of input as a programming language)
  * Credits: *{-}*

* **New Feature**: `undo` and `redo` commands [\#99](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/99)
  * What it does: Allows users to undo/redo changes
  * Justification: Users are able to revert/restore unintended changes to `SOCket`
  * Highlights: Stores the states of `SOCket` to allow for a simple implementation of the undo/redo functionality
  * Credits: *{This feature was implemented as proposed in [AddressBook-Level3](https://se-education.org/addressbook-level3/DeveloperGuide.html#proposed-undoredo-feature)}*

* **New Feature**: `Project` class to represent a project (in collaboration with [@dillongoh](https://github.com/dillongoh)) [\#130](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/130)
  * What it does: Allows users to store information about a project, including details regarding the project repository, deadline, and members
  * Justification: Users are able to easily manage and retrieve information regarding the project
  * Highlights: `VALIDATION_REGEX` and `isValid*` methods for fields ensure validity of inputs stored in `Project` instance (does not guarantee that `ProjectRepoHost` or `ProjectRepoName` exist, only that they are valid); `Project` instances are stored in an additional list in the `socket.json` data file, with its own copies of the `Person` instances that are members of the `Project`; references to the `Person` instances are restored upon reading in the `socket.json` data file
  * Credits: *{-}*

* **New Feature**: `deletepj` command to delete existing projects [\#150](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/150)
  * What it does: Allows users to delete existing projects
  * Justification: Users are able to remove finished projects from `SOCket`
  * Highlights: `JUnit` tests to test command functionality
  * Credits: *{-}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=chia-yh&breakdown=true)

* **Project management**:
  * Set up the GitHub [team organization](https://github.com/AY2223S2-CS2103T-T12-4)/[repository](https://github.com/AY2223S2-CS2103T-T12-4/tp)
  * Set up and helped maintain the [issue tracker](https://github.com/AY2223S2-CS2103T-T12-4/tp/issues)
  * Set up GitHub Projects to manage [User Stories](https://github.com/orgs/AY2223S2-CS2103T-T12-4/projects/2)
  * Updated [README](https://github.com/AY2223S2-CS2103T-T12-4/tp/blob/master/README.md) page to match project [\#43](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/43)
  * Set up milestones for [v1.1](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/1), [v1.2](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/2), [v1.2b](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/5), [v1.3](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/3), [v1.3b](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/6), [v1.4](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/4)
  * Closed milestones [v1.1](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/1), [v1.2](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/2), [v1.2b](https://github.com/AY2223S2-CS2103T-T12-4/tp/milestone/5)

* **Enhancements to existing features**:
  * Updated `Person` class with new fields utilising newly added classes, modify behavior to allow fields to be empty [\#76](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/76)
  * Updated `edit` command to allow `Language` instances to be added cumulatively, instead of being replaced by new instances [\#82](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/82)
  * Converted link in `HelpWindow` shown by `help` command into a Hyperlink to allow users to more easily access the user guide [\#103](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/103)
  * Refactored code to remove references to `AB-3` [\#116](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/116) 
  * Updated `VALIDATION_REGEX`, `equals` in `Name` to enhance duplicate detection for `Person` instances [\#146](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/146)
  * Updated `VALIDATION_REGEX` in `Tag` to limit length [\#147](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/147)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add` and `edit` [\#44](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/44)
    * Did minor tweaks to existing documentation [\#44](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/44)
    * Added documentation for the features `undo` and `redo` [\#104](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/104)
  * Developer Guide:
    * Added documentation for target user profile, value proposition, user stories [\#53](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/53)
    * Added use cases `UC01`, `UC02`, `UC09` [\#54](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/54)
    * Updated glossary [\#55](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/55)
    * Updated non-functional requirements [\#56](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/56)
    * Updated implementation of `undo`/`redo` and credit proposed implementation in `AB-3` [\#106](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/106)
    * Updated existing UML diagrams to reflect changes in implementation of `Model` and `Storage` architectures, current implementation of `undo` and `redo`, as well as changes from code refactoring [\#164](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/164)

* **Community**:
  * PRs reviewed (with non-trivial review comments): `to be added soon`
  * Contributed to forum discussions (examples: `to be added soon`)
  * Reported bugs and suggestions for other teams in the class (examples: `to be added soon`)
  * Some parts of the history feature I added was adopted by several other classmates (`to be added soon`)

* **Tools**:
  * `to be added soon`
