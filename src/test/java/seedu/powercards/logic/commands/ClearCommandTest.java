package seedu.powercards.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import org.junit.jupiter.api.Test;

import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyMasterDeck_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyMasterDeck_success() {
        Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
        // expectedModel.setMasterDeck(new MasterDeck());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        ClearCommand clearCommand = new ClearCommand();

        // same object -> returns true
        assertTrue(clearCommand.equals(clearCommand));

        // different types -> returns false
        assertFalse(clearCommand.equals(1));

        // null -> returns false
        assertFalse(clearCommand.equals(null));

        // different ClearCommand -> returns true
        assertTrue(clearCommand.equals(new ClearCommand()));
    }

}
