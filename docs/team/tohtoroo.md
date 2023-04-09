---
layout: page
title: Aeron Toh's Project Portfolio Page
---

### Project: Dengue Hotspot Tracker

Dengue Hotspot Tracker is a desktop app for managing dengue cases. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 9.5 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to use prefixes to find people in the dengue case list.
  * What it does: allows the user to use prefixes along with the find command, the prefixes are optional but there
  should at least be one of the following prefix `n/`,`a/`,`d/`,`p/` and `v/`.
  * Justification: This feature improves the product significantly because an NEA staff can search for matching cases in
  the case list beyond just the name and postal code.
  * Highlights: This enhancement affects existing commands. It required an in-depth analysis of design alternatives
  especially on how to check for each prefix as they are optional. The implementation required some workaround with the
  postal code especially as we needed to keep the original functionality of find, which allowed for substrings of the
  original postal, but our check for validity for all postal codes allowed only valid 6 digits postal codes.

* **New Feature**: Added the ability to find by date ranges to find people in the dengue case list.
  * What it does: Allows the user to find cases by a specified date range using the prefixes `sd/` and `ed/`.
  * Justification: This feature improves the product significantly because an NEA staff may realistically require to
  search for cases that happened in a specific date range to use for data analytics.
  * Highlights: This enhancement affects existing commands. It required a team member (Valerie) and I to decide on a
  design option to accommodate for date ranges as she also needed it for her delete command. The implementation required
  some abstraction by making a new `Range` class along with `StartDate` and `EndDate`. One difficulty faced was on
  checking the validity of the dates, such as if the start date came after end date. Another difficulty faced was on
  deciding on whether to make it mandatory for both `sd/` and `ed/` to be present. We ended up deciding only requiring
  one of the prefix for the function to operate, by taking all dates after the given start date to be filtered, or all
  dates before the given end date to be filtered.

* **New Feature**: Added the ability to find by age ranges to find people in the dengue case list.
  * What it does: Allows the user to find cases by a specified age range using the prefixes `sa/` and `ea/`.
  * Justification: This feature improves the product significantly because an NEA staff may realistically require to
    search for cases that happened in a specific age range to use for data analytics.
  * Highlights: This enhancement affects existing commands. I had to decide on a design option to accommodate for age
  ranges. I re-used the code written for the date ranges made with a teammate (Valerie). The implementation required
  the abstraction used from making new `Range` class, along with two new classes, `StartAge` and `EndAge`. One
  difficulty faced was on checking the validity of the ages, such as if the start date came after end date. Another
  difficulty faced was on deciding on whether to make it mandatory for both `sa/` and `ea/` to be present. We ended up
  deciding only requiring one of the prefix for the function to operate, by taking all dates after the given start date
  to be filtered, or all dates before the given end date to be filtered.

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=Tohtoroo)

* **Project Management:**
  * Managed pull request reviews for `v1.3` - `v1.4` (2 milestones) on GitHub.
  * Handled bug/smoke testing for `v1.3` - `v1.4`.
  * Facilitated task assignment to be completed before milestone deadlines.

* **Enhancements implemented:**
    * Added lower case allowance for `variant` names in `edit`, `add` and `find`.
    ([#108](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/108))
    * Abstracted predicates to check for whether each prefix applies to a `Person`, splitting `FindPredicate` into
    further abstracted `PersonContainsAgePredicate`, `PersonContainsDatePredicate`, `PersonContainsNamePredicate`,
    `PersonContainsPostalPrediate`, `PersonContainsVariantsPredicate` and `RangeContainsPersonPredicate`.
    ([#120](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/120))
    * Abstracted `ParserUtil` further into `PredicateUtil`, which is used in each of the above predicates.
    ([#120](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/120))
    * Edited the help window to contain more than a link to the User Guide.
    ([#66](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/66))
    * Edited the help window's UI to be more appealing and appropriate to our application.
    ([#156](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/156))
    * Edited the help window's application icon.
    ([#156](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/156))
    * Edited `find` to match all inputs given instead of any matching to better match the functionality of our
    application.
    ([#120](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/120))
    * Edited `find` to allow for spaces in the name.
    ([#128](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/128))


* **Contributions to the UG:**
    * Added documentation for the prefix `find`, `find`-by-date-range, `find`-by-age-range.
    ([#151](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/151))
    * Added more pictures for each of the functions. ([#171](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/171))


* **Contributions to the DG:**
    * Added UML sequence diagram for prefix `find` command.
    ([#104](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/104))
    * Added UML activity diagram for prefix `find` command.
    ([#104](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/104))


* **Community:**
  * PRs reviewed (with non-trivial review comments):
    * [#96](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/96),
    [#126](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/126),
    [#126](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/126),
    [#148](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/148),
    [#164](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/164),
    [#250](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/250),
    [#251](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/251),
    [#258](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/258),
    [#266](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/266),
    [#272](https://github.com/AY2223S2-CS2103-W17-2/tp/pull/272)
