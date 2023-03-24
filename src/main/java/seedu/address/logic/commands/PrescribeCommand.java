package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Medication;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Changes the medication of an existing person in the address book.
 */
public class PrescribeCommand extends Command {

    public static final String COMMAND_WORD = "prescribe";

    //@@author Jeffry Lum-reused
    //Reused from https://nus-cs2103-ay2223s2.github.io/tp/tutorials/AddRemark.html
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes current medication information of the person identified "
            + "by their NRIC. "
            + "Existing medication information will be overwritten by input.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC "
            + PREFIX_MEDICATION + "[MEDICATION] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NRIC + "S1234567A "
            + PREFIX_MEDICATION + "1 paracetamol";

    public static final String MESSAGE_ADD_PRESCRIBE_SUCCESS = "Added medication to Person: %1$s";
    public static final String MESSAGE_DELETE_PRESCRIBE_SUCCESS = "Removed medication from Person: %1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_SELECTED_DOCTOR_FAILURE = "You have selected a doctor, this command is only "
            + "applicable for doctors.";
    public static final String MESSAGE_INVALID_PERSON = "This patient does not exist.";

    public final Nric nric;
    private final Medication medication;

    /**
     * @param nric of the person in the filtered person list to edit
     * @param medication to be changed to
     */
    public PrescribeCommand(Nric nric, Medication medication) {
        requireAllNonNull(nric, medication);

        this.nric = nric;
        this.medication = medication;
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

        Patient editedPerson = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(), patientToEdit.getNric(),
                patientToEdit.getAddress(), medication, patientToEdit.getTags(), patientToEdit.getPatientAppointments()
        );

        model.setPerson(patientToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the medication is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !medication.value.isEmpty() ? MESSAGE_ADD_PRESCRIBE_SUCCESS : MESSAGE_DELETE_PRESCRIBE_SUCCESS;
        return String.format(message, personToEdit);
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
                && medication.equals(e.medication);
    }

}
