# Leon Poh's Project Portfolio Page
### Project: E-Lister

E-Lister is a desktop application designed to help finance planning consultants manage large amounts of customer information. The user interacts with it using a CLI, within a GUI created with JavaFX. It is written in Java, and has about 20 kLoC.

Given below are my contributions to the project.

* **Feature I**: Added `tag`ing for persons by adding a tag to the person.
  * What it does: Allows for the user better organise their contact list by tagging certain attributes to the person.
  * Justification: There needs to be a way for insurance agents to tag specific information to a person and later
  * retrieving it.
  `tag` enables such searches, via the use of another command called filter.
<!--  * Highlights: TBD -->
* **Feature II**: Added the `income` field for persons by creating a new field.
  * What it does: Allows Insurance agent to keep track of the person's income
  * Justification: Insurance agents need a way to keep track of the user's income to sell them financial products.
  `income` field enables such searches, via the use of other commands such as find and filter.
<!--  * Highlights: TBD -->
* **Feature III**: Added `mass` op command
  * what it does: Allows the insurance to execute mass operations on a bunch of people like adding tag to 5 people.
  * Justification: Insurance agents have large contact lists and sometimes need a way to tag a bunch of people to take note
  * of important information
  `mass` op enables them to delete a bunch of people or to tag them.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=T17-3&sort=groupTitle&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17)


* **Project management**:
  * Advised implementation-level interaction between project features
  * Handled standard portion of PR review
  * Advised on peer-examination bug triage

**Documentation**:
 * User Guide:
    * Added documentation for own contributed features
    * Adjusted documentation cross-referencing between features
  * Developer Guide:
    * Added Tag command sequence diagram for future developers.
