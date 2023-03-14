package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.review.Review;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Card> PREDICATE_SHOW_ALL_CARDS = unused -> true;

    Predicate<Deck> PREDICATE_SHOW_ALL_DECKS = unused -> true; //this is unnecessary?

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
     * Replaces deck data with the data in {@code addressBook}.
     */
    void setMasterDeck(ReadOnlyMasterDeck deck);

    /** Returns the Deck */
    ReadOnlyMasterDeck getMasterDeck();

    /**
     * Returns true if a card with the same identity as {@code card} exists in the address book.
     */
    boolean hasCard(Card card);

    /**
     * Deletes the given card.
     * The card must exist in the address book.
     */
    void deleteCard(Card target);

    /**
     * Adds the given card.
     * {@code card} must not already exist in the address book.
     */
    void addCard(Card card);

    /**
     * Replaces the given card {@code target} with {@code editedCard}.
     * {@code target} must exist in the address book.
     * The card identity of {@code editedCard} must not be the same as another existing card in the address book.
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

    void removeDeck(Deck key);

    void selectDeck(Index idx);

    void unselectDeck();
    String getSelectedDeckName();

    void reviewDeck(Index idx);

    Optional<Review> getReview();

    void endReview();

    String getReviewDeckName();

    void flipCard();
}
