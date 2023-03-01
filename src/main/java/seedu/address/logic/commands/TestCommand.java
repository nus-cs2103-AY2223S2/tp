package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class TestCommand extends Command{

    public static final String COMMAND_WORD = "test";

    public static final String MESSAGE_SUCCESS = "test command please ignore";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
