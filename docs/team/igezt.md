---
layout: page
title: Pierce Ng's Project Portfolio Page
---

### Project: NeoBook

NeoBook is a desktop application for NUS students to better manage their contacts and the events they have scheduled with them. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: User class and persistent storage for User. (PR [#93](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/93))
  * Justification: For storage of events and personal information of the user. Also useful for future exporting user information features.
  * Highlights: this feature creates a place for the User to easily input data about themselves. This data will then be persistently saved into NeoBook. 

* **New Feature**: Implemented persistent storage and integrated Events model within the User class. (PRs [#119](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/119), [#172](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/172))
  * Justification: Events need to be persistently stored within NeoBook. Also couples the Events list with the User class.
  * Highlights: Forms the foundation for all events commands.

* **New Feature**: EditUser command. (PR [#93](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/93))
  * Justification: User has to be able to edit his own details
  * Highlights: This feature will change the user model of NeoBook and will persistently store all edits

* **New Feature**: EditEvent command. (PR [#198](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/198))
  * Justification: User has to be able to edit events
  * Highlights: This feature can change any event stored in NeoBook and will persistently store all edits

* **New Feature**: Add model support for event commands. (PRs [#171](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/171), [#161](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/161))
  * Justification: For ease of access into the Model system for implementation of AddEventCommand and DeleteEventCommand
  * Highlights: This feature provides methods allows future developers to easily access events stored in the ModelManager

* **New Feature**: Implement Light and Dark mode commands. (PR [#178](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/178))
  * Justification: For the user to use CLI to change between light and dark mode

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=igezt&breakdown=true)

* **Project management**:
  * Acted as Team Lead
  * Organized features, enhancements and bugs into GitHub issues and appropriately distributed the workload among the members of our group

* **Enhancements to existing features**:
  * Changed AddCommand and EditCommand to account for the new fields for the Person class. (PRs [#45](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/45))
  * Changed FindCommand to work for multiple fields. (PR [#143](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/143))
  * Created and abstracted common logic into Field and SuperField classes for ease of polymorphism (PR [#147](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/147))

* **Documentation**:
  * User Guide:
    * Edited documentation for the features `add`, `find` and `edit` of UG (PRs [#200](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/200), [#267](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/267))
    * Edited documentation for data-related section of UG (PRs [#267](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/267), [#269](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/269))
    * Added documentation for the `tagpersonevent` and `light`/`dark` features (PR [#182](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/267))
    * Made final edits, organized and cleaned up UG (PR [#309](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/309))
  * Developer Guide:
    * Add documentation for new Person and Storage models ([#157](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/157))
    * Added documentation for manual testing for the following commands ([#269](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/269))
      * AddCommand
      * EditCommand
      * FindCommand
      * EditUserCommand
      * Light/DarkCommand
    * Wrote effort appendix (PR [#307](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/307))
    * Added export user information proposed feature section (PR [PR [#307](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/307)])
    * Made final edits, organized and cleaned up DG



* **Community**:
  * PRs reviewed (with non-trivial review comments): PRs [#81](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/81), [#261](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/261), [#164](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/164), [#115](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/115)


