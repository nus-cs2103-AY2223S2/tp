---
layout: page
title: Zhang Yuquan's Project Portfolio Page
---

### Project: Ez-Schedule

EZ-Schedule is a desktop application used for managing and scheduling of events. The user interacts with it using a
Command Line Interface (CLI), and it has a Graphical User Interface (GUI) created and built with JavaFX. It is 
written in mainly Java and supported with relevant files and documents. This application has approximately 
about 10k Loc.
Given below are my contributions to the project.


### Summary of contributions
* **Code contributed**: Check it out
[here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=bigheartsmallbrain&breakdown=true)


* **New features**:
  * Undo most recent commands ( only applicable to `Add`, `Edit`, `Delete`, `Recur`)
    * Description: 
      
      The `Undo` command will undo the most recent and previous valid command. Restoring the 
        scheduler's previous state.
    * Reason: 

      In the case of a user story when the user has accidentally performed a command, and the user wishes to revert 
      his/her action. Hence, undo previous command is applicable for users to save trouble or re-entering details of 
      event.


* **Enhancements to existing features**:
  * Bug Fix:
    * [#184 Fix bugs for undo feature for invalid commands](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/184)
    * [#220 Fix delete command for bugs related to undo command](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/220)
  * Testcase Code:
    * [#219 Add test cases for undocommand feature. Fixed minor bugs in code and corresponding helper classes and 
      functions.](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/219)


* **Documentation**:
  * User Guide:
      * Update UG for `undo` feature [#107](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/107/files)
      * Contributed to the idealisation of UG Overview (Such as evolve features, command summary)
    

  * Developer Guide:
    * Undo Command implementation

  * Others:
    * Update AboutUS [#39](https://github.com/AY2223S2-CS2103-W17-3/tp/pull/39)
    * Update Ui mockup [#37] (https://github.com/AY2223S2-CS2103-W17-3/tp/pull/37)


* **PRs reviewed**:
  * Milestone none: [2] [pr-reviewed-no-milestone]
  * Milestone v1.1: [1] [pr-reviewed-v1.1]
  * Milestone v1.2: [1 [pr-reviewed-v1.2]
  * Milestone v1.3: [2] [pr-reviewed-v1.3]
  * Milestone v1.3b: [3] [pr-reviewed-v1.3b]
  * Milestone v1.4: [2] [pr-reviewed-v1.4]
  * Total: [11] [pr-reviewed-total]


[pr-reviewed-no-milestone]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me+no%3Amilestone
[pr-reviewed-v1.1]: https://github.com/AY2223S2-CS2103-W17-3/tp/pull/30
[pr-reviewed-v1.2]: https://github.com/AY2223S2-CS2103-W17-3/tp/pull/49
[pr-reviewed-v1.3]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me
[pr-reviewed-v1.3b]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me
[pr-reviewed-v1.4]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me+milestone%3Av1.4
[pr-reviewed-total]: https://github.com/AY2223S2-CS2103-W17-3/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me