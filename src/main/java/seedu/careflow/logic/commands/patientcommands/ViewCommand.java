package seedu.careflow.logic.commands.patientcommands;


import static java.util.Objects.requireNonNull;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.careflow.logic.parser.patientparser.PatientParser.OPERATION_TYPE;

import java.util.List;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.patient.Patient;

/**
 * Views a patient from the patient records
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = OPERATION_TYPE + " " + COMMAND_WORD
            + ": Views the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: "
            + PREFIX_INDEX + " INDEX (must be a positive integer)\n"
            + "Example: " + OPERATION_TYPE + " " + COMMAND_WORD + " -i 1";

    public static final String MESSAGE_VIEW_PATIENT_SUCCESS = "Viewed patient: %1$s";

    private Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof seedu.careflow.logic.commands.patientcommands.ViewCommand) {
            if (targetIndex != null) {
                return targetIndex.equals((
                        (seedu.careflow.logic.commands.patientcommands.ViewCommand)
                                other).targetIndex);
            }
        }
        return other == this;
    }

    /**
     *  Executes the Patient deletion
     * @param model {@code Model} which the command should operate on.
     * @return The command result if deletion is successful
     * @throws CommandException If an error occurred during deletion
     */
    public CommandResult execute(CareFlowModel model) throws CommandException {
        requireNonNull(model);
        List<Patient> patientList = model.getFilteredPatientList();
        if (targetIndex.getZeroBased() >= patientList.size() || targetIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        } else {
            Patient patientToView = patientList.get(targetIndex.getZeroBased());
            return new CommandResult(patientToView, String.format(MESSAGE_VIEW_PATIENT_SUCCESS, patientToView));
        }
    }
}
