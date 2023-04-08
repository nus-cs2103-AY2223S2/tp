---
layout: page
title: Jyothika's Project Portfolio Page
---

### Project: ModTrek

ModTrek is a desktop application for managing a typical NUS Computer Science student’s modules and degree progression, optimised for use via a Command Line Interface (CLI). We envision the app to be convenient so that students can easily access and update their modules within presses of a keyboard.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=cjyothika&breakdown=true)

* **Enhancements implemented**:

    * **New feature:** Tag command that deletes and adds multiple tags
        * See PR ![#43](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/43)
        * What it does: Deletes user specified tags from a specific module
        * In-depth: Modifies the tags of a module with `Module::getModifiableTags` to retrieve a modifiable reference set of Tags that the Module object has in Module list if the Module list has the specified module.
        * Highlights: Implemented an extension to the `tag` function in the same class with a new module object method.

    * **New feature:** Sort command 
        * See PR ![#107](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/107)
        * What it does: Groups modules in the module list by category for the user to view
        * In-depth: Iterates through the module list and adds module objects to module lists of their specified category which are retrieved by key (String object of that category) in a TreeMap to pass to the UI to show to the user
        * Highlights: Implemented the `sort` function by creating the `SortCommandParser`, `SortCommand` classes and enhancing `UniqueModuleList` to return TreeMap<Object, ObservableList<Module>> objects in the `getModuleGroups` method to the UI where module lists are split into categorical groups for the UI to show to the user.
        
    * **Enhancement to existing features:** Edit Add, Delete, Edit, Find command classes to accept new DegreeProgression Model instead of AB3's model
        * See PR ![#38](https://github.com/AY2223S2-CS2103T-T13-1/tp/pull/38) 
        * What it does: Adds, deletes, edits and finds single modules by module code in the module list
        * In-depth: Adapt logic and test code of AB3 to module objects and new MODTrek Model and change `equals()` method of module object so that duplicate modules do not arise

* **Documentation**:
    * User Guide:
        * 
    * Developer Guide:
        * to be added soon

* **Community**:
  to be added soon

* **Review/Mentoring Contributions**:
to be added soon
