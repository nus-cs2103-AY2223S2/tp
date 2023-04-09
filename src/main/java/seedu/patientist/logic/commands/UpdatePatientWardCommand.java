package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_WARD;

import java.util.List;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.IsStaffPredicate;
import seedu.patientist.model.ward.Ward;

/**
 * Update Patient's ward identified using it's displayed index from the patientist book.
 */

public class UpdatePatientWardCommand extends Command {
    public static final String COMMAND_WORD = "trfward";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Moves the patient."
            + "Parameters: "
            + "Index " + PREFIX_WARD + "WARD "
            + "Example: " + COMMAND_WORD + " "
            + "2 " + PREFIX_WARD + "Block C Ward 1";

    public static final String MESSAGE_SUCCESS = "Patient %1$s has been transferred from ward %2$s to ward %3$s";
    public static final String MESSAGE_WARD_NOT_FOUND = "Ward not found: %1$s";
    public static final String MESSAGE_WARD_INCORRECT = "New ward of patient is incorrect";
    public static final String MESSAGE_STAFF_DETECTED = "Index %1$s is a staff";

    private final String nextWard;
    private final Index patient;

    /**
     * Creates an UpdatePatientWardCommand to change specified {@code Index} ward {@code ogWard} to
     * {@code nextWard}.
     */
    public UpdatePatientWardCommand(Index patient, String nextWard) {
        requireNonNull(patient);
        requireNonNull(nextWard);
        this.nextWard = nextWard;
        this.patient = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasWard(new Ward(nextWard))) {
            throw new CommandException(String.format(MESSAGE_WARD_NOT_FOUND, nextWard));
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        if (patient.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToBeUpdated = lastShownList.get(patient.getZeroBased());
        Ward ward = null;
        if (new IsStaffPredicate().test(personToBeUpdated)) {
            throw new CommandException(String.format(MESSAGE_STAFF_DETECTED, patient.getOneBased()));
        }
        Patient patientToBeUpdated = (Patient) personToBeUpdated;
        for (String wardName : model.getWardNames()) {
            if (model.getWard(wardName).containsPatient(patientToBeUpdated)) {
                ward = model.getWard(wardName);
                break;
            }
        }

        if (ward == null) {
            throw new CommandException("Patient not found in Patientist");
        }
        Patientist patientist = (Patientist) model.getPatientist();
        try {
            patientist.transferPatient(patientToBeUpdated, ward, model.getWard(nextWard));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_WARD_INCORRECT);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getOneBased(), ward, nextWard));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdatePatientWardCommand
                && patient.equals(((UpdatePatientWardCommand) other).patient)
                && nextWard.equals(((UpdatePatientWardCommand) other).nextWard)); // instanceof handles nulls
    }
}
