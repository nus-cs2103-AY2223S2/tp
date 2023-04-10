package seedu.powercards.logic.commands.deckcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.commons.core.Messages.MESSAGE_DECKS_LISTED_OVERVIEW;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalDecks.VALID_DECK_SCIENCE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.deck.DeckContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindDecksCommandTest {
    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void equals() {
        FindDecksCommand findFirstCommand = new FindDecksCommand(Collections.singletonList("first"));
        FindDecksCommand findSecondCommand = new FindDecksCommand(Collections.singletonList("second"));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDecksCommand findFirstCommandCopy = new FindDecksCommand(Collections.singletonList("first"));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different find deck command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noDeckFound() {
        String userInput = " ";
        String expectedMessage = String.format(MESSAGE_DECKS_LISTED_OVERVIEW, 0);
        DeckContainsKeywordsPredicate predicate = prepareDeckPredicate(userInput);
        List keywords = prepareKeywords(userInput);
        FindDecksCommand command = new FindDecksCommand(keywords);
        CommandResult expectedCommandResult = new CommandResult(
                expectedMessage, false, false, false, false, false, false, false, true, false, false);
        expectedModel.updateFilteredDeckList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeckList());
    }

    @Test
    public void execute_singleKeywords_oneDecksFound() {
        String userInput = "science";
        String expectedMessage = String.format(MESSAGE_DECKS_LISTED_OVERVIEW, 1);
        DeckContainsKeywordsPredicate predicate = prepareDeckPredicate(userInput);
        List keywords = prepareKeywords(userInput);
        FindDecksCommand command = new FindDecksCommand(keywords);
        CommandResult expectedCommandResult = new CommandResult(
                expectedMessage, false, false, false, false, false, false, false, true, false, false);
        expectedModel.updateFilteredDeckList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(VALID_DECK_SCIENCE), model.getFilteredDeckList());
    }

    /**
     * Parses {@code userInput} into a {@code DeckContainsKeywordsPredicate}.
     */
    private DeckContainsKeywordsPredicate prepareDeckPredicate(String userInput) {
        return new DeckContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code List}.
     */
    private List prepareKeywords(String userInput) {
        return Arrays.asList(userInput.split("\\s+"));
    }
}
