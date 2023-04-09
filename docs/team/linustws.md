---
layout: page
title: Linus Tan's Project Portfolio Page
---

### Project: CoDoc

CoDoc is a desktop contact management application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20.8k LoC.

##### Summary of Contributions

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=linustws&tabRepo=AY2223S2-CS2103T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
* **Major Enhancement**: Boosted `find` command. [#74](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/74), [#82](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/82), [#94](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/94)
  * What it does: Allows users to find contacts by their year and course. 
  * Justification: Enhances the search ability of users, so they can find contacts based on their year and course, on top of other attributes, enabling them to reach out to them for collaboration quicker and more effectively. This would be useful, especially when the user has many contacts.
  * Highlights: 
    * Made finding to find by logical AND, both across different prefixes and within the same prefix. This follows the typical convention that websites use for their filters, e.g. Shopee and YouTube.
    * Made it such that finding uses _contains_ instead of _containsWord_ as it is more commonly used, e.g. `Cmd-F`/`Ctrl-F` also uses _contains_.
    * Made `find` command case-insensitive to allow users to search faster without having to press the `Caps Lock` key.
* **New Feature**: Implemented clicking of tabs and PersonCard. [#119](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/119)
  * What it does: Allows users to click on the PersonCard in the PersonListPanel to view the details on the right info panel and allows users to change tabs by clicking.
  * Justification: Adds convenience for users on top of the `view` command.
  * Highlights:
    * Had trouble implementing things like FireEvent/CatchEvent/EventDispatcherChain to handle events, but managed to work around it, though not the best solution.
    * Was later improved with [Harin](https://ay2223s2-cs2103t-f12-2.github.io/tp/team/harin0826.html)'s fix. [#129](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/129)
* **New Feature**: Added default profile pictures. [#119](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/119)
  * What it does: Improves aesthetics of CoDoc, can allow users to identify contacts by specific profile pictures as well.
  * Justification: As much as we wanted to allow the user to be able to add their own preferred profile pictures, this allows each contact to have a semi-unique (allows duplicates) profile picture the moment it is added, eliminating the need for the user to add it manually every time.
  * Highlights:
    * Profile picture path is saved as a person's attribute.
    * They are randomly picked from a pool of 50 pictures when the person is added.
  * Credits: The pool of 50 pictures was sourced from here: https://www.flaticon.com/packs/animals-123
  
* **Enhancements to existing features**:
  * Modified `Tag` to `Skill` to fit CoDoc's value proposition better. [#46](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/46)
  * Modified `Phone` to `Github` with relevant constraints, made optional. [#56](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/56), [#58](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/58)
  * Modified `Address` to `Linkedin` with relevant constraints, made optional. [#56](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/56), [#64](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/64)
  * Modified `PersonListCard` and rearranged the details to improve user visibility. [#119](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/119)
  * Added scroll to bottom when `Person`added so user can see its index for viewing. [#160](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/160)
  * Created a new set of sample data that are relevant and complete, i.e. persons with valid attributes and lists of realistic modules and skills, to make it for appealing for users. [#119](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/119)
  * Minor improvements and bug fixes: [#107](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/107), [#122](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/122), [#126](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/126), [#127](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/127), [#135](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/135), [#147](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/147), [#170](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/170)

* **Documentation**:
  * User Guide:
    * Created _Navigating CoDoc_ and _Tutorial_ sections. [#138](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/138)
    * Updated pictures for the _Tutorial_ section. [#157](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/157)
    * Fixed and organised table of contents. [#143](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/143)
    * Fixed language errors. [#157](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/157), [#213](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/213)
  * Developer Guide:
    * Added details for `ProfilePicture`, `Github`, `Linkedin` and `Skill` attributes under _Person Class_. [#92](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/92), [#213](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/213)
    * Added all `find` command implementation details, including sequence diagram, activity diagram and design considerations. [#92](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/92), [#102](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/102), [#213](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/213)
    * Added _Appendix: Planned Enhancements_ section. [#213](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/213)

* **Project management**:
  * Updated project notes from time to time.
  * Sourced and modified Figma template for mock UI. [Figma](https://www.figma.com/file/6FyhHLhFrLIqt5TPRHkN58/CoDoc?node-id=0-1&t=ua9RFnI4eIA5ZkcT-0)
  * Updated README file. [#25](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/25)
  * Updated site-wide settings. [#28](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/28)
  * Renamed packages to remove anything related to AB3. [#64](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/64)

* **Community**:
  * Reviewed and commented on teammates' PRs. [PRs reviewed by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Alinustws), [My comments on PRs](https://nus-cs2103-ay2223s2.github.io/dashboards/contents/tp-comments.html#165-tan-inus-linustws-4-comments)
  * Opened and assigned issues to teammates and me. [Issues opened by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Alinustws), [Issues assigned to me](https://github.com/AY2223S2-CS2103T-F12-2/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Alinustws)
  * Merged teammates' PRs.
  * Reported bugs for other teams. [PE-D](https://github.com/linustws/ped/issues)
