---
layout: page
title: An Cheng Yang's Project Portfolio Page
---

### Project: CoDoc

CoDoc is a desktop contact management application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20.8k LoC.

Given below are my contributions to the project.

* **New Feature**: Edit command and edit command parser: [#48](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/48), [#66](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/66), [#141](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/141), [#201](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/201)
    * What it does: It is able to add and delete individual modules and skills from a person.
    * Justification: This allows the module and skill list to be easily updated. The original edit would only replace the original skill list with the new skills that the user fed as inputs.
    * Highlights: New prefixes were added, `m+/`, `m-/`, `s+/` and `s-/`. This allows the user to flexibly manage the lists without having to recreate the original list. For v1.2, there were other prefixes such as `so/` and `sn/` to represent a 1-to-1 update of the skill list. User would have to specify the old skill to be updated and the new skill. These were later removed in v1.3 as I realised that using `s-/` and `s+/` in the same command has the same functionality as `so/` and `sn/`.

* **New Feature**: Course List: [#120](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/120)
  * What it does: A list of courses that our application supports.
  * Justification: The list was introduced to standardize user inputs for courses, preventing users from inputting `cs` or `comp sci` to represent `Computer Science`. This helped to make our `find` function more user-friendly as users know what to search for, rather than having to toggle between the different names representing the same course. Users could simply input the index of the course that is shown in the left panel, minimising the keystrokes as well.
  * Highlights: This is displayed as a list with index numbers in the left panel of our application.

* **New Feature**: Help window: [#98](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/98), [#99](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/99)
  * What it does: Improved the help window to display a table of our command summary.
  * Justification: Experienced users need not refer to our user guide everytime they forget a specific command. They can simply refer to our command summary table in the help window and this would increase the user experience as it can be troublesome to reference the user guide everytime.
  * Highlights: The table also allows users to copy the commands and paste them into the text box, streamlining the user experience.

* **New Feature**: Copy url buttons: [#139](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/139), [#148](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/148)
  * What it does: Allows user to copy the email address, github profile and linkedin profile links of a contact.
  * Justification: Improved the user experience as the links can be copied to their clipboard to be used in their browser.
  * Highlights: Copy to clipboard
  * Credits: The original copy url button that was meant for the user guide link in the help window.

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=zoom&zA=anchengyang&zR=AY2223S2-CS2103T-F12-2%2Ftp%5Bmaster%5D&zACS=247.67299412915852&zS=2023-02-17&zFS=&zU=2023-04-05&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Project management**:
    * Opened PRs to allow teammates to review and provide feedback: [PRs opened by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/pulls?q=is%3Apr+author%3A%40me+is%3Aclosed) 
    * Provided feedback on PRS and merged PRs by teammates: [PRs reviewed by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
    * Assigned issues to teammates and I: [Issues assigned to me](https://github.com/AY2223S2-CS2103T-F12-2/tp/issues?q=is%3Aissue+assignee%3Aanchengyang)
    * Reminded teammates about the deliverables every week through weekly meetings and group chat

* **Enhancements to existing features**:
    * Modified the CodocParser with every new iteration to accommodate new changes to the commands
    * Created a singleton for a dummy person within the Person class to be displayed when the contact list was empty: [#125](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/125)
    * Enhanced the help window to improve user experience with the command summary and enabling the copying of each command: [#97](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/97)
    * Enhanced the add and edit command: [#67](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/67)
    * Provide bug fix support for new features whenever a bug is spotted: [#83](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/83), [#86](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/86), [#87](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/87)
    * Add `scroll back to Table of Contents` feature to help readers quickly navigate back to content page

* **Documentation**:
    * User Guide:
        * Add new sections for the user guide, particularly the Glossary, Command Summary, Navigating the User Guide, and Notes about the Command Format: [#149](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/149)
        * Edited the user guide pertaining to the features that I implemented: [#151](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/151), [#203](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/203), [#208](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/208)
        * Edited the table of contents and navigation of the user guide with every new iteration
        * Standardize formatting of each command section so there are no discrepancies between each section and there is a uniform look
        * Improve readability by using different text formats such as code, bold and italics
        * Include :information_source:, :bulb: and :exclamation: to allow readers to view important information that will be useful to note
    * Developer Guide:
        * Add new sections for the developer guide, particularly the Glossary, Target Audience and Navigating the Developer Guide: [#88](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/88)
        * Edited the non-functional requirements and use cases: [#29](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/29), [#134](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/134)
        * Edited the developer guide pertaining to the features that I implemented with UML diagrams such as edit command, parser and person class as well as the UI and logic component: [#88](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/88), [#90](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/90), [#134](https://github.com/AY2223S2-CS2103T-F12-2/tp/pull/134)
        * Edited the table of contents and navigation of the developer guide with every new iteration
        * Add `scroll back to top` feature to help readers quickly navigate back to content page

* **Tools**:
    * Java
    * JavaFX
    * SceneBuilder
    * Jackson
    * Junit5
