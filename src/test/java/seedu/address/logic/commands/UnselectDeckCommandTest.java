package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deckcommands.UnselectDeckCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UnselectDeckCommandTest {

    @Test
    public void execute_unselectDeck_success() {
        Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(
                UnselectDeckCommand.MESSAGE_SUCCESS, false, false, false, false, false, true);
        expectedModel.unselectDeck();

        assertCommandSuccess(new UnselectDeckCommand(), model, expectedCommandResult, expectedModel);
    }

}
