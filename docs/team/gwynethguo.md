---
layout: page
title: Gwyneth Guo Bai Ling's Project Portfolio Page
---

### Project: MATHUTORING

MATHUTORING is a desktop application used for private Math tuition teachers to manage their students contact details and performance. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to export a student's progress using Command Line Interface (CLI) `exportp` and Graphical User Interface (GUI).
  * What it does: allows the user to export a student's progress into a PDF file using CLI or GUI. The PDF file consists of the student's name, date created, score list, and task list.
  * Justification: 
    * This feature improves the product significantly because a user can directly export a student's progress to send it to the student or parent. 
    * This feature can also solve the problem where labels are not fully displayed in the application's User Interface.
  * Highlights: 
    * The implementation was too challenging as it required writing to PDF files using coordinates. 
    * Manual wrapping must also be implemented to ensure texts do not overflow in the PDF file.
    * Algorithms are needed to create lines and texts for task list and score list. 
    * A new command is also needed for CLI, involving user directory paths, which creates a lot of possible bugs for user with different OSes.
  * Credits: Used a third-party library [Apache PDFBox](https://pdfbox.apache.org/)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=gwynethguo&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=gwynethguo&tabRepo=AY2223S2-CS2103-W17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**: 
  * Managed a release `v1.3.1` on GitHub

* **Enhancements to existing features**:
  * Added GUI for Task List and Score List panels ([#78](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/78))
  * Added unit and integration tests for `exportp` command involving Stubs and Mocks ([#103](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/103), [#179](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/179))
  * Enhanced code quality for `PdfConverter.java` ([#171](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/171))

* **Documentation**:
  * User Guide: ([#132](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/132), [#133](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/133), [#176](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/176))
    * Added screenshots to User Guide
    * Rearranged User Guide to ensure good flow 
    * Added table of contents, user interface layout, user input restrictions, added links to sections.
  * Developer Guide: ([#176](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/176))
    * Added manual testing for exporting a student's progress feature
    * Arranged some parts of the Developer Guide

* **Contributions to team-based tasks**:
  * Setup tP Team Organization
    * Created a [GitHub Organization](https://github.com/AY2223S2-CS2103-W17-1) for the team.
    * Add members to the organization and created a `developers` team.
  * Setup tP Team Repository
    * Forked the [repo](https://github.com/nus-cs2103-AY2223S2/tp) to the team organization.
    * Enabled the issue tracker and GitHub Actions.
    * Setup CodeCov.
    * Added members to the repository and managed access of the team members.
  * Added GitHub issues and helped manage the issues by adding labels and milestones.
  * Updated site-wide settings ([#26](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/26), [#28](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/28), [#176](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/176))
    * Changed project name, title, and badges.
    * Changed site header to ensure that the details are correct when converting the documentation to the `.pdf` format.
    * Changed site settings to change fonts and colors of the sites.

* **Community**:
  * PRs reviewed (with non-trivial review comments): ([#30](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/30), [#119](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/119), [#70](https://github.com/AY2223S2-CS2103-W17-1/tp/pull/70))
  * Reported bugs and suggestions for other teams in the class ([#16](https://github.com/gwynethguo/ped/issues/16), [#3](https://github.com/gwynethguo/ped/issues/3), [#6](https://github.com/gwynethguo/ped/issues/6))
  * Assist a group in git branching and fixing JUnit tests.

* **Tools**:
  * Integrated Code Coverage ([CodeCov](https://app.codecov.io/gh/AY2223S2-CS2103-W17-1/tp)) to the project.
  * Integrated a third party library ([Apache PDFBox](https://pdfbox.apache.org/))
  * Integrated a mocking framework ([Mockito](https://site.mockito.org/))
