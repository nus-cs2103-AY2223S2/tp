---
layout: page
title: Clevon Wong's Project Portfolio Page
---

### Project: GoodMatch

GoodMatch is a desktop application used for managing applicants and job listings for hiring managers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added Listing class

  * What it does: It is the listing that users will be creating, deleting and editing when they use our app.
  * Justification: It is the basic building block of our application, a listing is what users are working with.
  * Highlight: Implementation of things at the beginning were mainly handled by Homun, Branda and I. We had to get the ball rolling with though simple features but features that were tough to implement because it was the very first step taken.
  * Credits: This class was implemented without referencing any other sources because our Listing is unique.

* **New Feature**: Add Command

    * What it does: Allows the user to add a job listing into the ListingBook.
    * Justification: This feature is a core feature of the product, without it, nothing else can be done.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and also had to be up as soon as possible so that other members can work on their commands (if not they don't have any listings to test with). Since this was the first major change as well, many issues cropped up with things from AB3 that did not work with our implementation of things and it took a long time to delete and debug which we all did together as a team.
    * Credits: The implementation was referenced from AB3. However, since a listing is completely different from a person, the only parts that could be referenced is the parsing flow, from ListingBookParser to AddCommandParser.
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=clevon-w&breakdown=true)

* **Project management**:

    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:

    * Revamped the GUI by changing the entire CSS.
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:

    * User Guide:
        * Added documentation for the features `exit`.
        * Edit according to peer review and comments from CS2101 Prof.
        * Checked through and edited the next few drafts.
    * Developer Guide:
      * Implemented first draft.
      * Implemented second draft.
        * Front matters.
        * Acknowledgements.
        * Setting up.
        * Design.
        * Implementation overview, add, and autocomplete.
        * Appendices.
      * Everyone helped to edit together.

* **Community**:

    * PRs reviewed (with non-trivial review comments): 23 PRs in total, 22 of them are non-trivial reviews.
    * Contributed to forum discussions: 4 posts.
    * Reported bugs and suggestions for other teams in the class: [PED catcher](https://github.com/clevon-w/ped/issues).

