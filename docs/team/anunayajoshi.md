---
layout: page
title: Anun's Project Portfolio Page
---

### Project: Advis.io

Advis.io (AIO) is a desktop app for managing clients information, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added the ability to add policies to a client.
  * What it does: Given a Policy Class, the user can add a policy to a client, which contains a UniquePolicyList.
  * Justification: This feature improves the product significantly because a user can now add policies to a client,
    to get a better idea of the client's financial situation.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an
    in-depth analysis of adding a different type of list to the client class. The other editPolicy and
    deletePolicy commands relied on the similar implementation.
  *

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=anunayajoshi&breakdown=true)


* **Enhancements to existing features**:
  * Refactored the 'Person' class to 'Client' class, which was a very tedious and large task. [\#59](https://github.
    com/AY2223S2-CS2103T-T09-4/tp/pull/59)
  * Wrote additional test cases for policy commands and other methods to increase coverage [\#71](https://github.
    com/AY2223S2-CS2103T-T09-4/tp/pull/71)
  * Added a enum functionality to the Policy Name class.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addPolicy`
    * Refactored referring to a person, and now instead to a client
  * Developer Guide:
    * Added implementation details of the `addPolicy` feature.

* **Community**:
* PRs reviewed (with non-trivial review comments): [\#61](https://github.com/AY2223S2-CS2103T-T09-4/tp/pull/61),
  [\#63](https://github.com/AY2223S2-CS2103T-T09-4/tp/pull/63),
