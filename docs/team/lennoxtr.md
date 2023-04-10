---
layout: page
title: Khang Tran's Project Portfolio Page
---

#### Table of Contents
- [Project: Le Tracker](#project-le-tracker)
- [Summary of Contributions](#summary-of-contributions)
  - [Code Contribution](#code-contribution)
  - [Tag/ Untag Feature](#tag-untag-feature)
  - [Export/ Import Feature](#export-import-feature)
  - [Documentation](#documentation)
  - [Team-based Tasks](#team-based-tasks)

### Project: Le Tracker

**Le Tracker** makes it easy to measure your overall study progress by tracking how much lecture content you have covered across various modules. **More** than just a simple to-do list app, **Le Tracker** blends the **efficiency** of a command line interface with the **elegance** of modern graphical user interface.

### Summary of Contributions

#### Code Contribution
You can refer to my individual code contribution at this [link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=lennoxtr&breakdown=true).

#### Tag/ Untag Feature

**Originally**, the user could only apply tags to a newly added person and remove tags by editing that person. As AB3 is adapted to a content tracker, the old system is no longer suitable. Users may label contents according to their own needs as they study.

**Enahancements** made were modification to the tagging feature of AB3 to allow the user to add and remove multiple tags at the same time from modules, lectures, and video, while making sure that each tag is only applied once. Tagging and untagging can be done after the content is added to the tracker so that user can use the tag to categorize and organize the studying content effectively.

#### Export/ Import Feature

I implemented the exporting and importing features of Le Tracker:

- **Exporting modules**, so that users don't have to worry about losing the data. sers may also want to export modules to a file, which serves as an archive, so that they can keep track of the productivity level and learning process after the semester ends.

- `**Importing modules**, so that users may revise specific concepts from the modules they previously took. User can also use the feature to track productivity level during a semester. 

To implement said features, an `Archive` class was adapted from AB3's JSONStorage.

#### Documentation

- **User Guide**
  - Update the UG documentation for the `tag`, `untag` commands 
- **Developer Guide**
  - Update the DG "Implementation" section for the `tag`, `untag`, `export`, `import` commands
  - Update the DG user stories, use cases, and manual testing instructions for the `tag`, `untag`, `export`, `import` commands

#### Team-Based Tasks

- Created v1.4 Issues
- Bugs Reported: [link](https://github.com/AY2223S2-CS2103-F10-2/tp/issues?q=is%3Aissue+author%3Alennoxtr+label%3Atype.Bug+)
- PRs reviewed: [#393](https://github.com/AY2223S2-CS2103-F10-2/tp/pull/393)