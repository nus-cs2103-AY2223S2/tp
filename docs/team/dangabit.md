---
layout: page
title: Dangabit's Project Portfolio Page
---

### Project: ModTrek

ModTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI).
The app is provides a convenient platform for students to easily access and update their modules within presses of a keyboard.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=dangabit&breakdown=true)

* **Enhancements implemented**:

  * **New feature:** Degree Progress Data
    * What it does: Calculates the data (GPA, requirements progress, overall progress) relevant to a person's degree based on the modules data it currently have.
    * In-depth: Calculation is done by following the NUS way of calculation. Special algorithm is developed to account for multi tagged modules. This also detects some impossible cases in user's module list.
    * Highlights: Algorithm for calculation of all the relevant data, factory method to generate based on module list.

  * **New feature:** View command
    * What it does: Toggles the GUI between degree progress view and module list view. User inputs `view <screen>`, which the parameter is parsed and information is passed to the GUI on which screen to display.
    * Justification: Worked with jmestxr and Cheamybunny to discuss about the ways to toggle between the GUI screens. Settled on passing the information through `CommandResult`. Some commands also used this new information passing.
    * Highlights: Implemented the `view` function by creating the `ViewCommandParser`, `ViewCommand` classes and enhancing `CommandResult` to facilitate information passing to GUI.
  
  * **Additional work:** Model Creation
    * What it does: Models and abstracts the different parts of the `Module`.
    * Justification: Most of the models `Code`, `Credit`, `Grade`, `Module`, `SemYear`, `Tag`, `ValidTag` are created with their appropriate validity checkers in place. The acceptable range of each model is made to be as precise as possible in the context of NUS.
    
* **Notable Bug Fix**:

  * **Fix:** Saving state of Find
    * What happened: On any update to the full list of modules, find module screen will also revert back to showcase the full list of modules.
    * Discussion: Worked with Jun-How to provide a savable state of find for the find screen by saving the last modified predicate.

  * **Fix:** Negative degree progress
    * What happened: Due to multiple mapping of modules, my initial algorithm to calculate the duplicates resulted in negative percentage for overall progress.
    * Discussion: Worked with Cheamybunny to create another algorithm to circumvent this problem. More details on the calculation can be found at (to be added, link to DG).

* **Documentation**:
  * User Guide:
    * Included some new commands (`sort` and `view`) and its specification.
    * Added user stories relevant to the commands.
  * Developer Guide:
    * Added Glossary of common terms used.
    * Added draft for `sort` command.

* **Contributions to team-based tasks**:

  * Organised weekly team meeting and facilitated discussions.
  * Set up master branch protection.
  * Worked with all teammates to liaise and integrate all the features seamlessly.
  * Provided ideas when discussing implementation with regards to `Model`.

* **Review/mentoring contributions**:

  * Reviewed PRs (eg. [#38](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/38), [#68](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/68), [#91](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/91))
  * Suggestions of implementation and improvement of features (eg. [#62](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/62), [#66](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/66))
  * Bug catching (eg. [#42](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/42), [#57](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/57), [#70](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/70))

* **Contributions beyond the project team**:

* Contributed to [PE-D bug catching](https://github.com/dangabit/ped/issues)
