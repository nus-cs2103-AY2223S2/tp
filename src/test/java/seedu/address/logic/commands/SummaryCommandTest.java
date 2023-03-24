package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SummaryCommand.SHOWING_SUMMARY_MESSAGE;

public class SummaryCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_summary_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_SUMMARY_MESSAGE, false, true, false);
        assertCommandSuccess(new SummaryCommand(), model, expectedCommandResult, expectedModel);
    }
}
