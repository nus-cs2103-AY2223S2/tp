---
layout: page
title: Hingen's Project Portfolio Page
---
### Project: Le Tracker

**Le Tracker** makes it easy to measure your overall study progress by tracking how much lecture content you have covered across various modules. **More** than just a simple to-do list app, **Le Tracker** blends the **efficiency** of a command line interface with the **elegance** of modern graphical user interface.

### Summary of Contributions

#### Table of Contents

- [Code and Features](#code-and-features)
- [User and Developer Guide](#user-and-developer-guide)
- [Team-Based Tasks](#team-based-tasks)
- [Review/Mentoring](#reviewmentoring)
- [Misc](#misc)

#### Code and Features

Check out the code contributions [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=hingen&breakdown=true)

- **Adapting Model Component**\
  Le Tracker requires a hierarchical structure for it's model with `Module`s which each contains a list of `Lecture`s and `Lecture`s which each contains a list of `Video`s. This required major changes to be made to the AB3 **Model** component which is non-hierarchical, utilising `Person` objects to store person contact details.
- **Adapting Storage Component**\
  The changes made to the **Model** component required that the **Storage** component be updated as well. As the one that adapted the **Model** component, I had to handle the updating of the **Storage** component as well.
- **Adapting Add and Edit Command**\
  Due to the hierarchical structure of the **Model** component, we needed to redesign our commands to match the new structure. `add` and `edit` needed to be updated to be able to add and edit modules, lectures, or videos depending on what arguments are included in the user input.
- **Implementing an Event System**\
  Due to the hierarchical structure of the **Model** component, the implementation of various features started to become rather difficult with many bugs popping up during integration. To limit the number of dependencies needed to resolve these bugs, I implemented a `TrackerEventSystem` class designed using the Observer design pattern.

#### User and Developer Guide

- **Add and Edit Command Documentation**
  - Update the UG documentation for the `add` and `edit` commands
  - Update the DG "Implementation" section for the `add` and `edit` commands
  - Update the DG user stories, use cases, and manual testing instructions for the `add` and `edit` commands
- **UG Design**
  - Update "Design" section, "Architecture" subsection class diagram and information
  - Update "Design" section, "Model" subsection class diagram
  - Update "Design" section, "Storage" subsection class diagram and information
- **UG Command Syntax**
  - Create a "Command Syntax" section which is adapted from AB3's UG's command format notes
- **UG Argument Formats**
  - Create an "Argument Formats" section that documents the required format of each argument's values
- **Standardise UG Format**
  - Define the UG "Command Manual" section convention that all members are to follow

#### Team-Based Tasks

- Created GitHub Organisation and Team Repository
- Created v1.1 Milestone and Issues
- Define Agendas before each weekly meeting
- Bugs Reported: [link](https://github.com/AY2223S2-CS2103-F10-2/tp/issues?q=is%3Aissue+author%3Ahingen+label%3Atype.Bug+)
- PRs reviewed: [#109](https://github.com/AY2223S2-CS2103-F10-2/tp/pull/109), [#370](https://github.com/AY2223S2-CS2103-F10-2/tp/pull/370)

#### Misc

- Forum Contributions: [link](https://github.com/nus-cs2103-AY2223S2/forum/issues?q=is%3Aissue+commenter%3Ahingen+)
- Assisted team CS2103-W16-3, via email and [GitHub](https://github.com/nus-cs2103-AY2223S2/forum/issues/337), in resolving a Linux specific UI bug
