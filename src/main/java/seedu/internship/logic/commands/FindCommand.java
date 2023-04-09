package seedu.internship.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.internship.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.internship.commons.core.Messages;
import seedu.internship.model.Model;
import seedu.internship.model.internship.InternshipContainsKeywordsPredicate;

/**
 * Finds and lists all internships in InternBuddy which match with the given search terms
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internships containing at least one of the "
            + "inputs for every different field (COMPANY_NAME, ROLE, STATUS, DATE or TAG) that you have provided.\n"
            + "Fields: "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY_NAME]... "
            + "[" + PREFIX_ROLE + "ROLE]... "
            + "[" + PREFIX_STATUS + "STATUS]... "
            + "[" + PREFIX_DATE + "DATE]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Apple "
            + PREFIX_COMPANY_NAME + "Google "
            + PREFIX_ROLE + "Software Developer "
            + PREFIX_ROLE + "App Developer "
            + PREFIX_STATUS + "Applied "
            + PREFIX_STATUS + "Offered "
            + PREFIX_DATE + "2023-01-01 "
            + PREFIX_DATE + "2023-02-02 "
            + PREFIX_TAG + "Python "
            + PREFIX_TAG + "Java";

    private final InternshipContainsKeywordsPredicate predicate;

    public FindCommand(InternshipContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIP_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
