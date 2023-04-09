---
layout: page
title: Chia Zi Xuan's Project Portfolio Page
---

### Project: MediMeet

**MediMeet** is a patient appointment management system for individual practitioners and small clinics. With MediMeet, you can efficiently manage patient information and appointment information in one place, saving you both time and money.
**MediMeet** combines the visual appeal of a GUI (graphical user interface) with the speed and convenience of a command-line interface. Our easy-to-use commands make it easy to add, edit patient information, appointments and so much more! 

Given below are my contributions to the project.

* **New Feature**: `find_patient_details` - Added a command to find patients based on all their details.
  * **What it does**: Finds a patient by searching for the given keywords in patient's details, including their phone number, email, address, remarks and tags, instead of just their names.
  * **Justification**: The normal `find_patient` command is not useful when users do not remember a patient's name, but remember something related to them that they added as a remark for that patient. This feature allows users to do more thorough searches for patients. This feature also enables users to search and filter by tag.
  * **Highlights**: Implementing this feature required comprehensive understanding of how `Predicate`s are used and how the `Model` updates a filtered list given a predicate.
  
* **New Feature**: `find_appt` - Added a command to find appointments based on all their timeslot.
  * **What it does**: Finds an appointment based on the timeslot. There are two variations of this command. If only one time is provided, appointments that occur during that time will be found. If two times are provided, all appointments that occur anytime during that time period will be found.
  * **Justification**: Users can find what appointments they have at a given time and get details regarding the appointment at a particular time. Users can also see their schedule for a particular period of time in the day.
  * **Highlights**: Implementing this feature required a thorough understanding of polymorphism and understanding how the algorithm that detects overlaps in ranges works.

* **New Feature**: `edit_appt` - Added a command to edit appointments.
  * **What it does**: Edits an appointment's timeslot, description or doctor.
  * **Justification**: Appointments can be rescheduled or updated with new information using this feature. Wrongly entered appointments can also be edited using this command.

* **New Feature**: `today` - Added a command to find appointments based on all their timeslot.
  * **What it does**: Shows the appointments that are scheduled for today.
  * **Justification**: Viewing the schedule for today is a common need, thus a short form for such a feature saves users a lot of typing.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=zxisatree&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
	* Released JARs for v1.2, v1.3 and v1.4 product releases
	* Helped coordinate the team and split the work amongst them
	* Assigned unassigned issues to relevant teammates for milestones 1.3 and 1.4

* **Enhancements implemented**:
	* Created appointment model and updated saving to disk functionality to save appointment list as well
	* Added automatic deletion of appointments upon deleting a patient
	* Changed `edit_patient` to not be able to edit patient names

* **Testing**:
	* Added tests for the `find_appt` and `find_patient_details` commands

* **Documentation**:
  * Developer Guide:
	* Updated model and storage UML diagrams
	* Added implementation details of `edit_patient`, `edit_appt`, `find_patient`, `find_patient_details` and `find_appt` 

* **Contributions to team based tasks**:
  * PRs reviewed (with non-trivial review comments): Reviewed 12 PRs (e.g. pull request [#18](https://github.com/AY2223S2-CS2103T-W12-4/tp/pull/18))
