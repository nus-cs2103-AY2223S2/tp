package trackr.logic.commands;

import trackr.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Trackr as requested ...";

    /**
     * Returns the exit acknowledgement message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Exit acknowledgement message for display.
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
