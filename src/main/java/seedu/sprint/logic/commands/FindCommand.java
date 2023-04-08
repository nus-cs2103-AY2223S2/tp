package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.Model;
import seedu.sprint.model.application.ApplicationContainsKeywordsPredicate;

/**
 * Finds and lists all internship applications in internship book which has company names
 * containing any of the argument keywords. Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Formats: find keyword(s) "
            + "OR find [r/keyword(s)] [c/keyword(s)] [s/keyword(s)] \n"
            + "Example: " + COMMAND_WORD + " r/SWE Intern c/Meta s/Offered";

    private final ApplicationContainsKeywordsPredicate predicate;

    /**
     * Creates an FindCommand to find applications that fulfil the given predicate.
     */
    public FindCommand(ApplicationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        requireNonNull(model);
        model.updateFilteredApplicationList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_APPLICATIONS_LISTED_OVERVIEW,
                model.getFilteredApplicationList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
