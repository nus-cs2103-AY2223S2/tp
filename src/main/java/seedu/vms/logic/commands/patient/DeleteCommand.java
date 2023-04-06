package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.index.Index;
import seedu.vms.commons.exceptions.UnexpectedChangeException;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.patient.Patient;

/**
 * Deletes a patient identified using it's displayed index from the patient manager.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_GROUP = "patient";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Deletes the patient identified by the PATIENT_ID used in the displayed patient list.\n"
            + "Parameters: PATIENT_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";

    private final Index targetIndex;
    private final boolean isForce;


    /**
     * Constructs a {@code DeleteCommand} that will force the change.
     */
    public DeleteCommand(Index targetIndex) {
        // TODO: this constructor is just so that test dont break. It should be removed.
        this(targetIndex, true);
    }


    /**
     * Constructs a {@code DeleteCommand}.
     *
     * @param targetIndex - the id of the patient to delete.
     * @param isForce - {@code true} if the changes the command will make
     *      should be forced and {@code false} otherwise.
     */
    public DeleteCommand(Index targetIndex, boolean isForce) {
        this.targetIndex = targetIndex;
        this.isForce = isForce;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);
        Map<Integer, IdData<Patient>> patientList = model.getPatientManager().getMapView();

        if (!patientList.containsKey(targetIndex.getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_ID);
        }

        Patient patientToDelete = patientList.get(targetIndex.getZeroBased()).getValue();
        try {
            model.deletePatient(targetIndex.getZeroBased(), isForce);
        } catch (UnexpectedChangeException uce) {
            throw new CommandException(String.format("%s\n%s",
                    uce.getMessage(),
                    Messages.MESSAGE_USE_FORCE));
        }
        return new CommandMessage(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
