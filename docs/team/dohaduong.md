---
layout: page
title: Do Ha Duong's Project Portfolio Page
---

### Project: Duke Driver

Duke Driver is a task and contact management app that aims to help busy delivery men. Duke Driver is built by implementing CLI and GUI, helping to enhance users' experience.

Given below are my contributions to the project.

* **New Feature**: Added the ability to view timetable of specific week
  * What it does: Displays timetable of the week specified by users using command `timetable` or `timetable_date`. Alternatively, users can simply click on Timetable button.
  * Justification: This feature is implemented to help users to structure their plans in the day - they can view what needs to be done more easily. It is linked with the delivery job list in the app.
  * Highlight: This feature was challenging at first to implement as I have to decide on the design, GUI and how to display the timetable myself - JavaFX did not have any integrated timetable format. Moreover, as this enhancement is directly linked with job list, it could affect existing commands and commands to be added in the future. It requires me to change the existing commands and sort the job list, thus the implementation too was challenging.
  * Credits: N.A.

* **New Feature**: Added the ability to view list of completed jobs and jobs with invalid date and/or slot
  * What it does: Allows users to view list of completed jobs and jobs with invalid date and/or slot using the corresponding commands or click on buttons (GUI).
  * Justification: This features improves the product and enhances the timetable feature significantly, as completed jobs and those with invalid dates/slots are not shown in the Timetable. Thus, if users want to view list of completed jobs and unscheduled jobs, they can refer to this feature as well as the Timetable feature.
  * Highlight: This feature is directly linked with job list, thus changes made in the existing functions could affect the features directly. Moreover, the implementation was also challenging as it required changes to the existing commands. 
  * Credits: N.A.

* **New Feature**: Added the ability to add jobs with empty delivery date and/or slot
    * What it does: Allows users to add jobs without specifying its delivery date and slot (making delivery date and slot optional), making the `add_job` command much shorter.
    * Justification: This features improves the product as the `add_job` command is quite long (with compulsory delivery date and slot), and users may want to add jobs without specifying its delivery date and timeslot. Thus, the app should allow delivery date and timeslot to be optional.
    * Highlight: This feature is directly linked with job list and with the existing command/functions, thus it required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands, including methods on how to import/read the existing data and add_job command.
    * Credits: N.A.



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dohaduong&breakdown=true)

* **Project management**:
    * Managed releases v1.2 and v1.3.trial (2 releases) on GitHub
    * Update release v1.3 with description on GitHub
    * Create tags and labels for issues/PRs on GitHub

* **Enhancements to existing features**:
    * Wrote tests for existing features, such as: all Timetable commands (TimetableDateCommand, TimetableCompletedCommand, TimetableCommand, TimetableUnscheduledCommand), reminder commands (AddReminderCommand, DeleteReminderCommand, ListReminderCommand), jobs commands (CompleteDeliveryJobCommand, DeleteDeliveryJobCommand),... (PR #189, #260)
    * Added and updated JavaDoc comments for public methods (PR #165)
    * Updated to make feedbacks and command instructions clearer to users (i.e. provide examples on how to use the commands) (PR #194)
    * Added ability to navigate to Help Window from Timetable Window and Customer Window (PR #189)

* **Documentation**:
    * User Guide:
        * Added auto-updated Table of Content (PR #254)
        * Added "How to use this Guide?"" and "Windows and Features Overview" sections (PR #253)
        * Added documentation for `timetable`, `timetable_date`, `timetable_completed`, `timetable_unscheduled` (PR #184)
        * Added and updated documentation for `add_job`, `find_job`, `delete_job`, `com_job`, `uncom_job` (PR #184, #253)
        * Added documentation for Job Detail (UI) in Main Window (PR #254)
        * Update UG summary timetable (PR #198)
        * Added examples for commands in UG (PR #198)
        * Re-format, touch up, fix minor bugs and finalize UG (PR #200, #205, #260)
      
    * Developer Guide:
        * Added implementation details for Timetable functions (PR #128)
        * Added user stories, use cases and glossary (PR #20, #23, #34)

* **Community**:
    * PRs reviewed (with non-trivial review comments): #180, #156 
    * Identified bugs in commands and brought up discussion among the group in weekly meeting
    * Contributed to forum discussions (examples: #24)

* **Tools**:
   * N.A.


