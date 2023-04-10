---
layout: page
title: William's Project Portfolio Page
---

### Project: Team Builder

Team Builder is a desktop personal contacts book application used by the user to form a team for any event. The user interacts with it using a CLI, and it has a GUI created with JavaFX. 

Given below are my contributions to the project.

* **Undo functionality**:
  * Justification:
    * Careless Users might want to undo their last command.
    * Rather than finding the reversing action, they can just use `undo`.
    * This is especially useful for users that accidentally use the `clear` command and would like to recover their contact list.

  * Highlights:
    * Able to undo up to 10 last commands.
    * Able to undo: `add, edit, delete, clear`

  * Credits:
    * Momento Design Pattern

* **Redo functionality**:
  * Justification:
    * Careless Users might want to redo their last undo.
    * Rather than finding the reversing action, they can just use `redo`.
    * This is especially useful for users that accidentally use the `undo` command after adding someone important and would like to recover their last contact.

  * Highlights:
    * Able to redo up to 10 last commands.

  * Credits:
    * Momento Design Pattern

* **Testing**:
  * Created tests for all new features implemented above.

{::comment}

* **New Feature**: `to be added soon`
  * Justification: `to be added soon`
  * Highlights: `to be added soon`
  * Credits: `to be added soon` *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

{:/comment}

* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=WillCWX&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByNone&breakdown=true&since=2023-02-17&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=WillCWX&tabRepo=AY2223S2-CS2103T-T17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
  * ~2697 lines

* **Project management**:
  * Created issues: [\#26](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/26), [\#51](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/51), [\#54](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/54), [\#55](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/55), [\#56](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/56), [\#103](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/103), [\#104](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/104), [\#105](https://github.com/AY2223S2-CS2103T-T17-1/tp/issues/105)
  * Created Demo v1.3 and v1.2

* **Documentation**:
  * README:
    * Added Badges and new UI.png
    * Removed all references to AB-3 for TeamBuilder
    * Added Acknowledgements for NUS AB-3 source code and
  for the SE-EDU initiative.
    * Added Installation guide
    * Linked User guide
    * Linked Developer guide and added contribution guidelines
    * Added Authors and linked to their github
    * Added futher acknowledgements for Microsoft Fluent-UI freeuse.

  * User Guide:
    * Removed all references to AB-3 for TeamBuilder
    * Added the sections `Undoing a command` and `Redoing an undo command` 
    * Created and implemented a narrative style from `About this guide` to `Deleting a contact` and also in `Undoing a command` and `Redoing an undo command`.
      * Added multiple photos, some css stylings
      * Added multiple `information`,`caution` and `tip` divisions for better style
    
  * Developer Guide:
    * Created the entire implementation detail for `Undo/Redo feature`
      * Mutiple UML models used, including class diagrams, sequence diagrams and an activity diagram.
    * Added to the manual testing appendix
    * Fixed links that were previously pointing to seedu

* **Tools**:
  * Integrated an existing plugin fully (CodeCov) on team fork

{::comment}

* **Community**:
  * PRs reviewed (with non-trivial review comments): `to be added soon` *{[\#12](), [\#32](), [\#19](), [\#42]()}*
  * Contributed to forum discussions (examples: `to be added soon`)
  * Reported bugs and suggestions for other teams in the class (examples: `to be added soon`)
  * Some parts of the history feature I added was adopted by several other class mates `to be added soon`

  * Integrated a third party library (Natty) to the project `to be added soon`
  * Integrated a new Github plugin (CircleCI) to the team repo `to be added soon`

_{you can add/remove categories in the list above}_

{:/comment}
