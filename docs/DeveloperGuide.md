# Developer Guide

# Acknowledgments

Codebase foundation by AB3.

---

# Setting up, getting started

## Setting up the project on your computer

<aside>
❗ Caution: Follow the steps in the following guide precisely. Things will not work out if you deviate in some steps.

</aside>

First, **fork** this repo, and **clone** the fork into your computer.

If you plan to use IntelliJ IDEA (highly recommended):

1. **Configure the JDK**: Follow the guide *[[se-edu/guides] IDEA: Configuring the JDK](https://se-education.org/guides/tutorials/intellijJdk.html)* to ensure Intellij is configured to use **JDK 11**.
2. **Import the project as a Gradle project**: Follow the guide *[[se-edu/guides] IDEA: Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)* to import the project into IDEA. 

<aside>
💡 Note: Importing a Gradle project is slightly different from importing a normal Java project.

</aside>

1. **Verify the setup**:
    1. Run the `seedu.address.Main` and try a few commands.
    2. [Run the tests](https://se-education.org/addressbook-level3/Testing.html) to ensure they all pass.

## Before writing code

1. **Configure the coding style** 
    
    If using IDEA, follow the guide *[[se-edu/guides] IDEA: Configuring the code style](https://se-education.org/guides/tutorials/intellijCodeStyle.html)* to set up IDEA’s coding style to match ours.
    

<aside>
💡 Tip: Optionally, you can follow the guide *[[se-edu/guides] Using Checkstyle](https://se-education.org/guides/tutorials/checkstyle.html)* to find how to use the CheckStyle within IDEA e.g., to report problems *as* you write code.

</aside>

1. **Set up CI**
    
    This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the `master` branch or to any PR. No set up is required.
    
2. **Learn the design**
    
    When you are ready to start coding, we recommend that you get a sense of the overall design by reading about [AddressBook’s architecture](https://se-education.org/addressbook-level3/DeveloperGuide.html#architecture).
    
3. **Do the tutorials** These tutorials will help you get acquainted with the codebase.
    - [Tracing code](https://se-education.org/addressbook-level3/tutorials/TracingCode.html)
    - [Adding a new command](https://se-education.org/addressbook-level3/tutorials/AddRemark.html)
    - [Removing fields](https://se-education.org/addressbook-level3/tutorials/RemovingFields.html)

---

# Appendix: Requirements

## Product scope

**************************Target user profile: Recruiters**************************

- Has a need to manage a significant number of job listings.
- Prefer desktop apps over other types.
- Can type fast.
- Prefers typing to mouse interactions.
- Is reasonably comfortable using CLI apps

**************************************Value proposition:************************************** All-in-one app for managing job listings with an intuitive user experience

## User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| No. | Priority | As a …​ | I want to …​ | So that… |
| --- | --- | --- | --- | --- |
| 1 | * * * | Recruiter who receives thousands of applications each day.  | store all information about my applicants | I don't lose track of any applicants. |
| 2 | * * * | Recruiter | post a new job posting | I can start attracting applicants. |
| 3 | * * * | Recruiter | list out information regarding a job offer | I can confirm the information |
| 4 | * * * | Recruiter | update job listings | it reflects the most updated information |
| 5 | * * * | Recruiter | view all job listings |  |
| 6 | * * * | Recruiter | delete outdated job listing | the information is accurate |
| 7 | * * | Recruiter | sort job listings by expiry date | finding most urgent job listing can be easy |
| 8 | * * * | Recruiter | view all applicants |  |
| 9 | * * * | Recruiter | view applicants for each job listing | I know who has applied for each job |
| 10 | * * * | Recruiter | add applicants to job listing |  |
| 11 | * * * | Recruiter | remove disqualified applicant | the database stays clean |
| 12 | * * * | Recruiter | view applicant details | I can make sure it’s updated |
| 13 | * * * | Recruiter | update applicant status | it’s easy for me to track the progress of each applicant |
| 14 | * * * | Recruiter | update applicant details | each applicant’s info is updated |
| 15 | * * * | Recruiter | auto-remove job listings from view if they are expired | viewing job listings won't be cluttered |
| 16 | * * * | Recruiter | auto-remove job listings from view if vacancy fulfilled | the current list of jobs are all still available |
| 17 | * * * | Recruiter | search job listings by title |  |
| 18 | * * * | Recruiter | prevent applicants from applying to the same job more than once | the list of applicants is not cluttered |
| 19 | * * | Recruiter | add tag indicating relevant hiring department to the job listing |  |
| 20 | * * | Recruiter | view the tag attached to the job listing | it's easy to find types of job listings |
| 21 | * * | Recruiter | change the tag attached to the job listing | update the tag to reflect the most updated information |
| 22 | * * | Recruiter from a particular department | filter the job listings according to the tag | easily find jobs |
| 23 | * * * | Recruiter | search for applicants in each job listing |  |
| 24 | * * | Recruiter | sort job listings by the number of applicants |  |
| 25 | * * * | Recruiter | update the status of applicants | it's easy for us to know how to contact the applicant |
| 26 | * * * | Recruiter | filter status of applicants | easily find the types of applicants I’m looking for |
| 27 | * * * | Recruiter | sort the applicant by status |  |
| 28 | * * | Recruiter | add notes about applicants | it’s easy to remember details about each applicant |
| 29 | * * | Recruiter | get help with how to use the program | I know what commands I have available |
| 30 | * * * | Recruiter | come back to the program and continue from where I left off | I won't lose my progress  |

*{More to be added}*

## Use cases

(For all use cases below, the **System** is the `ListingBook` and the **Actor** is the `Recruiter` unless specified otherwise)

### **Use case: Delete a Listing**

**MSS**

1. Recruiter requests to list listings
2. ListingHub shows a list of listings
3. The recruiter requests to delete a specific listing from the list
4. ListingHub deletes the listing
    
    Use case ends.
    

**Extensions**

- 2a. The list is empty.
    
    Use case ends.
    
- 3a. The given index is invalid.
    - 3a1. ListingHub shows an error message.
        
        Use case resumes at step 2.
        


### **Use case: List all job listings**

**MSS**

1. Recruiter requests to list job listings
2. ListingBook shows a list of job listings
Use case ends.

**Extensions**

- 2a. The list is empty.
Use case ends.


### **Use case: Add a new job listing**

**MSS**

1. The recruiter requests to add a new job listing
2. ListingBook adds the job listing to the list of job listings
Use case ends.

********************Extensions********************

- 2a. The placeholders used are invalid
    - 2a1. ListingBook shows an error message.
        
        Use case resumes at step 1.
        
- 2b. The new job title is invalid
    - 2b1. ListingBook shows an error message.
        
        Use case resumes at step 1.
        
- 2c. The new job description is invalid
    - 2c1. ListingBook shows an error message.
        
        Use case resumes at step 1.
        
- 2d. There is a duplicate listing in the listing book
    - 2d1. ListingBook shows an error message.
        
        Use case resumes at step 1.
        
- 2e. Update filtered listing got some problem la idk
    - 2e1. ListingBook shows an error message.
        
        Use case resumes at step 1.
        


### **Use case: Update a job listing**

**MSS**

1. Recruiter requests to update a job listing
2. ListingBook shows a list of job listings
3. The recruiter requests to update a specific listing from the list
4. ListingBook updates the job listing
Use case ends.

**Extensions**

- 2a. The list is empty.
Use case ends.
- 3a. The given index is invalid.
    - 3a1. ListingBook shows an error message.
    Use case resumes at step 2.
- 3b. The placeholders used are invalid
    - 3b1. ListingBook shows an error message.
        
        Use case resumes at step 2.
        
- 3c. The new job title is invalid
    - 3c1. ListingBook shows an error message.
        
        Use case resumes at step 2.
        
- 3d. The new job description is invalid
    - 3d1. ListingBook shows an error message.
        
        Use case resumes at step 2.
        
- 3e. There is a duplicate listing in the listing book
    - 3e1. ListingBook shows an error message.
        
        Use case resumes at step 2.
        
- 3f. Update filtered listing got some problem la idk
    - 3f1. ListingBook shows an error message.
        
        Use case resumes at step 2.
        


### **Use case: Find a job listing**

**MSS**

1. Recruiter requests to find a job listing by keyword(s).
2. ListingBook displays a list of job listings that match the keyword
Use case ends.

**Extensions**

- 2a. No job listings match the keyword.
Use case ends.
- 2b. The list is empty.
    
    Use case ends.
    

### **Use case: Sort job listings**

**MSS**

1. Recruiter requests to sort job listings
2. ListingBook sorts the job listings according to the selected option
3. ListingBook displays the sorted list of job listings
Use case ends.

********************Extensions********************

- 2a. The list is empty.
    
    Use case ends.
    

### **Use case: Filter job listings**

**MSS**

1. Recruiter requests to filter job listings
2. ListingBook filters the job listings according to the selected option
3. ListingBook displays the filtered list of job listings
Use case ends.

**Extensions**

- 4a. No job listings match the filter criteria.
Use case ends.
- 4b. The list is empty.
    
    Use case ends.
    

## Non-Functional Requirements

1. Should work on any *mainstream OS* as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 listings without a noticeable sluggishness in performance for typical usage.
3. A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. Should be maintainable and have a clear code structure and documentation, so new updates and bug fixes can be easily implemented.
5. Should be easy to use with clear instructions and meaningful error messages.

## Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Private contact detail**: A contact detail that is not meant to be shared with others
