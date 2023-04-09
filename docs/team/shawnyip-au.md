---
layout: page
title: Yip-Au Hew Kit, Shawn's Project Portfolio Page
---

### Project: CoDoc

CoDoc is a desktop contact management application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20.8k LoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shawnyip-au&breakdown=true)
* **New Feature**: store/retrieve new attribute(modules) into/from a json file. [#50](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/50)
    * What it does: allow users to keep track of all modules and skills associated to a person in a non-volatile way.
    * Justification: we design CoDoc with collaboration in mind and the ability to store these two attributes will help users find the right people to collaborate with.
    * Highlights: had to study how the codebase handles storing and revival of data and then emulate it for the new fields while also making a stub as the modules component were implemented in parallel.
    * Credits: existing implementation of storage component from AB3.
* **New Feature**: find command for modules and skills. [#104](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/104)
    * What it does: allow users to filter out persons in their contact list that does not meet the specified requirement.
    * Justification: compared to scrolling, the find command is faster—one of CoDoc selling point—when it comes to searching for people.
    * Highlights: to facilitate the finding process, finding for modules and skills takes in any number of inputs after it with modules being a larger hurdle as it supports multi-semester inputs such as `find m/CS1101 AY2122S1 CS210T CS2101 AY2122S2 CS3230` where only those that have taken
      * `CS1101` regardless of semester, 
      * `CS210T` and `CS2101` in `AY2122S1`
      * `CS3230` in `AY2122S2` <br> 

      will be shown. Therefore, many test case were included to make sure that future implementation does not alter the expected behaviour.
* **New Feature**: stackable predicates for find command. [#109](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/109)
    * What it does: allow users to continuously search from the current filtered list.
    * Justification: allow users to append new predicate to all of their previously specified predicates thereby giving them the flexibility to decide how they wish to continue to filter their filtered list without the need to retype in everything.
    * Highlights: as all prefixes supported by find command had to be changed, the most naive option would have been to implement it individually for each prefix which would have made future expansion more cumbersome. After much examination of the code, a more ideal location was found—one that is independent of future expansion, should more attributes be included.
* **New Feature**: image support for jar file. [#167](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/167)
    * What is does: supports showing image when starting CoDoc from a jar file.
    * Justification: the hierarchy is different from our development environment thereby causing the application to work abnormally when launched from a jar file.
    * Highlights: Was able to convert CoDoc to use relative file path in an existing code base that highly depends on absolute file path with minimal changes to the code base.
* **Project management**:
    * Merged PRs by teammates
    * Resolve merge conflict
    * Provided feedback on PRs
    * Assigned issues
    * Point out bugs in conversations
    * Manual testing of CoDoc
* **Enhancements to existing features**:
    * Change test cases of storage component to reflect CoDoc's implementation
    * Minor Ui alignments [#124](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/124)
    * Found and resolved bug that crash the program upon clear command [#189](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/198)
    * Clean up code residues and remove magic variables [#227](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/227)
* **Documentation**:
    * User Guide:
        * Created tutorial section
        * Created "How to Open CoDoc for Mac" in additional resources section
        * `find` command documentation
        * Incorporate improvement suggestion from PE(Dry Run) [#205](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/205)
        * Proofreading, editing and conversion to PDF [#219](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/219)
    * Developer Guide:
        * Re-organise entire DG 
        * Created class diagram to reflect changes done for storage component
        * Update storage component text
        * Created sequence diagram for how data is stored and retrieved
* **Tools**:
    * Java
    * Gradle
    * Jackson
    * Junit5
