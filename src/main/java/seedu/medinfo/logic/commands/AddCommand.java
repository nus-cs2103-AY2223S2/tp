package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.patient.Patient;

/**
 * Adds a patient to MedInfo.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to MedInfo. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_NRIC + "NRIC "
            + "[" + PREFIX_STATUS + "STATUS] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe " + PREFIX_NRIC + "S1234567A " + PREFIX_STATUS + "GRAY \n";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in MedInfo";

    private final Patient toAdd;

    /**
     * Constructs a new {@code AddCommand} to add the specified {@code Patient}.
     */
    public AddCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    /**
     * Executes the {@code AddCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPatientNric(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

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
