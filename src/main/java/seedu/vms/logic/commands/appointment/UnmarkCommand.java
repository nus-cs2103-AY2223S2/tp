package seedu.vms.logic.commands.appointment;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.appointment.Appointment;

/**
 * Unmarks an appointment identified using it's displayed index from the appointment manager.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_GROUP = "appointment";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Unmarks the appointment identified by the index number used in the displayed appointment list"
            + " and change its status to incomplete.\n"
            + "Syntax: " + COMMAND_GROUP + " " + COMMAND_WORD + " INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_APPOINTMENT_SUCCESS = "Unmarked Appointment: %1$s";

    private final Index targetIndex;

    public UnmarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);
        Map<Integer, IdData<Appointment>> appointmentList = model.getAppointmentManager().getMapView();

        if (!appointmentList.containsKey(targetIndex.getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToUnmark = appointmentList.get(targetIndex.getZeroBased()).getValue();

        // @@author nusE0726844
        Index patientId = appointmentToUnmark.getPatient();
        for (Map.Entry<Integer, IdData<Appointment>> entry : model.getAppointmentManager().getMapView().entrySet()) {
            Appointment appointment = entry.getValue().getValue();
            if (appointment.getPatient().equals(patientId)
                    && !appointment.getStatus()) {
                throw new CommandException(String.format("Patient #%04d has active appointments",
                        patientId.getOneBased()));
            }
        }

        model.unmarkAppointment(targetIndex.getZeroBased());
        return new CommandMessage(String.format(MESSAGE_UNMARK_APPOINTMENT_SUCCESS, appointmentToUnmark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkCommand) other).targetIndex)); // state check
    }
}
