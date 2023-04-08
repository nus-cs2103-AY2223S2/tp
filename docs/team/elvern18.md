---
layout: page
title: Elvern Tan's Project Portfolio Page
---

### Project: MODcheck

MODcheck is a desktop app to allow students to easily check the module coordinators, professors and teaching 
assistants in the modules they are taking. 

Given below are my contributions to the project.

* **Code contributed**: [RepoSenseLink](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=elvern18&tabRepo=AY2223S2-CS2103-F10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements to existing features**: DeleteMultipleIndexCommand
  * What it does: Allow users to delete contacts by listing multiple indexes.
  * Justification: This new command would help users delete contacts more quickly and conveniently, helping them save time, since users no longer have to delete using a single index multiple times.
  * Highlights: Have to refactor the original DeleteCommand into a parent class, and create another class DeleteSingleIndexCommand. It was tricky on how to accept valid indexes, and many scenarios have to be carefully thought. 

* **Enhancements to existing features**: DeleteByNameCommand
    * What it does: Allow users to delete contacts by their name.
    * Justification: This new command would help users delete contacts more quickly and conveniently, helping them save time, since users no longer have to slowly search for the index that the person is in. This is especially time-consuming when there are a large number of contacts in the list.
    * Highlights: Apart from having to refactor DeleteCommand, I had to think about how to handle the validity of the parsing of names, as it is no longer integers or indexes that I had to handle.

* **Enhancements to existing features**: EditByNameCommand
    * What it does: Allow users to edit contacts by their name.
    * Justification: This new command would help users edit contacts more quickly and conveniently, helping them save time, since users no longer have to delete using a single index multiple times.
    * Highlights: Have to refactor the original EditCommand into a parent class, and create another class EditByIndexCommand. It was tricky on how to handle the validity of the parsing of names, as it is no longer integers or indexes that I had to handle.


* **Documentation**:
    * User Guide:
        * DeleteSingleIndexCommand
        * DeleteMultipleIndexCommand
        * DeleteByNameCommand
        * EditByIndexCommand
        * EditByNameCommand

    * Developer Guide:
        * DeleteSingleIndexCommand's use case
        * DeleteMultipleIndexCommand's use case
        * DeleteByNameCommand's use case
        * EditByIndexCommand's use case
        * EditByNameCommand's use case
        * User stories
        * Non-Functionality Requirements
        * Glossary

* **Project management**:
    * To be added soon

* **Community**:
    * To be added soon

* **Tools**:
    * To be added soon

