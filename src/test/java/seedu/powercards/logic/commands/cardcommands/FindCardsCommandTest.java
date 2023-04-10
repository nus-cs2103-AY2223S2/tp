package seedu.powercards.logic.commands.cardcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.commons.core.Messages.MESSAGE_CARDS_LISTED_OVERVIEW;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.LOOP;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.card.QuestionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCardsCommandTest {
    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void equals() {
        FindCardsCommand findFirstCommand = new FindCardsCommand(Collections.singletonList("first"));
        FindCardsCommand findSecondCommand = new FindCardsCommand(Collections.singletonList("second"));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCardsCommand findFirstCommandCopy = new FindCardsCommand(Collections.singletonList("first"));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different find card command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCardsFound() {
        String userInput = " ";
        String expectedMessage = String.format(MESSAGE_CARDS_LISTED_OVERVIEW, 0);
        QuestionContainsKeywordsPredicate predicate = prepareCardPredicate(userInput);
        List keywords = prepareKeywords(userInput);
        FindCardsCommand command = new FindCardsCommand(keywords);
        CommandResult expectedCommandResult = new CommandResult(
                expectedMessage, false, false, false, false, false, false, true, false, false, false);
        expectedModel.selectDeck(INDEX_FIRST);
        model.selectDeck(INDEX_FIRST);
        expectedModel.updateFilteredCardList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCardList());
    }

    @Test
    public void execute_singleKeywords_oneCardFound() {
        String userInput = "loop";
        String expectedMessage = String.format(MESSAGE_CARDS_LISTED_OVERVIEW, 1);
        QuestionContainsKeywordsPredicate predicate = prepareCardPredicate(userInput);
        List keywords = prepareKeywords(userInput);
        FindCardsCommand command = new FindCardsCommand(keywords);
        CommandResult expectedCommandResult = new CommandResult(
                expectedMessage, false, false, false, false, false, false, true, false, false, false);
        expectedModel.selectDeck(INDEX_FIRST);
        model.selectDeck(INDEX_FIRST);
        expectedModel.updateFilteredCardList(predicate);
        assertCommandSuccess(command, model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(LOOP), model.getFilteredCardList());
    }

    /**
     * Parses {@code userInput} into a {@code DeckContainsKeywordsPredicate}.
     */
    private QuestionContainsKeywordsPredicate prepareCardPredicate(String userInput) {
        return new QuestionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code List}.
     */
    private List prepareKeywords(String userInput) {
        return Arrays.asList(userInput.split("\\s+"));
    }
}
