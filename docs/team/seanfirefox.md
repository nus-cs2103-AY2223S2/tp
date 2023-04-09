---
layout: page
title: Tan Li Thai Sean's Project Portfolio Page
---

### Project: EduMate

EduMate is a desktop address book application used to help NUS students maintain both their social and academic life by lowering the barriers to meet up and also make new friends within modules and school. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 30 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=seanfirefox&breakdown=true)

* **New Feature**: Map the UP and DOWN keys to set the [previous commands](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/model/EduMateHistory.java) typed by the user into the text field.
  * Justification: It allows the user to access previously typed commands quickly and edit over. This enhances User Experience and the idea came from the Command Line Interface (CLI).
* **Enhancement**: [Find command](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/logic/commands/FindCommand.java) extension to find by all types of information and searches by substring.
  * Justification: The existing find command only searches by the full name. Extending the feature allows the user to search with partial information in case the user does not remember the full details of the contact.
* **Enhancement**: Collaborated with [@zichen-3974](https://ay2223s2-cs2103t-w14-2.github.io/tp/team/zichen-3974.html) on parts of the [UI](https://github.com/AY2223S2-CS2103T-W14-2/tp/tree/master/src/main/java/seedu/address/ui) for organise, meet and help command ([#205](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/205), [#212](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/212), [#227](https://github.com/AY2223S2-CS2103T-W14-2/tp/pull/227)).

* **Documentation**:
  * User Guide:
    * Pushed the first version of the User Guide that is drafted by the whole team.
    * Updated User Guide for Find command and Key Maps.
  * Developer Guide:
    * Updated Developer Guide Find command and Key Maps.
    * Created activity diagrams for the above two.

* **Testing**:
  * UI tests:
    * Spearhead the integration of [AddressBook Level 4 (AB4)](https://github.com/se-edu/addressbook-level4) GUI tests into EduMate.
      * Highlight: [GUI Handles](https://github.com/AY2223S2-CS2103T-W14-2/tp/tree/master/src/test/java/guitests) and [GUI test cases](https://github.com/AY2223S2-CS2103T-W14-2/tp/tree/master/src/test/java/seedu/address/ui) can be accessed here.
      * Highlight: Faced problems with [`build.gradle`](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/build.gradle) and shared the fixes on [#350](https://github.com/nus-cs2103-AY2223S2/forum/issues/350).

* **Community**:
  * [PRs Authored](https://github.com/AY2223S2-CS2103T-W14-2/tp/pulls?q=is%3Apr+author%3Aseanfirefox)
  * [PRs reviewed](https://github.com/AY2223S2-CS2103T-W14-2/tp/pulls?q=is%3Apr+commenter%3Aseanfirefox+-author%3Aseanfirefox)
  * [Issues and Bugs Reported](https://github.com/AY2223S2-CS2103T-W14-2/tp/issues?q=is%3Aissue+author%3Aseanfirefox)
  * [Forum Activity](https://github.com/nus-cs2103-AY2223S2/forum/issues?q=is%3Aissue+commenter%3Aseanfirefox)

* **Tools**:
  * Java 11, JavaFX, TestFX, JUnit, Gradle, CSS, Joda-TIme

