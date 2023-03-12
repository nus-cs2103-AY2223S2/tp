package seedu.address.logic.commands.generalcommand;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.CareFlowModel;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Careflow as requested ...";

    @Override
    public CommandResult execute(CareFlowModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }
}
