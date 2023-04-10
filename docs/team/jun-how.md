---
layout: page
title: Jun-How's Project Portfolio Page
---

### Project: MODTrek

MODTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI).
The app is provides a convenient platform for students to easily access and update their modules within presses of a keyboard.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jun-how&breakdown=true)

* **Enhancements implemented**:
  * **New feature**: Module filtering via Find Command (See PRs [#91](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/91), [#108](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/108))
    * What it does: Find Command not only allows users to find a specific module, but it also allows users to filter the list of modules by module code prefix, credits, sem-year, grade and tag. Multiple filters are also allowed.
    * Justification: This feature offers added convenience by allowing users to filter and navigate between modules more easily to search for certain modules satisfying a condition.
    * Highlights: Implemented the filter function by enhancing the `FindCommandParser`, `FindCommand` classes and modifying the `ModuleCodePredicate` which forms the core of the function.

  * **Enhancement to existing feature**: Help Command (See PRs [#68](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/68), [#192](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/192))
    * What it does: Help Command acts as a tool that reminds users of command formats whenever they forget and require aid.
    * Justification: The old help command merely links users to the software's user guide page, which does not add much value. With this enhancement, users could simply call help for the relevant command that they require more information about, which offers an added convenience.
    * Highlights: Enhanced the `HelpCommandParser` and `HelpCommand` classes to apply help function for every command, including a general one that showcases a list of help commands.

  * **Enhancement to existing feature**: Delete Command (See PR [#48](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/48))
    * What it does: Allow the delete command to delete all modules possible that are found in the list. Offered 3 cases to be considered:
      1) If the delete command only provides modules found in the list, all the modules are deleted successfully.
      2) If the delete command only provides modules not found in the list, an exception is thrown.
      3) If the delete command provides both module(s) found and not found in the list, the modules found are deleted and the ones not found are simply ignored.
    * Justification: Previously, if the delete command provides both module(s) found and not found in the list, an exception is thrown. This allows for more flexibility when trying to delete modules.
    * Highlights: Modified the `DeleteCommand` class to accommodate this enhancement.

  * **Miscellaneous**: Integration of logic into UI components (See PR [#55](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/55))
    * What it does: Render the command result message and graphics accurately in the GUI.
    * Highlights: Implemented the basic functionality of the controllers, made the CLI section dynamically accept user commands and made the Graphics section display all the relevant information by calling relevant methods in `Logic` and `Model`.

  * **Miscellaneous**: Prettifying and standardising of the format of output messages
    * What it does: The format of correct messages and errors are standardised to make it easier for users to read and understand where they went wrong.
    * Justification: Discussed with cjyothika for viable solutions in handling edge cases and their respective error messages to be output, with potential tradeoffs.
    * Highlights: Handled edge cases for error messages as extensively as possible to hint to users where the mistake in their input command lies.
    * See PRs [#106](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/106), [#205](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/205)

  * **Miscellaneous**: Wrote and updated extensive test cases to align with new features in MODTrek.

* **Documentation**:
  * User Guide:
    * Included new Help command and its specification, enhanced user stories relevant to the commands, added new section on short tutorial, improved on general language and tone, standardised "You" language (See PR [#193](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/193)).
  * Developer Guide: 
    * Wrote the implementation of the Find module(s) feature and designed the sequence and activity diagrams (See PR [#97](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/97)).
    * Contributed to some parts of the Appendix (e.g. user stories, use cases).

* **Contributions to team-based tasks**:
  * Set up the Team Repo on GitHub (Issue tracker, GitHub Actions, project website etc.), worked with all teammates to liaise and integrate all the features seamlessly.

* **Review/mentoring contributions**: 
  * Reviewed PRs (Examples: [#45](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/45), [#165](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/165), [#166](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/166))
  * Offered suggestions to improve on the quality of the UG (Example: [#88](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/88))

* **Contributions beyond the project team**:
  * Contributed to [PE-D bug catching](https://github.com/jun-how/ped/issues)
