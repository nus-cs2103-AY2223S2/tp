package seedu.socket.logic.commands;

import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.testutil.TypicalProjects.getTypicalSocket;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;

public class ClearProjectCommandTest {

    @Test
    public void execute_emptyProjectList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearProjectCommand(), model, ClearProjectCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyProjectList_success() {
        Model model = new ModelManager(getTypicalSocket(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSocket(), new UserPrefs());
        expectedModel.setProjects(new ArrayList<>());

        assertCommandSuccess(new ClearProjectCommand(), model, ClearProjectCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
