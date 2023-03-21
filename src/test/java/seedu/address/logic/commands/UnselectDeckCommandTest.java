package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UnselectDeckCommandTest {

    @Test
    public void execute_unselectDeck_success() {
        Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        expectedModel.unselectDeck();

        assertCommandSuccess(new UnselectDeckCommand(), model, UnselectDeckCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
