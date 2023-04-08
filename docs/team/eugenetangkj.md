---
layout: page
title: Eugene Tang's Project Portfolio Page
---

## About InternBuddy
InternBuddy is a desktop application for Computing undergraduates to manage their internship applications.
It is optimized for typing where it allows you to complete internship management tasks much more efficiently
via the keyboard as compared to using traditional Graphical User Interface (GUI) applications.

## Project Contributions
My code contributions can be found on 
[RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=eugenetangkj&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).

### Features and Enhancement
- **Refactored code to adapt the content of AB3 to the context of InternBuddy**
  [\#37](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/37)
  * Renamed classes and packages, such as from `Person` to `Internship`.
  * Renamed variables.
  * Redefined test cases to suit the context of internships instead of persons.

- **Redesigned the GUI of InternBuddy**
  [\#52](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/52),
  [\#78](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/78),
  [\#191](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/191)
  * Created a new look for the List Panel.
  * Implemented a new responsive View Panel for the viewing of internship information.

- **Implemented feature to add optional field,** `COMMENT`
  [\#79](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/79)
  * Users can now add an optional comment to an internship entry via the `add` command.
  * Editing of comments is made possible via the `edit` command.
  * If the user did not include a value for the comment, the comment will have a default value of `NA`.
  * Added test cases accordingly, such as in the class `CommentTest`.

<div style="page-break-after: always;"></div>

- **Implemented the** `view` **command**
  [\#78](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/78)
  * Users can now view the detailed information of a selected internship entry.
  * Details are displayed in the View Panel.
  * Apart from showing the internship information, I added a tips box that will appear
    below the internship information. The contents of the tips box will change depending
    on the status of the internship entry.
  * Added test cases accordingly, such as in the class `InternshipDetailsCardTest`.

- **Redesigned the Help Window**
  [\#81](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/81)
  [\#132](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/132)
  * Inserted the command summary and hyperlink to InternBuddy's user guide in the Help Window.
  * Created a new look for the Help Window.

- **Co-implemented GUI testing for InternBuddy**
  [\#52](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/52)
  * With code references from [AddressBook Level 4](https://github.com/se-edu/addressbook-level4)
    and [Please Hire Us](https://github.com/AY2223S1-CS2103T-W17-4/tp), I managed to implement
    test cases for UI components such as in the class `InternshipCardTest`.
  * Worked with my teammate, Christopher, to implement this.
  * GUI testing improved code coverage for InternBuddy.

### Documentation

- **Contributed to User Guide**
  [\#21](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/21),
  [\#34](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/34),
  [\#102](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/102),
  [\#109](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/109),
  [\#204](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/204)
  * Documented the explanation for the different parts in InternBuddy's GUI.
  * Wrote up the command information, explaining the format and constraints of commands and
    fields in InternBuddy.
  * Responsible for the write-up of the `list`, `add`, `view`, `help` and `exit` commands.
  * Created the appendices to explain to users how to install Java 11, manually edit the `internbuddy.json` file
    and populate InternBuddy with sample data.
- **Contributed to Developer Guide**
  [\#23](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/23),
  [\#82](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/82),
  [\#84](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/84),
  [\#86](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/86),
  [\#119](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/119)
  * Explained implementation and design considerations for `add` and `view` commands, supplementing the explanations with sequence diagrams.
  * Defined product scope by identifying InternBuddy's target audience and value proposition, as well as drafted user stories.
  * Added test cases for instructions on manual testing, such as for the `add` and `view` commands.

### Others
- Provided non-trivial PR reviews for
  [\#47](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/47),
  [\#80](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/80),
  [\#100](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/100),
  [\#201](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/201).
- Designed the logo for InternBuddy.
- Contributed to CS2103T forum discussions for
  [\#45](https://github.com/nus-cs2103-AY2223S2/forum/issues/45),
  [\#99](https://github.com/nus-cs2103-AY2223S2/forum/issues/99),
  [\#177](https://github.com/nus-cs2103-AY2223S2/forum/issues/177),
  [\#252](https://github.com/nus-cs2103-AY2223S2/forum/issues/252),
  [\#254](https://github.com/nus-cs2103-AY2223S2/forum/issues/254),
  [\#266](https://github.com/nus-cs2103-AY2223S2/forum/issues/266),
  [\#319](https://github.com/nus-cs2103-AY2223S2/forum/issues/319),
  [\#322](https://github.com/nus-cs2103-AY2223S2/forum/issues/322).

