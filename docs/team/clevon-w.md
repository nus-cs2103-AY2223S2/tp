---
layout: page
title: Clevon Wong's Project Portfolio Page
---

### Project: GoodMatch

GoodMatch is a desktop application used for managing applicants and job listings for hiring managers. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added Listing class [#16](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/16)

  * What it does: It is the listing that users will be creating, deleting and editing when they use our app.
  * Justification: It is the basic building block of our application, a listing is what users are working with.
  * Highlight: Implementation of things at the beginning were mainly handled by Homun, Branda and I. We had to get the ball rolling with though simple features but features that were tough to implement because it was the very first step taken.
  * Credits: This class was implemented without referencing any other sources because our Listing is unique.

* **New Feature**: Add Command [#52](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/52)

    * What it does: Allows the user to add a job listing into the ListingBook.
    * Justification: This feature is a core feature of the product, without it, nothing else can be done.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and also had to be up as soon as possible so that other members can work on their commands (if not they don't have any listings to test with). Since this was the first major change as well, many issues cropped up with things from AB3 that did not work with our implementation of things and it took a long time to delete and debug which we all did together as a team.
    * Credits: The implementation was referenced from AB3. However, since a listing is completely different from a person, the only parts that could be referenced is the parsing flow, from ListingBookParser to AddCommandParser.
    
* **New Feature**: Completely new User Interface [#85](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/85) [#88](https://github.com/AY2223S2-CS2103T-W14-3/tp/pull/88)

  * What it does: Revamped the GUI for a much more refreshing look.
  * Justification: We did not want a user interface that was the exact same as the base AddressBook3 because it would seem dull and un original. Also, a white theme is something that is much cleaner and more minimalistic for users.
  * Highlights: CSS for FXML and HTML is actually a little different. Working through those differences took quite some time. Also, going through the current CSS to find out what does what also took quite a lot of effort because unlike traditional frontend frameworks or HTML where classes are added, most of the CSS are tagged to the elements generated and not classes. Tidying up and understanding everything from all the different files took time before I could implement our own custom CSS.
  * Credits: All the work is done by myself. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=clevon-w&breakdown=true)

* **Project management**:

    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:

    * Revamped the GUI by changing the entire CSS.
      * Implemented a completely new theme, new scrollbars, and even transitions and animations.
      * Fixed all UI related bugs from that were found from PED and our own testings.
      * Cleaned up the CSS by removing any code that was not used in the application.
    * Wrote additional tests for existing features to increase coverage.
    * Fixed bugs from error messages and UI as well as those found during PED.

* **Documentation**:

    * User Guide:
      * Implemented the first draft of the UG on Notion before transferring over to code.
      * Added documentation for the features `exit`, `add`, `help`, `view`, `edit`, `delete` with Nicholas.
      * Edit according to peer review and comments from CS2101 Prof.
      * Checked through and edited the next few drafts.
      * Beautified by using screenshots and implementing more "you" language.
    * Developer Guide:
      * Majority of DG was done by me but the rest of the team helped to check and make edits.
      * Implemented first draft.
      * Implemented second draft.
        * Front matters.
        * Acknowledgements.
        * Setting up.
        * Design.
        * Implementation overview, add, and autocomplete.
        * Appendices.
      * Drew the majority of the diagrams in the DG.

* **Community**:

    * PRs reviewed (with non-trivial review comments): 23 PRs in total, 22 of them are non-trivial reviews.
    * Contributed to forum discussions: 4 posts.
    * Reported bugs and suggestions for other teams in the class: [PED catcher](https://github.com/clevon-w/ped/issues).

