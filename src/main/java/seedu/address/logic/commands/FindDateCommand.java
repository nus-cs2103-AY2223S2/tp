package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.application.DatePredicate;

/**
 * Finds and lists all internship application in record whose {@code InterviewDate}
 * is before, after or between specified date(s).
 * The date specified is inclusive.
 */
public class FindDateCommand extends FindCommand {
    private final DatePredicate predicate;

    /**
     * Creates an FindDateCommand to find the internship application with {@code InternshipDate}
     * matches the given predicate.
     *
     * @param predicate The predicate containing date condition to be matched
     */
    public FindDateCommand(DatePredicate predicate) {
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
                || (other instanceof FindDateCommand // instanceof handles nulls
                && predicate.equals(((FindDateCommand) other).predicate)); // state check
    }
}
