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

(**This part can change according to your contributions**)

(**Code contributions**)

Example

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=merrickneo&breakdown=true)

- **Project management**:

  - Set up Google Drive to help facilitate document sharing within team members
  - Set up recurring Zoom meetings to discuss the team's progress and future plans

(**Enhancements implemented**)

- **Features implemented**
1. Implemented a `DateTimeParser` class to help check and validate interview dates for applicants.
> Format: `DD-MM-YYYY HH:MM`
- If the date provided is not in the right format, the error message `Invalid date and time provided` will be presented to the user.


2. Implemented `SummaryCommand` to provide users with useful statistics on their hiring processes.
- Summary statistics provided include:
  * Average time to interview
  * Percentage of applicants with interviews


- **Enhancements to existing features**:

1. Make error messages presented more specific to the user's situation. 
> Example: `Invalid Date and Time Provided. Expected Format: "DD-MM-YYYY HH:MM"`

2. Enhance `AddCommand` to show the original list after each `AddCommand`.

3. Enhance `DeleteCommand` to identify an `Applicant` using their name and phone number
- Original implementation was to identify `Applicants` based on indexes
  - However, when there are too many applicants in the list, identifying an applicant this way may be challenging.
  - This enhancement then allows for easy deletion of `Applicants` by providing the above-mentioned fields to uniquely identify the unique individual.
> Example Input: `delete n/John Doe p/(John Doe's number)`
>
> Output: `Deleted Person: John Doe; Phone: 91234567; Email: johndoe@gmail.com; Address: SoC; Status: REJECTED; Notes: [Python]`

### Test Cases
1. Wrote test cases for `DateTimeParser` and `InterviewDateTime`
- Test cases provides full path coverage for the classes
- Checks all valid and invalid datetime scenarios provided by the user

2. Wrote test cases for  `DeleteCommand` and `DeleteCommandParser`
- Rewrote test cases to test the new format for a  `DeleteCommand`
- Checked positive cases where both name and phone number was provided
- Checked negative cases where either the name or phone fields were missing and whether the applicant is tracked in HMHero.

3. Edit test cases for `AddCommand`
- Rewrote test cases to check if the model rejects a duplicate Applicant.
- Make adjustments to test cases to support the updating of the model to the orignal list after
each `AddCommand`.

4. Wrote test cases for `InterviewCommand`
- Check if the output of the command tallies with the expected behaviour.

5. Wrote test cases for `SummaryCommand`
- Check if summary statistics are calculated correctly
- Checked if the correct statistics are output to the user when the ApplicantList is both filled and empty.


- **Documentation**:

  - User Guide
    - Added documentation for the feature `add`.
    - Added documentation on the Quick Start section.
    - Added restrictions to the Placeholders for Commands.

  - Developer Guide
    - Added non-functional requirements for the project.
    - Added documentation for the design concept for HMHero.
    - Added sequence and activity diagrams for the `DeleteCommand`, `EditCommand` and `ListCommand`.

- **Community**:

  - Reviewed Pull Requests (PRs) made by team members
  - Assign issues to members for better tracking of tasks for milestones

- **Tools**:
  - SourceTree
  - Git & Github
  - Java
