---
layout: page
title: Owen Yap's Project Portfolio Page
---

### Project: Docedex


Docedex is a **desktop application** built for **medical administrative assistants**
to manage doctors and patients within clinics.

Here's a **quick snapshot** of how Docedex can help you
streamline your hospital management processes.
- Store and edit information about your patients and doctors
- Track the status of your patients and doctors
- Assign patients to doctors upon triaging

Docedex is a **desktop application** built for **medical administrative assistants**
to manage doctors and patients within clinics.

### Summary of Contributions
Given below are my contributions to the project.

#### Code contributed
My code contributions can be visualised through [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=F12-1&sort=groupTitle&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17).

#### Enhancements implemented
Here are my contributions to the project.

**Features:**
- Functional and test code: Implement patient and doctor models
  - What it does: Creates the patient and doctor models which extend AB3 person class
  - Justification: Each command relies on the doctor / patient model to be implemented first.
- Functional and test code: Implement patient and doctor attributes
  - What it does: Creates the different attributes for doctors and patients, as well as the constraints for each attribute.
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
  - Update: Seeded data was removed after discussion with team

#### Contributions to the UG
Here are my contributions to the UG.
- Adding a command summary for quick reference by the users.
- Added descriptions for editing a doctor and editing a patient
- Added definitions to terminology used throughout the UG
- Made parameter descriptions more specific after PED

#### Contributions to the DG
Here are my contributions to the DG
- Sections
  - Architecture: Model
  - Implementations: EditDoctorCommand
  - Planned Enhancements: DoctorPatient Association class
  - Planned Enhancements: Validation for FindCommand
  - Appendix F: Glossary
  - Appendix G: Effort
- UML diagrams
  - Edit Doctor Sequence Diagram
  - Edit Doctor Activity Diagram
  - Updated Model Diagram
  - Proposed New Model Diagram (DoctorPatient class)

#### Contributions to team-based tasks
Here are my contributions to team-based tasks
- Brainstorming of user stories and use cases
- Envisioning product design (ie. problem statement, target audience)
- Project management: Created, assigned and closed issues for milestones 1.2, 1.2b, 1.3
- PR reviews

#### Review/mentoring contributions
Here are some selected PRs that I have reviewed (with non-trivial comments).
- [#80](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/80)
- [#253](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/253#pullrequestreview-1375393252)
- [#273](https://github.com/AY2223S2-CS2103T-F12-1/tp/pull/273)

Here are some ways through which I have aided my team members
- Discussed design considerations actively with the team and gave constructive reviews wherever necessary.
- Helped the team understand the healthcare landscape and how to position Docedex as a great productivity tool in clinics.
