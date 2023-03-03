package seedu.address.logic.commands.patientcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CareFlowModel;
import seedu.address.model.PatientRecord;

import static java.util.Objects.requireNonNull;

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
