package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TECHNICIAN_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds an existing technician to a specific appointment
 */
public class AddTechnicianToAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "addappointmenttech";
    public static final String MESSAGE_SUCCESS_FORMAT = "Technician %d added to Appointment %d";
    public static final String COMMAND_USAGE =
        COMMAND_WORD + ": Assigns an existing technician to an existing appointment. "
            + "Parameters: "
            + PREFIX_TECHNICIAN_ID + "TECHNICIAN_ID "
            + PREFIX_APPOINTMENT_ID + "APPOINTMENT_ID "
            + "Example Usage: "
            + PREFIX_TECHNICIAN_ID + "2 "
            + PREFIX_APPOINTMENT_ID + "3";

    private final int techId;
    private final int appointmentId;

    /**
     * Constructs command that adds an existing technician to a
     * specified appointment
     *
     * @param techId        ID of technician
     * @param appointmentId ID of appointment
     */
    public AddTechnicianToAppointmentCommand(int techId, int appointmentId) {
        this.techId = techId;
        this.appointmentId = appointmentId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            model.getShop().addTechnicianToAppointment(techId, appointmentId);
            model.selectAppointment(lst -> lst.stream().filter(a -> a.getId() == appointmentId).findFirst().get());
            return new CommandResult(String.format(MESSAGE_SUCCESS_FORMAT, this.techId, this.appointmentId),
                Tab.APPOINTMENTS);
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }
    }
}
