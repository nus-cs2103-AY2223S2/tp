package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.Model;
import seedu.address.model.person.InternshipStatus;
import seedu.address.model.person.StatusPredicate;

/**
 * Finds and lists all internship application in record whose status
 * matches the specified keyword.
 * Keyword matching is case insensitive.
 */
public class FindStatusCommand extends FindCommand {
    private final StatusPredicate predicate;

    public FindStatusCommand(StatusPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICATION_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }
}
