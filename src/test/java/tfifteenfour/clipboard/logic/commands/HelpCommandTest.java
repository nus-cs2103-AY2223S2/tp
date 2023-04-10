package tfifteenfour.clipboard.logic.commands;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tfifteenfour.clipboard.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.testutil.TypicalModel;

public class HelpCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
    }

    @Test
    public void execute_help_success() {
        assertCommandSuccess(new HelpCommand(), model, SHOWING_HELP_MESSAGE, expectedModel);
    }
}

