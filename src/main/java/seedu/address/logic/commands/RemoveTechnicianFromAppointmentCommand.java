package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TECHNICIAN_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Removes a specific technician from a specific appointment
 */
public class RemoveTechnicianFromAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "removeappointmenttech";
    public static final String MESSAGE_SUCCESS_FORMAT = "Technician %d unassigned from Appointment %d";
    public static final String COMMAND_USAGE =
            COMMAND_WORD + ": Unassigns an existing technician from an existing appointment. "
                    + "Parameters: "
                    + PREFIX_TECHNICIAN_ID + "TECHNICIAN_ID "
                    + PREFIX_APPOINTMENT_ID + "APPOINTMENT_ID "
                    + "Example Usage: "
                    + PREFIX_TECHNICIAN_ID + "2 "
                    + PREFIX_APPOINTMENT_ID + "3";

    private final int techId;
    private final int appointmentId;

    /**
     * @param techId        ID of technician
     * @param appointmentId ID of appointment
     */
    public RemoveTechnicianFromAppointmentCommand(int techId, int appointmentId) {
        this.techId = techId;
        this.appointmentId = appointmentId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.getShop().removeTechnicianFromAppointment(techId, appointmentId);
            model.selectAppointment(lst -> lst.stream().filter(a -> a.getId() == appointmentId)
                    .findFirst().orElse(null));
            return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, this.techId, this.appointmentId),
                    Tab.APPOINTMENTS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
