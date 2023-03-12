package seedu.modtrek.logic.commands;

import static seedu.modtrek.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modtrek.logic.commands.HelpCommand.SHOWING_ALL_MESSAGE_USAGE;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.Model;
import seedu.modtrek.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_helpNoArgs_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_ALL_MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(""), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpValidArgs_success() {
        CommandResult expectedCommandResult = new CommandResult(AddCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(AddCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }
}
