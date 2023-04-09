---
layout: page
title: Ho Yin's Project Portfolio Page
---
### Project: EZ-Schedule
_EZ-Schedule_ is a desktop application for managing and scheduling of events,
optimized for use via a Command Line Interface (CLI)
while still providing an easy way to visualize all events through a Graphical User Interface (GUI).

_EZ-Schedule_ has about 13kLoC and 2.5k lines of documentation.
It is developed in Java, and uses JavaFX to create the GUI.

### Summary of Contributions
- **Code Contributed** - Check it out
  [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=lhy-hoyin&breakdown=true)

- **Enhancements Implemented**
  - ~~Create parallel package and copied starting base code from AB3~~
    (made useless due to possible violation of `Constraint-Brownfield`)
  - Make `Event` comparable
  - Automatic sorting of events in chronological order
  - _Show Upcoming Events_ feature

- **Contributions to the User Guide**
  - Overview of _EZ-Schedule_ (including target audience/user)
  - _Retrieve Next Events_ section
  - _Limitations_ section
  - _Frequently Asked Questions (FAQ)_ section
  - Add alert-box for a more intuitive look
  - Add `<div style="page-break-after: always;"></div>` page breaks for PDF printing

- **Contributions to the Developer Guide**
  - _Glossary_ section
  - **Data Verification when loading save files** in _Appendix: Planned Enhancements_ section
  - _Appendix: Effort_ section
  - Use of [HTML Details Elements](https://www.w3schools.com/tags/tag_details.asp) in uses cases
  - Use of reference-style links to gather all the links together (easier maintenance + cleaner text for other sections)

- **Contributions to Team-based Tasks**
  - Set up the GitHub team org and repository
  - Create milestones and labels within the team repository
  - Actively check team repository issues and pull requests
    - Add corresponding labels to issues and PRs (when missing)
      - Assign task to team member in charge
  - Rename `package` and all references to previous package
  - User Guide: Came up with the template structure for details of commands
  - Make running `shadowJar` in gradle also run all tests and checkstyles
  - Release `v1.3` and `v1.3.1` on [GitHub](https://github.com/AY2223S2-CS2103-W17-3/tp/releases)

- **Review/mentoring Contributions**
  - Milestone v1.1: [4] [pr-reviewed-v1.1] PRs reviewed
  - Milestone v1.2: [1] [pr-reviewed-v1.2] PRs reviewed
  - Milestone v1.3/b: [4] [pr-reviewed-v1.3] + [6] [pr-reviewed-v1.3b] PRs reviewed
  - Milestone v1.4: [30] [pr-reviewed-v1.4] PRs reviewed
  - Others: [3] [pr-reviewed-others] PRs reviewed
  - Total: [48] [pr-reviewed-total] PRs reviewed

- _PR Highlights_
  - Milestone 1: [Updated AboutUs and UserGuide](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/9)
  - Milestone 1: [Branch user story Developer Guide](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/31)
  - Milestone 2: [Update AboutUs](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/39)
  - Milestone 3/b: [Remove redundant class branch](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/59)
  - Milestone 3/b: [Refactor test cases](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/64)
  - Milestone 4: [Tests for ShowNextCommand, chronological sort](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/177)
  - Milestone 4: [Fix end time earlier than start time bug](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/167)
  - Milestone 4: [Add Instructions for Manual Testing in DG](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/212)

- _Mentoring Highlights_
  - Help resolve [Add InvalidDateException to CommandBox](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/189)
   through [Exception handling for DateTimeParseException](https://github.com/AY2223S2-CS2103-W17-3/tp/issues/175)

- **Contributions beyond the project team**
  - Opened questions/issues on the forum
    [3 times](https://github.com/nus-cs2103-AY2223S2/forum/issues?q=is%3Aissue+author%3Alhy-hoyin)
  - Participate in the forum in 12 posts
    ([Ranked 19](https://nus-cs2103-ay2223s2.github.io/dashboards/contents/forum-activities.html#19-lo-h-yin-lhy-hoyin-12-posts))

[pr-reviewed-v1.1]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Alhy-hoyin+milestone%3Av1.1
[pr-reviewed-v1.2]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Alhy-hoyin+milestone%3Av1.2
[pr-reviewed-v1.3]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Alhy-hoyin+milestone%3Av1.3
[pr-reviewed-v1.3b]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Alhy-hoyin+milestone%3Av1.3b
[pr-reviewed-v1.4]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Alhy-hoyin+milestone%3Av1.4
[pr-reviewed-others]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Alhy-hoyin+no%3Amilestone
[pr-reviewed-total]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Amerged+reviewed-by%3Alhy-hoyin


<div style="page-break-after: always;"></div>


### Contributions to the Developer Guide (Extracts)
Reproduce the parts in the Developer Guide that you wrote. 
Alternatively, you can show the various diagrams you contributed.


<div style="page-break-after: always;"></div>


### Contributions to the User Guide (Extracts)
