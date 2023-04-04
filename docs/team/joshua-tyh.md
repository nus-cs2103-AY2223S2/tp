---
layout: page
title: Joshua's Project Portfolio Page
---

### Project: Bookopedia

Bookopedia is a desktop application specifically built for parcel delivery riders, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). This enables delivery riders to be able to quickly plan their deliveries by typing in commands and still be able to view them in an organized manner.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=joshua-tyh&breakdown=true)

* **New Feature**: Sort
  * What it does: Allows users to sort the current list of deliveries according to delivery status in ascending order.
  * Justification: Sorting deliveries by status can help users better manage their time and prioritize their work. It can also help them quickly identify deliveries that require attention or have a specific status, such as failed deliveries that need to be rescheduled or pending deliveries that have yet to be completed.
  * Highlights: 
    * Users can sort their deliveries in ascending order based on their status.
    * The ordering of `PENDING` < `OTW` < `DONE` < `FAILED` < `RETURN` was predefined when creating the Enumeration for Delivery Statuses.

* **New Feature**: Add Parcels to Deliveries
  * What it does: Allows users to add parcels to their existing deliveries.
  * Justification: Adding parcels to existing deliveries can help users better manage their work and keep track of multiple items in a single delivery. It can also help users save time spent deleting and creating new delivery entries when receiving new parcels for an existing delivery.
  * Highlights:
    * The existing delivery to add a new parcel to is identified by their index
    * The implementation was challenging as it required changes to existing objects variables
  * Credits: This feature was implemented by refactoring the Tag class in the codebase. No external libraries were used.

* **Enhancements to existing features**:
  * Made the email address field optional by allowing it to accept blank inputs.

* **Documentation**:
  * User Guide:
      * Added documentation for the [sort](https://ay2223s2-cs2103-w16-1.github.io/tp/UserGuide.html#sorting-all-deliveries--sort) feature.
      * Supplemented documentation for the [edit](https://ay2223s2-cs2103-w16-1.github.io/tp/UserGuide.html#editing-a-delivery--edit) feature.
  * Developer Guide:
      * Added implementation details for the [sort](https://ay2223s2-cs2103-w16-1.github.io/tp/DeveloperGuide.html#implementation-details-4) feature.
      * Added design considerations for the [sort](https://ay2223s2-cs2103-w16-1.github.io/tp/DeveloperGuide.html#design-considerations-4) feature.

* **Contributions to team-based tasks**:
  * [Bug fix](https://github.com/AY2223S2-CS2103-W16-1/tp/pull/147) for edit command where the optional field of email address was not behaving as expected.
  * Added JUnit tests wherever necessary to ensure code coverage requirement.
  * Kept test utility files updated for testing implemented features.
  * Generated UI mockup.

* **Review/mentoring contributions**:
  * Reviewed PR [#138](https://github.com/AY2223S2-CS2103-W16-1/tp/pull/138)
  * Reviewed PR [#143](https://github.com/AY2223S2-CS2103-W16-1/tp/pull/143)
  * Reviewed PR [#144](https://github.com/AY2223S2-CS2103-W16-1/tp/pull/144)

* **Contributions beyond the project team**:
  * Helped with CATcher load test.
  * Helped report bugs and suggestions for other teams during [PE-D](https://github.com/joshua-tyh/ped/issues).


