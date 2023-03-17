package seedu.fitbook.logic.commands;

import seedu.fitbook.model.FitBookModel;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting FitBook as requested ...";

    @Override
    public CommandResult execute(FitBookModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
