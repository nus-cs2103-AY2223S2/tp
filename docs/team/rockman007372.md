---
layout: page 
title: Tran Hieu Nghia's Project Portfolio Page
---

### Project: PowerCards

PowerCards enable university students to create flashcards of their desired topic and practice active recall of the information in these flashcards.

Given below are my contributions to the project.

* **Feature 1**: Make `Card` and `Deck` class immutable.
    * What it does: Make cards' and decks' attributes `final`. Whenever a card or deck's attribute needs editing, a new instance is created.
    * Justification: Immutable classes are simpler to use and reason about than mutable classes. Because their state cannot change, we don't need to worry about unexpected side effects or hidden state changes.
    * Highlights: Since a card is referenced in many internal lists, changing its state leads to unexpected behaviours and produced many bugs. While making Card immutable requires a big refactor of the code thus far, it was worth the effort as the number of bugs were reduced significantly.   

* **Feature 2**: Refactor `Review` class extensively to follow more OOP principles.
  * What it does: I did a complete overhaul of `Review` class so that it stores its own `UniqueCardList`, allowing the `Review` class to directly interact with the cards in a review session.
  * Justification: In earlier iterations, the `Review` sole purpose is to store the Review statistics. All interactions with the cards in review are done through the `ModelManager` class.
  * Highlights: This reduces the responsibility of `ModelManger` as it does not have to manage a review anymore, thus adhering to the Single Responsibility Principle. I also create and link the `FilteredList<Card>` in `Review` to the UI using the Observer pattern. Previously, the Review relied on the same `FilteredList<Card>` in `ModelManager`, which results in poorer OOP.
  * Credits: to Kok Hai for designing and implementing the Review Class initially. 

* **Feature 3**: Colour-code the selected deck
  * What it does: When a deck is selected, its colour is changed to differentiate it from the other decks in the list.
  * Justification: This provides visual clarity for users so that they know which deck they are currently selecting.
  * Highlights: This gives me a chance to learn more about how the UI components work in tandem with the Model component, as well as applying the Observer pattern.

* **Feature 4**: Create a confirmation box whenever Clear command is executed.
  * What it does: When users execute `clear` command to delete all data, a confirmation box appears to ask for double confirmation from users.
  * Justification: This prevents users from accidentally deleting all data, as this command is irreversible at this point.
  * Highlights: I learned to use JavaFX components and practice event handling.

* **Feature 4**: Tag the cards dynamically during a review.
  * What it does: Allows users to tag a card with a difficulty during a review. The new tag will be dynamically modified in UI, and saved in the `MasterDeck`.
  * Justifications: Users usually only know how difficult a card is during review, not when they create a card. It is much more convenient to tag cards during review, instead of using the `editCard` command in Main Mode.
  * Highlights: I understood the behavior of Javafx `ObservableList` class better, and practiced the Observer pattern.

* **Code contributed**: [RepoSense](https://nus-cs2103-ay2223s2.github.io/tp-dashboard/?search=rockman007372&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-02-17&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
    * (To be added)

* **Enhancements to existing features**:
    * Refactor and rename many classes to follow naming consistency (e.g., `Person` to `Card`).
    * 
    * 

* **Documentation**:
    * User Guide:
        * (To be added)
        * (To be added)
    * Developer Guide:
        * A(To be added)

* **Community**:
    * (To be added)
    * (To be added)
    * (To be added)
    * (To be added)

* **Tools**: NA


