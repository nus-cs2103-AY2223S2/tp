package seedu.calidr.logic.commands;

import seedu.calidr.logic.commands.exceptions.CommandException;
import seedu.calidr.model.Model;

/**
 * Test command please ignore
 */
public class TestCommand extends Command {

    public static final String COMMAND_WORD = "test";

    public static final String MESSAGE_SUCCESS = "test command please ignore";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
