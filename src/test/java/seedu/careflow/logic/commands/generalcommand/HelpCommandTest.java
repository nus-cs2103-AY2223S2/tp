package seedu.careflow.logic.commands.generalcommand;

import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.logic.commands.generalcommand.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;

public class HelpCommandTest {
    private CareFlowModel model = new CareFlowModelManager();
    private CareFlowModel expectedModel = new CareFlowModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
