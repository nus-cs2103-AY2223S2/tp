package seedu.powercards.logic.commands;

import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.model.Model;


/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting PowerCards as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT,
                false, true, false, false, false, false, false, false, false, false);
    }

}
