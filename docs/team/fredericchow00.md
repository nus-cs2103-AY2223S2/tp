---
layout: page
title: <Frederic Chow> Project Portfolio Page
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

- Implemented "list" feature that lists out all applicants across all statuses,
with a statistic that shows the number of applicants in each status

Example Input: `list`

Example Output:
```
Here are the list of all applicants:
1. John Doe (Applied)
2. Jane Doe (Shortlisted)
3. Joe Doe (Accepted)

There are a total of 3 applicants.
Applied: 1
Shortlisted: 1
Accepted: 1
Rejected: 0
```

- Enhanced the `AdvanceCommand` and `AdvanceCommandParser` to detect when there should or should not be 
a need for the user to provide an interview date for the applicant when the user calls the `Advance` feature


Example 1: John Doe's application status is `Applied`, so calling the `Advance` command would change 
John Doe's status to `Shortlisted`, where an interview date is mandatory. 

Example 2: Calling `Advance` on John Doe again change John Doe's status to `Accepted` and hence, the command 
would *NOT* require an interview date. 

(**Code contributions**)

Example

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=fredericchow00&breakdown=true)

- **Project management**:

  - to be added soon

(**Enhancements implemented**)

- **Enhancements to existing features**:

  - to be added soon

- **Documentation**:

  - User Guide
    - Added documentation for the features `list`

  - Developer Guide
    - Added documentation for Product Scope and User Stories

- **Community**:

  - Reviewed Pull Requests (PRs) made by team members

- **Tools**:

  - to be added soon

