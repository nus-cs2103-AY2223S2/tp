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
    public void execute_helpAdd_success() {
        CommandResult expectedCommandResult = new CommandResult(AddCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(AddCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpEdit_success() {
        CommandResult expectedCommandResult = new CommandResult(EditCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(EditCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpDelete_success() {
        CommandResult expectedCommandResult = new CommandResult(DeleteCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(DeleteCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpTag_success() {
        CommandResult expectedCommandResult = new CommandResult(TagCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(TagCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpList_success() {
        CommandResult expectedCommandResult = new CommandResult(ListCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(ListCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpFind_success() {
        CommandResult expectedCommandResult = new CommandResult(FindCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(FindCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpExit_success() {
        CommandResult expectedCommandResult = new CommandResult(ExitCommand.MESSAGE_USAGE);
        assertCommandSuccess(new HelpCommand(ExitCommand.MESSAGE_USAGE), model, expectedCommandResult, expectedModel);
    }
}
