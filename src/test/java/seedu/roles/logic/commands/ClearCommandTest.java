package seedu.roles.logic.commands;

import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;
import seedu.roles.model.RoleBook;
import seedu.roles.model.UserPrefs;

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
