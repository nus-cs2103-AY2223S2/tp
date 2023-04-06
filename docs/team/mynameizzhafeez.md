---
layout: page
title: Izz Hafeez's Project Portfolio Page
---

## Project: EduMate

EduMate is a desktop application designed for NUS students to manage their academic and social lives. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 30 kLoC.

## Contributions to project

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=mynameizzhafeez&breakdown=true)

* **New Feature**: Added the ability to save/load EduMate to/from a data file.
  * What it does: Allows the user to save EduMate into a file of their choice. The user can then load it back into EduMate.
  * Justification: This feature serves as a safety net in case the user's progress is lost to corruption or accidental behaviour.
  * Highlights: This enhancement required the refactoring of the [Command Result](https://github.com/AY2223S2-CS2103T-W14-2/tp/tree/master/src/main/java/seedu/address/logic/commands/results) class, to better support the exit, help, view, save and load commands.
* **New Feature**: Added the ability to sort the contacts in EduMate.
  * What it does: Allows the user to arrange contacts based on certain criteria. Users can chain together these comparators to give more sophisticated sorts.
  * Justification: This feature allows the user to quickly find contacts that they want, as this sort command will bring those contacts to the top of the list.
  * Highlights: This enhancement required the refactoring of the [Prefix](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/logic/parser/Prefix.java) class into an enumeration, as these sort keys need to be easily converted into descriptors (Email: Descending). In addition, the person fields needed to be made comparable, necessitating the creation of the [Module Tag Set](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/model/person/ModuleTagSet.java) and the [Group Tag Set](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/model/person/GroupTagSet.java).
* **New feature**: Added the ability to generate a sample EduMate.
  * What it does: Allows the user to refresh their EduMate with random contacts.
  * Justification: This feature 
