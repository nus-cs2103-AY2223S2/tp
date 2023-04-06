---
layout: page
title: Owen Yap's Project Portfolio Page
---

### Project: Docedex

***Taking control of hospital administrative management is just a few keystrokes away!***

Docedex is a **desktop application** built for **medical administrative assistants**
to manage doctors and patients within clinics.

### Summary of Contributions
Given below are my contributions to the project.

#### Code contributed
My code contributions can be visualised through [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=F12-1&sort=groupTitle&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17).

#### Enhancements implemented
Here are my contributions to the project.

**Features:**
- Functional code: Implement patient and doctor models
  - What it does: Creates the patient and doctor models which extend AB3 person class
  - Justification: Each command relies on the doctor / patient model to be implemented first.
- New Feature: `edit-doc / edit-ptn` - Edits a doctor / patient in Docedex
  - What it does: Allows the user to make changes to the doctor / patient
  - Justification: User may need to change the doctor's details or modify the patient's status according to the hospital's workflow.
- New Feature: `sd` - Selects a doctor from the doctor's list through the CLI
  - What it does: Allows the user to select a doctor to view their details through the CLI
  - Justification: As Docedex is optimized for fast typers, we need a convenient way for users to view doctor / patient details without having to touch their mouse to click on the specific doctor / patient card.
  - Highlights: Use of Java's `Optional` to pass the doctor object to the GUI to be displayed.
- New Feature: Loads mock data into file if data folder is not found
  - What it does: Loads Docedex with seeded data.
  - Justification: New users who first start the application are probably still adjusting to the application. Seeded data allows users to have something to perform commands on. This helps the users orientate themselves quickly to the application.

#### Contributions to the UG
Here are my contributions to the UG.
- Adding a command summary for quick reference by the users.
- Added descriptions for editing a doctor and editing a patient

#### Contributions to the DG
Here are my contributions to the DG
- Sections
  - (to be added)
- UML diagrams
  - (to be added)

#### Contributions to team-based tasks
Here are my contributions to team-based tasks
- Brainstorming of user stories and use cases
- Envisioning product design (ie. problem statement, target audience)
- Project management: Created, assigned and closed issues for milestones 1.2, 1.2b, 1.3

#### Review/mentoring contributions
Here are the PRs that I have reviewed (with non-trivial comments).
- [#80](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/80)
- [#253](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/253#pullrequestreview-1375393252)

Here are some ways through which I have aided my team members
- (to be added)
