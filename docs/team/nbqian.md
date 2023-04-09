---
layout: page
title: Niu Boqian's Project Portfolio Page
---

### Project: TutorPro

TutorPro is a desktop app designed to help private tutors manage their student information effectively. With TutorPro, tutors can easily keep track of their students' addresses, contact details, lessons, homework, and progress, all in one place. This app is optimised for use via a Graphical User Interface (GUI), allowing tutors to interact with the app using easy-to-understand buttons and menus. However, TutorPro also provides a Command Line Interface (CLI) for those who prefer a faster way of getting things done. Whether you're managing a handful of students or hundreds, TutorPro can help you streamline your workflow and make your tutoring experience more efficient.
Given below are my contributions to the project.

* **New Feature**: Adds the ability to add a lesson for a particular student (new-lesson)
    - What it does: Allows user to add a lesson to a particular student, including details such as the title of the lesson, it's start time, and it's end time.
    - Justification: This feature enables tutors to effectively plan future lessons for a particular student, keeping track of when he should be teaching which student.
    - Highlights: This feature required the new class `CreateLessonCommand` and various modifications to handle the addition of lessons.
    -Credits: N/A


* **New Feature**: Adds the ability to view a student's lessons (view-lessons)
    - What it does: Enables users to view lessons of students by typing in the command `view-lessons`, with parameters such as the student's name and the lesson's title, lesson name, date of lesson, and whether the lesson has been completed or not.
    - Justification: This feature provides users with quick access to lessons planned for a student so what the tutor can know how many lessons he has taught the student, how many upcoming lessons there are, and when is the next lesson going to take place, so that he can better plan his schedule.
    - Highlights: This feature required modification to the existing codebase to add the `ViewLessonCommand` command and implement the functionality to display the lessons associated with a student. The command is highly flexible, allowing users to view lessons by student name, lesson title, lesson name, date of lesson, and whether the lesson has been completed or not. Each of these fields can be left blank, in which case the command will display all lessons associated with all students.
    - Credits: N/A


* **New Feature**: Adds the ability to delete a student's lessons (delete-lessons)
  - What it does: Enables users to delete a student's lessons by typing in the command `delete-lesson`, with index of the lesson to be deleted.
  - Justification: This feature allows users to delete lessons planned for a student so that they can remove lessons that have already taken place or that they no longer need to plan for.
  - Highlights: This feature required modification to the existing codebase to add the `DeleteLessonCommand` command and implement the functionality to delete lessons associated with a student.
  - Credits: N/A


* **New Feature**: Adds the ability to update a student's lessons' details (update-lessons)
  - What it does: Enables users to update a student's lessons' details by typing in the command `update-lesson`, with parameters such as the student's name and the updated lesson's title, lesson name, start time, and end time.
  - Justification: This feature allows users to update lessons planned for a student so that they can modify the details of a lesson without needing to delete and re-add it.
  - Highlights: This feature required modification to the existing codebase to add the `UpdateLessonCommand` command and implement the functionality to update the lessons associated with a student.
  - Credits: N/A


* **New Feature**: Adds the ability to add an exam for a particular student (new-exam)
  - What it does: Allows user to add an exam to a particular student, including details such as the title of the exam, it's start time, and it's end time.
  - Justification: This feature enables tutors to effectively plan future lessons for a particular student based on what exams the student needs to prepare for.
  - Highlights: This feature required the new class `CreateExamCommand` and various modifications to handle the addition of exams.
    -Credits: N/A


* **New Feature**: Adds the ability to view a student's exams (view-exams)
  - What it does: Enables users to view all exams planned for a student by using the `view-exam` command.
  - Justification: This feature provides users with quick access to exams planned for a student so what the tutor can know how many exams a student needs to prepare for, and and plan his lessons to prepare the student for the exams.
  - Highlights: This feature required modification to the existing codebase to add the `ViewExamCommand` command and implement the functionality to display the exams associated with a student. The command is highly flexible, allowing users to view exams by student name, exam title, exam name, date of exam, and whether the exam has been completed or not. Each of these fields can be left blank, in which case the command will display all exams associated with all students.
  - Credits: N/A


* **New Feature**: Adds the ability to delete a student's exams (delete-exams)
  - What it does: Enables users to delete a student's exams by typing in the command `delete-exam`, with index of the exam to be deleted.
  - Justification: This feature allows users to delete exams planned for a student so that they can remove exams that have already taken place or that they no longer need to plan for.
  - Highlights: This feature required modification to the existing codebase to add the `DeleteExamCommand` command and implement the functionality to delete exams associated with a student.
  - Credits: N/A


* **New Feature**: Adds the ability to update a student's exams' details (update-exams)
  - What it does: Enables users to update a student's exams' details by typing in the command `update-exam`, with parameters such as the exam's title, start time, and end time.
  - Justification: This feature allows users to update exams planned for a student so that they can modify the details of an exam without needing to delete and re-add it.
  - Highlights: This feature required modification to the existing codebase to add the `UpdateExamCommand` command and implement the functionality to update the exams associated with a student.
  - Credits: N/A


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=nbqian&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Created and assigned issues to team members on GitHub and kept track of their progress
    * Created labels and categorized issues on GitHub
* **Enhancements to existing features**:
    * Changed the find, edit, delete command such that they now require the user to use prefixes to specify the fields to search for, edit, or delete
    * Added lesson unique lists in teh application, added, and updated relevant methods in a logical model, storage, and other classes ot fit the change.
* **Documentation**:
  * User Guide:
    * Added documentation for the features `new-lesson`, `view-lesson`, `delete-lesson`, `update-lesson`, `new-exam`, `view-exam`, `delete-exam`, `update-exam`: [#184](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/184)
    * Added Glossary, List of Commands, and List of Prefixes as 3 separate tables: [#117](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/117)
  * Developer Guide:
    * Added implementation for `CreateLessonCommand` [#89](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/89)
  
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
