package seedu.careflow.logic.commands.patientcommands;

import static java.util.Objects.requireNonNull;

import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.PatientRecord;

/**
 * Empties the existing Patient records
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Patient record has been cleared!";
    @Override
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        model.setPatientRecord(new PatientRecord());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
