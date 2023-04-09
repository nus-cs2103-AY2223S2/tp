---
layout: page
title: Jyothika's Project Portfolio Page
---

### Project: ModTrek

ModTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI).
The app is provides a convenient platform for students to easily access and update their modules within presses of a keyboard.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cjyothika&breakdown=true)

* **Enhancements implemented**:

    * **New feature:** Tag command that deletes and adds multiple tags
        * See PR [#43](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/43)
        * What it does: Deletes user specified tags from a specific module
        * Justification: This feature allows users to add or delete multiple tags of modules with one command instead of having to use the tag command multiple times or re-list the existing tags of a module when using the edit command
        * In-depth: Modifies the tags of a module with `Module::getModifiableTags` to retrieve a modifiable Set of Tags that the Module object has in Module list if the Module list has the specified module.
        * Highlights: Implemented an extension to the `tag` function in the same class with a new module object method.

    * **New feature:** Sort command 
        * See PR [#107](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/107)
        * What it does: Groups modules in the module list by category for the user to view
        * Justification: This feature allows users to have flexibility in the order and grouping by which modules are shown to them on UI instead of by only chronological year-semester order
        * In-depth: `UniqueModuleList::sortModules` iterates through the module list and adds module objects to module lists of their specified category which are retrieved by key (String object of that category) in a TreeMap to pass to the UI through the `UniqueModuleList::getModuleList` method to show to the user
        * Highlights: Implemented the `sort` function by creating the `SortCommandParser`, `SortCommand` classes and enhancing `UniqueModuleList` to return TreeMap<Object, ObservableList<Module>> objects in the `getModuleGroups` method to the UI where module lists are split into categorical groups for the UI to show to the user.
        
    * **Enhancement to existing features:** Update Add, Delete, Edit, Find command classes of AB3 to add, delete, edit and find modules in module list
        * See PR [#38](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/38) 
        * What it does: Adds, deletes, edits and finds single modules by module code in the module list
        * In-depth: Adapt logic and test code of AB3 to new MODTrek objects and change `equals()` method of module object and add a method `UniqueModuleList::hasModule` so that duplicate modules can be checked for and prevented

* **Documentation**:
    * User Guide:
        * Edited tone of UG to be friendlier and removed some typos (See PR [#197](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/197))
        * Added some clarifying notes on validity of module codes and year-semester user inputs (See PR [#167](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/167))
    * Developer Guide:
        * Wrote the implementation of the Delete Command feature and designed the sequence and activity diagrams
        * Added some use cases, user profile, NFRs and user stories (See PR [#20](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/20))
        * Edited AB3 diagrams to remove traces of AB3 in Developer Guide (See PR [#114](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/114))

* **Community**:
   * Reviewed PRs (Examples: [1](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/192), [2](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/195), [3](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/84))
   * Authored issues regarding improvements to application (Examples: [1](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/47), [2](https://github.com/AY2223S2-CS2103T-T13-1/tp/issues/53))
   * Contributed to [PE-D bug catching](https://github.com/cjyothika/ped/issues)
  
* **Tools**:
    * Java (for code)
    * PlantUML (for diagrams)
