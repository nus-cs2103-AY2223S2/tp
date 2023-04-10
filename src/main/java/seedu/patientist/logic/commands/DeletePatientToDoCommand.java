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
 * Deletes a todo from the patient identified using its displayed index from the patientist book.
 */
public class DeletePatientToDoCommand extends Command {
    public static final String COMMAND_WORD = "delpattodo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a todo identified by the index number used in the"
            + "Details panel to the patient identified by the index number used"
            + " in the displayed person list.\n"
            + "Parameters: "
            + "INDEX OF PATIENT (must be a positive integer) "
            + "INDEX OF TODO (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "1";

    public static final String MESSAGE_DELETE_TODO_SUCCESS = "Deleted Todo %1$s from %2$s.";
    public static final String MESSAGE_NOT_PATIENT = "Person selected is not a Patient.";
    public static final String MESSAGE_NOT_SHOWING_PERSON_LIST = "Delete Patient ToDo Command does not work on wards.";

    private final Index targetIndex;
    private final Index targetToDoIndex;

    /**
     * Creates an AddPatientToDoCommand to add the specified {@code PatientToDo} into the target Patient.
     */
    public DeletePatientToDoCommand(Index targetIndex, Index targetToDoIndex) {
        this.targetIndex = targetIndex;
        this.targetToDoIndex = targetToDoIndex;
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
        patientToAdd.deletePatientToDo(targetToDoIndex);

        patientist.removePerson(personToAdd);
        model.addPatient(patientToAdd, ward);

        return new CommandResult(String.format(MESSAGE_DELETE_TODO_SUCCESS, targetToDoIndex.getOneBased(),
                patientToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePatientToDoCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePatientToDoCommand) other).targetIndex)
                && targetToDoIndex.equals(((DeletePatientToDoCommand) other).targetToDoIndex)); // state check
    }
}
