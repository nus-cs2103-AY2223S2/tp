---
layout: page
title: Kynhan Tang's Project Portfolio Page
---

### Project: ExpressLibrary

ExpressLibrary is a desktop application used for librarians to easily track the status of book in the library. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added a borrow command
    * What it does: Allows the user to lend a specified book in the book list to a specified person in the person list with a given due date.
    * Justification: This is a key feature for ExpressLibrary because it allows the user to track which books are borrowed by which persons and when they are due.
    * Highlights: This command was quite challenging to implement because there weren't any existing commands that dealt with two objects from two different lists (AB-3 had no book list). Thus, it was interesting to come up with ways to accept two different indexes using the existing `ArgumentMultimap` parser system. 

* **New Feature**: Added a return command
    * What it does: Allows the user to return a person's specified borrowed book back so that it is available to borrow again
    * Justification: This is another essential feature for ExpressLibrary because it allows the user to reset the status of a book from being borrowed to available again and removes the book from the respective person's borrowed books list.
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=kynhan&breakdown=true)

* **Project management**:
    * Reviewed pull requests from Kenz

* **Enhancements to existing features**:
    * Improved on the `editBook` command to account for cases where a user could edit borrow or due dates even if the book isn't borrowed ([\#163](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/163))
    * Improved validation of dates when editing dates in `editBook` command and when specifying dates in the `borrow` command ([\#176](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/176))
    * Added better error messages for users when index-parsing issues occur ([\#179](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/179))

* **Documentation**:
    * User Guide:
        * Added documentation for `borrow`
        * Added documentation for `return`
    * Developer Guide:
        * Added section on how the `borrow` and `return` commands were implemented
        * Added use case section on deleting a person

* **Community**:
    * PRs reviewed (with review comments): [\#74](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/74), [\#89](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/89), [\#90](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/90), [\#180](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/180) 

