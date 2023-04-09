---
layout: page
title: Ryan's Project Portfolio Page
---

### Project: *Fish Ahoy!*

*Fish Ahoy!* Is a **desktop app for managing your fish, fish tanks, and relevant tasks, optimized for use via a Command
Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). <br>

*Fish Ahoy!* **streamlines** the fish keeping experience by helping you keep track of your many **fishes**, **tanks** and
**weekly tasks**, such as feeding and cleaning.

Given below are my contributions to the project.

* **New Fish Sort command**: Added a sorting function for user to sort fish.
  * What it does: allows the user to sort fish by a given attribute.
  * Justification: This feature improves the product significantly because a user can sort his fishes to his own preference,
    and change the ordering of the fishes.
  * Highlights: This enhancement builds upon existing FilteredList functionality through integrating a SortedList.
    It required a study of design alternatives and Java FilteredList and SortedList implementations.

* **Improved Task Sorting**: Added a sorting based off priority to Tasks.
  * What it does: allows the user to easily view tasks based off priority.
  * Justification: This feature improves the product because a user can now see the most urgent tasks first and focus on them,
    improving the benefits of the tasks feature for the user.
  * Highlights: This enhancement also uses SortedLists to sort the tasks by priority. To facilitate this, I also added
    enums for priority to allow for easier sorting. (Pull Requests [\#119](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/119), [\#214](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/214))

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=ryanchua00&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management/Contributions to team-based tasks**:
  * Managed releases `v1.3` - `v1.3.1` (2 releases) on GitHub.
  * Checked, approved and merged 32+ PRs, handling conflicts and helped fix group mates' PRs:
    * Thoroughly reviewed PRs: [\#49](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/49)
      [\#63](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/63) - `v1.1 - v1.4`
  * Created 28 and closed 53 issues, of which the latter 40 was assigned and closed by me.
  * Ensure project progress is on schedule.
  * Summarise and delegate tasks at every project meeting, see [Project notes](https://docs.google.com/document/d/1SKZt5__jKFv3l1Zu2YpQm-VNddbT2NYJlIxam2Zethk/edit#bookmark=id.pnxemekevmpi).

* **Enhancements to existing features**:
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#67](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/67), [\#39](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/39))
  * **Improved Help page**: Added a JavaFX WebView to load the UserGuide.
    * What it does: allows the user to seek for help from the application.
    * Justification: This feature improves the product because a user can now access the help page from the application,
      and also a local html version of the help page if there is not internet access.
    * Highlights: This enhancement uses JavaFX WebView to load the help page.
    * Credits: After coming up with the idea to use JavaFX WebView, I referenced an already existing implementation of a
      help page built on JavaFX WebView from [AY2223S1-CS2103- F14-1](https://github.com/AY2223S1-CS2103-F14-1/tp/pull/66)

* **Documentation**:
  * User Guide:
    * Created and added all Ui images, including feature screenshots.
    * Update features to latest command format (Pull Request [\#55](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/55)).
    * Update descriptions for (Pull Request [\#80](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/80)):
      * Tanks, Fishes and Tasks.
      * Feature `fish sort`.
      * Introduction section.
  * Developer Guide:
    * Add implementation details of the `fish sort` feature.
    * Add sequence diagram for `fish sort`.
    * Add acknowledgements section.

* **Community**:
  * Contributed to forum discussions ([\#296](https://github.com/nus-cs2103-AY2223S2/forum/issues/296))
  * Helped team clarify an issue in `v1.4` feature freeze ([\#334](https://github.com/nus-cs2103-AY2223S2/forum/issues/334))

