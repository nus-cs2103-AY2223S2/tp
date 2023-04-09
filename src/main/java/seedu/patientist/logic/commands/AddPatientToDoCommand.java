package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.List;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.patient.PatientToDo;
import seedu.patientist.model.ward.Ward;

/**
 * Adds a todo to the patient identified using its displayed index from the patientist book.
 */
public class AddPatientToDoCommand extends Command {
    public static final String COMMAND_WORD = "addpattodo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a todo to the patient identified by the index number used in"
            + " the displayed person list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_TODO + "TODO\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_TODO + "Take medicine";

    public static final String MESSAGE_ADD_TODO_SUCCESS = "Added todo %1$s to %2$s.";
    public static final String MESSAGE_NOT_PATIENT = "Person selected is not a Patient.";
    public static final String MESSAGE_NOT_SHOWING_PERSON_LIST = "Add Patient ToDo Command does not work with wards.";

    private final Index targetIndex;
    private final List<PatientToDo> toDos;

    /**
     * Creates an AddPatientToDoCommand to add the specified {@code PatientToDo} into the target Patient.
     */
    public AddPatientToDoCommand(Index targetIndex, List<PatientToDo> toDos) {
        this.targetIndex = targetIndex;
        this.toDos = toDos;
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
                break;
            }
        }

        if (ward == null) {
            throw new CommandException("Patient not found in Patientist");
        }

        Patient patientToAdd = (Patient) personToAdd;

        for (PatientToDo toDo : toDos) {
            patientToAdd.addPatientToDo(toDo);
        }

        patientist.removePerson(personToAdd);
        model.addPatient(patientToAdd, ward);

        return new CommandResult(String.format(MESSAGE_ADD_TODO_SUCCESS, toDos, patientToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPatientToDoCommand // instanceof handles nulls
                && targetIndex.equals(((AddPatientToDoCommand) other).targetIndex)
                && toDos.equals(((AddPatientToDoCommand) other).toDos)); // state check
    }
}
