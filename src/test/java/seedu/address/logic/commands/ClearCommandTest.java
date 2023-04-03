package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult.ModuleEditInfo;
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

        ClearCommand cc = new ClearCommand();
        CommandResult actual = cc.execute(model);

        assertEquals(new CommandResult(ClearCommand.MESSAGE_SUCCESS,
                new ModuleEditInfo(TypicalModules.getCs2040s(), null),
                new ModuleEditInfo(TypicalModules.getSt2334(), null)), actual);

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
