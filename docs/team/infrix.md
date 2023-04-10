---
layout: page
title: Dawson Nui Jun Yi's Project Portfolio Page
---

### Project: LoyaltyLift

LoyaltyLift is a desktop application for small business owners to manage customers and their orders to boost customer satisfaction and retention rate. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Setting points for customers with the `setpoints` command ([\#43](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/43), [\#95](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/95))
  * What it does: Allows the user to setpoints for each customer. The user can overwrite the customer's existing current points.
  * Justification: Points are a key feature of our application. It allows the business owner to track and associate points to their customers. With this, business owners can see their most loyal customers and create a reward system where they can use their customer's points to claim rewards. 
  * Highlights: As `Points` is a new attribute in the `Customer` class, this feature spanned across all major components of the application. As a result, it was fairly time consuming to look through the different major components for places to edit, as this is a brownfield project.
  In the Logic component, a new command, and command parser would have to be implemented. Also, a parser for points with a new prefix pt/ would have to be implemented.
  In the Model component, a new `Points` class would be implemented. Subsequently, a new attribute in the `Customer` class would result in an outdated constructor. Consequently, there were design decisions about whether the previous constructor should be kept, a new constructor should be made, or an overloaded constructor should be implemented.
  In the Storage component, the new `Points` attribute would be stored for every `Customer` in the `JsonAdaptedCustomer` class.
  In the UI component, a new points badge had to be added at `CustomerCard` class and new points information had to be added at `CustomerGeneralInfo` class.
  Since this feature spans across all major components, test cases had to similarly be implemented in Logic, Model, Storage and UI.

* **New Feature**: Adding/subtracting points from customers with the `addpoints` command ([\#53](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/53), [\#95](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/95))
  * What it does: Allows the user to update points for each customer. This command will not overwrite the customer's existing points. 
  * Justification: It would not be feasible for business owners to only rely on the `setpoints` command. If they do, they would have to first refer to and remember the customer's existing points, subtract or add points on their own, before setting the customer's new points. The `addpoints` command would remove this hassle as business owners can simply update customer's points, keeping LoyaltyLift a fast application to use.
  Moreover, a new system of cumulative points was needed for customers. It would not make much sense for a loyal customer with low current points as he had claimed certain rewards, to be of a lower tier than a new customer with higher current points. With this command, it is possible to separate current points and cumulative points as we can simply not subtract cumulative points when claiming rewards.
  * Highlights: This feature similarly spanned across most major components of the application. As a result, it was a bit time consuming to look through the different major components for places to edit.
  In the Logic component, a new command and command parser would have to be implemented. Also, a new parser for addPoints would be implemented. There were certain design decisions to be made for parser, as to whether to utilise the `Integer.valueOf` method in parsing or whether I should implement my own way of parsing the points with the modifier enum class, stated below.
  In the Model component, a new attribute, `cumulative points` would be added to the `Points` class. In this component, there were also design decisions made as I originally implemented an inner static class called `AddPoints` to encapsulate the information for the addition or subtraction of points. However, this turned out to be too complicated to achieve the intended objective, and so a simpler approach of checking that the customer's points was valid after adding/subtracting points was adopted. There are more in-depth details of this design consideration in the Developer Guide.
  Similarly, test cases had to be added in Logic, Model and Storage.

* **New Feature**: Tier system for customers ([\#103](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/103))
  * What it does: Allows the user to observe what tier a customer is in, allowing them to find their most loyal customers at a glance
  * Justification: There should be a way to differentiate customers based on their points, allowing business owners to perhaps provide discounts to customers in higher tiers.
  * Highlights: This feature originally also composed of a `settier` command for users to customise the point threshold of their tiers. However, it was not an easy task to implement this as executing the command would result in a change in point threshold which consequently, would require an update on all customers, to check the new tier that they would belong to. Moreover, a way to store the user preferences for the point threshold for each tier would have to be implemented. As a result, around a day's worth of work was discarded as we eventually decided not to implement the command. See this [branch](https://github.com/Infrix/tp/tree/branch-set-tier-command) for the previous, attempted implementation of this feature. Eventually, we settled on a much simpler, fixed tier system where there are fixed point thresholds.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=Infrix&breakdown=true)

* **Project management**:
  * Created UML diagram for how v1.2 should look like on Google Drive, to keep everyone on the same page
  * Managed releases v1.1 - v1.4 (4 releases) on GitHub

* **Documentation**:
  * User Guide:
    * Added `setpoints` and `addpoints` command [\#35](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/35)
    * Added Rewards tutorial [\#173](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/173)
    * Involved in the final draft of the UG, edited language and edited the uG based on consultation feedback [\#173](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/173)
  * Developer Guide:
    * Added initial target user profile [\#10](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/10)
    * Added initial value proposition [\#10](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/10)
    * Added 30+ user stories [\#10](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/10)
    * Added glossary [\#10](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/10)
    * Added design details for implementation of adding/subtracting points [\#71](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/71), [\#184](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/184)
    * Updated architecture section [\#184](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/184)

* **Community**:
  * Reviewed 26 PRs, 3 with non-trivial comments:[\#50](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/50), [\#56](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/56), [\#102](https://github.com/AY2223S2-CS2103T-T09-3/tp/pull/102)
  * Identified [11 bugs](https://github.com/Infrix/ped/issuess) for CS2103T-W10-2 during PE-D