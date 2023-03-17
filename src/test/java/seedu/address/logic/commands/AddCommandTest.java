package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
import seedu.address.model.MasterDeck;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMasterDeck;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.review.Review;
import seedu.address.testutil.CardBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Card validCard = new CardBuilder().build();
        CommandResult commandResult = new AddCommand(validCard).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validCard), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCard), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Card validCard = new CardBuilder().build();
        AddCommand addCommand = new AddCommand(validCard);
        ModelStub modelStub = new ModelStubWithPerson(validCard);
        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_CARD, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Card alice = new CardBuilder().withQuestion("Alice").build();
        Card bob = new CardBuilder().withQuestion("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different card -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
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

        /*
        @Override
        public void setDeck(Deck target, Deck editedDeck) {

        }
         */

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
     * A Model stub that contains a single card.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Card card;

        ModelStubWithPerson(Card card) {
            requireNonNull(card);
            this.card = card;
        }

        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return this.card.isSameCard(card);
        }
    }

    /**
     * A Model stub that always accept the card being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Card> personsAdded = new ArrayList<>();
        @Override
        public boolean hasCard(Card card) {
            requireNonNull(card);
            return personsAdded.stream().anyMatch(card::isSameCard);
        }

        @Override
        public void addCard(Card card) {
            requireNonNull(card);
            personsAdded.add(card);
        }

        @Override
        public ReadOnlyMasterDeck getMasterDeck() {
            return new MasterDeck();
        }
    }

}
