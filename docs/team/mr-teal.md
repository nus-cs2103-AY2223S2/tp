---
layout: page
title: Ding Chiyu's Project Portfolio Page
---

### Project: CookHub

CookHub is a desktop application for student chefs to store and check their recipes, especially for those who only have limited budget and time to cook themselves.
It is written in Java and uses JavaFX to create GUI. 

The following are my contributions to this project:

- **Code contributed**: [Link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=mr-teal&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Modified Components**: Changing the codes in Commands when transforming Address Book Level-3 to CookHub. [#85](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/85) [#87](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/87)
  * This component contains all the classes for user commands. Each command has its own class to deal with user input and make changes to the recipe list.
  * Justification: To fit the codes to CookHub, all words like `person` `address book` for Address Book need to be changed to `recipe` `recipe book`.
As model is changed, commands classes also need changing.

- **Modified Testing**: Changing the codes for testing Commands [#98](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/98) [#113](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/113)
  * Changing the test codes for Commands and the files in `testutil`
  * Justification: To test and ensure that all commands work as expected and the entire code can pass CI.

- **New Feature**: Adding `Star` `Unstar` and `Favorites` commands [#139](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/139)
  - Use: `Star` is used to mark certain recipes as in the `favorites`. `Unstar` is to move certain recipes out of `favorites`. `Favorites` shows the `favorites` list.
  - Justification: These commands mean to help users mark and find their favorite recipes faster.

- **Enhancements in existing features**: Make the UI look better: result box display pattern, title/description/tag name display

- **Documentation**:
  - User Guide:
     * Initial v1.1 skeleton
     * Add command examples and results pictures [#164](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/164)
  - Developer Guide:
     * Initial v1.1 skeleton
     * Update Logic part [#116](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/116)

- **Community**: PR reviews [#96](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/96)
