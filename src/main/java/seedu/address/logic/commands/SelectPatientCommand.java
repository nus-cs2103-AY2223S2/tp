package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.patient.Patient;

/**
 * Lists a specific patient in Docedex to the user.
 */
public class SelectPatientCommand extends Command implements CommandInterface {

    public static final String COMMAND_WORD = "sp";

    private static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final String MESSAGE_SELECT_PATIENT_SUCCESS = "Selected patient %1$s";

    private Index targetIndex;

    public SelectPatientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    public static String getCommandUsage() {
        return MESSAGE_USAGE;
    }

    public static String getMessageSuccess() {
        return MESSAGE_SELECT_PATIENT_SUCCESS;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }
        Patient patientToShow = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(String.format(MESSAGE_SELECT_PATIENT_SUCCESS, patientToShow),
                patientToShow);
    }
}
