package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Name;

import static java.util.Objects.requireNonNull;

public class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appointment";

    public static final String MESSAGE_USAGE = "";
    public static final String MESSAGE_SUCCESS = "New appointment booked for "; // todo patient name
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment slot is already booked";

    public static final String MESSAGE_INVALID_PERSON = "This patient does not exist";
    private final Appointment appointment;

    public AppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPerson(appointment.getName())) { // todo hasPerson by name not person
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        // todo only disallow appointment duplication per doctor and not the entire system
        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.bookAppointment(appointment);
        return new CommandResult(String.format(fullSuccessMessage(appointment), appointment));
    }

    public String getPatientName(Appointment appointment) {
        Name patientName = appointment.getName();
        String patientNameStr = patientName.toString();
        return patientNameStr;
    }

    public String fullSuccessMessage(Appointment appointment) {
        String fullMessage = MESSAGE_SUCCESS + getPatientName(appointment);
        return fullMessage;
    }
}
