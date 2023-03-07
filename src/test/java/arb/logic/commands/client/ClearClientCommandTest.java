package arb.logic.commands.client;

import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import arb.model.AddressBook;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;

public class ClearClientCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearClientCommand(), model, ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearClientCommand(), model, ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
