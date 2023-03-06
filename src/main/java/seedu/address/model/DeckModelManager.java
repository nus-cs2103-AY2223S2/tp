package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.powercard.PowerCard;
import seedu.address.model.powerdeck.PowerDeck;
import seedu.address.model.powerdeck.ReadOnlyPowerDeck;

/**
 * Represents the in-memory model of the deck data.
 */
public class DeckModelManager implements DeckModel {
    private static final Logger logger = LogsCenter.getLogger(DeckModelManager.class);
    private PowerDeck selectedDeck = null;
    private final ArrayList<PowerDeck> powerDecks = new ArrayList<>();
    private final UserPrefs userPrefs;
    //  private final FilteredList<PowerCard> filteredCards;

    /**
     * Initializes a CardModelManager with the given deck and userPrefs.
     */
    public DeckModelManager(ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(userPrefs);

        logger.fine("Initializing with user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        //      filteredCards = new FilteredList<>(this.selectedDeck.getCardList());
    }

    public DeckModelManager() {
        this(new UserPrefs());
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
        this.selectedDeck.resetData(readOnlyDeck);
    }

    @Override
    public ReadOnlyPowerDeck getDeck() {
        return this.selectedDeck;
    }

    /* ========================== PowerCards ============================ */

    @Override
    public boolean hasCard(PowerCard card) {
        requireNonNull(card);
        return this.selectedDeck.hasCard(card);
    }

    @Override
    public void deleteCard(PowerCard card) {
        this.selectedDeck.removeCard(card);
    }

    @Override
    public void addCard(PowerCard card) {
        this.selectedDeck.addCard(card);
        updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
    }

    @Override
    public void setCard(PowerCard target, PowerCard editedCard) {
        requireAllNonNull(target, editedCard);
        this.selectedDeck.setCard(target, editedCard);
    }

    /* ========================== Filtered Card List Accessors ============================ */

    /**
     * Returns an unmodifiable view of the list of {@code cards} backed by the internal list of
     * {@code versionedPowerDeck}
     */
    @Override
    public ObservableList<PowerCard> getFilteredCardList() {
        return null;
        // return filteredCards;
    }

    @Override
    public void updateFilteredCardList(Predicate<PowerCard> predicate) {
        requireNonNull(predicate);
        // filteredCards.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof DeckModelManager)) {
            return false;
        }

        // state check
        DeckModelManager other = (DeckModelManager) obj;
        return this.selectedDeck.equals(other.selectedDeck)
                && userPrefs.equals(other.userPrefs);
        // && filteredCards.equals(other.filteredCards);
    }

    @Override
    public ArrayList<PowerDeck> getDecks() {
        return this.powerDecks;
    }

    @Override
    public void unSelectDeck() {
        this.selectedDeck = null;
    }

    /* ========================== When No Deck selected ============================ */

    @Override
    public void createDeck() {
        PowerDeck newDeck = new PowerDeck();
        this.powerDecks.add(newDeck);
    }

    @Override
    public void selectDeck(int deckIndex) {
        this.selectedDeck = this.powerDecks.get(deckIndex);
    }
}
