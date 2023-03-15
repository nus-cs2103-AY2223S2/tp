---
layout: page
title: <Li Bo Feng> Project Portfolio Page
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

- Implemented `NamePhoneNumberPredicate` class to allow `advance`, `reject` and `find` features to access the Applicant 
  by the `name` and `phone`

Example Input: `advance n/John Doe p/91918153`,

>Original List:
```
Here are the list of all applicants:
1. John Doe (Applied) 91918153 
2. Jane Doe (Shortlisted) 98762345
3. Joe Doe (Accepted) 91234321
```
Example Output:
```
Successfully advanced John Doe


Here are the list of all applicants:
1. John Doe (Shortlisted) 91918153 
2. Jane Doe (Accepted) 98762345
```

- Implemented `advance` feature to advance applicants' status from `Applied` -> `Shorlisted` and 
  `Shorlisted` -> `Accepted` to be tracked in HMHero

Example Input: `advance n/John Doe p/91918153`, 
                `advance n/Jane Doe p/98762345`

>Original List:
```
Here are the list of all applicants:
1. John Doe (Applied) 91918153 
2. Jane Doe (Shortlisted) 98762345
3. Joe Doe (Accepted) 91234321
```
Example Output:
```
Successfully advanced John Doe
Successfully advanced Jane Done

Here are the list of all applicants:
1. John Doe (Shortlisted) 91918153 
2. Jane Doe (Accepted) 98762345
```

- Implemented `reject` feature to reject applicants' status from `Applied`, `Shorlisted` and `Accpted` to `Rejected`
  to be tracked in HMHero

Example Input: `reject n/John Doe p/91918153`, 
                `reject n/Jane Doe p/98762345`,
                `reject n/Joe Doe p/91234321`

>Original List:
```
Here are the list of all applicants:
1. John Doe (Applied) 91918153 
2. Jane Doe (Shortlisted) 98762345
3. Joe Doe (Accepted) 91234321
```
Example Output:
```
Successfully rejected John Doe
Successfully rejected Jane Doe
Successfully rejected Joe Doe

Here are the list of all applicants:
1. John Doe (Rejected) 91918153 
2. Jane Doe (Rejected) 98762345
3. Joe Doe (Rejected) 91234321
```

(**This part can change according to your contributions**)

Example

- to be added soon

(**Code contributions**)

Example

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=bofeng1999&breakdown=true)

- **Project management**:

  - Helped teammates on debugging to ensure that everyone is on task.

(**Enhancements implemented**)

- **Enhancements to existing features**:

- Enhanced the `FindCommand` to check the `equals` method to return true when `namePredicate`, `phonePredicate`,
 `nameAndPhoneContainsKeywordsPredicate` returns true if both objects are null to prevent NullPointerException

- Created test cases for AdvanceCommand and AdvanceCommandParser

- **Documentation**:

  - to be added soon

- **Community**:

  - to be added soon

- **Tools**:

  - to be added soon

- _{you can add/remove categories in the list above}_
