---
layout: page
title: Ong Chong Yang's Project Portfolio Page
---

### Project: Clock-Work

Clock-Work is a desktop application for managing tasks and assignments, optimised for use via a Command Line Interface
(CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Clock-Work can get your
assignment management tasks done faster than traditional GUI applications.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=lywich)

* **Project management**:
    * Refactored original AB3 to fit our project needs.
      * Removed `address`, `email`, `phone`.
      * Removed images from AB3 that is not reused.
      * Refactored testcases to be valid according to our project needs.
      * Created `Date` class.
        * `Date` is a field for `Deadline` and `Event` objects.
        * `Date` abstracts the "date" function away from `Task` to encourage decoupling for maintainability.
      * Created `Description` class.
        * `Description` is a field for `Task` objects.
        * `Description` abstracts the "description" of a task away from `Task` to encourage decoupling for maintainability.

* **Enhancements to existing features**:
    * `sort` command enhancement.
      * `sort` is meant to organise the task list for the users to have a clearer image of the tasks at hand. 
      * This is such that a user can see all `Event`, `Deadline`, `SimpleTask` objects adjacent to each other rather than scattered according to input sequence.
    * Color code UI enhancement
      * AB3 has all its tags of the same color background, which impacts the users ability to sieve out `Task` with the same tags.
      * From a 20 predefined colors, a color is assigned to a tag based on the tag's name.
        * This ensures tags with the same name will have the same color.
      * Some other alternatives thought up during the process: 
        * A `Task`'s `R`, `G`, `B` values calculated independent of each other.
          * This gives us 256^3 colors to choose from, but this does not guarantee visually appealing combinations.
        * Randomized `R`, `G`, `B` values calculated on new tag and store it somewhere.
          * This suffers from the same problem of having combinations that are visually unappealing.

* **Documentation**:
    * User Guide:
        * Added table of contents and indexing.
        * Write-up for `sort` command.
        * Write-up for color codes.
        * Add new image for `help` window.
        * Fixed redirection links for sections.
      
    * Developer Guide:
        * Added table of contents and indexing.
        * Write-up for `sort` command.
        * Write-up for color codes.
        * Write-up for `clear` command (including sequence and activity diagram). 
        * Write-up for `list` command (including sequence and activity diagram).
        * Write-up for `help` command (including sequence and activity diagram).
        * Fixed redirection links for sections.
        * Edited existing diagrams to refer to our project(`Task`) rather than AB3(`Address`).

* **Community**:
    * Released v1.3.trial jar.
    * Released v1.3 jar.
    * Enabled assertions in team repo.
    * Reviewed 20/71 Pull Requests.
    * Gave suggestion comments on reviews as needed. 

