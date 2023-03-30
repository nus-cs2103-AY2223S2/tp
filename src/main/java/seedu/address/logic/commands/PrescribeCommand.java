package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
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

    //@@author Jeffry Lum-reused
    //Reused from https://nus-cs2103-ay2223s2.github.io/tp/tutorials/AddRemark.html
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

    public static final String MESSAGE_ADD_SUCCESS = "Prescription of patient added!: %1$s";
    public static final String MESSAGE_EDIT_SUCCESS = "Cost of prescription changed!: %1$s";
    public static final String MESSAGE_INVALID_PERSON = "This patient does not exist.";

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

    //@@author Jeffry Lum-reused
    //Reused from https://nus-cs2103-ay2223s2.github.io/tp/tutorials/AddRemark.html
    // with minor modifications
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (!model.hasPatientByNric(nric)) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        Patient patientToEdit = null;
        for (Person person : lastShownList) {
            if (person.isDoctor()) {
                continue;
            }
            if (person.getNric().equals(nric)) {
                patientToEdit = (Patient) person;
            }
        }

        if (patientToEdit == null) {
            throw new CommandException(MESSAGE_INVALID_PERSON);
        }

        boolean isNewPrescription = true;
        Set<Prescription> patientPrescriptions = patientToEdit.getPrescriptions();

        // todo use streams
        for (Prescription p : patientPrescriptions) {
            if (p.getMedication().equals(prescription.getMedication())) {
                isNewPrescription = false;
                patientPrescriptions.remove(p);
                break;
            }
        }

        patientPrescriptions.add(prescription);

        Patient editedPerson = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(), patientToEdit.getNric(),
                patientToEdit.getAddress(), patientPrescriptions,
                patientToEdit.getTags(),
                patientToEdit.getPatientAppointments(),
                patientToEdit.getRole());

        model.setPerson(patientToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson, isNewPrescription));
    }

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
