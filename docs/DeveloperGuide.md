## User Guide

### Product scope

**Target user profile**:

* has a need to manage a significant number of flights, pilots and crews
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Airline managers will be able to take labor, welfare, and resource optimization 
into consideration such that they can assign tasks to the most appropriate crew based on their location 
and availability while optimizing their staff’s physical well-being.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- |--------------------------------------------| ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | airline manager    | add new locations to the list of locations where we operate         | I can assign new departing locations and arrival locations |
| `* * *`  | airline manager    | list old locations from the locations where we were operating       |    I can view all the locations    |
| `* * *`  | airline manager    | remove old locations from the locations where we were operating     | I can update departing locations and arrival locations |
| `* * *`  | airline manager    | add crew to our workforce     | I can assign them to flights |
| `* * *`  | airline manager    | list crew from our workforce     | I can view all the crew |
| `* * *`  | airline manager    | delete crew from our workforce     | I can remove them from flights |
| `* * *`  | airline manager    | add new planes to our fleet                                     | I can assign them to flights                               |
| `* * *`  | airline manager    | list planes from our fleet                                      | I can view all the planes in our fleet                     |
| `* * *`  | airline manager    | remove old planes from our fleet                                | I can update planes which can be used for flights          |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a location**

**MSS**

1.  User requests to list locations
2.  Wingman shows a list of locations
3.  User requests to delete a specific location in the list
4.  Wingman deletes the location
5.  User request to add a location
6.  Wingman adds the location

    Use case ends.

**Extensions**

* 2a. The list is empty.
  Use case ends.

* 3a. The given location is invalid.
    * 3a1. AddressBook shows an error message.
      Use case resumes at step 2.

* 5b. The given location is a duplicate of an existing one
   *  5b1. Wingman shows an error message.
      Use case resumes at step 4. 
      
**Use case: Delete a crew**

**MSS**

1.  User requests to list crew
2.  Wingman shows a list of crew
3.  User requests to delete a specific crew in the list
4.  Wingman deletes the crew
5.  User request to add a crew
6.  Wingman adds the crew

**Extensions**

* 2a. The list is empty.
  Use case ends.

* 3a. The given crew is invalid.
    * 3a1. AddressBook shows an error message.
      Use case resumes at step 2.

* 5b. The given crew is a duplicate of an existing one
   *  5b1. Wingman shows an error message.
      Use case resumes at step 4. 
    *  5b1. Wingman shows an error message.
       Use case resumes at step 4.

**Use case: Delete a plane**

**MSS**

1.  User requests to list planes
2.  Wingman shows a list of planes
3.  User requests to delete a specific plane in the list
4.  Wingman deletes the plane
5.  User request to add a plane
6.  Wingman adds the plane


    Use case ends.

**Extensions**

* 2a. The list is empty.
  Use case ends.
  
* 3a. The given plane is invalid.
  * 3a1. The AddressBook shows an error message. Use case resumes at step 2.
  
* 5b. The given plane is a duplicate of an existing plane.
  * 5b1. Wingman shows an error message. Use case resumes at step 4.


*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 100 flights, crews, and pilots without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system should respond within a second.
5.  The data should be stored locally and should be in a human editable text file.

*{More to be added}*

### Glossary
* **Plane**: A unit plane which can be assigned to flights.
* **Location**: A unit place that flights may depart from or arrive at. 
* **crew**: A unit person who can be added to or deleted from a flight.
