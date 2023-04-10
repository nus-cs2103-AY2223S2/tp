---
layout: page
title: Nam Harin's Project Portfolio Page
---

### Project: CoDoc

CoDoc is a desktop contact management application. The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is mainly written in Java, and below is a summary of what I have contributed (about 1800 LoC).

* **Major Feature Added**: Info Panel system design. ([#38](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/38), [#47](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/47), [#68](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/68))
    * What it does: Info Panel is a key element of CoDoc that allows users to view specific details of a contact stored
  in a concise manner through a multi-tab system.
    * Justification: A contact may hold long information and there is a need to organizing them in separate neat panel
  to not overload the users.

* **New Feature**: `protagonist` system in the CoDoc model. ([#47](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/47))
  * What it does: Facilitates smooth interaction of Info Panel and CoDoc database, as well as execution of various commands.
  * Justification: Implementation of Info Panel complicates UI and various command implementation. The `protagonist`
  system allows CoDoc model to keep a special reference to be used for UI and commands.

* **New Feature**: View Command. ([#68](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/68))
  * What it does: Primary way of users to interact with the Info panel through the CLI.
  * Justification: Main target audience for CoDoc are users who are able to type fast and benefit off CLI. This command
  allows easy interaction with the Info Panel and also makes comparison easy between contacts.

* **UI Overhaul**: New Theme for CoDoc. ([#71](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/71))
  * What it does: Update color scheme and various layouts to improve UI design.
  * Justification: To give CoDoc a modern, polished look. Popular font (Roboto) was also added to make it more
  professional yet not overwhelming.
  * Highlights: Clean looking UI that greatly improves readability and user experience.
  * *Credits*: Referenced Apple's UI design. Roboto Font obtained from [Google fonts](https://fonts.google.com/specimen/Roboto), used under [Apache license](https://en.wikipedia.org/wiki/Apache_License).

* **Enhancements to existing features**:
    * Improved display of person list for more concise display. ([#72](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/72))
    * Improved implementation of Edit Command to follow Software Engineering principles. ([#105](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/105))
    * Enhanced implementation of GUI clicking function by [Linus](https://ay2223s2-cs2103t-f12-2.github.io/tp/team/harin0826.html)
  to improve code quality and remove errors. ([#129](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/129), [#133](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/133))
    * Implement auto-scroll and highlight feature to improve user experience when interacting through GUI and View Command. ([#161](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/161))
    * Overall enhancements to code quality to better follow Software Engineering principles and Design fundamentals.


* **Documentation**:
    * User Guide:
        * Improve flow for Tutorial section to make it more approachable for users. ([#152](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/152))
        * Update Additional Resources section with detailed instructions in running the program. ([#153](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/153))
        * Add entries to FAQ section. ([#155](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/155))
        * Add more screenshots to different sections where instructions could seem complicated. ([#155](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/155))
    * Developer Guide:
        * Create UI implementation section which explains the majority of design considerations for UI. ([#108](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/108))
        * Include my implementation and design considerations for `protagonist` system and View Command for `Model` components. ([#164](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/164)) 


* **Project management**:
  * Set up shared project folder and project notes through Google Drive.
  * Note taker for project meetings.
  * Reminder for project deadlines and milestones to achieve.
  * Provide directions to help team better adhere to the project requirements.
  * Write detailed Pull Requests (e.g. [#71](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/71), [#105](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/105))
  to help team members understand better on my improvements and implementations.
  * Manage overall code quality to ensure the codebase adhere to Design principles.


* **Community**:
  * Reviewed and commented on teammates' PRs. [PRs reviewed by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aharin0826)
  * Opened and assigned issues to teammates and me. [Issues opened by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Aharin0826), [Issues assigned to me](https://github.com/AY2223S2-CS2103T-F12-2/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Aharin0826)
  * Reviewed and merged teammates' PRs.
  * Reported bugs for other teams. [PE-D](https://github.com/harin0826/ped/issues)


* **Tools**:
  * Java
  * JavaFX
  * SceneBuilder
  * Jackson
  * Gradle
  * Junit5
