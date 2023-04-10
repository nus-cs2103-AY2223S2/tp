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
  * Highlights: During changing the codes, I also had a good look at the code pieces. This helps me know more about the
structure of this project, and write codes fitting the structure and running correctly. Moreover, I knew that what changes my
teammates made so that I can write codes with a style similar to them to make the codes more readable.


- **Modified Testing**: Changing the codes for testing Commands [#98](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/98) [#113](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/113)
  * Changing the test codes for Commands and the files in `testutil`
  * Justification: To test and ensure that all commands work as expected and the entire code can pass CI.
  * Highlights: This is also part of viewing the codes. During writing the codes, the test part is forgotten by us at first.
Because of this, we are unable to pass CI in Github. Knowing this, we start to modify testing. After learning the tests from
AddressBook level-3, I know clearly what should be tested in our CookHub.


- **New Feature**: Adding `Star` `Unstar` and `Favorites` commands [#139](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/139)
  - Use: `Star` is used to mark certain recipes as in the `favorites`. `Unstar` is to move certain recipes out of `favorites`. `Favorites` shows the `favorites` list.
  - Justification: These commands mean to help users mark and find their favorite recipes faster.
  - Highlights: After starring a recipe, a star will be shown after the title of the starred recipe, requiring UI changes.
For the `favorites` command, certain recipes which have stars will be shown, and a predicate of having star is implicitly created
to help filtering the recipes.


- **Enhancements in existing features**: Fixing bugs coming from PE-D [#218](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/218)
  1. Long messages in result box need scrolling in two directions.
  2. Long tag names can only be seen parts.
  3. In one recipe, steps with the same content will have the same index number shown in UI.
  
  - Highlights: Those three bugs are mainly about UI which I'm not quite familiar with even after learning the JavaFX tutorial.
However, after understanding more about the UI part, and with my own coding experience, I managed to solve them all.


- **Documentation**:
  - User Guide:
     * Initial v1.1 skeleton
     * Add command examples and results pictures [#164](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/164)
     * Remake the command examples and results pictures after decimal points issues are solved [#249](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/249)
  - Developer Guide:
     * Initial v1.1 skeleton
     * Update Logic part (diagrams and words) [#116](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/116)
     * Add Implementation information and sequence diagrams for `star` and `favorites` commands [#272](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/272)


- **Community**: 
  - PR reviews [#96](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/96)
[#238](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/238)
[#240](https://github.com/AY2223S2-CS2103T-W09-1/tp/pull/240)
  - Found 8 bugs in team [CS2103T-W15-1](https://github.com/AY2223S2-CS2103T-T12-1/tp)'s product during PE-D.
