package seedu.medinfo.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.medinfo.logic.parser.CliSyntax.PREFIX_WARD;
import static seedu.medinfo.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.function.Predicate;

import seedu.medinfo.commons.core.Messages;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.patient.Patient;

/**
 * Finds and lists all patients in MedInfo whose name contains any of the
 * argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_NAME
            + ": Finds all patients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " alice bob charlie\n"
            + COMMAND_WORD + " " + PREFIX_NRIC
            + ": Finds all patients whose NRIC matches any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NRIC + " S1234567A\n"
            + COMMAND_WORD + " " + PREFIX_STATUS
            + ": Finds all patients whose Status is any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STATUS + " GRAY GREEN\n"
            + COMMAND_WORD + " " + PREFIX_WARD
            + ": Finds all patients whose ward is any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_WARD + " A03\n";

    private final Predicate<Patient> predicate;

    /**
     * Constructs a {@code FindCommand} to display {@code Patients} which meet the given predicate.
     * @param predicate Condition to be met by the displayed {@code Patients}.
     */
    public FindCommand(Predicate<Patient> predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the {@code FindCommand} on the given model.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which is the result of the operation.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        int total = model.getFilteredPatientList().size();
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size(), total));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                        && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
