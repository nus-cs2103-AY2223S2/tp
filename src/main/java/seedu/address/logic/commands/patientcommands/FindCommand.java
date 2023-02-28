package seedu.address.logic.commands.patientcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;
import seedu.address.model.Model;

/**
 * Finds a patient from the patient records
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        return null;
    }
}
