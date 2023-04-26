---
layout: page
title: Amos Hung's Project Portfolio Page
---

### Docédex
{:.no_toc}
Docédex is a **desktop application for managing doctors and patients within hospitals**, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you type fast, Docédex can get your patient management tasks done faster than traditional GUI apps.<br>

* Table of Contents
{:toc}

### Summary of Contributions

#### Code contributed:
My code contibutions can be viewed at [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=bobfree546&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Enhancements implemented:
- Created various Command classes (`FindDoctorCommand`, `FindPatientCommand`, `ListDoctorCommand`, `ListPatientCommand`, `AssignPatientCommand`, `UnassignPatientCommand`) and their respective Parser classes.
- Created various Model classes for filtering doctors and patients (`DoctorFilter`, `PatientFilter`, `DoctorContainsKeywordsPredicate`, `PatientContainsKeywordsPredicate`).
- Test cases for `FindDoctorCommand`, `FindPatientCommand`, `ListDoctorCommand`, `ListPatientCommand` and their parsers

#### Contributions to the User Guide:
- Added sections for the enhancements implemented [User Guide](../UserGuide.md#features)

#### Contributions to the Developer Guide:
- Added some sections in features
  - Implementation detailes of Find, Assign and Unassign features
  - UML diagrams: Sequence diagrams for the features above
- Added [Appendix H: Instructions for manual testing](../DeveloperGuide.md#appendix-h-instructions-for-manual-testing)

#### Contributions to team-based tasks:
- Brainstorming of user stories and use cases
- Planning of product design and features
- Manual testing of features prior to release
- PR reviews

#### Review/mentoring contributions:
Here are some PRs that I have reviewed
- Enable proper storing, loading and display of doctors [#80](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/80)
- Upon filtering doctors through find-doc or list-doc, UI updates the enlarged contact card [#99](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/99)
- Enable viewing of patients on UI [#125](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/125)
