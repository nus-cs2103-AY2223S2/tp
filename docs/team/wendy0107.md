---
layout: page
title: Wen Li's Project Portfolio Page
---

### Project: TeachMeSenpai

TeachMeSenpai **is a student managing application** specially customised for **teaching assistants** who have a lot of
students to keep track of. It is tailored to assist the user in monitoring their students' progress, and details. TeachMeSenpai is optimised for fast-typists with a **Command Line Interface (CLI)** so users can easily edit their student's details, with the benefits of a **Graphical User Interface (GUI)** so users can conveniently view their student entries.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=wendy0107&breakdown=true)

* **Enhancements implemented**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Made phone number an optional field for `add` and `edit` (Pull request [\#42]())
  * Created the `Subject` class to include the details of a student's subejcts (Pull request [\#54]())
  * Added icons to Person Card (Ui) (Pull request [\#64]())
  * Implemented the Ui to support the `show` command and fixed bugs on unexpected behaviour of Ui (Pull request [\#94](), [\#98]())
  * Improved the Ui to support the empty optional fields as well as differentiate the tag colours between education, subject and tags (Pull request [\#103]())
  * Implemented the `undo` and `redo` feature (Pull request [\#120]())
  * Improved the `remark` feature by including a keyboard shortcut `Ctrl + Z` to exit the remark window (Pull request [\#121]())
  * Updated Ui with an app icon and new icons for student details, and truncated remarks shown in the student list (Pull request [\#122]())
  * Add sample data for remarks (Pull request [\#138]())
  * Update Ui to support `Telegram` field for student entries (Pull request [\#145]())
  * Fixed bug for `show` where the Ui does not update when `show` is used on newly added student entries (Pull request [\#189]())
  * Fixed `undo` `redo` bug where the student list updates irregularly when used in succession and removed some unused code (Pull request [\#206]())
  * Fixed `add` and `edit` bug where duplicate students are allowed to be added due to case-sensitve naming and made students unique by telegram handle, phone number
  and case-insensitive name (Pull requests [\#207](), [\#209]())
  * Fixed bug for `show` where editing a shown student's name results in the entry disappearing (Pull request [\#212]())
  * Fixed bug for `show` where deleting a shown student's name doesn't remove it from the shown list (Pull request [\#212]())
  * Fixed test cases (Pull request [\#262]())


* **Documentation**:
  * User Guide:
    * Update the command and prefix summary (Pull requests [\#104](), [\#108]())
    * Improved navigability and user-friendliness by adding a table on parameters, markdown summary, and an About section (Pull requests [\#106](), [\#109](), [\#111]())
    * Fixed broken hyperlinks (Pull request [\#108]())
    * Updated the user guide with features `undo`, `redo`, `clear` and updated the website landing page (Pull request [\#152]())
    * Fixed broken hyperlinks, typos, and issues with markdown rendering and images not loading (Pull requests [\#207](), [\#208]())
    * Update UG based on v1.4 updates (with bug fixes, feature flaws, documentation bug fixes, new appendix for editing data file) (Pull requests [\#238](), [\#243](), [\#303](), [\#306]())
  * Developer Guide:
    * Added UML diagrams, implementation details and design considerations for `add`, `edit`, `delete`, `find`, `list` (Pull requests [\#66](), [\#79](), [\#99]())
    * Updated the Ui, model, storage, and student class diagram. (Pull requests [\#66](), [\#79](), [\#99]())
    * Included proposed feature implementations for `undo`, `redo`, `delete` by multiple indexes, `find` by fields, and `sort` (Pull requests [\#66](), [\#79](), [\#99]())
    * Updated the About section, improved navigability with table of contents and hyperlinks (Pull request [\#99]())
    * Updated the proposed feature implementation for `undo` and `redo` (Pull request [\#206]())
    * Update DG with planned enhancements (Pull requests [\#296](), [\#271](), [\#324]())
    * Updated UML class and sequence diagrams (Pull requests [\#272](), [\#308]())
    * Added Appendix on effort (Pull request [\#324]())
    * Added manual testing for add, edit and show (Pull requests [\#315](), [\#316]())

* **Contributions to team-based tasks**:
  * Set up the GitHub team organisation and repo, codecoverage and issue tracker
  * Created the Ui mock up (Pull request [\#33]())
  * Changed product icon
  * Managed releases for v1.2, v1.3
  * PRs reviewed (with non-trivial review comments): [\#74](), [\#102](), [\#151]()
  
