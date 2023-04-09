---
layout: page
title: Javon Teo's Project Portfolio Page
---

### Project: LoyaltyLift

LoyaltyLift is an application for small business owners to manage customers and their orders to boost customer satisfaction and retention rate. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to bookmark customers with the `markc` command.
  * What it does: Allows the user to bookmark any customer of the user's choice.
  * Justification: This feature enabled business owners to bookmark specific customers, if they required special attention on the customer.
  * Highlights: This enhancement affected the Customer class as there was a need to include an attribute to record whether a customer was bookmarked. The implementation too was challenging as it required changes to the existing GUI of the application.

* **New Feature**: Added the ability to un-bookmark customers with the `unmarkc` command.
  * What it does: A inverse of the `markc` command, it allows the user to un-bookmark any customer of the user's choice.
  * Justification: Business owners can un-bookmark already bookmarked customers, if they no longer required special attention.
  * Highlights: This implementation required changes to the existing GUI of the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=JavonTeo&breakdown=true)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
  * Added functionality to `listc` command to allow for filtering of bookmarked customer.
  * Revised the code to correct errors that prevented the application from functioning properly (Pull requests [#137](https://github.com/AY2223S2-CS2103T-T09-3/tp/issues/137), [#149](https://github.com/AY2223S2-CS2103T-T09-3/tp/issues/149), [#150](https://github.com/AY2223S2-CS2103T-T09-3/tp/issues/150))
  * Wrote additional tests for existing features (Pull request [#87](https://github.com/AY2223S2-CS2103T-T09-3/tp/issues/87))

* **Documentation**:
    * User Guide:
        * Added `markc` and `unmarkc` command description and format under "Features"
    * Developer Guide:
        * Added non-functional requirements and glossary
        * Added Target user profile and Value proposition under "Product Scope" 

* **Community**:
    * Several PRs reviewed
    * Reported bugs and suggestions for other teams taking the module ([Issues reported](https://github.com/JavonTeo/ped/issues))
    * Contributed to forum discussions (Issue [#171](https://github.com/nus-cs2103-AY2223S2/forum/issues/171))
