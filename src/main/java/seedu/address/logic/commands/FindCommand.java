package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import java.util.function.Predicate;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.NricContainsKeywordsPredicate;
import seedu.address.model.patient.StatusContainsKeywordsPredicate;
import seedu.address.model.patient.Status;

/**
 * Finds and lists all patients in MedInfo whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */

@SuppressWarnings({"unchecked"})
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
            + "Example: " + COMMAND_WORD + " " + PREFIX_STATUS + " GRAY GREEN\n";


    private final Predicate predicate;

    public FindCommand(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
