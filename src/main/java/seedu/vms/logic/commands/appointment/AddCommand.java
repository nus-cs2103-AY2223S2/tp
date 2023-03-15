package seedu.vms.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.util.Map;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.vaccination.VaxType;

/**
 * Adds an appointment to the patient manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_GROUP = "appointment";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Adds an appointment to the patient manager. "
            + "Parameters: "
            + DELIMITER + PREFIX_PATIENT + " PATIENT_ID "
            + DELIMITER + PREFIX_STARTTIME + " START_TIME "
            + DELIMITER + PREFIX_ENDTIME + " END_TIME "
            + DELIMITER + PREFIX_VACCINATION + " VAX_GROUP\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " "
            + DELIMITER + PREFIX_PATIENT + " 1 "
            + DELIMITER + PREFIX_STARTTIME + " 2024-01-01 1330 "
            + DELIMITER + PREFIX_ENDTIME + " 2024-01-01 1400 "
            + DELIMITER + PREFIX_VACCINATION + " Dose 1 (Moderna)\n";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists"
            + " in the appointment manager";
    public static final String MESSAGE_EXISTING_PATIENT_ID = "This patient already has an existing appointment";
    public static final String MESSAGE_MISSING_VAX_TYPE = "The given vaccine is not in the vaccine manager";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Appointment}
     */
    public AddCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Checks if patient manager contains the given index
        Map<Integer, IdData<Patient>> patientList = model.getPatientManager().getMapView();
        if (!patientList.containsKey(toAdd.getPatient().getZeroBased())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        // Checks if vaxType manager contains the vaccine to be used in the appointment
        ObservableMap<String, VaxType> vaccinationList = model.getVaxTypeManager().asUnmodifiableObservableMap();
        if (!vaccinationList.containsKey(toAdd.getVaccination().getName())) {
            throw new CommandException(MESSAGE_MISSING_VAX_TYPE);
        }

        // Checks if appointment manager already contains an appointment from the patient of the given index
        Map<Integer, IdData<Appointment>> appointmentList = model.getAppointmentManager().getMapView();
        for (Map.Entry<Integer, IdData<Appointment>> entry : appointmentList.entrySet()) {
            Appointment appointment = entry.getValue().getValue();
            if (appointment.getPatient().equals(toAdd.getPatient())) {
                throw new CommandException(MESSAGE_EXISTING_PATIENT_ID);
            }
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
