package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_AFTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_BEFORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all internship application in record whose CompanyName
 * and/or JobTitle contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": (1) Finds all internship applications whose "
            + "CompanyName and/or JobTitle contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " google software engineer intern\n"
            + "(2) Finds all internship applications whose status matches the specified status (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: " + "[" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_STATUS + "RECEIVED\n"
            + "(3) Finds all internship applications whose interview date is before, after "
            + "or between specified date(s)\n"
            + "(i) Parameters: " + PREFIX_DATE_BEFORE + "DATE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DATE_BEFORE + "12/31/2023 at 12:00 PM\n"
            + "(ii) Parameters: " + PREFIX_DATE_AFTER + "DATE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DATE_AFTER + "12/31/2023 at 12:00 PM\n"
            + "(iii) Parameters: " + PREFIX_DATE_FROM + "START_DATE " + PREFIX_DATE_TO + "END_DATE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DATE_FROM + "12/1/2023 at 1:00 PM "
            + PREFIX_DATE_TO + "12/31/2023 at 7:00 PM";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand() {
        this.predicate = null;
    }

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW,
                        model.getSortedFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
