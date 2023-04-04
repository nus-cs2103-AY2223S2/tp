---
layout: page
title: Lee Zong Xun's Project Portfolio Page
---

### Project: FriendlyLink

FriendlyLink is a personnel information management tool designed for Voluntary Welfare Organisations to keep elderly and volunteer records, and pair them up efficiently and effectively.

Given below are my contributions to the project.

* **New Feature**: Command recommendations and autocompletion
    * What it does: Provides command recommendations for registered [commands](../UserGuide.html#command) and [field's](../UserGuide.html#field) [prefixes](../UserGuide.html#prefix).
    * Justification: Autocompletion and Recommendation are features that can greatly enhance the user experience when using the application. By predicting the set of words that the user intends to type, it allows the user to operate efficiently and effectively when typing the commands into the application.
    * Highlights: The implementation was challenging as it requires attention to intricate details and different edges cases. Code is written as modular as possible, so that it is easily extensible to more input validation, and plugins.
    * Credits: Adapted from [Agolia Documentation](https://www.algolia.com/doc/guides/solutions/ecommerce/search/autocomplete/predictive-search-suggestions/)

* **New Feature**: Popover UI for Pair
    * What it does: Popover that appears on hover which allows user to view details in greater depth for those involved in a particular pairing.
    * Justification: Viewing the pairing details in a quick and easy manner without cluttering the UI.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=Zxun2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=Zxun2&tabRepo=AY2223S2-CS2103T-W12-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.3.trial` (1 trial release) on GitHub
    * Set up GitHub Organisation, Repo and Project board
    * Organized weekly meetings and triage meeting notes. 

* **Enhancements to existing features**:
    * Major overhaul of existing UI, including changes to color scheme, UI layout and Help Window (Pull requests [\#108](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/108), [\#153](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/153), [\#174](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/174))
    * Add Storage Classes for Elderly and Volunteer (Pull requests [\#32](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/32))
    * Add available dates attributes to elderly and volunteers (Pull request [\#99](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/99))

* **Documentation**:
    * Did cosmetic tweaks to existing documentation of UG and DG
    * User Guide:
        * Added documentation for the features `Command Recommendation`, `help`
    * Developer Guide:
        * Added implementation details of the `Command Recommendation`, `Autocompletion` and `Input Validation` features.
        * Added implementation details for `Storage` class 

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#21](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/21), [\#69](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/69), [\#114](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/114), [\#150](https://github.com/AY2223S2-CS2103T-W12-1/tp/pull/150)

* **Tools**:
  * Integrated a new Github Plugin (CodeFactor) to the team repo
  * Integrated a new Github Workflow (Stale) to mark inactive issues as stale
