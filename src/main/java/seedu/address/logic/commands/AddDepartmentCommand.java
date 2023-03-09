package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Adds a department to SudoHR.
 */
public class AddDepartmentCommand extends Command {

    public static final String COMMAND_WORD = "adep";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Added a department!");
    }
}
