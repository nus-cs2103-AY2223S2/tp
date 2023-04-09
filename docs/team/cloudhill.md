---
layout: page
title: Ryo Hilmawan's Project Portfolio Page
---

## Project: LoyaltyLift

LoyaltyLift is a desktop application for small business owners to manage customers and their orders to boost customer satisfaction and retention rate. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Model**: Created the model, UI, and initial test cases for **Orders** ([\#30](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/30), [\#36](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/36), [\#44](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/44))

* **New Feature**: Setting and appending notes to customers and orders with the `setnotec`,  `appendnotec`, `setnoteo`, and `appendnoteo` commands ([\#59](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/59))
  * What it does: Allows the user to set a note for each customer and order. The user can either overwrite the customer or order's existing note using the set commands, or append to an existing note using the append commands. 
  * Justification: Notes allow the user to keep any extra information they may need for each customer or order. As a business owner, there may be information related to a specific customer or order that the user wants to keep track of.
  * Highlights: Involves adding a new note property associated with customers and orders, and UI changes to the customer and order view

* **New Feature**: Finding orders by name with the `findo` command ([\#69](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/69))
  * What it does: A counterpart to `findc`, the `findo` command allows the user to find orders by name. 
  * Justification: With a large number of orders in a list, it can be difficult to locate a specific order. Thus, the ability to search for orders by name would be useful.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cloudhill&breakdown=true)

* **Project management**:
  * Managed releases v1.1 - v1.4 (4 releases) on GitHub

* **Enhancements to existing features**:
  * Added sorting options to `listc` to sort customers by name and points [\#77](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/77)
  * Added filtering options to `listc` to filter customers by customer type [\#97](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/97)
  * Added sorting options to `listo` to sort orders by created date, name, and status [\#81](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/81)
  * Added filtering options to `listo` to filter orders by status [\#86](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/86)
  * Fixed a bug where updated customers and orders are not displayed after certain commands (e.g. upon adding a new order, or marking a customer) [\#171](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/171)

* **Documentation**:
  * User Guide:
    * Added the `setnotec`, `appendnotec`, `setnoteo`, and `appendnoteo` commands [\#69](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/69)
    * Updated the command formats throughout the Features section [\#161](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/161)
  * Developer Guide:
    * Added use cases [\#15](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/15), [\#168](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/168)
    * Added implementation details for customer and order notes [\#74](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/74)
    * Added implementation details for sorting [\#77](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/77)
    * Updated the UI section to fit LoyaltyLift [\#168](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/168)
    * Added **Allow Customers to have similar Orders** to planned enhancements [\#168](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/168)
    * Added **Setting tiers** to planned enhancements [\#174](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/174)
    * Added manual testing instructions for customer commands [\#181](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/181)

* **Community**:
  * Reviewed 11 PRs, 4 with non-trivial comments: [\#43](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/43), [\#53](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/53), [\#71](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/71), [\#173](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/173)
