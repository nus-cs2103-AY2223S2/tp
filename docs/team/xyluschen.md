---
layout: page
title: Xinyang's Project Portfolio Page
---

## Project: sprINT

**sprINT** is an **internship-tracking application** that was created to assist students in their internship hunt.

Students often face a great administrative burden in keeping track of the high volume of job or internship applications, which
include details like interview dates. Therefore, sprINT aims to help these students by
tracking and recording their application details, such as the company, company contact information and status.

sprINT is a desktop application that is optimised for Command Line Interface (CLI) users, while still providing the benefits
of a Graphical User Interface (GUI).

Given below are my contributions to the project

* **Code contributed**:
Please refer to this [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=xyluschen&breakdown=true)


* **Project management**
* Authored **21** issues on the project repository.
* Reviewed and merged **29** pull requests.
* Verified and filtered all **43** issue reports received from external parties during the PE-Dry Run.


* **New Feature**
  * Application Statistics 
  * Please refer to PR [#152](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/152)
  * **About**: This feature enables students to have a clear overview of their application progress, and they can quickly
  identify which applications require their attention. 
  * **Benefits**: By providing users with this information, they can easily determine if they need to follow up on 
  any applications or if they need to adjust their job search strategies. Additionally, by tracking their application statistics,
  students can better evaluate their job search performance and identify areas for improvement. For example, they may discover that
  they have applied to many positions but not receiving many offers. This information can help them improve their application materials
  and refine their job search strategies, ultimately increasing their chances of securing a placement.
  * **Challenges**: This feature was the first that involved significant modifications to the existing GUI, as it was necessary
  to create a Statistics Panel that displays a User's application statistics. It also involved the creation of a `Statistics` entity
  as part of the `Model` component. It required changes as to how `Commands` were processed by the `LogicManager`, specifically commands
  that potentially changes the application statistics.

  
* **Enhancements to existing features**
  * Logic and UI component Refactoring 
    * Please refer to PR [#62](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/62)
    * Updated existing classes in Logic and UI components to match sprINT's requirements.

  * Adding an application
    * Please refer to PR [#62](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/62)
    * The first feature to be added after the initial refactoring of AB3 code base.
    * Updated the initial `AddCommand` in AB3 (now `AddApplicationCommand`) to match sprINT's user
      story and requirements.

  * Tags for Applications
    * Please refer to PR [#104](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/104)
    * Integrated the existing `Tag` entity in AB3 to work with sprINT's application entities.

  * Clearing all applications
    * Please refer to PR [#109](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/109)
    * Updated the initial `ClearCommand` in AB3 to match sprINT's requirements.

  * Exiting sprINT
    * Please refer to PR [#104](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/104)
    * Update the initial `ExitCommand` in AB3 to match sprINT's requirements.

* **Documentation**
  * ReadMe
    * Please refer to PR [#32](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/32)
    * Update `README.md` to match sprINT's user story and requirements.

  * User Guide
    * Please refer to PR [#40](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/40)
    * Updated documentation for the Add, Clear and Exit features to match sprINT's requirements

  * Developer Guide:
    * Please refer to PR [#120](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/120), [#130](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/130)
          and [#132](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/132)
    * Created UML diagrams for Add, Clear and Exit features of sprINT.
    * Added implementation details for Add Application, Clear and Exit features of sprINT. 
    * Documented how each feature's command is processed by sprINT and explains the interaction betweeen the different
    components of the app itself.
 
* **Team-based tasks**
  * Enforced build success with the help of GitHub Actions throughout sprINT's development
  * As the team lead, delegated project tasks and responsibilties using GitHub's issue tracker to team members.



