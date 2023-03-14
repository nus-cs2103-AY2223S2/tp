package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.*;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
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

        if (!model.hasPerson(appointment.getName())) { // todo hasPerson by name not person
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        // todo only disallow appointment duplication per doctor and not the entire system
        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }
        Name patientNameAppointment = appointment.getName();
        List<Person> patients = model.getFilteredPersonList();
        Person appointmentPatient = null;
        for (Person patient : patients) {
            Name otherPatientNameAppointment = patient.getName();
            if (patientNameAppointment.equals(otherPatientNameAppointment)) {
                appointmentPatient = patient;
            }
        }
        try {
            model.bookAppointment(appointment);
            appointmentPatient.addPatientAppointment(appointment);
        } catch (DuplicateAppointmentException e) {
        }
        // String s = appointmentPatient.patientAppointmentstoString();
        Person editedPatient = new Person(appointmentPatient.getName(), appointmentPatient.getPhone(),
                appointmentPatient.getEmail(), appointmentPatient.getAddress(), appointmentPatient.getTags(),
                appointmentPatient.getPatientAppointments());

        model.setPerson(appointmentPatient, editedPatient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
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
