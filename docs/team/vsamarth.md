---
layout: page
title: Samarth's Portfolio Page
---

### Project: ConnectUS

ConnectUS is the ultimate **contact management system** for your everyday needs. If you're an NUS School of Computing (SoC) student, this app is for you. With ConnectUS, you can **easily connect with people** without the anxiety of _remembering who you met where_ holding you back from socialising.

We're focused on:
- **Efficiency**: Optimized for use via a Command Line Interface (CLI), you can **easily view and edit your contacts** at your fingertips with ConnectUS.
- **User-friendliness**: With the benefits of having a Graphical User Interface (GUI), **easily navigate through your contact information** to find exactly what you need to **connect** with others.

Given below are my contributions to the project.

* **Major Enhancement**: Added an optional `Birthday` field.
  * What it does: It allows the user to store the birthdays for any contact.
  * Justification: This feature is important as it allows you to remember the birthdays of your contacts, making sure that you can wish them on the special day.
  * Highlights: This was the first optional field that was added to the `Person` class. It required several design decisions, such as how to store
  the birthday in JSON since it might be missing for some contacts. It also required changes to the `Add` and `Edit` commands to allow the user to add and edit the birthday field. The design of the `Birthday` class then 
  adapted by other team members to add other optional fields.
  * Related Pull Requests: [#62](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/62)
* **New feature**: Implemented the `Upcoming Birthday` feature.
  * What it does: It allows the user to view the contacts whose birthdays are in the next 60 days.
  * Justification: It is always hard to remember the birthdays of all our friends and it can become awkward if we forget to wish them on their special day. This feature allows the user to easily view the birthdays of their contacts in the next 60 days, so that they can wish them on time.
  * Highlights: This feature required the implementation of a new `UpcomingBirthdayCommand` class. It also required me to add detailed test cases to make sure it's functionality was not broken across versions.
  * Related Pull Requests: [#147](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/147), [#156](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/156)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=vsamarth&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Brainstormed features and solutions with team to find solutions.

* **Enhancements to existing features**:
  * Modified the user interface and added icons for `v1.1`.
  * added JUnit tests for all features I implemented
  * Fixed bugs in v1.4.

* **Documentation**:
  * User Guide:
    * added relevant user stories to user guide regarding birthdays
  * Developer Guide:
    * Added implementation of Upcoming Birthday feature [(#235)](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/235)
    * Added detailed instructions for manually testing the app 
      for each command [(#231)](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/231)

* **Community**:
  * PRs reviewed: As of 10th April, I have reviewed 10 PRs.
  * Reported bugs and suggestions for other teams in PE dry run [here](https://github.com/vsamarth/ped).
