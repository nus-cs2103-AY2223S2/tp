package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

/**
 * Displays a patient with full detail, identified using it's displayed index from the address book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the entity identified by the index number used in the displayed current list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_ENTITY_SUCCESS = "Showing Patient:";

    private final Index targetIndex;

    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownList = model.getFilteredPatientList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }
        Patient patientToDisplay = lastShownList.get(targetIndex.getZeroBased());
        Predicate<Appointment> predicate = new Predicate<Appointment>() {
            @Override
            public boolean test(Appointment appointment) {
                return appointment.getPatientName().fullName.equals(patientToDisplay.getName().fullName);
            }
        };
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(String.format(MESSAGE_SHOW_ENTITY_SUCCESS, model.getFilteredAppointmentList()),
                true, targetIndex.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
