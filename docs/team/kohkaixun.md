---
layout: page
title: Kai Xun's Project Portfolio Page
---

### Project: InternBuddy

InternBuddy provides a 1-stop platform for Computing undergraduates to manage and track their internship applications. It is optimized for typing which Computing undergraduates are comfortable and proficient in, allowing them to fully and efficiently exploit the application’s organisational capabilities.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

My code contributions can be found [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=kohkaixun&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other) on RepoSense.

#### Features and Enhancements

* **New Feature**: Implemented multiple iterations of the `find` command [#50](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/50) [#58](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/58) [#89](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/89)
  * What it does:
    * The first iteration of the `find` command [#50](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/50) would look for all internship with the matching `COMPANY NAME`, `STATUS` and `TAG` fields. Other than the `TAG` field, fields with multiple instances in the input only had the last instance considered.
    * The second iteration [#58](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/58) took into account the `ROLE` field as well when searching for internships. InternBuddy also now took into consideration multiple instances for every field.
    * The third iteration [#89](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/89) could now take into account the `DATE` field as well. Matching of user input to internship field was also changed to an exact but case-insensitive match.
  * Justification
    * In the first iteration [#50](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/50), if InternBuddy has a long list of internship entries, the user can quickly look for the desired entry using the `find` command instead of manually going through the entire list.
    * In the second iteration [#58](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/58), the user can just enter one `find` command to search for entries with different information in the same field and compare them all on the same screen, instead of entering multiple `find` commands and being unable to view all these entries altogether at once.
    * In the third iteration [#89](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/89), exact matching between inputs and internship fields will streamline search results, instead of keyword matching where entries could be filtered out due to a matching with a non-essential keyword within the input.

* **New Feature**: Save past user input [#144](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/144)
  * What it does: Keeps a record of all user input in the current run of InternBuddy and allows user to navigate through them using the up and down arrow keys.
  * Justification:
    * If the user is trying to enter multiple similar commands, after entering the first command, he or she can simply use the up arrow key to retrieve the last inputted command and make a quick edit instead of typing the whole command again.
    * Suppose there was a past input that was of the wrong format, the user would be able to navigate to it and make edits before entering instead of typing everything out again

* **Enhancements to existing features**:
  * Made displayed indexing error messages more specific. [#131](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/131) [#133](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/133)
  * Addressed integer overflow bug in displayed error messages, but enhancement was not merged after discovering it violated feature freeze. [#194](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/194)

**Project Management**
* Reviewed and approved pull requests for merging.
* Testing of InternBuddy releases on MacOS environment.

**Documentation**
* User Guide:
  * Initial update of `list` and `delete` commands [#32](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/32)
  * Update of `find` command according to new implementation [#123](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/123)
  * Add and update of navigating through command history section [#149](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/149) [#202](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/202)
* Developer Guide:
  * Update of `find` command with inclusion of UML sequence diagrams [#88](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/88) [#90](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/90) [#95](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/95) [#123](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/123)
  * Update set up and get started section [#107](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/107)

**Community**
* PRs reviewed (with non-trivial review comments): [#79](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/79), [#80](https://github.com/AY2223S2-CS2103T-T14-3/tp/pull/80)
* Contributed to forum discussions (example: [1](https://github.com/nus-cs2103-AY2223S2/forum/issues/340))
