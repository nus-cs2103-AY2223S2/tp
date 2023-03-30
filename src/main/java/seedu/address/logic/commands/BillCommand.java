package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.List;
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
}
