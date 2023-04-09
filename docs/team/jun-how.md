---
layout: page
title: Jun-How's Project Portfolio Page
---

### Project: MODTrek

ModTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI).
The app is provides a convenient platform for students to easily access and update their modules within presses of a keyboard.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jun-how&breakdown=true)

* **Enhancements implemented**:
  * **New feature**: Module filtering via Find Command
    * What it does: Find Command not only allows users to find a specific module, but it also allows users to filter the list of modules by module code prefix, credits, sem-year, grade and tag. Multiple filters are also allowed.
    * Justification: This feature offers added convenience by allowing users to filter and navigate between modules more easily to search for certain modules satisfying a condition.
    * Highlights: Implemented the filter function by enhancing the FindCommandParser, FindCommand classes and modifying the Predicate which forms the core of the function.

  * **Enhancement to existing feature**: Help Command
  * What it does: Help Command acts as a tool that reminds users of command formats whenever they forget and require aid.
  * Justification: The old help command merely links users to the software's user guide page, which does not help much. With this enhancement, users could simply call help for the relevant command that they require more information about, which offers an added convenience.
  * Highlights: Enhanced the HelpCommandParser and HelpCommand classes to apply help function for every command, including a general one.

  * **Enhancement to existing feature**: Delete Command
  * What it does: Allow the delete command to delete all modules possible that are found in the list. Offered 3 cases to be considered:
  1) If the delete command only provides modules found in the list, all the modules are deleted successfully.
  2) If the delete command only provides modules not found in the list, an exception is thrown.
  3) If the delete command provides both module(s) found and not found in the list, the modules found are deleted and the ones not found are simply ignored.
  * Justification: Previously, if the delete command provides both module(s) found and not found in the list, an exception is thrown. This allows for more flexibility when trying to delete modules.
  * Highlights: Modified the DeleteCommand class to accommodate this enhancement.

  * **Miscellaneous**: Integration of logic into UI components
  * What it does: Render the command result message and graphics accurately in the GUI.
  * Highlights: Implemented the basic functionality of the controllers, dynamically accept user commands in the CLI section and display all the relevant information in the Graphics section by calling relevant methods in Logic and Model.

  * **Miscellaneous**: Prettify and standardise the format of output messages
  * What it does: The format of correct messages and errors are standardised to make it easier for users to read.
  * Highlights: Handled edge cases for error messages to hint to users where the mistake in their input command lies.

  * **Miscellaneous**: Wrote and updated extensive test cases to align with new features in ModTrek.

  * **Miscellaneous**: Ensured adherence to coding standards and code quality.

* **Project management**: Set up the Team Repo on GitHub (Issue tracker, GitHub Actions, project website etc.)

* **Documentation**:
  * User Guide: 
    * Offered suggestions to improve on the quality of the UG. (Example: [1](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/88))
  * Developer Guide: 
    * Wrote the implementation of the Find module(s) feature and designed sequence and activity diagrams. Contributed to some parts of the Appendix.

* **Community**: 
  * Reviewed PRs of team members (Examples: [1](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/45), [2](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/165))

* **Tools**: 
  * Java (for code)
  * PlantUML (for diagrams)
