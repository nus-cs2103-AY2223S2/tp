---
layout: page
title: Stanley Neoh's Project Portfolio Page
---

### Project: Mycelium

**Mycelium** is a desktop application aimed at helping **freelance web developers
manage clients and projects** from multiple online sources like [Upstack](https://upstackhq.com/),
[Fiverr](https://www.fiverr.com/), and [Toptal](https://www.toptal.com/). All interactions
with **Mycelium** are done through text commands or HotKeys, enabling one to efficiently
manipulate data while availing oneself to the convenience of viewing, offered by the
Graphical User Interface (GUI) created with JavaFX. The application is written in Java.

Below are my contributions to the project:
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=W14&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=StanleyNeoh&tabRepo=AY2223S2-CS2103T-W14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements implemented**: I added the `UiEventManager` class and its related classes to handle the keyboard events that occur in the UI, allowing the user to perform keyboard shortcuts such as:

  * navigate the UI with `Ctrl-J`, `Ctrl-K`, `Ctrl-L`, `Ctrl-S`
  * toggling the command box between search mode and command mode to quickly find projects and clients with `Ctrl-F`
  
  I also added hooks to the command response through the `UiAction` interface. This enables commands to perform actions to the UI such as automatically switching between the project tab and the client tab depending on which tab is relevant to the command executed.
  
  I added the `Mode` class for the command box, enabling the command box to be stateful and change its behaviour depending on its `Mode`.

* **Testing**: I am responsible for testing the `UiAction`, `UiEvent`, `UiEventsManager`, and `Mode` classes and its relevant dependencies.

* **Project management**: I helped enforce the proper use of labels and milestones, ensured that the team is on task with issues.

* **Documentation**:
  * **Contributions to UG**: 
    I am responsible for writing up the following sections of the User Guide

    * [HotKeys](https://ay2223s2-cs2103t-w14-1.github.io/tp/UserGuide.html#hotkeys) 

    I also assisted in writing up the following sections of the User Guide

    * [Ui Overview](https://ay2223s2-cs2103t-w14-1.github.io/tp/UserGuide.html#ui-overview)

  * **Contributions to DG**: 
    I am responsible for writing up the following sections of the Developer Guide

    * [Hotkeys with UiEvents](https://ay2223s2-cs2103t-w14-1.github.io/tp/DeveloperGuide.html#hotkeys-with-uievents)
    * [Command Box](https://ay2223s2-cs2103t-w14-1.github.io/tp/DeveloperGuide.html#command-box)
    * [Command Handling](https://ay2223s2-cs2103t-w14-1.github.io/tp/DeveloperGuide.html#command-handling)

* **Contributions to team-based tasks**: I assisted in reviewing and merging PRs, and assisted by making the necessary changes to PRs to ensure that they are up to standard.

* **Review/mentoring contributions**:
  * PRs reviewed:
    [#29](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/29),
    [#30](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/30),
    [#34](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/34),
    [#38](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/38),
    [#75](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/75),
    [#81](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/81),
    [#82](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/82),
    [#85](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/85),
    [#89](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/89),
    [#118](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/118),
    [#119](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/119),
    [#133](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/133),
    [#139](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/139),
    [#142](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/142),
    [#143](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/143),
    [#148](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/148),
    [#164](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/164),
    [#166](https://github.com/AY2223S2-CS2103T-W14-1/tp/pull/166)

