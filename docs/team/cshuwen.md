---
layout: page
title: Chen Shuwen's Project Portfolio Page
---

# Overview
Careflow is a desktop application for patient and drug inventory management of medical clinics, optimised for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Careflow can get your patient and drugs management tasks done faster than traditional applications.

### Summary of Contributions

* ###**Major enhancement**:
    * Implement the `Patient` commands and parsers. (Pull request [\#65](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/65), [\#63](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/63))
        * What it does: This feature implements the `FindCommand`, `DeleteCommand` in the system, allowing users to search for a patient in the patient list by a patient's name and delete an unwanted patient's record. 
        * Credits: adapted from existing AddressBook code.

    * Implement the JsonSerializable (Pull request [\#78](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/78))
        * What it does: Added `JsonSerializableDrug` and `JsonSerializablePatient` classes to allow storage of patient and drug model in Json format.
        * Credits: adapted from existing AddressBook code.
      
* ###**Minor enhancement**:
    * Recolour the Dark Theme  (Pull request [\#103](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/103))
      * Recolour the DarkTheme to suit the theme of clinic management by adapting a blue color theme.
      * Credits: adapted the css files from existing AddressBook code.
    * Set up UI for drug tab. (Pull request [\#10](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/10))
      
* ###**New Features**:
  * Restructure the UI 
    * Restructure the UI to allow detail of patient/drug to be displayed on the right section of the patient/drug tab. (Pull requests [\#97](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/97/files))
    * Reduce information on patient/drug cards to only displaying either patient name and phone number or drug name. (Pull requests [\#97](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/97/files))
    * Implemented onclicklistner to the patient/drug card to allow viewing the patient/drug details by clicking any of the patient card. (Pull requests [\#97](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/97/files))
    * Allow patient/drug display to be updated when changes being made to the patient/drug information. (Pull request [\#171](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/171), [\#123](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/123))
    * Enhance the format of the patient/drug detail display. (Pull requests [\#197](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/197/files))
    
  * Added View Command (Pull request [\#183](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/183))
    * Implement new command to view the patient/drug detail at the patient/drug display section by inputting their index. 
    
* ###**Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cshuwen&breakdown=true)

* ###**Enhancements to existing features**:
    * Wrote additional tests for Logic Manager and all patient commands files to increase coverage by 27.7% (Pull request [\#144](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/144),  [\#136](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/136))
    * Bug fixing received from PE-D (Pull request [\#294](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/294), [\#284](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/284), [\#274](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/274))

* ###**Documentation**:
    * User Guide:
        * Create drop down lists for the Quickstart section to avoid over cluttered information. (Pull requests [\#205](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/205/files))
        * Created Back to top hyperlinks in between each section to allow better navigability of the UG. (Pull requests [\#210](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/210), [\#127](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/127), [\#118](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/118)) 
        * Added view patient and view drug sections. (Pull request [\#187](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/187), [\#186](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/186))
        * Updated Command Summary. (Pull request [\#118](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/118))
        * Enhanced overall structure (eg, image size, breaks between sections and font) of the UG to allow better readability. (Pull requests [\#211](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/211), [\#209](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/209), [\#208](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/208), [\#207](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/207), [\#206](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/206), [\#205](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/205), [\#192](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/192), [\#191](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/191), [\#190](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/190))
    * Developer Guide:
      * Added implementation details and UML diagram of the UI component. (Pull requests [\#159](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/159))
      * Added implementation details, sequence diagram and class diagram, architecture diagram for the Architecture section. (Pull requests [\#166](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/166), [\#163](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/163))
      * Added user stories of DG. (Pull request [\#35](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/35))
      * Added Non-functional requirements of DG. (Pull request [\#35](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/35))

* ###**Community**:
    * PRs reviewed for teammate (with non-trivial review comments): [\#62](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/62), [\#66](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/66), [\#101](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/101), [\#102](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/102),  [\#117](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/117)
      * Actively correct checkstyle errors for the team to pass java CI. (Pull request [\#295](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/295),  [\#124](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/124), [\#109](https://github.com/AY2223S2-CS2103T-W09-3/tp/pull/109))
    * Help release milestone versions. (Versions: [v1.2](https://github.com/AY2223S2-CS2103T-W09-3/tp/releases/tag/v1.2), [v1.3](https://github.com/AY2223S2-CS2103T-W09-3/tp/releases/tag/v1.3))
    * Reported bugs and suggestions for other teams in the class [CS2103-W11-4](https://github.com/cshuwen/ped/issues)
