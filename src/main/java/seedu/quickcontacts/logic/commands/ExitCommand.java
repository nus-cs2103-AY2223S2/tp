package seedu.quickcontacts.logic.commands;

import seedu.quickcontacts.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting QuickContacts as requested ...";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n" + "Example: " + COMMAND_WORD;
    public static final String COMMAND_DESCRIPTION = "Exits the program.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
