package seedu.medinfo.logic.commands;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting MedInfo as requested ...";

    /**
     * Executes the {@code ExitCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
