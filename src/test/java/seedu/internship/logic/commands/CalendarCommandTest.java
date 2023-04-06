package seedu.internship.logic.commands;

import static seedu.internship.logic.commands.CalendarCommand.MESSAGE_CALENDAR_TIP;
import static seedu.internship.logic.commands.CalendarCommand.MESSAGE_SHOW_CALENDAR;
import static seedu.internship.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.internship.model.Model;
import seedu.internship.model.ModelManager;

public class CalendarCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_calendar_success() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_SHOW_CALENDAR + MESSAGE_CALENDAR_TIP,
                ResultType.CALENDAR);
        assertCommandSuccess(new CalendarCommand(), model, expectedCommandResult, expectedModel);
    }
}
