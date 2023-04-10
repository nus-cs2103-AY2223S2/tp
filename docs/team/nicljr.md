---
layout: page
title: Nicholas Lim's Project Portfolio Page
---

### Project: AddressBook Level 3

AddressBook - Level 3 is a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to Favourite and "Unfavourite" Contacts (PR [#76](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/76)).
  * What it does: allow the user to favourite contacts of interest as well as unfavourite contacts that are no longer of your interest in the `Address Book`.
  * Justification: users might want to favourite certain profiles, they commonly contact for easier retrieval. This makes it more convenient for users.
  * Highlights: This feature models after existing commands, where a Parser parses the Favourite Command after the user input. The Person class had to be implemented with an additional Favourite Status Variable.

* **New Feature**: Added the ability for Users to Add Events in the Events Tab. (PR [#81](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/81)).
  * What it does: allows the user to add new events of different recurrence levels, to be displayed within the Events Tab UI.
  * Justification: we want the users to be able to add and track events in their busy lifestyles.
  * Highlights: this feature required an implementation of a new Event Class and an UniqueEventList to store all the various events the user might have. The feature mostly models after the existing commands.

* **New Feature**: Added the ability for Users to delete events in the Events Tab. (PR [#165](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/165)).
  * What it does: allows the user to delete existing events within their events tab.
  * Justification: we want users to be abe to delete events since they are able to add events to their tab. This is to prevent overflowing their events tabs once they have finished that particular event.
  * Highlights: this feature models after existing commands, where it will delete a target event according to their assigned Index within the UniqueEventList.

* **New Feature**: Added the ability for Users to tag/untag contacts to particular events. (PR [#164](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/164)).
  * What it does: allows the user to tag existing contacts in their address book to any of their events.
  * Justification: we want to make the application feel more interconnected, this way users can keep track of the different attendees of particular events they have within the tab. This allows for more meaningful use of the events tab.
  * Highlights: this feature require the Event Class to take on a new variable which is a List of Persons that are tagged to the current event, for it to be recorded and displayed on the UI.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=nicljr&breakdown=true)

* **Project management**:
  * Release NeoBook's JAR

* **Enhancements to existing features**:
  * Enhanced the Edit Command for Contacts in AddressBook (PR [#76](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/76)).
    * The existing Edit Command entirely resets fields which are SuperFields.
    * Meaning to say, users cannot selectively add on top of current Tags/Modules Taken or selectively remove a particular Tag or Module Taken.
    * Enhanced the Edit Command such that users, can selectively add and remove within the SuperField instead of entirely reseting said SuperFields.

* **Documentation**:
  * User Guide:
    * Added [documentation for the `fav` and `unfav` commands](https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html#favourite-a-contact--fav) (https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html#unfavourite-a-contact--unfav) (PR [#189](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/189)). 
    * Added [documentation for the `addevent` command](https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html#adding-an-event--addevent) (PR [#189](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/189)).
    * Added [documentation for the `delevent` command](https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html#deleting-an-event--delevent) (PR [#189](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/189)).
    * Added [documentation for the `tagpersonevent` and `untagpersonevent` command](https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html#tagging-contacts-to-an-event--tagpersonevent) (https://ay2223s2-cs2103t-f12-3.github.io/tp/UserGuide.html#untagging-contacts-from-an-event--untagpersonevent) (PR [#189](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/189/)).
  * Developer Guide:
    * Added [implementation for the `fav` and `unfav` commands](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#command-for-favourite-contacts) (https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#command-for-unfavourite-contacts) (PR [#154](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/154)).
    * Added [implmentation for the `addevent` commands](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#command-for-adding-events) (PR [#154](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/154)).
    * Added [manual testing for the `fav` and `unfav` commands](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#adding-an-event) (PR [#323](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/323)).
    * Added [manual testing for the `addevent` commands](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#adding-an-event) (PR [#323](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/323)).
    * Added [manual testing for the `editevent` commands](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#editing-an-event) (PR [#323](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/323)).
    * Added [manual testing for the `delevent` commands](https://ay2223s2-cs2103t-f12-3.github.io/tp/DeveloperGuide.html#deleting-an-event) (PR [#323](https://github.com/AY2223S2-CS2103T-F12-3/tp/pull/323)).

* **Community**:
  * Reviewed PRs

* **Tools**:
  * `To be added soon`
