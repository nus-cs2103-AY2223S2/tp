package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.ward.Ward;

/**
 * Deletes a status to the patient identified using it's displayed index from the patientist book.
 */
public class DeletePatientStatusCommand extends Command {
    public static final String COMMAND_WORD = "delpatstatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                                               + ": Deletes a status identified by the index number used in the"
                                               + "DetailsPopup panel to the patient identified by the index number used"
                                               + " in the displayed person list.\n"
                                               + "Parameters: "
                                               + "INDEX OF PATIENT (must be a positive integer) "
                                               + "INDEX OF STATUS (must be a positive integer)\n"
                                               + "Example: " + COMMAND_WORD + " "
                                               + "1 "
                                               + "1";

    public static final String MESSAGE_DELETE_STATUS_SUCCESS = "Deleted Status %1$s from %2$s.";
    public static final String MESSAGE_NOT_PATIENT = "Person selected is not a Patient.";
    public static final String MESSAGE_NOT_SHOWING_PERSON_LIST =
            "Delete Patient Status Command does not work with wards.";

    private final Index targetIndex;
    private final Index targetStatusIndex;

    /**
     * Creates an AddPatientStatusCommand to add the specified {@code PatientStatusDeatils} into the target Patient.
     */
    public DeletePatientStatusCommand(Index targetIndex, Index targetStatusIndex) {
        this.targetIndex = targetIndex;
        this.targetStatusIndex = targetStatusIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Patientist patientist = (Patientist) model.getPatientist();
        if (!patientist.isShowingPersonList()) {
            throw new CommandException(MESSAGE_NOT_SHOWING_PERSON_LIST);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAdd = lastShownList.get(targetIndex.getZeroBased());

        if (!(personToAdd instanceof Patient)) {
            throw new CommandException(MESSAGE_NOT_PATIENT);
        }

        Ward ward = null;

        for (String wardName : model.getWardNames()) {
            if (model.getWard(wardName).containsPatient((Patient) personToAdd)) {
                ward = model.getWard(wardName);
            }
        }

        if (ward == null) {
            throw new CommandException("Patient not found in Patientist");
        }

        Patient patientToAdd = (Patient) personToAdd;
        patientToAdd.deletePatientStatusDetails(targetStatusIndex);

        patientist.removePerson(personToAdd);
        model.addPatient(patientToAdd, ward);

        return new CommandResult(String.format(MESSAGE_DELETE_STATUS_SUCCESS, targetStatusIndex.getOneBased(),
                patientToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof DeletePatientStatusCommand // instanceof handles nulls
                   && targetIndex.equals(((DeletePatientStatusCommand) other).targetIndex)
                   && targetStatusIndex.equals(((DeletePatientStatusCommand) other).targetStatusIndex)); // state check
    }
}
