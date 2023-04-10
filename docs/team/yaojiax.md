---
layout: page
title: Jiaxin's Project Portfolio Page
---

### Project: sprINT

**sprINT** is an **internship-tracking application** that was created to assist students in their internship hunt.

Students often face a great administrative burden in keeping track of the high volume of job or internship
applications. With sprINT, students can easily manage details of their internship applications, and enjoy functionalities
like sorting their application entries by deadlines, or finding a specific application with a keyword.

sprINT is written in Java, with the GUI built using JavaFX.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=yaojiax)


* **New Features**:
    * Implemented undo and redo feature
    * Please refer to issue [#91](https://github.com/AY2223S2-CS2103T-T13-3/tp/issues/91) or PR [#143](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/143)
    * **About:** This features allow for easy reversal of commands that may have been inputted incorrectly and also the ability to reverse these reversals.
    * **Benefits:** As our commands are inherently long due to the tasks and app distinction and the many possible fields that needs to be filled up, it makes sense to provide a feature for users to quickly revert to their previous 
  state. However, because this command might be used too loosely as it is very short, we provided a redo function too to supplement
  the user in case they input the undo as a mistake.
  * **Challenges:** This feature required the creation of a CommandHistory class, which required some changes to be made to all the existing command's code. Additionally, it required the creation of a VersionedInternshipBook which extends 
from the InternshipBooks, which can keep track of all the history. This requires that commands where undo needs to be supported be committed into the VersionedInternshipBook. Thus, I need to know clearly what the old code does and integrate into the new classes.

* **Project management**:
  * Merged 12 PRs [#50](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/50), [#51](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/51), [#58](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/58)
    , [#68](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/68)
    , [#120](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/120), [#140](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/140), [#141](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/141), [#175](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/175), [#249](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/249), [#258](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/258), [#259](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/259) and [#271](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/271).
  * Helped to assign milestones for some issues that were not initially assigned.
  * Helped assign labels to some issues

* **Enhancements to existing features**:

    **Implemented edit-app feature**
  * See issue [#42](https://github.com/AY2223S2-CS2103T-T13-3/tp/issues/42) or PR [#71](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/71)
  
* **Documentation**:
  * User Guide:
    * See PR [#57](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/57), [#59](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/59)
, [#148](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/148), [#169](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/169) and [#239](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/239)
    * Helped include instructions for redo/undo and edit-app
    * Added my commands into the summary section.
    * Wrote tips on how to achieve equivalent results as undo for commands without undo support.
  * Developer Guide:
    * See PR [#66](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/66), [#121](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/121)
and [#148](https://github.com/AY2223S2-CS2103T-T13-3/tp/pull/148)
    * Repurposed UML diagrams for undo/redo and edited the DG section on undo/redo in our software's context.
    * Created UML diagrams and wrote the DG section for edit-app command.
    * Wrote proposed enhancements for error handling for edit-app.


* **Team-based tasks**:
  * Refactor existing code for commands that made sense to have an undo feature. 

  
