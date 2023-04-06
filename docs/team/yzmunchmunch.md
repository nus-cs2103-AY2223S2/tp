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

* **Code contributed**: [RepoSense link]()


* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
  * Integrated a third party library (Natty) to the project ([\#42]())
  * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
