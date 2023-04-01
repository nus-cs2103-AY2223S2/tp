package seedu.techtrack.logic.commands;

import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.techtrack.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.techtrack.model.Model;
import seedu.techtrack.model.ModelManager;
import seedu.techtrack.model.UserPrefs;
import seedu.techtrack.model.role.Role;
import seedu.techtrack.testutil.RoleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRoleBook(), new UserPrefs());
    }

    @Test
    public void execute_newRole_success() {
        Role validRole = new RoleBuilder().build();

        Model expectedModel = new ModelManager(model.getRoleBook(), new UserPrefs());
        expectedModel.addRole(validRole);

        assertCommandSuccess(new AddCommand(validRole), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validRole), expectedModel);
    }

    @Test
    public void execute_duplicateRole_throwsCommandException() {
        Role roleInList = model.getRoleBook().getRoleList().get(0);
        assertCommandFailure(new AddCommand(roleInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
