---
layout: page
title: Bo Kuan's Project Portfolio Page
---

### Project: *Fish Ahoy!*

*Fish Ahoy!* Is a **desktop app for managing your fish, fish tanks, and relevant tasks, optimized for use via a Command
Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). <br>

*Fish Ahoy!* **streamlines** the fish keeping experience by helping you keep track of your many **fishes**, **tanks** and
**weekly tasks**, such as feeding and cleaning.

Given below are my major contributions to the project.

* **New Feature**: Added the entire task interface feature, including feeding reminders, excluding task priorities
  * What it does:
    * Allows users to keep track of tasks and save them to their hard disk, viewing them again after opening *Fish Ahoy!*
    * Get feeding reminders automatically whenever they open the app and any tank have hungry fish
    * Also implemented feature where feeding reminder for a tank is deleted when `tank feed` command is used
  * Justification: This feature improves the product significantly because a user a major proportion of our user stories include allowing
users to keep track of their fish-keeping tasks. Also, a main feature of a fish keeping app should be keeping your fish alive
by reminding you to feed them.
  * Highlights: This was the first feature to be added, without reference to previous implementations within our own group.
This made the implementation more difficult as I could not reference the github diffs of previous feature PRs.
  * Credits: Task implementation was inspired by [SoConnect](https://github.com/AY2223S1-CS2103T-W15-1/tp) and fxml file was reused. However, our implementation is entirely new.

* **New Feature**: Added a backend of the whole tank readings feature excluding some basic model files that required further enhancement
  * What it does:
    * Allows users to keep track of aquarium environmental readings by adding and deleting readings and saving them to the hard disk
  * Justification: This is crucial for fish survival, as indicated by some user stories
  * Highlights: Storage architecture of this feature did not follow that of previous features like fish, tank or task. There is
one additional layer of abstraction where the complete readings are a list of individual tank readings, which then contain
each tank's respective ammonia level, pH and temperature readings. This reduces code needed and increases the ease of adding enhancements
like adding more types of readings in the future.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=bokuant&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management/ contributions to team based tasks**:
  * Checked, approved and merged the majority of PRs, handling conflicts and helped fix group mates' PRs which could not even
pass CI checks: [232](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/232), [73](https://github.com/AY2223S2-CS2103T-T17-4/tp/pull/73) - `v1.1 - v1.4`
  * Managed team's weekly dashboard tasks, ensuring they are marked as completed
  * Ensure project progress is on schedule

* **Enhancements to existing features**:
  * Refactored `Person` to `Fish`
  * Changed several attributes of persons to match attributes of fishes

* **Documentation**:
  * User Guide:
    * Wrote the entire draft of the user guide in doc format and transferred it to `md` format in `v1.1`
    * Rewrote the entire `features` section of every command to tackle documentation bugs surfaced in PE-D, adding examples and
more detailed usage (`v1.4`)
    * Added `using this guide` and `glossary` section in `v1.4`
    * Ensured that additional information (:information_source:), warnings (:exclamation:) and tips (:bulb:) are consistent
throughout the UG. Added several useful additional information, warnings and tips also in `v1.4`
  * Developer Guide:
    * Added implementation details of the feeding reminders feature.
    * Refactored many origial `.puml` files to align AB3 diagrams with *Fish Ahoy!*

* **Community**:
  * Posted in forum when github was down so `v1.3` deadline was extended [298](https://github.com/nus-cs2103-AY2223S2/forum/issues/298)
  * Helped team clarify `v1.4` feature freeze constraints [335](https://github.com/nus-cs2103-AY2223S2/forum/issues/335)

