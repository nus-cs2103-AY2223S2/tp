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
  * Justification: This feature serves as a safety net in case the user's data is lost to corruption or accidental behaviour. It also allows the user to practice their commands without fear of losing their progress.
  * Highlights: This enhancement required the refactoring of the [Command Result](https://github.com/AY2223S2-CS2103T-W14-2/tp/tree/master/src/main/java/seedu/address/logic/commands/results) class, to better support the exit, help, view, save and load commands.
* **New Feature**: Added the ability to sort the contacts in EduMate.
  * What it does: Allows the user to arrange contacts based on certain criteria. Users can chain together these comparators to give more sophisticated sorts.
  * Justification: This feature allows the user to quickly find contacts that they want, as this sort command will bring those contacts to the top of the list.
  * Highlights: This enhancement required the refactoring of the [Prefix](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/logic/parser/Prefix.java) class into an enumeration, as these sort keys need to be easily converted into descriptors (Email: Descending). In addition, the person fields needed to be comparable, necessitating the creation of the [Module Tag Set](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/model/person/ModuleTagSet.java) and the [Group Tag Set](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/model/person/GroupTagSet.java). Finally, the `FilteredList` of persons required an additional `SortedList` wrapper, in order to sort the person list under JavaFX standards.
* **New feature**: Added the ability to generate a sample EduMate.
  * What it does: Allows the user to refresh their EduMate with random contacts.
  * Justification: This feature allows the user to practice the other commands on a typical EduMate, rather than being forced to insert their own data.
  * Highlights: This feature required the modification of the [Sample Data Util](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/model/util/SampleDataUtil.java) to support random selection of contacts.
* **New Feature**: Added the ability to recommend meet, eat, and study locations and timings.
  * What it does: Recommends locations and timings to meet up with people based on their addresses and schedule.
  * Justification: This feature simplifies the process of organising meet ups with people by suggesting locations and times that are convenient for everyone.
  * Highlights: Firstly, the data for meet up locations ([libraries](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/resources/data/study.txt) and [malls](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/resources/data/eat.txt)) was scraped from the internet using Python. Then, the `Address` of the person was repurposed as [Station](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/model/person/Station.java), and uses [MRT](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/resources/data/stations.txt) data retrieved from LTA. Firstly, the code takes the timing suggestions from the [Timing Recommender](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/logic/recommender/timing/TimingRecommender.java). From there, the [Location Tracker](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/logic/recommender/location/LocationTracker.java) predicts the position of each person at each given time, which is then used by the [Location Recommender](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/logic/recommender/location/LocationRecommender.java) to suggest locations to meet up. Finally, the [Recommender](https://github.com/AY2223S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/address/logic/recommender/Recommender.java) collates this information and gives the most suitable meet ups.
* **Enhancement**: Integrated the module tags with the timetable.
  * What it does: Enables users to add and remove lessons to a person's schedule.
  * Justification: This enhancement allows the Recommender to suggest more suitable timings to meet up, since it now has access to the person's schedule.
  * 
