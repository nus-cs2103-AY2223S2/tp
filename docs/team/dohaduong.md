---
layout: page
title: Do Ha Duong's Project Portfolio Page
---

### Project: Duke Driver

Duke Driver is a task and contact management app that aims to help busy delivery men. It is built by implementing CLI and GUI, helping to enhance users' experience.

Given below are my contributions to the project.

* **New Feature**: Added the ability to view timetable of specific week
  * What it does: Displays timetable of the week specified by users using commands. Alternatively, users can simply click on corresponding button.
  * Justification: This feature is implemented to help users to structure their plans in the day - they can view upcoming tasks more easily. It is linked with the delivery job list in the app.
  * Highlight: This feature was challenging at first to implement as I have to decide on the design and GUI of the timetable myself - JavaFX did not have any integrated timetable format. Moreover, as this enhancement is directly linked with job list, it could affect existing commands and commands to be added in the future. It also requires me to change the existing commands and sort the job list.

* **New Feature**: Added the ability to view list of completed jobs and jobs with invalid date and/or slot
  * What it does: Allows users to view list of completed jobs and jobs with invalid date and/or slot using the corresponding commands or click on buttons (GUI).
  * Justification: This features improves the product and enhances the timetable feature significantly, as completed jobs and those with invalid dates/slots are not shown in the Timetable. Thus, if users want to view list of completed jobs and unscheduled jobs, they can refer to this feature instead.
  * Highlight: This feature is directly linked with job list, thus changes made in the existing functions could affect the features directly. Moreover, the implementation was also challenging as it required changes to the existing commands. 

* **New Feature**: Added the ability to add jobs with empty delivery date and/or slot
    * What it does: Allows users to add jobs without specifying its delivery date and slot (making delivery date and slot optional), making the `add_job` command much shorter.
    * Justification: This features improves the product as the `add_job` command is quite long (with compulsory delivery date and slot), and users may want to add jobs without specifying its delivery date and timeslot. Thus, the app should allow delivery date and timeslot to be optional.
    * Highlight: This feature is directly linked with job list and with the existing command/functions, thus it required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands, including methods on how to import/read the existing data and add_job command.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dohaduong&breakdown=true)

* **Project management**:
    * Managed releases v1.2 and v1.3.trial (2 releases) on GitHub
    * Update release v1.3 with description on GitHub
    * Create tags and labels for issues/PRs on GitHub

* **Enhancements to existing features**:
    * Wrote tests for existing features, such as: all Timetable commands, reminder commands (`add_reminder`, `delete_reminder`, `list_reminder`), jobs commands (`com_job/uncom_job`, `delete_job`,..), etc. (PR #189, #260)
    * Added and updated JavaDoc comments for public methods (PR #165)
    * Updated to make feedbacks and command instructions clearer to users (provide examples on how to use the commands in the app) (PR #194)
    * Added ability to navigate to Help Window from different windows (PR #189)

* **Documentation**:
    * User Guide:
        * Added _How to use this Guide?_ and _Windows and Features Overview_ sections (PR #253)
        * Added documentation for Timetable commands (PR #184)
        * Added and updated documentation for Delivery Job commands (PR #184, #253)
        * Added documentation for Job Detail (UI) in Main Window (PR #254)
        * Update UG summary timetable and Table of Content (PR #198, #254)
        * Added examples for commands in UG (PR #198)
        * Re-format, touch up, fix minor bugs and finalize UG (PR #200, #205, #260)
      
    * Developer Guide:
        * Added implementation details and diagrams for `UI component`, `Timetable` and `delete_job` (PR #128, #270)
        * Added user stories for timetable, reminder; use cases for timetable and glossary (PR #20, #23, #34, #270)
        * Added Appendix: Effort (PR #270)

* **Community**:
    * PRs reviewed (with non-trivial review comments): #180, #156, #264 
    * Identified bugs in commands and brought up discussion among the group in weekly meeting
    * Contributed to forum discussions (examples: #24)

* **Tools**:
   * N.A.


