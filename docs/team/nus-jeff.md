---
layout: page
title: Jeff Lee's Project Portfolio Page
---

### Project: SOCket

SOCket is a desktop app for NUS Software Engineering Students to manage the contact information of their peers and professors.
It manages it by using CLI while still having a GUI, easy on the eyes yet faster management through typing.

Given below are my contributions to the project.

* **New Feature**: `addpj` command [\#165](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/165)
  * What it does: Allows users to add projects into SOCket
  * Justification: Users can track project details, meeting date and due date in SOCket at the same time
  * Highlights: Users can now add projects and manage them in SOCket along with their contacts.
  * Credits: *{-}*

* **New Feature**: `assign` command [\#175](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/175)
  * What it does: Allows users to assign persons to projects into SOCket
  * Justification: A project will definitely contain at least one member, and can increase in number of members at any time
  * Highlights: Simple assigning from the persons contact list to project list through the use of their index numbers
  * Credits: *{-}*

* **New Feature**: Child classes of `Predicate<Person>` class for address, email, phone & profile field [\#100](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/100)
  * What it does: Checks if a given person's address/email/phone/profile field matches any of the keywords given
  * Justification: The newest implementation of `find` command works on all field but there is only a child class of `Predicate<Person>` class for name field
  * Highlights: The new child classes can now check a list of keyword if any of it matches the value of the address, email, phone & profile field of a person
  * Credits: *{-}*

* **New Feature**: Child classes of `Predicate<Person>` class for tag/language field [\#100](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/100)
  * What it does: Checks if a given person's tag/language field matches any of the keywords given
  * Justification: The newest implementation of `find` command works on all field but there is only a child class of `Predicate<Person>` class for name field. Moreover, tag & language fields can contain more than 1 value unlike the other fields in person.
  * Highlights: The new child classes can now check on all values of tag/language if a list of keyword contains any matches
  * Credits: *{-}*

* **New Feature**: Child class of `Predicate<Person>` class for person [\#100](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/100)
  * What it does: Checks if a given person's field matches any of the keywords given for that respective field
  * Justification: The `updateFilteredPersonList` for `ModelManager` class only accepts 1 `Predicate<Person>` class, however the newest implementation of `find` command works on all field, we cannot give the method multiple predicates to filter.
  * Highlights: Predicates for all fields of a person is being encapsulated by just 1 person predicate class, can be passed into `updateFilteredPersonList` without heavily modifying all other existing codes. As long as one of the field predicate returns `true`, it will return `true`
  * Credits: *{-}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=nus-jeff&breakdown=true)

* **Enhancements to existing features**:
  * Updated `find` command to be able to search on any field of Persons, instead of only the name field [\#100](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/100)
  * Added `convertArgumentsIntoList` method in `StringUtil.java` to abstract out the need to break arguments in a list of words, reducing the need to repeat the same line of codes throughout the entire codebase. [\#100](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/100)
  * Refactored predicate classes name and location to remove any confusion caused due to similar naming [\#122](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/122)
  * Refactored test case names and its location for the predicate classes to match with the actual predicate class location in main [\#198](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/198) 
  * Updated `ParserUtilTest.java` to include test cases for project fields [\#199](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/199)

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `find` [\#63](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/63), [\#109](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/109)
    * Added documentation for the feature `addpj` & `assign` [\#185](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/185)
    * Modified UG to ensure consistency in wordings and grammar format [\#186](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/186)
    * Modified both introduction & features description to be more user-centric, added tips/warning to some features [\#263](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/263) 
  * Developer Guide:
    * Added use cases `UC05`, `UC11`, `UC14`, `UC20` & `UC22` [\#70](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/70), [\#278](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/278)
    * Added implementation of `find` [\#166](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/166)
    * Added sequence diagram for `find` [\#267](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/267)
    * Added activity diagram for the logic section on how the logic component parses a user input [\#267](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/267)
    * Added manual test case for `find`, `addpj` & `assign` [\#267](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/267)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#75](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/75), [\#76](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/76), [\#82](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/82), [\#127](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/127), [\#128](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/128), [\#196](https://github.com/AY2223S2-CS2103T-T12-4/tp/pull/196)
  * Reported bugs and suggestions for other teams in the class (examples: [\#94](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/94), [\#99](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/99), [\#108](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/108), [\#112](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/112), [\#123](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/123), [\#131](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/131), [\#134](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/134), [\#135](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/135), [\#136](https://github.com/AY2223S2-CS2103-F11-4/tp/issues/136))

