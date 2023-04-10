package seedu.address.logic.commands.reminder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeliveryJobs.getTypicalDeliveryJobSystem;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ListReminderCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryJobSystem(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), getTypicalDeliveryJobSystem(), new UserPrefs());
    }

    @Test
    public void execute_reminderList_showsReminderList() {
        CommandResult listReminderCommandResult = new CommandResult(ListReminderCommand.SHOWING_REMINDER_LIST_MESSAGE,
                false, false, true, false, false);
        assertCommandSuccess(new ListReminderCommand(), model, listReminderCommandResult, expectedModel);
    }

}
