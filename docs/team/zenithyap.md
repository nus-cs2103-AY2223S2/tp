---
layout: page
title: Zenith's Project Portfolio Page
---

### Project: MyLib

MyLib is a desktop bookmarking application used for keeping track of books a user is reading. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense hyperlink](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=zenithyap&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: Added extra tags functionality. This includes, adding a tag to the tag list, deleting a tag from the tag list and listing all tags from the tag list.
  * What it does: Provides the user with a personalized tag list.
  * Justification: This provides a more consistent of the usage of tagging, to reduce errors such as typo errors.
  * Highlights: This enhancement required the addition of several new functionalities as well as the need to store the tag list in a json file, which was quite challenging.

* **New Feature**: Added the ability to sort bookmarks by rating.
  * What it does: Allows the user to sort the bookmarks by rating in either ascending or descending order.
  * Justification: This feature improves the user's ability to manage the bookmarks as the user may have many different bookmarks and the app should provide a way of sorting the bookmarks.

* **Enhancements to existing features**:
  * Changed the naming of the codebase to fit our application (Pull request [\#65](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/65))

* Added test cases to increase code coverage [\#270](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/270) [\#280](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/280)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addtag`, `dtag`, and `tags` [\#159](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/159)
    * Added documentation for the feature `sort` [\#190](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/190)
  * Developer Guide:
    * Added implementation details of the `addtag`, `dtag`, and `tags` features.
