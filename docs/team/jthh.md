---
layout: page
title: Jiatong's Project Portfolio Page
---

### Project: TrAcker

TrAcker - TrAcker is a desktop event management application for NUS CS2040 Teaching Assitants (TA). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the function to add/edit/delete notes from events.
    * What it does: 
        * Allows TAs to add their notes to events such as tutorials, labs, and consultations.
        * Allows TAs to edit their notes from events such as tutorials, labs, and consultations.
        * Allows TAs to delete their notes from events such as tutorials, labs, and consultations.
    * Justification: Useful since TAs are managing many students from different sessions with different needs. Also, they are usually tasked with multiple duties. In order to keep organized, they need a noting system.
    * Highlights: Good usability and efficient management system inside each event.

* **New Feature**: Added the GUI for listing notes directly from event cards.
    * What it does: Displays the list of notes neatly inside the event cards when clicked with mouse, with notes hidden by default.
    * Justification: Useful since TAs will be able to check notes conveniently through events, instead of listing notes in a centralised manner which hardly enable efficiently note finding, editing, and deleting.
    * Highlights: Different presentations between when there are notes and when there are not. Notes are hidden by default, and listed neatly when users expand them.

* **New Feature**: Added the search utility that locates the note from an event to delete or edit.
    * What it does: Searches notes from list of events by event names and note indices.
    * Justification: Useful since TAs will be able to edit or delete notes more efficiently by our internal search optimisations. 
    * Highlights: Supports wildcard matching with event names and note indices.

* **Code contributed**: [RepoSense link](https://github.com/jthh/tp)

* **Project management**:
    * Assisted in releases `v1.3` - `v1.4` (2 releases) on GitHub.

* **Enhancements to existing features**:
    * Created part of the GUI for displaying list of notes: 
    [\#158](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/158)
    * Enhanced command parser and handlers for adding, deleting, and editing notes: 
    [\#158](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/158)
    * Enhanced unit tests to cover for adding, deleting, and editing notes utilities:
    [\#82](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/82).

* **Documentation**:
    * User Guide:
        * Added a documentation and command summaries for adding, deleting, and editing notes: 
        [\#162](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/162), 
        [\#265](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/265).
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`.
    * Developer Guide:
        * Added implementation details of the `note` feature: 
        [\#135](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/135), 
        [\#158](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/158).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#16](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/16), 
    [\#59](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/59), 
    [\#65](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/65), 
    [\#84](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/84), 
    [\#86](https://github.com/AY2223S2-CS2103-F11-1/tp/pull/86)
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/JThh/ped/issues/1), 
    [2](https://github.com/JThh/ped/issues/2), [3](https://github.com/JThh/ped/issues/3))

* **Tools**:
    * JavaFX BorderPane, ScrollPane, VBox.
