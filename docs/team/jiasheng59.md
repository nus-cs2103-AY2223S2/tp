---
layout: page
title: Lee Jia Sheng's Project Portfolio Page
---

### Project: InternEase

InternEase is a powerful and innovative desktop app designed to streamline the internship application process for Computer Science undergraduates. 
With its optimized combination of a Command Line Interface (CLI) and Graphical User Interface (GUI), 
InternEase offers users the best of both worlds - the speed and efficiency 
of a CLI for those who can type quickly, and the user-friendly experience of a GUI for those who prefer a visual interface. 
If you are new to internship application, InternEase will definitely be your best buddy that gets you started smoothly.

Given below are my contributions to the project.

* **New Feature**: Added a summary panel to show statistics of internship applications made.
    * What it does: allows the user to have an overview to current status of internship applications. 
    * Justification: This feature improves the product in terms of visualising data into a more intuitive form and helps user to better manage those applications which have yet to received reply. 
    * Highlights: This enhancement not only support summarising existing status of internship applications implemented but also will support any further internship status to be implemented in future automatically. It's complete in the sense that it's complete and not hard-coded so by introducing new `status`, it won't require any changes in this portion. 
    * Disclaimer: The GUI design of the summary panel references the design of `ListView` panel of AB3 which is the upstream repo InternEase forked from.

* **New Feature**: Added a sort command that allows the user to sort all applications by company name, job title, application status and interview dates.
  * What it does: allows the user to have a sorted view of the list of internship applications by above-mentioned attributes.
  * Justification: This feature makes the product more user-friendly as the user is able to better manage large volume of applications made and view the relatively more urgent internship applications using sort by interview date command.
  * Highlights: This enhancement is challenging to implement as it requires changing the underlying representation of the internship application list without breaking the abstraction barrier, so that other commands relying on the initial representation don't break after the change.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jiasheng59&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Managed releases `v1.3` - `v1.4` (2 releases) on GitHub

* **Enhancements to existing features**: Enhanced the find command that allows the user to find internship application by the company name, job title, application status and interview date(s).
  * What it does: allows the user to find an internship application by matching keyword for the company name and job title, by a specified application status, and by specifying a range of dates where the interview date falls within.
  * Justification: This feature makes the product more user-friendly as the user is able to search through the list with huge number of applications efficiently and makes subsequent changes.
  * Highlights: This enhancement is challenging to implement as to make the command format as `defensive` as it could be, the checking in parser is somewhat exponential.

*--------------below are to be updated-------------------*

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Updated the help command link to
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
        * Added documentation for the features `delete` and `find` [\#72]()
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

* **Tools**:
    * Integrated a third party library (Natty) to the project ([\#42]())
    * Integrated a new Github plugin (CircleCI) to the team repo

* _{you can add/remove categories in the list above}_
