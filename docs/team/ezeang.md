---
layout: page
title: Ezekiel Ang's Project Portfolio Page
---
### Project: NextBigFish (NBF)

NBF is a desktop app tailored for the needs of sales-people, supporting the management of contacts, leads or clients **
and optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, NBF can get your client and lead management tasks done faster than traditional GUI apps.
NBF allows client contacts to hold data on their potential business size and counts of past transactions and aids users in categorising them using tags.
On top of that, NBF also provides a summary page to allow users to quickly get an overview of their performance each season, detailing relevant statistics.
Given below are my contributions to the project.

* **New Feature [SORT]**: Added the ability to **sort** by different fields as specified by user.
  * What it does: The `Sort` feature allows users to sort the contact list in ascending or descending order by a number of fields namely:
    * Name
    * Potential Sale Value (aka Size)
    * Transaction count
    * Priority
  
  * Justification: Here, we want users to be able to quickly sort their contact list by a specified field,
  so that they can adjust their priorities and focus their attention on their desired group, given their objectives.
  For example, a salesman may choose to sort his contact list by ascending transaction count,
  to view the contacts (or clients) that may not have transacted with him before.
  Here, his focus is to gain new customers instead of focusing on existing customers. The sort feature would quickly benefit him.
  * Highlights: This enhancement affects existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and required parsing of multiple arguments to achieve different behaviours.

* **New Feature [Filter]**: Added a filter command that allows the user to **filter** contacts by specifying tags.
  * What it does: The `Filter` feature allows users to filter the contact list by specifying tags.

    * Justification: Here, we want users to be able to quickly view a subset of their contact list which are categorised by a specific tag.
      This is so that they can adjust their priorities and focus their attention on their desired group, given their objectives.
      The feature can also be used to search for types of client contacts.
      For example, a salesman may choose to filter his contact list by specifying tags `car` `insurance`,
      to view the contacts (or clients) that may be interested in car insurance.
      
    * Highlights: This enhancement affects existing commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and required the use of streams in a different manner.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=ezeAng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=ezeAng&tabRepo=AY2223S2-CS2103-F10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.3` - `v1.4` (3 releases) on GitHub
  * Managed team's GitHub organisation
  * Managed team's repo's issues tracker
  * Supplied team's repo's issues labels
  * Managed team's repo's milestones and corresponding deadlines

* **Enhancements to existing features**:
  * Updated the GUI color scheme, window sizes, text wrapping etc (Pull requests [\#93](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/93/), [\#86](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/86/))
  * Updated the Summary Window sizes and label texts to fit accordingly
  * Made sure all same-spelt contact names are equal regardless of capitalisation (Pull requests [\#137](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/137))
  * Fixed bugs pertaining to contact names, the comparison of contact names and parsing commands (Pull requests [\#137](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/137))
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find`
    * Added documentation for the features `add`
    * Added documentation for the features `sort` and `filter`
    * Updated documentation for adding of priority
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`
    * Did cosmetic tweaks to the UG layout
    * Updated all relevant links to other pages
  * Developer Guide:
    * Added implementation details of the `delete` feature.
    * Added implementation details of the `filter` feature.
    * Added implementation details of the `sort` feature.
    * Wrote the project scope
    * Updated all relevant links
  * Github's README.md:
    * Wrote the project scope
    * Updated all relevant links
  * index page:
    * Wrote the project scope
    * Updated all relevant links

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#149](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/149), [\#87](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/87), [\#136](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/136), [\#24](https://github.com/AY2223S2-CS2103-F10-4/tp/pull/24)


* **Tools**:
  * Integrated a new GitHub plugin (CircleCI) to the team repo
  * Integrated CodeCov to team repo
  * Integrated JavaCI checks to team repo

