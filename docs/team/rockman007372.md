---
layout: page 
title: Tran Hieu Nghia's Project Portfolio Page
---

### Project: PowerCards

PowerCards enable university students to create flashcards of their desired topic and practice active recall of the information in these flashcards.

Given below are my contributions to the project.

* **Feature 1**: Make `Card` and `Deck` class immutable.
    * What it does: Make cards' and decks' attributes `final`. 
    * Justification: Immutable classes are simpler to use and reason about (no unexpected side effects or hidden state changes).
    * Highlights: Since a card is referenced in many internal lists, changing its state leads to unexpected behaviours and produced many bugs. The refactoring was heavy, but it was worth the effort as bugs were reduced significantly.   

* **Feature 2**: Refactor `Review` class extensively to follow more OOP principles.
  * What it does: A complete overhaul of `Review` class so that it stores its own `UniqueCardList` and interact directly with these cards.
  * Justification: Earlier, the `Review` sole purpose is to store the Review statistics. All interactions with the cards in review are done through the `ModelManager` class.
  * Highlights: This reduces the responsibility of `ModelManger` as it does not have to manage a review anymore, thus adhering to the Single Responsibility Principle. I also link the `FilteredList<Card>` in `Review` to the UI. 
  * Credits: to Kok Hai for designing and implementing the Review class. 

* **Feature 3**: Tag the cards dynamically during a review.
  * What it does: Allows users to tag a card with a difficulty during a review. The new tag will be dynamically modified in UI, and saved in the `MasterDeck`.
  * Justifications: It is much more convenient to tag cards during review, instead of using the `editCard` command in Main Mode.
  * Highlights: I studied and understood the behavior of Javafx `ObservableList` class better, and practiced the Observer pattern. This also taught me how the UI components worked in tandem with Model component. 

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=rockman007372&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * Create issues, assign roles and set internal deadlines.

* **Enhancements to existing features**:
    * Add and modify exising test cases to increase code coverage (e.g. create the test cases in ReviewTest).
    * Refactor many classes to follow OOP and design patterns, e.g., `MasterDeckParser` was refactored to follow Don't Repeat Yourself (DRY) principle. 
    * Modify Storage components to allow Cards and Decks to be saved as JSON data.

* **Documentation**:
    * User Guide:
        * Contribute to the majority of the documentation of all Main Mode commands [#319](https://github.com/AY2223S2-CS2103T-W11-3/tp/pull/319).
        * Add indices for all headers [#319](https://github.com/AY2223S2-CS2103T-W11-3/tp/pull/319).
        * Vet the User Guide thoroughly to fix bugs, e.g., [#346](https://github.com/AY2223S2-CS2103T-W11-3/tp/pull/346), [#335](https://github.com/AY2223S2-CS2103T-W11-3/tp/pull/335), [#322](https://github.com/AY2223S2-CS2103T-W11-3/tp/pull/322).
    * Developer Guide:
        * Add documentation and UML class diagrams under `Model` [component](https://ay2223s2-cs2103t-w11-3.github.io/tp/DeveloperGuide.html#model-component), `Review` [component](https://ay2223s2-cs2103t-w11-3.github.io/tp/DeveloperGuide.html#review), `MasterDeck` [component](https://ay2223s2-cs2103t-w11-3.github.io/tp/DeveloperGuide.html#implementation-of-masterdeck). 
        * Add documentation and UML sequence diagram for `TagEasyCommand` [feature](https://ay2223s2-cs2103t-w11-3.github.io/tp/DeveloperGuide.html#tag-cards-during-the-review-feature).
        * Contribute to the documentation of [use cases](https://ay2223s2-cs2103t-w11-3.github.io/tp/DeveloperGuide.html#use-cases).

* **Community**:
    * PRs reviewed: [58](https://github.com/AY2223S2-CS2103T-W11-3/tp/pulls?q=is%3Apr+reviewed-by%3Arockman007372) 
