package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyEventBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyEventBook_success() {
        Model model = new ModelManager(getTypicalEventBook(), getTypicalContactList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalEventBook(), getTypicalContactList(), new UserPrefs());
        expectedModel.setEventBook(new EventBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
