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
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;

/**
 * Transfers the person indicated by the index to the specified ward.
 */

public class UpdateWardCommand extends Command {
    public static final String COMMAND_WORD = "trfward";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Moves the person."
            + "Parameters: "
            + "Index " + PREFIX_WARD + "WARD "
            + "Example: " + COMMAND_WORD + " "
            + "2 " + PREFIX_WARD + "Block C Ward 1";

    public static final String MESSAGE_SUCCESS = "Patient %1$s has been transferred from ward %2$s to ward %3$s";
    public static final String MESSAGE_WARD_NOT_FOUND = "Ward not found: %1$s";
    public static final String MESSAGE_WARD_INCORRECT = "New ward of person is incorrect";
    public static final String MESSAGE_STAFF_DETECTED = "Staff %1$s has been transferred from ward %2$s to ward %3$s";
    public static final String MESSAGE_NOT_SHOWING_PERSON_LIST = "Transfer Ward Command does not work on wards.";

    private final String nextWard;
    private final Index patient;

    /**
     * Creates an UpdatePatientWardCommand to change specified {@code Index} ward {@code ogWard} to
     * {@code nextWard}.
     */
    public UpdateWardCommand(Index patient, String nextWard) {
        requireNonNull(patient);
        requireNonNull(nextWard);
        this.nextWard = nextWard;
        this.patient = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Patientist patientist = (Patientist) model.getPatientist();
        if (!patientist.isShowingPersonList()) {
            throw new CommandException(MESSAGE_NOT_SHOWING_PERSON_LIST);
        }

        if (!model.hasWard(new Ward(nextWard))) {
            throw new CommandException(String.format(MESSAGE_WARD_NOT_FOUND, nextWard));
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        if (patient.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToBeUpdated = lastShownList.get(patient.getZeroBased());
        Ward ward = null;

        for (String wardName : model.getWardNames()) {
            if (model.getWard(wardName).containsPerson(personToBeUpdated)) {
                ward = model.getWard(wardName);
                break;
            }
        }

        if (ward == null) {
            throw new CommandException("Patient not found in Patientist");
        }

        if (new IsStaffPredicate().test(personToBeUpdated)) {
            try {
                Staff staffToBeUpdated = (Staff) personToBeUpdated;
                patientist.transferStaff(staffToBeUpdated, ward, model.getWard(nextWard));
                return new CommandResult(String.format(MESSAGE_STAFF_DETECTED, patient.getOneBased(), ward, nextWard));
            } catch (Exception e) {
                throw new CommandException(MESSAGE_WARD_INCORRECT);
            }
        }

        try {
            Patient patientToBeUpdated = (Patient) personToBeUpdated;
            patientist.transferPatient(patientToBeUpdated, ward, model.getWard(nextWard));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_WARD_INCORRECT);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getOneBased(), ward, nextWard));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateWardCommand
                && patient.equals(((UpdateWardCommand) other).patient)
                && nextWard.equals(((UpdateWardCommand) other).nextWard)); // instanceof handles nulls
    }
}
