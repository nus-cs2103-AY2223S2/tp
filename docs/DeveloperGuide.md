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

* single administrator of Non-Profit Organisation (NPO) who needs to track volunteers and their assigned elderly
* works alone in managing volunteer and elderly information.
* tech-savvy
* has a need to manage a significant number of contacts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is comfortable using CLI apps

**Value proposition**: FriendlyLink streamlines volunteer and elderly management for single administrators of small NPOs.
With its easy-to-use text-based interface and contact management features, say goodbye to manual record-keeping and hello
to a more efficient and organised way of managing the volunteers’ and elderly’s contact details.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority   | As a …​                                                                                    | I want to …​                                                                           | So that I can…​                                                                   |
|------------|--------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| `* * *`    | single administrator of small NPOs                                                         | list volunteers                                                                        | see all the volunteers and their information                                      |
| `* * *`    | single administrator of small NPOs                                                         | add volunteer to the list of volunteers                                                | include new volunteers in the application                                         |
| `* * *`    | single administrator of small NPOs                                                         | delete volunteers                                                                      | remove volunteers that have left                                                  |
| `* * *`    | single administrator of small NPOs                                                         | remove all the pairs a volunteer is in                                                 | accurately keep track of which elderly are affected when a volunteer leaves       |
| `* * *`    | single administrator of small NPOs                                                         | read the list of elderly members                                                       | have a clear view of existing elderly members in system                           |
| `* * *`    | single administrator of small NPOs                                                         | add a new elderly member to the system                                                 |                                                                                   |
| `* * *`    | single administrator of small NPOs                                                         | remove an existing elderly member from the system                                      |                                                                                   |
| `* * *`    | single administrator of small NPOs                                                         | remove all the pairings an elderly member has when he / she is removed from the system | maintain accurate and error-free records of pairings                              |
| `* * *`    | single administrator of small NPOs                                                         | find the particular elderly member by search of nric                                   | access the information of each elderly member conveniently                        |
| `* * *`    | single administrator of small NPOs                                                         | edit the particulars of elderly members, such as names or addresses                    | manage elderly information in a more flexible manner                              |
| `* * *`    | single administrator of small NPOs                                                         | add a pair of a volunteer to an elderly                                                | to make sure every elderly member is taken care of                                |
| `* * *`    | single administrator of small NPOs                                                         | filter pairs by involved elderly members                                               | to quikcly find involved volunteers when elderly members are in need of attention |
| `* * *`    | single administrator of small NPOs                                                         | find and list unpaired elderlies                                                       | pair new incoming volunteers easily                                               |
| `* * *`    | single administrator of small NPOs                                                         | delete a pairing of a volunteer form an elderly                                        | to remove pairs that are no longer used                                           |
| `* *`      | single administrator of small NPOs                                                         | update volunteers & volunteer information                                              | keep the volunteer information up-to-date                                         |
| `* * `     | single administrator of small NPOs                                                         | search for particular volunteers by keywords                                           | quickly see the volunteer's details                                               |
| `* *`      | single administrator of small NPOs                                                         | view nursing / medical courses that volunteers have taken in the past                  | pair an elderly witha more suitable volunteer                                     |
| `* *`      | single administrator of small NPOs                                                         | filter and list elderly members by keyword search of name                              | increasing efficiency of finding elderly with certain names                       |
| `* *`      | single administrator of small NPOs                                                         | filter and list elderly members by age group                                           | dedicate more attentions to older members                                         |
| `* *`      | single administrator of small NPOs                                                         | filter and list elderly members by risk level                                          | dedicate more attentions to members with higher risks                             |
| `* *`      | single administrator of small NPOs                                                         | filter and list elderly members by region and community                                | pair volunteers who can better reach out to elderly living close-by               |
| `* *`      | single administrator of small NPOs                                                         | search elderly members by tags                                                         | access the information of elderly members with specific tags                      |
| `* *`      | single administrator of small NPOs                                                         | rank elderly members in the order of their medical risk level                          | better pair volunteers with more medical knowledge with higher-risk elderly       |
| `* *`      | single administrator of small NPOs                                                         | keep track of the region and community of the elderly members                          | reach out to the elderly members conveniently                                     |
| `* *`      | single administrator of small NPOs                                                         | view the last visited time/date of the elderly                                         | know when to plan the next visit                                                  |
| `* *`      | single administrator of small NPOs                                                         | set up reminder system for elderlies                                                   | plan volunteers to assist on those days                                           |
| `* *`      | single administrator of small NPOs                                                         | find a pair by keyword                                                                 | to quickly look up important information when required                            |
| `* *`      | single administrator of small NPOs                                                         | view overlapping pairs between the same volunteers or elderly members                  | to take note of overlapping work.                                                 |
| `* *`      | single administrator of small NPOs                                                         | filter pairs by tags                                                                   | to quickly find certain groups of elderly members for events or routine checkups  |
| `* *`      | single administrator of small NPOs                                                         | see summaries of number of elderly members assigned to each volunteer                  | to evenly distribute workload of volunteers                                       |
| `* *`      | single administrator of small NPOs                                                         | see min, max and average number of elderly buddies per volunteer                       | to evenly distribute workload of volunteers or to request for more resources      |
| `*`        | single administrator of small NPOs                                                         | filter volunteers by tags                                                              | access relevant groups of volunteers quickly                                      |
| `*`        | single administrator of small NPOs                                                         | manage volunteers by region                                                            | arrange the volunteers such that they can conveniently reach out to the elderly   |
| `*`        | single administrator of small NPOs                                                         | record the community information of volunteers, but not their specific address         | ensure that the volunteers' privacy is not compromised                            |
| `*`        | single administrator of small NPOs                                                         | manage the volunteers' available dates and time                                        | efficiently find volunteers available for activities                              |
| `*`        | single administrator of small NPOs                                                         | see how long a volunteer has been with the program                                     | assess their experience                                                           |
| `*`        | single administrator of small NPOs                                                         | track the befriending history of a volunteer                                           | audit past involvements easily                                                    |
| `*`        | single administrator of small NPOs                                                         | rank elderly members in the order of their loneliness situation                        | arrange more frequent volunteer visits for more lonely elderly                    |
| `*`        | single administrator of small NPOs                                                         | track the befriending history of an elderly                                            | audit past involvements easily                                                    |
| `*`        | single administrator of small NPOs                                                         | view past pairings                                                                     | to pair up members familiar with each other                                       |
| `*`        | single administrator of small NPOs                                                         | making recurring pairings                                                              | to handle recurrent changes in pairs.                                             |
| `*`        | single administrator of small NPOs                                                         | adjust frequency and period limit of pairings                                          | to facilitate regular swaps of volunteers and elderly members.                    |
| `*`        | single administrator of small NPOs                                                         | track important dates                                                                  | to facilitate regular volunteer check ins on elderly members.                     |
| `*`        | single administrator of small NPOs                                                         | set up reminders                                                                       | to remind volunteers of their commitments                                         |
| `*`        | single administrator of small NPOs                                                         | set up version control of the application                                              | trace commands that are executed throughout the lifetime of the application       | 
| `*`        | lazy single administrator of small NPOs                                                    | automatically pair up available volunteers to elderlies                                | quickly assign a volunteer to an elderly                                          | 
| `*`        | efficient single administrator of small NPOs                                               | use natural language dates                                                             | quickly assign add a volunteer availability into the database                     | 
| `*`        | forgetful single administrator of small NPOs                                               | autocomplete commands                                                                  | quickly complete the commands                                                     |  
| `*`        | organized single administrator of small NPOs                                               | add tags to volunteer, elderly and pairs                                               | filter the entities by tags                                                       |  
| `*`        | organized single administrator of small NPOs                                               | assign a random integer ID to each entry                                               | retrieve, modify and delete them directly without looking through the list        |  
| `*`        | organized single administrator of small NPOs who have used the application for a long time | retrieve summary statistics of elderlies, volunteers, and pairs in the database        | have a better understanding of the organisation and it's clients                  |  


### Use cases

(For all use cases below, the **System** is the `FriendlyLink (FL)` and the **Actor** is the `Admin`, unless specified otherwise)

**Use case: UC01- Pairs Volunteer and Elderly**

**MSS**

1.  User enters the details of elderly and volunteer to be paired into the application.
2.  FL adds the pair into the database, and feedbacks the successful addition of the pair.
3.  User see the pair details appear in the joint list.

    Use case ends.

**Extensions**

* 1a. FL detects that the elderly is not in the current database.
    * 1a1. FL informs User that the elderly has not been created.

    Use case ends.

* 1b. FL detects that volunteer is not in the current database.
    * 1b1. FL informs User that the volunteer has not been created.

    Use case ends.

* 1c. FL detects missing arguments or an error in the entered data.
    * 1c1. FL feedbacks that entered data is in a wrong format

    Use case ends.

* 1d. FL detects duplicate pair records in the entered data.

    * 1d1. FL feedbacks that it is a duplicate record, and rejects the data entry

    Use case ends.

**Use case: UC02- Add Elderly**

**MSS**

1.  User enters the details of elderly to be added into the application.
2.  FL adds the elderly into the database, and feedbacks the successful addition of the elderly.
3.  User see the elderly details appear in the elderly list.

    Use case ends.

**Extensions**

* 1a. FL detects missing arguments or an error in the entered data.
    * 1a1. FL requests for the correct data.
    * 1a2. User enters new data.
    
    Steps 1a1-1a2 are repeated until the data entered are correct.
    
    Use case ends.

* 1b. FL detects duplicate elderly records in the entered data.

    * 1b1. FL informs it is a duplicate record, requests for the new data.
    * 1b2. User enters new data.

    Steps 1b1-1b2 are repeated until the data entered are correct.

    Use case ends.

**Use case: UC03- Add Volunteer**

**MSS**

1.  User enters the details of volunteer to be added into the application.
2.  FL adds the volunteer into the database, and feedbacks the successful addition of the volunteer.
3.  User see the volunteer details appear in the volunteer list.

    Use case ends.

**Extensions**

* 1a. FL detects missing arguments or an error in the entered data.
    * 1a1. FL requests for the correct data.
    * 1a2. User enters new data.

  Steps 1a1-1a2 are repeated until the data entered are correct.

  Use case ends.

* 1b. FL detects duplicate volunteer records in the entered data.

    * 1b1. FL informs it is a duplicate record, requests for the new data.
    * 1b2. User enters new data.

  Steps 1b1-1b2 are repeated until the data entered are correct.

  Use case ends.

**Use case: UC04- Unpair Volunteer and Elderly**

**MSS**

1.  User enters the pair details (elderly & volunteer) to be deleted into FL.
2.  FL deletes the pair from the database, and feedbacks the successful unpairing.
3.  User see the pair details removed from the joint list.

    Use case ends.

**Extensions**

* 1a. FL detects that the pair is not in the current database.
    * 1a1. FL informs User that the pair has not been created.

  Use case ends.

* 1b. FL detects missing arguments or an error in the entered data.
    * 1b1. FL feedbacks that entered data is in a wrong format

  Use case ends.

**Use case: UC05- Delete Volunteer**

**MSS**

1.  User enters the NRIC of the volunteer to be deleted.
2.  FL deletes the volunteer from the database, and feedbacks the successful deletion of the volunteer.
3.  User see the volunteer details removed from the volunteer list.

    Use case ends.

**Extensions**

* 1a. FL detects missing arguments or an error in the entered data.
    * 1a1. FL requests for the correct data.
    * 1a2. User enters new data.

  Steps 1a1-1a2 are repeated until the data entered are correct.

  Use case ends.

* 1b. FL detects that the volunteer is not inside the records.

    * 1b1. FL informs that the volunteer does not exist, and requests for the new data.
    * 1b2. User enters new data.

  Steps 1b1-1b2 are repeated until the data entered are correct.

  Use case ends.

**Use case: UC06-  Delete Elderly**

**MSS**

1.  User enters the NRIC of the elderly to be deleted.
2.  FL deletes the elderly from the database, and feedbacks the successful deletion of the elderly.
3.  User see the elderly details removed from the elderly list.

    Use case ends.

**Extensions**

* 1a. FL detects missing arguments or an error in the entered data.
    * 1a1. FL requests for the correct data.
    * 1a2. User enters new data.

  Steps 1a1-1a2 are repeated until the data entered are correct.

  Use case ends.

* 1b. FL detects that the elderly is not inside the records.

    * 1b1. FL informs that the elderly does not exist, and requests for the new data.
    * 1b2. User enters new data.

  Steps 1b1-1b2 are repeated until the data entered are correct.

  Use case ends.

**Use case: UC07-  Exit application**

**MSS**

1.  User press the “X” button to exit the application.
2.  FL closes the application.

    Use case ends.


### Non-Functional Requirements

1. FriendlyLink should work on Microsoft Windows, macOS, and Linux that has `Java 11` is installed.
1. FriendlyLink Should be able to hold up to 100 persons (elderly and volunteer) without incurring a delay larger than 3 second for any command.
1. A user with above average typing speed (40wpm) for regular English text (i.e. not code, not system admin commands) should be able to perform at least 75% of use cases faster using commands instead of using the mouse.
1. FriendlyLink will perform minimal checks on correctness of details entered into FriendlyLink (as specified in the user guide advanced section).
1. FriendlyLink will not be responsible for the privacy and security of the data stored in FriendlyLink.
1. FriendlyLink will not recover from corrupted data files.
1. FriendlyLink will only available in English.

### Glossary

| Term         | Definition                                                                               |
|--------------|------------------------------------------------------------------------------------------|
| FriendlyLink | The name of the application                                                              |
| Volunteer    | Volunteers who are willing to pair up with and accompany Elderly members                 |
| Elderly      | Elderlies who need help from their buddies                                               |
| Pair         | The pair of people that consists of an elderly and the volunteer assigned to the elderly |
| NRIC         | A unique identifier given to all Singaporeans.                                           |

--------------------------------------------------------------------------------------------------------------------
