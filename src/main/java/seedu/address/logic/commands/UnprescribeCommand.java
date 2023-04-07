package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Medication;
import seedu.address.model.prescription.Prescription;

/**
 * Remove the medication of an existing person in the address book.
 */
public class UnprescribeCommand extends Command {

    public static final String COMMAND_WORD = "unprescribe";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a prescription from a person identified by their NRIC.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC "
            + PREFIX_MEDICATION + "MEDICATION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_MEDICATION + "paracetamol ";

    public static final String MESSAGE_DELETE_SUCCESS = "Prescription of patient deleted: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "This patient does not exist.";
    public static final String MESSAGE_INVALID_MEDICATION = "This patient is not prescribed to this medication.";

    private final Nric nric;
    private final Medication medication;

    /**
     * @param nric of the person in the filtered person list to edit
     * @param medication to remove
     */
    public UnprescribeCommand(Nric nric, Medication medication) {
        requireAllNonNull(nric, medication);

        this.nric = nric;
        this.medication = medication;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Patient patientToEdit = getPatientFromModel(model);
        Set<Prescription> patientPrescriptions = patientToEdit.getPrescriptions();
        List<Prescription> filteredPrescriptions = patientPrescriptions.stream()
                .filter(p -> p.getMedication().equals(medication))
                .collect(Collectors.toList());


        if (filteredPrescriptions.size() == 0) {
            throw new CommandException(MESSAGE_INVALID_MEDICATION);
        }
        patientPrescriptions.remove(filteredPrescriptions.get(0));

        Patient editedPerson = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(), patientToEdit.getNric(),
                patientToEdit.getAddress(), patientPrescriptions, patientToEdit.getTags(),
                patientToEdit.getPatientAppointments(), patientToEdit.getRole());

        model.setPerson(patientToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnprescribeCommand)) {
            return false;
        }

        // state check
        UnprescribeCommand e = (UnprescribeCommand) other;
        return nric.equals(e.nric)
                && medication.equals(e.medication);
    }

    private Patient getPatientFromModel(Model model) throws CommandException {
        if (!model.hasPatientByNric(nric)) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }
        Person personToEdit = model.getPersonByNric(nric);
        if (!personToEdit.isPatient()) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }
        return (Patient) personToEdit;
    }
}
