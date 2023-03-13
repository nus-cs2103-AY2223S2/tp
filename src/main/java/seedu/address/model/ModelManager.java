package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.card.Card;
import seedu.address.model.card.CardInDeckPredicate;
import seedu.address.model.deck.Deck;
import seedu.address.model.review.Review;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private MasterDeck masterDeck;
    private final UserPrefs userPrefs;
    private FilteredList<Deck> filteredDecks;
    private Optional<Deck> selectedDeck = Optional.empty();
    private Optional<Review> currReview = Optional.empty();
    private FilteredList<Card> filteredCards;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyMasterDeck masterDeck, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(masterDeck, userPrefs);

        logger.fine("Initializing with address book: " + masterDeck + " and user prefs " + userPrefs);

        this.masterDeck = new MasterDeck(masterDeck);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCards = new FilteredList<>(this.masterDeck.getCardList());
        filteredDecks = new FilteredList<>(this.masterDeck.getDeckList());
    }

    public ModelManager() {
        this(new MasterDeck(), new UserPrefs());
    }



    //=========== UserPrefs ==================================================================================

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
    public Path getMasterDeckFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setMasterDeckFilePath(Path masterDeckFilePath) {
        requireNonNull(masterDeckFilePath);
        userPrefs.setAddressBookFilePath(masterDeckFilePath);
    }

    //=========== PowerDeck ================================================================================

    @Override
    public void setMasterDeck(ReadOnlyMasterDeck deck) {
        this.masterDeck.resetData(deck);
    }

    @Override
    public ReadOnlyMasterDeck getMasterDeck() {
        return masterDeck;
    }

    @Override
    public boolean hasCard(Card card) {
        requireNonNull(card);
        return masterDeck.hasCard(card);
    }

    @Override
    public void deleteCard(Card target) {
        masterDeck.removeCard(target);
    }

    @Override
    public void addCard(Card card) {
        masterDeck.addCard(card);
    }

    @Override
    public void setCard(Card target, Card editedCard) {
        requireAllNonNull(target, editedCard);

        masterDeck.setCard(target, editedCard);
    }

    //=========== Filtered Card/Deck List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Card} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Card> getFilteredCardList() {
        return filteredCards;
    }

    public ObservableList<Deck> getFilteredDeckList() {
        return filteredDecks;
    }

    @Override
    public void updateFilteredCardList(Predicate<Card> predicate) {
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
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return masterDeck.equals(other.masterDeck)
                && userPrefs.equals(other.userPrefs)
                && filteredDecks.equals(other.filteredDecks);
    }


    /* NEWLY ADDED COMMANDS TO SUPPORT DECKS */
    @Override
    public void updateFilteredDeckList(Predicate<Deck> predicate) {
        requireNonNull(predicate);
        filteredDecks.setPredicate(predicate);
    }

    @Override
    public void addDeck(Deck deck) {
        masterDeck.addDeck(deck);
        updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
    }

    @Override
    public boolean hasDeck(Deck deck) {
        return masterDeck.hasDeck(deck);
    }

    @Override
    public void removeDeck(Deck key) { //TODO should remove all cards associated with deck
        masterDeck.removeDeck(key);
        updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
    }

    @Override
    public void selectDeck(Index deckIndex) {
        int zeroBasesIdx = deckIndex.getZeroBased();
        selectedDeck = Optional.of(filteredDecks.get(zeroBasesIdx));
    }

    @Override
    public void unselectDeck() {
        this.selectedDeck = Optional.empty();
        updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
    }

    @Override
    public Optional<Deck> getSelectedDeck() {
        return selectedDeck;
    }

    @Override
    public String getSelectedDeckName() {
        return selectedDeck.get().getDeckName();
    }

    /**
     * Starts a new review session based on deckIndex selected
     * @param deckIndex
     */
    public void reviewDeck(Index deckIndex) {
        int zeroBasesIdx = deckIndex.getZeroBased();
        Deck deckToReview = filteredDecks.get(zeroBasesIdx);
        List<Card> cardList = new FilteredList<>(
                masterDeck.getCardList(), new CardInDeckPredicate(deckToReview)
        );
        currReview = Optional.of(new Review(deckToReview, cardList));
    };

    @Override
    public Optional<Review> getReview() {
        return currReview;
    };

    @Override
    public void endReview() {
        currReview = Optional.empty();
        // Todo: set predicate to showing all cards again?
    }

    @Override
    public String getReviewDeckName() {
        return currReview.map(rev -> rev.getDeckName()).orElse(null);
    }

}
