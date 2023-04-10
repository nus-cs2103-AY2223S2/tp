---
layout: page
title: Tseng Chen-Yu's Project Portfolio Page
---

# Overview
Careflow is a desktop application for patient and drug inventory management of medical clinics, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Careflow can get your patient and drugs management tasks done faster than traditional applications.

### Summary of Contributions
* **Code Contributed:** [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cydtseng&breakdown=true)

* **Enhancements implemented:**
  * **Major:**
    * Implement parser for `Patient` related commands. (Pull request: [\#60](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/60), [\#71](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/71))
      * What it does: 
    * Implement `Patient` related commands. (Pull request:[\#60](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/60))
      * What it does:
    * Implement `Hospital` related classes. (Pull request: [\#98](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/98))
      * What it does: allow for the display of Hospital Hotlines Tab and list cells. 
      * Credit: Adapted from Drug class from AddressBook code.
    * Modify overall UI layout. (Pull request: [\#94](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/94))
      * What it does: To improve user experience and create more hierarchy for better information display.
      * Credits: Adapted original UI from AddressBook code.
    * Added Drug Pie Chart. (Pull request: [\#101](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/101))
      * What it does: Displays an overview of drug inventory distribution percentages.
      * Highlights: Concurrent updates to pie chart as per changes in the inventory, hovering over chart segments also 
      show tooltip with the counts of each drug.
    * Added Tab Panes. (Pull request: [\#94](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/94))
      * What it does: Prevents cluttered display of Patient, Drug and Hospital hotline information.
  * **Minor:**
    * Add JsonAdaptedDrug class. (Pull request: [\#74](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/74))
    * List cell visual enhancement for improved interactivity. (Pull request: [\#94](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/94))
    * Add animated robot assistant as part of console result for better user experience. (Pull request: [\#199](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/199))

* **Issues Fixed:**
  * Pie chart legend visibility. (Pull request: [\#189](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/189))
  * Pie chart drug inventory 'NaN' bug caused by zero count. (Pull request: [\#276](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/276))
  * User guide poor formatting. (Pull request: [\#276](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/276))
  * Program spelling errors. (Pull request: [\#276](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/276))

* **Tests Implemented:**
  * JUnit tests for patient UpdateCommandParser. (Pull request: [\#145](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/145))
  * JUnit tests for patient DeleteCommandParser. (Pull request: [\#134](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/134))
  * JUnit tests for patient AddCommandParser. (Pull request: [\#139](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/129))
  * JUnit tests for patient FindCommandParser. (Pull request: [\#141](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/141))

* **Documentation:**
  * **User Guide:**
    * User guide styling in terms of background colors, font sizes, font colors. (Direct master commits.)
    * Add data storage section. (Pull request: [\#281](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/281))
    * Add Quick Start labelled interface diagram. (Pull request: [\#301](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/301))
    * Add more QnA related information. (Pull request: [\#285](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/285))
    * Add instructions for Windows terminal. (Pull request: [\#303](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/303))
  * **Developer Guide:**
    * Developer guide styling in terms of background colors, font sizes, font colors. (Direct master commits.) 
    * Add more detailed value propositions. (Pull request: [\#156](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/156)) 
    * Add pie chart implementation detail in terms of sequence diagrams and text. (Pull request: [\#173](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/173)) 
    * Modify legacy diagrams to suit CareFlow. (Pull request: [\#160](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/160))

* **Contributions to team-based tasks:**
  * **Project Management:**
    * PRs merged for teammates. (Commits: [\#274](https://github.com/AY2223S2-CS2103T-W09-3/tp/commit/78a41525970f362a62d45848cab4341ae89e179e), [\#297](https://github.com/AY2223S2-CS2103T-W09-3/tp/commit/9a1068f293d455fa03d3560f4baab62ead3f022e), [\#195](https://github.com/AY2223S2-CS2103T-W09-3/tp/commit/89487da795e7c240b1ab8a1455af4f4350a19ec6))
    * Update readme with new product UI image and codecov badge. (Pull request: [\#143](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/143))
    * Update project description on GitHub.
    * Enable assertions for tp by modifying build.gradle. (Pull request: [\#153](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/153))
    * Release of v1.3 trial. (Release: [v1.3 trial](https://github.com/AY2223S2-CS2103T-W09-3/tp/releases/tag/v1.3.trial))
    * Setting up of milestones and deadlines.
    * Modifying folder structures to suit CareFlow project organisation. (Pull request: [\#116](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/116))
  * **Review / mentoring contributions:**
    * PRs reviewed for teammates. (Pull request: [\#95](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/97), [\#85](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/85), [\#55](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/55), [\#63](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/63), [\#65](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/65)) 
    * Created skeleton files for test cases for teammates' ease of test case implementation. (Pull request: [\#111](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/111))

* **Community**
  * Bug reports for other teams in [PE-D](https://github.com/cydtseng/ped/issues)

