package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.powercard.PowerCard;
import seedu.address.model.powerdeck.PowerDeck;
import seedu.address.model.powerdeck.ReadOnlyPowerDeck;

/**
 * Represents the in-memory model of the deck data.
 */
public class DeckModelManager implements DeckModel {
    private static final Logger logger = LogsCenter.getLogger(DeckModelManager.class);
    private final ArrayList<PowerDeck> powerDecks;
    private final UserPrefs userPrefs;
    private PowerDeck selectedDeck;
    private FilteredList<PowerCard> filteredCards;

    /**
     * Initializes a CardModelManager with the given deck and userPrefs.
     */
    public DeckModelManager(ArrayList<PowerDeck> powerDecks, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(userPrefs);

        logger.fine("Initializing with user prefs " + userPrefs);
        this.powerDecks = powerDecks;
        this.userPrefs = new UserPrefs(userPrefs);
    }

    public DeckModelManager() {
        this(new ArrayList<PowerDeck>(), new UserPrefs());
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

    /* ========================== PowerDeck Operations ============================ */

    @Override
    public void setDeck(ReadOnlyPowerDeck readOnlyDeck) { // I think this is for loading from storage
        this.selectedDeck.resetData(readOnlyDeck);
    }

    // Todo: Link getDeck() to GUI
    @Override
    public ReadOnlyPowerDeck getSelectedDeck() {
        return this.selectedDeck;
    }

    @Override
    public void createDeck() { // Todo: deck should have a name - how to store in storage?
        PowerDeck newDeck = new PowerDeck();
        this.powerDecks.add(newDeck);
    }

    @Override
    public void selectDeck(Index deckIndex) {
        int zeroBasesIdx = deckIndex.getZeroBased();
        selectedDeck = powerDecks.get(zeroBasesIdx);
    }

    @Override
    public void unselectDeck() {
        this.selectedDeck = null;
        this.filteredCards = null;
    }

    // Todo: link PowerDecks to GUI through getDecks
    @Override
    public List<PowerDeck> getDecks() {
        return this.powerDecks;
    }

    /* ========================== PowerCard Operations ============================ */

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
        if (!(obj instanceof DeckModelManager)) {
            return false;
        }

        // state check
        DeckModelManager other = (DeckModelManager) obj;
        return selectedDeck.equals(other.selectedDeck)
                && powerDecks.equals(other.powerDecks)
                && userPrefs.equals(other.userPrefs)
                && filteredCards.equals(other.filteredCards);
    }
}
