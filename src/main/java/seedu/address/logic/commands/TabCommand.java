package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Switches between UI tabs in the application.
 */
public class TabCommand extends Command {
    public static final String COMMAND_WORD = "tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Tab command is WIP");
    }
}
