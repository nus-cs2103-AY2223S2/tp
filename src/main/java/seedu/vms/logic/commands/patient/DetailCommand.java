package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;

import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.patient.Patient;

/**
 * Command to show the details of a patient.
 */
public class DetailCommand extends Command {
    public static final String COMMAND_WORD = "detail";
    public static final String COMMAND_GROUP = "patient";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Gets the detail of the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Detailing patient: #%04d %s";
    public static final String MESSAGE_FAILURE = "The patient does not exist";

    private final Index index;

    /**
     * Constructs a {@code DetailCommand}.
     */
    public DetailCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);
        IdData<Patient> patient = model
                .getPatientManager()
                .getMapView()
                .get(index.getZeroBased());
        if (patient == null) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        model.setDetailedPatient(patient);
        return new CommandMessage(
                String.format(MESSAGE_SUCCESS, patient.getId() + 1, patient.getValue().getName().fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DetailCommand // instanceof handles nulls
                        && index.equals(((DetailCommand) other).index)); // state check
    }
}
