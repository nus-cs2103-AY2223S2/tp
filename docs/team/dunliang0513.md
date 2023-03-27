---
layout: page
title: <Foo Dun Liang> Project Portfolio Page
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

(**Code contributions**)

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dunliang0513&breakdown=true)

- **Project Management**

(**Enhancements implemented**)

- **Features implemented**

1. Implemented Find Command
  - Can use applicant's name or phone or both as a keyword

Example Input: `find Pauline`

> Original List:

```
Listed all applicants
Total Applicants: 3
Applied: 2
Shortlisted:

1. Alice Pauline 91234567 (Applied)
2. Alex Pauline 94351253 (Shortlisted)
3. Joe Doe 92849104 (Applied)
```

Example Output:

```
2 persons listed!

1. Alice Pauline 91234567 (Applied)
2. Alex Pauline 94351253 (Shortlisted)
```

Example Input: `find 91234567`

> Original List:

```
Listed all applicants
Total Applicants: 3
Applied: 2
Shortlisted:

1. Alice Pauline 91234567 (Applied)
2. Alex Pauline 94351253 (Shortlisted)
3. Joe Doe 92849104 (Applied)
```

Example Output:

```
1 persons listed!

1. Alice Pauline 91234567 (Applied)
```

2. Implemented Remind Command
  - Remind the Hiring Manager when there is any applicant going to have interview within three days.

Assume current time is 22-03-2023 18:00

Example Input: `remind`

> Original List 1:

```
Listed all applicants
Total Applicants: 3
Applied: 2
Shortlisted:

1. Alice Pauline (Applied)
2. Alex Pauline (Shortlisted) 24-03-2023 18:00
3. Joe Doe (Applied)
```

Example Output 1:

```
Listed all applicants that going to have interview within three days!

1. Alex Pauline (Shortlisted) 24-03-2023 18:00
```

> Original List 2:

```
Listed all applicants
Total Applicants: 3
Applied: 2
Shortlisted:

1. Alice Pauline (Applied)
2. Alex Pauline (Shortlisted) 26-03-2023 18:00
3. Joe Doe (Applied)
```

Example Output 2:

```
There is no applicant having interview within three days.

```

- **Test Cases**:

1. Wrote test cases for RemindCommand
  - Test cases that cover possible paths taken by `execute(Model)` in `RemindCommand` class

- **Documentation**:

  - User Guide

    - Created the template of our team User Guide
    - Added documentation for the Troubleshooting
    - Added documentation for the FAQ
    - Added documentation for the Glossary

  - Developer Guide
    - Added documentation and diagram for Architecture
    - Added documentation and diagram for Ui
    - Added documentation and diagram for Storage

- **Community**:

  - Reviewed Pull Requests (PRs) made by team members

- **Tools**:

  - to be added soon
