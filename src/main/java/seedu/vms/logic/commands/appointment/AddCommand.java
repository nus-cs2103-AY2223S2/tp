package seedu.vms.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.ObservableMap;
import seedu.vms.commons.core.Messages;
import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Age;
import seedu.vms.model.GroupName;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.vaccination.VaxChecker;
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
            + DELIMITER + PREFIX_VACCINATION + " Dose 1 (Mordena)\n";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists"
            + " in the appointment manager";
    public static final String MESSAGE_MISSING_VAX_TYPE = "The given vaccine is not in the vaccine manager";
    public static final String MESSAGE_MISSING_VAX_REQ = "The Patient does not have previous appointments for the"
            + "needed vaccine";
    public static final String MESSAGE_EXIST_VAX_REQ = "The Patient already has an appointment for this vaccine dose";
    public static final String MESSAGE_EXISTING_APPOINTMENT = "This patient already has an upcoming appointment";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Appointment}
     */
    public AddCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandMessage execute(Model model) throws CommandException {
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

        // Checks for no existing next appointment
        for (Map.Entry<Integer, IdData<Appointment>> entry : model.getAppointmentManager().getMapView().entrySet()) {
            Appointment appointment = entry.getValue().getValue();
            if (appointment.getPatient().equals(toAdd.getPatient())
                    && appointment.getAppointmentEndTime().isAfter(LocalDateTime.now())
                    && !appointment.getStatus()) {
                throw new CommandException(MESSAGE_EXISTING_APPOINTMENT);
            }
        }

        // Checks if the given patient can take the vaccination
        Patient patient = patientList.get(toAdd.getPatient().getZeroBased()).getValue();
        VaxType toTake = vaccinationList.get(toAdd.getVaccination().getName());

        List<VaxType> patientHistory = patient.getVaccine()
                .stream()
                .map(vaxName -> vaccinationList.get(vaxName.getName()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Age patientAge;
        try {
            patientAge = new Age(LocalDateTime.now().getYear() - patient.getDob().value.getYear());
        } catch (IllegalArgumentException illArgEx) {
            // if for some reason the user decide to turn back time on their system
            throw new CommandException("Patient contains an invalid DOB");
        }

        HashSet<GroupName> allergies = new HashSet<>(patient.getAllergy());

        boolean isTakable = VaxChecker.check(toTake, patientAge, allergies, patientHistory);

        if (!isTakable) {
            throw new CommandException("Patient cannot take the vaccination");
        }

        model.addAppointment(toAdd);
        return new CommandMessage(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
