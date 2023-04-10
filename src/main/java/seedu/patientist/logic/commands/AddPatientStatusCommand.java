package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.commons.core.index.Index;
import seedu.patientist.logic.commands.exceptions.CommandException;
import seedu.patientist.model.Model;
import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.patient.PatientStatusDetails;
import seedu.patientist.model.ward.Ward;

/**
 * Adds a status to the patient identified using it's displayed index from the patientist book.
 */
public class AddPatientStatusCommand extends Command {
    public static final String COMMAND_WORD = "addpatstatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                                               + ": Adds a status to the patient identified by the index number used in"
                                               + " the displayed person list.\n"
                                               + "Parameters: "
                                               + "INDEX (must be a positive integer) "
                                               + PREFIX_STATUS + "STATUS\n"
                                               + "Example: " + COMMAND_WORD + " "
                                               + "1 "
                                               + PREFIX_STATUS + "Feeling fine";

    public static final String MESSAGE_ADD_STATUS_SUCCESS = "Added status %1$s to %2$s.";
    public static final String MESSAGE_NOT_PATIENT = "Person selected is not a Patient.";
    public static final String MESSAGE_NOT_SHOWING_PERSON_LIST = "Add Patient Status Command does not work with wards.";

    private final Index targetIndex;
    private final List<PatientStatusDetails> statusDetails;

    /**
     * Creates an AddPatientStatusCommand to add the specified {@code PatientStatusDeatils} into the target Patient.
     */
    public AddPatientStatusCommand(Index targetIndex, List<PatientStatusDetails> statusDetails) {
        this.targetIndex = targetIndex;
        this.statusDetails = statusDetails;
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

        for (PatientStatusDetails statusDetail : statusDetails) {
            patientToAdd.addPatientStatusDetails(statusDetail);
        }

        patientist.removePerson(personToAdd);
        model.addPatient(patientToAdd, ward);

        return new CommandResult(String.format(MESSAGE_ADD_STATUS_SUCCESS, statusDetails, patientToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof AddPatientStatusCommand // instanceof handles nulls
                   && targetIndex.equals(((AddPatientStatusCommand) other).targetIndex)
                   && statusDetails.equals(((AddPatientStatusCommand) other).statusDetails)); // state check
    }
}
