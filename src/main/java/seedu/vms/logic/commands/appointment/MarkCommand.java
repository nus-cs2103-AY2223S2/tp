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
 * Marks an appointment identified using it's displayed index from the appointment manager.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_GROUP = "appointment";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Marks the appointment identified by the index number used in the displayed appointment list as"
            + " completed.\n"
            + "Syntax: " + COMMAND_GROUP + " " + COMMAND_WORD + " INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_APPOINTMENT_SUCCESS = "Marked Appointment: %1$s";

    private final Index targetIndex;

    public MarkCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
        requireNonNull(model);
        Map<Integer, IdData<Appointment>> appointmentList = model.getAppointmentManager().getMapView();

        if (!appointmentList.containsKey(targetIndex.getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToMark = appointmentList.get(targetIndex.getZeroBased()).getValue();
        model.markAppointment(targetIndex.getZeroBased());
        return new CommandMessage(String.format(MESSAGE_MARK_APPOINTMENT_SUCCESS, appointmentToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && targetIndex.equals(((MarkCommand) other).targetIndex)); // state check
    }
}
