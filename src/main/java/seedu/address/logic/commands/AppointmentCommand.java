package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Creates an appointment.
 */
public class AppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appointment";

    public static final String MESSAGE_USAGE = "";
    public static final String MESSAGE_SUCCESS = "New appointment booked: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment slot is already booked";

    public static final String MESSAGE_INVALID_PERSON = "This patient that you want to schedule an appointment for"
            + " does not exist";
    public static final String MESSAGE_INVALID_DOCTOR = "This doctor that you want to schedule an appointment with"
            + " does not exist";

    private final Appointment appointment;

    /**
     * Creates an AppointmentCommand to add the specified {@code Person}
     */
    public AppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasPatientByNric(appointment.getPatientNric())) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        if (!model.hasDrByNric(appointment.getDrNric())) {
            throw new CommandException(MESSAGE_INVALID_DOCTOR);
        }

        if (model.hasAppointment(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        Nric patientNricAppointment = appointment.getPatientNric();
        List<Person> persons = model.getFilteredPersonList();

        Patient appointmentPatient = null;
        Doctor appointmentDoctor = null;
        for (Person person : persons) {
            if (person.isDoctor()) {
                boolean isMatchDr = person.getNric().equals(appointment.getDrNric());
                if (isMatchDr) {
                    appointmentDoctor = (Doctor) person;
                }
            }
            if (person.isPatient()) {
                Nric otherPatientNricAppointment = person.getNric();
                if (patientNricAppointment.equals(otherPatientNricAppointment)) {
                    appointmentPatient = (Patient) person;
                }
            }
        }
        try {
            model.bookAppointment(appointment);
            appointmentPatient.addPatientAppointment(appointment);
            appointmentDoctor.addPatientAppointment(appointment);
        } catch (DuplicateAppointmentException e) {
            throw new DuplicateAppointmentException();
        }

        Patient editedPatient = new Patient(appointmentPatient.getName(), appointmentPatient.getPhone(),
                appointmentPatient.getEmail(), appointmentPatient.getNric(), appointmentPatient.getAddress(),
                appointmentPatient.getPrescriptions(), appointmentPatient.getTags(),
                appointmentPatient.getPatientAppointments(), appointmentPatient.getRole());

        Doctor editedDoctor = new Doctor(appointmentDoctor.getName(), appointmentDoctor.getPhone(),
                appointmentDoctor.getEmail(), appointmentDoctor.getNric(), appointmentDoctor.getAddress(),
                appointmentDoctor.getTags(),
                appointmentDoctor.getPatientAppointments(), appointmentDoctor.getRole());
        model.setPerson(appointmentPatient, editedPatient);
        model.setPerson(appointmentDoctor, editedDoctor);

        //todo confirm
        //model.updatePersonView(editedPatient);
        //model.updatePersonView(editedDoctor);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, appointment));
    }
}
