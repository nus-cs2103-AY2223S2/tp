package seedu.address.logic.commands.parent;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.parent.ParentNameContainsKeywordsPredicate;

/**
 * A ParentFindCommand class for parent find command
 */
public class ParentFindCommand extends ParentCommand {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all parents whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final ParentNameContainsKeywordsPredicate predicate;
    /**
     * Creates a StudentFindCommand to find the specified {@code Student}
     * @param predicate
     */
    public ParentFindCommand(ParentNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredParentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredParentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParentFindCommand // instanceof handles nulls
                && predicate.equals(((ParentFindCommand) other).predicate)); // state check
    }
}
