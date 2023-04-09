---
layout: page
title: WesleyBLDC's Project Portfolio Page
---

## Project: SalesPUNCH

### Overview

SalesPunch is a lightweight alternative Customer Relationship Management (CRM)
software, inspired by the conventional CRM software, such as SalesForce, but uses
a CLI-based user interface instead of a GUI, aimed towards enhancing efficiency
for fast typists.

### Summary of contributions

#### Code contributed: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=wesleybldc&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Enhancements Implemented:

* **Find by Tag – Finding a contact tag: `findtag`**: 
  * **What it does**: Finds and returns the contact list that matches the `TAG`
  * **Justification**: Managing a long list of contacts can be difficult for a salesperson. To make categorization easier, the salesperson can tag each contact after each interaction. Tags can indicate the level of friendship between contacts or the industry they belong to. Each contact can have multiple tags, providing countless ways for the salesperson to categorize their contacts. The findtag function is essential for searching contacts by these tags.
    * Overall, findtag is a valuable tool for salespeople who want to manage their contacts more effectively and streamline their sales funnel cycle. By providing easy access to contacts based on tags, the findtag command helps salespeople prioritize their sales tasks and close deals more efficiently.

* **Find by Lead Status – Finding a contact based on lead status:: `findlead`**: 
  * **What it does**: Finds and returns the contact list that matches the `STATUS`
  * **Justification**: As a salesperson managing a long list of contacts, it's important to keep track of the contact list and the stage of their dealing. The `findlead` command helps the user find contacts by a specific lead status, indicating the stage of the deal flow process. By assigning a lead status after each interaction with a contact, the salesperson can prioritize their sales tasks and close deals more efficiently.
    * `findlead` plays an essential role in searching contacts based on lead status. The user can use either the long form or short form method to search, making it easy to find contacts by lead status. Managing a large number of contacts can be difficult, but with `findlead`, it's easy to keep track of the contact list and the stage of their dealing
    * Overall, `findlead` is a valuable tool for salespeople who want to manage their contacts more effectively and streamline their sales funnel cycle. By providing easy access to contacts based on lead status, the `findlead` command helps salespeople prioritize their sales tasks and close deals more efficiently.

* **Find by All – Finding a contact based on keyword: `findall`**: 
  * **What it does**: Finds and returns the contact list that matches the `KEYWORD`
  * **Justification**: As a salesperson managing a significant number of contacts, you need efficient ways to categorize and search your leads. This is where the `findall` command comes in handy. With this command, you can search for any keyword that matches any attribute within your contact list, including name, email, and phone number. By providing a comprehensive search functionality, `findall` helps you keep track of your contacts and prioritize your sales tasks effectively. 
    * It's worth noting that the `findall` command excludes searching by tags and lead status, as they have their own separate commands. This means you can use those commands to search for contacts based on their tags or lead status. Additionally, `findall` does not parse through data in the tasklist and transactions, but this is a feature that will be added in future extensions.
    * Overall, the `findall` command is a powerful tool that can help you manage your contacts more efficiently and focus on closing deals.

* **FindTxn by Name – Finding a contact and their list of transactions:: `findtxn`**: 
  * **What it does**: Finds and returns the contact & transaction that matches the `NAME`
  * **Justification**: As a salesperson managing a significant number of contacts, it can be challenging to keep track of all transactions related to a particular contact. This is where the `findtxn` command comes in handy. By searching for a single contact and all transactions related to that contact, the user can easily access all relevant information in one place. This allows the user to quickly access all the relevant information related to that contact and make informed decisions about their sales strategy.
    * Overall, the `findtxn` command is a valuable tool for salespeople who want to manage their contacts more effectively and streamline their sales funnel cycle. By providing easy access to transaction information related to a specific contact, the `findtxn` command helps salespeople prioritize their sales tasks and close deals more efficiently.

#### Project management
  * Managed release `v1.2` on GitHub
  * Setup the CS2103 Team Repo, Organisation and Codecov setup

#### Documentation
  * **User Guide:**
    * Added documentation for all the find-related features ([#45](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/45),[#77](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/77), [#79](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/79), [#82](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/82),[#90](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/90))
    * Contributed to overall improvements in UG ([#174](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/174), [#150](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/150), [#178](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/178))
  * **Developer Guide:**
    * Added implementation details for all the find-related features, including all related diagrams ([#191](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/191))
    * Added user stories and use cases ([#194](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/194))

#### Community & Team
  * PRs reviewed: ([#150](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/150),[#147](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/147), [#141](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/141), [#186](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/186),[#146](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/146), [#145](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/145), [#78](https://github.com/AY2223S2-CS2103-W16-4/tp/pull/78)...)
  * Reported bugs and suggestions for our team members for TP: [Issues Link](https://github.com/AY2223S2-CS2103-W16-4/tp/issues?q=is%3Aissue+author%3AWesleyBLDC+is%3Aclosed)
  * Reported bugs and suggestions for other teams in the class: [Issues Link](https://github.com/WesleyBLDC/ped/issues)

<!-- #### Contributions to team-based tasks:


- Review, comment and approve teammate PRs

#### Review/mentoring contributions:

Links to PRs reviewed, instances of helping team members in other ways.

#### Contributions beyond the project team:


- Setup the CS2103 Team Repo and Organisation
- Codecov setup for repo
- Adding description for Saving the data file and Editing the data file
- Updated UG for find feature
- Wrote findtag feature
  - Wrote unit tests for findtag
  - UG,DG for findtag feature
- Wrote findlead feature
  - Wrote unit tests for findlead
  - UG,DG for findlead feature
- Wrote findall feature
  - Wrote unit tests for findall
  - UG,DG ffor findall feature
- Wrote findtxn feature
  - Wrote unit tests for findtxn
  - UG,DG for findtxn feature -->
