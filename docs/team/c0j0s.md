---
layout: page
title: c0j0s's Project Portfolio Page
---

### Project: Duke Driver

Duke Driver - a delivery tasking and planning application used by delivery personnel from DUKE pte ltd. Duke Driver provides bulk management and personalised job listing for drivers. 

Given below are my contributions to the project.

* **New Feature**: Added Delivery Job System Storage.
    * What is does: Handle saving and loading of delivery job list from data files.
    * Justification: This feature allow delivery job data to be saved in a separate data file to avoid congesting the address book.
    * Highlights: Largely inline with address book storage structure with modification to json adapted classes to fit our custom delivery job model.
    * Credits: Address book storage.

* **New Feature**: Delivery Job List and Detail Pane in MainWindow.
    * What is does: Display a list of pending delivery jobs for user. Able to view the job detail when selected.
    * Justification: The user needs to see all the available jobs and its details.
    * Highlights: User can select to view the job detail by mouse or arrow keys.
    * Credits: MainWindow.

* **New Feature**: List Delivery Job Command.
    * What is does: List all the available jobs.
    * Justification: Core function.
    * Highlights: N.A.
    * Credits: ListCommand.

* **New Feature**: Add Delivery Job Command.
    * What is does: Adds a new delivery jobs.
    * Justification: Core function.
    * Highlights: Support GUI mode.
    * Credits: AddCommand.

* **New Feature**: Delete Delivery Job Command.
    * What is does: Delete a selected jobs.
    * Justification: Core function.
    * Highlights: User can select and delete a job using `del` key from the MainWindow.
    * Credits: DeleteCommand.

* **New Feature**: Edit Delivery Job Command.
    * What is does: Edit a selected jobs.
    * Justification: Core function.
    * Highlights: Able to select jobs using list index or job id. Support GUI mode.
    * Credits: EditCommand.

* **New Feature**: Find Delivery Job Command.
    * What is does: Find a selected jobs.
    * Justification: Core function.
    * Highlights: All attributes of `DeliveryJob` can be use as a command option for searching.
    * Credits: FindCommand.

* **New Feature**: Sort and Filter Job List.
    * What is does: Order job list.
    * Justification: Allows user to filter out completed or sort by high earning jobs.
    * Highlights: Support both ascending and descending mode.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=c0j0s&breakdown=true)

* **Project management**:
  * Setting up the GitHub team org/repo
  * Setup project dashboard.
  * Setup project milestones.
  * Add cache support for github actions.

* **Enhancements to existing features**:
  * Expandable result display box in contact and main window.
  * Command grouping support to control specific command groups in desired command locations.

* **Documentation**:
  * User Guide:
    * Added documentation for the delivery tasking management system features `list`, `add job`, `edit job`, `delete job` and `find job`.
  * Developer Guide:
    * Update diagram for `UI Componets`, `Model` and `Storage`.
    * Added use case for `list delivery job detail`, `delete job`, `edit job` and `find job`.
    * Added implementation details foe delivery job system in developer guide.
    * Added manual test cases for delivery jobs.

* **Community**:
  * Review PRs.
  * Forum discussions.
