package seedu.vms.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import java.util.Map;

import seedu.vms.commons.core.Messages;
import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.IdData;
import seedu.vms.model.Model;
import seedu.vms.model.appointment.Appointment;
import seedu.vms.model.patient.Patient;

/**
 * Adds a patient to the patient manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the patient manager. "
            + "Parameters: "
            + DELIMITER + PREFIX_PATIENT + " PATIENT ID "
            + DELIMITER + PREFIX_STARTTIME + " START TIME "
            + DELIMITER + PREFIX_ENDTIME + " END TIME "
            + DELIMITER + PREFIX_VACCINATION + " VAX GROUP\n"
            + "Example: appointment " + COMMAND_WORD + " "
            + DELIMITER + PREFIX_PATIENT + " 1 "
            + DELIMITER + PREFIX_STARTTIME + " 2024-01-01 "
            + DELIMITER + PREFIX_ENDTIME + " 2024-01-01 "
            + DELIMITER + PREFIX_VACCINATION + " Mordena\n";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists"
            + " in the appointment manager";

    private final Appointment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Map<Integer, IdData<Patient>> patientList = model.getPatientManager().getMapView();
        int patientId = toAdd.getPatient().getZeroBased();
        if (!patientList.containsKey(patientId)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patient = patientList.get(patientId).getValue();

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
