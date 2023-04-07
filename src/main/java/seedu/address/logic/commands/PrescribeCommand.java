package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Prescription;

/**
 * Changes the medication of an existing person in the address book.
 */
public class PrescribeCommand extends Command {

    public static final String COMMAND_WORD = "prescribe";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Attaches a medication (and its cost) to a person identified by their NRIC. If the medication is "
            + "already attached, this edits the cost instead.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC "
            + PREFIX_MEDICATION + "MEDICATION "
            + PREFIX_COST + "COST\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_MEDICATION + "paracetamol "
            + PREFIX_COST + "3.5";

    public static final String MESSAGE_ADD_SUCCESS = "Prescription of patient added: %1$s";
    public static final String MESSAGE_EDIT_SUCCESS = "Cost of prescription changed: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "This patient does not exist.";
    public static final String MESSAGE_IDENTICAL_PRESCRIPTION = "This prescription already exists.";

    public final Nric nric;
    private final Prescription prescription;

    /**
     * @param nric of the person in the filtered person list to edit
     * @param prescription to add
     */
    public PrescribeCommand(Nric nric, Prescription prescription) {
        requireAllNonNull(nric, prescription);

        this.nric = nric;
        this.prescription = prescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Patient patientToEdit = getPatientFromModel(model);
        boolean isNewPrescription = !hasOldPrescription(patientToEdit);

        Set<Prescription> patientPrescriptions = patientToEdit.getPrescriptions();
        if (!isNewPrescription) {
            removeOldPrescription(patientPrescriptions);
        }
        patientPrescriptions.add(prescription);

        Patient editedPerson = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(), patientToEdit.getNric(),
                patientToEdit.getAddress(), patientPrescriptions, patientToEdit.getTags(),
                patientToEdit.getPatientAppointments(), patientToEdit.getRole());

        model.setPerson(patientToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson, isNewPrescription));
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

    private boolean hasOldPrescription(Patient patient) {
        long matches = patient.getPrescriptions()
                .stream()
                .filter(p -> p.getMedication().equals(prescription.getMedication()))
                .count();
        assert matches < 2 : "For each person, there should not be multiple prescriptions with"
                + "the same medication!";
        return matches == 1;
    }

    private void removeOldPrescription(Set<Prescription> prescriptions) throws CommandException {
        Prescription oldPrescription = prescriptions
                .stream()
                .filter(p -> p.getMedication().equals(prescription.getMedication()))
                .findFirst()
                .get();
        if (oldPrescription.equals(prescription)) {
            throw new CommandException(MESSAGE_IDENTICAL_PRESCRIPTION);
        }
        prescriptions.remove(oldPrescription);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://nus-cs2103-ay2223s2.github.io/tp/tutorials/AddRemark.html
    // with minor modifications
    /**
     * Generates a command execution success message when prescription is changed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit, boolean isNewPrescription) {
        return isNewPrescription
                ? String.format(MESSAGE_ADD_SUCCESS, personToEdit)
                : String.format(MESSAGE_EDIT_SUCCESS, personToEdit);
    }
    //@@author Jeffry Lum

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PrescribeCommand)) {
            return false;
        }

        // state check
        PrescribeCommand e = (PrescribeCommand) other;
        return nric.equals(e.nric)
                && prescription.equals(e.prescription);
    }


}
