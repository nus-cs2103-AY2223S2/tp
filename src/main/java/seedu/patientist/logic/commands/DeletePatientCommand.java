package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_ID;

import java.util.List;

import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.patient.DummyPatient;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.ward.Ward;

/**
 * Deletes a patient using their unique id number.
 */
public class DeletePatientCommand extends Command {
    public static final String COMMAND_WORD = "delpat";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by their id number.\n"
            + "Parameters: id/ID_NUMBER\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ID + "A612877Z";

    public static final String MESSAGE_DELETE_PATIENT_ID_SUCCESS = "Deleted Patient with id: %1$s";

    public static final String MESSAGE_PATIENT_ID_NOT_FOUND = "Patient with id: %1$s not found";

    private final IdNumber idNumber;

    /**
     * Creates a DeletePatientCommand to remove the {@code Patient} based on their {@code IdNumber}.
     *
     * @param idNumber The id number of the staff member.
     */
    public DeletePatientCommand(IdNumber idNumber) {
        requireNonNull(idNumber);
        this.idNumber = idNumber;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<String> wardNames = model.getWardNames();
        Patient patientToMatch = new DummyPatient(idNumber);

        for (String wardName : wardNames) {
            Ward ward = model.getWard(wardName);
            if (ward.containsPatient(patientToMatch)) {
                ward.deletePatientById(patientToMatch);
                return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_ID_SUCCESS, idNumber));
            }
        }

        throw new CommandException(String.format(MESSAGE_PATIENT_ID_NOT_FOUND, idNumber));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same instance
                || (other instanceof DeletePatientCommand)
                && this.idNumber.equals(((DeletePatientCommand) other).idNumber);
    }
}
