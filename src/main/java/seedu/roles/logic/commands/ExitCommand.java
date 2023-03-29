package seedu.roles.logic.commands;

import seedu.roles.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Company Book as requested ...";

    @Override
    public CommandResult<String> execute(Model model) {
        return new CommandResult<>(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
