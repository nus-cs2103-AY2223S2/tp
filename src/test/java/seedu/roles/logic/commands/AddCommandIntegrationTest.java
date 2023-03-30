package seedu.roles.logic.commands;

import static seedu.roles.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;
import seedu.roles.model.UserPrefs;
import seedu.roles.model.job.Role;
import seedu.roles.testutil.RoleBuilder;

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
