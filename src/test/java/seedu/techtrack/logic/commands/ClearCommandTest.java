package seedu.techtrack.logic.commands;

import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.techtrack.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.Test;

import seedu.techtrack.model.Model;
import seedu.techtrack.model.ModelManager;
import seedu.techtrack.model.RoleBook;
import seedu.techtrack.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyRoleBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRoleBook_success() {
        Model model = new ModelManager(getTypicalRoleBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalRoleBook(), new UserPrefs());
        expectedModel.setRoleBook(new RoleBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
