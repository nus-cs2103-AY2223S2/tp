---
layout: page
title: Joel Low's Project Portfolio Page
---

### Project: MODcheck

MODcheck is a desktop app to allow students to easily check the module coordinators, professors and teaching 
assistants in the modules they are taking. 

Given below are my contributions to the project.
________________________________________________________________________________________________________________________
#### Features added

##### Undo/Redo feature

* **What it does**: Allows user to use `undo` to reverse a previous command.

* **Justification**: This allows users to much more easily reverse a change made to ModCheck. As ModCheck commands 
  mostly use textual input, it is easy to make a typo in a command. It would be a hassle for users to have to 
  manually reverse an erroneous command (especially in case of `clear`, `load`, etc). Rather, the `undo` command 
  allows users to easily revert ot a previous state.

* **Highlights**: The challenge of the `undo` feature lay in the complexity of its interactions with other commands. 
  Many possible scenarios have to be considered, for example, what happens when a modification is made while the app 
  is in an undone state. Support for chained `undo` and `redo` commands is also difficult to implement as the 
  version tracking had to work with only 'modification' commands, and had to automatically clear the history past 
  the undo limit.

##### Load feature
* **What it does**: Allows users to `load` a selected data file into ModCheck.

* **Justification**: As the target users of ModCheck are students, this feature allows module coordinators to send 
  students a data file of important module contacts, which the student will be able to load easily, without 
  disturbing their current contacts. 

* **Highlights**: The challenge of the `load` feature lay in figuring out how to use JavaFX's FileChooser, while 
  maintaining the integrity of the [architecture](..%2FDeveloperGuide.md). The FileChooser object requires a 
  Stage object as input, which exists in the Ui component, but the command is only parsed in the Logic 
  component, which does not have access to the Stage object. 
  
  To implement the load command, I had to use an exception to communicate between the Ui and Logic components. This 
  allowed the FileChooser to be shown during execution of the command, yet still minimising coupling between Ui and 
  Logic components (compared to the alternative of passing the Stage object as a parameter into Logic). 

________________________________________________________________________________________________________________________
* **Documentation**:
    * User Guide:
        * To be added soon
    * Developer Guide:
        * To be added soon

* **Contribution to team tasks**:
    * Set up zoom sessions for team meetings 
    * Created team PR to upstream repo
    * Added screenshots of V1.2 and V1.3 to project notes document
    * Reviewed and commented on pull requests to the team repo
    * Authored and commented on issues in the team repo

* Notable links to PRs reviewed 
    * https://github.com/AY2223S2-CS2103-F10-3/tp/pull/63
    * https://github.com/AY2223S2-CS2103-F10-3/tp/pull/68
    * https://github.com/AY2223S2-CS2103-F10-3/tp/pull/88
    * https://github.com/AY2223S2-CS2103-F10-3/tp/pull/89
    * https://github.com/AY2223S2-CS2103-F10-3/tp/pull/92
* Notable links to issues created/commented on
    * https://github.com/AY2223S2-CS2103-F10-3/tp/issues/9
    * https://github.com/AY2223S2-CS2103-F10-3/tp/issues/59
    * https://github.com/AY2223S2-CS2103-F10-3/tp/issues/73
    * https://github.com/AY2223S2-CS2103-F10-3/tp/issues/74
    * https://github.com/AY2223S2-CS2103-F10-3/tp/issues/90
    * https://github.com/AY2223S2-CS2103-F10-3/tp/issues/61
    * https://github.com/AY2223S2-CS2103-F10-3/tp/issues/60
________________________________________________________________________________________________________________________
* **Link to RepoSnese Report**: https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=joellow88&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other
