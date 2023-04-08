---
layout: page
title: Pei Wenqi's Project Portfolio Page
---

### Project: HospiSearch

HospiSearch is a desktop app for healthcare administrators to manage hospital/clinic patients' particulars and is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, HospiSearch can get your contact management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    [\#80](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/80)
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.



* **New Feature**: Added the ability to switch between the dark and light theme.
    [\#61](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/61)
    * What it does: allows the user to choose the GUI theme he/she prefers at a specific time.
    * Justification: This feature increases the accessibility of the product because a user may not want to face strong screen light at night and don't want to look at a dark screen in the daytime.
    * Highlights: The implementation of this feature requires combined knowledge of JavaFX, CSS, and fine arts. 
    * Credit: Adapted `Theme` class from [here](https://github.com/junlong4321/tp/blob/master/src/main/java/tutorspet/ui/stylesheet/Stylesheet.java).



* **New Feature**: Added the ability to view patient details.
    [\#106](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/106)
    * What it does: allows the user to see detailed patient information other than the information on the small card.
    * Justification: This feature is an indispensable part of the product because the patient card in the list panel cannot contain detailed patient particulars.
    * Highlights: This enhancement is related to all commands which make modifications to the data. A view pane should also be built upon the original GUI to implement the functionality.



* **User Interface**:
    * Styled application UI with Java FX.
    * Touched up overall application layout.
    * Edited colours and restyled UI components.
    * Enabled event handler (double click) for View command.
    * Added a `Theme` toggle on the top bar.
    * GUI Change ([\#34](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/34), [\#48](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/48), [\#50](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/50), [\#136](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/136), [\#209](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/209))



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cedric&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)


* **Project management**:
    * Set up the team Google Drive folder.
    * Organised and coordinated team meetings.
    * Published `v1.3` demo.
    * Reviewed and approved PRs for merging.
    

* **Documentation**:
    * User Guide:
        * Added documentation for the features `dark` and `light`: [\#80](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/80)
        * Added documentation for the features `undo` and `redo`: [\#80](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/80)
        * Added documentation for the feature `view`: [\#131](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/131)
        * Added screenshots and captions for all features: [\#131](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/131)
        * Corrected grammar mistakes and polished sentences: [\#64](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/64)

    * Developer Guide:
        * Added implementation details of the `dark` and `light` feature: [\#64](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/64)
        * Integrated and added all user stories.


* **Community**:
    * PRs reviewed (with non-trivial comments):[\#42](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/42), [\#58](https://github.com/AY2223S2-CS2103T-T11-4/tp/pull/58)
    * Reported a total of [12] (https://github.com/CedricPei/ped) bugs and issues for PE-D.
