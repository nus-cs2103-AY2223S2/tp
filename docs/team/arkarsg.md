---
layout: page
title: Arkar's Portfolio Page
---

### Project: Trackr

Trackr is a desktop application used to keep track of order, suppliers and tasks. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=arkarsg&breakdown=true)

* **Contribution to code base**
  * Initial refactoring of `Supplier` from `Person`
    * Justification: This improves the product because the differentiation between `Supplier` and `Person` fits the use case of our product.
  * Refactor `Order` to have a reference to `MenuItem`
    * Justification: This association is needed as users should only be able to create orders from pre-existing `MenuItem`.
    * Highlight: 
      * Adapt `Storage` and `JSON` files to support `MenuItem` nested within `Order`
      * Generate total profits and sales from each order and all orders
      * Profits and sales responsive to changes in `OrderQuantity` and `OrderItem`
      * Rewrite relevant test cases for Order
  * Overhauls Ui
    * Highlights:
      * Refactor into packages
      * Cosmetic changes using CSS
      * Added tabbed views for different data
  * New feature: `TabCommand` that allows users to switch between tabs
    * Justification: Like most applications, users can use `CTRL + TAB` or `ARROW KEYS` to cycle through area of focus. This added feature allow users to skip tabs while using a verbose command. This also further optimises the product for CLI use.
    *  Highlight: Used the `Observer / Observable` pattern with JavaFX `SimpleIntegerProperty` to listen to changes in the selected tab index. This decouples `TabCommand` in `Logic` from `Ui` and enforces Law of Demeter. Some commands such as `list`/`find` also snaps to relevant tab for better user experience.

* **Review / Peer Help**
  * Helped teammates with debugging and test cases

* **Documentation**:
  * User Guide:
    * Drafted command syntax and examples
    * Provided overall feedback [\#267](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/267)
  * Developer Guide:
    * Updated UML diagram for `Ui` and its description 
    * Added activity diagram for `DeleteCommand` 
    * Updated user stories and use cases.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#267](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/267), [\#177](https://github.com/AY2223S2-CS2103T-W15-2/tp/pull/177)

* **Tools**:
  * Java 11, JavaFx, Jekyll

