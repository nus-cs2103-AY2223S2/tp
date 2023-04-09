---
layout: page
title: Niu Boqian's Project Portfolio Page
---

### Project: TutorPro

TutorPro is a desktop app designed to help private tutors manage their student information effectively. With TutorPro, tutors can easily keep track of their students' addresses, contact details, lessons, homework, and progress, all in one place. Its ability to keep track of a student's lessons and exams makes it especially useful for tutors to plan their lessons to prepare his/her students for their exams.

Given below are my contributions to the project.

* **New Feature**: Lesson Feature and Lesson Related commands (new-lesson, view-lesson, delete-lesson, update-lesson)
  * What it does: Allows the user to add, view, delete, and update lessons for a student.
  * Justification: This feature improves the product significantly because a tutor can manage the lessons of a student more efficiently.
  * Highlights: 
    * The commands are simple to use. The view-lesson command is especially flexible as the user can filter by date, name of student, subject, or any combination of these.
    * I created a storage for the lesson list, so that now each json file of a student will have a lesson list.
    * I created relevant helper classes to help with the implementation of the lesson feature. This include various predicate and exception classes.
    * I create test classes for various classes in the lesson feature.
  * Credits: N/A

* **New Feature**: Exam Related Commands (view-exam, delete-exam, update-exam)
  * What it does: Allows the user to view, delete, and update exams for a student.
  * Justification: This feature improves the product significantly because a tutor can be aware of the exams of a student and can plan the lessons accordingly.
  * Highlights: 
    * The commands are simple to use. The view-exam command is especially flexible as the user can filter by date, name of student, exam name, or any combination of these.
    * I created a storage for the exam list, so that now each json file of a student will have a exam list.
    * I created relevant helper classes to help with the implementation of the exam feature. This include various predicate and exception classes.
  * Credits: N/A


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=nbqian&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Created and assigned issues to team members on GitHub and kept track of their progress
    * Created labels and categorized issues on GitHub
* **Enhancements to existing features**:
    * Changed the find, edit, delete command such that they now require the user to use prefixes to specify the fields to search for, edit, or delete
    * Changed the add command such that when a new student is created, his/her name cannot be part of any existing students' names, and vice versa.
    * Added lesson unique lists in teh application, added, and updated relevant methods in a logical model, storage, and other classes ot fit the change.
* **Documentation**:
  * User Guide:
    * Added documentation for the features `new-lesson`, `view-lesson`, `delete-lesson`, `update-lesson`, `new-exam`, `view-exam`, `delete-exam`, `update-exam`: [#184](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/184)
    * Added Glossary, List of Commands, and List of Prefixes as 3 separate tables: [#117](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/117)
  * Developer Guide:
    * Added implementation for `CreateLessonCommand` [#89](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/89)
    * Added Glossary and changed the table of contents [#198](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/198)
  
* **Community**:
  * Reported bugs and suggestions for other teams in the class:
    [#1](https://github.com/NBQian/ped/issues/1)
    [#2](https://github.com/NBQian/ped/issues/2)
    [#3](https://github.com/NBQian/ped/issues/3)
    [#4](https://github.com/NBQian/ped/issues/4)
    [#5](https://github.com/NBQian/ped/issues/5)
    [#6](https://github.com/NBQian/ped/issues/6)
    [#7](https://github.com/NBQian/ped/issues/7)
    [#8](https://github.com/NBQian/ped/issues/8)
* **Tools**:
* Use PlantUML to add more UML diagrams in the developer guide.
