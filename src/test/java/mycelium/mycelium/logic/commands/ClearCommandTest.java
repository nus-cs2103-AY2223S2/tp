package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.logic.commands.CommandTestUtil.assertCommandSuccess;
import static mycelium.mycelium.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.UserPrefs;

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
