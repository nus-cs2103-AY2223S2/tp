package seedu.address.logic.commands.timetable;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDeliveryJobs.getTypicalDeliveryJobSystem;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TimetableDateCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryJobSystem(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalDeliveryJobSystem(), new UserPrefs());
    }

    @Test
    public void execute_focusDateIsUpdated() {
        String showTimetableMessage = String.format(TimetableDateCommand.SHOWING_TIMETABLE_MESSAGE, LocalDate.now());
        assertCommandSuccess(new TimetableDateCommand(LocalDate.now()),
                model,
                new CommandResult(showTimetableMessage, false, true, false, false, false),
                expectedModel);
    }
}
