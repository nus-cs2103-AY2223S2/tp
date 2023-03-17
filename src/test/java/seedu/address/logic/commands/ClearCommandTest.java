package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExecutiveProDb;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyExecutiveProDb_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExecutiveProDb_success() {
        Model model = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());
        expectedModel.setExecutiveProDb(new ExecutiveProDb());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
