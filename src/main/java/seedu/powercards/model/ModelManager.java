package seedu.powercards.model;

import static java.util.Objects.requireNonNull;
import static seedu.powercards.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.util.Pair;
import seedu.powercards.commons.core.GuiSettings;
import seedu.powercards.commons.core.LogsCenter;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.CardHasTagPredicate;
import seedu.powercards.model.card.CardInDeckPredicate;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.review.Review;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.model.tag.Tag.TagName;

/**
 * Represents the in-memory model of the master deck data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MasterDeck masterDeck;
    private final UserPrefs userPrefs;
    private final FilteredList<Deck> filteredDecks;
    private final FilteredList<Card> filteredCards;
    private Deck selectedDeck;
    private Review currReview;
    private int numCardsPerReview = -1;

    /**
     * Initializes a ModelManager with the given masterDeck and userPrefs.
     */
    public ModelManager(ReadOnlyMasterDeck masterDeck, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(masterDeck, userPrefs);

        logger.fine("Initializing with PowerCards: " + masterDeck + " and user prefs " + userPrefs);

        this.masterDeck = new MasterDeck(masterDeck);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCards = new FilteredList<>(this.masterDeck.getCardList());
        filteredDecks = new FilteredList<>(this.masterDeck.getDeckList());
        updateFilteredCardList(PREDICATE_SHOW_NO_CARDS);
    }

    public ModelManager() {
        this(new MasterDeck(), new UserPrefs());
    }

    /* ==================================== UserPrefs ==================================== */

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
        return userPrefs.getMasterDeckFilePath();
    }

    @Override
    public void setMasterDeckFilePath(Path masterDeckFilePath) {
        requireNonNull(masterDeckFilePath);
        userPrefs.setMasterDeckFilePath(masterDeckFilePath);
    }

    /* ==================================== MasterDeck Operations ==================================== */

    @Override
    public void setMasterDeck(ReadOnlyMasterDeck deck) {
        this.masterDeck.resetData(deck);
    }

    @Override
    public ReadOnlyMasterDeck getMasterDeck() {
        return masterDeck;
    }

    /* ==================================== PowerCards Operations ==================================== */

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
        masterDeck.addCard(card); // Todo: setDeck of card to selectedDeck here
    }

    @Override
    public void setCard(Card target, Card editedCard) {
        requireAllNonNull(target, editedCard);
        masterDeck.setCard(target, editedCard);
    }

    /* ==================================== Filtered Card/Deck List Accessors ==================================== */

    /**
     * Returns an unmodifiable view of the list of {@code Card} backed by the internal list of
     * {@code versionedMasterDeck}
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

    /* ==================================== PowerDeck Operations ==================================== */

    @Override
    public void updateFilteredDeckList(Predicate<Deck> predicate) {
        requireNonNull(predicate);
        filteredDecks.setPredicate(predicate);
    }

    @Override
    public void addDeck(Deck deck) {
        masterDeck.addDeck(deck);
    }

    @Override
    public boolean hasDeck(Deck deck) {
        return masterDeck.hasDeck(deck);
    }

    @Override
    public void setDeck(Deck target, Deck editedDeck) {
        masterDeck.setDeck(target, editedDeck);
    }

    @Override
    public void moveCards(Deck oldDeck, Deck newDeck) {
        masterDeck.moveCards(oldDeck, newDeck);
    }

    /**
     * Deletes the given deck and the associated cards in that deck.
     *
     * @param deckToDelete Deck to be deleted.
     */
    @Override
    public void deleteDeck(Deck deckToDelete) {
        masterDeck.removeDeck(deckToDelete);
    }

    @Override
    public void selectDeck(Index deckIndex) {
        int zeroBasesIdx = deckIndex.getZeroBased();
        Deck toSelect = filteredDecks.get(zeroBasesIdx);

        // Always unselect the previous deck before selecting a new deck
        if (selectedDeck != null) {
            setDeck(selectedDeck, selectedDeck.buildUnselectedDeck());
        }

        selectedDeck = toSelect.buildSelectedDeck();
        setDeck(toSelect, selectedDeck); // also updates GUI to highlight blue card

        updateFilteredCardList(new CardInDeckPredicate(selectedDeck));
    }

    @Override
    public void unselectDeck() {
        assert this.selectedDeck != null : "Can only unselectDeck when a deck is selected";

        setDeck(selectedDeck, selectedDeck.buildUnselectedDeck());
        this.selectedDeck = null;
        updateFilteredCardList(PREDICATE_SHOW_NO_CARDS);
    }

    @Override
    public Optional<Deck> getSelectedDeck() {
        return Optional.ofNullable(selectedDeck);
    }

    @Override
    public String getSelectedDeckName() {
        return Optional.ofNullable(selectedDeck)
                .map(Deck::getDeckName)
                .orElse("No deck selected.");
    }

    @Override
    public int getDeckSize(int deckIndex) {
        Deck deck = filteredDecks.get(deckIndex);
        return new FilteredList<>(
                masterDeck.getCardList(), new CardInDeckPredicate(deck)
        ).size();
    }

    @Override
    public int getDeckSizeFilteredTag(int deckIndex, List<TagName> difficulties) {
        Deck deck = filteredDecks.get(deckIndex);
        return new FilteredList<>(masterDeck.getCardList(),
                new CardInDeckPredicate(deck)
                        .and(new CardHasTagPredicate(difficulties))).size();
    }

    /* ==================================== Review Operations ==================================== */

    /**
     * Starts a new review session based on deckIndex selected
     * @param deckIndex Index of the deck
     */
    @Override
    public void reviewDeck(Index deckIndex, List<TagName> difficulties) {
        int zeroBasesIdx = deckIndex.getZeroBased();
        Deck deckToReview = filteredDecks.get(zeroBasesIdx);

        List<Card> cardsToReview =
                new FilteredList<>(masterDeck.getCardList(),
                        new CardInDeckPredicate(deckToReview)
                                .and(new CardHasTagPredicate(difficulties)));

        currReview = new Review(deckToReview, cardsToReview, numCardsPerReview);
    }

    /**
     * Returns the card list in Review
     */
    @Override
    public ObservableList<Card> getReviewCardList() {
        assert currReview != null : "Must be in review mode";
        return currReview.getFilteredReviewCardList();
    }

    @Override
    public void setNumCardsPerReview(int n) {
        numCardsPerReview = n;
    }

    @Override
    public boolean goToPrevCard() {
        boolean isFirstCard = this.currReview.goToPrevCard();
        return isFirstCard;
    }

    @Override
    public boolean goToNextCard() {
        boolean isLastCard = this.currReview.goToNextCard();
        return isLastCard;
    }

    @Override
    public Optional<Review> getReview() {
        return Optional.ofNullable(currReview);
    };

    @Override
    public void endReview() {
        currReview = null;
        updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
        if (selectedDeck != null) {
            updateFilteredCardList(new CardInDeckPredicate(selectedDeck));
        } else {
            updateFilteredCardList(PREDICATE_SHOW_NO_CARDS);
        }
    }

    @Override
    public String getReviewDeckName() {
        assert currReview != null : "Review command executed without a Review session.";
        return currReview.getDeckName();
    }

    @Override
    public void flipCard() {
        assert currReview != null : "Flip command executed without a Review session.";
        currReview.flipCurrCard();
    }

    @Override
    public boolean isReviewCardFlipped() {
        assert currReview != null : "Flip command executed without a Review session.";
        return currReview.isCurrCardFlipped();
    }

    @Override
    public void tagCurrentCardInReview(Tag tag) {
        masterDeck.tagCard(currReview.getCurrCard(), tag); // modifies masterDeck
        currReview.tagCurrentCard(tag); // reflects in GUI
    }

    @Override
    public ObservableList<Pair<String, String>> getReviewStatsList() {
        return currReview.getReviewStatsList();
    }

    @Override
    public ObservableList<Pair<String, String>> getReviewDeckNameList() {
        return currReview.getReviewDeckNameList();
    }

    /* ==================================== Model States ==================================== */

    @Override
    public ModelState getState() {
        if (currReview != null) {
            return ModelState.REVIEW_MODE;
        }

        if (selectedDeck != null) {
            return ModelState.MAIN_SELECTED_MODE;
        }

        return ModelState.MAIN_UNSELECTED_MODE;
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
                && filteredDecks.equals(other.filteredDecks)
                && filteredCards.equals(other.filteredCards); // todo: check equal selectedDeck and review
    }
}
