package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Tracker;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalModules;

public class ClearCommandTest {

    @Test
    public void execute_emptyTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTracker_success() {
        Model model = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalModules.getTypicalTracker(), new UserPrefs());
        expectedModel.setTracker(new Tracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
