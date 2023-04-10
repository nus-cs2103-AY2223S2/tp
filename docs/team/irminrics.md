---
layout: page
title: Ang Jun Kang's Project Portfolio Page
---

### Project: PlanEase

PlanEase is designed for users who work as event planners.
It provides a centralised platform for them to manage their work, contacts, and events information, making the process of searching for stakeholders and keeping track of details easier and more efficient.

Given below are my contributions to the project.

* **New Feature**: Added a UI for event. ([#49](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/49))
  * What it does: Allow users to view the displayed events.
  * Justification: This feature enables the user to peruse the list of displayed events in accordance with their input commands.

* **New Feature**: Duplicate event validation. ([#40](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/40)), ([#41](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/41)), ([#117](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/117))
  * What it does: Validates if a newly added event is a duplicate event.
  * Justification: By validating the uniqueness of newly added events, we ensure that the system is not overwhelmed with duplicate events, which can lead to confusion and inconsistencies in event tracking. The implementation of this feature is in line with our overarching goal of maintaining the integrity of our event tagging system and providing users with a streamlined and efficient experience when utilizing our application.
  * Highlights: In keeping with our goal of maintaining the uniqueness of event tags, we have opted to implement duplicate event validation using only the event name, similar to our approach to duplicate persons validation. This measure serves to reduce ambiguity that may arise in cases where two events with identical names are assigned to the same individual.

* **New Feature**: Added a `listall` command. ([#61](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/61))
  * What it does: Allow users to list all persons and events.
  * Justification: Given the functionality of filters in PlanEase, it is possible that the list of persons or events displayed on the user interface may not include all available options. Therefore, users may find it necessary to utilize a command that will reset the filters and display the complete list of persons and events within the application.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=irminrics&breakdown=true)

* **Enhancements implemented**:
  * Updated UI with better QoL fixes ([#60](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/60))
  * Updated JAR name generated ([#71](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/71))
  * Updated new application icon for PlanEase ([#80](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/80))

* **Project Management**:
  * Released [v1.3](https://github.com/AY2223S2-CS2103-W16-3/tp/releases/tag/v1.3) on GitHub.

* **Contributions to the Developer Guide**
  * Updated Product Scope, User Stories, NFRs and Glossary ([#15](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/15))
  * Updated UI Component
    * Updated [description](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/67)
    * Drew [UI Class Diagram](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/59)
  * Increase size of all sequence diagram ([#87](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/87))
  * Added instruction for manual testing for `findevent`, `listall` and `listevcontact` ([#132](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/132))
  * Added use case for `findevent` ([#132](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/132))
  * Updated instruction for manual testing for `delevent` ([#138](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/138))

* **Contributions to the User Guide**
  * Updated Description and Command Summary section ([#15](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/15))
  * Updated Quickstart section ([#47](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/47))
  * List all section ([#81](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/81))
  * Visual examples for all commands ([#115](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/115))

* **Contributions to team-based tasks**
  * Setting up the GitHub team org/repo.
  * Setting up tools e.g., GitHub Workflow, Assertions in Gradle ([#62](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/62)), Codecov.
  * Setting up project site.
  * Adding user stories to issue tracker.
  * Creating milestones and tags/labels for issue tracker.
  * Crafted all the user stories with the team.
  * Crafted some use cases with the team.
  * Worked on the demo video with the team.

* **Review/mentoring contributions**
  * Reviewed and commented non-trivial comments on the following PRs: [#33](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/33), [#39](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/39), [#48](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/48), [#57](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/57), [#114](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/114).

* **Contributions beyond the project team**
  * Reported 11 bugs for Team AY2223S2 CS2103T-W14-1: [PE-D Issues Link](https://github.com/irminrics/ped/issues).
