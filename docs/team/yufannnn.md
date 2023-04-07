---
layout: page
title: Zhu Yufan's Project Portfolio Page
---

### Project: TutorPro

TutorPro is a desktop app designed to help private tutors manage their student information effectively. With TutorPro, tutors can easily keep track of their students' addresses, contact details, lessons, homework, and progress, all in one place. This app is optimised for use via a Graphical User Interface (GUI), allowing tutors to interact with the app using easy-to-understand buttons and menus. However, TutorPro also provides a Command Line Interface (CLI) for those who prefer a faster way of getting things done. Whether you're managing a handful of students or hundreds, TutorPro can help you streamline your workflow and make your tutoring experience more efficient.

Given below are my contributions to the project.

*  **Idea**: Come up with the idea of enhancing AB3 to an student management application for private tutors.

*  **New Feature**: Homework Feature and Homework Related commands (new-homework, view-homework, delete-homework, make-homework, unmark-homework, and update-homework commands)
    - what it does: The Homework Feature and Homework related commands are designed to help tutors manage their student homework tasks more efficiently. It allows tutors to create, view, update, and delete homework tasks using a set of commands. The new commands include:
    
      1. `new-homework` - Creates a new homework task with a given deadline and description.
    
      2. `view-homework` - Displays a list of all the pending homework tasks.
    
      3. `delete-homework` - Deletes a specific homework task from the list.
    
      4. `make-homework` - Marks a homework task as completed.
    
      5. `unmark-homework` - Marks a completed homework task as pending.
    
      6. `update-homework` - Updates the deadline or description of a specific homework task.
    
    - Justification: The Homework Feature is aimed at addressing the common problem of managing multiple homework tasks for private tutors. With this feature, users can easily keep track of their students assignments and deadlines, prioritize their work, and avoid missing important submission dates.
    
    - Highlights: The Homework Feature is easy to use and can be accessed through a set of simple commands. The feature helps tutors stay organized and on top of the students' homework tasks, reducing the risk of missing important deadlines.
    
    - Credit: N/A
    
*  **New Feature**: Redesigned GUI and Detailed Information Section
- what it does: The new feature redesigns the UI of the application and adds a Detailed Information Section that provides more specific information about each student. The Detailed Information Section includes a Profile Page, a Homework Page, a Lessons Page, and an Exams Page. Each page is accessible through quick access buttons on the student card.
    
- Justification: The redesigned UI and Detailed Information Section are aimed at improving the user experience of the application, making the application more aesthetically appealing. It provides a more user-friendly interface for tutors to manage their students' information and allows for more efficient navigation between different types of information.
    
- Highlights: The Detailed Information Section provides more specific information about each student, including their personal information, homework assignments, past and upcoming lessons, and past and upcoming exams. 
    
- Credit: N/A


* **New Feature**: New Feature: Quick Access Button on Each Student Card
- What it does: The Quick Access Button is a new feature that provides a more user-friendly interface for tutors to access student-specific information. It adds four buttons on each student card, including profile, homework, lessons, and exams. By clicking on any of these buttons, the user can quickly access the corresponding information for the selected student.
    
- Justification: The Quick Access Button feature is aimed at improving the user experience for tutors who need to manage multiple students' profiles, homework, lessons, and exams. It helps to streamline the process of accessing student-specific information and eliminates the need for users to search through long lists to find the information they need.
    
- Highlights: The Quick Access Button is easy to use and provides users with quick access to the information they need. The feature is intuitive and visually appealing, making it easy for users to navigate through the app and access the relevant information.
    
- Credit: N/A
* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=Yufannnn&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=zoom&zA=Yufannnn&zR=AY2223S2-CS2103T-W13-4%2Ftp%5Bmaster%5D&zACS=247.67299412915852&zS=2023-02-17&zFS=&zU=2023-04-05&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)
*  **Project management**:
    * Set up Team Repo
    * Updated Workflow: [#1](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/1)
    * Created and assigned issues to team members on GitHub and kept track of their progress
    * Created labels and categorized issues on GitHub
    * Created and manages milestones on GitHub, changed their due dates, and closed them to wrap-up.
    * Managed releases `v1.3.Trail` on GitHub ([Link to v1.3 release](https://github.com/AY2223S2-CS2103T-W13-4/tp/releases/tag/v1.3.trial))
* **Enhancements to existing features**:

    * Refactor the Person model to Student model and added relevant methods in logic model, storage and other class to fit the change: [#1](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/1)
    * Added homework unique lists in the application, added, and updated relevant methods in logic, model, storage and other class to fit the changes: [#13](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/13)
    * Redesign the GUI to make it more aesthetically appealing and add more sections on the GUI: [#56](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/13)
* **Documentation**:
  * User Guide:
    * Updated the `Quick Start` section and update TOC:  [#170](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/170)
    * Added documentation for the new GUI I redesigned: [#124](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/124)
    * Added documentation for the new six homework command I created: [#170](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/170)
  * Developer Guide:
    * Added implementation for `Detailed Information Section and Quick Access Button`: [#80](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/80), [#81](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/81)
    * Updated `UI component Section`: [#80](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/80), [#81](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/81)
* **Community**:
  * PRs reviewed (with non-trivial review comments): 
    [#44](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/44),
    [#70](https://github.com/AY2223S2-CS2103T-W13-4/tp/pull/70).
  * Reported bugs and suggestions for other teams in the class:
    [#106](https://github.com/AY2223S2-CS2103T-W10-3/tp/issues/106),
    [#116](https://github.com/AY2223S2-CS2103T-W10-3/tp/issues/116), 
    [#118](https://github.com/AY2223S2-CS2103T-W10-3/tp/issues/118),
    [#120](https://github.com/AY2223S2-CS2103T-W10-3/tp/issues/120),
    [#123](https://github.com/AY2223S2-CS2103T-W10-3/tp/issues/123), 
    [#131](https://github.com/AY2223S2-CS2103T-W10-3/tp/issues/131).
  * Some manual testing was done to ensure that UI worked fine.
* **Tools**:
  * Used JavaFX and Scene Builder to modify the UI.
  * Used PlantUML to add more UML diagrams in the developer guide.
