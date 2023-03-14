## **Appendix: Requirements**

### Product Scope

**Target user profile**:

* Students studying computing-related courses looking for internships
* Reasonably comfortable using CLI apps
* Has a need to manage a significant number of internship positions

**Value Proposition**: manage jobs faster than a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a…. | I want to …                                | So that I can…                                          |
|----------|--------|--------------------------------------------|---------------------------------------------------------|
| ***      | user   | add contacts for companies                 | quickly send my application through those contacts      |
| ***      | user   | add a job/internship to the program        | see whether the internship is suitable for me           |
| ***      | user   | view all the internships that I have added | see all the internships that I'm interested in one shot |
| ***      | user   | delete company internship                  | ignore the companies that I don't like                  |
| ***      | user   | Save data                                  | view my internship job later on                         |
| **       | user   | rank the jobs                              | know which job I am most interested                     |

### Use cases
(For all use cases below, the System is the TechTrack and the Actor is the user, unless specified otherwise)


**Use case: Add a job**

**MSS**

1. User opens the program
2. User enters job to add a job
3. TechTrack adds a job to its list.

Use case ends.

**Extensions**
* 3a. Job exist
    * 3a1. TechTrack
    Use case resumes at step 3b.
* 3b. Save job
    * 3b1. Job is auto-saved
    Use case resumes at step 2.
* 3c. Duplicate job detected
    * 3c1. TechTrack outputs error
    Use case resumes at Step 2
* 3d. Invalid data detected
    * 3d1. TechTrack outputs error
    Use case resumes at Step 2




**Use case: View a job**
**MSS**
1. User opens the application
2. User enters the “view” command
3. UI displays more specific details on the jobs saved

Use case ends.

**Extensions:**
* 2a. Job does not exist
    * 2a1. TechTrack outputs error
    Use case resumes at step 2.
* 2b. List is empty 
  Use Case ends.



**Use case: Delete a job**

**MSS**
1. User opens the application
2. User enters the “view” command to see which jobs to delete
3. UI display the list of jobs with its index
4. User enters the “delete {job ID}” to delete the jobs
5. UI will response with the selected jobs being deleted

Use case ends.

**Extensions**
* 2a. The list is empty.
    Use case ends.
* 3a. The given index is invalid.
    * 3a1. TechTrack shows an error message.
    Use case resumes at step 2.

* 4a. Save job
    * 4a1. Job is saved




### Non-function requirement

1. Should work on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 roles without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Job**: Refers to internship job/job posting/internship role
