package seedu.address.logic.commands;

import seedu.address.model.ApplicationModel;

/**
 * Terminates the program.
 */
public class ExitSprintCommand extends ApplicationCommand {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting sprINT as requested ...";

    @Override
    public CommandResult execute(ApplicationModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
