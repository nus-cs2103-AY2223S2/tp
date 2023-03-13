package seedu.address.logic.commands;

import seedu.address.model.ModelNew;

/**
 * Terminates the program.
 */
public class ExitCommandNew extends CommandNew {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Ultron as requested ...";

    @Override
    public CommandResultNew execute(ModelNew model) {
        return new CommandResultNew(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
