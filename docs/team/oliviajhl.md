---
layout: page
title: Jiahui's Project Portfolio Page
---

### Project: FitBook

FitBook is a desktop application designed for tracking the progress and information.
It is built using Java and features both a Command-Line Interface (CLI) and a 
Graphical User Interface (GUI) created with JavaFX. The CLI allows users to interact 
with the application using text-based commands, while the GUI provides a visual interface 
for navigating the application's features. With FitBook, fitness professionals can easily 
track their clients' progress, manage appointments, and customize routines to help their 
clients reach their fitness goals.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed releases `v1.3` - `v1.5rc` (3 releases) on GitHub

* **Enhancements to existing features**:
    * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
    * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
    * User Guide:
      * Added documentation for `Quick Start` [\#17]()
      * Added documentation for `User Interface Introduction` [\#60]()
      * Added documentation for `Prefixes for Client Commands` [\#143]()
      * Added documentation for `Prefixes for Routine Commands` [\#166]()
      * Added documentation for the features `listClients` [\#196]()
      * Added documentation for `FAQ` [\#605]()
      * Added documentation for `Command Sumamry` [\#634]()
      
  * Developer Guide:
      * Added implementation details of the `delete` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
    * Reported bugs and suggestions for other teams in the class (examples: [PE-DRY-RUN](https://github.com/OliviaJHL/ped))
