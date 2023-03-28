package seedu.quickcontacts.logic.commands;

import static seedu.quickcontacts.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.quickcontacts.testutil.TypicalAddressBooks.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.quickcontacts.model.AddressBook;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.ModelManager;
import seedu.quickcontacts.model.UserPrefs;

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
