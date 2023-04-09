# Jeff's Project Portfolio Page
### Project: E-Lister

E-Lister is a desktop application designed to help finance planning consultants manage large amounts of customer information. The user interacts with it using a CLI, within a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **Feature I**: Added `filter`ing for persons by a combination of fields.
  * What it does: Allows for the user to search persons using (∩) combinations of any available fields.
  * Justification: The existing `find` command filters only by name, whereas the target user would need to search by other fields or even combinations thereof.
  `filter` enables such searches, and does so via regex - which is sufficiently powerful to nominally meet any expected needs, but also has basic functionality mimicking simpler input schemes.
<!--  * Highlights: TBD -->


* **Feature II**: Added the ability to `undo` and `redo` an appropriate subset of commands.
  * What it does: Reverts previous commands in standard undo-fashion. Operates on all other commands which modify the data of Persons or the display thereof.
  * Justification: The option to `undo` provides a user an easy means to revert an undesirable command effect.
  Accordingly, a user can recover from typo errors, and can also attempt to use a command without having to heavily scrutinize its effect beforehand.
<!--  * Highlights: TBD -->


* **Feature III**: Added the ability to `freeze` filters.
  * What it does: Halts the updating of the displayed list according to the current filter. (By default, if a person is edited to no longer satisfy the current filter, they would be automatically dropped from the display - `freeze` disables this.)
  * Justification: Reactive filters can be effective for the user and have been retained, but there are also scenarios where
  disabling continuous updates to the list would be preferred - to prevent repeated index changes, or Persons disappearing from the list.
<!--  * Highlights: TBD -->


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=T17-3&sort=groupTitle&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)


* **Project management**:
  * Advised implementation-level interaction between project features
  * Advised individual feature ad-hoc debugging
  * Handled standard portion of PR review
  * Advised on peer-examination bug triage


* **Enhancements to existing features**:
  * Refactor `InputHistory` to avoid a major naming conflict
  * Expand on `Predicate` usage
  * Improve `mass`-operation commands for greater subcommand flexibility


* **Documentation**:
  * User Guide:
    * Added documentation for own contributed features
    * Adjusted documentation cross-referencing between features
  * Developer Guide:
    * Added implementation details for `StateHistory` (`undo`/`redo`)

<!--
* **Community**:
  * TBD


* **Tools**:
  * TBD
-->
