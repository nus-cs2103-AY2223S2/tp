package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.techtrack.commons.core.Messages;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.role.TagContainsKeywordsPredicate;

/**
 * Finds and lists all Roles in role book whose tags contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tags whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n \n"
            + "Parameters: TAG [MORE_KEYWORDS]...\n \n"
            + "Example: " + COMMAND_WORD + " Tech";

    private final TagContainsKeywordsPredicate predicate;
    public TagCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoleList(predicate);
        return new CommandResult<>(
                String.format(Messages.MESSAGE_ROLES_LISTED_OVERVIEW, model.getFilteredRoleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && predicate.equals(((TagCommand) other).predicate)); // state check
    }
}
