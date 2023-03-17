package seedu.vms.logic.commands.basic;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandMessage execute(Model model) {
        return new CommandMessage(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
