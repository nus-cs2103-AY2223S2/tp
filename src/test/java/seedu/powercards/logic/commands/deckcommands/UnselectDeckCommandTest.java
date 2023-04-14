package seedu.powercards.logic.commands.deckcommands;

import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;

public class UnselectDeckCommandTest {

    @Test
    public void execute_unselectDeck_success() {
        Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        model.selectDeck(Index.fromOneBased(1));

        Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        expectedModel.selectDeck(Index.fromOneBased(1));

        CommandResult expectedCommandResult = new CommandResult(
                UnselectDeckCommand.MESSAGE_SUCCESS, false, false, false,
                false, false, true, false, false, false, false);

        expectedModel.unselectDeck();

        assertCommandSuccess(new UnselectDeckCommand(), model, expectedCommandResult, expectedModel);
    }

}
