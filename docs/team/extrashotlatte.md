---
layout: page
title: Sean Wong's Project Portfolio Page
---

### Project: Paidlancers

Paidlancers is a desktop app designed to help freelancers streamline their event management tasks. It is the brainchild me and my team, who wanted to simplify and automate the administrative side of freelancing.

The app is optimized for use through a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). Some of its features include adding and linking contacts to events, tracking rates, marking events as done, creating new events, viewing event lists and revenue, and more.

By centralizing all event-related information and tasks in one place, Paidlancers aims to increase productivity and help freelancers focus on their craft.

Given below are my contributions to the project.

* **New Feature**:​
  * `Link Contact` Added ability to link contacts to events. (Pull Request [#88](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/88))
      * What it does: Allows the user to link contacts to events.
      * Justification: This feature improves the product significantly because a user can keep track of all the contacts they have and link them to events. This is useful for users who want to keep track of all their contacts and link them to events.
      * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and the creation of new ones.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=extrashotlatte&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)

* **Project management**:
    * Managed release of `v1.2.1` on GitHub.
    (Pull Request [#135](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/135))
    (Release [v1.2.1](https://github.com/AY2223S2-CS2103T-T11-3/tp/releases/tag/v.1.2.1)).
    * Setup CI and codecov (Pull Request [#52](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/52)).
    * Smoke tested JAR trial release on Windows Operating System
    (Issue [#36](https://github.com/AY2223S2-CS2103T-T11-3/tp/issues/136)).

* **Enhancements to existing features**:
  * Enhanced the comparisons of `event` class
  (Pull Request [#86](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/86)).
    * What it does: Enhanced comparison of `events` to account for different cases.
    * Justification: An event with the same name but of a different timing should not be equal to each other.
    Reason being that the same event can happen twice, both at different timings.

* **Documentation**:
    * User Guide:
      * Added introduction section to the user guide
      (Pull Request [#127](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/127)).
      * Restructured the presentation of the user guide for clarity
      (Pull Request [#127](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/127)).
      * Added logo to the user guide (Pull Request [#127](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/127)).
      * Added section on how to use the user guide
      (Pull Request [#127](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/127)).
      * Enhanced first-time-user story (Pull Request [#142](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/142)).
      * Added additional command guides and examples (Pull Request [#151](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/151)).
      * Sorted UG and added callouts (Pull Request [#165](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/165), [#177](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/177), [#242](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/242)).
    * Developer Guide:
      * Added implementation details of the `linkcontact` feature
      (Pull Request [#137](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/137)).
      * Added Activity Diagram for `linkcontact` feature
        (Pull Request [#170](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/170)).

* **Community**:
    * PRs reviewed and merged:
      * [#9](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/9),
      [#10](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/10),
      [#15](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/15),
      [#20](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/20),
      [#21](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/21),
      [#34](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/34),
      [#37](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/37),
      [#70](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/70),
      [#85](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/85),
      [#98](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/98),
      [#100](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/100),
      [#125](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/125),
      [#129](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/129),
      [#141](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/141),
      [#167](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/167),
      [#168](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/168),
      [#169](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/169),
      [#232](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/232),
      [#241](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/241)

    * PRs reviewed (with non-trivial review comments):
      * [#31](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/31),
      [#35](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/35),
      [#104](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/104),
      [#132](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/132),
      [#133](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/133),
      [#140](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/140)

    * Bugs found and raised:
      * [#90](https://github.com/AY2223S2-CS2103T-T11-3/tp/issues/90)

    * Bugs fixed:
      * [#225](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/225) UG Bug
      * [#233](https://github.com/AY2223S2-CS2103T-T11-3/tp/pull/233) Wrong linkage of contact

    * Enhancement raised:
      * [#131](https://github.com/AY2223S2-CS2103T-T11-3/tp/issues/131)

* **Tools**:
    * to be added soon
