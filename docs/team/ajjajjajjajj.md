---
layout: page
title: Jing Jie's Project Portfolio Page
---

### Project: SalesPUNCH

SalesPUNCH is a lightweight alternative Customer Relationship Management (CRM)
software, inspired by the conventional CRM software, such as SalesForce, but uses
a CLI-based user interface instead of a GUI, aimed towards enhancing efficiency
for fast typists.
  
* **New Feature**: Status Features - Lead Status and Transaction Status
  * **What it does**: Provides a timestamped attribute to contacts' lead status and transaction statuses. Statuses are
    limited to a number of pre-determined statuses
  * **Justification**: A salesperson managing a long list of contacts and past transactions will find it difficult to find 
    recall whether their contact is a ready-to-buy client, newly added contact etc. The same applies for transactions, 
  where it might be difficult to memorise whether a transaction has been closed, or is ongoing.  
    The user would also want to recall how long a list item has stayed in the location, which allows them to prioritise 
    their work. For example, a previously unqualified lead might have been unqualified for a long amount of time, which 
    indicates that it might be time for the user to check in with them again. 
  * **Highlights**: Statuses of persons and transactions are shown as a badge next to the name in the GUI, along with 
  the time elapsed (in least granular time) since the last change to the status. The badge is dynamic, such that the 
  time elapsed is refreshed every time the GUI is refreshed. Accessing the statuses and updating them has been added through commands.
    

* **New Feature**: Transactions Feature, relations
  * Formulated the idea for users to track their sales via transactions, and wrote ([#44](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/42/commits)) 
  the skeletal classes. 
  * Created the GUI for the list of transactions, which is intentionally similar to the person list, for the sake of
  consistency for the user ([#67](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/67)).
  * **Justification**: Transactions are part of the every working life of a salesperson, and providing a means for them
  to track their sales is essential. Each transaction is also tied to a customer by nature, salespeople want to be able 
  to link their sales with their customers.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=ajjajjajjajj&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ajjajjajjajj&tabRepo=AY2223S2-CS2103-W16-4/tp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Managed releases `v1.1` to `v1.4` on GitHub

* **Enhancements to existing features**:
  * Additional GUI enhancements 
    * Added the transactions list within the GUI ([#67](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/67))
    * Modified the representation of contacts in GUI to accommodate new attributes ([#76](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/76),
    [#67](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/67))
    * Adapted the GUI for the team's product in general ([#141](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/141))

* **Documentation**:
  * User Guide:
    * Added documentation for Status features ([#147](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/147))
    * Contributed to bug fixing and cosmetic changes to UG ([#147](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/147))
  * Developer Guide:
    * Added implementation details for the Status features, including all UML diagrams
    ([#66](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/66),
    [#185](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/185))
    * Added user stories and use cases ([#21](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/21))

* **Community/Team**:
  * PRs reviewed (with non-trivial comments): ([#44](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/44), 
  [#47](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/47), [#142](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/142))
  * Created bug reports for internal product testing: (examples: [1](https://github.com/AY2223S2-CS2103-W16-4/tp/issues/164),
    [2](https://github.com/AY2223S2-CS2103-W16-4/tp/issues/155))
  * Assisted team members with bug fixing and test writing for other features: (examples: [#146](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/146),
  [#57](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/57))
  * Assisted team in classifying and allocating bugs identified in the cross-team product testing (all bugs listed 
  [here](https://github.com/AY2223S2-CS2103-W16-4/tp/issues?q=is:issue+is:closed+%5BPE-D%5D))
  * Contributed to forum discussions: [1](https://github.com/nus-cs2103-AY2223S2/forum/issues/182), [2](https://github.com/nus-cs2103-AY2223S2/forum/issues/81)
  * General code enhancements for the team product (examples: [#142](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/142), 
  [#180](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/180))
