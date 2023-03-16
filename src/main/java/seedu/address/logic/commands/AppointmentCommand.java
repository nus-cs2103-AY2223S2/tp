package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.*;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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

        if (!model.hasPatientByNric(appointment.getPatientNric())) { // todo hasPerson by name not person
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        // todo only disallow appointment duplication per doctor and not the entire system
        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        Nric patientNricAppointment = appointment.getPatientNric();
        List<Person> persons = model.getFilteredPersonList();

        Patient appointmentPatient = null;
        for (Person person : persons) {
            if (person.isDoctor()) {
                continue;
            }
            Nric otherPatientNricAppointment = person.getNric();
            if (patientNricAppointment.equals(otherPatientNricAppointment)) {
                appointmentPatient = (Patient) person;
            }
        }
        try {
            model.bookAppointment(appointment);
            appointmentPatient.addPatientAppointment(appointment);
        } catch (DuplicateAppointmentException e) {
        }
        // String s = appointmentPatient.patientAppointmentstoString();
        Patient editedPatient = new Patient(appointmentPatient.getName(), appointmentPatient.getPhone(),
                appointmentPatient.getEmail(), appointmentPatient.getNric(), appointmentPatient.getAddress(),
                appointmentPatient.getTags(), appointmentPatient.getPatientAppointments());

        model.setPerson(appointmentPatient, editedPatient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(fullSuccessMessage(appointment), appointment));
    }

    public String getPatientNric(Appointment appointment) {
        Nric patientNric = appointment.getPatientNric();
        String patientNameStr = patientNric.toString();
        return patientNameStr;
    }

    public String fullSuccessMessage(Appointment appointment) {
        String fullMessage = MESSAGE_SUCCESS + getPatientNric(appointment); // todo show the name instead of ic
        return fullMessage;
    }
}
