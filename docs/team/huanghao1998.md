---
layout: page
title: Huang Hao's Project Portfolio Page
---

### Project: SOCket

SOCket is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: `remove` command [\#88](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/88)
    * What it does: Allows users to remove specific field value based on the input command.
    * Justification: Users are able to remove any field value of the contact information without excess typing.
    * Highlights: `to be added soon`
    * Credits: *{-}*

* **New Feature**: `view` command [\#108](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/108)
  * What it does: Allows users to view a specific contact detail.
  * Justification: Users are able to view detailed information of a contact by `view` command or click on the contact card.
  * Highlights: `to be added soon`
  * Credits: *{-}*

* **New Feature**: `clearpj` command [\#154](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/154)
    * What it does: Allows users to clear off all the project in the project list
    * Justification: Users are able to clear all projects in a single command.
    * Highlights: `to be added soon`
    * Credits: *{This feature idea was adopted from `clear` command}*

* **New Feature**: Added keyboard shortcut [\#203](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/203)
  * What it does: Allows users to perform `undo`, `redo` and `exit` command using keyboard shortcut
  * Justification: Users are able to do undo/redo/exit similar to other commonly used applications.
  * Highlights: `to be added soon`
  * Credits: *{This feature idea was adopted from `help` keyboard shortcut of AddressBook-Level3}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=huanghao1998&breakdown=true)

* **Project management**:
    * Create issues when things are needed to be refined

* **Enhancements to existing features**:
    * Updated `clear` command to allow users to remove group of contacts associated with the given tag(s) [\#127](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/127)
    * Updated the restriction on the number of tags associated to each contact to prevent misuse of the feature [\#172](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/172)
    * Improved the UI in `HelpWindow` by categorising commands into different subgroups so that users can look for the command guide easily [\#190](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/190)
    * Improved the UI and UX in `MainWindow` [\#108](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/108), [\#117](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/117), [\#128](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/128), [\#162](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/162)
      * Reallocated the result display and command box from the top of the application to the bottom as it allows for a more natural and intuitive user flow, with the display panel at the top.
      * Seperated the contact display into 2 components, contact list panel and contact detail panel, using split panel. Contact list displays minimal information while contact detail panel displays all the information related to the contact.
      * Improved the contact list by allowing users to click on the card and displays contact detail of the contact in contact detail panel.
      * Improved contact card by automatically wrapping tags based on the width of the contact card.
      * Improved the shape of the display card from sharp to round edges, so that the overall aesthetic of the application is enhanced. The round edges create a smoother and more polished look which help to soften the visual impact of the display card, which can give the impression of a more refined and elegant design.
      * Implemented a hover effect on contact list so that users can quickly identify which elements on the page are interactive and clickable.

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `remove`, `clear`, `clearpj`, `view` [\#52](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/52), [\#91](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/91), [\#111](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/111), [\#179](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/179)
        * Added "GUI of SOCKet" image under Quick start section [\#179](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/179)
        * Added screenshot to `add`, `edit`, `remove`, `find`, `sort`, `addpj`, `editpj`, `removepj`, `sortpj`, `assign`, `unassign` and `help` command [\#255](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/255)
    * Developer Guide:
        * Added use cases `UC06`, `UC13`, `UC17`.
        * Updated existing UI class diagram with `ProjectListPanel`, `ProjectCard`, `PersonDetailPanel` and `PersonDetailCard`.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#89](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/89)
    * Contributed to forum discussions: [\#78](https://github.com/nus-cs2103-AY2223S2/forum/issues/78#issuecomment-1406976199).

