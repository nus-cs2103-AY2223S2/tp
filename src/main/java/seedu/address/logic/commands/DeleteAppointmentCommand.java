package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;



/**
 * Deletes an appointment
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "deleteAppointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the appointment of the patient identified "
        + "by the NRIC and index number of the "
        + "corresponding patient's appointment listing. "
        + "\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "ic/ [NRIC]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + "ic/ S1234567Z";
    public static final String MESSAGE_SUCCESS = "Appointment deleted: %1$s";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Appointment: %2$s";

    public static final String MESSAGE_INVALID_INDEX = "Specified index is invalid";
    public static final String MESSAGE_INVALID_PATIENT = "This patient that you want to delete an appointment for"
            + " does not exist";

    private final Nric nric;
    private final Index index;

    /**
     * Creates a DeleteAppointmentCommand to delete the specified {@code Appointment}
     */
    public DeleteAppointmentCommand(Index index, Nric nric) {
        requireAllNonNull(index, nric);
        this.index = index;
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> persons = model.getFilteredPersonList();
        List<Appointment> appointments = model.getAddressBook().getAppointmentList();
        Patient appointmentPatient = null;
        boolean found = false;
        for (Person p : persons) {
            if (p.getNric().equals(nric)) {
                found = true;
            }
        }

        if (found == false) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }

        for (Person p : persons) {
            if (p.getNric().equals(nric)) {
                if (!p.isPatient()) {
                    throw new CommandException(MESSAGE_INVALID_PATIENT);
                }
                appointmentPatient = (Patient) p;
                break;
            }
        }

        int patientApptSize = appointmentPatient.getAppointmentSize();
        int ind = index.getZeroBased();
        if (patientApptSize <= 0 || ind >= patientApptSize) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }

        Appointment appointmentToDeletePatient = appointmentPatient.deletePatientAppointment(ind);
        Nric appointmentToDeleteDrNric = appointmentToDeletePatient.getDrNric();
        Doctor appointmentToDeleteDr = null;
        for (Person pp : persons) {
            if (pp.getNric().equals(appointmentToDeleteDrNric)) {
                appointmentToDeleteDr = (Doctor) pp;
            }
        }

        Appointment toDeleteDrAppmt = null;
        for (Appointment a : appointmentToDeleteDr.getPatientAppointments()) {
            if (a.isSameAppointment(appointmentToDeletePatient)) {
                toDeleteDrAppmt = a;
            }
        }
        appointmentToDeleteDr.deletePatientAppointment(toDeleteDrAppmt);
        /*
        return new CommandResult(String.valueOf(ind) + " "
                + appointmentPatient.getPatientAppointments().get(ind).toString()
        + "\n"
        + "patientapptsize: " + String.valueOf(patientApptSize) +"\n"
                + appointmentToDelete.toString());


        if (patientApptSize <= 0 || ind > patientApptSize) {
            throw new CommandException(MESSAGE_INVALID_INDEX);
        }
        Appointment appointmentToDelete = appointmentPatient.deletePatientAppointment(ind);
        // appointmentDoctor.deletePatientAppointment(appointment);
        model.deleteAppointment(appointmentToDelete);
        */
        Patient editedPatient = new Patient(appointmentPatient.getName(), appointmentPatient.getPhone(),
                appointmentPatient.getEmail(), appointmentPatient.getNric(), appointmentPatient.getAddress(),
                appointmentPatient.getPrescriptions(), appointmentPatient.getTags(),
                appointmentPatient.getPatientAppointments(), appointmentPatient.getRole());

        Doctor editedDoctor = new Doctor(appointmentToDeleteDr.getName(), appointmentToDeleteDr.getPhone(),
                appointmentToDeleteDr.getEmail(), appointmentToDeleteDr.getNric(), appointmentToDeleteDr.getAddress(),
                appointmentToDeleteDr.getTags(),
                appointmentToDeleteDr.getPatientAppointments(), appointmentToDeleteDr.getRole());
        model.setPatient(appointmentPatient, editedPatient);
        model.setDoctor(appointmentToDeleteDr, editedDoctor);

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.deleteAppointment(appointmentToDeletePatient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointmentToDeletePatient));


    }

    /*
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAppointmentCommand)) {
            return false;
        }

        // state check
        DeleteAppointmentCommand e = (DeleteAppointmentCommand) other;
        return index.equals(e.index)
                && appointment.equals(e.appointment);
    }
    */
}
