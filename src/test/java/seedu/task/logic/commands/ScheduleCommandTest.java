package seedu.task.logic.commands;

import static seedu.task.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalPlanner;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;

public class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalPlanner());

    @Test
    public void execute_validDate_success() throws CommandException {
        ScheduleCommand scheduleCommand = new ScheduleCommand(LocalDate.parse("2023-04-28"));

        String expectedMessage = ScheduleCommand.SCHEDULE_SUCCESS_MESSAGE;
        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());

        expectedModel.schedule(LocalDate.parse("2023-04-28"));

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEffort_throwsCommandException() {
        ScheduleCommand scheduleCommand = new ScheduleCommand(LocalDate.parse("2023-09-28"));
        assertCommandFailure(scheduleCommand, model, ScheduleCommand.OUT_OF_RANGE_MESSAGE);
    }

    @Test
    public void execute_planEmptyTaskBook_throwsCommandException() {
        Model model = new ModelManager();
        ModelManager expectedModel = new ModelManager();

        assertCommandFailure(new ScheduleCommand(LocalDate.parse("2023-04-28")), model,
                ScheduleCommand.OUT_OF_RANGE_MESSAGE);
    }
}
