---
layout: page
title: Tan Kai Min, Russell's Project Portfolio Page
---

### Project: EduMate

EduMate is a desktop address book application used to help NUS students maintain both their social and academic life by lowering the barriers to meet up and also make new friends within modules and school. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Gradle Manager**
  * Manage packages, imports and settings via Gradle.
  * Enabled assertions.

* **Basic User Profile**:
  * What it does: Personalise settings for the user of the application.
  * Justification: Gives a personalised touch to the user. Allows users to personalise their application's settings.
  * Highlights: Inherits from Person.

* **Overhaul of EduMate**:
  * Modified Json data to fit application requirements.
  * Split tags to Module and Group Tags
  * Allow User and Person to have Module, Group Tags and Telegram Handle.
  * Rework parsers to include Module and Group Tags and Telegram Handle.
  * Tests for User will use a stub instead of the actual User.

* **Spearhead the use of ContactIndex**
  * ContactIndex is a unique identifier for each contact added to the application.
  * Deprecate the use of Index from AB3 which obtains a Person from their index in the ObservableList.
  * Built IndexHandler for the Model so that each newly added contact could be assigned a contact index
and that contacts could be queried by their contact index instead of their index in ObservableList.

* **UI**
  * Let each Person Card's index number to display contact index instead of just their relative index in ObservableList.
  
* **View Command**:
  * What it does: Allows users to view profile of either him/herself or their contacts.
  * A single ```view``` command will show the user's own profile.
  * ```view <index>``` command will retrieve and show the contact's information by its index.
  * ```view \n <NAME>``` command will retrieve and show the contact's information by name.

* **HourBlock, TimeBlock, TimePeriod**
  * Created before Timing Recommender so that we can represent periods of time in a timetable.
  * Extensive testing for HourBlock, TimeBlock and TimePeriod.
  * TimePeriod is abstract parent class, from which HourBlock and TimeBlock inherits from. 
  * HourBlock is essentially TimeBlock but limited to 1 hour and can contain a Lesson/Commitment.
  * Built using [Joda-Time](https://www.joda.org/joda-time/index.html)

* **Day Enum**
  * Day contains the days on a **WeekDay**.

* **MathUtil**
  * Mathematical tools which can assist Timing Recommender to perform its functions
  * Some functions included : Cartesian Product, Indexing.

* **TimeUtil**
  * Some utility functions include: 
    * Clash Checks, 
    * Consecutive TimeBlock/HourBlock checks, 
    * Merging suitable TimeBlocks or HourBlocks to form a single TimePeriods.

* **Timetable Class**
  * Represents a contact's or your timetable.
  * A timetable contains HourBlocks which can contain Lessons/Commitments.
  * Extensive testing done on Timetable before integration work begins.

* **Timing Recommender**
  * What it does: Looks through all participant's timetables and find time slots that every participant is free.
  * Justification: Automated tool does the job faster than NUS students messaging each other when is a good time to meet.
  * Extensive testing for timing recommender before integration with Location Recommender (see Hafeez's PPP).

* **Meet Command**
  * Participated and assisted in the integration work.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=russelltankaimin&breakdown=true)

* **Project management**:
  * Initiated use of Github Projects to manage progress.
  * Chaired weekly meetings.

* **Documentation**:
  * User Guide:
    * Used Figma to obtain desired diagrams used in the entire User Guide.
    * Fix grammatical errors and sentence structure in the entire User Guide.
  * Developer Guide:
    * Added UML diagrams and wrote content for View Command, View Command Parser, Recommender, Time-related modules.
    * Contributed to adding placeholders for new upcoming commands during V1.2 in preparation for V1.3.

* **Community**:
    * [\#9](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/9), 
    * [\#33](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/33),
    * [\#48](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/58)
    * [\#119](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/119)
    * [\#167](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/167)
    * [\#176](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/176)
    * [/#228](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/228)

* **Tools**:
  * Java 11
  * JavaFX
  * Joda-Time
  * JUnit
  * Gradle

