---
layout: page
title: Song Yi, Ina's Project Portfolio Page
---

### Project: PlanEase

PlanEase is designed for users who work as event planners.
It provides a centralised platform for them to manage their work, contacts, and events information, making the process of searching for stakeholders and keeping track of details easier and more efficient.

Given below are my contributions to the project.

* **New Feature**: Added a `listevent` command that lists events. ([\#50](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/50))
  * What it does: allows the user to list all events.
  * Justification: This feature improves the product because a user can re-list all events after performing a `findevent` command that displays events of containing specified keyword(s).


* **New Feature**: Added a `delevent` command that allows the user to delete an event. ([\#39](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/39), [\#50](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/50))
  * What it does: allows the user to delete events (one event per use of the command).
  * Justification: This feature improves the product because a user can delete an event that they no longer want/need to keep track of.
  * Highlights: While this command is similar to that of `delete`, removing an event from the event list meant that any person(s) with the specified event tag had to have that event tag removed. This meant that the implementation had to check through all person(s) as well (i.e., not just those currently displayed).


* **New Feature**: Added an `editevent` command that allows the user to edit an event. ([\#55](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/55))
  * What it does: allows the user to edit events (one event per use of the command).
  * Justification: This feature improves the product because a user can edit an event's details (name, start date-time, end date-time) such that they are accurate.
  * Highlights: The addition of this command allows for users to edit the start date-time and the end date-time of an event non-concurrently, unlike `addevent` which requires the user to enter both fields concurrently. This meant that the implementation required ensuring that the start date-time was still earlier than the end date-time, such that the date-time range remains valid.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=inas0ng&breakdown=true)


* **Project management**:
  * Managed release `v1.2` on GitHub


* **Enhancements to existing features**:
  * Updated the `Name` and `EventName` class to set character limit ([\#66](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/66))
  * Updated the sample address book with more realistic data for first-time users ([\#72](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/72))


* **Documentation**:
  * User Guide:
    * Added documentation for the features `delevent` ([\#13](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/13)), `editevent` ([\#55](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/55)), and `findevent` ([\#82](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/82))
  * Developer Guide:
    * Added implementation details of the `delevent` feature ([\#58](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/58))


* **Contributions to team-based tasks**:
  * Updated `README.md` to promote our application ([\#42](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/42))
  * Updated Project Notes with `v1.2` Feature Demo
  * Added user stories to the tP Issue Tracker


* **Review/mentoring contributions**:
  * PRs reviewed (with non-trivial review comments): [\#48](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/48), [\#56](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/56), [\#63](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/63), [\#68](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/68), [\#69](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/69)


* **Contributions beyond the project team**:
  * Reported 19 bugs and suggestions for another team: [PE-D Issues link](https://www.github.com/inas0ng/ped/issues)
