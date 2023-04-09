---
layout: page
title: Ang Yuze's Project Portfolio Page
---

### Project: Adviso.io

Advis.io (AIO) is a all-in-one solution for financial advisors with problems managing their clientele. AIO is currently available on PC, and is optimized for use via a Command Line Interface (CLI) while still having the benefits of having a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added `policy` class as an additional parameter to `client` class
  * `Policy` : Represents the particular policy with respect to the client. Takes in the following few classes as parameters. (`PolicyName`, `CustomDate`, `Premium`, `Frequency`)
  * `PolicyName` : Represents the name of the policy
  * `CustomDate` : A class that represents a date in the format of dd.mm.yyyy.
  * `Premium` : Represents the amount paid by the client for the policy.
  * `Frequency` : Represents the frequency of payment by the client for the policy
  * `UniquePolicyList` : Stores a list of policies for the particular client, similar to `UniqueClientList`


* **Enhancements to existing features**:
  * Enhanced the `isSameClient` method in the `Client` class, such that it now checks if all client's `Name` AND (`Phone` OR `Email`) of both comparing clients are the same, instead of the original method where it only checks if the client names are the same


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=yzmunchmunch&breakdown=true)
* **Documentation**:
  * User Guide:
    * Edited documentation for the all the features in General Management and Client Management.
    * Added screenshots to General Management and Client Management sections.
  * Developer Guide:
    * Added flow of implementation for model, `Client` and `Policy` classes with UML diagrams


