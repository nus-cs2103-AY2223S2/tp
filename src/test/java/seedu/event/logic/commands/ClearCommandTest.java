package seedu.event.logic.commands;

import static seedu.event.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.event.testutil.TypicalContacts.getTypicalContactList;
import static seedu.event.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.jupiter.api.Test;

import seedu.event.model.ContactList;
import seedu.event.model.EventBook;
import seedu.event.model.Model;
import seedu.event.model.ModelManager;
import seedu.event.model.UserPrefs;

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
        expectedModel.setContactList(new ContactList());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
