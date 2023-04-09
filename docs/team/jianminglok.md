---
layout: page
title: Lok Jian Ming's Project Portfolio Page
---

### Project: InternEase

InternEase is a **powerful and innovative desktop app designed to streamline the internship application process** for **Computer Science undergraduates**. With its optimized **combination of a Command Line Interface (CLI) and Graphical User Interface (GUI)**, InternEase offers users the best of both worlds - the speed and efficiency of a CLI for those who can type quickly, and the user-friendly experience of a GUI for those who prefer a visual interface. InternEase is a good choice for you who wants to concentrate on your internship preparation by helping to manage your internship applications' data.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jianminglok&breakdown=true)

* **New Features**:
    * Manage the company contact of an internship application
      * What it does: Users are able to add, a company contact, which consists of both a phone and an email, to an internship application, and subsequently edit and remove it.
      * Justification: This feature allows the user, an internship applicant in this case to quickly retrieve the contact details of an application, so that he can make an enquiry or ask for an update on his application status, making his/her internship application journey even easier.
      * Highlights: The regular expression used for validating the email from the user input was updated, which was challenging in the sense that those available on the Internet were not always correct and have to be carefully modified and adapted.
      * Credits: Some parts of the code was reused and adapted from the [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).
    * Manage the documents of an internship application
      * What it does: Users are able to add links to documents consisting of a resume and a cover letter to an internship application, and edit and remove them afterwards.
      * Justification: This features allows users to retrieve the specific version of their resume and cover letter submitted for a particular application for reference before an interview.
      * Highlights: A URL validation feature had to be implemented to ensure that the links to the documents provided by the user are valid.
    * Edit the status of an internship application
      * What it does: Users are able to update the status of their application to one of the following: `PENDING`, `RECEIVED`, `ACCEPTED`, `DECLINED`, `REJECTED`.
      * Justification: This feature allows users to quickly identify the status of an application, so that they will not need to repeatedly check their email for the latest response, if any from the company.
      * Highlights: Enumerations are used to ensure that the status provided is valid.
    * Archive an internship application
      * What is does: Users are able to archive and unarchive an internship application. They can also view a list of archived applications.
      * Justification: This features allow users to focus on the currently ongoing internship applications, without having their list of applications getting cluttered up over time.
      * Highlights: New predicates were created to allow ongoing and archived internship applications to be listed separately.
* **Project management**:
    * Setup and added Codecov label for the team project

* **Enhancements to existing features**:
    * Updated the `list` feature to only show ongoing internship applications, separating out the archived ones in [#138](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/138).
    * Added implementation of the `ViewContentPanel` class as part of our GUI enhancement in [#165](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/165).
    * Updated the RegEx (regular expression) for validating email addresses in [#266](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/266).
    * Credits: Some parts of the code was reused and adapted from the [AB3 project](https://github.com/nus-cs2103-AY2223S2/tp) created by the [SE-EDU initiative](https://se-education.org/).

* **Documentation**:
    * User Guide:
        * Added documentation for the features below in [#45](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/45), [#217](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/217) and [#267](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/267).
          * `add_contact`, `edit_contact`, `delete_contact`
          * `add_docs`, `edit_docs`, `delete_docs` 
          * `archive`, `unarchive`, `list_archived`
          * `edit_status`
    * Developer Guide:
        * Added use cases, implementation details and UML diagrams for the features below in [#61](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/61), [#287](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/287) and [#288](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/288).
          * `add_contact`, `edit_contact`, `delete_contact`
          * `add_docs`, `edit_docs`, `delete_docs`
          * `archive`, `unarchive`, `list_archived`
          * `edit_status`

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#141](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/141), [#160](https://github.com/AY2223S2-CS2103T-W15-4/tp/pull/160)
    * Reported bugs and suggestions for other teams in the class: [Team CS2103T-W11-3](https://github.com/jianminglok/ped/issues)

* **Tools**:
    * Java 11 and JavaFX
