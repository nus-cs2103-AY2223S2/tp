---
layout: page
title: Stanley Han's Project Portfolio Page
---

### Project: NeoBook

NeoBook is a desktop application for NUS students to better manage their contacts and the events they have scheduled with them. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added the ability to switch between UI tabs (PR [#105](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/105)).
  * What it does: allows the user to switch between the `Address Book`, `Events`, and `Me` tabs using the tab command.
  * Justification: clicking the buttons in the tab bar requires the user's hands to leave the keyboard; the tab command alleviates this by duplicating the functionality in the command line.
  * Highlights: this feature integrates two types of input methods, necessitating a single source of truth in the model for a cohesive user experience.
* **New Feature**: Added light and dark modes (PR [#129](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/129))
  * Justification: most contemporary applications have light and dark modes.
  * Highlights: this feature required extensive use of CSS variables to propagate the theme colours throughout the UI.

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=hansstanley&breakdown=true).

* **Project management**:
  * Initialised team organisation and tP repository
  * Created PR to the module's `master` branch
  * Enabled issue tracker and GitHub Actions (for CI and codecov)
  * Maintained the issue tracker and milestones
  * Set up and adapted the project website for NeoBook
  * Triaged issues from PE-D

* **Enhancements to existing features**:
  * Redesigned the GUI
    * Changed the layout (PRs [#39](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/39), [#56](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/56), [#57](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/57), [#59](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/59), [#62](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/62), [#74](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/74), [#87](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/87), [#96](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/96), [#107](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/107), [#131](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/131)).
    * Split the single contacts list panel into list and details panels to accommodate the increased number of information fields without cluttering the list.
    * Moved the command box and result display to the bottom of the window, borrowing familiarity from popular desktop messaging applications.
    * Added a tabbed view for the events and "me" panels to avoid cluttering the screen space, while borrowing familiarity from the widely used Outlook desktop client.
    * Added a system of CSS styling options to increase code reuse.
      * Labels have a series of font sizes (`h1..h6, p`).
      * Paddings have predefined styling (e.g., `pt` for top padding, `pa-l` for "all except left" padding).
      * The backgrounds for labels and panes have 3 colour types: `normal`, `accented`, and `muted`. Generally, `muted` is used for background components, while `accented` is used in components containing information useful to the user.
  * Enhanced the help window with the list of all commands (PR [#181](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/181)).
    * The list uses the `MESSAGE_USAGE` from each of the commands, making it consistent with the help messages that show up in the result display.
  * Added extensive testing for new models and command parsers (PRs [#271](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/271), [#272](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/272)).
    * Improved code coverage by ~10%.
    * Fixed several correctness bugs.

* **Documentation**:
  * User Guide:
    * Added [documentation for the `tab` command](https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html#switching-between-tabs-tab) (PR [#105](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/105)).
  * Developer Guide:
    * Updated [implementation details of the UI](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#ui-component) (PR [#139](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/139)).
    * Added [implementation details of the `tab` command](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#command-for-tab-switching) (PR [#139](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/139)).
    * Added [`Planned enhancements`](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#appendix-planned-enhancements) section (PR [#303](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/303)).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#76](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/76), [#81](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/81), [#93](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/93), [#138](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/138) [#143](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/143), [#178](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/178), [#182](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/182).
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S2/forum/issues/1), [47](https://github.com/nus-cs2103-AY2223S2/forum/issues/47), [65](https://github.com/nus-cs2103-AY2223S2/forum/issues/65), [76](https://github.com/nus-cs2103-AY2223S2/forum/issues/76), [123](https://github.com/nus-cs2103-AY2223S2/forum/issues/123)).

* **Tools**:
  * Integrated a third party library (CalendarFX) to the project (PR [#122](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/122)), but subsequently removed it due to bugs and usability issues.
