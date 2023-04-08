---
layout: page
title: Valerie Tan's Project Portfolio Page
---

### Project: Dengue Hotspot Tracker

Dengue Hotspot Tracker is a desktop app for managing dengue cases.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 9.5 kLoC.

Given below are my contributions to the project.

* **New Feature:** Added the ability to delete multiple indexes at once.
  * Justification: This feature allows users to delete cases in batches, rather than having to do so one at a time.
* **New Feature:** Added the ability to delete all cases from a given date.
  * Justification: This feature allows users to delete all cases from a given date at once, which may be useful
    when the user wants to clear older or erroneous cases from the same date from the `DengueHotspotTracker`.
  * Highlights: This enhancement was challenging as it required significant changes to the original `delete` command parser.
    It caused the `delete` command to be the only command that could accept indexes and prefixes, _but never together_.
    This meant that much effort had to be put into ensuring that the parser could accurately detect when the two had been mixed,
    regardless of the index(es) came before or after the date, and throw exceptions accordingly.
    The `delete` command itself was also updated to use the `Optional` class instead, as it now had to hold both indexes and dates,
    but never at the same time. Much abstraction was required to keep the code clean and readable.
* **New Feature:** Added the ability to delete all cases from a given date range.
  * Justification: This feature allows users to delete all cases from a given date at once, which may be useful
    when the user wants to clear erroneous cases from the same date from the `DengueHotspotTracker`.
  * Highlights: Similar to the previous feature, this enhancement was challenging as it required significant changes
    to the architecture of the `delete` command parser and `delete` command. In addition to the aforementioned changes
    to the parser and use of the `Optional` class, new classes such as `Range`, `StartDate`, and `EndDate`, as well as
    new generic interfaces `Start` and `End`, had to be created in line with the object-oriented style of programming
    used throughout the rest of the project. Range validation also had to be implemented.
* **New Feature:** Added the ability to sort cases by name, age, postal code, and date.
  * Justification: This features allows users another avenue to get an overview of the cases by viewing them in a natural sorted order.
* **New Feature:** Added the ability to clear the displayed list (rather than only the entire list).
  * Justification: This feature allows users to delete cases in batches, most likely after using the `find` command,
    rather than having to do so one at a time. To implement this feature, the original `clear` command was modified,
    as the team felt that the original behaviour of the `clear` command was not useful and could be generalised to
    clearing the current displayed list, rather than the entire list.
* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=valerietanhx)
* **Project management:**
  * Managed issue tracking for `v1.3`–`v1.4` on GitHub
* **Documentation:**
  * User Guide
    * Added documentation for the `clear`, multi-index `delete`, `delete`-by-date, `delete`-by-date-range, and `sort` features
    * Improved grammar, organisation, and clarity of documentation ([#243](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/243))
  * Developer Guide
    * Added implementation details and UML diagrams of the multi-index `delete` and `sort` features
* **Community:**
  * PRs reviewed (with non-trivial review comments): [#141](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/141)
