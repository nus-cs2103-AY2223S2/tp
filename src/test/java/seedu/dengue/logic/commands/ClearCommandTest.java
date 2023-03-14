package seedu.dengue.logic.commands;

import static seedu.dengue.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import org.junit.jupiter.api.Test;

import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.Model;
import seedu.dengue.model.ModelManager;
import seedu.dengue.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyDengueHotspotTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyDengueHotspotTracker_success() {
        Model model = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalDengueHotspotTracker(), new UserPrefs());
        expectedModel.setDengueHotspotTracker(new DengueHotspotTracker());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
