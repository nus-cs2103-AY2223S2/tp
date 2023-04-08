package seedu.techtrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.techtrack.commons.core.Messages;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.role.NameContainsKeywordsPredicate;

/**
 * Finds and lists all roles in role book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class NameCommand extends Command {

    public static final String COMMAND_WORD = "name";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all roles whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n \n"
            + "Example: " + COMMAND_WORD + " Software Engineer Analyst";

    private final NameContainsKeywordsPredicate predicate;

    public NameCommand(NameContainsKeywordsPredicate predicate) {
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
                || (other instanceof NameCommand // instanceof handles nulls
                && predicate.equals(((NameCommand) other).predicate)); // state check
    }
}
