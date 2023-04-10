package seedu.connectus.logic.commands;

import static seedu.connectus.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.connectus.testutil.TypicalPersons.getTypicalConnectUs;

import org.junit.jupiter.api.Test;

import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.Model;
import seedu.connectus.model.ModelManager;
import seedu.connectus.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyConnectUs_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyConnectUs_success() {
        Model model = new ModelManager(getTypicalConnectUs(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalConnectUs(), new UserPrefs());
        expectedModel.setConnectUs(new ConnectUs());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
