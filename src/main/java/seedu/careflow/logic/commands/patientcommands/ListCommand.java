package seedu.careflow.logic.commands.patientcommands;

import static java.util.Objects.requireNonNull;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;

/**
 * Lists all patients in the patient records
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all patients";

    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPatientList(model.PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
