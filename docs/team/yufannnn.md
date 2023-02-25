---
layout: page
title: Zhu Yufan's Project Portfolio Page
---

### Project: TutorPro

TutorPro is a desktop app designed to help private tutors manage their student information effectively. With TutorPro, tutors can easily keep track of their students' addresses, contact details, lessons, homework, and progress, all in one place. This app is optimised for use via a Graphical User Interface (GUI), allowing tutors to interact with the app using easy-to-understand buttons and menus. However, TutorPro also provides a Command Line Interface (CLI) for those who prefer a faster way of getting things done. Whether you're managing a handful of students or hundreds, TutorPro can help you streamline your workflow and make your tutoring experience more efficient.

Given below are my contributions to the project.

* **New Feature**: Adds the ability to view a student's personal profile.
    
    - What it does: Enables users to view detailed information about a student, such as their home address and phone number, by clicking on the "view profile" button next to their name.
    - Justification: This feature provides users with quick access to a student's contact information, which can be essential for communication or other purposes.
    - Highlights: This feature required modifications to the existing codebase to add the "view profile" button and implement the functionality to display the student's contact information.
    - Credits: N/A
    
* **New Feature**: Adds the ability to assign and delete homework for a specific student.

    - What it does: Allows users to assign homework to a particular student, including details such as the title, description, and due date. It also provides the ability to delete homework assignments for a specific student.
    - Justification: This feature enables teachers or administrators to efficiently assign and manage homework for students, keeping track of their progress and deadlines.
    - Highlights: This feature required the creation of new classes, such as `CreateHomeworkCommand` and `DeleteHomeworkCommand`, to handle the assigning and deletion of homework assignments. It also required modifications to the existing codebase to display the assigned homework for each student and provide a way to filter homework by student name and status.
    - Credits: N/A

* **New Feature**: Adds the ability to mark a homework as done or undone.

    - What it does: Enables tutors to mark a specific student's homework as done or undone.
    - Justification: This feature enables teachers or administrators to efficiently track and manage student homework progress and deadlines.
    - Highlights: This feature required the creation of new classes, such as `MarkHomeworkAsDoneCommand` and `UnmarkHomeworkAsUndoCommand`, to handle the marking and unmarking of homework for a student.
    - Credits: N/A

* **New Feature**: Adds the ability to view a student's upcoming assignments.

    - What it does: Allows users to view a student's upcoming assignments by clicking on the "view tasks" button next to their name.
    - Justification: This feature enables teachers or administrators to efficiently plan lessons and assignments for students.
    - Highlights: This feature required modifications to the existing codebase to add the "view tasks" button and implement the functionality to display the student's upcoming assignments.
    - Credits: N/A

* **New Feature**: Adds the ability to view a list of homework with filtering by student name and homework status.

    - What it does: Displays a list of homework with the option to filter by student name and homework status.
    - Justification: This feature allows users to quickly and easily view all homework assignments or filter by specific students or status, making it easier to manage homework assignments.
    - Highlights: This feature required modifications to the existing codebase and implementation of filtering logic for student name and status.
    - Credits: N/A

* **Code contributed**:

* **Project management**:

    * Set up Team Repo
    * Updated Workflow

    * Create PR to the upstream Repo

* **Enhancements to existing features**:

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]
    * Added documentation for the introduction and description
  * Developer Guide:

* **Community**:
  * PRs reviewed (with non-trivial review comments): )
  * Contributed to forum discussions ()
  * Reported bugs and suggestions for other teams in the class ()
  * Some parts of the history feature I added was adopted by several other class mates ()

* **Tools**:

  
