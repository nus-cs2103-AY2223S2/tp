package seedu.careflow.logic.commands.patientcommands;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.logic.parser.CliSyntax.PREFIX_IC;

import java.util.List;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.commons.core.index.Index;
import seedu.careflow.logic.commands.Command;
import seedu.careflow.logic.commands.CommandResult;
import seedu.careflow.logic.commands.exceptions.CommandException;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.person.Ic;
import seedu.careflow.model.person.Patient;

/**
 * Deletes a patient from the patient records
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1"
            + "\nOR\n"
            + COMMAND_WORD + ":  Deletes the the patient identified by the IC number.\n"
            + "Parameters: "
            + PREFIX_IC + " IC\n"
            + "Example: " + COMMAND_WORD + " -ic T1234567A";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted patient: %1$s";

    private Index targetIndex;

    private Ic targetIc;


    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public DeleteCommand(Ic ic) {
        this.targetIc = ic;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.careflow.logic.commands.patientcommands.DeleteCommand
                // instanceof handles nulls
                && targetIndex.equals((
                        (seedu.careflow.logic.commands.patientcommands.DeleteCommand)
                                other).targetIndex)); // state check
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
        if (targetIc == null) {
            if (targetIndex.getZeroBased() >= patientList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
            }
            Patient patientToDelete = patientList.get(targetIndex.getZeroBased());
            model.deletePatient(patientToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete));
        } else {
            Patient patientToDelete = null; // delete using patient ic
            for (Patient patient : patientList) {
                if (patient.getIc().equals(targetIc)) {
                    patientToDelete = patient;
                    break;
                }
            }
            if (patientToDelete == null) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_IC);
            }
            model.deletePatient(patientToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete));
        }
    }
}
