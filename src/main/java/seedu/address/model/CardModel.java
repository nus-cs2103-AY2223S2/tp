package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.powercard.PowerCard;
import seedu.address.model.powerdeck.ReadOnlyPowerDeck;

/**
 * The API of the Model component for Deck.
 */
public interface CardModel {

    /** {@code Predicate} that always evaluate to true */
    Predicate<PowerCard> PREDICATE_SHOW_ALL_CARDS = unused -> true;

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
     * Returns the user prefs' deck file path.
     */
    Path getDeckFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setDeckFilePath(Path DeckFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setDeck(ReadOnlyPowerDeck deck);

    /** Returns the deck */
    ReadOnlyPowerDeck getDeck();

    /**
     * Returns true if a card with the same identity as {@code card} exists in the deck.
     */
    boolean hasCard(PowerCard card);

    /**
     * Deletes the given card.
     * The card must exist in the deck.
     */
    void deleteCard(PowerCard card);

    /**
     * Adds the given card.
     * {@code card} must not already exist in the deck.
     */
    void addCard(PowerCard card);

    /**
     * Replaces the given card {@code target} with {@code editedCard}.
     * {@code target} must exist in the deck.
     * The card identity of {@code editedPerson} must not be the same as another existing card in the address book.
     */
    void setCard(PowerCard target, PowerCard editedCard);

    /** Returns an unmodifiable view of the filtered deck (card list) */
    ObservableList<PowerCard> getFilteredCardList();

    /**
     * Updates the filter of the filtered card list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCardList(Predicate<PowerCard> predicate);

}
