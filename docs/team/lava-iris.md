---
layout: page
title: Lavanya's Portfolio Page
---

### Project: ConnectUS

ConnectUS is the ultimate **contact management system** for your everyday needs. If you're an NUS School of Computing (SoC) student, this app is for you. With ConnectUS, you can **easily connect with people** without the anxiety of _remembering who you met where_ holding you back from socialising.

We're focused on:
- **Efficiency**: Optimized for use via a Command Line Interface (CLI), you can **easily view and edit your contacts** at your fingertips with ConnectUS.
- **User-friendliness**: With the benefits of having a Graphical User Interface (GUI), **easily navigate through your contact information** to find exactly what you need to **connect** with others.

Given below are my contributions to the project.

* **New Feature**: Added module as a tag.
  * What it does: allows the user to store modules as a tag to the contact.
  * Justification: This feature is important as it makes the app more tailored to NUS students and makes it easy to look for people in the same
  * Highlights: Since it was a new field, it required changes in almost all files, from logic to model to commands and tests. Since it was also the first new tag to be added, there were design decisions to be made regarding how the different tags would be distinguished (we settled on changing the colour) and how much the common functionalities of tags can be used to make thing less repetitive.
  * Credits: The first implementation of module tags was completely based on the implementation of tags in the AB3 code base with minor changes.
  * Related Pull Requests: [\#80](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/80)
* **Major Enhancement**: Implemented advanced search by field
  * What it does: With the new search, users can search for people by any field (not just name) and can find matches for keywords contained in the information (don't need exact matches)
  * Justification: One of the major selling points of ConnectUS is the ease with which you can search for the people you're looking for and contact them directly from the app. For that, being able to search based on modules, ccas, the position in cca, telegram id, etc. is necessary.
  * Highlights: It was hard to figure out how to implement it and many design decisions had to be made about what action fell under the purview of which class to maintain OOP principles. There were changes being made to the fields (cca and major) concurrently, which affected search and had to be incorporated afterwards.
  * Credits: I took inspiration from how the `Edit` command functioned to design the structure of the search command.
  * Related pull requests: [\134](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/134), [\159](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/159)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=lava-iris&breakdown=true&sort=groupTitle&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Set and maintained deadlines for the team.
  * Brainstormed features and solutions with team to find solutions.

* **Enhancements to existing features**:
  * changed the sample contacts to better show the features of ConnectUS. Related pull request: [\#146](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/146)
  * added JUnit tests for all features I implemented
  * fix bugs in v1.3. Related pull requests: [\#222](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/222), [\#223](https://github.com/AY2223S2-CS2103T-W15-1/tp/pull/223)

* **Documentation**:
  * User Guide:
    * added user stories related to social media and open and chat commands
    * added use cases for Adding a contact and Searching for a contact
  * Developer Guide:
    * Added implementation of Search command

* **Community**:
  * PRs reviewed: As of 10th April, I have reviewed 24 PRs. Refer to [this](https://github.com/AY2223S2-CS2103T-W15-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me) for most recent statistics.
  * Reported bugs and suggestions for other teams in PE dry run [here](https://github.com/Lava-Iris/ped).
