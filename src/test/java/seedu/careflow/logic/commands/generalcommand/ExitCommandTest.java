package seedu.careflow.logic.commands.generalcommand;

import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.logic.commands.generalcommand.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;

public class ExitCommandTest {
    private CareFlowModel model = new CareFlowModelManager();
    private CareFlowModel expectedModel = new CareFlowModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
