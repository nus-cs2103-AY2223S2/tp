---
layout: page
title: Tan Matthew Simon Castaneda's Project Portfolio Page
---

### Project: NextBigFish (NBF)


NBF is a desktop app tailored for the needs of sales-people, supporting the management of contacts, leads or clients **
and optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, NBF can get your client and lead management tasks done faster than traditional GUI apps.
NBF allows client contacts to hold data on their potential business size and counts of past transactions and aids users in categorising them using tags.
On top of that, NBF also provides a summary page to allow users to quickly get an overview of their performance each season, detailing relevant statistics.



Given below are my contributions to the project.

* **New Feature**: Added the ability to track the number of transactions that have taken place between the user and client. 
    * What it does: Allows the user to easily decrement of increment the amount of transactions that have taken place between the user and client, thus minimizing any sort of calculation errors involved in bookeeping.
    * Justification: This feature is in line with the applications aim of tailoring to fast typists, hence the user's train of thought would not have to be broken by performing a manual calculation. 
    * Highlights: There were multiple design considerations that I had to deal with, and ultimately I picked the one that was most tailored to the users experience. For example, the command syntax is very similar to the edit command, so that users can ride of their existing familiarity with the edit command to perform commands quickly in succession.

* **New Feature**: Made heavy changes to the person model to allow the user to track transaction count, and also distinguish between HIGH, MEDIUM, and LOW priority clients. 
  * What it does: Lets the user keep track of more information relating to the client, which they then can use to decide which clients they want to prioritize. 
  * Justification: This allows the user to be able to filter and sort clients by their importance, and hence as a salesperson, focus on the leads that are most important / loyal to you. 
  * Highlights: Again, several design considerations with this. However, the biggest hurdle was constantly maintaining the test files and logic everywhere to be consistent with the new fields.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=seriouslia0&tabRepo=AY2223S2-CS2103-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Wrote additional tests for existing features to increase coverage. Maintained all the JUnit tests previously written in line with implementing additional fields (Pull requests [\#32](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/32), [\#150](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/150)
  , [\#156](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/156))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `increment` and `decrement` [\#91](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/91)
        * Tweaked the documentation for the add and edit functions to match the current functionality.
    * Developer Guide:
        * Added implementation details of the `increment/decrement` feature.
        * Added the relevant user stories to the developer guide, reflecting how some of my enhancements along with my teammates features can be integrated into 1 user story.  [\#157](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/157)
        * Added instructions for manual testing of the `add` , `edit`, `sort`, `mark`, `increment/decrement` features. [\#160](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/160)

* **Community and Team Tasks**:
    * Reported over 10 bugs during PE-D, above the cohort average. 
    * Helped to fix the JUnit tests after changes to existing classes were made by teammates. 
    * Facilitate group discussion and give ideas on how to improve current features. 




