---
layout: page
title: Joy Tan QiaoTong's Project Portfolio Page
---

## Project: Le Tracker

**Le Tracker** makes it easy to measure your overall study progress by tracking how much lecture content you have covered across various modules. **More** than just a simple to-do list app, **Le Tracker** blends the **efficiency** of command line interface with the **elegance** of modern graphical user interface.

## Summary of Contributions

### Code Contributed

Refer to my individual code contributions [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=joytqt-1202&tabRepo=AY2223S2-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

#### Delete

**Originally**, the delete command was implemented for person contacts by index in list.

**Enhancements** performed were to update person contacts to match the team's defined Module > Lecture > Video structure, allowing deletion of module, lecture and video from their specific contexts in Le Tracker. See [here](https://github.com/AY2223S2-CS2103-F10-2/tp/pull/106).

Further enhancements were made to allow the acceptance of multiple modules, lectures and videos to be accepted in a single command to delete the listed entities that fall in the same context in one command call. See [here](https://github.com/AY2223S2-CS2103-F10-2/tp/pull/201)

Update delete command to be in sync with team's event system listener.

#### Clear

Update clear command to be in sync with team's event system listener.

### New Features

#### Mark and Unmark

Allow users to mark videos in lecture of modules as watched or unwatched to better keep track of their progress. See [here](https://github.com/AY2223S2-CS2103-F10-2/tp/pull/110)

Allow the marking of multiple videos in a single command for ease of use. See [here](https://github.com/AY2223S2-CS2103-F10-2/tp/pull/221)

Update `mark` and `unmark` command to be in sync with team's event system listner.

### Contributions to UG and DG

Updated UG and DG documentation for the following commands:
- `delete`
- `mark`
- `unmark`
- `clear`

DG documentation includes:
- Implementation
- User stories
- Use cases
- Glossary
- Manual testing instructions
- Feature flaw for `unmark`

### Contributions to Team-Based Tasks

- Created v1.4 Milestones
- Created various v1.4 Issues
- Bugs reported: [link](https://github.com/AY2223S2-CS2103-F10-2/tp/issues?q=is%3Aissue+author%3Ajoytqt-1202+label%3Atype.Bug+)
