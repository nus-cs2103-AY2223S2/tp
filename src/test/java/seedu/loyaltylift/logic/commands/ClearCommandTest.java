package seedu.loyaltylift.logic.commands;

import static seedu.loyaltylift.logic.commands.CommandResult.ListViewGuiAction.REMOVE_INFO_FROM_VIEW;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.loyaltylift.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.Model;
import seedu.loyaltylift.model.ModelManager;
import seedu.loyaltylift.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model,
                new CommandResult(ClearCommand.MESSAGE_SUCCESS, REMOVE_INFO_FROM_VIEW), expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model,
                new CommandResult(ClearCommand.MESSAGE_SUCCESS, REMOVE_INFO_FROM_VIEW), expectedModel);
    }

}
