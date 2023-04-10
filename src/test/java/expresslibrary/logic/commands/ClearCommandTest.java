package expresslibrary.logic.commands;

import static expresslibrary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static expresslibrary.testutil.TypicalExpressLibrary.getTypicalExpressLibrary;

import org.junit.jupiter.api.Test;

import expresslibrary.model.ExpressLibrary;
import expresslibrary.model.Model;
import expresslibrary.model.ModelManager;
import expresslibrary.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyExpressLibrary_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExpressLibrary_success() {
        Model model = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalExpressLibrary(), new UserPrefs());
        expectedModel.setExpressLibrary(new ExpressLibrary());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
