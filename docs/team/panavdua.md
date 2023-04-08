# Panav's Project Portfolio Page

## Overview

***ExecutivePro*** is a desktop app for Human Resource managers to manage their employee information, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, EP can get your employee management tasks done **faster** than traditional GUI apps.


### Code Contributed:
- [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=panavdua&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

**Enhancements implemented :**
- Improved `find` command to have functionality to choose between _normal search_
  (return all employees whose details match any part of **any** inputted keywords) and _advanced search_
  (return all employees whose details match any part of **all** inputted keywords).
- Improved `find` command to have functionality to search employees based on the departments (Finance, Marketing etc.)
in addition to searching based on their full name.
- Created `filter` command from scratch which filters out all the employees satisfying the inputted criteria. Currently,
the criteria can check whether the number of leaves or the payroll of an employee is greater than/equal to/
lesser than the inputted value.
- Came up with new `Predicate` classes to satisfy the different criterias which could be used for the `find`
and `filter` command, such as `FilterByPayrollPredicate`, `NameContainsAllKeywords` etc.

**Contributions to the UG :**
- Added the instructions for the users to understand the usage of `find` and `filter` command along with relevant screenshots and examples.
- Added the `find` and `filter` commands to other sections of the UG concerned with the commands such as the _Command Summary_ section.


**Contributions to the DG :**
- Added the _Implementation_ of `find` and `filter` commands including the internal working on the function,and it's sequence diagrams.
- Added the _Glossary_ section.
- Added multiple _Use Cases_.
- Helped adapt the DG from the previous _AddressBook_ implementation to our _ExecutivePro_ implementation.
- Wrote the introductory sections about the target audience and purpose of the DG.

**Contributions to team-based tasks :**
- Played active role in the brainstorming sessions to come up with new features.
- Helped to create and distribute issues.
- Write tests for the features I implemented (`find`,`filter` command).
- Contributed to manual testing of application to find bugs.
- Added custom messages in the `Messages` class for the team to use.
- Helped with the sequence diagrams and adapting them to _ExecutivePro_ from the previous _AddressBook_ implementation.
- Implemented utility functions related to `String` and `Parser` class, for everyone's use.
- Helped fix general bugs received after the Practical Exam dry-run.

**Review/mentoring contributions :**
- Pull Requests reviewed and merged:
[#177](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/177),
[#168](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/168),
[#164](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/164),
[#155](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/155),
[#146](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/146),
[#137](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/137),
[#133](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/133),
[#113](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/113),
[#94](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/94),
[#79](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/79),
[#78](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/78),
[#59](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/59),
[#58](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/58),
[#51](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/51),
[#21](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/21),
[#19](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/19),
[#16](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/16),
[#13](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/13),
[#8](https://github.com/AY2223S2-CS2103T-W09-4/tp/pull/8)


**Contributions beyond the project team :**
- Reported numerous bugs for team [_CS2103T-W12-4(Medimeet)_](https://github.com/AY2223S2-CS2103T-W12-4/tp)
  Some examples are: [#136](https://github.com/AY2223S2-CS2103T-W12-4/tp/issues/136), [#128](https://github.com/AY2223S2-CS2103T-W12-4/tp/issues/128),
[#133](https://github.com/AY2223S2-CS2103T-W12-4/tp/issues/133)

