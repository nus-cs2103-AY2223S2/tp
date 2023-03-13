package codoc.logic.commands;

import static codoc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static codoc.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import codoc.model.Model;
import codoc.model.ModelManager;
import codoc.model.UserPrefs;
import codoc.model.AddressBook;

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
