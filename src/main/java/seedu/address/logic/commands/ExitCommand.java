package seedu.address.logic.commands;

import seedu.address.logic.commands.results.ExitCommandResult;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public ExitCommandResult execute(Model model) {
        return new ExitCommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

}
