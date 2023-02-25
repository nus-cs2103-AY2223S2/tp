---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------



## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: FriendlyLink streamlines volunteer and elderly management for single administrators of small NPOs.
With its easy-to-use text-based interface and contact management features, say goodbye to manual record-keeping and hello
to a more efficient and organised way of managing the volunteers’ and elderly’s contact details.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority         | As a …​                            | I want to …​                                                                   | So that I can…​                                                                 |
|------------------|------------------------------------|--------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| `* * *`          | single administrator of small NPOs | list volunteers                                                                | see all the volunteers and their information                                    |
| `* * *`          | single administrator of small NPOs | add volunteer to the list of volunteers                                        | include new volunteers in the application                                       |
| `* * *`          | single administrator of small NPOs | delete volunteers                                                              | remove volunteers that have left                                                |
| `* * *`          | single administrator of small NPOs | remove all the pairs a volunteer is in                                         | accurately keep track of which elderly are affected when a volunteer leaves     |
| `* *`            | single administrator of small NPOs | update volunteers & volunteer information                                      | keep the volunteer information up-to-date                                       |
| `* * `           | single administrator of small NPOs | search for particular volunteers by keywords                                   | quickly see the volunteer's details                                             |
| `*`              | single administrator of small NPOs | filter volunteers by tags                                                      | access relevant groups of volunteers quickly                                    |
| `*`              | single administrator of small NPOs | manage volunteers by region                                                    | arrange the volunteers such that they can conveniently reach out to the elderly |
| `*`              | single administrator of small NPOs | record the community information of volunteers, but not their specific address | ensure that the volunteers' privacy is not compromised                          |
| `*`              | single administrator of small NPOs | manage the volunteers' available dates and time                                | efficiently find volunteers available for activities                            |
| `* *`            | single administrator of small NPOs | view nursing / medical courses that volunteers have taken in the past          | pair an elderly with a more suitable volunteer                                  |
| `*`              | single administrator of small NPOs | see how long a volunteer has been with the program                             | assess their experience                                                         |
| `*`              | single administrator of small NPOs | track the befriending history of a volunteer                                   | audit past involvements easily                                                  |
| Heyi's part      |
| `* * *`          | single administrator of small NPOs | find and list unpaired elderlies                                               | pair new incoming volunteers easily                                             |
| `* *`            | single administrator of small NPOs | view the last visited time/date of the elderly                                 | know when to plan the next visit                                                |
| `* *`            | single administrator of small NPOs | set up reminder system for elderlies                                           | plan volunteers to assist on those days                                         |
| `*`              | single administrator of small NPOs | track the befriending history of an elderly                                    | audit past involvements easily                                                  |
| Yong Jing's part |
| Zong Xun's part  |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to delete a specific person in the list
4.  AddressBook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 100 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  It is up to the user to ensure details of elderlies and volunteers entered into FriendlyLink is valid.
5.  The user should be responsible for the security of the data stored in FriendlyLink.
6.  FriendlyLink is only available in english

### Glossary

| Term         | Definition                                                                               |
|--------------|------------------------------------------------------------------------------------------|
| FriendlyLink | The name of the application                                                              |
| Volunteer    | Volunteers who are willing to pair up with and accompany Elderly members                 |
| Elderly      | Elderlies who need help from their buddies                                               |
| Pair         | The pair of people that consists of an elderly and the volunteer assigned to the elderly |
| NRIC         | A unique identifier given to all Singaporeans.                                           |

--------------------------------------------------------------------------------------------------------------------
