---
layout: page
title: Jerome Neo's Project Portfolio Page
---

### Project: PlanEase

PlanEase is designed for event planners who prefer typing on their keyboards to retrieve information quickly.
It provides a centralised platform for them to manage their work, contacts, and event information.
This application helps to streamline the process of searching for stakeholders' contact information and keeping track of important events.

Given below are my contributions to the project.

* **New Feature**: Augmented the existing storage to accept event objects as well event objects stored in each contact. ([#36](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/36))
  * What it does: Allows the rest of the features to save the events that we created in the address book.
  * Justification: This feature is crucial as it supports the additional data type that we implemented in this project.

* **New Feature**: Add checking of valid event dates in `addevent`. ([#36](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/36), [#37](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/37))
  * What it does: It checks the event dates are of the valid range. The start date will have to be earlier than the end date.
  * Justification: It is not logical to have an end date earlier than a start date.
  * Highlights: This implementation also ensures that same date and time set to the data range is accepted for an event.

* **New Feature**: Add searching features for events in `findevent`. ([#57](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/57))
  * What it does: It looks for events with names containing the keywords that the user wants to match with.
  * Justification: This is to cater to use cases where there are large number of events being stored in the list and the user may want to isolate the display of events to those events.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jerome-neo&breakdown=true)

* **Enhancements implemented**:
  * Reduce window white space which was present on macOS users. ([#51](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/51))

* **Contributions to the Developer Guide**
  * Added `findevent` documentation as well as sequence diagrams. ([#63](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/63))
  * Updated storage diagrams as well as class diagrams. ([#63](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/63))
  * Added proposed enhancements. ([#135](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/135))
  * Added additional manual testing and use cases for `findevent`. ([#135](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/135))

* **Contributions to the User Guide**
  * Update documentation for `listevent`. ([#30](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/30))
  * Added examples for `findevent`. ([#116](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/116))
  * Improve explanation of naming restrictions. ([#116](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/116))

* **Contributions to team-based tasks**
  * Update team information and images. ([#10](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/10))
  * Added user stories to tp issue tracker.

* **Review/mentoring contributions**
  * PRs reviewed (with useful comments): [#33](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/33), [#69](https://github.com/AY2223S2-CS2103-W16-3/tp/pull/69)

* **Contributions beyond the project team**
  * Reported 10 bugs for: [PE-D Issues link](https://github.com/jerome-neo/ped/issues)
