---
layout: page
title: antonlee59's Project Portfolio Page
---

### Project: Ultron

Ultron is a system that assists students in tracking their internship applications by organizing their internships and monitoring the status of each application. To enhance the user experience, I have made a significant contribution to the project by implementing new features, refactoring the code, and improving the documentation.

Given below are my contributions to the project.

* **Features**:
    * Upcoming feature (sorting element)
      - allows students to sort their internships based on the number of days until the application deadline.
      - Created a custom Date Comparator to correctly implement the sorting aspect of upcoming
      - Modified the original FilteredList abstraction into a more flexible and versatile ObeservableList to support the sorting aspect of the Openings
      - Created a custom `OpeningsBeforeDaysPredicate` to check how many days are after the current date and then filter it accordingly
    * Refactored the `Find` feature
      - Enhance its usability and efficiency
      - Implemented two custom predicate classes that filter the ObservableList to match user queries
      - Supported the filtering of Company and Positions
    * Merged the various refactorings
      - Combined all the different parts of the refactored code for the team to make the system more maintainable and efficient
      - Improved the system's architecture, making it easier to maintain and enhance in the future.
      - Created various new classes such as ModelManager, OpeningList etc.
    * Created custom error messages for the `Add` Command
      - If a user tries to `add` an opening without the four compulsory fields which are companies, position, status and email, the correct custom error messages will be shown to guide the user accordingly

* **Code contributed**:
  * In summary, my contributions to the Ultron project have significantly enhanced the user experience, improved the system's architecture, and made the codebase more maintainable and efficient.
  * You can find the report [here](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=antonlee&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
    * Documented the refactored Find Command
    * Documented the steps on the lifecycle of a single execution of the `upcoming` command in the Developer's Guide. This would help Developers understand the creation and deletion of CommandResult Objects so that they can further improve on it if need be. 
    * Documented the Sequence Diagram for the `upcoming` command with an example of using `upcoming 5` to show a valid example.
