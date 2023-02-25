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

* is an administrator of a small Non-Profit Organisation (NPO) who needs to track volunteers and their assigned buddy elderly.
* works alone in managing volunteer and elerly information.
* tech-savvy.
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

| Priority         | As a …​                            | I want to …​                                                           | So that I can…​                                                                        |
|------------------|------------------------------------|------------------------------------------------------------------------|----------------------------------------------------------------------------------------|
| Shaun's part     |
| `* *`            | single administrator of small NPOs | view nursing / medical courses that volunteers have taken in the past  | pair an elderly with a more suitable volunteer                                         |
| `*`              | single administrator of small NPOs | see how long a volunteer has been with the program                     | assess their experience                                                                |
| `*`              | single administrator of small NPOs | track the befriending history of a volunteer                           | audit past involvements easily                                                         |
| Heyi's part      |
| `* * *`          | single administrator of small NPOs | find and list unpaired elderly members                                 | pair new incoming volunteers easily                                                    |
| `* *`            | single administrator of small NPOs | view the last visited time/date of the elderly                         | know when to plan the next visit                                                       |
| `* *`            | single administrator of small NPOs | set up reminder system for elderly members                             | plan volunteers to assist on those days                                                |
| `*`              | single administrator of small NPOs | track the befriending history of an elderly                            | audit past involvements easily                                                         |
| Yong Jing's part |
| `* * *`          | single administrator of small NPOs | add a pair of a volunteer to an elderly                                | to make sure every elderly member is taken care of                                     |
| `* *`            | single administrator of small NPOs | find a pair by keyword                                                 | to quickly look up important information when required                                 |
| `* * *`          | single administrator of small NPOs | delete a pairing of a volunteer form an elderly                        | to remove pairs that are no longer used                                                |
| `* *`            | single administrator of small NPOs | view overlapping pairs between the same volunteers or elderly members  | to take note of overlapping work.                                                      |
| `* * *`          | single administrator of small NPOs | filter pairs by involved elderly members                               | to quikcly find involved volunteers when elderly members are in need of attention      |
| `* *`            | single administrator of small NPOs | filter pairs by tags                                                   | to quickly find certain groups of elderly members for events or routine checkups       |
| `* *`            | single administrator of small NPOs | see summaries of number of elderly members assigned to each volunteer  | to evenly distribute workload of volunteers                                            |
| `* *`            | single administrator of small NPOs | see min, max and average number of elderly buddies per volunteer       | to evenly distribute workload of volunteers or to request for more resources           |
| `*`              | single administrator of small NPOs | view past pairings                                                     | to pair up members familiar with each other                                            |
| `*`              | single administrator of small NPOs | making recurring pairings                                              | to handle recurrent changes in pairs.                                                  |
| `*`              | single administrator of small NPOs | adjust frequency and period limit of pairings                          | to facilitate regular swaps of volunteers and elderly members.                         |
| `*`              | single administrator of small NPOs | track important dates                                                  | to facilitate regular volunteer check ins on elderly members.                          |
| `*`              | single administrator of small NPOs | set up reminders                                                       | to remind volunteers of their commitments                                              |
| Zong Xun's part  |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC01 - Pairs Volunteer and Elderly**

**MSS**

1. User enters the details of elderly and volunteer to be paired into the application.
2. FL feedbacks the successful addition of the pair.
3. The pair details appear in the joint list.

    Use case ends.

**Extensions**

* 1a. FL detects that Elderly is not in the current database.

  * 1a1. FL informs User that the Elderly has not been created.

  Use case ends

* 1b. FL detects that Volunteer is not in the current database.

  * 1b1. FL informs User that the Volunteer has not been created.

  Use case ends

* 1c. FL detects missing arguments or an error in the entered data.

  * 1c1. FL feedbacks that entered data is in a wrong format

  Use case ends.

* 1d. FL detects duplicate pair records in the entered data.

  * 1d1. FL feedbacks that it is a duplicate record, and rejects the data entry.

  Use case ends.

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
