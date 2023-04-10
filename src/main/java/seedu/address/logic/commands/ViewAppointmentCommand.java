package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.service.appointment.Appointment;

/**
 * Finds and selects a specified appointment identified by its id
 */
public class ViewAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "viewappointment";

    public static final String MESSAGE_APPOINTMENT_NOT_FOUND = "Appointment %d not in system";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Display appointment details given appointment id."
            + "Parameters: ID\n"
            + "Example: " + COMMAND_WORD + " 8";

    private final int appointmentId;

    public ViewAppointmentCommand(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.getShop().hasAppointment(this.appointmentId)) {
            throw new CommandException(String.format(MESSAGE_APPOINTMENT_NOT_FOUND, this.appointmentId));
        }
        model.updateFilteredAppointmentList(a -> a.getId() == this.appointmentId);
        Appointment current = model.getFilteredAppointmentList().get(0);
        model.selectAppointment(lst -> lst.stream().filter(a -> a.getId() == current.getId())
                .findFirst().orElse(null));
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENT_VIEW_OVERVIEW), Tab.APPOINTMENTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAppointmentCommand // instanceof handles nulls
                && this.appointmentId == ((ViewAppointmentCommand) other).appointmentId); // state check
    }
}
