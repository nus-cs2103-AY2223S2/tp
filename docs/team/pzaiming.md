---
layout: page
title: pzaiming's Project Portfolio Page
---

## Project: TechTrack

{{ site.data.techtrack.about.summary }}

I worked on the backend and documentation. I was mainly in charge of implementation and testing of the new features.

## Table of Contents

* [Contributions](#contributions)
* [Enhancements](#enhancements)
* [Bug Fixes](#bug-fixes)
* [Links to Contributions](#links-to-contributions)

## Contributions

* **New Feature**: Added the deadline parameter for `add` command.
    * What it does: User can input deadline for the roles.
    * Justification: The addition of deadline is important to allow the user to have enough time to prepare for its
      internship.
    * Highlights: TechTrack users can now add their deadline. The restriction of deadline must be in the format '
      YYYY-MM-DD'.


* **New Feature**: Add a new `salary` command to allow the sorting of roles based on salary.
    * What it does: Allow user to see which roles are the most attractive or least attractive depending on the salary.
    * Justification: This is an important feature from the user point of view as salary is tied closely to the
      importance of different roles.
    * Highlights: Makes it more friendly for the user to search through different roles.


* **Implementation**: Add a new `OrderParser` class to allow order in asc/desc of sorting commands.
    * What it does: Allow developers to easy parse the sorting requirement for their sorting commands.
    * Justification: The order needs to be either ascending or descending and makes it more OOP by creating a Parser
      class.
    * Highlights: OOP design to allow more sorting features to develop in the future by accessing this class.


* **Refactoring**: Refactored the `address` package to the `roles` package.
    * What it does: The package is changed accordingly to the needs of the application.
    * Justification: Roles fits towards TechTrack instead of address and would be clearer for future developments.
    * Highlights: Refactored AB3 code and tests.


* **Testing**: Tests new implementation and class created.
    * What it does: Allow the code to be covered by more tests and making it more robust.
    * Justification: New classes such as `OrderParser`, `Deadline` and `DeadlineCommand` needs to be added to allow the
      sorting of salary and deadline. Tests need to be written to ensure the accuracy of it by writing assertion.
    * Highlights: Better code coverage and robustness of TechTrack.

## Enhancements

* Implementation of sorting especially for salary.
* Allow the user to enter the deadline for adding of roles.
* Enhanced the salary restriction to make it more intuitive for the user.
* Add welcome message on startup to let the user know the avaliable commands.

## Bug Fixes

* Fixed a bug where the left padding of salary is allowed, E.g.: $00001.
* Fixed a bug where zero salary is allowed.
* Fixed a bug where the sorting after searching commands such as tag, name deletes the original list in storage.

* **Documentation:**
    * User Guide:
        * Wrote to specify that add comamnd will take the latter if mutliple similar parameters is given.
        * Wrote and provided the screenshots of salary asc/desc command.
    * Developer Guide (TO BE ADDED):
        * Wrote the implementation of SalaryCommand.
        * Wrote the alternatives of SalaryCommand.
        * Constructed the sequence diagram of SalaryCommand.

## Links to Contributions

[RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=pzaiming&breakdown=true)
[Commit History](https://github.com/AY2223S2-CS2103-W16-2/tp/commits?author=pzaiming)
[Pull Requests](https://github.com/AY2223S2-CS2103-W16-2/tp/pulls?q=is%3Apr+author%3Apzaiming)
[Issues](https://github.com/AY2223S2-CS2103-W16-2/tp/issues?q=is%3Aissue+author%3Apzaiming)
