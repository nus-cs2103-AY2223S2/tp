package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Prescription;

/**
 * Shows the user the Bill of a patient
 */
public class BillCommand extends Command {

    public static final String MESSAGE_INVALID_PERSON = "This patient does not exist.";
    public static final String MESSAGE_SUCCESS = "Bill: %.2f";
    public static final String COMMAND_WORD = "bill";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generates the cost of a patient's medication.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NRIC + "S1234567A ";

    public final Nric nric;

    /**
     * @param nric of the person whom bill we generate
     */
    public BillCommand(Nric nric) {
        requireNonNull(nric);
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Patient patientToEdit = getPatientFromModel(model);

        return new CommandResult(generateSuccessMessage(patientToEdit.getPrescriptions()));
    }

    /**
     * Generates a command execution success message that contains the bill of the Patient
     */
    private String generateSuccessMessage(Set<Prescription> prescriptions) {
        float sum = prescriptions.stream().map(prescription -> prescription.getCost().getValue())
                .reduce(Float.valueOf(0), (x, y) -> x + y);
        return String.format(MESSAGE_SUCCESS, sum);
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



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BillCommand)) {
            return false;
        }

        // state check
        return ((BillCommand) other).nric.equals(nric);
    }
}
