---
layout: page
title: Yong Jing's Project Portfolio Page
---

### Project: FriendlyLink

FriendlyLink is a personnel information management tool designed for Voluntary Welfare Organisations to keep elderly and volunteer records, and pair them up efficiently and effectively.

Given below are my contributions to the project.

* **New Feature**: Added the ability to pair up elderly and volunteers.
    * What it does: Allows elderly to be paired up with volunteers in the system
    * Justification: This feature allows the administrative staff to pair up elderly and volunteers so that they can take care of assigned elderly and keep them company.
    * Highlights: This feature checks if the members to be paired exists in FriendlyLink, and also prevents duplicate records from being added.

* **New Feature**: Added the ability to see summary statistics.
  * What it does: Allows users to see summary statistics 
  * Justification: This feature allows the administrative staff to make sure each elderly member is taken care of and that the workload of volunteers is allocated evenly, recruiting more volunteers if required
  * Highlights: This feature is versatile and can be used with the `find` command to see statistics on a filtered list (E.g. See total number of volunteers in the north, or total number of elderly with high risk levels.)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=gohyongjing&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Released [v1.2.1](https://github.com/AY2223S2-CS2103T-W12-1/tp/releases/tag/v1.2.1), [v1.3.1](https://github.com/AY2223S2-CS2103T-W12-1/tp/releases/tag/v1.3.1)

* **Documentation**:
    * User Guide:
      * `Pair` feature
      * `Unpair` feature
      * `stats` feature
    * Developer Guide:
      * Explain design of pair and unpair command and their rationale
      * Explain design of `stats` command, and included a class diagram to show how components of the summary interacts.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#76](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/76), [\#32](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/32), [\#117](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/117)

* **Other tasks**:
  * Maintained [meeting minutes](https://docs.google.com/document/d/15eB2sp73eeeCPjcQS6cQhXyVL8q3mPB8O3KFBov5dDk/edit?usp=sharing)
  * Improve user guide's quickstart by adding more details
  * Update name of product in developer guide's architecture section

* **Extracts from user guide**

    1. Ensure you have [Java 11](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html) installed in your Computer.

    1. Download the latest `friendlylink.jar` from [here](https://github.com/AY2223S2-CS2103T-W12-1/tp/releases).

    1. Move the `friendlylink.jar` to an empty folder where you want FriendlyLink to store information.

    1. Double-click on the `friendlylink.jar` file. If the app does not open, follow the following steps instead.
        * Open a terminal
            * On **Windows**: Click Start and search for `Command Prompt`
            * On **macOS**: Open Launchpad and search for `terminal`
        * Move into the folder you are keeping FriendlyLink by entering `cd FILE/PATH/TO/FRIENDLYLINK` into the terminal
        * Open the app by entering `java -jar friendlylink.jar` into the terminal.


* Extracts from developer guide

    The `stats` command displays summary statistics about FriendlyLink, such as the total number of elderly, volunteers and unpaired persons.

    It is implemented using the `Summary` and `AggregateFunction` class.

    The `AggregateFunction`
    * describes a particular statistic of FriendlyLink with a number.
    * is an abstract class that requires concrete classes to override the `getDescription()` and `getResult()` method.

    The `Summary` object
    * formats the results to be displayed to the user.
    * takes in 0 or more `AggregateFunction`s to show their description and results.
  <img src="images/developerGuide/StatsCommandClassDiagram.png" width="500" />
