---
layout: page
title: Russell Tan's Project Portfolio Page
---

### Project: EduMate

`EduMate` is a desktop address book application used to help NUS students maintain both their social and academic life by lowering the barriers to meet up and also make new friends within modules and school. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 30 kLoC.

Given below are my contributions to the project.
* **Code Contributed to team-based tasks**:
  * **Basic User Profile** [#7](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/7)
    * What it does: Separate User Profile from a typical contact.
    * Justification: Personalise settings for the user of the application.
  * **Overhaul of `EduMate` From AB3** [#7](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/7), [#32](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/32):
    * What it does: Gives a basic structure of `EduMate` to work with.
    * Justification: Fits into requirements of `EduMate` through adaptation of code.
  * **Spearhead the use of `ContactIndex` and `IndexHandler`** [#124](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/124) Testing: [#134](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/134)
    * What it does: Gives a unique identifier for each contact added to `EduMate`, replacing `Index` entirely.
    * Justification: Use of `Index` is too rigid as it obtains a `Person` from their index in `ObservableList` only.
    * Highlight: `IndexHandler` can help assign a `ContactIndex` to a newly-added `Person`. 
Contacts can be queried by their `ContactIndex` instead of their index in `ObservableList`.
  * **Person Card UI changes** [#134](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/134)
    * Let each Person Card's index number display `ContactIndex` instead of just their relative index in `ObservableList`.
  * **`view` Command**: [#82](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/82), Testing: [#118](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/118)
    * What it does: Displays the `Person` or `User` profile on the Profile Panel.
    * Justification: So that the `User` can view the full profile of the contact or him/herself.
    * Highlight: Can view profiles via different command formats.
  * **`HourBlock`, `TimeBlock`, `TimePeriod`, `Day`** [#144](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/144), [#193](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/193) Testing: [#144](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/144), [#193](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/193)
    * What it does: Represents periods of a time in a `Timetable`.
    * Justification: Abstracts out a period of time for the `TimingRecommender`.
    * Highlight: Built using [Joda-Time](https://www.joda.org/joda-time/index.html)
  * **MathUtil and TimeUtil** [#144](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/144), [#193](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/193) Testing: [#193](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/193)
    * What it does: Utility functions to aid `TimingRecommender` in its functions.
    * Justification: Separate abstraction of methods which do not fit into any Time-based classes.
    * Highlights: Cartesian Product, Indexing, Clash checks, Merge `TimePeriods`.
  * **`Timetable` Class** [#144](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/144), [#193](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/193) Testing: [#144](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/144)
    * What it does: Represents a contact's or `User`'s timetable.
    * Justification: To display the schedule of the `Person` or `User`
  * **`TimingRecommender`** [#144](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/144), [#204](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/204)
    * What it does: Looks through all participant's timetables and find time slots that every participant is free.
    * Justification: Using an automated tool to schedule meetups is more efficient compared to relying on NUS students to manually message each other to find a suitable meeting time.
  * **`meet` Command and `TimingRecommender` integration** [#204](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/204)
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=russelltankaimin&breakdown=true)
* **Project management**:
  * Initiated use of GitHub Projects to manage progress.
  * Chaired weekly meetings.
  * Managed Gradle for the entire team (imports, packages, enabling assertions) [#161](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/161)
* **Documentation**:
  * User Guide [#210](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/210), [#218](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/218), [#225](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/225), [#238](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/238), [#246](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/246), [#325](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/325)
    * Partial reformatting of User Guide (Content presentation, whitespace usage)
    * Used Figma to obtain desired diagrams used in the entire User Guide.
    * Fix grammatical errors and sentence structure in the entire User Guide.
  * Developer Guide [#166](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/166), [#177](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/177), [#199](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/199), [#218](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/218), [#225](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/225),
    * Added UML diagrams and content for View Command, View Command Parser, Recommender, Time-related classes.
    * Contributed to adding placeholders for new upcoming commands during V1.2 in preparation for V1.3.
* **Reviewing/Mentoring contributions**:
    * [#9](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/9), [#33](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/33),
[#48](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/58), [#119](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/119),
[#167](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/167), [#176](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/176),
[#228](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/228)
* **Tools**:
  * Java 11, JavaFX, Joda-Time, JUnit, Gradle, Figma

