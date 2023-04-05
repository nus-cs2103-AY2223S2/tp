---
layout: page
title: Christopher's Project Portfolio Page
---

### Project: InternBuddy

InternBuddy provides a 1-stop platform for Computing undergraduates to manage and track their internship applications. It is optimized for typing which Computing undergraduates are comfortable and proficient in, allowing them to fully and efficiently exploit the application’s organisational capabilities.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

## Project Contributions
My code contributions can be found on 
[RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=potty10&breakdown=true)


### Features and Enhancement

* **New Feature**: Refactored `delete` as `delete-index` command [#100](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/100)
  * What it does: Allows users to delete multiple internships by their indexes.
  * Justification: This feature allows the user to clear delete multiple internships from the list at once, instead of entering `delete` multiple times. Users can then
  keep their internship list nice and tidy.
* **New Feature**: Added `delete-field` command [#100](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/100)
  * What it does: Allows users to delete multiple internships by their fields.
  * Justification: Users may want to mass delete internships from the list when internship application season is over, but still want to archive some for future reference. It is an improvement over `clear`, which deletes all internship entries.

* **Co-implemented GUI testing for InternBuddy**
  [\#198](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/198),
  [\#57](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/57)
  * With code references from [AB4](https://github.com/se-edu/addressbook-level4)
    and [Please Hire Us](https://github.com/AY2223S1-CS2103T-W17-4/tp), I managed to implement
    test cases for UI components such as in the class `InternshipCardTest` and `CommandBoxTest`.
  * Worked with my teammate, Eugene, to implement this.
  * GUI testing improved code coverage for InternBuddy.

### Documentation
* **Contributed to About Us page**
  [\#18](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/18)
  * Collected my team photos and drafted the About Us page.

* **Project management**:
  * Managed release v1.3.2 on GitHub.
* **Enhancements to existing features**:
  * Added date labels to UI, so that users understand what the dates mean. [\#57](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/57), [\#138](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/138)
  * Added a feature where clicking on an internship entry updates the right panel with the selected internship. [\#150](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/150)
  * Refactored Status as a set of final constant strings, mitigating misuse of Status strings.  [\#57](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/57)
  * Fixed a bug where the right panel does not reset after clear. [\##128](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/128)
* **Documentation**:
  * User Guide:
    * Contributed to Introduction, Quick Start, Notes about Features, FAQ [\#26](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/26)
    * Contributed to implementation of `delete-index` command [\#121](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/121)
    * Contributed to implementation of `delete-field` command [\#121](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/121)
    * Fixed UG bugs from PE-D [#195](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/195)
  * Developer Guide:
    * Contributed to Acknowledgements, Non functional requirements and Glossary [\#31](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/31)
    * Contributed to Introduction and About Developer Guide [\#106](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/106)
    * Added implementation for `delete-field` command [#122](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/122)
    * Updated UML sequence diagram for `delete-field` command [#94](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/94)
    
* **Community**:
  * PRs reviewed (with non-trivial review comments): 
  [\#118](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/118),
  [\#58](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/58), 
  [\#32](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/32), 
  [\#30](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/30), 
  [\#20](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/20)
  * Contributed to forum discussions for 
  [\#297](https://github.com/nus-cs2103-AY2223S2/forum/issues/297), 
  [\#287](https://github.com/nus-cs2103-AY2223S2/forum/issues/287), 
  [\#190](https://github.com/nus-cs2103-AY2223S2/forum/issues/190)


