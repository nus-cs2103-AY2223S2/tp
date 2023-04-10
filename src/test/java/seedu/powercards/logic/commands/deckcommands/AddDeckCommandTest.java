package seedu.powercards.logic.commands.deckcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalDecks.VALID_DECK_HISTORY;
import static seedu.powercards.testutil.TypicalDecks.VALID_DECK_SCIENCE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.model.ModelStub;
import seedu.powercards.model.deck.Deck;

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
     * A Model stub that contains a single deck.
     */
    private static class ModelStubWithDeck extends ModelStub {
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
    private static class ModelStubAcceptingDeckAdded extends ModelStub {
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
