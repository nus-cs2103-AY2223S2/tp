package seedu.task.logic.commands;

import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.logic.commands.StatsCommand.STATS_SUCCESS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.task.model.Model;
import seedu.task.model.ModelManager;

public class StatsCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_statsEmptyTaskBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new StatsCommand(), model, STATS_SUCCESS_MESSAGE, expectedModel);
    }

    @Test
    public void execute_stats_success() {
        CommandResult expectedCommandResult = new CommandResult(STATS_SUCCESS_MESSAGE, false, false);
        assertCommandSuccess(new StatsCommand(), model, expectedCommandResult, expectedModel);
    }
}
