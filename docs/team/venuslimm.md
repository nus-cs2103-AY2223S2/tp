---
layout: page
title: Lim Jia Yi Venus's Project Portfolio Page
---

### Project: PlanEase

PlanEase is designed for users who work as event planners.
It provides a centralised platform for them to manage their work, contacts, and events information, making the process of searching for stakeholders and keeping track of details easier and more efficient.

Given below are my contributions to the project.

* **New Feature**: Added an `addevent` command that adds an event ([#33](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/33))
  * What it does:
    * Allows user to add an event with the given name, start date time, and end date time.
  * Justification:
    * With this feature, the user is able to manage their events on this application by simply adding the events they are involved in or the events they wish to keep track.
    * This allows the user to manage their contacts and events in 1 single application.

* **New Feature**: Added a `sortevent` command that sorts the current list of events displayed ([#69](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/69))
  * What it does:
    * Allows user to sort the current list of events displayed on the application.
  * Justification:
    * With this feature, the user is able to handle their list of events efficiently in a logical, neater, and consistent order, by sorting the events by either their names or date times.
  * Highlights:
    * The user is given the flexibility to sort the events based on the events' names (in ascending/descending order), or events' start date times (in ascending order), or events' end date times (in ascending order).
    * If the user forgets what command to enter to sort the events by a certain type, the user can enter `sortevent` to list all the possible valid inputs.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=venuslimm&breakdown=true)

* **Enhancements implemented**:
  * Updated the application's UI ([#77](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/77))
    * Changed the colours of the UI.
    * Increased the size of `Contacts` and `Events` labels.

* **Project Management**:
  * Released [v1.2.1](https://github.com/AY2223S2-CS2103-W16-3/tp/releases/tag/v1.2.1) on GitHub.

* **Contributions to the Developer Guide**
  * Design section: _**Model component**_ ([#64](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/64))
    * Updated information on Model to include the newly added `Event` objects.
    * Redrew [class diagram for Model](https://github.com/AY2223S2-CS2103-W16-3/tp/blob/master/docs/images/ModelClassDiagram.png).
  * Implementation section: _**Add event feature**_ section ([#70](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/70))
    * Added information on how `addevent` command works.
    * Drew [sequence diagram for addevent](https://github.com/AY2223S2-CS2103-W16-3/tp/blob/master/docs/images/AddEventSequenceDiagram.png).
  * Implementation section: _**Sort events feature**_ section ([#70](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/70))
    * Added information on how `sortevent` command works.
    * Drew [sequence diagram for sortevent](https://github.com/AY2223S2-CS2103-W16-3/tp/blob/master/docs/images/SortEventSequenceDiagram.png).
  * Appendix section: _**Instructions for manual testing**_
    * Updated _**Launch and shutdown**_ ([#85](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/85)).
    * Updated _**Saving data**_ ([#122](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/122)).
    * Added _**Adding an event**_ ([#122](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/122)).
    * Added _**Sorting event list**_ ([#122](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/122)).
  * Appendix section: _**Planned Enhancements**_
    * Added _**Person name will not be case-sensitive**_ ([#122](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/122)).

* **Contributions to the User Guide**
  * _**Quick start**_ section ([#73](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/73), [#85](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/85))
    * Updated to match our current application.
  * _**Adding a new event**_ section ([#31](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/31))
  * _**Sorting events**_ section ([#73](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/73))

* **Contributions to team-based tasks**
  * Updated [README.md](https://github.com/AY2223S2-CS2103-W16-3/tp/blob/master/README.md) and [index.md](https://github.com/AY2223S2-CS2103-W16-3/tp/blob/master/docs/index.md) badges.
  * Added some user stories onto GitHub.
  * Crafted all the user stories with the team.
  * Crafted some use cases with the team.
  * Worked on the demo video with the team.

* **Review/mentoring contributions**
  * Reviewed and commented non-trivial comments on the following PRs: [#4](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/4), [#10](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/10), [#39](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/39), [#48](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/48), [#55](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/55), [#56](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/56), [#63](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/63), [#68](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/68), [#112](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/112), [#119](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/119), [#130](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/130), [#132](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/132).

* **Contributions beyond the project team**
  * Reported 20 bugs for Team AY2223S2 CS2103T-W11-4: [PE-D Issues Link](https://github.com/venuslimm/ped/issues).
