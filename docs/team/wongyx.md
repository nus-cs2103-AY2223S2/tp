---
layout: page
title: Wong Yong Xiang's Project Portfolio Page
---

### Project: MyLib

MyLib is a desktop bookmarking application used for keeping track of books a user is reading. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense hyperlink](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=wongyx&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=wongyx&tabRepo=AY2223S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Add the ability for users to find bookmarks using multiple optional fields.
  * What it does: Provide users with a more efficient way of searching for bookmarks.
  * Justification: This provides convenience for the users.

* **Enhancement to existing features**:
  * Updated Title to only accept up to 180 characters.
  * Updated BookmarkContainsPredicate to accept name, genre, tags, and author.

* Added test cases to increase code coverage. (Pull request [\#276](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/276))

* **Documentation**:
  * User Guide:
    * Updated documentations of feature `find`. (Pull request [\#145](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/145))
    * Updated examples commands for `add` to fix inconsistency between UG and the app itself. (Pull request [\#252](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/252))
  * Developer Guide:
    * Added implementation details of `find`.
