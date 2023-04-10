---
layout: page
title: Leow Li Yong's Project Portfolio Page
---

### Project: AutoM8

AutoM8 is a **desktop app for an auto repair shop, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AutoM8 can get your auto repair shop management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Added the IdGenerator class: [\#49](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/49)
    * Manages the auto generation of unique auto-incrementing IDs for the different entities in the application.
    * Justification: Entities each are identified using a unique ID so it is crucial to ensure that IDs generated are unique.
    * Consideration: Was a class with 100% static methods initially, but was changed to non-static after abstracting away data manipulation to the `Shop` class. Implmentation details can be found in the [Developer Guide](../DeveloperGuide.md).

* **New Feature**: Added the ability to add entities to the Shop: [\#70](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/43)
* **New Feature**: Sorting Entities: [\#92](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/92)
  * Entities can be sorted via a user specified parameter such as ID, name, email, date, etc.
  * Justification: Sorting entities allows the user to view the entities in a more organized manner.
  * Consideration: This was done via updating `SortedList` wrappers around the raw data with comparators.
* **New Feature**: Finding Entities: [\#108](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/108)
  * Entities can be found via user specified keywords. Entities matching the keywords will be displayed.
  * Justification: This allows the user to view specific entities matching certain keywords.
  * Consideration: This was achieved by having each class implement a `Findable` interface which facilitates checking for the presence of keywords in the entity. More details can be found in the [Developer Guide](../DeveloperGuide.md).
* **New Feature**: `Shop` API: [\#150](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/150)
  * Modified the `Shop` class to expose an API for data manipulation.
  * Justification: Commands were managing data relationships, calls to IdGenerator, and data manipulations directly. This was not ideal as it violated the Single Responsibility Principle.
    It was difficult to manage and almost impossible to debug as potential causes were spread over multiple classes and packages.
  * Highlights: Shifted all the data manipulation logic to the `Shop` class. The `Shop` class would automate all the data relationships and Id generation. This allowed the commands to focus on the user input and output, and would only have the responsibility of deciding what `Shop` API to call.
    A large variety of known bugs were fixed after this refactoring and many undiscovered bugs were also fixed.
  * Consideration: Implementing the API required slowly considering all entity relationships and data manipulations. It was challenging to ensure that every API call had no logical errors and could only throw checked exceptions.
    However it was still significantly easier to manage and debug than the previous implementation. More implementation details can be found in the [Developer Guide](../DeveloperGuide.md).
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=ginloy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)
* **Enhancements to existing features**:
  * Added `ParserUtil` functions to support parsing integers and other AutoM8 specific types.
  * Modified original AB3 `list` command to work with all AutoM8 entities at the same time.
* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub
* **Documentation**:
    * User Guide:
        * Added documentation for the features `addX`, `find` and `sortX`
    * Developer Guide:
        * Added implementation details of the `add` feature.
        * Added implementation details of the `IdGenerator` class.
        * Added implementation details of the refactored `Shop` class.
        * Modified existing undo/redo documentation to reflect the new implementation.
* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#93](https://github.com/AY2223S2-CS2103-W17-4/tp/pull/93)
