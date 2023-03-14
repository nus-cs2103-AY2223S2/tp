package trackr.logic.commands;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalPersons.getTypicalAddressBook;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.TaskList;
import trackr.model.UserPrefs;

public class ClearTaskCommandTest {

    @Test
    public void execute_emptyTaskList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTaskList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
        expectedModel.setTaskList(new TaskList());

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
