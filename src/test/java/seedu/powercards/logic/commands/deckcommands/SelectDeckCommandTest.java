package seedu.powercards.logic.commands.deckcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.deck.Deck;

public class SelectDeckCommandTest {

    private Model model = new ModelManager();
    private Deck testDeck = new Deck("Test Deck");

    @Test
    public void execute_validIndex_success() {
        model.addDeck(testDeck);
        Index validIndex = INDEX_FIRST;
        SelectDeckCommand selectDeckCommand = new SelectDeckCommand(validIndex);
        String expectedMessage = String.format(SelectDeckCommand.MESSAGE_SUCCESS, testDeck.getDeckName());
        Model expectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(
                expectedMessage, false, false, false, false, true, false, false, false, false, false);
        expectedModel.addDeck(testDeck);
        expectedModel.selectDeck(validIndex);

        assertCommandSuccess(selectDeckCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        model.addDeck(testDeck);
        Index invalidIndex = INDEX_SECOND;
        SelectDeckCommand selectDeckCommand = new SelectDeckCommand(invalidIndex);

        String expectedMessage = SelectDeckCommand.MESSAGE_INVALID_DECK_DISPLAYED_INDEX;
        assertCommandFailure(selectDeckCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Index index1 = INDEX_FIRST;
        Index index2 = INDEX_SECOND;
        SelectDeckCommand selectDeckCommand1 = new SelectDeckCommand(index1);
        SelectDeckCommand selectDeckCommand2 = new SelectDeckCommand(index1);
        SelectDeckCommand selectDeckCommand3 = new SelectDeckCommand(index2);

        // same object -> returns true
        assertTrue(selectDeckCommand1.equals(selectDeckCommand1));

        // same values -> returns true
        assertTrue(selectDeckCommand1.equals(selectDeckCommand2));

        // different index -> returns false
        assertFalse(selectDeckCommand1.equals(selectDeckCommand3));
    }
}
