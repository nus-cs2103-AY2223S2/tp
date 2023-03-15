package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;
import static seedu.vms.logic.parser.CliSyntax.DELIMITER;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.vms.logic.parser.CliSyntax.PREFIX_VACCINATION;

import seedu.vms.logic.commands.Command;
import seedu.vms.logic.commands.CommandResult;
import seedu.vms.logic.commands.exceptions.CommandException;
import seedu.vms.model.Model;
import seedu.vms.model.patient.Patient;

/**
 * Adds a patient to the patient manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_GROUP = "patient";

    public static final String MESSAGE_USAGE = COMMAND_GROUP + " " + COMMAND_WORD
            + ": Adds a patient to the patient manager. "
            + "Parameters: "
            + DELIMITER + PREFIX_NAME + " NAME "
            + DELIMITER + PREFIX_PHONE + " PHONE "
            + DELIMITER + PREFIX_DOB + " DATE OF BIRTH "
            + DELIMITER + PREFIX_BLOODTYPE + " BLOOD TYPE "
            + "(" + DELIMITER + PREFIX_ALLERGY + " ALLERGY)..."
            + "(" + DELIMITER + PREFIX_VACCINATION + " VACCINATION)...\n"
            + "*Values in () are optional\n"
            + "Example: " + COMMAND_GROUP + " " + COMMAND_WORD + " "
            + DELIMITER + PREFIX_NAME + " John Doe "
            + DELIMITER + PREFIX_PHONE + " 98765432 "
            + DELIMITER + PREFIX_DOB + " 2001-03-19 "
            + DELIMITER + PREFIX_BLOODTYPE + " B+ "
            + DELIMITER + PREFIX_ALLERGY + " cat fur "
            + DELIMITER + PREFIX_ALLERGY + " pollen"
            + DELIMITER + PREFIX_VACCINATION + " covax";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the patient manager";

    private final Patient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addPatient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                        && toAdd.equals(((AddCommand) other).toAdd));
    }
}
