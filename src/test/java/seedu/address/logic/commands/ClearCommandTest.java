package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void execute_getCommandUsage_success() {
        String messageUsage = ClearCommand.COMMAND_WORD + ": Clears all entries from the address book.\n"
                + "Example: " + ClearCommand.COMMAND_WORD;
        assertEquals(ClearCommand.getCommandUsage(), messageUsage);
    }

    @Test
    public void execute_getMessageSuccess_success() {
        String messageSuccess = "Address book has been cleared!";
        assertEquals(ClearCommand.getMessageSuccess(), messageSuccess);
    }

}
