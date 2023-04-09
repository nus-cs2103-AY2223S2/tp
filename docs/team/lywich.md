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
    * Refactor original AB3 to fit our project needs.
      * Remove `address`, `email`, `phone`. [#38](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/38)
      * Refactor testcases to be valid according to our project needs. [#38](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/38)
      * Create `Description` class. [#38](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/38)
        * `Description` is a field for `Task` objects.
        * `Description` abstracts the "description" of a task away from `Task` to encourage decoupling for maintainability.
      * Create `Date` class. [#50](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/50)
          * `Date` is a field for `Deadline` and `Event` objects.
          * `Date` abstracts the "date" function away from `Task` to encourage decoupling for maintainability.
      * Remove images from AB3 that are not reused. [#149](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/149)

* **Enhancements to existing features**:
    * `sort` command enhancement. [#61](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/61)
      * `sort` is meant to organise the task list for the users to have a clearer image of the tasks at hand.
      * This is such that a user can see all `Event`, `Deadline`, `SimpleTask` objects adjacent to each other rather than scattered according to input sequence.
    * Color code UI enhancement. [#74](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/74)
      * AB3 has all its tags of the same color background, which impacts the users ability to sieve out different `Task` with the same tags.
      * From a 20 predefined colors, a color is assigned to a tag based on the tag's name.
        * This ensures tags with the same name will have the same color.
      * Some other alternatives thought up during the process:
        * A `Task`'s `R`, `G`, `B` values calculated independent of each other.
          * This gives us 256^3 colors to choose from, but this does not guarantee visually appealing combinations.
        * Randomized `R`, `G`, `B` values calculated on new tag and store it somewhere.
          * This suffers from the same problem of having combinations that are visually unappealing.

* **Documentation**:
    * User Guide:
        * Add table of contents and indexing. [#116](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/116)
        * Write-up for `sort` command. [#64](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/64)
        * Write-up for color codes. [#144](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/144)
        * Add new image for `help` window. [#149](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/149)

    * Developer Guide:
        * Add table of contents and indexing. [#94](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/94)
        * Write-up for `sort` command. [#94](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/94)
        * Write-up for color codes. [#144](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/144)
        * Write-up for `clear` command (including sequence and activity diagram). [#144](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/144)
        * Write-up for `list` command (including sequence and activity diagram). [#144](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/144)
        * Write-up for `help` command (including sequence and activity diagram). [#144](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/144)
        * Edit existing diagrams to refer to our project(`Task`) rather than AB3(`Address`). [#149](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/149)
        * MSS for `edit` command. [#140](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/140/files)

* **Community**:
    * Released v1.3.trial jar.
    * Released v1.3 jar.
    * Enabled assertions in team repo. [#114](https://github.com/AY2223S2-CS2103T-W13-3/tp/pull/114/files)
    * Reviewed 20/71 Pull Requests.
    * Gave suggestion comments on reviews as needed.

