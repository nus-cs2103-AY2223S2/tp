---
layout: page
title: Hingen's Project Portfolio Page
---
### Project: Le Tracker

**Le Tracker** makes it easy to measure your overall study progress by tracking how much lecture content you have covered across various modules. **More** than just a simple to-do list app, **Le Tracker** blends the **efficiency** of a command line interface with the **elegance** of modern graphical user interface.

### Summary of Contributions

#### Table of Contents

- [Table of Contents](#table-of-contents)
- [Code and Features](#code-and-features)
- [User Guide](#user-guide)
- [Developer Guide](#developer-guide)
- [Team-Based Tasks](#team-based-tasks)
- [Review/Mentoring](#reviewmentoring)
- [Misc](#misc)

#### Code and Features

Check out the code contributions [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=hingen&breakdown=true)

- **Adapting Model component**\
  Le Tracker requires a hierarchical structure for it's model with `Module`s which each contains a list of `Lecture`s and `Lecture`s which each contains a list of `Video`s. This required major changes to be made to the AB3 **Model** component which is non-hierarchical, utilising `Person` objects to store person contact details.
- **Adapting Storage component**\
  The changes made to the **Model** component required that the **Storage** component be updated as well. As the one that adapted the **Model** component, I had to handle the updating of the **Storage** component as well.
- **Adapting Add and Edit Command**\
  Due to the hierarchical structure of the **Model** component, we needed to redesign our commands to match the new structure. `add` and `edit` needed to be updated to be able to add and edit modules, lectures, or videos depending on what arguments are included in the user input.
- **Implementing an Event System**\
  Due to the hierarchical structure of the **Model** component, the implementation of various features started to become rather difficult with many bugs popping up during integration. To limit the number of dependencies needed to resolve these bugs, I implemented a `TrackerEventSystem` class designed using the Observer design pattern.

#### User and Developer Guide

- Add Command documentation
- Edit Command documentation
- UG Design section
- UG Command Syntax
- UG Argument Formats
- UG Command Summary
- Standardise UG Format

#### Team-Based Tasks

- Created GitHub Organisation
- Created Team Repository
- Created v1.1 Milestone and Issues
- Bug Reported: #TODO: List out some bug reports

#### Review/Mentoring

- PRs reviewed:

#### Misc

- Forum Contributions:
- Bugs Reported:
