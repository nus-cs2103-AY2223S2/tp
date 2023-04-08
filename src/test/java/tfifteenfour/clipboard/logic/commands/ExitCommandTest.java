
package tfifteenfour.clipboard.logic.commands;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class ExitCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
    }

    @Test
    public void execute_exit_success() {
        ExitCommand exitCommand = new ExitCommand();
        CommandResult expectedCommandResult = new CommandResult(exitCommand, ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, false);
        assertCommandSuccess(exitCommand, model, expectedCommandResult, model);
    }

}

