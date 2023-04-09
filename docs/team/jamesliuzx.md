---
layout: page
title: James Liu Zixin's Project Portfolio Page
---

### Project: RIZZipe

RIZZipe is a desktop recipe book application used for busy cooks who want to track their recipes. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

**New Features**: 
* Created GUI forms for adding and editing recipes. (Pull Requests [\#117](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/117) [\#146](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/146))
    * What it does: It allows users to add/edit recipes easier without typing in the terminal.
    * Justification: It is a more intuitive and visually easier way of inputting/editing data, especially with longer recipes.
    * Highlights:
        * Additional keyboard and mouse listeners were also added, so hovering over or clicking on the recipe and pressing 'F' on the keyboard opens the form.
        * Careful consideration of good OOP design was used when implementing these new forms. Separate `event` classes were made to handle the deletion/editing of recipes. An abstract class `RecipeForm` was also made, with `EditRecipeForm` and `AddRecipeForm` inheriting from the parent class. 
    * Credits: [Filbert](filbertphang.md) for refactoring forms to conform to better OOP principles, [Seeu Sim](seeusim.md) for bugfixing the logic behind the forms.
* Created GUI Recipe Popup Cards for recipe details. Pull Request [\#117](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/117)
    * What it does: It allows users to view the full recipe details on a separate window. 
    * Justification: Current recipe list panel constricts each `RecipeCard` to a fixed size (based on window size), so recipe cards with longer recipe details are truncated and not very viewable in the main window screen.
    * Highlights:
        * Additional keyboard and mouse listeners were also added, so hovering over or clicking on the recipe and pressing 'P' on the keyboard opens the form.
    * Credits: [Seeu Sim](seeusim.md) for enhancing the FXML styling of `RecipePopup`. 
* Created GUI menu items for import/export. (Pull Requests [\#193](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/193) [\#146](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/146))
    * What it does: It provides users with a button and a keyboard shortcut to import or export recipes.
    * Justification: One selling point of our app is the ability to share recipes with friends in json files
    * Highlights:
        * Additional keyboard shortcuts were also added, namely `F3` for import and `F4` for export. 

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=jamesliuzx&breakdown=true)

**Project management**:
    * Managed releases [`v1.2` - `v1.3`](https://github.com/AY2223S2-CS2103T-T13-2/tp/tags) on GitHub

**Enhancements to existing features**:
* Refactored storage (Pull request [\#91](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/91))
* Improved styling of RecipeCard, forms and MainWindow. [\#146](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/146) 

**Documentation**:
* User Guide:
    * Added documentation for the feature `delete` [\#f8a7ea1](https://github.com/AY2223S2-CS2103T-T13-2/tp/commits/f8a7ea1)
    * Added table of contents and hyperlinks [\#2307c7f](https://github.com/AY2223S2-CS2103T-T13-2/tp/commits/2307c7f)
    * Added command fields format [\#261](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/261)  

* Developer Guide:
    * Added DG glossary [\#42]()  
    * Added UI section and diagrams[\#126]()

**Community**:
* PRs reviewed (with non-trivial review comments): [\#30](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/30), [\#35](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/35), [\#37](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/37), [\#75](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/75), [\#77](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/77), [\#127](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/127), [\#137](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/137), [\#138](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/138), [\#189](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/189), [\#245](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/245), [\#252](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/245), [\#270](https://github.com/AY2223S2-CS2103T-T13-2/tp/pull/270)
* Reported 12 bugs in the Practical Exam dry run: [Bug Report](https://github.com/JamesLiuZX/ped/tree/main/files)
