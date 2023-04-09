---
layout: page
title: Goh Jun Yi's Project Portfolio Page
---

## Project: LoyaltyLift

LoyaltyLift is a desktop application designed for small business owners to manage their customers and orders efficiently, allowing you to **improve customer relations** and **increase customer loyalty**. 

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=junyi00&breakdown=true)

* **New Feature**: Added `viewc` and `viewo` commands ([#28](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/28), [#92](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/92))
    * What it does: When the user uses `viewc` / `viewo` command or clicks on the customer / order in the list, the information panel will display the customer or order's information.
    * Justification: As there are many details to a customer or an order, a bigger view allows proper display of information for the user to read the information easily. 
    * Highlights: This allows for additional information to be shown such as an order history of a customer. This also sets up for other commands (e.g. `edito`, `markc`), to also display the customer or order in the information panel automatically.

* **New Feature**: Added the ability to assign a customer as an individual or enterprise ([#33](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/33))
    * What it does: Adds an attribute to `Customer` to allow the user to set that a `Customer` is an individual or enterprise with `addc` and `editc`.
    * Justification: As the user may have customers that are organisations instead of just people, this helps users to categorise them correctly.

* **New Feature**: Added storage for `Order` ([#50](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/50), [#56](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/56))
    * What it does: Whenever an `Order` is created, updated or deleted, the application automatically saves order list into the local storage.
    * Justification: Similar to how `Customer` data are saved, `Order` data is saved as well so the data is lost on application close.

* **New Feature**: Added Status / Progress History for `Order` ([#75](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/75))
    * What it does: Allows the user to view how an `Order` has progressed as the status changed.
    * Justification: As an order progresses from **Pending**, **Paid** and onwards, this allows the user to refer to the history of an order. My changes provide the `Model` and `Storage` implementation for commands such as `advo`, `revo` and `cancelo` to work with.
    * Highlights: This required analysis of different design alternatives. Among the alternatives, the implementation used was chosen for extensibility, in the future, we expect that the user may want to more information such as notes to each progress update or revert.

* **Enhancements to existing features**:
    * Added UI to view orders that belong to a customer in the information panel for a customer
    * Revamped the default view of the UI
        * Include a tab to switch between customer and order list and an information panel to display a customer or order's information
        * Styling with CSS with a defined color scheme to enhance the aesthetics for the UI

* **Project management**:
    * Managed releases v1.1 - v1.4 (4 releases) on GitHub
    * Updated README.md and site-wide settings

* **Documentation**:
    * User Guide:
        * Added `viewc` command [#82](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/82) 
        * Added and updated _Introduction_, _Tutorials_ & _Frequently Asked Questions_ [#102](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/102)
        * Updated language to be more user-friendly [#180](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/180)
        * Organised _Features_ & _Command Summary_ into sections for improved readability [#102](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/102)
        * Added & updated most screenshots [#180](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/180)
    * Developer Guide:
        * Updated details in `Logic` component [#160](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/160)
        * Added implementation details for `markc` and `unmarkc` [#160](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/160)
        * Added implementation details for an `Order`'s status attribute  [#82](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/82)
        * Added planned enhancements for feature flaw where customers with the same names cannot be added [#160](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/160)
        * Added manual testing instructions for order commands [#185](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/185)
        * Updated user stories [#160](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/160)

* **Review/mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): [#51](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/51), [#89](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/89), [#179](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/179)

* **Community**:
    * Helped others on the [forum](https://nus-cs2103-ay2223s2.github.io/dashboards/contents/forum-activities.html#7-goh-n-yi-junyi00-23-posts)
    * Identified [11 bugs](https://github.com/Junyi00/ped/issues) for CS2103T-T11-3 during PE-D

