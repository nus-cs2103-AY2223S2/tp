package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.ApplicationModel;
import seedu.sprint.model.application.NameContainsKeywordsPredicate;

/**
 * Finds and lists all internship applications in internship book which has company names
 * containing any of the argument keywords. Keyword matching is case-insensitive.
 */
public class FindApplicationCommand extends ApplicationCommand {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Formats: find keyword(s) "
            + "OR find [r/keyword(s)] [c/keyword(s)] [s/keyword(s)] \n"
            + "Example: " + COMMAND_WORD + " Google c/Meta";

    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates an FindApplicationCommand to find applications that fulfil the given predicate.
     */
    public FindApplicationCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(ApplicationModel model, CommandHistory commandHistory) {
        requireNonNull(model);
        model.updateFilteredApplicationList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_APPLICATIONS_LISTED_OVERVIEW,
                model.getFilteredApplicationList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindApplicationCommand // instanceof handles nulls
                && predicate.equals(((FindApplicationCommand) other).predicate)); // state check
    }
}
