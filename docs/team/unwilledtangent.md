---
layout: page
title: unwilledtangent's Project Portfolio Page
---

## Project: TechTrack

{{ site.data.techtrack.about.summary }}

I worked on implementing features, such as adding the required experience, as well as filtering by tags

## Table of Contents

* [Contributions](#contributions)
* [Enhancements](#enhancements)
* [Bug Fixes](#bug-fixes)
* [Links to Contributions](#links-to-contributions)

## Contributions

* **New Feature**: Added the `experience` parameter for `add` command.
    * What it does: User can input `experience` for the `roles`.
    * Justification: The addition of `experience` is important for any applicant looking to apply for internships/jobs,    
      providing them with the information they need to make more informed
      decisions about their job search.
    * Highlights: TechTrack users can now add their experiences of respective roles. `experience` must not be an empty string.


* **New Feature**: Add a new `tag` command to allow filtering of the `tag` field.
    * What it does: Allows users to filter companies by the keyword specified. e.g. `tag Tech`
    * Justification: Filtering by company names is important as it speeds up the process of
      finding relevant companies that the user is interested in applying for.
    * Highlights: Makes it more friendly for the user to search through different roles.
      The keyword entered is not case-sensitive.


* **Implementation**: Add a new `TagContainsKeywordPredicate` class to check for keywords in the `tag` field.
    * What it does: Allow developers to check for equivalence of keywords and testing.
    * Justification: Follows the original Tag command format of AB3 where the Tag of the role is filtered
      by `TagContainsKeywordPredicate`.
    * Highlights: Allows for a separate, unique predicate checking on keywords parsed to the `Tag` command.


* **Testing**: Tests new implementation and class created.
    * What it does: Allow the code to be covered by more tests and making it more robust.
    * Justification: New classes such as `TagCommandParser`, `Experience` and `TagCommand` have test cases in their
      respective test classes. These tests are written to ensure the reliability of these classes.
    * Highlights: Better code coverage and robustness of TechTrack.

## Enhancements

* Implementation of `Experience` in TechTrack.
* Implementation of `TagCommand` where users can filter for various companies.
* ...

## Bug Fixes
* Fixed UG bugs. (missing commands in command summary)
* ...

* **Documentation:**
    * User Guide:
        * Added the `x/` parameter for the `experience` field in the `add` command
        * Wrote and provided the screenshots of `tag` command.
    * Developer Guide (TO BE ADDED):
        * Wrote the implementation of TagCommand.
        * Wrote the alternatives of TagCommand.
        * Constructed the sequence diagram of TagCommand.
        * Added Use cases for TechTrack v2 commands.

## Links to Contributions

[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=unwilledtangent&breakdown=true)
[Commit History](https://github.com/AY2223S2-CS2103-W16-2/tp/commits?author=unwilledtangent)
[Pull Requests](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+author%3Aunwilledtangent)
[Issues](https://github.com/AY2223S2-CS2103-W16-2/tp/issues?q=is%3Aissue+assignee%3Aunwilledtangent)
