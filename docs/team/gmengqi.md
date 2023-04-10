---
layout: page
title: Gao Mengqi's Project Portfolio Page
---

### Project: Where Got Time

Where Got Time is an addressbook-cum-timetable. Students find it hard to find free time slot within their group
of friends in NUS as they have to compare their timetables. Therefore, this product helps students to easily find
free time slots within a group of NUS friends, and keep track of personal and friends' timetable/schedule.

### My Contributions to the project
- **Code contributed**:
  [Reposense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=gmengqi&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)
  * **Additional Feature**: incorporated IsolatedEvent in finding free time slot
    * What it does: Checking of the isolated events in the isolated event list to filter out the weekly free time slots 
    * Justification: Main use of the application
    * Contributions: Updated IsolatedEventList to find free time slots and added it to the implemented model for free time slot.

  * **Additional Feature**: CRUD for Isolated event class 
    * What it does: Allows users to create, read, update and delete isolated events. 
    * Justification: This allows users to organize Persons' isolated events, and also making implementation of finding of free time slots easier. 
    * Contributions: Implemented command and parser classes for Isolated event related commands.
  * **Testing**: Wrote user-related tests
    * Wrote JUnit tests to test for isolated events features for CRUD

- **Contributions to the UG**:
  * Added documentation for the `event_create` feature
  * Added documentation for the `ie_edit` feature 
  * Added documentation for the `ie_delete` feature 

- **Contributions to the DG**:
  - Added documentations for user stories in the DeveloperGuide.md
  - Added details for implementation of `event_create` feature 
  - Made sequence diagram and activity diagram for `event_create` feature in the DG 
  - Added details for implementation of `ie_edit` feature 
  - Made sequence diagram and activity diagram for `ie_edit` feature in the DG

- **Contributions to team-based tasks**:
  - Added documentations for README.md
  - Reviewed teammates' PR. 
  - Completed tasks and milestones before the deadline. 
  - Created issues on the issue tracker to track tasks/bugs, as seen [here](https://github.com/AY2223S2-CS2103T-T09-2/tp/issues/created_by/@me)

- **Contributions beyond the project team**:
  - Reviewed peers' tP (CS2103T-T12-1)
  - Reported bugs/flaws in peers' tp for PED
