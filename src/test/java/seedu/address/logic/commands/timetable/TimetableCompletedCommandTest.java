package seedu.address.logic.commands.timetable;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeliveryJobs.getTypicalDeliveryJobSystem;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TimetableCompletedCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryJobSystem(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), getTypicalDeliveryJobSystem(), new UserPrefs());
    }

    @Test
    public void execute_focusDateIsUpdated() {
        assertCommandSuccess(new TimetableCompletedCommand(),
                model, TimetableCompletedCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
