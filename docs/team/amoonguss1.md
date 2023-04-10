---
layout: page
title: amoonguss1's Project Portfolio Page
---

## Project: TechTrack

{{ site.data.techtrack.about.summary }}

I worked on the backend mainly and documentation. I was mainly in charge of implementation and testing.

## Contributions
* **New Feature**: Added the `salary` parameter for `add` command.
  * What it does: User can input `salary` for the `roles`.
  * Justification: The addition of `salary` is important for any applicant looking to apply for internships/jobs,    
    providing them with the information they need to make more informed
    decisions about their job search.
  * Highlights: TechTrack users can now add salaries of respective roles. `salary` must be
    integers/float values and their values must not start with a 0.


* **New Feature**: Add a new `company` command to allow filtering of the `company` field.
  * What it does: Allows users to filter companies by the keyword specified. e.g. `company Google`
  * Justification: Filtering by company names is important as it speeds up the process of
    finding relevant companies that the user is interested in applying for.
  * Highlights: Makes it more friendly for the user to search through different roles.
    The keyword entered is not case-sensitive.


* **Implementation**: Add a new `CompanyContainsKeywordPredicate` class to check for keywords in the `company` field.
  * What it does: Allow developers to check for equivalence of keywords and testing.
  * Justification: Follows the original Name command format of AB3 where the Name of the role is filtered
    by `NameContainsKeywordPredicate`. 
  * Highlights: Allows for a separate, unique predicate checking on keywords parsed to the `Company` command.


* **Enhancements to existing features**: 
  * Refactored all instances of `Person` to `role` in all the files.
    * What it does: Renamed all methods, classes and variables that contained `person`
      to `role`.
    * Justification: Roles fits towards TechTrack instead of person in AB3 and would be clearer for future developments.
    * Highlights: Refactored AB3 code and tests.

* **Documentation:**
  * User Guide:
    * Added the `$/` parameter for the `salary` field in the `add` command
    * Wrote and provided the UI screenshots of `company` command.
    * Proof-read and edited mistakes in the UG.
  * Developer Guide:
    * Wrote the implementation and alternatives of `CompanyCommand`.
    * Wrote the implementation of `DeleteCommand` and its dependencies.
    * Constructed the sequence diagram of `CompanyCommand`.
    * Added all Use cases for TechTrack v2 commands.
    * Changed all uml diagrams to use `role` instead of `address`.
    * Updated the class diagrams to use the current TechTrack role fields.

* **Contributions to team-based tasks**:
  * Created several tags in team repository to help manage the work done by the team.
  * Managed and reviewed most of the Pull Requests done by the team.
  * Proof-read documentation to rectify any mistakes done by team members.
  * Facilitated team meetings by planning out what we needed to do for the week.
  * Bug Fixes
    * Fixed UG bug, where command summary was not explained clearly.
    * Fixed multiple UG bugs where grammatical errors were found.
    * Fixed DG bug, where uml diagrams would display wrongly after refactoring the code base.

* **Review/mentoring contributions**:
  Offered useful and feedback reviewing PRs. Examples: [1](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/292/), [2](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/254/), [3](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/254/)

## Links to Contributions

[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=amoonguss1&breakdown=true)
[Commit History](https://github.com/AY2223S2-CS2103-W16-2/tp/commits?author=amoonguss1)
[Pull Requests](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+author%3Aamoonguss1)
[Issues](https://github.com/AY2223S2-CS2103-W16-2/tp/issues?q=is%3Aissue+assignee%3Aamoonguss1)
