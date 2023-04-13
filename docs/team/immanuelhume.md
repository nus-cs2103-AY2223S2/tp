---
layout: page
title: Li Junyi's Project Portfolio Page

---

## Project: Mycelium

**Mycelium** is a desktop application aimed at helping **freelance web developers
manage clients and projects** from multiple online sources like [Upstack](https://upstackhq.com/),
[Fiverr](https://www.fiverr.com/), and [Toptal](https://www.toptal.com/). All interactions
with **Mycelium** are done through text commands or HotKeys, enabling one to efficiently
manipulate data while availing oneself to the convenience of viewing, offered by the
Graphical User Interface (GUI) created with JavaFX. The application is written in Java.

### Summary of Contributions

My overall code contributions can be seen
[here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=immanuelhume&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

**Enhancements implemented**

I contributed to two main components of the project - fuzzy search, and Project
CRUD.

1. Fuzzy search is a feature which allows users to search for something via a
   partial match, similar to file searching capabilities in most IDEs. I
   implemented the [main
   algorithm](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/util/Fuzzy.java)
   used, and helped to hook it up to the UI.
1. A core feature of Mycelium is to manage projects, which thus requires code
   for defining and manipulating a project. I implemented the [Project
   model](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/project/Project.java),
   and was responsible for the end-to-end CRUD functionalities of projects,
   including its integration into the current app's
   [Model](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/model/ProjectModel.java),
   [serialization into
   JSON](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/storage/JsonAdaptedProject.java),
   and other abstractions needed. I also implemented the command and
   functionality for [updating
   projects](https://github.com/AY2223S2-CS2103T-W14-1/tp/blob/master/src/main/java/mycelium/mycelium/logic/commands/UpdateProjectCommand.java).

**Testing**

I have contributed unit and integration tests for most aspects of the
application except the GUI. These include tests for:

* Project CRUD
* Commands and command parsers
* Fuzzy search algorithm
* JSON serialization

**Contributions to the UG**

I covered these sections for the UG:

* [Managing projects](https://ay2223s2-cs2103t-w14-1.github.io/tp/UserGuide.html#managing-projects)
* [Fuzzy search](https://ay2223s2-cs2103t-w14-1.github.io/tp/UserGuide.html#fuzzy-search)

I also wrote most of the frontmatter.

**Contributions to the DG**

I wrote the section explaining how [fuzzy search is
implemented](https://ay2223s2-cs2103t-w14-1.github.io/tp/DeveloperGuide.html#fuzzy-searching).
I also wrote the instructions for manual testing as well as most of the
frontmatter.

**Code reviews**

I have provided non-trivial code reviews (beyond just a LGTM) for these PRs:
[#62](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/62),
[#75](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/75),
[#99](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/99),
[#100](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/100),
[#120](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/120),
[#139](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/139),
[#141](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/141), and
[#175](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/175).

