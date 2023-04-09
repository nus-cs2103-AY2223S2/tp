---
layout: page
title: Teo Rayon's Project Portfolio Page
---

### Project: MyLib

MyLib is a desktop bookmarking application used for keeping track of webnovels and comic a user is reading. Its bookmarking features are general enough to perform bookmarking tasks for other things like blogs, articles and basically anything. The user interacts with the application using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.
* **Code Contributed**: [Reposense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=CS2103T-T13-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=NoyaRoeT&tabRepo=AY2223S2-CS2103T-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **New Feature**: Added the ability to find bookmarks by title using keywords
  * What it does: allows the user to find bookmarks whose title contains at least one of the provided keywords.
  * Justification: this feature improves the product significantly since it allows a user to quickly find a bookmark whose title contains one of the provided keywords. Being able to quickly find a bookmark is an important aspect of any bookmarking application.
  * Credits: the implementation of this feature made use of existing infrastructure from AB3. Specifically, AB3 already had a feature that allowed finding people whose name contains at least one word in a provided set of keywords. That existing implementation was modified to be used for finding bookmarks instead.

* **New Feature**: Changed most of bookmark data to be optional
  * What it does: allows the user to omit certain bookmark data when adding bookmarks
  * Justification: many fields that a bookmark can contain may not be required for bookmarking tasks. For example, some users may not care about the author of a book. Hence, it is more convenient for the user if only certain fields were made compulsory.
  * Highlights:  many issues arose due to the fact that we inherited most of AB3's code, where all the fields in a Person object were compulsory.
    * The Bookmark class was created by modifying the Person class. It inherited all of Person's test cases which expects all fields to be compulsory. These test cases needed to be tracked down, updated or deleted. 
    * When loading the data for MyLib from json, the conversion from json to a `Bookmark` object expects the fields to be present and non-null. Needed to modify the conversion from json to a Bookmark object to accept optional fields that are not present. 
<br/>

* **New Feature**: Added a `Progress` field to a bookmark
  * What it does: allows a bookmark to track a user's progress with regards to the book being bookmarked.
  * Justification: one of the goals of our application is to track books the user is reading, so they may revisit the book in the future. Tracking progress allows them to easily see where they last left off with a book and continue from there if they decide to do so.
  * Highlights: as a field in the Bookmark class which forms the core data of our application, this enhancement will affect all future updates to the application. Hence, it required more thorough considerations of the various design alternatives. These include considerations such as how user input should be represented, how should the interface of the class look like to blend well with the other existing bookmark data, whether there is a need to separate volume, chapter and page which are contained in `Progress` into their own separate classes and how should progress input from the user be parsed and used to construct a `Progress` object. There was also a need to consider how `Progress` is adapted for JSON storage.

* **Contributions to UG**:
  * Added documentation for Introduction section, which gives a brief overview of what to expect from MyLib.
  * Added documentation for Getting Started section, which gives a step-by-step guide on how to download and start using MyLib.
  * Added documentation for About User Guide section, which informs the reader on the objective of the user guide and how to use it.
  * Added documentation for Key Definitions section to explain what a bookmark, genre and a tag is.
  * Added documentation for Adding a Bookmark in Commands section.

* **Contributions to DG**:
  * Added a detailed description of the implementation of the `Progress` class and its related classes.
  * Added product scope, user stories, use cases, non-functional requirements and glossary.

* **Contributions to team-based tasks**:
  * Arranged and lead the discussion for project meetings.
  * Lead the brainstorming and confirmation of features for each iteration.
  * Set up most of the Github issues that need to be addressed for each milestone.
