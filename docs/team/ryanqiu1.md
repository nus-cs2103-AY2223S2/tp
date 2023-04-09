---
layout: page
title: ryanqiu1's Project Portfolio Page
---

## Project: TechTrack

{{ site.data.techtrack.about.summary }}

For me i worked on the backend component and fixing bugs. In addition, i worked on the various documentations, UG, DG
and "About Us" page.

## Contributions
* **New Feature**: Add field `Website` to `add` command
    * What it does: Allows the company website to be added by the user.
    * Justification: Many Companies have their own website and the user might want to store the website somewhere.
    * Highlights: 
      * Company of Google (www.google.com) `w/www.google.com`
      * It was challenging at the start to find how the ab3 program works, as i needed to figure out which files to refactor and change.
      
* **New Feature**: Sort roles based on deadline 
    * What it does: Allows the user to sort the deadline of role application by ascending or descending order.
    * Justification: Users might forget the deadline for the application of the job, hence can sort by the deadline.
    * Highlights: 
      * Using the command `deadline asc`, shows the nearest application that the user should apply.
      * Role can be easily sorted for the user's convenience as applying jobs is a time-sensitive task.


* **Enhancements to existing features**: 
    * Refactored the `Address` classes and methods to the `Role`, e.g. AddressBook to RoleBook .
      * What it does: The package is changed accordingly to the needs of the application.
      * Justification: Roles fits towards TechTrack instead of address and would be clearer for future developments.
      * Highlights: Refactored AB3 code and tests.
    * Set Deadline to only allow current date and after current date.
    * Set Website field to be www. + any amount of characters + .com.


* **Documentation**:
    * User Guide:
        * Added my own commands and set up table of commands.
        * `w/www.google` field and `deadline asc` command.
        * Added UI Images to each commands.
    * Developer Guide:
        * Added Sequence diagram and Commit Activity Diagram for `Deadline` Command.
        * Added Sequence diagram, description  for `add` and `edit` Command.
        * Added documentation for `add` and `edit` manual testing appendix.
        * Added NFR section of requirement details.
        * Adjusted and ensure proper formatting of guide.


* **Contributions to team-based tasks**:
    * Created priority and type tags in team repository.
    * Set Goals and Deadlines for teammates.
    * Managed some releases on GitHub
    * Created and Managed Milestones for 1.2,1.3.
    
    * Bug Fixes
        * Fixed deadline that does not allow for current date.
        * Fixed UG bugs, where commands is not stated clearly.
        * Fixed bug where error message is not shown.
        * Fixed sorting bug, where original list is deleted after filtered.
        * Fixed Bugs in the UI (Wrong display format)
        * Fixed Bugs in the EditCommand (Unable to edit website, fixed through the parser)


* **Review/mentoring contributions**:
  Offered useful and feedback with issues and reviewing PRs. Examples: [1](https://github.com/AY2223S2-CS2103-W16-2/tp/issues/145), [2](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/52/), [3](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/142)

## Links to Contributions
[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=ryanqiu1&breakdown=true)
[Commit History](https://github.com/AY2223S2-CS2103-W16-2/tp/commits?author=ryanqiu1)
[Pull Requests](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+author%3Aryanqiu1)
[Issues](https://github.com/AY2223S2-CS2103-W16-2/tp/issues?q=is%3Aissue+assignee%3Aryanqiu1)
