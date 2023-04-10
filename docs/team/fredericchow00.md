---
layout: page
title: <Frederic Chow> Project Portfolio Page
---

### Project: HMHero

**Overview**

HMHero helps Hiring Managers track the statuses of candidates' applications and streamline the advancing and rejecting
of applicants, all in a single app that is easy to use.


<br>

**Summary of Contributions**

**Code contributions**

- **Code contributed**: 
[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=fredericchow00&breakdown=true)

- **Project management**:

  - Liaised with teammates on deadlines during meetings to ensure that everyone is on task.

**Enhancements implemented**

- **New Features implemented**

1. Implemented `list` feature that lists out all applicants across all statuses,
   with a statistic that shows the number of applicants in each status

2. Implemented `ApplicationDateTime` class that is an additional field under Person,
   to track the date of application for usage in other commands.

3. Enhanced GUI to make application look more visually appealing.

- **Enhancements to existing features implemented by other team members**:

1. Enhanced `AdvanceCommand` and `AdvanceCommandParser` to detect when there should or should not be
   a need for the user to provide an interview date for the applicant when the user calls the `Advance` feature

2. Enhanced `AdvanceCommand` to detect for duplicate interview date and time when
advancing an applicant from `APPLIED` to `SHORTLISTED`.

3. Fixed GUI bugs to accommodate and anticipate for unexpected user behaviours.

- **Test Cases**:

1. Wrote test cases for RejectCommand

    - Test cases that covers possible paths taken by `execute(Model model)` and
   `equals()` in `RejectCommand` class.

2. Wrote test cases for RejectCommandParser

    - Test cases that covers possible paths taken by `parse(String args)`
   in `RejectCommandParser` class.


- **Documentation**:

  - User Guide
    - Added documentation for the features `list`
    - Added documentation for About and How to Use the User Guide.
    - Edited User Guide to look more readable.

  - Developer Guide
    - Added documentation for Product Scope and User Stories
    - Added documentation for Annex such as User Stories and Manual Testing.
    - Reviewed diagrams and content in the Command section
    - Added Efforts section and Planned Enhancements for feature flaws found during PE-D after v1.3.

- **Community**:

  - Reviewed Pull Requests (PRs) made by team members
  - Ensure timely closure of milestones and issues
  - Set deadlines before the hard deadline so that the team does not fall behind.


