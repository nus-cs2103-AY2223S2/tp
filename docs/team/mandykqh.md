---
layout: page
title: Mandy Kaw's Project Portfolio Page
---

### Project: MediConnect

MediConnect is a desktop address book application used for managing patients and doctors information.
The user interacts with it using a CLI, and it has a GUI created with JavaFX.
It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=mandykqh&breakdown=true)

* **Project management**:
  * Contributed to the deliverables for `v1.1` - `v1.4`
  * Convened weekly meetings to update team members on the current progress
  * Sought help from team members when in doubt
  * Met all the internal deadlines set by the team

* **Enhancements implemented**:
  * Add an appointment
    * Schedule an appointment for a patient with a doctor
    * This adds an appointment to the patient's appointment list and the corresponding doctor's appointment list
      * Doctors and administrative staff can now view all the appointments (including date and doctor) lined up for the specific patient
      * Doctors and administrative staff can now view all the appointments (including date and patient) lined up for the specific doctor
  * Delete an appointment
    * Remove an appointment for a patient that was previously scheduled with a doctor
    * This removes an appointment from the patient's appointment list and the corresponding doctor's appointment list
  * Display detailed person view
    * Display a panel of detailed information of a specified person
      * Patient's detailed view entails personal particulars, appointments, and prescription
      * Doctor's detailed view entails personal particulars, and appointments
    * Having all the detailed information compacted in the person list can serve to be distracting for the user, especially when the list gets very long
      * Only the Name and NRIC of the person is displayed on the person list, and the rest of the particulars are abstracted for the detailed person view
  * Enhanced UI
    * Designed to better suit the clinical theme of the app
      * Intentionally designed to make the app more visually welcoming and appealing to use, and to specifically ease new users into using a clinical management app that can otherwise appear intimidating and complicated during first-time usage

* **Documentation**:
  * User Guide:
    * Updated documentation for the commands `appointment`, `deleteAppointment`, and `display`
  * Developer Guide:
    * Added documentation for user stories
    * Updated implementation details for the commands `appointment`, `deleteAppointment`, and `display`

* **Contributions to team-based tasks**:
  * Reviewed team member's PRs
    * https://github.com/AY2223S2-CS2103T-W13-1/tp/pull/41

* **Community**:
  * Reported bugs in other team's products during PE-Dry run

* **Tools**:
  * IntelliJ IDEA
  * Sourcetree
  * GitHub
  * Git
  * SceneBuilder
