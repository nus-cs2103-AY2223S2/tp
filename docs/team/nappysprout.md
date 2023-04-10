---
layout: page
title: Darie Chan Rong Zhi's Project Portfolio Page
---

### Project: CoDoc

CoDoc is a desktop contact management application. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 20.8k LoC.

##### Summary of Contributions
* **Code contributed**: [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=nappysprout&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
* **New Feature**: Module
    * What it does: Provide Module field for Person in Codoc
    * Justification: Codoc is an address book for Students and Module is an important aspect of student
    * Highlights: The regex checked for the correct format and an additonal validation was required to ensure correct module Years

* **New Feature**: Course
  * What it does: Provide Course field for Person in Codoc
  * Justification: Codoc is an address book for Students and Course is an important aspect of student

* **New Feature**: Year
  * What it does: Provide Year field for Person in Codoc
  * Justification: Codoc is an address book for Students and Year is an important aspect of student


* **Project management**: I was able to merge PRs of my teammates and even spot errors which might be bad for the project before they are merged into master branch.
I was able to set up the organisation, create the repository and gave my teammates access as well. 

* **Enhancements to existing features**:
    * Extended Person class to include more attributes
    * Modified Storage class to account for new attributes
    * Modify add command to include the new attributes
    * Modify find command to include the new attributes

* **Documentation**:
    * User Guide:
        * Fix github issues pertaining to confusing layout in UserGuide
    * Developer Guide:
        * Explanation of Module Class
        * Tidied up table of content links and navigation and ensure their proper function
        * Tidied up glossaries and added Java specific definitions
        * Added use cases and neaten up use cases which can be inside other use cases
        * Provided images for the UI implementation segments and added Course List Panel Segment.
* **Community**:
    * Reviewed, merged and commented on teammates' PRs. [PRs reviewed by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Anappysprout), [My comments on PRs](https://nus-cs2103-ay2223s2.github.io/dashboards/contents/tp-comments.html#163-dari-zhi-nappysprout-5-comments)
    * Opened and assigned issues to teammates and me. [Issues opened by me](https://github.com/AY2223S2-CS2103T-F12-2/tp/issues?q=is%3Aissue+is%3Aclosed+author%3Anappysprout), [Issues assigned to me](https://github.com/AY2223S2-CS2103T-F12-2/tp/issues?q=is%3Aissue+is%3Aclosed+assignee%3Anappysprout+)
    * Reported bugs for other teams. [PE-D](https://github.com/nappysprout/ped/issues)
* **Tools**:
    * JavaFX
    * Jackson
    * JUnit5
