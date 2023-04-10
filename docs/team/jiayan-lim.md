---
layout: page
title: Jiayan's Project Portfolio Page
---

# Overview
Careflow is a desktop application for patient and drug inventory management of medical clinics, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Careflow can get your patient and drugs management tasks done faster than traditional applications.

### Summary of Contributions

* **Major enhancement**:
    * Implement the `Patient` model.
      * What it does: This feature implements the Patient model in the system, allowing users to store and manage information about patients.
      * Highlights: The Patient model allows for the inclusion of necessary patient information, such as patient name, phone etc. , while also accommodating optional fields like emergency contacts and drug allergies. This flexibility allows for more complete patient records, which can help healthcare providers make more informed decisions and provide better care.
      * Credits: adapted from existing AddressBook code.

    * Implement the `update` patient feature.
      * What it does: This feature allows users to update patient information in the app without having to delete then add a new patient profile.
      * Credits: adapted from existing AddressBook code.

    * Allow system to check for patient with duplicate IC
      * What it does: This feature allows the app to check for patients with duplicate NRIC or FIN numbers to avoid duplication of patient.
      * Highlights: The addition of a feature that checks for duplicate NRIC or FIN numbers is crucial to the accuracy and integrity of the patient database. Without this feature, it is possible for duplicate patient records to be created, which can lead to confusion, errors in treatment.


* **Minor enhancement**:
    * make emergency contact and drug allergy be optional for add patient command(Pull request [\#174](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/174))
    * fix error message displayed to the user (Pull request [\#223](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/223), [\#224](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/224))


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jiayan-lim&breakdown=true)


* **Enhancements to existing features**:
    * Wrote additional tests for drug commands to increase coverage from 47.19% to 62.01% (Pull request [\#138](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/138))
    * Wrote additional tests for model component(Patient and Drug) to increase coverage from 47.19% to 62.01% (Pull request [\#138](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/138), [\#49](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/49))
    * Bug fixing received from PE-D (Pull requests [\#271](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/271), [\#272](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/272), [\#282](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/282))


* **Documentation**:
    * User Guide:
        * Added Introduction section (Pull request: [\#195](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/195))
        * Added About This User Guide section (Pull request: [\#195](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/195))
        * Added Overview of Feature section (Pull request: [\#214](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/214))
        * Added documentation for the features `update` (Pull request: [\#91](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/91))
        * Divided and reformatted Command Summary into three category (Pull request: [\#195](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/195))
        * Added Glossary of UG (Pull request: [\#214](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/214))
        * Added label for table and image (Pull request: [\#214](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/214))
    * Developer Guide:
        * Added implementation details and UML diagram of the model component. (Pull requests [\#180](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/180), [\#184](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/184))
        * Added use cases for each important feature. (Pull requests [\#31](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/31), [\#40](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/40), [\#114](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/114))
        * Added glossary of DG. (Pull request [\#31](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/31))
        * Update DG with target user profile and value proposition. (Pull request [\#77](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/77))
        * Update DG with Enhancement in Appendix (Pull request [\#279](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/279), [\#288](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/288))
        * Fix broken URL link (Pull request [\#184](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/184))


* **Community**:
    * PRs reviewed for teammate (with non-trivial review comments): [\#190](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/190#discussion_r1148819990), [\#183](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/183#discussion_r1149770358)
    * PRs reviewed for PE-D tester (with non-trivial review comments): [\#246](https://github.com/AY2223S2-CS2103T-W09-3/tp/issues/246#issuecomment-1498584781), [\#244](https://github.com/AY2223S2-CS2103T-W09-3/tp/issues/244#issuecomment-1498579241)
    * Reported bugs and suggestions for other teams in the class [CS2103-F10](https://github.com/Jiayan-Lim/ped/issues)
