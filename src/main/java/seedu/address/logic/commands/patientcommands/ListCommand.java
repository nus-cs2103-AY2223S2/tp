package seedu.address.logic.commands.patientcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;

/**
 * Lists all patients in the patient records
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        return null;
    }
}
