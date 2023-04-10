---
layout: page
title: Anthony's Project Portfolio Page
---
### Overview. Project: EZ-Schedule

EZ-Schedule is an application that allows for a high-level overview and managing of events,
optimized for use via a Command Line Interface (CLI). The app stores events chronologically
in a list format, while having a calendar for easy overview of events on a month-by-month basis.

### Summary of contributions
Code contributed:
check it out [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=anthonynmh&breakdown=true)

* **New Feature**:
  * AutoMarkDone of past events
    * [#69 Develop auto mark done](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/69)
      * Description: This feature will automatically tag an event as "complete" once the end time of the event has past.
      * Contributed classes:
        * New classes: -
        * Enhanced classes - `Event`, `EventCard`, `Time`.
  * RecurCommand
    * [#86 Merge branch-RecurCommand](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/86)
    * [#88 Add WEEK to RecurCommand](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/88)
      * Description: This feature allows for events to be repeated over a period of _Recur Factor_.
        _Recur Factor_ can be any of the following {day, week, month}.
      * Contributed classes:
        * New classes: `RecurCommand`, `RecurCommandParser`, `RecurFactor`.
        * Enhanced classes - `CliSyntax`, `ParserUtil`, `SchedulerParser`, `Date`, `UniqueEventList`.

* **Enhancements to existing features**:
  * Functional code
    * [#48 Update code to support Event](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/48)
  * Bug Fixes
    * [#83 Enhance events duplicate checker to check all fields](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/83)
    * [#84 Fix bug in AutoMarkDone](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/84)
    * [#109 Fix broken image links in UG](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/109)
    * [#111 Fix exception catching for Recur](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/111)
    * [#172 Enhance SchedulerParser](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/172)
    * [#173 Update RecurCommand](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/173)
    * [#178 Update RecurCommand](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/178)
  * Test code
    * [#193 Add RecurCommand tests](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/193)
    * [#205 Add RecurFactor tests](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/205)

* **Documentation**:
  * User Guide:
    * v1.1
      * Ideated on feature lists
      * Ideated on command summary
    * v1.3
      * [#105 Branch ug](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/105)
      * [#165 Improve Recur parameter description in UG](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/165)
  * Developer Guide:
    * v1.1
      * [#31 Branch user story dg](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/31)
    * v1.4
      * [#225 Update DG](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/225)
      * [#216 Update user stories and use cases](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/216)

### Review/mentoring contributions
[pr-reviewed-v1.1]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Aanthonynmh+milestone%3Av1.1
[pr-reviewed-v1.2]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Aanthonynmh+milestone%3Av1.2
[pr-reviewed-v1.3]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Aanthonynmh+milestone%3Av1.3
[pr-reviewed-v1.4]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Aanthonynmh+milestone%3Av1.4
[pr-reviewed-total]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Aanthonynmh

| Milestone | PRs reviewed             |
|-----------|--------------------------|
| v1.1      | [1] [pr-reviewed-v1.1]   |
| v1.2      | [0] [pr-reviewed-v1.2]   |
| v1.3      | [4] [pr-reviewed-v1.3]   |
| v1.4      | [14] [pr-reviewed-v1.4]  |
| Total:    | [19] [pr-reviewed-total] |
