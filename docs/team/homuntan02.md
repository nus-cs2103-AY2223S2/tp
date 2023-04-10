---
layout: page
title: Tan Homun's Project Portfolio Page
---

### Project: GoodMatch

GoodMatch is a desktop application used for managing applicants and job listings for hiring managers. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Refactoring**: Refactor codebase to change context from AddressBook to ListingBook for GoodMatch
  * AddressBook => ListingBook
  * Person => Listing
  * Tag => Applicant
  * Collaborated with Branda Ang([panpannnnn](https://github.com/panpannnnn)): She duplicated and changed relevant 
  files while I deleted relevant files and completed refactoring.
  * Pull request [\#51](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/51)

* **New Feature**: Added Applicant class
  * What it does: It is the applicants that users will be creating and deleting when they use our app.
  * Justification: It is the basic building block of our application, applicants are what the users will add to listings.
  * Highlight: Implementation of things at the beginning were mainly handled by Clevon, Branda and I. We had to get the 
  ball rolling with though simple features but features that were tough to implement because it was the very first step taken.
  * Credits: This class was implemented without referencing any other sources because our Applicants is unique.

* **New Feature**: Implemented Applicants into Listing
  * What it does: Allows applicants to be added to listings.
  * Justification: When we first implemented Listings, we added applicants only as
  strings, but I changed it to be applicant class.
  * Highlights: This was the final step for the foundation of our app. it allowed my teamates to start 
  implementing the other features. 
  * Credits: This implementation was done without referencing any other sources.

* **New Feature**: Added the ability to undo previous commands.
  * What it does: Allows the user to undo all previous commands executed after the app one at a time. 
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. 
  The implementation too was challenging as it required changes to existing commands and classes.
  * Credits: This feature was implemented with a brief reference to the idea recommended in the AB3's developer's guide.
  the code was completely original and some changes have been made such as the use of an arraylist to store all previous commands since
  the opening of the app.

* **Logo Design**: Created the logo from scratch. [\#74]()
  * What it does: Replaces the AB3 logo.
  * Justification: To help differentiate our app from AB3.
  * Highlights: It was created from scratch using Canva.
  * Credits: None as the logo was original.

* **Quality Assurance**: Performed an in-depth review of the app and all its features in release v1.4.
  * What it does: Help us identify remaining bugs in our app.
  * Justification: To allow the team to fix existing bugs and minor flaws such as typos before the final submission.
  * Credits: None. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=homuntan02&breakdown=true)

* **Project management**:

  * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:

  * Wrote additional tests for existing features to increase coverage(Pull requests: [\#22](), [\#48]())

* **Documentation**:

  * User Guide:
    * Added documentation for the features `Applicant` and `Undo`.
    * Added draft documentation for `Applicant` into the common notion page for.
  * Developer Guide:
    * Added implementation details of the `Undo` feature and added the diagrams for it.

* **Community**:

  * PRs reviewed (with non-trivial review comments): [\#16](), [\#29](), [\#79](), [\#87](), [\#199]()
  * Contributed to forum discussions (Pull Requests: [132](), [175](), [200]())
  * Reported bugs and suggestions for other teams in the class: Reported bugs during PE dry run via CATcher.

