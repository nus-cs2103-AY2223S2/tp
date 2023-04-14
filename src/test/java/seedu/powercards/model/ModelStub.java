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
 * A default model stub that have all the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getMasterDeckFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setMasterDeckFilePath(Path deckFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCard(Card card) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setMasterDeck(ReadOnlyMasterDeck newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyMasterDeck getMasterDeck() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCard(Card card) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCard(Card target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCard(Card target, Card editedCard) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Card> getFilteredCardList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Deck> getFilteredDeckList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCardList(Predicate<Card> predicate) {
        return; // AddCardCommand does call updateFilteredCardList method
    }

    @Override
    public void updateFilteredDeckList(Predicate<Deck> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    /* ==================================== Deck Operations ==================================== */

    @Override
    public Optional<Deck> getSelectedDeck() {
        return Optional.of(new Deck("Default"));
    }

    @Override
    public void addDeck(Deck deck) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasDeck(Deck deck) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDeck(Deck target, Deck editedDeck) {
        throw new AssertionError("This method should not be called.");
    }

    public void moveCards(Deck oldDeck, Deck newDeck) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteDeck(Deck key) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void selectDeck(Index deckIndex) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void unselectDeck() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String getSelectedDeckName() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getDeckSize(int deckIndex) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getDeckSizeFilteredTag(int deckIndex, List<TagName> difficulties) {
        throw new AssertionError("This method should not be called.");
    }

    /* ==================================== Review Operations ==================================== */

    @Override
    public Optional<Review> getReview() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Card> getReviewCardList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void reviewDeck(Index deckIndex, List<TagName> difficulties) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setNumCardsPerReview(int limit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Pair<String, String>> getReviewStatsList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void endReview() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String getReviewDeckName() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Pair<String, String> > getReviewDeckNameList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void flipCard() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean goToPrevCard() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean goToNextCard() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ModelState getState() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void tagCurrentCardInReview(Tag tag) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isReviewCardFlipped() {
        throw new AssertionError("This method should not be called.");
    }
}
