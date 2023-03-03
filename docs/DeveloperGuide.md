---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

# :calendar: Developer Guide

Calidr is a **time-management and scheduling calendar application**, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

## Appendix
### Requirements
#### Target User Profile

* NUS staff or student
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

#### Value Proposition
* Text-based timetable management
    * Add todos and events
    * Add reminders to todo/event
    * Search
    * /view day
    * /view month

#### User Stories


| As a ... | I want to ... | So that I can ... | Priority |
| -------- | -------- | -------- | -------- |
| NUS staff/student     | add my classes and todos to my calendar     |  keep track of my daily schedule.     | *High*
| NUS staff/student  | add a title to an event/todo during it's creation  | distinguish events/todos  | *High*
| NUS staff/student | import/export my calendar as an iCalendar (`.ics`) file | save it as an offline copy | *High*
| NUS student | set notifications for upcoming exams and assignments | not forget to complete them on time. | *High*
| NUS student with a busy schedule | view my calendar in different views (daily, weekly, monthly) | get a comprehensive view of my schedule. | *Mid*
| NUS staff/student | search/sort for todos <ul><li>by description</li><li>by a range of dates</li><li>by recency</li></ul> | <ul><li>look for specific or related todos for editing</li><li>check my availability during holidays/exam weeks</li><li>identify todos with higher priority</li></ul> | *High*
| NUS staff/student | set (during creation) the duration for a certain event | manage todos that occur over a span of time. | *High*
| NUS staff/student | change the duration of a event after its creation | make changes if I make mistakes | *Mid*
| NUS staff/student | set tags on events and todos | easily find them again | *Mid*
| NUS staff/student | add a priority to todo | keep track of them easily | *Mid*
| NUS staff/student | be able to change the name of a event/todo after its creation | - | *Low*
| NUS staff/student | set a recurrence for a todo/event | save time on repeating todos | *Low*
| NUS staff/student | keep track of my todos in a list | refer to them in a convenient fashion | *High*
| NUS staff/student | add notes or comments to each event in my calendar | note down non-critical but useful information. | *Low*
| NUS staff/student | be able to add a description to a todo/event | keep track of additional details | *High*
| NUS staff/student  | add an optional location to my todos  | record specific addresses if it is a place I need to be.  | *Low*

### Use Cases

(For all use cases below, the System is Calidr and the Actor is the user, unless specified otherwise)

#### Change the duration of an event

**MSS**

1. User requests to list events
1. Calidr shows a list of events
1. User requests to query a specific event in the list by index and overwrite the date fields with a new value
1. Calidr modifies and saves the new dates for the event.
   Use case ends.

**Extensions**

- 2a. The list is empty.
Use case ends.
- 3a. The given index is invalid.
- 3a1. Calidr shows an error message.
Use case resumes at step 2.


### Non-functional requirements

1. Should work on any mainstream OS (in particular file I/O) with Java 11 installed.
2. Should be able to manage 1000 items without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the todos faster using commands than using the mouse.


### Glossary

1. **Mainstream OS**: Windows, Linux, Unix, OS-X
1. **Items**: Elements on a calendar.
    a. **ToDos**: Items with a single associated date-time (e.g. a due date) and status (done or not done).
    b. **Events**: Items with two associated date-times.
