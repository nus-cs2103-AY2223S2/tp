package seedu.address.logic.commands.patientcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;

/**
 * Adds a patient to the patient records
 */
public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        return null;
    }
}
