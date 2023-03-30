package seedu.sprint.logic.commands;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.ApplicationModel;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting sprINT as requested ...";

    @Override
    public CommandResult execute(ApplicationModel model, CommandHistory commandHistory) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
