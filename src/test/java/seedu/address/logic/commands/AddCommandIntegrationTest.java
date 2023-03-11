package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRoles.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Role;
import seedu.address.testutil.RoleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newRole_success() {
        Role validRole = new RoleBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addRole(validRole);

        assertCommandSuccess(new AddCommand(validRole), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validRole), expectedModel);
    }

    @Test
    public void execute_duplicateRole_throwsCommandException() {
        Role roleInList = model.getAddressBook().getRoleList().get(0);
        assertCommandFailure(new AddCommand(roleInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
