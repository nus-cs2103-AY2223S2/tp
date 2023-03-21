package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipApplication;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all internship application in record whose CompanyName
 * and/or JobTitle contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internship applications whose "
            + "CompanyName and/or JobTitle contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " google software engineer intern";

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
                String.format(Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
