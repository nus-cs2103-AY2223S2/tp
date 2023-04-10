---
layout: page
title: Shirshajit's Project Portfolio Page
---

### Project: FastTrack

FastTrack is an expense tracking app that helps computing students keep track of their expenses by providing a simple and convenient command-line interface. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FastTrack can get your expense management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

### Summary of Contributions

- **New Feature**: `Recurring Expenses`: Created the recurring expense functionality

  - What it does: Creates recurring expenses for the user, i.e., expenses that occur at regular intervals. Theese expenses are automatically generated based on the user's input and current date. The user can also view all recurring expenses, delete recurring expenses, and mark recurring expenses as done.
  - Justification: This feature improves the product significantly because a large number of users would prefer to track their recurring expenses. This feature also allows the user to save time by not having to manually create recurring expenses.
  - Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands and the logic to handle the recurring expenses.

- **New Feature**: `Category` and `Expense` models: Created the main Category and Expense models.

  - What it does: The Category model is used to store the different categories of expenses that the user can add. The Expense model is used to store the different expenses that the user can add.
  - Justification: This feature improves the product significantly because it allows the user to add expenses and categorize them. This feature also allows the user to save time by not having to manually create recurring expenses.
  - Highlights: This enhancement affects existing commands and commands to be added in the future. It required an in-depth analysis of design alternatives. The implementation was challenging as it required changes to existing commands and the logic to handle the recurring expenses.

- **Code Contributions**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=shirsho-12&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

- **Project Management**:
  - Managed releases `v1.1` - `v1.4` (3 releases) on GitHub
  - Managed issue creation and assignment, milestone creation, issue labelling, and issue closing
  - Enforced coding standards and code quality
  - Managed the project's [issue tracker](https://github.com/AY2223S2-CS2103T-W09-2/tp/issues)
  - Managed the project's [pull requests](https://github.com/AY2223S2-CS2103T-W09-2/tp/pulls)
  - Managed the project [repository](https://github.com/AY2223S2-CS2103T-W09-2/tp) with the help of Isaac,[@gitsac](https://github.com/gitsac/), and Nicholas, [@niceleejy](https://github.com/niceleejy/).

**Enhancements implemented**:

- Implemented the recurring expense generation functionality
- Developed Expense Type enums for the different frequency types of recurring expenses
- Implemented functionality to strip out additional whitespace in user input

**Contributions to the UG:**

- Added documentation for the Category and Expense models
- Added documentation for the recurring expense functionality

**Contributions to the DG:**

- Created Activity Diagrams for all the commands
- Created UML Diagrams for the Category and Expense models, Commands, and the Parser
- Designed the architecture diagrams for the project

**Contributions to team-based tasks:**

- Managed the setting up of the project repository and test suite
- Created test cases for multiple commands, models, and storage
- Reviewed and merged multiple pull requests
- Redesigned the architecture of the Command classes to make it more extensible
- Fixed a number of bugs in the parser and storage classes
- Fixed test cases that were failing due to changes in the codebase
- Refactored the codebase to improve code quality

**Review/Mentoring Contributions:**

- Reviewed and provided feedback on multiple pull requests
- Reviewed and provided feedback on multiple issues
- Reviewed and provided feedback on multiple code quality issues

**Contributions beyond team project:**

- Reported bugs and suggestions for improvement for other team projects
- Participated in the discussion forum and helped other students with their queries
