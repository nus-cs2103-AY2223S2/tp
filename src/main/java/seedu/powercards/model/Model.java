package seedu.powercards.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.powercards.commons.core.GuiSettings;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.review.Review;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.model.tag.Tag.TagName;


/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Card> PREDICATE_SHOW_ALL_CARDS = unused -> true;
    Predicate<Deck> PREDICATE_SHOW_ALL_DECKS = unused -> true;
    Predicate<Card> PREDICATE_SHOW_NO_CARDS = unused -> false;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' deck book file path.
     */
    Path getMasterDeckFilePath();

    /**
     * Sets the user prefs' deck file path.
     */
    void setMasterDeckFilePath(Path deckFilePath);

    /**
     * Replaces deck data with the data in {@code deck}.
     */
    void setMasterDeck(ReadOnlyMasterDeck deck);

    /** Returns the Deck */
    ReadOnlyMasterDeck getMasterDeck();

    /**
     * Returns true if a card with the same identity as {@code card} exists in the master deck.
     */
    boolean hasCard(Card card);

    /**
     * Deletes the given card.
     * The card must exist in the master deck.
     */
    void deleteCard(Card target);

    /**
     * Adds the given card.
     * {@code card} must not already exist in the master deck.
     */
    void addCard(Card card);

    /**
     * Replaces the given card {@code target} with {@code editedCard}.
     * {@code target} must exist in the master deck.
     * The card identity of {@code editedCard} must not be the same as another existing card in the master deck.
     */
    void setCard(Card target, Card editedCard);

    /** Returns an unmodifiable view of the filtered card list */
    ObservableList<Card> getFilteredCardList();

    /** Returns an unmodifiable view of the filtered deck list */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Updates the filter of the filtered card list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCardList(Predicate<Card> predicate);


    /* NEWLY ADDED COMMANDS TO SUPPORT DECK LIST (NOT IN AB3) */

    void updateFilteredDeckList(Predicate<Deck> predicate);

    /** Returns the deck */
    Optional<Deck> getSelectedDeck();

    void addDeck(Deck deck);

    /**
     * Returns true if a deck with the same name as {@code deck} exists.
     */
    boolean hasDeck(Deck deck);

    /**
     * Replaces the given deck {@code target} with {@code editedDeck}.
     * {@code target} must exist.
     * The deck name of {@code editedDeck} must not be the same as another existing deck.
     */
    void setDeck(Deck target, Deck editedDeck);

    /**
     * Changes the association of all the cards from the old deck to the new deck.
     *
     * @param oldDeck The deck which cards are currently associated with.
     * @param newDeck The deck which cards are to be associated with.
     */
    void moveCards(Deck oldDeck, Deck newDeck);

    void deleteDeck(Deck key);

    void selectDeck(Index idx);

    void unselectDeck();

    String getSelectedDeckName();

    int getDeckSize(int deckIndex);

    /* ==================================== Review Operations ==================================== */

    int getDeckSizeFilteredTag(int deckIndex, List<TagName> difficulties);

    void reviewDeck(Index idx, List<TagName> difficulties);

    Optional<Review> getReview();

    void endReview();

    String getReviewDeckName();

    void flipCard();

    boolean goToPrevCard();

    boolean goToNextCard();

    boolean isReviewCardFlipped();

    void setNumCardsPerReview(int i);

    void tagCurrentCardInReview(Tag tag);

    ObservableList<Pair<String, String>> getReviewStatsList();

    ObservableList<Pair<String, String>> getReviewDeckNameList();

    /**
     * Returns the list of cards in the Review.
     */
    ObservableList<Card> getReviewCardList();

    /* ==================================== State ==================================== */

    /**
     * Returns the state of the model
     */
    ModelState getState();
}
