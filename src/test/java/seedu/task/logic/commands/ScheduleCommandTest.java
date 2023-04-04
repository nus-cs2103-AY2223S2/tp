package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import seedu.task.model.task.Effort;

public class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalPlanner());

    @Test
    public void execute_validDateNullEffort_success() throws CommandException {
        ScheduleCommand scheduleCommand = new ScheduleCommand(LocalDate.parse("2023-04-28"));

        String expectedMessage = ScheduleCommand.SCHEDULE_SUCCESS_MESSAGE;
        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());

        expectedModel.schedule(LocalDate.parse("2023-04-28"));

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validDateNonNullEffort_success() throws CommandException {
        ScheduleCommand scheduleCommand = new ScheduleCommand(LocalDate.parse("2023-04-28"), new Effort(10));

        String expectedMessage = PlanCommand.PLAN_SUCCESS_MESSAGE + "\n" + ScheduleCommand.SCHEDULE_SUCCESS_MESSAGE;
        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());

        expectedModel.plan(10);
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

    @Test
    public void equals() {
        LocalDate date1 = LocalDate.parse("2023-03-23");
        LocalDate date2 = LocalDate.parse("2023-03-24");

        ScheduleCommand cmdA = new ScheduleCommand(date1);
        ScheduleCommand cmdB = new ScheduleCommand(date2);
        ScheduleCommand cmdC = new ScheduleCommand(date1);
        ScheduleCommand cmdD = new ScheduleCommand(date1, new Effort(5));
        ScheduleCommand cmdE = new ScheduleCommand(date1, new Effort(5));
        ScheduleCommand cmdF = new ScheduleCommand(date1, new Effort(1));
        ScheduleCommand cmdG = new ScheduleCommand(date2, new Effort(1));

        // same object -> returns true
        assertTrue(cmdA.equals(cmdA));

        // same date, effort null -> return true
        assertTrue(cmdA.equals(cmdC)); // both effort null
        assertTrue(cmdA.equals(cmdD)); // one effort null

        // same date, same effort -> return true
        assertTrue(cmdE.equals(cmdD));

        // same date, different effort -> return false
        assertFalse(cmdE.equals(cmdF));

        // different date -> return false
        assertFalse(cmdA.equals(cmdB)); // both effort null
        assertFalse(cmdC.equals(cmdG)); // one effort null
        assertFalse(cmdF.equals(cmdG)); // no null effort
    }
}
