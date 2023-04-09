---
layout: page
title: Shazrin's Project Portfolio Page
---

### Project: Bookopedia

Bookopedia is a desktop application specifically built for parcel delivery riders, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). This enables delivery riders to be able to quickly plan their deliveries by typing in commands and still be able to view them in an organized manner.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shazxrin&breakdown=true)

* **New Feature**: Added the ability to mark delivery with status
  * What it does: Mark a delivery with a status.
  * Justification: This feature assists the delivery rider in keeping track deliveries' statuses 
  * Highlights: 
    * Able to set deliveries with the following statuses:
      * `pending` for pending delivery
      * `otw` for in progress delivery _(p.s. otw is an abbreviation of 'on the way', synonymously with 'in progress')_
      * `failed` for failed delivery
      * `done` for done delivery
      * `return` for return delivery (i.e used when recipient rejects delivery)
    * Ensure that `done` and `return` deliveries are immutable to mutable commands: `mark`, `edit`, `add_pc`, `mark_pc` 
    * UI will reflect the status with its respective colors

* **New Feature**: Added the ability to view number of delivery attempts
  * What it does: Show the number of delivery attempts for each delivery.
  * Justification: This feature assists the delivery rider in keeping track of attempts for a delivery
  * Highlights:
    * UI will reflect the number of attempts

* **New Feature**: Added the ability to automatically mark delivery as 'return' (return to sender)
  * What it does: Deliveries with 3 failed attempts are marked as return automatically.
  * Justification: This feature assists the delivery rider without having to manually set a parcel is returned by keeping track on the number of attempts.
  * Highlights:
    * Setting `failed` to a delivery 3 times will automatically mark the delivery as `return`

* **Enhancements implemented**:
  * Renamed the project to `Bookopedia`
  * Refactor the codebase to `Bookopedia`

* **Documentation**:
  * User Guide:
    * Added documentation for the features `mark` [#15](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/15)
  * Developer Guide:
    * Added user stories [#23](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/23)

* **Contributions to team-based tasks**:
  * Gave design suggestions on adding parcel features
  * Helped with sorting and giving comments on bug reports from PE-D
  * Helped the team with Git issues
  * Created demo video
  * Bug fix to existing edit command implementation, conflicting with our requirements
  * Bug fixes on other team commands ensuring that their commands do not mutate deliveries that are done or returned

* **Review/mentoring contributions**:
  * Reviewed [#28](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/28)
  * Reviewed [#41](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/41)
  * Reviewed [#57](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/57)
  * Reviewed [#78](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/78)
  * Reviewed [#83](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/83)
  * Reviewed [#84](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/84)
  * Reviewed [#143](https://github.com/AY2223S2-CS2103-W16-1/tp/issues/143)

* **Contributions beyond the project team**:
  * Helped with CATcher load test
  * Participated in PE-D and gave feedback to the assigned team


