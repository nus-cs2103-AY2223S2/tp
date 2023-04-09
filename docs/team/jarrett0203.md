---
layout: page
title: Jarrett Teo's Project Portfolio Page
---

### Project: ExpressLibrary

ExpressLibrary is a desktop application used for librarians to easily track the status of books in the library. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Modified the book object in ExpressLibrary so that it is not only a field in Person class and added ListBookCommand.
  * What it does: allows the user to list all books in
    ExpressLibrary.
  * Justification: This feature is essential for ExpressLibrary to function properly because the Book object is a very vital component of ExpressLibrary that would break exisitng and new commands if not implemented properly.
  * Highlights: This was very challenging as it required to all four components in ExpressLibrary in order to work. For `UI`, the GUI had to be reworked in order to accomodate the new book list. For `Logic`, commands had to acoomodate the new book object. `Model` is self explantory and for `Storage` reading and writing the new book object.

* **New Feature**: Added colour coding for due dates when the book is about to expire/expiring.
  * What it does: alerts the user about the books that are going to expire by changing the colour of due dates so that the user can remind the borrowers to return their books.
  * Justification: This feature is a nice-to-have for ExpressLibrary as it provides users with instant visual feedback about what books to prioritise.
  * Highlights: It was interesting to find out how CSS works differently with JavaFX and coming up with alternative implementations to solve this problem.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jarrett&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Jarrett0203&tabRepo=AY2223S2-CS2103T-T12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Created team repo
  * Managed all milestones
  * Managed release `v1.3`

* **Enhancements to existing features**:
  * Mass refactoring of AddressBook to ExpressLibrary. ([\#44](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/44))
  * Updated PersonCard.java to reflect the new book object. ([\#62](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/62))
  * Improved on DeleteBookCommand and DeletePersonCommand to account for the case where the book has been borrowed / person has borrowed a book. ([\#109](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/109))
  * Added validation for dates on when editing dates for EditBookCommand. ([\#112](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/112))

* **Documentation**:
  * User Guide:
    * Added documentation for `listBook`.
    * Added screenshots for some commands.
    * Added colour coding section for due dates.
  * Developer Guide:
    * Changed `UI` and `Model` diagrams to include new book object.
    * Added section on how data is saved in ExpressLibrary.
    * Added section on manual testing for saving data.
    * Modified target user profile in Appendix.

* **Community**:
  * PRs reviewed (with non-trivial review comments):
  [\#47](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/47), [\#65](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/65), [\#79](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/79), [\#82](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/82), [\#86](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/86), [\#91](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/91), [\#92](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/92), [\#162](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/162), [\#164](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/164), [\#166](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/166), [\#173](https://github.com/AY2223S2-CS2103T-T12-3/tp/pull/173) 
