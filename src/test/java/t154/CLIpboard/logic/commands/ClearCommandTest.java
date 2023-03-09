package t154.CLIpboard.logic.commands;

import static t154.CLIpboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static t154.CLIpboard.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import t154.CLIpboard.model.AddressBook;
import t154.CLIpboard.model.Model;
import t154.CLIpboard.model.ModelManager;
import t154.CLIpboard.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
