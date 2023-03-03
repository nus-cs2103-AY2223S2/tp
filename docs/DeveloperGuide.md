---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

The set-up guide is still **_in progress_**

--------------------------------------------------------------------------------------------------------------------

## Architecture

The documentation for Architecture is still **_in progress_**

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**
We hope to target admin staff within a clinic who have to settle triaging of patients.<br>
Here are some characteristics of our target user profile: <br>
* needs to assign patients to the appropriate doctors quickly
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Perform quick lookup and assignment of appropriate doctors to each patient in triage, faster than a typical mouse/GUI driven app.


### User stories

In the table below, **_user_** refers to the triage admin staff.

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                                                     | So that I can…​                                                                       |
|----------|--------------------------------------------|------------------------------------------------------------------|---------------------------------------------------------------------------------------|
| `* * *`  | user                                       | add doctor contacts into my address book                         | store their contacts in case I need them in the future                                |
| `* * *`  | user                                       | look up doctors by their name, specialty, department and position | assign patients to relevant doctors                                                   |
| `* * *`  | user                                       | sort doctors based on specialty                                  | assign patients to doctors according to nature of condition                           |
| `* * *`  | user                                       | sort doctors based on years of experience                        | assign patients to doctors according to severity of condition                         |
| `* * *`  | user                                       | sort doctors based on availability                               | assign patients to doctors currently present in the clinic                            |
| `* * *`  | user                                       | edit the doctor contacts in Docedex                              | keep the doctor contacts up to date                                                   |
| `* * *`  | user                                       | delete doctors that have left the hospital or have retired       | ensure all doctor contacts reflect doctors in the hospital                            |
| `* * *`  | user                                       | save my doctor contacts in my desktop                            | refer to doctor contacts in the future                                                |
| `* * *`  | user                                       | load my contacts from a file when I boot up the application      | refer to doctor contacts created in the past                                          |
| `* *`    | user                                       | exit the application through the CLI                             |                                                                                       |
| `* *`    | user                                       | access the help menu                                             | know how to use the commands within Docedex                                           |
| `*`      | user                                       |                                                                  |                                                                                       |
| `*`      | user                                       |                                                                  |                                                                                       |
| `*`      | user                                       |                                                                  |                                                                                       |
| `*`      | user                                       | add remarks for doctors                                          | store additional information on each doctor                                           |
| `*`      | user                                       | star certain doctors as important                                | perform quick retrieval of information pertaining to important doctors                |
| `*`      | user                                       | see the history of doctors I viewed recently                     | re-access recently queried doctor contacts quickly                                    |
| `*`      | user                                       | retrieve the size of my contact book for doctors                 | varify the number of doctors in my clinic                                             |
| `*`      | user                                       | archive doctors that are no longer working            | I can retain information about such doctors without having them appear in my searches |
| `*`      | user                                       |                                                                  |                                                                                       |
| `*`      | user                                       |                                                                  |                                                                                       |
| `*`      | user                                       |                                                                  |                                                                                       |
| `*`      | user                                       |                                                                  |                                                                                       |

### Use cases

(For all use cases below, the **System** is `Docedex` and the **Actor** is the `user`, unless specified otherwise)

**Use case: UC3 - Delete Doctor**

Actor: User

**MSS**

1. User enters command to delete a doctor. 
2. Docedex confirms the deletion of the doctor contact.<br> 
   Use case ends.

**Extensions**

* 1a. Docedex detects an error in the command.
  * 1a1. Docedex requests to correct the format of the command.
  * 1a2. User enters command to delete a doctor.<br>
  Steps 1a1-1a2 are repeated until a valid delete command is entered.<br>
  Use case resumes from step 2.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 doctor contacts and 30,000 patient contacts without noticeable reduction in performance.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should not utilize any network to transmit any information.
5. The average time required to boot up the application should be under 5 seconds.
6. Feedback from Docedex should be displayed within 2 seconds of the user's input.
7. The file size of the application's `jar` should not exceed 100MB.
8. Should utilize less than 300MB of memory when in use.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X.
* **User**: Triage Admin Staff within the clinic.
* **Contact**: Data entry that stores the contact information of a doctor or patient in Docedex.
* 

--------------------------------------------------------------------------------------------------------------------
