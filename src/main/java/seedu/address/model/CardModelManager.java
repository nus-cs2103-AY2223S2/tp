package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.powercard.PowerCard;
import seedu.address.model.powerdeck.PowerDeck;
import seedu.address.model.powerdeck.ReadOnlyPowerDeck;

/**
 * Represents the in-memory model of the deck data.
 */
public class CardModelManager implements CardModel {
    private static final Logger logger = LogsCenter.getLogger(CardModelManager.class);

    private final PowerDeck deck;
    private final UserPrefs userPrefs;
    private final FilteredList<PowerCard> filteredCards;

    /**
     * Initializes a CardModelManager with the given deck and userPrefs.
     */
    public CardModelManager(ReadOnlyPowerDeck powerDeck, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(powerDeck, userPrefs);

        logger.fine("Initializing with deck: " + powerDeck + " and user prefs " + userPrefs);

        this.deck = new PowerDeck(powerDeck);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCards = new FilteredList<>(this.deck.getCardList());
    }

    public CardModelManager() {
        this(new PowerDeck(), new UserPrefs());
    }


    /* ========================== UserPrefs ============================ */

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDeckFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setDeckFilePath(Path deckFilePath) {
        requireNonNull(deckFilePath);
        userPrefs.setAddressBookFilePath(deckFilePath);
    }

    /* ========================== PowerDeck ============================ */

    @Override
    public void setDeck(ReadOnlyPowerDeck readOnlyDeck) {
        this.deck.resetData(readOnlyDeck);
    }

    @Override
    public ReadOnlyPowerDeck getDeck() {
        return deck;
    }

    /* ========================== PowerCards ============================ */

    @Override
    public boolean hasCard(PowerCard card) {
        requireNonNull(card);
        return deck.hasCard(card);
    }

    @Override
    public void deleteCard(PowerCard card) {
        deck.removeCard(card);
    }

    @Override
    public void addCard(PowerCard card) {
        deck.addCard(card);
        updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
    }

    @Override
    public void setCard(PowerCard target, PowerCard editedCard) {
        requireAllNonNull(target, editedCard);
        deck.setCard(target, editedCard);
    }

    /* ========================== Filtered Card List Accessors ============================ */

    /**
     * Returns an unmodifiable view of the list of {@code cards} backed by the internal list of
     * {@code versionedPowerDeck}
     */
    @Override
    public ObservableList<PowerCard> getFilteredCardList() {
        return filteredCards;
    }

    @Override
    public void updateFilteredCardList(Predicate<PowerCard> predicate) {
        requireNonNull(predicate);
        filteredCards.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof CardModelManager)) {
            return false;
        }

        // state check
        CardModelManager other = (CardModelManager) obj;
        return deck.equals(other.deck)
                && userPrefs.equals(other.userPrefs)
                && filteredCards.equals(other.filteredCards);
    }
}
