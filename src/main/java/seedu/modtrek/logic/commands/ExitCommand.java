package seedu.modtrek.logic.commands;

import seedu.modtrek.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting ModTrek as requested ...";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits ModTrek.\n"
            + "Parameters: NIL\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
