---
layout: page
title: pzaiming's Project Portfolio Page
---

## Project: TechTrack

{{ site.data.techtrack.about.summary }}

I worked on the backend and documentation. I was mainly in charge of implementation and testing of the new features.

## **Contributions**

* **New Feature**: Add `deadline` parameter for `add` command.
  * What it does: User can input deadline for the roles.
  * Justification: The addition of deadline is important to allow the user to have enough time to prepare for the
    role.
  * Highlights: TechTrack users can now add their deadline. The restriction of deadline must be in the format 
    `YYYY-MM-DD`.

* **New Feature**: Add new `salary` command to allow the sorting of roles based on salary.
  * What it does: Allow user to see which roles are the most attractive or least attractive depending on the salary.
  * Justification: This is an important feature from the user point of view as salary is tied closely to the
    importance of different roles.
  * Highlights: Makes it more friendly for the user to search through different roles.


* **Implementation**: Add a new `OrderParser` class to allow order in asc/desc of sorting commands.
  * What it does: Allow developers to easily parse the `ORDER` for their sorting commands.
  * Justification: The order needs to be either ascending or descending and makes it more OOP by creating a Parser
    class.
  * Highlights: OOP design to allow more sorting features to develop in the future by accessing this class.


* **Testing**: Tests new implementation and class created.
  * What it does: Allow the code to be covered by more tests and making it more robust.
  * Justification: New classes such as `OrderParser`, `Deadline` and `DeadlineCommand` needs to be added to allow the
    sorting of salary and deadline. Tests need to be written to ensure the accuracy of it by writing assertion.
  * Highlights: Better code coverage and robustness of TechTrack.


* **Enhancements to existing features**:
  * Refactoring: Refactored the `address` package to the `techtrack` package [#169](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/169)
    * What it does: The package is changed accordingly to the needs of the application
    * Justification: `techtrack` instead of `address` as it will be clearer for future developments
    * Highlights: Refactored AB3 code and tests
  * Enhanced the salary restriction to make it more intuitive for the user [#255](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/255)
  * Add welcome message on startup to let the user know the available commands [#118](https://github.com/AY2223S2-CS2103-W16-2/tp/pull/118)


* **Documentation**:
  * User Guide:
    * Added and provided the UI screenshots of `salary asc/desc` command.
    * Added restrictions for the role table.
  * Developer Guide:
    * Added implementation, alternatives and limitations for `salary` and `deadline` sort.
    * Constructed the sequence diagram of sorting for `salary` and `deadline`.
    * Added documentation for `salary`, `deadline` and `view` for manual testing.
    * Added the first 7 of NFR section of requirement details.
    * Adjusted and ensure proper formatting of guide.


* **Contributions to Team-based Tasks**:
  * Created priority and type tags in team repository.
  * Reviewed Pull Requests done by the team.
  * Bug Fixes:
    * Fixed DG bug for the sequence diagram of sorting, where the `SalaryCommand` and `DeadlineCommand` object is 
      created before `OrderParser`.
    * Fixed UG bug to specify that add command will take the latter details if multiple similar parameters is given.
    * Fixed salary bug where salary of zero is allowed.
    * Fixed sorting bug, where sorting after filter commands such as tag, name deletes the original list in storage.
  * Offered useful and feedback with issues and reviewing PRs
    (examples: [1](https://github.com/AY2223S2-CS2103-W16-2/tp/issues/138),
               [2](https://github.com/AY2223S2-CS2103-W16-2/tp/issues/149),
               [3](https://github.com/AY2223S2-CS2103-W16-2/tp/issues/242) )


* **Community**:
  * Reported bugs and suggestions for other team
    (examples: [1](https://github.com/AY2223S2-CS2103T-W14-4/tp/issues/135),
               [2](https://github.com/AY2223S2-CS2103T-W14-4/tp/issues/142), 
               [3](https://github.com/AY2223S2-CS2103T-W14-4/tp/issues/156) )

## Links to Contributions

[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=pzaiming&breakdown=true)
[Commit History](https://github.com/AY2223S2-CS2103-W16-2/tp/commits?author=pzaiming)
[Pull Requests](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+author%3Apzaiming)
[Issues](https://github.com/AY2223S2-CS2103-W16-2/tp/issues?q=is%3Aissue+assignee%3Apzaiming)
