---
layout: page
title: Branda Ang's Project Portfolio Page
---

### Project: GoodMatch

GoodMatch is a desktop application used for managing applicants and job listings for hiring managers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Refactoring**: Refactor codebase to change context from AddressBook to ListingBook for GoodMatch
  * AddressBook => ListingBook
  * Person => Listing
  * Tag => Applicant
  * Collaborated with Homun Tan([homuntan02](https://github.com/homuntan02)): I duplicated and changed relevant files while he deleted relevant files and completed refactoring.
  * Pull request [\#51](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/51)

* **New Feature**: Added the ability view all job listings.
    * What it does: allows the user to view all existing job listings.
    * Justification: This feature improves the product significantly as it allows the user to keep track of all the job listings they have.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It allows all other commands to be able to display results.
    * Credits: *Referenced from AB3's list command*
    * Pull request [\#58](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/58)

* **New Feature**: Added the ability edit applicants.
  * What it does: allows the user edit the names of existing applicants.
  * Justification: This feature improves the product significantly as it allows the user edit any mistakes made during the creation of the listing and/or applicant without replacing the whole applicant list.
  * Pull request [\#92](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/92)

* **Update Feature**: Updated the UG link for the help feature.
  * Pull request [\#181](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/181)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=panpannnnn&breakdown=true)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added Feature List [\#118](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/118)
        * Added documentation for the features `add_app`, `edit_app`, `del_app` [\#191](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/191) and `undo` [\#193](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/193)
        * Updated documentation for the features `add`, `edit` and `edit_app` [\#193](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/193)
        * Updated Command Summary [\#193](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/193)
        * Updated GUI start up image [\#193](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/193)
        * Updated documentation for `add` and `edit` to clarify user doubts [\#195](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/195)
    * Developer Guide:
        * Created first draft of DG on Notion and transferred it to GitHub [\#56](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/56)
        * Fixed trailing whitespaces arising from Notion conversion [\#84](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/84)
        * Added use case for the `edit_app` feature [\#102](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/102)
        * Added diagrams for `sort` feature [\#205](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/205)

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#29](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/29), [\#78](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/78), [\#79](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/79)
    * Reported bugs and suggestions for other teams in the class ([PE Dry Run](https://catcher-org.github.io/CATcher/phaseBugReporting))

