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
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internships containing at least one of the "
            + "inputs from every different type of field (name, role, status, date or tag) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_COMPANY_NAME + "COMPANY_NAME]... "
            + "[" + PREFIX_ROLE + "ROLE]... "
            + "[" + PREFIX_STATUS + "STATUS]... "
            + "[" + PREFIX_DATE + "DATE]..."
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_COMPANY_NAME + "apple "
            + PREFIX_COMPANY_NAME + "google "
            + PREFIX_ROLE + "software "
            + PREFIX_ROLE + "developer "
            + PREFIX_STATUS + "applied "
            + PREFIX_STATUS + "offered "
            + PREFIX_DATE + "2023-01-01 "
            + PREFIX_DATE + "2023-02-02 "
            + PREFIX_TAG + "python "
            + PREFIX_TAG + "java";

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
