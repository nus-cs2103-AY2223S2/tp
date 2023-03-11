package seedu.vms.logic.commands.patient;

import static java.util.Objects.requireNonNull;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the patient manager. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_DOB + "DATE OF BIRTH "
            + PREFIX_BLOODTYPE + "BLOOD TYPE "
            + "(" + PREFIX_ALLERGY + "ALLERGY)...\n"
             + PREFIX_VACCINATION + "ALLERGY...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_DOB + "2001-03-19 "
            + PREFIX_BLOODTYPE + "B+ "
            + PREFIX_ALLERGY + "cat fur "
            + PREFIX_ALLERGY + "pollen"
            + PREFIX_VACCINATION + "covax";

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
