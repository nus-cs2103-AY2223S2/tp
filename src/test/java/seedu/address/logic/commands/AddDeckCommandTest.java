package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDecks.VALID_DECK_HISTORY;
import static seedu.address.testutil.TypicalDecks.VALID_DECK_SCIENCE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMasterDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.review.Review;

public class AddDeckCommandTest {

    @Test
    public void constructor_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDeckCommand(null));
    }

    @Test
    public void execute_deckAcceptedByModel_success() throws Exception {
        ModelStubAcceptingDeckAdded modelStub = new ModelStubAcceptingDeckAdded();

        CommandResult commandResult = new AddDeckCommand(VALID_DECK_HISTORY).execute(modelStub);

        assertEquals(String.format(AddDeckCommand.MESSAGE_SUCCESS, VALID_DECK_HISTORY.getDeckName()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(VALID_DECK_HISTORY), modelStub.decksAdded);
    }

    @Test
    public void execute_duplicateDeck_throwsCommandException() {
        Deck validDeck = new Deck("Biology");
        AddDeckCommand addDeckCommand = new AddDeckCommand(validDeck);
        ModelStub modelStub = new ModelStubWithDeck(validDeck);

        assertThrows(CommandException.class,
                AddDeckCommand.MESSAGE_DUPLICATE_DECK, () -> addDeckCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        AddDeckCommand addValidScienceDeckCommand = new AddDeckCommand(VALID_DECK_SCIENCE);
        AddDeckCommand addValidHistoryDeckCommand = new AddDeckCommand(VALID_DECK_HISTORY);

        // same object -> returns true
        assertTrue(addValidScienceDeckCommand.equals(addValidScienceDeckCommand));

        // same values -> returns true
        AddDeckCommand addValidScienceDeckCommandCopy = new AddDeckCommand(VALID_DECK_SCIENCE);
        assertTrue(addValidScienceDeckCommand.equals(addValidScienceDeckCommandCopy));

        // different types -> returns false
        assertFalse(addValidScienceDeckCommand.equals(1));

        // null -> returns false
        assertFalse(addValidScienceDeckCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(addValidScienceDeckCommand.equals(addValidHistoryDeckCommand));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
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
            return; // AddCommand does call updateFilteredCardList method
        }

        @Override
        public void updateFilteredDeckList(Predicate<Deck> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /* NEWLY ADDED COMMANDS TO SUPPORT DECK LIST */
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

        @Override
        public void removeDeck(Deck key) {
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


        public Optional<Review> getReview() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void reviewDeck(Index deckIndex) {
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
        public void flipCard() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean markWrong() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean markCorrect() {
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

    }

    /**
     * A Model stub that contains a single deck.
     */
    private class ModelStubWithDeck extends ModelStub {
        private final Deck deck;

        ModelStubWithDeck(Deck deck) {
            requireNonNull(deck);
            this.deck = deck;
        }

        @Override
        public boolean hasDeck(Deck deck) {
            requireNonNull(deck);
            return this.deck.isSameDeck(deck);
        }
    }

    /**
     * A Model stub that always accept the deck being added.
     */
    private class ModelStubAcceptingDeckAdded extends ModelStub {
        final ArrayList<Deck> decksAdded = new ArrayList<>();

        @Override
        public boolean hasDeck(Deck deck) {
            requireNonNull(deck);
            return decksAdded.stream().anyMatch(deck::isSameDeck);
        }
        @Override
        public void addDeck(Deck deck) {
            requireNonNull(deck);
            decksAdded.add(deck);
        }

    }

}
