---
layout: page
title: Haiqel's Project Portfolio Page
---

### Project: MODcheck

MODcheck is a desktop app to allow students to easily check the module coordinators, professors and teaching 
assistants in the modules they are taking. 

Given below are my contributions to the project.

* **New Feature**: Added Login Feature to allow the user to secure MODCheck with their own password.
  * What it does: allows the user to create a password to prevent other unwanted users from using MODCheck. The user is able to skip creating a password and use MODCheck without any password.
  * Justification: This feature improves the security of MODCheck as the user might not want anyone else to access the contacts stored in MODCheck and the contacts stored will be private to the user.
  * Highlights: This enhancement affects how the user will potentially interact with MODCheck as a first time user and after subsequent usage. 
                It required extensive analysis and code tracing of existing implementation of AB3 so that the Login feature adheres to coding standards set by previous students in AB3.
                The implementation was extremely challenging as it was the first time that I had learnt very deeply on how to use JavaFX and FXML effectively to create UI. 
                Not only that, implementing the UI requires knowledge on the Observer Design Pattern as I had to handle multiple scenarios when the user clicks or enter text in the UI which was not easy.
                The most difficult part was learning how the original AB3 codebase worked so that my login feature can follow the same design patterns and architecture design.
  * Credits: [Jakob Jenkov's tutorial website](https://jenkov.com/tutorials/javafx/index.html) for learning JavaFX and FXML
  
* **New Feature**: Added a filter command with name, phone number, email addresses, description and tag parameters.
  * What it does: allows the user to filter contacts based on different parameters given.
  * Justification: This feature will make the user's experience easier when navigating MODCheck as MODCheck may have a lot of contacts and the user
                   is able to filter the desired contacts easily in a single command.
  * Highlights: This feature was somewhat challenging as I had to learn how other existing features were implemented and follow the developer guide extensively.
                It was also the first time for me experiencing writing quite a few test cases to ensure that my feature works as expected. 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=Acerizm&tabRepo=AY2223S2-CS2103-F10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Documentation**:
    * User Guide:
        * Added documentation for filter command
        * Added documentation for Login feature
    * Developer Guide:
        * Added further documentation for Ui Class Diagram, Model Class Diagram and Storage Class Diagram
        * Added documentation for filter command 

* **Contributions to team-based tasks**:
    * Created the [MODCheck repo](https://github.com/AY2223S2-CS2103-F10-3/tp)
    * Added CodeCov
    * Updated the [ReadMe](https://github.com/AY2223S2-CS2103-F10-3/tp/blob/923f49aa6c5552f5b45aa6bbe279f54ea1fd84f2/README.md) documentation
    * Added essential labels to be assigned for issues
    * Protected the master branch of [MODCheck's repo](https://github.com/AY2223S2-CS2103-F10-3/tp) with custom settings where each pull request has
        to be approved by at least 1 team member (including myself to prevent abuse of repo ownership)
    * Assigned team members to multiple issues/pull requests with the correct labels/milestones 
* **Review/mentoring contributions**:
    * PRs reviewed extensively on other teammates forks and branches: 
  [#11](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/11)
  [#15](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/15)
  [#17](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/17)
  [#18](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/18)
  [#19](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/19)
  [#21](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/21)
  [#36](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/36)
  [#37](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/37)
  [#41](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/41)
  [#42](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/42)
  [#43](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/43)
  [#50](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/50)
  [#53](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/53)
  [#58](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/58)
  [#104](https://github.com/AY2223S2-CS2103-F10-3/tp/pull/104)
    * Helped teammates when they encounter difficulties in general through Telegram

