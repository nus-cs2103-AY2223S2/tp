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
  * Lead the discussion in weekly project meeting
  * Encourage teammates to brainstorm for Unique Selling Point of InternEase at the end of v1.2 iteration
  * Managed releases `v1.3` - `v1.4` (2 releases) on GitHub

* **Enhancements to existing features**: Enhanced the find command that allows the user to find internship application by the company name, job title, application status and interview date(s).
  * What it does: allows the user to find an internship application by matching keyword for the company name and job title, by a specified application status, and by specifying a range of dates where the interview date falls within.
  * Justification: This feature makes the product more user-friendly as the user is able to search through the list with huge number of applications efficiently and makes subsequent changes.
  * Highlights: This enhancement is challenging to implement as to make the command format as `defensive` as it could be, the checking in parser is somewhat exponential.

* **Enhancements to existing features: Others**:
    * Updated the help command link to current UG
    * Wrote additional tests for new and existing features to increase coverage from 36.91% to 39.83% (Pull requests [\#290](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/290))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `list`, `help`, `sort` and `find`: [\#200](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/200), [\#210](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/210), [\#212](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/212)
        * Did cosmetic tweaks to existing documentation of features: [\#291](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/291)
    * Developer Guide:
        * Added implementation details of the `list`, `sort`, and `find` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#106](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/106), [\#129](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/129), 
[\#130](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/130), [\#131](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/131), [\#132](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/132), 
[\#133](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/133), [\#147](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/147), [\#155](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/155), [\#262](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/262)
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2223S2/forum/issues/239#issuecomment-1454813105), [2](https://github.com/nus-cs2103-AY2223S2/forum/issues/294#issuecomment-1489103419), [3](https://github.com/nus-cs2103-AY2223S2/forum/issues/294), [4](https://github.com/nus-cs2103-AY2223S2/forum/issues/247#issuecomment-1460008541))
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2223S2-CS2103T-W13-3/tp/issues/161#event-8953814014))
