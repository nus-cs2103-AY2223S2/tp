---
layout: page
title: Zhou Yuxin's Project Portfolio Page
---

### Project: MyLib

MyLib is a desktop bookmarking application used for keeping track of books a user is reading. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense hyperlink](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=zhoyx&tabRepo=AY2223S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Added new Rating field. 
  * What it does: Allow users add a numeric rating from 0 to 5 to each bookmark.
  * Justification: This field can then be used for sorting based on rating to allow users to easily find the best and worst content stored in the library. [#157](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/157)

* **New Feature**: Created fixed list of Genres. [#139](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/139)
  * What it does: Added validation to only allow Genres from that list to be added.
  * Justification: Ensure consistency of Genres and reduce overhead

* **New Feature**: Added command to list valid Genres. [#139](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/139)
    * What it does: Displays the list of valid Genres
    * Justification: Allows users to check what are the valid genres available.

* **Enhancements**:
    * Created Progress field when modifying AddressBook to MyLib. [#75](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/75)
    * Updated Test cases for Fixed set of Genres. [#155](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/155)

* **Documentation**:
    * User Guide:
        * Added Target Audience
        * Added Key Definitions Section
        * Updated Add and Edit Section to include new Genre requirement and Ratings field. [#167](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/167)
    * Developer Guide:
      * Updated plantUML diagrams to reflect new Fields and classes. [#140](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/140)
      * Added Implementation for Rating Field. [#167](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/167)
      * Added User Stories for Tag, Sort and Delete[#328](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/328)
      * Added to Instructions for Manual testing for Tag related commands [#334](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/334)
      * Added Implementation for Add Feature. [#335](https://github.com/AY2223S2-CS2103T-T13-4/tp/pull/335)
* **Contributions to team-based tasks**
  * Created release for V1.3.1.trial
  * Maintain issue tracker for tasks


