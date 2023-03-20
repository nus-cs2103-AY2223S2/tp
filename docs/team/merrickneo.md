---
layout: page
title: <Merrick Neo> Project Portfolio Page
---

### Project: HMHero

### What should be included

- [x] Code contributed
- [x] Enhancements implemented
- [x] Contributions to the UG (Optional)
- [x] Contributions to the DG (Optional)
- [x] Contributions to team-based tasks
- [x] Review/mentoring contributions
- [x] Contributions beyond the project team

(**Overview**)

- HMHero helps Hiring Managers track the statuses of candidates' applications

(**Summary of Contributions**)
1.  Enhanced `DeleteCommand` and `DeleteCommandParser` to remove an applicant based on their name and phone number, which was previously implemented using indexes.

Example Input: `delete n/John Doe p/(John Doe's number)`

Output: `Deleted Person: John Doe; Phone: 91234567; Email: johndoe@gmail.com; Address: SoC; Status: REJECTED; Tags: [Python]`

2. Refactor tags to notes to allow users to note down traits and skillsets of applicants

3. Implemented a `DateTimeParser` class to help check and validate interview dates for applicants.
> Format: `DD-MM-YYYY HH:MM`
- If the date provided is not in the right format, the error message `Invalid date and time provided` will be presented to the user.

### Test Cases
1. Wrote test cases for `DateTimeParser` and `InterviewDateTime`
- Test cases provides full path coverage for the classes
- Checks all valid and invalid datetime scenarios provided by the user

2. Wrote test cases for  `DeleteCommand` and `DeleteCommandParser`
- Rewrote test cases to test the new format for a  `DeleteCommand`
- Checked positive cases where both name and phone number was provided
- Checked negative cases where either the name or phone fields were missing

(**This part can change according to your contributions**)

Example

- to be added soon

(**Code contributions**)

Example

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=merrickneo&breakdown=true)

- **Project management**:

  - Set up Google Drive to help facilitate document sharing within team members
  - Set up recurring Zoom meetings to discuss the team's progress and future plans

(**Enhancements implemented**)

- **Enhancements to existing features**:

  - Make error messages presented more specific to the user's situation
  - Example: 
  `Invalid Date and Time Provided. Expected Format: "DD-MM-YYYY HH:MM"`
 

- **Documentation**:

  - User Guide
    - Added documentation for the feature `add`
  - Developer Guide
    - Added non-functional requirements for the project.

- **Community**:

  - Reviewed Pull Requests (PRs) made by team members

- **Tools**:

  - to be added soon
