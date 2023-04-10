package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.application.StatusPredicate;

/**
 * Finds and lists all internship application in record whose status
 * matches the specified keyword.
 * Keyword matching is case insensitive.
 */
public class FindStatusCommand extends FindCommand {
    private final StatusPredicate predicate;

    /**
     * Creates an FindStatusCommand to find the specified {@code InternshipStatus}.
     *
     * @param predicate The predicate containing status to be matched
     */
    public FindStatusCommand(StatusPredicate predicate) {
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
                || (other instanceof FindStatusCommand // instanceof handles nulls
                && predicate.equals(((FindStatusCommand) other).predicate)); // state check
    }
}
